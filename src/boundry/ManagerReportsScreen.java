package boundry;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import control.ControlFlight;
import control.ControlReports;
import entity.Airport;
import entity.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.swing.JRViewer;
import utils.FlightStatus;

public class ManagerReportsScreen implements Initializable {

	
	/********************************BIG FLIGHTS REPORT*******************************/
	/****************Flight Table****************/
	
	@FXML
	private TableView<Flight> flightTable;
	@FXML
	private TableColumn<Flight,String> fNumber;
	@FXML
	private TableColumn<Flight, Timestamp> fDeparture;
	@FXML
	private TableColumn<Flight, Timestamp> fArrival;
	@FXML
	private TableColumn<Flight,String> fAirplane;
	@FXML
	private TableColumn<Flight, FlightStatus> fStatus;
	@FXML
	private TableColumn<Flight, Integer> fOrigin;
	@FXML
	private TableColumn<Flight, Integer> fDestination;
	
	@FXML
	private DatePicker departureDate;
	@FXML
	private DatePicker arrivalDate;
	@FXML
	private TextField seatsQuantity;
	@FXML
	private Button btnCreateReport;
	
	/*get all the flights from the database*/
	private ObservableList<Flight> getFlightsToTable(){
	ObservableList<Flight> flights=FXCollections.observableArrayList();
	ArrayList<Flight> query;
	int seats = Integer.parseInt(seatsQuantity.getText());
	java.sql.Date dep = java.sql.Date.valueOf(departureDate.getValue());
	java.sql.Date arr = java.sql.Date.valueOf(arrivalDate.getValue());

	try {
		query = new ArrayList<Flight>(ControlReports.getInstance().getBigFlightsReport(seats, dep, arr));
		flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return flights;	
	}	
	
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
	
	/*get all the airport from the database to show in table view*/
	private ObservableList<Airport> getAirportsToTable(){
	ObservableList<Airport> airplanes=FXCollections.observableArrayList();
	ArrayList<Airport> query;
	ArrayList<String> airportsInFlights = new ArrayList<>();
	try {
		query = new ArrayList<Airport>(ControlFlight.getInstance().getAirports());
		for(Airport ap : query) {
			airportsInFlights.add(ap.toString());
		}
		airplanes.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return airplanes;	
	}	
	
	public void createReport() {
		try {

			/**************************************Flight Page*****************************************/

			//set in flight table
			fNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightSerialNumber"));
			fDeparture.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture"));
			fArrival.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival"));
			fAirplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
			fStatus.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
			fOrigin.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("originAirport"));
			fDestination.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("destinationAirport"));
			flightTable.setItems(getFlightsToTable());
			}
			
		catch(Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
}
