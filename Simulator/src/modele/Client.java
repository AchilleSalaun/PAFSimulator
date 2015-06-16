package modele;

public class Client extends Objet{

	private int tolerance; //nombre de clients au dela duquel le client ne veut pas rentrer dans la file
	
	public Client(double timeout, int tolerance) {
		
		super(timeout);
		this.tolerance = tolerance;
	}
	
	public int getTolerance(){
		return this.tolerance;
	}

}
