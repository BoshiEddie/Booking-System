package BoshiPan;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class PassengerManager {
	private final ArrayList<Passenger> passengerList;
	
	public PassengerManager() {
		this.passengerList = new ArrayList<>();
	}
	
	public boolean addPassenger(Passenger p) {
		if(p == null) {
			throw new IllegalArgumentException("Passenger argument is null");
		}	
		if(passengerList.contains(p)) {
			return false;
		}
		
		passengerList.add(p);
		try {
			saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
				
	}
	
	public void deletePassenger(Passenger p) {
		if(p == null) {
			throw new IllegalArgumentException("Passenger argument is null");
		}
		
		this.passengerList.remove(p);
	}
	
	public void deletePassengerById(String id) {
		
		if(id == null) {
			throw new IllegalArgumentException("Passenger ID argument is null");
		}
		
		for(Passenger p : this.passengerList) {
			if(p.getId().equals(id)) {
				this.passengerList.remove(p);
			}
		}
		
	}
	
	public List<Passenger> getAllPassengers(){
		return this.passengerList;
	}
	
	public Passenger getPassengerById(String ID) {
		for(Passenger p : this.passengerList) {
			if(p.getId().equals(ID)) {
				return p;
			}
		}
		return null;
	}
	
	public List<Passenger>getPassengersByFilter(IFilter filter){
		if(filter == null) {
			throw new IllegalArgumentException("Passenger argument is null");
		}
		
		List<Passenger> list = new ArrayList<Passenger>();
		
		for(Passenger p : this.passengerList) {
			if(filter.matches(p) == true) {
				list.add(p);
			}
		}
		return list;
	}
	
	public void loadFromFile(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			
			sc.useDelimiter("[,\r\n]+");
			while(sc.hasNext()) {
				String name = sc.next();
				String id = sc.next();
				String email = sc.next();
				String phone = sc.next();
				double latitude = sc.nextDouble();
				double longitude = sc.nextDouble();
				
				GPSLocation gps = new GPSLocation(latitude,longitude);
				
				passengerList.add(new Passenger(name,id,email,phone,gps));
			}
			sc.close();
			
			
			}catch(IOException e) {
				System.out.println("Exception thrown. " + e);
			}
	}
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for(Passenger p : this.passengerList) {
			strBuilder.append(p.toString());
		}
		return strBuilder.toString();
	}
	
	public List<Passenger> getCopyAllPassengers(){
		List<Passenger> clonedList = new ArrayList<>();
		
		for(Passenger passenger : passengerList) {
			clonedList.add(new Passenger(passenger));
		}
		
		return clonedList;
	}
	
	public Passenger getCopyPassengerById(String id) {
		for(Passenger p : this.passengerList) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public List<Passenger> getCopyOfPassengersByFilter(IFilter filter){
		
		List<Passenger> list = new ArrayList<Passenger>();
		
		for(Passenger p : passengerList) {
			if(filter.matches(p) == true) {
				list.add(new Passenger(p));
			}
		}
		return list;
	}
	
	public void saveToFile() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("passengers.txt"));
		for(Passenger p : this.passengerList) {
			String name = p.getName();
			String id = p.getId();
			String email = p.getEmail();
			String phone = p.getPhone();
			double latitude = p.getLocation().getLatitude();
			double longitude = p.getLocation().getLongtitude();
			
			String pinfo = name+","+id+","+email+","+phone+","+latitude +"," + longitude +"\n";
			writer.write(pinfo);
					
		}
		writer.close();
	}
}
