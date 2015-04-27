package pfe.bouygues.construction.servlet;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pfe.bouygues.construction.DbConnection;

/**
 * 
 * @author Benjamin
 * Execution avant le chargement du tomcat
 */
public class ContextListener implements ServletContextListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.debug("context Initialized");
		
		//exemple de connexion a la base (voir la classe Sql.java a adapter)
		Connection conn = DbConnection.getConnection();
		
		//TODO charger le xml, le parser et le mettre en base
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("context Destroyed");
		
	}

}
