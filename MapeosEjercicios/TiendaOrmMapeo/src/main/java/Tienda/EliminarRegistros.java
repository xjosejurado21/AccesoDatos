package Tienda;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EliminarRegistros {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Realizar una consulta para obtener los datos antes de la eliminación
            String selectHqlBeforeDelete = "FROM FabricanteTienda WHERE id = 14";
            Query<FabricanteTienda> selectQueryBeforeDelete = session.createQuery(selectHqlBeforeDelete, FabricanteTienda.class);
            List<FabricanteTienda> fabricantesBeforeDelete = selectQueryBeforeDelete.list();

            // Imprimir resultados antes de la eliminación
            System.out.println("Registros en la tabla fabricante antes de la eliminación:");
            for (FabricanteTienda f : fabricantesBeforeDelete) {
                System.out.println(f.toString());
            }

            // Eliminar el registro
            String deleteHql = "DELETE FROM FabricanteTienda WHERE id = 14";
            Query<?> deleteQuery = session.createQuery(deleteHql);
            deleteQuery.executeUpdate();

            // Realizar una nueva consulta para obtener los datos después de la eliminación
            String selectHqlAfterDelete = "FROM FabricanteTienda";
            Query<FabricanteTienda> selectQueryAfterDelete = session.createQuery(selectHqlAfterDelete, FabricanteTienda.class);
            List<FabricanteTienda> fabricantesAfterDelete = selectQueryAfterDelete.list();

            // Imprimir resultados después de la eliminación
            System.out.println("Registros en la tabla fabricante después de la eliminación:");
            for (FabricanteTienda f : fabricantesAfterDelete) {
                System.out.println(f.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
