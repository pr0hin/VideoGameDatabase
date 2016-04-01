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
import model.EmployeeModel;
import model.NotLoggedInException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


    public void getGamesOnAllPlatforms(ActionEvent event){

        EmployeeModel model = (EmployeeModel) getModel();
        try {
            ResultSet rs = model.executeGetGamesOnAllPlatforms();
            if(rs == null){
                createDialog("No games are on all platforms");
            }
            ResultSetParser rsparser = new ResultSetParser();
            TableView tbl = rsparser.colparse(rs);
            Stage stage = new Stage();
            Scene tscene = new Scene(tbl);
            stage.setTitle("RESULTS");
            stage.setScene(tscene);
            stage.show();
        } catch (SQLException sqle){
            createDialog(sqle.getMessage());
        }
    }

    public void createDialog(String s) {
        ButtonType okbutton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(okbutton);
        dialog.setContentText(s);
        dialog.show();

    }
























































}


