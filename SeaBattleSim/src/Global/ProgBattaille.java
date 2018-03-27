package Global;

//import Exception.TerrainInitNullException;
/**
 * Programme permettant de simuler une bataille navale, affichée en console.
 * Le programme se déroule sans intervention extérieure
 * Des bateaux parcourent le plateau de jeu à chaque tour. 
 * Les Attaquants pourchassent et tirent sur les autres bateaux
 * Les Healer soignent les autres bateaux
 * Les Cibles sont... des cibles !
 * @author Al
 *
 */
public class ProgBattaille {
	public static void main(String[] args) {
		int nbBateau = 15; // réglable si on veut plus de bateau !
		Terrain t = null;
		t = new Terrain(nbBateau);
		/*
		 * try { t = new Terrain(nbBateau); } catch (TerrainInitNullException e) {}
		 */

		int i = 0;
		while (i < 300) { // nb de tours de jeu !

			for (int j = 0; j < nbBateau; ++j) {

				if (t.getFlotte().getBateaux()[j].getNomB() == 'A') { // si bateau attaquant, lui faire détecter le
																		// bateau le plus proche
					t.getFlotte().getBateaux()[j].plusProche(t.getFlotte());
				}

				if (t.getFlotte().getBateaux()[j].EncoreEnVie()) {
					t.getFlotte().getBateaux()[j].deplacer(t); // modifie les coordonnées x y de chaque bateau pour
																// qu'ils se
					// déplacent d'une case
				}
			}

			t.refresh(); // réinitialise les cases de terrain avec des ~

			for (int j = 0; j < nbBateau; ++j) {
				//if (t.getFlotte().getBateaux()[j].EncoreEnVie()) 
				{
					t.inclureBateau(t.getFlotte().getBateaux()[j]); // met les nouvelles coordonnées du bateau dans le
																	// tableau Terrain
					
				}

			}

			System.out.print(t.toString()); // affiche le plateau de jeu
			System.out.println(toString(nbBateau, t)); // affiche le statut des bateaux

			pause();
			++i;
		}

	}
/**
 * Permet de ralentir l'exécution du programme pour la rendre compréhensible
 */
	private static void pause() { // ralentit l'exécution du programme à chaque tour
		try {
			Thread.sleep(300);
		} catch (Exception e) {
		}
	}

	/**
	 * Décrit l'état des bateaux (PV, Bateaux contenus dans le Sonar)
	 * 
	 * @param nbBateau
	 *            le chiffre limitant la boucle d'écriture
	 * @param t
	 *            le Terrain contenant les bateaux
	 * @return le String qui contient la description
	 */
	public static String toString(int nbBateau, Terrain t) {
		String s = new String();
		s = "";
		s += ("Operation Status");

		s += System.getProperty("line.separator");
		for (int k = 0; k < nbBateau; ++k) {
			if (t.getFlotte().getBateaux()[k].EncoreEnVie()) {
				s += t.getFlotte().getBateaux()[k].getNomB();
				s += t.getFlotte().getBateaux()[k].getNum();
				s += "         PV = " + (t.getFlotte().getBateaux()[k].getPv());

				for (int l = 0; l < nbBateau; l++)
				{
					if (t.getFlotte().getBateaux()[k].getSc().getBateauxAPortee()[l] != null) {

						s += "  Sonar " + l + " : ";
						s += t.getFlotte().getBateaux()[k].getSc().getBateauxAPortee()[l].getNomB();
						s += t.getFlotte().getBateaux()[k].getSc().getBateauxAPortee()[l].getNum();
						s += " Coord : ";
						s += t.getFlotte().getBateaux()[k].getSc().getBateauxAPortee()[l].getX();
						s += "-";
						s += t.getFlotte().getBateaux()[k].getSc().getBateauxAPortee()[l].getY();
						s += "  ";
					}
				}

				s += System.getProperty("line.separator");
			}
		}
		return s;
	}

}
