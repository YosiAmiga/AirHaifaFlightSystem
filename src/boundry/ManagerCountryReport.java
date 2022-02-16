package boundry;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

public class ManagerCountryReport implements Initializable {
	/********************************CITY REPORT*******************************/
	
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
	private Button fillCombo;
	
	/****************Airport Table****************/
	
	private String selectedCountryForReport;
	@FXML
	private TableView<Airport> airportTable;
	@FXML
	private TableColumn<Airport,Integer> airportColumn;
	@FXML
	private TableColumn<Airport, String> countryColumn;
	@FXML
	private TableColumn<Airport,String> cityColumn;
	@FXML
	private TableColumn<Airport,Double> timeZoneColumn;
	@FXML
	private ComboBox<String> originAirports;
	@FXML
	private Button btnCreateCityReport;
	@FXML
	private Label countryLabel;
	@FXML
	private Label presentage;
	public void getCityReport(ActionEvent e) {
		//Save the data from the current origin airport combo box
		String originA = originAirports.getValue();
		//Extract only the Airport ID
		String originAirportNumber= originA.replaceAll("[^0-9]", "");
		String country = ControlFlight.getInstance().getAirportByCountry(Integer.parseInt(originAirportNumber));
		countryLabel.setText(country);
		int airport = Integer.parseInt(originAirportNumber);
		int comboSize = airportsToCheck.size();
		int countGivenCountry = 0;
		int press = 0;
		for(int i : airportsToCheck) {
			if(i == airport) {
				countGivenCountry++;
			}
		}
		
		press = (countGivenCountry*100/comboSize);

		String p = String.valueOf(press);
		p = p+"%";

		presentage.setText(p);
	}

	
	
	public void initAirports() {
		//setting all the airports in the flights combo box
		ObservableList<String> ObservableListAirports = FXCollections.observableArrayList();		
		ArrayList<Airport> airportsQuery;
		ArrayList<String> airportsInFlights = new ArrayList<>();
		try {
			airportsQuery = new ArrayList<Airport>(ControlFlight.getInstance().getAirports());
			for(Airport ap : airportsQuery) {
				if(airportsToCheck.contains(ap.getUniqueAirportID())) {
					System.out.println(airportsToCheck);
					airportsInFlights.add(ap.toString());					
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(airportsToCheck);
		ObservableListAirports.addAll(airportsInFlights);
		originAirports.setItems(ObservableListAirports);

		
	}
	ArrayList<Integer> airportsToCheck = new ArrayList<Integer>();

	/*get all the flights from the database*/
	private ObservableList<Flight> getFlightsToTable(){
	ObservableList<Flight> flights=FXCollections.observableArrayList();
	ArrayList<Flight> query;
	try {
		query = new ArrayList<Flight>(ControlFlight.getInstance().getFlights());
		
		for(Flight f : query) {
			if(f.getFlightDeparture().getMonth() == Calendar.getInstance().get(Calendar.MONTH)  && 
					f.getFlightDeparture().getYear() == (Calendar.getInstance().get(Calendar.YEAR) -1900)) {
				airportsToCheck.add(f.getDestinationAirport());
				flights.add(f);
			}
		}
//		flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(airportsToCheck);
	return flights;	
}	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//set in airports table
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
		
//		airportColumn.setCellValueFactory(new PropertyValueFactory<Airport,Integer>("uniqueAirportID"));
//		countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
//		cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
//		timeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport,Double>("timeZone"));
//		airportTable.setItems(getAirportsToTable());
		
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
	

	
	
	
}
