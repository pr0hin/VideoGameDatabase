package model;

import java.sql.Connection;

/**
 * Created by kevinchiang on 2016-03-23.
 */
public abstract class AbstractModel {
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return this.conn;
    }
}
