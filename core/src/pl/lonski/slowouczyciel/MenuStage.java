package pl.lonski.slowouczyciel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

class MenuStage extends SlowouczycielStage {

	private static final float WIDTH = 320;
	private static final float HEIGHT = 240;
	private final Skin skin;
	private final Slowouczyciel slowouczyciel;

	private float yOffset;
	private float xOffset;

	MenuStage(final Slowouczyciel slowouczyciel) {
		this.slowouczyciel = slowouczyciel;
		((OrthographicCamera) getCamera()).setToOrtho(false, WIDTH, HEIGHT);
		skin = new Skin(Gdx.files.internal("skins/visui/uiskin.json"));
		yOffset = HEIGHT * 0.95f;
		xOffset = 20;
		addLogo();
		for (Config.Dataset dataset : slowouczyciel.getConfig().datasets) {
			addButton(dataset);
		}
	}

	private void addLogo() {
		Label logo = new Label("Słowouczyciel", skin);
		logo.setColor(Color.YELLOW);
		logo.setPosition(xOffset, yOffset - logo.getHeight());
		addActor(logo);
		yOffset -= logo.getHeight() * 1.4f;
	}

	private void addButton(final Config.Dataset dataset) {
		TextButton button = new TextButton(dataset.name, skin);
		button.setPosition(xOffset, yOffset - button.getHeight());
		yOffset -= button.getHeight() * 1.2f;
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				MenuStage.this.slowouczyciel.learnWords(dataset);
			}
		});
		addActor(button);
	}

	@Override
	InputAdapter getInputAdapter() {
		return this;
	}

}
