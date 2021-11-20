package com.codegym.task.task27.task2712.kitchen;

import com.codegym.task.task27.task2712.ConsoleHelper;
import com.codegym.task.task27.task2712.Tablet;

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
        return sb.toString() + "from " + tablet.toString();
    }
}
