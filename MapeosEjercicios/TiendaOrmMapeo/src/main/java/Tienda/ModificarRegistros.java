package Tienda;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ModificarRegistros {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Realizar una consulta para obtener los datos antes de la modificación
            String selectHqlBeforeUpdate = "FROM FabricanteTienda WHERE id = 14";
            Query<FabricanteTienda> selectQueryBeforeUpdate = session.createQuery(selectHqlBeforeUpdate, FabricanteTienda.class);
            List<FabricanteTienda> fabricantesBeforeUpdate = selectQueryBeforeUpdate.list();

            // Imprimir resultados antes de la modificación
            System.out.println("Registros en la tabla fabricante antes de la modificación:");
            for (FabricanteTienda f : fabricantesBeforeUpdate) {
                System.out.println(f.toString());
            }

            // Modificar el nombre por "Gandalf"
            String updateHql = "UPDATE FabricanteTienda SET nombre = 'Gandalf' WHERE id = 14";
            Query<?> updateQuery = session.createQuery(updateHql);
            updateQuery.executeUpdate();

            // Realizar una nueva consulta para obtener los datos actualizados
            String selectHqlAfterUpdate = "FROM FabricanteTienda WHERE id = 14";
            Query<FabricanteTienda> selectQueryAfterUpdate = session.createQuery(selectHqlAfterUpdate, FabricanteTienda.class);
            List<FabricanteTienda> fabricantesAfterUpdate = selectQueryAfterUpdate.list();

            // Imprimir resultados después de la modificación
            System.out.println("Registros en la tabla fabricante después de la modificación:");
            for (FabricanteTienda f : fabricantesAfterUpdate) {
                System.out.println(f.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
