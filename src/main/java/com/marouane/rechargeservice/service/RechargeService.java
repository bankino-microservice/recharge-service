package com.marouane.rechargeservice.service;

import com.marouane.rechargeservice.exception.*;
import com.marouane.rechargeservice.feign.AccountFeignClient;
import com.marouane.rechargeservice.model.dto.RechargeRequestDTO;
import com.marouane.rechargeservice.model.dto.RechargeResponseDTO;
import com.marouane.rechargeservice.model.dto.feign.AccountFeignGetDTO;
import com.marouane.rechargeservice.model.dto.feign.AccountFeignPostDTO;
import com.marouane.rechargeservice.model.dto.feign.AccountResponseWrapper;
import com.marouane.rechargeservice.model.entity.Recharge;
import com.marouane.rechargeservice.model.enumeration.feign.StatusCompte;
import com.marouane.rechargeservice.model.mapper.RechargeMapper;
import com.marouane.rechargeservice.repository.RechargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RechargeService {

    private final RechargeRepository rechargeRepository;
    private final AccountFeignClient accountFeignClient;
    private final RechargeMapper rechargeMapper;

    private static final List<Integer> IAM_OFFRES = List.of(1, 2, 3, 4, 5, 6, 9, 22, 30, 66);
    private static final List<Integer> INWI_OFFRES = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 30, 33, 77);
    private static final List<Integer> ORANGE_OFFRES = List.of(1, 2, 3, 4, 5, 6, 8, 22, 30, 33, 66, 77);

    private Boolean validerOffreRecharge(RechargeRequestDTO dto) {
        int offreId = dto.getOffreId();
        return switch (dto.getOperator()) {
            case ORANGE -> ORANGE_OFFRES.contains(offreId);
            case INWI -> INWI_OFFRES.contains(offreId);
            case IAM -> IAM_OFFRES.contains(offreId);
            default -> false;
        };
    }

    public List<RechargeResponseDTO> getAllRechargesParCompte(Long compteId) {
        List<RechargeResponseDTO> rechargeResponseDTOS;
        try {
            rechargeResponseDTOS = rechargeRepository.findByAccountId(compteId)
                    .stream()
                    .map(rechargeMapper::toRechargeResponseDTO)
                    .toList();
            return rechargeResponseDTOS;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erreur d'accès à la base de données lors de la récupération des recharges", e);
        }
    }

    public RechargeResponseDTO processRecharge(RechargeRequestDTO rechargeRequestDTO) {

        // 1️⃣ Validation de l'offre
        if (!validerOffreRecharge(rechargeRequestDTO)) {
            throw new InvalidRechargeOfferException(
                    rechargeRequestDTO.getOperator(),
                    rechargeRequestDTO.getOffreId());
        }

        // 2️⃣ Appel au service Account
        AccountFeignGetDTO account = callAccountService(rechargeRequestDTO.getAccountId());

        // 3️⃣ Vérification du solde avec compareTo et parsing
        Double montant = Double.parseDouble(rechargeRequestDTO.getAmount());
        if (account.getBalance().compareTo(montant) < 0) {
            throw new SoldeInsuffisantException("Solde insuffisant pour effectuer la recharge.");
        }

        // 4️⃣ Vérification du status du compte
        if (!account.getStatusCompte().equals(StatusCompte.ACTIF)) {
            throw new AccountBlockedOrSuspended("Compte bloqué ou suspendu, recharge impossible.");
        }

        // 5️⃣ Débit du compte
        debitAccount(rechargeRequestDTO.getAccountId(), account.getBalance() - montant);

        // 6️⃣ Sauvegarde de la recharge
        Recharge recharge = rechargeRepository.save(
                rechargeMapper.fromRechargeRequestToRecharge(rechargeRequestDTO));

        return rechargeMapper.toRechargeResponseDTO(recharge);
    }

    private AccountFeignGetDTO callAccountService(Long accountId) {
        try {
            AccountResponseWrapper response = accountFeignClient.getAccountById(accountId);
            if (response == null || response.getCompte() == null) {
                throw new AccountNotFoundException("Compte introuvable pour l'ID : " + accountId);
            }
            return response.getCompte();
        } catch (feign.FeignException.NotFound e) {
            throw new AccountNotFoundException("Compte introuvable pour l'ID : " + accountId);
        } catch (feign.RetryableException e) {
            throw new AccountFeignException("Impossible de contacter le service Account, réessayez plus tard", e);
        } catch (feign.FeignException e) {
            throw new AccountFeignException("Erreur HTTP depuis le service Account: " + e.status(), e);
        } catch (Exception e) {
            throw new AccountFeignException("Erreur inconnue lors de l'appel à AccountService", e);
        }
    }

    private void debitAccount(Long accountId, Double newBalance) {
        AccountFeignPostDTO updateDTO = AccountFeignPostDTO.builder()
                .accountId(accountId)
                .balance(newBalance)
                .build();

        try {
            accountFeignClient.updateAccountBalance(updateDTO);
        } catch (feign.RetryableException e) {
            throw new AccountFeignException("Impossible de contacter le service Account, réessayez plus tard", e);
        } catch (feign.FeignException e) {
            throw new AccountFeignException("Erreur HTTP depuis le service Account: " + e.status(), e);
        } catch (Exception e) {
            throw new AccountFeignException("Erreur inconnue lors de l'appel à AccountService", e);
        }
    }
}
