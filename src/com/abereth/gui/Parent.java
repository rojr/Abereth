package com.abereth.gui;

import com.abereth.draw.Drawable;

import java.util.ArrayList;

/**
 * Created by exfos on 23/01/15.
 */
public interface Parent
{
	ArrayList<Child> getChildren();
	void add(Child d);
	Drawable getOrigin();
}
