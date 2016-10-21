package Simulador;

import java.util.Random;

import java.lang.Math;

public class GeradorDeNumerosAleatorios {

	/****************** GERAÇÃO DE NÚMEROS ALEATORIOS ********************/
	public static Random rand = new Random(ParametrosDoSimulador.semente);
	public  static int poisson(double lambda) {
		double elambda = Math.exp(-1*lambda);
		double product = 1;
		int count =  0;
		 int result=0;
		while (product >= elambda) {
			product *= rand.nextDouble();
			result = count;
			count++; // keep result one behind
			}
		return result;
		}
	public static int getPoissonRandom(double mean) {
	    Random r = rand;
	    double L = Math.exp(-mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}
	public static double ranf()
	/* Esta funcao tem que ser trabalhada com cuidado pois ela nao deve voltar 
	   valor igual a 0 pois tem geradores que trabalham com log e isto 
		 gerara um valor incorreto. Para o UNIX isto precisa ser verificado. */
	{
		/* return(random()/2.147483647E9);										 Para o  UNIX */

		double num_alea; 
		num_alea = rand.nextFloat();
		if ( num_alea < 0.)
		{
			MensagensDoSimulador.setMensagem("\n\nFc ranf() - Erro grave no gerador do S.O.");
			System.exit(0);
		}
		while (num_alea == 0.)															/* Para evitar que ranf gere 0*/
		{
			num_alea = rand.nextFloat();																/* Para o Visual C */
		}
		return (float) num_alea;
	}
	/****************** DISTRIBUIICOES DE PROBABILIDADE UTILIZADAS ********************/

	/*--------------  EXPONENTIAL RANDOM VARIATE GENERATOR  --------------*/
	public static double expntl(double x)
	{ /* 'expntl' returns a psuedo-random variate from a negative     */
		/* exponential distribution with mean x.                        */
		return(-x*Math.log(ranf()));
	}

	/*------------  UNIFORM [a, b] RANDOM VARIATE GENERATOR  -------------*/
	public static double uniform(double a, double b)
	{ /* 'uniform' returns a psuedo-random variate from a uniform     */
		/* distribution with lower bound a and upper bound b.           */
		//if (a>b) then error(0,"uniform Argument Error: a > b");
		return(a+(b-a)*ranf());
	}
	public static int uniform(int a, int b)
	{ /* 'uniform' returns a psuedo-random variate from a uniform     */
		/* distribution with lower bound a and upper bound b.           */
		//if (a>b) then error(0,"uniform Argument Error: a > b");
		return( (int) (rand.nextInt(b - a+1)  + a));
	}


}
