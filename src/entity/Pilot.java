package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class Pilot extends Worker {
	
	/*Pilot fields*/
	private int licenseNumber;
	private Date licenseDateIssued;
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param startingDate
	 * @param finishingDate
	 * @param licenseNumber
	 * @param licenseDateIssued
	 */
	public Pilot(int id, String firstName, String lastName, Date startingDate, Date finishingDate,
			int licenseNumber, Date licenseDateIssued) {
		super(id, firstName, lastName, startingDate, finishingDate);
		this.licenseNumber = licenseNumber;
		this.licenseDateIssued = licenseDateIssued;
	}

	/*Pilot getters and setters*/
	public int getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(int licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Date getLicenseDateIssued() {
		return licenseDateIssued;
	}

	public void setLicenseDateIssued(Date licenseDateIssued) {
		this.licenseDateIssued = licenseDateIssued;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((licenseDateIssued == null) ? 0 : licenseDateIssued.hashCode());
		result = prime * result + licenseNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilot other = (Pilot) obj;
		if (licenseDateIssued == null) {
			if (other.licenseDateIssued != null)
				return false;
		} else if (!licenseDateIssued.equals(other.licenseDateIssued))
			return false;
		if (licenseNumber != other.licenseNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pilot [licenseNumber=" + licenseNumber + ", licenseDateIssued=" + licenseDateIssued + "]";
	}

	

}
