package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.EmployeeModel;

import java.net.URL;
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
}
