package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetPlayersController {
    @FXML
    private TextField playerOne;
    @FXML
    private TextField playerTwo;
    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        startButton.setDisable(true);
    }

    @FXML
    private void clickHandler(ActionEvent e) throws Exception {
        var stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        loadNextScene(stage);
    }

    @FXML
    private void handleKeyReleased() {
        String plOne = playerOne.getText();
        String plTwo = playerTwo.getText();
        boolean disableButton = plOne.trim().isEmpty() || plTwo.trim().isEmpty();
        startButton.setDisable(disableButton);
    }

    private void loadNextScene(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gameBoard.fxml"));
        Parent root = fxmlLoader.load();
        GameBoardController controller = fxmlLoader.<GameBoardController>getController();
        controller.setPlayer1(playerOne.getText());
        controller.setPlayer2(playerTwo.getText());
        controller.setNextPlayer(playerOne.getText());
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setContentText("Are you sure you want to quit?");
            if(alert.showAndWait().get() == ButtonType.OK){
                Platform.exit();
            }
        });
    }

}
