package it.ice.game;

import java.util.Vector;

public abstract class Property {
	protected Entity entity;
	private Vector listeners;

	protected Property() {
		listeners = new Vector();
	}

	public void addPropertyListener(PropertyListener listener) {
		listeners.addElement(listener);
	}

	protected void notifyPropertyChanged() {
		for (int i = 0; i < listeners.size(); i++) {
			PropertyListener listener = (PropertyListener) listeners.elementAt(i);
			listener.onPropertyChange(this);
		}
	}

	protected abstract void update();
	public abstract Object getValue();
}
