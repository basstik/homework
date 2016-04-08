package codingmentor.service;

import codingmentor.dto.MobileDTO;
import java.util.ArrayList;
import java.util.Collection;
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
//@LocalBean        ??????????????????????????MIÉRT NEM szabad IDERAKNI????  //http://stackoverflow.com/questions/21894958/injecting-stateless-local-ejb-3-1-into-webcomponent-in-weblogic-12c-not-worki
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class InventoryService {

    private List<MobileDTO> mobilList = new ArrayList<>();


    @PostConstruct
    private void init() {
         mobilList.add(new MobileDTO("3310","Nokia",5000,3));
         mobilList.add(new MobileDTO("Galaxy S3","Samsung",34500,12));
        //mobilList.add(new MobileDTO("S5-S5","Apple",10000,5));
        //mobilList.add(new MobileDTO("P8-P8","Hauwei",49900,0));
    }

    @Lock(LockType.WRITE)
    public Integer addMobile(MobileDTO mobile) {
        mobilList.add(mobile);
        return mobilList.size();
    }
    
    //Verify, that the mobile is in list and the number is bigger than 0
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

    //If the the mobile is purchasable then decrease the number of piece
    public synchronized MobileDTO buyMobile(MobileDTO mobile) {
        if(mobileIsPurchasable(mobile)){
            mobile.setPiece(mobile.getPiece()-1);
            return mobile;
        }
        throw new IllegalArgumentException("No such mobile in inventory");
    }
    
    
         
    public Collection<MobileDTO> getMobilesList() {
        return mobilList;
    }


}
