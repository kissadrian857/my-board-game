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
import org.tinylog.Logger;

// CHECKSTYLE:OFF
public class SetPlayersController {
    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        startButton.setDisable(true);
    }

    @FXML
    private void clickHandler(ActionEvent e) throws Exception {
        var stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Logger.info("Clicked on startButton Button");
        loadNextScene(stage);
    }

    @FXML
    private void handleKeyReleased() {
        String plOne = player1.getText();
        String plTwo = player2.getText();
        boolean disableButton = plOne.trim().isEmpty() || plTwo.trim().isEmpty();
        startButton.setDisable(disableButton);
    }

    private void loadNextScene(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gameBoard.fxml"));
        Parent root = fxmlLoader.load();
        GameBoardController controller = fxmlLoader.<GameBoardController>getController();
        controller.setPlayer1(player1.getText());
        controller.setPlayer2(player2.getText());
        controller.setNextPlayer(player1.getText());
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        Logger.debug("Loading /gameBoard.fxml scene");
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
// CHECKSTYLE:ON