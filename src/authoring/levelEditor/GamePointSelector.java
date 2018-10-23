package authoring.levelEditor;

import java.util.Set;

import engine.AuthoringModelController;
import util.PropertiesGetter;

public class GamePointSelector extends Selector{
	private static final int INVALID_AMOUNT = -1;
	private final int POINT_DEFAULT = 100;
	private final String POINT_SELECTOR_PROMPT_TEXT = "pointPrompt";
	
	public GamePointSelector(AuthoringModelController controller) {
		super(controller);
		setAmountPromptText(PropertiesGetter.getProperty(POINT_SELECTOR_PROMPT_TEXT));
		setUpdateLambda(e->record());
	}

	private void record() {
		Set<Integer> selected = getSelectedLevels();
		int currLv = getController().getCurrentLevel();
		int amount = getAmount();
		if (amount==INVALID_AMOUNT) {
			for(Integer i: getTotalCheckBoxes()) {
				getController().setLevel(i);
				getController().setLevelPointQuota(POINT_DEFAULT);
			}
		}
		else {
			for (Integer i : getTotalCheckBoxes()) {
				getController().setLevel(i);
				if(selected.contains(i)) {
					getController().setLevelPointQuota(amount);
				}
				else {
					getController().setLevelPointQuota(POINT_DEFAULT);
				}
			}
		}
		getController().setLevel(currLv);
	}
}
