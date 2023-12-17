package com.productmanagementsystem.services;


//import com.example.productmanagementsystem.model.Product;
import com.productmanagementsystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;


public interface ProductService {

    //get all listed in students db
    List<Product> getAllmodels();

//    List<Allmodeldto> findAll();

    Optional<Product> getAllmodelById(String product_id);

    Product saveAll(Product allmd);
//    void editAll(String product_id);
//Product getProductById(String product_id);
    void deletebyId(String product_id);
    public Product getProductById(String product_id);
//    Page<Allmodeldto> pageProducts(int pageNo);
    Page<Product> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection);

//    List<Allmodel> searchProducts(String keyword);
public List<Product> search(String product_name);

//    public Product savetodb(MultipartFile file, String product_id, String product_name, String product_category, int product_price, int stock);
public Product savetodb( String product_id, String product_name, String product_category, int product_price, int stock);
public void updateProductStock(String product_id, int quantity);



//    List<Product> getAllProduct(String keyword);

}
