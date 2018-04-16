package com.Colorz.game.States;

import com.Colorz.game.ClassDefinations.Player;
import com.Colorz.game.ColorzGame;
import com.Colorz.game.Obstacles.Barrier;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.badlogic.gdx.input.RemoteInput;
import com.badlogic.gdx.math.Vector3;

public class playState extends state{

    private Texture player;
    private Player play;
    private Texture BG;
    private Barrier Test;
    private float playerX;
    private float playerY;
    private float playerBX;
    private float playerBY;

    public playState(GameStatemanager gsm) {
        super(gsm);
        Test = new Barrier(0,ColorzGame.HEIGHT, 200, 20, 0, -2f, "Brown");
        play = new Player(0,0,50, 50, "Red");
        BG = new Texture("Black.jpg");
        player = new Texture("Red.jpg");
       // cam.setToOrtho(false, ColorzGame.WIDTH / 2, ColorzGame.HEIGHT / 2);

    }

    @Override
    protected void handleInput() {

        if(Gdx.input.isTouched())
        {
            playerBX = Gdx.input.getX();
            playerBY = ColorzGame.HEIGHT - Gdx.input.getY() - 22.5f;     // sorry for the magic number

            playerX = Gdx.input.getX() - (play.GetScale().x/2);
            playerY = ColorzGame.HEIGHT - Gdx.input.getY() - (play.GetScale().y/2) - 22.5f;     // sorry for the magic number
            if(playerBX > play.GetPosition().x && playerBX < play.GetPosition().x + play.GetScale().x)
            {
                if(playerBY > play.GetPosition().y && playerBY < play.GetPosition().y + play.GetScale().y)
                {
                    play.SetPosition(new Vector3(playerX, playerY, 0));
                }
            }


            System.out.println(Gdx.input.getX() + " : " + Gdx.input.getY());
            System.out.println(playerX + " : " + playerY);
            System.out.println(play.GetPosition().x + " : " + play.GetPosition().y);
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        Test.Update(dt);
        play.Update(dt);
        if(play.getCollider().overlaps(Test.getCollider()))
        {
            if(Test.ID != play.ID)
            {
                Test.SetTexture(play.ID);
            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {

        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(BG, 0, 0, ColorzGame.WIDTH, ColorzGame.HEIGHT);
        sb.draw(Test.GetTexture(), Test.GetPosition().x, Test.GetPosition().y, Test.GetScale().x, Test.GetScale().y);
        sb.draw(play.GetTexture(), play.GetPosition().x, play.GetPosition().y, play.GetScale().x, play.GetScale().y);
        //sb.draw(player, 50, 50, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

        Test.GetTexture().dispose();
        play.GetTexture().dispose();

    }
}
