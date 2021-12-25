package main.kitchen;

import main.ConsoleHelper;
import main.statistics.StatisticsManager;
import main.statistics.event.OrderReadyEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - "
                + order
                + ", cooking time "
                + order.getTotalCookingTime()
                + " min");
        setChanged();
        notifyObservers(arg);
        StatisticsManager.getInstance().record(
                //Tablet Name needed for constructor we get from our observable object passed to the method
                new OrderReadyEventDataRow(o.toString(), name, order.getTotalCookingTime(), order.getDishes()));

    }

    @Override
    public String toString() {
        return name;
    }
}
