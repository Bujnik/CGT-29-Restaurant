package main;


import main.kitchen.Cook;
import main.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(76);
        Cook amigoCook = new Cook("Amigo");
        Waiter waiter = new Waiter();
        amigoCook.addObserver(waiter);
        tablet.addObserver(amigoCook);
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        //Checking if managerTablet works as intended
        ManagerTablet managerTablet = new ManagerTablet();
        managerTablet.printAdRevenue();
        managerTablet.printCookUtilization();
        managerTablet.printActiveVideoSet();
        managerTablet.printArchivedVideoSet();

    }
}
