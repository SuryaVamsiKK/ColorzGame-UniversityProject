package com.Colorz.game.States;

import com.Colorz.game.ColorzGame;
import com.Colorz.game.Obstacles.Barrier;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;

public class playState extends state{

    private Texture player;
    private Texture BG;
    private Barrier Test;

    public playState(GameStatemanager gsm) {
        super(gsm);
        Test = new Barrier(0,ColorzGame.HEIGHT, 200, 20, 5f, -0.5f, "Brown");
        BG = new Texture("Black.jpg");
        player = new Texture("Red.jpg");
       // cam.setToOrtho(false, ColorzGame.WIDTH / 2, ColorzGame.HEIGHT / 2);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        handleInput();
        Test.Update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(BG, 0, 0, ColorzGame.WIDTH, ColorzGame.HEIGHT);
        sb.draw(Test.GetTexture(), Test.GetPosition().x, Test.GetPosition().y, Test.GetScale().x, Test.GetScale().y);
        sb.draw(player, 50, 50, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
