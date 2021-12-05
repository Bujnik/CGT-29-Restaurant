package main.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private final int duration;

    Dish(int i) {
        this.duration = i;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){
        StringBuilder sb = new StringBuilder();
        for (Dish dish : Dish.values()) sb.append(dish).append(", ");
        sb.replace(sb.lastIndexOf(","),sb.length()+1,"");
        return sb.toString();
    }
}
