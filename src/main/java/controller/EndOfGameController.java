package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EndOfGameController {
    @FXML
    public ImageView cupImageView;
//    @FXML
//    private Label informLabel;
    @FXML
    private void initialize(){
        Image image = new Image(getClass().getResourceAsStream("/cup.png"));
        cupImageView.setImage(image);
        cupImageView.setFitWidth(300);
        cupImageView.setFitHeight(300);
        cupImageView.setPreserveRatio(true);
    }

}
