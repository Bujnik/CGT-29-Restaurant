package main.statistics.event;

import main.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideosSelectedEventDataRow implements EventDataRow{
    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;
    private Date currentDate;


    public VideosSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.VIDEOS_SELECTED;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    public long getAmount() {
        return amount;
    }
}
