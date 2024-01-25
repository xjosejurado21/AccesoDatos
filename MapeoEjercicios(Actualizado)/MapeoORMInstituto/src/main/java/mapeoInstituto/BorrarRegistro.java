package mapeoInstituto;

import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class BorrarRegistro {
    public static void main(String[] args) {

        // Configurar la sesion de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Configurar la sesion en el contexto actual
        ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
        context.bind(sessionFactory.openSession());

        try {
            // Crea el objeto FabricanteTienda
            Alumno alumno = new Alumno("Santiago", "Segura", "Fernandez", Date.valueOf("1999-03-05"),true,"638436056");
            Session session = context.currentSession();

            // Iniciar transaccion
            session.beginTransaction();

            // Guardar objeto en la base de datos
            session.save(alumno);

            // Hacer el commit de la transaccion
            session.getTransaction().commit();

            // Iniciar una nueva transacción para la eliminación
            session.beginTransaction();

            // Eliminar el registro por ID
            int id = 7;
            Alumno alumno1 = session.get(Alumno.class, id);
            if (alumno1 != null) {
                session.delete(alumno1);
            } else {
                System.out.println("No se encontró ningún registro con ID " + id);
            }

            // Hacer el commit de la transacción de eliminación
            session.getTransaction().commit();

            // Realizar una nueva consulta para obtener los datos actualizados
            session.beginTransaction();
            String selectHql = "FROM alumno WHERE id = :id";
            Query<Alumno> selectQuery = session.createQuery(selectHql, Alumno.class);
            selectQuery.setParameter("id", id);
            List<Alumno> alumnos = selectQuery.list();
            session.getTransaction().commit();

            // Imprimir resultados
            System.out.println("Registros en la tabla FabricanteTienda después de la eliminación:");
            for (Alumno f : alumnos) {
                System.out.println(f.toString());
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
