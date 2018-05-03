package com.Colorz.game.States;

import com.Colorz.game.ColorzCore;
import com.Colorz.game.BuildingBlocks.Block;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class playState extends state{

    private Block play;
    private Texture BG;
    private Block Bar1;
    private Block Bar2;
    private int score = 0;
    private float playerX;
    private float playerY;
    private float playerBX;
    private float playerBY;

    private float rots = 0;
    private  boolean block = false;
    BitmapFont font;
    private ShapeRenderer spr;
    private boolean inputtaken = false;

    public playState(GameStatemanager gsm) {
        super(gsm);

        Bar1 = new Block(0,ColorzCore.HEIGHT, 500, 20, 0, ColourGen());
        Bar2 = new Block(0,ColorzCore.HEIGHT, 300, 20, 0, ColourGen());
        play = new Block(0,0,50, 50, 0,"Red");
        BG = new Texture("Black.jpg");
        spr = new ShapeRenderer();
        font = new BitmapFont();
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
            playerBY = ColorzCore.HEIGHT - Gdx.input.getY() - 22.5f;     // sorry for the magic number
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
                playerY = ColorzCore.HEIGHT - Gdx.input.getY() - (play.GetScale().y/2) - 22.5f;     // sorry for the magic number
                play.SetPosition(new Vector2(playerX, playerY));
            }
        }
    }

    @Override
    public void update(float dt) {

        handleInput();
        Bar1.Update(dt);
        Bar2.Update(dt);
        play.Update(dt);

        if(Bar1.position.y < 0) {
            Bar1.SetTexture(ColourGen());
            Bar1.position.x = MathUtils.random(0, ColorzCore.WIDTH - Bar1.GetScale().x);
            Bar1.position.y = Bar1.startPos.y;
            score++;
        }

        Bar1.position.add(0, -10f);

        if(Intersector.overlapConvexPolygons(play.getCollider(), Bar1.getCollider()))
        {
            if(Bar1.ID != play.ID)
            {
                Bar1.SetTexture(play.ID);
            }
        }

        if(Bar2.position.y < 0) {
            Bar2.SetTexture(ColourGen());
            Bar2.position.x = MathUtils.random(0, ColorzCore.WIDTH - Bar2.GetScale().x);
            Bar2.position.y = Bar2.startPos.y;
            score++;
        }
        Bar2.SetRotation(rots++);
        Bar2.position.add(0, -5f);

        if(Intersector.overlapConvexPolygons(play.getCollider(), Bar2.getCollider()))
        {
            if(Bar2.ID != play.ID)
            {
                Bar2.SetTexture(play.ID);
            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {

        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(BG, 0, 0, ColorzCore.WIDTH, ColorzCore.HEIGHT);
        Bar1.GetSprite().draw(sb);
        Bar2.GetSprite().draw(sb);
        play.GetSprite().draw(sb);
        font.getData().setScale(1.2f, 1.2f);
        font.setColor(Color.YELLOW);
        font.draw(sb, "Score : " + score, 500,1030);
        sb.end();

        //region Debugging
        spr.begin(ShapeRenderer.ShapeType.Line);
        debugmode(Color.GREEN, Bar1.getCollider());
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

        Bar1.GetTexture().dispose();
        Bar2.GetTexture().dispose();
        play.GetTexture().dispose();

    }
}
