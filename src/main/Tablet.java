package main;

import main.ad.AdvertisementManager;
import main.ad.NoVideoAvailableException;
import main.kitchen.Order;
import main.kitchen.TestOrder;

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
            processOrder(order);
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "The console is unavailable.");
            return null;
        }
    }

    public void createTestOrder() {
        try {
            //Logic is the same as regular Order
            Order order = new TestOrder(this);
            processOrder(order);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "The console is unavailable.");
        }
    }

    private void processOrder(Order order) {
        if (!order.isEmpty()) {
            //Order is sent to the Cook only if contains dishes
            setChanged();
            notifyObservers(order);
            try {
                //If there is nothing on the playlist, exception will be thrown, we have to inform the manager.
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the following order: " + order);
            }
        }
    }
}
