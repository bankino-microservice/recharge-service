package com.marouane.rechargeservice.exception;

import com.marouane.rechargeservice.model.enumeration.Operator;

public class InvalidRechargeOfferException extends RuntimeException {
    private final Operator operator;
    private final Integer offreId;
    public InvalidRechargeOfferException(Operator operator, Integer offreId) {
        super("Offre de recharge invalide : code " + offreId + " pour l'opérateur " + operator);
        this.operator = operator;
        this.offreId = offreId;
    }
    public Operator getOperator() {
        return operator;
    }

    public Integer getOffreId() {
        return offreId;
    }
}
