package simulatorpack;

import java.sql.Date;

import modele.ActeurInterface;
import modele.Objet;

    
    public  class Evenement 
    {
        
        private Objet acteur;
        private Date temps ;
        private int action ;
        
        public Evenement(Objet acteur, Date temps, int action)
        {
        	this.acteur = acteur ;
        	this.action = action ;
        	this.temps = temps ;
        }
        
        public ActeurInterface getActeur()
        {
            return this.acteur;
        }
        
        
        public Date getDate()
        {
            return this.temps;
        }
        
        
        public int getAction()
        {
            return this.action;
        }        
    }

