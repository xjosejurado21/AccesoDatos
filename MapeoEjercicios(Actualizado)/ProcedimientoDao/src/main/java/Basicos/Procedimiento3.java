package Basicos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Procedimiento3 {
    public static void main(String[] args) {
        // Configura la sesión de Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            // Usa Session.doWork para ejecutar SQL nativo
            session.doWork(connection -> {
                // Configura la llamada al procedimiento almacenado
                String procedimientoAlmacenado = "{ CALL insertar_empleado3(?, ?, ?) }";

                // Ejecuta el procedimiento almacenado
                try (CallableStatement statement = connection.prepareCall(procedimientoAlmacenado)) {
                    // Configura los parámetros de entrada
                    statement.setInt(1, 1); // ID Empleado (número ficticio)
                    statement.setString(2, "John"); // Nombre ficticio
                    statement.setString(3, "Doe"); // Apellido ficticio

                    // Ejecuta el procedimiento almacenado
                    statement.execute();

                    System.out.println("Procedimiento ejecutado con éxito");
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
