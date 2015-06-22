package alea;

import java.util.Random;

public class Alea 
{
	private static Random alea = new Random();
	
	public static double exponentielle(double lambda){
		
		return -Math.log(1 - alea.nextDouble());
	}

}
