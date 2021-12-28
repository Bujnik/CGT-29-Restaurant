package main;


import main.kitchen.Cook;
import main.kitchen.Order;
import main.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    //This constant will be used for randomly generated orders
    private static final int ORDER_CREATION_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {

        //Created 2 cooks, 2 waiters and created threads based on Cook object
        Cook amigoCook = new Cook("Amigo");
        Waiter waiter = new Waiter();
        amigoCook.addObserver(waiter);
        amigoCook.setQueue(orderQueue);
        Thread cook1Thread = new Thread(amigoCook);
        cook1Thread.start();

        Cook professorCook = new Cook("Professor");
        Waiter waiter2 = new Waiter();
        professorCook.addObserver(waiter2);
        professorCook.setQueue(orderQueue);
        Thread cook2Thread = new Thread(professorCook);
        cook2Thread.start();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask rogt = new RandomOrderGeneratorTask(tablets, ORDER_CREATION_INTERVAL);
        Thread t1 = new Thread(rogt);
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();

        //Checking if managerTablet works as intended
        ManagerTablet managerTablet = new ManagerTablet();
        managerTablet.printAdRevenue();
        managerTablet.printCookUtilization();
        managerTablet.printActiveVideoSet();
        managerTablet.printArchivedVideoSet();

    }
}
