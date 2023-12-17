package com.productmanagementsystem.repository;


//import com.example.productmanagementsystem.model.Product;
import com.productmanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>  {
//    @Query("select a from Product a")
//    Page<Product> pageProduct (Pageable pageable);

//    @Query("select p from Allmodel p where p.product_name like %?1% or p.product_id like %?1%")
//    Page<Allmodel> searchProducts(String keyword, Pageable pageable);
//
//

//    List<Allmodel> searchProductsList(String keyword);
@Query("SELECT a FROM Product a WHERE a.product_name LIKE %?1%")
    List<Product> findALL(String product_name);

//@Query("SELECT product FROM Product product WHERE CONCAT(product.product_id, ' ',product.product_name,' ',product.product_category,' ',product.product_name) LIKE %?1%")
//    public List<Product> search(String keyword);
////    public Product findByproductname(String product_name);

}
