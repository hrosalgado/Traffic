import java.sql.Connection;

public class Main{
	// Main
	public static void main(String[] args){
		/** Create datasets **/
		/* Drivers */
		// Dataset.drivers(40);
		
		/* Datetime */
		// Dataset.checkDatetime(2000, 2016);
		
		/* Vehicles */
		// Dataset.vehicles(20);
		
		/* KmPoint */
		// Dataset.roads();
		 Dataset.kmPoint();
		
		/* Infractions */
		// Dataset.descriptionInfraction();
		// Dataset.infractions(10);
		
		/** Datasets to sql database **/
		// Connection connection = Database.ConnectDB();
		// Database.addDatetime(connection);
		// Database.addDriver(connection);
		// Database.addKmPoint(connection);
		// Database.vehicle(connection);
		// Database.infraction(connection);
		/* Database.test(connection); */
	}
}
