import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database{
	public static Connection ConnectDB(){
		final String url = "jdbc:mysql://localhost:3306/";
		final String dbName = "traffic";
		final String driver = "com.mysql.jdbc.Driver";
		final String username = "root";
		final String password = "";
		
		try{
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url + dbName, username, password);
			
			return connection;
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static void addDatetime(Connection connection){
		String query = "INSERT INTO datetime(idDatetime, day, month, year, hour, season, weather, weekend, holiday)"
				+ " VALUES(0, 1, 1, 2000, 0, 'winter', 'cloudy', 0, 0)";
		
		try{
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}