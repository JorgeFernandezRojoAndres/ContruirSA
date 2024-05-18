import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConstruirSA {

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

    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/construirsa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
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

    private static void listarHerramientas() throws SQLException {
        String query = "SELECT nombre, stock FROM herramienta WHERE stock > 10";
        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Herramientas con stock superior a 10:");
            while (resultSet.next()) {
                System.out.println("Herramienta: " + resultSet.getString("nombre") + ", Stock: " + resultSet.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar herramientas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void darDeBajaPrimerEmpleado() throws SQLException {
        String query = "DELETE FROM empleado ORDER BY id_empleado LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Primer empleado dado de baja con éxito.");
            } else {
                System.out.println("No hay empleados para dar de baja.");
            }
        } catch (SQLException e) {
            System.out.println("Error al dar de baja al primer empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
