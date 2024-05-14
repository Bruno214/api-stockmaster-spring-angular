package com.cederj.uff.tcc.stockmaster.Enums.transaction;

public enum TransactionType {
    ENTRADA(0),
    SAIDA(1);

    private final Integer transationType;

    TransactionType(Integer transationType) {
        this.transationType = transationType;
    }

    public Integer getTransationType() {
        return transationType;
    }
}
