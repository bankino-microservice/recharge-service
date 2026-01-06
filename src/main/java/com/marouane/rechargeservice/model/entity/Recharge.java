package com.marouane.rechargeservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recharge {
    @Id
    private UUID id;
    private Long accountId;
    private String phoneNumber;
    private Integer offreId;
    private Integer amount;
}
