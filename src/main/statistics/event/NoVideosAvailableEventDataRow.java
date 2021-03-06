package main.statistics.event;

import java.util.Date;

public class NoVideosAvailableEventDataRow implements EventDataRow{
    private int totalDuration;
    private Date currentDate;

    public NoVideosAvailableEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.NO_VIDEOS_AVAILABLE;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return 0;
    }
}
