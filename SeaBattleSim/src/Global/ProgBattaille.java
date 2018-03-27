package Global;

//import Exception.TerrainInitNullException;
/**
 * Programme permettant de simuler une bataille navale, affich�e en console.
 * Le programme se d�roule sans intervention ext�rieure
 * Des bateaux parcourent le plateau de jeu � chaque tour. 
 * Les Attaquants pourchassent et tirent sur les autres bateaux
 * Les Healer soignent les autres bateaux
 * Les Cibles sont... des cibles !
 * @author Al
 *
 */
public class ProgBattaille {
	public static void main(String[] args) {
		int nbBateau = 15; // r�glable si on veut plus de bateau !
		Terrain t = null;
		t = new Terrain(nbBateau);
		/*
		 * try { t = new Terrain(nbBateau); } catch (TerrainInitNullException e) {}
		 */

		int i = 0;
		while (i < 300) { // nb de tours de jeu !

			for (int j = 0; j < nbBateau; ++j) {

				if (t.getFlotte().getBateaux()[j].getNomB() == 'A') { // si bateau attaquant, lui faire d�tecter le
																		// bateau le plus proche
					t.getFlotte().getBateaux()[j].plusProche(t.getFlotte());
				}

				if (t.getFlotte().getBateaux()[j].EncoreEnVie()) {
					t.getFlotte().getBateaux()[j].deplacer(t); // modifie les coordonn�es x y de chaque bateau pour
																// qu'ils se
					// d�placent d'une case
				}
			}

			t.refresh(); // r�initialise les cases de terrain avec des ~

			for (int j = 0; j < nbBateau; ++j) {
				//if (t.getFlotte().getBateaux()[j].EncoreEnVie()) 
				{
					t.inclureBateau(t.getFlotte().getBateaux()[j]); // met les nouvelles coordonn�es du bateau dans le
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
 * Permet de ralentir l'ex�cution du programme pour la rendre compr�hensible
 */
	private static void pause() { // ralentit l'ex�cution du programme � chaque tour
		try {
			Thread.sleep(300);
		} catch (Exception e) {
		}
	}

	/**
	 * D�crit l'�tat des bateaux (PV, Bateaux contenus dans le Sonar)
	 * 
	 * @param nbBateau
	 *            le chiffre limitant la boucle d'�criture
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
