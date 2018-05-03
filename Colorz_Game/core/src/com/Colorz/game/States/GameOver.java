package com.Colorz.game.States;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.Colorz.game.ColorzCore;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends state {


    BitmapFont font;
    private int score;
    private int highscore;
    //private Music sd;

    public GameOver(GameStatemanager gsm) {
        super(gsm);
        font = new BitmapFont();
        //sd = Gdx.audio.newMusic(Gdx.files.internal("Shader Graph Tutorial - Creating a Shader Graph [3⁄8] Live 2018⁄3⁄07.mp3"));
        //sd.setLooping(true);
        //sd.setVolume(1f);
        //sd.play();

    }

    @Override
    protected void handleInput() {

        if(Gdx.input.isTouched())
        {
            SetScore(0);
            gsm.set(new Menu(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        score = getScore();
        highscore = getHighscore();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        font.getData().setScale(1.2f, 1.2f);
        font.setColor(Color.YELLOW);
        font.draw(sb, "Score : " + score, 500,1030);
        font.draw(sb, "HighScore : " + highscore, 460,1000);
        sb.end();

    }

    @Override
    public void dispose() {

    }


    public int getHighscore()
    {
        int score;
        Preferences prefs = Gdx.app.getPreferences("ColorzGame");
        score = prefs.getInteger("Highscore");
        return score;
    }

    public int getScore()
    {
        int score;
        Preferences prefs = Gdx.app.getPreferences("ColorzGame");
        score = prefs.getInteger("Score");
        return score;
    }


    public void SetScore(int score)
    {
        Preferences prefs = Gdx.app.getPreferences("ColorzGame");
        prefs.putInteger("Score", score);
        prefs.flush();
    }
}
