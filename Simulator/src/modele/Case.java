package modele;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Case extends Acteur
{
	private LinkedList<Objet> listeObjets;
    private ArrayList<Case> sortie;
    private ArrayList<Case> echappatoire ;
    private ArrayList<Case> entree ;
    private int capacity;
    private boolean c_in = true ;
    private boolean c_out = true ;    
	
    /** Constructeur **/
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
    
    /************************************************************************************/
    /** Condition in/out **/
    public boolean getC_In()
    {
    	return this.c_in ;
    }
        
    public boolean getC_Out()
    {
    	return this.c_out ;
    }
    
    public void switchC_In()
    {
    	this.c_in = !c_in;
    }
    
    public void switchC_Out()
    {
    	this.c_out = !c_out;
    }
    
    /************************************************************************************/
    /** capacity **/
	public int getCapacity(){
		return this.capacity;
	}

	public void setCapacity(int capacity2){
		this.capacity = capacity2;
	}

	/************************************************************************************/
	/** listeObjet **/
    public LinkedList<Objet> getListeObjets()
    {
    	return this.listeObjets;
    }
    
    public Objet getFirstObjet()
	{
		Objet firstObjet = 
				this.listeObjets.peek();
		return firstObjet;
	}
    
    /************************************************************************************/
    /** entree **/
    
    public ArrayList<Case> getEntree() 
    {
    	return this.sortie;
    }
    
    public void setEntree(ArrayList<Case> entree)
    {
		this.entree = entree;
	}
    
    /************************************************************************************/
    /** sortie **/
    public ArrayList<Case> getSortie() 
    {
    	return this.sortie;
    }
    
    public void setSortie(ArrayList<Case> sortie)
    {
		this.sortie = sortie;
	}
	
	public void relierSortie(Case caseSortie)
	{
		this.sortie.add(caseSortie);
		caseSortie.getEntree().add(this);
	}
	
	public Case compareSortie(){
		int fileMin = 0;
		for (int i=0; i<(this.getSortie()).size(); i++){
			for (int j=i+1; j<(this.getSortie()).size(); j++)
			{
				if ( !(this.getSortie().get(i)instanceof Puit) 
						&& !(this.getSortie().get(j)instanceof Puit) 
						&& (this.getSortie().get(i)).compareCase((this.getSortie()).get(j)) <= 0 
						&& ((this.getSortie()).get(i)).compareCase((this.getSortie()).get(fileMin)) <= 0)
				{
					fileMin =i;
				}
			}
		}
		return (this.getSortie()).get(fileMin);
	}
    
	/************************************************************************************/
    /** echappatoire **/
	public boolean hasTimeOut()
	{
		return true ;
	}
	
	public int getIndexTimeOutObjet()
	{
		for(int i=0 ; i <this.listeObjets.size() ; i++)
		{
			if(listeObjets.get(i).isTimedOut())
			{
				return i ;
			}
		}
		return -1;
	}
	
    public ArrayList<Case> getEchappatoire() 
    {
    	return this.echappatoire;
    }
    public void setEchappatoire(ArrayList<Case> echappatoire)
    {
		this.echappatoire=echappatoire;
	}
	
    public void relierEchappatoire(Case caseEchap)
    {
    	this.echappatoire.add(caseEchap);
    	caseEchap.getEntree().add(this);
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
	
	public Case compareEchappatoire()
	{
		int fileMin = 0;
		for (int i=0; i<(this.getEchappatoire()).size(); i++)
		{
			for (int j=i+1; j<(this.getSortie()).size(); j++)
			{
				if ( !(this.getEchappatoire().get(i)instanceof Puit) 
						&& !(this.getEchappatoire().get(j)instanceof Puit) 
						&& (this.getEchappatoire().get(i)).compareCase((this.getEchappatoire()).get(j)) <= 0 
						&& ((this.getEchappatoire()).get(i)).compareCase((this.getEchappatoire()).get(fileMin)) <= 0)
									
				{
					fileMin =i;
				}
			}
		}
		return (this.getSortie()).get(fileMin);
	}

}
