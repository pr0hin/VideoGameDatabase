package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rohinpatel on 2016-03-13.
 */
public class CustomerModel extends AbstractModel {

    public ResultSet getGames() throws SQLException {
        try {
            Statement stmt = this.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("select u.title, u.launchyear, platform, genre, devname, pubname from gameupc u, gamedevs d " +
                    "where u.title = d.title and u.launchyear = d.launchyear");
            return rs;
        } catch (SQLException sqle) {
            throw sqle;
        }
    }
}
