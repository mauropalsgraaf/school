package org.systems.programming.ciphers;

public interface CipherInterface {
    String encrypt(String toEncrypt);
    String decrypt(String toDecrypt);
}
