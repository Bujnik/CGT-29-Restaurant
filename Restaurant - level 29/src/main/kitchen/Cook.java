package main.kitchen;

import main.ConsoleHelper;

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
    }

    @Override
    public String toString() {
        return name;
    }
}
