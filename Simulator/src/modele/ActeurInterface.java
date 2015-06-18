package modele;

import simulatorpack.Echeancier;


public interface ActeurInterface 
{
	
	public void realise(int action, Echeancier echeancier) ;
	/**{
		switch(action)
		{
		  	case 1: this.generer() ; //generer un objet
		  	case 2: this.patienter() ; //patienter
		  	case 3: this.passer() ; //passer a  la case suivante
		  	case 4: this.partir() ; // partir 
			default : // ne rien faire  	
		}
	}	*/
	

	
}
