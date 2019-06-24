package BoshiPan;
import java.util.Comparator;
public class seatComparator implements Comparator<Vehicle>{
	public int compare(Vehicle v1 , Vehicle v2) {
		return v1.getNumber_of_seat() - v2.getNumber_of_seat();
	}
}
