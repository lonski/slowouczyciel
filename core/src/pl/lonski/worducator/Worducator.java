package pl.lonski.worducator;

import java.util.List;
import java.util.Locale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Worducator extends ApplicationAdapter {

	private static BitmapFont font;

	private final SpeakerProvider speakerProvider;
	private Speaker speaker;
	private WorducatorStage stage;
	private Config config;

	Worducator(SpeakerProvider speakerProvider) {
		this.speakerProvider = speakerProvider;
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

	void learnWords(Config.Dataset dataset) {
		stage = new WordLearnStage(dataset, this);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	void startExam(List<Word> words) {
		stage = new pl.lonski.worducator.ExamStage(words, this);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	void gameMenu() {
		stage = new MenuStage(this);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	void chooseLanguage() {
		stage = new ChooseLanguageStage(this);
		Gdx.input.setInputProcessor(stage.getInputAdapter());
	}

	Config getConfig() {
		return config;
	}

	void setConfig(String filename) {
		this.config = Config.load(Gdx.files.internal(filename));
		this.speaker = speakerProvider.get(new Locale(config.language));
	}

	@Override
	public void create() {
		chooseLanguage();
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
