package org.systems.programming;

import org.springframework.util.Assert;
import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;
import org.systems.programming.filevault.FileVault;
import org.systems.programming.filevault.AESFileVault;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, EncryptionException, DecryptionException {
        args = new String[5];
        args[0] = "key";
        args[1] = "READ";

        Assert.isTrue(args.length >= 2);
        Assert.isTrue(
            args[1].equals("NEW_FILE") || args[1].equals("WRITE") || args[1].equals("READ"),
            "MODE should either be NEW_FILE, WRITE OR READ"
        );

        AESCipher cipher = new AESCipher(args[0]);

        FileVault fileVault = new AESFileVault(cipher);

        if (args[1].equals("NEW_FILE")) {
            Assert.isTrue(args.length == 5, "When creating a new file, provide Path, filename, content of file");
            args[2] = "/users/mauropalsgraaf/Documents/Projects/test";
            args[3] = "filename.txt";
            args[4] = "content";


            fileVault.newFile(args[2], args[3], args[4]);
        }

        if (args[1].equals("READ")) {
            Assert.isTrue(args.length == 5, "When creating a new file, provide Path and filename");
            args[2] = "/users/mauropalsgraaf/Documents/Projects/test";
            args[3] = "filename.txt";

            System.out.println(fileVault.read(args[2], args[3]));
        }
    }

}
