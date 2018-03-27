package Marine;

import java.util.Random;

import Global.Flotte;
import Global.Terrain;
import Exception.TerrainInitNullException;

public class Cible extends Bateau {
	private boolean descend = true; // controleur de direction
	private boolean droite = true;
	private static char nCible = '1'; // donne son numéro de cible à chaque
										// cible créée

	/**
	 * Constructeur du bateau Cible
	 * 
	 * @param nbBateau nombre de bateaux
	 */
	public Cible(int nbBateau)

	{
		super(nbBateau);
		setNomB('C');
		setNum(nCible);
		nCible++;

	}

	/**
	 * Une méthode déplacer qui fait faire au bateau le déplacement en
	 * boustrophédon.
	 * 
	 * @param t prend en paramètre le terrain.
	 */
	public void deplacer(Terrain t) { // déplacement en boustrophédon
		if (getX() == 0 && getY() == Terrain.getTaille() - 1 && descend && !droite) { 
			droite = true;
			this.Bouger(getX() + 1, getY()); // déplacement vers la droite
		} else if (getX() == Terrain.getTaille() - 1 && getY() == 0 && !descend && droite) { 
			droite = false;
			this.Bouger(getX() - 1, getY());
		} else if (descend) {
			if (getX() < Terrain.getTaille() - 1 && droite) {
				this.Bouger(getX() + 1, getY());
			} else if (getX() == Terrain.getTaille() - 1 && droite && getY() != Terrain.getTaille() - 1) {
				this.Bouger(getX(), getY() + 1);
				droite = false;
			} else if (getX() > 0 && !droite) {
				this.Bouger(getX() - 1, getY());
			} else if (getX() == 0 && !droite) {
				this.Bouger(getX(), getY() + 1);
				droite = true;
			} else if (getX() == Terrain.getTaille() - 1 && getY() == Terrain.getTaille() - 1) {
				descend = false;
				droite = false;
				this.Bouger(getX() - 1, getY());
			}

		} else {
			if (getX() > 0 && !droite) {
				this.Bouger(getX() - 1, getY());
			} else if (getX() == 0 && !droite && getY() > 0) {
				this.Bouger(getX(), getY() - 1);
				droite = true;
			} else if (getX() < Terrain.getTaille() - 1 && droite) {
				this.Bouger(getX() + 1, getY());
			} else if (getX() == Terrain.getTaille() - 1 && droite) {
				this.Bouger(getX(), getY() - 1);
				droite = false;
			} else if (getX() == 0 && getY() == 0) {
				descend = true;
				droite = true;
				this.Bouger(getX() + 1, getY());
			}

		}
	}

	@Override
	public void plusProche(Flotte f) {
		// TODO Auto-generated method stub

	}
}
