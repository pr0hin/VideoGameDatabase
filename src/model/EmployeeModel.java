package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rohinpatel on 2016-03-13.
 */
public class EmployeeModel extends AbstractModel {
    public ResultSet executeGetGamesOnAllPlatforms() throws SQLException{
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("select distinct title from gameupc G where not exists (select * from gameupc P where not exists (select * from gameupc C where C.platform = P.platform and C.upc = G.upc))");
        return rs;
    }

    public ResultSet executeGetNumberOfGamesOnPlatform (String platform, String queryType) throws SQLException{

        Statement stmt = this.getConn().createStatement();
        ResultSet rs;
        switch(queryType){
            case "Min Year":
                 rs = stmt.executeQuery("select platform, min(launchyear) from gameupc where platform = \'"+platform+"\' group by platform");
                break;

            case "Max Year":
                 rs = stmt.executeQuery("select platform, max(launchyear) from gameupc where platform = \'"+platform+"\' group by platform");
                break;

            case "Title Count":
                 rs = stmt.executeQuery("select platform, count(title) from gameupc where platform = \'"+platform+"\' group by platform");
                break;

            default:
                 rs = null;
                break;
        }

        return rs;
    }

    public int executeAddOneTuple() throws SQLException {
        Statement stmt = getConn().createStatement();
        int res = stmt.executeUpdate("INSERT INTO gamedevs VALUES ('The Addition', 2016, 'Ubisoft', 'Ubisoft')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'PS5', 'Action')");
        return res;
    }

    public int executeRestofTuples() throws SQLException {

        Statement stmt = getConn().createStatement();
        int res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'PS', 'Action')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'PC', 'Action')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'PS3', 'Action')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'DC', 'Action')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'WII', 'Action')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'XBOX', 'Action')");
        res = stmt.executeUpdate("INSERT INTO gameupc VALUES ('000000000017', 'The Addition', 2016, 'PS2', 'Action')");
        return res;


    }
}

