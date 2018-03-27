package Marine;
import Exception.TerrainInitNullException;
import Global.Flotte;
import Global.Terrain;

/**
 * Bateau créé uniquement lors d'une Detection raté. Ce bateau copie les informations du bateau mal détecté
 * @author Al
 */
public class Illusion extends Bateau { // bateau appelé pour permettre au sonar de stocker de fausses coordonnées

	/**
	 * Constructeur copiant les données du bateau copié
	 * @param x coordonnée horizontale du bateau copié
	 * @param y coordonnée verticale du bateau copié
	 * @param c Nom du bateau copié
	 * @param num Numéro du bateau copié
	 */
	
	public Illusion (int x, int y, char c, char num) 
		{
		super();
		setX(x);
		setY(y);		
		setNomB(c);
		setNum(num);

		
	}

	@Override
	public void deplacer(Terrain t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plusProche(Flotte f) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
