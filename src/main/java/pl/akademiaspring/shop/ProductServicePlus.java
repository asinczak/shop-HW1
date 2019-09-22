package pl.akademiaspring.shop;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
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

    @EventListener(ApplicationReadyEvent.class)
    public void getTotalPriceWithVAT(){
        double totalPrice = productServiceStart.getTotalPrice();
       double totalPriceWithVAT = totalPrice + (totalPrice * (vat / 100));
        System.out.println("Total price with VAT : " + totalPriceWithVAT);
    }


}
