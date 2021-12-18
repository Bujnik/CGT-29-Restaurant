package main.ad;

public class Advertisement {
    private Object content;
    protected String name;
    private long amountPaid;
    private int impressionsRemaining;
    protected int duration;
    private long amountPerImpression;

    public Advertisement(Object content, String name, long amountPaid, int impressionsRemaining, int duration) {
        this.content = content;
        this.name = name;
        this.amountPaid = amountPaid;
        this.impressionsRemaining = impressionsRemaining;
        this.duration = duration;
        if (duration == 0) this.duration = -1;
        if (impressionsRemaining == 0) {
            this.amountPerImpression = 0;
            this.duration = -1;
        }
        else this.amountPerImpression = amountPaid / impressionsRemaining;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerImpression() {
        return amountPerImpression;
    }

    public int getImpressionsRemaining() {
        return impressionsRemaining;
    }

    public void revalidate() {
        //Number of impressions must be positive
        if (impressionsRemaining <= 0) throw new UnsupportedOperationException();
        else impressionsRemaining--;
    }
}
