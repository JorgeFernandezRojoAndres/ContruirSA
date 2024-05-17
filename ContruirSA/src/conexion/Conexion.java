package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Conexion {
    // Datos de conexiÃ³n sin contraseÃ±a
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/construirsa";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    
    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/construirsa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
}

    
    public static Connection getConnection() {
 if (connection == null) {
 try {
 connection = DriverManager.getConnection(URL, USER, PASSWORD);
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
 return connection;
}
   
}
