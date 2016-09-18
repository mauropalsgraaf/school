package org.systems.programming.ciphers.playfair;

import java.util.function.Function;

public class PlayFairKey {

    final int NUMBER_OF_CHARACTERS_ON_ROW = 5;
    final int NUMBER_OF_CHARACTERS_ON_COLUMN = 5;

    private String[][] key;

    public PlayFairKey(String[][] key) {
        assert key.length == 5;

        for (String[] k : key) {
            assert k.length == 5;
        }

        this.key = key;
    }

    public PlayFairPair encryptPlayFairPair(PlayFairPair pair) {
        PlayFairCharacterLocation location1 = findCharacterInKey(pair.first());
        PlayFairCharacterLocation location2 = findCharacterInKey(pair.second());

        return encryptPair(location1, location2);
    }

    public PlayFairPair decryptPlayFairPair(PlayFairPair pair) {
        PlayFairCharacterLocation location1 = findCharacterInKey(pair.first());
        PlayFairCharacterLocation location2 = findCharacterInKey(pair.second());

        return decryptPair(location1, location2);
    }

    private PlayFairCharacterLocation findCharacterInKey(String character) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                if (key[i][j].equals(character)) {
                    return new PlayFairCharacterLocation(j, i);
                }
            }
        }

        return null;
    }

    private PlayFairPair encryptPair(
        PlayFairCharacterLocation firstCharacter,
        PlayFairCharacterLocation secondCharacter
    ) {
        if (firstCharacter.getX() == secondCharacter.getX()) {
            return this.getPlayFairPairFromSameColumn(
                firstCharacter,
                secondCharacter,
                playFairCharacterLocation -> playFairCharacterLocation.getY() == NUMBER_OF_CHARACTERS_ON_COLUMN - 1
                    ? 0
                    : playFairCharacterLocation.getY() + 1
            );
        }

        if (firstCharacter.getY() == secondCharacter.getY()) {
            return this.getPlayFairPairFromSameRow(
                firstCharacter,
                secondCharacter,
                playFairCharacterLocation -> playFairCharacterLocation.getX() == NUMBER_OF_CHARACTERS_ON_ROW - 1
                    ? 0
                    : playFairCharacterLocation.getX() + 1
            );
        }

        return PlayFairPair.fromStringCharacters(
            key[firstCharacter.getY()][secondCharacter.getX()],
            key[secondCharacter.getY()][firstCharacter.getX()]
        );
    }

    private PlayFairPair decryptPair(
        PlayFairCharacterLocation firstCharacter,
        PlayFairCharacterLocation secondCharacter
    ) {
        if (firstCharacter.getX() == secondCharacter.getX()) {
            return this.getPlayFairPairFromSameColumn(
                firstCharacter,
                secondCharacter,
                playFairCharacterLocation -> playFairCharacterLocation.getY() == 0
                    ? NUMBER_OF_CHARACTERS_ON_COLUMN - 1
                    : playFairCharacterLocation.getY() - 1
            );
        }

        if (firstCharacter.getY() == secondCharacter.getY()) {
            return this.getPlayFairPairFromSameRow(
                firstCharacter,
                secondCharacter,
                playFairCharacterLocation -> playFairCharacterLocation.getX() == 0
                    ? NUMBER_OF_CHARACTERS_ON_ROW - 1
                    : playFairCharacterLocation.getX() - 1
            );
        }

        return PlayFairPair.fromStringCharacters(
            key[firstCharacter.getY()][secondCharacter.getX()],
            key[secondCharacter.getY()][firstCharacter.getX()]
        );
    }

    private PlayFairPair getPlayFairPairFromSameRow(
        PlayFairCharacterLocation firstCharacter,
        PlayFairCharacterLocation secondCharacter,
        Function<PlayFairCharacterLocation, Integer> calculateEncryptedCharacterLocation
    ) {
        int xLocationFirstCharacter = calculateEncryptedCharacterLocation.apply(firstCharacter);
        int xLocationSecondCharacter = calculateEncryptedCharacterLocation.apply(secondCharacter);

        return PlayFairPair.fromStringCharacters(
            key[firstCharacter.getY()][xLocationFirstCharacter],
            key[secondCharacter.getY()][xLocationSecondCharacter]
        );
    }

    private PlayFairPair getPlayFairPairFromSameColumn(
        PlayFairCharacterLocation firstCharacter,
        PlayFairCharacterLocation secondCharacter,
        Function<PlayFairCharacterLocation, Integer> calculatePlayerLocation
    ) {
        int yLocationFirstCharacter = calculatePlayerLocation.apply(firstCharacter);
        int yLocationSecondCharacter = calculatePlayerLocation.apply(secondCharacter);

        return PlayFairPair.fromStringCharacters(
            key[yLocationFirstCharacter][firstCharacter.getX()],
            key[yLocationSecondCharacter][secondCharacter.getX()]
        );
    }

    private class PlayFairCharacterLocation {
        private int x;
        private int y;

        PlayFairCharacterLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
