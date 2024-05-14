package com.cederj.uff.tcc.stockmaster.repositories.item;

import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends GenericRepository<Item> {
    List<Item> findAllByInventoryId(Long id);
    @Query("SELECT i FROM Item i WHERE LOWER(i.name) = LOWER(:name) AND i.inventory.id = :inventoryId")
    Optional<Item> findByNameIgnoreCaseAndInventoryId(@Param("name") String name, @Param("inventoryId") Long inventoryId);
}
