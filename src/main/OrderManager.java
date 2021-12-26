package main;

import main.kitchen.Cook;
import main.kitchen.Order;
import main.statistics.StatisticsManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {

    LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread orderThread = new Thread(() -> {
            //Our thread is going to be daemon
            while (true) {
                try {
                    //Grabs set of cooks and checks if there are orders to make, then looks for cook available and makes him cook
                    Set<Cook> cooks = StatisticsManager.getInstance().getCooks();
                    if(!orderQueue.isEmpty()) {
                        for (Cook cook : cooks) {
                            if (!cook.isBusy()) {
                                cook.startCookingOrder(orderQueue.take());
                            }
                        }
                    }
                    else Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
        });
        orderThread.setDaemon(true);
        orderThread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tablet) orderQueue.add((Order) arg);
    }
}
