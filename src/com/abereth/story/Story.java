package com.abereth.story;

import com.abereth.event.EventManager;
import com.abereth.event.story.StoryEvent;
import com.abereth.view.View;

/**
 * Created by roger on 29/01/15.
 */
public class Story extends EventManager<StoryEvent, View>
{
    public Story(View view) {
        super(view);
    }
}
