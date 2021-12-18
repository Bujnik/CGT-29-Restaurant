package main;

import main.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ConsoleHelper {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
        return br.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> output = new ArrayList<>();
        writeMessage("Choose dishes from list:");
        writeMessage(Dish.allDishesToString());
        String s = "";
        while (true){
            s = readString();
            if ((!s.equals("exit") && !Dish.allDishesToString().contains(s)) || s.equals("")) {
                writeMessage("There is no such dish. Try again.");
                continue;
            }
            if (s.equals("exit")) break;
            output.add(Dish.valueOf(s));
        }
        return output;
    }




}
