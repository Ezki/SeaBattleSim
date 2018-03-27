package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Global.Terrain;

class ProgBattailleTest {

	@Test
	public void testCreation() {  // Test de la création des bateau
		int nbBateau = 10;
		Terrain t = new Terrain (nbBateau);
		assertTrue(t.getFlotte() != null);
		assertTrue(t.getFlotte().getBateaux() != null);
		assertTrue(t.getFlotte().getBateaux()[0].getNomB() != '\u0000');
		assertTrue(t.getFlotte().getBateaux()[0].getSc() != null);
		
		
		
	}
	
	@Test
	public void testTypeBateau() {  // Test de la bonne attribution des types de bateau à l'initialisation
		int nbBateau = 10;
		Terrain t = new Terrain (nbBateau);
		int i;
		for (i = 0; i < 3 ; ++i)
		assertTrue(t.getFlotte().getBateaux()[i].getNomB() == 'H');
		for(i = 3; i < 6 ; ++i)
		assertTrue(t.getFlotte().getBateaux()[i].getNomB() == 'A');	
		for(i = 6; i < t.getFlotte().getNbBateaux() ; ++i)
		assertTrue(t.getFlotte().getBateaux()[i].getNomB() == 'C');	
		
	}
	


}
