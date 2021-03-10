package Connect;
import java.sql.*;

/**
 * Clasa in care se face conexiunea la baza de date din MySql WorkBench
 */
public class ConnectionFactory {
    private Connection conn;

    /**
     * Metoda care conecteaza programul la baza de date
     */
    public void conecteaza(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb", "root", "Gih11669");
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

}

