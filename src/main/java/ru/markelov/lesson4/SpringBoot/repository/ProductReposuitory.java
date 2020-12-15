package ru.markelov.lesson4.SpringBoot.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.markelov.lesson4.SpringBoot.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class ProductReposuitory {
    private List<Product> productList;

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Apple", 40));
        productList.add(new Product(2L, "Orange", 55));
        productList.add(new Product(3L, "Pasta", 45));
        productList.add(new Product(4L, "Milk", 60));
        productList.add(new Product(5L, "Meat", 90));
    }

    public Product addOrUpdateProduct(Product product){
        if(product.getId() != null){
            for (int i = 0; i < productList.size(); i++) {
                if(productList.get(i).getId().equals(product.getId())){
                    productList.set(i, product);
                    return product;
                }
            }
        }

        Long id = productList.stream().mapToLong(p -> p.getId()).max().orElseGet(() -> 0L) + 1L;
        product.setId(id);
        productList.add(product);
        return product;
    }

    public void remove(Long id){
        productList.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> filter (Integer min, Integer max){
        List<Product> filterList = productList;
        if (min != null){
            filterList = filterList.stream().filter(p -> p.getCost() > min).collect(Collectors.toList());
        }
        if (max != null){
            filterList = filterList.stream().filter(p -> p.getCost() < max).collect(Collectors.toList());
        }
        return filterList;
    }

}
