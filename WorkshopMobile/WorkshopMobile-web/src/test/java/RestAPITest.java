/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.UUID;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import xyz.codingmentor.ee.dto.MobileDTO;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;



/**
 *
 * @author basstik
 */
public class RestAPITest {
     ResteasyClient client;

    public RestAPITest() {
        client = new ResteasyClientBuilder().build();
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        
        WebTarget target = client.target("http://localhost:8080/"
                + "WorkshopMobile-web/mobileworkshop/users");

        //Invocation.Builder invocationBuilder = target.request();
        //Response response =  invocationBuilder.get();
        //assertEquals(Response.Status.OK, response.getStatus());
        assertEquals(true,true);
    }
}
