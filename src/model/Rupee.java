package model;
import java.awt.Rectangle;
import java.io.Serializable;


public class Rupee implements Serializable {
	private static final long serialVersionUID = 1L;
	 private int x;
	 private int y;
	 
	 private boolean collected = false;
	 
	 public Rupee(int x, int y) {
		 this.x = x;
		 this.y = y;
	 }
	 public Rectangle getBounds() {
		 return new Rectangle(x,y,24,24);
	 }
	 
	 public int getX() {
		 return x;
	 }
	 
	 public int getY() {
		 return y;
	 }
	 
	public boolean isCollected() {
		return collected;
	}
	
	public void collect() {
		collected = true;
	}
}