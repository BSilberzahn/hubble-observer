package pfe.bouygues.construction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pfe.bouygues.construction.ControlFile.Field;


public class DataUtil {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	//TODO
	public void createProject(String name, HashMap<String, String> markers) {
		Connection conn = DbConnection.getConnection(); 
		try{
			/*
			Statement stat = conn.createStatement();
			stat.execute(
					"INSERT INTO track_tbl (albumArtist, album, title, genre, numberTrack, path) VALUES (" +
							"\""+albumArtist+"\"," +
							"\""+album+"\"," +
							"\""+title+"\"," +
							genre+"," +
							"\""+numberTrack+"\"," +
							"\""+path+"\");"
					);
			stat.close();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//TODO
	public HashMap<Integer, String> findProject(String projet) {
		Connection conn = DbConnection.getConnection(); 

		try {
			ResultSet result = conn.createStatement(
					).executeQuery(
							"SELECT path FROM track_tbl WHERE id = " + projet
							);
			if(result.next()){
				result.getString("path"); 
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	public float findProjectEventTime(String projet) {
		Connection conn = DbConnection.getConnection(); 

		try {
			ResultSet result = conn.createStatement(
					).executeQuery(
							"SELECT path FROM track_tbl WHERE id = " + projet
							);
			if(result.next()){
				result.getString("path"); 
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0F;
	}

	public void createTables(){
		try{
			Connection conn = DbConnection.getConnection();
			if(conn != null){
				logger.info("[DATABASE] get Connection");
			} else {
				logger.error("[DATABASE] can't get Connection");
			}

			Statement stat = conn.createStatement();

			stat.executeUpdate("CREATE TABLE IF NOT EXISTS project_marker ( " +
					"project varchar(30) , " +
					"marker varchar(255), " +
					"date varchar(10));");
			stat.close();
			logger.info("[DATABASE] create table");
		} catch (Exception e){
			logger.info("[DATABASE] failed to create table", e);
		}
	}
	
	public Collection<String> getAllValuesOf(ControlFile.Field f){
		ArrayList<String> list = new ArrayList<String>();
		for(ControlFile cf : XmlDocument.getDocument().values()){
			if(!list.contains(cf.getField(f)))
				list.add(cf.getField(f));
		}
		return list;
	}
	
	public Collection<String> getAllValuesOfWithConstraint(ControlFile.Field f, Constraint... cts){
		ArrayList<String> list = new ArrayList<String>();
		for(ControlFile cf : XmlDocument.getDocument().values()){
			boolean ctOk = true;
			for(Constraint c : cts){
				if(!cf.getField(c.getField()).equals(c.value)){
					ctOk = false;
					break;
				}
			}
			if(!list.contains(cf.getField(f)) && ctOk)
				list.add(cf.getField(f));
		}
		return list;
	}
	
	public static class Constraint {
		
		private ControlFile.Field field;
		private String value;
		
		public Constraint(Field field, String value) {
			this.field = field;
			this.value = value;
		}

		public ControlFile.Field getField() {
			return field;
		}

		public String getValue() {
			return value;
		}
		
	}
}
