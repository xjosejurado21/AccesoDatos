package ormMapeo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

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
			FabricanteTienda fabrica = new FabricanteTienda("Google España");
			
			
			// Obtener la sesion actual
			Session session = context.currentSession();
			
			// Iniciar transaccion
			session.beginTransaction();
			
			// Guardar objeto en la base de datos
			session.save(fabrica);
			
			// Hacer el commit de la transaccion
			session.getTransaction().commit();
			
			// Imprimir fabricante guardado en la base datatos
			System.out.println("Cliente : " + fabrica);
			
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