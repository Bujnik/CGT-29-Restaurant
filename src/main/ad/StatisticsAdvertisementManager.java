package main.ad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatisticsAdvertisementManager {
    private static StatisticsAdvertisementManager statisticsAdvertisementManager;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticsAdvertisementManager(){}

    public static StatisticsAdvertisementManager getInstance() {
        if (statisticsAdvertisementManager == null) statisticsAdvertisementManager = new StatisticsAdvertisementManager();
        return statisticsAdvertisementManager;
    }

    public List<Advertisement> getVideoSets(boolean isActive) {
        //List is going to be already sorted by video name
        //true means active videos, false - inactive
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement ad : advertisementStorage.list()) {
            if ((ad.getImpressionsRemaining() > 0) == isActive) result.add(ad);
        }
        result.sort(Comparator.comparing(Advertisement::getName, String.CASE_INSENSITIVE_ORDER));
        return result;
    }
}
