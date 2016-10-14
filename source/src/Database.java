import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.csvreader.CsvReader;

public class Database{
	/*public static void test(Connection connection){
		try{			
			Statement statement = connection.createStatement();
			
			String query;
			query = "INSERT INTO datetime(idDatetime, day, month, year, hour, season, weather, weekend, holiday)"
						+ " VALUES(0, 1, 1, 2016, 0, 'winter', 'rainy', 0, 1)";
			
			statement.executeUpdate(query);
			
			query = "INSERT INTO driver(idDriver, age, sex, experience, previous_infractions, illness)"
						+ " VALUES(0, 22, 0, 4, 0, 0)";
			
			statement.executeUpdate(query);
			
			query = "INSERT INTO kmpoint(idKmPoint, start, end, road_name, road_type, black_point, signposting, radar)"
						+ " VALUES(0, 0, 1, 'A-52', 'roadway', 0, 1, 0)";
			
			statement.executeUpdate(query);
			
			query = "INSERT INTO vehicle(idVehicle, type, brand, car_spaces, passengers, antiquity, drive_permission, electric)"
						+ " VALUES(0, 'car', 'lamborgini', 2, 2, 4, 1, 0)";
			
			statement.executeUpdate(query);
			
			query = "INSERT INTO infraction(idInfraction, idDriver, idDatetime, idKmPoint, idVehicle, type, description, penalty)"
						+ " VALUES(0, 0, 0, 0, 0, 'low', 'not view straight line', 100)";
			
			statement.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	public static Connection ConnectDB(){
		final String url = "jdbc:mysql://localhost:3306/";
		final String dbName = "traffic";
		final String driver = "com.mysql.jdbc.Driver";
		final String username = "traffic";
		final String password = "traffic";
		
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
		try{
			CsvReader file = new CsvReader("datetime.csv");
			
			file.readHeaders();
			
			// Fields
			int idDatetime, day, month, year, hour, weekend, holiday;
			String season, weather;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			
			while(file.readRecord()){
				idDatetime = Integer.parseInt(file.get("id"));
				day = Integer.parseInt(file.get("day"));
				month = Integer.parseInt(file.get("month"));
				year = Integer.parseInt(file.get("year"));
				hour = Integer.parseInt(file.get("hour"));
				season = file.get("season");
				weather = file.get("weather");
				weekend = Integer.parseInt(file.get("weekend"));
				holiday = Integer.parseInt(file.get("holiday"));
				
				query = "INSERT INTO datetime(idDatetime, day, month, year, hour, season, weather, weekend, holiday)"
						+ " VALUES(" + idDatetime + ", " + day + ", " + month + ", " + year + ", " + hour + ", '"
						+ season + "', '" + weather + "', " + weekend + ", " + holiday + ")";
				
				statement.executeUpdate(query);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void addDriver(Connection connection){
		try{
			CsvReader file = new CsvReader("drivers.csv");
			
			file.readHeaders();
			
			// Fields
			int idDriver, age, sex, experience, previousInfractions, illness;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			
			while(file.readRecord()){
				idDriver = Integer.parseInt(file.get("id"));
				age = Integer.parseInt(file.get("age"));
				sex = Integer.parseInt(file.get("sex"));
				experience = Integer.parseInt(file.get("experience"));
				previousInfractions = Integer.parseInt(file.get("previous_infractions"));
				illness = Integer.parseInt(file.get("illness"));
				
				query = "INSERT INTO driver(idDriver, age, sex, experience, previous_infractions, illness)"
						+ " VALUES(" + idDriver + ", " + age + ", " + sex + ", " + experience + ", "
						+ previousInfractions + ", " + illness + ")";
				
				statement.executeUpdate(query);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void addKmPoint(Connection connection){
		try{
			CsvReader file = new CsvReader("km.csv");
			
			file.readHeaders();
			
			// Fields
			int idKmPoint, start, end, blackPoint, signposting, radar;
			String roadName, roadType;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			
			while(file.readRecord()){
				idKmPoint = Integer.parseInt(file.get("id"));
				start = Integer.parseInt(file.get("start"));
				end = Integer.parseInt(file.get("end"));
				roadName = file.get("road_name");
				roadType = file.get("road_type");
				blackPoint = Integer.parseInt(file.get("black_point"));
				signposting = Integer.parseInt(file.get("signposting"));
				radar = Integer.parseInt(file.get("radar"));
				
				query = "INSERT INTO kmpoint(idKmPoint, start, end, road_name, road_type, black_point, signposting, radar)"
						+ " VALUES(" + idKmPoint + ", " + start + ", " + end + ", '" + roadName + "', '"
						+ roadType + "', " + blackPoint + ", " + signposting + ", " + radar + ")";
				
				statement.executeUpdate(query);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void vehicle(Connection connection){
		try{
			CsvReader file = new CsvReader("vehicles.csv");
			
			file.readHeaders();
			
			// Fields
			int idVehicle, carSpaces, passengers, antiquity, driverPermission, electric;
			String type, brand;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			
			while(file.readRecord()){
				idVehicle = Integer.parseInt(file.get("id"));
				type = file.get("type");
				brand = file.get("brand");
				carSpaces = Integer.parseInt(file.get("car_spaces"));
				passengers = Integer.parseInt(file.get("passengers"));
				antiquity = Integer.parseInt(file.get("antiquity"));
				driverPermission = Integer.parseInt(file.get("drive_permission"));
				electric = Integer.parseInt(file.get("electric"));
				
				query = "INSERT INTO vehicle(idVehicle, type, brand, car_spaces, passengers, antiquity, drive_permission, electric)"
						+ " VALUES(" + idVehicle + ", '" + type + "', '" + brand + "', " + carSpaces + ", "
						+ passengers + ", " + antiquity + ", " + driverPermission + ", " + electric + ")";
				
				statement.executeUpdate(query);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void infraction(Connection connection){
		try{
			CsvReader file = new CsvReader("infractions.csv");
			
			file.readHeaders();
			
			// Fields
			int idInfraction, idDriver, idDatetime, idKmPoint, idVehicle, penalty;
			String type, description;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			
			while(file.readRecord()){
				idInfraction = Integer.parseInt(file.get("infraction_id"));
				idDriver = Integer.parseInt(file.get("driver_id"));
				idDatetime = Integer.parseInt(file.get("datetime_id"));
				idKmPoint = Integer.parseInt(file.get("vehicle_id"));
				idVehicle = Integer.parseInt(file.get("km_id"));
				type = file.get("type");
				description = file.get("description");
				penalty = Integer.parseInt(file.get("penalty"));
				
				query = "INSERT INTO infraction(idInfraction, idDriver, idDatetime, idKmPoint, idVehicle, type, description, penalty)"
						+ " VALUES(" + idInfraction + ", " + idDriver + ", " + idDatetime + ", " + idKmPoint + ", "
						+ idVehicle + ", '" + type + "', '" + description + "', " + penalty + ")";
				
				statement.executeUpdate(query);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}