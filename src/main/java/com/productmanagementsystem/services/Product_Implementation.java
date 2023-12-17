package com.productmanagementsystem.services;

//import com.example.productmanagementsystem.model.Product;
//import com.example.productmanagementsystem.repository.ProductRepository;
import com.productmanagementsystem.exception.ResourceNotFoundException;
import com.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.model.Product;
import com.productmanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

//import static org.apache.tomcat.util.net.SocketWrapperBase.transfer;

@Service
public class Product_Implementation implements ProductService {
    private final ProductRepository amd;
    @Autowired
public Product_Implementation(ProductRepository amd) {this.amd=amd;
    }


    @Override
    public List<Product> getAllmodels() {
        return  amd.findAll();
    }

    @Override
    public Optional<Product> getAllmodelById(String product_id) {
        return amd.findById(product_id);
    }

//    @Override
//    public product saveAll(product allmd) {
//        return amd.saveAll(allmd);
//    }

    @Override
    public Product saveAll(Product allmd) {
    amd.save(allmd);
        return allmd;
    }

    @Override
    public Product getProductById(String product_id) {
        Optional <Product> optional = amd.findById(product_id);
        Product products = null;
        if (optional.isPresent()) {
            products = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + product_id);
        }
        return products;
//        return amd.findById(product_id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + product_id));
    }
    public Product saveProduct(Product product) {
        return amd.save(product);
    }
    public void updateProductStock(String product_id, int quantity) {
        Product product = getProductById(product_id);
        int newStock = product.getStock() - quantity;
        if (newStock < 0) {
            throw new IllegalArgumentException("Insufficient stock for product with id: " + product_id);
        }
        product.setStock(newStock);
        saveProduct(product);
    }

//    @Override
//    public void editAll(String product_id) {
//     amd.save(product_id);
//    }

    @Override
    public void deletebyId(String product_id) {
     amd.deleteById(product_id);
    }



//    @Override
//    public Page<Product> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//        Pageable pageable = PageRequest.of(pageNo -1,pageSize,sort);
//        return this.amd.findAll(pageable);
//    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("product_price"));
//        Page<Product> pageResult = amd.findAll(pageable);

        return (Page<Product>) this.amd.findAll(pageable);

    }



//    @Override
//    public Page<Product> searchProducts(int pageNo, String keyword) {
//        return null;
//    }




//    @Override
//    public List<Allmodel> searchProducts(String keyword) {
//        return null;
//    }


//    public List<Allmodel> searchProducts(String keyword) {
////        Pageable pageable = PageRequest.of(pageNo, 5);
////        List<Allmodel> productDtoList = amd.searchProductsList(keyword);
//        List<Allmodel> products = amd.searchProductsList(keyword);
//        return products;
//
//    }
    public List<Product> search(String product_name) {

        return amd.findALL(product_name);
    }

    public Product savetodb(String product_id, String product_name, String product_category, int product_price, int stock){
        Product products = new Product();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if (fileName.contains("..")){
//            System.out.println("not a valid file");
//        }
        products.setProduct_id(product_id);
        products.setProduct_name(product_name);
//        try {
//            products.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        products.setProduct_category(product_category);
        products.setProduct_price(product_price);
        products.setStock(stock);
        amd.save(products);
        return products;
    }


//    public Product savetodb(MultipartFile file, String product_id, String product_name, String product_category, int product_price, int stock){
//        Product products = new Product();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if (fileName.contains("..")){
//            System.out.println("not a valid file");
//        }
//        products.setProduct_id(product_id);
//        products.setProduct_name(product_name);
//        try {
//            products.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        products.setProduct_category(product_category);
//        products.setProduct_price(product_price);
//        products.setStock(stock);
//        amd.save(products);
//        return products;
//    }



//    @Override
//    public List<Product> getAllProduct(String keyword) {
//        if (keyword != null){
//            return amd.search(keyword);
//        }
//        else
//            return (List<Product>) amd.findAll();
//    }


//    public void buyProduct(Buyproducts buyProduct) throws OutOfStockException {
//        Product product = amd.findBy(buyProduct.getProduct())
//                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
//
//        if (buyProduct.getQuantity() > product.getStock()) {
//            throw new OutOfStockException("Out of stock");
//        }
//
//        product.setStock(product.getStock() - buyProduct.getQuantity());
//
//        productRepository.save(product);
//    }

}
