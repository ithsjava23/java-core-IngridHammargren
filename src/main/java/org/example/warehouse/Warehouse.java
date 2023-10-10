package org.example.warehouse;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class Warehouse {


    private final String name;
    private final List<ProductRecord> products = new ArrayList<>();
    private final List<ProductRecord> changedProducts = new ArrayList<>();


   private Warehouse(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public static Warehouse getInstance() {
        return new Warehouse("Default");
    }



    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }


    public boolean isEmpty() {

        return products.isEmpty();
    }

    public List<ProductRecord> getProducts() {
        return List.of(products.toArray(new ProductRecord[0]));
    }


    public ProductRecord addProduct(UUID id, String productName, Category category, BigDecimal price) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }


        UUID finalId = id;
        boolean productExist = products.stream().anyMatch(product -> product.id().equals(finalId));
        if (productExist) {

            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        ProductRecord product = new ProductRecord(id, productName, category, price);
        products.add(product);
        return product;

    }


    public Optional<ProductRecord> getProductById(UUID id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .findFirst();



    }

    public void updateProductPrice(UUID id, BigDecimal price) {
        Optional<ProductRecord> productOptional = getProductById(id);

        if (productOptional.isPresent()) {
            ProductRecord product = productOptional.get();

           ProductRecord updateProduct = new ProductRecord(product.id(), product.productName(), product.category(), price);

            products.replaceAll(p -> p.id().equals(id) ? updateProduct : p);
            changedProducts.add(product);

        } else {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }

    }

    public List<ProductRecord> getChangedProducts() {
        return List.copyOf(changedProducts);
    }


    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));


    }


    public List<ProductRecord> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }
}






