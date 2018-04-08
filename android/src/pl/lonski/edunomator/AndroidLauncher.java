package pl.lonski.edunomator;

import java.util.Locale;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

public class AndroidLauncher extends AndroidApplication implements TextToSpeech.OnInitListener {

	private TextToSpeech tts;
	private AndroidSpeaker actionResolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tts = new TextToSpeech(this, this);
		actionResolver = new AndroidSpeaker(tts);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Edunomator(actionResolver), config);
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(new Locale("pl"));
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			}
		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}
}
