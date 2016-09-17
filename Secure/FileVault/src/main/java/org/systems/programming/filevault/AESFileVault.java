package org.systems.programming.filevault;

import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
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
        String content = new String(Files.readAllBytes(Paths.get(absolutePath.concat("/").concat(fileName))));
        String decryptedContent = cipher.decrypt(content);

        return decryptedContent;
    }
}
