package com.Colorz.game.States;
import com.Colorz.game.BuildingBlocks.Block;
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

public class CreditsState extends state {


    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("AGENCYB.TTF"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;
    private Texture BG;
    private Block creditButton;
    //private Music sd;

    public CreditsState(GameStatemanager gsm) {
        super(gsm);
        font = new BitmapFont();
        BG = new Texture("Black.jpg");
        parameter.size = 36;
        font = generator.generateFont(parameter);
        generator.dispose();
        creditButton = new Block((ColorzCore.AWIDTH/2) - 110, (ColorzCore.AHEIGHT/2) - 200 * ColorzCore.H, 220, 100, 0, "Yellow");
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
            if(ColorzCore.isTouching(creditButton.GetPosition().x, creditButton.GetPosition().y, creditButton.GetScale().x, creditButton.GetScale().y) == true)
            {
                gsm.set(new Menu(gsm));
                dispose();
            }
        }

        if(!Gdx.input.isTouched())
        {
            ColorzCore.touchPass = false;
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        creditButton.Update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(BG, 0, 0, ColorzCore.AWIDTH, ColorzCore.AHEIGHT);
        font.getData().setScale(1, 1);
        font.setColor(Color.GREEN);
        creditButton.GetSprite().draw(sb);
        font.draw(sb, "Scripts & Concept : Surya Vamsi KK", ColorzCore.AWIDTH/2, ColorzCore.AHEIGHT/2, 0, Align.center, true);
        sb.end();

    }

    @Override
    public void dispose() {

        BG.dispose();
        creditButton.GetTexture().dispose();

    }
}
