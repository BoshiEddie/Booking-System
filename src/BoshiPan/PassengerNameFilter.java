package BoshiPan;

public class PassengerNameFilter implements IFilter {
	private String name;
	
	public PassengerNameFilter(String name) {
		this.name = name;
	}
	
	@Override
	public boolean matches(Object o) {
		Passenger p = (Passenger) o;
		return p.getName().equalsIgnoreCase(name);
	}
}
