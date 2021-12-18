package main.ad;

import main.ConsoleHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    //Will be assigned to each Tablet, will choose optimal sequence of ads

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    //targetList is our list of videos to be shown. setVideos method calls to this variable
    private List<Advertisement> targetList = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {

        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        //We check if we have only valid videos in our set (impressions > 0, duration <= timeSeconds)
        List<Advertisement> fitList = checkValidVideos(storage.list(), timeSeconds);
        if (fitList.isEmpty()) throw new NoVideoAvailableException();
        //We sort our whole set of videos by amount of money per impression and duration, descending.
        fitList.sort(Comparator.comparing(Advertisement::getAmountPerImpression)
                .thenComparing(Advertisement::getDuration).reversed());

        setVideos(fitList, new ArrayList<>(), timeSeconds);
        if (targetList.isEmpty()) throw new NoVideoAvailableException();

        //Requirements of task stated that we have to display Ad name, amount per impression and amount per impression, per second.
        //In my opinion, the best revenue would be not when we add most amount by impression videos, but when we add videos that have most amount of money per second.
        for (Advertisement ad : targetList) {
            ConsoleHelper.writeMessage("Displaying "
                    + ad.getName()
                    + "... "
                    + ad.getAmountPerImpression()
                    + ", "
                    + (int)(((double)ad.getAmountPerImpression() / ad.getDuration()) * 1000));
            ad.revalidate();
        }
    }

    private void setVideos(List<Advertisement> videoList, List<Advertisement> chosenList, int timeLeft) {
        //We proceed with ending criteria (our video repository has to be empty)
        if (videoList.isEmpty()) {
            if (targetList.isEmpty()) targetList = chosenList;
            else {
                int totTime1 = 0;
                long totCash1 = 0;
                for (Advertisement ad: targetList) {
                    totTime1 += ad.getDuration();
                    totCash1 += ad.getAmountPerImpression();
                }
                int totTime2 = 0;
                long totCash2 = 0;
                for (Advertisement ad: chosenList) {
                    totTime2 += ad.getDuration();
                    totCash2 += ad.getAmountPerImpression();
                }
                //First we compare revenue
                //Second - total time, the more, the better
                //Third - number of videos, the less, the better
                if (totCash1 != totCash2) targetList = totCash1 > totCash2 ? targetList : chosenList;
                else if (totTime1 != totTime2) targetList = totTime1 > totTime2 ? targetList : chosenList;
                else targetList = targetList.size() < chosenList.size() ? targetList : chosenList;
            }
        }
        else {
            //We take one video from the pool and analyze if it can be fit (it is ensured, that videos are sorted by money per video before calling the method
            Advertisement video = videoList.remove(0);
            if (video.getDuration() <= timeLeft) {
                chosenList.add(video);
                setVideos(videoList, chosenList, timeLeft - video.getDuration());
            }
            else setVideos(videoList, chosenList, timeLeft);
        }
    }

    private List<Advertisement> checkValidVideos(List<Advertisement> list, int timeSeconds) {
        List<Advertisement> fitList = new ArrayList<>();
        for (Advertisement ad : list) {
            //It is preliminary check, eliminates videos with invalid length or amount of impressions
            if (ad.duration <= timeSeconds && ad.getImpressionsRemaining() > 0) fitList.add(ad);
        }
        return fitList;
    }
}
