package com.productmanagementsystem.services;


//import com.example.productmanagementsystem.dto.Buydto;
import com.productmanagementsystem.dto.Buydto;
import com.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.model.Product;

import java.util.List;
import java.util.Optional;

public interface BuyService {
//    void savebuyproducts(Buydto buydto);
//    List<Buydto> findAllbuyproducts();

//    Buyproducts saveAll(Buyproducts buying);

    Buydto saveboughtproducts(Buydto buydto);
public Buyproducts saveBuyProduct(Buyproducts buyProduct);
    List<Buydto> findAllboughtproducts();
    public Buyproducts getBuysProductById(int buy_id);

    Optional<Buyproducts> getAllmodelById(int buy_id);
    void deletebyId(int buy_id);

}
