package com.Colorz.game.States;


import com.Colorz.game.ColorzGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;

/**
 * Created by SuryaVamsiKK on 3/25/2018.
 */

public class CreditsState extends state {

    private Texture bG;

    public CreditsState(GameStatemanager gsm) {
        super(gsm);
        bG = new Texture("Green.jpg");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bG, 0, 0, ColorzGame.WIDTH, ColorzGame.HEIGHT);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
