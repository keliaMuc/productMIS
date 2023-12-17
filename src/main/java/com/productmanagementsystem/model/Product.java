package com.productmanagementsystem.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component

public class Product {
    @Id
    private String product_id;
//    @NotNull(message = "product_name is required")
//@NotBlank(message = "product_name is required")
    private String product_name;
//    @NotNull(message = "product_category is required")
//@NotBlank(message = "product_category is required")
    private String product_category;
//    @NotNull(message = "product_price is required")
//@NotBlank(message = "product_price is required")
    private int product_price;
//    @NotNull(message = "stock is required")
//@NotBlank(message = "stock is required")
    private int stock;


//    @Lob
//    @Column(columnDefinition = "MEDIUMBLOB")
//    @Column(nullable = true, length = 64)
//    private String image;

//    @OneToMany(mappedBy ="buyproduct", cascade = CascadeType.ALL)
//    private List<Buyproducts> buyproducts;

//@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//private List<Buyproducts> buyproducts;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Buyproducts> buyproducts;


    public void updateStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock");
        }
    }
//    @Transient
//    public String getPhotosImagePath() {
//        if (image == null) return null;
//
//        return "/product-photos/" + product_id + "/" + image;
//    }




//    public Product() {
//    }
//
//    public Product(String product_id, String product_name, String product_category, int product_price, int stock, String image) {
//        this.product_id = product_id;
//        this.product_name = product_name;
//        this.product_category = product_category;
//        this.product_price = product_price;
//        this.stock = stock;
//        this.image = image;
//    }
//
//
//    public String getProduct_id() {
//        return product_id;
//    }
//
//    public void setProduct_id(String product_id) {
//        this.product_id = product_id;
//    }
//
//    public String getProduct_name() {
//        return product_name;
//    }
//
//    public void setProduct_name(String product_name) {
//        this.product_name = product_name;
//    }
//
//    public String getProduct_category() {
//        return product_category;
//    }
//
//    public void setProduct_category(String product_category) {
//        this.product_category = product_category;
//    }
//
//    public int getProduct_price() {
//        return product_price;
//    }
//
//    public void setProduct_price(int product_price) {
//        this.product_price = product_price;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

}
