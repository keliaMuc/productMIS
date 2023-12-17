package com.productmanagementsystem.mapper;

import com.productmanagementsystem.dto.Buydto;
import com.productmanagementsystem.dto.Productdto;
import com.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.model.Product;

public class ProductMapper {

    public static Product toEntity(Productdto productdto) {
    Product product = new Product();
    product.setProduct_id(productdto.getProduct_id());
    product.setProduct_name(productdto.getProduct_name());
    product.setProduct_price(productdto.getProduct_price());
    product.setProduct_category(productdto.getProduct_category());
    product.setStock(productdto.getStock());
//    product.setImage(productdto.getImage());
    return product;
    }


    public static Productdto toDto(Product product) {
        Productdto productdto = new Productdto();
        productdto.setProduct_id(product.getProduct_id());
        productdto.setProduct_name(product.getProduct_name());
        productdto.setProduct_price(product.getProduct_price());
        productdto.setProduct_category(product.getProduct_category());
        productdto.setStock(product.getStock());
//        productdto.setImage(productdto.getImage());
        return productdto;
    }


}
