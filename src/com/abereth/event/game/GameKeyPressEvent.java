package com.abereth.event.game;

import com.abereth.game.Game;
import com.abereth.input.Keyboard;

/**
 * Created by r on 02/02/2016.
 */
public abstract class GameKeyPressEvent extends GameEvent {

    private Keyboard.Key[] keyTriggers;
    public GameKeyPressEvent()
    {

    }

    @Override
    public boolean isDone(Game game) {
        return false;
    }

    @Override
    public void onUpdate(int delta, Game game) {
        if( keyTriggers != null )
        {
            for( Keyboard.Key key : this.keyTriggers )
            {
                if( Keyboard.getPressedCharacter() )
                {

                }
            }
        }
    }
}
