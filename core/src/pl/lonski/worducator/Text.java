package pl.lonski.worducator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

class Text extends Actor {

	private final BitmapFont font;
	private final Color color;
	private String text;

	Text(String text, Color color) {
		this.text = text;
		this.color = color;
		this.font = Worducator.getFont();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		draw(batch, getX(), getY(), getScaleX(), getScaleY());
	}

	void setText(String text) {
		this.text = text;
	}

	void draw(Batch batch, float x, float y, float scaleX, float scaleY) {
		font.getData().setScale(1.5f * scaleX, 1.5f * scaleY);
		float fx = x + 40;
		float fy = y + 20 + font.getData().getFirstGlyph().height * font.getData().scaleY * 1.2f;
		font.setColor(Color.BLACK);
		font.draw(batch, text, fx + 6, fy - 6);
		font.setColor(color);
		font.draw(batch, text, fx, fy);
	}
}
