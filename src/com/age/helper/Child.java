package com.age.helper;

import com.age.graphics.Drawable;

/**
 * Created by apex on 06/03/14.
 */
public interface Child {
    Parent getParent();
    Drawable getOrigin();
    void setParent(Parent p);
}
