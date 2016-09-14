package org.systems.programming.ciphers.playfair;

import java.util.ArrayList;
import java.util.List;

public class PlayFairPair {

    private String pair;

    public static List<PlayFairPair> fromString(String toConvert) {
        assert toConvert.length() % 2 == 0;

        List<PlayFairPair> playFairPairs = new ArrayList<>();

        for (int i = 0; i < toConvert.length(); i = i+2) {
            playFairPairs.add(
                new PlayFairPair(
                    toConvert.substring(i, i + 2)
                )
            );
        }

        return playFairPairs;
    }

    public static String listToString(List<PlayFairPair> pairs) {
        return pairs.stream().map(PlayFairPair::toString).reduce("", String::concat);
    }

    public static PlayFairPair fromStringCharacters(String char1, String char2) {
        return new PlayFairPair(char1.concat(char2));
    }

    public String first() {
        return this.pair.substring(0, 1);
    }

    public String second() {
        return this.pair.substring(1, 2);
    }

    public String toString() {
        return this.pair;
    }

    private PlayFairPair(String pair) {
        assert pair.length() == 2;

        this.pair = pair;
    }
}
