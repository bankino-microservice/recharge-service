package com.marouane.rechargeservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "compte_id")
    private Long accountId;

    @Column(name = "numero_telephone")
    private String phoneNumber;

    @Column(name = "offre_id")
    private Integer offreId;

    @Column(name = "montant")
    private Integer amount;
}
