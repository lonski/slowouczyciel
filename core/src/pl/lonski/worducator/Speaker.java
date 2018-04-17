package pl.lonski.worducator;

public interface Speaker {
	void speak(String text);

	void speakQueued(String text);
}
