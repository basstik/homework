package xyz.codingmentor.ee.service;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import xyz.codingmentor.ee.dto.MobileDTO;
import xyz.codingmentor.ee.interceptor.BeanValidation;

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
@LocalBean
@BeanValidation
public class CartService implements Serializable{
 
    private final List<MobileDTO> mobilList = new ArrayList<>();

    public CartService() {
        //default constuctor
    }

    public Integer addToCart(MobileDTO mobile) {
        mobilList.add(mobile);
        return mobilList.size();
    }

    public List<MobileDTO> getMobiles() {
        return mobilList;
    }

    @Remove
    public void checkout() {
        mobilList.clear();
    }
}
