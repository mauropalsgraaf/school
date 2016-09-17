package org.systems.programming;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.systems.programming.cipher.AESCipher;
import org.systems.programming.cipher.EncryptionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

public class FileVaultApp extends Application {

    private String key;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        HBox hbox = new HBox();

        Label keyLabel = new Label("Key:     ");
        Label selectedKeyLabel = new Label("");
        selectedKeyLabel.setPadding(new Insets(5));

        TextField keyField = new TextField();
        keyField.setAlignment(Pos.TOP_LEFT);

        Button setKeyButton = new Button("Use key from input field");

        setKeyButton.setOnMouseClicked(
            event -> {
                this.key = keyField.getText();
                keyField.setText("");
                selectedKeyLabel.setText(this.key);
            }
        );

        hbox.getChildren().addAll(keyLabel, keyField, setKeyButton, selectedKeyLabel);

        root.getChildren().add(hbox);

        Button openFileButton = new Button("Open file to encrypt");
        openFileButton.addEventHandler(
            MouseEvent.MOUSE_CLICKED,
            handleFileAction(primaryStage)
        );

        root.getChildren().addAll(openFileButton);

        Scene scene = new Scene(root, 800, 800);

        primaryStage.setTitle("FileVault");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private EventHandler<MouseEvent> handleFileAction(Stage primaryStage) {
        return event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file to encrypt");
            File file = fileChooser.showOpenDialog(primaryStage);
        };
    }

    private String convertFileToString(File file) throws IOException {
        assert file != null;

        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        return new String(data, "UTF-8");



//        } catch (Exception e) {
//            System.out.println("Since this is only a small programming assignment and a UI is not part" +
//                "of the course, we ignore this exceptions for now");
//        }
    }
}
