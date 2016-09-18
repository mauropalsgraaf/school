package org.systems.programming.filevault;

import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AESFileVault implements FileVault {

    private AESCipher cipher;

    public AESFileVault(AESCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public void newFile(String absolutePath, String fileName, String content) throws IOException, EncryptionException {
        new File(absolutePath.concat("/").concat(fileName));
        FileOutputStream out = new FileOutputStream(absolutePath.concat("/").concat(fileName));

        String encryptedContent = cipher.encrypt(content);
        out.write(encryptedContent.getBytes());
        out.close();
    }

    @Override
    public void write(String absolutePath, String fileName, String newContent) {
    }

    @Override
    public String read(String absolutePath, String fileName) throws IOException, DecryptionException {
        String fullPath = absolutePath.concat("/").concat(fileName);
        Path path = Paths.get(fullPath);
        byte[] fileContent = Files.readAllBytes(path);
        return cipher.decrypt(new String(fileContent));
    }
}
