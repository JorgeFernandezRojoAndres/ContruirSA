
package contruirsa;
import static conexion.Conexion.getConexion;
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
    private static void insertarEmpleado(String nombre, boolean estado) throws SQLException {
        String query = "INSERT INTO empleado (nombre, estado) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            int estadoValue = estado ? 1 : 0; // Convertir booleano a 1 o 0
            System.out.println("Insertando empleado: " + nombre + ", Estado: " + estadoValue);
            statement.setInt(2, estadoValue);
            statement.executeUpdate();
            System.out.println("Empleado insertado con éxito: " + nombre + ", Estado: " + estadoValue);
        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void insertarHerramienta(String nombre, int stock) throws SQLException {
        String query = "INSERT INTO herramienta (nombre, stock, estado) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setInt(2, stock);
            statement.setInt(3, 1); // Agregar estado explícitamente con valor predeterminado 1
            statement.executeUpdate();
            System.out.println("Herramienta insertada con éxito: " + nombre);
        } catch (SQLException e) {
            System.out.println("Error al insertar herramienta: " + e.getMessage());
            e.printStackTrace(); // Corregir a printStackTrace
        }
    }
}
