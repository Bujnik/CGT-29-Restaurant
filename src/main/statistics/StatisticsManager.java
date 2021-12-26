package main.statistics;

import main.kitchen.Cook;
import main.statistics.event.EventDataRow;
import main.statistics.event.EventType;
import main.statistics.event.OrderReadyEventDataRow;
import main.statistics.event.VideosSelectedEventDataRow;

import java.util.*;

public class StatisticsManager {
    //This class will be singleton, as we need only one stat manager,
    //also we will have one repository for events, so it will be inner class for this one
    private static StatisticsManager instance;
    private StatisticsStorage statisticsStorage = new StatisticsStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticsManager(){}

    public Set<Cook> getCooks() {
        return cooks;
    }

    public static StatisticsManager getInstance(){
        if (instance == null) {
            instance = new StatisticsManager();
        }
        return instance;
    }

    public void record (EventDataRow data) {
        statisticsStorage.put(data);
    }

    public void register(Cook cook){
        cooks.add(cook);
    }

    public Map<Date, Long> getAdRevenueByDay(){
        List<EventDataRow> adSets = statisticsStorage.getListByEvent(EventType.VIDEOS_SELECTED);
        //We have to sort our results descending by date
        Map<Date, Long> result = new TreeMap<>(Comparator.reverseOrder());
        for (EventDataRow ad : adSets) {
            Date date = removeTime(ad.getDate());
            long revenue = ((VideosSelectedEventDataRow) ad).getAmount();
            if (!result.containsKey(date)) result.put(date, revenue);
            else result.put(date, result.get(date) + revenue);
        }
        return result;
    }

    public Map<Date, Map<String, Integer>> getCookUtilizationByDay(){
        List<EventDataRow> orderSets = statisticsStorage.getListByEvent(EventType.ORDER_READY);
        //We sort our results descending by date, ascending by name of the cook
        Map<Date, Map<String, Integer>> result = new TreeMap<>(Comparator.reverseOrder());
        for (EventDataRow order: orderSets) {
            Date date = removeTime(order.getDate());
            String name = ((OrderReadyEventDataRow) order).getCookName();
            int time = order.getTime();
            if (!result.containsKey(date)) {
                result.put(date, new TreeMap<>());
                result.get(date).put(name, time);
            }
            else {
                Map<String, Integer> tempMap = result.get(date);
                if (!tempMap.containsKey(name)) tempMap.put(name, time);
                else tempMap.put(name, tempMap.get(name) + time);
                result.put(date, tempMap);
            }
        }
        return result;
    }

    //This method cuts off hours/minutes/seconds/milliseconds from our input,
    //to ensure that dates put in resultMap are limited to the same day only
    private Date removeTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private class StatisticsStorage {
        //Events will be stored in map, divided by event type
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        StatisticsStorage(){
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<>());
            }
        }

        private List<EventDataRow> getListByEvent(EventType eventType) {
            return storage.get(eventType);
        }

        //We add our events to given list, by EventType
        private void put (EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }

}
