package controllers;

import javafx.fxml.Initializable;
import model.EmployeeModel;

import java.net.URL;
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
}
