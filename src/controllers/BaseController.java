package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kevinchiang on 2016-03-22.
 */
public class BaseController {

    @FXML
    private Parent customer;

    @FXML
    private CustomerController customerController;

    @FXML
    private EmployeeController employeeController;

    @FXML
    private ManagerController managerController;

    public List<AbstractTabController> getTabControllers() {
        return Arrays.asList(
                customerController,
                employeeController,
                managerController
        );
    }

    public CustomerController getCustomerController() {
        return this.customerController;
    }
}
