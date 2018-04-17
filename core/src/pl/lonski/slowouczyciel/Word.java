package pl.lonski.slowouczyciel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Word extends Actor {

	private final FileHandle textureFile;
	private Texture texture;
	private Text label;
	private final String name;
	private boolean drawLabel;
	private boolean isPlural;

	Word(FileHandle file, String name, boolean isPlural) {
		this.textureFile = file;
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.drawLabel = true;
		this.isPlural = isPlural;
		setPosition(Gdx.graphics.getWidth(), 0);
	}

	void load() {
		if (texture == null) {
			texture = new Texture(textureFile);
			label = new Text(name, Color.RED);
		}
	}

	String getWordName() {
		return name;
	}

	boolean isPlural() {
		return isPlural;
	}

	void setLabelVisible(boolean visible) {
		drawLabel = visible;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		if (texture != null) {
			batch.draw(texture, getX(), getY(), Gdx.graphics.getWidth() * getScaleX(),
					Gdx.graphics.getHeight() * getScaleY());
			if (drawLabel) {
				label.draw(batch, getX(), getY(), getScaleX(), getScaleY());
			}
		}
	}
}
