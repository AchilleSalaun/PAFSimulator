package simulatorpack;

import java.util.Date;

import modele.Acteur;
import modele.Case;
import modele.Objet;

    
    public  class Evenement 
    {
        
        private Acteur acteur;
        private int action ;
        private Date temps ;
        private Case caseActuelle;
        private Case caseCible ;
        
        public Evenement(Acteur acteur,int action,Date temps, Case caseActuelle, Case caseCible )
        {
        	this.acteur = acteur ;
        	this.action = action ;
        	this.temps = temps ;
        	this.caseActuelle = caseActuelle;
        	this.caseCible = caseCible ;
        }
        
        /********************************************************************************************************/
        /** getters & setters **/
        public Acteur getActeur()
        {
            return this.acteur;
        }
        
        public int getAction()
        {
            return this.action;
        } 
        
        public Date getDate()
        {
            return this.temps;
        } 
        
        public Case getcaseActuelle() 
        {
        	return this.caseActuelle;
        }
        
        public Case getCaseCible()
        {
        	return this.caseCible;
        }
        
        public void setDate(Date date) 
        {
        	this.temps=date;
        }
        
        public void setAction(int action) 
        {
        	this.action=action;
        }
        
        public void setActeur(Acteur acteur) 
        {
        	this.acteur=acteur;
        }
        
        /********************************************************************************************************/
    }

