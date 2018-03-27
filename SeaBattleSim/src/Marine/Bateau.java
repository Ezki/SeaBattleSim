package Marine;
import java.util.Random;

import Global.Flotte;
import Global.Terrain;
import Util.Sonar;
import Exception.TerrainInitNullException;

/**
 * Classe abstraite repr�sentant une entit� Bateau
 * PV_MAX Points de vie initiaux d'un bateau
 * sc Sonar permettant de rep�rer les bateaux alentour
 * pv Points de vie actuels d'un bateau
 * x Coordonn�e horizontale d'un bateau. Commence � 0
 * y Coordonn�e verticale d'un bateau. Commence � 0
 * nomB contient le nom du bateau
 * num contient le num�ro du bateau (incr�ment� par type)
 * @author Al
 *
 */
public abstract class Bateau {
	
	private final static int PV_MAX = 100; // r�sistance du bateau
	private Sonar sc; // permet de d�tecter les bateaux alentour
	private int pv; // stocke les PV actuels
	private int x; // coord horizontale
	private int y; // coord verticale
	private char nomB;  
	private char num; 

	

	/**
	 * Constructeur par d�faut, utilis� seulement pour les Bateaux Illusion
	 * Donne au Bateau ses PV max
	 * Donne une position al�atoire au bateau
	 */
	
	public Bateau() // Constructeur donnant une position al�atoire au bateau
	{
		setPv(getPV_MAX());
		Random rd = new Random();
		setX(rd.nextInt(Terrain.getTaille() - 1));
		setY(rd.nextInt(Terrain.getTaille() - 1));
		
	}
	
	/** 
	 * Constructeur utilis� pour les bateaux cr��s par Flotte. Identique au constructeur par d�faut mais initialise le sonar
	 * @param nbBateau permet de donner une taille au tableau de bateau contenu dans sc
	 */
	
	public Bateau (int nbBateau) // Constructeur donnant une position al�atoire au bateau

	{
		
		setPv(getPV_MAX());
		Random rd = new Random();
		setX(rd.nextInt(Terrain.getTaille() - 1));
		setY(rd.nextInt(Terrain.getTaille() - 1));
		setSc(nbBateau);
		

	}
	
	/**
	 * Permet de d�tecter le bateau le plus proche de celui-ci
	 * @param f contient les informations sur les autres bateaux
	 */
	public abstract void plusProche(Flotte f);
	
	/**
	 * Permet de modifier les coordonn�es d'un bateau, selon des r�gles propres � chaque sous-type de bateau
	 * @param t contient les informations sur le terrain et les autres bateaux
	 */
	public abstract void deplacer(Terrain t); // methode abstraite utilis�e dans les classes d�riv�es de Bateau
	
	/**
	 * V�rifie que les PV d'un Bateau sont sup�rieurs � 0
	 * @return true si les PV sont strictement sup�rieurs � 0
	 */
	public boolean EncoreEnVie() {
		return this.pv > 0;
	}
	
	/**
	 * Remplace le nomB et num d'un bateau par 'X'
	 */
	public void isDead() {
		setNomB('X');
		setNum('X');
	}
	

	public Sonar getSc() {
		return sc;
	}
	
	public void setSc(int nbBateau) {
		this.sc = new Sonar (nbBateau);
	}
	
	
	public char getNum() {
		return this.num;
	}
	
	public void setNum(char n) {
		this.num = n;
	}
	

	public char getNomB() {
		return this.nomB;
	}
	
	protected void setNomB(char nom) {
		this.nomB = nom;
	}
	
	public int getX() {
		return this.x;
	}
	
	protected void setX(int newX) {
		this.x = newX;
	}
	
	
	public int getY() {
		return this.y;
	}
	
	protected void setY(int newY) {
		this.y = newY;
	}
	
	/**
	 * // Permet de d�placer un bateau sur une autre case
	 * @param newX nouvelle coordonn�e horizontale
	 * @param newY nouvelle coordonn�e verticale
	 */
	public void Bouger(int newX, int newY) { 
		this.x=newX;
		this.y=newY;

	}
	protected void setPv(int i) {
		this.pv=i;
		
	}
	
	 public int getPv() {
		return this.pv;
	}
	
	 public int getPV_MAX() {
		return this.PV_MAX;
	}
	
	 
}
