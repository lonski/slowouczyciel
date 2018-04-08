package pl.lonski.edunomator;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Word extends Actor {

	private final Texture texture;
	private final BitmapFont font;
	private final String name;

	private Word(Texture texture, String name) {
		this.texture = texture;
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.font = new BitmapFont(Gdx.files.internal("font/noto.fnt"));
		this.font.setColor(Color.RED);
		this.font.getData().setScale(2);

		setPosition(Gdx.graphics.getWidth(), 0);
	}

	static List<Word> fromFiles(String folder) {
		List<Word> words = new ArrayList<>();
		for (FileHandle file : Gdx.files.internal(folder).list()) {
			String name = file.name().substring(0, file.name().indexOf('.'));
			Word word = new Word(new Texture(file), name);
			words.add(word);
		}
		return words;
	}

	String getWordName() {
		return name;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		float fx = getX() + 40;
		float fy = getY() + 20 + font.getData().getFirstGlyph().height * font.getData().scaleY * 1.2f;
		font.setColor(Color.BLACK);
		font.draw(batch, name, fx + 6, fy + 6);
		font.draw(batch, name, fx + 6, fy - 6);
		font.draw(batch, name, fx - 6, fy + 6);
		font.draw(batch, name, fx - 6, fy - 6);
		font.setColor(Color.RED);
		font.draw(batch, name, fx, fy);
	}
}
