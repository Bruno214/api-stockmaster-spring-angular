package com.cederj.uff.tcc.stockmaster.models.transaction;

import com.cederj.uff.tcc.stockmaster.Enums.transaction.CommercialTypeEntity;
import com.cederj.uff.tcc.stockmaster.Enums.transaction.TransactionType;
import com.cederj.uff.tcc.stockmaster.models.GenericModel;
import com.cederj.uff.tcc.stockmaster.models.Item.Item;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction extends GenericModel {
    @Column(nullable = false)
    private LocalDateTime date;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TransactionType transactionType;
    @Column(nullable = false)
    private Integer quantityItem;
    private String observation;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @JsonIgnore
    private Inventory inventory;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CommercialTypeEntity commercialTypeEntity;
    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;
    //private Cliente cliente;
    //private Fornecedor fornecedor;
}
