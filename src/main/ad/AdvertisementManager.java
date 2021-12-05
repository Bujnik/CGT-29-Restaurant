package main.ad;

import main.ConsoleHelper;

public class AdvertisementManager {
    //Will be assigned to each Tablet, will choose optimal sequence of ads

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){
        ConsoleHelper.writeMessage("Calling the processVideos method");
    }
}
