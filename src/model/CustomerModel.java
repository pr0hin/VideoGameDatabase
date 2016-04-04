package model;


import java.sql.*;

/**
 * Created by rohinpatel on 2016-03-13.
 */
public class CustomerModel extends AbstractModel {

    private String userlogin = "";
    private boolean isInStock;
    private boolean details;

    public ResultSet executeGameSearchQuery(String title, String city) throws SQLException{
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
            if (!city.equals("")) {
                qry.append("UPPER(city) LIKE \'%" + city.toUpperCase() + "%\' AND ");
            }
            if (isInStock) {
                qry.append("stock > 0 AND ");
            }

            if (!title.equals("")) {
                qry.append("UPPER(title) LIKE \'%" + title.toUpperCase() + "%\'");

            }
            int len = qry.length();
            String andStr = qry.substring(len-4);
            System.out.println(andStr);
            if (andStr.equals("AND ")) {
                qry.delete(len-4, len);
            }
        }
            String finalquery = qry.toString();
            System.out.println(finalquery);
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(finalquery);
            return rs;
    }
    public String executeLogin(String email) throws SQLException {


            Statement stmt = this.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE customer.email=\'"+email+"\'");

            /* Doesn't work for some reason!
            PreparedStatement pstmt = getConn().prepareStatement("SELECT * FROM customer WHERE customer.email=?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            */

            if (!rs.next()) {
                return "Login Failed!";
            } else {
                userlogin = email;
                return "Login success!";
            }

    }

    public String generateAdvancedSearchString(String cols, String conds) {
        StringBuilder qry = new StringBuilder("SELECT ");
               if (cols.equals(""))  {
                  qry.append("*");

               } else {
                  qry.append(cols);
               }
                qry.append(" FROM gameupc NATURAL JOIN gamedevs");
                if (!conds.equals("")) {
                    qry.append(" WHERE ");
                    qry.append(conds);
                }

        return qry.toString();
    }

    public ResultSet executeAdvancedGameSearch(String qry) throws SQLException {


        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(qry);
        return rs;

    }

    public ResultSet executeGetFavouriteGames() throws NotLoggedInException, SQLException{

            Statement stmt = this.getConn().createStatement();
            if (userlogin.equals("")) {
                throw new NotLoggedInException("User not logged in");
            }
            ResultSet rs = stmt.executeQuery("SELECT * from favoritegame NATURAL JOIN gameupc WHERE favoritegame.email=\'" + userlogin + "\'");
            return rs;

    }

    public String executeLogout() {
        String user = new String(userlogin);
        userlogin = "";
        return user;


    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }


    public void setDetails(boolean details) {
        this.details = details;
    }
}
