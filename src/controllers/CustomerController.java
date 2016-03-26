package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CustomerModel;

import java.net.URL;
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

    public void selectionForm(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.setTitle("Selection form");
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Text scenetitle = new Text("Games selection");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(scenetitle, 0, 0, 2, 1);

            Label titleLabel = new Label("Video game title:");
            grid.add(titleLabel, 0, 1);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);

            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            final Text actiontarget = new Text();
            grid.add(actiontarget, 1, 6);

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontarget.setText("Executing query");
                    CustomerModel model = (CustomerModel) getModel();
                    ResultSet rs = model.makeTitleSelection(userTextField.getText());
                    ResultSetParser rsparser = new ResultSetParser();
                    TableView tbl = rsparser.colparse(rs);
                    Scene tscene = new Scene(tbl);
                    stage.setTitle("RESULTS");
                    stage.setScene(tscene);
                    stage.show();

                   }
            });

            Scene scene = new Scene(grid, 300, 275);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
