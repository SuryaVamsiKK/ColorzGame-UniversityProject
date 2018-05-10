package com.Colorz.game.android;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.Colorz.game.ColorzCore;

public class AndroidLauncher extends AndroidApplication {

	Context context;
	Display display;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		initialize(new ColorzCore(display.getWidth(), display.getHeight()), config);
//		System.out.println(display.getWidth());
//		System.out.println(display.getHeight());
//       	initialize(new ColorzCore(), config);
	}
}
