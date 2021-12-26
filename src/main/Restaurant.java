package main;


import main.kitchen.Cook;
import main.kitchen.Waiter;
import main.statistics.StatisticsManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    //This constant will be used for randomly generated orders
    private static final int ORDER_CREATION_INTERVAL = 100;

    public static void main(String[] args) throws InterruptedException {

        //Created 2 cooks, 2 waiters and registered the cooks
        Cook amigoCook = new Cook("Amigo");
        Waiter waiter = new Waiter();
        amigoCook.addObserver(waiter);

        Cook professorCook = new Cook("Professor");
        Waiter waiter2 = new Waiter();
        professorCook.addObserver(waiter2);

        StatisticsManager.getInstance().register(amigoCook);
        StatisticsManager.getInstance().register(professorCook);

        OrderManager orderManager = new OrderManager();
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.addObserver(orderManager);
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
