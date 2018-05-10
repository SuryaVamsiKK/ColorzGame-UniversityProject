package com.Colorz.game.States;

import com.Colorz.game.ColorzCore;
import com.Colorz.game.BuildingBlocks.Block;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Colorz.game.ClassDefinations.GameStatemanager;
import com.Colorz.game.ClassDefinations.state;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.spi.orbutil.fsm.Input;

public class playState extends state {

    private Block play;
    private Texture BG;
    private Block Bar1;
    private Block Bar2;
    private Block Bar3;
    private Block pauseB;
    private Block backB;
    public int score = 0;
    public int highscore = 0;
    private float playerX;
    private float playerY;
    private float playerBX;
    private float playerBY;
    private float speed = 0.5f;
    private float speedS = 0.5f;
    private boolean speedSStrored = false;
    private boolean paused = false;
    private boolean pauseTaken = false;

    private float rots = 0;
    private  boolean block = false;
    private ShapeRenderer spr;
    private boolean inputtaken = false;
    private boolean dir = true;
    private boolean dirS = true;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("AGENCYB.TTF"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font15;


    public playState(GameStatemanager gsm) {
        super(gsm);
        parameter.size = 30;
        font15 = generator.generateFont(parameter);
        generator.dispose();
        Bar1 = new Block(0,ColorzCore.AHEIGHT, 500, 50, 0, ColorzCore.ColourGen());
        Bar2 = new Block(0,ColorzCore.AHEIGHT, 300, 30, 0, ColorzCore.ColourGen());
<<<<<<< HEAD
//      Bar3 = new Block(0,ColorzCore.AHEIGHT, 0, 30, 0, ColorzCore.ColourGen());
=======
        Bar3 = new Block(0,ColorzCore.AHEIGHT, 0, 30, 0, ColorzCore.ColourGen());
>>>>>>> parent of e47f74e... few changes
        pauseB = new Block(525 * ColorzCore.W,980 * ColorzCore.H, 50, 50, 0, "Red");
        backB = new Block(25 * ColorzCore.W,980 * ColorzCore.H, 50, 50, 0, "SkyBlue");
        play = new Block(0,0,50, 50, 0,"Red");
/*        System.out.println(ColorzCore.W);
        System.out.println(ColorzCore.H);*/
        BG = new Texture("Black.jpg");
        spr = new ShapeRenderer();
        //cam.setToOrtho(false, ColorzGame.WIDTH / 2, ColorzGame.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

        if (!Gdx.input.isTouched()) {
            block = false;
            inputtaken = false;
            ColorzCore.touchPass = false;
            pauseTaken = false;
        }

        if(Gdx.input.isTouched() && pauseTaken == false)
        {
            if(ColorzCore.isTouching(pauseB.GetPosition().x, pauseB.GetPosition().y, pauseB.GetScale().x, pauseB.GetScale().y) == true)
            {
                paused = !paused;
                pauseTaken = true;
//                System.out.println(paused);
                if(paused == false)
                {
                    speed = speedS;
                }
            }

            if(ColorzCore.isTouching(backB.GetPosition().x, backB.GetPosition().y, backB.GetScale().x, backB.GetScale().y) == true)
            {
                ColorzCore.touchPass = false;
                gsm.set(new Menu(gsm));
                dispose();
            }
        }

        if(paused == false)
        {
            if (Gdx.input.isTouched()) {
                playerBX = Gdx.input.getX();
                playerBY = ColorzCore.AHEIGHT - Gdx.input.getY() - 22.5f;     // sorry for the magic number
                if (playerBX > play.GetPosition().x && playerBX < play.GetPosition().x + play.GetScale().x) {
                    if (playerBY > play.GetPosition().y && playerBY < play.GetPosition().y + play.GetScale().y) {
                        inputtaken = true;
                    }
                }
                if (inputtaken == true) {
                    if (block == false) {
                        play.SetTexture(ColorzCore.ColourGen());
                    }
                    block = true;
                    playerX = Gdx.input.getX() - (play.GetScale().x / 2);
                    playerY = ColorzCore.AHEIGHT - Gdx.input.getY() - (play.GetScale().y / 2) - 22.5f;     // sorry for the magic number
                    play.SetPosition(new Vector2(playerX, playerY));
                    ColorzCore.touchPass = true;
                }
            }
        }
    }

    @Override
    public void update(float dt) {

        if(paused == false) {
            speedSStrored = false;
            speed += 0.0001f;
        }
        if(paused == true && speedSStrored == false)
        {
            speedS = speed;
            speedSStrored = true;
//            System.out.println(ColorzCore.AHEIGHT);
            speed = 0;
        }

//        System.out.println(ColorzCore.AHEIGHT);
        handleInput();
        Bar1.Update(dt);
        Bar2.Update(dt);
        Bar3.Update(dt);
        play.Update(dt);
        pauseB.Update(dt);
        backB.Update(dt);

        //regionBar1

        if((Bar1.GetPosition().x > (ColorzCore.AWIDTH - Bar1.GetScale().x)) && dir == true)
        {
            dir = false;
        }
        if(Bar1.GetPosition().x < 0 && dir == false)
        {
            dir = true;
        }
        if(dir)
        {
            Bar1.position.add(5f * speed, 0);
        }
        else
        {
            Bar1.position.add(-5f * ColorzCore.W *speed, 0);
        }
        Bar1.position.add(0, -10f * ColorzCore.H * speed);

        if(Bar1.position.y < 0) {
            Bar1.position.x = 0;
            Bar1.position.y = Bar1.startPos.y;
            Bar1.SetTexture(ColorzCore.ColourGen());
            score++;
        }


        if(Intersector.overlapConvexPolygons(play.getCollider(), Bar1.getCollider()))
        {
            if(Bar1.ID != play.ID)
            {
                //Bar1.SetTexture(play.ID);
                gameOver();
            }
        }
        //endregion

        //region Bar2
        if(Bar2.position.y < 0) {
            Bar2.position.x = MathUtils.random(0, ColorzCore.AWIDTH - Bar2.GetScale().x);
            Bar2.position.y = Bar2.startPos.y;
            Bar2.SetTexture(ColorzCore.ColourGen());
            score++;
        }
        Bar2.SetRotation(rots += 2 * speed);
        Bar2.position.add(0, -5f * speed);

        if(Intersector.overlapConvexPolygons(play.getCollider(), Bar2.getCollider()))
        {
            if(Bar2.ID != play.ID)
            {
                //Bar2.SetTexture(play.ID);
                gameOver();
            }
        }

        ColorzCore.highscoreCheck(score);
        highscore = ColorzCore.getHighscore();
        highscore = ColorzCore.getHighscore();
        ColorzCore.SetScore(score);
        //endregion

        //region Bar2
        if(Bar3.position.y < 0) {

            int check = 0;

            check = MathUtils.random(0, 1);

            if(check == 0)
            {
                Bar3.position.x = 0;
                Bar3.SetScale(new Vector2(Bar3.GetScale().x * -1, Bar3.GetScale().y * -1));
            }
            if(check == 1)
            {
                Bar3.position.x = ColorzCore.AWIDTH;
                Bar3.SetScale(new Vector2(-Bar3.GetScale().x, -Bar3.GetScale().y));
            }

            Bar3.position.y = Bar3.startPos.y;
            Bar3.SetTexture(ColorzCore.ColourGen());
            score++;
        }


        if((Bar3.GetScale().x > (ColorzCore.AWIDTH - Bar3.GetScale().x)) && dirS == true)
        {
            dirS = false;
        }
        if(Bar3.GetScale().x < 20f && dirS == false)
        {
            dirS = true;
        }
        if(dir)
        {
            Bar3.SetScale(new Vector2(Bar3.GetScale().x + 5f * speed, Bar3.GetScale().y));
        }
        else
        {
            Bar3.SetScale(new Vector2(Bar3.GetScale().x - 5f * speed, Bar3.GetScale().y));
        }
        Bar3.position.add(0, -7f * speed);

        if(Intersector.overlapConvexPolygons(play.getCollider(), Bar3.getCollider()))
        {
            if(Bar3.ID != play.ID)
            {
                //Bar2.SetTexture(play.ID);
                gameOver();
            }
        }

        ColorzCore.highscoreCheck(score);
        highscore = ColorzCore.getHighscore();
        highscore = ColorzCore.getHighscore();
        ColorzCore.SetScore(score);
        //endregion
    }

    @Override
    public void render(SpriteBatch sb) {

        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(BG, 0, 0, ColorzCore.AWIDTH, ColorzCore.AHEIGHT);
        Bar1.GetSprite().draw(sb);
        Bar2.GetSprite().draw(sb);
        Bar3.GetSprite().draw(sb);
        pauseB.GetSprite().draw(sb);
        backB.GetSprite().draw(sb);
        play.GetSprite().draw(sb);
        font15.getData().setScale(1, 1);
        font15.setColor(Color.YELLOW);
        font15.draw(sb, "Score : " + score, 25 * ColorzCore.W,ColorzCore.AHEIGHT - 125);
        font15.draw(sb, "HighScore : " + highscore, 25 * ColorzCore.W,ColorzCore.AHEIGHT - 175);
        sb.end();

        //region Debugging
       /* spr.begin(ShapeRenderer.ShapeType.Line);
        debugmode(Color.GREEN, Bar1.getCollider());
        debugmode( Color.RED, play.getCollider());
        spr.end();*/
        //endregion
    }

    public void debugmode(Color clr, Polygon ply)
    {
        spr.setColor(clr);
        spr.polygon(ply.getTransformedVertices());
    }

    public void gameOver()
    {
        gsm.set(new GameOver(gsm));
        dispose();
    }

    @Override
    public void dispose() {

        Bar1.GetTexture().dispose();
        Bar2.GetTexture().dispose();
        Bar3.GetTexture().dispose();
        play.GetTexture().dispose();
        BG.dispose();
        pauseB.GetTexture().dispose();
        backB.GetTexture().dispose();

    }
}
