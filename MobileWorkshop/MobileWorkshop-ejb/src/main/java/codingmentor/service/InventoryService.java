package codingmentor.service;

import codingmentor.dto.MobileDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class InventoryService {

    private List<MobileDTO> mobilList = new ArrayList<>();


    @PostConstruct
    private void init() {
        mobilList.add(new MobileDTO("3310","Nokia",5000,3));
        mobilList.add(new MobileDTO("Galaxy S3","Samsung",34500,12));
        mobilList.add(new MobileDTO("S5","Apple",10000,5));
        mobilList.add(new MobileDTO("P8","Hauwei",49900,0));
    }

    @Lock(LockType.WRITE)
    public Integer addMobile(MobileDTO mobile) {
        mobilList.add(mobile);
        return mobilList.size();
    }
    
    public boolean mobileIsPurchasable(MobileDTO mobile){
        for (MobileDTO mob : mobilList) {
            if(mob.equals(mobile)){
                int piece = mob.getPiece();
                if(piece > 0){
                    return true;
                }
                else{
                    throw new IllegalArgumentException("There isn't available mobile");
                }
            }
        }
        return false;
    }

    //First search the available mobile and if the number of this mobile is
    //bigger than 0, than decrease the number
    public synchronized MobileDTO buyMobile(MobileDTO mobile) {
        if(mobileIsPurchasable(mobile)){
            mobile.setPiece(mobile.getPiece()-1);
            return mobile;
        }
        throw new IllegalArgumentException("No such mobile in inventory");
    }
    
    
         
    public List<MobileDTO> getMobilesList() {
        return mobilList;
    }


}
