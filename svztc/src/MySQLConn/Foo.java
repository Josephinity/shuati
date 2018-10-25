package MySQLConn;

/**

 */
public class Foo {
    public static void main(String[] args) throws Exception {
        MySQLAccess connection = new MySQLAccess();
        connection.getConnection();
        connection.createTable();
        connection.close();
    }
}
