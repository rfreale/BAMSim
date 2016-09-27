package Simulador;

import java.util.List;
import java.util.Vector;



public class Estatisticas {
	/** Quando alfa vale 0.1, (1 - alfa/2) = 0.95. Valor de t-student-95 para n tendendo a infinito = 1.645.*/
	public static final double T_STUDENT_095 = 1.96;
	/** 
	 * M�todo utilizado para calcular intervalo de confian�a de n amostras.
	 * @param amostras analisadas
	 * @return percentual do intervalo de confian�a
	 */
	public static double calculaIntervaloDeConfianca(List<Double> amostras) {

		double n = amostras.size();
		
		double somatorioValores = 0.0;
		double somatorioQuadradoValores = 0.0;
		
		for (Double amostra : amostras) {
			somatorioValores += amostra;
			somatorioQuadradoValores += Math.pow(amostra, 2);
		}
		return calculaIC(somatorioQuadradoValores, somatorioValores, n);
	}

	
	/** 
	 * M�todo utilizado para calcular intervalo de confian�a de n amostras.
	 * @param somatorioQuadradoValores de uma amostra
	 * @param somatorioValores de uma amostra
	 * @param size tamanho de uma amostra
	 * @return percentual do intervalo de confian�a
	 */
	public static double calculaIC(double somatorioQuadradoValores, double somatorioValores, double size) {
		
		double variancia = 0.0;
		double n = size;
		
		//TODO: Agora: Verificar formula. Tem que levar em conta a m�dia. 
		//Refer�ncia da f�rmula: Site de AD - Ano:2000 - Per�odo:1� - Assuntos:Simula��o - 4a Quest�o
		variancia = ((somatorioQuadradoValores)/(n-1)) - (Math.pow(somatorioValores, 2)/(n*(n-1)));

		return T_STUDENT_095 * Math.sqrt(variancia / n);

	}

	/** 
	 * M�todo utilizado para calcular intervalo de confian�a de n amostras.
	 * @param somatorioQuadradoValores de uma amostra
	 * @param somatorioValores de uma amostra
	 * @param size tamanho de uma amostra
	 * @return percentual do intervalo de confian�a
	 */
	public static double calculaIC(List<Double> amostras) {
		
		double variancia = 0.0;
		double n = amostras.size();
		
		variancia=calculaVariancia(amostras);
		return T_STUDENT_095 * Math.sqrt(variancia / n);
		
	}
	
	public static double calculaDesvioPadrao(List<Double> amostras) {
		
	
		return Math.sqrt(calculaVariancia(amostras));
	
	}
	
	public static Double calculaVariancia(List<Double> amostras) {
		double media = calculaMedia(amostras);
		double soma = 0;
		double variancia = 0.0;
		double n = amostras.size();
		
		for (Double amostra : amostras) {
			soma += Math.pow((amostra-media),2);
		}
		variancia = soma / (n-1);
		return variancia;
		
	}
	
	/**
	 * Calcula a m�dia de n amostras.
	 * @param amostras analisadas
	 * @return m�dia amostral
	 */
	public static Double calculaMedia(List<Double> amostras) {
		int n = amostras.size();
		
		double somatorioValores = 0.0;
		
		for (Double amostra : amostras) {
			somatorioValores += amostra;
		}
		return calculaMedia(somatorioValores, n);
		
	}
	
	/**
	 * Calcula a m�dia de um somat�rio de valores.
	 * @param somatorioValores analisados
	 * @param size tamanho do conjunto analisado
	 * @return m�dia amostral
	 */
	public static double calculaMedia(double somatorioValores, int size) {
		return somatorioValores/size;
	}
	
}
