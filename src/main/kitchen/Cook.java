package main.kitchen;

import main.ConsoleHelper;
import main.statistics.StatisticsManager;
import main.statistics.event.OrderReadyEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable{
    private String name;
    private boolean busy = false;

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) throws InterruptedException {
        //Busy if flag used to manage orders in OrderManager class
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
}
