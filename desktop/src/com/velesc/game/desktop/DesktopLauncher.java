package com.velesc.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.velesc.game.velEscaldGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Hello-World";
		config.useGL30 = false;
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new velEscaldGame(), config);
	}
}
