// Write a Java program to process a large dataset of products using streams. 
// Perform operations such as grouping products by category, finding the most expensive product in each category, 
// and calculating the average price of all products.

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String toString() {
        return name + " | Category: " + category + " | Price: $" + price;
    }
}

public class experiment6_3_22BCS13785 {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1500),
            new Product("Phone", "Electronics", 800),
            new Product("Shirt", "Clothing", 50),
            new Product("Jeans", "Clothing", 70),
            new Product("TV", "Electronics", 1200),
            new Product("Blender", "Home", 100),
            new Product("Sofa", "Home", 700)
        );

        // Group by category
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        // Most expensive product in each category
        Map<String, Optional<Product>> maxInEachCategory = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));

        // Average price of all products
        double averagePrice = products.stream()
                .mapToDouble(p -> p.price)
                .average()
                .orElse(0.0);

        System.out.println("Products Grouped by Category:");
        groupedByCategory.forEach((category, list) -> {
            System.out.println(category + ": " + list);
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        maxInEachCategory.forEach((category, product) -> {
            product.ifPresent(p -> System.out.println(category + ": " + p));
        });

        System.out.printf("\nAverage Price of All Products: $%.2f\n", averagePrice);
    }
}
