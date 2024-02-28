package hibernate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class VerDatos {

    public static void main(String[] args) {

        // Crear una instancia de SessionFactory
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {

            // Crear una sesión
            try (Session session = sessionFactory.openSession()) {

                // Comenzar una transacción
                try {
                    Transaction transaction = session.beginTransaction();

                    // Consultar todos los profesores
                    String hql = "FROM Profesor";
                    Query<Profesor> query = session.createQuery(hql, Profesor.class);

                    // Ejecutar la consulta y obtener los resultados
                    List<Profesor> profesores = query.list();

                    // Imprimir resultados
                    System.out.println("Registros en la tabla de profesores:");
                    for (Profesor profesor : profesores) {
                        System.out.println(profesor.toString());
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
