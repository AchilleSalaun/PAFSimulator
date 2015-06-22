package modele;

import java.lang.reflect.Array;
import java.util.Queue;



public class FileAttente extends Case 

{
	public FileAttente() {
	}
	
	private int nombrePlacesRestantes;
	public int getNombrePlacesRestantes(){
		return nombrePlacesRestantes;
	}
	public void setNombrePlacesRestantes(int nombrePlaces){
		this.nombrePlacesRestantes=nombrePlaces;
		
	}

}
