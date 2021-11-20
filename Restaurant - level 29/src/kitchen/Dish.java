package com.codegym.task.task27.task2712.kitchen;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString(){
        StringBuilder sb = new StringBuilder();
        for (Dish dish : Dish.values()) sb.append(dish).append(", ");
        sb.replace(sb.lastIndexOf(","),sb.length()+1,"");
        return sb.toString();
    }
}
