package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */

public class CustomerController extends AbstractTabController implements Initializable {

    @FXML
    private TableView<Game> gameTableView;

    @FXML
    private TableColumn<Game, String> titleTableColumn;

    @FXML
    private TableColumn<Game, String> yearTableColumn;

    @FXML
    private TableColumn<Game, String> platformTableColumn;

    @FXML
    private TableColumn<Game, String> genreTableColumn;

    @FXML
    private TableColumn<Game, String> devnameTableColumn;

    @FXML
    private TableColumn<Game, String> pubnameTableColumn;

    public CustomerController() {
        super(new CustomerModel());
    }

    public void displayTable(ActionEvent event) {
        gameTableView.getItems().setAll(getItemsToAdd());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleTableColumn.setText("title");
        yearTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearTableColumn.setText("year");
        platformTableColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        platformTableColumn.setText("platform");
        genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreTableColumn.setText("genre");
        devnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("devname"));
        devnameTableColumn.setText("devname");
        pubnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("pubname"));
        pubnameTableColumn.setText("pubname");
    }

    public List<Game> getItemsToAdd() {
        ArrayList<Game> games = new ArrayList<>();
        Game game;
        try {
            CustomerModel customerModel = (CustomerModel) this.getModel();
            ResultSet rs = customerModel.getGames();

            while (rs.next()) {
                String title = rs.getString(1);
                String launchyear = rs.getString(2);
                String platform = rs.getString(3);
                String genre = rs.getString(4);
                String devname = rs.getString(5);
                String pubname = rs.getString(6);

                game = new Game();
                game.setTitle(title);
                game.setYear(launchyear);
                game.setPlatform(platform);
                game.setGenre(genre);
                game.setDevname(devname);
                game.setPubname(pubname);
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}
