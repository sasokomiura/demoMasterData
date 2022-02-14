package com.example.demoMasterData.tables;

import java.util.*;

public class Manufacturer {
    private String manufactured_id;
    private String manufactured_name;

    public String getManufactured_name() {
        return manufactured_name;
    }

    private Map<String, Product> products = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(manufactured_id, that.manufactured_id)
                && Objects.equals(manufactured_name, that.manufactured_name)
                && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufactured_id, manufactured_name, products);
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public String getManufactured_id() {
        return manufactured_id;
    }

    public Manufacturer(String manufactured_id, String manufactured_name) {
        this.manufactured_id = manufactured_id;
        this.manufactured_name = manufactured_name;
    }

    @Override
    public String toString() {
        return "Manufactured_id : " + this.manufactured_id +
                "; Manufactured_name : " + this.manufactured_name + ". " +
                (this.products == null ? "No active products." : this.getProducts().values());
    }
}
