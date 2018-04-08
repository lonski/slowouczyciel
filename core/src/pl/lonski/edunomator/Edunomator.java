package pl.lonski.edunomator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Edunomator extends ApplicationAdapter {

	private WordLearnStage stage;
	private final Speaker speaker;


	public Edunomator(Speaker resolver) {
		this.speaker = resolver;
	}

	@Override
	public void create() {
		stage = new WordLearnStage(speaker);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(1 / 60f);
		stage.draw();
	}

	@Override
	public void dispose() {
	}
}
