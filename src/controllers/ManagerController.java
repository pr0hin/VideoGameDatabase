package controllers;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ManagerModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by kevinchiang on 2016-03-19.
 */
public class ManagerController extends AbstractTabController implements Initializable {

    public static final Integer MIN_WAGE = 10;

    public ManagerController() {
        super(new ManagerModel());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void gameSearch() {
        ManagerModel model = (ManagerModel) getModel();
        try {
            ResultSet rs = model.getGames();
            ResultSetParser rsparser = new ResultSetParser();
            TableView tbl = rsparser.colparse(rs);
            addTable(tbl);
        } catch (SQLException sqle) {
            createDialog(sqle.getMessage());
        }
    }

    public void getMaxStoreStock(ActionEvent event){
        ManagerModel model = (ManagerModel) getModel();
        try {
            ResultSet rs = model.executeGetMaxStoreStock();
            if(rs == null){
                createDialog("Unable to find store with maximum stock");
            }
            ResultSetParser rsparser = new ResultSetParser();
            TableView tbl = rsparser.colparse(rs);
            addTable(tbl);
        } catch (SQLException sqle){
            createDialog(sqle.getMessage());
        }
    }

    public void getMinStoreStock(ActionEvent event){
        ManagerModel model = (ManagerModel) getModel();
        try {
            ResultSet rs = model.executeGetMinStoreStock();
            if(rs == null){
                createDialog("Unable to find store with minimum stock");
            }
            ResultSetParser rsparser = new ResultSetParser();
            TableView tbl = rsparser.colparse(rs);
            addTable(tbl);
        } catch (SQLException sqle){
            createDialog(sqle.getMessage());
        }
    }

    public void employeeSearchForm(ActionEvent event) {

        Stage stage = new Stage();
        stage.setTitle("Employee Search");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Employee selection");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label nameLabel = new Label("Employee Name:");
        grid.add(nameLabel, 0, 1);

        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 1);

        Label storeNumLabel = new Label("Storenum to search in:");
        grid.add(storeNumLabel, 0, 2);

        TextField storeNumTextField = new TextField();
        grid.add(storeNumTextField, 1, 2);

        Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        //A checkbox without a caption
        CheckBox details = new CheckBox("SHOW managers");
        details.setText("Show managers");
        grid.add(details, 2, 3);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Executing query");
                ManagerModel model = (ManagerModel) getModel();
                model.setIsManager(details.isSelected());
                try {
                    ResultSet rs = model.executeEmployeeSearchQuery(nameTextField.getText(), storeNumTextField.getText());
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

    public void employeeUpdateForm(ActionEvent event) {
        List<Node> containerChildren = super.getContainer().getChildren();

        if (containerChildren.size() == 0) {
            createDialog("Please query and select an employee first");
            return;
        }

        TableView employeeTable = (TableView) containerChildren.get(0);

        // Get employeeTable column titles
        List cols = employeeTable.getColumns();

        // Get the selected table row
        List item = (List) employeeTable.getSelectionModel().getSelectedItem();
        if (item == null || item.size() == 0) {
            createDialog("Please select a row to update");
            return;
        }

        String eid = "";
        String name = "";
        String wage = "";
        String storenum = "";

        for (int i = 0; i < cols.size(); i++) {
            TableColumn col = (TableColumn) cols.get(i);
            if (col.getText().equals("EID")) {
                eid = (String) item.get(i);
            } else if (col.getText().equals("NAME")) {
                name = ((String) item.get(i)).trim();
            } else if (col.getText().equals("WAGE")) {
                wage = ((String) item.get(i));
            } else if (col.getText().equals("STORENUM")) {
                storenum = ((String) item.get(i));
            }
        }

        if (eid.equals("") || name.equals("")) {
            createDialog("No columns found");
            return;
        }

        Stage stage = new Stage();
        stage.setTitle("Employee Update");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Updating info for " + name + " (EID = " + eid + ")" );
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label wageLabel = new Label("New Wage:");
        grid.add(wageLabel, 0, 1);

        TextField wageTextField = new TextField(wage);
        grid.add(wageTextField, 1, 1);

        Label storeNumLabel = new Label("New Store Number:");
        grid.add(storeNumLabel, 0, 2);

        TextField storeNumTextField = new TextField(storenum);
        grid.add(storeNumTextField, 1, 2);

        Button btn = new Button("Update");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        final String finalEid = eid;

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Executing query");
                ManagerModel model = (ManagerModel) getModel();
                try {
                    int success = model.updateEmployee(finalEid, wageTextField.getText(), storeNumTextField.getText());
                    if (success == 1) {
                        createDialog("Update succeeded!");
                    } else {
                        createDialog("Update Failed");
                    }
                } catch (SQLException sqle) {
                    if (sqle.getErrorCode() == 2290) {
                        createDialog("Pay needs to be at least minimum wage you slave worker");
                    } else {
                        createDialog(sqle.getMessage());
                    }
                }

            }
        });

        Scene scene = new Scene(grid, 500, 300);
        stage.setScene(scene);
        stage.show();

    }

    public void employeeDelete(ActionEvent event) {
        List<Node> containerChildren = super.getContainer().getChildren();

        if (containerChildren.size() == 0) {
            createDialog("Please query and select an employee first");
            return;
        }

        TableView employeeTable = (TableView) containerChildren.get(0);

        // Get employeeTable column titles
        List cols = employeeTable.getColumns();

        // Get the selected table row
        List item = (List) employeeTable.getSelectionModel().getSelectedItem();
        if (item == null || item.size() == 0) {
            createDialog("Please select a row to delete");
            return;
        }

        String eid = "";
        String name = "";

        for (int i = 0; i < cols.size(); i++) {
            TableColumn col = (TableColumn) cols.get(i);
            if (col.getText().equals("EID")) {
                eid = (String) item.get(i);
            } else if (col.getText().equals("NAME")) {
                name = ((String) item.get(i)).trim();
            }
        }

        if (eid.equals("") || name.equals("")) {
            createDialog("This row does not look like an employee. Try again.");
            return;
        }

        Stage stage = new Stage();
        stage.setTitle("Delete Employee");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Remove " + name + " (EID = " + eid + ") from database?" );
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(yesBtn);
        hbBtn.getChildren().add(noBtn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        final String finalEid = eid;

        yesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Executing query");
                ManagerModel model = (ManagerModel) getModel();
                try {
                    int success = model.deleteEmployee(finalEid);
                    System.out.println(success);
                    if (success == 1) {
                        stage.close();
                        createDialog("EID: " + finalEid + " deleted successfully!");
                    } else {
                        createDialog("Deletion failed");
                    }
                } catch (SQLException sqle) {
                    createDialog(sqle.getMessage());
                }

            }
        });

        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        Scene scene = new Scene(grid, 400, 150);
        stage.setScene(scene);
        stage.show();

    }

    public void gameDelete(ActionEvent event) {
        List<Node> containerChildren = super.getContainer().getChildren();

        if (containerChildren.size() == 0) {
            createDialog("Please query and select a game first");
            return;
        }

        TableView gameTable = (TableView) containerChildren.get(0);

        // Get gameTable column titles
        List cols = gameTable.getColumns();

        // Get the selected table row
        List item = (List) gameTable.getSelectionModel().getSelectedItem();
        if (item == null || item.size() == 0) {
            createDialog("Please select a row to delete");
            return;
        }

        String upc = "";
        String title = "";
        String platform = "";

        for (int i = 0; i < cols.size(); i++) {
            TableColumn col = (TableColumn) cols.get(i);
            if (col.getText().equals("UPC")) {
                upc = (String) item.get(i);
            } else if (col.getText().equals("TITLE")) {
                title = ((String) item.get(i)).trim();
            } else if (col.getText().equals("PLATFORM")) {
                platform = ((String) item.get(i)).trim();
            }
        }

        if (upc.equals("") || title.equals("") || platform.equals("")) {
            createDialog("This row does not look like a game. Try again.");
            return;
        }

        Stage stage = new Stage();
        stage.setTitle("Delete Game");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Remove " + title + " (UPC = " + upc + ") on " + platform + " from database?" );
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(yesBtn);
        hbBtn.getChildren().add(noBtn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        final String finalUpc = upc;
        final String finalPlatform = platform;

        yesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Executing query");
                ManagerModel model = (ManagerModel) getModel();
                try {
                    int success = model.deleteGame(finalUpc, finalPlatform);
                    System.out.println(success);
                    if (success == 1) {
                        stage.close();
                        createDialog("UPC: " + finalUpc + " on " + finalPlatform + " deleted successfully!");
                    } else {
                        createDialog("Deletion failed");
                    }
                } catch (SQLException sqle) {
                    createDialog(sqle.getMessage());
                }

            }
        });

        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        Scene scene = new Scene(grid, 550, 150);
        stage.setScene(scene);
        stage.show();

    }
}
