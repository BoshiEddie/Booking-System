package BoshiPan;
import java.util.Comparator;
public class ComparatorBookingDate implements Comparator<Booking>{
	public int compare(Booking a, Booking b) {
		return -(a.getDate().compareTo(b.getDate()));
	}
}
