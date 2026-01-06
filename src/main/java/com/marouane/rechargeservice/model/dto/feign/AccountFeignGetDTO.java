package com.marouane.rechargeservice.model.dto.feign;

import com.marouane.rechargeservice.model.enumeration.feign.StatusCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFeignGetDTO {
    private Long accountId;
    private Double balance;
    private StatusCompte statusCompte;
}
