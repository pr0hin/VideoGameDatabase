import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.*;

/**
 * Created by rohinpatel on 2016-02-24.
 */
public class Main {
    public static void main(String[] args) {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug", "username", "password");
            } catch (SQLException sqle) {
                System.out.println(sqle);
                System.exit(-2);
            }


    }

}
