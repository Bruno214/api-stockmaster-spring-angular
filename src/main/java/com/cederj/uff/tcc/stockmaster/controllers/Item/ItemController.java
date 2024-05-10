package com.cederj.uff.tcc.stockmaster.controllers.Item;

import com.cederj.uff.tcc.stockmaster.ServicesImpl.itemImpl.ItemServiceImpl;
import com.cederj.uff.tcc.stockmaster.dtos.item.CreatedItemDto;
import com.cederj.uff.tcc.stockmaster.dtos.item.UpdateItemDto;
import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.vos.item.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemServiceImpl itemServiceImpl;

    @Autowired
    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    @GetMapping("/allItems/{inventoryId}")
    public ResponseEntity<List<ItemVO>> getAllItemsByInventoryId(@PathVariable("inventoryId") Long id) {
        List<Item> listItens = this.itemServiceImpl.findAllForInventoryId(id);
        List<ItemVO> listaResponse = listItens.stream().map(item -> new ItemVO(item.getId(), item.getName(), item.getUnitPrice(), item.getAmount(), item.getPoster())).toList();

        return ResponseEntity.ok(listaResponse);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemVO> findItemById(@PathVariable("itemId") Long idItem){
        Item item =  this.itemServiceImpl.findItemById(idItem);
        ItemVO itemVO = new ItemVO(item.getId(), item.getName(), item.getUnitPrice(), item.getAmount(), item.getPoster());
        return ResponseEntity.status(HttpStatus.OK).body(itemVO);
    }

    @PostMapping("/created/{inventoryId}")
    public ResponseEntity<Void> createdInventory(@PathVariable("inventoryId") Long idInventory, @RequestBody CreatedItemDto data) {
        this.itemServiceImpl.save(data, idInventory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("itemId") Long itemId){
        this.itemServiceImpl.delete(itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<Void> updateInventoryById(@PathVariable("itemId") Long itemId, @RequestBody UpdateItemDto data) {
        this.itemServiceImpl.updateItemById(itemId, data);
        return ResponseEntity.ok().build();
    }
}


