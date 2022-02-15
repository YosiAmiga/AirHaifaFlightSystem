package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public abstract class Worker {
	
	/*Workers fields*/
	private int id;
	private String firstName;
	private String lastName;
	private Date startingDate;
	private Date finishingDate;
	
	
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param startingDate
	 * @param finishingDate
	 */
	public Worker(int id, String firstName, String lastName, Date startingDate, Date finishingDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.startingDate = startingDate;
		this.finishingDate = finishingDate;
	}
	/*Getters and setters*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getFinishingDate() {
		return finishingDate;
	}
	public void setFinishingDate(Date finishingDate) {
		this.finishingDate = finishingDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finishingDate == null) ? 0 : finishingDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		if (finishingDate == null) {
			if (other.finishingDate != null)
				return false;
		} else if (!finishingDate.equals(other.finishingDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (startingDate == null) {
			if (other.startingDate != null)
				return false;
		} else if (!startingDate.equals(other.startingDate))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Worker [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", startingDate="
				+ startingDate + ", finishingDate=" + finishingDate + "]";
	}
	
	
	

}
