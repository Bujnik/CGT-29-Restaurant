package main;

import main.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }

    public Order createOrder(){
        try {
            Order order = new Order(this);
            /*Observable - Observer logic:
                1. Observer is added to Observable object
                (Restaurant - main: Cook object to Tablet object)
                2. Observable notifies observant when crucial action is done - order is created
                3. Observer can proceed with update() method by receiving current status

            */
            if (!order.isEmpty()){
                //Order is sent to the Cook only if contains dishes
                setChanged();
                notifyObservers(order);
            }
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "The console is unavailable.");
            return null;
        }
    }
}
