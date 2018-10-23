package authoring.levelEditor;

import java.util.Set;
import engine.AuthoringModelController;
import util.PropertiesGetter;

public class GameHealthSelector extends Selector{
	private static final int INVALID_AMOUNT = -1;
	private final int HEALTH_DEFAULT = 100;
	private final String HEALTH_SELECTOR_PROMPT_TEXT = "healthAmount";

	public GameHealthSelector(AuthoringModelController controller) {
	super(controller);
	setAmountPromptText(PropertiesGetter.getProperty(HEALTH_SELECTOR_PROMPT_TEXT));
	setUpdateLambda(e->record());
	}

	private void record() {
		Set<Integer> selected = getSelectedLevels();
		int currLv = getController().getCurrentLevel();
		int amount = getAmount();
		if (amount==INVALID_AMOUNT) {
			for(Integer i: getTotalCheckBoxes()) {
				getController().setLevel(i);
				getController().setLevelPointQuota(HEALTH_DEFAULT);
			}
		}
		else {
			for (Integer i : getTotalCheckBoxes()) {
				getController().setLevel(i);
				if(selected.contains(i)) {
					getController().setLevelHealth(amount);
				}
				else {
					getController().setLevelHealth(HEALTH_DEFAULT);
				}
			}
		}
		getController().setLevel(currLv);
	}

	
}
