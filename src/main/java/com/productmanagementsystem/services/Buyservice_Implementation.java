package com.productmanagementsystem.services;


//import com.example.productmanagementsystem.dto.Buydto;
//import com.example.productmanagementsystem.mapper.BuyMapper;
//import com.example.productmanagementsystem.model.Buyproducts;
//import com.example.productmanagementsystem.repository.BuyRepo;
import com.productmanagementsystem.dto.Buydto;
import com.productmanagementsystem.exception.ResourceNotFoundException;
import com.productmanagementsystem.mapper.BuyMapper;
import com.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.model.Product;
import com.productmanagementsystem.repository.BuyRepo;
//import jakarta.transaction.Transactional;
import com.productmanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Buyservice_Implementation implements BuyService{

//    @Autowired
    private BuyMapper buyMapper;
    private final BuyRepo buyRepo;
    private ProductRepository productRepository;
    private final ProductService productService;
    @Autowired
    public Buyservice_Implementation(BuyMapper buyMapper, BuyRepo buyRepo, ProductRepository productRepository, ProductService productService) {
        this.buyMapper = buyMapper;
        this.buyRepo = buyRepo;
//        this.productRepositorys = productRepositorys;

        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    public Buydto saveboughtproducts(Buydto buydto) {
        Buyproducts buyproducts = buyMapper.toEntity(buydto);
        buyproducts = buyRepo.save(buyproducts);
        return buyMapper.toDto(buyproducts);

    }



    @Override
    public List<Buydto> findAllboughtproducts() {
        List<Buyproducts> buyproductss =buyRepo.findAll();

        return buyproductss.stream().map(BuyMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Buyproducts getBuysProductById(int buy_id) {

        Optional <Buyproducts> optional = buyRepo.findById(buy_id);
        Buyproducts buyproducts = null;
        if (optional.isPresent()) {
            buyproducts = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + buy_id);
        }
        return buyproducts;
    }

    @Override
    public Optional<Buyproducts> getAllmodelById(int buy_id) {
        return buyRepo.findById(buy_id);
    }

    @Override
    public void deletebyId(int buy_id) {
        buyRepo.deleteById(buy_id);
    }

//    @Transactional
//    @Override
//    public void savebuyproducts(Buydto buydto) {
//        Buyproducts byproduct = buyMapper.toEntity(buydto);
////        Product product
//        Buyproducts b = new Buyproducts(byproduct.getBuy_id(), byproduct.getProduct(), byproduct.getQuantity(),byproduct.getTotal_price());
//        buyRepo.save(b);
////         return buydto;
//    }
//
//    @Override
//    public List<Buydto> findAllbuyproducts() {
//        List<Buyproducts> buyproducts = buyRepo.findAll();
//        return buyproducts.stream()
//                .map(buyMapper::toDto)
//                .collect(Collectors.toList());
//    }


//    public Buyproducts create(Buyproducts buyProduct) {
//        // Save the buy transaction to the database
//        buyProduct = buyRepo.save(buyProduct);
//
//        // Decrease the stock of the corresponding product
//        Product product = productRepository.findById(buyProduct.getProduct().getProduct_id()).orElse(null);
//        if (product != null) {
//            product.setStock(product.getStock() - buyProduct.getQuantity());
//            if (product.getStock() == 0) {
//                System.out.println("Out of stock for product: " + product.getProduct_name());
//            }
//            productRepository.save(product);
//        }
//
//        return buyProduct;
//    }


    public Buyproducts getBuyProductById(int buy_id) {
        return buyRepo.findById(buy_id)
                .orElseThrow(() -> new ResourceNotFoundException("Buy product not found with id: " + buy_id));
    }

    public Buyproducts saveBuyProduct(Buyproducts buyProduct) {
        productService.updateProductStock(buyProduct.getProduct().getProduct_name(), buyProduct.getQuantity());
        buyProduct.setTotal_price(buyProduct.getTotal_price());
        return buyRepo.save(buyProduct);
    }
}
