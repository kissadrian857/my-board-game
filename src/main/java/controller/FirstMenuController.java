package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BoardGameModel;

public class FirstMenuController {
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

        BoardGameModel.setPlayerNames(playerOne.getText(),playerTwo.getText());
        System.out.println("Game started"+ " " + BoardGameModel.getPlayers().get(0)+ " "+BoardGameModel.getPlayers().get(1));
        var stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        loadNextScene(stage);
    }

    @FXML
    private void handleKeyReleased() {
        String plOne = playerOne.getText();
        String plTwo = playerTwo.getText();
        boolean disableButton = plOne.trim().isEmpty() || plTwo.trim().isEmpty();
        startButton.setDisable(disableButton);
    }

    private void loadNextScene(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/gameBoard.fxml"));
        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
