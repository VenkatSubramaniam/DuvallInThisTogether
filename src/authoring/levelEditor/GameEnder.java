package authoring.levelEditor;

import engine.AuthoringModelController;
import engine.authoring_engine.AuthoringController;
import javafx.scene.layout.VBox;
/**
 * 
 * @author Venkat 
 * Class that contains and manipulates everything related to selecting victory conditions and loss 
 * conditions. 
 * Including it in the masterpiece as it shows how I distinguished between different components, and
 * I think that this encapsulation was necessary.
 *
 *
 */
public class GameEnder extends VBox{
	private GameEnderConditions conditions;
	private GameHealthSelector health;
	private GamePointSelector points;
	private GameTimeSelector time;
	
	/**
	 * Constructor for the class.
	 * @param controller
	 */
	public GameEnder(AuthoringModelController controller) {
		conditions = new GameEnderConditions(controller);
		health = new GameHealthSelector(controller);
		points = new GamePointSelector(controller);
		time = new GameTimeSelector(controller);
		this.getChildren().addAll(conditions, health, points, time);
		this.setPrefWidth(300);
		this.setSpacing(100);
		}
	
	/**
	 * Updates the conditions when needed.
	 */
	public void update() {
		conditions.update();
	}
	
	/**
	 * Sets the various objects that are required by the GameEnderConditions.
	 * @param r
	 */
	public void setRecorder(GameEnderRecorder r) {
		conditions.setRecorder(r);
		conditions.setTimeRecorder(time);
		conditions.setPointRecorder(points);
		conditions.setHealthManager(health);
	}
	
}
