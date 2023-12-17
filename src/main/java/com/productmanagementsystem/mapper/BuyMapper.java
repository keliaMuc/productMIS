package com.productmanagementsystem.mapper;


//import com.example.productmanagementsystem.dto.Buydto;
//import com.example.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.dto.Buydto;
import com.productmanagementsystem.model.Buyproducts;
import org.springframework.stereotype.Component;

@Component
public class BuyMapper {

    public static Buyproducts toEntity(Buydto buydto) {
        Buyproducts buyproducts = new Buyproducts();
        buyproducts.setBuy_id(buydto.getBuy_id());
        buyproducts.setProduct(buydto.getProduct());
//        buyproducts.setBuy_product_price(buydto.getBuy_product_price());
        buyproducts.setQuantity(buydto.getQuantity());

        buyproducts.setTotal_price(buydto.getTotal_price());
//        buyproducts.setPrice(buydto.getPrice());
      return buyproducts;
    }

    public static Buydto toDto(Buyproducts buyproducts) {
        Buydto buydto = new Buydto();
        buydto.setBuy_id(buyproducts.getBuy_id());
        buydto.setProduct(buyproducts.getProduct());
//        buydto.setBuy_product_price(buyproducts.getBuy_product_price());
        buydto.setQuantity(buyproducts.getQuantity());
        buydto.setTotal_price(buyproducts.getTotal_price());
        buydto.setCreatedAt(buyproducts.getCreatedAt());
//        buydto.setPrice(buyproducts.getPrice());
        return buydto;
    }

}
