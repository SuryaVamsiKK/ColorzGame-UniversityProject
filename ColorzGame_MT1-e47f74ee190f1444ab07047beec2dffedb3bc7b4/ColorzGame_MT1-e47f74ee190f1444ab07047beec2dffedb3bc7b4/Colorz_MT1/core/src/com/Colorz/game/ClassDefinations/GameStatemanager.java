package com.Colorz.game.ClassDefinations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by SuryaVamsiKK on 3/12/2018.
 */

public class GameStatemanager {

    private Stack<state> states;

    public GameStatemanager()
    {
        states = new Stack<state>();
    }

    public void push(state state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop();
    }

    public void set(state state)
    {
        states.pop();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public  void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
}
