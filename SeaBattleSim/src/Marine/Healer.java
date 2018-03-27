package Marine;

import java.util.Random;

import Global.Flotte;
import Global.Terrain;
import Global.ProgBattaille;
import Util.Direction;
import Util.Sonar;
import Exception.TerrainInitNullException;

public class Healer extends Bateau {
	private int heal;
	Direction d = Direction.NORD;
	private static char nHeal = '1';

	/**
	 * Constructeur du bateau Healer.
	 * 
	 * @param nbBateau nombre de bateaux
	 */
	public Healer(int nbBateau) {
		super(nbBateau);
		heal = 50;
		setNomB('H');
		setNum(nHeal);
		nHeal++;

	}

	/**
	 * Ajoute la valeur de heal aux PV de Bateau b
	 * 
	 * @param b le bateau soigné
	 */
	public void heal(Bateau b) {
		if (b.getPv() > 0)
			b.setPv(b.getPv() + heal);
		if (b.getPv() > b.getPV_MAX())
			b.setPv(getPV_MAX());

	}

	/**
	 * Une méthode déplacer qui donne un déplacement aléatoire au bateau hopital
	 * 
	 * @param t prend en paramètre le terrain.
	 */
	public void deplacer(Terrain t) {
		while (getX() + d.getDx() < 0 || getX() + d.getDx() >= t.getTaille() || getY() + d.getDy() >= t.getTaille()
				|| getY() + d.getDy() < 0) {
			d = d.deriver(5);
		}
		this.Bouger(getX() + d.getDx(), getY() + d.getDy());

		for (int i = 0; i < t.getFlotte().getNbBateaux(); i++) {
			if (this.getX() == t.getFlotte().getBateaux()[i].getX()
					&& this.getY() == t.getFlotte().getBateaux()[i].getY()
					&& (this.getNomB() != t.getFlotte().getBateaux()[i].getNomB()
							&& this.getNum() != t.getFlotte().getBateaux()[i].getNum()))
				heal(t.getFlotte().getBateaux()[i]);

		}

	}

	@Override
	public void plusProche(Flotte f) {
		// TODO Auto-generated method stub

	}

}