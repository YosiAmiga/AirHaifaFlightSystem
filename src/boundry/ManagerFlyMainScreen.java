package boundry;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ManagerFlyMainScreen implements Initializable{
	@FXML
	private AnchorPane rootPane;
	@FXML
	private StackPane mainPane;
	@FXML
	private ToggleButton add;
	@FXML
	private ToggleButton reports;
	@FXML
	private ToggleButton reports1;
	@FXML
	private ToggleButton exit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	/****Enter the manager adding,removing, and updating page****/
	//works
	public void loadAdd(ActionEvent e)
	{
		try {
			if(add.isSelected())
			{

				TabPane pane=FXMLLoader.load(getClass().getResource("ManagerFlyMain.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	//works
	public void loadReport(ActionEvent e)
	{
		try {
			if(reports.isSelected())
			{

				Pane pane=FXMLLoader.load(getClass().getResource("ManagerReportsPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	//works
	public void loadReport1(ActionEvent e)
	{
		try {
			if(reports1.isSelected())
			{

				Pane pane=FXMLLoader.load(getClass().getResource("ManagerReportByDestination.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	/***Exit the  program***/
	public void exitProgram(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to exit the program?");
		al.setTitle("Exit Program");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			System.exit(0);
		}
	}

}
