package com.Colorz.game.States;

import com.Colorz.game.BuildingBlocks.Block;
import com.Colorz.game.ColorzCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;

/**
 * Created by SuryaVamsiKK on 3/12/2018.
 */

public class Menu extends state {

    private Texture BG;
    private Block playButton;
    private Block creditButton;

    public Menu(GameStatemanager gsm) {

        super(gsm);
        BG = new Texture("Black.jpg");
        playButton = new Block(ColorzCore.AWIDTH/2, (ColorzCore.AHEIGHT/2) - 50, 220, 100, 0, "Red");
        creditButton = new Block(ColorzCore.AWIDTH/2, (ColorzCore.AHEIGHT/2) - 200 * ColorzCore.H, 220, 100, 0, "Yellow");
    }

    @Override
    public void handleInput() {

        if(!Gdx.input.justTouched())
        {
            ColorzCore.touchPass = false;
        }

        if(Gdx.input.justTouched() && ColorzCore.touchPass == false)
        {
            ColorzCore.touchPass = true;
            if(ColorzCore.isTouching(playButton.GetPosition().x, playButton.GetPosition().y, playButton.GetScale().x, playButton.GetScale().y) == true)
            {
                gsm.set(new playState(gsm));
                dispose();
            }

            if(ColorzCore.isTouching(creditButton.GetPosition().x, creditButton.GetPosition().y, creditButton.GetScale().x, creditButton.GetScale().y) == true)
            {
                gsm.set(new CreditsState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {

        handleInput();
        playButton.Update(dt);
        creditButton.Update(dt);

    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(BG, 0, 0, ColorzCore.AWIDTH, ColorzCore.AHEIGHT);
        playButton.GetSprite().draw(sb);
        creditButton.GetSprite().draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {

        BG.dispose();
        playButton.GetTexture().dispose();
        creditButton.GetTexture().dispose();

    }
}
