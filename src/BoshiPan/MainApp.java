package BoshiPan;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainApp {
	public static void main(String[] args) {
		//Adding a booking to the system, duplicates are not allow
		BookingManager bMgr = new BookingManager();
		System.out.println("1.Add a booking to the system");
		//initial gps location for passenger
		GPSLocation gps = new GPSLocation(56.0973563,-6.362542);
		//create am passenger object
		Passenger bp = new Passenger("Boshi","PSG000" ,"boshi@pan.cn","083-047842323",gps);
		//create an vehicle references a car object
		Vehicle v = new Car("VW","Polo","18 LU 5657",4,2000,LocalDate.of(2018, Month.MARCH, 3),new GPSLocation(56.0123535,-6.135135),4,VehicleType.Car,2);
		Vehicle v2 = new Truck("SD","Vole","18 DU 3422",2,3000,LocalDate.of(2018, Month.APRIL, 10),new GPSLocation(56.0123535,-6.135135),2,VehicleType.Van,500);
		
		
		//declare a booking and construct it
		Booking b = new Booking("BP001",bp,v,LocalDate.now(),gps,new GPSLocation(57.032442,-6.0031));
		Booking b1 = new Booking("BP002",bp,v2,LocalDate.of(2018,Month.APRIL,20),gps,new GPSLocation(57.032442,-6.0031));
		System.out.println(b1.getCostCalculate());
		System.out.println(b.getCostCalculate());
		//adding booking into booking manager
		bMgr.addBooking(b);
		bMgr.addBooking(b1);
		
		System.out.println("Booking manager");
		System.out.println(bMgr);
		//check the duplicate
		bMgr.addBooking(b);
		System.out.println(bMgr);
		ComparatorBookingDate date = new ComparatorBookingDate();
		Collections.sort(bMgr.getBookingList(), date);
		System.out.println(bMgr);
		System.out.println("Average Length:"+getAverageLength(bMgr.getBookingList()));
		//Adding a passenger to the system, duplicate is not allow
		PassengerManager pMgr = new PassengerManager();
		
		System.out.println("4.Add a passenger to the system");
		//load data from a file 
		pMgr.loadFromFile("passengers.txt");
		System.out.println("List of passengers loaded from passengers.txt");
		System.out.println(pMgr);
		System.out.println();
		//add new Passenger
		GPSLocation gps1 = new GPSLocation(56.0973563,-6.362542);
		Passenger p1 = new Passenger("Post Malone" ,"PSG001","post@malone.ie","0873-836752627",gps1);
		pMgr.addPassenger(p1);
		System.out.println("List of passengers after adding Post Malone");
		System.out.println(pMgr);
		pMgr.addPassenger(p1);
		System.out.println(pMgr);
		
		System.out.println();
		
		List<Passenger> list = pMgr.getAllPassengers();
		System.out.println("List of passengers returned from getAllPassengers()");
		for(Passenger p : list) {
			System.out.println(p);
		}
		System.out.println();
		
		IFilter filter = new PassengerNameFilter("Selina Simms");
		List<Passenger> filteredList  = pMgr.getPassengersByFilter(filter);
		System.out.println("List of passenger returned from getPassengerByFilter()");
		System.out.println("Filtered by name: \n" + filteredList);
		System.out.println();
		System.out.println("Adding a new car");
		VehicleManager vMgr = new VehicleManager();
		vMgr.loadFormFile("vehicles.txt");
		System.out.println(vMgr);
		vMgr.addVehicle(v2);
		
		vMgr.addVehicle(v);
		System.out.println("OK");
		bMgr.loadFromFile("bookings.txt",pMgr.getAllPassengers(),vMgr.getVehicles());
		System.out.println(bMgr);
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
}
