package hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    // Establecer la URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/simulacroExamen?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Establecer la conexión
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Insertar un profesor de ejemplo
            insertarProfesor(connection, "Juan", "García", "Calle Mayor 123", "28001", "Madrid", "Madrid", "123456789");

            // Mostrar todos los profesores
            mostrarProfesores(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un profesor en la base de datos
    private static void insertarProfesor(Connection connection, String nombre, String apellidos, String direccion,
                                          String cp, String ciudad, String provincia, String telefono) throws SQLException {
        String insertSQL = "INSERT INTO profesores (nombre, apellidos, direccion, cp, ciudad, provincia, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, direccion);
            preparedStatement.setString(4, cp);
            preparedStatement.setString(5, ciudad);
            preparedStatement.setString(6, provincia);
            preparedStatement.setString(7, telefono);

            preparedStatement.executeUpdate();
        }
    }

    // Método para mostrar todos los profesores de la base de datos
    private static void mostrarProfesores(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM profesores";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Nombre: " + resultSet.getString("nombre"));
                    System.out.println("Apellidos: " + resultSet.getString("apellidos"));
                    System.out.println("Dirección: " + resultSet.getString("direccion"));
                    System.out.println("CP: " + resultSet.getString("cp"));
                    System.out.println("Ciudad: " + resultSet.getString("ciudad"));
                    System.out.println("Provincia: " + resultSet.getString("provincia"));
                    System.out.println("Teléfono: " + resultSet.getString("telefono"));
                    System.out.println("--------------------");
                }
            }
        }
    }
}
