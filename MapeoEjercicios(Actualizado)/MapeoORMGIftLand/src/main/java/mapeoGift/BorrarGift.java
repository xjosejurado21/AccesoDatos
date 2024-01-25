package mapeoGift;

import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class BorrarGift {
    public static void main(String[] args) {

        // Configurar la sesion de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Configurar la sesion en el contexto actual
        ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
        context.bind(sessionFactory.openSession());

        try {
            // Crea el objeto FabricanteTienda
        	Gift gift = new Gift("Play 9","Consola de 9th generacion",900,"sony",50);
            Session session = context.currentSession();

            // Iniciar transaccion
            session.beginTransaction();

            // Guardar objeto en la base de datos
            session.save(gift);

            // Hacer el commit de la transaccion
            session.getTransaction().commit();

            // Iniciar una nueva transacción para la eliminación
            session.beginTransaction();

            // Eliminar el registro por ID
            int id = 8;
            Gift gifts = session.get(Gift.class, id);
            if (gifts != null) {
                session.delete(gifts);
            } else {
                System.out.println("No se encontró ningún registro con ID " + id);
            }

            // Hacer el commit de la transacción de eliminación
            session.getTransaction().commit();

            // Realizar una nueva consulta para obtener los datos actualizados
            session.beginTransaction();
            String selectHql = "FROM Gift WHERE id = :id";
            Query<Gift> selectQuery = session.createQuery(selectHql, Gift.class);
            selectQuery.setParameter("id", id);
            List<Gift> gifts1 = selectQuery.list();
            session.getTransaction().commit();

            // Imprimir resultados
            System.out.println("Registros en la tabla Gifts después de la eliminación:");
            for (Gift f : gifts1) {
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
