package com.example.clase2.controllers;


import com.example.clase2.entitys.CategoryEntity;
import com.example.clase2.entitys.ProductEntity;
import com.example.clase2.repositorys.CategoryRepository;
import com.example.clase2.repositorys.ProductRepository;
import com.example.clase2.repositorys.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/list")
    public String getList(Model model){
        model.addAttribute("productList", productRepository.findAll());
        return "product/list";
    }

    @GetMapping("/editProduct")
    public String editProduct(Model model, @RequestParam("id") int id){
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if(productOpt.isPresent()){
            model.addAttribute("product",productOpt.get());
            model.addAttribute("suppliersList", supplierRepository.findAll());
            model.addAttribute("categoriesList", categoryRepository.findAll());
            return "product/editProduct";
        }else{
            return "redirect:/product/list";
        }
    }

}
