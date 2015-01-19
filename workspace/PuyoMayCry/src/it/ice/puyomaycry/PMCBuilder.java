package it.ice.puyomaycry;

import it.ice.game.Action;
import it.ice.game.Entity;
import it.ice.game.Form;
import it.ice.game.Property;
import it.ice.game.State;
import it.ice.puyomaycry.actions.Actions;
import it.ice.puyomaycry.actions.CoccolaAction;
import it.ice.puyomaycry.actions.MangiaAction;
import it.ice.puyomaycry.entities.Game;
import it.ice.puyomaycry.entities.Puyo;
import it.ice.puyomaycry.forms.Forms;
import it.ice.puyomaycry.forms.J2MEForm;
import it.ice.puyomaycry.forms.PuyoForm;
import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.puyomaycry.states.States;

import java.io.IOException;

import javax.microedition.lcdui.game.Sprite;

public class PMCBuilder {
	private Entity game;

	public void buildGame() throws IOException {
		game = new Game();

		State defaultState = new State();
		Form backgroundForm = new J2MEForm(getImagePath(Forms.BACKGROUND), 64,
				64);
		defaultState.setForm(backgroundForm);
		game.addState(States.DEFAULT, defaultState);
		game.setActiveState(States.DEFAULT);
	}

	public void buildPuyo() throws IOException {
		Entity puyo = new Puyo();
		game.addChild(Game.PUYO, puyo);

		/* proprietà */
		Property affetto = new PuyoProperty(puyo);
		affetto.addPropertyListener(puyo);
		puyo.addProperty(Properties.AFFETTO, affetto);

		Property cibo = new PuyoProperty(puyo);
		cibo.addPropertyListener(puyo);
		puyo.addProperty(Properties.CIBO, cibo);

		/* stati */
		State nascente = new State();
		Form form = new PuyoForm(getImagePath(Forms.PUYO_NASCE));
		nascente.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.NASCENTE, nascente);

		State avanti = new State();
		form = new PuyoForm(getImagePath(Forms.PUYO_DEFAULT));
		avanti.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.AVANTI, avanti);

		State indietro = new State();
		form = new PuyoForm(getImagePath(Forms.PUYO_DEFAULT));
		Sprite avatar = (Sprite) form.getAvatar();
		avatar.setTransform(Sprite.TRANS_MIRROR);
		indietro.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.INDIETRO, indietro);

		State coccolante = new State();
		form = new PuyoForm(getImagePath(Forms.PUYO_COCCOLA));
		coccolante.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.COCCOLANTE, coccolante);

		State arrabbiato = new State();
		form = new PuyoForm(getImagePath(Forms.PUYO_ARRABBIATO));
		arrabbiato.setForm(form);
		puyo.addState(States.ARRABBIATO, arrabbiato);

		State mangiante = new State();
		form = new PuyoForm(getImagePath(Forms.PUYO_MANGIA));
		mangiante.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.MANGIANTE, mangiante);

		State affamato = new State();
		form = new PuyoForm(getImagePath(Forms.PUYO_AFFAMATO));
		affamato.setForm(form);
		puyo.addState(States.AFFAMATO, affamato);

		puyo.setActiveState(States.NASCENTE);

		/* azioni */
		Action coccola = new CoccolaAction(puyo);
		puyo.addAction(Actions.COCCOLA, coccola);

		Action mangia = new MangiaAction(puyo);
		puyo.addAction(Actions.MANGIA, mangia);
	}

	public Entity getGame() {
		return game;
	}

	public String getImagePath(int id) throws IOException {
		String path = null;
		switch (id) {
		case Forms.BACKGROUND:
			path = Forms.BACKGROUND_PATH;
			break;
		case Forms.PUYO_NASCE:
			path = Forms.PUYO_NASCE_PATH;
			break;
		case Forms.PUYO_DEFAULT:
			path = Forms.PUYO_DEFAULT_PATH;
			break;
		case Forms.PUYO_COCCOLA:
			path = Forms.PUYO_COCCOLA_PATH;
			break;
		case Forms.PUYO_ARRABBIATO:
			path = Forms.PUYO_ARRABBIATO_PATH;
			break;
		case Forms.PUYO_MANGIA:
			path = Forms.PUYO_MANGIA_PATH;
			break;
		case Forms.PUYO_AFFAMATO:
			path = Forms.PUYO_AFFAMATO_PATH;
			break;
		}
		// Image image = Image.createImage(path);
		// return image;
		return path;
	}
}