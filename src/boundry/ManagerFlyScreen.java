package boundry;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.*;
import control.ControlFlight;
import control.ControlJSON;

import entity.Airplane;
import entity.Airport;
import entity.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.FlightStatus;

public class ManagerFlyScreen implements Initializable {
	
	private ControlFlight control;
	private ControlJSON controlJson;
//	
//	@FXML
//	private Button exportJSON;
	
	@FXML
	public void doingExport()
	{
		//get the current date and pass it to the function
		java.sql.Date today = java.sql.Date.valueOf(java.time.LocalDate.now());
		System.out.println(today);
		controlJson.getInstance().exportToJSON(today);
	}
	
	/**************************************Flight Page*****************************************/
	
	@FXML
	private ComboBox<String> flightFromDB;
	@FXML
	private Button loadFlightData;
	
	@FXML
	private ComboBox<String> flightStatus;
	
	@FXML
	private TextField flightNumber;
	@FXML
	private DatePicker departureDate;
	@FXML
	private ComboBox<String> departureHour;
	@FXML
	private ComboBox<String> departureMinute;
	@FXML
	private DatePicker arrivalDate;
	@FXML
	private ComboBox<String> arrivalHour;
	@FXML
	private ComboBox<String> arrivalMinute;	
	@FXML
	private ComboBox<String> airplaneInFlight;
	@FXML
	private ComboBox<String> originAirports;
	@FXML
	private ComboBox<String> destAirports;
	@FXML
	private Button createNewFlight;

	
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
	


	

	
	/*get all the flights from the database*/
	private ObservableList<Flight> getFlightsToTable(){
	ObservableList<Flight> flights=FXCollections.observableArrayList();
	ArrayList<Flight> query;
	try {
		query = new ArrayList<Flight>(ControlFlight.getInstance().getFlights());
		flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return flights;	
}	
	
	

	
	/**************************************Airport Page*****************************************/
	
	@FXML
	private ComboBox<String> airportsInDB;
	
	@FXML
	private Button loadAirportData;
	
	@FXML
	private TextField airportID;
	@FXML
	private TextField country;
	@FXML
	private TextField city;
	@FXML
	private TextField timeZone;
	
	@FXML
	private Button addNewAirport;
	
	@FXML
	private TextField airportToDelete;
	@FXML
	private Button deleteAirport;
	
	/****************Airport Table****************/
	
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
	
	
	/**************************************Airplane Page*****************************************/
	@FXML
	private ComboBox<String> airplanesInDB;
	
	@FXML
	private Button loadAirplaneFromDB;
	
	@FXML
	private TextField airplaneID;
	@FXML
	private TextField airplaneSize;
	
	@FXML
	private Button addNewAirplane;
	
	@FXML
	private TextField airplaneToDelete;
	@FXML
	private Button deleteAirplane;
	
	/****************Airplane Table****************/
	
	@FXML
	private TableView<Airplane> airplaneTable;
	@FXML
	private TableColumn<Airplane,String> airplaneNumber;
	@FXML
	private TableColumn<Airplane, Integer> airPlaneSize;
	
	/**********Getting the data from the database methods********/
	
	
	/*get all the airplanes from the database to show in table view*/
	private ObservableList<Airplane> getAirplaneToTable(){
	ObservableList<Airplane> airplanes=FXCollections.observableArrayList();
	ArrayList<Airplane> query;
	try {
		query = new ArrayList<Airplane>(ControlFlight.getInstance().getAirplanes());
		airplanes.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return airplanes;	
}	

	/*************Initialize all the combo boxes*************/
	
	public void initFlights() {
		//setting all the airplanes in the flights combo box
		flightFromDB.setValue("Select Flight Serial Number");
		ObservableList<String> flights=FXCollections.observableArrayList();
		ArrayList<Flight> query;
		try {
			query = new ArrayList<Flight>(ControlFlight.getInstance().getFlights());
			for(Flight f : query) {
				flights.add(f.getFlightSerialNumber());
			}
			flightFromDB.setItems(flights);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initAirports() {
		//setting all the airports in the flights combo box
		airportsInDB.setValue("Select Airport Number");
		ObservableList<String> ObservableListAirports = FXCollections.observableArrayList();		
		ArrayList<Airport> airportsQuery;
		ArrayList<String> airportsInFlights = new ArrayList<>();
		try {
			airportsQuery = new ArrayList<Airport>(ControlFlight.getInstance().getAirports());
			for(Airport ap : airportsQuery) {
				airportsInFlights.add(ap.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableListAirports.addAll(airportsInFlights);
		originAirports.setItems(ObservableListAirports);
		destAirports.setItems(ObservableListAirports);
		airportsInDB.setItems(ObservableListAirports);
		
	}
	
	public void initAirplanes() {
		//setting all the airplanes in the flights combo box
		airplanesInDB.setValue("Select Airplane Number");
		ObservableList<String> ObservableListAirplanes = FXCollections.observableArrayList();		
		ArrayList<Airplane> airplanesQuery;
		ArrayList<String> airplanesInFlights = new ArrayList<>();
		try {
			airplanesQuery = new ArrayList<Airplane>(ControlFlight.getInstance().getAirplanes());
			for(Airplane a : airplanesQuery) {
				airplanesInFlights.add(a.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableListAirplanes.addAll(airplanesInFlights);
		airplaneInFlight.setItems(ObservableListAirplanes);
		airplanesInDB.setItems(ObservableListAirplanes);
	}
	
	public void initTime() {
		ObservableList<String> ObservableListHours = FXCollections.observableArrayList();		
		ObservableList<String> ObservableListMinues = FXCollections.observableArrayList();
		ArrayList<String> hours = new ArrayList<String>();
		ArrayList<String> minutes = new ArrayList<String>();
		try {
			for(int i=0;i<=23;i++) {
				if(i<10) {
					hours.add("0"+i);					
				}
				else {
					hours.add(String.valueOf(i));
				}
				
			}
			for(int j=0;j<=59;j++) {
				if(j<10) {
					minutes.add("0"+j);					
				}
				else {
					minutes.add(String.valueOf(j));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableListHours.addAll(hours);
		ObservableListMinues.addAll(minutes);
		departureHour.setItems(ObservableListHours);
		arrivalHour.setItems(ObservableListHours);

		departureMinute.setItems(ObservableListMinues);
		arrivalMinute.setItems(ObservableListMinues);

		
	}

	public void initStatus() {
		ObservableList<String> ObservableListStatus = FXCollections.observableArrayList();
		ArrayList<String> s = new ArrayList<String>();
		for(FlightStatus fs : FlightStatus.values()) {
			s.add(String.valueOf(fs));
		}
		ObservableListStatus.addAll(s);

		flightStatus.setItems(ObservableListStatus);		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initFlights();
		initAirports();
		initAirplanes();
		initTime();
		initStatus();
		doingExport();
		
		
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
		flightTable.setItems(getFlightsToTable());
		
		
		/**************************************Airport Page*****************************************/


		airportID.setText("");
		country.setText("");
		city.setText("");
		timeZone.setText("");
		airportToDelete.setText("");
		
		//set in airports table
		
		airportColumn.setCellValueFactory(new PropertyValueFactory<Airport,Integer>("uniqueAirportID"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
		timeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport,Double>("timeZone"));
		airportTable.setItems(getAirportsToTable());
		
		/**************************************Airplane Page*****************************************/
		airplaneID.setText("");
		airplaneSize.setText("");
		airplaneToDelete.setText("");
		
		//set in airplane table
		airplaneNumber.setCellValueFactory(new PropertyValueFactory<Airplane, String>("airplaneSerialNumber"));
		airPlaneSize.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("airplaneSize"));
		airplaneTable.setItems(getAirplaneToTable());
		
	}

	/********************Flight methods********************/
	
	public void btnLoadFlightData() {
		//get all the flights data
		try {
			ArrayList<Flight> query = new ArrayList<Flight>(ControlFlight.getInstance().getFlights());
			String flightToUpdate = flightFromDB.getValue();
			for(Flight f : query) {
				//if the given value of flight number is indeed in tha DB update him as a new flight
				if(f.getFlightSerialNumber() == flightToUpdate) {
					//enter

					flightNumber.setText(f.getFlightSerialNumber());
					departureDate.setValue(LocalDate.of(f.getFlightDeparture().toLocalDateTime().getYear(), f.getFlightDeparture().toLocalDateTime().getMonthValue(), f.getFlightDeparture().toLocalDateTime().getDayOfMonth()));
					departureHour.setValue(String.valueOf(f.getFlightDeparture().getHours()));
					departureMinute.setValue(String.valueOf(f.getFlightDeparture().getMinutes()));

					arrivalDate.setValue(LocalDate.of(f.getFlightArrival().toLocalDateTime().getYear(), f.getFlightArrival().toLocalDateTime().getMonthValue(), f.getFlightArrival().toLocalDateTime().getDayOfMonth()));
					arrivalHour.setValue(String.valueOf(f.getFlightArrival().getHours()));
					arrivalMinute.setValue(String.valueOf(f.getFlightArrival().getMinutes()));
					
					airplaneInFlight.setValue(f.getAirplane());
					originAirports.setValue(String.valueOf(f.getOriginAirport()));
					destAirports.setValue(String.valueOf(f.getDestinationAirport()));
					flightStatus.setValue(String.valueOf(f.getStatus()));

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	//TODO fix method
	public void btnNewFlight() {
		control = new ControlFlight();
		try {
			ArrayList<Flight> flightsQuery = new ArrayList<Flight>(control.getFlights());
			
			int dpYear = departureDate.getValue().getYear();
			int dpMonth = departureDate.getValue().getMonthValue();
			int dpDay = departureDate.getValue().getDayOfMonth();
			int dpHour = Integer.parseInt(departureHour.getValue());
			int dpMinute = Integer.parseInt(departureMinute.getValue());


			Timestamp departureTimeStamp = Timestamp.valueOf(dpYear+"-"+dpMonth+"-"+dpDay+" "+dpHour+":"+dpMinute+":"+"00"+".000");
			
			int arrYear = arrivalDate.getValue().getYear();
			int arrMonth = arrivalDate.getValue().getMonthValue();
			int arrDay = arrivalDate.getValue().getDayOfMonth();
			int arrHour = Integer.parseInt(arrivalHour.getValue());
			int arrMinute = Integer.parseInt(arrivalMinute.getValue());
			
			Timestamp arrivalTimeStamp = Timestamp.valueOf(arrYear+"-"+arrMonth+"-"+arrDay+" "+arrHour+":"+arrMinute+":"+"00"+".000");

			String airplaneNumber = airplaneInFlight.getValue();
			
			//Save the data from the current origin airport combo box
			String originA = originAirports.getValue();
			//Extract only the Airport ID
			String originAirportNumber= originA.replaceAll("[^0-9]", "");
			
			//Save the data from the current destination airport combo box
			String destA = destAirports.getValue();
			//Extract only the Airport ID
			String destAirportNumber= destA.replaceAll("[^0-9]", "");	

			String stat = flightStatus.getValue();
//			//can't have both origin and destination airport to be the same airport 
//			if( originAirportNumber == destAirportNumber) {
//				
//				//throw exception
//			}
			
			for(Flight f : flightsQuery) {
				if(f.getFlightSerialNumber().equals(flightNumber.getText())) {
					//First Section -> updating an existing flight
					if(control.updateFlight(flightNumber.getText(),departureTimeStamp,arrivalTimeStamp,airplaneNumber,
							stat,Integer.parseInt(originAirportNumber), Integer.parseInt(destAirportNumber))) {
						successAdded("Flight", "Updating Flight");
						refreshScreen();
						return;
					}
				}
			}

			
			if(control.createNewFlight(flightNumber.getText(),departureTimeStamp,arrivalTimeStamp,airplaneNumber,
					"OnTime",Integer.parseInt(originAirportNumber), Integer.parseInt(destAirportNumber))) {
				successAdded("Flight", "Adding Flight");
				refreshScreen();
			}
			else {
				System.out.println("not good");
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	/*******************Airplane methods*******************/
	
	//Loading Airplane
	public void btnLoadAirplane() {
		control = new ControlFlight();
		try {
			ArrayList<Airplane> airplanesQuery = new ArrayList<Airplane>(control.getAirplanes());
			String airplaneNumber = airplanesInDB.getValue();
			
			for(Airplane a : airplanesQuery) {
				if(a.getAirplaneSerialNumber() == airplaneNumber) {
					airplaneID.setText(a.getAirplaneSerialNumber());
					airplaneSize.setText(String.valueOf(a.getAirplaneSize()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("not good");
			e.printStackTrace();
		}
		
	}
	
	//Adding a new Airplane to system
	public void btnNewAirplane() {
		control = new ControlFlight();
		try {
			//First section -> checking if it is an existing airplane, if so, update his new data
			ArrayList<Airplane> airplanesQuery = new ArrayList<Airplane>(control.getAirplanes());
			
			for(Airplane a : airplanesQuery) {
				if(airplaneID.getText().equals(a.getAirplaneSerialNumber())) {
					if(control.updateAirplane(airplaneID.getText(), Integer.parseInt(airplaneSize.getText()))) {
						successUpdate("Airplane", "Updating Airplane");
						refreshScreen();
						return;
					}
				}
			}
			
			//Second section -> if the given airplane does not exist, create a new one
			if(control.createNewAirplane(airplaneID.getText(), Integer.parseInt(airplaneSize.getText()))) {
				successAdded("Airplane", "Adding Airplane");
				refreshScreen();
			}
			else {
				System.out.println("not good");
			}
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	//deleting an airplane
	public void btnDeleteAirplane() {
		control = new ControlFlight();
		if(control.deleteAirplane(airplaneToDelete.getText())) {
			successRemove("Airplane", "Removing Airplane");
			refreshScreen();
		}
		else {
			System.out.println("not good");
		}
		
	}
	

	/*******************Airport methods*******************/
	
	//Loading Airplane
	public void btnLoadAirport() {
		control = new ControlFlight();
		try {
			ArrayList<Airport> airportsQuery = new ArrayList<Airport>(control.getAirports());
			//Save the data from the current airport combo box
			String airportString = airportsInDB.getValue();
			//Extract only the Airport ID
			String airportNumber= airportString.replaceAll("[^0-9]", "");
			
			for(Airport a : airportsQuery) {
				if(a.getUniqueAirportID() == Integer.parseInt(airportNumber)) {
					airportID.setText(String.valueOf(a.getUniqueAirportID()));
					country.setText(a.getCountry());
					city.setText(a.getCity());
					timeZone.setText(String.valueOf(a.getTimeZone()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("not good");
			e.printStackTrace();
		}
		
	}
	
	//Adding a new Airport to system
	public void btnNewAirport() {
		control = new ControlFlight();
		try {
			//First section -> checking if it is an existing airport, if so, update his new data
			ArrayList<Airport> airportsQuery = new ArrayList<Airport>(control.getAirports());

			for(Airport a : airportsQuery) {
				if(a.getUniqueAirportID() == Integer.parseInt(airportID.getText())) {
					//First section -> if the current airport ID is in the database, update his new data
					if(control.updateAirport(Integer.parseInt(airportID.getText()), country.getText(),city.getText(), Double.parseDouble(timeZone.getText()))) {
						successUpdate("Airport", "Updating Airport");
						refreshScreen();
						return;
					}
				}
			}				
			
			//Second section -> if the current airport ID does not exist in the database, create a new airport
			if(control.createNewAirport(Integer.parseInt(airportID.getText()), country.getText(),city.getText(), Double.parseDouble(timeZone.getText()))) {
				successAdded("Airport", "Adding Airport");
				refreshScreen();
			}
			else {
				System.out.println("not good");
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("not good");
			e.printStackTrace();
		}
		
	}
	
	//deleting an Airport
	public void btnDeleteAirport() {
		control = new ControlFlight();
		if(control.deleteAirport(Integer.parseInt(airportToDelete.getText()))) {
			successRemove("Airplane", "Removing Airport");
			refreshScreen();
		}
		else {
			System.out.println("not good");
		}
		
	}
	/***After action sound and alert***/
	
	public void successUpload() {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Successfully uploaded!");
		al.setHeaderText("Upload");
		al.setTitle("Photo");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successRemove(String content, String header) {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Removed Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successUpdate(String content, String header) {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Updated Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSelection(String content, String header) {
//		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to select : " + content);
		al.setHeaderText(header);
		al.setTitle("ComboBox");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failUpdate(String content, String header) {
//		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failAdd(String content, String header) {
//		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	
//	/******Sounds*****/
//	
//	public void fail(String content, String header) {
//		badSound();
//		Alert al = new Alert(Alert.AlertType.ERROR);
//		al.setContentText("Faild to add : " + content);
//		al.setHeaderText(header);
//		al.setTitle("Database");
//		al.setResizable(false);
//		al.showAndWait();
//	}
//
//	public void goodSound() {
//		Sounds s = new Sounds();
//		try {
//			s.successSound();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//	}
//
//	public void badSound() {
//		Sounds s = new Sounds();
//		try {
//			s.errorSound();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//	}
//	
//	public void successSound() {
//		Sounds s = new Sounds();
//		try {
//			s.addSound();
//		}catch(Exception e2) {
//			e2.printStackTrace();
//		}
//	}
	
	public void refreshScreen(){
		initFlights();
		initAirports();
		initAirplanes();
		initTime();

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
		flightTable.setItems(getFlightsToTable());
		
		
		/**************************************Airport Page*****************************************/


		airportID.setText("");
		country.setText("");
		city.setText("");
		timeZone.setText("");
		airportToDelete.setText("");
		
		//set in airports table
		
		airportColumn.setCellValueFactory(new PropertyValueFactory<Airport,Integer>("uniqueAirportID"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
		timeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport,Double>("timeZone"));
		airportTable.setItems(getAirportsToTable());
		
		/**************************************Airplane Page*****************************************/
		airplaneID.setText("");
		airplaneSize.setText("");
		airplaneToDelete.setText("");
		
		//set in airplane table
		airplaneNumber.setCellValueFactory(new PropertyValueFactory<Airplane, String>("airplaneSerialNumber"));
		airPlaneSize.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("airplaneSize"));
		airplaneTable.setItems(getAirplaneToTable());
	}

}
