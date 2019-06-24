package BoshiPan;

public class GPSLocation {
	private double latitude;
	private double longtitude;
	
	
	public GPSLocation() {
	}


	public GPSLocation(double latitude, double longtitude) {
		this.latitude = latitude;
		this.longtitude = longtitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongtitude() {
		return longtitude;
	}


	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}


	@Override
	public String toString() {
		return "GPSlocation [latitude=" + latitude + ", longtitude=" + longtitude + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longtitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GPSLocation other = (GPSLocation) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longtitude) != Double.doubleToLongBits(other.longtitude))
			return false;
		return true;
	}
	
	public GPSLocation(GPSLocation gps) {
		this.latitude = gps.latitude;
		this.longtitude = gps.longtitude;
	}
	
	
}
