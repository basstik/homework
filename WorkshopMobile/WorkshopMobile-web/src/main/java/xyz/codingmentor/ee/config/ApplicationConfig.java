package xyz.codingmentor.ee.config;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import xyz.codingmentor.ee.config.service.CartRESTService;
import xyz.codingmentor.ee.config.service.InventoryRESTService;
import xyz.codingmentor.ee.config.service.UserRESTService;
import xyz.codingmentor.ee.exception.GeneralExceptionMapper;
import xyz.codingmentor.ee.exception.IdNotMatchExceptionMapper;
import xyz.codingmentor.ee.exception.ValidationExceptionMapper;

@ApplicationPath("/mobileworkshop")
public class ApplicationConfig extends Application {

    private final Set<Class<?>> classes;

    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(CartRESTService.class);
        c.add(InventoryRESTService.class);
        c.add(UserRESTService.class);
        
        c.add(IdNotMatchExceptionMapper.class);    
        c.add(ValidationExceptionMapper.class);
        c.add(GeneralExceptionMapper.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}