package pl.lonski.worducator;

import com.badlogic.gdx.input.GestureDetector;

interface DirectionListener {
	void onLeft();

	void onRight();

	void onUp();

	void onDown();
}

public class SimpleDirectionGestureDetector extends GestureDetector {

	public SimpleDirectionGestureDetector(DirectionListener directionListener) {
		super(new DirectionGestureListener(directionListener));
	}

	private static class DirectionGestureListener extends GestureAdapter {
		DirectionListener directionListener;

		DirectionGestureListener(DirectionListener directionListener) {
			this.directionListener = directionListener;
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			if (Math.abs(velocityX) > Math.abs(velocityY)) {
				if (velocityX > 0) {
					directionListener.onRight();
				} else {
					directionListener.onLeft();
				}
			} else {
				if (velocityY > 0) {
					directionListener.onDown();
				} else {
					directionListener.onUp();
				}
			}
			return super.fling(velocityX, velocityY, button);
		}

	}

}
