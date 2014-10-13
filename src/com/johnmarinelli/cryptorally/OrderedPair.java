package com.johnmarinelli.cryptorally;

public class OrderedPair<X,Y> {
	private X x;
	private Y y;

	public OrderedPair(X left, Y right) {
		this.x = left;
		this.y = right;
	}
	
	public X getX() { 
		return this.x;
	}
	
	public Y getY() {
		return this.y;
	}
	
	public void setX(X x) {
		this.x = x;
	}
	
	public void setY(Y y) {
		this.y = y;
	}
}
