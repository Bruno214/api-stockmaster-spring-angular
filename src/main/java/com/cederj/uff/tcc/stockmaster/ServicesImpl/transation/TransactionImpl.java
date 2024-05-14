package com.cederj.uff.tcc.stockmaster.ServicesImpl.transation;

import com.cederj.uff.tcc.stockmaster.Enums.transaction.CommercialTypeEntity;
import com.cederj.uff.tcc.stockmaster.Enums.transaction.TransactionType;
import com.cederj.uff.tcc.stockmaster.dtos.transaction.CreatedTransactionDto;
import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.models.transaction.Transaction;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import com.cederj.uff.tcc.stockmaster.repositories.inventory.InventoryRepositoy;
import com.cederj.uff.tcc.stockmaster.repositories.item.ItemRepository;
import com.cederj.uff.tcc.stockmaster.repositories.transaction.TransactionRepository;
import com.cederj.uff.tcc.stockmaster.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final InventoryRepositoy inventoryRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public TransactionImpl(TransactionRepository transactionRepository, InventoryRepositoy inventoryRepository, ItemRepository itemRepository){
        this.transactionRepository = transactionRepository;
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public GenericRepository<Transaction> getRepository() {
        return this.transactionRepository;
    }

    @Override
    public List<Transaction> findAllTranscationsForInventoryId(Long inventoryId) {
        return this.transactionRepository.findAllByInventoryId(inventoryId);
    }

    @Override
    @Transactional
    public void createTransactionEntry(CreatedTransactionDto data, Long idInventory) {
        Optional<Inventory> optionalUser = this.inventoryRepository.findById(idInventory);
        Inventory inventory = optionalUser.orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        if (data.transactionType() == TransactionType.SAIDA) {
            throw new RuntimeException("Valor da transação é inválido");
        }

        Optional<Item> optionalItem = this.itemRepository.findByNameIgnoreCaseAndInventoryId(data.item().name().toLowerCase(), idInventory);
        Item item = new Item();

        if (optionalItem.isEmpty()) {
            item.setName(data.item().name());
            item.setUnitPrice(data.item().unitPrice());
            item.setAmount(data.quantityItem());
            item.setPoster(data.item().poster());
            item.setInventory(inventory);
        }else {
            item = optionalItem.get();
            item.setAmount(item.getAmount() + data.quantityItem());
        }

        this.itemRepository.save(item);

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setTransactionType(data.transactionType());
        transaction.setInventory(inventory);
        transaction.setQuantityItem(data.quantityItem());
        transaction.setObservation(data.observation());
        transaction.setItem(item);
        transaction.setCommercialTypeEntity(CommercialTypeEntity.CLIENTE);

        this.transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void createTransactionOutput(CreatedTransactionDto data, Long idInventory) {
        Optional<Inventory> optionalUser = this.inventoryRepository.findById(idInventory);
        Inventory inventory = optionalUser.orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        if (data.transactionType() == TransactionType.ENTRADA) {
            throw new RuntimeException("Valor da transação é inválido");
        }

        Optional<Item> optionalItem = this.itemRepository.findByNameIgnoreCaseAndInventoryId(data.item().name().toLowerCase(), idInventory);
        Item item = new Item();

        if (optionalItem.isPresent()) {
            item = optionalItem.get();
            if (item.getAmount() - data.quantityItem() < 0) {
                throw new RuntimeException("Não é possível criar esta transação de saída pois a quantidade de itens disponíveis é menor que a quantidade solicitada " + "quatidade de itens Disponíveis: " + item.getAmount());
            }
            item.setAmount(item.getAmount() - data.quantityItem());
        }

        this.itemRepository.save(item);

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setTransactionType(data.transactionType());
        transaction.setInventory(inventory);
        transaction.setQuantityItem(data.quantityItem());
        transaction.setObservation(data.observation());
        transaction.setItem(item);
        transaction.setCommercialTypeEntity(CommercialTypeEntity.SUPPLIER);

        this.transactionRepository.save(transaction);
    }
}
