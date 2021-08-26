package com.codegym;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void setProduct(ArrayList<Product> arrayList) {
        this.products = arrayList;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void showProduct() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public int findProductById(int productId) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productId) {
                index = i;
                break;
            }
        }
        return index;
    }

}
