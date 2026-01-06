package com.marouane.rechargeservice.controller;

import com.marouane.rechargeservice.model.dto.RechargeRequestDTO;
import com.marouane.rechargeservice.model.dto.RechargeResponseDTO;
import com.marouane.rechargeservice.pyload.ApiResponse;
import com.marouane.rechargeservice.service.RechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/recharges")
@RequiredArgsConstructor
public class RechargeController {
    private final RechargeService rechargeService;
    @PostMapping
    public ApiResponse<RechargeResponseDTO> createRecharge(@RequestBody RechargeRequestDTO rechargeRequestDTO) {
        try {
            RechargeResponseDTO rechargeResponseDTO = rechargeService.processRecharge(rechargeRequestDTO);
            return ApiResponse.<RechargeResponseDTO>builder()
                    .code("200")
                    .status(HttpStatus.OK.value())
                    .message("Recharge effectuée avec succès")
                    .data(rechargeResponseDTO)
                    .errors(new HashMap<>())
                    .build();
        } catch (Exception e) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("exception", e.getMessage());

            return ApiResponse.<RechargeResponseDTO>builder()
                    .code("500")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Erreur lors de la création de la recharge")
                    .data(null)
                    .errors(errors)  // <-- ici on met la map avec le message
                    .build();
        }
    }
    @GetMapping
    public ApiResponse<List<RechargeResponseDTO>> getAllRecharges() {
        try {
            List<RechargeResponseDTO> recharges = rechargeService.getAllRechargesParCompte(null);
            return ApiResponse.<List<RechargeResponseDTO>>builder()
                    .code("200")
                    .status(HttpStatus.OK.value())
                    .message("Recharges récupérées avec succès")
                    .data(recharges)
                    .errors(new HashMap<>())
                    .build();
        } catch (Exception e) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("exception", e.getMessage());

            return ApiResponse.<List<RechargeResponseDTO>>builder()
                    .code("500")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Erreur lors de la récupération des recharges")
                    .data(null)
                    .errors(errors)
                    .build();
        }
    }


}
