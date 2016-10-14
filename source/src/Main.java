import java.sql.Connection;

public class Main{
	// Main
	public static void main(String[] args){
		/** Create datasets **/
		/* Drivers */
		// Dataset.drivers(20000); //
		
		/* Datetime */
		// Dataset.datetime(2000, 2016);
		
		/* Vehicles */
		// Dataset.vehicles(5000); //
		
		/* KmPoint */
		// Dataset.roads();
		// Dataset.kmPoint();
		
		/* Infractions */
		// Dataset.descriptionInfraction();
		// Dataset.infractions(100000); //
		
		/** Datasets to sql database **/
		Connection connection = Database.ConnectDB();
		// Database.addDatetime(connection);
		// Database.addDriver(connection);
		// Database.addKmPoint(connection);
		// Database.vehicle(connection);
		// Database.infraction(connection);
		/* Database.test(connection); */
	}
}
