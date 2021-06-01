package ru.nellyd3v.tictactoe_fx.code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static Stage window;

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("/ru/nellyd3v/tictactoe_fx/resources/interface.fxml")
        );
        window.setTitle("Крестики-нолики");
        window.setScene(new Scene(root, 300, 300));
        window.setResizable(false);
        Main.window = window;
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
