package com.mycompany;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class OrderEJB {

    public void sendOrderToWorkflowOhneReturn() {

        System.out.println("Befor sleep1.\t Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);           //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("After sleep1.\t Thread: " + Thread.currentThread().getName());

        System.out.println("Befor sleep2.\t Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);           //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("After sleep2.\t Thread: " + Thread.currentThread().getName());

        return;
    }

    public Future<Integer> sendOrderToWorkflowWithReturn() {

        System.out.println("Befor sleep1.\t Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);           //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("After sleep1.\t Thread: " + Thread.currentThread().getName());

        return new AsyncResult<>(10);
    }

}
