package pfe.bouygues.construction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Sql {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * CREATE   
	 */
	public void create(String albumArtist, String album, String title,
			Integer genre, String numberTrack, String path) {
		Connection conn = DbConnection.getConnection(); 
		try{
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
		} catch (SQLException e) {
			System.out.println("l'adresse du fichier "+path+" ne peut être sauvegardée");
			e.printStackTrace();
		}
	}


	/**
	 * FIND   
	 */
	public String find(int id) {
		Connection conn = DbConnection.getConnection(); 
		String URI = null;

		try {
			ResultSet result = conn.createStatement(
					).executeQuery(
							"SELECT path FROM track_tbl WHERE id = " + id
							);
			if(result.next()){
				URI = result.getString("path"); 
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return URI;
	}

	/**
	 * FIND ALL
	 */
//	public ArrayList<TrackBean> findAll() {
//		Connection conn = DbConnection.getConnection(); 
//		ArrayList<TrackBean> trackList = new ArrayList<TrackBean>();
//		try {
//			ResultSet result = conn.createStatement(
//					).executeQuery(
//							"SELECT * FROM track_tbl"
//							);
//			while(result.next()){
//				if(! result.getString("title").equals("null")){
//					trackList.add(new TrackBean(
//							result.getInt("id"), 
//							result.getString("albumArtist"),
//							result.getString("album"),
//							result.getString("title"),
//							result.getInt("genre"),
//							result.getString("numberTrack")));
//				}
//			}
//			result.close();
//		} catch (SQLException e) {
//			logger.error("Failed to find all the data in the database: "+e.getMessage());
//			e.printStackTrace();
//		}
//		return trackList;
//	}

	/**
	 * COUNT
	 */
	public int count() {
		Connection conn = DbConnection.getConnection(); 
		int rowNumber = 0;
		try {
			ResultSet result = conn.createStatement(
					).executeQuery(
							"SELECT count(id) FROM track_tbl"
							);
			if(result.next()){

				rowNumber = result.getRow();
			}

			result.close();
		} catch (SQLException e) {
			logger.error("Failed to count the number of row in the database: "+e.getMessage());
		}
		return rowNumber;
	}

	/**
	 * CREATION DE LA TABLE
	 */
	public void createTable(){
		try{
			Connection conn = DbConnection.getConnection();
			if(conn != null){
				logger.info("[DATABASE] get Connection");
			} else {
				logger.error("[DATABASE] can't get Connection");
			}

			Statement stat = conn.createStatement();
			stat.executeUpdate("DROP TABLE IF EXISTS track_tbl;");

			stat.executeUpdate("CREATE TABLE track_tbl ( " +
					"id INTEGER PRIMARY KEY ASC, " +
					"albumArtist varchar(30), " +
					"album varchar(30), " +
					"title varchar(30), " +
					"genre INTEGER, " +
					"numberTrack varchar(10), " +
					"path varchar(250));");
			stat.close();
			logger.info("[DATABASE] create table");
		} catch (Exception e){
			logger.info("[DATABASE] failed to create table", e);
		}
	}
}
