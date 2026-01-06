package com.marouane.rechargeservice.model.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountFeignPostDTO {
    private Long accountId;
    private Double balance;
}
