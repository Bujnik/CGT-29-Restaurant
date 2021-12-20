package main.statistics;

import main.statistics.event.EventDataRow;
import main.statistics.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsManager {
    //This class will be singleton, as we need only one stat manager,
    //also we will have one repository for events, so it will be inner class for this one
    private static StatisticsManager instance;
    private StatisticsStorage statisticsStorage = new StatisticsStorage();

    private StatisticsManager(){

    }
    public static StatisticsManager getInstance(){
        if (instance == null) {
            instance = new StatisticsManager();
        }
        return instance;
    }

    public void record (EventDataRow data) {

    }

    private class StatisticsStorage {
        //Events will be stored in map, divided by event type
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        StatisticsStorage(){
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<>());
            }
        }
    }

}
