package pl.akademiaspring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Profile("PRO")
public class ProductServicePro {

    private ProductServiceStart productServiceStart;

    @Value("${api-data.discount}")
    private double discount;

    @Value("${api-data.vat}")
    private double vat;

    @Autowired
    public ProductServicePro(ProductServiceStart productServiceStart){
        this.productServiceStart = productServiceStart;
    }


    public double getTotalPriceWithVAT(){
        double totalPrice = productServiceStart.getTotalPrice();
        double totalPriceWithVAT = totalPrice + (totalPrice * (vat / 100));
        System.out.println("Total price with VAT : " + totalPriceWithVAT);
        return totalPriceWithVAT;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getTotalPriceWithDiscount(){
        double totalPrice = getTotalPriceWithVAT();
        double totalPriceWithDiscount = totalPrice - (totalPrice * (discount / 100));
        System.out.println("Total price with discount : " + totalPriceWithDiscount);
    }
}
