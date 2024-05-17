
package contruirsa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ContruirSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/construirsa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
}
    }
