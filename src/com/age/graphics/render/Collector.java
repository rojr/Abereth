package com.age.graphics.render;

import java.util.ArrayList;
import java.util.Arrays;

import com.age.Age;
import com.age.graphics.Camera;
import com.age.graphics.Drawable;

public class Collector{

	public static ArrayList<Layer> drawArray = new ArrayList<Layer>();

	public static boolean add(Drawable p) {
		/*
		 * This is to make sure the graphic is in bounds when added, otherwise
		 * if it's not it won't add it
		 */
		for (Camera cam : Age.cameraList) {
			float tX = -cam.getTranslateX();
			float tY = cam.getTranslateY();
			float cW = cam.getWidth();
			float cH = cam.getHeight();

			double eX = p.getDrawX();
			double eY = p.getDrawY();
			double eW = p.getDrawWidth();
			double eH = p.getDrawHeight();
			boolean one = eX >= -tX && eX <= -tX + cW && eY >= -tY
					&& eY <= -tY + cH;
			boolean two = eX + eW >= -tX && eX + eW <= -tX + cW && eY >= -tY
					&& eY <= -tY + cH;
			boolean three = eX + eW >= -tX && eX + eW <= -tX + cW
					&& eY + eH >= -tY && eY + eH <= -tY + cH;
			boolean four = eX >= -tX && eX <= -tX + cW && eY + eH >= -tY
					&& eY + eH <= -tY + cH;
			//if (one || two || three || four || !p.isUseTranslate()) {
			if(true){
				int layer = p.getLayer();
				for (Layer l : drawArray) {
					if (l.getNumber() == layer) {
						l.add(p);
						return true;
					}
				}
				drawArray.add(new Layer(layer));
				Layer[] temp = new Layer[drawArray.size()];
				for (int i = 0; i < drawArray.size(); i++) {
					temp[i] = drawArray.get(i);
				}
				Arrays.sort(temp);
				for (int i = 0; i < drawArray.size(); i++) {
					drawArray.set(i, temp[i]);
				}
				return true;
			}
		}
		return false;
	}

	public static class Layer implements Comparable{
		private ArrayList<Drawable> drawList = new ArrayList<Drawable>();
		private int number;

		public Layer(int number) {
			this.number = number;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public void add(Drawable d) {
			drawList.add(d);
		}

		public ArrayList<Drawable> getList() {
			return drawList;
		}

		public void clear() {
			drawList.clear();
		}

		public int compareTo(Object l) {
			int otherLayer = ((Layer) l).getNumber();
			return this.number - otherLayer;
		}
	}
}
