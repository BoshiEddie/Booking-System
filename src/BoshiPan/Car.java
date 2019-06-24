package BoshiPan;

import java.time.LocalDate;

public class Car extends Vehicle{
	private int backSeatCapacity;

	

	public Car() {};

	public Car(String make, String model, String registration_number, double miles_per_kWh,
			double mileage, LocalDate lastServiceDate, GPSLocation location, int number_of_seat, VehicleType type,int backSeatCapacity) {
		super(make, model, registration_number, miles_per_kWh, mileage, lastServiceDate, location,
				number_of_seat, type);
		this.backSeatCapacity = backSeatCapacity;
	}

	public Car(String make, String model, String registration_number, int number_of_seat, VehicleType type,int backSeatCapacity) {
		super(make, model, registration_number, number_of_seat, type);
		this.backSeatCapacity = backSeatCapacity;
	}

	public int getBackSeatCapacity() {
		return backSeatCapacity;
	}

	public void setBackSeatCapacity(int backSeatCapacity) {
		this.backSeatCapacity = backSeatCapacity;
	}

	@Override
	public String toString() {
		return super.toString() + "backSeatCapacity=" + this.backSeatCapacity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + backSeatCapacity;
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
		Car other = (Car) obj;
		if (backSeatCapacity != other.backSeatCapacity)
			return false;
		return true;
	}

	
	
}

