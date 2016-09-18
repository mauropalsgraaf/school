package org.systems.programming.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESCipher implements CipherInterface {
    private SecretKeySpec key;

    public AESCipher(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] keyBytes = key.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);

        this.key = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public String encrypt(String toEncrypt) throws EncryptionException {
        try {
            Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            aesCipher.init(Cipher.ENCRYPT_MODE, key);

            return Base64.getEncoder().encodeToString(aesCipher.doFinal(toEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new EncryptionException(e.getMessage());
        }
    }

    @Override
    public String decrypt(String toDecrypt) throws DecryptionException {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
        } catch (Exception e) {
            throw new DecryptionException(e.getMessage());
        }
    }
}
