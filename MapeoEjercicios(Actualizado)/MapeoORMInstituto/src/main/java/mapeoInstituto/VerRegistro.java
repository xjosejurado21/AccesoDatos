package mapeoInstituto;

import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class VerRegistro {
// he cambiado em la base de datos el tipo es_repetidor de tipo enum a boolean para que no problemas
    public static void main(String[] args) {

        // Configurar la sesion de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Configurar la sesion en el contexto actual
        ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
        context.bind(sessionFactory.openSession());

        try {
            // Crea el objeto alumno
            Alumno alumno = new Alumno("Pepe", "Gomez", "De la cruz", Date.valueOf("1999-03-05"), true, "638436056");
            Session session = context.currentSession();

            // Iniciar transaccion
            session.beginTransaction();

            // Guardar objeto en la base de datos
            session.save(alumno);

            // Hacer el commit de la transaccion
            session.getTransaction().commit();

            // Imprimir alumno guardado en la base de datos
            System.out.println("Alumno: " + alumno);

            // Crear consulta HQL para seleccionar todos los registros de la tabla alumno
            String hql = "FROM Alumno";
            Query<Alumno> query = session.createQuery(hql, Alumno.class);

            // Ejecutar consulta y obtener resultados
            List<Alumno> alumnos = query.list();

            // Imprimir resultados
            System.out.println("Registros en la tabla alumnos:");
            for (Alumno a : alumnos) {
                System.out.println(a.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Desligar la sesion del contexto
            ThreadLocalSessionContext.unbind(sessionFactory);
            // Cerrar la sesion de Hibernate
            sessionFactory.close();
        }

    }
}
