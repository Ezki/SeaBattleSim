package Global;
import Marine.Attaquant;
import Marine.Bateau;
import Marine.Cible;
import Marine.Healer;
import Exception.TerrainInitNullException;

/**
 * Classe permettant de gérer l'ensemble des bateaux
 * @author Al
 * Bateau[] contient l'ensemble des bateaux créés
 * NbBateau permet de retrouver le nombre de bateaux créés
 */
public class Flotte {
	private Bateau[] Bateaux;  // tableau qui contient l'ensemble des bateaux créés
	private static int nbBateaux;
	
	
/**
 * Constructeur permettant de créer nbBateau Bateau dans le tableau de bateau Bateaux
 * Le 1er 1/3 des bateaux créés seront des Healer
 * Le 2e 1/3 des bateaux créés seront des Attaquant
 * Le reste des bateaux créés seront des Cible
 * @param nbBateau indique le nombre de bateau à créer
 */
	public Flotte(int nbBateau) // crée une flotte composé à un tiers d'hopitaux, d'un tiers d'attaquant et de 4/10 de cible
	//throws TerrainInitNullException
	{ 
		this.nbBateaux=nbBateau;
		Bateaux= new Bateau[nbBateau];
		int i;
		for( i=0;i<(nbBateau/3);++i) {
			Bateaux[i]=new Healer(nbBateau);
		}
		for(i=(nbBateau/3);i<(2*(nbBateau/3));++i) {
			Bateaux[i]=new Attaquant(nbBateau);
		}
		for(i=(2*(nbBateau/3));i<nbBateau;++i) {
			Bateaux[i]= new Cible(nbBateau);
		}
		
	/*	for (i = 0 ; i < nbBateau ; ++i)
		{
			if (Bateaux[i].getSc() == null)
				System.out.println("prout");
		} */

		
	}
	public Bateau[] getBateaux() {
		return Bateaux;
	}
	public int getNbBateaux() {
		return nbBateaux;
	}
	
}
