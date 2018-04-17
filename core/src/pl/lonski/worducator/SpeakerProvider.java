package pl.lonski.worducator;

import java.util.Locale;

public interface SpeakerProvider {

	Speaker get(Locale locale);
}
