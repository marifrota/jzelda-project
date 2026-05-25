package model;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * it is to collect rupee used like a money
 * */
public class Rupee implements Serializable {
	private static final long serialVersionUID = 1L;
	 private int posicaox;
	 private int posicaoy;
	 private int valor;
	 /**
	  * manages if it was collected the rupee
	  * */
	 private boolean coletada = false;//collected
	 
	 public static final int ROSA = 1; //pink rupee
	 public static final int AZUL = 5;//blue rupee
	 public static final int VERMELHO = 10; //red rupee
	 public static final int DOURADO = 15;//gold rupee
	 public static final int ROXO = 20; // purple rupee
	 /**
	  * creates a new rupee pink by default
	  * */
	 public Rupee(int posicaox, int posicaoy) {
	        this(posicaox, posicaoy, ROSA);
	 }
	 /**
	  * this is for create a currency with a speciic type
	  */
	 public Rupee(int posicaox, int posicaoy, int valor) {
	        this.posicaox = posicaox;
	        this.posicaoy = posicaoy;
	        this.valor = valor;
	}
	 /**
	  * returns rupee collision area
	  * */
	 public Rectangle getBounds() {
		 return new Rectangle(posicaox,posicaoy,24,24);
	 }
	 /**
	  * return X position rupee
	  * */
	 public int getposicaoX() {
		 return posicaox;
	 }
	 
	 /**
	  * return Y position rupee
	  * */
	 public int getposicaoY() {
		 return posicaoy;
	 }
	 /**
	  * verify if its true if rupee was collected
	  * */
	public boolean iscoletada() {
		return coletada;
	}
	/**
	 * mark the rupee as collected
	 * */
	public void coletada() {
		coletada = true;
	}
	/**
	 * return the rupee valore
	 */
	public int getValor() {
		return valor;
	}
}