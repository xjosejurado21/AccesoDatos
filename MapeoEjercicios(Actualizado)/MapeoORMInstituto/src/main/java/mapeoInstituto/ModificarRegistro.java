package mapeoInstituto;

import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class ModificarRegistro {
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

            // Iniciar una nueva transacción para la actualización
            session.beginTransaction();

            // Modificar el nombre por "Gandalf"
            String updateHql = "UPDATE Alumno SET nombre = 'Eloisa' WHERE id = 4";
            Query<?> updateQuery = session.createQuery(updateHql);
            updateQuery.executeUpdate();

            // Hacer el commit de la transacción de actualización
            session.getTransaction().commit();

            // Realizar una nueva consulta para obtener los datos actualizados
            session.beginTransaction();
            String selectHql = "FROM alumno WHERE id = 4";
            Query<Alumno> selectQuery = session.createQuery(selectHql, Alumno.class);
            List<Alumno> alumnos = selectQuery.list();
            session.getTransaction().commit();

            // Imprimir resultados
            System.out.println("Registros en la tabla Alumno después de la actualización:");
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
