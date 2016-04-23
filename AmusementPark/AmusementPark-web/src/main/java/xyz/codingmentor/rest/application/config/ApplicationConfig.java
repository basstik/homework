
package xyz.codingmentor.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(xyz.codingmentor.rest.MachineREST.class);
        resources.add(xyz.codingmentor.rest.ParkREST.class);
        resources.add(xyz.codingmentor.rest.VisitorREST.class);
        resources.add(xyz.codingmentor.rest.mappers.GeneralExceptionMapper.class);
        resources.add(xyz.codingmentor.rest.mappers.IllegalArgumentExceptionMapper.class);
        resources.add(xyz.codingmentor.rest.mappers.NotEnoughAreaExceptionMapper.class);
        resources.add(xyz.codingmentor.rest.mappers.NotEnoughMoneyExceptionMapper.class);
        resources.add(xyz.codingmentor.rest.mappers.NotFoundExceptionMapper.class);
    }

}
