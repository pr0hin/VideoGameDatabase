package model;


import java.sql.*;

/**
 * Created by rohinpatel on 2016-03-13.
 */
public class CustomerModel extends AbstractModel {


    private boolean isInStock;
    private boolean details;

    public ResultSet getGames() {
        try {
            Statement stmt = this.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT u.title, u.launchyear, platform, genre, devname, pubname FROM gameupc u, gamedevs d " +
                    "WHERE u.title = d.title AND u.launchyear = d.launchyear");
            return rs;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return null;
    }

    public ResultSet generateAndExecuteQuery(String title, String city) {
        StringBuilder qry = new StringBuilder();
        String tables = "gameupc NATURAL JOIN gamedevs NATURAL JOIN isininventory NATURAL JOIN store";
        if (details) {
            qry.append("SELECT * FROM ");

        } else {
            qry.append("SELECT title, platform, storeNum, city, streetAddress, stock FROM ");
        }
        qry.append(tables);
        if ((!city.equals("")) || (isInStock) || (!title.equals(""))) {
            qry.append(" WHERE ");
            StringBuilder cityqry = new StringBuilder("");
            if (!city.equals("")) {
                qry.append("city LIKE \'%" + city + "%\' AND ");
            }
            if (isInStock) {
                qry.append("stock > 0 AND ");
            }
            if (!title.equals("")) {
                qry.append("title LIKE \'%" + title + "%\'");

            }
            int len = qry.length();
            String andStr = qry.substring(len-4);
            System.out.println(andStr);
            if (andStr.equals("AND ")) {
                qry.delete(len-4, len);
            }
        }
        try {
            String finalquery = qry.toString();
            System.out.println(finalquery);
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(finalquery);
            return rs;
        }  catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return null;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }


    public void setDetails(boolean details) {
        this.details = details;
    }
}
