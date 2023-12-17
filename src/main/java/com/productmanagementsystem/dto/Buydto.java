package com.productmanagementsystem.dto;


//import com.example.productmanagementsystem.model.Product;
import com.productmanagementsystem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component


public class Buydto {

    private int buy_id;
    @NotNull(message = "product is required")
    private Product product;

//    private Product buy_product_price;
    @NotNull(message = "quantity is required")
    private int quantity;
    @NotNull(message = "total_price is required")
    private int total_price;
    private LocalDateTime createdAt;
//    private int price;
}
