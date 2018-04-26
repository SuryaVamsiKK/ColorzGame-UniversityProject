package com.Colorz.game.BuildingBlocks;

import com.Colorz.game.ColorzGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Block {

    public Vector2 position;
    public Vector2 startPos;
    private Vector2 velocity;
    private Vector2 scale;
    public Vector2 startScale;
    public java.lang.String ID;
    public java.lang.String path;
    private Polygon col;
    private float rotations = 10;
    private Texture barrier;
    private Sprite barriersp;
    private float verts[];

    public Block(int x, int y, float scaleX, float scaleY, float Roatats, java.lang.String skin)
    {
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
