package Ejer7;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Mapeo.Alumno;

public class VerRegistros {

    public static void main(String[] args) {

        // Crear una instancia de SessionFactory
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {

            // Crear una sesión
            try (Session session = sessionFactory.openSession()) {

                // Comenzar una transacción
                try {
                    Transaction transaction = session.beginTransaction();

                    // Consultar todos los alumnos
                    String hql = "FROM Alumno";
                    Query<Alumno> query = session.createQuery(hql, Alumno.class);

                    // Ejecutar la consulta y obtener los resultados
                    List<Alumno> alumnos = query.list();

                    // Imprimir resultados
                    System.out.println("Registros en la tabla alumnos:");
                    for (Alumno alumno : alumnos) {
                        System.out.println(alumno.toString());
                    }

                    // Confirmar la transacción
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Si hay algún error, realizar un rollback
                    session.getTransaction().rollback();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
