package org.systems.programming.cipher;

public interface CipherInterface {
    String encrypt(String toEncrypt) throws EncryptionException;
    String decrypt(String toDecrypt) throws DecryptionException;
}
