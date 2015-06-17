package modele;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class Case extends Acteur
{
	private PriorityQueue<Objet> listeObjets;
    private ArrayList <Case> entree;
    private ArrayList <Case> sortie;
    
    public ArrayList <Case> getEntree(){
    	return this.entree;
    }
    
    public ArrayList <Case> getSortie() {
    	return this.sortie;
    }
	public void setEntree(ArrayList<Case> entree) {
		this.entree = entree;
	}
	public void setSortie(ArrayList<Case> sortie){
		this.sortie = sortie;
	}
}
