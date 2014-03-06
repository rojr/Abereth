package com.age.helper;

import com.age.graphics.Drawable;

import java.util.ArrayList;

/**
 * Created by apex on 06/03/14.
 */
public interface Parent {
    ArrayList<Child> getChildren();
    void add(Child d);
    Drawable getOrigin();
}
