package com.cederj.uff.tcc.stockmaster.services.transaction;

import com.cederj.uff.tcc.stockmaster.dtos.transaction.CreatedTransactionDto;
import com.cederj.uff.tcc.stockmaster.models.transaction.Transaction;
import com.cederj.uff.tcc.stockmaster.services.GenericService;

import java.util.List;

public interface TransactionService extends GenericService<Transaction> {
    List<Transaction> findAllTranscationsForInventoryId(Long inventoryId);
    void createTransactionEntry(CreatedTransactionDto data, Long idInventory);
    void createTransactionOutput(CreatedTransactionDto data, Long idInventory);

}
