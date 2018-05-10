package com.Colorz.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.Colorz.game.ColorzGame;

/**
 * Created by SuryaVamsiKK on 3/12/2018.
 */

public class Menu extends state {

    private Texture BG;
    private Texture PlayBtn;
    float X = 500;
    float Y = 500;

    public Menu(GameStatemanager gsm) {

        super(gsm);
        BG = new Texture("SkyBlue.jpg");
        PlayBtn = new Texture("Green.jpg");
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched())
        {
            gsm.set(new playState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(BG, 0, 0, ColorzGame.WIDTH, ColorzGame.HEIGHT);
        sb.draw(PlayBtn, (ColorzGame.WIDTH / 2), (ColorzGame.HEIGHT / 2), X , Y);
        sb.end();
    }

    @Override
    public void dispose() {

        BG.dispose();
        PlayBtn.dispose();

    }
}
