package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModulesPane extends Accordion{

	private ObservableList<Module> modulesTerm1;
	private ListView<Module> unselectedTerm1;
	private ObservableList<Module> reservedModulesTerm1;
	private ListView<Module> reservedTerm1;
	private ObservableList<Module> modulesTerm2;
	private ListView<Module> unselectedTerm2;
	private ObservableList<Module> reservedModulesTerm2;
	private ListView<Module> reservedTerm2;
	
	private TitledPane t1;
	private TitledPane t2;
	
	private Button btnAdd;
	private Button btnRemove;
	private Button btnConfirm;
	private Button btnAdd2;
	private Button btnRemove2;
	private Button btnConfirm2;
	
	
	public ReserveModulesPane() {
		
		//initialise labels
		Label lblUnselectedTerm1Modules = new Label("Unselected Term 1 Modules");
		Label lblReservedTerm1Modules = new Label("Reserved Term 1 Modules");
		Label lblUnselectedTerm2Modules = new Label("Unselected Term 2 Modules");
		Label lblReservedTerm2Modules = new Label("Reserved Term 2 Modules");
		
		Label lbl30Credits = new Label("Reserve 30 credits worth of term 1 modules");
		Label lbl30Credits2 = new Label("Reserve 30 credits worth of term 2 modules");
		
		//initialise Buttons
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnConfirm = new Button("Confirm");
		
	    btnAdd2 = new Button("Add");
	    btnRemove2 = new Button("Remove");
		btnConfirm2 = new Button("Confirm");
		
		//Initialise List view
		modulesTerm1 =FXCollections.observableArrayList ();
		unselectedTerm1 = new ListView<>(modulesTerm1);
		
		//add to v
		VBox unselectedTerm1Box = new VBox();
		unselectedTerm1Box.getChildren().add(unselectedTerm1);
		unselectedTerm1.setPrefSize(500, 350);
		
		//list view
		reservedModulesTerm1 =FXCollections.observableArrayList ();
		reservedTerm1 = new ListView<>(reservedModulesTerm1);
		
		//add to v
		VBox reservedTerm1Box = new VBox();
		reservedTerm1Box.getChildren().add(reservedTerm1);	
		reservedTerm1.setPrefSize(500, 350);
		
		t1 = new TitledPane("Term 1 modules",null);//collapsible panes
		t2 = new TitledPane("Term 2 modules",null);
	
		this.getPanes().addAll(t1, t2);
	
		//CHANGE TO HBOXES
		//Adding grid Pane layout in titled pane
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(100);
		grid.setVgap(20);
		grid.setPadding(new Insets(30,30,30,30));
		
		grid.add(lblUnselectedTerm1Modules, 0, 0);
		grid.add(unselectedTerm1Box, 0, 1);
		
		grid.add(lblReservedTerm1Modules, 1, 0);
		grid.add(reservedTerm1Box, 1, 1);
		
		//Adding a HBox
		HBox buttonPane = new HBox();
		buttonPane.getChildren().addAll(lbl30Credits,btnAdd,btnRemove,btnConfirm);
		buttonPane.setSpacing(10);
		grid.add(buttonPane, 1, 2);
		
		
		t1.setContent(grid);
			
		//T2 Pane section now
				
   			modulesTerm2 =FXCollections.observableArrayList ();
			unselectedTerm2 = new ListView<>(modulesTerm2);
			
			VBox unselectedTerm2Box = new VBox();
			
			unselectedTerm2Box.getChildren().add(unselectedTerm2);
			
			unselectedTerm2.setPrefSize(500, 350);
				
			reservedModulesTerm2 =FXCollections.observableArrayList ();
				
			reservedTerm2 = new ListView<>(reservedModulesTerm2);
			VBox reservedTerm2Box = new VBox();
			reservedTerm2Box.getChildren().add(reservedTerm2);	
			reservedTerm2.setPrefSize(500, 350);
				
			
				//Adding grid Pane layout in titled pane
				GridPane grid2 = new GridPane();
				grid2.setAlignment(Pos.CENTER);
				grid2.setHgap(100);
				grid2.setVgap(20);
				grid2.setPadding(new Insets(30,30,30,30));
				
				grid2.add(lblUnselectedTerm2Modules, 0, 0);
				grid2.add(unselectedTerm2Box, 0, 1);
				
				grid2.add(lblReservedTerm2Modules, 1, 0);
				grid2.add(reservedTerm2Box, 1, 1);
				
				//Adding a HBox
				HBox buttonPane2 = new HBox();
				buttonPane2.getChildren().addAll(lbl30Credits2,btnAdd2,btnRemove2,btnConfirm2);
				buttonPane2.setSpacing(10);
				grid2.add(buttonPane2, 1, 2);
				
				
			t2.setContent(grid2);
				

		
		
	
	}
	
	//methods
	public ObservableList<Module> getModulesTerm1() {
		return modulesTerm1;
	}
	//returns list view
	public ListView<Module> getUnselectedTerm1(){
		return unselectedTerm1;
	}
	
	public ObservableList<Module> getModulesTerm2() {
		return modulesTerm2;
	}
	//returns list view
	public ListView<Module> getUnselectedTerm2(){
		return unselectedTerm2;
	}
	
	//methods
		public ObservableList<Module> getReservedModulesTerm1() {
			return reservedModulesTerm1;
		}
		//returns list view
		public ListView<Module> getReservedTerm1(){
			return reservedTerm1;
		}
		
		public ObservableList<Module> getReservedModulesTerm2() {
			return reservedModulesTerm2;
		}
		//returns list view
		public ListView<Module> getReservedTerm2(){
			return reservedTerm2;
		}
	

	//Handler methods
	public void addAddToReservedModules1Handler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
	
	public void addRemoveFromReservedModules1Handler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	
	public void addAddToReservedModules2Handler(EventHandler<ActionEvent> handler) {
		btnAdd2.setOnAction(handler);
	}
	
	public void addRemoveFromReservedModules2Handler(EventHandler<ActionEvent> handler) {
		btnRemove2.setOnAction(handler);
	}
	
	public void addConfirmModules1Handler (EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	
	public void addConfirmModules2Handler (EventHandler<ActionEvent> handler) {
		btnConfirm2.setOnAction(handler);
	}
}

