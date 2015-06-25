package com.company.core.dao;

import com.company.core.api.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Locale;

/**
 * @author Sophie
 * @date 10.05.2015.
 */
public class DBHelper {

    private static final Logger logger = Logger.getLogger(DBHelper.class);

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(Constants.URL, Constants.name, Constants.password);
        } catch (ClassNotFoundException e) {
            logger.error("Driver of DB don't found ", e);
        } catch (SQLException e) {
            logger.error("Connection is not established ", e);
        }
        return connection;
    }

    public static void freeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();;
        } catch (SQLException e) {
            logger.error("Error to close resources",e);
        }
    }

    public static void freeResources(Connection connection, PreparedStatement preparedStatement){
        try {
            preparedStatement.close();
            connection.close();;
        } catch (SQLException e) {
            logger.error("Error to close resources",e);
        }
    }

    public static void freeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Error to close resources",e);
        }
    }

}