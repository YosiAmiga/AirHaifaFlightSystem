package entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class GroundAttendant extends Worker{
	
	// all the shifts the ground attendant is in
	//sort by first calendar -> start date
	//second calendar -> end date
	private HashMap<Calendar, HashMap<Calendar, Shift>> shifts;

	public GroundAttendant(int id, String firstName, String lastName, Date startingDate, Date finishingDate) {
		super(id, firstName, lastName, startingDate, finishingDate);
		shifts = new HashMap<Calendar, HashMap<Calendar, Shift>>();
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
		if (!(obj instanceof GroundAttendant))
			return false;
		return true;
	}

	
	//Methods to add and remove a shift for the ground attendant	
//	public void addGroundAttendantToShift(Shift s) {
//		// if the given shifts s starting and ending date 
//		if(!shifts.containsKey(s.getStartDate()) && !shifts.get(s).containsKey(s.getEndDate())) {
//			HashMap<Calendar, Shift> endingDate = new HashMap<Calendar, Shift>();
//			shifts.put(s.getStartDate(), endingDate);			
//		}
//	}
	public void removeGroundAttendantFromShift(GroundAttendantsInShift g) {
		shifts.remove(g);
	}
	
	@Override
	public String toString() {
		return "GroundAttendant [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getStartingDate()=" + getStartingDate() + ", getFinishingDate()="
				+ getFinishingDate() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}



}
