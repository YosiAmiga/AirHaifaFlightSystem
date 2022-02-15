package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

public class FlightAttendant extends Worker{
	
	private HashMap<String, Flight> flights;

	public FlightAttendant(int id, String firstName, String lastName, Date startingDate, Date finishingDate) {
		super(id, firstName, lastName, startingDate, finishingDate);
		flights = new HashMap<String, Flight>();
	}
	
	// methods to link between many flights to many flight attendants
	public boolean linkFlight(Flight f) {
		
		if(!flights.containsKey(f.getFlightSerialNumber()) && f != null) {
			flights.put(f.getFlightSerialNumber(), f);
			return true;
		}
		return false;
	}
	
	public boolean removeFlight(Flight f) {		
		if(flights.containsKey(f.getFlightSerialNumber())) {
			flights.remove(f);
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof FlightAttendant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlightAttendant [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getStartingDate()=" + getStartingDate() + ", getFinishingDate()="
				+ getFinishingDate() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}




}
