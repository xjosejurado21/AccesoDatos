package mapeoORMTienda;


import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

public class MainTienda {

	public static void main(String[] args) {
		
		//Configurar la sesion del Hibernate
		SessionFactory sessionFactory = new Configuration()
				.configure() // llama al fichero hibernate.cfg.xml
				
				// .configure("hibernate.cfg.xml") // Ruta del archivo configuracion
				.buildSessionFactory(); // Construir la sesion de Hibernate
		
		// Configurar la sesion en el contexto actual
		ThreadLocalSessionContext context = new ThreadLocalSessionContext((SessionFactoryImplementor) sessionFactory);
		context.bind(sessionFactory.openSession());
		
		try {
			// Crea el objeto fabricante
			FabricanteTienda fabricante = new FabricanteTienda("Google España");
			
			
			// Obtener la sesion actual
			Session session = context.currentSession();
			
			// Iniciar transaccion
			session.beginTransaction();
			
			// Guardar objeto en la base de datos
			session.save(fabricante);
			
			// Hacer el commit de la transaccion
			session.getTransaction().commit();
			
			// Imprimir fabricante guardado en la base datatos
			System.out.println("Cliente Fabricante : " + fabricante);
			
			// Crear consulta HQL para seleccionar todos los registros de la tabla fabricante
			String hql = "FROM FabricanteTienda";
			Query<FabricanteTienda> query = session.createQuery(hql, FabricanteTienda.class);



            // Ejecutar consulta y obtener resultados
            List<FabricanteTienda> fabricantes = query.list();


            // Imprimir resultados
            System.out.println("Registros en la tabla fabricante:");
            for (FabricanteTienda f : fabricantes) {
                System.out.println(f.toString());
            }
            
            //ejer 4 avanzados
            // Añadir fabricante
            FabricanteTienda fabricante1 = new FabricanteTienda("pc gaming2");


        	// Obtener la sesión actual
        	Session session1 = context.currentSession();


        	// Iniciar transacción
        	session1.beginTransaction();


        	// Guardar objeto fabricante en la base de datos
        	session1.save(fabricante1);      	     	


        	// Hacer commit de la transacción
        	session1.getTransaction().commit();
        	
        	
        	// Crear objeto producto
        	Producto producto = new Producto("PORTATIL gaming2", 3000, fabricante1);


        	// Obtener la sesión actual
        	Session session11 = context.currentSession();


        	// Iniciar transacción
        	session11.beginTransaction();


        	// Guardar objeto producto en la base de datos
        	session11.save(producto);


        	// Hacer commit de la transacción
        	session11.getTransaction().commit();
        	
        	
        	// añadir compra ( ejer 5 avanzado)
        	
        	
        	Compras compra = new Compras(Date.valueOf("1999-03-05"), fabricante1, producto, 88);
        	
        	// Obtener la sesión actual
        	Session session111 = context.currentSession();


        	// Iniciar transacción
        	session111.beginTransaction();


        	// Guardar objeto fabricante en la base de datos
        	session111.save(fabricante1);      	     	


        	// Hacer commit de la transacción
        	session111.getTransaction().commit();
        	
        	
        	// imprimir por pantalla objetos creados
        	System.out.println(producto.toString());
        	System.out.println(fabricante1.toString());
        	System.out.println(compra.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// Desligar la sesion del contexto
			ThreadLocalSessionContext.unbind(sessionFactory);
			// Cerrar la sesion del Hibernate
			sessionFactory.close();
		}

	}

}
