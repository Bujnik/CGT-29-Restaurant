package main.kitchen;

import main.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg + " was prepared by " + o);
    }
}
