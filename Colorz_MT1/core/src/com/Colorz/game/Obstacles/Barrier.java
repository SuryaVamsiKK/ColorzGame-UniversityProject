package com.Colorz.game.Obstacles;

import com.Colorz.game.ColorzGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Barrier {

    private Vector2 position;
    private Vector2 startPos;
    private Vector2 velocity;
    private Vector2 scale;
    private Vector2 startScale;
    private float hSpeed;
    private float vSpeed;
    public java.lang.String ID;
    public java.lang.String path;
    private Polygon col;
    private float rotations = 10;

    private Texture barrier;
    private Sprite barriersp;
    private float verts[];

    public Barrier(int x, int y, float scaleX, float scaleY, float Roatats,float HSpeed, float VSpeed, java.lang.String skin)
    {
        hSpeed = HSpeed;
        vSpeed = VSpeed;
        ID = skin;
        position = new Vector2(x, y);
        startPos = new Vector2(x, y);
        scale = new Vector2(scaleX, scaleY);
        startScale = new Vector2(scaleX, scaleY);
        velocity = new Vector2(0, 0);
        path = skin + ColorzGame.TextureFormate;
        barrier = new Texture(path);
        barriersp = new Sprite(barrier);
        verts = new float[]{0,0,scale.x,0,scale.x,scale.y,0,scale.y};
        col = new Polygon(verts);
        rotations = Roatats;
        //col = new Rectangle(x ,y, scaleX, scaleY);
    }

    public void Update(float dt)
    {
        col.setPosition(position.x, position.y);
        col.setOrigin(scale.x/2, scale.y/2);
        barriersp.setPosition(position.x, position.y);
        barriersp.setSize(scale.x, scale.y);
        barriersp.setOrigin(scale.x/2, scale.y/2);
        barriersp.setRotation(rotations);
        col.setRotation(rotations);

        if(position.y < 0)
        {
            position.y = startPos.y;
        }
        if(position.x > ColorzGame.WIDTH)
        {
            position.x = startPos.x - startScale.x;
        }
        position.add(hSpeed, vSpeed);
    }

    public Texture GetTexture()
    {
        return barriersp.getTexture();
    }

    public void SetTexture(String skin)
    {
        if(barrier != null)
        {
            barrier.dispose();
        }
        path = skin + ColorzGame.TextureFormate;
        ID = skin;
        barrier = new Texture(path);
        barriersp.setTexture(barrier);
    }

    public Vector2 GetPosition()
    {
        return position;
    }
    public void  SetPosition(Vector2 pos)
    {
        position = pos;
    }

    public Vector2 GetScale()
    {
        return scale;
    }

    public Sprite GetSprite()
    {
        return barriersp;
    }

    public void  SetScale(Vector2 scl)
    {
        scale = scl;
    }

    public Polygon getCollider()
    {
        return col;
    }

    public void SetRotation(float gg)
    {
        rotations = gg;
    }

    public float GetRotation()
    {
        return rotations;
    }
}
