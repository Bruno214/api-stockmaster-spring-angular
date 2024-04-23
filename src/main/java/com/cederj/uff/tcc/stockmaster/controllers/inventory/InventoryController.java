package com.cederj.uff.tcc.stockmaster.controllers.inventory;

import com.cederj.uff.tcc.stockmaster.ServicesImpl.inventoryimpl.InventoryServiceImpl;
import com.cederj.uff.tcc.stockmaster.dtos.inventory.CreatedInventoryDto;
import com.cederj.uff.tcc.stockmaster.vos.inventory.InventoryVO;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryServiceImpl inventoryServiceImpl;

    @Autowired
    public InventoryController(InventoryServiceImpl inventoryServiceImpl) {
        this.inventoryServiceImpl = inventoryServiceImpl;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<InventoryVO>> getAllInventoriesById(@PathVariable("userId") Long id) {
        List<Inventory> listInventories = this.inventoryServiceImpl.findAllByUserId(id);
        List<InventoryVO> listaResponse = listInventories.stream().map(i -> new InventoryVO(i.getName(), i.getDescription())).toList();
        return ResponseEntity.ok(listaResponse);
    }

    @PostMapping("/created/{userId}")
    public ResponseEntity<Void> createdInventory(@PathVariable("userId") Long idUser, @RequestBody CreatedInventoryDto data) {
        this.inventoryServiceImpl.save(data, idUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
