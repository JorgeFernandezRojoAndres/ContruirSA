
package contruirsa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ContruirSA {

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = getConexion();
            System.out.println("Conexión establecida con éxito.");

            // Insertar 3 empleados (activos)
            insertarEmpleado("Empleado1", true);
            insertarEmpleado("Empleado2", true);
            insertarEmpleado("Empleado3", true);

            // Insertar 2 herramientas
            insertarHerramienta("Herramienta1", 15);
            insertarHerramienta("Herramienta2", 20);

            // Listar todas las herramientas con stock superior a 10
            listarHerramientas();

            // Dar de baja al primer empleado ingresado a la base de datos
            darDeBajaPrimerEmpleado();

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    }
    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/construirsa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
}
    }
