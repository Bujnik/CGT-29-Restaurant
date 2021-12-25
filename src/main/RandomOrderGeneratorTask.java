package main;

import main.kitchen.Cook;
import main.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> tablets = new ArrayList<>();
    {
        tablets.add(new Tablet(1));
        tablets.add(new Tablet(2));
        tablets.add(new Tablet(3));
        tablets.add(new Tablet(4));
    }
    private Cook cook;
    private int interval;

    public RandomOrderGeneratorTask(int interval, Cook cook){
        this.interval = interval;
        this.cook = cook;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Tablet tablet = tablets.get((int) (Math.random() * tablets.size() - 1));
                Waiter waiter = new Waiter();
                cook.addObserver(waiter);
                tablet.addObserver(cook);
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
