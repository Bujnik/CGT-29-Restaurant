package main;

import main.kitchen.Cook;
import main.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval){
        this.interval = interval;
        this.tablets = tablets;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Tablet tablet = tablets.get((int) (Math.random() * tablets.size() - 1));
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
