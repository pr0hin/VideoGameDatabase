package controllers;

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
}
