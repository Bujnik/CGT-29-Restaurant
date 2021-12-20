package main.statistics.event;

import java.util.Date;

public class NoVideosAvailableEventDataRow {
    private int totalDuration;
    private Date currentDate;

    public NoVideosAvailableEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }
}
