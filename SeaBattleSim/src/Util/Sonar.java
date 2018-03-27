package Util;

import java.util.Random;

import Global.Flotte;
import Marine.Bateau;
import Marine.Illusion;

public class Sonar { // classe permettant de détecter les bateaux autour d'un
						// bateau
	private int precision = 90; // chance que la détection soit juste
	private static final int portee = 4; // portee en case du sonar
	private Bateau[] bateauxAPortee; // contient les bateaux repérés

	/**
	 * Constructeur du Sonar
	 * 
	 * @param nbBateau
	 *            nombre de bateaux
	 */
	public Sonar(int nbBateau) {
		// bateauxAPortee = new Bateau[nbBateau];
		setBateauxAPortee(nbBateau);

	}

	/**
	 * 
	 * @return int la portée du Sonar
	 */
	public int getPortee() {
		return portee;
	}

	/**
	 * Renvoie un tableau de bateaux qui indique quels bateaux sont à portée du
	 * Sonar.
	 * 
	 * @return un tableau de Bateau
	 */
	public Bateau[] getBateauxAPortee() {
		return bateauxAPortee;
	}
/**
 * Permet de reinitialiser le tableau de bateaux à portés entre deux utilisations
 * @param nbBateau nombre de bateaux
 */
	public void setBateauxAPortee(int nbBateau) {
		this.bateauxAPortee = new Bateau[nbBateau];
	}
/**
 * Permet de changer un bateau dans une case du tableau de bateaux à portée.
 * @param b un bateau
 * @param indiceTab l'indice dans le tableau de bateaux
 */
	public void setBateauxAPortee_Case(Bateau b, int indiceTab) {
		this.bateauxAPortee[indiceTab] = b;
	}
/**
 * Permet d'analyser le tableaux de bateaux de la flotte pour en obtenir les bateaux les plus proches du bateau.
 * @param f la flotte
 * @param b un bateau
 */
	public void detection(Flotte f, Bateau b) {
		Random rd = new Random();
		int randPrecision;
		for (int i = 0; i < f.getNbBateaux(); ++i) {
			randPrecision = rd.nextInt(100);
			if (randPrecision < this.precision) {
				if (portee >= Math.sqrt(Math.pow(2, (f.getBateaux()[i].getX() - b.getX()))
						+ Math.pow(2, (f.getBateaux()[i].getY() - b.getY())))) {
					bateauxAPortee[i] = f.getBateaux()[i];
				} else
					bateauxAPortee[i] = null;

			}

			else { // si le radar échoue
					// bateauxAPortee[i] = null;
				int result = rd.nextInt(2); // on regarde quelle erreur donne le
											// radar.
				//System.out.println("cas de détection rapée");
				switch (result) { // usage d'un switch permet de plus facilement ajouter des cas supplémentaires !

				case 0:
					failMisplace(f, b, i); // dans ce cas, sonar enregistre un bateau avec de mauvaises coordonnées
					//System.out.println("mauvaises coordonnées    ");
					break;

				case 1: // fail_Nosee (donc rien à faire)
					break;

				default:
					break;

				}
			}
		}
	}
/**
 * simple fonction qui teste si le radar renvoie un résultat vrai ou faux
 * @param txDetection  
 * @return Boolean qui indique si le radar se trompe ou pas
 */
	public boolean radarFail(int txDetection) { 
		Random rd = new Random();
		if (rd.nextInt(100) > txDetection)
			return true;
		else
			return false;

	}
/**
 * Cas d'échec ou le sonar enregistre les mauvaises coordonnées d'un bateau
 * @param f la flotte de bateaux
 * @param b un bateau
 * @param indice indice du tableau
 */
	public void failMisplace(Flotte f, Bateau b, int indice) {
		

		Random rd = new Random();
		int x, y;
		do {
			x = rd.nextInt(2) - 1; // permet, additionné à - 1, d'avoir un décalage aléatoire de [-1 ; 1]
			y = rd.nextInt(2) - 1;
		} while (x == 0 && y == 0); // évite que l'illusion ait la position du bateau mal scanné

		//System.out.print(" " + f.getBateaux()[indice].getNomB() + f.getBateaux()[indice].getNum() + "   " + x + "/" + y);
		
		Illusion illu = new Illusion(f.getBateaux()[indice].getX() - x, f.getBateaux()[indice].getY() - y,
				f.getBateaux()[indice].getNomB(), f.getBateaux()[indice].getNum());
		// on crée un bateau de type illusion, qui copie les caractéristiques dubbateau mal détecté, avec un décalage de placement

		this.getBateauxAPortee()[indice] = illu; // on alloue le bateau aux mauvaises coordonnées à la case indice du tableau du sonar.

	}

}
