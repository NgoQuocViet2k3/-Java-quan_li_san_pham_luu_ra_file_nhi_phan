package com.codegym;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
    List<Product> products = new ArrayList<>();

    public int size() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }


    public void display() {
        for (int i = 0; i < size(); i++) {
            System.out.println(products.get(i));
        }
    }

    public void add(Product product) {
        products.add(product);
    }

    public int indexFindById(String id) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (products.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }


}
