package BoshiPan;

import java.time.LocalDate;

public class Truck extends Vehicle{
	private int loadSpace;

	

	public Truck() {};

	public Truck(String make, String model, String registration_number, double miles_per_kWh,
			double mileage, LocalDate lastServiceDate, GPSLocation location, int number_of_seat, VehicleType type,int loadSpace) {
		super(make, model, registration_number, miles_per_kWh, mileage, lastServiceDate, location,
				number_of_seat, type);
		this.loadSpace = loadSpace;
	}

	public Truck(String make, String model, String registration_number, int number_of_seat, VehicleType type,int loadSpace) {
		super(make, model, registration_number, number_of_seat, type);
		this.loadSpace = loadSpace;
	}

	public int getLoadSpace() {
		return loadSpace;
	}

	public void setLoadSpace(int loadSpace) {
		this.loadSpace = loadSpace;
	}

	@Override
	public String toString() {
		return super.toString() + "loadSpace=" + loadSpace + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + loadSpace;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		if (loadSpace != other.loadSpace)
			return false;
		return true;
	}
	
	
}
