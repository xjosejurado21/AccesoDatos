package mapeoGift;

import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class ModificarGift {
    public static void main(String[] args) {

        // Configurar la sesion de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Configurar la sesion en el contexto actual
        ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
        context.bind(sessionFactory.openSession());

        try {
            // Crea el objeto FabricanteTienda
        	 Gift gift = new Gift("Play 8","Consola de 8th generacion",800,"sony",89);
            Session session = context.currentSession();

            // Iniciar transaccion
            session.beginTransaction();

            // Guardar objeto en la base de datos
            session.save(gift);

            // Hacer el commit de la transaccion
            session.getTransaction().commit();

            // Iniciar una nueva transacción para la actualización
            session.beginTransaction();

            // Modificar el nombre por "Gandalf"
       
        
            String updateHql = "UPDATE Gift SET nombre = 'Psp' WHERE id = 2";

            Query<?> updateQuery = session.createQuery(updateHql);
            updateQuery.executeUpdate();

            // Hacer el commit de la transacción de actualización
            session.getTransaction().commit();

            // Realizar una nueva consulta para obtener los datos actualizados
            session.beginTransaction();
            String selectHql = "FROM Gift WHERE id = 2";
            Query<Gift> selectQuery = session.createQuery(selectHql, Gift.class);
            List<Gift> gifts = selectQuery.list();
            session.getTransaction().commit();

            // Imprimir resultados
            System.out.println("Registros en la tabla Giftland después de la actualización:");
            for (Gift f : gifts) {
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
