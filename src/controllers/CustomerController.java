package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */

public class CustomerController implements Initializable {

    @FXML
    private TableView<Game> gameTableView;

    @FXML
    private TableColumn<Game, String> titleTableColumn;

    @FXML
    private TableColumn<Game, String> yearTableColumn;

    public void displayTable(ActionEvent event) {
        gameTableView.getItems().setAll(getItemsToAdd());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleTableColumn.setText("title");
        yearTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearTableColumn.setText("year");
    }

    public List<Game> getItemsToAdd() {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Game game = new Game();
            game.setTitle("The Last of Us");
            game.setYear(2014);
            games.add(game);
        }

        return games;
    }
}
