import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.csvreader.CsvReader;

public class Database{
	public static Connection ConnectDB(){
		final String url = "jdbc:mysql://localhost:3306/";
		final String dbName = "traffic";
		final String params = "?autoReconnect=true&useSSL=false";
		final String driver = "com.mysql.jdbc.Driver";
		final String username = "traffic";
		final String password = "traffic";
		
		try{
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url + dbName + params, username, password);
			
			return connection;
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static void addDatetime(Connection connection){
		try{
			CsvReader file = new CsvReader("store/datetime/datetime.csv");
			
			file.readHeaders();
			
			// Fields
			int day, month, year, hour, weekend, holiday;
			String season, weather;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			
			while(file.readRecord()){
				// Get values from each row in csv
				day = Integer.parseInt(file.get("day"));
				month = Integer.parseInt(file.get("month"));
				year = Integer.parseInt(file.get("year"));
				hour = Integer.parseInt(file.get("hour"));
				season = file.get("season");
				weather = file.get("weather");
				weekend = Integer.parseInt(file.get("weekend"));
				holiday = Integer.parseInt(file.get("holiday"));
				
				// Check if already exists in the table in the database
				query = "SELECT count(*) FROM datetime WHERE day = " + day + " and month = " + month + " and year = "
						+ year + " and hour = " + hour + " and season = '" + season + "' and weather = '" + weather
						+ "' and weekend = " + weekend + " and holiday = " + holiday;
				
				ResultSet resultSet = statement.executeQuery(query);
				
				int cont = 0;
				while(resultSet.next()){
					cont = resultSet.getInt("count(*)");
				}
				
				resultSet.close();
				
				if(cont == 0){
					query = "INSERT INTO datetime(day, month, year, hour, season, weather, weekend, holiday)"
							+ " VALUES(" + day + ", " + month + ", " + year + ", " + hour + ", '"
							+ season + "', '" + weather + "', " + weekend + ", " + holiday + ")";
					
					statement.executeUpdate(query);
				}
				
				
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
			File folder = new File("store/driver/");
			
			int successful = 0;
			
			for(File file : folder.listFiles()){
				CsvReader drivers = new CsvReader("store/driver/" + file.getName());
				
				drivers.readHeaders();
				
				// Fields
				int age, sex, experience, previousInfractions, illness;
				
				// Query
				String query;
				Statement statement = connection.createStatement();
				
				while(drivers.readRecord()){
					age = Integer.parseInt(drivers.get("age"));
					sex = Integer.parseInt(drivers.get("sex"));
					experience = Integer.parseInt(drivers.get("experience"));
					previousInfractions = Integer.parseInt(drivers.get("previous_infractions"));
					illness = Integer.parseInt(drivers.get("illness"));
					
					// Check if already exists in the table in the database
					query = "SELECT count(*) FROM driver WHERE age = " + age + " and sex = " + sex + " and experience = "
							+ experience + " and previous_infractions = " + previousInfractions + " and illness = " + illness;
					
					ResultSet resultSet = statement.executeQuery(query);
					
					int cont = 0;
					while(resultSet.next()){
						cont = resultSet.getInt("count(*)");
					}
					
					resultSet.close();
					
					if(cont == 0){
						query = "INSERT INTO driver(age, sex, experience, previous_infractions, illness)"
								+ " VALUES(" + age + ", " + sex + ", " + experience + ", "
								+ previousInfractions + ", " + illness + ")";
						
						statement.executeUpdate(query);
						
						successful++;
					}
				}
			}
			
			System.out.println("Driver inserted successfully: " + successful);
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
			CsvReader file = new CsvReader("store/km/km.csv");
			
			file.readHeaders();
			
			// Fields
			int start, end, blackPoint, signposting, radar;
			String roadName, roadType;
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			
			while(file.readRecord()){
				start = Integer.parseInt(file.get("start"));
				end = Integer.parseInt(file.get("end"));
				roadName = file.get("road_name");
				roadType = file.get("road_type");
				blackPoint = Integer.parseInt(file.get("black_point"));
				signposting = Integer.parseInt(file.get("signposting"));
				radar = Integer.parseInt(file.get("radar"));
				
				query = "SELECT count(*) FROM kmpoint WHERE start = " + start + " and end = " + end + " and road_name = '"
						+ roadName + "' and road_type = '" + roadType + "' and black_point = " + blackPoint
						+ " and signposting = " + signposting + " and radar = " + radar;
				
				resultSet = statement.executeQuery(query);
				
				int cont = 0;
				while(resultSet.next()){
					cont = resultSet.getInt("count(*)");
				}
				
				resultSet.close();
				
				if(cont == 0){			
					query = "INSERT INTO kmpoint(start, end, road_name, road_type, black_point, signposting, radar)"
							+ " VALUES(" + start + ", " + end + ", '" + roadName + "', '"
							+ roadType + "', " + blackPoint + ", " + signposting + ", " + radar + ")";
					
					statement.executeUpdate(query);
				}
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
			File folder = new File("store/vehicle/");
			
			for(File file : folder.listFiles()){
				CsvReader vehicles = new CsvReader("store/vehicle/" + file.getName());
				
				vehicles.readHeaders();
				
				// Fields
				int carSpaces, passengers, antiquity, driverPermission, electric;
				String type, brand;
				
				// Query
				String query;
				Statement statement = connection.createStatement();
				ResultSet resultSet;
				
				while(vehicles.readRecord()){
					type = vehicles.get("type");
					brand = vehicles.get("brand");
					carSpaces = Integer.parseInt(vehicles.get("car_spaces"));
					passengers = Integer.parseInt(vehicles.get("passengers"));
					antiquity = Integer.parseInt(vehicles.get("antiquity"));
					driverPermission = Integer.parseInt(vehicles.get("drive_permission"));
					electric = Integer.parseInt(vehicles.get("electric"));
					
					query = "SELECT count(*) FROM vehicle WHERE type = '" + type + "' and brand = '" + brand
							+ "' and car_spaces = " + carSpaces + " and passengers = " + passengers
							+ " and antiquity = " + antiquity + " and drive_permission = " + driverPermission
							+ " and electric = " + electric;
					
					resultSet = statement.executeQuery(query);
					
					int cont = 0;
					while(resultSet.next()){
						cont = resultSet.getInt("count(*)");
					}
					
					if(cont == 0){
						query = "INSERT INTO vehicle(type, brand, car_spaces, passengers, antiquity, drive_permission, electric)"
								+ " VALUES('" + type + "', '" + brand + "', " + carSpaces + ", "
								+ passengers + ", " + antiquity + ", " + driverPermission + ", " + electric + ")";
						
						statement.executeUpdate(query);
					}					
				}
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void infraction(Connection connection, int amount){
		try{
			Random rand = new Random();
			
			// Query
			String query;
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			
			// Driver
			query = "SELECT idDriver FROM driver";
			
			resultSet = statement.executeQuery(query);
			
			List<Integer> idsDriver = new ArrayList<Integer>();
			
			while(resultSet.next()){
				idsDriver.add(resultSet.getInt("idDriver"));
			}
			
			int sizeDrivers = idsDriver.size();
			
			// Datetime
			query = "SELECT idDatetime FROM datetime";
			
			resultSet = statement.executeQuery(query);
			
			List<Integer> idsDatetime = new ArrayList<Integer>();
			
			while(resultSet.next()){
				idsDatetime.add(resultSet.getInt("idDatetime"));
			}
			
			int sizeDatetime = idsDatetime.size();
			
			// Kmpoint
			query = "SELECT idKmPoint FROM kmpoint";
			
			resultSet = statement.executeQuery(query);
			
			List<Integer> idsKmpoint = new ArrayList<Integer>();
			
			while(resultSet.next()){
				idsKmpoint.add(resultSet.getInt("idKmPoint"));
			}
			
			int sizeKmpoint = idsKmpoint.size();
			
			// Vehicle
			query = "SELECT idVehicle FROM vehicle";
			
			resultSet = statement.executeQuery(query);
			
			List<Integer> idsVehicle = new ArrayList<Integer>();
			
			while(resultSet.next()){
				idsVehicle.add(resultSet.getInt("idVehicle"));
			}
			
			int sizeVehicle = idsVehicle.size();
			
			CsvReader descriptionFile = new CsvReader("store/infraction/descs_type_penalty.csv");
			descriptionFile.readHeaders();
			
			String [][] descriptions = new String[46][3];
			
			int i = 0;
			while(descriptionFile.readRecord()){
				descriptions[i][0] = descriptionFile.get("type");
				descriptions[i][1] = descriptionFile.get("description");
				descriptions[i][2] = descriptionFile.get("penalty");
				i++;
			}
			
			int idDriver, idDatetime, idKmpoint, idVehicle, penalty;
			String type, description;
			boolean flag = false;
			for(i = 0; i < amount; i++){
				while(!flag){
					idDriver = idsDriver.get(rand.nextInt(sizeDrivers));
					idDatetime = idsDatetime.get(rand.nextInt(sizeDatetime));
					idKmpoint = idsKmpoint.get(rand.nextInt(sizeKmpoint));
					idVehicle = idsVehicle.get(rand.nextInt(sizeVehicle));
					
					int randDescTypePenalty = rand.nextInt(46);
					type = descriptions[randDescTypePenalty][0];
					description = descriptions[randDescTypePenalty][1];
					penalty = Integer.parseInt(descriptions[randDescTypePenalty][2]);
					
					query = "SELECT count(*) FROM infraction WHERE idDriver = " + idDriver + " and idDatetime = " + idDatetime
							+ " and idKmPoint = " + idKmpoint + " and idVehicle = " + idVehicle + " and type = '" + type
							+ "' and description = '" + description + "' and penalty = " + penalty;
					
					resultSet = statement.executeQuery(query);
					
					int cont = 0;
					while(resultSet.next()){
						cont = resultSet.getInt("count(*)");
					}
					
					resultSet.close();
					
					if(cont == 0){
						query = "INSERT INTO infraction(idDriver, idDatetime, idKmPoint, idVehicle, type, description, penalty)"
								+ " VALUES(" + idDriver + ", " + idDatetime + ", " + idKmpoint + ", " + idVehicle + ", '" + type
								+ "', '" + description + "', " + penalty + ")";
						
						statement.executeUpdate(query);
						
						flag = true;
					}
				}
				
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}