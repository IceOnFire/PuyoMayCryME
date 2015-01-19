package it.ice.puyomaycry.actions;

import it.ice.game.Action;
import it.ice.game.Entity;
import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.puyomaycry.states.States;

public class MangiaAction extends Action {
	public MangiaAction(Entity agent) {
		super(agent);
	}

	protected void run() {
		PuyoProperty cibo = (PuyoProperty) agent
				.getProperty(Properties.CIBO);
		cibo.increase();
		agent.setActiveState(States.MANGIANTE);
	}
}
