package model;

import java.sql.*;

/**
 * Created by rohinpatel on 2016-03-13.
 */
public class ManagerModel extends AbstractModel {

    private boolean isManager;

    public ResultSet executeEmployeeSearchQuery(String name, String storeNum) throws SQLException {
        StringBuilder qry = new StringBuilder();

        // Listed all of the columns so that I could rename them
        qry.append("SELECT eid, ssin, name, phone, wage, hours, isManager, storenum, " +
                "city AS store_location, streetaddress AS store_address FROM employee NATURAL JOIN store");
        if ((!name.equals("")) || (isManager) || (!storeNum.equals(""))) {
            qry.append(" WHERE ");
            if (!name.equals("")) {
                qry.append("name LIKE \'%" + name + "%\' AND ");
            }
            if (isManager) {
                qry.append("isManager = 1 AND ");
            }

            if (!storeNum.equals("")) {
                qry.append("storenum = \'" + storeNum + "\'");
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

    public int updateEmployee(String eid, String wage, String storeNum) throws SQLException {
        StringBuilder qry = new StringBuilder();

        qry.append("UPDATE employee set ");
        if ((!wage.equals("")) || (!storeNum.equals(""))) {
            if (!wage.equals("")) {
                qry.append("wage = \'" + wage + "\', ");
            }

            if (!storeNum.equals("")) {
                qry.append("storenum = \'" + storeNum + "\' ");
            }

            int len = qry.length();
            String tailStr = qry.substring(len-2);
            System.out.println(tailStr);
            if (tailStr.equals(", ")) {
                qry.delete(len-2, len);
            }
            qry.append("WHERE eid = " + eid);
        }
        String finalquery = qry.toString();
        System.out.println(finalquery);
        Statement stmt = getConn().createStatement();

        int success = stmt.executeUpdate(finalquery);

        return success;
    }

    public ResultSet executeGetMaxStoreStock() throws SQLException{
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("select S.streetAddress, S.city, T.storeNum, maxStock from(select  max(stockSum) as maxStock from (select storeNum, sum(stock) as stockSum from isInInventory group by storeNum)) Q, (select storeNum, sum(stock) as stockSum from isInInventory group by storeNum) T, store S where T.stockSum = maxStock and T.storeNum = S.storeNum");
        return rs;
    }

    public ResultSet executeGetMinStoreStock() throws SQLException{
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("select S.streetAddress, S.city, T.storeNum, minStock from (select  min(stockSum) as minStock from (select storeNum, sum(stock) as stockSum from isInInventory group by storeNum)) Q, (select storeNum, sum(stock) as stockSum from isInInventory group by storeNum) T, store S where T.stockSum = minStock and T.storeNum = S.storeNum");
        return rs;
    }

    public int deleteEmployee(String eid) throws SQLException {
        String qry = "DELETE from employee WHERE eid = \'" + eid + "\'";

        Statement stmt = getConn().createStatement();
        int success = stmt.executeUpdate(qry);

        return success;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
}
