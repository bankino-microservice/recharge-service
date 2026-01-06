package com.marouane.rechargeservice.model.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marouane.rechargeservice.model.enumeration.feign.StatusCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFeignGetDTO {
    @JsonProperty("id")
    private Long accountId;

    @JsonProperty("solde")
    private Double balance;

    @JsonProperty("status")
    private StatusCompte statusCompte;
}
