package BoshiPan;
import java.time.LocalDate;
public class Booking {
	
	private String bookingID;
	private Passenger passenger;
	private Vehicle vehicle;
	private LocalDate date;
	private GPSLocation startLocation;
	private GPSLocation endLocation;
	
	public Booking() {};
	
	public Booking(String bookingID, Passenger passenger, Vehicle vehicle, LocalDate date, GPSLocation startLocation,
			GPSLocation endLocation) {
		
		this.bookingID = bookingID;
		this.passenger = passenger;
		this.vehicle = vehicle;
		this.date = date;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		
	}

	public String getBookingID() {
		return bookingID;
	}

	

	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ",passenger=" + passenger + ",vehicle=" + vehicle + ", date="
				+ date + ", startLocation=" + startLocation + ", endLocation=" + endLocation + "]";
	}

	public Passenger getPassenger() {
		return passenger;
	}
	

	public Vehicle getVehicle() {
		return vehicle;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public GPSLocation getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(GPSLocation startLocation) {
		this.startLocation = startLocation;
	}

	public GPSLocation getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(GPSLocation endLocation) {
		this.endLocation = endLocation;
	}
	
	

	public double getDistance() {
		double dslatitude = 69 * Math.abs(this.getStartLocation().getLatitude() - this.getVehicle().getLocation().getLatitude());
		double dslongitude = 71 * Math.abs(this.getStartLocation().getLongtitude() - this.getVehicle().getLocation().getLongtitude());
		double selatitude = 69 * Math.abs(this.getStartLocation().getLatitude()-this.getEndLocation().getLatitude());
		double selongitude = 71 * Math.abs(this.getStartLocation().getLatitude() - this.getEndLocation().getLongtitude());
		double edlatitude = 69* Math.abs(this.getEndLocation().getLatitude() - this.getVehicle().getLocation().getLatitude());
		double edlongitude = 71 * Math.abs(this.getEndLocation().getLongtitude() - this.getVehicle().getLocation().getLongtitude());
		
		double distanceFromDepotToStart = Math.sqrt(dslatitude * dslatitude + dslongitude * dslongitude);
		double distanceFromStartToEnd = Math.sqrt(selatitude * selatitude + selongitude * selongitude);
		double distanceFromEndToDepot = Math.sqrt(edlatitude * edlatitude + edlongitude * edlongitude);
		
		double distance = distanceFromDepotToStart + distanceFromStartToEnd + distanceFromEndToDepot;
		
		return distance;
	}
	public double getCostCalculate() {
		
		int pricePerMile = 0;
		switch (this.getVehicle().getType()) {
		case Car:
			pricePerMile = 2;
			break;
		case Four:
			pricePerMile = 4;
			break;
		case Van:
			pricePerMile = 6;
			break;
		case Truck:
			pricePerMile = 10;
		}	
		
		
		double cost = ((double)pricePerMile) * this.getDistance();
		
		return cost;
		
	}

}
