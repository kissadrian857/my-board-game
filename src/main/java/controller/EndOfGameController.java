package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.tinylog.Logger;

// CHECKSTYLE:OFF
public class EndOfGameController {
    @FXML
    public ImageView cupImageView;
    @FXML
    private Label resultLabel;
    @FXML
    private Button newGameButton;
    @FXML
    private Button returnToMain;

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/cup.png"));
        cupImageView.setImage(image);
        cupImageView.setFitWidth(300);
        cupImageView.setFitHeight(300);
        cupImageView.setPreserveRatio(true);
    }

    public void setResultLabel(String playerName) {
        resultLabel.setText("Congratulations," + playerName + " !");
    }

    @FXML
    private void newGameClicked(ActionEvent event) throws Exception {
        Logger.info("Clicked on newGameButton Button");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loadScene(stage,"/setPlayers.fxml",800,600);
    }

    @FXML
    private void returnToMainClicked(ActionEvent event) throws Exception {
        Logger.info("Clicked on returnToMain Button");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loadScene(stage,"/mainMenu.fxml",400,400);
    }

    private void loadScene(Stage stage, String resource,int width,int height) throws Exception {
        Logger.debug(String.format("Loading %s scene",resource));
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        Scene scene = new Scene(root,width,height);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
// CHECKSTYLE:ON
