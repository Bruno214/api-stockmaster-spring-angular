package com.cederj.uff.tcc.stockmaster.controller.inventory;

import com.cederj.uff.tcc.stockmaster.DTO.inventory.CreatedInventoryDTO;
import com.cederj.uff.tcc.stockmaster.VO.inventory.InventoryVO;
import com.cederj.uff.tcc.stockmaster.model.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.service.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<List<InventoryVO>> getAllInventoriesById(@PathVariable("inventoryId") Long id) {
        List<Inventory> listInventories = this.inventoryService.findAllByUserId(id);
        List<InventoryVO> listaResponse = listInventories.stream().map(i -> new InventoryVO(i.getName(), i.getDescription())).toList();
        return ResponseEntity.ok(listaResponse);
    }

    @PostMapping("/created/{userId}")
    public ResponseEntity<Void> createdInventory(@PathVariable("userId") Long idUser, @RequestBody CreatedInventoryDTO data) {
        this.inventoryService.createdInventory(idUser, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
