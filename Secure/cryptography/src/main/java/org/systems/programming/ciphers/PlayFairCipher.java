package org.systems.programming.ciphers;

import org.systems.programming.ciphers.playfair.PlayFairKey;
import org.systems.programming.ciphers.playfair.PlayFairPair;
import org.systems.programming.ciphers.playfair.PlayFairMessageToEncrypt;

import java.util.List;
import java.util.stream.Collectors;

public class PlayFairCipher implements CipherInterface {

    private PlayFairKey playFairKey;

    @Override
    public String encrypt(String toEncrypt) {
        PlayFairMessageToEncrypt playFairMessageToEncrypt = new PlayFairMessageToEncrypt(toEncrypt);
        playFairMessageToEncrypt.prepare();

        List<PlayFairPair> playFairPairs = PlayFairPair.fromString(playFairMessageToEncrypt.toString());

        List<PlayFairPair> encryptedPlayFairPairs = playFairPairs
            .stream()
            .map(pair -> this.playFairKey.encryptPlayFairPair(pair))
            .collect(Collectors.toList());

        return PlayFairPair.listToString(encryptedPlayFairPairs);
    }

    @Override
    public String decrypt(String toDecrypt) {
        List<PlayFairPair> playFairPairs = PlayFairPair.fromString(toDecrypt);

        List<PlayFairPair> decryptedPlayFairPairs = playFairPairs
            .stream()
            .map(pair -> this.playFairKey.decryptPlayFairPair(pair))
            .collect(Collectors.toList());

        return PlayFairPair.listToString(decryptedPlayFairPairs);
    }

    public PlayFairCipher(PlayFairKey playFairKey) {
        this.playFairKey = playFairKey;
    }
}
