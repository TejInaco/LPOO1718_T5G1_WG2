package com.velesc.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.velesc.game.VelocidadeEscaldante;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Velocidade Escaldante";
		config.useGL30 = false;
		config.width = VelocidadeEscaldante.largura;
		config.height = VelocidadeEscaldante.altura;
		new LwjglApplication(new VelocidadeEscaldante());
	}
}
