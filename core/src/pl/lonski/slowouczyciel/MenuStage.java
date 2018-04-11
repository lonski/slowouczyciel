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
		addButton("Owoce", "fruits/");
		addButton("Warzywa", "vegetables/");
		addButton("Zwierzęta", "animals/");
		addButton("Języki programowania", "languages/");
	}

	private void addLogo() {
		Label logo = new Label("Słowouczyciel", skin);
		logo.setColor(Color.YELLOW);
		logo.setPosition(xOffset, yOffset - logo.getHeight());
		addActor(logo);
		yOffset -= logo.getHeight() * 1.4f;
	}

	private void addButton(String label, final String folder) {
		TextButton button = new TextButton(label, skin);
		button.setPosition(xOffset, yOffset - button.getHeight());
		yOffset -= button.getHeight() * 1.2f;
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				MenuStage.this.slowouczyciel.learnWords(folder);
			}
		});
		addActor(button);
	}

	@Override
	InputAdapter getInputAdapter() {
		return this;
	}

}
