package com.Colorz.game.States;

import com.Colorz.game.ClassDefinations.Player;
import com.Colorz.game.ColorzGame;
import com.Colorz.game.Obstacles.Barrier;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class playState extends state{

    private Texture player;
    private Player play;
    private Texture BG;
    private Barrier Test;
    private float playerX;
    private float playerY;
    private float playerBX;
    private float playerBY;
    private  boolean block = false;
    private ShapeRenderer spr;
    private boolean inputtaken = false;

    public playState(GameStatemanager gsm) {
        super(gsm);

        Test = new Barrier(0,ColorzGame.HEIGHT, 200, 20, 0, ColourGen());
        play = new Player(0,0,50, 50, "Red");
        BG = new Texture("Black.jpg");
        player = new Texture("Red.jpg");
        spr = new ShapeRenderer();
       //cam.setToOrtho(false, ColorzGame.WIDTH / 2, ColorzGame.HEIGHT / 2);

    }

    @Override
    protected void handleInput() {

        if(!Gdx.input.isTouched())
        {
            block = false;
            inputtaken = false;
        }

        if(Gdx.input.isTouched())
        {
            playerBX = Gdx.input.getX();
            playerBY = ColorzGame.HEIGHT - Gdx.input.getY() - 22.5f;     // sorry for the magic number
            if(playerBX > play.GetPosition().x && playerBX < play.GetPosition().x + play.GetScale().x)
            {
                if(playerBY > play.GetPosition().y && playerBY < play.GetPosition().y + play.GetScale().y)
                {
                   inputtaken = true;
                }
            }
            if(inputtaken == true)
            {
                if(block == false)
                {
                    play.SetTexture(ColourGen());
                }
                block = true;
                playerX = Gdx.input.getX() - (play.GetScale().x/2);
                playerY = ColorzGame.HEIGHT - Gdx.input.getY() - (play.GetScale().y/2) - 22.5f;     // sorry for the magic number
                play.SetPosition(new Vector2(playerX, playerY));
            }
        }
    }

    @Override
    public void update(float dt) {

        handleInput();
        Test.Update(dt);
        play.Update(dt);

        if(Test.position.y < 0)
        {
            Test.SetTexture(ColourGen());
            Test.position.y = Test.startPos.y;
        }
        if(Test.position.x > ColorzGame.WIDTH)
        {
            Test.SetTexture(ColourGen());
            Test.position.x = Test.startPos.x - Test.startScale.x;
        }
        Test.position.add(0, -2f);

        if(Intersector.overlapConvexPolygons(play.getCollider(), Test.getCollider()))
        {
            if(Test.ID != play.ID)
            {
                Test.SetTexture(play.ID);
            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {

      // sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(BG, 0, 0, ColorzGame.WIDTH, ColorzGame.HEIGHT);
        Test.GetSprite().draw(sb);
        play.GetSprite().draw(sb);
        sb.end();

        //region Debugging
        spr.begin(ShapeRenderer.ShapeType.Line);
        debugmode(Color.GREEN, Test.getCollider());
        debugmode( Color.RED, play.getCollider());
        spr.end();
        //endregion
    }

    public void debugmode(Color clr, Polygon ply)
    {
        spr.setColor(clr);
        spr.polygon(ply.getTransformedVertices());
    }

    public String ColourGen()
    {
        float radbar = MathUtils.random(5);
        String clr = new String();
        if (radbar == 0) {
            clr = "Yellow";
        }
        if (radbar == 1) {
            clr = "Blue";
        }
        if (radbar == 2) {
            clr = "Brown";
        }
        if (radbar == 3) {
            clr = "Green";
        }
        if (radbar == 4) {
            clr = "Red";
        }
        if (radbar == 5) {
            clr = "SkyBlue";
        }

        return clr;
    }

    @Override
    public void dispose() {

        Test.GetTexture().dispose();
        play.GetTexture().dispose();

    }
}
