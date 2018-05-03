package com.Colorz.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Colorz.game.ColorzCore;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ColorzCore.WIDTH;
		config.height = ColorzCore.HEIGHT;
		new LwjglApplication(new ColorzCore(), config);
	}
}
