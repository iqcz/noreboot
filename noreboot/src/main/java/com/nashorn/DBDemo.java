package com.nashorn;

import com.google.common.base.Strings;
import com.nashorn.entity.Address;

import javax.script.ScriptException;

import static com.nashorn.util.Constant.*;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * run this class as Java Wep application in tomcat, jetty or other server.
 * here just a demo
 * @author i324779
 * @date 07/02/2018
 */
public class DBDemo {

    public static void main(String[] args) {
        DBDemo demo = new DBDemo();

        System.out.println(demo.findAddressById(1));
    }

    /**
     * load mysql driver and and connection.
     * @return db connection
     * @throws SQLException
     */
    private Connection getDatabaseConnection() throws SQLException {
        try {
            Class.forName(MYSQL_DRIVER);
            return DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end method getDatabaseConnection

    /**
     * according address id to get satisfied records.
     * @param addressId address id
     * @return address list
     */
    private List<Address> findAddressById(int addressId) {
        List<Address> addresses = new ArrayList<>(10);
        Connection conn = null;

        try {
            conn = getDatabaseConnection();
            String sql = "SELECT addr_id, street, city, state, country FROM ADDRESSES WHERE addr_id=?";

            try {
                String appendConditions = SqlAppend.appendConditions();
                if (!Strings.isNullOrEmpty(appendConditions)) {
                    sql += appendConditions;
                }
            } catch (FileNotFoundException | ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, addressId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                addresses.add(establishAddress(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return addresses;
    } // end method findAddressById

    /**
     * get result set to map object.
     * @param rs result set
     * @return mapping db columns to object fields.
     * @throws SQLException
     */
    private Address establishAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setAddressId(rs.getInt("addr_id"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setCountry(rs.getString("country"));

        return address;
    } // end method establishAddress
}
