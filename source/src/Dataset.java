import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Dataset{
	// Create a csv file "Drivers"
	public static void drivers(int amount){
		boolean fileExists = new File("drivers.csv").exists();
		
		try{
			// Create a csv file
			CsvWriter file = new CsvWriter(new FileWriter("drivers.csv", true), ',');
			
			int id = 0;
			
			// If file doesn't exist, write header line
			if(!fileExists){
				file.write("id");
				file.write("age");
				file.write("sex");
				file.write("experience");
				file.write("previous_infractions");
				file.write("illness");
				
				file.endRecord();
			}else{
				CsvReader readDrivers = new CsvReader("drivers.csv");
				
				int countDrivers = 0;
				while(readDrivers.readRecord()){
					countDrivers++;
				}
				
				id = countDrivers - 1;
			}
			
			// Else assume that file has a correct header line and write new records
			Random rand = new Random();
			
			for(int i = id; i < amount + id; i++){
				// id
				file.write("" + i);
				
				// age
				int randAge = rand.nextInt((85 - 18) + 1) + 18;
				file.write("" + randAge);
				
				// sex (0: male, 1: female)
				int randSex = rand.nextInt((1 - 0) + 1);
				file.write("" + randSex);
				
				// experience
				int randExperience = rand.nextInt((67 - 0) + 1);
				while(randExperience > randAge - 18){
					randExperience = rand.nextInt((67 - 0) + 1);
				}
				file.write("" + randExperience);
				
				// previous infractions
				int randPreviousInfractions = rand.nextInt((10 - 0) + 1);
				file.write("" + randPreviousInfractions);
				
				// illness
				float randIllness = rand.nextFloat();
				if(randIllness > 0.80){
					file.write("" + 1);
				}else{
					file.write("" + 0);
				}
				
				file.endRecord();
			}
			
			// Close file
			file.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create a csv file "Datetime"
	public static void datetime(int start, int end){
		// Dicts
		String [] weather = {"sunny", "rainy", "cloudy", "foggy"};
		
		boolean fileExists = new File("datetime.csv").exists();
		
		try{
			// Delete file if exists
			if(fileExists){
				File f = new File("datetime.csv");
				f.delete();
			}
			
			// Create a csv file
			CsvWriter file = new CsvWriter(new FileWriter("datetime.csv", true), ',');
			
			// Write header line
			file.write("id");
			file.write("day");
			file.write("month");
			file.write("year");
			file.write("hour");
			file.write("season");
			file.write("weather");
			file.write("weekend");
			file.write("holiday");
			
			file.endRecord();
			
			// Write records
			Random rand = new Random();
			
			int id = 0;
			int contDays = 1;
			int maxDays = 0;
			for(int year = start; year <= end; year++){
				for(int month = 1; month <= 12; month++){
					switch(month){
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
							maxDays = 31; 
							break;
						case 4:
						case 6:
						case 9:
						case 11:
							maxDays = 30;
							break;
						case 2:
							maxDays = 28;
							break;
					}
					
					for(int day = 1; day <= maxDays; day++){					
						for(int hour = 0; hour < 24; hour++){
							// Id
							file.write("" + id);
							
							// Day
							file.write("" + day);
							
							// Month
							file.write("" + month);
							
							// Year
							file.write("" + year);
							
							// Hour
							file.write("" + hour);
							
							// Season
							switch(month){
								case 1:
								case 2:
								case 3:
									file.write("winter");
									break;
								case 4:
								case 5:
								case 6:
									file.write("spring");
									break;
								case 7:
								case 8:
								case 9:
									file.write("summer");
									break;
								case 10:
								case 11:
								case 12:
									file.write("autumn");
									break;
							}
							
							// Weather
							int randWeather = rand.nextInt((3 - 0) + 1);
							file.write(weather[randWeather]);
							
							// Weekend
							if(contDays == 6 || contDays == 7){
								file.write("" + 1);
							}else{
								file.write("" + 0);
							}
							
							// Holiday
							if(day == 1 && month == 1 || day == 6 && month == 1 || day == 15 && month == 8 || 
								day == 12 && month == 10 || day == 1 && month == 11 || day == 6 && month == 12 ||
								day == 8 && month == 12 || day == 25 && month == 12){
								file.write("" + 1);
							}else{
								file.write("" + 0);
							}
							
							// Special season
							// ¿?
							
							file.endRecord();
							
							id++;
						}
						if(contDays == 7){
							contDays = 1;
						}else{
							contDays++;
						}
					}
				}
			}
			
			// Close file
			file.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create a csv file "Vehicles"
	public static void vehicles(int amount){
		// Dicts
		String [] carBrands = {"Acura", "Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW", "Bugatti", "Cadillac", "Chevrolet", "Chrysler", "Citroen",
							"Dodge", "Ferrari", "Fiat", "Ford", "Honda", "Hyunday", "Infiniti", "Jaguar", "Jeep", "KIA", "Koenigsegg", "Lamborgini",
							"Land Rover", "Lexus", "Maserati", "Mazda", "McLaren", "Mercedes-Benz", "Mini", "Mitsubishi", "Nissan", "Opel", "Pagani",
							"Peugeot", "Porsche", "Renault", "Rolls Royce", "Saab", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen"}; // 44
		
		String [] lorryBrands = {"Iveco", "Pegaso", "Agrale", "Volvo", "Mercedes-Benz"}; // 5
		
		String [] motorbykeBrands = {"Aprilia", "Derbi", "Ducati", "Harley Davidson", "Kawasaki", "KTM", "Vespa", "Yamaha"}; // 8
		
		String [] vanBrands = {"Citroen", "Dacia", "Fiat", "Ford", "Iveco", "Mercedes-Benz", "Nissan", "Opel", "Peugeot", "Renault", "Seat", "Toyota",
								"Volkswagen"}; // 13
		
		String [] tractorBrands = {"John Deere", "Fendt", "Deutz", "Massey Ferguson", "New Holland", "Case", "Valtra", "Claas", "Ford", "Lanz Bulldog"}; // 10
		
		String [] withoutCarnetBrands = {"Aixam", "Ligier", "Chatenet", "Microcar"}; // 4
		
		String [] types = {"Car", "Lorry", "Motorbyke", "Van", "Tractor", "Without_carnet"};
		
		boolean fileExists = new File("vehicles.csv").exists();
		
		try{
			// Create a csv file
			CsvWriter file = new CsvWriter(new FileWriter("vehicles.csv", true), ',');
			
			int id = 0;
			
			// If file doesn't exist, write header line
			if(!fileExists){
				file.write("id");
				file.write("type");
				file.write("brand");
				file.write("car_spaces");
				file.write("passengers");
				file.write("antiquity"); // years
				file.write("drive_permission");
				file.write("electric");
				
				file.endRecord();
			}else{
				CsvReader readVehicles = new CsvReader("vehicles.csv");
				
				int countVehicles = 0;
				while(readVehicles.readRecord()){
					countVehicles++;
				}
				
				id = countVehicles - 1;
			}
			
			// Else assume that file has a correct header line and write new records
			Random rand = new Random();
			
			for(int i = id; i < amount + id; i++){
				// id
				file.write("" + i);
				
				// type
				int randType = rand.nextInt(6);
				file.write(types[randType]);
				
				switch(types[randType]){
					case "Car":
						int randCarBrand = rand.nextInt(44);
						file.write(carBrands[randCarBrand]);
						break;
					case "Lorry":
						int randLorryBrand = rand.nextInt(5);
						file.write(lorryBrands[randLorryBrand]);
						break;
					case "Motorbyke":
						int randMotorbykeBrand = rand.nextInt(8);
						file.write(motorbykeBrands[randMotorbykeBrand]);
						break;
					case "Van":
						int randVanBrand = rand.nextInt(13);
						file.write(vanBrands[randVanBrand]);
						break;
					case "Tractor":
						int randTractorBrand = rand.nextInt(10);
						file.write(tractorBrands[randTractorBrand]);
						break;
					case "Without_carnet":
						int randWithoutCarnetBrand = rand.nextInt(4);
						file.write(withoutCarnetBrands[randWithoutCarnetBrand]);
						break;
				}
				
				// spaces & passengers
				switch(types[randType]){
					case "Car":
						int randCarSpaces = rand.nextInt((5 - 2) + 1) + 2;
						file.write("" + randCarSpaces);
						
						int randCarPassengers = rand.nextInt((randCarSpaces - 1) + 1) + 1;
						file.write("" + randCarPassengers);
						
						break;
					case "Lorry":
						file.write("3");
						
						int randLorryPassengers = rand.nextInt((3 - 1) + 1) + 1;
						file.write("" + randLorryPassengers);
						
						break;
					case "Motorbyke":
						int randMotorbykeSpaces = rand.nextInt((2 - 1) + 1) + 1;
						file.write("" + randMotorbykeSpaces);
						
						int randMotorbykePassengers = rand.nextInt((randMotorbykeSpaces - 1) + 1) + 1;
						file.write("" + randMotorbykePassengers);
						
						break;
					case "Van":
						int randVanSpaces = rand.nextInt((8 - 5) + 1) + 5;
						file.write("" + randVanSpaces);
						
						int randVanPassengers = rand.nextInt((randVanSpaces - 5) + 1) + 5;
						file.write("" + randVanPassengers);
						
						break;
					case "Tractor":
						int randTractorSpaces = rand.nextInt((2 - 1) + 1) + 1;
						file.write("" + randTractorSpaces);
						
						int randTractorPassengers = rand.nextInt((randTractorSpaces - 1) + 1) + 1;
						file.write("" + randTractorPassengers);
						
						break;
					case "Without_carnet":
						int randWithoutCarnetSpaces = rand.nextInt((2 - 1) + 1) + 1;
						file.write("" + randWithoutCarnetSpaces);
						
						int randWithoutCarnetPassengers = rand.nextInt((randWithoutCarnetSpaces - 1) + 1) + 1;
						file.write("" + randWithoutCarnetPassengers);
						
						break;
				}
				
				// Antiquity
				int randAntiquity = rand.nextInt(31);
				file.write("" + randAntiquity);
				
				// Drive_permission (true = need carnet, false = not need carnet)
				if(types[randType] == "Without_carnet"){
					file.write("" + 0);
				}else{
					file.write("" + 1);
				}
				
				// electric
				float randElectric = rand.nextFloat();
				if(randElectric > 0.9){
					file.write("" + 1);
				}else{
					file.write("" + 0);
				}
				
				file.endRecord();
			}
			
			
			// Close file
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Create a csv file "roads"
	public static void roads(){
		// Roads
		try{
			// Storage roads
			String [][] dataRoads = new String[413][4];
			
			CsvReader roadsAux = new CsvReader("roadsAux.csv");
			
			roadsAux.readHeaders();
			
			int i = 0;
			while(roadsAux.readRecord()){
				String name = roadsAux.get("name");
				String start = roadsAux.get("start");
				String end = roadsAux.get("end");
				
				String auxType = name.substring(0, name.indexOf('-'));
				
				String type = "";
				switch(auxType){
					case "A":
						type = "highway";
						break;
					case "AP":
						type = "motorway";
						break;
					case "N":
						type = "national";
						break;
					default:
						type = "highway";
						break;
				}
				
				start = start.substring(0, start.indexOf('+'));
				end = end.substring(0, end.indexOf('+'));
				
				dataRoads[i][0] = name;
				dataRoads[i][1] = type;
				dataRoads[i][2] = start;
				dataRoads[i][3] = end;
				
				i++;
			}
			
			roadsAux.close();
			
			// Create a csv road
			CsvWriter roads = new CsvWriter("roads.csv");
			
			// Header
			roads.write("name");
			roads.write("type");
			roads.write("start");
			roads.write("end");
			
			roads.endRecord();
			
			// Record
			for(i = 0; i < dataRoads.length - 1; i++){
				for(int j = 0; j < dataRoads[i].length; j++){
					roads.write(dataRoads[i][j]);
				}
				roads.endRecord();
			}
			
			roads.close();
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}catch(IOException e2){
			e2.printStackTrace();
		}
	}
	
	// Create a csv file "km_point"
	public static void kmPoint(){
		boolean fileExists = new File("km.csv").exists();
		
		Random rand = new Random();
		
		try{
			// Create a csv
			CsvWriter km = new CsvWriter("km.csv");
			
			// If file doesn't exist, write header line
			if(!fileExists){
				km.write("id");
				km.write("start");
				km.write("end");
				km.write("road_name");
				km.write("road_type");
				km.write("black_point");
				km.write("signposting");
				km.write("radar");
				
				km.endRecord();
			}
			
			// Record
			CsvReader roads = new CsvReader("roads.csv");
			
			roads.readHeaders();
			
			int id = 0;
			while(roads.readRecord()){
				int start = Integer.parseInt(roads.get("start"));
				int end = Integer.parseInt(roads.get("end"));
				
				for(int i = start; i <= end; i++){
					//id
					km.write("" + id);
					
					// start
					km.write("" + i);
					
					// end
					km.write("" + (i + 1));
					
					// name
					km.write(roads.get("name"));
					
					// type
					km.write(roads.get("type"));
					
					float randBlackPoint = rand.nextFloat();
					if(randBlackPoint > 0.9){
						km.write("" + 1);
					}else{
						km.write("" + 0);
					}
					
					float randSignposting = rand.nextFloat();
					if(randSignposting > 0.4){
						km.write("" + 1);
					}else{
						km.write("" + 0);
					}
					
					float randRadar = rand.nextFloat();
					if(randRadar > 0.7){
						km.write("" + 1);
					}else{
						km.write("" + 0);
					}
					
					km.endRecord();
					
					id++;
				}
			}
			
			km.close();
		}catch(Exception e){
			// TODO: handle exception
		}
	}
	
	// Create a csv infractions
	public static void infractions(int amount){
		Random rand = new Random();
		
		boolean fileExists = new File("infractions.csv").exists();
		
		try{
			// Create a csv file infractions
			CsvWriter infractions = new CsvWriter(new FileWriter("infractions.csv", true), ',');
			
			int id = 0;
			
			// Header
			if(!fileExists){
				id = 0;
				
				infractions.write("infraction_id");
				infractions.write("driver_id");
				infractions.write("datetime_id");
				infractions.write("vehicle_id");
				infractions.write("km_id");
				infractions.write("type");
				infractions.write("description");
				infractions.write("penalty");
				
				infractions.endRecord();
			}else{
				CsvReader readInfractions = new CsvReader("infractions.csv");
				
				int countInfractions = 0;
				while(readInfractions.readRecord()){
					countInfractions++;
				}
				
				id = countInfractions - 1;
			}
			
			// Record
			// Driver
			CsvReader driver = new CsvReader("drivers.csv");
			driver.readHeaders();
			
			int countDriver = 0;
			while(driver.readRecord()){
				countDriver++;
			}
			
			// Datetime
			CsvReader datetime = new CsvReader("datetime.csv");
			datetime.readHeaders();
			
			int countDatetime = 0;
			while(datetime.readRecord()){
				countDatetime++;
			}
			
			// Vehicle
			CsvReader vehicle = new CsvReader("vehicles.csv");
			vehicle.readHeaders();
			
			int countVehicle = 0;
			while(vehicle.readRecord()){
				countVehicle++;
			}
			
			// Km
			CsvReader km = new CsvReader("km.csv");
			km.readHeaders();
			
			int countKm = 0;
			while(km.readRecord()){
				countKm++;
			}
			
			// Description
			CsvReader description = new CsvReader("descs_type_penalty.csv");
			description.readHeaders();
			
			description.get(12);
			
			String [][] descriptions = new String[46][3];
			
			int i = 0;
			while(description.readRecord()){
				descriptions[i][0] = description.get("type");
				descriptions[i][1] = description.get("description");
				descriptions[i][2] = description.get("penalty");
				i++;
			}
				
			for(int k = id; k < amount + id; k++){
				// infraction_id
				infractions.write("" + k);
				
				// driver_id
				int driverId = rand.nextInt(countDriver);
				infractions.write("" + driverId);
				
				// datetime_id
				int datetimeId = rand.nextInt(countDatetime);
				infractions.write("" + datetimeId);
				
				// vehicle_id
				int vehicleId = rand.nextInt(countVehicle);
				infractions.write("" + vehicleId);
				
				// km_id
				int kmId = rand.nextInt(countKm);
				infractions.write("" + kmId);
				
				// description, type & penalty
				int randDescTypePenalty = rand.nextInt(45);
				infractions.write(descriptions[randDescTypePenalty][0]);
				infractions.write(descriptions[randDescTypePenalty][1]);
				infractions.write(descriptions[randDescTypePenalty][2]);
				
				infractions.endRecord();
			}
			
			infractions.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Create a csv file: descriptions
	public static void descriptionInfraction(){
		// To store descriptions
		HashMap<String, String> map = new HashMap<String, String>();
		
		try{
			// Read descriptions for the dataset
			CsvReader description = new CsvReader("descriptions.csv");
			
			description.readHeaders();
			
			while(description.readRecord()){
				map.put(description.get("15"), "0");
				map.put(description.get("17"), "0");
			}
			
			description.close();
			
			// Save the correct descriptions
			CsvWriter descs = new CsvWriter("descs.csv");
			
			descs.write("description");
			descs.endRecord();
			
			for(Map.Entry<String, String> entry : map.entrySet()){
				descs.write(entry.getKey());
				descs.endRecord();
			}
			
			descs.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}