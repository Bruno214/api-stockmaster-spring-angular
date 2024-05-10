package com.cederj.uff.tcc.stockmaster.models.Item;

import com.cederj.uff.tcc.stockmaster.models.GenericModel;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item extends GenericModel {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private Integer amount;
    private String poster;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @JsonIgnore
    private Inventory inventory;
}