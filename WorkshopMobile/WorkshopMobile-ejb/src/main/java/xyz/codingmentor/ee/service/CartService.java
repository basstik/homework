package xyz.codingmentor.ee.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import xyz.codingmentor.ee.dto.MobileDTO;

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
@LocalBean
public class CartService {
    
    @Resource
    SessionContext context;     //It is important, because this field remember,
                //whose is this Bean

    public CartService() {
    }

    private final List<MobileDTO> mobilList = new ArrayList<>();

    @PrePassivate
    private void passivate() {
       //products.add(new MobileDTO());
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
