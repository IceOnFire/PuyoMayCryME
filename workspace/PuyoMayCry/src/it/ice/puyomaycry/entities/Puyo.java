package it.ice.puyomaycry.entities;

import it.ice.game.Entity;
import it.ice.game.Event;
import it.ice.game.EventConstants;
import it.ice.game.Property;
import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.states.States;

public class Puyo extends Entity {
	private boolean updateState() {
		Integer affetto = (Integer) getProperty(Properties.AFFETTO).getValue();
		Integer cibo = (Integer) getProperty(Properties.CIBO).getValue();

		if (affetto.intValue() <= 0) {
			setActiveState(States.ARRABBIATO);
			return true;
		}
		if (cibo.intValue() <= 0) {
			setActiveState(States.AFFAMATO);
			return true;
		}
		return false;
	}

	public void onPropertyChange(Property property) {
		updateState();
	}

	public void onEvent(Event event) {
		if (event.getId() == EventConstants.ANIMATION_END) {
			if (activeState == getState(States.AVANTI)) {
				setActiveState(States.INDIETRO);
			} else {
				if (!updateState()) {
					setActiveState(States.AVANTI);
				}
			}
		}
	}
}
