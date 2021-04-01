package com.lupus.gui.utils.coordinates;

public class Vector2D<E> {
	private E x, y;

	public Vector2D(E x, E y) {
		this.x = x;
		this.y = y;
	}

	public E getX() {
		return x;
	}

	public void setX(E x) {
		this.x = x;
	}

	public E getY() {
		return y;
	}

	public void setY(E y) {
		this.y = y;
	}
}
