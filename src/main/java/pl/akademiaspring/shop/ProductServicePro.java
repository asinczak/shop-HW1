package pl.akademiaspring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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
        return totalPrice + (totalPrice * (vat / 100));
    }

    public double getTotalPriceWithDiscount(){
        double totalPrice = getTotalPriceWithVAT();
        return totalPrice - (totalPrice * (discount / 100));
    }
}
