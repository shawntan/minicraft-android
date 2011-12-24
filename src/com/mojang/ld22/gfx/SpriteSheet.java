package com.mojang.ld22.gfx;

import android.graphics.Bitmap;

public class SpriteSheet {
	public int width, height;
	public int[] pixels;

	public SpriteSheet(Bitmap image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width*height];
		System.out.println(width+"x"+height);
		/**
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (pixels[i] & 0xff) / 64;
		}**/
		for(int y = 0; y < height;y++) {
			for(int x=0; x< width; x++) {
				pixels[x+ y*width] = (image.getPixel(x, y) & 0xff) / 64;
			}
		}
	}
}