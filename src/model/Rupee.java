package model;
import java.awt.Rectangle;

public class Rupee {
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