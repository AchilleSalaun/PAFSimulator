package modele;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class Case
{
	private PriorityQueue<Objet> listeObjets;
    private ArrayList <Case> entree;
    private ArrayList <Case> sortie;
    //private long wait ;
    
    public ArrayList <Case> getEntree(){
    	return this.entree;
    }
    
    /*public long getWait(){
    	return this.wait;
    }*/
    
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
