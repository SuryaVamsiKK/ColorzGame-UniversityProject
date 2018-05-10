package com.Colorz.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Colorz.game.ColorzCore;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)ColorzCore.WIDTH;
		config.height = (int)ColorzCore.HEIGHT;
		new LwjglApplication(new ColorzCore(607, 1080), config);
//		new LwjglApplication(new ColorzCore(), config);
	}
}
