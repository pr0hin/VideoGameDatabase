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

    public ResultSet executeGetNumberOfGamesOnPlatform (String platform) throws SQLException{

        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("select platform, count(title) from gameupc where platform = \'"+platform+"\' group by platform");
        return rs;
    }
}
