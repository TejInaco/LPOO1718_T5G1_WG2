package com.velesc.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Android implementation class
 * Makes available the use of the Gyroscope and the vibration
 * The AndroidManifest.xml has modified permissions to englobe the bellow configurations
 * */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGyroscope = true;
		config.useWakelock = true;
		initialize(new VelocidadeEscaldante(), config);
	}
}
