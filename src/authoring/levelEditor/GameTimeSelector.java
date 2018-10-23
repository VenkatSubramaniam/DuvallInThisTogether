package authoring.levelEditor;

import java.util.Set;
import engine.AuthoringModelController;
import util.PropertiesGetter;

public class GameTimeSelector  extends Selector{
	private static final int TIME_CYCLE_CONVERTER = 60;
	private static final String TIME_SELECTOR_PROMPT_TEXT = "timeLimitPrompt";
	private static final int TIME_DEFAULT = 1000;
	private static final int INVALID_AMOUNT = -1;

	public GameTimeSelector(AuthoringModelController controller) {
		super(controller);
		setAmountPromptText(PropertiesGetter.getProperty(TIME_SELECTOR_PROMPT_TEXT));
		setUpdateLambda(e->record());
	}

	private void record() {
		Set<Integer> selected = getSelectedLevels();
		int currLv = getController().getCurrentLevel();
		int amount = getAmount();
		if (amount==INVALID_AMOUNT) {
			for(Integer i: getTotalCheckBoxes()) {
				getController().setLevel(i);
				getController().setLevelTimeLimit(TIME_DEFAULT);
			}
		}
		else {
			for (Integer i : getTotalCheckBoxes()) {
				getController().setLevel(i);
				if(selected.contains(i)) {
					getController().setLevelTimeLimit(amount*TIME_CYCLE_CONVERTER);
				}
				else {
					getController().setLevelTimeLimit(TIME_DEFAULT);
				}
			}
		}
		getController().setLevel(currLv);
	}
}
