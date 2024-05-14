package com.cederj.uff.tcc.stockmaster.repositories.transaction;

import com.cederj.uff.tcc.stockmaster.models.transaction.Transaction;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends GenericRepository<Transaction> {
    List<Transaction> findAllByInventoryId(Long id);
}