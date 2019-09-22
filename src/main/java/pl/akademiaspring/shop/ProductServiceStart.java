package pl.akademiaspring.shop;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceStart {

    private List<Product> basket;

    public ProductServiceStart(){
        Product product1 = new Product("Iron", generateRandomPrice());
        Product product2 = new Product("Vacuum cleaner", generateRandomPrice());
        Product product3 = new Product("Food processor", generateRandomPrice());
        Product product4 = new Product("Hair dryer", generateRandomPrice());
        Product product5 = new Product("Toaster", generateRandomPrice());
        basket = new ArrayList<>();
        basket.add(product1);
        basket.add(product2);
        basket.add(product3);
        basket.add(product4);
        basket.add(product5);
    }

    private double generateRandomPrice (){
        double rangeMin = 50;
        double rangeMax = 300;
        Random r = new Random();
        return  rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

    public double getTotalPrice(){
        double totalProductPrice = 0;
        for (Product product : basket) {
            totalProductPrice = totalProductPrice + product.getPrice();
        }
        return totalProductPrice;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void displayTotalPrice(){

        System.out.println("Total price : " + getTotalPrice());
    }



}
