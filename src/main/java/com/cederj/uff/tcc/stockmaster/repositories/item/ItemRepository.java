package com.cederj.uff.tcc.stockmaster.repositories.item;

import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends GenericRepository<Item> {
    List<Item> findAllByInventoryId(Long id);
}
