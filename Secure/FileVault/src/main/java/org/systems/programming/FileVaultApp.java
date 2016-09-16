package org.systems.programming;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.DecryptionException;
import org.systems.programming.cipher.EncryptionException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class FileVaultApp extends Application {

//    public static void main(String[] args) throws EncryptionException, DecryptionException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        AESCipher cipher = new AESCipher("test");
//        System.out.println(cipher.encrypt("test"));
//        System.out.println(cipher.decrypt(cipher.encrypt("test")));
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        Button openFileButton = new Button("Open file to encrypt");
        openFileButton.addEventHandler(
            MouseEvent.MOUSE_CLICKED,
            event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open file to encrypt");
                fileChooser.showOpenDialog(primaryStage);
            }
        );

        root.getChildren().add(openFileButton);

        Scene scene = new Scene(root, 800, 800);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
