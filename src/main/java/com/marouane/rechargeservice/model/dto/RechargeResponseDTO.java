package com.marouane.rechargeservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RechargeResponseDTO {
    private Long id;
    private Long accountId;
    private String phoneNumber;
    private Integer offreId;
    private Integer amount;
}
