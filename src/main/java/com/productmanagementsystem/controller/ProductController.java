package com.productmanagementsystem.controller;


//import com.example.productmanagementsystem.model.Product;
//import com.example.productmanagementsystem.repository.ProductRepository;
//import com.example.productmanagementsystem.service.DatabasePDFService;
//import com.example.productmanagementsystem.service.ProductService;
import com.productmanagementsystem.model.Product;
import com.productmanagementsystem.repository.ProductRepository;
import com.productmanagementsystem.services.DatabasePDFService;
import com.productmanagementsystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    public final ProductService dservice;


@Autowired
public ProductController(ProductService dservice){this.dservice=dservice;}

    @GetMapping("/listing")
    public String listStudents (Model model){
    List<Product> pds =dservice.getAllmodels();
    model.addAttribute("Product",pds);

//        return findPaginated(1, "product_id", "asc", model);
        return "list";
}
    @Autowired
    ProductRepository repository;

//    @RequestMapping(value = "/home",method = RequestMethod.GET)
//    @ResponseBody
//    public ModelAndView returnListProduct()
//    {
//        ModelAndView mv = new ModelAndView();
//        List<Product> products = repository.findAll();
//        mv.setViewName("list");
//        mv.addObject("Product", products);
//        return mv;
//
//    }

//    @GetMapping("/")
//    public String home (Model model){
//        model.addAttribute("Product") ;
//        return "home";
//
//    }



    @GetMapping("/add")
    public String registerStudentPage(Model model) {
        Product products = new Product();
        model.addAttribute("Product", products);
        // List<Student> students = studentService.studentList();
        return "addproducts";

    }

//    @PostMapping("/register")
//    public String registerProductInDb(@ModelAttribute("product") Product theProduct) {
//        Product savedProduct = dservice.saveAll(theProduct);
//        if (savedProduct != null) {
//            return "redirect:/home";
//        }
//        return "addproducts";
//
    //}
//        @PostMapping("/register")
//    public String registerProductInDb(@ModelAttribute("product") @RequestParam("file") MultipartFile file, @RequestParam("product_name") String product_name, @RequestParam("product_id") String product_id, @RequestParam("product_category") String product_category, @RequestParam("price") int price) {
//        product savedProduct = dservice.savetodb(file,product_id,product_name,product_category,price);
//        if (savedProduct != null) {
//            return "redirect:/home";
//        }
//        return "addproducts";
//
//    }

//    @PostMapping("/registers")
//    public String registerProductInDb(@RequestParam("image") MultipartFile file, @RequestParam("product_name") String product_name, @RequestParam("product_id") String product_id, @RequestParam("product_category") String product_category, @RequestParam("product_price") int product_price, @RequestParam("stock") int stock) {
//       dservice.savetodb(file,product_id,product_name,product_category,product_price,stock);
//
//            return "redirect:/admin/home";
////        }
////        return "addproducts";
//
//    }

@PostMapping("/registers")
public String registerProductInDb(@ModelAttribute("Product") Product theProduct) {
    dservice.saveAll(theProduct);

    return "redirect:/admin/home";
//        }
//        return "addproducts";

}



    @GetMapping("/edits/{product_id}")
    public String editproductForm(@PathVariable(value="product_id") String product_id, Model model){
        Product products =dservice.getProductById(product_id);
        model.addAttribute("Product", products);
        return "edit";
    }

    @GetMapping("/deletes/{product_id}")
    public String deleteproduct(@PathVariable(value="product_id")String product_id) {
        dservice.deletebyId(product_id);
        return "redirect:/admin/home";
    }

//        @GetMapping ("/search")
//        public String searchMethod(Model model){
//            model.addAttribute("search",new Product());
//            return "list";
//        }

//    @GetMapping ("/search")
//    public String searchMethod(Model model){
//        model.addAttribute("search",new Product());
//        return "searchlist";
//    }
//    @PostMapping("/search")
//    public String getEmployee(@ModelAttribute("search") Product product, Model model){
//            Product product1=dservice.getProductById(product.getProduct_id());
//            if (product1!=null) {
//                model.addAttribute("product1",product1);
//                return "searchlist";
//            }else {
//                model.addAttribute("error","He/She is not found");
//                return "searchlist";
//            }
//        }

    @GetMapping ("/searchps")
    public String searchMethod(Model model){
        model.addAttribute("searchps",new Product());
        return "searchlist";
    }

    @PostMapping("/searchps")
    public String getEmployee(@ModelAttribute("searchps") Product soldier, Model model){
        Product soldier1=dservice.getProductById(soldier.getProduct_id());
        if (soldier1!=null) {
            model.addAttribute("soldier1",soldier1);
            return "searchlist";
        }else {
            model.addAttribute("error","Product is not found");
            return "searchlist";
        }
    }



    @GetMapping("/homes/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir , Model model){
        int pageSize=5;
//        Page
        Page <Product> productspage = dservice.findPaginated(pageNo,pageSize,sortField,sortDir) ;
        List<Product> listproducts = productspage.getContent();
//        model.addAttribute("title", "List User Information");
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", productspage.getTotalPages());
        model.addAttribute("totalItems", productspage.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("Product", listproducts);
//        model.addAttribute("Product",dservice.getAllmodels());
        return "list";
    }



//    @GetMapping("/search-result")
//    public String search(@Param("product_name") String product_name, Model model) {
//        List<Product> products = dservice.search(product_name);
//        model.addAttribute("Product",products);
//        model.addAttribute("product_name",product_name);
//        return "list";
//    }


//
//    @GetMapping("/searchs")
//    public String projects(@Param("keyword") String keyword, Model model) {
//        List<Product> listproducts = dservice.getAllProduct(keyword);
//        model.addAttribute("listproducts",listproducts);
//        model.addAttribute("keyword",keyword);
//        return "list";
//    }







    @GetMapping(value = "/openpdf/products", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ProductReport()  throws IOException {
        List<Product> products = (List<Product>) repository.findAll();

        ByteArrayInputStream bis = DatabasePDFService.ProductPDFReport(products);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Productlist.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }



}