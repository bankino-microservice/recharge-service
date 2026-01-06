package com.marouane.rechargeservice.model.mapper;

import com.marouane.rechargeservice.model.dto.RechargeDTO;
import com.marouane.rechargeservice.model.dto.RechargeRequestDTO;
import com.marouane.rechargeservice.model.dto.RechargeResponseDTO;
import com.marouane.rechargeservice.model.entity.Recharge;
import com.marouane.rechargeservice.utils.PhoneUtils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RechargeMapper {
    PhoneUtils phoneUtils = new PhoneUtils();
    public RechargeResponseDTO toRechargeResponseDTO(Recharge recharge);
    default public Recharge fromRechargeRequestToRecharge(RechargeRequestDTO rechargeRequestDTO) {
        return Recharge.builder()
                .accountId(rechargeRequestDTO.getAccountId())
                .phoneNumber(PhoneUtils.normalize(rechargeRequestDTO.getPhoneNumber()))
                .offreId(rechargeRequestDTO.getOffreId())
                .amount(Integer.valueOf(rechargeRequestDTO.getAmount()))
                .build();
    }
}
