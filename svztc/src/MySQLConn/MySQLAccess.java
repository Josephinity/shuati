package MySQLConn;

import java.sql.*;
import java.sql.DriverManager;
import java.lang.Exception;
import java.util.*;
/**
 *
 */
public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void getConnection() {
        //Class.forName("com.mysql.jdbc.Driver");
        try {
            connect = DriverManager.getConnection("jdbc:mysql://aplusplus.coigfknncdoz.us-west-2.rds.amazonaws.com",
                    "aplusplus", "YBByRsZ0jIlX");
        } catch(Exception e){
            close();
            System.out.println("fail to connect");
        }
    }


    public void createTable() throws Exception {
        try{
            statement = connect.createStatement();
            String query = "CREATE TABLE tmp.Standard(" +
                    "id INT not null," +
                    "data VARCHAR(64),"+
                    "PRIMARY KEY(id)"+
                    ")";
            statement.executeUpdate(query);
        }catch(Exception e){
            close();
            throw e;
        }finally {
            close();
        }
    }



    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
