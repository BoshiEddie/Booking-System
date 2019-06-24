package BoshiPan;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BookingManager {
	private final ArrayList<Booking> bookinglist;

	public BookingManager() {
		this.bookinglist = new ArrayList<>();
	}
	
	public boolean addBooking(Booking b) {
		if(b == null) {
			throw new IllegalArgumentException("Booking argument is null");
		}
		if(bookinglist.contains(b)) {
			return false;
		}
		this.bookinglist.add(b);
		try {
			sendEmail(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void deleteBooking(Booking b) {
		if(b == null) {
			throw new IllegalArgumentException("Booking argument is null");
		}
		this.bookinglist.remove(b);
	}
	
	public void deleteBookingById(String id) {
		if(id == null) {
			throw new IllegalArgumentException("Booking ID argument is null");
		}
		
		for(Booking b: this.bookinglist) {
			if(b.getBookingID().equals(id)) {
				this.bookinglist.remove(b);
			}
		}
	}
	
	
	public Booking getBookingById(String id) {
		for(Booking b : this.bookinglist) {
			if(b.getBookingID().equals(id)) {
				return b;
			}
		}
		return null;
	}
	
	public List<Booking> getBookingByFilter(IFilter filter){
		
		if(filter == null) {
			throw new IllegalArgumentException("Filter argument is null");
		}
		List<Booking> list = new ArrayList<Booking>();
		
		for(Booking b : this.bookinglist) {
			if(filter.matches(b) == true) {
				list.add(b);
			}
		}
		
		return list;
		
	}
	
	public ArrayList<Booking> getBookingList(){
		return this.bookinglist;
	}
	
	
	public void loadFromFile(String filename,List<Passenger> passenger , List<Vehicle> vehicle) {
		if (filename == null) {
			throw new IllegalArgumentException("file argument is a null");
		}
		
		try {
			
			Scanner sc = new Scanner(new File(filename));
			
			while(sc.hasNext()) {
				sc.useDelimiter("[,\r\n]+");
				String bookingID = sc.next();
				String passengerid = sc.next();
				String passengername = sc.next();
				String registration = sc.next();
				String type = sc.next();
				String bookingdate = sc.next();
				double slatitude = sc.nextDouble();
				double slongitude = sc.nextDouble();
				double elatitude = sc.nextDouble();
				double elongitude = sc.nextDouble();
				
				GPSLocation start = new GPSLocation(slatitude,slongitude);
				GPSLocation end = new GPSLocation(elatitude,elongitude);
				Passenger psgr = new Passenger();
				for(Passenger p: passenger) {
					if(p.getId().equals(passengerid)) {
						psgr = p;
					}
				}
				Vehicle vc;
				
				if(type.equals("Car") || type.equals("Four")){
					vc = new Car();
					}
				else {
					vc = new Truck();
				}
				
				for(Vehicle v : vehicle) {
					if(v.getRegistration_number().equals(registration)) {
						vc = v;
					}
				}
				
				this.bookinglist.add(new Booking(bookingID,psgr,vc,LocalDate.parse(bookingdate),start,end));
			}
			sc.close();
		}catch(IOException e) {
			System.out.println("Exception throws: " + e);
		}
	}
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for(Booking b : this.bookinglist) {
			strBuilder.append(b.toString());
		}
		return strBuilder.toString();
	}
	
	public void saveToFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt"));
		writer.write("");
		writer.flush();
		for(Booking b : this.bookinglist) {
			String bookingID = b.getBookingID();
			String passengerid = b.getPassenger().getId();
			String passengername = b.getPassenger().getName();
			String registration = b.getVehicle().getRegistration_number();
			String type = b.getVehicle().getType().toString();
			String bookingdate = b.getDate().toString();
			double slatitude = b.getStartLocation().getLatitude();
			double slongitude = b.getStartLocation().getLongtitude();
			double elatitude = b.getEndLocation().getLatitude();
			double elongitude = b.getEndLocation().getLongtitude();
			
			String info = bookingID + "," + passengerid + "," + passengername + "," + registration +"," + type + "," + bookingdate + "," + slatitude + "," + slongitude + "," + elatitude + "," + elongitude + "\n";
			writer.write(info);
		}
		
		writer.close();
	}
	
	public void sendEmail(Booking b) throws IOException{
		try
        {
            File output = new File("email.txt");
            Scanner reader = new Scanner(output);
            BufferedWriter writer = new BufferedWriter(new FileWriter(output,true));
            BufferedWriter cleaner = new BufferedWriter(new FileWriter(output,false));
            
            cleaner.write("");
            cleaner.flush();
            cleaner.close();
            
            writer.write("Email: "+b.getPassenger().getEmail());
            writer.flush();
            writer.newLine();
            writer.write("Title: Booking on "+b.getDate().toString());
            writer.flush();
            writer.newLine();
            writer.write("Hello "+b.getPassenger().getName());
            writer.flush();
            writer.newLine();
            writer.write("You have made a booking, the details are as follows:");
            writer.flush();
            writer.newLine();
            writer.write("Date: "+b.getDate().toString());
            writer.flush();
            writer.newLine();
            writer.write("Cost: €"+b.getCostCalculate());
            writer.flush();
            writer.newLine();
            writer.write("Starting point: "+b.getStartLocation().getLatitude()+","+b.getStartLocation().getLongtitude());
            writer.flush();
            writer.newLine();
            writer.write("Destination point: "+b.getEndLocation().getLatitude()+","+b.getEndLocation().getLongtitude());
            writer.flush();
            writer.newLine();
            writer.write("			Name ");
            writer.flush();
            writer.newLine();
            writer.write("Your Info: "+b.getPassenger().getName());
            writer.flush();
            writer.newLine();
            writer.write("	      Make	Model	Registration Number");
            writer.flush();
            writer.newLine();
            writer.write("Your vehicle: "+b.getVehicle().getMake() + b.getVehicle().getModel() + b.getVehicle().getRegistration_number());
            writer.flush();
            writer.newLine();
            writer.write("Hope you enjoy our service.");
            writer.flush();
            writer.newLine();
            writer.write("Yours Faithfully");
            writer.flush();
            writer.newLine();
            writer.write("Email Bot");
            writer.flush();
            writer.close();
        
        }catch(Exception e)
        {
            if(e instanceof IOException)
            {
                System.out.println("IOException caught!");
            }else if(e instanceof FileNotFoundException)
            {
                System.out.println("ERROR File missing!");
            }else
            {
                System.out.println("ERROR!  "+e);
            }
        }
    }

		
}
