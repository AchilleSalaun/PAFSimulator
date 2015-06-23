package alea;

import java.util.ArrayList;
import java.util.Random;

import modele.Case;

public class Alea 
{
	private static Random alea = new Random();
	
	public static double exponentielle(double lambda){
		
		return -Math.log(1 - alea.nextDouble());
	}
	
	public static int getRandomIndex(ArrayList<Case> liste)
	{
		double random = alea.nextDouble();
		int sizeListe = liste.size();
		random = random*sizeListe ;
		return (int) random ;
	}
}
