package view;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;
import model.Schedule;

public class SelectModulesPane extends GridPane {
	
	private ListView<Module> unselectedTerm1;
    private ObservableList<Module> modulesTerm1;
    private ListView<Module> unselectedTerm2;
    private ObservableList<Module> modulesTerm2;
    private ListView<Module> selectedYearLong;
    private ObservableList<Module> selectedYearLongModules;
    private ListView<Module> selectedTerm1Modules;
    private ObservableList<Module> modulesSelectedTerm1;
    private ListView<Module> selectedTerm2Modules;
    private ObservableList<Module> modulesSelectedTerm2;
  //  private ListView<Module> 
    //private ObservableList<Module>
	private Button btnAdd;
	private Button btnRemove;
	private Button btnReset;
	private Button btnSubmit;
	private Button btnAdd2;
	private Button btnRemove2;

	private TextField creditField1;
	private TextField creditField2;
	
	
	public SelectModulesPane() {
		
		
		this.setHgap(70);
		this.setVgap(10);
		
		//Initialise Buttons
		btnAdd = new Button("Add");
	    btnRemove = new Button("Remove");
		
		//Initialise Labels
	     Label lblUnselectedTerm1Modules = new Label("Unselected Term 1 modules ");
		Label lblTerm1 = new Label("Term 1 ");
		Label lblUnselectedTerm2Modules = new Label("Unselected Term 2 modules ");
		Label lblTerm2 = new Label("Term 2");
		Label lblSelectedYearLongModules = new Label("Selected Year Long modules ");
		Label lblSelectedTerm1Modules = new Label("Selected Term 1 modules ");
		Label lblSelectedTerm2Modules = new Label("Selected Term 2 modules ");
		Label lblCurrentTerm1Credits = new Label("Current term 1 credits: ");
		Label lblCurrentTerm2Credits = new Label("Current term 2 credits: "); 
		
		//Unselected Term1
		VBox unselectedTerm1Container = new VBox();
		unselectedTerm1Container.setAlignment(Pos.TOP_CENTER);
		unselectedTerm1Container.setPadding(new Insets(20,20,20,20));
		unselectedTerm1Container.setPrefSize(600, 300);
		lblUnselectedTerm1Modules.setPadding(new Insets(10,10,10,10));
		this.add(lblUnselectedTerm1Modules, 0, 0);
		this.add(unselectedTerm1Container, 0, 1);
		
		//add Hbox for buttons and label
		
		HBox buttonsContainerTerm1 = new HBox();	//top left//just under
		buttonsContainerTerm1.setAlignment(Pos.CENTER);
		buttonsContainerTerm1.setSpacing(10);
		buttonsContainerTerm1.getChildren().add(lblTerm1);//under
		btnAdd.setMaxWidth(Double.MAX_VALUE);
		btnRemove.setMaxWidth(Double.MAX_VALUE);
		buttonsContainerTerm1.getChildren().add(btnAdd);//to the side
		buttonsContainerTerm1.getChildren().add(btnRemove);//to the side
		this.add(buttonsContainerTerm1, 0, 2);
		//adds vbox to grid
		
		 //UnselectedTerm2
		 VBox unselectedTerm2Container = new VBox();
		unselectedTerm2Container.setAlignment(Pos.TOP_CENTER);
		 unselectedTerm2Container.setPadding(new Insets(20,20,20,20));
			unselectedTerm2Container.setPrefSize(600, 300);
			lblUnselectedTerm2Modules.setPadding(new Insets(10,10,10,10));
			this.add(lblUnselectedTerm2Modules, 0, 3);
			this.add(unselectedTerm2Container, 0, 4);
			
			//Initialise Buttons
			btnAdd2 = new Button("Add");
			btnRemove2 = new Button("Remove");
		
			//Create button hbox 2
			HBox buttonContainerTerm2 = new HBox();
			buttonContainerTerm2.setAlignment(Pos.CENTER);
			buttonContainerTerm2.getChildren().add(lblTerm2);
			buttonContainerTerm2.setSpacing(10);
			buttonContainerTerm2.getChildren().add(btnAdd2);
			buttonContainerTerm2.getChildren().add(btnRemove2);
			this.add(buttonContainerTerm2, 0, 5);
			
//			creat hbox credit hbox
	    	HBox creditBox1 = new HBox();
			creditField1 = new TextField("0");
	    	creditField1.setPrefWidth(70);
			lblCurrentTerm1Credits.setPadding(new Insets(10,10,10,10));
			creditBox1.getChildren().add(lblCurrentTerm1Credits);
			creditBox1.getChildren().add(creditField1);
			this.add(creditBox1, 0, 6);
			
			//SelectedYearLong
			VBox selectedYearLongContainer = new VBox();
			selectedYearLongContainer.setPadding(new Insets(20,20,20,20));
			selectedYearLongContainer.setPrefSize(600, 150);
			selectedYearLongContainer.setMaxWidth(Double.MAX_VALUE);
			
			
			this.add(lblSelectedYearLongModules, 1, 0);
			this.add(selectedYearLongContainer, 1, 1);
			
			//SelectedTerm1
			VBox selectedTerm1Container = new VBox();
			selectedTerm1Container.setPadding(new Insets(20,20,20,20));
			selectedTerm1Container.setPrefSize(600, 300);
			selectedTerm1Container.setMaxWidth(Double.MAX_VALUE);
			selectedTerm1Container.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
		    
			
			this.add(lblSelectedTerm1Modules, 1, 2);
			this.add(selectedTerm1Container, 1, 3);
			
			//SelectedTerm2
			VBox selectedTerm2Container = new VBox();
			selectedTerm2Container.setPadding(new Insets(20,20,20,20));
			selectedTerm2Container.setPrefSize(600,300);
			
			
			
			this.add(lblSelectedTerm2Modules, 1, 4);
			this.add(selectedTerm2Container, 1, 5);
//			
//			//Hbox for credits2
	        HBox creditBox2 = new HBox();
			creditField2 = new TextField();
			creditField2.setPrefWidth(70);
	        creditBox2.getChildren().add(lblCurrentTerm2Credits);
			creditBox2.getChildren().add(creditField2);
			this.add(creditBox2, 1, 6);
			
			//Initialise Buttons
			btnReset = new Button("Reset");
			btnSubmit = new Button("Submit");
			
			//Create button hbox 3
			HBox buttonContainer3 = new HBox();
			
			buttonContainer3.getChildren().add(btnReset);
			buttonContainer3.getChildren().add(btnSubmit);
			this.add(buttonContainer3, 1,  7);
					 
	
		//initialise list views
		modulesTerm1 =FXCollections.observableArrayList ();
		unselectedTerm1 = new ListView<>(modulesTerm1);
		
		
		modulesTerm2 =FXCollections.observableArrayList ();
		unselectedTerm2 = new ListView<>(modulesTerm2);
		
		selectedYearLongModules =FXCollections.observableArrayList ();
		selectedYearLong = new ListView<>(selectedYearLongModules);
		
		 modulesSelectedTerm1 =FXCollections.observableArrayList ();
		 selectedTerm1Modules = new ListView<>(modulesSelectedTerm1);
		
		modulesSelectedTerm2 =FXCollections.observableArrayList ();
		selectedTerm2Modules = new ListView<>(modulesSelectedTerm2);
		
		
		//adds list views to contiainer
		unselectedTerm1Container.getChildren().add(unselectedTerm1);//adds listview to container
		unselectedTerm1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		unselectedTerm2Container.getChildren().add(unselectedTerm2);
		unselectedTerm2.setMaxHeight(Double.MAX_VALUE);
		
		selectedYearLongContainer.getChildren().add(selectedYearLong);
		selectedYearLong.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		selectedTerm1Container.getChildren().add(selectedTerm1Modules);
		selectedTerm1Modules.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		selectedTerm2Container.getChildren().add(selectedTerm2Modules);
		selectedTerm1Modules.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		//single selection change listener
		
		

		
		unselectedTerm1.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
			System.out.println(newValue); 
		});
		
		
	}

	//Methods
	public void addTerm1Module(Module module) {
		modulesSelectedTerm1.add(module);
	}
	
	public void removeTerm1Modules(Module module) {
		modulesSelectedTerm1.remove(module);
	}
	public void addTerm2Module(Module module) {
		modulesSelectedTerm2.add(module);
	}
	public void removeTerm2Module(Module module) {
		modulesSelectedTerm2.remove(module);
	}
	

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

	public ObservableList<Module> getSelectedYearLongModules() {
		return selectedYearLongModules;
	}
	
	
	//returns list view
	public ListView<Module> getSelectedYearLong(){
		return selectedYearLong;
	}
	
	public ObservableList<Module> getModulesSelectedTerm1() {
		return modulesSelectedTerm1;
	}
	//returns list view
	public ListView<Module> getSelectedTerm1Modules(){
		return selectedTerm1Modules;
	}

	public ObservableList<Module> getModulesSelectedTerm2() {
		return modulesSelectedTerm2;
	}
	//returns list view
	public ListView<Module> getSelectedTerm2Modules(){
		return selectedTerm2Modules;
	}
	

	//returns textfield
	public TextField getCreditBox1() {
		return creditField1;
	}
	
	public TextField getCreditBox2() {
		return creditField2;
	}

	public void setCreditBox1(String term1Credits) {
		creditField1.setText(term1Credits);
	}
	
	//Handlers start here
	public void addAddTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
    //
	public void addRemoveTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	//
	public void addAddTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
		btnAdd2.setOnAction(handler);
	}
	//method to 
	public void addRemoveTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
		btnRemove2.setOnAction(handler);
	}
	
	public void addResetToOriginalModulesHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}

	public void addSubmitModulesHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
	public ChangeListener<Module> selectModuleChangeListener() {
		return (observable, oldValue, newValue) -> System.out.println(newValue);
			
		}
	}

	
	
	

