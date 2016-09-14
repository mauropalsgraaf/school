package org.systems.programming.filevault;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface FileVault {
    void write(byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException;

    String read(byte[] key);
}
