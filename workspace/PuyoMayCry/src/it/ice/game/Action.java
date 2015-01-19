package it.ice.game;

public abstract class Action {
	protected Entity agent;
	private boolean persistent;

	public Action(Entity agent) {
		this.agent = agent;
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	protected abstract void run();
}
