package com.productmanagementsystem.repository;


//import com.example.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.model.Buyproducts;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface BuyRepo extends JpaRepository<Buyproducts, Integer> {



}
