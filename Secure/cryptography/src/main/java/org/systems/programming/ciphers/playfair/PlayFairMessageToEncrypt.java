package org.systems.programming.ciphers.playfair;

public class PlayFairMessageToEncrypt {

    private final String PLAY_FAIR_SEPERATION_CHARACTER = "x";
    private final String PLAY_FAIR_PADDING_CHARACTER = "z";

    private String toEncrypt;

    public PlayFairMessageToEncrypt(String toEncrypt) {
        this.toEncrypt = toEncrypt;
    }

    public void prepare() {
        this.toEncrypt = prepareToEncrypt(this.toEncrypt);
    }

    public String toString() {
        return toEncrypt;
    }

    private String prepareToEncrypt(String toEncrypt) {
        assert toEncrypt.length() > 0;

        return addPadding(
            seperateMultipleOccurancesNextToEachother(
                replaceJWithI(toEncrypt)
            )
        );
    }

    private String replaceJWithI(String toEncrypt) {
        return toEncrypt.replace('j', 'i');
    }

    private String seperateMultipleOccurancesNextToEachother(String toEncrypt) {
        System.out.println("toEncrypt = " + toEncrypt);
        String prev = toEncrypt.substring(0, 1);
        String result = prev;

        for (int i = 1; i < toEncrypt.length(); i++) {
            System.out.println("toEncrypt = " + toEncrypt.length());
            String current = toEncrypt.substring(i, i + 1);

            if (current.equals(prev) && result.length() % 2 == 1) {
                result = result.concat(PLAY_FAIR_SEPERATION_CHARACTER + current);
            } else {
                result = result.concat(current);
            }

            prev = current;

        }

        System.out.println("result = " + result);

        return result;
    }

    private String addPadding(String toEncrypt) {
        if (toEncrypt.length() % 2 == 1) {
            return toEncrypt.concat(String.valueOf(PLAY_FAIR_PADDING_CHARACTER));
        }

        return toEncrypt;
    }
}
