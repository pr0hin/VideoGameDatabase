package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.EmployeeModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */
public class EmployeeController extends AbstractTabController implements Initializable {

    @FXML
    AnchorPane employeeTableViewContainer;

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
            addTable(tbl, employeeTableViewContainer);
        } catch (SQLException sqle){
            createDialog(sqle.getMessage());
        }
    }

}
