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

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
@LocalBean
public class CartService implements Serializable{
 
    private final List<MobileDTO> mobilList = new ArrayList<>();

    public CartService() {
        //default constuctor, because it is Bean class
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
