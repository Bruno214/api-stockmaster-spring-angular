package com.cederj.uff.tcc.stockmaster.repositories.inventory;

import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepositoy extends GenericRepository<Inventory> {
    List<Inventory> findAllByUserId(Long userId);
}
