package com.cederj.uff.tcc.stockmaster.services.item;

import com.cederj.uff.tcc.stockmaster.dtos.item.CreatedItemDto;
import com.cederj.uff.tcc.stockmaster.dtos.item.UpdateItemDto;
import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.services.GenericService;

import java.util.List;

public interface ItemService extends GenericService<Item> {
    List<Item> findAllForInventoryId(Long inventoryId);
    void save(CreatedItemDto data, Long idInventory);
    Item findItemById(Long id);
    void delete(Long id);
    void updateItemById(Long id, UpdateItemDto dado);
}
