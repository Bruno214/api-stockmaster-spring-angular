package com.cederj.uff.tcc.stockmaster.Enums.transaction;

public enum CommercialTypeEntity {
    CLIENTE(0),
    SUPPLIER(1);

    private final Integer numberParticipantType;

    CommercialTypeEntity(Integer numberParticipantType) {
        this.numberParticipantType = numberParticipantType;
    }

    public Integer getNumberParticipantType() {
        return numberParticipantType;
    }
}
