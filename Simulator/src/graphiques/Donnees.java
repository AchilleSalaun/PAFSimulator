package graphiques;

import java.util.Date;

/**
 * Cette classe sert a construire un arrayList de donnees pour construire des courbes
 *
 */
public class Donnees {

	private Date date;
	private int nombreClients;
	
	public Donnees(Date date, int nombreClients){
		this.date = date;
		this.nombreClients = nombreClients;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date d){
		date = d;
	}
	
	public int getNombreClients(){
		return nombreClients;
	}
	
	public void setNombreClients(int number){
		nombreClients = number;
	}
	
}
