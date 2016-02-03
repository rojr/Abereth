package com.abereth.event.view;

import com.abereth.game.View;
import com.abereth.input.Keyboard;

/**
 * Created by r on 02/02/2016.
 */
public abstract class ViewKeyPressEvent extends ViewEvent {

    private Keyboard.Key[] keyTriggers;
    public ViewKeyPressEvent()
    {
        this( new Keyboard.Key[]{} );
    }

    public ViewKeyPressEvent(Keyboard.Key... key)
    {
        this.keyTriggers = key;
    }

    public boolean isDone(View game) {
        return false;
    }

    @Override
    public void onUpdate( int delta, View view ) {
        if( keyTriggers != null )
        {
            if( this.keyTriggers.length != 0 )
            {
                for( Keyboard.Key key : this.keyTriggers )
                {
                    if( Keyboard.isKeyPressed(key) )
                    {
                        this.onKeyPress( view, key );
                    }
                }
            }
            else
            {
                this.onKeyPress( view, null );
            }
        }
    }

    public abstract void onKeyPress( View view, Keyboard.Key key );
}
