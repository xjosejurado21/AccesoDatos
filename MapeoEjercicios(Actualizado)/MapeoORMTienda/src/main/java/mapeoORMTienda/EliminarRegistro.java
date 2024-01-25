package mapeoORMTienda;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class EliminarRegistro {
    public static void main(String[] args) {

        // Configurar la sesion de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Configurar la sesion en el contexto actual
        ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
        context.bind(sessionFactory.openSession());

        try {
            // Crea el objeto FabricanteTienda
            FabricanteTienda fabricante = new FabricanteTienda("google");
            Session session = context.currentSession();

            // Iniciar transaccion
            session.beginTransaction();

            // Guardar objeto en la base de datos
            session.save(fabricante);

            // Hacer el commit de la transaccion
            session.getTransaction().commit();

            // Iniciar una nueva transacción para la eliminación
            session.beginTransaction();

            // Eliminar el registro por ID
            int fabricanteId = 14;
            FabricanteTienda fabricanteAEliminar = session.get(FabricanteTienda.class, fabricanteId);
            if (fabricanteAEliminar != null) {
                session.delete(fabricanteAEliminar);
            } else {
                System.out.println("No se encontró ningún registro con ID " + fabricanteId);
            }

            // Hacer el commit de la transacción de eliminación
            session.getTransaction().commit();

            // Realizar una nueva consulta para obtener los datos actualizados
            session.beginTransaction();
            String selectHql = "FROM FabricanteTienda WHERE id = :id";
            Query<FabricanteTienda> selectQuery = session.createQuery(selectHql, FabricanteTienda.class);
            selectQuery.setParameter("id", fabricanteId);
            List<FabricanteTienda> fabricantes = selectQuery.list();
            session.getTransaction().commit();

            // Imprimir resultados
            System.out.println("Registros en la tabla FabricanteTienda después de la eliminación:");
            for (FabricanteTienda f : fabricantes) {
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
