package pl.akademiaspring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("PLUS")
public class ProductServicePlus {

    private ProductServiceStart productServiceStart;

    @Value("${api-data.vat}")
    private double vat;

    @Autowired
    public ProductServicePlus(ProductServiceStart productServiceStart) {
        this.productServiceStart = productServiceStart;
    }

    public double getTotalPriceWithVAT(){
        double totalPrice = productServiceStart.getTotalPrice();
       return totalPrice + (totalPrice * (vat / 100));
    }


}
