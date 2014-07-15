package net.soulfoam.game.entities;

import java.awt.Rectangle;

public class DoubleRectangle {
	
	public double x, y, width, height;
	
	public DoubleRectangle(){
		setBounds(0, 0, 0, 0);
	}
	
	public DoubleRectangle(double x, double y, double width, double height){
		setBounds(x, y, width, height);
	}
	
	public void setBounds(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}
