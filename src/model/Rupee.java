package model;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * it is to collect rupee used like a money
 * */
public class Rupee implements Serializable {
	private static final long serialVersionUID = 1L;
	 private int x;
	 private int y;
	 /**
	  * manages if it was collected the rupee
	  * */
	 private boolean collected = false;
	 /**
	  * creates a new rupee
	  * */
	 public Rupee(int x, int y) {
		 this.x = x;
		 this.y = y;
	 }
	 /**
	  * returns rupee collision area
	  * */
	 public Rectangle getBounds() {
		 return new Rectangle(x,y,24,24);
	 }
	 /**
	  * return X position rupee
	  * */
	 public int getX() {
		 return x;
	 }
	 /**
	  * return Y position rupee
	  * */
	 public int getY() {
		 return y;
	 }
	 /**
	  * verify if its true if rupee was collected
	  * */
	public boolean isCollected() {
		return collected;
	}
	/**
	 * mark the rupee as collected
	 * */
	public void collect() {
		collected = true;
	}
}