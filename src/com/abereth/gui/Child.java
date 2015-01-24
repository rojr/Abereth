package com.abereth.gui;

import com.abereth.draw.Drawable;

/**
 * Created by exfos on 23/01/15.
 */
public interface Child
{
	Parent getParent();
	Drawable getOrigin();
	void setParent(Parent p);
}
