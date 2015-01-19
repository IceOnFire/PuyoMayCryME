package it.ice.game;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Entity implements PropertyListener, EventListener {
	protected Hashtable properties;
	protected Hashtable children;
	protected Hashtable states;
	protected State activeState;
	protected Hashtable availableActions;
	protected Hashtable enabledActions;

	public Entity() {
		super();
		properties = new Hashtable();
		children = new Hashtable();
		states = new Hashtable();
		availableActions = new Hashtable();
		enabledActions = new Hashtable();
	}

	public void addProperty(int propertyId, Property property) {
		properties.put(new Integer(propertyId), property);
	}

	public void removeProperty(int propertyId) {
		properties.remove(new Integer(propertyId));
	}

	public Property getProperty(int propertyId) {
		return (Property) properties.get(new Integer(propertyId));
	}

	public void addChild(int childId, Entity child) {
		children.put(new Integer(childId), child);
	}

	public void removeChild(int childId) {
		children.remove(new Integer(childId));
	}

	public Entity getChild(int childId) {
		return (Entity) children.get(new Integer(childId));
	}

	public void addState(int stateId, State state) {
		states.put(new Integer(stateId), state);
	}

	public void removeState(int stateId) {
		states.remove(new Integer(stateId));
	}

	public State getState(int stateId) {
		return (State) states.get(new Integer(stateId));
	}

	public State getActiveState() {
		return activeState;
	}

	public void setActiveState(int stateId) {
		if (activeState != null) {
			activeState.dispose();
		}
		activeState = (State) states.get(new Integer(stateId));
		activeState.activate();
	}

	public void addAction(int actionId, Action action) {
		availableActions.put(new Integer(actionId), action);
	}

	public void enableAction(int actionId) {
		Integer id = new Integer(actionId);
		Object action = availableActions.remove(id);
		enabledActions.put(id, action);
	}

	public void disableAction(int actionId) {
		Integer id = new Integer(actionId);
		Object action = enabledActions.remove(id);
		availableActions.put(id, action);
	}

	public void runAction(int actionId) {
		Action action = (Action) enabledActions.get(new Integer(actionId));
		action.run();
		if (!action.isPersistent()) {
			// action.dispose();
			disableAction(actionId);
		}
	}

	private void runActions() {
		Enumeration actionIds = enabledActions.keys();
		while (actionIds.hasMoreElements()) {
			Integer actionId = (Integer) actionIds.nextElement();
			runAction(actionId.intValue());
		}
	}

	public void update() {
		Enumeration properties = this.properties.elements();
		while (properties.hasMoreElements()) {
			Property property = (Property) properties.nextElement();
			property.update();
		}

		activeState.update();

		runActions();

		Enumeration children = this.children.elements();
		while (children.hasMoreElements()) {
			Entity child = (Entity) children.nextElement();
			child.update();
		}
	}
}
