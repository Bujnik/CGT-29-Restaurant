package main;

import main.ad.Advertisement;
import main.ad.StatisticsAdvertisementManager;
import main.statistics.StatisticsManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ManagerTablet {
    //This class is representing Manager Tablet in restaurant, is responsible for printing several statistics
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdRevenue(){
        Map<Date, Long> adMap = StatisticsManager.getInstance().getAdRevenueByDay();
        double total = 0;
        //We need to display results in given format, as stated in code below
        for (Map.Entry<Date, Long> pair : adMap.entrySet()) {
            double splitValue = (double) pair.getValue()/100;
            ConsoleHelper.writeMessage(String.format("%s - %.2f", sdf.format(pair.getKey()), splitValue));
            total += splitValue;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total));
    }
    public void printCookUtilization(){
        Map<Date, Map<String, Integer>> cookMap = StatisticsManager.getInstance().getCookUtilizationByDay();
        for (Map.Entry<Date, Map<String, Integer>> pair : cookMap.entrySet()){
            ConsoleHelper.writeMessage(sdf.format(pair.getKey()));
            for (Map.Entry<String, Integer> pair2 : pair.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format("%s - %d min", pair2.getKey(), pair2.getValue()));
            }
        }
    }
    public void printActiveVideoSet(){
        List<Advertisement> list = StatisticsAdvertisementManager.getInstance().getVideoSets(true);
        for (Advertisement ad : list){
            ConsoleHelper.writeMessage(String.format("%s - %d", ad.getName(), ad.getImpressionsRemaining()));
        }
    }
    public void printArchivedVideoSet(){
        List<Advertisement> list = StatisticsAdvertisementManager.getInstance().getVideoSets(false);
        for (Advertisement ad : list){
            ConsoleHelper.writeMessage(String.format("%s", ad.getName()));
        }
    }
}
