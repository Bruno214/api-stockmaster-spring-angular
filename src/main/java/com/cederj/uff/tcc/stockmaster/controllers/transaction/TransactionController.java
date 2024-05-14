package com.cederj.uff.tcc.stockmaster.controllers.transaction;

import com.cederj.uff.tcc.stockmaster.ServicesImpl.transation.TransactionImpl;
import com.cederj.uff.tcc.stockmaster.dtos.transaction.CreatedTransactionDto;
import com.cederj.uff.tcc.stockmaster.models.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionImpl transactionImpl;

    @Autowired
    public TransactionController(TransactionImpl transactionImpl) {
        this.transactionImpl = transactionImpl;
    }

    @GetMapping("/allTransactions/{inventoryId}")
    public ResponseEntity<List<Transaction>> getAllInventoriesByIdInventory(@PathVariable("inventoryId") Long id) {
        List<Transaction> transations = this.transactionImpl.findAllTranscationsForInventoryId(id);
        return ResponseEntity.ok(transations);
    }

    @PostMapping("/created/entry/{inventoryId}")
    public ResponseEntity<Void> createdEntryTransaction(@PathVariable("inventoryId") Long idInventory, @RequestBody CreatedTransactionDto data) {
        this.transactionImpl.createTransactionEntry(data, idInventory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/created/output/{inventoryId}")
    public ResponseEntity<Void> createdOutputTransaction(@PathVariable("inventoryId") Long idInventory, @RequestBody CreatedTransactionDto data) {
        this.transactionImpl.createTransactionOutput(data, idInventory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
