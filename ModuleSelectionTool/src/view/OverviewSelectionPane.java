package view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OverviewSelectionPane extends GridPane {

	private Button btnOverviewSelection;
	private TextArea profile;
	private TextArea selectedModules;
	private TextArea reservedModules;
	
	public OverviewSelectionPane() {
		
		//Initialise Button
		Button saveOverview = new Button("Save Overview");
		
		//Make 3 text Areas
		 profile = new TextArea("Profile will appear here");
		 selectedModules = new TextArea("Selected modules will appear here");
		 reservedModules = new TextArea("Reserved modules will appear here");
		
		//gridPane adjustments
		this.setHgap(20);
		this.setVgap(20);
		this.setPadding(new Insets(20,20,20,20));
		
		//HBox
		HBox saveOverviewContainer = new HBox();
		saveOverviewContainer.getChildren().add(saveOverview);
		saveOverviewContainer.setAlignment(Pos.BOTTOM_CENTER);
		
		//
		VBox profileContainer = new VBox();
		profile.setPrefRowCount(10);
		profile.setWrapText(true);
		
		profileContainer.getChildren().add(profile);
		
		
		VBox selectedModulesContainer = new VBox();
		selectedModulesContainer.getChildren().add(selectedModules);
		selectedModules.setPrefRowCount(10);
		profile.setWrapText(true);
		
		VBox reservedModulesContainer = new VBox();
		reservedModulesContainer.getChildren().add(reservedModules);
		
		this.add(profileContainer, 1, 0);
		this.add(selectedModulesContainer, 0, 1);
		this.add(reservedModulesContainer, 2, 1);
		this.add(saveOverviewContainer, 1 , 2);
		
	}
	
	
	public void addFullStudentName(String fullname) {
		profile.setText("Name: " + fullname +"\n");
	}
	
	public void addPnumber(String pnumber) {
		profile.appendText("PNo: " + pnumber +"\n");
	}
	
	public void addStudentEmail(String mail) {
		profile.appendText("Email: " + mail + "\n");
	}
	
	public void addStudentDate(LocalDate date) {
		profile.appendText("Date: " + date.toString() + "\n");
	}
	
	public void addCourseName(String coursename) {
		profile.appendText("Course: " + coursename);
	}
	
	public TextArea getSelectedModulesAppear() {
		return selectedModules;
	}
	
	public TextArea getReservedModulesAppear() {
		return reservedModules;
	}
	
	public void addOverviewSelectionHandler(EventHandler<ActionEvent> handler) {
		btnOverviewSelection.setOnAction(handler);
	
}
	
}
