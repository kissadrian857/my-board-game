package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.tinylog.Logger;


public class MainMenuController {
    @FXML
    private Button newGameButton;
    @FXML
    private Button scoresButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handleNewGame(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Logger.info("Clicked on newGameButton Button.");
        loadScene(stage, "/setPlayers.fxml");
    }

    @FXML
    private void handleScores(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Logger.info("Clicked on scoresButton Button.");
        loadScene(stage, "/resultView.fxml");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Logger.info("Clicked on exitButton Button.");
        Platform.exit();
    }

    private void loadScene(Stage stage, String resource) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        Logger.debug(String.format("Loading %s scene",resource));
        stage.show();
    }
}
