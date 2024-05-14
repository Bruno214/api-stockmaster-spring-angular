package com.cederj.uff.tcc.stockmaster.services.inventory;

import com.cederj.uff.tcc.stockmaster.dtos.inventory.CreatedInventoryDto;
import com.cederj.uff.tcc.stockmaster.dtos.inventory.UpdateInventoryDto;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.services.GenericService;

import java.util.List;

public interface InventoryService extends GenericService<Inventory> {
    List<Inventory> findAllByUserId(Long userId);
    Inventory findInventoryById(Long inventoryId);
    void updateInventoryById(Long id, UpdateInventoryDto dado);
    void save(CreatedInventoryDto data, Long idUser);
    void delete(Long id);
}
