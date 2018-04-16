package com.Colorz.game.ClassDefinations;

import com.Colorz.game.ColorzGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {

    private Vector3 position;
    private Vector3 startPos;
    private Vector3 scale;
    private Vector3 startScale;
    public java.lang.String ID;
    public java.lang.String path;
    private Rectangle col;

    private Texture player;

    public Player (int x, int y, float scaleX, float scaleY, String skin)
    {
        position = new Vector3(x, y, 0);
        startPos = new Vector3(x, y, 0);
        scale = new Vector3(scaleX, scaleY, 0);
        startScale = new Vector3(scaleX, scaleY, 0);
        ID = skin;
        path = skin + ColorzGame.TextureFormate;
        player = new Texture(path);
        col = new Rectangle(x, y, scaleX, scaleY);
    }

    public void Update(float dt)
    {
        col.setPosition(position.x, position.y);
    }

    public Texture GetTexture()
    {
        return player;
    }

    public void SetTexture(String skin)
    {
        if(player != null)
        {
            player.dispose();
        }
        path = skin + ColorzGame.TextureFormate;
        ID = skin;
        player = new Texture(path);
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
