package com.Colorz.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.States.CreditsState;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.States.Menu;
import com.badlogic.gdx.math.MathUtils;

public class ColorzCore extends ApplicationAdapter {

	public static float W = 607;
	public static float H = 1080;

	public static final float WIDTH = 607;
	public static final float HEIGHT = 1080;

	public static float AWIDTH;
	public static float AHEIGHT;

	public static String TextureFormate = ".jpg";
	public static boolean Mode = true;

	public static final String TITLE = "ColorZ";
	private GameStatemanager gsm;
	private SpriteBatch batch;
	public static boolean touchPass = false;

	public ColorzCore(float Width, float Height)
	{
		//System.out.println("aergggggggvrsdfva wresfcaergfdvbstrfdgbvedfzcgvaerzdgvarezdfgvsrtdfgvserzdgvaezrsdgvzewsdzxfczesd");
		AWIDTH = Width;
		AHEIGHT = Height;
	}


	@Override
	public void create ()
	{
		W = AWIDTH/WIDTH;
		H = AHEIGHT/HEIGHT;
//		System.out.println(AWIDTH);
//		System.out.println(AHEIGHT);
//		System.out.println(WIDTH);
//		System.out.println(HEIGHT);
//		System.out.println(AWIDTH/WIDTH);
//		System.out.println(AHEIGHT/HEIGHT);
//		System.out.println(W);
//		System.out.println(H);
		batch = new SpriteBatch();
		gsm = new GameStatemanager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new Menu(gsm));
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public static boolean isTouching(float bx, float by, float width, float height)
	{
		boolean truth = false;
		float touchx, touchy;
		touchx = Gdx.input.getX();
		touchy = ColorzCore.AHEIGHT - Gdx.input.getY();
		if(touchx > bx && (touchx < bx+(width)))
		{
			if(touchy > by && touchy < by+(height+(height/2)))
			{
				truth = true;
			}
		}

		return truth;
	}

	public static int getHighscore()
	{
		int score;
		Preferences prefs = Gdx.app.getPreferences("ColorzGame");
		score = prefs.getInteger("Highscore");
		return score;
	}

	public static int getScore()
	{
		int score;
		Preferences prefs = Gdx.app.getPreferences("ColorzGame");
		score = prefs.getInteger("Score");
		return score;
	}

	public static void SetScore(int score)
	{
		Preferences prefs = Gdx.app.getPreferences("ColorzGame");
		prefs.putInteger("Score", score);
		prefs.flush();
	}

	public static void highscoreCheck(int currentScore)
	{
		int score;
		Preferences prefs = Gdx.app.getPreferences("ColorzGame");
		score = prefs.getInteger("Highscore");
		if(currentScore > score)
		{
			prefs.putInteger("Highscore", currentScore);
			prefs.flush();
		}
	}

	public static String ColourGen()
	{
		float radbar = MathUtils.random(2);
		String clr = new String();
		if (radbar == 0) {
			clr = "Red";
		}
		if (radbar == 1) {
			clr = "Blue";
		}
		if (radbar == 2) {
			clr = "Green";
		}
		if (radbar == 3) {
			clr = "Brown";
		}
		if (radbar == 4) {
			clr = "Yellow";
		}
		if (radbar == 5) {
			clr = "SkyBlue";
		}

		return clr;
	}
}
