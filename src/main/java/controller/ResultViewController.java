package controller;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.State;
import org.tinylog.Logger;
import result.LocalDateTimeAdapter;
import result.Result;
import result.ResultContainer;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


// CHECKSTYLE:OFF
public class ResultViewController {
    @FXML
    TableView<Result> tableView;
    @FXML
    TableColumn<Result,String> player1;
    @FXML
    TableColumn<Result,String> player2;
    @FXML
    TableColumn<Result,String> winner;
    @FXML
    TableColumn<Result, LocalDateTime> date;

    @FXML
    private void initialize(){
        player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        winner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        date.setCellFactory(column -> {
            TableCell<Result, LocalDateTime> cell = new TableCell<>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    }
                }
            };
            return cell;
        });
        tableView.setItems(getObservable());
    }

    private ObservableList<Result> getObservable(){
        ObservableList<Result> res = FXCollections.observableArrayList();
        res.addAll(ResultContainer.getResults());
        return res;
    }

    @FXML
    private void handleBack(ActionEvent event){
        Logger.info("Clicked on back Button");
        Logger.debug("Loading /mainMenu.fxml scene");
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
            Scene scene = new Scene(root,400,400);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
// CHECKSTYLE:ON