package simulatorpack;

import java.sql.Date;

import modele.Acteur;

    
    public abstract class Evenement 
    {
        
        private Acteur acteur;
        private Date temps ;
        private int action ;
        
        public Evenement(Acteur acteur, Date temps, int action)
        {
        	this.acteur = acteur ;
        	this.action = action ;
        	this.temps = temps ;
        }
        
        public Acteur getActeur()
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

