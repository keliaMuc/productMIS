package com.productmanagementsystem.model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component

public class Buyproducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buy_id;
//    @Not(message = "product is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "product_id")
    private Product product;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name= "product_price")
//    private Product buy_product_price;
//@NotNull(message = "Customer is required")
//@NotBlank(message = "quantity is required")
    private int quantity;
//    @NotBlank(message = "total_price is required")
    private int total_price;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
//     private int price;


    public void setProduct(Product product) {
        this.product = product;
    }

    @PrePersist
    public void prePersist() {
        product.updateStock(quantity);
    }

}
