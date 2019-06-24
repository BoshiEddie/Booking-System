package BoshiPan;

import java.util.Comparator;

public class ComparatorPassengerName implements Comparator<Passenger>{

		public int compare(Passenger p1, Passenger p2) {
			return p1.getName().compareTo(p2.getName());
		}
}
