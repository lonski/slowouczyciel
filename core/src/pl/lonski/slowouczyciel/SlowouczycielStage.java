package pl.lonski.slowouczyciel;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

abstract class SlowouczycielStage extends Stage {

	abstract InputAdapter getInputAdapter();
}
