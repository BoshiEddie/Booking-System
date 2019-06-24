package BoshiPan;

import java.time.LocalDate;

public abstract class Vehicle {
	private String make;
	private String model;
	private String registration_number;
	private double miles_per_kWh;
	private double cost_per_mile;
	private double mileage;
	private LocalDate lastServiceDate;
	private GPSLocation location;
	private int number_of_seat;
	private VehicleType type;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String make,String model,String registration_number,int number_of_seat,VehicleType type) {
		this.make = make;
		this.model = model;
		this.registration_number = registration_number;
		this.number_of_seat = number_of_seat;
		this.type = type;
		if(type == VehicleType.Car) {
			this.cost_per_mile = 2;
		}else if(type == VehicleType.Four) {
			this.cost_per_mile = 4;
		}else if (type == VehicleType.Van) {
			this.cost_per_mile = 6;
		}else {
			this.cost_per_mile = 10;
		}
	}
	
	
	public Vehicle(String make, String model, String registration_number, double miles_per_kWh, 
			double mileage,LocalDate lastServiceDate, GPSLocation location, int number_of_seat, VehicleType type) {
		this.make = make;
		this.model = model;
		this.registration_number = registration_number;
		this.miles_per_kWh = miles_per_kWh;
		this.mileage = mileage;
		this.lastServiceDate = lastServiceDate;
		this.location = location;
		this.number_of_seat = number_of_seat;
		this.type = type;
		
		if(type == VehicleType.Car) {
			this.cost_per_mile = 2;
		}else if(type == VehicleType.Four) {
			this.cost_per_mile = 4;
		}else if (type == VehicleType.Van) {
			this.cost_per_mile = 6;
		}else {
			this.cost_per_mile = 10;
		}
	}
	
	public LocalDate getLastServiceDate() {
		return this.lastServiceDate;
	}
	
	public void setLastServiceDate(LocalDate lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	
	public GPSLocation getLocation() {
		return location;
	}

	public void setLocation(GPSLocation location) {
		this.location = location;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public double getMiles_per_kWh() {
		return miles_per_kWh;
	}

	public void setMiles_per_kWh(double miles_per_kWh) {
		this.miles_per_kWh = miles_per_kWh;
	}

	public double getCost_per_mile() {
		return cost_per_mile;
	}


	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	
	public GPSLocation getGPS() {
		return location;
	}

	public void setGPS(GPSLocation location) {
		this.location = location;
	}

	public int getNumber_of_seat() {
		return number_of_seat;
	}

	public void setNumber_of_seat(int number_of_seat) {
		this.number_of_seat = number_of_seat;
	}

	@Override
	public String toString() {
		return  this.getClass() + "[ make=" + make + ", model=" + model + ", registration_number=" + registration_number
				+ ", miles_per_kWh=" + miles_per_kWh + ", cost_per_mile=" + cost_per_mile + ", mileage=" + mileage
				+ ", lastServiceDate=" + lastServiceDate + ", location=" + location + ", number_of_seat="
				+ number_of_seat + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost_per_mile);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lastServiceDate == null) ? 0 : lastServiceDate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		temp = Double.doubleToLongBits(mileage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(miles_per_kWh);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + number_of_seat;
		result = prime * result + ((registration_number == null) ? 0 : registration_number.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (Double.doubleToLongBits(cost_per_mile) != Double.doubleToLongBits(other.cost_per_mile))
			return false;
		if (lastServiceDate == null) {
			if (other.lastServiceDate != null)
				return false;
		} else if (!lastServiceDate.equals(other.lastServiceDate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (Double.doubleToLongBits(mileage) != Double.doubleToLongBits(other.mileage))
			return false;
		if (Double.doubleToLongBits(miles_per_kWh) != Double.doubleToLongBits(other.miles_per_kWh))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (number_of_seat != other.number_of_seat)
			return false;
		if (registration_number == null) {
			if (other.registration_number != null)
				return false;
		} else if (!registration_number.equals(other.registration_number))
			return false;
		if (type != other.type)
			return false;
		return true;
	}


	
	
	
}

