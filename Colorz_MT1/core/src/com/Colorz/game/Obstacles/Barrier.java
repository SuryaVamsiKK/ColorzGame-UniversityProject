package com.Colorz.game.Obstacles;

import com.Colorz.game.ColorzGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Barrier {

    private Vector3 position;
    private Vector3 startPos;
    private Vector3 velocity;
    private Vector3 scale;
    private Vector3 startScale;
    private float hSpeed;
    private float vSpeed;
    public java.lang.String ID;
    public java.lang.String path;
    private Rectangle col;

    private Texture barrier;

    public Barrier(int x, int y, float scaleX, float scaleY, float HSpeed, float VSpeed, java.lang.String skin)
    {
        hSpeed = HSpeed;
        vSpeed = VSpeed;
        ID = skin;
        position = new Vector3(x, y, 0);
        startPos = new Vector3(x, y, 0);
        scale = new Vector3(scaleX, scaleY, 0);
        startScale = new Vector3(scaleX, scaleY, 0);
        velocity = new Vector3(0, 0, 0);
        path = skin + ColorzGame.TextureFormate;
        barrier = new Texture(path);
        col = new Rectangle(x ,y, scaleX, scaleY);
    }

    public void Update(float dt)
    {
        col.setPosition(position.x, position.y);

        if(position.y < 0)
        {
            position.y = startPos.y;
        }
        if(position.x > ColorzGame.WIDTH)
        {
            position.x = startPos.x - startScale.x;
        }
        position.add(hSpeed, vSpeed,0f);
    }

    public Texture GetTexture()
    {
        return barrier;
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
    }

    public Vector3 GetPosition()
    {
        return position;
    }
    public void  SetPosition(Vector3 pos)
    {
        position = pos;
    }

    public Vector3 GetScale()
    {
        return scale;
    }

    public void  SetScale(Vector3 scl)
    {
        scale = scl;
    }

    public Rectangle getCollider()
    {
        return col;
    }
}
