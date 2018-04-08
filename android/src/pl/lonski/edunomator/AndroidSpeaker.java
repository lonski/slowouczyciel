package pl.lonski.edunomator;

import android.speech.tts.TextToSpeech;

public class AndroidSpeaker implements Speaker {

	private TextToSpeech tts;
	private String lastSpoken;

	AndroidSpeaker(TextToSpeech tts) {
		this.tts = tts;
	}

	@Override
	public void speak(String text) {
		speak(text, TextToSpeech.QUEUE_FLUSH);
	}

	@Override
	public void speakQueued(String text) {
		speak(text, TextToSpeech.QUEUE_ADD);
	}

	private void speak(String text, int queueMode) {
		if (!tts.isSpeaking() || !text.equals(lastSpoken)) {
			tts.speak(text, queueMode, null);
			lastSpoken = text;
		}
	}
}
