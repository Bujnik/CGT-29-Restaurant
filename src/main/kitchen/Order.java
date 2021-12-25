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
        initDishes();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    public int getTotalCookingTime(){
        int time = 0;
        for (Dish dish : dishes) time += dish.getDuration();
        return time;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("Your order: [");
        for (Dish dish : dishes) sb.append(dish).append(", ");
        sb.replace(sb.lastIndexOf(","),sb.length()+1,"").append("]");
        return sb.toString() + " from " + tablet.toString();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
