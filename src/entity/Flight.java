package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import utils.FlightStatus;

public class Flight implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private Calendar flightDeparture;
//	private Calendar flightArrival;
//	private Airport originAirport;
//	private Airport destinationAirport;
//	private Airplane flightAirplane;
//	private FlightStatus status;
//	private Pilot firstPilotID;
//	private Pilot secondPilotID;
//	
	
	private String flightSerialNumber;
	private Timestamp flightDeparture;
	private Timestamp flightArrival;
	private String airplane;
	private String status;
	private int originAirport;
	private int destinationAirport;
	
	
	private Pilot firstPilotID;
	private Pilot secondPilotID;

	private HashMap<Integer , FlightAttendant> flightAttendantsInFlight;
	
	//without pilots
	public Flight(
			String flightSerialNumber,			
			Timestamp flightDeparture,
			Timestamp flightArrival,
			String flightAirplane,
			String flightStatus,
			int originAirport,
			int destinationAirport
		
			) {
		
		super();
		this.flightSerialNumber = flightSerialNumber;
		this.flightDeparture = flightDeparture;
		this.flightArrival = flightArrival;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.airplane = flightAirplane;
		this.status = flightStatus;	
		this.firstPilotID = null;
		this.secondPilotID = null;
		flightAttendantsInFlight = new HashMap<Integer , FlightAttendant>();
	}
	
//	
//	public Flight(
//			String flightStatus,
//			String flightSerialNumber,
//			String flightAirplane,
//			
//			int originAirport,
//			int destinationAirport,
//			int firstPilotID2,
//			int secondPilotID2,
//			
//			Timestamp flightArrival,
//			Timestamp flightDeparture
//			) {
//		super();
//		this.flightSerialNumber = flightSerialNumber;
//		this.flightDeparture2 = flightDeparture;
//		this.flightArrival2 = flightArrival;
//		this.originAirport2 = originAirport;
//		this.destinationAirport2 = destinationAirport;
//		this.airplane2 = flightAirplane;
//		this.firstPilotID2 = firstPilotID2;
//		this.secondPilotID2 = secondPilotID2;
//		this.status2 = flightStatus;	
//		
//		flightAttendantsInFlight = new HashMap<Integer , FlightAttendant>();
//	}
	
//	/**
//	 * @param flightSerialNumber
//	 * @param flightDeparture
//	 * @param flightArrival
//	 * @param originAirport
//	 * @param destinationAirport
//	 * @param flightAirplane
//	 * @param status
//	 */
//	
//	public Flight(String flightSerialNumber, Calendar flightDeparture, Calendar flightArrival, Airport originAirport,
//			Airport destinationAirport, Airplane flightAirplane) {
//		super();
//		this.flightSerialNumber = flightSerialNumber;
//		this.flightDeparture = flightDeparture;
//		this.flightArrival = flightArrival;
//		this.originAirport = originAirport;
//		this.destinationAirport = destinationAirport;
//		this.flightAirplane = flightAirplane;
//		this.status = FlightStatus.OnTime;	
//		
//		flightAttendantsInFlight = new HashMap<Integer , FlightAttendant>();
//	}

	// methods to link flight attendants to flights
	public boolean linkFlightAttendants(FlightAttendant fa) {
		if(fa != null && !flightAttendantsInFlight.containsKey(fa.getId())) {
			flightAttendantsInFlight.put(fa.getId(), fa);
			return true;
		}
		return false;
	}
	
	public boolean removeFlightAttendants(FlightAttendant fa) {
		
		if(flightAttendantsInFlight.containsKey(fa.getId())) {
			flightAttendantsInFlight.remove(fa);
			return true;
		}
		return false;
	}

	public String getFlightSerialNumber() {
		return flightSerialNumber;
	}


	public void setFlightSerialNumber(String flightSerialNumber) {
		this.flightSerialNumber = flightSerialNumber;
	}


	public Timestamp getFlightDeparture() {
		return flightDeparture;
	}

	public void setFlightDeparture(Timestamp flightDeparture) {
		this.flightDeparture = flightDeparture;
	}

	public Timestamp getFlightArrival() {
		return flightArrival;
	}

	public void setFlightArrival(Timestamp flightArrival) {
		this.flightArrival = flightArrival;
	}

	public String getAirplane() {
		return airplane;
	}

	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(int originAirport) {
		this.originAirport = originAirport;
	}

	public int getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(int destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Pilot getFirstPilotID() {
		return firstPilotID;
	}

	public void setFirstPilotID(Pilot firstPilotID) {
		this.firstPilotID = firstPilotID;
	}

	public Pilot getSecondPilotID() {
		return secondPilotID;
	}

	public void setSecondPilotID(Pilot secondPilotID) {
		this.secondPilotID = secondPilotID;
	}

	public HashMap<Integer, FlightAttendant> getFlightAttendantsInFlight() {
		return flightAttendantsInFlight;
	}

	public void setFlightAttendantsInFlight(HashMap<Integer, FlightAttendant> flightAttendantsInFlight) {
		this.flightAttendantsInFlight = flightAttendantsInFlight;
	}

	/********* Methods *************/
	
//	public double checkTimeBeforeTakeoff() {
//		//get the current date (day,month,year)
//		Calendar currentDate = Calendar.getInstance();
//		//calculate the difference between the current date and the Flight Departure date
//		return (daysBetween(currentDate, getFlightDeparture()));
//
//	}
	
	//a method that return the difference in days between 2 dates.
//	public static long daysBetween(Calendar startDate, Calendar endDate) {
//	    long end = endDate.getTimeInMillis();
//	    long start = startDate.getTimeInMillis();
//	    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
//	}
//	
//	public boolean checkMinimumCrew() {
//		//minimum staff (2p, 2a)
//		if(firstPilotID != null && secondPilotID != null && flightAttendantsInFlight.size() >= 2) {
//			//stage -> pre sale
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean checkPartialCrew() {
//		if(checkMinimumCrew() && !checkFullCrew()) {			
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean checkFullCrew() {
//		//1 attendant for 25 seats, plane size/ ammount of attendants = 25 for it to be full.
//		if(flightAirplane.getAirplaneSize()/flightAttendantsInFlight.size() == 25) {
//			return true;
//		}
//		return false;
//	}
//	
//	public void checkFlightStage() {
//		//init stage -> reset
//		
//		//minimum staff (2p, 2a)
//		if(firstPilotID != null && secondPilotID != null && flightAttendantsInFlight.size() >= 2) {
//			//stage -> pre sale
//		}
//		
//		//1 attendant for 25 seats, plane size
//		if(checkFullCrew()) {
//			//stage -> normal sale
//		}
//		
//	}

	@Override
	public String toString() {
		return "Flight Number: " + flightSerialNumber + ", flight Departure: " + flightDeparture
				+ ", flight Arrival: " + flightArrival + ", origin Airport: " + originAirport + ", destination Airport: "
				+ destinationAirport + ", air plane=" + airplane + ", first PilotID: " + firstPilotID
				+ ", second PilotID: " + secondPilotID + ", status: " + status + "]";
	}
	


}
