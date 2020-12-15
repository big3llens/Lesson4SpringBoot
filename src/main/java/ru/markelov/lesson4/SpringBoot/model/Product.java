package ru.markelov.lesson4.SpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private int cost;
}
