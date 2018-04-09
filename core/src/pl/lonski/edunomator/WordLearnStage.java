package pl.lonski.edunomator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class WordLearnStage extends EdunomatorStage implements DirectionListener {

	private final float screenWidth;
	private final Speaker speaker;
	private final Edunomator edunomator;
	private final WordLoader loader;
	private List<Word> words;
	private int currentWordIdx;
	private Word currentWord;
	private Text loadingLabel;

	WordLearnStage(String folder, Edunomator edunomator) {
		this.speaker = edunomator.getSpeaker();
		this.edunomator = edunomator;
		this.words = new ArrayList<>();
		this.screenWidth = Gdx.graphics.getWidth();
		this.currentWordIdx = -1;
		this.loader = new WordLoader(folder);

		loadingLabel = new Text("Ładowanie", Color.GREEN);
		loadingLabel.setPosition(100, 100);
		loadingLabel.setScale(0.7f);
		addActor(loadingLabel);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (!loader.isDone()) {
			loadingLabel.setText("Ładowanie (" + loader.getLoadedCount() + "/" + loader.getTotalCount() + ")");
			loader.loadNext();
		} else if (words.isEmpty()) {
			this.words = loader.getWords();
			onLeft();
		}
	}

	@Override
	InputAdapter getInputAdapter() {
		return new SimpleDirectionGestureDetector(this);
	}

	private Action wrapAction(Action... toWrap) {
		List<Action> actions = new ArrayList<>();
		actions.add(touchable(Touchable.disabled));
		actions.addAll(Arrays.asList(toWrap));
		actions.add(touchable(Touchable.enabled));
		return sequence(actions.toArray(new Action[]{}));
	}

	@Override
	public void onLeft() {
		if (currentWordIdx < words.size() - 1) {
			currentWordIdx += 1;
			if (currentWord != null) {
				currentWord.addAction(wrapAction(moveTo(-screenWidth, 0, 0.2f), removeActor()));
			}
			currentWord = words.get(currentWordIdx);
			System.out.println(currentWord.getWordName());
			currentWord.setPosition(screenWidth, 0);
			currentWord.addAction(wrapAction(moveTo(0, 0, 0.2f)));
			addActor(currentWord);
			speaker.speak(currentWord.getWordName());
		} else {
			edunomator.startExam(words);
		}
	}

	@Override
	public void onRight() {
		if (currentWordIdx > 0) {
			currentWordIdx -= 1;
			if (currentWord != null) {
				currentWord.addAction(wrapAction(moveTo(screenWidth, 0, 0.2f), removeActor()));
			}
			currentWord = words.get(currentWordIdx);
			currentWord.setPosition(-screenWidth, 0);
			currentWord.addAction(wrapAction(moveTo(0, 0, 0.2f)));
			addActor(currentWord);
			speaker.speak(currentWord.getWordName());
		}
	}

	@Override
	public void onUp() {
	}

	@Override
	public void onDown() {
	}
}
