package pl.lonski.slowouczyciel;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class WordLearnStage extends SlowouczycielStage implements DirectionListener {

	private final float screenWidth;
	private final Speaker speaker;
	private final Slowouczyciel slowouczyciel;
	private final WordLoader loader;
	private List<pl.lonski.slowouczyciel.Word> words;
	private int currentWordIdx;
	private pl.lonski.slowouczyciel.Word currentWord;
	private Text loadingLabel;

	WordLearnStage(String folder, Slowouczyciel slowouczyciel) {
		this.speaker = slowouczyciel.getSpeaker();
		this.slowouczyciel = slowouczyciel;
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
		actions.add(new Action() {
			@Override
			public boolean act(float delta) {
				loadWordAt(currentWordIdx + 1);
				return true;
			}
		});
		return sequence(actions.toArray(new Action[]{}));
	}

	@Override
	public void onLeft() {
		if (currentWordIdx < words.size() - 1) {
			currentWordIdx += 1;
			if (currentWord != null) {
				currentWord.addAction(wrapAction(moveTo(-screenWidth, 0, 0.2f), removeActor()));
			}
			currentWord = getWordAtCurrentIdx();
			currentWord.setPosition(screenWidth, 0);
			currentWord.addAction(wrapAction(moveTo(0, 0, 0.2f)));
			addActor(currentWord);
			speaker.speak(currentWord.getSpokenName());
		} else {
			slowouczyciel.startExam(words);
		}
	}

	private pl.lonski.slowouczyciel.Word getWordAtCurrentIdx() {
		loadWordAt(currentWordIdx);
		return words.get(currentWordIdx);
	}

	@Override
	public void onRight() {
		if (currentWordIdx > 0) {
			currentWordIdx -= 1;
			if (currentWord != null) {
				currentWord.addAction(wrapAction(moveTo(screenWidth, 0, 0.2f), removeActor()));
			}
			currentWord = getWordAtCurrentIdx();
			currentWord.setPosition(-screenWidth, 0);
			currentWord.addAction(wrapAction(moveTo(0, 0, 0.2f)));
			addActor(currentWord);
			speaker.speak(currentWord.getSpokenName());
		}
	}

	private void loadWordAt(int idx) {
		if (idx >= 0 && idx < words.size()) {
			words.get(idx).load();
		}
	}

	@Override
	public void onUp() {
	}

	@Override
	public void onDown() {
	}
}
