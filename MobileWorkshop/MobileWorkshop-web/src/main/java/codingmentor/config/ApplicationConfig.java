package codingmentor.config;

import codingmentor.config.service.CartRESTService;
import codingmentor.config.service.InventoryRESTService;
import codingmentor.config.service.UserRESTService;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class ApplicationConfig extends Application {

    private final Set<Class<?>> classes;

    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(CartRESTService.class);
        c.add(InventoryRESTService.class);
        c.add(UserRESTService.class);
        //add Mapper-s

        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}