package mapeoGift;


import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class Main {

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
			Gift gifts = new Gift("Adam y eva","Un reencuentro para la historia",39.99,"ABAC",50);
			
			
			
			// Obtener la sesion actual
			Session session = context.currentSession();
			
			// Iniciar transaccion
			session.beginTransaction();
			
			// Guardar objeto en la base de datos
			session.save(gifts);
			
			// Hacer el commit de la transaccion
			session.getTransaction().commit();
			
			// Imprimir fabricante guardado en la base datatos
			System.out.println("Cliente Fabricante : " + gifts);
			
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
