package main.statistics.event;

import java.util.Date;

public interface EventDataRow {
    //This is marker interface
    EventType getType();
    Date getDate();
    int getTime();
}
