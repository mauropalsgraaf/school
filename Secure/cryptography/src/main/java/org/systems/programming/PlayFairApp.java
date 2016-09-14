package org.systems.programming;

import org.springframework.util.Assert;
import org.systems.programming.ciphers.CipherInterface;
import org.systems.programming.ciphers.PlayFairCipher;
import org.systems.programming.ciphers.playfair.PlayFairKey;

public class PlayFairApp {

    private static final String DECRYPT = "DECRYPT";
    private static final String ENCRYPT = "ENCRYPT";

    public static void main(String[] args) {
        Assert.isTrue(args.length == 3, "Please provide 3 arguments: Key, Message, Mode");
        Assert.isTrue(args[0].length() == 25, "The key provided should be a string of 25 characters");
        Assert.isTrue(args[1].length() != 0, "Message may not be empty");
        Assert.isTrue(args[2].equals(ENCRYPT) || args[2].equals(DECRYPT), "The mode should be ENCRYPT or DECRYPT");

        String[][] stringTable = PlayFairApp.stringKeyToStringTable(args[0]);

        PlayFairKey key = new PlayFairKey(stringTable);

        CipherInterface cipher = new PlayFairCipher(key);

        if (args[2].equals(ENCRYPT)) {
            System.out.println(cipher.encrypt(args[1]));
        }

        if (args[2].equals(DECRYPT)) {
            System.out.println(cipher.decrypt(args[1]));
        }
    }

    private static String[][] stringKeyToStringTable(String key) {
        String[][] stringTable = new String[5][5];

        for (int i = 0; i < key.length(); i++) {
            stringTable[i / 5][i % 5] = key.substring(i, i+1);
        }

        return stringTable;
    }
}
