package com.cederj.uff.tcc.stockmaster.repository.inventory;

import com.cederj.uff.tcc.stockmaster.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepositoy extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByUserId(Long id);
}
