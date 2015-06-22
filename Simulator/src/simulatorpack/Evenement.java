package simulatorpack;

import java.sql.Date;

import modele.ActeurInterface;
import modele.Case;

    
    public abstract class Evenement 
    {
        
        private ActeurInterface acteur;
        private Date temps ;
        private int action ;
        private Case caseActuelle;
        
        public Evenement(ActeurInterface acteur,int action,Date temps, Case caseActuelle )
        {
        	this.acteur = acteur ;
        	this.action = action ;
        	this.temps = temps ;
        	this.caseActuelle = caseActuelle;
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
        public Case getcaseActuelle() 
        {
        	return this.caseActuelle;
        }
    }

