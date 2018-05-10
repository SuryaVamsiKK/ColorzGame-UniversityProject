package com.Colorz.game.States;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.Colorz.game.ColorzCore;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;

public class GameOver extends state {


    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("AGENCYB.TTF"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;
    private int score;
    private int highscore;
    private Texture BG;
    //private Music sd;

    public GameOver(GameStatemanager gsm) {
        super(gsm);
        font = new BitmapFont();
        BG = new Texture("Black.jpg");
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();
        //sd = Gdx.audio.newMusic(Gdx.files.internal("Shader Graph Tutorial - Creating a Shader Graph [3⁄8] Live 2018⁄3⁄07.mp3"));
        //sd.setLooping(true);
        //sd.setVolume(1f);
        //sd.play();

    }

    @Override
    protected void handleInput() {

        if(Gdx.input.isTouched() && ColorzCore.touchPass == false)
        {
            ColorzCore.touchPass = true;
            ColorzCore.SetScore(0);
            gsm.set(new Menu(gsm));
            dispose();
        }

        if(!Gdx.input.isTouched())
        {
            ColorzCore.touchPass = false;
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        score = ColorzCore.getScore();
        highscore = ColorzCore.getHighscore();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(BG, 0, 0, ColorzCore.AWIDTH, ColorzCore.AHEIGHT);
        font.getData().setScale(1, 1);
        font.setColor(Color.GREEN);
        font.draw(sb, "Score : " + score, ColorzCore.AWIDTH/2, ColorzCore.AHEIGHT/2, 0, Align.center, true);
        font.setColor(Color.YELLOW);
        font.draw(sb, "HighScore : " + highscore, ColorzCore.AWIDTH/2, ColorzCore.AHEIGHT/2 - 100, 0, Align.center, true);
        sb.end();

    }

    @Override
    public void dispose() {

        BG.dispose();

    }
}
