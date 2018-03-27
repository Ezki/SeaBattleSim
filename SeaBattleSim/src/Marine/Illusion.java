package Marine;
import Exception.TerrainInitNullException;
import Global.Flotte;
import Global.Terrain;

/**
 * Bateau cr�� uniquement lors d'une Detection rat�. Ce bateau copie les informations du bateau mal d�tect�
 * @author Al
 */
public class Illusion extends Bateau { // bateau appel� pour permettre au sonar de stocker de fausses coordonn�es

	/**
	 * Constructeur copiant les donn�es du bateau copi�
	 * @param x coordonn�e horizontale du bateau copi�
	 * @param y coordonn�e verticale du bateau copi�
	 * @param c Nom du bateau copi�
	 * @param num Num�ro du bateau copi�
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
