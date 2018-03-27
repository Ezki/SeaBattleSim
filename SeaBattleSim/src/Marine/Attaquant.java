package Marine;

import java.util.Random;

import Global.Flotte;
import Global.Terrain;
import Util.Sonar;
import Exception.TerrainInitNullException;

public class Attaquant extends Bateau {
	private final static int DEGATS = 10;
	private int distanceAttaque = 3;
	private int indicePlusProche;// attention a la distance differente de 0
	private static char nAtq = '1';
/**
 * Constructeur du bateau Attaquant.
 * @param nbBateau nombre de bateaux
 */
	public Attaquant(int nbBateau)

	{
		
		super(nbBateau);
		setNomB('A');
		setNum(nAtq);
		setDistanceAttaque(3);
		nAtq++;

		int indicePlusProche = -1;
	}
/**
 * M�thode permettant d'obtenir apr�s annalyse du tableau du sonar le bateau le plus proche de celui ci.
 * si deux bateaux sont � la m�me distance il m�morise le dernier verifi�.
 * @param f la flotte comprenant la totalit� des bateaux.
 */
	@Override
	public void plusProche(Flotte f) {
		this.getSc().detection(f, this);
		double distance = getSc().getPortee();
		for (int i = 0; i < f.getNbBateaux(); ++i) {
			if (this.getSc().getBateauxAPortee()[i] != null) {
				 if ((f.getBateaux()[i].getY() != this.getY()
						&& f.getBateaux()[i].getX() != this.getX())
						&& distance >= Math.sqrt(Math.pow(2, (f.getBateaux()[i].getX() - this.getX()))
								+ Math.pow(2, (f.getBateaux()[i].getY() - this.getY())))) {
					this.indicePlusProche = i;
					distance = Math.sqrt(Math.pow(2, (f.getBateaux()[i].getX() - this.getX()))
							+ Math.pow(2, (f.getBateaux()[i].getY() - this.getY())));
				}
			}
		}
	}
/**
 * 
 * @return int distance d'attaque d'un bateau
 */
	public int getDistanceAttaque() {
		return distanceAttaque;
	}
/**
 * M�thode permettant de changer la distance d'attaque d'un bateau.
 * @param a nouvelle distance d'attaque
 */
	public void setDistanceAttaque(int a) {
		this.distanceAttaque = a;
	}

	/**
	 * Une m�thode qui permet de faire des d�gats aux bateaux pr�sents sur la
	 * case du bateau cibl�.
	 * 
	 * @param t
	 *            prend en param�tre le terrain.
	 */
	public void attaque(Terrain t) {

		for (int i = 0; i < t.getFlotte().getNbBateaux(); ++i)

			if (distanceAttaque >= (Math.sqrt(Math.pow(2, (t.getFlotte().getBateaux()[i].getX() - this.getX()))
					+ Math.pow(2, (t.getFlotte().getBateaux()[i].getY()
							- this.getY()))))&& t.getFlotte().getBateaux()[i]!=this) {
				if (t.getFlotte().getBateaux()[i].getX() == t.getFlotte().getBateaux()[indicePlusProche].getX()
						&& t.getFlotte().getBateaux()[i].getY() == t.getFlotte().getBateaux()[indicePlusProche].getY()
						&& t.getFlotte().getBateaux()[i].getPv() > 0) {
					t.getFlotte().getBateaux()[i].setPv(t.getFlotte().getBateaux()[i].getPv() - DEGATS);
					if (!t.getFlotte().getBateaux()[i].EncoreEnVie())
						t.getFlotte().getBateaux()[i].isDead();
				}
			}
	}

	/**
	 * Une m�thode d�placer qui en fonction de la pr�sence de bateaux autour du
	 * bateau attaquant choisi de fa�on de se d�placer diff�rente.
	 * 
	 * @param t
	 *            prend en param�tre le terrain.
	 */
	public void deplacer(Terrain t) {
		Random r = new Random();
		int i;

		if (indicePlusProche == -1) {// si pas de bateaux à portée
										// ,deplacement random
			do {
				i = r.nextInt(3) - 1;// donne un random de -1 ,0 ou1
			} while (this.getX() + i < 0 || this.getX() + i > t.getTaille() - 1);
			this.setX(this.getX() + i);
			do {
				i = r.nextInt(3) - 1;// donne un random de -1 ,0 ou1

			} while (this.getY() + i < 0 || this.getY() + i > t.getTaille() - 1);
			this.setY(this.getY() + i); // est remplacable par deriver
		} else {

			if (t.getFlotte().getBateaux()[indicePlusProche].getX() > this.getX())
				this.setX(this.getX() + 1);
			else if (t.getFlotte().getBateaux()[indicePlusProche].getX() < this.getX())
				this.setX(this.getX() - 1);
			else if (t.getFlotte().getBateaux()[indicePlusProche].getX() == this.getX())
				;
			if (t.getFlotte().getBateaux()[indicePlusProche].getY() > this.getY())
				this.setY(this.getY() + 1);
			else if (t.getFlotte().getBateaux()[indicePlusProche].getY() < this.getY())
				this.setY(this.getY() - 1);
			else if (t.getFlotte().getBateaux()[indicePlusProche].getY() == this.getY())
				;
			this.attaque(t);
			indicePlusProche = -1;
		}

	}
}
