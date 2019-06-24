package BoshiPan;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
	static Scanner sc = new Scanner(System.in);
	static PassengerManager pmgr = new PassengerManager();
	static VehicleManager vmgr = new VehicleManager();
	static BookingManager bmgr = new BookingManager();
	public static void main(String[] args) throws IOException {
		
		boolean run = true;
		while(run) {
			System.out.println("Please choose the service you want");
			System.out.println("1.Overview passengers");
			System.out.println("2.Overview vehicles");
			System.out.println("3.Overivew bookings");
			
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
				case "1":
					passengers();
					break;
				case "2":
					carsDetail();
					break;
				case "3":
					bookingDetails();
					break;
				case "0":
					run = false;
					break;
				default:
					System.out.println("Invalid input, try again:");
					break;
			}
		}
	}
	
	public static void passengers() throws IOException {
		boolean open = true;
		while(open) {
			System.out.println("1.Edit");
			System.out.println("2.Delete");
			System.out.println("3.Display");
			System.out.println("4.Add");
			
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
			case "1":
				edit();
				break;
			case "2":
				delete();
				break;
			case "3":
				display();
				break;
			case "4":
				break;
			case "0":
				open = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
	}
	
	public static void edit() throws IOException {
		pmgr.loadFromFile("passengers.txt");
		System.out.println("Passenger ID:");
		String passengerid = sc.next();
		Passenger e = pmgr.getPassengerById(passengerid);
		while(e == null) {
			System.out.println("Passenger not Found, Try again");
			passengerid = sc.next();
			e = pmgr.getPassengerById(passengerid);
		}
		
		boolean run =true;
		while(run) {
			System.out.println("1.Edit Telephone");
			System.out.println("2.Edie Location");
			
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
			case "1":
				editphone(e);
				pmgr.saveToFile();
				break;
			case "2":
				editLocation(e);
				pmgr.saveToFile();
				break;
			case "0":
				run = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
		
	}
	public static void editphone(Passenger e) {
		System.out.println("Inputnew nubmer:");
		String nubmer = sc.next();
		e.setPhone(nubmer);
		System.out.println("Successful");
	}
	public static void editLocation(Passenger e) {
		System.out.println("Input new latitude");
		double latitude = sc.nextDouble();
		System.out.println("Input new longitude");
		double longitude = sc.nextDouble();
		GPSLocation n = new GPSLocation(latitude,longitude);
		e.setLocation(n);
		System.out.println("Successful");
	}
	
	public static void delete() {

		pmgr.loadFromFile("passengers.txt");
		System.out.println("Passenger ID:");
		String passengerid = sc.next();
		Passenger e = pmgr.getPassengerById(passengerid);
		while(e == null) {
			System.out.println("Passenger not Found, Try again");
			passengerid = sc.next();
			e = pmgr.getPassengerById(passengerid);
		}
		
		pmgr.deletePassengerById(passengerid);
	}
	
	public static void display() {
		pmgr.loadFromFile("passenger.txt");
		List<Passenger> list = 	pmgr.getAllPassengers();
		for(Passenger p : list) {
			System.out.print(p.getId() + " ");
			System.out.print(p.getName() + " ");
			System.out.print(p.getPhone() + " ");
			System.out.print(p.getLocation() + " ");
			System.out.println();
		}
	}
	public static void add() throws IOException {
		pmgr.loadFromFile("passenger.txt");
		List<Passenger> list = pmgr.getAllPassengers();
		ArrayList<String> ids = new ArrayList<>();
		for(Passenger p : list) {
			ids.add(p.getId());
		}
		System.out.println("Name");
		String name = sc.next();
		System.out.println("Phone nubmer");
		String nubmer = sc.next();
		System.out.println("Email");
		String email = sc.next();
		System.out.println("Latitude");
		double latitude = sc.nextDouble();
		System.out.println("Longitude");
		double longitude = sc.nextDouble();
		
		GPSLocation n = new GPSLocation(latitude,longitude);
		
		int ni = 0;
		for(String id : ids) {
			String numbers = id.substring(3);
			int numberc = Integer.parseInt(numbers);
			ni = numberc + 1;
		}
		
		String passengerid = "PSG" + ni;
		
		Passenger add = new Passenger(name,passengerid,email,nubmer,n);
		pmgr.addPassenger(add);
		pmgr.saveToFile();
	}
	
	
	public static void carsDetail() {
		boolean open = true;
		while(open) {
			System.out.println("1.Display By Type wanted");
			System.out.println("2.Display By number of seat");
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
			case "1":
				displayByType();
				break;
			case "2":
				vmgr.loadFormFile("vehicles.txt");
				List<Vehicle> list = vmgr.getVehicles();
				Comparator<Vehicle> ns = new seatComparator();
				Collections.sort(list, ns);
				for(Vehicle v : list) {
					System.out.println(v);
				}
				break;
			case "0":
				open = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
	}
	public static void displayByType() {
		vmgr.loadFormFile("vehicles.txt");
		List<Vehicle> list = vmgr.getVehicles();
		Comparator<Vehicle> rg = new RGcomparator();
		boolean open = true;
		while(open) {
			System.out.println("1.Car");
			System.out.println("2.Four");
			System.out.println("3.Van");
			System.out.println("4.Truck");
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
			case "1":
				List<Vehicle> car =new ArrayList<>();
				for(Vehicle v : list) {
					if(v.getType().equals(VehicleType.Car)) {
						car.add(v);
					}
				}
				Collections.sort(car,rg);
				for(Vehicle v : car) {
					System.out.println(v);
				}
				break;
			case "2":
				List<Vehicle> four =new ArrayList<>();
				for(Vehicle v : list) {
					if(v.getType().equals(VehicleType.Four)) {
						four.add(v);
					}
				}
				Collections.sort(four,rg);
				for(Vehicle v : four) {
					System.out.println(v);
				}
				break;
			case "3":
					List<Vehicle> van =new ArrayList<>();
					for(Vehicle v : list) {
						if(v.getType().equals(VehicleType.Van)) {
							van.add(v);
						}
					}
					Collections.sort(van,rg);
					for(Vehicle v : van) {
						System.out.println(v);
					}
					break;
			case "4":
				List<Vehicle> truck =new ArrayList<>();
				for(Vehicle v : list) {
					if(v.getType().equals(VehicleType.Truck)) {
						truck.add(v);
					}
				}
				Collections.sort(truck,rg);
				for(Vehicle v : truck) {
					System.out.println(v);
				}
				break;
			case "0":
				open = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
	}
	public static void bookingDetails() throws IOException {
		boolean open = true;
		while(open) {
			System.out.println("1.Edit");
			System.out.println("2.Delete");
			System.out.println("3.Display");
			System.out.println("4.Add");
			
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
			case "1":
				editb();
				break;
			case "2":
				deleteb();
				break;
			case "3":
				displayb();
				break;
			case "4":
				addb();
				
				break;
			case "0":
				open = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
	}
	
	public static void addb() {
		pmgr.loadFromFile("passengers.txt");
		List<Passenger> list1 = pmgr.getAllPassengers();
		vmgr.loadFormFile("vehicles.txt");
		
		List<Vehicle> list2 = vmgr.getVehicles();
		bmgr.loadFromFile("bookings.txt", list1, list2);
		
		System.out.println("ID");
		String bookingID = sc.next();
		System.out.println("Passenger ID");
		String passengerid = sc.next();
		System.out.println("Passenger name");
		System.out.println("Registration number");
		String registration = sc.next();
		System.out.println("Type");
		String type = sc.next();
		System.out.println("date(yyyy-mm-dd)");
		String bookingdate = sc.next();
		System.out.println("Start latitude");
		double slatitude = sc.nextDouble();
		System.out.println("Start longitude");
		double slongitude = sc.nextDouble();
		System.out.println("End latitude");
		double elatitude = sc.nextDouble();
		System.out.println("End longitude");
		double elongitude = sc.nextDouble();
		
		GPSLocation start = new GPSLocation(slatitude,slongitude);
		GPSLocation end = new GPSLocation(elatitude,elongitude);
		Passenger psgr = new Passenger();
		for(Passenger p: list1) {
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
		
		for(Vehicle v : list2) {
			if(v.getRegistration_number().equals(registration)) {
				vc = v;
			}
		}
		
		bmgr.getBookingList().add(new Booking(bookingID,psgr,vc,LocalDate.parse(bookingdate),start,end));
	}
	public static void deleteb() throws IOException {
		pmgr.loadFromFile("passengers.txt");
		List<Passenger> list1 = pmgr.getAllPassengers();
		vmgr.loadFormFile("vehicles.txt");
		
		List<Vehicle> list2 = vmgr.getVehicles();
		bmgr.loadFromFile("bookings.txt", list1, list2);
		
		System.out.println("Booking id");
		String id = sc.next();
		bmgr.deleteBookingById(id);
		bmgr.saveToFile();
	}
	
	public static void editb() throws IOException {
		pmgr.loadFromFile("passengers.txt");
		List<Passenger> list1 = pmgr.getAllPassengers();
		vmgr.loadFormFile("vehicles.txt");
		
		List<Vehicle> list2 = vmgr.getVehicles();
		bmgr.loadFromFile("bookings.txt", list1, list2);
		
		System.out.println("Booking ID:");
		String passengerid = sc.next();
		Booking e = bmgr.getBookingById(passengerid);
		while(e == null) {
			System.out.println("Passenger not Found, Try again");
			passengerid = sc.next();
			e = bmgr.getBookingById(passengerid);
		}
		
		boolean run =true;
		while(run) {
			System.out.println("1.Edit date");
			System.out.println("2.Edit Start Location");
			System.out.println("3.Edit End Location");
			
			System.out.println("0.Quit");
			String choice = sc.next();
			switch(choice) {
			case "1":
				editdate(e);
				bmgr.saveToFile();
				break;
			case "2":
				editsLocation(e);
				bmgr.saveToFile();
				break;
			case "3":
				editeLocation(e);
				bmgr.saveToFile();
			case "0":
				run = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
	}
	
	public static void editdate(Booking e) {
		System.out.println("Year");
		int year = sc.nextInt();
		System.out.println("Month");
		int month = sc.nextInt();
		System.out.println("Day");
		int day = sc.nextInt();
		String newdate;
		if(month < 10) {
			if(day < 10) {
				newdate = String.valueOf(year) + "-0"+String.valueOf(month)+"-0"+String.valueOf(day);
			}else {
				newdate = String.valueOf(year) + "-0"+String.valueOf(month)+"-"+String.valueOf(day);
				
			}
		}else {
			if(day < 10) {
				newdate = String.valueOf(year) + "-0"+String.valueOf(month)+"-0"+String.valueOf(day);
			}else {
				newdate = String.valueOf(year) + "-0"+String.valueOf(month)+"-"+String.valueOf(day);
				
			}
		}
		
		e.setDate(LocalDate.parse(newdate));
	}
	
	public static void editsLocation(Booking e) {
		System.out.println("Latitude");
		double latitude = sc.nextDouble();
		System.out.println("Longitude");
		double longitude = sc.nextDouble();
		GPSLocation startLocation = new GPSLocation(latitude,longitude);
		e.setStartLocation(startLocation);
	}
	
	public static void editeLocation(Booking e) {
		System.out.println("Latitude");
		double latitude = sc.nextDouble();
		System.out.println("Longitude");
		double longitude = sc.nextDouble();
		GPSLocation startLocation = new GPSLocation(latitude,longitude);
		e.setEndLocation(startLocation);
	}
	
	public static void displayb() {
		pmgr.loadFromFile("passengers.txt");
		List<Passenger> list1 = pmgr.getAllPassengers();
		vmgr.loadFormFile("vehicles.txt");
		
		List<Vehicle> list2 = vmgr.getVehicles();
		bmgr.loadFromFile("bookings.txt", list1, list2);
		List<Booking> list = bmgr.getBookingList();
		boolean run =true;
		while(run) {
			System.out.println("1.Display All Bookings");
			System.out.println("2.Display Current");
			System.out.println("3.Display in tiem order");
			System.out.println("4.Average Lenght");
			System.out.println("0.Quit");
			
			String choice = sc.next();
			switch(choice) {
			case "1":
				for(Booking b : list) {
					System.out.print(b.getBookingID() +" ");
					System.out.print(b.getPassenger().getId()+" ");
					System.out.print(b.getPassenger().getName()+" ");
					System.out.print(b.getStartLocation()+" ");
					System.out.print(b.getEndLocation()+" ");
					System.out.println();
				}
				break;
			case "2":
				IFilter future = new BookingFutureFilter();
				List<Booking> fu = getFuture(list,future);
				for(Booking b : fu) {
					System.out.print(b.getBookingID() +" ");
					System.out.print(b.getPassenger().getId()+" ");
					System.out.print(b.getPassenger().getName()+" ");
					System.out.print(b.getStartLocation()+" ");
					System.out.print(b.getEndLocation()+" ");
					System.out.println();
				}
				break;
			case "3":
				ComparatorBookingDate date = new ComparatorBookingDate();
				Collections.sort(list, date);
				for(Booking b : list) {
					System.out.print(b.getBookingID() +" ");
					System.out.print(b.getPassenger().getId()+" ");
					System.out.print(b.getPassenger().getName()+" ");
					System.out.print(b.getStartLocation()+" ");
					System.out.print(b.getEndLocation()+" ");
					System.out.println();
				}
			case "4":
				double average = getAverageLength(list);
				System.out.println(average);
			case "0":
				run = false;
				break;
			default:
				System.out.println("Invalid input, try again:");
			}
		}
		
		
	}
	
	public static double getAverageLength(List<Booking> a) {
		
		if(a == null) {
			throw new IllegalArgumentException("List Arguments is null");
		}
		
		double total = 0;
		for(Booking b : a) {
			total += b.getDistance();
		}
		
		return total/a.size();
	}
	
	public static List<Booking> getFuture(List<Booking> bookings, IFilter filter){
		List<Booking> list = new ArrayList<Booking>();
		for(Booking b : bookings) {
			if(filter.matches(b)) {
				list.add(b);
			}
		}
		return list;
	}
}

