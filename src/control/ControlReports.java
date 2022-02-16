package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.Airplane;
import entity.Consts;
import entity.Flight;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class ControlReports {
	
	 private static ControlReports _instance;
	    public static ControlReports getInstance() {
	        if (_instance == null)
	            _instance = new ControlReports();
	        return _instance;
	    }
	    
	    
	    public ArrayList<Flight> getBigFlightsReport(int seats,Date departure, Date arrival) {
			 ArrayList<Flight> flights= new ArrayList<Flight>();

				try {
		            Class.forName(Consts.JDBC_STR);
		            
		            try {
		            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
		            		//plane SQL
		                    PreparedStatement stmt = conn.prepareStatement(Consts.BIG_FLIGHTS_REPORT);
		                    int i=1;
		                    stmt.setInt(i++, seats);
		                    stmt.setDate(i++, departure);
		                    stmt.setDate(i++, arrival);

		                    try(ResultSet rs = stmt.executeQuery())
		                    {
		    	            	while (rs.next()) {
		    	            		int j = 1;
		    	            		flights.add(
		    	            			new Flight(
		    	            					rs.getString(j++),
		    	            					rs.getTimestamp(j++),
		    	            					rs.getTimestamp(j++),
		    	            					rs.getString(j++),
		    	            					rs.getString(j++),
		    	            					rs.getInt(j++),
		    	            					rs.getInt(j++)
		    	            					));	    	            		
		    	            		}
		                    	
		                    }
		                    return flights;
		                   
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();

		        }
		        return flights;

	    }
	    

	    
	    
	   
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	    
	    

}
