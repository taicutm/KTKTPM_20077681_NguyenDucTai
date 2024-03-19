package org.example.baitaplab05.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Product product;
    private String customerName;
    private String customerEmail;
    private long quantity_order;
}
