package pfe.bouygues.construction.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pfe.bouygues.construction.DbConnection;
import pfe.bouygues.construction.XmlDocument;

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
		
		DbConnection.getConnection();
		XmlDocument.getDocument();
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("context Destroyed");
		
	}

}
