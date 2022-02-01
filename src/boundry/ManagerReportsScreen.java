package boundry;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import control.ControlFlight;
import control.ControlReports;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.swing.JRViewer;

public class ManagerReportsScreen implements Initializable {


	@FXML
	private DatePicker departureDate;
	@FXML
	private DatePicker arrivalDate;
	@FXML
	private TextField seatsQuantity;
	@FXML
	private Button btnCreateReport;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
	
	public void createReport() {
		try {

			
			Date d = Date.valueOf(departureDate.getValue());
			Date a = Date.valueOf(arrivalDate.getValue());
			int seats = Integer.parseInt(seatsQuantity.getText());

				JFrame frame = ControlReports.getInstance().getBigFlightsReport(d,a, seats);
				frame.setVisible(true);
			}
			
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
