package pl.lonski.edunomator;

import android.speech.tts.TextToSpeech;

public class AndroidSpeaker implements Speaker {

	private TextToSpeech tts;

	AndroidSpeaker(TextToSpeech tts) {
		this.tts = tts;
	}

	@Override
	public void speak(String text) {
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}
