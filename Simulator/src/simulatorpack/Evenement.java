package simulatorpack;

import java.sql.Date;
    
    public class Evenement 
    {
        
        private ActeurInterface acteur;
        private Date temps ;
        private String action ;
        
        public Evenement(ActeurInterface acteur, Date temps, String action)
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
        
        
        public String getAction()
        {
            return this.action;
        }
        
      
        
    }

