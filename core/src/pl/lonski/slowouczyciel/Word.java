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
	private final Text label;
	private final String name;
	private boolean drawLabel;

	Word(FileHandle file, String name) {
		this.textureFile = file;
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1).replaceAll("_", " ");
		this.drawLabel = true;
		this.label = new Text(this.name, Color.RED);

		setPosition(Gdx.graphics.getWidth(), 0);
	}

	void load() {
		if (texture == null) {
			texture = new Texture(textureFile);
		}
	}

	String getWordName() {
		return name;
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
