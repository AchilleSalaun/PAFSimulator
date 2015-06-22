package modele;

/**
 * Le puit sert à compter le nombre de clients qui sont partis après avoir attendu trop longtemps dans la file
 *
 */
public class PuitClient extends Case{

	private int compteur = 0;
	
	public void incrementer(){
		compteur++;
	}
}
