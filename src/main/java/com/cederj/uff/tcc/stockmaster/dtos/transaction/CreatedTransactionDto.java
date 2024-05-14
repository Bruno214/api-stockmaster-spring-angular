package com.cederj.uff.tcc.stockmaster.dtos.transaction;

import com.cederj.uff.tcc.stockmaster.Enums.transaction.TransactionType;

public record CreatedTransactionDto(TransactionType transactionType, Integer quantityItem, String observation, CreateItemWithTransactionDto item) {
}
