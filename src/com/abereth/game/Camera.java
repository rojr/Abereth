package com.abereth.game;

public class Camera
{
    private View view;

    public Camera ( View view, int x, int y, int width, int height )
    {
        super ();
    }

    public View getView()
    {
        return this.view;
    }

    public void render()
    {
        getView().getDrawList ().render (  );
    }
}
