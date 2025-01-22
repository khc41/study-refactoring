package study.refactoring;

public class PerformanceData {
    private String playID;
    private int audience;
    private Play play;

    public PerformanceData(String playID, int audience, Play play) {
        this.playID = playID;
        this.audience = audience;
        this.play = play;
    }

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
