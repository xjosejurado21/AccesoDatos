package Basicos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Reto {
    public static void main(String[] args) {
        // Configura la sesión de Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            // Usa Session.doWork para ejecutar SQL nativo
            session.doWork(connection -> {
                // Configura la llamada al procedimiento almacenado
                String procedimientoAlmacenado = "{ CALL insertar_cliente(?, ?, ?, ?, ?, ?, ?) }";

                // Ejecuta el procedimiento almacenado
                try (CallableStatement statement = connection.prepareCall(procedimientoAlmacenado)) {
                    // Configura los parámetros de entrada
                    statement.setInt(1, 1); // ID Cliente
                    statement.setString(2, "Juan Pérez"); // Nombre

                    // Configura los parámetros de la dirección
                    statement.setString(3, "123 Main St"); // Calle
                    statement.setString(4, "Cityville"); // Ciudad
                    statement.setString(5, "Stateville"); // Estado
                    statement.setString(6, "12345"); // Código Postal

                    // Registra el parámetro de salida
                    statement.registerOutParameter(7, Types.VARCHAR);

                    // Ejecuta el procedimiento almacenado
                    statement.execute();

                    // Obtiene el valor del parámetro de salida
                    String mensaje = statement.getString(7);

                    // Muestra la salida
                    System.out.println("Procedimiento ejecutado con éxito. Mensaje: " + mensaje);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate al finalizar
            sessionFactory.close();
        }
    }
}
