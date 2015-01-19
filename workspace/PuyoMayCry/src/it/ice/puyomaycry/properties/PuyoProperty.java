package it.ice.puyomaycry.properties;

import it.ice.game.Entity;
import it.ice.game.Property;
import it.ice.puyomaycry.PMCController;

import java.util.Random;

public class PuyoProperty extends Property {
	private final static int DEFAULT_AMOUNT = 5;
	private final static int DECREASE_TIME_MILLIS = 5000;

	private int maxAmount;
	private int amount;
	private int decreaseInterval;
	private int time;

	public PuyoProperty(Entity entity) {
		this(entity, DEFAULT_AMOUNT, DECREASE_TIME_MILLIS);
	}

	public PuyoProperty(Entity entity, int maxAmount, int decreaseTime) {
		super();
		this.maxAmount = maxAmount;
		amount = maxAmount;
		this.decreaseInterval = decreaseTime;
		time = 0;
	}

	public void update() {
		new Thread() {
			public void run() {
				time += -(new Random().nextInt(200) >> 1)
						+ PMCController.MILLISECONDS_PER_TIMESTEP;
				if (time >= decreaseInterval) {
					decrease();
					notifyPropertyChanged();
					time = 0;
				}
			}
		}.start();

	}

	public Object getValue() {
		return new Integer(amount);
	}

	public void increase() {
		if (amount < maxAmount) {
			amount++;
			System.out.println(amount);
		}
	}

	public void decrease() {
		if (amount > 0) {
			amount--;
		}
		System.out.println(amount);
	}
}
