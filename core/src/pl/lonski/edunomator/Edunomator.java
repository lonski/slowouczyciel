package pl.lonski.edunomator;

import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Edunomator extends ApplicationAdapter {

	private static BitmapFont font;
	private EdunomatorStage stage;
	private final Speaker speaker;


	public Edunomator(Speaker resolver) {
		this.speaker = resolver;
	}

	public static BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont(Gdx.files.internal("font/noto.fnt"));
		}
		return font;
	}

	Speaker getSpeaker() {
		return speaker;
	}

	void learnWords(String folder) {
		stage = new WordLearnStage(folder, this);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	void startExam(List<Word> words) {
		stage = new ExamStage(words, this);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	@Override
	public void create() {
		learnWords("fruits");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(1 / 60f);
		stage.draw();
	}

	@Override
	public void dispose() {
	}
}
