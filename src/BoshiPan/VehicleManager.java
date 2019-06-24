package BoshiPan;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class VehicleManager {

	private final ArrayList<Vehicle> vehicleList;
	
	public VehicleManager() {
		this.vehicleList = new ArrayList<>();
	}
	
	public boolean addVehicle(Vehicle v) {
		if(v == null) {
			throw new IllegalArgumentException("Vehicle argument is null");
		}
		for(Vehicle x : this.vehicleList) {
			if(x.getRegistration_number().equals(v.getRegistration_number())) {
				return false;
			}
		}
		vehicleList.add(v);
		try {
			this.saveTofile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public List<Vehicle> getVehicles(){
		return vehicleList;
	}
	
	public Vehicle getVehicleByRegistration(String registration) {
		for(Vehicle v : this.vehicleList) {
			if(v.getRegistration_number().equals(registration)) {
				return v;
			}
		}
		return null;
	}
	
	public List<Vehicle> getVehicleByFilter(IFilter filter){
		if(filter == null) {
			throw new IllegalArgumentException("Filter argument is null");
		}
		
		List<Vehicle> list = new ArrayList<Vehicle>();
		
		for(Vehicle v : this.vehicleList) {
			if(filter.matches(v) == true) {
				list.add(v);
			}
		}
		
		return list;
		
	}
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for(Vehicle v : this.vehicleList) {
			strBuilder.append(v.toString());
		}
		return strBuilder.toString();
	}
	
	public void loadFormFile(String filename) {
		if(filename == null) {
			throw new IllegalArgumentException("filename argument is null");
		}
		
		try {
			Scanner sc = new Scanner(new File(filename));
			sc.useDelimiter("[,\r\n]+");
			while(sc.hasNext()) {
				String type = sc.next();
				String make = sc.next();
				String model = sc.next();
				String registration = sc.next();
				double miles_per_mile = sc.nextDouble();
				double mileage = sc.nextDouble();
				String lastServiceDate = sc.next();
				double latitude = sc.nextDouble();
				double longitude = sc.nextDouble();
				int number_of_seat = sc.nextInt();
				int identify = sc.nextInt();
				GPSLocation depot = new GPSLocation(latitude,longitude);
				LocalDate date = LocalDate.parse(lastServiceDate);
				switch(type) {
				case "Car":
					this.vehicleList.add(new Car(make,model,registration,miles_per_mile,mileage,date,depot,number_of_seat,VehicleType.Car,identify));
				case "Four":
					this.vehicleList.add(new Car(make,model,registration,miles_per_mile,mileage,date,depot,number_of_seat,VehicleType.Four,identify));
				case "Van":
					this.vehicleList.add(new Truck(make,model,registration,miles_per_mile,mileage,date,depot,number_of_seat,VehicleType.Van,identify));
				case "Truck":
					this.vehicleList.add(new Truck(make,model,registration,miles_per_mile,mileage,date,depot,number_of_seat,VehicleType.Truck,identify));
				}
				
			}
			
			sc.close();
		}catch(IOException e) {
			System.out.println("Exception throw" + e);
		}
	}
	
	public void saveTofile() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("vehicles.txt"));
		for(Vehicle v : this.vehicleList) {
			String type = v.getType().toString();
			String make = v.getMake();
			String model = v.getModel();
			String registration = v.getRegistration_number();
			double costPerMile = v.getCost_per_mile();
			double mileage = v.getMileage();
			String date = v.getLastServiceDate().toString();
			double latitude = v.getLocation().getLatitude();
			double longitude = v.getLocation().getLongtitude();
			int number_of_seat = v.getNumber_of_seat();
			int identify = 0;
			
			if(v instanceof Car) {
				Car c = (Car) v;
				identify = c.getBackSeatCapacity();
			}else {
				Truck t =(Truck) v;
				identify = t.getLoadSpace();
			}
			
			String info = type + "," + make + "," + model + "," + registration + "," + costPerMile + "," + mileage + "," + date + "," + latitude + "," + longitude + "," + number_of_seat + "," + identify + "\n"; 
			writer.write(info);
		}
		writer.close();
	}
	
	
}
