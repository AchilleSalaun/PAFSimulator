package simulatorpack;

import java.util.Date;

import modele.ActeurInterface;
import modele.Case;

    
    public  class Evenement 
    {
        
        private ActeurInterface acteur;
        private int action ;
        private Date temps ;
        private Case caseActuelle;
        
        public Evenement(ActeurInterface acteur,int action,Date temps, Case caseActuelle )
        {
        	this.acteur = acteur ;
        	this.action = action ;
        	this.temps = temps ;
        	this.caseActuelle = caseActuelle;
        }
        
        /********************************************************************************************************/
        /** getters & setters **/
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
        public void setDate(Date date) {
        	this.temps=date;
        }
        public void setAction(int action) {
        	this.action=action;
        }
        public void setActeur(ActeurInterface acteur) {
        	this.acteur=acteur;
        }
        
        /********************************************************************************************************/
    }

