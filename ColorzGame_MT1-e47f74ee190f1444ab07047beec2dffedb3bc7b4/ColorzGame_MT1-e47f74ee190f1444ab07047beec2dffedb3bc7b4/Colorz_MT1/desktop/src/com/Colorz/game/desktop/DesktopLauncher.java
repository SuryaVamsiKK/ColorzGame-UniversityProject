package com.Colorz.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Colorz.game.ColorzGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ColorzGame.WIDTH;
		config.height = ColorzGame.HEIGHT;
		new LwjglApplication(new ColorzGame(), config);
	}
}
