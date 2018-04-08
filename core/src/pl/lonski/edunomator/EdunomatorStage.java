package pl.lonski.edunomator;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class EdunomatorStage extends Stage {

	abstract InputAdapter getInputAdapter();
}
