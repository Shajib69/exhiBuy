package com.clone.spring.crud.controller;

import com.clone.spring.crud.model.Product;
import com.clone.spring.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class controller {

    @RequestMapping("/index")
    public String home(){
        return "index";
    }

    @RequestMapping("/profile")
    public String profile() {return "profile";}

    @Autowired
    private ProductService productService;

    @GetMapping("/addProduct")
    public String showAddProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "add";
    }

    @PostMapping("/category")
    public String saveProduct(Product product, MultipartFile file)
    {
        productService.saveProductToDB(file, product);
        return "redirect:/product";
    }

    @GetMapping("/product")
    public String addProduct(Model model){

        List<Product> productList = productService.getAllProduct();

        model.addAttribute("products", productList);
        return "tableData";
    }

    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable long id){
        productService.deleteById(id);
        return "redirect:/product";
    }

}
