package ru.markelov.lesson4.SpringBoot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.markelov.lesson4.SpringBoot.model.Product;
import ru.markelov.lesson4.SpringBoot.service.ProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @RequestMapping
    public String hello(Model model, @RequestParam(value = "min", required = false) Integer min, @RequestParam(value = "name", required = false) Integer max) {
        model.addAttribute("productList", productService.getProductList(min, max));
        return "productsPage";
    }

    @GetMapping("/add")
    public String addOrUpdateProduct(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "name") String name, @RequestParam("cost") int cost){
        Product product = new Product(id, name, cost);
        productService.addOrUpdateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id){
        productService.remove(id);
        return "redirect:/products";
    }
}
