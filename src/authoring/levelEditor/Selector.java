package authoring.levelEditor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import engine.AuthoringModelController;
import factory.AlertFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import util.PropertiesGetter;

/**
 * 
 * @author Venkat
 * Super class that allows one to create different kinds of selector areas that allow 
 * people to set different parameters for the victory conditions that they have selected.
 *
 */
public abstract class Selector extends VBox{
	private final String ALERT_HEADER_TEXT = "invalidInput";
	private final String UPDATE_BUTTON_TEXT = "updateButton";
	private final String DONE_LABEL = "doneLabel";
	private final String ALERT_INFO_MESSAGE = "invalidInputInfo";
	
	private List<CheckBox> checkBoxes;
	private AuthoringModelController myController;
	private TextField amount;
	private Button update;
	private Button done;
	private List<Integer> selectedCheckBoxes;
	
	/**
	 * Constructor for the class. Takes in an authoring controller.
	 * 
	 * @param controller
	 */
	public Selector(AuthoringModelController controller) {
		myController = controller;
		amount = new TextField();
		update = new Button(PropertiesGetter.getProperty(UPDATE_BUTTON_TEXT));
		done = new Button(PropertiesGetter.getProperty(DONE_LABEL));
		done.setOnAction(e->hide());
	}
	
	/**
	 * Makes this VBox visible.
	 */
	void show() {
		setVisible(true);
	}
	
	/**
	 * Hides this VBox.
	 */
	private void hide() {
		setVisible(false);
	}
	
	/**
	 * 
	 * @return myController the subclasses that ask for it.
	 */
	protected AuthoringModelController getController() {
		return myController;
	}
	
	/**
	 * Protected method that allows subclasses to set the text in the amount TextBox.
	 * @param s 
	 */
	protected void setAmountPromptText(String s) {
		amount.setPromptText(s);
	}
	
	
	/**
	 * Allows subclasses to set eventhandler of the update button.
	 * @param value
	 */
	protected void setUpdateLambda(EventHandler<ActionEvent> value) {
		update.setOnAction(value);
	}
	
	/**
	 * Creates checkboxes of selected levels to then perform subselection on.
	 * @param lvs
	 */
	public void createCheckBoxes(List<Integer> lvs) {
		selectedCheckBoxes = lvs;
		checkBoxes = new ArrayList<>();
		for (Integer i : lvs) {
			CheckBox c =  new CheckBox();
			c.setAllowIndeterminate(false);
			c.setText(Integer.toString(i));
			checkBoxes.add(c);
		}
		this.getChildren().clear();
		this.getChildren().addAll(checkBoxes);
		this.getChildren().addAll(amount, update, done);	
	}
	
	/**
	 * Returns a list of the selectedCheckBoxes from the GameEnderConditions
	 * 
	 * @return
	 */
	protected List<Integer> getTotalCheckBoxes(){
		return selectedCheckBoxes;
	}
	
	/**
	 * This returns a Set of the levels that have been selected to have their victory paramters set.
	 * @return
	 */
	protected Set<Integer> getSelectedLevels() {
		Set<Integer> selectedLevels = new HashSet<>();
		for (int i = 0; i<checkBoxes.size(); i++) {
			if(checkBoxes.get(i).isSelected()) {
				selectedLevels.add(Integer.parseInt(checkBoxes.get(i).getText()));
				checkBoxes.get(i).fire();
			}
		}
	return selectedLevels;
	}
	
	/**
	 * returns an int typed into the amount TextBox, or opens an alert if not and returns -1.
	 * @return
	 */
	protected int getAmount() {
		String s = amount.getText();
		amount.clear();
		try {
			return Integer.parseInt(s);
		}catch(Exception nfe){
			new AlertFactory(PropertiesGetter.getProperty(ALERT_INFO_MESSAGE),PropertiesGetter.getProperty(ALERT_HEADER_TEXT),AlertType.ERROR);
			return -1;
		}
		
	}

}