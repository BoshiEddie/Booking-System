package BoshiPan;
import java.util.Comparator;
public class RGcomparator implements Comparator<Vehicle>{
	public int compare(Vehicle v1 , Vehicle v2) {
		return v1.getRegistration_number().compareTo(v2.getRegistration_number());
	}
}
