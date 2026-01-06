package com.marouane.rechargeservice.controller;

import com.marouane.rechargeservice.model.dto.RechargeRequestDTO;
import com.marouane.rechargeservice.model.dto.RechargeResponseDTO;
import com.marouane.rechargeservice.payload.ApiResponse;
import com.marouane.rechargeservice.service.RechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/recharges")
@RequiredArgsConstructor
public class RechargeController {
    private final RechargeService rechargeService;

    @PostMapping
    public ApiResponse<RechargeResponseDTO> createRecharge(@RequestBody RechargeRequestDTO rechargeRequestDTO) {
        RechargeResponseDTO rechargeResponseDTO = rechargeService.processRecharge(rechargeRequestDTO);
        return ApiResponse.<RechargeResponseDTO>builder()
                .code("200")
                .status(HttpStatus.OK.value())
                .message("Recharge effectuée avec succès")
                .data(rechargeResponseDTO)
                .errors(new HashMap<>())
                .build();
    }

    @GetMapping
    public ApiResponse<List<RechargeResponseDTO>> getAllRecharges() {
        List<RechargeResponseDTO> recharges = rechargeService.getAllRechargesParCompte(null);
        return ApiResponse.<List<RechargeResponseDTO>>builder()
                .code("200")
                .status(HttpStatus.OK.value())
                .message("Recharges récupérées avec succès")
                .data(recharges)
                .errors(new HashMap<>())
                .build();
    }

    @GetMapping("/compte/{compteId}")
    public ApiResponse<List<RechargeResponseDTO>> getRechargesByCompte(@PathVariable Long compteId) {
        List<RechargeResponseDTO> recharges = rechargeService.getAllRechargesParCompte(compteId);
        return ApiResponse.<List<RechargeResponseDTO>>builder()
                .code("200")
                .status(HttpStatus.OK.value())
                .message("Recharges récupérées avec succès pour le compte " + compteId)
                .data(recharges)
                .errors(new HashMap<>())
                .build();
    }

}
