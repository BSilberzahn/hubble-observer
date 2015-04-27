package pfe.bouygues.construction;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnection {

	private final static Logger logger = LoggerFactory.getLogger(DbConnection.class); 
	
	private static Connection connect;

	private DbConnection()
	{
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			String url = "jdbc:sqlite:hubble-observer-db";
			connect = DriverManager.getConnection(url);

		} catch (Exception e) {
			logger.error("Can't connect to the bdd", e);
		}
	}

	public static Connection getConnection(){
		if(connect == null)
		{
			new DbConnection();
		}
		return connect;
	}   
}
