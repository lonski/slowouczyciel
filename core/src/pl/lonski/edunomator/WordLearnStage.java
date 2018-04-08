package pl.lonski.edunomator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WordLearnStage extends Stage implements DirectionListener {

	private final float screenWidth;
	private final Speaker speaker;
	private final List<Word> words;
	private int currentWordIdx;
	private Word currentWord;

	WordLearnStage(Speaker speaker) {
		this.speaker = speaker;
		screenWidth = Gdx.graphics.getWidth();
		words = Word.fromFiles("fruits/");
	}

	InputAdapter getInputAdapter() {
		return new SimpleDirectionGestureDetector(this);
	}

	@Override
	public void onLeft() {
		if (currentWordIdx < words.size() - 1) {
			currentWordIdx += 1;
			if (currentWord != null) {
				currentWord.addAction(sequence(moveTo(-screenWidth, 0, 0.3f), removeActor()));
			}
			currentWord = words.get(currentWordIdx);
			currentWord.addAction(moveTo(0, 0, 0.3f));
			addActor(currentWord);
			speaker.speak(currentWord.getWordName());
		}
	}

	@Override
	public void onRight() {
		if (currentWordIdx > 0) {
			currentWordIdx -= 1;
			if (currentWord != null) {
				currentWord.addAction(sequence(moveTo(screenWidth, 0, 0.3f), removeActor()));
			}
			currentWord = words.get(currentWordIdx);
			currentWord.setPosition(-screenWidth, 0);
			currentWord.addAction(moveTo(0, 0, 0.3f));
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
