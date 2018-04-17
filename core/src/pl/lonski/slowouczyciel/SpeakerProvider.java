package pl.lonski.slowouczyciel;

import java.util.Locale;

public interface SpeakerProvider {

	Speaker get(Locale locale);
}
