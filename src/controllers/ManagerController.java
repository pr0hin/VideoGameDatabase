package controllers;

import javafx.fxml.Initializable;
import model.ManagerModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */
public class ManagerController extends AbstractTabController implements Initializable {

    public ManagerController() {
        super(new ManagerModel());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
