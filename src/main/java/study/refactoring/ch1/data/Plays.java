package study.refactoring.ch1.data;

import java.util.Map;

public class Plays {
    private Map<String, Play> plays;

    public Plays(Map<String, Play> plays) {
        this.plays = plays;
    }

    public Play getPlay(String key) {
        return plays.get(key);
    }
}
