package main.kitchen;

import main.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        int numberOfDishes = (int) (Math.random() * 5 + 1);
        for (int i = 0; i < numberOfDishes; i++){
            int randomNumber = (int) (Math.random() * (Dish.values().length - 1));
            Dish dish = Dish.values()[randomNumber];
            dishes.add(dish);
        }
    }
}
