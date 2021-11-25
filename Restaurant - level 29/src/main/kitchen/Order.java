package main.kitchen;

import main.ConsoleHelper;
import main.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("Your order: [");
        for (Dish dish : dishes) sb.append(dish).append(", ");
        sb.replace(sb.lastIndexOf(","),sb.length()+1,"").append("]");
        return sb.toString() + " from " + tablet.toString();
    }
}
