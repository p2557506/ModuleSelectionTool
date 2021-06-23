package controller;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleSelectionToolRootPane;
import view.OverviewSelectionPane;
import view.ReserveModulesPane;
import view.SelectModulesPane;
import view.CreateStudentProfilePane;
import view.ModuleSelectionToolMenuBar;

public class ModuleSelectionToolController {

	//fields to be used throughout class
	private ModuleSelectionToolRootPane view;
	private StudentProfile model;
	
	
	private CreateStudentProfilePane cspp;
	private ModuleSelectionToolMenuBar mstmb;
	private SelectModulesPane smp;
	private ReserveModulesPane rmp;
	private OverviewSelectionPane osp;
	

	public ModuleSelectionToolController(ModuleSelectionToolRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		smp = view.getSelectModulesPane();
		rmp = view.getReserveModulesPane();
		osp = view.getOverviewSelectionPane();
		

		//add courses to combobox in create student profile pane using the generateAndReturnCourses helper method below
		cspp.addCoursesToComboBox(generateAndReturnCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}

	
	//helper method - used to attach event handlers to user interface
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		//attach event handler to the select modules pane
		smp.addAddTerm1ModuleHandler(new AddTerm1ModuleHandler());
		smp.addRemoveTerm1ModuleHandler(new RemoveTerm1ModuleHandler());
		smp.addAddTerm2ModuleHandler(new AddTerm2ModuleHandler());
		smp.addRemoveTerm2ModuleHandler(new RemoveTerm2ModuleHandler());
		smp.addSubmitModulesHandler(new SubmitModulesHandler());
		smp.addResetToOriginalModulesHandler(new ResetToOriginalModulesHandler());
		
		//attach event handler to reserve module pane
		rmp.addAddToReservedModules1Handler(new AddToReservedModules1Handler());
		rmp.addRemoveFromReservedModules1Handler(new RemoveFromReservedModules1Handler());
		rmp.addAddToReservedModules2Handler(new AddToReservedModules2Handler());
     	rmp.addRemoveFromReservedModules2Handler(new RemoveFromReservedModules2Handler());
        rmp.addConfirmModules1Handler(new ConfirmModules1Handler());
        rmp.addConfirmModules2Handler(new ConfirmModules2Handler());
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
	}
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
		//retrieve data from commbo box
			Collection<Module> courseModules = cspp.getSelectedCourse().getAllModulesOnCourse();//gets all modules on course
			
			//separate out courses into term1, term2 , year long and mandatory
			//use for loop y iterate through collection
			for(Module x: courseModules) {
				if(x.getDelivery()==Schedule.TERM_1 && x.isMandatory()!= true) {
					smp.getModulesTerm1().add(x);
				
				}else if(x.getDelivery()==Schedule.TERM_1 && x.isMandatory()==true){
					smp.getModulesSelectedTerm1().add(x);
				
				}else if(x.getDelivery()==Schedule.YEAR_LONG && x.isMandatory()==true) {
					smp.getSelectedYearLongModules().add(x);
				
				}else if(x.getDelivery()==Schedule.TERM_2 && x.isMandatory()!=true) {
					smp.getModulesTerm2().add(x);
				
				}else if(x.getDelivery()==Schedule.TERM_2 && x.isMandatory()==true) {
					smp.getModulesSelectedTerm2().add(x);
				}
			}
			//if(module is term 1) {
				//add to unselcted term 1 list
			//}
			
			//add modules to list
			smp.getModulesTerm1().addAll(courseModules);
			
			
			//retrieve firstname
			String firstName = cspp.getStudentName().getFirstName();
			//Retrieve lastname
			String familyName = cspp.getStudentName().getFamilyName();
		    //add full name to profile
			osp.addFullStudentName(firstName + " "+ familyName);
			
			//retrieve p number
			String pnumber = cspp.getStudentPnumber();
			//add pnumber to overview selection profile
			osp.addPnumber(pnumber);
			
			//retrieve email
			String email = cspp.getStudentEmail();
			//add email to overview selection pane
			osp.addStudentEmail(email);
			
			//retrieve and add date
			osp.addStudentDate(cspp.getStudentDate());
			
			//retrieve coursename
			String courseName = cspp.getSelectedCourse().getCourseName();
			osp.addCourseName(courseName);
			
	    }
								
	}

	//select modules pane
	private class AddTerm1ModuleHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			//need to select module from unselected term 1 list
		Module x = 	smp.getUnselectedTerm1().getSelectionModel().getSelectedItem();
		
		//add module to selectedterm 1
			smp.addTerm1Module(x);
			
			//remove same module from unselectedterm2
			smp.getModulesTerm1().remove(x);
			//retrieve credits of module 
			int moduleCredits = x.getModuleCredits();
			//add same credits to textfield
			smp.getCreditBox1().setText(String.valueOf(moduleCredits));
			//
			
			
			//check if reached max value of credits
//			if(smp.getCreditBox1().toString() == String.valueOf(60)) {
//				return;
//			}else {//if max isnt reached add to existing value
//				int creditBox1Current = Integer.parseInt(smp.getCreditBox1().toString());
//				int sum = moduleCredits + creditBox1Current;//adds credit in box credit retrieved
//				smp.getCreditBox1().setText(String.valueOf(sum));
//			//}
			
		}
		
			
		}
	
	private class RemoveTerm1ModuleHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
			
			//need to select module from unselected term 1 list
			Module x = smp.getSelectedTerm1Modules().getSelectionModel().getSelectedItem();
			
			//remove module from selected term 1 list
				smp.removeTerm1Modules(x);
				//add same module back into unselected
				smp.getModulesTerm1().add(x);

		}
		
	}
	


	
	private class AddTerm2ModuleHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
			
			//need to select module from unselected term 1 list
			Module x = 	smp.getUnselectedTerm2().getSelectionModel().getSelectedItem();
			
			//add module to Term2 selected
				smp.addTerm2Module(x);
				//remove same module ,unselected
				smp.getModulesTerm2().remove(x);

		}
	}
	
	private class RemoveTerm2ModuleHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
			
			//need to select module from unselected term 1 list
			Module x = 	smp.getSelectedTerm2Modules().getSelectionModel().getSelectedItem();
			
			//remove module from select term 2
				smp.removeTerm2Module(x);
				//add same module back to  unselected term 2
				smp.getModulesTerm2().add(x);

		}
	}
	
	private class SubmitModulesHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
			//Take modules from unselected term1 in smp and add to unselected term1 in Reservemodulespane
			ObservableList<Module> modulesTerm1NotChosen = smp.getModulesTerm1();
		    rmp.getModulesTerm1().addAll(modulesTerm1NotChosen);
			
			//take modules from unselectedTerm2 in selectpane and add to unselected term2 in reserver pane
			ObservableList<Module> modulesTerm2NotChosen = smp.getModulesTerm2();
			rmp.getModulesTerm2().addAll(modulesTerm2NotChosen);
			
			//add selected to overview selection
			Collection<Module> selectedModules = FXCollections.observableArrayList ();
			selectedModules.addAll(smp.getModulesSelectedTerm1());
			selectedModules.addAll(smp.getModulesSelectedTerm2());
			
			for(Module x: selectedModules) {
				String modulesSelected =x.actualToString();
				osp.getSelectedModulesAppear().appendText(modulesSelected);
			}
		}
		}
	
	private class ResetToOriginalModulesHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			//clear and add original modules back
			smp.getModulesTerm1().clear();
			smp.getModulesTerm2().clear();
			smp.getModulesSelectedTerm1().clear();
			smp.getModulesSelectedTerm2().clear();
			smp.getSelectedYearLongModules().clear();
			
			Collection<Module> courseModules = cspp.getSelectedCourse().getAllModulesOnCourse();
			
			for(Module x: courseModules) {
				if(x.getDelivery()==Schedule.TERM_1 && x.isMandatory()!= true) {
					smp.getModulesTerm1().add(x);
				
				}else if(x.getDelivery()==Schedule.TERM_1 && x.isMandatory()==true){
					smp.getModulesSelectedTerm1().add(x);
				
				}else if(x.getDelivery()==Schedule.YEAR_LONG && x.isMandatory()==true) {
					smp.getSelectedYearLongModules().add(x);
				
				}else if(x.getDelivery()==Schedule.TERM_2 && x.isMandatory()!=true) {
					smp.getModulesTerm2().add(x);
				
				}else if(x.getDelivery()==Schedule.TERM_2 && x.isMandatory()==true) {
					smp.getModulesSelectedTerm2().add(x);
				}
			}
			
		}
	}
	
	private class AddToReservedModules1Handler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Module x = 	rmp.getUnselectedTerm1().getSelectionModel().getSelectedItem();
			
			//get list from reserved term 1 and add
				rmp.getReservedModulesTerm1().add(x);
				
				//remove same module from unselectedterm1
				rmp.getModulesTerm1().remove(x);
		}
	}
	
	private class RemoveFromReservedModules1Handler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
			
			//need to select module from unselected term 1 list
			Module x = rmp.getReservedTerm1().getSelectionModel().getSelectedItem();
			
			//remove module from reserved term 1 list
				rmp.getReservedModulesTerm1().remove(x);
				//add same module back into unselected
				rmp.getModulesTerm1().add(x);

		}
		
	}
	
	private class AddToReservedModules2Handler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Module x = 	rmp.getUnselectedTerm2().getSelectionModel().getSelectedItem();
			
			//get list from reserved term 1 and add
				rmp.getReservedModulesTerm2().add(x);
				
				//remove same module from unselectedterm1
				rmp.getModulesTerm2().remove(x);
		}
	}
	
	private class RemoveFromReservedModules2Handler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
			
			//need to select module from unselected term 1 list
			Module x = rmp.getReservedTerm2().getSelectionModel().getSelectedItem();
			
			//remove module from reserved term 1 list
				rmp.getReservedModulesTerm2().remove(x);
				//add same module back into unselected
				rmp.getModulesTerm2().add(x);

		}
		
	}

	private class ConfirmModules1Handler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e) {
				
				Collection<Module> reservedModules = FXCollections.observableArrayList ();
				reservedModules.addAll(rmp.getReservedModulesTerm1());
				
				
				for(Module y: reservedModules) {
					String modulesReserved =y.actualToString();
					osp.getReservedModulesAppear().appendText(modulesReserved);
				
				
			}
				
	
		
		}
	}
		private class ConfirmModules2Handler implements EventHandler<ActionEvent>{
			public void handle (ActionEvent e) {
					
					Collection<Module> reservedModules2 = FXCollections.observableArrayList ();
					reservedModules2.addAll(rmp.getReservedModulesTerm2());
					
					
					for(Module y: reservedModules2) {
						String modulesReserved2 =y.actualToString();
						osp.getReservedModulesAppear().appendText(modulesReserved2);
					
					
				}
					
		
			
			}

	}
	
	
		
//		public ChangeListener<Module> getModuleChangeListener() {
//			return (observable, oldValue, newValue) -> (smp.getUnselectedTerm1().getSelectionModel().setSelectionMode(newValue));
//		}
		
	

	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndReturnCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}
   
	
		
}
