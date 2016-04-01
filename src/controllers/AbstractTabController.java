package controllers;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.AbstractModel;

import java.sql.Connection;

/**
 * Created by kevinchiang on 2016-03-23.
 */
public abstract class AbstractTabController {
    private AbstractModel model;

    public AbstractTabController(AbstractModel model) {
        this.model = model;
    }

    public void setConn(Connection conn) {
        this.model.setConn(conn);
    }

    public AbstractModel getModel() {
        return this.model;
    }

    public void addTable(TableView tbl, AnchorPane container) {
        // Clear previous table(s) and add the new table to the AnchorPane
        container.getChildren().clear();
        container.getChildren().add(tbl);

        // Some setting to make the child table fill the parent AnchorPane
        container.setBottomAnchor(tbl, 0.0);
        container.setTopAnchor(tbl, 0.0);
        container.setLeftAnchor(tbl, 0.0);
        container.setRightAnchor(tbl, 0.0);
    }

    public void createDialog(String s) {
        ButtonType okbutton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(okbutton);
        dialog.setContentText(s);
        dialog.show();
    }
}
