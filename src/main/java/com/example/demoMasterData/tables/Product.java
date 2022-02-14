package com.example.demoMasterData.tables;

import java.util.Objects;

public class Product {

    public String getProduct_id() {
        return product_id;
    }

    private String product_id;
    private String product_name;
    private String manufactured_id;

    public Product(String product_id, String product_name, String manufactured_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.manufactured_id = manufactured_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(product_id, product.product_id)
                //&& Objects.equals(product_name, product.product_name)
                && Objects.equals(manufactured_id, product.manufactured_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_name, manufactured_id);
    }

    @Override
    public String toString() {
        return "\nproduct_id=" + product_id + ", product_name=" + product_name;
    }
}
