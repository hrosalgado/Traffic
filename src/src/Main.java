import java.sql.Connection;

public class Main{
	// Main
	public static void main(String[] args){
		// Take the initial time
		long start = System.currentTimeMillis();
		float time;
				
		/** Create datasets **/
		/* Drivers */
		Dataset.drivers(100000);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After file drivers - Time in seconds: " + time);
		
		/* Datetime */
		Dataset.checkDatetime(2000, 2016);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After file datetime - Time in seconds: " + time);
		
		/* Vehicles */
		Dataset.vehicles(100000);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After file vehicles - Time in seconds: " + time);
		
		/* KmPoint */
		// Dataset.roads();
		Dataset.kmPoint();
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After file km - Time in seconds: " + time);
		
		/* Infractions */
		Dataset.descriptionInfraction();
		// Dataset.infractions(10);
		
		/** Datasets to sql database **/
		Connection connection = Database.ConnectDB();
		
		Database.addDatetime(connection);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After insert datetime - Time in seconds: " + time);
		
		Database.addDriver(connection);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After insert drivers - Time in seconds: " + time);
		
		Database.addKmPoint(connection);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After insert kms - Time in seconds: " + time);
		
		Database.vehicle(connection);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After insert vehicles - Time in seconds: " + time);
		
		Database.infraction(connection, 100000);
		
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("After insert infractions - Time in seconds: " + time);
		 
		// Take the last time
		time = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("Time in seconds: " + time);
	}
}
