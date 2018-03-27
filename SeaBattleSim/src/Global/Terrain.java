package Global;
//import Exception.TerrainInitNullException;
import Marine.Bateau;

/**
 * Plateau de jeu représentant l'océan où évoluent les bateaux
 * @author Al
 * terrain et terrain 2 contiennent l'ensemble des paires de caractères représentant les cases du tableau.
 * ces paires évoluent en fonction de ce qui les occupent (case vide ou bateau)
 */
public class Terrain {
	private final static int TAILLE_OCEAN = 20;
	private char[][] terrain;
	private char[][] terrain2;
	private Flotte f;
	
	
	/**
	 * Constructeur permettant d'initialiser la Flotte qui crééra les Bateaux
	 * @param nbBateau contient le nombre de bateaux à initialiser
	 */
	public Terrain(int nbBateau) {
		this(TAILLE_OCEAN, TAILLE_OCEAN);
		f=new Flotte(nbBateau);	
	}

	/**
	 * Constructeur du Terrain initial
	 * @param tLigne contient le nombre de lignes
	 * @param tColonne contient le nombre de colonnes
	 */
	public Terrain(int tLigne, int tColonne) {
		terrain = new char[tLigne][tColonne];
		terrain2 = new char[tLigne][tColonne]; 
		for (int i = 0; i < tLigne; ++i) {
			for (int j = 0; j < tColonne; ++j) {
				this.terrain[i][j] = '~';
				this.terrain2[i][j] = '~';
			}

		}

	}

	
	/**
	 * Permet l'affichage du plateau à chaque tour de jeu
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < TAILLE_OCEAN; ++i) {
			for (int j = 0; j < TAILLE_OCEAN; ++j) {
				s += " " + this.terrain[j][i] + this.terrain2[j][i];
			}
			s += " ";
			s += System.getProperty("line.separator");
		}
		for (int i = 0; i < TAILLE_OCEAN + 1 ; ++i)
		{
			s += "   ";
		}
		s += System.getProperty("line.separator");
		return s;
	}	
	
	public  void setChar(int x,int y, char newChar) { // setter du tableau terrain
		this.terrain[x][y]=newChar;
	}
	
	public static int getTaille(){ // getter de la taille du terrain
		return TAILLE_OCEAN;
	}
	public char[][] getTerrain(){
		return terrain;
	}
	
	public char[][] getTerrain2(){
		return terrain2;
	}
	
	public Flotte getFlotte() {
		return this.f;
	}	
	
	public char getChar(int x, int y){ // getter du tableau terrain
		return this.terrain[x][y];
	}
	
	/**
	 * remplace les ~ des cases correspondants aux bateaux par leur nom et numéro
	 * @param b le bateau dont les coordonnées doivent être enregistrées
	 */
	public void inclureBateau (Bateau b) { 
		this.getTerrain()[b.getX()][b.getY()]=b.getNomB();
		this.getTerrain2()[b.getX()][b.getY()]=b.getNum();
	}
	
	/**
	 * met des ~ sur toutes les cases du tableau
	 */
	public void refresh() { // 
		
		for (int i = 0; i < TAILLE_OCEAN; ++i) {
			for (int j = 0; j < TAILLE_OCEAN; ++j) {
				this.terrain[i][j] = '~';
				this.terrain2[i][j] = '~';
			}

		}
	}
	
}
