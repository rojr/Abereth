package com.gmail.robmadeyou.Draw;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Layer;

import java.util.ArrayList;

public class Collector {

    static ArrayList<DrawParameters> drawArraySorted = new ArrayList<DrawParameters>();
    static ArrayList<DrawParameters> drawArrayUnsorted = new ArrayList<DrawParameters>();

    public static void add(DrawParameters p) {
        drawArrayUnsorted.add(p);
    }

    public static void organize() {
        int currentLayer = 0;
        while (drawArrayUnsorted.size() != drawArraySorted.size()) {
            for (DrawParameters aDrawArrayUnsorted : drawArrayUnsorted) {
                if (aDrawArrayUnsorted.getLayer() == currentLayer) {
                    drawArraySorted.add(aDrawArrayUnsorted);
                }
            }
            currentLayer++;
            if (currentLayer > Layer.layers) {
                break;
            }
        }
        System.out.println(drawArraySorted.size());
    }

    public static void clear() {
        drawArraySorted.clear();
        drawArrayUnsorted.clear();
    }

    public static class DrawParameters {

        private double x, y, w, h;
        private int texID;
        private int layerID;
        private Color color;
        private String type;
        private boolean useTranslate;
        private float opacity;
        private boolean inverts;

        public DrawParameters(String type, double x, double y, double width, double height,
                              int texID, Color color, int layerID) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = texID;
            this.color = color;
            this.layerID = layerID;
            this.useTranslate = false;
            this.opacity = 1f;
            this.inverts = false;
        }

        public DrawParameters(String type, double x, double y, double width, double height,
                              int texID, Color color, int layerID, boolean useTranslate) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = texID;
            this.color = color;
            this.layerID = layerID;
            this.useTranslate = useTranslate;
            this.opacity = 1f;
            this.inverts = false;
        }

        public DrawParameters(String type, double x, double y, double width, double height,
                              int texID, Color color, float opacity, int layerID, boolean useTranslate) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = texID;
            this.color = color;
            this.layerID = layerID;
            this.useTranslate = useTranslate;
            this.opacity = opacity;
            this.inverts = false;
        }

        public DrawParameters(String type, double x, double y, double width, double height,
                              int texID, Color color, float opacity, int layerID, boolean useTranslate, boolean inverts) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = texID;
            this.color = color;
            this.layerID = layerID;
            this.useTranslate = useTranslate;
            this.opacity = opacity;
            this.inverts = inverts;
        }

        public DrawParameters(String type, double x, double y, double width, double height,
                              int texID, Color color, float opacity, int layerID) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = texID;
            this.color = color;
            this.layerID = layerID;
            this.opacity = opacity;
            this.inverts = false;
        }

        public String getType() {
            return type;
        }

        public int getLayer() {
            return layerID;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getWidth() {
            return w;
        }

        public double getHeight() {
            return h;
        }

        public Color getColor() {
            return color;
        }

        public int getTextureID() {
            return texID;
        }

        public boolean useTranslate() {
            return useTranslate;
        }

        public float getOpacity() {
            return opacity;
        }

        public boolean getInverts() {
            return inverts;
        }
    }
}
