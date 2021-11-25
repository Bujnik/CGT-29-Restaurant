package main;


import main.kitchen.Cook;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(76);
        Cook amigoCook = new Cook("Amigo");
        tablet.addObserver(amigoCook);
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();


    }
}
