package model;


import java.sql.*;

/**
 * Created by rohinpatel on 2016-03-13.
 */
public class CustomerModel extends AbstractModel {

    public ResultSet getGames() {
        try {
            Statement stmt = this.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("select u.title, u.launchyear, platform, genre, devname, pubname from gameupc u, gamedevs d " +
                    "where u.title = d.title and u.launchyear = d.launchyear");
            return rs;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return null;
    }

    public ResultSet makeTitleSelection(String title) {
        try {
            String qry = "SELECT * FROM gameupc WHERE gameupc.title LIKE \'%" + title + "%\'";
            Statement stmt = this.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            return rs;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }
}
