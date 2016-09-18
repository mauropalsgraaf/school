package org.systems.programming;

import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;
import org.systems.programming.filevault.FileVault;
import org.systems.programming.filevault.AESFileVault;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class App {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("Welcome to the encrypted FileVault");
        System.out.println("What's your name");

        String username = UserInputScanner.getInputFromUser();

        System.out.println("\nWelcome ".concat(username));
        System.out.println("\nTo make sure this filevault is safe, we use AES Encryption. This means we need a key for encrypting and decrypting your files");
        System.out.println("\nPlease provide a key, for example ThisIsASafeKey2013#235:)");

        String key = UserInputScanner.getInputFromUser();

        System.out.println("\nThank you for providing a key, we will make sure its safe during this session");

        System.out.println("\nPlease provide a system location path where the filevault will be located, for example: /users/henkiedevries/Documents/Projects");
        System.out.println("Make sure this is an existing directory and the path is absolute, so no ~");

        String path = UserInputScanner.getInputFromUser();

        AESCipher cipher = new AESCipher(key);

        FileVault fileVault = new AESFileVault(cipher);

        System.out.println("We are ready to go");

        App.listInstructions();

        boolean shouldLoop = true;

        while (shouldLoop) {
            System.out.println("\nWhat would you like to do");
            String userAction = UserInputScanner.getInputFromUser();

            if (userAction.equals("NEW_FILE")) {
                newFile(fileVault, path);
            } else if (userAction.equals("READ_FILE")) {
                readFile(fileVault, path);
            } else if (userAction.equals("HELP")) {
                App.listInstructions();
            } else if (userAction.equals("EXIT")) {
                shouldLoop = false;
            } else {
                App.listInstructions();
            }
        }

        endSession();
    }

    private static void listInstructions() {
        System.out.println("\nWe have a few commands you can use to make use of this vault. A list is down below");
        System.out.println("\nTo Create a new file, use the following command: NEW_FILE");
        System.out.println("To read from an existing file from this vault, use the command: READ_FILE");
        System.out.println("If you have any trouble finding the right command, use the command: HELP");
        System.out.println("If you want to stop the session, use the command: EXIT");
    }

    private static void endSession() {
        System.out.println("The session is about to stop, if you want to decrypt the files, make sure you provide the same key next time");
        System.out.println("\nBye Bye");
    }

    private static void newFile(FileVault fileVault, String path) {
        System.out.println("\nPlease provide a name for the file with extention, for example: filename.txt");
        String filename = UserInputScanner.getInputFromUser();

        System.out.println("\nPlease provide the content. If you want a new line in the content, use \\n");
        String content = UserInputScanner.getInputFromUser();
        try {
            fileVault.newFile(path, filename, content);
        } catch (EncryptionException e) {
            System.out.println("\nERROR: Something went wrong with encrypting your file, please try again and report the error to the system administrator");
        } catch (IOException e) {
            System.out.println("\nAn error occured while encrypting the file: , are you sure the file exists?");
        }

        System.out.println("\nYour file has been stored successfully!");
    }

    private static void readFile(FileVault fileVault, String path) {
        System.out.println("\nPlease provide the filename you want to read");
        String filename = UserInputScanner.getInputFromUser();

        try {
            String content = fileVault.read(path, filename);
            System.out.println("\nHere you go: \n");
            System.out.println(content);
        } catch (DecryptionException e) {
            System.out.println("\nERROR: Something went wrong with encrypting your file, please try again and report the error to the system administrator");
        } catch (IOException e) {
            System.out.println("An error occured while decrypting the file, are you sure the file exists?");
        }
    }
}
