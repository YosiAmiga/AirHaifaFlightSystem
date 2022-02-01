package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import entity.Consts;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControlJSON {
	
	private static ControlJSON instance;

	public static ControlJSON getInstance() {
		if (instance == null)
			instance = new ControlJSON();
		return instance;
	}
	
	public static void exportToJSON(java.sql.Date today) 
	{

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(
							Consts.FLIGHTS_FROM_DATE)){
					stmt.setDate(1, today);
					ResultSet rs = stmt.executeQuery(); {
				JsonArray updatedFlights = new JsonArray();
				while (rs.next()) {
					JsonObject flight = new JsonObject();

					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						flight.put(rs.getMetaData().getColumnName(i), rs.getString(i));
						if(i==1) {
							flight.put("Seats",getSeatOfFlights(rs.getString(1)));
							
						}
					}
				
					updatedFlights.add(flight);
				}

				JsonObject doc = new JsonObject();
				doc.put("Flights", updatedFlights);

				
				File file = new File("json/Flights.json");
				file.getParentFile().mkdir();

				try (FileWriter writer = new FileWriter(file)) {
					writer.write(Jsoner.prettyPrint(doc.toJson()));
					writer.flush();
					 Alert alert = new Alert(AlertType.INFORMATION, "Flights data exported successfully!");
					 alert.setHeaderText("Success");
					 alert.setTitle("Success Data Export");
					 alert.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
					}
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/*Method to bring from the DB each flight its seats*/
	public static JsonArray getSeatOfFlights(String FlightString) 
	{

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(
							Consts.GET_SEAT_BY_FLIGHT)){
					stmt.setString(1, FlightString);
					ResultSet rs = stmt.executeQuery(); {
				JsonArray seatOfEachFlight = new JsonArray();
				while (rs.next()) {
					JsonObject seat = new JsonObject();

					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						seat.put(rs.getMetaData().getColumnName(i), rs.getString(i));
					}
				
					seatOfEachFlight.add(seat);
				}

				return seatOfEachFlight;

					}
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	

}
