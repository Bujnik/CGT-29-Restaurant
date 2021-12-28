package main.kitchen;

import main.ConsoleHelper;
import main.statistics.StatisticsManager;
import main.statistics.event.OrderReadyEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy = false;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) throws InterruptedException {
        //Busy if flag used to manage orders in run() method
        busy = true;
        int cookingTime = order.getTotalCookingTime();
        ConsoleHelper.writeMessage("Start cooking - "
                + order
                + ", cooking time "
                + cookingTime
                + " min");
        Thread.sleep(cookingTime * 10L);
        setChanged();
        notifyObservers(order);
        StatisticsManager.getInstance().record(
                new OrderReadyEventDataRow(order.getTablet().toString(), name, cookingTime, order.getDishes()));
        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        Thread orderThread = new Thread(() -> {
            while (true) {
                try {
                    if(!queue.isEmpty()) {
                        if (!isBusy()) {
                            startCookingOrder(queue.take());
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
}
