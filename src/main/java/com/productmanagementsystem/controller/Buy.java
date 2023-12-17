package com.productmanagementsystem.controller;

//import com.example.productmanagementsystem.dto.Buydto;
//import com.example.productmanagementsystem.model.Product;
//import com.example.productmanagementsystem.service.BuyService;
//import com.example.productmanagementsystem.service.ProductService;
import com.productmanagementsystem.dto.Buydto;
import com.productmanagementsystem.model.Buyproducts;
import com.productmanagementsystem.model.Product;
import com.productmanagementsystem.services.BuyService;
import com.productmanagementsystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class Buy {

    private final BuyService buyService;
    private final ProductService productService;


    @Autowired
    public Buy(BuyService buyService, ProductService productService)
    {
        this.buyService = buyService;
        this.productService = productService;
    }

//    @GetMapping("/buylistings")
//    public String listbuyproducts(Model model)
//    {
//        List<Buydto> buys = buyService.findAllbuyproducts();
//        model.addAttribute("buys", buys);
//        return "buylist";
//    }
//
//
//
//    @PostMapping("/newbuy")
//    public String saveProductsbought(@ModelAttribute("buys") Buydto buydto,Product product, Model model){
//        model.addAttribute("buys", buydto);
//        model.addAttribute("products",product);
//        buyService.savebuyproducts(buydto);
//        return "redirect:/buylistings";
//    }
//
//    @GetMapping("/newbuy")
//    public String createbuyForm(Model model) {
//        List<Product> products = productService.getAllmodels();
//        model.addAttribute("products", products);
//        model.addAttribute("buys", new Buydto());
//        return "buyproducts";
//    }
//
//
//
//    @GetMapping("/byproduct")
//    public String showBuyProducts(Model model) {
//        List<Buydto> buyProductsList = buyService.findAllbuyproducts();
//        model.addAttribute("buyProductsList", buyProductsList);
//        return "buyproducts";
//    }

    @GetMapping("/buylistings")
    public String listbuyproducts(Model model)
    {
        List<Buydto> bought = buyService.findAllboughtproducts();
        model.addAttribute("bought", bought);
        return "buylist";
    }



    @GetMapping("/newbuy")
    public String createbuyForm(Model model){
        Buydto buydto = new Buydto();
        model.addAttribute("buyout", buydto);
        model.addAttribute("products",productService.getAllmodels());
//        buyService.savebuyproducts(buydto);
        return "buyproducts";
    }

    @PostMapping("/newbuys")
    public String saveProductsbought(@ModelAttribute("buyout") @Valid Buydto buydto, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "buyproducts";
        }
        buyService.saveboughtproducts(buydto);
        return "redirect:/buylistings";
    }

//
//
//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel) {
//        Buyproducts theBuyProduct = new Buyproducts();
//        theModel.addAttribute("buyProduct", theBuyProduct);
//        List<Product> theProducts = productService.getAllmodels();
//        theModel.addAttribute("products", theProducts);
//        return "buyproducts";
//    }
//
//
//    @PostMapping("/saveBuyProduct")
//    public String saveBuyProduct(@ModelAttribute("buyProduct") Buyproducts theBuyProduct) {
//        buyService.saveBuyProduct(theBuyProduct);
//        productService.updateProductStock(theBuyProduct.getProduct().getProduct_id(), theBuyProduct.getQuantity());
//        return "redirect:/buylistings";
//    }




//    @GetMapping("/byproduct")
//    public String showBuyProducts(Model model) {
//        List<Buydto> buyProductsList = buyService.findAllbuyproducts();
//        model.addAttribute("buyProductsList", buyProductsList);
//        return "buyproducts";
//    }

    @GetMapping ("/search")
    public String searchMethod(Model model){
        model.addAttribute("search",new Buyproducts());
        return "searchbuylist";
    }

    @PostMapping("/search")
    public String getproduct(@ModelAttribute("search") Buyproducts soldier, Model model){
        Buyproducts soldier1=buyService.getBuysProductById(soldier.getBuy_id());
        if (soldier1!=null) {
            model.addAttribute("soldier1",soldier1);
            return "searchbuylist";
        }else {
            model.addAttribute("error","Product is not found");
            return "searchbuylist";
        }
    }

    @GetMapping("/edit/{buy_id}")
    public String editproductForm(@PathVariable("buy_id") int buy_id, Model model) {
        Buyproducts products = buyService.getBuysProductById(buy_id);
        model.addAttribute("products", productService.getAllmodels());
        model.addAttribute("buyout", products);
        return "editbuyproducts";
    }

    @GetMapping("/delete/{buy_id}")
    public String deleteproduct(@PathVariable("buy_id")int buy_id) {
        buyService.deletebyId(buy_id);
        return "redirect:/admin/home";
    }

}
