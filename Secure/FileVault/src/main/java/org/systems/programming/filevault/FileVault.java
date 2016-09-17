package org.systems.programming.filevault;

import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;

import java.io.IOException;

public interface FileVault {
    void newFile(String absolutePath, String fileName, String content) throws IOException, EncryptionException;

    void write(String absolutePath, String fileName, String newContent);

    String read(String absolutePath, String fileName) throws IOException, DecryptionException;
}
