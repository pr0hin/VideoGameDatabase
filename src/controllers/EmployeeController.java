package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.CustomerModel;
import model.EmployeeModel;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */
public class EmployeeController extends AbstractTabController implements Initializable {

    public EmployeeController() {
        super(new EmployeeModel());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void insertAdditionTuple(ActionEvent e) {

        EmployeeModel model = (EmployeeModel) getModel();

        try {
            int res = model.executeAddOneTuple();
        } catch (SQLException sqle) {
            createDialog(sqle.getMessage());
        }
    }

    public void insertRestofTheTuples(ActionEvent e) {
        EmployeeModel model = (EmployeeModel) getModel();

        try {
            int res = model.executeRestofTuples();
        } catch (SQLException sqle) {
            createDialog(sqle.getMessage());
        }



    }

    public void getGamesOnAllPlatforms(ActionEvent event){

        EmployeeModel model = (EmployeeModel) getModel();
        try {
            ResultSet rs = model.executeGetGamesOnAllPlatforms();
            if(rs == null){
                createDialog("No games are on all platforms");
            }
            ResultSetParser rsparser = new ResultSetParser();
            TableView tbl = rsparser.colparse(rs);
            addTable(tbl);
        } catch (SQLException sqle){
            createDialog(sqle.getMessage());
        }
    }



    public void getCountOfGamesOnGivenPlat(ActionEvent event){

        Stage stage = new Stage();
        stage.setTitle("Game Search");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Platform selection");
        scenetitle.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList(
                "Min Year", "Max Year", "Title Count")
        );

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "PC", "PS", "PS2", "PS3", "PS5", "WII", "DC", "XBOX")
        );

        grid.add(cb, 1, 4);
        grid.add(cb2, 1, 3);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Executing query");
                EmployeeModel model = (EmployeeModel) getModel();
                try {
                    ResultSet rs = model.executeGetNumberOfGamesOnPlatform(cb.getValue().toString(), cb2.getValue().toString());
                    if (rs == null) {
                        createDialog("No games on specified platform");
                    }
                    ResultSetParser rsparser = new ResultSetParser();
                    TableView tbl = rsparser.colparse(rs);

                    addTable(tbl);

                } catch (SQLException sqle) {
                    createDialog(sqle.getMessage());
                }


            }
        });


        Scene scene = new Scene(grid, 500, 300);
        stage.setScene(scene);
        stage.show();

    }
}
