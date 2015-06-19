package main;

import simulatorpack.Echeancier;

public class MainSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Echeancier echeancier = new Echeancier();
		
		while(!echeancier.isEmpty()){
			echeancier.nextEvent();
		}
	}

}
