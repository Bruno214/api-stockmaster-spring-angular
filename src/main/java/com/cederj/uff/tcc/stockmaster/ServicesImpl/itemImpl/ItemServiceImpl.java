package com.cederj.uff.tcc.stockmaster.ServicesImpl.itemImpl;

import com.cederj.uff.tcc.stockmaster.dtos.item.CreatedItemDto;
import com.cederj.uff.tcc.stockmaster.dtos.item.UpdateItemDto;
import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import com.cederj.uff.tcc.stockmaster.repositories.inventory.InventoryRepositoy;
import com.cederj.uff.tcc.stockmaster.repositories.item.ItemRepository;
import com.cederj.uff.tcc.stockmaster.services.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final InventoryRepositoy inventoryRepositoy;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, InventoryRepositoy inventoryRepositoy) {
        this.itemRepository = itemRepository;
        this.inventoryRepositoy = inventoryRepositoy;
    }

    @Override
    public GenericRepository<Item> getRepository() {
        return this.itemRepository;
    }

    @Override
    public List<Item> findAllForInventoryId(Long inventoryId) {
        if (inventoryId == null) {
            throw new RuntimeException("o id do inventario não foi informado");
        }
        return this.itemRepository.findAllByInventoryId(inventoryId);
    }

    @Override
    public void save(CreatedItemDto data, Long idInventory) {
        Optional<Inventory> optionalUser = this.inventoryRepositoy.findById(idInventory);

        Inventory inventory = optionalUser.orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        Item item = new Item();
        item.setName(data.name());
        item.setAmount(data.amount());
        item.setPoster(data.poster());
        item.setUnitPrice(data.unitPrice());
        item.setInventory(inventory);

        this.itemRepository.save(item);
    }

    @Override
    public Item findItemById(Long id) {
        return this.findById(id);
    }

    @Override
    public void delete(Long id) {
        this.itemRepository.deleteById(id);
    }

    @Override
    public void updateItemById(Long id, UpdateItemDto dado) {
        Optional<Item> optionalItem = this.itemRepository.findById(id);

        if (optionalItem.isEmpty()) {
            throw new RuntimeException("item não encontrado para o id informado");
        }
        Item item = optionalItem.get();
        item.setName(dado.name() != null && !dado.name().isBlank() ? dado.name() : item.getName());
        item.setUnitPrice(dado.unitPrice() != null ? dado.unitPrice() : item.getUnitPrice());
        item.setPoster(dado.poster() != null && !dado.poster().isBlank() ? dado.poster() : item.getPoster());

        this.itemRepository.save(item);
    }
}
