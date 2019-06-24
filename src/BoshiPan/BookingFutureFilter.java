package BoshiPan;

import java.time.LocalDate;

public class BookingFutureFilter implements IFilter {
	public boolean matches(Object o) {
		Booking b = (Booking) o;
		return b.getDate().isAfter(LocalDate.now());
	}
}
