package com.cederj.uff.tcc.stockmaster.ServicesImpl.inventoryimpl;

import com.cederj.uff.tcc.stockmaster.dtos.inventory.CreatedInventoryDto;
import com.cederj.uff.tcc.stockmaster.dtos.inventory.UpdateInventoryDto;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.models.user.User;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import com.cederj.uff.tcc.stockmaster.repositories.inventory.InventoryRepositoy;
import com.cederj.uff.tcc.stockmaster.repositories.user.UserRepository;
import com.cederj.uff.tcc.stockmaster.services.inventory.InventoryService;
import com.cederj.uff.tcc.stockmaster.vos.inventory.InventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepositoy inventoryRepositoy;
    private final UserRepository userRepository;


    @Autowired
    public InventoryServiceImpl(InventoryRepositoy inventoryRepositoy, UserRepository userRepository) {
        this.inventoryRepositoy = inventoryRepositoy;
        this.userRepository = userRepository;
    }

    @Override
    public GenericRepository<Inventory> getRepository() {
        return this.inventoryRepositoy;
    }

    @Override
    public List<Inventory> findAllByUserId(Long userId) {
        return this.inventoryRepositoy.findAllByUserId(userId);
    }

    @Override
    public Inventory findInventoryById(Long inventoryId) {
        return this.findById(inventoryId);
    }

    @Override
    public void updateInventoryById(Long id, UpdateInventoryDto dado) {
        Optional<Inventory> optionalInventory = this.inventoryRepositoy.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new RuntimeException("estoque não encontrado para o id informado");
        }
        Inventory inventory = optionalInventory.get();
        inventory.setName(dado.name() != null && !dado.name().isBlank() ? dado.name() : inventory.getName());
        inventory.setDescription(dado.description() != null && !dado.description().isBlank() ? dado.description() : inventory.getDescription());

        this.inventoryRepositoy.save(inventory);
    }

    @Override
    public void save(CreatedInventoryDto data, Long idUser) {
        Optional<User> optionalUser = this.userRepository.findById(idUser);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User não encontrado"));

        Inventory inventory = new Inventory();
        inventory.setUser(user);
        inventory.setName(data.name());
        inventory.setDescription(data.description());

        this.inventoryRepositoy.save(inventory);
    }

    @Override
    public void delete(Long id) {
        this.deleteById(id);
    }

}
