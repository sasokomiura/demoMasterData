package com.example.demoMasterData.controller;

import com.example.demoMasterData.parser.ParseJsonIntoEntities;
import com.example.demoMasterData.tables.Manufacturer;
import com.example.demoMasterData.tables.Product;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping()
public class MessageController {

    private List<Manufacturer> manufacturerList = new LinkedList<>();

    @GetMapping
    public String homeList() {
        return manufacturerList.toString();
    }

    @GetMapping("getAllProducts")
    private String getAllProducts(@RequestParam String manufacturer_id) {

        if (manufacturerList.stream().filter(t ->
                        t.getManufactured_id().equals(manufacturer_id))
                .findFirst()
                .isEmpty()) {
            return "There are no manufacturer with manufacturer_id " + manufacturer_id;
        }

        Manufacturer manufacturer = manufacturerList.stream().filter(t ->
                        t.getManufactured_id().equals(manufacturer_id))
                .findFirst()
                .get();

        return (manufacturer.getProducts() == null
                ? "There are no products for manufacturer with id " + manufacturer_id
                : manufacturer.getProducts().values() + "\n"
        );
    }

    @PostMapping("addManufacturer")
    public String createManufacturer(@RequestBody String jsonString) {
        ParseJsonIntoEntities obj = new ParseJsonIntoEntities(jsonString);
        String manufacturer_id = obj.getValue("manufacturer_id");
        if (manufacturer_id.length() == 0) return ("Invalid JSON. Please add manufacturer_id");

        String manufacturer_name = obj.getValue("manufacturer_name");
        if (manufacturer_name.length() == 0) return ("Invalid JSON. Please add manufacturer_name");

        Manufacturer manufacturer = new Manufacturer(manufacturer_id, manufacturer_name);
        if (manufacturerList.contains(manufacturer))
            return (manufacturer_name + " with id " + manufacturer_id + " already exists");

        manufacturerList.add(manufacturer);

        return (manufacturer_name + " with id " + manufacturer_id + " added");
    }

    @PostMapping("addProduct")
    public String addProduct(@RequestBody String jsonString) {
        ParseJsonIntoEntities obj = new ParseJsonIntoEntities(jsonString);

        String manufacturer_id = obj.getValue("manufacturer_id");
        if (manufacturer_id.length() == 0) return ("Invalid JSON. Please add manufacturer_id");

        String product_id = obj.getValue("product_id");
        if (product_id.length() == 0) return ("Invalid JSON. Please add product_id");

        //не обязательное поле
        String product_name = obj.getValue("product_name");

        Product newProduct = new Product(product_id, product_name, manufacturer_id);
        for (Manufacturer manufacturer : manufacturerList) {
            if (manufacturer.getManufactured_id().equals(manufacturer_id)) {
                if (manufacturer.getProducts().containsKey(product_id))
                    return "There are already exists this product id for current manufacturer. " +
                            "Please use PUT method to update.";
                manufacturer.getProducts().put(product_id, newProduct);
                return "Product successfully added";
            }

        }

        return "There are no manufacturer with id " + manufacturer_id;

        /*
        *можно создать производителя, если он не был создан к этому моменту, вместо возвращения отказа
        createManufacturer(jsonString);
        return addProduct(jsonString);
         */

    }

    @PutMapping("addProduct")
    public String updateManufacturersProducts(@RequestBody String jsonString) {
        ParseJsonIntoEntities obj = new ParseJsonIntoEntities(jsonString);

        String manufacturer_id = obj.getValue("manufacturer_id");
        if (manufacturer_id.length() == 0) return ("Invalid JSON. Please add manufacturer_id");

        String product_id = obj.getValue("product_id");
        if (product_id.length() == 0) return ("Invalid JSON. Please add product_id");

        //не обязательное поле
        String product_name = obj.getValue("product_name");

        Product newProduct = new Product(product_id, product_name, manufacturer_id);
        for (Manufacturer manufacturer : manufacturerList) {
            if (manufacturer.getManufactured_id().equals(manufacturer_id)) {
                if (!manufacturer.getProducts().containsKey(product_id))
                    return "There are no product for current manufacturer. Please use POST method to add.";
                manufacturer.getProducts().put(product_id, newProduct);
                return "Product successfully updated";
            }

        }
        return "There are no manufacturer with id " + manufacturer_id;
    }

    @GetMapping("getBigManufactures")
    public List<String> getManufactoriesWithProductsCountMoreThanInputParameter(@RequestParam int counter) {
        List<String> result = new ArrayList<>();
        for (Manufacturer manufacturer : manufacturerList) {
            if (manufacturer.getProducts().size() > counter)
                result.add(manufacturer.getManufactured_name());
        }
        return result;
    }

}
