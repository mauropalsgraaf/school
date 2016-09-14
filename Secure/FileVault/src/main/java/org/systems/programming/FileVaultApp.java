package org.systems.programming;

import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class FileVaultApp {

    public static void main(String[] args) throws EncryptionException, DecryptionException, UnsupportedEncodingException, NoSuchAlgorithmException {
        AESCipher cipher = new AESCipher("test");
        System.out.println(cipher.encrypt("test"));
        System.out.println(cipher.decrypt(cipher.encrypt("test")));
    }
}
