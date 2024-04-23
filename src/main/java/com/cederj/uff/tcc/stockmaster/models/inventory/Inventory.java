package com.cederj.uff.tcc.stockmaster.models.inventory;

import com.cederj.uff.tcc.stockmaster.models.GenericModel;
import com.cederj.uff.tcc.stockmaster.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_inventory")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inventory extends GenericModel {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
