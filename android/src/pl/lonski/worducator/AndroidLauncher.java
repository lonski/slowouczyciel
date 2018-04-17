package pl.lonski.worducator;

import java.util.Locale;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Worducator
				worducator = new Worducator(new pl.lonski.worducator.SpeakerProvider() {
			@Override
			public Speaker get(Locale locale) {
				return new AndroidSpeaker(AndroidLauncher.this, locale);
			}
		});

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(worducator, config);
	}

	@Override
	public void onBackPressed() {
	}

	@Override
	protected void onPause() {
		super.onPause();
		ActivityManager activityManager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		activityManager.moveTaskToFront(getTaskId(), 0);
	}
}
