package com.productmanagementsystem.dto;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import jakarta.persistence.OneToMany;

import com.productmanagementsystem.model.Buyproducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component

public class Productdto {

    private String product_id;
//    @NotNull(message = "product_name is required")
    private String product_name;
//    @NotNull(message = "product_category is required")
    private String product_category;
//    @NotNull(message = "product_price is required")
    private int product_price;
//    @NotNull(message = "stock is required")
    private int stock;


//    @Lob
////    @Column(columnDefinition = "MEDIUMBLOB")
//    private String image;


//    private List<Buyproducts> buyproducts;




}
