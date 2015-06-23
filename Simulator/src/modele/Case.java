package modele;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Case extends Acteur
{
	private LinkedList<Objet> listeObjets;
    private ArrayList<Case> sortie;
    private ArrayList<Case> echappatoire;
    private int capacity;
	
    public Case(int capacity)
    {
    	LinkedList<Objet> liste = new LinkedList<Objet>();
    	this.listeObjets = liste ;
    	
    	ArrayList<Case> listeSortie = new ArrayList<Case>();
    	this.sortie = listeSortie ;
    	
    	ArrayList<Case> listeEchap = new ArrayList<Case>();
    	this.echappatoire = listeEchap ;
    	this.capacity = capacity ;
    }
	
	public int getCapacity(){
		return this.capacity;
	}

	public void setCapacity(int capacity2){
		this.capacity = capacity2;
	}
    public LinkedList<Objet> getListeObjets(){
    	return this.listeObjets;
    }
    
    public ArrayList<Case> getSortie() {
    	return this.sortie;
    }
    
    public ArrayList<Case> getEchappatoire() {
    	return this.echappatoire;
    }
    public void setEchappatoire(ArrayList<Case> echappatoire){
		this.echappatoire=echappatoire;
	}
	
    public void relierEchappatoire(Case caseEchap)
    {
    	this.echappatoire.add(caseEchap);
    }
    
	public void setSortie(ArrayList<Case> sortie){
		this.sortie = sortie;
	}
	
	public void relierSortie(Case caseSortie)
	{
		this.sortie.add(caseSortie);
	}
	
	public Objet getFirstObjet(){
		Objet firstObjet = (this.listeObjets).poll();
		return firstObjet;
	}

	public int compareCase(Case case2){
		if ((this.getListeObjets()).size() < (case2.getListeObjets()).size()){
			return -1;
		}
		else if ((this.getListeObjets()).size() == (case2.getListeObjets()).size()){
			return 0;
		}
		else 
			return 1;
	}
	
	public Case compareSortie(){
		int fileMin = 0;
		for (int i=0; i<(this.getSortie()).size(); i++){
			for (int j=i+1; j<(this.getSortie()).size(); j++){
				if ( !(this.getSortie().get(i)instanceof Puit) && !(this.getSortie().get(j)instanceof Puit) && (this.getSortie().get(i)).compareCase((this.getSortie()).get(j)) <= 0 && ((this.getSortie()).get(i)).compareCase((this.getSortie()).get(fileMin)) <= 0){
					fileMin =i;
				}
			}
		}
		return (this.getSortie()).get(fileMin);
	}

}
