package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.Operator;
import model.Position;

import java.util.ArrayList;
import java.util.List;


public class GameBoardController {
    private BoardGameModel boardGameModel = new BoardGameModel();
    @FXML
    private GridPane gridPane;
    @FXML
    private Label informationLabel1;
    @FXML
    private Label informationLabel2;
    private StringProperty player1 = new SimpleStringProperty();
    private StringProperty player2 = new SimpleStringProperty();
    private StringProperty nextPlayer = new SimpleStringProperty();

    private final Background WHITE_BACKGROUND = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(1, 1, 1, 1)));
    private final Background YELLOW_BACKGROUND = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, new Insets(1, 1, 1, 1)));
    private List<Position> selectedPositions = new ArrayList<>();

    @FXML
    private void initialize() {
        createBoard();
        createPieces();
        informationLabel1.textProperty().bind(nextPlayer);
    }

    public void setNextPlayer(String string) {
        nextPlayer.set(string);
    }

    private void alterNextPlayer() {
        nextPlayer.set(
                switch (boardGameModel.getNextPlayer()) {
                    case PLAYER1 -> player1.get();
                    case PLAYER2 -> player2.get();
                }
        );
        System.out.println(boardGameModel.getNextPlayer());
    }

    public void setPlayer1(String name) {
        this.player1.set(name);
    }

    public void setPlayer2(String name) {
        this.player2.set(name);
    }

    public void setInformationLabel1(String string) {
        informationLabel1.setText(string);
    }

    private void createBoard() {
        for (int i = 0; i < BoardGameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardGameModel.BOARD_SIZE; j++) {
                var square = createSquare();
                gridPane.add(square, j, i);
            }
        }
    }

    private Circle createPiece(Color color) {
        var piece = new Circle(55);
        piece.setFill(color);
        return piece;
    }

    private void createPieces() {
        ObservableList<Node> children = gridPane.getChildren();
        for (var square : children) {
            try {
                int row = GridPane.getRowIndex(square);
                int col = GridPane.getColumnIndex(square);
                Color color = Color.valueOf(boardGameModel.colourOfSquare(new Position(row, col)).toString());
                ((StackPane) square).getChildren().add(createPiece(color));
            } catch (Exception e) {
                continue;
            }
        }
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setPrefSize(120, 120);
        square.setOnMouseClicked(this::handleMouseClicked);
        return square;
    }

    @FXML
    private void handleMouseClicked(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        if (event.getButton() == MouseButton.PRIMARY) {
            handleMousePrimary(new Position(row, col));
        } else if (event.getButton() == MouseButton.SECONDARY) {
            handleMouseSecondary();
        }
    }

    private void handleMousePrimary(Position position) {
        informationLabel2.setText("");
        StackPane square = getSquare(position);
        if (!selectedPositions.contains(position)) {
            selectedPositions.add(new Position(position.getRow(), position.getCol()));
        }
        Background background = YELLOW_BACKGROUND;
        square.setBackground(background);
    }

    private void handleMouseSecondary() {
        Operator operator = Operator.of(selectedPositions);
        if (operator != null && boardGameModel.isValidStep(operator)) {
            boardGameModel.makeStep(operator);
            alterPieces();
        } else {
            informationLabel2.setText("Invalid operation");
        }
        alterSelectedBackgrounds();
        selectedPositions.clear();
        if (boardGameModel.isOver()) {
            handleGameOver();
        } else {

        }
        alterNextPlayer();
    }

    private void alterPieces() {
        for (Position position : selectedPositions) {
            alterPiece(position);
        }
    }

    private void alterSelectedBackgrounds() {
        for (Position position : selectedPositions) {
            alterBackground(position);
        }
    }

    private void alterPiece(Position position) {
        Color color = Color.valueOf(boardGameModel.colourOfSquare(position).toString());
        StackPane square = getSquare(position);
        ((Circle) square.getChildren().get(0)).setFill(color);
    }

    private void alterBackground(Position position) {
        StackPane square = getSquare(position);
        Background background = square.getBackground();
        if (background.equals(WHITE_BACKGROUND)) {
            square.setBackground(YELLOW_BACKGROUND);
        } else {
            square.setBackground(WHITE_BACKGROUND);
        }
    }

    private StackPane getSquare(Position position) {
        for (var square : gridPane.getChildren()) {
            try {
                int row = GridPane.getRowIndex(square);
                int col = GridPane.getColumnIndex(square);
                if (row == position.getRow() && col == position.getCol()) {
                    return (StackPane) square;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private void handleGameOver() {
        //TODO
    }

    private void loadNextScene(Stage stage) {
        //TODO
    }
}
