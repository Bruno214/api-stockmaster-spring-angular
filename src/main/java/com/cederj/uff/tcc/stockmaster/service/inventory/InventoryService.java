package com.cederj.uff.tcc.stockmaster.service.inventory;

import com.cederj.uff.tcc.stockmaster.DTO.inventory.CreatedInventoryDTO;
import com.cederj.uff.tcc.stockmaster.model.inventory.Inventory;
import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.repository.inventory.InventoryRepositoy;
import com.cederj.uff.tcc.stockmaster.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepositoy inventoryRepositoy;
    private final UserRepository userRepository;

    @Autowired
    public InventoryService(InventoryRepositoy inventoryRepositoy, UserRepository userRepository) {
        this.inventoryRepositoy = inventoryRepositoy;
        this.userRepository = userRepository;
    }

    public List<Inventory> findAllByUserId(Long id){
        return this.inventoryRepositoy.findAllByUserId(id);
    }

    public void createdInventory(Long idUser, CreatedInventoryDTO data) {
        Optional<User> optionalUser = this.userRepository.findById(idUser);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        Inventory inventory = new Inventory();
        inventory.setUser(user);
        inventory.setName(data.name());
        inventory.setDescription(data.description());

        this.inventoryRepositoy.save(inventory);

    }
}
