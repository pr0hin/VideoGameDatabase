package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CustomerModel;
import model.NotLoggedInException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */

public class CustomerController extends AbstractTabController implements Initializable {

    @FXML
    private AnchorPane gameTableViewContainer;

    public CustomerController() {
        super(new CustomerModel());
    }

    public void logout(ActionEvent event) {
        CustomerModel model = (CustomerModel) getModel();
        String user = model.executeLogout();
        ButtonType okbutton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(okbutton);
        dialog.setContentText("Logged out " + user);
        dialog.show();
    }

    public void getFavouriteGames(ActionEvent event) {

        CustomerModel model = (CustomerModel) getModel();
        try {
            ResultSet rs = model.executeGetFavouriteGames();
            if (rs == null) {
                createDialog("No favourite games");

            }
            ResultSetParser rsparser = new ResultSetParser();
            TableView tbl = rsparser.colparse(rs);

            // Insert table into the AnchorPane
            addTable(tbl, this.gameTableViewContainer);
        } catch (NotLoggedInException e) {
            createDialog(e.getMessage());
        } catch (SQLException sqle) {
            createDialog(sqle.getMessage());
        }


    }

    public void loginForm(ActionEvent event) {

        Stage stage = new Stage();
        stage.setTitle("Game Search");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label emailLabel = new Label("Email: ");
        grid.add(emailLabel, 0, 1);

        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 1);

        Button btn = new Button("Login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Logging in...");
                CustomerModel model = (CustomerModel) getModel();
                try {
                    String response = model.executeLogin(emailTextField.getText());
                    actiontarget.setText(response);
                } catch (SQLException sqle) {
                    createDialog(sqle.getMessage());

                }
            }
        });

        Scene scene = new Scene(grid, 300, 300);
        stage.setScene(scene);
        stage.show();

    }
    public void advancedSearchForm(ActionEvent event){

        Stage stage = new Stage();
        stage.setTitle("Advanced Game Search");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Games selection");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label columnLabel = new Label("Column headers:");
        grid.add(columnLabel, 0, 1);

        TextField columnTextField = new TextField();
        grid.add(columnTextField, 1, 1);

        Label conditionLabel = new Label("Conditions:");
        grid.add(conditionLabel, 0, 2);

        TextField conditionTextField = new TextField();
        grid.add(conditionTextField, 1, 2);

        Button genbtn = new Button("Generate Query");
        Button sbtn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(sbtn);
        hbBtn.getChildren().add(genbtn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        CustomerModel model = (CustomerModel) getModel();

        genbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String qry = model.generateAdvancedSearchString(columnTextField.getText(), conditionTextField.getText());
                actiontarget.setText(qry);
            }
        });

        sbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ResultSet rs = model.executeAdvancedGameSearch(actiontarget.getText());
                } catch (SQLException sqle) {
                    createDialog(sqle.getMessage());
                }

            }
        });

        Scene scene = new Scene(grid, 300, 300);
        stage.setScene(scene);
        stage.show();



    }

    public void gameSearchForm(ActionEvent event) {

            Stage stage = new Stage();
            stage.setTitle("Game Search");
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

            TextField titleTextField = new TextField();
            grid.add(titleTextField, 1, 1);

            Label cityLabel = new Label("City to search in:");
            grid.add(cityLabel, 0, 2);

            TextField cityTextField = new TextField();
            grid.add(cityTextField, 1, 2);

            Button btn = new Button("Search");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            final Text actiontarget = new Text();
            grid.add(actiontarget, 1, 6);

            //A checkbox without a caption
            CheckBox details = new CheckBox("SHOW Game Details");
            details.setText("Show game details");
            grid.add(details, 2, 3);

            CheckBox checkStock = new CheckBox();
            checkStock.setText("In Stock?");
            grid.add(checkStock, 1, 3);

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontarget.setText("Executing query");
                    CustomerModel model = (CustomerModel) getModel();
                    model.setInStock(checkStock.isSelected());
                    model.setDetails(details.isSelected());
                    try {
                        ResultSet rs = model.executeGameSearchQuery(titleTextField.getText(), cityTextField.getText());
                        ResultSetParser rsparser = new ResultSetParser();
                        TableView tbl = rsparser.colparse(rs);

                        addTable(tbl, gameTableViewContainer);
                    } catch (SQLException sqle) {
                        createDialog(sqle.getMessage());
                    }

                }
            });

            Scene scene = new Scene(grid, 500, 300);
            stage.setScene(scene);
            stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
