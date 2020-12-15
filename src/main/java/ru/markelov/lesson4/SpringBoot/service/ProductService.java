package ru.markelov.lesson4.SpringBoot.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.markelov.lesson4.SpringBoot.model.Product;
import ru.markelov.lesson4.SpringBoot.repository.ProductReposuitory;

import java.util.List;
import java.util.Optional;

@Component
@Data
public class ProductService {
    private final ProductReposuitory productReposuitory;

    public List<Product> getProductList(){
        return productReposuitory.getProductList();
    }

    public Product addOrUpdateProduct(Product product){
        return productReposuitory.addOrUpdateProduct(product);
    }

    public void remove(Long id){
        productReposuitory.remove(id);
    }
    public List<Product> getProductList(Integer min, Integer max){
        return productReposuitory.filter(min, max);
    }
}
