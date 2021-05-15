import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import result.ResultContainer;

public class BoardGameApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        stage.setTitle("JavaFX Board Game");
        ResultContainer.readResults();
        Scene scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
