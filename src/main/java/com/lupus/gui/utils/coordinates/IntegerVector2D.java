package com.lupus.gui.utils.coordinates;

public class IntegerVector2D extends Vector2D<Integer> {
	public IntegerVector2D(int x, int y) {
		super(x, y);
	}
	public IntegerVector2D(int slot){
		super(slot%9, (int) Math.nextDown((double)slot/9d));
	}
}
