package pl.lonski.edunomator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.touchable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class ExamStage extends EdunomatorStage {

	private final List<Word> words;
	private final Random random;
	private final Edunomator edunomator;
	private final Speaker speaker;
	private Question question;

	ExamStage(List<Word> words, Edunomator edunomator) {
		this.words = words;
		Collections.shuffle(this.words);
		this.random = ThreadLocalRandom.current();
		this.edunomator = edunomator;
		this.speaker = edunomator.getSpeaker();
		nextQuestion();
	}

	private boolean nextQuestion() {

		for (Actor actor : getActors()) {
			actor.remove();
		}

		if (words.size() >= 4) {
			question = new Question();
			String name = question.asked.getWordName();
			String text = "Gdzie jest " + name;
			if (name.endsWith("y")) {
				text = "Gdzie są " + name;
			}
			speaker.speakQueued(text);
			return true;
		}
		return false;
	}


	@Override
	InputAdapter getInputAdapter() {
		return this;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (question.guess(screenX, screenY)) {
			if (!nextQuestion()) {
				edunomator.learnWords("fruits");
			}
		}
		return true;
	}

	private class Question {

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		Word asked;

		Question() {
			List<Vector2> corners = getCorners();

			asked = words.remove(random.nextInt(words.size()));
			putWord(asked, corners);

			for (Word word : pickThreeRandomWords()) {
				putWord(word, corners);
			}
		}

		private void putWord(Word word, List<Vector2> corners) {
			word.setLabelVisible(false);
			word.setScale(1f);
			Vector2 corner = corners.remove(random.nextInt(corners.size()));
			word.setPosition(corner.x, corner.y);
			word.addAction(getScaleAction());
			addActor(word);
		}

		private Action getScaleAction() {
			return sequence(
					touchable(Touchable.disabled),
					scaleBy(-0.5f, -0.5f, 0.3f),
					touchable(Touchable.enabled)
			);
		}

		boolean guess(float x, float y) {
			if (x >= asked.getX() && x < (asked.getX() + width / 2) && y < height - asked.getY() &&
					y > (height / 2 - asked.getY())) {
				speaker.speak("Dobrze!");
				return true;
			}
			speaker.speak("Niestety nie.");
			return false;
		}

		private List<Word> pickThreeRandomWords() {
			List<Word> picked = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				picked.add(words.remove(random.nextInt(words.size())));
			}
			words.addAll(picked);
			return picked;
		}

		private List<Vector2> getCorners() {
			return new ArrayList<>(Arrays.asList(
					new Vector2(0, 0),
					new Vector2(0, height / 2),
					new Vector2(width / 2, 0),
					new Vector2(width / 2, height / 2)
			));
		}
	}
}
