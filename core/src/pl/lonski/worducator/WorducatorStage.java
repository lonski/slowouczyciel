package pl.lonski.worducator;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

abstract class WorducatorStage extends Stage {

	abstract InputAdapter getInputAdapter();
}
