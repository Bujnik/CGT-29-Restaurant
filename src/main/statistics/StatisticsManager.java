package main.statistics;

import main.kitchen.Cook;
import main.statistics.event.EventDataRow;
import main.statistics.event.EventType;

import java.util.*;

public class StatisticsManager {
    //This class will be singleton, as we need only one stat manager,
    //also we will have one repository for events, so it will be inner class for this one
    private static StatisticsManager instance;
    private StatisticsStorage statisticsStorage = new StatisticsStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticsManager(){
        //We populate cooks with our first cook, called Amigo
        cooks.add(new Cook("Amigo"));

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

    private class StatisticsStorage {
        //Events will be stored in map, divided by event type
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        StatisticsStorage(){
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<>());
            }
        }

        //We add our events to given list, by EventType
        private void put (EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }

}
