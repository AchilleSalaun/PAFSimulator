package simulatorpack;

import java.sql.Date;


    
    public class Evenement {
        
        private Objet acteur;
        private Date temps ;
        private String action ;
        
        
        
        public Objet getActeur(){
            return this.acteur;
        }
        
        
        public Date getDate(){
            return this.temps;
        }
        
        
        public String getAction(){
            return this.action;
        }
        
        
        
    }

