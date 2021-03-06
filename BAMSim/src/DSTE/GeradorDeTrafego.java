package DSTE;

import Simulador.*;
import java.io.IOException; 
import org.jrobin.core.RrdException; 

public class GeradorDeTrafego {

	private static int numeroDeLSPsPorMinuto[][];
	private static int numeroDeLSPsPorSlot[][];
	
	
	
	
	// Forcado
	public static void trafegoForcado2(RodadaDeSimulacao rodada, Topologia to, No dados) {
		
		final int b = 4;  // baixo
		final int m = 15;  //médio
		final int a = 24;  //alto
		final int ma = 36;  //muito alto
		
		final int CT0_h1  =	a	;
		final int CT1_h1  =	b	;
		final int CT2_h1  =	b	;
				
		final int CT0_h2  =	ma	;
		final int CT1_h2  =	ma	;
		final int CT2_h2  =	ma	;
		   		
		final int CT0_h3  =	a	;
		final int CT1_h3  =	b	;
		final int CT2_h3  =	m	;
		   		
		final int CT0_h4  =	ma	;
		final int CT1_h4  =	m	;
		final int CT2_h4  =	ma	;
		   		
		final int CT0_h5  =	m	;
		final int CT1_h5  =	b	;
		final int CT2_h5  =	a	;
		   		
		final int CT0_h6  =	a	;
		final int CT1_h6  =	ma	;
		final int CT2_h6  =	a	;
		   		
		final int CT0_h7  =	m	;
		final int CT1_h7  =	a	;
		final int CT2_h7  =	b	;
		   		
		final int CT0_h8  =	ma	;
		final int CT1_h8  =	ma	;
		final int CT2_h8  =	a	;
		   		
		final int CT0_h9  =	b	;
		final int CT1_h9  =	a	;
		final int CT2_h9  =	b	;
		   		
		final int CT0_h10  =	ma	;
		final int CT1_h10  =	ma	;
		final int CT2_h10  =	ma	;
		   		
		final int CT0_h11  =	b	;
		final int CT1_h11  =	ma	;
		final int CT2_h11  =	b	;
		   		
		final int CT0_h12  =	ma	;
		final int CT1_h12  =	a	;
		final int CT2_h12  =	ma	;
		   		
		final int CT0_h13  =	ma	;
		final int CT1_h13  =	b	;
		final int CT2_h13  =	b	;
		   		
		final int CT0_h14  =	m	;
		final int CT1_h14  =	ma	;
		final int CT2_h14  =	ma	;
		   		
		final int CT0_h15  =	b	;
		final int CT1_h15  =	a	;
		final int CT2_h15  =	m	;
		   		
		final int CT0_h16  =	ma	;
		final int CT1_h16  =	ma	;
		final int CT2_h16  =	ma	;
		   		
		final int CT0_h17  =	a	;
		final int CT1_h17  =	m	;
		final int CT2_h17  =	b	;
		   		
		final int CT0_h18  =	ma	;
		final int CT1_h18  =	a	;
		final int CT2_h18  =	a	;
		   		
		final int CT0_h19  =	b	;
		final int CT1_h19  =	m	;
		final int CT2_h19  =	a	;
		   		
		final int CT0_h20  =	a	;
		final int CT1_h20  =	ma	;
		final int CT2_h20  =	ma	;
		   		
		final int CT0_h21  =	b	;
		final int CT1_h21  =	b	;
		final int CT2_h21  =	a	;
		   		
		final int CT0_h22  =	a	;
		final int CT1_h22  =	a	;
		final int CT2_h22  =	ma	;
		   		
		final int CT0_h23  =	b	;
		final int CT1_h23  =	b	;
		final int CT2_h23  =	ma	;
		   		
		final int CT0_h24  =	ma	;
		final int CT1_h24  =	ma	;
		final int CT2_h24  =	m	;
		final int [][]matrizB =
			{ 		
				{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},
				{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},
				{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},
				{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},
				{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},
				{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},
				{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},{CT0_h7, CT1_h7, CT2_h7},
				{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},{CT0_h8, CT1_h8, CT2_h8},
				{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},{CT0_h9, CT1_h9, CT2_h9},
				{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},{CT0_h10, CT1_h10, CT2_h10},
				{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},{CT0_h11, CT1_h11, CT2_h11},
				{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},{CT0_h12, CT1_h12, CT2_h12},
				{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},{CT0_h13, CT1_h13, CT2_h13},
				{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},{CT0_h14, CT1_h14, CT2_h14},
				{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},{CT0_h15, CT1_h15, CT2_h15},
				{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},{CT0_h16, CT1_h16, CT2_h16},
				{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},{CT0_h17, CT1_h17, CT2_h17},
				{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},{CT0_h18, CT1_h18, CT2_h18},
				{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},{CT0_h19, CT1_h19, CT2_h19},
				{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},{CT0_h20, CT1_h20, CT2_h20},
				{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},{CT0_h21, CT1_h21, CT2_h21},
				{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},{CT0_h22, CT1_h22, CT2_h22},
				{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},{CT0_h23, CT1_h23, CT2_h23},
				{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},{CT0_h24, CT1_h24, CT2_h24},


				
				
				
				
				/*{CT0_h1+0, CT1_h1+3, CT2_h1+4},{CT0_h1+1, CT1_h1+2, CT2_h1+3},{CT0_h1+2, CT1_h1+1, CT2_h1+2},{CT0_h1+3, CT1_h1+4, CT2_h1+4},{CT0_h1+2, CT1_h1+3, CT2_h1+2},{CT0_h1+4, CT1_h1+2, CT2_h1+2},{CT0_h1+2, CT1_h1+1, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+0},{CT0_h1+3, CT1_h1+3, CT2_h1+0},{CT0_h1+2, CT1_h1+2, CT2_h1+0},{CT0_h1+4, CT1_h1+1, CT2_h1+1},{CT0_h1+2, CT1_h1+4, CT2_h1+2},{CT0_h1+2, CT1_h1+3, CT2_h1+3},{CT0_h1+2, CT1_h1+2, CT2_h1+5},{CT0_h1+1, CT1_h1+4, CT2_h1+0},{CT0_h1+4, CT1_h1+2, CT2_h1+4},{CT0_h1+2, CT1_h1+2, CT2_h1+5},{CT0_h1+3, CT1_h1+2, CT2_h1+2},{CT0_h1+2, CT1_h1+1, CT2_h1+4},{CT0_h1+1, CT1_h1+4, CT2_h1+2},{CT0_h1+4, CT1_h1+2, CT2_h1+3},{CT0_h1+2, CT1_h1+3, CT2_h1+2},{CT0_h1+3, CT1_h1+2, CT2_h1+4},{CT0_h1+2, CT1_h1+1, CT2_h1+0},{CT0_h1+1, CT1_h1+4, CT2_h1+4},{CT0_h1+4, CT1_h1+2, CT2_h1+3},{CT0_h1+3, CT1_h1+3, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+4},{CT0_h1+1, CT1_h1+5, CT2_h1+2},{CT0_h1+2, CT1_h1+0, CT2_h1+2},{CT0_h1+3, CT1_h1+4, CT2_h1+2},{CT0_h1+2, CT1_h1+5, CT2_h1+0},{CT0_h1+1, CT1_h1+2, CT2_h1+0},{CT0_h1+4, CT1_h1+4, CT2_h1+0},{CT0_h1+3, CT1_h1+2, CT2_h1+1},{CT0_h1+2, CT1_h1+2, CT2_h1+2},{CT0_h1+1, CT1_h1+2, CT2_h1+3},{CT0_h1+4, CT1_h1+1, CT2_h1+5},{CT0_h1+3, CT1_h1+4, CT2_h1+0},{CT0_h1+2, CT1_h1+2, CT2_h1+4},{CT0_h1+1, CT1_h1+3, CT2_h1+3},{CT0_h1+2, CT1_h1+2, CT2_h1+2},{CT0_h1+3, CT1_h1+1, CT2_h1+4},{CT0_h1+2, CT1_h1+4, CT2_h1+2},{CT0_h1+4, CT1_h1+2, CT2_h1+2},{CT0_h1+4, CT1_h1+3, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+0},{CT0_h1+2, CT1_h1+5, CT2_h1+0},{CT0_h1+2, CT1_h1+4, CT2_h1+0},{CT0_h1+1, CT1_h1+5, CT2_h1+1},{CT0_h1+1, CT1_h1+1, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+3},{CT0_h1+4, CT1_h1+1, CT2_h1+2},{CT0_h1+3, CT1_h1+2, CT2_h1+1},{CT0_h1+2, CT1_h1+4, CT2_h1+4},{CT0_h1+2, CT1_h1+3, CT2_h1+3},{CT0_h1+3, CT1_h1+2, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+1},{CT0_h1+1, CT1_h1+3, CT2_h1+2},{CT0_h1+0, CT1_h1+0, CT2_h1+0},
				{CT0_h2+0, CT1_h2+3, CT2_h2+4},{CT0_h2+1, CT1_h2+2, CT2_h2+3},{CT0_h2+2, CT1_h2+1, CT2_h2+2},{CT0_h2+3, CT1_h2+4, CT2_h2+4},{CT0_h2+2, CT1_h2+3, CT2_h2+2},{CT0_h2+4, CT1_h2+2, CT2_h2+2},{CT0_h2+2, CT1_h2+1, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+0},{CT0_h2+3, CT1_h2+3, CT2_h2+0},{CT0_h2+2, CT1_h2+2, CT2_h2+0},{CT0_h2+4, CT1_h2+1, CT2_h2+1},{CT0_h2+2, CT1_h2+4, CT2_h2+2},{CT0_h2+2, CT1_h2+3, CT2_h2+3},{CT0_h2+2, CT1_h2+2, CT2_h2+5},{CT0_h2+1, CT1_h2+4, CT2_h2+0},{CT0_h2+4, CT1_h2+2, CT2_h2+4},{CT0_h2+2, CT1_h2+2, CT2_h2+5},{CT0_h2+3, CT1_h2+2, CT2_h2+2},{CT0_h2+2, CT1_h2+1, CT2_h2+4},{CT0_h2+1, CT1_h2+4, CT2_h2+2},{CT0_h2+4, CT1_h2+2, CT2_h2+3},{CT0_h2+2, CT1_h2+3, CT2_h2+2},{CT0_h2+3, CT1_h2+2, CT2_h2+4},{CT0_h2+2, CT1_h2+1, CT2_h2+0},{CT0_h2+1, CT1_h2+4, CT2_h2+4},{CT0_h2+4, CT1_h2+2, CT2_h2+3},{CT0_h2+3, CT1_h2+3, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+4},{CT0_h2+1, CT1_h2+5, CT2_h2+2},{CT0_h2+2, CT1_h2+0, CT2_h2+2},{CT0_h2+3, CT1_h2+4, CT2_h2+2},{CT0_h2+2, CT1_h2+5, CT2_h2+0},{CT0_h2+1, CT1_h2+2, CT2_h2+0},{CT0_h2+4, CT1_h2+4, CT2_h2+0},{CT0_h2+3, CT1_h2+2, CT2_h2+1},{CT0_h2+2, CT1_h2+2, CT2_h2+2},{CT0_h2+1, CT1_h2+2, CT2_h2+3},{CT0_h2+4, CT1_h2+1, CT2_h2+5},{CT0_h2+3, CT1_h2+4, CT2_h2+0},{CT0_h2+2, CT1_h2+2, CT2_h2+4},{CT0_h2+1, CT1_h2+3, CT2_h2+3},{CT0_h2+2, CT1_h2+2, CT2_h2+2},{CT0_h2+3, CT1_h2+1, CT2_h2+4},{CT0_h2+2, CT1_h2+4, CT2_h2+2},{CT0_h2+4, CT1_h2+2, CT2_h2+2},{CT0_h2+4, CT1_h2+3, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+0},{CT0_h2+2, CT1_h2+5, CT2_h2+0},{CT0_h2+2, CT1_h2+4, CT2_h2+0},{CT0_h2+1, CT1_h2+5, CT2_h2+1},{CT0_h2+1, CT1_h2+1, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+3},{CT0_h2+4, CT1_h2+1, CT2_h2+2},{CT0_h2+3, CT1_h2+2, CT2_h2+1},{CT0_h2+2, CT1_h2+4, CT2_h2+4},{CT0_h2+2, CT1_h2+3, CT2_h2+3},{CT0_h2+3, CT1_h2+2, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+1},{CT0_h2+1, CT1_h2+3, CT2_h2+2},{CT0_h2+0, CT1_h2+0, CT2_h2+0},
				{CT0_h3+0, CT1_h3+3, CT2_h3+4},{CT0_h3+1, CT1_h3+2, CT2_h3+3},{CT0_h3+2, CT1_h3+1, CT2_h3+2},{CT0_h3+3, CT1_h3+4, CT2_h3+4},{CT0_h3+2, CT1_h3+3, CT2_h3+2},{CT0_h3+4, CT1_h3+2, CT2_h3+2},{CT0_h3+2, CT1_h3+1, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+0},{CT0_h3+3, CT1_h3+3, CT2_h3+0},{CT0_h3+2, CT1_h3+2, CT2_h3+0},{CT0_h3+4, CT1_h3+1, CT2_h3+1},{CT0_h3+2, CT1_h3+4, CT2_h3+2},{CT0_h3+2, CT1_h3+3, CT2_h3+3},{CT0_h3+2, CT1_h3+2, CT2_h3+5},{CT0_h3+1, CT1_h3+4, CT2_h3+0},{CT0_h3+4, CT1_h3+2, CT2_h3+4},{CT0_h3+2, CT1_h3+2, CT2_h3+5},{CT0_h3+3, CT1_h3+2, CT2_h3+2},{CT0_h3+2, CT1_h3+1, CT2_h3+4},{CT0_h3+1, CT1_h3+4, CT2_h3+2},{CT0_h3+4, CT1_h3+2, CT2_h3+3},{CT0_h3+2, CT1_h3+3, CT2_h3+2},{CT0_h3+3, CT1_h3+2, CT2_h3+4},{CT0_h3+2, CT1_h3+1, CT2_h3+0},{CT0_h3+1, CT1_h3+4, CT2_h3+4},{CT0_h3+4, CT1_h3+2, CT2_h3+3},{CT0_h3+3, CT1_h3+3, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+4},{CT0_h3+1, CT1_h3+5, CT2_h3+2},{CT0_h3+2, CT1_h3+0, CT2_h3+2},{CT0_h3+3, CT1_h3+4, CT2_h3+2},{CT0_h3+2, CT1_h3+5, CT2_h3+0},{CT0_h3+1, CT1_h3+2, CT2_h3+0},{CT0_h3+4, CT1_h3+4, CT2_h3+0},{CT0_h3+3, CT1_h3+2, CT2_h3+1},{CT0_h3+2, CT1_h3+2, CT2_h3+2},{CT0_h3+1, CT1_h3+2, CT2_h3+3},{CT0_h3+4, CT1_h3+1, CT2_h3+5},{CT0_h3+3, CT1_h3+4, CT2_h3+0},{CT0_h3+2, CT1_h3+2, CT2_h3+4},{CT0_h3+1, CT1_h3+3, CT2_h3+3},{CT0_h3+2, CT1_h3+2, CT2_h3+2},{CT0_h3+3, CT1_h3+1, CT2_h3+4},{CT0_h3+2, CT1_h3+4, CT2_h3+2},{CT0_h3+4, CT1_h3+2, CT2_h3+2},{CT0_h3+4, CT1_h3+3, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+0},{CT0_h3+2, CT1_h3+5, CT2_h3+0},{CT0_h3+2, CT1_h3+4, CT2_h3+0},{CT0_h3+1, CT1_h3+5, CT2_h3+1},{CT0_h3+1, CT1_h3+1, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+3},{CT0_h3+4, CT1_h3+1, CT2_h3+2},{CT0_h3+3, CT1_h3+2, CT2_h3+1},{CT0_h3+2, CT1_h3+4, CT2_h3+4},{CT0_h3+2, CT1_h3+3, CT2_h3+3},{CT0_h3+3, CT1_h3+2, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+1},{CT0_h3+1, CT1_h3+3, CT2_h3+2},{CT0_h3+0, CT1_h3+0, CT2_h3+0},
				{CT0_h4+0, CT1_h4+3, CT2_h4+4},{CT0_h4+1, CT1_h4+2, CT2_h4+3},{CT0_h4+2, CT1_h4+1, CT2_h4+2},{CT0_h4+3, CT1_h4+4, CT2_h4+4},{CT0_h4+2, CT1_h4+3, CT2_h4+2},{CT0_h4+4, CT1_h4+2, CT2_h4+2},{CT0_h4+2, CT1_h4+1, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+0},{CT0_h4+3, CT1_h4+3, CT2_h4+0},{CT0_h4+2, CT1_h4+2, CT2_h4+0},{CT0_h4+4, CT1_h4+1, CT2_h4+1},{CT0_h4+2, CT1_h4+4, CT2_h4+2},{CT0_h4+2, CT1_h4+3, CT2_h4+3},{CT0_h4+2, CT1_h4+2, CT2_h4+5},{CT0_h4+1, CT1_h4+4, CT2_h4+0},{CT0_h4+4, CT1_h4+2, CT2_h4+4},{CT0_h4+2, CT1_h4+2, CT2_h4+5},{CT0_h4+3, CT1_h4+2, CT2_h4+2},{CT0_h4+2, CT1_h4+1, CT2_h4+4},{CT0_h4+1, CT1_h4+4, CT2_h4+2},{CT0_h4+4, CT1_h4+2, CT2_h4+3},{CT0_h4+2, CT1_h4+3, CT2_h4+2},{CT0_h4+3, CT1_h4+2, CT2_h4+4},{CT0_h4+2, CT1_h4+1, CT2_h4+0},{CT0_h4+1, CT1_h4+4, CT2_h4+4},{CT0_h4+4, CT1_h4+2, CT2_h4+3},{CT0_h4+3, CT1_h4+3, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+4},{CT0_h4+1, CT1_h4+5, CT2_h4+2},{CT0_h4+2, CT1_h4+0, CT2_h4+2},{CT0_h4+3, CT1_h4+4, CT2_h4+2},{CT0_h4+2, CT1_h4+5, CT2_h4+0},{CT0_h4+1, CT1_h4+2, CT2_h4+0},{CT0_h4+4, CT1_h4+4, CT2_h4+0},{CT0_h4+3, CT1_h4+2, CT2_h4+1},{CT0_h4+2, CT1_h4+2, CT2_h4+2},{CT0_h4+1, CT1_h4+2, CT2_h4+3},{CT0_h4+4, CT1_h4+1, CT2_h4+5},{CT0_h4+3, CT1_h4+4, CT2_h4+0},{CT0_h4+2, CT1_h4+2, CT2_h4+4},{CT0_h4+1, CT1_h4+3, CT2_h4+3},{CT0_h4+2, CT1_h4+2, CT2_h4+2},{CT0_h4+3, CT1_h4+1, CT2_h4+4},{CT0_h4+2, CT1_h4+4, CT2_h4+2},{CT0_h4+4, CT1_h4+2, CT2_h4+2},{CT0_h4+4, CT1_h4+3, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+0},{CT0_h4+2, CT1_h4+5, CT2_h4+0},{CT0_h4+2, CT1_h4+4, CT2_h4+0},{CT0_h4+1, CT1_h4+5, CT2_h4+1},{CT0_h4+1, CT1_h4+1, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+3},{CT0_h4+4, CT1_h4+1, CT2_h4+2},{CT0_h4+3, CT1_h4+2, CT2_h4+1},{CT0_h4+2, CT1_h4+4, CT2_h4+4},{CT0_h4+2, CT1_h4+3, CT2_h4+3},{CT0_h4+3, CT1_h4+2, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+1},{CT0_h4+1, CT1_h4+3, CT2_h4+2},{CT0_h4+0, CT1_h4+0, CT2_h4+0},
				{CT0_h5+0, CT1_h5+3, CT2_h5+4},{CT0_h5+1, CT1_h5+2, CT2_h5+3},{CT0_h5+2, CT1_h5+1, CT2_h5+2},{CT0_h5+3, CT1_h5+4, CT2_h5+4},{CT0_h5+2, CT1_h5+3, CT2_h5+2},{CT0_h5+4, CT1_h5+2, CT2_h5+2},{CT0_h5+2, CT1_h5+1, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+0},{CT0_h5+3, CT1_h5+3, CT2_h5+0},{CT0_h5+2, CT1_h5+2, CT2_h5+0},{CT0_h5+4, CT1_h5+1, CT2_h5+1},{CT0_h5+2, CT1_h5+4, CT2_h5+2},{CT0_h5+2, CT1_h5+3, CT2_h5+3},{CT0_h5+2, CT1_h5+2, CT2_h5+5},{CT0_h5+1, CT1_h5+4, CT2_h5+0},{CT0_h5+4, CT1_h5+2, CT2_h5+4},{CT0_h5+2, CT1_h5+2, CT2_h5+5},{CT0_h5+3, CT1_h5+2, CT2_h5+2},{CT0_h5+2, CT1_h5+1, CT2_h5+4},{CT0_h5+1, CT1_h5+4, CT2_h5+2},{CT0_h5+4, CT1_h5+2, CT2_h5+3},{CT0_h5+2, CT1_h5+3, CT2_h5+2},{CT0_h5+3, CT1_h5+2, CT2_h5+4},{CT0_h5+2, CT1_h5+1, CT2_h5+0},{CT0_h5+1, CT1_h5+4, CT2_h5+4},{CT0_h5+4, CT1_h5+2, CT2_h5+3},{CT0_h5+3, CT1_h5+3, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+4},{CT0_h5+1, CT1_h5+5, CT2_h5+2},{CT0_h5+2, CT1_h5+0, CT2_h5+2},{CT0_h5+3, CT1_h5+4, CT2_h5+2},{CT0_h5+2, CT1_h5+5, CT2_h5+0},{CT0_h5+1, CT1_h5+2, CT2_h5+0},{CT0_h5+4, CT1_h5+4, CT2_h5+0},{CT0_h5+3, CT1_h5+2, CT2_h5+1},{CT0_h5+2, CT1_h5+2, CT2_h5+2},{CT0_h5+1, CT1_h5+2, CT2_h5+3},{CT0_h5+4, CT1_h5+1, CT2_h5+5},{CT0_h5+3, CT1_h5+4, CT2_h5+0},{CT0_h5+2, CT1_h5+2, CT2_h5+4},{CT0_h5+1, CT1_h5+3, CT2_h5+3},{CT0_h5+2, CT1_h5+2, CT2_h5+2},{CT0_h5+3, CT1_h5+1, CT2_h5+4},{CT0_h5+2, CT1_h5+4, CT2_h5+2},{CT0_h5+4, CT1_h5+2, CT2_h5+2},{CT0_h5+4, CT1_h5+3, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+0},{CT0_h5+2, CT1_h5+5, CT2_h5+0},{CT0_h5+2, CT1_h5+4, CT2_h5+0},{CT0_h5+1, CT1_h5+5, CT2_h5+1},{CT0_h5+1, CT1_h5+1, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+3},{CT0_h5+4, CT1_h5+1, CT2_h5+2},{CT0_h5+3, CT1_h5+2, CT2_h5+1},{CT0_h5+2, CT1_h5+4, CT2_h5+4},{CT0_h5+2, CT1_h5+3, CT2_h5+3},{CT0_h5+3, CT1_h5+2, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+1},{CT0_h5+1, CT1_h5+3, CT2_h5+2},{CT0_h5+0, CT1_h5+0, CT2_h5+0},
				{CT0_h6+0, CT1_h6+3, CT2_h6+4},{CT0_h6+1, CT1_h6+2, CT2_h6+3},{CT0_h6+2, CT1_h6+1, CT2_h6+2},{CT0_h6+3, CT1_h6+4, CT2_h6+4},{CT0_h6+2, CT1_h6+3, CT2_h6+2},{CT0_h6+4, CT1_h6+2, CT2_h6+2},{CT0_h6+2, CT1_h6+1, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+0},{CT0_h6+3, CT1_h6+3, CT2_h6+0},{CT0_h6+2, CT1_h6+2, CT2_h6+0},{CT0_h6+4, CT1_h6+1, CT2_h6+1},{CT0_h6+2, CT1_h6+4, CT2_h6+2},{CT0_h6+2, CT1_h6+3, CT2_h6+3},{CT0_h6+2, CT1_h6+2, CT2_h6+5},{CT0_h6+1, CT1_h6+4, CT2_h6+0},{CT0_h6+4, CT1_h6+2, CT2_h6+4},{CT0_h6+2, CT1_h6+2, CT2_h6+5},{CT0_h6+3, CT1_h6+2, CT2_h6+2},{CT0_h6+2, CT1_h6+1, CT2_h6+4},{CT0_h6+1, CT1_h6+4, CT2_h6+2},{CT0_h6+4, CT1_h6+2, CT2_h6+3},{CT0_h6+2, CT1_h6+3, CT2_h6+2},{CT0_h6+3, CT1_h6+2, CT2_h6+4},{CT0_h6+2, CT1_h6+1, CT2_h6+0},{CT0_h6+1, CT1_h6+4, CT2_h6+4},{CT0_h6+4, CT1_h6+2, CT2_h6+3},{CT0_h6+3, CT1_h6+3, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+4},{CT0_h6+1, CT1_h6+5, CT2_h6+2},{CT0_h6+2, CT1_h6+0, CT2_h6+2},{CT0_h6+3, CT1_h6+4, CT2_h6+2},{CT0_h6+2, CT1_h6+5, CT2_h6+0},{CT0_h6+1, CT1_h6+2, CT2_h6+0},{CT0_h6+4, CT1_h6+4, CT2_h6+0},{CT0_h6+3, CT1_h6+2, CT2_h6+1},{CT0_h6+2, CT1_h6+2, CT2_h6+2},{CT0_h6+1, CT1_h6+2, CT2_h6+3},{CT0_h6+4, CT1_h6+1, CT2_h6+5},{CT0_h6+3, CT1_h6+4, CT2_h6+0},{CT0_h6+2, CT1_h6+2, CT2_h6+4},{CT0_h6+1, CT1_h6+3, CT2_h6+3},{CT0_h6+2, CT1_h6+2, CT2_h6+2},{CT0_h6+3, CT1_h6+1, CT2_h6+4},{CT0_h6+2, CT1_h6+4, CT2_h6+2},{CT0_h6+4, CT1_h6+2, CT2_h6+2},{CT0_h6+4, CT1_h6+3, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+0},{CT0_h6+2, CT1_h6+5, CT2_h6+0},{CT0_h6+2, CT1_h6+4, CT2_h6+0},{CT0_h6+1, CT1_h6+5, CT2_h6+1},{CT0_h6+1, CT1_h6+1, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+3},{CT0_h6+4, CT1_h6+1, CT2_h6+2},{CT0_h6+3, CT1_h6+2, CT2_h6+1},{CT0_h6+2, CT1_h6+4, CT2_h6+4},{CT0_h6+2, CT1_h6+3, CT2_h6+3},{CT0_h6+3, CT1_h6+2, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+1},{CT0_h6+1, CT1_h6+3, CT2_h6+2},{CT0_h6+0, CT1_h6+0, CT2_h6+0},
				{CT0_h7+0, CT1_h7+3, CT2_h7+4},{CT0_h7+1, CT1_h7+2, CT2_h7+3},{CT0_h7+2, CT1_h7+1, CT2_h7+2},{CT0_h7+3, CT1_h7+4, CT2_h7+4},{CT0_h7+2, CT1_h7+3, CT2_h7+2},{CT0_h7+4, CT1_h7+2, CT2_h7+2},{CT0_h7+2, CT1_h7+1, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+0},{CT0_h7+3, CT1_h7+3, CT2_h7+0},{CT0_h7+2, CT1_h7+2, CT2_h7+0},{CT0_h7+4, CT1_h7+1, CT2_h7+1},{CT0_h7+2, CT1_h7+4, CT2_h7+2},{CT0_h7+2, CT1_h7+3, CT2_h7+3},{CT0_h7+2, CT1_h7+2, CT2_h7+5},{CT0_h7+1, CT1_h7+4, CT2_h7+0},{CT0_h7+4, CT1_h7+2, CT2_h7+4},{CT0_h7+2, CT1_h7+2, CT2_h7+5},{CT0_h7+3, CT1_h7+2, CT2_h7+2},{CT0_h7+2, CT1_h7+1, CT2_h7+4},{CT0_h7+1, CT1_h7+4, CT2_h7+2},{CT0_h7+4, CT1_h7+2, CT2_h7+3},{CT0_h7+2, CT1_h7+3, CT2_h7+2},{CT0_h7+3, CT1_h7+2, CT2_h7+4},{CT0_h7+2, CT1_h7+1, CT2_h7+0},{CT0_h7+1, CT1_h7+4, CT2_h7+4},{CT0_h7+4, CT1_h7+2, CT2_h7+3},{CT0_h7+3, CT1_h7+3, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+4},{CT0_h7+1, CT1_h7+5, CT2_h7+2},{CT0_h7+2, CT1_h7+0, CT2_h7+2},{CT0_h7+3, CT1_h7+4, CT2_h7+2},{CT0_h7+2, CT1_h7+5, CT2_h7+0},{CT0_h7+1, CT1_h7+2, CT2_h7+0},{CT0_h7+4, CT1_h7+4, CT2_h7+0},{CT0_h7+3, CT1_h7+2, CT2_h7+1},{CT0_h7+2, CT1_h7+2, CT2_h7+2},{CT0_h7+1, CT1_h7+2, CT2_h7+3},{CT0_h7+4, CT1_h7+1, CT2_h7+5},{CT0_h7+3, CT1_h7+4, CT2_h7+0},{CT0_h7+2, CT1_h7+2, CT2_h7+4},{CT0_h7+1, CT1_h7+3, CT2_h7+3},{CT0_h7+2, CT1_h7+2, CT2_h7+2},{CT0_h7+3, CT1_h7+1, CT2_h7+4},{CT0_h7+2, CT1_h7+4, CT2_h7+2},{CT0_h7+4, CT1_h7+2, CT2_h7+2},{CT0_h7+4, CT1_h7+3, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+0},{CT0_h7+2, CT1_h7+5, CT2_h7+0},{CT0_h7+2, CT1_h7+4, CT2_h7+0},{CT0_h7+1, CT1_h7+5, CT2_h7+1},{CT0_h7+1, CT1_h7+1, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+3},{CT0_h7+4, CT1_h7+1, CT2_h7+2},{CT0_h7+3, CT1_h7+2, CT2_h7+1},{CT0_h7+2, CT1_h7+4, CT2_h7+4},{CT0_h7+2, CT1_h7+3, CT2_h7+3},{CT0_h7+3, CT1_h7+2, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+1},{CT0_h7+1, CT1_h7+3, CT2_h7+2},{CT0_h7+0, CT1_h7+0, CT2_h7+0},
				{CT0_h8+0, CT1_h8+3, CT2_h8+4},{CT0_h8+1, CT1_h8+2, CT2_h8+3},{CT0_h8+2, CT1_h8+1, CT2_h8+2},{CT0_h8+3, CT1_h8+4, CT2_h8+4},{CT0_h8+2, CT1_h8+3, CT2_h8+2},{CT0_h8+4, CT1_h8+2, CT2_h8+2},{CT0_h8+2, CT1_h8+1, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+0},{CT0_h8+3, CT1_h8+3, CT2_h8+0},{CT0_h8+2, CT1_h8+2, CT2_h8+0},{CT0_h8+4, CT1_h8+1, CT2_h8+1},{CT0_h8+2, CT1_h8+4, CT2_h8+2},{CT0_h8+2, CT1_h8+3, CT2_h8+3},{CT0_h8+2, CT1_h8+2, CT2_h8+5},{CT0_h8+1, CT1_h8+4, CT2_h8+0},{CT0_h8+4, CT1_h8+2, CT2_h8+4},{CT0_h8+2, CT1_h8+2, CT2_h8+5},{CT0_h8+3, CT1_h8+2, CT2_h8+2},{CT0_h8+2, CT1_h8+1, CT2_h8+4},{CT0_h8+1, CT1_h8+4, CT2_h8+2},{CT0_h8+4, CT1_h8+2, CT2_h8+3},{CT0_h8+2, CT1_h8+3, CT2_h8+2},{CT0_h8+3, CT1_h8+2, CT2_h8+4},{CT0_h8+2, CT1_h8+1, CT2_h8+0},{CT0_h8+1, CT1_h8+4, CT2_h8+4},{CT0_h8+4, CT1_h8+2, CT2_h8+3},{CT0_h8+3, CT1_h8+3, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+4},{CT0_h8+1, CT1_h8+5, CT2_h8+2},{CT0_h8+2, CT1_h8+0, CT2_h8+2},{CT0_h8+3, CT1_h8+4, CT2_h8+2},{CT0_h8+2, CT1_h8+5, CT2_h8+0},{CT0_h8+1, CT1_h8+2, CT2_h8+0},{CT0_h8+4, CT1_h8+4, CT2_h8+0},{CT0_h8+3, CT1_h8+2, CT2_h8+1},{CT0_h8+2, CT1_h8+2, CT2_h8+2},{CT0_h8+1, CT1_h8+2, CT2_h8+3},{CT0_h8+4, CT1_h8+1, CT2_h8+5},{CT0_h8+3, CT1_h8+4, CT2_h8+0},{CT0_h8+2, CT1_h8+2, CT2_h8+4},{CT0_h8+1, CT1_h8+3, CT2_h8+3},{CT0_h8+2, CT1_h8+2, CT2_h8+2},{CT0_h8+3, CT1_h8+1, CT2_h8+4},{CT0_h8+2, CT1_h8+4, CT2_h8+2},{CT0_h8+4, CT1_h8+2, CT2_h8+2},{CT0_h8+4, CT1_h8+3, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+0},{CT0_h8+2, CT1_h8+5, CT2_h8+0},{CT0_h8+2, CT1_h8+4, CT2_h8+0},{CT0_h8+1, CT1_h8+5, CT2_h8+1},{CT0_h8+1, CT1_h8+1, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+3},{CT0_h8+4, CT1_h8+1, CT2_h8+2},{CT0_h8+3, CT1_h8+2, CT2_h8+1},{CT0_h8+2, CT1_h8+4, CT2_h8+4},{CT0_h8+2, CT1_h8+3, CT2_h8+3},{CT0_h8+3, CT1_h8+2, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+1},{CT0_h8+1, CT1_h8+3, CT2_h8+2},{CT0_h8+0, CT1_h8+0, CT2_h8+0},
				{CT0_h9+0, CT1_h9+3, CT2_h9+4},{CT0_h9+1, CT1_h9+2, CT2_h9+3},{CT0_h9+2, CT1_h9+1, CT2_h9+2},{CT0_h9+3, CT1_h9+4, CT2_h9+4},{CT0_h9+2, CT1_h9+3, CT2_h9+2},{CT0_h9+4, CT1_h9+2, CT2_h9+2},{CT0_h9+2, CT1_h9+1, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+0},{CT0_h9+3, CT1_h9+3, CT2_h9+0},{CT0_h9+2, CT1_h9+2, CT2_h9+0},{CT0_h9+4, CT1_h9+1, CT2_h9+1},{CT0_h9+2, CT1_h9+4, CT2_h9+2},{CT0_h9+2, CT1_h9+3, CT2_h9+3},{CT0_h9+2, CT1_h9+2, CT2_h9+5},{CT0_h9+1, CT1_h9+4, CT2_h9+0},{CT0_h9+4, CT1_h9+2, CT2_h9+4},{CT0_h9+2, CT1_h9+2, CT2_h9+5},{CT0_h9+3, CT1_h9+2, CT2_h9+2},{CT0_h9+2, CT1_h9+1, CT2_h9+4},{CT0_h9+1, CT1_h9+4, CT2_h9+2},{CT0_h9+4, CT1_h9+2, CT2_h9+3},{CT0_h9+2, CT1_h9+3, CT2_h9+2},{CT0_h9+3, CT1_h9+2, CT2_h9+4},{CT0_h9+2, CT1_h9+1, CT2_h9+0},{CT0_h9+1, CT1_h9+4, CT2_h9+4},{CT0_h9+4, CT1_h9+2, CT2_h9+3},{CT0_h9+3, CT1_h9+3, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+4},{CT0_h9+1, CT1_h9+5, CT2_h9+2},{CT0_h9+2, CT1_h9+0, CT2_h9+2},{CT0_h9+3, CT1_h9+4, CT2_h9+2},{CT0_h9+2, CT1_h9+5, CT2_h9+0},{CT0_h9+1, CT1_h9+2, CT2_h9+0},{CT0_h9+4, CT1_h9+4, CT2_h9+0},{CT0_h9+3, CT1_h9+2, CT2_h9+1},{CT0_h9+2, CT1_h9+2, CT2_h9+2},{CT0_h9+1, CT1_h9+2, CT2_h9+3},{CT0_h9+4, CT1_h9+1, CT2_h9+5},{CT0_h9+3, CT1_h9+4, CT2_h9+0},{CT0_h9+2, CT1_h9+2, CT2_h9+4},{CT0_h9+1, CT1_h9+3, CT2_h9+3},{CT0_h9+2, CT1_h9+2, CT2_h9+2},{CT0_h9+3, CT1_h9+1, CT2_h9+4},{CT0_h9+2, CT1_h9+4, CT2_h9+2},{CT0_h9+4, CT1_h9+2, CT2_h9+2},{CT0_h9+4, CT1_h9+3, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+0},{CT0_h9+2, CT1_h9+5, CT2_h9+0},{CT0_h9+2, CT1_h9+4, CT2_h9+0},{CT0_h9+1, CT1_h9+5, CT2_h9+1},{CT0_h9+1, CT1_h9+1, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+3},{CT0_h9+4, CT1_h9+1, CT2_h9+2},{CT0_h9+3, CT1_h9+2, CT2_h9+1},{CT0_h9+2, CT1_h9+4, CT2_h9+4},{CT0_h9+2, CT1_h9+3, CT2_h9+3},{CT0_h9+3, CT1_h9+2, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+1},{CT0_h9+1, CT1_h9+3, CT2_h9+2},{CT0_h9+0, CT1_h9+0, CT2_h9+0},
				{CT0_h10+0, CT1_h10+3, CT2_h10+4},{CT0_h10+1, CT1_h10+2, CT2_h10+3},{CT0_h10+2, CT1_h10+1, CT2_h10+2},{CT0_h10+3, CT1_h10+4, CT2_h10+4},{CT0_h10+2, CT1_h10+3, CT2_h10+2},{CT0_h10+4, CT1_h10+2, CT2_h10+2},{CT0_h10+2, CT1_h10+1, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+0},{CT0_h10+3, CT1_h10+3, CT2_h10+0},{CT0_h10+2, CT1_h10+2, CT2_h10+0},{CT0_h10+4, CT1_h10+1, CT2_h10+1},{CT0_h10+2, CT1_h10+4, CT2_h10+2},{CT0_h10+2, CT1_h10+3, CT2_h10+3},{CT0_h10+2, CT1_h10+2, CT2_h10+5},{CT0_h10+1, CT1_h10+4, CT2_h10+0},{CT0_h10+4, CT1_h10+2, CT2_h10+4},{CT0_h10+2, CT1_h10+2, CT2_h10+5},{CT0_h10+3, CT1_h10+2, CT2_h10+2},{CT0_h10+2, CT1_h10+1, CT2_h10+4},{CT0_h10+1, CT1_h10+4, CT2_h10+2},{CT0_h10+4, CT1_h10+2, CT2_h10+3},{CT0_h10+2, CT1_h10+3, CT2_h10+2},{CT0_h10+3, CT1_h10+2, CT2_h10+4},{CT0_h10+2, CT1_h10+1, CT2_h10+0},{CT0_h10+1, CT1_h10+4, CT2_h10+4},{CT0_h10+4, CT1_h10+2, CT2_h10+3},{CT0_h10+3, CT1_h10+3, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+4},{CT0_h10+1, CT1_h10+5, CT2_h10+2},{CT0_h10+2, CT1_h10+0, CT2_h10+2},{CT0_h10+3, CT1_h10+4, CT2_h10+2},{CT0_h10+2, CT1_h10+5, CT2_h10+0},{CT0_h10+1, CT1_h10+2, CT2_h10+0},{CT0_h10+4, CT1_h10+4, CT2_h10+0},{CT0_h10+3, CT1_h10+2, CT2_h10+1},{CT0_h10+2, CT1_h10+2, CT2_h10+2},{CT0_h10+1, CT1_h10+2, CT2_h10+3},{CT0_h10+4, CT1_h10+1, CT2_h10+5},{CT0_h10+3, CT1_h10+4, CT2_h10+0},{CT0_h10+2, CT1_h10+2, CT2_h10+4},{CT0_h10+1, CT1_h10+3, CT2_h10+3},{CT0_h10+2, CT1_h10+2, CT2_h10+2},{CT0_h10+3, CT1_h10+1, CT2_h10+4},{CT0_h10+2, CT1_h10+4, CT2_h10+2},{CT0_h10+4, CT1_h10+2, CT2_h10+2},{CT0_h10+4, CT1_h10+3, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+0},{CT0_h10+2, CT1_h10+5, CT2_h10+0},{CT0_h10+2, CT1_h10+4, CT2_h10+0},{CT0_h10+1, CT1_h10+5, CT2_h10+1},{CT0_h10+1, CT1_h10+1, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+3},{CT0_h10+4, CT1_h10+1, CT2_h10+2},{CT0_h10+3, CT1_h10+2, CT2_h10+1},{CT0_h10+2, CT1_h10+4, CT2_h10+4},{CT0_h10+2, CT1_h10+3, CT2_h10+3},{CT0_h10+3, CT1_h10+2, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+1},{CT0_h10+1, CT1_h10+3, CT2_h10+2},{CT0_h10+0, CT1_h10+0, CT2_h10+0},
				{CT0_h11+0, CT1_h11+3, CT2_h11+4},{CT0_h11+1, CT1_h11+2, CT2_h11+3},{CT0_h11+2, CT1_h11+1, CT2_h11+2},{CT0_h11+3, CT1_h11+4, CT2_h11+4},{CT0_h11+2, CT1_h11+3, CT2_h11+2},{CT0_h11+4, CT1_h11+2, CT2_h11+2},{CT0_h11+2, CT1_h11+1, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+0},{CT0_h11+3, CT1_h11+3, CT2_h11+0},{CT0_h11+2, CT1_h11+2, CT2_h11+0},{CT0_h11+4, CT1_h11+1, CT2_h11+1},{CT0_h11+2, CT1_h11+4, CT2_h11+2},{CT0_h11+2, CT1_h11+3, CT2_h11+3},{CT0_h11+2, CT1_h11+2, CT2_h11+5},{CT0_h11+1, CT1_h11+4, CT2_h11+0},{CT0_h11+4, CT1_h11+2, CT2_h11+4},{CT0_h11+2, CT1_h11+2, CT2_h11+5},{CT0_h11+3, CT1_h11+2, CT2_h11+2},{CT0_h11+2, CT1_h11+1, CT2_h11+4},{CT0_h11+1, CT1_h11+4, CT2_h11+2},{CT0_h11+4, CT1_h11+2, CT2_h11+3},{CT0_h11+2, CT1_h11+3, CT2_h11+2},{CT0_h11+3, CT1_h11+2, CT2_h11+4},{CT0_h11+2, CT1_h11+1, CT2_h11+0},{CT0_h11+1, CT1_h11+4, CT2_h11+4},{CT0_h11+4, CT1_h11+2, CT2_h11+3},{CT0_h11+3, CT1_h11+3, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+4},{CT0_h11+1, CT1_h11+5, CT2_h11+2},{CT0_h11+2, CT1_h11+0, CT2_h11+2},{CT0_h11+3, CT1_h11+4, CT2_h11+2},{CT0_h11+2, CT1_h11+5, CT2_h11+0},{CT0_h11+1, CT1_h11+2, CT2_h11+0},{CT0_h11+4, CT1_h11+4, CT2_h11+0},{CT0_h11+3, CT1_h11+2, CT2_h11+1},{CT0_h11+2, CT1_h11+2, CT2_h11+2},{CT0_h11+1, CT1_h11+2, CT2_h11+3},{CT0_h11+4, CT1_h11+1, CT2_h11+5},{CT0_h11+3, CT1_h11+4, CT2_h11+0},{CT0_h11+2, CT1_h11+2, CT2_h11+4},{CT0_h11+1, CT1_h11+3, CT2_h11+3},{CT0_h11+2, CT1_h11+2, CT2_h11+2},{CT0_h11+3, CT1_h11+1, CT2_h11+4},{CT0_h11+2, CT1_h11+4, CT2_h11+2},{CT0_h11+4, CT1_h11+2, CT2_h11+2},{CT0_h11+4, CT1_h11+3, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+0},{CT0_h11+2, CT1_h11+5, CT2_h11+0},{CT0_h11+2, CT1_h11+4, CT2_h11+0},{CT0_h11+1, CT1_h11+5, CT2_h11+1},{CT0_h11+1, CT1_h11+1, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+3},{CT0_h11+4, CT1_h11+1, CT2_h11+2},{CT0_h11+3, CT1_h11+2, CT2_h11+1},{CT0_h11+2, CT1_h11+4, CT2_h11+4},{CT0_h11+2, CT1_h11+3, CT2_h11+3},{CT0_h11+3, CT1_h11+2, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+1},{CT0_h11+1, CT1_h11+3, CT2_h11+2},{CT0_h11+0, CT1_h11+0, CT2_h11+0},
				{CT0_h12+0, CT1_h12+3, CT2_h12+4},{CT0_h12+1, CT1_h12+2, CT2_h12+3},{CT0_h12+2, CT1_h12+1, CT2_h12+2},{CT0_h12+3, CT1_h12+4, CT2_h12+4},{CT0_h12+2, CT1_h12+3, CT2_h12+2},{CT0_h12+4, CT1_h12+2, CT2_h12+2},{CT0_h12+2, CT1_h12+1, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+0},{CT0_h12+3, CT1_h12+3, CT2_h12+0},{CT0_h12+2, CT1_h12+2, CT2_h12+0},{CT0_h12+4, CT1_h12+1, CT2_h12+1},{CT0_h12+2, CT1_h12+4, CT2_h12+2},{CT0_h12+2, CT1_h12+3, CT2_h12+3},{CT0_h12+2, CT1_h12+2, CT2_h12+5},{CT0_h12+1, CT1_h12+4, CT2_h12+0},{CT0_h12+4, CT1_h12+2, CT2_h12+4},{CT0_h12+2, CT1_h12+2, CT2_h12+5},{CT0_h12+3, CT1_h12+2, CT2_h12+2},{CT0_h12+2, CT1_h12+1, CT2_h12+4},{CT0_h12+1, CT1_h12+4, CT2_h12+2},{CT0_h12+4, CT1_h12+2, CT2_h12+3},{CT0_h12+2, CT1_h12+3, CT2_h12+2},{CT0_h12+3, CT1_h12+2, CT2_h12+4},{CT0_h12+2, CT1_h12+1, CT2_h12+0},{CT0_h12+1, CT1_h12+4, CT2_h12+4},{CT0_h12+4, CT1_h12+2, CT2_h12+3},{CT0_h12+3, CT1_h12+3, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+4},{CT0_h12+1, CT1_h12+5, CT2_h12+2},{CT0_h12+2, CT1_h12+0, CT2_h12+2},{CT0_h12+3, CT1_h12+4, CT2_h12+2},{CT0_h12+2, CT1_h12+5, CT2_h12+0},{CT0_h12+1, CT1_h12+2, CT2_h12+0},{CT0_h12+4, CT1_h12+4, CT2_h12+0},{CT0_h12+3, CT1_h12+2, CT2_h12+1},{CT0_h12+2, CT1_h12+2, CT2_h12+2},{CT0_h12+1, CT1_h12+2, CT2_h12+3},{CT0_h12+4, CT1_h12+1, CT2_h12+5},{CT0_h12+3, CT1_h12+4, CT2_h12+0},{CT0_h12+2, CT1_h12+2, CT2_h12+4},{CT0_h12+1, CT1_h12+3, CT2_h12+3},{CT0_h12+2, CT1_h12+2, CT2_h12+2},{CT0_h12+3, CT1_h12+1, CT2_h12+4},{CT0_h12+2, CT1_h12+4, CT2_h12+2},{CT0_h12+4, CT1_h12+2, CT2_h12+2},{CT0_h12+4, CT1_h12+3, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+0},{CT0_h12+2, CT1_h12+5, CT2_h12+0},{CT0_h12+2, CT1_h12+4, CT2_h12+0},{CT0_h12+1, CT1_h12+5, CT2_h12+1},{CT0_h12+1, CT1_h12+1, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+3},{CT0_h12+4, CT1_h12+1, CT2_h12+2},{CT0_h12+3, CT1_h12+2, CT2_h12+1},{CT0_h12+2, CT1_h12+4, CT2_h12+4},{CT0_h12+2, CT1_h12+3, CT2_h12+3},{CT0_h12+3, CT1_h12+2, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+1},{CT0_h12+1, CT1_h12+3, CT2_h12+2},{CT0_h12+0, CT1_h12+0, CT2_h12+0},
				{CT0_h13+0, CT1_h13+3, CT2_h13+4},{CT0_h13+1, CT1_h13+2, CT2_h13+3},{CT0_h13+2, CT1_h13+1, CT2_h13+2},{CT0_h13+3, CT1_h13+4, CT2_h13+4},{CT0_h13+2, CT1_h13+3, CT2_h13+2},{CT0_h13+4, CT1_h13+2, CT2_h13+2},{CT0_h13+2, CT1_h13+1, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+0},{CT0_h13+3, CT1_h13+3, CT2_h13+0},{CT0_h13+2, CT1_h13+2, CT2_h13+0},{CT0_h13+4, CT1_h13+1, CT2_h13+1},{CT0_h13+2, CT1_h13+4, CT2_h13+2},{CT0_h13+2, CT1_h13+3, CT2_h13+3},{CT0_h13+2, CT1_h13+2, CT2_h13+5},{CT0_h13+1, CT1_h13+4, CT2_h13+0},{CT0_h13+4, CT1_h13+2, CT2_h13+4},{CT0_h13+2, CT1_h13+2, CT2_h13+5},{CT0_h13+3, CT1_h13+2, CT2_h13+2},{CT0_h13+2, CT1_h13+1, CT2_h13+4},{CT0_h13+1, CT1_h13+4, CT2_h13+2},{CT0_h13+4, CT1_h13+2, CT2_h13+3},{CT0_h13+2, CT1_h13+3, CT2_h13+2},{CT0_h13+3, CT1_h13+2, CT2_h13+4},{CT0_h13+2, CT1_h13+1, CT2_h13+0},{CT0_h13+1, CT1_h13+4, CT2_h13+4},{CT0_h13+4, CT1_h13+2, CT2_h13+3},{CT0_h13+3, CT1_h13+3, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+4},{CT0_h13+1, CT1_h13+5, CT2_h13+2},{CT0_h13+2, CT1_h13+0, CT2_h13+2},{CT0_h13+3, CT1_h13+4, CT2_h13+2},{CT0_h13+2, CT1_h13+5, CT2_h13+0},{CT0_h13+1, CT1_h13+2, CT2_h13+0},{CT0_h13+4, CT1_h13+4, CT2_h13+0},{CT0_h13+3, CT1_h13+2, CT2_h13+1},{CT0_h13+2, CT1_h13+2, CT2_h13+2},{CT0_h13+1, CT1_h13+2, CT2_h13+3},{CT0_h13+4, CT1_h13+1, CT2_h13+5},{CT0_h13+3, CT1_h13+4, CT2_h13+0},{CT0_h13+2, CT1_h13+2, CT2_h13+4},{CT0_h13+1, CT1_h13+3, CT2_h13+3},{CT0_h13+2, CT1_h13+2, CT2_h13+2},{CT0_h13+3, CT1_h13+1, CT2_h13+4},{CT0_h13+2, CT1_h13+4, CT2_h13+2},{CT0_h13+4, CT1_h13+2, CT2_h13+2},{CT0_h13+4, CT1_h13+3, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+0},{CT0_h13+2, CT1_h13+5, CT2_h13+0},{CT0_h13+2, CT1_h13+4, CT2_h13+0},{CT0_h13+1, CT1_h13+5, CT2_h13+1},{CT0_h13+1, CT1_h13+1, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+3},{CT0_h13+4, CT1_h13+1, CT2_h13+2},{CT0_h13+3, CT1_h13+2, CT2_h13+1},{CT0_h13+2, CT1_h13+4, CT2_h13+4},{CT0_h13+2, CT1_h13+3, CT2_h13+3},{CT0_h13+3, CT1_h13+2, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+1},{CT0_h13+1, CT1_h13+3, CT2_h13+2},{CT0_h13+0, CT1_h13+0, CT2_h13+0},
				{CT0_h14+0, CT1_h14+3, CT2_h14+4},{CT0_h14+1, CT1_h14+2, CT2_h14+3},{CT0_h14+2, CT1_h14+1, CT2_h14+2},{CT0_h14+3, CT1_h14+4, CT2_h14+4},{CT0_h14+2, CT1_h14+3, CT2_h14+2},{CT0_h14+4, CT1_h14+2, CT2_h14+2},{CT0_h14+2, CT1_h14+1, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+0},{CT0_h14+3, CT1_h14+3, CT2_h14+0},{CT0_h14+2, CT1_h14+2, CT2_h14+0},{CT0_h14+4, CT1_h14+1, CT2_h14+1},{CT0_h14+2, CT1_h14+4, CT2_h14+2},{CT0_h14+2, CT1_h14+3, CT2_h14+3},{CT0_h14+2, CT1_h14+2, CT2_h14+5},{CT0_h14+1, CT1_h14+4, CT2_h14+0},{CT0_h14+4, CT1_h14+2, CT2_h14+4},{CT0_h14+2, CT1_h14+2, CT2_h14+5},{CT0_h14+3, CT1_h14+2, CT2_h14+2},{CT0_h14+2, CT1_h14+1, CT2_h14+4},{CT0_h14+1, CT1_h14+4, CT2_h14+2},{CT0_h14+4, CT1_h14+2, CT2_h14+3},{CT0_h14+2, CT1_h14+3, CT2_h14+2},{CT0_h14+3, CT1_h14+2, CT2_h14+4},{CT0_h14+2, CT1_h14+1, CT2_h14+0},{CT0_h14+1, CT1_h14+4, CT2_h14+4},{CT0_h14+4, CT1_h14+2, CT2_h14+3},{CT0_h14+3, CT1_h14+3, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+4},{CT0_h14+1, CT1_h14+5, CT2_h14+2},{CT0_h14+2, CT1_h14+0, CT2_h14+2},{CT0_h14+3, CT1_h14+4, CT2_h14+2},{CT0_h14+2, CT1_h14+5, CT2_h14+0},{CT0_h14+1, CT1_h14+2, CT2_h14+0},{CT0_h14+4, CT1_h14+4, CT2_h14+0},{CT0_h14+3, CT1_h14+2, CT2_h14+1},{CT0_h14+2, CT1_h14+2, CT2_h14+2},{CT0_h14+1, CT1_h14+2, CT2_h14+3},{CT0_h14+4, CT1_h14+1, CT2_h14+5},{CT0_h14+3, CT1_h14+4, CT2_h14+0},{CT0_h14+2, CT1_h14+2, CT2_h14+4},{CT0_h14+1, CT1_h14+3, CT2_h14+3},{CT0_h14+2, CT1_h14+2, CT2_h14+2},{CT0_h14+3, CT1_h14+1, CT2_h14+4},{CT0_h14+2, CT1_h14+4, CT2_h14+2},{CT0_h14+4, CT1_h14+2, CT2_h14+2},{CT0_h14+4, CT1_h14+3, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+0},{CT0_h14+2, CT1_h14+5, CT2_h14+0},{CT0_h14+2, CT1_h14+4, CT2_h14+0},{CT0_h14+1, CT1_h14+5, CT2_h14+1},{CT0_h14+1, CT1_h14+1, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+3},{CT0_h14+4, CT1_h14+1, CT2_h14+2},{CT0_h14+3, CT1_h14+2, CT2_h14+1},{CT0_h14+2, CT1_h14+4, CT2_h14+4},{CT0_h14+2, CT1_h14+3, CT2_h14+3},{CT0_h14+3, CT1_h14+2, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+1},{CT0_h14+1, CT1_h14+3, CT2_h14+2},{CT0_h14+0, CT1_h14+0, CT2_h14+0},
				{CT0_h15+0, CT1_h15+3, CT2_h15+4},{CT0_h15+1, CT1_h15+2, CT2_h15+3},{CT0_h15+2, CT1_h15+1, CT2_h15+2},{CT0_h15+3, CT1_h15+4, CT2_h15+4},{CT0_h15+2, CT1_h15+3, CT2_h15+2},{CT0_h15+4, CT1_h15+2, CT2_h15+2},{CT0_h15+2, CT1_h15+1, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+0},{CT0_h15+3, CT1_h15+3, CT2_h15+0},{CT0_h15+2, CT1_h15+2, CT2_h15+0},{CT0_h15+4, CT1_h15+1, CT2_h15+1},{CT0_h15+2, CT1_h15+4, CT2_h15+2},{CT0_h15+2, CT1_h15+3, CT2_h15+3},{CT0_h15+2, CT1_h15+2, CT2_h15+5},{CT0_h15+1, CT1_h15+4, CT2_h15+0},{CT0_h15+4, CT1_h15+2, CT2_h15+4},{CT0_h15+2, CT1_h15+2, CT2_h15+5},{CT0_h15+3, CT1_h15+2, CT2_h15+2},{CT0_h15+2, CT1_h15+1, CT2_h15+4},{CT0_h15+1, CT1_h15+4, CT2_h15+2},{CT0_h15+4, CT1_h15+2, CT2_h15+3},{CT0_h15+2, CT1_h15+3, CT2_h15+2},{CT0_h15+3, CT1_h15+2, CT2_h15+4},{CT0_h15+2, CT1_h15+1, CT2_h15+0},{CT0_h15+1, CT1_h15+4, CT2_h15+4},{CT0_h15+4, CT1_h15+2, CT2_h15+3},{CT0_h15+3, CT1_h15+3, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+4},{CT0_h15+1, CT1_h15+5, CT2_h15+2},{CT0_h15+2, CT1_h15+0, CT2_h15+2},{CT0_h15+3, CT1_h15+4, CT2_h15+2},{CT0_h15+2, CT1_h15+5, CT2_h15+0},{CT0_h15+1, CT1_h15+2, CT2_h15+0},{CT0_h15+4, CT1_h15+4, CT2_h15+0},{CT0_h15+3, CT1_h15+2, CT2_h15+1},{CT0_h15+2, CT1_h15+2, CT2_h15+2},{CT0_h15+1, CT1_h15+2, CT2_h15+3},{CT0_h15+4, CT1_h15+1, CT2_h15+5},{CT0_h15+3, CT1_h15+4, CT2_h15+0},{CT0_h15+2, CT1_h15+2, CT2_h15+4},{CT0_h15+1, CT1_h15+3, CT2_h15+3},{CT0_h15+2, CT1_h15+2, CT2_h15+2},{CT0_h15+3, CT1_h15+1, CT2_h15+4},{CT0_h15+2, CT1_h15+4, CT2_h15+2},{CT0_h15+4, CT1_h15+2, CT2_h15+2},{CT0_h15+4, CT1_h15+3, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+0},{CT0_h15+2, CT1_h15+5, CT2_h15+0},{CT0_h15+2, CT1_h15+4, CT2_h15+0},{CT0_h15+1, CT1_h15+5, CT2_h15+1},{CT0_h15+1, CT1_h15+1, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+3},{CT0_h15+4, CT1_h15+1, CT2_h15+2},{CT0_h15+3, CT1_h15+2, CT2_h15+1},{CT0_h15+2, CT1_h15+4, CT2_h15+4},{CT0_h15+2, CT1_h15+3, CT2_h15+3},{CT0_h15+3, CT1_h15+2, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+1},{CT0_h15+1, CT1_h15+3, CT2_h15+2},{CT0_h15+0, CT1_h15+0, CT2_h15+0},
				{CT0_h16+0, CT1_h16+3, CT2_h16+4},{CT0_h16+1, CT1_h16+2, CT2_h16+3},{CT0_h16+2, CT1_h16+1, CT2_h16+2},{CT0_h16+3, CT1_h16+4, CT2_h16+4},{CT0_h16+2, CT1_h16+3, CT2_h16+2},{CT0_h16+4, CT1_h16+2, CT2_h16+2},{CT0_h16+2, CT1_h16+1, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+0},{CT0_h16+3, CT1_h16+3, CT2_h16+0},{CT0_h16+2, CT1_h16+2, CT2_h16+0},{CT0_h16+4, CT1_h16+1, CT2_h16+1},{CT0_h16+2, CT1_h16+4, CT2_h16+2},{CT0_h16+2, CT1_h16+3, CT2_h16+3},{CT0_h16+2, CT1_h16+2, CT2_h16+5},{CT0_h16+1, CT1_h16+4, CT2_h16+0},{CT0_h16+4, CT1_h16+2, CT2_h16+4},{CT0_h16+2, CT1_h16+2, CT2_h16+5},{CT0_h16+3, CT1_h16+2, CT2_h16+2},{CT0_h16+2, CT1_h16+1, CT2_h16+4},{CT0_h16+1, CT1_h16+4, CT2_h16+2},{CT0_h16+4, CT1_h16+2, CT2_h16+3},{CT0_h16+2, CT1_h16+3, CT2_h16+2},{CT0_h16+3, CT1_h16+2, CT2_h16+4},{CT0_h16+2, CT1_h16+1, CT2_h16+0},{CT0_h16+1, CT1_h16+4, CT2_h16+4},{CT0_h16+4, CT1_h16+2, CT2_h16+3},{CT0_h16+3, CT1_h16+3, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+4},{CT0_h16+1, CT1_h16+5, CT2_h16+2},{CT0_h16+2, CT1_h16+0, CT2_h16+2},{CT0_h16+3, CT1_h16+4, CT2_h16+2},{CT0_h16+2, CT1_h16+5, CT2_h16+0},{CT0_h16+1, CT1_h16+2, CT2_h16+0},{CT0_h16+4, CT1_h16+4, CT2_h16+0},{CT0_h16+3, CT1_h16+2, CT2_h16+1},{CT0_h16+2, CT1_h16+2, CT2_h16+2},{CT0_h16+1, CT1_h16+2, CT2_h16+3},{CT0_h16+4, CT1_h16+1, CT2_h16+5},{CT0_h16+3, CT1_h16+4, CT2_h16+0},{CT0_h16+2, CT1_h16+2, CT2_h16+4},{CT0_h16+1, CT1_h16+3, CT2_h16+3},{CT0_h16+2, CT1_h16+2, CT2_h16+2},{CT0_h16+3, CT1_h16+1, CT2_h16+4},{CT0_h16+2, CT1_h16+4, CT2_h16+2},{CT0_h16+4, CT1_h16+2, CT2_h16+2},{CT0_h16+4, CT1_h16+3, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+0},{CT0_h16+2, CT1_h16+5, CT2_h16+0},{CT0_h16+2, CT1_h16+4, CT2_h16+0},{CT0_h16+1, CT1_h16+5, CT2_h16+1},{CT0_h16+1, CT1_h16+1, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+3},{CT0_h16+4, CT1_h16+1, CT2_h16+2},{CT0_h16+3, CT1_h16+2, CT2_h16+1},{CT0_h16+2, CT1_h16+4, CT2_h16+4},{CT0_h16+2, CT1_h16+3, CT2_h16+3},{CT0_h16+3, CT1_h16+2, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+1},{CT0_h16+1, CT1_h16+3, CT2_h16+2},{CT0_h16+0, CT1_h16+0, CT2_h16+0},
				{CT0_h17+0, CT1_h17+3, CT2_h17+4},{CT0_h17+1, CT1_h17+2, CT2_h17+3},{CT0_h17+2, CT1_h17+1, CT2_h17+2},{CT0_h17+3, CT1_h17+4, CT2_h17+4},{CT0_h17+2, CT1_h17+3, CT2_h17+2},{CT0_h17+4, CT1_h17+2, CT2_h17+2},{CT0_h17+2, CT1_h17+1, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+0},{CT0_h17+3, CT1_h17+3, CT2_h17+0},{CT0_h17+2, CT1_h17+2, CT2_h17+0},{CT0_h17+4, CT1_h17+1, CT2_h17+1},{CT0_h17+2, CT1_h17+4, CT2_h17+2},{CT0_h17+2, CT1_h17+3, CT2_h17+3},{CT0_h17+2, CT1_h17+2, CT2_h17+5},{CT0_h17+1, CT1_h17+4, CT2_h17+0},{CT0_h17+4, CT1_h17+2, CT2_h17+4},{CT0_h17+2, CT1_h17+2, CT2_h17+5},{CT0_h17+3, CT1_h17+2, CT2_h17+2},{CT0_h17+2, CT1_h17+1, CT2_h17+4},{CT0_h17+1, CT1_h17+4, CT2_h17+2},{CT0_h17+4, CT1_h17+2, CT2_h17+3},{CT0_h17+2, CT1_h17+3, CT2_h17+2},{CT0_h17+3, CT1_h17+2, CT2_h17+4},{CT0_h17+2, CT1_h17+1, CT2_h17+0},{CT0_h17+1, CT1_h17+4, CT2_h17+4},{CT0_h17+4, CT1_h17+2, CT2_h17+3},{CT0_h17+3, CT1_h17+3, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+4},{CT0_h17+1, CT1_h17+5, CT2_h17+2},{CT0_h17+2, CT1_h17+0, CT2_h17+2},{CT0_h17+3, CT1_h17+4, CT2_h17+2},{CT0_h17+2, CT1_h17+5, CT2_h17+0},{CT0_h17+1, CT1_h17+2, CT2_h17+0},{CT0_h17+4, CT1_h17+4, CT2_h17+0},{CT0_h17+3, CT1_h17+2, CT2_h17+1},{CT0_h17+2, CT1_h17+2, CT2_h17+2},{CT0_h17+1, CT1_h17+2, CT2_h17+3},{CT0_h17+4, CT1_h17+1, CT2_h17+5},{CT0_h17+3, CT1_h17+4, CT2_h17+0},{CT0_h17+2, CT1_h17+2, CT2_h17+4},{CT0_h17+1, CT1_h17+3, CT2_h17+3},{CT0_h17+2, CT1_h17+2, CT2_h17+2},{CT0_h17+3, CT1_h17+1, CT2_h17+4},{CT0_h17+2, CT1_h17+4, CT2_h17+2},{CT0_h17+4, CT1_h17+2, CT2_h17+2},{CT0_h17+4, CT1_h17+3, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+0},{CT0_h17+2, CT1_h17+5, CT2_h17+0},{CT0_h17+2, CT1_h17+4, CT2_h17+0},{CT0_h17+1, CT1_h17+5, CT2_h17+1},{CT0_h17+1, CT1_h17+1, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+3},{CT0_h17+4, CT1_h17+1, CT2_h17+2},{CT0_h17+3, CT1_h17+2, CT2_h17+1},{CT0_h17+2, CT1_h17+4, CT2_h17+4},{CT0_h17+2, CT1_h17+3, CT2_h17+3},{CT0_h17+3, CT1_h17+2, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+1},{CT0_h17+1, CT1_h17+3, CT2_h17+2},{CT0_h17+0, CT1_h17+0, CT2_h17+0},
				{CT0_h18+0, CT1_h18+3, CT2_h18+4},{CT0_h18+1, CT1_h18+2, CT2_h18+3},{CT0_h18+2, CT1_h18+1, CT2_h18+2},{CT0_h18+3, CT1_h18+4, CT2_h18+4},{CT0_h18+2, CT1_h18+3, CT2_h18+2},{CT0_h18+4, CT1_h18+2, CT2_h18+2},{CT0_h18+2, CT1_h18+1, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+0},{CT0_h18+3, CT1_h18+3, CT2_h18+0},{CT0_h18+2, CT1_h18+2, CT2_h18+0},{CT0_h18+4, CT1_h18+1, CT2_h18+1},{CT0_h18+2, CT1_h18+4, CT2_h18+2},{CT0_h18+2, CT1_h18+3, CT2_h18+3},{CT0_h18+2, CT1_h18+2, CT2_h18+5},{CT0_h18+1, CT1_h18+4, CT2_h18+0},{CT0_h18+4, CT1_h18+2, CT2_h18+4},{CT0_h18+2, CT1_h18+2, CT2_h18+5},{CT0_h18+3, CT1_h18+2, CT2_h18+2},{CT0_h18+2, CT1_h18+1, CT2_h18+4},{CT0_h18+1, CT1_h18+4, CT2_h18+2},{CT0_h18+4, CT1_h18+2, CT2_h18+3},{CT0_h18+2, CT1_h18+3, CT2_h18+2},{CT0_h18+3, CT1_h18+2, CT2_h18+4},{CT0_h18+2, CT1_h18+1, CT2_h18+0},{CT0_h18+1, CT1_h18+4, CT2_h18+4},{CT0_h18+4, CT1_h18+2, CT2_h18+3},{CT0_h18+3, CT1_h18+3, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+4},{CT0_h18+1, CT1_h18+5, CT2_h18+2},{CT0_h18+2, CT1_h18+0, CT2_h18+2},{CT0_h18+3, CT1_h18+4, CT2_h18+2},{CT0_h18+2, CT1_h18+5, CT2_h18+0},{CT0_h18+1, CT1_h18+2, CT2_h18+0},{CT0_h18+4, CT1_h18+4, CT2_h18+0},{CT0_h18+3, CT1_h18+2, CT2_h18+1},{CT0_h18+2, CT1_h18+2, CT2_h18+2},{CT0_h18+1, CT1_h18+2, CT2_h18+3},{CT0_h18+4, CT1_h18+1, CT2_h18+5},{CT0_h18+3, CT1_h18+4, CT2_h18+0},{CT0_h18+2, CT1_h18+2, CT2_h18+4},{CT0_h18+1, CT1_h18+3, CT2_h18+3},{CT0_h18+2, CT1_h18+2, CT2_h18+2},{CT0_h18+3, CT1_h18+1, CT2_h18+4},{CT0_h18+2, CT1_h18+4, CT2_h18+2},{CT0_h18+4, CT1_h18+2, CT2_h18+2},{CT0_h18+4, CT1_h18+3, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+0},{CT0_h18+2, CT1_h18+5, CT2_h18+0},{CT0_h18+2, CT1_h18+4, CT2_h18+0},{CT0_h18+1, CT1_h18+5, CT2_h18+1},{CT0_h18+1, CT1_h18+1, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+3},{CT0_h18+4, CT1_h18+1, CT2_h18+2},{CT0_h18+3, CT1_h18+2, CT2_h18+1},{CT0_h18+2, CT1_h18+4, CT2_h18+4},{CT0_h18+2, CT1_h18+3, CT2_h18+3},{CT0_h18+3, CT1_h18+2, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+1},{CT0_h18+1, CT1_h18+3, CT2_h18+2},{CT0_h18+0, CT1_h18+0, CT2_h18+0},
				{CT0_h19+0, CT1_h19+3, CT2_h19+4},{CT0_h19+1, CT1_h19+2, CT2_h19+3},{CT0_h19+2, CT1_h19+1, CT2_h19+2},{CT0_h19+3, CT1_h19+4, CT2_h19+4},{CT0_h19+2, CT1_h19+3, CT2_h19+2},{CT0_h19+4, CT1_h19+2, CT2_h19+2},{CT0_h19+2, CT1_h19+1, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+0},{CT0_h19+3, CT1_h19+3, CT2_h19+0},{CT0_h19+2, CT1_h19+2, CT2_h19+0},{CT0_h19+4, CT1_h19+1, CT2_h19+1},{CT0_h19+2, CT1_h19+4, CT2_h19+2},{CT0_h19+2, CT1_h19+3, CT2_h19+3},{CT0_h19+2, CT1_h19+2, CT2_h19+5},{CT0_h19+1, CT1_h19+4, CT2_h19+0},{CT0_h19+4, CT1_h19+2, CT2_h19+4},{CT0_h19+2, CT1_h19+2, CT2_h19+5},{CT0_h19+3, CT1_h19+2, CT2_h19+2},{CT0_h19+2, CT1_h19+1, CT2_h19+4},{CT0_h19+1, CT1_h19+4, CT2_h19+2},{CT0_h19+4, CT1_h19+2, CT2_h19+3},{CT0_h19+2, CT1_h19+3, CT2_h19+2},{CT0_h19+3, CT1_h19+2, CT2_h19+4},{CT0_h19+2, CT1_h19+1, CT2_h19+0},{CT0_h19+1, CT1_h19+4, CT2_h19+4},{CT0_h19+4, CT1_h19+2, CT2_h19+3},{CT0_h19+3, CT1_h19+3, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+4},{CT0_h19+1, CT1_h19+5, CT2_h19+2},{CT0_h19+2, CT1_h19+0, CT2_h19+2},{CT0_h19+3, CT1_h19+4, CT2_h19+2},{CT0_h19+2, CT1_h19+5, CT2_h19+0},{CT0_h19+1, CT1_h19+2, CT2_h19+0},{CT0_h19+4, CT1_h19+4, CT2_h19+0},{CT0_h19+3, CT1_h19+2, CT2_h19+1},{CT0_h19+2, CT1_h19+2, CT2_h19+2},{CT0_h19+1, CT1_h19+2, CT2_h19+3},{CT0_h19+4, CT1_h19+1, CT2_h19+5},{CT0_h19+3, CT1_h19+4, CT2_h19+0},{CT0_h19+2, CT1_h19+2, CT2_h19+4},{CT0_h19+1, CT1_h19+3, CT2_h19+3},{CT0_h19+2, CT1_h19+2, CT2_h19+2},{CT0_h19+3, CT1_h19+1, CT2_h19+4},{CT0_h19+2, CT1_h19+4, CT2_h19+2},{CT0_h19+4, CT1_h19+2, CT2_h19+2},{CT0_h19+4, CT1_h19+3, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+0},{CT0_h19+2, CT1_h19+5, CT2_h19+0},{CT0_h19+2, CT1_h19+4, CT2_h19+0},{CT0_h19+1, CT1_h19+5, CT2_h19+1},{CT0_h19+1, CT1_h19+1, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+3},{CT0_h19+4, CT1_h19+1, CT2_h19+2},{CT0_h19+3, CT1_h19+2, CT2_h19+1},{CT0_h19+2, CT1_h19+4, CT2_h19+4},{CT0_h19+2, CT1_h19+3, CT2_h19+3},{CT0_h19+3, CT1_h19+2, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+1},{CT0_h19+1, CT1_h19+3, CT2_h19+2},{CT0_h19+0, CT1_h19+0, CT2_h19+0},
				{CT0_h20+0, CT1_h20+3, CT2_h20+4},{CT0_h20+1, CT1_h20+2, CT2_h20+3},{CT0_h20+2, CT1_h20+1, CT2_h20+2},{CT0_h20+3, CT1_h20+4, CT2_h20+4},{CT0_h20+2, CT1_h20+3, CT2_h20+2},{CT0_h20+4, CT1_h20+2, CT2_h20+2},{CT0_h20+2, CT1_h20+1, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+0},{CT0_h20+3, CT1_h20+3, CT2_h20+0},{CT0_h20+2, CT1_h20+2, CT2_h20+0},{CT0_h20+4, CT1_h20+1, CT2_h20+1},{CT0_h20+2, CT1_h20+4, CT2_h20+2},{CT0_h20+2, CT1_h20+3, CT2_h20+3},{CT0_h20+2, CT1_h20+2, CT2_h20+5},{CT0_h20+1, CT1_h20+4, CT2_h20+0},{CT0_h20+4, CT1_h20+2, CT2_h20+4},{CT0_h20+2, CT1_h20+2, CT2_h20+5},{CT0_h20+3, CT1_h20+2, CT2_h20+2},{CT0_h20+2, CT1_h20+1, CT2_h20+4},{CT0_h20+1, CT1_h20+4, CT2_h20+2},{CT0_h20+4, CT1_h20+2, CT2_h20+3},{CT0_h20+2, CT1_h20+3, CT2_h20+2},{CT0_h20+3, CT1_h20+2, CT2_h20+4},{CT0_h20+2, CT1_h20+1, CT2_h20+0},{CT0_h20+1, CT1_h20+4, CT2_h20+4},{CT0_h20+4, CT1_h20+2, CT2_h20+3},{CT0_h20+3, CT1_h20+3, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+4},{CT0_h20+1, CT1_h20+5, CT2_h20+2},{CT0_h20+2, CT1_h20+0, CT2_h20+2},{CT0_h20+3, CT1_h20+4, CT2_h20+2},{CT0_h20+2, CT1_h20+5, CT2_h20+0},{CT0_h20+1, CT1_h20+2, CT2_h20+0},{CT0_h20+4, CT1_h20+4, CT2_h20+0},{CT0_h20+3, CT1_h20+2, CT2_h20+1},{CT0_h20+2, CT1_h20+2, CT2_h20+2},{CT0_h20+1, CT1_h20+2, CT2_h20+3},{CT0_h20+4, CT1_h20+1, CT2_h20+5},{CT0_h20+3, CT1_h20+4, CT2_h20+0},{CT0_h20+2, CT1_h20+2, CT2_h20+4},{CT0_h20+1, CT1_h20+3, CT2_h20+3},{CT0_h20+2, CT1_h20+2, CT2_h20+2},{CT0_h20+3, CT1_h20+1, CT2_h20+4},{CT0_h20+2, CT1_h20+4, CT2_h20+2},{CT0_h20+4, CT1_h20+2, CT2_h20+2},{CT0_h20+4, CT1_h20+3, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+0},{CT0_h20+2, CT1_h20+5, CT2_h20+0},{CT0_h20+2, CT1_h20+4, CT2_h20+0},{CT0_h20+1, CT1_h20+5, CT2_h20+1},{CT0_h20+1, CT1_h20+1, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+3},{CT0_h20+4, CT1_h20+1, CT2_h20+2},{CT0_h20+3, CT1_h20+2, CT2_h20+1},{CT0_h20+2, CT1_h20+4, CT2_h20+4},{CT0_h20+2, CT1_h20+3, CT2_h20+3},{CT0_h20+3, CT1_h20+2, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+1},{CT0_h20+1, CT1_h20+3, CT2_h20+2},{CT0_h20+0, CT1_h20+0, CT2_h20+0},
				{CT0_h21+0, CT1_h21+3, CT2_h21+4},{CT0_h21+1, CT1_h21+2, CT2_h21+3},{CT0_h21+2, CT1_h21+1, CT2_h21+2},{CT0_h21+3, CT1_h21+4, CT2_h21+4},{CT0_h21+2, CT1_h21+3, CT2_h21+2},{CT0_h21+4, CT1_h21+2, CT2_h21+2},{CT0_h21+2, CT1_h21+1, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+0},{CT0_h21+3, CT1_h21+3, CT2_h21+0},{CT0_h21+2, CT1_h21+2, CT2_h21+0},{CT0_h21+4, CT1_h21+1, CT2_h21+1},{CT0_h21+2, CT1_h21+4, CT2_h21+2},{CT0_h21+2, CT1_h21+3, CT2_h21+3},{CT0_h21+2, CT1_h21+2, CT2_h21+5},{CT0_h21+1, CT1_h21+4, CT2_h21+0},{CT0_h21+4, CT1_h21+2, CT2_h21+4},{CT0_h21+2, CT1_h21+2, CT2_h21+5},{CT0_h21+3, CT1_h21+2, CT2_h21+2},{CT0_h21+2, CT1_h21+1, CT2_h21+4},{CT0_h21+1, CT1_h21+4, CT2_h21+2},{CT0_h21+4, CT1_h21+2, CT2_h21+3},{CT0_h21+2, CT1_h21+3, CT2_h21+2},{CT0_h21+3, CT1_h21+2, CT2_h21+4},{CT0_h21+2, CT1_h21+1, CT2_h21+0},{CT0_h21+1, CT1_h21+4, CT2_h21+4},{CT0_h21+4, CT1_h21+2, CT2_h21+3},{CT0_h21+3, CT1_h21+3, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+4},{CT0_h21+1, CT1_h21+5, CT2_h21+2},{CT0_h21+2, CT1_h21+0, CT2_h21+2},{CT0_h21+3, CT1_h21+4, CT2_h21+2},{CT0_h21+2, CT1_h21+5, CT2_h21+0},{CT0_h21+1, CT1_h21+2, CT2_h21+0},{CT0_h21+4, CT1_h21+4, CT2_h21+0},{CT0_h21+3, CT1_h21+2, CT2_h21+1},{CT0_h21+2, CT1_h21+2, CT2_h21+2},{CT0_h21+1, CT1_h21+2, CT2_h21+3},{CT0_h21+4, CT1_h21+1, CT2_h21+5},{CT0_h21+3, CT1_h21+4, CT2_h21+0},{CT0_h21+2, CT1_h21+2, CT2_h21+4},{CT0_h21+1, CT1_h21+3, CT2_h21+3},{CT0_h21+2, CT1_h21+2, CT2_h21+2},{CT0_h21+3, CT1_h21+1, CT2_h21+4},{CT0_h21+2, CT1_h21+4, CT2_h21+2},{CT0_h21+4, CT1_h21+2, CT2_h21+2},{CT0_h21+4, CT1_h21+3, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+0},{CT0_h21+2, CT1_h21+5, CT2_h21+0},{CT0_h21+2, CT1_h21+4, CT2_h21+0},{CT0_h21+1, CT1_h21+5, CT2_h21+1},{CT0_h21+1, CT1_h21+1, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+3},{CT0_h21+4, CT1_h21+1, CT2_h21+2},{CT0_h21+3, CT1_h21+2, CT2_h21+1},{CT0_h21+2, CT1_h21+4, CT2_h21+4},{CT0_h21+2, CT1_h21+3, CT2_h21+3},{CT0_h21+3, CT1_h21+2, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+1},{CT0_h21+1, CT1_h21+3, CT2_h21+2},{CT0_h21+0, CT1_h21+0, CT2_h21+0},
				{CT0_h22+0, CT1_h22+3, CT2_h22+4},{CT0_h22+1, CT1_h22+2, CT2_h22+3},{CT0_h22+2, CT1_h22+1, CT2_h22+2},{CT0_h22+3, CT1_h22+4, CT2_h22+4},{CT0_h22+2, CT1_h22+3, CT2_h22+2},{CT0_h22+4, CT1_h22+2, CT2_h22+2},{CT0_h22+2, CT1_h22+1, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+0},{CT0_h22+3, CT1_h22+3, CT2_h22+0},{CT0_h22+2, CT1_h22+2, CT2_h22+0},{CT0_h22+4, CT1_h22+1, CT2_h22+1},{CT0_h22+2, CT1_h22+4, CT2_h22+2},{CT0_h22+2, CT1_h22+3, CT2_h22+3},{CT0_h22+2, CT1_h22+2, CT2_h22+5},{CT0_h22+1, CT1_h22+4, CT2_h22+0},{CT0_h22+4, CT1_h22+2, CT2_h22+4},{CT0_h22+2, CT1_h22+2, CT2_h22+5},{CT0_h22+3, CT1_h22+2, CT2_h22+2},{CT0_h22+2, CT1_h22+1, CT2_h22+4},{CT0_h22+1, CT1_h22+4, CT2_h22+2},{CT0_h22+4, CT1_h22+2, CT2_h22+3},{CT0_h22+2, CT1_h22+3, CT2_h22+2},{CT0_h22+3, CT1_h22+2, CT2_h22+4},{CT0_h22+2, CT1_h22+1, CT2_h22+0},{CT0_h22+1, CT1_h22+4, CT2_h22+4},{CT0_h22+4, CT1_h22+2, CT2_h22+3},{CT0_h22+3, CT1_h22+3, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+4},{CT0_h22+1, CT1_h22+5, CT2_h22+2},{CT0_h22+2, CT1_h22+0, CT2_h22+2},{CT0_h22+3, CT1_h22+4, CT2_h22+2},{CT0_h22+2, CT1_h22+5, CT2_h22+0},{CT0_h22+1, CT1_h22+2, CT2_h22+0},{CT0_h22+4, CT1_h22+4, CT2_h22+0},{CT0_h22+3, CT1_h22+2, CT2_h22+1},{CT0_h22+2, CT1_h22+2, CT2_h22+2},{CT0_h22+1, CT1_h22+2, CT2_h22+3},{CT0_h22+4, CT1_h22+1, CT2_h22+5},{CT0_h22+3, CT1_h22+4, CT2_h22+0},{CT0_h22+2, CT1_h22+2, CT2_h22+4},{CT0_h22+1, CT1_h22+3, CT2_h22+3},{CT0_h22+2, CT1_h22+2, CT2_h22+2},{CT0_h22+3, CT1_h22+1, CT2_h22+4},{CT0_h22+2, CT1_h22+4, CT2_h22+2},{CT0_h22+4, CT1_h22+2, CT2_h22+2},{CT0_h22+4, CT1_h22+3, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+0},{CT0_h22+2, CT1_h22+5, CT2_h22+0},{CT0_h22+2, CT1_h22+4, CT2_h22+0},{CT0_h22+1, CT1_h22+5, CT2_h22+1},{CT0_h22+1, CT1_h22+1, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+3},{CT0_h22+4, CT1_h22+1, CT2_h22+2},{CT0_h22+3, CT1_h22+2, CT2_h22+1},{CT0_h22+2, CT1_h22+4, CT2_h22+4},{CT0_h22+2, CT1_h22+3, CT2_h22+3},{CT0_h22+3, CT1_h22+2, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+1},{CT0_h22+1, CT1_h22+3, CT2_h22+2},{CT0_h22+0, CT1_h22+0, CT2_h22+0},
				{CT0_h23+0, CT1_h23+3, CT2_h23+4},{CT0_h23+1, CT1_h23+2, CT2_h23+3},{CT0_h23+2, CT1_h23+1, CT2_h23+2},{CT0_h23+3, CT1_h23+4, CT2_h23+4},{CT0_h23+2, CT1_h23+3, CT2_h23+2},{CT0_h23+4, CT1_h23+2, CT2_h23+2},{CT0_h23+2, CT1_h23+1, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+0},{CT0_h23+3, CT1_h23+3, CT2_h23+0},{CT0_h23+2, CT1_h23+2, CT2_h23+0},{CT0_h23+4, CT1_h23+1, CT2_h23+1},{CT0_h23+2, CT1_h23+4, CT2_h23+2},{CT0_h23+2, CT1_h23+3, CT2_h23+3},{CT0_h23+2, CT1_h23+2, CT2_h23+5},{CT0_h23+1, CT1_h23+4, CT2_h23+0},{CT0_h23+4, CT1_h23+2, CT2_h23+4},{CT0_h23+2, CT1_h23+2, CT2_h23+5},{CT0_h23+3, CT1_h23+2, CT2_h23+2},{CT0_h23+2, CT1_h23+1, CT2_h23+4},{CT0_h23+1, CT1_h23+4, CT2_h23+2},{CT0_h23+4, CT1_h23+2, CT2_h23+3},{CT0_h23+2, CT1_h23+3, CT2_h23+2},{CT0_h23+3, CT1_h23+2, CT2_h23+4},{CT0_h23+2, CT1_h23+1, CT2_h23+0},{CT0_h23+1, CT1_h23+4, CT2_h23+4},{CT0_h23+4, CT1_h23+2, CT2_h23+3},{CT0_h23+3, CT1_h23+3, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+4},{CT0_h23+1, CT1_h23+5, CT2_h23+2},{CT0_h23+2, CT1_h23+0, CT2_h23+2},{CT0_h23+3, CT1_h23+4, CT2_h23+2},{CT0_h23+2, CT1_h23+5, CT2_h23+0},{CT0_h23+1, CT1_h23+2, CT2_h23+0},{CT0_h23+4, CT1_h23+4, CT2_h23+0},{CT0_h23+3, CT1_h23+2, CT2_h23+1},{CT0_h23+2, CT1_h23+2, CT2_h23+2},{CT0_h23+1, CT1_h23+2, CT2_h23+3},{CT0_h23+4, CT1_h23+1, CT2_h23+5},{CT0_h23+3, CT1_h23+4, CT2_h23+0},{CT0_h23+2, CT1_h23+2, CT2_h23+4},{CT0_h23+1, CT1_h23+3, CT2_h23+3},{CT0_h23+2, CT1_h23+2, CT2_h23+2},{CT0_h23+3, CT1_h23+1, CT2_h23+4},{CT0_h23+2, CT1_h23+4, CT2_h23+2},{CT0_h23+4, CT1_h23+2, CT2_h23+2},{CT0_h23+4, CT1_h23+3, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+0},{CT0_h23+2, CT1_h23+5, CT2_h23+0},{CT0_h23+2, CT1_h23+4, CT2_h23+0},{CT0_h23+1, CT1_h23+5, CT2_h23+1},{CT0_h23+1, CT1_h23+1, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+3},{CT0_h23+4, CT1_h23+1, CT2_h23+2},{CT0_h23+3, CT1_h23+2, CT2_h23+1},{CT0_h23+2, CT1_h23+4, CT2_h23+4},{CT0_h23+2, CT1_h23+3, CT2_h23+3},{CT0_h23+3, CT1_h23+2, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+1},{CT0_h23+1, CT1_h23+3, CT2_h23+2},{CT0_h23+0, CT1_h23+0, CT2_h23+0},
				{CT0_h24+0, CT1_h24+3, CT2_h24+4},{CT0_h24+1, CT1_h24+2, CT2_h24+3},{CT0_h24+2, CT1_h24+1, CT2_h24+2},{CT0_h24+3, CT1_h24+4, CT2_h24+4},{CT0_h24+2, CT1_h24+3, CT2_h24+2},{CT0_h24+4, CT1_h24+2, CT2_h24+2},{CT0_h24+2, CT1_h24+1, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+0},{CT0_h24+3, CT1_h24+3, CT2_h24+0},{CT0_h24+2, CT1_h24+2, CT2_h24+0},{CT0_h24+4, CT1_h24+1, CT2_h24+1},{CT0_h24+2, CT1_h24+4, CT2_h24+2},{CT0_h24+2, CT1_h24+3, CT2_h24+3},{CT0_h24+2, CT1_h24+2, CT2_h24+5},{CT0_h24+1, CT1_h24+4, CT2_h24+0},{CT0_h24+4, CT1_h24+2, CT2_h24+4},{CT0_h24+2, CT1_h24+2, CT2_h24+5},{CT0_h24+3, CT1_h24+2, CT2_h24+2},{CT0_h24+2, CT1_h24+1, CT2_h24+4},{CT0_h24+1, CT1_h24+4, CT2_h24+2},{CT0_h24+4, CT1_h24+2, CT2_h24+3},{CT0_h24+2, CT1_h24+3, CT2_h24+2},{CT0_h24+3, CT1_h24+2, CT2_h24+4},{CT0_h24+2, CT1_h24+1, CT2_h24+0},{CT0_h24+1, CT1_h24+4, CT2_h24+4},{CT0_h24+4, CT1_h24+2, CT2_h24+3},{CT0_h24+3, CT1_h24+3, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+4},{CT0_h24+1, CT1_h24+5, CT2_h24+2},{CT0_h24+2, CT1_h24+0, CT2_h24+2},{CT0_h24+3, CT1_h24+4, CT2_h24+2},{CT0_h24+2, CT1_h24+5, CT2_h24+0},{CT0_h24+1, CT1_h24+2, CT2_h24+0},{CT0_h24+4, CT1_h24+4, CT2_h24+0},{CT0_h24+3, CT1_h24+2, CT2_h24+1},{CT0_h24+2, CT1_h24+2, CT2_h24+2},{CT0_h24+1, CT1_h24+2, CT2_h24+3},{CT0_h24+4, CT1_h24+1, CT2_h24+5},{CT0_h24+3, CT1_h24+4, CT2_h24+0},{CT0_h24+2, CT1_h24+2, CT2_h24+4},{CT0_h24+1, CT1_h24+3, CT2_h24+3},{CT0_h24+2, CT1_h24+2, CT2_h24+2},{CT0_h24+3, CT1_h24+1, CT2_h24+4},{CT0_h24+2, CT1_h24+4, CT2_h24+2},{CT0_h24+4, CT1_h24+2, CT2_h24+2},{CT0_h24+4, CT1_h24+3, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+0},{CT0_h24+2, CT1_h24+5, CT2_h24+0},{CT0_h24+2, CT1_h24+4, CT2_h24+0},{CT0_h24+1, CT1_h24+5, CT2_h24+1},{CT0_h24+1, CT1_h24+1, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+3},{CT0_h24+4, CT1_h24+1, CT2_h24+2},{CT0_h24+3, CT1_h24+2, CT2_h24+1},{CT0_h24+2, CT1_h24+4, CT2_h24+4},{CT0_h24+2, CT1_h24+3, CT2_h24+3},{CT0_h24+3, CT1_h24+2, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+1},{CT0_h24+1, CT1_h24+3, CT2_h24+2},{CT0_h24+0, CT1_h24+0, CT2_h24+0},
				{CT0_h25+0, CT1_h25+3, CT2_h25+4},{CT0_h25+1, CT1_h25+2, CT2_h25+3},{CT0_h25+2, CT1_h25+1, CT2_h25+2},{CT0_h25+3, CT1_h25+4, CT2_h25+4},{CT0_h25+2, CT1_h25+3, CT2_h25+2},{CT0_h25+4, CT1_h25+2, CT2_h25+2},{CT0_h25+2, CT1_h25+1, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+0},{CT0_h25+3, CT1_h25+3, CT2_h25+0},{CT0_h25+2, CT1_h25+2, CT2_h25+0},{CT0_h25+4, CT1_h25+1, CT2_h25+1},{CT0_h25+2, CT1_h25+4, CT2_h25+2},{CT0_h25+2, CT1_h25+3, CT2_h25+3},{CT0_h25+2, CT1_h25+2, CT2_h25+5},{CT0_h25+1, CT1_h25+4, CT2_h25+0},{CT0_h25+4, CT1_h25+2, CT2_h25+4},{CT0_h25+2, CT1_h25+2, CT2_h25+5},{CT0_h25+3, CT1_h25+2, CT2_h25+2},{CT0_h25+2, CT1_h25+1, CT2_h25+4},{CT0_h25+1, CT1_h25+4, CT2_h25+2},{CT0_h25+4, CT1_h25+2, CT2_h25+3},{CT0_h25+2, CT1_h25+3, CT2_h25+2},{CT0_h25+3, CT1_h25+2, CT2_h25+4},{CT0_h25+2, CT1_h25+1, CT2_h25+0},{CT0_h25+1, CT1_h25+4, CT2_h25+4},{CT0_h25+4, CT1_h25+2, CT2_h25+3},{CT0_h25+3, CT1_h25+3, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+4},{CT0_h25+1, CT1_h25+5, CT2_h25+2},{CT0_h25+2, CT1_h25+0, CT2_h25+2},{CT0_h25+3, CT1_h25+4, CT2_h25+2},{CT0_h25+2, CT1_h25+5, CT2_h25+0},{CT0_h25+1, CT1_h25+2, CT2_h25+0},{CT0_h25+4, CT1_h25+4, CT2_h25+0},{CT0_h25+3, CT1_h25+2, CT2_h25+1},{CT0_h25+2, CT1_h25+2, CT2_h25+2},{CT0_h25+1, CT1_h25+2, CT2_h25+3},{CT0_h25+4, CT1_h25+1, CT2_h25+5},{CT0_h25+3, CT1_h25+4, CT2_h25+0},{CT0_h25+2, CT1_h25+2, CT2_h25+4},{CT0_h25+1, CT1_h25+3, CT2_h25+3},{CT0_h25+2, CT1_h25+2, CT2_h25+2},{CT0_h25+3, CT1_h25+1, CT2_h25+4},{CT0_h25+2, CT1_h25+4, CT2_h25+2},{CT0_h25+4, CT1_h25+2, CT2_h25+2},{CT0_h25+4, CT1_h25+3, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+0},{CT0_h25+2, CT1_h25+5, CT2_h25+0},{CT0_h25+2, CT1_h25+4, CT2_h25+0},{CT0_h25+1, CT1_h25+5, CT2_h25+1},{CT0_h25+1, CT1_h25+1, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+3},{CT0_h25+4, CT1_h25+1, CT2_h25+2},{CT0_h25+3, CT1_h25+2, CT2_h25+1},{CT0_h25+2, CT1_h25+4, CT2_h25+4},{CT0_h25+2, CT1_h25+3, CT2_h25+3},{CT0_h25+3, CT1_h25+2, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+1},{CT0_h25+1, CT1_h25+3, CT2_h25+2},{CT0_h25+0, CT1_h25+0, CT2_h25+0},
				{CT0_h26+0, CT1_h26+3, CT2_h26+4},{CT0_h26+1, CT1_h26+2, CT2_h26+3},{CT0_h26+2, CT1_h26+1, CT2_h26+2},{CT0_h26+3, CT1_h26+4, CT2_h26+4},{CT0_h26+2, CT1_h26+3, CT2_h26+2},{CT0_h26+4, CT1_h26+2, CT2_h26+2},{CT0_h26+2, CT1_h26+1, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+0},{CT0_h26+3, CT1_h26+3, CT2_h26+0},{CT0_h26+2, CT1_h26+2, CT2_h26+0},{CT0_h26+4, CT1_h26+1, CT2_h26+1},{CT0_h26+2, CT1_h26+4, CT2_h26+2},{CT0_h26+2, CT1_h26+3, CT2_h26+3},{CT0_h26+2, CT1_h26+2, CT2_h26+5},{CT0_h26+1, CT1_h26+4, CT2_h26+0},{CT0_h26+4, CT1_h26+2, CT2_h26+4},{CT0_h26+2, CT1_h26+2, CT2_h26+5},{CT0_h26+3, CT1_h26+2, CT2_h26+2},{CT0_h26+2, CT1_h26+1, CT2_h26+4},{CT0_h26+1, CT1_h26+4, CT2_h26+2},{CT0_h26+4, CT1_h26+2, CT2_h26+3},{CT0_h26+2, CT1_h26+3, CT2_h26+2},{CT0_h26+3, CT1_h26+2, CT2_h26+4},{CT0_h26+2, CT1_h26+1, CT2_h26+0},{CT0_h26+1, CT1_h26+4, CT2_h26+4},{CT0_h26+4, CT1_h26+2, CT2_h26+3},{CT0_h26+3, CT1_h26+3, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+4},{CT0_h26+1, CT1_h26+5, CT2_h26+2},{CT0_h26+2, CT1_h26+0, CT2_h26+2},{CT0_h26+3, CT1_h26+4, CT2_h26+2},{CT0_h26+2, CT1_h26+5, CT2_h26+0},{CT0_h26+1, CT1_h26+2, CT2_h26+0},{CT0_h26+4, CT1_h26+4, CT2_h26+0},{CT0_h26+3, CT1_h26+2, CT2_h26+1},{CT0_h26+2, CT1_h26+2, CT2_h26+2},{CT0_h26+1, CT1_h26+2, CT2_h26+3},{CT0_h26+4, CT1_h26+1, CT2_h26+5},{CT0_h26+3, CT1_h26+4, CT2_h26+0},{CT0_h26+2, CT1_h26+2, CT2_h26+4},{CT0_h26+1, CT1_h26+3, CT2_h26+3},{CT0_h26+2, CT1_h26+2, CT2_h26+2},{CT0_h26+3, CT1_h26+1, CT2_h26+4},{CT0_h26+2, CT1_h26+4, CT2_h26+2},{CT0_h26+4, CT1_h26+2, CT2_h26+2},{CT0_h26+4, CT1_h26+3, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+0},{CT0_h26+2, CT1_h26+5, CT2_h26+0},{CT0_h26+2, CT1_h26+4, CT2_h26+0},{CT0_h26+1, CT1_h26+5, CT2_h26+1},{CT0_h26+1, CT1_h26+1, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+3},{CT0_h26+4, CT1_h26+1, CT2_h26+2},{CT0_h26+3, CT1_h26+2, CT2_h26+1},{CT0_h26+2, CT1_h26+4, CT2_h26+4},{CT0_h26+2, CT1_h26+3, CT2_h26+3},{CT0_h26+3, CT1_h26+2, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+1},{CT0_h26+1, CT1_h26+3, CT2_h26+2},{CT0_h26+0, CT1_h26+0, CT2_h26+0},
				{CT0_h27+0, CT1_h27+3, CT2_h27+4},{CT0_h27+1, CT1_h27+2, CT2_h27+3},{CT0_h27+2, CT1_h27+1, CT2_h27+2},{CT0_h27+3, CT1_h27+4, CT2_h27+4},{CT0_h27+2, CT1_h27+3, CT2_h27+2},{CT0_h27+4, CT1_h27+2, CT2_h27+2},{CT0_h27+2, CT1_h27+1, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+0},{CT0_h27+3, CT1_h27+3, CT2_h27+0},{CT0_h27+2, CT1_h27+2, CT2_h27+0},{CT0_h27+4, CT1_h27+1, CT2_h27+1},{CT0_h27+2, CT1_h27+4, CT2_h27+2},{CT0_h27+2, CT1_h27+3, CT2_h27+3},{CT0_h27+2, CT1_h27+2, CT2_h27+5},{CT0_h27+1, CT1_h27+4, CT2_h27+0},{CT0_h27+4, CT1_h27+2, CT2_h27+4},{CT0_h27+2, CT1_h27+2, CT2_h27+5},{CT0_h27+3, CT1_h27+2, CT2_h27+2},{CT0_h27+2, CT1_h27+1, CT2_h27+4},{CT0_h27+1, CT1_h27+4, CT2_h27+2},{CT0_h27+4, CT1_h27+2, CT2_h27+3},{CT0_h27+2, CT1_h27+3, CT2_h27+2},{CT0_h27+3, CT1_h27+2, CT2_h27+4},{CT0_h27+2, CT1_h27+1, CT2_h27+0},{CT0_h27+1, CT1_h27+4, CT2_h27+4},{CT0_h27+4, CT1_h27+2, CT2_h27+3},{CT0_h27+3, CT1_h27+3, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+4},{CT0_h27+1, CT1_h27+5, CT2_h27+2},{CT0_h27+2, CT1_h27+0, CT2_h27+2},{CT0_h27+3, CT1_h27+4, CT2_h27+2},{CT0_h27+2, CT1_h27+5, CT2_h27+0},{CT0_h27+1, CT1_h27+2, CT2_h27+0},{CT0_h27+4, CT1_h27+4, CT2_h27+0},{CT0_h27+3, CT1_h27+2, CT2_h27+1},{CT0_h27+2, CT1_h27+2, CT2_h27+2},{CT0_h27+1, CT1_h27+2, CT2_h27+3},{CT0_h27+4, CT1_h27+1, CT2_h27+5},{CT0_h27+3, CT1_h27+4, CT2_h27+0},{CT0_h27+2, CT1_h27+2, CT2_h27+4},{CT0_h27+1, CT1_h27+3, CT2_h27+3},{CT0_h27+2, CT1_h27+2, CT2_h27+2},{CT0_h27+3, CT1_h27+1, CT2_h27+4},{CT0_h27+2, CT1_h27+4, CT2_h27+2},{CT0_h27+4, CT1_h27+2, CT2_h27+2},{CT0_h27+4, CT1_h27+3, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+0},{CT0_h27+2, CT1_h27+5, CT2_h27+0},{CT0_h27+2, CT1_h27+4, CT2_h27+0},{CT0_h27+1, CT1_h27+5, CT2_h27+1},{CT0_h27+1, CT1_h27+1, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+3},{CT0_h27+4, CT1_h27+1, CT2_h27+2},{CT0_h27+3, CT1_h27+2, CT2_h27+1},{CT0_h27+2, CT1_h27+4, CT2_h27+4},{CT0_h27+2, CT1_h27+3, CT2_h27+3},{CT0_h27+3, CT1_h27+2, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+1},{CT0_h27+1, CT1_h27+3, CT2_h27+2},{CT0_h27+0, CT1_h27+0, CT2_h27+0},
				{CT0_h28+0, CT1_h28+3, CT2_h28+4},{CT0_h28+1, CT1_h28+2, CT2_h28+3},{CT0_h28+2, CT1_h28+1, CT2_h28+2},{CT0_h28+3, CT1_h28+4, CT2_h28+4},{CT0_h28+2, CT1_h28+3, CT2_h28+2},{CT0_h28+4, CT1_h28+2, CT2_h28+2},{CT0_h28+2, CT1_h28+1, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+0},{CT0_h28+3, CT1_h28+3, CT2_h28+0},{CT0_h28+2, CT1_h28+2, CT2_h28+0},{CT0_h28+4, CT1_h28+1, CT2_h28+1},{CT0_h28+2, CT1_h28+4, CT2_h28+2},{CT0_h28+2, CT1_h28+3, CT2_h28+3},{CT0_h28+2, CT1_h28+2, CT2_h28+5},{CT0_h28+1, CT1_h28+4, CT2_h28+0},{CT0_h28+4, CT1_h28+2, CT2_h28+4},{CT0_h28+2, CT1_h28+2, CT2_h28+5},{CT0_h28+3, CT1_h28+2, CT2_h28+2},{CT0_h28+2, CT1_h28+1, CT2_h28+4},{CT0_h28+1, CT1_h28+4, CT2_h28+2},{CT0_h28+4, CT1_h28+2, CT2_h28+3},{CT0_h28+2, CT1_h28+3, CT2_h28+2},{CT0_h28+3, CT1_h28+2, CT2_h28+4},{CT0_h28+2, CT1_h28+1, CT2_h28+0},{CT0_h28+1, CT1_h28+4, CT2_h28+4},{CT0_h28+4, CT1_h28+2, CT2_h28+3},{CT0_h28+3, CT1_h28+3, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+4},{CT0_h28+1, CT1_h28+5, CT2_h28+2},{CT0_h28+2, CT1_h28+0, CT2_h28+2},{CT0_h28+3, CT1_h28+4, CT2_h28+2},{CT0_h28+2, CT1_h28+5, CT2_h28+0},{CT0_h28+1, CT1_h28+2, CT2_h28+0},{CT0_h28+4, CT1_h28+4, CT2_h28+0},{CT0_h28+3, CT1_h28+2, CT2_h28+1},{CT0_h28+2, CT1_h28+2, CT2_h28+2},{CT0_h28+1, CT1_h28+2, CT2_h28+3},{CT0_h28+4, CT1_h28+1, CT2_h28+5},{CT0_h28+3, CT1_h28+4, CT2_h28+0},{CT0_h28+2, CT1_h28+2, CT2_h28+4},{CT0_h28+1, CT1_h28+3, CT2_h28+3},{CT0_h28+2, CT1_h28+2, CT2_h28+2},{CT0_h28+3, CT1_h28+1, CT2_h28+4},{CT0_h28+2, CT1_h28+4, CT2_h28+2},{CT0_h28+4, CT1_h28+2, CT2_h28+2},{CT0_h28+4, CT1_h28+3, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+0},{CT0_h28+2, CT1_h28+5, CT2_h28+0},{CT0_h28+2, CT1_h28+4, CT2_h28+0},{CT0_h28+1, CT1_h28+5, CT2_h28+1},{CT0_h28+1, CT1_h28+1, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+3},{CT0_h28+4, CT1_h28+1, CT2_h28+2},{CT0_h28+3, CT1_h28+2, CT2_h28+1},{CT0_h28+2, CT1_h28+4, CT2_h28+4},{CT0_h28+2, CT1_h28+3, CT2_h28+3},{CT0_h28+3, CT1_h28+2, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+1},{CT0_h28+1, CT1_h28+3, CT2_h28+2},{CT0_h28+0, CT1_h28+0, CT2_h28+0},
				{CT0_h29+0, CT1_h29+3, CT2_h29+4},{CT0_h29+1, CT1_h29+2, CT2_h29+3},{CT0_h29+2, CT1_h29+1, CT2_h29+2},{CT0_h29+3, CT1_h29+4, CT2_h29+4},{CT0_h29+2, CT1_h29+3, CT2_h29+2},{CT0_h29+4, CT1_h29+2, CT2_h29+2},{CT0_h29+2, CT1_h29+1, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+0},{CT0_h29+3, CT1_h29+3, CT2_h29+0},{CT0_h29+2, CT1_h29+2, CT2_h29+0},{CT0_h29+4, CT1_h29+1, CT2_h29+1},{CT0_h29+2, CT1_h29+4, CT2_h29+2},{CT0_h29+2, CT1_h29+3, CT2_h29+3},{CT0_h29+2, CT1_h29+2, CT2_h29+5},{CT0_h29+1, CT1_h29+4, CT2_h29+0},{CT0_h29+4, CT1_h29+2, CT2_h29+4},{CT0_h29+2, CT1_h29+2, CT2_h29+5},{CT0_h29+3, CT1_h29+2, CT2_h29+2},{CT0_h29+2, CT1_h29+1, CT2_h29+4},{CT0_h29+1, CT1_h29+4, CT2_h29+2},{CT0_h29+4, CT1_h29+2, CT2_h29+3},{CT0_h29+2, CT1_h29+3, CT2_h29+2},{CT0_h29+3, CT1_h29+2, CT2_h29+4},{CT0_h29+2, CT1_h29+1, CT2_h29+0},{CT0_h29+1, CT1_h29+4, CT2_h29+4},{CT0_h29+4, CT1_h29+2, CT2_h29+3},{CT0_h29+3, CT1_h29+3, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+4},{CT0_h29+1, CT1_h29+5, CT2_h29+2},{CT0_h29+2, CT1_h29+0, CT2_h29+2},{CT0_h29+3, CT1_h29+4, CT2_h29+2},{CT0_h29+2, CT1_h29+5, CT2_h29+0},{CT0_h29+1, CT1_h29+2, CT2_h29+0},{CT0_h29+4, CT1_h29+4, CT2_h29+0},{CT0_h29+3, CT1_h29+2, CT2_h29+1},{CT0_h29+2, CT1_h29+2, CT2_h29+2},{CT0_h29+1, CT1_h29+2, CT2_h29+3},{CT0_h29+4, CT1_h29+1, CT2_h29+5},{CT0_h29+3, CT1_h29+4, CT2_h29+0},{CT0_h29+2, CT1_h29+2, CT2_h29+4},{CT0_h29+1, CT1_h29+3, CT2_h29+3},{CT0_h29+2, CT1_h29+2, CT2_h29+2},{CT0_h29+3, CT1_h29+1, CT2_h29+4},{CT0_h29+2, CT1_h29+4, CT2_h29+2},{CT0_h29+4, CT1_h29+2, CT2_h29+2},{CT0_h29+4, CT1_h29+3, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+0},{CT0_h29+2, CT1_h29+5, CT2_h29+0},{CT0_h29+2, CT1_h29+4, CT2_h29+0},{CT0_h29+1, CT1_h29+5, CT2_h29+1},{CT0_h29+1, CT1_h29+1, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+3},{CT0_h29+4, CT1_h29+1, CT2_h29+2},{CT0_h29+3, CT1_h29+2, CT2_h29+1},{CT0_h29+2, CT1_h29+4, CT2_h29+4},{CT0_h29+2, CT1_h29+3, CT2_h29+3},{CT0_h29+3, CT1_h29+2, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+1},{CT0_h29+1, CT1_h29+3, CT2_h29+2},{CT0_h29+0, CT1_h29+0, CT2_h29+0},
				{CT0_h30+0, CT1_h30+3, CT2_h30+4},{CT0_h30+1, CT1_h30+2, CT2_h30+3},{CT0_h30+2, CT1_h30+1, CT2_h30+2},{CT0_h30+3, CT1_h30+4, CT2_h30+4},{CT0_h30+2, CT1_h30+3, CT2_h30+2},{CT0_h30+4, CT1_h30+2, CT2_h30+2},{CT0_h30+2, CT1_h30+1, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+0},{CT0_h30+3, CT1_h30+3, CT2_h30+0},{CT0_h30+2, CT1_h30+2, CT2_h30+0},{CT0_h30+4, CT1_h30+1, CT2_h30+1},{CT0_h30+2, CT1_h30+4, CT2_h30+2},{CT0_h30+2, CT1_h30+3, CT2_h30+3},{CT0_h30+2, CT1_h30+2, CT2_h30+5},{CT0_h30+1, CT1_h30+4, CT2_h30+0},{CT0_h30+4, CT1_h30+2, CT2_h30+4},{CT0_h30+2, CT1_h30+2, CT2_h30+5},{CT0_h30+3, CT1_h30+2, CT2_h30+2},{CT0_h30+2, CT1_h30+1, CT2_h30+4},{CT0_h30+1, CT1_h30+4, CT2_h30+2},{CT0_h30+4, CT1_h30+2, CT2_h30+3},{CT0_h30+2, CT1_h30+3, CT2_h30+2},{CT0_h30+3, CT1_h30+2, CT2_h30+4},{CT0_h30+2, CT1_h30+1, CT2_h30+0},{CT0_h30+1, CT1_h30+4, CT2_h30+4},{CT0_h30+4, CT1_h30+2, CT2_h30+3},{CT0_h30+3, CT1_h30+3, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+4},{CT0_h30+1, CT1_h30+5, CT2_h30+2},{CT0_h30+2, CT1_h30+0, CT2_h30+2},{CT0_h30+3, CT1_h30+4, CT2_h30+2},{CT0_h30+2, CT1_h30+5, CT2_h30+0},{CT0_h30+1, CT1_h30+2, CT2_h30+0},{CT0_h30+4, CT1_h30+4, CT2_h30+0},{CT0_h30+3, CT1_h30+2, CT2_h30+1},{CT0_h30+2, CT1_h30+2, CT2_h30+2},{CT0_h30+1, CT1_h30+2, CT2_h30+3},{CT0_h30+4, CT1_h30+1, CT2_h30+5},{CT0_h30+3, CT1_h30+4, CT2_h30+0},{CT0_h30+2, CT1_h30+2, CT2_h30+4},{CT0_h30+1, CT1_h30+3, CT2_h30+3},{CT0_h30+2, CT1_h30+2, CT2_h30+2},{CT0_h30+3, CT1_h30+1, CT2_h30+4},{CT0_h30+2, CT1_h30+4, CT2_h30+2},{CT0_h30+4, CT1_h30+2, CT2_h30+2},{CT0_h30+4, CT1_h30+3, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+0},{CT0_h30+2, CT1_h30+5, CT2_h30+0},{CT0_h30+2, CT1_h30+4, CT2_h30+0},{CT0_h30+1, CT1_h30+5, CT2_h30+1},{CT0_h30+1, CT1_h30+1, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+3},{CT0_h30+4, CT1_h30+1, CT2_h30+2},{CT0_h30+3, CT1_h30+2, CT2_h30+1},{CT0_h30+2, CT1_h30+4, CT2_h30+4},{CT0_h30+2, CT1_h30+3, CT2_h30+3},{CT0_h30+3, CT1_h30+2, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+1},{CT0_h30+1, CT1_h30+3, CT2_h30+2},{CT0_h30+0, CT1_h30+0, CT2_h30+0},
				{CT0_h31+0, CT1_h31+3, CT2_h31+4},{CT0_h31+1, CT1_h31+2, CT2_h31+3},{CT0_h31+2, CT1_h31+1, CT2_h31+2},{CT0_h31+3, CT1_h31+4, CT2_h31+4},{CT0_h31+2, CT1_h31+3, CT2_h31+2},{CT0_h31+4, CT1_h31+2, CT2_h31+2},{CT0_h31+2, CT1_h31+1, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+0},{CT0_h31+3, CT1_h31+3, CT2_h31+0},{CT0_h31+2, CT1_h31+2, CT2_h31+0},{CT0_h31+4, CT1_h31+1, CT2_h31+1},{CT0_h31+2, CT1_h31+4, CT2_h31+2},{CT0_h31+2, CT1_h31+3, CT2_h31+3},{CT0_h31+2, CT1_h31+2, CT2_h31+5},{CT0_h31+1, CT1_h31+4, CT2_h31+0},{CT0_h31+4, CT1_h31+2, CT2_h31+4},{CT0_h31+2, CT1_h31+2, CT2_h31+5},{CT0_h31+3, CT1_h31+2, CT2_h31+2},{CT0_h31+2, CT1_h31+1, CT2_h31+4},{CT0_h31+1, CT1_h31+4, CT2_h31+2},{CT0_h31+4, CT1_h31+2, CT2_h31+3},{CT0_h31+2, CT1_h31+3, CT2_h31+2},{CT0_h31+3, CT1_h31+2, CT2_h31+4},{CT0_h31+2, CT1_h31+1, CT2_h31+0},{CT0_h31+1, CT1_h31+4, CT2_h31+4},{CT0_h31+4, CT1_h31+2, CT2_h31+3},{CT0_h31+3, CT1_h31+3, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+4},{CT0_h31+1, CT1_h31+5, CT2_h31+2},{CT0_h31+2, CT1_h31+0, CT2_h31+2},{CT0_h31+3, CT1_h31+4, CT2_h31+2},{CT0_h31+2, CT1_h31+5, CT2_h31+0},{CT0_h31+1, CT1_h31+2, CT2_h31+0},{CT0_h31+4, CT1_h31+4, CT2_h31+0},{CT0_h31+3, CT1_h31+2, CT2_h31+1},{CT0_h31+2, CT1_h31+2, CT2_h31+2},{CT0_h31+1, CT1_h31+2, CT2_h31+3},{CT0_h31+4, CT1_h31+1, CT2_h31+5},{CT0_h31+3, CT1_h31+4, CT2_h31+0},{CT0_h31+2, CT1_h31+2, CT2_h31+4},{CT0_h31+1, CT1_h31+3, CT2_h31+3},{CT0_h31+2, CT1_h31+2, CT2_h31+2},{CT0_h31+3, CT1_h31+1, CT2_h31+4},{CT0_h31+2, CT1_h31+4, CT2_h31+2},{CT0_h31+4, CT1_h31+2, CT2_h31+2},{CT0_h31+4, CT1_h31+3, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+0},{CT0_h31+2, CT1_h31+5, CT2_h31+0},{CT0_h31+2, CT1_h31+4, CT2_h31+0},{CT0_h31+1, CT1_h31+5, CT2_h31+1},{CT0_h31+1, CT1_h31+1, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+3},{CT0_h31+4, CT1_h31+1, CT2_h31+2},{CT0_h31+3, CT1_h31+2, CT2_h31+1},{CT0_h31+2, CT1_h31+4, CT2_h31+4},{CT0_h31+2, CT1_h31+3, CT2_h31+3},{CT0_h31+3, CT1_h31+2, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+1},{CT0_h31+1, CT1_h31+3, CT2_h31+2},{CT0_h31+0, CT1_h31+0, CT2_h31+0},
				{CT0_h32+0, CT1_h32+3, CT2_h32+4},{CT0_h32+1, CT1_h32+2, CT2_h32+3},{CT0_h32+2, CT1_h32+1, CT2_h32+2},{CT0_h32+3, CT1_h32+4, CT2_h32+4},{CT0_h32+2, CT1_h32+3, CT2_h32+2},{CT0_h32+4, CT1_h32+2, CT2_h32+2},{CT0_h32+2, CT1_h32+1, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+0},{CT0_h32+3, CT1_h32+3, CT2_h32+0},{CT0_h32+2, CT1_h32+2, CT2_h32+0},{CT0_h32+4, CT1_h32+1, CT2_h32+1},{CT0_h32+2, CT1_h32+4, CT2_h32+2},{CT0_h32+2, CT1_h32+3, CT2_h32+3},{CT0_h32+2, CT1_h32+2, CT2_h32+5},{CT0_h32+1, CT1_h32+4, CT2_h32+0},{CT0_h32+4, CT1_h32+2, CT2_h32+4},{CT0_h32+2, CT1_h32+2, CT2_h32+5},{CT0_h32+3, CT1_h32+2, CT2_h32+2},{CT0_h32+2, CT1_h32+1, CT2_h32+4},{CT0_h32+1, CT1_h32+4, CT2_h32+2},{CT0_h32+4, CT1_h32+2, CT2_h32+3},{CT0_h32+2, CT1_h32+3, CT2_h32+2},{CT0_h32+3, CT1_h32+2, CT2_h32+4},{CT0_h32+2, CT1_h32+1, CT2_h32+0},{CT0_h32+1, CT1_h32+4, CT2_h32+4},{CT0_h32+4, CT1_h32+2, CT2_h32+3},{CT0_h32+3, CT1_h32+3, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+4},{CT0_h32+1, CT1_h32+5, CT2_h32+2},{CT0_h32+2, CT1_h32+0, CT2_h32+2},{CT0_h32+3, CT1_h32+4, CT2_h32+2},{CT0_h32+2, CT1_h32+5, CT2_h32+0},{CT0_h32+1, CT1_h32+2, CT2_h32+0},{CT0_h32+4, CT1_h32+4, CT2_h32+0},{CT0_h32+3, CT1_h32+2, CT2_h32+1},{CT0_h32+2, CT1_h32+2, CT2_h32+2},{CT0_h32+1, CT1_h32+2, CT2_h32+3},{CT0_h32+4, CT1_h32+1, CT2_h32+5},{CT0_h32+3, CT1_h32+4, CT2_h32+0},{CT0_h32+2, CT1_h32+2, CT2_h32+4},{CT0_h32+1, CT1_h32+3, CT2_h32+3},{CT0_h32+2, CT1_h32+2, CT2_h32+2},{CT0_h32+3, CT1_h32+1, CT2_h32+4},{CT0_h32+2, CT1_h32+4, CT2_h32+2},{CT0_h32+4, CT1_h32+2, CT2_h32+2},{CT0_h32+4, CT1_h32+3, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+0},{CT0_h32+2, CT1_h32+5, CT2_h32+0},{CT0_h32+2, CT1_h32+4, CT2_h32+0},{CT0_h32+1, CT1_h32+5, CT2_h32+1},{CT0_h32+1, CT1_h32+1, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+3},{CT0_h32+4, CT1_h32+1, CT2_h32+2},{CT0_h32+3, CT1_h32+2, CT2_h32+1},{CT0_h32+2, CT1_h32+4, CT2_h32+4},{CT0_h32+2, CT1_h32+3, CT2_h32+3},{CT0_h32+3, CT1_h32+2, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+1},{CT0_h32+1, CT1_h32+3, CT2_h32+2},{CT0_h32+0, CT1_h32+0, CT2_h32+0},
				{CT0_h33+0, CT1_h33+3, CT2_h33+4},{CT0_h33+1, CT1_h33+2, CT2_h33+3},{CT0_h33+2, CT1_h33+1, CT2_h33+2},{CT0_h33+3, CT1_h33+4, CT2_h33+4},{CT0_h33+2, CT1_h33+3, CT2_h33+2},{CT0_h33+4, CT1_h33+2, CT2_h33+2},{CT0_h33+2, CT1_h33+1, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+0},{CT0_h33+3, CT1_h33+3, CT2_h33+0},{CT0_h33+2, CT1_h33+2, CT2_h33+0},{CT0_h33+4, CT1_h33+1, CT2_h33+1},{CT0_h33+2, CT1_h33+4, CT2_h33+2},{CT0_h33+2, CT1_h33+3, CT2_h33+3},{CT0_h33+2, CT1_h33+2, CT2_h33+5},{CT0_h33+1, CT1_h33+4, CT2_h33+0},{CT0_h33+4, CT1_h33+2, CT2_h33+4},{CT0_h33+2, CT1_h33+2, CT2_h33+5},{CT0_h33+3, CT1_h33+2, CT2_h33+2},{CT0_h33+2, CT1_h33+1, CT2_h33+4},{CT0_h33+1, CT1_h33+4, CT2_h33+2},{CT0_h33+4, CT1_h33+2, CT2_h33+3},{CT0_h33+2, CT1_h33+3, CT2_h33+2},{CT0_h33+3, CT1_h33+2, CT2_h33+4},{CT0_h33+2, CT1_h33+1, CT2_h33+0},{CT0_h33+1, CT1_h33+4, CT2_h33+4},{CT0_h33+4, CT1_h33+2, CT2_h33+3},{CT0_h33+3, CT1_h33+3, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+4},{CT0_h33+1, CT1_h33+5, CT2_h33+2},{CT0_h33+2, CT1_h33+0, CT2_h33+2},{CT0_h33+3, CT1_h33+4, CT2_h33+2},{CT0_h33+2, CT1_h33+5, CT2_h33+0},{CT0_h33+1, CT1_h33+2, CT2_h33+0},{CT0_h33+4, CT1_h33+4, CT2_h33+0},{CT0_h33+3, CT1_h33+2, CT2_h33+1},{CT0_h33+2, CT1_h33+2, CT2_h33+2},{CT0_h33+1, CT1_h33+2, CT2_h33+3},{CT0_h33+4, CT1_h33+1, CT2_h33+5},{CT0_h33+3, CT1_h33+4, CT2_h33+0},{CT0_h33+2, CT1_h33+2, CT2_h33+4},{CT0_h33+1, CT1_h33+3, CT2_h33+3},{CT0_h33+2, CT1_h33+2, CT2_h33+2},{CT0_h33+3, CT1_h33+1, CT2_h33+4},{CT0_h33+2, CT1_h33+4, CT2_h33+2},{CT0_h33+4, CT1_h33+2, CT2_h33+2},{CT0_h33+4, CT1_h33+3, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+0},{CT0_h33+2, CT1_h33+5, CT2_h33+0},{CT0_h33+2, CT1_h33+4, CT2_h33+0},{CT0_h33+1, CT1_h33+5, CT2_h33+1},{CT0_h33+1, CT1_h33+1, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+3},{CT0_h33+4, CT1_h33+1, CT2_h33+2},{CT0_h33+3, CT1_h33+2, CT2_h33+1},{CT0_h33+2, CT1_h33+4, CT2_h33+4},{CT0_h33+2, CT1_h33+3, CT2_h33+3},{CT0_h33+3, CT1_h33+2, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+1},{CT0_h33+1, CT1_h33+3, CT2_h33+2},{CT0_h33+0, CT1_h33+0, CT2_h33+0},
				{CT0_h34+0, CT1_h34+3, CT2_h34+4},{CT0_h34+1, CT1_h34+2, CT2_h34+3},{CT0_h34+2, CT1_h34+1, CT2_h34+2},{CT0_h34+3, CT1_h34+4, CT2_h34+4},{CT0_h34+2, CT1_h34+3, CT2_h34+2},{CT0_h34+4, CT1_h34+2, CT2_h34+2},{CT0_h34+2, CT1_h34+1, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+0},{CT0_h34+3, CT1_h34+3, CT2_h34+0},{CT0_h34+2, CT1_h34+2, CT2_h34+0},{CT0_h34+4, CT1_h34+1, CT2_h34+1},{CT0_h34+2, CT1_h34+4, CT2_h34+2},{CT0_h34+2, CT1_h34+3, CT2_h34+3},{CT0_h34+2, CT1_h34+2, CT2_h34+5},{CT0_h34+1, CT1_h34+4, CT2_h34+0},{CT0_h34+4, CT1_h34+2, CT2_h34+4},{CT0_h34+2, CT1_h34+2, CT2_h34+5},{CT0_h34+3, CT1_h34+2, CT2_h34+2},{CT0_h34+2, CT1_h34+1, CT2_h34+4},{CT0_h34+1, CT1_h34+4, CT2_h34+2},{CT0_h34+4, CT1_h34+2, CT2_h34+3},{CT0_h34+2, CT1_h34+3, CT2_h34+2},{CT0_h34+3, CT1_h34+2, CT2_h34+4},{CT0_h34+2, CT1_h34+1, CT2_h34+0},{CT0_h34+1, CT1_h34+4, CT2_h34+4},{CT0_h34+4, CT1_h34+2, CT2_h34+3},{CT0_h34+3, CT1_h34+3, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+4},{CT0_h34+1, CT1_h34+5, CT2_h34+2},{CT0_h34+2, CT1_h34+0, CT2_h34+2},{CT0_h34+3, CT1_h34+4, CT2_h34+2},{CT0_h34+2, CT1_h34+5, CT2_h34+0},{CT0_h34+1, CT1_h34+2, CT2_h34+0},{CT0_h34+4, CT1_h34+4, CT2_h34+0},{CT0_h34+3, CT1_h34+2, CT2_h34+1},{CT0_h34+2, CT1_h34+2, CT2_h34+2},{CT0_h34+1, CT1_h34+2, CT2_h34+3},{CT0_h34+4, CT1_h34+1, CT2_h34+5},{CT0_h34+3, CT1_h34+4, CT2_h34+0},{CT0_h34+2, CT1_h34+2, CT2_h34+4},{CT0_h34+1, CT1_h34+3, CT2_h34+3},{CT0_h34+2, CT1_h34+2, CT2_h34+2},{CT0_h34+3, CT1_h34+1, CT2_h34+4},{CT0_h34+2, CT1_h34+4, CT2_h34+2},{CT0_h34+4, CT1_h34+2, CT2_h34+2},{CT0_h34+4, CT1_h34+3, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+0},{CT0_h34+2, CT1_h34+5, CT2_h34+0},{CT0_h34+2, CT1_h34+4, CT2_h34+0},{CT0_h34+1, CT1_h34+5, CT2_h34+1},{CT0_h34+1, CT1_h34+1, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+3},{CT0_h34+4, CT1_h34+1, CT2_h34+2},{CT0_h34+3, CT1_h34+2, CT2_h34+1},{CT0_h34+2, CT1_h34+4, CT2_h34+4},{CT0_h34+2, CT1_h34+3, CT2_h34+3},{CT0_h34+3, CT1_h34+2, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+1},{CT0_h34+1, CT1_h34+3, CT2_h34+2},{CT0_h34+0, CT1_h34+0, CT2_h34+0},
				{CT0_h35+0, CT1_h35+3, CT2_h35+4},{CT0_h35+1, CT1_h35+2, CT2_h35+3},{CT0_h35+2, CT1_h35+1, CT2_h35+2},{CT0_h35+3, CT1_h35+4, CT2_h35+4},{CT0_h35+2, CT1_h35+3, CT2_h35+2},{CT0_h35+4, CT1_h35+2, CT2_h35+2},{CT0_h35+2, CT1_h35+1, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+0},{CT0_h35+3, CT1_h35+3, CT2_h35+0},{CT0_h35+2, CT1_h35+2, CT2_h35+0},{CT0_h35+4, CT1_h35+1, CT2_h35+1},{CT0_h35+2, CT1_h35+4, CT2_h35+2},{CT0_h35+2, CT1_h35+3, CT2_h35+3},{CT0_h35+2, CT1_h35+2, CT2_h35+5},{CT0_h35+1, CT1_h35+4, CT2_h35+0},{CT0_h35+4, CT1_h35+2, CT2_h35+4},{CT0_h35+2, CT1_h35+2, CT2_h35+5},{CT0_h35+3, CT1_h35+2, CT2_h35+2},{CT0_h35+2, CT1_h35+1, CT2_h35+4},{CT0_h35+1, CT1_h35+4, CT2_h35+2},{CT0_h35+4, CT1_h35+2, CT2_h35+3},{CT0_h35+2, CT1_h35+3, CT2_h35+2},{CT0_h35+3, CT1_h35+2, CT2_h35+4},{CT0_h35+2, CT1_h35+1, CT2_h35+0},{CT0_h35+1, CT1_h35+4, CT2_h35+4},{CT0_h35+4, CT1_h35+2, CT2_h35+3},{CT0_h35+3, CT1_h35+3, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+4},{CT0_h35+1, CT1_h35+5, CT2_h35+2},{CT0_h35+2, CT1_h35+0, CT2_h35+2},{CT0_h35+3, CT1_h35+4, CT2_h35+2},{CT0_h35+2, CT1_h35+5, CT2_h35+0},{CT0_h35+1, CT1_h35+2, CT2_h35+0},{CT0_h35+4, CT1_h35+4, CT2_h35+0},{CT0_h35+3, CT1_h35+2, CT2_h35+1},{CT0_h35+2, CT1_h35+2, CT2_h35+2},{CT0_h35+1, CT1_h35+2, CT2_h35+3},{CT0_h35+4, CT1_h35+1, CT2_h35+5},{CT0_h35+3, CT1_h35+4, CT2_h35+0},{CT0_h35+2, CT1_h35+2, CT2_h35+4},{CT0_h35+1, CT1_h35+3, CT2_h35+3},{CT0_h35+2, CT1_h35+2, CT2_h35+2},{CT0_h35+3, CT1_h35+1, CT2_h35+4},{CT0_h35+2, CT1_h35+4, CT2_h35+2},{CT0_h35+4, CT1_h35+2, CT2_h35+2},{CT0_h35+4, CT1_h35+3, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+0},{CT0_h35+2, CT1_h35+5, CT2_h35+0},{CT0_h35+2, CT1_h35+4, CT2_h35+0},{CT0_h35+1, CT1_h35+5, CT2_h35+1},{CT0_h35+1, CT1_h35+1, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+3},{CT0_h35+4, CT1_h35+1, CT2_h35+2},{CT0_h35+3, CT1_h35+2, CT2_h35+1},{CT0_h35+2, CT1_h35+4, CT2_h35+4},{CT0_h35+2, CT1_h35+3, CT2_h35+3},{CT0_h35+3, CT1_h35+2, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+1},{CT0_h35+1, CT1_h35+3, CT2_h35+2},{CT0_h35+0, CT1_h35+0, CT2_h35+0},
				{CT0_h36+0, CT1_h36+3, CT2_h36+4},{CT0_h36+1, CT1_h36+2, CT2_h36+3},{CT0_h36+2, CT1_h36+1, CT2_h36+2},{CT0_h36+3, CT1_h36+4, CT2_h36+4},{CT0_h36+2, CT1_h36+3, CT2_h36+2},{CT0_h36+4, CT1_h36+2, CT2_h36+2},{CT0_h36+2, CT1_h36+1, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+0},{CT0_h36+3, CT1_h36+3, CT2_h36+0},{CT0_h36+2, CT1_h36+2, CT2_h36+0},{CT0_h36+4, CT1_h36+1, CT2_h36+1},{CT0_h36+2, CT1_h36+4, CT2_h36+2},{CT0_h36+2, CT1_h36+3, CT2_h36+3},{CT0_h36+2, CT1_h36+2, CT2_h36+5},{CT0_h36+1, CT1_h36+4, CT2_h36+0},{CT0_h36+4, CT1_h36+2, CT2_h36+4},{CT0_h36+2, CT1_h36+2, CT2_h36+5},{CT0_h36+3, CT1_h36+2, CT2_h36+2},{CT0_h36+2, CT1_h36+1, CT2_h36+4},{CT0_h36+1, CT1_h36+4, CT2_h36+2},{CT0_h36+4, CT1_h36+2, CT2_h36+3},{CT0_h36+2, CT1_h36+3, CT2_h36+2},{CT0_h36+3, CT1_h36+2, CT2_h36+4},{CT0_h36+2, CT1_h36+1, CT2_h36+0},{CT0_h36+1, CT1_h36+4, CT2_h36+4},{CT0_h36+4, CT1_h36+2, CT2_h36+3},{CT0_h36+3, CT1_h36+3, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+4},{CT0_h36+1, CT1_h36+5, CT2_h36+2},{CT0_h36+2, CT1_h36+0, CT2_h36+2},{CT0_h36+3, CT1_h36+4, CT2_h36+2},{CT0_h36+2, CT1_h36+5, CT2_h36+0},{CT0_h36+1, CT1_h36+2, CT2_h36+0},{CT0_h36+4, CT1_h36+4, CT2_h36+0},{CT0_h36+3, CT1_h36+2, CT2_h36+1},{CT0_h36+2, CT1_h36+2, CT2_h36+2},{CT0_h36+1, CT1_h36+2, CT2_h36+3},{CT0_h36+4, CT1_h36+1, CT2_h36+5},{CT0_h36+3, CT1_h36+4, CT2_h36+0},{CT0_h36+2, CT1_h36+2, CT2_h36+4},{CT0_h36+1, CT1_h36+3, CT2_h36+3},{CT0_h36+2, CT1_h36+2, CT2_h36+2},{CT0_h36+3, CT1_h36+1, CT2_h36+4},{CT0_h36+2, CT1_h36+4, CT2_h36+2},{CT0_h36+4, CT1_h36+2, CT2_h36+2},{CT0_h36+4, CT1_h36+3, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+0},{CT0_h36+2, CT1_h36+5, CT2_h36+0},{CT0_h36+2, CT1_h36+4, CT2_h36+0},{CT0_h36+1, CT1_h36+5, CT2_h36+1},{CT0_h36+1, CT1_h36+1, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+3},{CT0_h36+4, CT1_h36+1, CT2_h36+2},{CT0_h36+3, CT1_h36+2, CT2_h36+1},{CT0_h36+2, CT1_h36+4, CT2_h36+4},{CT0_h36+2, CT1_h36+3, CT2_h36+3},{CT0_h36+3, CT1_h36+2, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+1},{CT0_h36+1, CT1_h36+3, CT2_h36+2},{CT0_h36+0, CT1_h36+0, CT2_h36+0},
				{CT0_h37+0, CT1_h37+3, CT2_h37+4},{CT0_h37+1, CT1_h37+2, CT2_h37+3},{CT0_h37+2, CT1_h37+1, CT2_h37+2},{CT0_h37+3, CT1_h37+4, CT2_h37+4},{CT0_h37+2, CT1_h37+3, CT2_h37+2},{CT0_h37+4, CT1_h37+2, CT2_h37+2},{CT0_h37+2, CT1_h37+1, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+0},{CT0_h37+3, CT1_h37+3, CT2_h37+0},{CT0_h37+2, CT1_h37+2, CT2_h37+0},{CT0_h37+4, CT1_h37+1, CT2_h37+1},{CT0_h37+2, CT1_h37+4, CT2_h37+2},{CT0_h37+2, CT1_h37+3, CT2_h37+3},{CT0_h37+2, CT1_h37+2, CT2_h37+5},{CT0_h37+1, CT1_h37+4, CT2_h37+0},{CT0_h37+4, CT1_h37+2, CT2_h37+4},{CT0_h37+2, CT1_h37+2, CT2_h37+5},{CT0_h37+3, CT1_h37+2, CT2_h37+2},{CT0_h37+2, CT1_h37+1, CT2_h37+4},{CT0_h37+1, CT1_h37+4, CT2_h37+2},{CT0_h37+4, CT1_h37+2, CT2_h37+3},{CT0_h37+2, CT1_h37+3, CT2_h37+2},{CT0_h37+3, CT1_h37+2, CT2_h37+4},{CT0_h37+2, CT1_h37+1, CT2_h37+0},{CT0_h37+1, CT1_h37+4, CT2_h37+4},{CT0_h37+4, CT1_h37+2, CT2_h37+3},{CT0_h37+3, CT1_h37+3, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+4},{CT0_h37+1, CT1_h37+5, CT2_h37+2},{CT0_h37+2, CT1_h37+0, CT2_h37+2},{CT0_h37+3, CT1_h37+4, CT2_h37+2},{CT0_h37+2, CT1_h37+5, CT2_h37+0},{CT0_h37+1, CT1_h37+2, CT2_h37+0},{CT0_h37+4, CT1_h37+4, CT2_h37+0},{CT0_h37+3, CT1_h37+2, CT2_h37+1},{CT0_h37+2, CT1_h37+2, CT2_h37+2},{CT0_h37+1, CT1_h37+2, CT2_h37+3},{CT0_h37+4, CT1_h37+1, CT2_h37+5},{CT0_h37+3, CT1_h37+4, CT2_h37+0},{CT0_h37+2, CT1_h37+2, CT2_h37+4},{CT0_h37+1, CT1_h37+3, CT2_h37+3},{CT0_h37+2, CT1_h37+2, CT2_h37+2},{CT0_h37+3, CT1_h37+1, CT2_h37+4},{CT0_h37+2, CT1_h37+4, CT2_h37+2},{CT0_h37+4, CT1_h37+2, CT2_h37+2},{CT0_h37+4, CT1_h37+3, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+0},{CT0_h37+2, CT1_h37+5, CT2_h37+0},{CT0_h37+2, CT1_h37+4, CT2_h37+0},{CT0_h37+1, CT1_h37+5, CT2_h37+1},{CT0_h37+1, CT1_h37+1, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+3},{CT0_h37+4, CT1_h37+1, CT2_h37+2},{CT0_h37+3, CT1_h37+2, CT2_h37+1},{CT0_h37+2, CT1_h37+4, CT2_h37+4},{CT0_h37+2, CT1_h37+3, CT2_h37+3},{CT0_h37+3, CT1_h37+2, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+1},{CT0_h37+1, CT1_h37+3, CT2_h37+2},{CT0_h37+0, CT1_h37+0, CT2_h37+0},
				{CT0_h38+0, CT1_h38+3, CT2_h38+4},{CT0_h38+1, CT1_h38+2, CT2_h38+3},{CT0_h38+2, CT1_h38+1, CT2_h38+2},{CT0_h38+3, CT1_h38+4, CT2_h38+4},{CT0_h38+2, CT1_h38+3, CT2_h38+2},{CT0_h38+4, CT1_h38+2, CT2_h38+2},{CT0_h38+2, CT1_h38+1, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+0},{CT0_h38+3, CT1_h38+3, CT2_h38+0},{CT0_h38+2, CT1_h38+2, CT2_h38+0},{CT0_h38+4, CT1_h38+1, CT2_h38+1},{CT0_h38+2, CT1_h38+4, CT2_h38+2},{CT0_h38+2, CT1_h38+3, CT2_h38+3},{CT0_h38+2, CT1_h38+2, CT2_h38+5},{CT0_h38+1, CT1_h38+4, CT2_h38+0},{CT0_h38+4, CT1_h38+2, CT2_h38+4},{CT0_h38+2, CT1_h38+2, CT2_h38+5},{CT0_h38+3, CT1_h38+2, CT2_h38+2},{CT0_h38+2, CT1_h38+1, CT2_h38+4},{CT0_h38+1, CT1_h38+4, CT2_h38+2},{CT0_h38+4, CT1_h38+2, CT2_h38+3},{CT0_h38+2, CT1_h38+3, CT2_h38+2},{CT0_h38+3, CT1_h38+2, CT2_h38+4},{CT0_h38+2, CT1_h38+1, CT2_h38+0},{CT0_h38+1, CT1_h38+4, CT2_h38+4},{CT0_h38+4, CT1_h38+2, CT2_h38+3},{CT0_h38+3, CT1_h38+3, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+4},{CT0_h38+1, CT1_h38+5, CT2_h38+2},{CT0_h38+2, CT1_h38+0, CT2_h38+2},{CT0_h38+3, CT1_h38+4, CT2_h38+2},{CT0_h38+2, CT1_h38+5, CT2_h38+0},{CT0_h38+1, CT1_h38+2, CT2_h38+0},{CT0_h38+4, CT1_h38+4, CT2_h38+0},{CT0_h38+3, CT1_h38+2, CT2_h38+1},{CT0_h38+2, CT1_h38+2, CT2_h38+2},{CT0_h38+1, CT1_h38+2, CT2_h38+3},{CT0_h38+4, CT1_h38+1, CT2_h38+5},{CT0_h38+3, CT1_h38+4, CT2_h38+0},{CT0_h38+2, CT1_h38+2, CT2_h38+4},{CT0_h38+1, CT1_h38+3, CT2_h38+3},{CT0_h38+2, CT1_h38+2, CT2_h38+2},{CT0_h38+3, CT1_h38+1, CT2_h38+4},{CT0_h38+2, CT1_h38+4, CT2_h38+2},{CT0_h38+4, CT1_h38+2, CT2_h38+2},{CT0_h38+4, CT1_h38+3, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+0},{CT0_h38+2, CT1_h38+5, CT2_h38+0},{CT0_h38+2, CT1_h38+4, CT2_h38+0},{CT0_h38+1, CT1_h38+5, CT2_h38+1},{CT0_h38+1, CT1_h38+1, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+3},{CT0_h38+4, CT1_h38+1, CT2_h38+2},{CT0_h38+3, CT1_h38+2, CT2_h38+1},{CT0_h38+2, CT1_h38+4, CT2_h38+4},{CT0_h38+2, CT1_h38+3, CT2_h38+3},{CT0_h38+3, CT1_h38+2, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+1},{CT0_h38+1, CT1_h38+3, CT2_h38+2},{CT0_h38+0, CT1_h38+0, CT2_h38+0},
				{CT0_h39+0, CT1_h39+3, CT2_h39+4},{CT0_h39+1, CT1_h39+2, CT2_h39+3},{CT0_h39+2, CT1_h39+1, CT2_h39+2},{CT0_h39+3, CT1_h39+4, CT2_h39+4},{CT0_h39+2, CT1_h39+3, CT2_h39+2},{CT0_h39+4, CT1_h39+2, CT2_h39+2},{CT0_h39+2, CT1_h39+1, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+0},{CT0_h39+3, CT1_h39+3, CT2_h39+0},{CT0_h39+2, CT1_h39+2, CT2_h39+0},{CT0_h39+4, CT1_h39+1, CT2_h39+1},{CT0_h39+2, CT1_h39+4, CT2_h39+2},{CT0_h39+2, CT1_h39+3, CT2_h39+3},{CT0_h39+2, CT1_h39+2, CT2_h39+5},{CT0_h39+1, CT1_h39+4, CT2_h39+0},{CT0_h39+4, CT1_h39+2, CT2_h39+4},{CT0_h39+2, CT1_h39+2, CT2_h39+5},{CT0_h39+3, CT1_h39+2, CT2_h39+2},{CT0_h39+2, CT1_h39+1, CT2_h39+4},{CT0_h39+1, CT1_h39+4, CT2_h39+2},{CT0_h39+4, CT1_h39+2, CT2_h39+3},{CT0_h39+2, CT1_h39+3, CT2_h39+2},{CT0_h39+3, CT1_h39+2, CT2_h39+4},{CT0_h39+2, CT1_h39+1, CT2_h39+0},{CT0_h39+1, CT1_h39+4, CT2_h39+4},{CT0_h39+4, CT1_h39+2, CT2_h39+3},{CT0_h39+3, CT1_h39+3, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+4},{CT0_h39+1, CT1_h39+5, CT2_h39+2},{CT0_h39+2, CT1_h39+0, CT2_h39+2},{CT0_h39+3, CT1_h39+4, CT2_h39+2},{CT0_h39+2, CT1_h39+5, CT2_h39+0},{CT0_h39+1, CT1_h39+2, CT2_h39+0},{CT0_h39+4, CT1_h39+4, CT2_h39+0},{CT0_h39+3, CT1_h39+2, CT2_h39+1},{CT0_h39+2, CT1_h39+2, CT2_h39+2},{CT0_h39+1, CT1_h39+2, CT2_h39+3},{CT0_h39+4, CT1_h39+1, CT2_h39+5},{CT0_h39+3, CT1_h39+4, CT2_h39+0},{CT0_h39+2, CT1_h39+2, CT2_h39+4},{CT0_h39+1, CT1_h39+3, CT2_h39+3},{CT0_h39+2, CT1_h39+2, CT2_h39+2},{CT0_h39+3, CT1_h39+1, CT2_h39+4},{CT0_h39+2, CT1_h39+4, CT2_h39+2},{CT0_h39+4, CT1_h39+2, CT2_h39+2},{CT0_h39+4, CT1_h39+3, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+0},{CT0_h39+2, CT1_h39+5, CT2_h39+0},{CT0_h39+2, CT1_h39+4, CT2_h39+0},{CT0_h39+1, CT1_h39+5, CT2_h39+1},{CT0_h39+1, CT1_h39+1, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+3},{CT0_h39+4, CT1_h39+1, CT2_h39+2},{CT0_h39+3, CT1_h39+2, CT2_h39+1},{CT0_h39+2, CT1_h39+4, CT2_h39+4},{CT0_h39+2, CT1_h39+3, CT2_h39+3},{CT0_h39+3, CT1_h39+2, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+1},{CT0_h39+1, CT1_h39+3, CT2_h39+2},{CT0_h39+0, CT1_h39+0, CT2_h39+0},
				{CT0_h40+0, CT1_h40+3, CT2_h40+4},{CT0_h40+1, CT1_h40+2, CT2_h40+3},{CT0_h40+2, CT1_h40+1, CT2_h40+2},{CT0_h40+3, CT1_h40+4, CT2_h40+4},{CT0_h40+2, CT1_h40+3, CT2_h40+2},{CT0_h40+4, CT1_h40+2, CT2_h40+2},{CT0_h40+2, CT1_h40+1, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+0},{CT0_h40+3, CT1_h40+3, CT2_h40+0},{CT0_h40+2, CT1_h40+2, CT2_h40+0},{CT0_h40+4, CT1_h40+1, CT2_h40+1},{CT0_h40+2, CT1_h40+4, CT2_h40+2},{CT0_h40+2, CT1_h40+3, CT2_h40+3},{CT0_h40+2, CT1_h40+2, CT2_h40+5},{CT0_h40+1, CT1_h40+4, CT2_h40+0},{CT0_h40+4, CT1_h40+2, CT2_h40+4},{CT0_h40+2, CT1_h40+2, CT2_h40+5},{CT0_h40+3, CT1_h40+2, CT2_h40+2},{CT0_h40+2, CT1_h40+1, CT2_h40+4},{CT0_h40+1, CT1_h40+4, CT2_h40+2},{CT0_h40+4, CT1_h40+2, CT2_h40+3},{CT0_h40+2, CT1_h40+3, CT2_h40+2},{CT0_h40+3, CT1_h40+2, CT2_h40+4},{CT0_h40+2, CT1_h40+1, CT2_h40+0},{CT0_h40+1, CT1_h40+4, CT2_h40+4},{CT0_h40+4, CT1_h40+2, CT2_h40+3},{CT0_h40+3, CT1_h40+3, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+4},{CT0_h40+1, CT1_h40+5, CT2_h40+2},{CT0_h40+2, CT1_h40+0, CT2_h40+2},{CT0_h40+3, CT1_h40+4, CT2_h40+2},{CT0_h40+2, CT1_h40+5, CT2_h40+0},{CT0_h40+1, CT1_h40+2, CT2_h40+0},{CT0_h40+4, CT1_h40+4, CT2_h40+0},{CT0_h40+3, CT1_h40+2, CT2_h40+1},{CT0_h40+2, CT1_h40+2, CT2_h40+2},{CT0_h40+1, CT1_h40+2, CT2_h40+3},{CT0_h40+4, CT1_h40+1, CT2_h40+5},{CT0_h40+3, CT1_h40+4, CT2_h40+0},{CT0_h40+2, CT1_h40+2, CT2_h40+4},{CT0_h40+1, CT1_h40+3, CT2_h40+3},{CT0_h40+2, CT1_h40+2, CT2_h40+2},{CT0_h40+3, CT1_h40+1, CT2_h40+4},{CT0_h40+2, CT1_h40+4, CT2_h40+2},{CT0_h40+4, CT1_h40+2, CT2_h40+2},{CT0_h40+4, CT1_h40+3, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+0},{CT0_h40+2, CT1_h40+5, CT2_h40+0},{CT0_h40+2, CT1_h40+4, CT2_h40+0},{CT0_h40+1, CT1_h40+5, CT2_h40+1},{CT0_h40+1, CT1_h40+1, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+3},{CT0_h40+4, CT1_h40+1, CT2_h40+2},{CT0_h40+3, CT1_h40+2, CT2_h40+1},{CT0_h40+2, CT1_h40+4, CT2_h40+4},{CT0_h40+2, CT1_h40+3, CT2_h40+3},{CT0_h40+3, CT1_h40+2, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+1},{CT0_h40+1, CT1_h40+3, CT2_h40+2},{CT0_h40+0, CT1_h40+0, CT2_h40+0},
				{CT0_h41+0, CT1_h41+3, CT2_h41+4},{CT0_h41+1, CT1_h41+2, CT2_h41+3},{CT0_h41+2, CT1_h41+1, CT2_h41+2},{CT0_h41+3, CT1_h41+4, CT2_h41+4},{CT0_h41+2, CT1_h41+3, CT2_h41+2},{CT0_h41+4, CT1_h41+2, CT2_h41+2},{CT0_h41+2, CT1_h41+1, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+0},{CT0_h41+3, CT1_h41+3, CT2_h41+0},{CT0_h41+2, CT1_h41+2, CT2_h41+0},{CT0_h41+4, CT1_h41+1, CT2_h41+1},{CT0_h41+2, CT1_h41+4, CT2_h41+2},{CT0_h41+2, CT1_h41+3, CT2_h41+3},{CT0_h41+2, CT1_h41+2, CT2_h41+5},{CT0_h41+1, CT1_h41+4, CT2_h41+0},{CT0_h41+4, CT1_h41+2, CT2_h41+4},{CT0_h41+2, CT1_h41+2, CT2_h41+5},{CT0_h41+3, CT1_h41+2, CT2_h41+2},{CT0_h41+2, CT1_h41+1, CT2_h41+4},{CT0_h41+1, CT1_h41+4, CT2_h41+2},{CT0_h41+4, CT1_h41+2, CT2_h41+3},{CT0_h41+2, CT1_h41+3, CT2_h41+2},{CT0_h41+3, CT1_h41+2, CT2_h41+4},{CT0_h41+2, CT1_h41+1, CT2_h41+0},{CT0_h41+1, CT1_h41+4, CT2_h41+4},{CT0_h41+4, CT1_h41+2, CT2_h41+3},{CT0_h41+3, CT1_h41+3, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+4},{CT0_h41+1, CT1_h41+5, CT2_h41+2},{CT0_h41+2, CT1_h41+0, CT2_h41+2},{CT0_h41+3, CT1_h41+4, CT2_h41+2},{CT0_h41+2, CT1_h41+5, CT2_h41+0},{CT0_h41+1, CT1_h41+2, CT2_h41+0},{CT0_h41+4, CT1_h41+4, CT2_h41+0},{CT0_h41+3, CT1_h41+2, CT2_h41+1},{CT0_h41+2, CT1_h41+2, CT2_h41+2},{CT0_h41+1, CT1_h41+2, CT2_h41+3},{CT0_h41+4, CT1_h41+1, CT2_h41+5},{CT0_h41+3, CT1_h41+4, CT2_h41+0},{CT0_h41+2, CT1_h41+2, CT2_h41+4},{CT0_h41+1, CT1_h41+3, CT2_h41+3},{CT0_h41+2, CT1_h41+2, CT2_h41+2},{CT0_h41+3, CT1_h41+1, CT2_h41+4},{CT0_h41+2, CT1_h41+4, CT2_h41+2},{CT0_h41+4, CT1_h41+2, CT2_h41+2},{CT0_h41+4, CT1_h41+3, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+0},{CT0_h41+2, CT1_h41+5, CT2_h41+0},{CT0_h41+2, CT1_h41+4, CT2_h41+0},{CT0_h41+1, CT1_h41+5, CT2_h41+1},{CT0_h41+1, CT1_h41+1, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+3},{CT0_h41+4, CT1_h41+1, CT2_h41+2},{CT0_h41+3, CT1_h41+2, CT2_h41+1},{CT0_h41+2, CT1_h41+4, CT2_h41+4},{CT0_h41+2, CT1_h41+3, CT2_h41+3},{CT0_h41+3, CT1_h41+2, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+1},{CT0_h41+1, CT1_h41+3, CT2_h41+2},{CT0_h41+0, CT1_h41+0, CT2_h41+0},
				{CT0_h42+0, CT1_h42+3, CT2_h42+4},{CT0_h42+1, CT1_h42+2, CT2_h42+3},{CT0_h42+2, CT1_h42+1, CT2_h42+2},{CT0_h42+3, CT1_h42+4, CT2_h42+4},{CT0_h42+2, CT1_h42+3, CT2_h42+2},{CT0_h42+4, CT1_h42+2, CT2_h42+2},{CT0_h42+2, CT1_h42+1, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+0},{CT0_h42+3, CT1_h42+3, CT2_h42+0},{CT0_h42+2, CT1_h42+2, CT2_h42+0},{CT0_h42+4, CT1_h42+1, CT2_h42+1},{CT0_h42+2, CT1_h42+4, CT2_h42+2},{CT0_h42+2, CT1_h42+3, CT2_h42+3},{CT0_h42+2, CT1_h42+2, CT2_h42+5},{CT0_h42+1, CT1_h42+4, CT2_h42+0},{CT0_h42+4, CT1_h42+2, CT2_h42+4},{CT0_h42+2, CT1_h42+2, CT2_h42+5},{CT0_h42+3, CT1_h42+2, CT2_h42+2},{CT0_h42+2, CT1_h42+1, CT2_h42+4},{CT0_h42+1, CT1_h42+4, CT2_h42+2},{CT0_h42+4, CT1_h42+2, CT2_h42+3},{CT0_h42+2, CT1_h42+3, CT2_h42+2},{CT0_h42+3, CT1_h42+2, CT2_h42+4},{CT0_h42+2, CT1_h42+1, CT2_h42+0},{CT0_h42+1, CT1_h42+4, CT2_h42+4},{CT0_h42+4, CT1_h42+2, CT2_h42+3},{CT0_h42+3, CT1_h42+3, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+4},{CT0_h42+1, CT1_h42+5, CT2_h42+2},{CT0_h42+2, CT1_h42+0, CT2_h42+2},{CT0_h42+3, CT1_h42+4, CT2_h42+2},{CT0_h42+2, CT1_h42+5, CT2_h42+0},{CT0_h42+1, CT1_h42+2, CT2_h42+0},{CT0_h42+4, CT1_h42+4, CT2_h42+0},{CT0_h42+3, CT1_h42+2, CT2_h42+1},{CT0_h42+2, CT1_h42+2, CT2_h42+2},{CT0_h42+1, CT1_h42+2, CT2_h42+3},{CT0_h42+4, CT1_h42+1, CT2_h42+5},{CT0_h42+3, CT1_h42+4, CT2_h42+0},{CT0_h42+2, CT1_h42+2, CT2_h42+4},{CT0_h42+1, CT1_h42+3, CT2_h42+3},{CT0_h42+2, CT1_h42+2, CT2_h42+2},{CT0_h42+3, CT1_h42+1, CT2_h42+4},{CT0_h42+2, CT1_h42+4, CT2_h42+2},{CT0_h42+4, CT1_h42+2, CT2_h42+2},{CT0_h42+4, CT1_h42+3, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+0},{CT0_h42+2, CT1_h42+5, CT2_h42+0},{CT0_h42+2, CT1_h42+4, CT2_h42+0},{CT0_h42+1, CT1_h42+5, CT2_h42+1},{CT0_h42+1, CT1_h42+1, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+3},{CT0_h42+4, CT1_h42+1, CT2_h42+2},{CT0_h42+3, CT1_h42+2, CT2_h42+1},{CT0_h42+2, CT1_h42+4, CT2_h42+4},{CT0_h42+2, CT1_h42+3, CT2_h42+3},{CT0_h42+3, CT1_h42+2, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+1},{CT0_h42+1, CT1_h42+3, CT2_h42+2},{CT0_h42+0, CT1_h42+0, CT2_h42+0},*/
				//Uma hora em branco
				{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0}

			};
				
			if (dados == null) {
				dados = new No();
				dados.item = 0;
				Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0,7,7);
				rodada.schedulep(3, 0, dados);

			} else {

				int slot = (int) dados.item;
				for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {

					for (int i = 0; i < matrizB[slot][ct]; i++) {
						No dadosLSP = new No();

						Lsp lsp = new Lsp(rodada);
						lsp.CargaReduzida = 0;
						lsp.src = 0; // id do router fonte
						lsp.dest = 1; // id do router destino
						lsp.CT = ct;
						lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);   //10
						lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.uniform(100, 130);//120; //(int) GeradorDeNumerosAleatorios.expntl(250);  //120
						dadosLSP.item = lsp;

						Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
								+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
								+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao(),7,7);
						rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);
					}
				}
				Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot ,7,7);
				dados.item = slot + 1;
				rodada.schedulep(3, 60, dados);
			}
		}


	
	
	
	// Forcado
	public static void trafegoForcado(RodadaDeSimulacao rodada, Topologia to, No dados) {
			
					
		final int CT0_h1  =	-5 ;
		final int CT1_h1  =	0 ;
		final int CT2_h1  =	0 ;
				
		final int CT0_h2  =	0 ;
		final int CT1_h2  =	0 ;
		final int CT2_h2  =	-5 ;
		   		
		final int CT0_h3  =	0 ;
		final int CT1_h3  =	0 ;
		final int CT2_h3  =	-5 ;
		   		
		final int CT0_h4  =	20 ;
		final int CT1_h4  =	1 ;
		final int CT2_h4  =	10	;
		   		
		final int CT0_h5  =	8 ;
		final int CT1_h5  =	15 ;
		final int CT2_h5  =	0	;
		   		
		final int CT0_h6  =	1 ;
		final int CT1_h6  =	10 ;
		final int CT2_h6  =	15 ;
		
		
		final int [][]matrizA =
			{  	
				


				/*T1 = ideal para RDM ou Alloc*/	
				//{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},{CT0_h1, CT1_h1, CT2_h1},

				/*T2 = ideal para Alloc Somente*/
				//{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},{CT0_h2, CT1_h2, CT2_h2},
				
				/*T3 = ideal para Alloc Somente*/
				//{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},{CT0_h3, CT1_h3, CT2_h3},
				
				/*T4 = ideal para MAM*/
				//{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},{CT0_h4, CT1_h4, CT2_h4},
				
				/*T4 = ideal para MAM*/
				//{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},{CT0_h5, CT1_h5, CT2_h5},
				
				/*T4 = ideal para MAM*/
				//{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},{CT0_h6, CT1_h6, CT2_h6},
				
					

				/*Novo*/
				/*T1 = ideal para RDM ou Alloc*/
				{CT0_h1+34, CT1_h1+6, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+4, CT2_h1+1},	{CT0_h1+33, CT1_h1+3, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+5, CT2_h1+3},	{CT0_h1+34, CT1_h1+7, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+35, CT1_h1+4, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+33, CT1_h1+5, CT2_h1+1},	{CT0_h1+35, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+2, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+36, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+32, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+2},	{CT0_h1+34, CT1_h1+1, CT2_h1+2},	{CT0_h1+36, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+35, CT1_h1+7, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+8, CT2_h1+2},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+3, CT2_h1+2},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+36, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+31, CT1_h1+6, CT2_h1+3},	{CT0_h1+34, CT1_h1+5, CT2_h1+0},	{CT0_h1+33, CT1_h1+9, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+35, CT1_h1+6, CT2_h1+2},	{CT0_h1+32, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+32, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+30, CT1_h1+7, CT2_h1+4},	{CT0_h1+32, CT1_h1+6, CT2_h1+2},	{CT0_h1+36, CT1_h1+2, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},

				/*Novo*/
				/*T2 = ideal para Alloc Somente*/
				{CT0_h2+11, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+11, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+3, CT2_h2+23},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+11, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+4, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+23},	{CT0_h2+12, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+2, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+11, CT1_h2+6, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+1, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+26},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+13, CT1_h2+7, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+8, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+12, CT1_h2+3, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+26},	{CT0_h2+11, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+21},	{CT0_h2+10, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+9, CT2_h2+23},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+25},	{CT0_h2+11, CT1_h2+5, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+7, CT2_h2+22},	{CT0_h2+14, CT1_h2+5, CT2_h2+24},	{CT0_h2+11, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+7, CT2_h2+20},	{CT0_h2+12, CT1_h2+6, CT2_h2+22},	{CT0_h2+11, CT1_h2+2, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},

				/*Novo*/
				/*T3 = ideal para Alloc Somente*/
				{CT0_h3+6, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+11, CT2_h3+25},	{CT0_h3+3, CT1_h3+12, CT2_h3+23},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+23},	{CT0_h3+4, CT1_h3+12, CT2_h3+25},	{CT0_h3+2, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+6, CT1_h3+12, CT2_h3+23},	{CT0_h3+1, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+26},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+8, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+3, CT1_h3+12, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+26},	{CT0_h3+5, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+21},	{CT0_h3+5, CT1_h3+10, CT2_h3+24},	{CT0_h3+9, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+25},	{CT0_h3+5, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+7, CT1_h3+12, CT2_h3+22},	{CT0_h3+5, CT1_h3+14, CT2_h3+24},	{CT0_h3+6, CT1_h3+11, CT2_h3+23},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+14, CT2_h3+20},	{CT0_h3+6, CT1_h3+12, CT2_h3+22},	{CT0_h3+2, CT1_h3+11, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},

				
				/*Novo*/
				/*T4 = ideal para MAM*/
				{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+27, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+28, CT2_h4+22},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+24, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+30, CT1_h4+26, CT2_h4+23},	{CT0_h4+28, CT1_h4+28, CT2_h4+21},	{CT0_h4+29, CT1_h4+24, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+23},	{CT0_h4+27, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+30, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+25, CT1_h4+28, CT2_h4+22},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+19},	{CT0_h4+29, CT1_h4+27, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+26, CT1_h4+24, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+23},	{CT0_h4+27, CT1_h4+29, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+25, CT2_h4+21},	{CT0_h4+24, CT1_h4+29, CT2_h4+23},	{CT0_h4+26, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},
			
				/*//Novo*/
				/*T5 = ideal para MAM*/
				{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+14, CT2_h5+17},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+25, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+19, CT2_h5+60},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+60},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+5, CT2_h5+60},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+22, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+32, CT1_h5+28, CT2_h5+18},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+10},	{CT0_h5+27, CT1_h5+36, CT2_h5+80},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+40},

				/*Novo*/
				/*T6 = ideal para MAM*/
				{CT0_h6+19, CT1_h6+30, CT2_h6+30},	{CT0_h6+20, CT1_h6+16, CT2_h6+20},	{CT0_h6+19, CT1_h6+24, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+18, CT1_h6+16, CT2_h6+27},	{CT0_h6+19, CT1_h6+19, CT2_h6+29},	{CT0_h6+18, CT1_h6+17, CT2_h6+32},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+19, CT1_h6+18, CT2_h6+27},	{CT0_h6+20, CT1_h6+23, CT2_h6+29},	{CT0_h6+19, CT1_h6+18, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+9},	{CT0_h6+19, CT1_h6+18, CT2_h6+12},	{CT0_h6+20, CT1_h6+17, CT2_h6+8},	{CT0_h6+20, CT1_h6+22, CT2_h6+10},	{CT0_h6+19, CT1_h6+15, CT2_h6+13},	{CT0_h6+18, CT1_h6+17, CT2_h6+11},	{CT0_h6+20, CT1_h6+26, CT2_h6+22},	{CT0_h6+19, CT1_h6+18, CT2_h6+36},	{CT0_h6+20, CT1_h6+18, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+25},	{CT0_h6+19, CT1_h6+18, CT2_h6+28},	{CT0_h6+21, CT1_h6+17, CT2_h6+25},	{CT0_h6+19, CT1_h6+19, CT2_h6+30},	{CT0_h6+20, CT1_h6+15, CT2_h6+30},	{CT0_h6+17, CT1_h6+17, CT2_h6+36},	{CT0_h6+19, CT1_h6+25, CT2_h6+28},	{CT0_h6+19, CT1_h6+18, CT2_h6+25},	{CT0_h6+20, CT1_h6+19, CT2_h6+22},	{CT0_h6+18, CT1_h6+18, CT2_h6+36},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+21, CT1_h6+27, CT2_h6+11},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+20, CT1_h6+16, CT2_h6+3},	{CT0_h6+20, CT1_h6+19, CT2_h6+12},	{CT0_h6+19, CT1_h6+28, CT2_h6+2},	{CT0_h6+18, CT1_h6+17, CT2_h6+10},	{CT0_h6+19, CT1_h6+18, CT2_h6+13},	{CT0_h6+20, CT1_h6+18, CT2_h6+10},	{CT0_h6+21, CT1_h6+17, CT2_h6+12},	{CT0_h6+19, CT1_h6+24, CT2_h6+28},	{CT0_h6+19, CT1_h6+17, CT2_h6+27},	{CT0_h6+20, CT1_h6+16, CT2_h6+27},	{CT0_h6+16, CT1_h6+2, CT2_h6+32},	{CT0_h6+19, CT1_h6+20, CT2_h6+32},	{CT0_h6+18, CT1_h6+3, CT2_h6+32},	{CT0_h6+19, CT1_h6+33, CT2_h6+27},	{CT0_h6+20, CT1_h6+36, CT2_h6+27},	{CT0_h6+17, CT1_h6+17, CT2_h6+11},	{CT0_h6+19, CT1_h6+31, CT2_h6+30},	{CT0_h6+19, CT1_h6+22, CT2_h6+12},	{CT0_h6+17, CT1_h6+15, CT2_h6+40},	{CT0_h6+19, CT1_h6+17, CT2_h6+10},	{CT0_h6+18, CT1_h6+26, CT2_h6+13},	{CT0_h6+19, CT1_h6+17, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+27},	{CT0_h6+15, CT1_h6+20, CT2_h6+27},	{CT0_h6+17, CT1_h6+30, CT2_h6+32},	{CT0_h6+21, CT1_h6+19, CT2_h6+32},	{CT0_h6+19, CT1_h6+18, CT2_h6+32},

				

				
				/*Novo*/
				/*T1 = ideal para RDM ou Alloc*/
				{CT0_h1+34, CT1_h1+6, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+4, CT2_h1+1},	{CT0_h1+33, CT1_h1+3, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+5, CT2_h1+3},	{CT0_h1+34, CT1_h1+7, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+35, CT1_h1+4, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+33, CT1_h1+5, CT2_h1+1},	{CT0_h1+35, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+2, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+36, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+32, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+2},	{CT0_h1+34, CT1_h1+1, CT2_h1+2},	{CT0_h1+36, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+35, CT1_h1+7, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+8, CT2_h1+2},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+3, CT2_h1+2},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+36, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+31, CT1_h1+6, CT2_h1+3},	{CT0_h1+34, CT1_h1+5, CT2_h1+0},	{CT0_h1+33, CT1_h1+9, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+35, CT1_h1+6, CT2_h1+2},	{CT0_h1+32, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+32, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+30, CT1_h1+7, CT2_h1+4},	{CT0_h1+32, CT1_h1+6, CT2_h1+2},	{CT0_h1+36, CT1_h1+2, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},

				/*Novo*/
				/*T2 = ideal para Alloc Somente*/
				{CT0_h2+11, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+11, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+3, CT2_h2+23},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+11, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+4, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+23},	{CT0_h2+12, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+2, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+11, CT1_h2+6, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+1, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+26},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+13, CT1_h2+7, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+8, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+12, CT1_h2+3, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+26},	{CT0_h2+11, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+21},	{CT0_h2+10, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+9, CT2_h2+23},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+25},	{CT0_h2+11, CT1_h2+5, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+7, CT2_h2+22},	{CT0_h2+14, CT1_h2+5, CT2_h2+24},	{CT0_h2+11, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+7, CT2_h2+20},	{CT0_h2+12, CT1_h2+6, CT2_h2+22},	{CT0_h2+11, CT1_h2+2, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},

				/*Novo*/
				/*T3 = ideal para Alloc Somente*/
				{CT0_h3+6, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+11, CT2_h3+25},	{CT0_h3+3, CT1_h3+12, CT2_h3+23},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+23},	{CT0_h3+4, CT1_h3+12, CT2_h3+25},	{CT0_h3+2, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+6, CT1_h3+12, CT2_h3+23},	{CT0_h3+1, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+26},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+8, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+3, CT1_h3+12, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+26},	{CT0_h3+5, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+21},	{CT0_h3+5, CT1_h3+10, CT2_h3+24},	{CT0_h3+9, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+25},	{CT0_h3+5, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+7, CT1_h3+12, CT2_h3+22},	{CT0_h3+5, CT1_h3+14, CT2_h3+24},	{CT0_h3+6, CT1_h3+11, CT2_h3+23},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+14, CT2_h3+20},	{CT0_h3+6, CT1_h3+12, CT2_h3+22},	{CT0_h3+2, CT1_h3+11, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},

				
				/*Novo*/
				/*T4 = ideal para MAM*/
				{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+27, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+28, CT2_h4+22},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+24, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+30, CT1_h4+26, CT2_h4+23},	{CT0_h4+28, CT1_h4+28, CT2_h4+21},	{CT0_h4+29, CT1_h4+24, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+23},	{CT0_h4+27, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+30, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+25, CT1_h4+28, CT2_h4+22},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+19},	{CT0_h4+29, CT1_h4+27, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+26, CT1_h4+24, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+23},	{CT0_h4+27, CT1_h4+29, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+25, CT2_h4+21},	{CT0_h4+24, CT1_h4+29, CT2_h4+23},	{CT0_h4+26, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},
			
				/*//Novo*/
				/*T5 = ideal para MAM*/
				{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+14, CT2_h5+17},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+25, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+19, CT2_h5+60},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+60},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+5, CT2_h5+60},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+22, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+32, CT1_h5+28, CT2_h5+18},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+10},	{CT0_h5+27, CT1_h5+36, CT2_h5+80},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+40},

				/*Novo*/
				/*T6 = ideal para MAM*/
				{CT0_h6+19, CT1_h6+30, CT2_h6+30},	{CT0_h6+20, CT1_h6+16, CT2_h6+20},	{CT0_h6+19, CT1_h6+24, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+18, CT1_h6+16, CT2_h6+27},	{CT0_h6+19, CT1_h6+19, CT2_h6+29},	{CT0_h6+18, CT1_h6+17, CT2_h6+32},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+19, CT1_h6+18, CT2_h6+27},	{CT0_h6+20, CT1_h6+23, CT2_h6+29},	{CT0_h6+19, CT1_h6+18, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+9},	{CT0_h6+19, CT1_h6+18, CT2_h6+12},	{CT0_h6+20, CT1_h6+17, CT2_h6+8},	{CT0_h6+20, CT1_h6+22, CT2_h6+10},	{CT0_h6+19, CT1_h6+15, CT2_h6+13},	{CT0_h6+18, CT1_h6+17, CT2_h6+11},	{CT0_h6+20, CT1_h6+26, CT2_h6+22},	{CT0_h6+19, CT1_h6+18, CT2_h6+36},	{CT0_h6+20, CT1_h6+18, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+25},	{CT0_h6+19, CT1_h6+18, CT2_h6+28},	{CT0_h6+21, CT1_h6+17, CT2_h6+25},	{CT0_h6+19, CT1_h6+19, CT2_h6+30},	{CT0_h6+20, CT1_h6+15, CT2_h6+30},	{CT0_h6+17, CT1_h6+17, CT2_h6+36},	{CT0_h6+19, CT1_h6+25, CT2_h6+28},	{CT0_h6+19, CT1_h6+18, CT2_h6+25},	{CT0_h6+20, CT1_h6+19, CT2_h6+22},	{CT0_h6+18, CT1_h6+18, CT2_h6+36},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+21, CT1_h6+27, CT2_h6+11},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+20, CT1_h6+16, CT2_h6+3},	{CT0_h6+20, CT1_h6+19, CT2_h6+12},	{CT0_h6+19, CT1_h6+28, CT2_h6+2},	{CT0_h6+18, CT1_h6+17, CT2_h6+10},	{CT0_h6+19, CT1_h6+18, CT2_h6+13},	{CT0_h6+20, CT1_h6+18, CT2_h6+10},	{CT0_h6+21, CT1_h6+17, CT2_h6+12},	{CT0_h6+19, CT1_h6+24, CT2_h6+28},	{CT0_h6+19, CT1_h6+17, CT2_h6+27},	{CT0_h6+20, CT1_h6+16, CT2_h6+27},	{CT0_h6+16, CT1_h6+2, CT2_h6+32},	{CT0_h6+19, CT1_h6+20, CT2_h6+32},	{CT0_h6+18, CT1_h6+3, CT2_h6+32},	{CT0_h6+19, CT1_h6+33, CT2_h6+27},	{CT0_h6+20, CT1_h6+36, CT2_h6+27},	{CT0_h6+17, CT1_h6+17, CT2_h6+11},	{CT0_h6+19, CT1_h6+31, CT2_h6+30},	{CT0_h6+19, CT1_h6+22, CT2_h6+12},	{CT0_h6+17, CT1_h6+15, CT2_h6+40},	{CT0_h6+19, CT1_h6+17, CT2_h6+10},	{CT0_h6+18, CT1_h6+26, CT2_h6+13},	{CT0_h6+19, CT1_h6+17, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+27},	{CT0_h6+15, CT1_h6+20, CT2_h6+27},	{CT0_h6+17, CT1_h6+30, CT2_h6+32},	{CT0_h6+21, CT1_h6+19, CT2_h6+32},	{CT0_h6+19, CT1_h6+18, CT2_h6+32},

				

				/*Novo*/
				/*T1 = ideal para RDM ou Alloc*/
				{CT0_h1+34, CT1_h1+6, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+4, CT2_h1+1},	{CT0_h1+33, CT1_h1+3, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+5, CT2_h1+3},	{CT0_h1+34, CT1_h1+7, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+35, CT1_h1+4, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+33, CT1_h1+5, CT2_h1+1},	{CT0_h1+35, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+2, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+36, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+32, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+2},	{CT0_h1+34, CT1_h1+1, CT2_h1+2},	{CT0_h1+36, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+35, CT1_h1+7, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+8, CT2_h1+2},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+3, CT2_h1+2},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+36, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+31, CT1_h1+6, CT2_h1+3},	{CT0_h1+34, CT1_h1+5, CT2_h1+0},	{CT0_h1+33, CT1_h1+9, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+35, CT1_h1+6, CT2_h1+2},	{CT0_h1+32, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+32, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+30, CT1_h1+7, CT2_h1+4},	{CT0_h1+32, CT1_h1+6, CT2_h1+2},	{CT0_h1+36, CT1_h1+2, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},

				/*Novo*/
				/*T2 = ideal para Alloc Somente*/
				{CT0_h2+11, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+11, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+3, CT2_h2+23},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+11, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+4, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+23},	{CT0_h2+12, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+2, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+11, CT1_h2+6, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+1, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+26},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+13, CT1_h2+7, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+8, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+12, CT1_h2+3, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+26},	{CT0_h2+11, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+21},	{CT0_h2+10, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+9, CT2_h2+23},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+25},	{CT0_h2+11, CT1_h2+5, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+7, CT2_h2+22},	{CT0_h2+14, CT1_h2+5, CT2_h2+24},	{CT0_h2+11, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+7, CT2_h2+20},	{CT0_h2+12, CT1_h2+6, CT2_h2+22},	{CT0_h2+11, CT1_h2+2, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},

				/*Novo*/
				/*T3 = ideal para Alloc Somente*/
				{CT0_h3+6, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+11, CT2_h3+25},	{CT0_h3+3, CT1_h3+12, CT2_h3+23},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+23},	{CT0_h3+4, CT1_h3+12, CT2_h3+25},	{CT0_h3+2, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+6, CT1_h3+12, CT2_h3+23},	{CT0_h3+1, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+26},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+8, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+3, CT1_h3+12, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+26},	{CT0_h3+5, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+21},	{CT0_h3+5, CT1_h3+10, CT2_h3+24},	{CT0_h3+9, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+25},	{CT0_h3+5, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+7, CT1_h3+12, CT2_h3+22},	{CT0_h3+5, CT1_h3+14, CT2_h3+24},	{CT0_h3+6, CT1_h3+11, CT2_h3+23},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+14, CT2_h3+20},	{CT0_h3+6, CT1_h3+12, CT2_h3+22},	{CT0_h3+2, CT1_h3+11, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},

				
				/*Novo*/
				/*T4 = ideal para MAM*/
				{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+27, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+28, CT2_h4+22},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+24, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+30, CT1_h4+26, CT2_h4+23},	{CT0_h4+28, CT1_h4+28, CT2_h4+21},	{CT0_h4+29, CT1_h4+24, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+23},	{CT0_h4+27, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+30, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+25, CT1_h4+28, CT2_h4+22},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+19},	{CT0_h4+29, CT1_h4+27, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+26, CT1_h4+24, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+23},	{CT0_h4+27, CT1_h4+29, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+25, CT2_h4+21},	{CT0_h4+24, CT1_h4+29, CT2_h4+23},	{CT0_h4+26, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},
			
				/*//Novo*/
				/*T5 = ideal para MAM*/
				{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+14, CT2_h5+17},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+25, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+19, CT2_h5+60},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+60},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+5, CT2_h5+60},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+22, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+32, CT1_h5+28, CT2_h5+18},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+10},	{CT0_h5+27, CT1_h5+36, CT2_h5+80},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+40},

				/*Novo*/
				/*T6 = ideal para MAM*/
				{CT0_h6+19, CT1_h6+30, CT2_h6+30},	{CT0_h6+20, CT1_h6+16, CT2_h6+20},	{CT0_h6+19, CT1_h6+24, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+18, CT1_h6+16, CT2_h6+27},	{CT0_h6+19, CT1_h6+19, CT2_h6+29},	{CT0_h6+18, CT1_h6+17, CT2_h6+32},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+19, CT1_h6+18, CT2_h6+27},	{CT0_h6+20, CT1_h6+23, CT2_h6+29},	{CT0_h6+19, CT1_h6+18, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+9},	{CT0_h6+19, CT1_h6+18, CT2_h6+12},	{CT0_h6+20, CT1_h6+17, CT2_h6+8},	{CT0_h6+20, CT1_h6+22, CT2_h6+10},	{CT0_h6+19, CT1_h6+15, CT2_h6+13},	{CT0_h6+18, CT1_h6+17, CT2_h6+11},	{CT0_h6+20, CT1_h6+26, CT2_h6+22},	{CT0_h6+19, CT1_h6+18, CT2_h6+36},	{CT0_h6+20, CT1_h6+18, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+25},	{CT0_h6+19, CT1_h6+18, CT2_h6+28},	{CT0_h6+21, CT1_h6+17, CT2_h6+25},	{CT0_h6+19, CT1_h6+19, CT2_h6+30},	{CT0_h6+20, CT1_h6+15, CT2_h6+30},	{CT0_h6+17, CT1_h6+17, CT2_h6+36},	{CT0_h6+19, CT1_h6+25, CT2_h6+28},	{CT0_h6+19, CT1_h6+18, CT2_h6+25},	{CT0_h6+20, CT1_h6+19, CT2_h6+22},	{CT0_h6+18, CT1_h6+18, CT2_h6+36},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+21, CT1_h6+27, CT2_h6+11},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+20, CT1_h6+16, CT2_h6+3},	{CT0_h6+20, CT1_h6+19, CT2_h6+12},	{CT0_h6+19, CT1_h6+28, CT2_h6+2},	{CT0_h6+18, CT1_h6+17, CT2_h6+10},	{CT0_h6+19, CT1_h6+18, CT2_h6+13},	{CT0_h6+20, CT1_h6+18, CT2_h6+10},	{CT0_h6+21, CT1_h6+17, CT2_h6+12},	{CT0_h6+19, CT1_h6+24, CT2_h6+28},	{CT0_h6+19, CT1_h6+17, CT2_h6+27},	{CT0_h6+20, CT1_h6+16, CT2_h6+27},	{CT0_h6+16, CT1_h6+2, CT2_h6+32},	{CT0_h6+19, CT1_h6+20, CT2_h6+32},	{CT0_h6+18, CT1_h6+3, CT2_h6+32},	{CT0_h6+19, CT1_h6+33, CT2_h6+27},	{CT0_h6+20, CT1_h6+36, CT2_h6+27},	{CT0_h6+17, CT1_h6+17, CT2_h6+11},	{CT0_h6+19, CT1_h6+31, CT2_h6+30},	{CT0_h6+19, CT1_h6+22, CT2_h6+12},	{CT0_h6+17, CT1_h6+15, CT2_h6+40},	{CT0_h6+19, CT1_h6+17, CT2_h6+10},	{CT0_h6+18, CT1_h6+26, CT2_h6+13},	{CT0_h6+19, CT1_h6+17, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+27},	{CT0_h6+15, CT1_h6+20, CT2_h6+27},	{CT0_h6+17, CT1_h6+30, CT2_h6+32},	{CT0_h6+21, CT1_h6+19, CT2_h6+32},	{CT0_h6+19, CT1_h6+18, CT2_h6+32},

				

				/*Novo*/
				/*T1 = ideal para RDM ou Alloc*/
				{CT0_h1+34, CT1_h1+6, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+4, CT2_h1+1},	{CT0_h1+33, CT1_h1+3, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+35, CT1_h1+5, CT2_h1+3},	{CT0_h1+34, CT1_h1+7, CT2_h1+1},	{CT0_h1+35, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+35, CT1_h1+4, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+4, CT2_h1+2},	{CT0_h1+33, CT1_h1+5, CT2_h1+1},	{CT0_h1+35, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+2, CT2_h1+2},	{CT0_h1+35, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+3},	{CT0_h1+36, CT1_h1+6, CT2_h1+4},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+32, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+3},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+2},	{CT0_h1+34, CT1_h1+1, CT2_h1+2},	{CT0_h1+36, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+35, CT1_h1+7, CT2_h1+3},	{CT0_h1+35, CT1_h1+7, CT2_h1+1},	{CT0_h1+34, CT1_h1+8, CT2_h1+2},	{CT0_h1+33, CT1_h1+4, CT2_h1+2},	{CT0_h1+34, CT1_h1+3, CT2_h1+2},	{CT0_h1+35, CT1_h1+1, CT2_h1+4},	{CT0_h1+36, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+35, CT1_h1+8, CT2_h1+2},	{CT0_h1+31, CT1_h1+6, CT2_h1+3},	{CT0_h1+34, CT1_h1+5, CT2_h1+0},	{CT0_h1+33, CT1_h1+9, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+35, CT1_h1+6, CT2_h1+2},	{CT0_h1+32, CT1_h1+5, CT2_h1+1},	{CT0_h1+34, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+0},	{CT0_h1+32, CT1_h1+7, CT2_h1+2},	{CT0_h1+34, CT1_h1+5, CT2_h1+4},	{CT0_h1+33, CT1_h1+6, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},	{CT0_h1+34, CT1_h1+6, CT2_h1+2},	{CT0_h1+30, CT1_h1+7, CT2_h1+4},	{CT0_h1+32, CT1_h1+6, CT2_h1+2},	{CT0_h1+36, CT1_h1+2, CT2_h1+1},	{CT0_h1+34, CT1_h1+5, CT2_h1+2},

				/*Novo*/
				/*T2 = ideal para Alloc Somente*/
				{CT0_h2+11, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+11, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+3, CT2_h2+23},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+11, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+4, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+4, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+23},	{CT0_h2+12, CT1_h2+4, CT2_h2+25},	{CT0_h2+12, CT1_h2+2, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+5, CT2_h2+24},	{CT0_h2+14, CT1_h2+6, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+11, CT1_h2+6, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+13, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+1, CT2_h2+24},	{CT0_h2+11, CT1_h2+5, CT2_h2+26},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+13, CT1_h2+7, CT2_h2+25},	{CT0_h2+11, CT1_h2+7, CT2_h2+25},	{CT0_h2+12, CT1_h2+8, CT2_h2+24},	{CT0_h2+12, CT1_h2+4, CT2_h2+23},	{CT0_h2+12, CT1_h2+3, CT2_h2+24},	{CT0_h2+14, CT1_h2+1, CT2_h2+25},	{CT0_h2+12, CT1_h2+7, CT2_h2+26},	{CT0_h2+11, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+8, CT2_h2+25},	{CT0_h2+13, CT1_h2+6, CT2_h2+21},	{CT0_h2+10, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+9, CT2_h2+23},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+25},	{CT0_h2+11, CT1_h2+5, CT2_h2+22},	{CT0_h2+12, CT1_h2+7, CT2_h2+24},	{CT0_h2+10, CT1_h2+6, CT2_h2+24},	{CT0_h2+12, CT1_h2+7, CT2_h2+22},	{CT0_h2+14, CT1_h2+5, CT2_h2+24},	{CT0_h2+11, CT1_h2+6, CT2_h2+23},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},	{CT0_h2+12, CT1_h2+6, CT2_h2+24},	{CT0_h2+14, CT1_h2+7, CT2_h2+20},	{CT0_h2+12, CT1_h2+6, CT2_h2+22},	{CT0_h2+11, CT1_h2+2, CT2_h2+26},	{CT0_h2+12, CT1_h2+5, CT2_h2+24},

				/*Novo*/
				/*T3 = ideal para Alloc Somente*/
				{CT0_h3+6, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+11, CT2_h3+25},	{CT0_h3+3, CT1_h3+12, CT2_h3+23},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+4, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+23},	{CT0_h3+4, CT1_h3+12, CT2_h3+25},	{CT0_h3+2, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+13, CT2_h3+24},	{CT0_h3+6, CT1_h3+14, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+13, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+6, CT1_h3+12, CT2_h3+23},	{CT0_h3+1, CT1_h3+12, CT2_h3+24},	{CT0_h3+5, CT1_h3+11, CT2_h3+26},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+13, CT2_h3+25},	{CT0_h3+7, CT1_h3+11, CT2_h3+25},	{CT0_h3+8, CT1_h3+12, CT2_h3+24},	{CT0_h3+4, CT1_h3+12, CT2_h3+23},	{CT0_h3+3, CT1_h3+12, CT2_h3+24},	{CT0_h3+1, CT1_h3+14, CT2_h3+25},	{CT0_h3+7, CT1_h3+12, CT2_h3+26},	{CT0_h3+5, CT1_h3+11, CT2_h3+24},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+8, CT1_h3+12, CT2_h3+25},	{CT0_h3+6, CT1_h3+13, CT2_h3+21},	{CT0_h3+5, CT1_h3+10, CT2_h3+24},	{CT0_h3+9, CT1_h3+12, CT2_h3+23},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+25},	{CT0_h3+5, CT1_h3+11, CT2_h3+22},	{CT0_h3+7, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+10, CT2_h3+24},	{CT0_h3+7, CT1_h3+12, CT2_h3+22},	{CT0_h3+5, CT1_h3+14, CT2_h3+24},	{CT0_h3+6, CT1_h3+11, CT2_h3+23},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},	{CT0_h3+6, CT1_h3+12, CT2_h3+24},	{CT0_h3+7, CT1_h3+14, CT2_h3+20},	{CT0_h3+6, CT1_h3+12, CT2_h3+22},	{CT0_h3+2, CT1_h3+11, CT2_h3+26},	{CT0_h3+5, CT1_h3+12, CT2_h3+24},

				
				/*Novo*/
				/*T4 = ideal para MAM*/
				{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+27, CT1_h4+25, CT2_h4+21},	{CT0_h4+28, CT1_h4+28, CT2_h4+22},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+26, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+24, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+20},	{CT0_h4+29, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+30, CT1_h4+26, CT2_h4+23},	{CT0_h4+28, CT1_h4+28, CT2_h4+21},	{CT0_h4+29, CT1_h4+24, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+23},	{CT0_h4+27, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+22},	{CT0_h4+29, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+29, CT1_h4+27, CT2_h4+23},	{CT0_h4+30, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+29, CT1_h4+25, CT2_h4+21},	{CT0_h4+25, CT1_h4+28, CT2_h4+22},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+27, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+27, CT2_h4+19},	{CT0_h4+29, CT1_h4+27, CT2_h4+21},	{CT0_h4+26, CT1_h4+26, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+19},	{CT0_h4+26, CT1_h4+24, CT2_h4+21},	{CT0_h4+28, CT1_h4+26, CT2_h4+23},	{CT0_h4+27, CT1_h4+29, CT2_h4+20},	{CT0_h4+28, CT1_h4+26, CT2_h4+21},	{CT0_h4+28, CT1_h4+25, CT2_h4+21},	{CT0_h4+24, CT1_h4+29, CT2_h4+23},	{CT0_h4+26, CT1_h4+26, CT2_h4+21},	{CT0_h4+30, CT1_h4+28, CT2_h4+20},	{CT0_h4+28, CT1_h4+27, CT2_h4+21},
			
				/*//Novo*/
				/*T5 = ideal para MAM*/
				{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+14, CT2_h5+17},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+25, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+19, CT2_h5+60},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+60},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+28, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+5, CT2_h5+60},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+30, CT1_h5+25, CT2_h5+20},	{CT0_h5+22, CT1_h5+30, CT2_h5+80},	{CT0_h5+31, CT1_h5+22, CT2_h5+40},	{CT0_h5+21, CT1_h5+36, CT2_h5+40},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+32, CT1_h5+25, CT2_h5+20},	{CT0_h5+29, CT1_h5+28, CT2_h5+15},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+31, CT1_h5+28, CT2_h5+17},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+29, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+28, CT1_h5+33, CT2_h5+60},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+27, CT1_h5+36, CT2_h5+40},	{CT0_h5+32, CT1_h5+28, CT2_h5+18},	{CT0_h5+32, CT1_h5+22, CT2_h5+40},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+27, CT1_h5+36, CT2_h5+10},	{CT0_h5+27, CT1_h5+36, CT2_h5+80},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+20},	{CT0_h5+32, CT1_h5+36, CT2_h5+40},

				/*Novo*/
				/*T6 = ideal para MAM*/
				{CT0_h6+19, CT1_h6+30, CT2_h6+30},	{CT0_h6+20, CT1_h6+16, CT2_h6+20},	{CT0_h6+19, CT1_h6+24, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+18, CT1_h6+16, CT2_h6+27},	{CT0_h6+19, CT1_h6+19, CT2_h6+29},	{CT0_h6+18, CT1_h6+17, CT2_h6+32},	{CT0_h6+20, CT1_h6+17, CT2_h6+31},	{CT0_h6+19, CT1_h6+18, CT2_h6+27},	{CT0_h6+20, CT1_h6+23, CT2_h6+29},	{CT0_h6+19, CT1_h6+18, CT2_h6+11},	{CT0_h6+20, CT1_h6+17, CT2_h6+9},	{CT0_h6+19, CT1_h6+18, CT2_h6+12},	{CT0_h6+20, CT1_h6+17, CT2_h6+8},	{CT0_h6+20, CT1_h6+22, CT2_h6+10},	{CT0_h6+19, CT1_h6+15, CT2_h6+13},	{CT0_h6+18, CT1_h6+17, CT2_h6+11},	{CT0_h6+20, CT1_h6+26, CT2_h6+22},	{CT0_h6+19, CT1_h6+18, CT2_h6+36},	{CT0_h6+20, CT1_h6+18, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+25},	{CT0_h6+19, CT1_h6+18, CT2_h6+28},	{CT0_h6+21, CT1_h6+17, CT2_h6+25},	{CT0_h6+19, CT1_h6+19, CT2_h6+30},	{CT0_h6+20, CT1_h6+15, CT2_h6+30},	{CT0_h6+17, CT1_h6+17, CT2_h6+36},	{CT0_h6+19, CT1_h6+25, CT2_h6+28},	{CT0_h6+19, CT1_h6+18, CT2_h6+25},	{CT0_h6+20, CT1_h6+19, CT2_h6+22},	{CT0_h6+18, CT1_h6+18, CT2_h6+36},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+21, CT1_h6+27, CT2_h6+11},	{CT0_h6+19, CT1_h6+17, CT2_h6+12},	{CT0_h6+20, CT1_h6+16, CT2_h6+3},	{CT0_h6+20, CT1_h6+19, CT2_h6+12},	{CT0_h6+19, CT1_h6+28, CT2_h6+2},	{CT0_h6+18, CT1_h6+17, CT2_h6+10},	{CT0_h6+19, CT1_h6+18, CT2_h6+13},	{CT0_h6+20, CT1_h6+18, CT2_h6+10},	{CT0_h6+21, CT1_h6+17, CT2_h6+12},	{CT0_h6+19, CT1_h6+24, CT2_h6+28},	{CT0_h6+19, CT1_h6+17, CT2_h6+27},	{CT0_h6+20, CT1_h6+16, CT2_h6+27},	{CT0_h6+16, CT1_h6+2, CT2_h6+32},	{CT0_h6+19, CT1_h6+20, CT2_h6+32},	{CT0_h6+18, CT1_h6+3, CT2_h6+32},	{CT0_h6+19, CT1_h6+33, CT2_h6+27},	{CT0_h6+20, CT1_h6+36, CT2_h6+27},	{CT0_h6+17, CT1_h6+17, CT2_h6+11},	{CT0_h6+19, CT1_h6+31, CT2_h6+30},	{CT0_h6+19, CT1_h6+22, CT2_h6+12},	{CT0_h6+17, CT1_h6+15, CT2_h6+40},	{CT0_h6+19, CT1_h6+17, CT2_h6+10},	{CT0_h6+18, CT1_h6+26, CT2_h6+13},	{CT0_h6+19, CT1_h6+17, CT2_h6+28},	{CT0_h6+19, CT1_h6+25, CT2_h6+27},	{CT0_h6+15, CT1_h6+20, CT2_h6+27},	{CT0_h6+17, CT1_h6+30, CT2_h6+32},	{CT0_h6+21, CT1_h6+19, CT2_h6+32},	{CT0_h6+19, CT1_h6+18, CT2_h6+32},

				

				
				
				
				{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0}

				};
		
		
				
			if (dados == null) {
				dados = new No();
				dados.item = 0;
				Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0,7,7);
				rodada.schedulep(3, 0, dados);

			} else {

				int slot = (int) dados.item;
				/*if(slot%5==4)
				{
					try {
						if(rodada.estatistica.devolucoes(300)>=5||rodada.estatistica.preempcoes(300)>=5)
						{
							Lsp LSPaux= new Lsp(); 
							LSPaux.Carga=0; 
							to.link[0].bamType = BAMType.PreemptionGBAM;
							to.link[0].BCLTH= new double[]
							{	000, //BC0 
								000, //BC1
								0  //BC2 Nunca mudar
							};
							LSPaux.CT=0; 
				      		BAM.devolutionG(to.link[0],LSPaux);
							
							
							to.link[0].BCHTL= new double[]
							{	0, //BC0 Nunca mudar
								000, //BC1
								000 //BC2
							};
							
							LSPaux.CT=2; 
				      		BAM.preemptionG(to.link[0],LSPaux); 
							
						}
						else if(rodada.estatistica.picoDeUtilizacaoDoEnlace(300,to.link[0])<=800)
						{
							to.link[0].bamType = BAMType.PreemptionGBAM;
							to.link[0].BCLTH= new double[]
							{	100, //BC0 
								100, //BC1
								0  //BC2 Nunca mudar
							};
							
							to.link[0].BCHTL= new double[]
							{	0, //BC0 Nunca mudar
								100, //BC
								100 //BC2
							};
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RrdException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				
				}
				*/

				for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {

					for (int i = 0; i < matrizA[slot][ct]; i++) {
						No dadosLSP = new No();

						Lsp lsp = new Lsp(rodada);
						lsp.CargaReduzida = 0;
						lsp.src = 0; // id do router fonte
						lsp.dest = 1; // id do router destino
						lsp.CT = ct;
						lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);   //10
						lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.uniform(100, 140);//
						dadosLSP.item = lsp;

						Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
								+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
								+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao(),7,7);
						rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);

					}
				}
				Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot,7,7);
				dados.item = slot + 1;
				rodada.schedulep(3, 60, dados);

			}

		}

	
	
	
		
	public static void trafegoForcadoNSF(RodadaDeSimulacao rodada, Topologia to, No dados) {
		int fonteDeTrafego = 3;
		
		final int b = 2-1;  // baixo
		final int m = 14-4;  //médio
		final int a = 20;  //alto
		final int ma = 38+2;  //muito alto
		
		
		final int CT0_h1  =	a	;
		final int CT1_h1  =	b	;
		final int CT2_h1  =	b	;
				
		final int CT0_h2  =	ma	;
		final int CT1_h2  =	ma	;
		final int CT2_h2  =	ma	;
		   		
		final int CT0_h3  =	a	;
		final int CT1_h3  =	b	;
		final int CT2_h3  =	m	;
		   		
		final int CT0_h4  =	ma	;
		final int CT1_h4  =	m	;
		final int CT2_h4  =	ma	;
		   		
		final int CT0_h5  =	m	;
		final int CT1_h5  =	b	;
		final int CT2_h5  =	a	;
		   		
		final int CT0_h6  =	a	;
		final int CT1_h6  =	ma	;
		final int CT2_h6  =	a	;
		   		
		final int CT0_h7  =	m	;
		final int CT1_h7  =	a	;
		final int CT2_h7  =	b	;
		   		
		final int CT0_h8  =	ma	;
		final int CT1_h8  =	ma	;
		final int CT2_h8  =	a	;
		   		
		final int CT0_h9  =	b	;
		final int CT1_h9  =	a	;
		final int CT2_h9  =	b	;
		   		
		final int CT0_h10  =	ma	;
		final int CT1_h10  =	ma	;
		final int CT2_h10  =	ma	;
		   		
		final int CT0_h11  =	b	;
		final int CT1_h11  =	ma	;
		final int CT2_h11  =	b	;
		   		
		final int CT0_h12  =	ma	;
		final int CT1_h12  =	a	;
		final int CT2_h12  =	ma	;
		   		
		final int CT0_h13  =	ma	;
		final int CT1_h13  =	b	;
		final int CT2_h13  =	b	;
		   		
		final int CT0_h14  =	m	;
		final int CT1_h14  =	ma	;
		final int CT2_h14  =	ma	;
		   		
		final int CT0_h15  =	b	;
		final int CT1_h15  =	a	;
		final int CT2_h15  =	m	;
		   		
		final int CT0_h16  =	ma	;
		final int CT1_h16  =	ma	;
		final int CT2_h16  =	ma	;
		   		
		final int CT0_h17  =	a	;
		final int CT1_h17  =	m	;
		final int CT2_h17  =	b	;
		   		
		final int CT0_h18  =	ma	;
		final int CT1_h18  =	a	;
		final int CT2_h18  =	a	;
		   		
		final int CT0_h19  =	b	;
		final int CT1_h19  =	m	;
		final int CT2_h19  =	a	;
		   		
		final int CT0_h20  =	a	;
		final int CT1_h20  =	ma	;
		final int CT2_h20  =	ma	;
		   		
		final int CT0_h21  =	b	;
		final int CT1_h21  =	b	;
		final int CT2_h21  =	a	;
		   		
		final int CT0_h22  =	a	;
		final int CT1_h22  =	a	;
		final int CT2_h22  =	ma	;
		   		
		final int CT0_h23  =	b	;
		final int CT1_h23  =	b	;
		final int CT2_h23  =	ma	;
		   		
		final int CT0_h24  =	ma	;
		final int CT1_h24  =	ma	;
		final int CT2_h24  =	m	;
		final int [][]matrizB =
			{ 		
				{CT0_h1+0, CT1_h1+3, CT2_h1+4},{CT0_h1+1, CT1_h1+2, CT2_h1+3},{CT0_h1+2, CT1_h1+1, CT2_h1+2},{CT0_h1+3, CT1_h1+4, CT2_h1+4},{CT0_h1+2, CT1_h1+3, CT2_h1+2},{CT0_h1+4, CT1_h1+2, CT2_h1+2},{CT0_h1+2, CT1_h1+1, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+0},{CT0_h1+3, CT1_h1+3, CT2_h1+0},{CT0_h1+2, CT1_h1+2, CT2_h1+0},{CT0_h1+4, CT1_h1+1, CT2_h1+1},{CT0_h1+2, CT1_h1+4, CT2_h1+2},{CT0_h1+2, CT1_h1+3, CT2_h1+3},{CT0_h1+2, CT1_h1+2, CT2_h1+5},{CT0_h1+1, CT1_h1+4, CT2_h1+0},{CT0_h1+4, CT1_h1+2, CT2_h1+4},{CT0_h1+2, CT1_h1+2, CT2_h1+5},{CT0_h1+3, CT1_h1+2, CT2_h1+2},{CT0_h1+2, CT1_h1+1, CT2_h1+4},{CT0_h1+1, CT1_h1+4, CT2_h1+2},{CT0_h1+4, CT1_h1+2, CT2_h1+3},{CT0_h1+2, CT1_h1+3, CT2_h1+2},{CT0_h1+3, CT1_h1+2, CT2_h1+4},{CT0_h1+2, CT1_h1+1, CT2_h1+0},{CT0_h1+1, CT1_h1+4, CT2_h1+4},{CT0_h1+4, CT1_h1+2, CT2_h1+3},{CT0_h1+3, CT1_h1+3, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+4},{CT0_h1+1, CT1_h1+5, CT2_h1+2},{CT0_h1+2, CT1_h1+0, CT2_h1+2},{CT0_h1+3, CT1_h1+4, CT2_h1+2},{CT0_h1+2, CT1_h1+5, CT2_h1+0},{CT0_h1+1, CT1_h1+2, CT2_h1+0},{CT0_h1+4, CT1_h1+4, CT2_h1+0},{CT0_h1+3, CT1_h1+2, CT2_h1+1},{CT0_h1+2, CT1_h1+2, CT2_h1+2},{CT0_h1+1, CT1_h1+2, CT2_h1+3},{CT0_h1+4, CT1_h1+1, CT2_h1+5},{CT0_h1+3, CT1_h1+4, CT2_h1+0},{CT0_h1+2, CT1_h1+2, CT2_h1+4},{CT0_h1+1, CT1_h1+3, CT2_h1+3},{CT0_h1+2, CT1_h1+2, CT2_h1+2},{CT0_h1+3, CT1_h1+1, CT2_h1+4},{CT0_h1+2, CT1_h1+4, CT2_h1+2},{CT0_h1+4, CT1_h1+2, CT2_h1+2},{CT0_h1+4, CT1_h1+3, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+0},{CT0_h1+2, CT1_h1+5, CT2_h1+0},{CT0_h1+2, CT1_h1+4, CT2_h1+0},{CT0_h1+1, CT1_h1+5, CT2_h1+1},{CT0_h1+1, CT1_h1+1, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+3},{CT0_h1+4, CT1_h1+1, CT2_h1+2},{CT0_h1+3, CT1_h1+2, CT2_h1+1},{CT0_h1+2, CT1_h1+4, CT2_h1+4},{CT0_h1+2, CT1_h1+3, CT2_h1+3},{CT0_h1+3, CT1_h1+2, CT2_h1+2},{CT0_h1+2, CT1_h1+2, CT2_h1+1},{CT0_h1+1, CT1_h1+3, CT2_h1+2},{CT0_h1+0, CT1_h1+0, CT2_h1+0},
				{CT0_h2+0, CT1_h2+3, CT2_h2+4},{CT0_h2+1, CT1_h2+2, CT2_h2+3},{CT0_h2+2, CT1_h2+1, CT2_h2+2},{CT0_h2+3, CT1_h2+4, CT2_h2+4},{CT0_h2+2, CT1_h2+3, CT2_h2+2},{CT0_h2+4, CT1_h2+2, CT2_h2+2},{CT0_h2+2, CT1_h2+1, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+0},{CT0_h2+3, CT1_h2+3, CT2_h2+0},{CT0_h2+2, CT1_h2+2, CT2_h2+0},{CT0_h2+4, CT1_h2+1, CT2_h2+1},{CT0_h2+2, CT1_h2+4, CT2_h2+2},{CT0_h2+2, CT1_h2+3, CT2_h2+3},{CT0_h2+2, CT1_h2+2, CT2_h2+5},{CT0_h2+1, CT1_h2+4, CT2_h2+0},{CT0_h2+4, CT1_h2+2, CT2_h2+4},{CT0_h2+2, CT1_h2+2, CT2_h2+5},{CT0_h2+3, CT1_h2+2, CT2_h2+2},{CT0_h2+2, CT1_h2+1, CT2_h2+4},{CT0_h2+1, CT1_h2+4, CT2_h2+2},{CT0_h2+4, CT1_h2+2, CT2_h2+3},{CT0_h2+2, CT1_h2+3, CT2_h2+2},{CT0_h2+3, CT1_h2+2, CT2_h2+4},{CT0_h2+2, CT1_h2+1, CT2_h2+0},{CT0_h2+1, CT1_h2+4, CT2_h2+4},{CT0_h2+4, CT1_h2+2, CT2_h2+3},{CT0_h2+3, CT1_h2+3, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+4},{CT0_h2+1, CT1_h2+5, CT2_h2+2},{CT0_h2+2, CT1_h2+0, CT2_h2+2},{CT0_h2+3, CT1_h2+4, CT2_h2+2},{CT0_h2+2, CT1_h2+5, CT2_h2+0},{CT0_h2+1, CT1_h2+2, CT2_h2+0},{CT0_h2+4, CT1_h2+4, CT2_h2+0},{CT0_h2+3, CT1_h2+2, CT2_h2+1},{CT0_h2+2, CT1_h2+2, CT2_h2+2},{CT0_h2+1, CT1_h2+2, CT2_h2+3},{CT0_h2+4, CT1_h2+1, CT2_h2+5},{CT0_h2+3, CT1_h2+4, CT2_h2+0},{CT0_h2+2, CT1_h2+2, CT2_h2+4},{CT0_h2+1, CT1_h2+3, CT2_h2+3},{CT0_h2+2, CT1_h2+2, CT2_h2+2},{CT0_h2+3, CT1_h2+1, CT2_h2+4},{CT0_h2+2, CT1_h2+4, CT2_h2+2},{CT0_h2+4, CT1_h2+2, CT2_h2+2},{CT0_h2+4, CT1_h2+3, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+0},{CT0_h2+2, CT1_h2+5, CT2_h2+0},{CT0_h2+2, CT1_h2+4, CT2_h2+0},{CT0_h2+1, CT1_h2+5, CT2_h2+1},{CT0_h2+1, CT1_h2+1, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+3},{CT0_h2+4, CT1_h2+1, CT2_h2+2},{CT0_h2+3, CT1_h2+2, CT2_h2+1},{CT0_h2+2, CT1_h2+4, CT2_h2+4},{CT0_h2+2, CT1_h2+3, CT2_h2+3},{CT0_h2+3, CT1_h2+2, CT2_h2+2},{CT0_h2+2, CT1_h2+2, CT2_h2+1},{CT0_h2+1, CT1_h2+3, CT2_h2+2},{CT0_h2+0, CT1_h2+0, CT2_h2+0},
				{CT0_h3+0, CT1_h3+3, CT2_h3+4},{CT0_h3+1, CT1_h3+2, CT2_h3+3},{CT0_h3+2, CT1_h3+1, CT2_h3+2},{CT0_h3+3, CT1_h3+4, CT2_h3+4},{CT0_h3+2, CT1_h3+3, CT2_h3+2},{CT0_h3+4, CT1_h3+2, CT2_h3+2},{CT0_h3+2, CT1_h3+1, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+0},{CT0_h3+3, CT1_h3+3, CT2_h3+0},{CT0_h3+2, CT1_h3+2, CT2_h3+0},{CT0_h3+4, CT1_h3+1, CT2_h3+1},{CT0_h3+2, CT1_h3+4, CT2_h3+2},{CT0_h3+2, CT1_h3+3, CT2_h3+3},{CT0_h3+2, CT1_h3+2, CT2_h3+5},{CT0_h3+1, CT1_h3+4, CT2_h3+0},{CT0_h3+4, CT1_h3+2, CT2_h3+4},{CT0_h3+2, CT1_h3+2, CT2_h3+5},{CT0_h3+3, CT1_h3+2, CT2_h3+2},{CT0_h3+2, CT1_h3+1, CT2_h3+4},{CT0_h3+1, CT1_h3+4, CT2_h3+2},{CT0_h3+4, CT1_h3+2, CT2_h3+3},{CT0_h3+2, CT1_h3+3, CT2_h3+2},{CT0_h3+3, CT1_h3+2, CT2_h3+4},{CT0_h3+2, CT1_h3+1, CT2_h3+0},{CT0_h3+1, CT1_h3+4, CT2_h3+4},{CT0_h3+4, CT1_h3+2, CT2_h3+3},{CT0_h3+3, CT1_h3+3, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+4},{CT0_h3+1, CT1_h3+5, CT2_h3+2},{CT0_h3+2, CT1_h3+0, CT2_h3+2},{CT0_h3+3, CT1_h3+4, CT2_h3+2},{CT0_h3+2, CT1_h3+5, CT2_h3+0},{CT0_h3+1, CT1_h3+2, CT2_h3+0},{CT0_h3+4, CT1_h3+4, CT2_h3+0},{CT0_h3+3, CT1_h3+2, CT2_h3+1},{CT0_h3+2, CT1_h3+2, CT2_h3+2},{CT0_h3+1, CT1_h3+2, CT2_h3+3},{CT0_h3+4, CT1_h3+1, CT2_h3+5},{CT0_h3+3, CT1_h3+4, CT2_h3+0},{CT0_h3+2, CT1_h3+2, CT2_h3+4},{CT0_h3+1, CT1_h3+3, CT2_h3+3},{CT0_h3+2, CT1_h3+2, CT2_h3+2},{CT0_h3+3, CT1_h3+1, CT2_h3+4},{CT0_h3+2, CT1_h3+4, CT2_h3+2},{CT0_h3+4, CT1_h3+2, CT2_h3+2},{CT0_h3+4, CT1_h3+3, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+0},{CT0_h3+2, CT1_h3+5, CT2_h3+0},{CT0_h3+2, CT1_h3+4, CT2_h3+0},{CT0_h3+1, CT1_h3+5, CT2_h3+1},{CT0_h3+1, CT1_h3+1, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+3},{CT0_h3+4, CT1_h3+1, CT2_h3+2},{CT0_h3+3, CT1_h3+2, CT2_h3+1},{CT0_h3+2, CT1_h3+4, CT2_h3+4},{CT0_h3+2, CT1_h3+3, CT2_h3+3},{CT0_h3+3, CT1_h3+2, CT2_h3+2},{CT0_h3+2, CT1_h3+2, CT2_h3+1},{CT0_h3+1, CT1_h3+3, CT2_h3+2},{CT0_h3+0, CT1_h3+0, CT2_h3+0},
				{CT0_h4+0, CT1_h4+3, CT2_h4+4},{CT0_h4+1, CT1_h4+2, CT2_h4+3},{CT0_h4+2, CT1_h4+1, CT2_h4+2},{CT0_h4+3, CT1_h4+4, CT2_h4+4},{CT0_h4+2, CT1_h4+3, CT2_h4+2},{CT0_h4+4, CT1_h4+2, CT2_h4+2},{CT0_h4+2, CT1_h4+1, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+0},{CT0_h4+3, CT1_h4+3, CT2_h4+0},{CT0_h4+2, CT1_h4+2, CT2_h4+0},{CT0_h4+4, CT1_h4+1, CT2_h4+1},{CT0_h4+2, CT1_h4+4, CT2_h4+2},{CT0_h4+2, CT1_h4+3, CT2_h4+3},{CT0_h4+2, CT1_h4+2, CT2_h4+5},{CT0_h4+1, CT1_h4+4, CT2_h4+0},{CT0_h4+4, CT1_h4+2, CT2_h4+4},{CT0_h4+2, CT1_h4+2, CT2_h4+5},{CT0_h4+3, CT1_h4+2, CT2_h4+2},{CT0_h4+2, CT1_h4+1, CT2_h4+4},{CT0_h4+1, CT1_h4+4, CT2_h4+2},{CT0_h4+4, CT1_h4+2, CT2_h4+3},{CT0_h4+2, CT1_h4+3, CT2_h4+2},{CT0_h4+3, CT1_h4+2, CT2_h4+4},{CT0_h4+2, CT1_h4+1, CT2_h4+0},{CT0_h4+1, CT1_h4+4, CT2_h4+4},{CT0_h4+4, CT1_h4+2, CT2_h4+3},{CT0_h4+3, CT1_h4+3, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+4},{CT0_h4+1, CT1_h4+5, CT2_h4+2},{CT0_h4+2, CT1_h4+0, CT2_h4+2},{CT0_h4+3, CT1_h4+4, CT2_h4+2},{CT0_h4+2, CT1_h4+5, CT2_h4+0},{CT0_h4+1, CT1_h4+2, CT2_h4+0},{CT0_h4+4, CT1_h4+4, CT2_h4+0},{CT0_h4+3, CT1_h4+2, CT2_h4+1},{CT0_h4+2, CT1_h4+2, CT2_h4+2},{CT0_h4+1, CT1_h4+2, CT2_h4+3},{CT0_h4+4, CT1_h4+1, CT2_h4+5},{CT0_h4+3, CT1_h4+4, CT2_h4+0},{CT0_h4+2, CT1_h4+2, CT2_h4+4},{CT0_h4+1, CT1_h4+3, CT2_h4+3},{CT0_h4+2, CT1_h4+2, CT2_h4+2},{CT0_h4+3, CT1_h4+1, CT2_h4+4},{CT0_h4+2, CT1_h4+4, CT2_h4+2},{CT0_h4+4, CT1_h4+2, CT2_h4+2},{CT0_h4+4, CT1_h4+3, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+0},{CT0_h4+2, CT1_h4+5, CT2_h4+0},{CT0_h4+2, CT1_h4+4, CT2_h4+0},{CT0_h4+1, CT1_h4+5, CT2_h4+1},{CT0_h4+1, CT1_h4+1, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+3},{CT0_h4+4, CT1_h4+1, CT2_h4+2},{CT0_h4+3, CT1_h4+2, CT2_h4+1},{CT0_h4+2, CT1_h4+4, CT2_h4+4},{CT0_h4+2, CT1_h4+3, CT2_h4+3},{CT0_h4+3, CT1_h4+2, CT2_h4+2},{CT0_h4+2, CT1_h4+2, CT2_h4+1},{CT0_h4+1, CT1_h4+3, CT2_h4+2},{CT0_h4+0, CT1_h4+0, CT2_h4+0},
				{CT0_h5+0, CT1_h5+3, CT2_h5+4},{CT0_h5+1, CT1_h5+2, CT2_h5+3},{CT0_h5+2, CT1_h5+1, CT2_h5+2},{CT0_h5+3, CT1_h5+4, CT2_h5+4},{CT0_h5+2, CT1_h5+3, CT2_h5+2},{CT0_h5+4, CT1_h5+2, CT2_h5+2},{CT0_h5+2, CT1_h5+1, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+0},{CT0_h5+3, CT1_h5+3, CT2_h5+0},{CT0_h5+2, CT1_h5+2, CT2_h5+0},{CT0_h5+4, CT1_h5+1, CT2_h5+1},{CT0_h5+2, CT1_h5+4, CT2_h5+2},{CT0_h5+2, CT1_h5+3, CT2_h5+3},{CT0_h5+2, CT1_h5+2, CT2_h5+5},{CT0_h5+1, CT1_h5+4, CT2_h5+0},{CT0_h5+4, CT1_h5+2, CT2_h5+4},{CT0_h5+2, CT1_h5+2, CT2_h5+5},{CT0_h5+3, CT1_h5+2, CT2_h5+2},{CT0_h5+2, CT1_h5+1, CT2_h5+4},{CT0_h5+1, CT1_h5+4, CT2_h5+2},{CT0_h5+4, CT1_h5+2, CT2_h5+3},{CT0_h5+2, CT1_h5+3, CT2_h5+2},{CT0_h5+3, CT1_h5+2, CT2_h5+4},{CT0_h5+2, CT1_h5+1, CT2_h5+0},{CT0_h5+1, CT1_h5+4, CT2_h5+4},{CT0_h5+4, CT1_h5+2, CT2_h5+3},{CT0_h5+3, CT1_h5+3, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+4},{CT0_h5+1, CT1_h5+5, CT2_h5+2},{CT0_h5+2, CT1_h5+0, CT2_h5+2},{CT0_h5+3, CT1_h5+4, CT2_h5+2},{CT0_h5+2, CT1_h5+5, CT2_h5+0},{CT0_h5+1, CT1_h5+2, CT2_h5+0},{CT0_h5+4, CT1_h5+4, CT2_h5+0},{CT0_h5+3, CT1_h5+2, CT2_h5+1},{CT0_h5+2, CT1_h5+2, CT2_h5+2},{CT0_h5+1, CT1_h5+2, CT2_h5+3},{CT0_h5+4, CT1_h5+1, CT2_h5+5},{CT0_h5+3, CT1_h5+4, CT2_h5+0},{CT0_h5+2, CT1_h5+2, CT2_h5+4},{CT0_h5+1, CT1_h5+3, CT2_h5+3},{CT0_h5+2, CT1_h5+2, CT2_h5+2},{CT0_h5+3, CT1_h5+1, CT2_h5+4},{CT0_h5+2, CT1_h5+4, CT2_h5+2},{CT0_h5+4, CT1_h5+2, CT2_h5+2},{CT0_h5+4, CT1_h5+3, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+0},{CT0_h5+2, CT1_h5+5, CT2_h5+0},{CT0_h5+2, CT1_h5+4, CT2_h5+0},{CT0_h5+1, CT1_h5+5, CT2_h5+1},{CT0_h5+1, CT1_h5+1, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+3},{CT0_h5+4, CT1_h5+1, CT2_h5+2},{CT0_h5+3, CT1_h5+2, CT2_h5+1},{CT0_h5+2, CT1_h5+4, CT2_h5+4},{CT0_h5+2, CT1_h5+3, CT2_h5+3},{CT0_h5+3, CT1_h5+2, CT2_h5+2},{CT0_h5+2, CT1_h5+2, CT2_h5+1},{CT0_h5+1, CT1_h5+3, CT2_h5+2},{CT0_h5+0, CT1_h5+0, CT2_h5+0},
				{CT0_h6+0, CT1_h6+3, CT2_h6+4},{CT0_h6+1, CT1_h6+2, CT2_h6+3},{CT0_h6+2, CT1_h6+1, CT2_h6+2},{CT0_h6+3, CT1_h6+4, CT2_h6+4},{CT0_h6+2, CT1_h6+3, CT2_h6+2},{CT0_h6+4, CT1_h6+2, CT2_h6+2},{CT0_h6+2, CT1_h6+1, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+0},{CT0_h6+3, CT1_h6+3, CT2_h6+0},{CT0_h6+2, CT1_h6+2, CT2_h6+0},{CT0_h6+4, CT1_h6+1, CT2_h6+1},{CT0_h6+2, CT1_h6+4, CT2_h6+2},{CT0_h6+2, CT1_h6+3, CT2_h6+3},{CT0_h6+2, CT1_h6+2, CT2_h6+5},{CT0_h6+1, CT1_h6+4, CT2_h6+0},{CT0_h6+4, CT1_h6+2, CT2_h6+4},{CT0_h6+2, CT1_h6+2, CT2_h6+5},{CT0_h6+3, CT1_h6+2, CT2_h6+2},{CT0_h6+2, CT1_h6+1, CT2_h6+4},{CT0_h6+1, CT1_h6+4, CT2_h6+2},{CT0_h6+4, CT1_h6+2, CT2_h6+3},{CT0_h6+2, CT1_h6+3, CT2_h6+2},{CT0_h6+3, CT1_h6+2, CT2_h6+4},{CT0_h6+2, CT1_h6+1, CT2_h6+0},{CT0_h6+1, CT1_h6+4, CT2_h6+4},{CT0_h6+4, CT1_h6+2, CT2_h6+3},{CT0_h6+3, CT1_h6+3, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+4},{CT0_h6+1, CT1_h6+5, CT2_h6+2},{CT0_h6+2, CT1_h6+0, CT2_h6+2},{CT0_h6+3, CT1_h6+4, CT2_h6+2},{CT0_h6+2, CT1_h6+5, CT2_h6+0},{CT0_h6+1, CT1_h6+2, CT2_h6+0},{CT0_h6+4, CT1_h6+4, CT2_h6+0},{CT0_h6+3, CT1_h6+2, CT2_h6+1},{CT0_h6+2, CT1_h6+2, CT2_h6+2},{CT0_h6+1, CT1_h6+2, CT2_h6+3},{CT0_h6+4, CT1_h6+1, CT2_h6+5},{CT0_h6+3, CT1_h6+4, CT2_h6+0},{CT0_h6+2, CT1_h6+2, CT2_h6+4},{CT0_h6+1, CT1_h6+3, CT2_h6+3},{CT0_h6+2, CT1_h6+2, CT2_h6+2},{CT0_h6+3, CT1_h6+1, CT2_h6+4},{CT0_h6+2, CT1_h6+4, CT2_h6+2},{CT0_h6+4, CT1_h6+2, CT2_h6+2},{CT0_h6+4, CT1_h6+3, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+0},{CT0_h6+2, CT1_h6+5, CT2_h6+0},{CT0_h6+2, CT1_h6+4, CT2_h6+0},{CT0_h6+1, CT1_h6+5, CT2_h6+1},{CT0_h6+1, CT1_h6+1, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+3},{CT0_h6+4, CT1_h6+1, CT2_h6+2},{CT0_h6+3, CT1_h6+2, CT2_h6+1},{CT0_h6+2, CT1_h6+4, CT2_h6+4},{CT0_h6+2, CT1_h6+3, CT2_h6+3},{CT0_h6+3, CT1_h6+2, CT2_h6+2},{CT0_h6+2, CT1_h6+2, CT2_h6+1},{CT0_h6+1, CT1_h6+3, CT2_h6+2},{CT0_h6+0, CT1_h6+0, CT2_h6+0},
				{CT0_h7+0, CT1_h7+3, CT2_h7+4},{CT0_h7+1, CT1_h7+2, CT2_h7+3},{CT0_h7+2, CT1_h7+1, CT2_h7+2},{CT0_h7+3, CT1_h7+4, CT2_h7+4},{CT0_h7+2, CT1_h7+3, CT2_h7+2},{CT0_h7+4, CT1_h7+2, CT2_h7+2},{CT0_h7+2, CT1_h7+1, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+0},{CT0_h7+3, CT1_h7+3, CT2_h7+0},{CT0_h7+2, CT1_h7+2, CT2_h7+0},{CT0_h7+4, CT1_h7+1, CT2_h7+1},{CT0_h7+2, CT1_h7+4, CT2_h7+2},{CT0_h7+2, CT1_h7+3, CT2_h7+3},{CT0_h7+2, CT1_h7+2, CT2_h7+5},{CT0_h7+1, CT1_h7+4, CT2_h7+0},{CT0_h7+4, CT1_h7+2, CT2_h7+4},{CT0_h7+2, CT1_h7+2, CT2_h7+5},{CT0_h7+3, CT1_h7+2, CT2_h7+2},{CT0_h7+2, CT1_h7+1, CT2_h7+4},{CT0_h7+1, CT1_h7+4, CT2_h7+2},{CT0_h7+4, CT1_h7+2, CT2_h7+3},{CT0_h7+2, CT1_h7+3, CT2_h7+2},{CT0_h7+3, CT1_h7+2, CT2_h7+4},{CT0_h7+2, CT1_h7+1, CT2_h7+0},{CT0_h7+1, CT1_h7+4, CT2_h7+4},{CT0_h7+4, CT1_h7+2, CT2_h7+3},{CT0_h7+3, CT1_h7+3, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+4},{CT0_h7+1, CT1_h7+5, CT2_h7+2},{CT0_h7+2, CT1_h7+0, CT2_h7+2},{CT0_h7+3, CT1_h7+4, CT2_h7+2},{CT0_h7+2, CT1_h7+5, CT2_h7+0},{CT0_h7+1, CT1_h7+2, CT2_h7+0},{CT0_h7+4, CT1_h7+4, CT2_h7+0},{CT0_h7+3, CT1_h7+2, CT2_h7+1},{CT0_h7+2, CT1_h7+2, CT2_h7+2},{CT0_h7+1, CT1_h7+2, CT2_h7+3},{CT0_h7+4, CT1_h7+1, CT2_h7+5},{CT0_h7+3, CT1_h7+4, CT2_h7+0},{CT0_h7+2, CT1_h7+2, CT2_h7+4},{CT0_h7+1, CT1_h7+3, CT2_h7+3},{CT0_h7+2, CT1_h7+2, CT2_h7+2},{CT0_h7+3, CT1_h7+1, CT2_h7+4},{CT0_h7+2, CT1_h7+4, CT2_h7+2},{CT0_h7+4, CT1_h7+2, CT2_h7+2},{CT0_h7+4, CT1_h7+3, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+0},{CT0_h7+2, CT1_h7+5, CT2_h7+0},{CT0_h7+2, CT1_h7+4, CT2_h7+0},{CT0_h7+1, CT1_h7+5, CT2_h7+1},{CT0_h7+1, CT1_h7+1, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+3},{CT0_h7+4, CT1_h7+1, CT2_h7+2},{CT0_h7+3, CT1_h7+2, CT2_h7+1},{CT0_h7+2, CT1_h7+4, CT2_h7+4},{CT0_h7+2, CT1_h7+3, CT2_h7+3},{CT0_h7+3, CT1_h7+2, CT2_h7+2},{CT0_h7+2, CT1_h7+2, CT2_h7+1},{CT0_h7+1, CT1_h7+3, CT2_h7+2},{CT0_h7+0, CT1_h7+0, CT2_h7+0},
				{CT0_h8+0, CT1_h8+3, CT2_h8+4},{CT0_h8+1, CT1_h8+2, CT2_h8+3},{CT0_h8+2, CT1_h8+1, CT2_h8+2},{CT0_h8+3, CT1_h8+4, CT2_h8+4},{CT0_h8+2, CT1_h8+3, CT2_h8+2},{CT0_h8+4, CT1_h8+2, CT2_h8+2},{CT0_h8+2, CT1_h8+1, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+0},{CT0_h8+3, CT1_h8+3, CT2_h8+0},{CT0_h8+2, CT1_h8+2, CT2_h8+0},{CT0_h8+4, CT1_h8+1, CT2_h8+1},{CT0_h8+2, CT1_h8+4, CT2_h8+2},{CT0_h8+2, CT1_h8+3, CT2_h8+3},{CT0_h8+2, CT1_h8+2, CT2_h8+5},{CT0_h8+1, CT1_h8+4, CT2_h8+0},{CT0_h8+4, CT1_h8+2, CT2_h8+4},{CT0_h8+2, CT1_h8+2, CT2_h8+5},{CT0_h8+3, CT1_h8+2, CT2_h8+2},{CT0_h8+2, CT1_h8+1, CT2_h8+4},{CT0_h8+1, CT1_h8+4, CT2_h8+2},{CT0_h8+4, CT1_h8+2, CT2_h8+3},{CT0_h8+2, CT1_h8+3, CT2_h8+2},{CT0_h8+3, CT1_h8+2, CT2_h8+4},{CT0_h8+2, CT1_h8+1, CT2_h8+0},{CT0_h8+1, CT1_h8+4, CT2_h8+4},{CT0_h8+4, CT1_h8+2, CT2_h8+3},{CT0_h8+3, CT1_h8+3, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+4},{CT0_h8+1, CT1_h8+5, CT2_h8+2},{CT0_h8+2, CT1_h8+0, CT2_h8+2},{CT0_h8+3, CT1_h8+4, CT2_h8+2},{CT0_h8+2, CT1_h8+5, CT2_h8+0},{CT0_h8+1, CT1_h8+2, CT2_h8+0},{CT0_h8+4, CT1_h8+4, CT2_h8+0},{CT0_h8+3, CT1_h8+2, CT2_h8+1},{CT0_h8+2, CT1_h8+2, CT2_h8+2},{CT0_h8+1, CT1_h8+2, CT2_h8+3},{CT0_h8+4, CT1_h8+1, CT2_h8+5},{CT0_h8+3, CT1_h8+4, CT2_h8+0},{CT0_h8+2, CT1_h8+2, CT2_h8+4},{CT0_h8+1, CT1_h8+3, CT2_h8+3},{CT0_h8+2, CT1_h8+2, CT2_h8+2},{CT0_h8+3, CT1_h8+1, CT2_h8+4},{CT0_h8+2, CT1_h8+4, CT2_h8+2},{CT0_h8+4, CT1_h8+2, CT2_h8+2},{CT0_h8+4, CT1_h8+3, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+0},{CT0_h8+2, CT1_h8+5, CT2_h8+0},{CT0_h8+2, CT1_h8+4, CT2_h8+0},{CT0_h8+1, CT1_h8+5, CT2_h8+1},{CT0_h8+1, CT1_h8+1, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+3},{CT0_h8+4, CT1_h8+1, CT2_h8+2},{CT0_h8+3, CT1_h8+2, CT2_h8+1},{CT0_h8+2, CT1_h8+4, CT2_h8+4},{CT0_h8+2, CT1_h8+3, CT2_h8+3},{CT0_h8+3, CT1_h8+2, CT2_h8+2},{CT0_h8+2, CT1_h8+2, CT2_h8+1},{CT0_h8+1, CT1_h8+3, CT2_h8+2},{CT0_h8+0, CT1_h8+0, CT2_h8+0},
				{CT0_h9+0, CT1_h9+3, CT2_h9+4},{CT0_h9+1, CT1_h9+2, CT2_h9+3},{CT0_h9+2, CT1_h9+1, CT2_h9+2},{CT0_h9+3, CT1_h9+4, CT2_h9+4},{CT0_h9+2, CT1_h9+3, CT2_h9+2},{CT0_h9+4, CT1_h9+2, CT2_h9+2},{CT0_h9+2, CT1_h9+1, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+0},{CT0_h9+3, CT1_h9+3, CT2_h9+0},{CT0_h9+2, CT1_h9+2, CT2_h9+0},{CT0_h9+4, CT1_h9+1, CT2_h9+1},{CT0_h9+2, CT1_h9+4, CT2_h9+2},{CT0_h9+2, CT1_h9+3, CT2_h9+3},{CT0_h9+2, CT1_h9+2, CT2_h9+5},{CT0_h9+1, CT1_h9+4, CT2_h9+0},{CT0_h9+4, CT1_h9+2, CT2_h9+4},{CT0_h9+2, CT1_h9+2, CT2_h9+5},{CT0_h9+3, CT1_h9+2, CT2_h9+2},{CT0_h9+2, CT1_h9+1, CT2_h9+4},{CT0_h9+1, CT1_h9+4, CT2_h9+2},{CT0_h9+4, CT1_h9+2, CT2_h9+3},{CT0_h9+2, CT1_h9+3, CT2_h9+2},{CT0_h9+3, CT1_h9+2, CT2_h9+4},{CT0_h9+2, CT1_h9+1, CT2_h9+0},{CT0_h9+1, CT1_h9+4, CT2_h9+4},{CT0_h9+4, CT1_h9+2, CT2_h9+3},{CT0_h9+3, CT1_h9+3, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+4},{CT0_h9+1, CT1_h9+5, CT2_h9+2},{CT0_h9+2, CT1_h9+0, CT2_h9+2},{CT0_h9+3, CT1_h9+4, CT2_h9+2},{CT0_h9+2, CT1_h9+5, CT2_h9+0},{CT0_h9+1, CT1_h9+2, CT2_h9+0},{CT0_h9+4, CT1_h9+4, CT2_h9+0},{CT0_h9+3, CT1_h9+2, CT2_h9+1},{CT0_h9+2, CT1_h9+2, CT2_h9+2},{CT0_h9+1, CT1_h9+2, CT2_h9+3},{CT0_h9+4, CT1_h9+1, CT2_h9+5},{CT0_h9+3, CT1_h9+4, CT2_h9+0},{CT0_h9+2, CT1_h9+2, CT2_h9+4},{CT0_h9+1, CT1_h9+3, CT2_h9+3},{CT0_h9+2, CT1_h9+2, CT2_h9+2},{CT0_h9+3, CT1_h9+1, CT2_h9+4},{CT0_h9+2, CT1_h9+4, CT2_h9+2},{CT0_h9+4, CT1_h9+2, CT2_h9+2},{CT0_h9+4, CT1_h9+3, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+0},{CT0_h9+2, CT1_h9+5, CT2_h9+0},{CT0_h9+2, CT1_h9+4, CT2_h9+0},{CT0_h9+1, CT1_h9+5, CT2_h9+1},{CT0_h9+1, CT1_h9+1, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+3},{CT0_h9+4, CT1_h9+1, CT2_h9+2},{CT0_h9+3, CT1_h9+2, CT2_h9+1},{CT0_h9+2, CT1_h9+4, CT2_h9+4},{CT0_h9+2, CT1_h9+3, CT2_h9+3},{CT0_h9+3, CT1_h9+2, CT2_h9+2},{CT0_h9+2, CT1_h9+2, CT2_h9+1},{CT0_h9+1, CT1_h9+3, CT2_h9+2},{CT0_h9+0, CT1_h9+0, CT2_h9+0},
				{CT0_h10+0, CT1_h10+3, CT2_h10+4},{CT0_h10+1, CT1_h10+2, CT2_h10+3},{CT0_h10+2, CT1_h10+1, CT2_h10+2},{CT0_h10+3, CT1_h10+4, CT2_h10+4},{CT0_h10+2, CT1_h10+3, CT2_h10+2},{CT0_h10+4, CT1_h10+2, CT2_h10+2},{CT0_h10+2, CT1_h10+1, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+0},{CT0_h10+3, CT1_h10+3, CT2_h10+0},{CT0_h10+2, CT1_h10+2, CT2_h10+0},{CT0_h10+4, CT1_h10+1, CT2_h10+1},{CT0_h10+2, CT1_h10+4, CT2_h10+2},{CT0_h10+2, CT1_h10+3, CT2_h10+3},{CT0_h10+2, CT1_h10+2, CT2_h10+5},{CT0_h10+1, CT1_h10+4, CT2_h10+0},{CT0_h10+4, CT1_h10+2, CT2_h10+4},{CT0_h10+2, CT1_h10+2, CT2_h10+5},{CT0_h10+3, CT1_h10+2, CT2_h10+2},{CT0_h10+2, CT1_h10+1, CT2_h10+4},{CT0_h10+1, CT1_h10+4, CT2_h10+2},{CT0_h10+4, CT1_h10+2, CT2_h10+3},{CT0_h10+2, CT1_h10+3, CT2_h10+2},{CT0_h10+3, CT1_h10+2, CT2_h10+4},{CT0_h10+2, CT1_h10+1, CT2_h10+0},{CT0_h10+1, CT1_h10+4, CT2_h10+4},{CT0_h10+4, CT1_h10+2, CT2_h10+3},{CT0_h10+3, CT1_h10+3, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+4},{CT0_h10+1, CT1_h10+5, CT2_h10+2},{CT0_h10+2, CT1_h10+0, CT2_h10+2},{CT0_h10+3, CT1_h10+4, CT2_h10+2},{CT0_h10+2, CT1_h10+5, CT2_h10+0},{CT0_h10+1, CT1_h10+2, CT2_h10+0},{CT0_h10+4, CT1_h10+4, CT2_h10+0},{CT0_h10+3, CT1_h10+2, CT2_h10+1},{CT0_h10+2, CT1_h10+2, CT2_h10+2},{CT0_h10+1, CT1_h10+2, CT2_h10+3},{CT0_h10+4, CT1_h10+1, CT2_h10+5},{CT0_h10+3, CT1_h10+4, CT2_h10+0},{CT0_h10+2, CT1_h10+2, CT2_h10+4},{CT0_h10+1, CT1_h10+3, CT2_h10+3},{CT0_h10+2, CT1_h10+2, CT2_h10+2},{CT0_h10+3, CT1_h10+1, CT2_h10+4},{CT0_h10+2, CT1_h10+4, CT2_h10+2},{CT0_h10+4, CT1_h10+2, CT2_h10+2},{CT0_h10+4, CT1_h10+3, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+0},{CT0_h10+2, CT1_h10+5, CT2_h10+0},{CT0_h10+2, CT1_h10+4, CT2_h10+0},{CT0_h10+1, CT1_h10+5, CT2_h10+1},{CT0_h10+1, CT1_h10+1, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+3},{CT0_h10+4, CT1_h10+1, CT2_h10+2},{CT0_h10+3, CT1_h10+2, CT2_h10+1},{CT0_h10+2, CT1_h10+4, CT2_h10+4},{CT0_h10+2, CT1_h10+3, CT2_h10+3},{CT0_h10+3, CT1_h10+2, CT2_h10+2},{CT0_h10+2, CT1_h10+2, CT2_h10+1},{CT0_h10+1, CT1_h10+3, CT2_h10+2},{CT0_h10+0, CT1_h10+0, CT2_h10+0},
				{CT0_h11+0, CT1_h11+3, CT2_h11+4},{CT0_h11+1, CT1_h11+2, CT2_h11+3},{CT0_h11+2, CT1_h11+1, CT2_h11+2},{CT0_h11+3, CT1_h11+4, CT2_h11+4},{CT0_h11+2, CT1_h11+3, CT2_h11+2},{CT0_h11+4, CT1_h11+2, CT2_h11+2},{CT0_h11+2, CT1_h11+1, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+0},{CT0_h11+3, CT1_h11+3, CT2_h11+0},{CT0_h11+2, CT1_h11+2, CT2_h11+0},{CT0_h11+4, CT1_h11+1, CT2_h11+1},{CT0_h11+2, CT1_h11+4, CT2_h11+2},{CT0_h11+2, CT1_h11+3, CT2_h11+3},{CT0_h11+2, CT1_h11+2, CT2_h11+5},{CT0_h11+1, CT1_h11+4, CT2_h11+0},{CT0_h11+4, CT1_h11+2, CT2_h11+4},{CT0_h11+2, CT1_h11+2, CT2_h11+5},{CT0_h11+3, CT1_h11+2, CT2_h11+2},{CT0_h11+2, CT1_h11+1, CT2_h11+4},{CT0_h11+1, CT1_h11+4, CT2_h11+2},{CT0_h11+4, CT1_h11+2, CT2_h11+3},{CT0_h11+2, CT1_h11+3, CT2_h11+2},{CT0_h11+3, CT1_h11+2, CT2_h11+4},{CT0_h11+2, CT1_h11+1, CT2_h11+0},{CT0_h11+1, CT1_h11+4, CT2_h11+4},{CT0_h11+4, CT1_h11+2, CT2_h11+3},{CT0_h11+3, CT1_h11+3, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+4},{CT0_h11+1, CT1_h11+5, CT2_h11+2},{CT0_h11+2, CT1_h11+0, CT2_h11+2},{CT0_h11+3, CT1_h11+4, CT2_h11+2},{CT0_h11+2, CT1_h11+5, CT2_h11+0},{CT0_h11+1, CT1_h11+2, CT2_h11+0},{CT0_h11+4, CT1_h11+4, CT2_h11+0},{CT0_h11+3, CT1_h11+2, CT2_h11+1},{CT0_h11+2, CT1_h11+2, CT2_h11+2},{CT0_h11+1, CT1_h11+2, CT2_h11+3},{CT0_h11+4, CT1_h11+1, CT2_h11+5},{CT0_h11+3, CT1_h11+4, CT2_h11+0},{CT0_h11+2, CT1_h11+2, CT2_h11+4},{CT0_h11+1, CT1_h11+3, CT2_h11+3},{CT0_h11+2, CT1_h11+2, CT2_h11+2},{CT0_h11+3, CT1_h11+1, CT2_h11+4},{CT0_h11+2, CT1_h11+4, CT2_h11+2},{CT0_h11+4, CT1_h11+2, CT2_h11+2},{CT0_h11+4, CT1_h11+3, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+0},{CT0_h11+2, CT1_h11+5, CT2_h11+0},{CT0_h11+2, CT1_h11+4, CT2_h11+0},{CT0_h11+1, CT1_h11+5, CT2_h11+1},{CT0_h11+1, CT1_h11+1, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+3},{CT0_h11+4, CT1_h11+1, CT2_h11+2},{CT0_h11+3, CT1_h11+2, CT2_h11+1},{CT0_h11+2, CT1_h11+4, CT2_h11+4},{CT0_h11+2, CT1_h11+3, CT2_h11+3},{CT0_h11+3, CT1_h11+2, CT2_h11+2},{CT0_h11+2, CT1_h11+2, CT2_h11+1},{CT0_h11+1, CT1_h11+3, CT2_h11+2},{CT0_h11+0, CT1_h11+0, CT2_h11+0},
				{CT0_h12+0, CT1_h12+3, CT2_h12+4},{CT0_h12+1, CT1_h12+2, CT2_h12+3},{CT0_h12+2, CT1_h12+1, CT2_h12+2},{CT0_h12+3, CT1_h12+4, CT2_h12+4},{CT0_h12+2, CT1_h12+3, CT2_h12+2},{CT0_h12+4, CT1_h12+2, CT2_h12+2},{CT0_h12+2, CT1_h12+1, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+0},{CT0_h12+3, CT1_h12+3, CT2_h12+0},{CT0_h12+2, CT1_h12+2, CT2_h12+0},{CT0_h12+4, CT1_h12+1, CT2_h12+1},{CT0_h12+2, CT1_h12+4, CT2_h12+2},{CT0_h12+2, CT1_h12+3, CT2_h12+3},{CT0_h12+2, CT1_h12+2, CT2_h12+5},{CT0_h12+1, CT1_h12+4, CT2_h12+0},{CT0_h12+4, CT1_h12+2, CT2_h12+4},{CT0_h12+2, CT1_h12+2, CT2_h12+5},{CT0_h12+3, CT1_h12+2, CT2_h12+2},{CT0_h12+2, CT1_h12+1, CT2_h12+4},{CT0_h12+1, CT1_h12+4, CT2_h12+2},{CT0_h12+4, CT1_h12+2, CT2_h12+3},{CT0_h12+2, CT1_h12+3, CT2_h12+2},{CT0_h12+3, CT1_h12+2, CT2_h12+4},{CT0_h12+2, CT1_h12+1, CT2_h12+0},{CT0_h12+1, CT1_h12+4, CT2_h12+4},{CT0_h12+4, CT1_h12+2, CT2_h12+3},{CT0_h12+3, CT1_h12+3, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+4},{CT0_h12+1, CT1_h12+5, CT2_h12+2},{CT0_h12+2, CT1_h12+0, CT2_h12+2},{CT0_h12+3, CT1_h12+4, CT2_h12+2},{CT0_h12+2, CT1_h12+5, CT2_h12+0},{CT0_h12+1, CT1_h12+2, CT2_h12+0},{CT0_h12+4, CT1_h12+4, CT2_h12+0},{CT0_h12+3, CT1_h12+2, CT2_h12+1},{CT0_h12+2, CT1_h12+2, CT2_h12+2},{CT0_h12+1, CT1_h12+2, CT2_h12+3},{CT0_h12+4, CT1_h12+1, CT2_h12+5},{CT0_h12+3, CT1_h12+4, CT2_h12+0},{CT0_h12+2, CT1_h12+2, CT2_h12+4},{CT0_h12+1, CT1_h12+3, CT2_h12+3},{CT0_h12+2, CT1_h12+2, CT2_h12+2},{CT0_h12+3, CT1_h12+1, CT2_h12+4},{CT0_h12+2, CT1_h12+4, CT2_h12+2},{CT0_h12+4, CT1_h12+2, CT2_h12+2},{CT0_h12+4, CT1_h12+3, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+0},{CT0_h12+2, CT1_h12+5, CT2_h12+0},{CT0_h12+2, CT1_h12+4, CT2_h12+0},{CT0_h12+1, CT1_h12+5, CT2_h12+1},{CT0_h12+1, CT1_h12+1, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+3},{CT0_h12+4, CT1_h12+1, CT2_h12+2},{CT0_h12+3, CT1_h12+2, CT2_h12+1},{CT0_h12+2, CT1_h12+4, CT2_h12+4},{CT0_h12+2, CT1_h12+3, CT2_h12+3},{CT0_h12+3, CT1_h12+2, CT2_h12+2},{CT0_h12+2, CT1_h12+2, CT2_h12+1},{CT0_h12+1, CT1_h12+3, CT2_h12+2},{CT0_h12+0, CT1_h12+0, CT2_h12+0},
				{CT0_h13+0, CT1_h13+3, CT2_h13+4},{CT0_h13+1, CT1_h13+2, CT2_h13+3},{CT0_h13+2, CT1_h13+1, CT2_h13+2},{CT0_h13+3, CT1_h13+4, CT2_h13+4},{CT0_h13+2, CT1_h13+3, CT2_h13+2},{CT0_h13+4, CT1_h13+2, CT2_h13+2},{CT0_h13+2, CT1_h13+1, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+0},{CT0_h13+3, CT1_h13+3, CT2_h13+0},{CT0_h13+2, CT1_h13+2, CT2_h13+0},{CT0_h13+4, CT1_h13+1, CT2_h13+1},{CT0_h13+2, CT1_h13+4, CT2_h13+2},{CT0_h13+2, CT1_h13+3, CT2_h13+3},{CT0_h13+2, CT1_h13+2, CT2_h13+5},{CT0_h13+1, CT1_h13+4, CT2_h13+0},{CT0_h13+4, CT1_h13+2, CT2_h13+4},{CT0_h13+2, CT1_h13+2, CT2_h13+5},{CT0_h13+3, CT1_h13+2, CT2_h13+2},{CT0_h13+2, CT1_h13+1, CT2_h13+4},{CT0_h13+1, CT1_h13+4, CT2_h13+2},{CT0_h13+4, CT1_h13+2, CT2_h13+3},{CT0_h13+2, CT1_h13+3, CT2_h13+2},{CT0_h13+3, CT1_h13+2, CT2_h13+4},{CT0_h13+2, CT1_h13+1, CT2_h13+0},{CT0_h13+1, CT1_h13+4, CT2_h13+4},{CT0_h13+4, CT1_h13+2, CT2_h13+3},{CT0_h13+3, CT1_h13+3, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+4},{CT0_h13+1, CT1_h13+5, CT2_h13+2},{CT0_h13+2, CT1_h13+0, CT2_h13+2},{CT0_h13+3, CT1_h13+4, CT2_h13+2},{CT0_h13+2, CT1_h13+5, CT2_h13+0},{CT0_h13+1, CT1_h13+2, CT2_h13+0},{CT0_h13+4, CT1_h13+4, CT2_h13+0},{CT0_h13+3, CT1_h13+2, CT2_h13+1},{CT0_h13+2, CT1_h13+2, CT2_h13+2},{CT0_h13+1, CT1_h13+2, CT2_h13+3},{CT0_h13+4, CT1_h13+1, CT2_h13+5},{CT0_h13+3, CT1_h13+4, CT2_h13+0},{CT0_h13+2, CT1_h13+2, CT2_h13+4},{CT0_h13+1, CT1_h13+3, CT2_h13+3},{CT0_h13+2, CT1_h13+2, CT2_h13+2},{CT0_h13+3, CT1_h13+1, CT2_h13+4},{CT0_h13+2, CT1_h13+4, CT2_h13+2},{CT0_h13+4, CT1_h13+2, CT2_h13+2},{CT0_h13+4, CT1_h13+3, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+0},{CT0_h13+2, CT1_h13+5, CT2_h13+0},{CT0_h13+2, CT1_h13+4, CT2_h13+0},{CT0_h13+1, CT1_h13+5, CT2_h13+1},{CT0_h13+1, CT1_h13+1, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+3},{CT0_h13+4, CT1_h13+1, CT2_h13+2},{CT0_h13+3, CT1_h13+2, CT2_h13+1},{CT0_h13+2, CT1_h13+4, CT2_h13+4},{CT0_h13+2, CT1_h13+3, CT2_h13+3},{CT0_h13+3, CT1_h13+2, CT2_h13+2},{CT0_h13+2, CT1_h13+2, CT2_h13+1},{CT0_h13+1, CT1_h13+3, CT2_h13+2},{CT0_h13+0, CT1_h13+0, CT2_h13+0},
				{CT0_h14+0, CT1_h14+3, CT2_h14+4},{CT0_h14+1, CT1_h14+2, CT2_h14+3},{CT0_h14+2, CT1_h14+1, CT2_h14+2},{CT0_h14+3, CT1_h14+4, CT2_h14+4},{CT0_h14+2, CT1_h14+3, CT2_h14+2},{CT0_h14+4, CT1_h14+2, CT2_h14+2},{CT0_h14+2, CT1_h14+1, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+0},{CT0_h14+3, CT1_h14+3, CT2_h14+0},{CT0_h14+2, CT1_h14+2, CT2_h14+0},{CT0_h14+4, CT1_h14+1, CT2_h14+1},{CT0_h14+2, CT1_h14+4, CT2_h14+2},{CT0_h14+2, CT1_h14+3, CT2_h14+3},{CT0_h14+2, CT1_h14+2, CT2_h14+5},{CT0_h14+1, CT1_h14+4, CT2_h14+0},{CT0_h14+4, CT1_h14+2, CT2_h14+4},{CT0_h14+2, CT1_h14+2, CT2_h14+5},{CT0_h14+3, CT1_h14+2, CT2_h14+2},{CT0_h14+2, CT1_h14+1, CT2_h14+4},{CT0_h14+1, CT1_h14+4, CT2_h14+2},{CT0_h14+4, CT1_h14+2, CT2_h14+3},{CT0_h14+2, CT1_h14+3, CT2_h14+2},{CT0_h14+3, CT1_h14+2, CT2_h14+4},{CT0_h14+2, CT1_h14+1, CT2_h14+0},{CT0_h14+1, CT1_h14+4, CT2_h14+4},{CT0_h14+4, CT1_h14+2, CT2_h14+3},{CT0_h14+3, CT1_h14+3, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+4},{CT0_h14+1, CT1_h14+5, CT2_h14+2},{CT0_h14+2, CT1_h14+0, CT2_h14+2},{CT0_h14+3, CT1_h14+4, CT2_h14+2},{CT0_h14+2, CT1_h14+5, CT2_h14+0},{CT0_h14+1, CT1_h14+2, CT2_h14+0},{CT0_h14+4, CT1_h14+4, CT2_h14+0},{CT0_h14+3, CT1_h14+2, CT2_h14+1},{CT0_h14+2, CT1_h14+2, CT2_h14+2},{CT0_h14+1, CT1_h14+2, CT2_h14+3},{CT0_h14+4, CT1_h14+1, CT2_h14+5},{CT0_h14+3, CT1_h14+4, CT2_h14+0},{CT0_h14+2, CT1_h14+2, CT2_h14+4},{CT0_h14+1, CT1_h14+3, CT2_h14+3},{CT0_h14+2, CT1_h14+2, CT2_h14+2},{CT0_h14+3, CT1_h14+1, CT2_h14+4},{CT0_h14+2, CT1_h14+4, CT2_h14+2},{CT0_h14+4, CT1_h14+2, CT2_h14+2},{CT0_h14+4, CT1_h14+3, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+0},{CT0_h14+2, CT1_h14+5, CT2_h14+0},{CT0_h14+2, CT1_h14+4, CT2_h14+0},{CT0_h14+1, CT1_h14+5, CT2_h14+1},{CT0_h14+1, CT1_h14+1, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+3},{CT0_h14+4, CT1_h14+1, CT2_h14+2},{CT0_h14+3, CT1_h14+2, CT2_h14+1},{CT0_h14+2, CT1_h14+4, CT2_h14+4},{CT0_h14+2, CT1_h14+3, CT2_h14+3},{CT0_h14+3, CT1_h14+2, CT2_h14+2},{CT0_h14+2, CT1_h14+2, CT2_h14+1},{CT0_h14+1, CT1_h14+3, CT2_h14+2},{CT0_h14+0, CT1_h14+0, CT2_h14+0},
				{CT0_h15+0, CT1_h15+3, CT2_h15+4},{CT0_h15+1, CT1_h15+2, CT2_h15+3},{CT0_h15+2, CT1_h15+1, CT2_h15+2},{CT0_h15+3, CT1_h15+4, CT2_h15+4},{CT0_h15+2, CT1_h15+3, CT2_h15+2},{CT0_h15+4, CT1_h15+2, CT2_h15+2},{CT0_h15+2, CT1_h15+1, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+0},{CT0_h15+3, CT1_h15+3, CT2_h15+0},{CT0_h15+2, CT1_h15+2, CT2_h15+0},{CT0_h15+4, CT1_h15+1, CT2_h15+1},{CT0_h15+2, CT1_h15+4, CT2_h15+2},{CT0_h15+2, CT1_h15+3, CT2_h15+3},{CT0_h15+2, CT1_h15+2, CT2_h15+5},{CT0_h15+1, CT1_h15+4, CT2_h15+0},{CT0_h15+4, CT1_h15+2, CT2_h15+4},{CT0_h15+2, CT1_h15+2, CT2_h15+5},{CT0_h15+3, CT1_h15+2, CT2_h15+2},{CT0_h15+2, CT1_h15+1, CT2_h15+4},{CT0_h15+1, CT1_h15+4, CT2_h15+2},{CT0_h15+4, CT1_h15+2, CT2_h15+3},{CT0_h15+2, CT1_h15+3, CT2_h15+2},{CT0_h15+3, CT1_h15+2, CT2_h15+4},{CT0_h15+2, CT1_h15+1, CT2_h15+0},{CT0_h15+1, CT1_h15+4, CT2_h15+4},{CT0_h15+4, CT1_h15+2, CT2_h15+3},{CT0_h15+3, CT1_h15+3, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+4},{CT0_h15+1, CT1_h15+5, CT2_h15+2},{CT0_h15+2, CT1_h15+0, CT2_h15+2},{CT0_h15+3, CT1_h15+4, CT2_h15+2},{CT0_h15+2, CT1_h15+5, CT2_h15+0},{CT0_h15+1, CT1_h15+2, CT2_h15+0},{CT0_h15+4, CT1_h15+4, CT2_h15+0},{CT0_h15+3, CT1_h15+2, CT2_h15+1},{CT0_h15+2, CT1_h15+2, CT2_h15+2},{CT0_h15+1, CT1_h15+2, CT2_h15+3},{CT0_h15+4, CT1_h15+1, CT2_h15+5},{CT0_h15+3, CT1_h15+4, CT2_h15+0},{CT0_h15+2, CT1_h15+2, CT2_h15+4},{CT0_h15+1, CT1_h15+3, CT2_h15+3},{CT0_h15+2, CT1_h15+2, CT2_h15+2},{CT0_h15+3, CT1_h15+1, CT2_h15+4},{CT0_h15+2, CT1_h15+4, CT2_h15+2},{CT0_h15+4, CT1_h15+2, CT2_h15+2},{CT0_h15+4, CT1_h15+3, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+0},{CT0_h15+2, CT1_h15+5, CT2_h15+0},{CT0_h15+2, CT1_h15+4, CT2_h15+0},{CT0_h15+1, CT1_h15+5, CT2_h15+1},{CT0_h15+1, CT1_h15+1, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+3},{CT0_h15+4, CT1_h15+1, CT2_h15+2},{CT0_h15+3, CT1_h15+2, CT2_h15+1},{CT0_h15+2, CT1_h15+4, CT2_h15+4},{CT0_h15+2, CT1_h15+3, CT2_h15+3},{CT0_h15+3, CT1_h15+2, CT2_h15+2},{CT0_h15+2, CT1_h15+2, CT2_h15+1},{CT0_h15+1, CT1_h15+3, CT2_h15+2},{CT0_h15+0, CT1_h15+0, CT2_h15+0},
				{CT0_h16+0, CT1_h16+3, CT2_h16+4},{CT0_h16+1, CT1_h16+2, CT2_h16+3},{CT0_h16+2, CT1_h16+1, CT2_h16+2},{CT0_h16+3, CT1_h16+4, CT2_h16+4},{CT0_h16+2, CT1_h16+3, CT2_h16+2},{CT0_h16+4, CT1_h16+2, CT2_h16+2},{CT0_h16+2, CT1_h16+1, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+0},{CT0_h16+3, CT1_h16+3, CT2_h16+0},{CT0_h16+2, CT1_h16+2, CT2_h16+0},{CT0_h16+4, CT1_h16+1, CT2_h16+1},{CT0_h16+2, CT1_h16+4, CT2_h16+2},{CT0_h16+2, CT1_h16+3, CT2_h16+3},{CT0_h16+2, CT1_h16+2, CT2_h16+5},{CT0_h16+1, CT1_h16+4, CT2_h16+0},{CT0_h16+4, CT1_h16+2, CT2_h16+4},{CT0_h16+2, CT1_h16+2, CT2_h16+5},{CT0_h16+3, CT1_h16+2, CT2_h16+2},{CT0_h16+2, CT1_h16+1, CT2_h16+4},{CT0_h16+1, CT1_h16+4, CT2_h16+2},{CT0_h16+4, CT1_h16+2, CT2_h16+3},{CT0_h16+2, CT1_h16+3, CT2_h16+2},{CT0_h16+3, CT1_h16+2, CT2_h16+4},{CT0_h16+2, CT1_h16+1, CT2_h16+0},{CT0_h16+1, CT1_h16+4, CT2_h16+4},{CT0_h16+4, CT1_h16+2, CT2_h16+3},{CT0_h16+3, CT1_h16+3, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+4},{CT0_h16+1, CT1_h16+5, CT2_h16+2},{CT0_h16+2, CT1_h16+0, CT2_h16+2},{CT0_h16+3, CT1_h16+4, CT2_h16+2},{CT0_h16+2, CT1_h16+5, CT2_h16+0},{CT0_h16+1, CT1_h16+2, CT2_h16+0},{CT0_h16+4, CT1_h16+4, CT2_h16+0},{CT0_h16+3, CT1_h16+2, CT2_h16+1},{CT0_h16+2, CT1_h16+2, CT2_h16+2},{CT0_h16+1, CT1_h16+2, CT2_h16+3},{CT0_h16+4, CT1_h16+1, CT2_h16+5},{CT0_h16+3, CT1_h16+4, CT2_h16+0},{CT0_h16+2, CT1_h16+2, CT2_h16+4},{CT0_h16+1, CT1_h16+3, CT2_h16+3},{CT0_h16+2, CT1_h16+2, CT2_h16+2},{CT0_h16+3, CT1_h16+1, CT2_h16+4},{CT0_h16+2, CT1_h16+4, CT2_h16+2},{CT0_h16+4, CT1_h16+2, CT2_h16+2},{CT0_h16+4, CT1_h16+3, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+0},{CT0_h16+2, CT1_h16+5, CT2_h16+0},{CT0_h16+2, CT1_h16+4, CT2_h16+0},{CT0_h16+1, CT1_h16+5, CT2_h16+1},{CT0_h16+1, CT1_h16+1, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+3},{CT0_h16+4, CT1_h16+1, CT2_h16+2},{CT0_h16+3, CT1_h16+2, CT2_h16+1},{CT0_h16+2, CT1_h16+4, CT2_h16+4},{CT0_h16+2, CT1_h16+3, CT2_h16+3},{CT0_h16+3, CT1_h16+2, CT2_h16+2},{CT0_h16+2, CT1_h16+2, CT2_h16+1},{CT0_h16+1, CT1_h16+3, CT2_h16+2},{CT0_h16+0, CT1_h16+0, CT2_h16+0},
				{CT0_h17+0, CT1_h17+3, CT2_h17+4},{CT0_h17+1, CT1_h17+2, CT2_h17+3},{CT0_h17+2, CT1_h17+1, CT2_h17+2},{CT0_h17+3, CT1_h17+4, CT2_h17+4},{CT0_h17+2, CT1_h17+3, CT2_h17+2},{CT0_h17+4, CT1_h17+2, CT2_h17+2},{CT0_h17+2, CT1_h17+1, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+0},{CT0_h17+3, CT1_h17+3, CT2_h17+0},{CT0_h17+2, CT1_h17+2, CT2_h17+0},{CT0_h17+4, CT1_h17+1, CT2_h17+1},{CT0_h17+2, CT1_h17+4, CT2_h17+2},{CT0_h17+2, CT1_h17+3, CT2_h17+3},{CT0_h17+2, CT1_h17+2, CT2_h17+5},{CT0_h17+1, CT1_h17+4, CT2_h17+0},{CT0_h17+4, CT1_h17+2, CT2_h17+4},{CT0_h17+2, CT1_h17+2, CT2_h17+5},{CT0_h17+3, CT1_h17+2, CT2_h17+2},{CT0_h17+2, CT1_h17+1, CT2_h17+4},{CT0_h17+1, CT1_h17+4, CT2_h17+2},{CT0_h17+4, CT1_h17+2, CT2_h17+3},{CT0_h17+2, CT1_h17+3, CT2_h17+2},{CT0_h17+3, CT1_h17+2, CT2_h17+4},{CT0_h17+2, CT1_h17+1, CT2_h17+0},{CT0_h17+1, CT1_h17+4, CT2_h17+4},{CT0_h17+4, CT1_h17+2, CT2_h17+3},{CT0_h17+3, CT1_h17+3, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+4},{CT0_h17+1, CT1_h17+5, CT2_h17+2},{CT0_h17+2, CT1_h17+0, CT2_h17+2},{CT0_h17+3, CT1_h17+4, CT2_h17+2},{CT0_h17+2, CT1_h17+5, CT2_h17+0},{CT0_h17+1, CT1_h17+2, CT2_h17+0},{CT0_h17+4, CT1_h17+4, CT2_h17+0},{CT0_h17+3, CT1_h17+2, CT2_h17+1},{CT0_h17+2, CT1_h17+2, CT2_h17+2},{CT0_h17+1, CT1_h17+2, CT2_h17+3},{CT0_h17+4, CT1_h17+1, CT2_h17+5},{CT0_h17+3, CT1_h17+4, CT2_h17+0},{CT0_h17+2, CT1_h17+2, CT2_h17+4},{CT0_h17+1, CT1_h17+3, CT2_h17+3},{CT0_h17+2, CT1_h17+2, CT2_h17+2},{CT0_h17+3, CT1_h17+1, CT2_h17+4},{CT0_h17+2, CT1_h17+4, CT2_h17+2},{CT0_h17+4, CT1_h17+2, CT2_h17+2},{CT0_h17+4, CT1_h17+3, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+0},{CT0_h17+2, CT1_h17+5, CT2_h17+0},{CT0_h17+2, CT1_h17+4, CT2_h17+0},{CT0_h17+1, CT1_h17+5, CT2_h17+1},{CT0_h17+1, CT1_h17+1, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+3},{CT0_h17+4, CT1_h17+1, CT2_h17+2},{CT0_h17+3, CT1_h17+2, CT2_h17+1},{CT0_h17+2, CT1_h17+4, CT2_h17+4},{CT0_h17+2, CT1_h17+3, CT2_h17+3},{CT0_h17+3, CT1_h17+2, CT2_h17+2},{CT0_h17+2, CT1_h17+2, CT2_h17+1},{CT0_h17+1, CT1_h17+3, CT2_h17+2},{CT0_h17+0, CT1_h17+0, CT2_h17+0},
				{CT0_h18+0, CT1_h18+3, CT2_h18+4},{CT0_h18+1, CT1_h18+2, CT2_h18+3},{CT0_h18+2, CT1_h18+1, CT2_h18+2},{CT0_h18+3, CT1_h18+4, CT2_h18+4},{CT0_h18+2, CT1_h18+3, CT2_h18+2},{CT0_h18+4, CT1_h18+2, CT2_h18+2},{CT0_h18+2, CT1_h18+1, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+0},{CT0_h18+3, CT1_h18+3, CT2_h18+0},{CT0_h18+2, CT1_h18+2, CT2_h18+0},{CT0_h18+4, CT1_h18+1, CT2_h18+1},{CT0_h18+2, CT1_h18+4, CT2_h18+2},{CT0_h18+2, CT1_h18+3, CT2_h18+3},{CT0_h18+2, CT1_h18+2, CT2_h18+5},{CT0_h18+1, CT1_h18+4, CT2_h18+0},{CT0_h18+4, CT1_h18+2, CT2_h18+4},{CT0_h18+2, CT1_h18+2, CT2_h18+5},{CT0_h18+3, CT1_h18+2, CT2_h18+2},{CT0_h18+2, CT1_h18+1, CT2_h18+4},{CT0_h18+1, CT1_h18+4, CT2_h18+2},{CT0_h18+4, CT1_h18+2, CT2_h18+3},{CT0_h18+2, CT1_h18+3, CT2_h18+2},{CT0_h18+3, CT1_h18+2, CT2_h18+4},{CT0_h18+2, CT1_h18+1, CT2_h18+0},{CT0_h18+1, CT1_h18+4, CT2_h18+4},{CT0_h18+4, CT1_h18+2, CT2_h18+3},{CT0_h18+3, CT1_h18+3, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+4},{CT0_h18+1, CT1_h18+5, CT2_h18+2},{CT0_h18+2, CT1_h18+0, CT2_h18+2},{CT0_h18+3, CT1_h18+4, CT2_h18+2},{CT0_h18+2, CT1_h18+5, CT2_h18+0},{CT0_h18+1, CT1_h18+2, CT2_h18+0},{CT0_h18+4, CT1_h18+4, CT2_h18+0},{CT0_h18+3, CT1_h18+2, CT2_h18+1},{CT0_h18+2, CT1_h18+2, CT2_h18+2},{CT0_h18+1, CT1_h18+2, CT2_h18+3},{CT0_h18+4, CT1_h18+1, CT2_h18+5},{CT0_h18+3, CT1_h18+4, CT2_h18+0},{CT0_h18+2, CT1_h18+2, CT2_h18+4},{CT0_h18+1, CT1_h18+3, CT2_h18+3},{CT0_h18+2, CT1_h18+2, CT2_h18+2},{CT0_h18+3, CT1_h18+1, CT2_h18+4},{CT0_h18+2, CT1_h18+4, CT2_h18+2},{CT0_h18+4, CT1_h18+2, CT2_h18+2},{CT0_h18+4, CT1_h18+3, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+0},{CT0_h18+2, CT1_h18+5, CT2_h18+0},{CT0_h18+2, CT1_h18+4, CT2_h18+0},{CT0_h18+1, CT1_h18+5, CT2_h18+1},{CT0_h18+1, CT1_h18+1, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+3},{CT0_h18+4, CT1_h18+1, CT2_h18+2},{CT0_h18+3, CT1_h18+2, CT2_h18+1},{CT0_h18+2, CT1_h18+4, CT2_h18+4},{CT0_h18+2, CT1_h18+3, CT2_h18+3},{CT0_h18+3, CT1_h18+2, CT2_h18+2},{CT0_h18+2, CT1_h18+2, CT2_h18+1},{CT0_h18+1, CT1_h18+3, CT2_h18+2},{CT0_h18+0, CT1_h18+0, CT2_h18+0},
				{CT0_h19+0, CT1_h19+3, CT2_h19+4},{CT0_h19+1, CT1_h19+2, CT2_h19+3},{CT0_h19+2, CT1_h19+1, CT2_h19+2},{CT0_h19+3, CT1_h19+4, CT2_h19+4},{CT0_h19+2, CT1_h19+3, CT2_h19+2},{CT0_h19+4, CT1_h19+2, CT2_h19+2},{CT0_h19+2, CT1_h19+1, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+0},{CT0_h19+3, CT1_h19+3, CT2_h19+0},{CT0_h19+2, CT1_h19+2, CT2_h19+0},{CT0_h19+4, CT1_h19+1, CT2_h19+1},{CT0_h19+2, CT1_h19+4, CT2_h19+2},{CT0_h19+2, CT1_h19+3, CT2_h19+3},{CT0_h19+2, CT1_h19+2, CT2_h19+5},{CT0_h19+1, CT1_h19+4, CT2_h19+0},{CT0_h19+4, CT1_h19+2, CT2_h19+4},{CT0_h19+2, CT1_h19+2, CT2_h19+5},{CT0_h19+3, CT1_h19+2, CT2_h19+2},{CT0_h19+2, CT1_h19+1, CT2_h19+4},{CT0_h19+1, CT1_h19+4, CT2_h19+2},{CT0_h19+4, CT1_h19+2, CT2_h19+3},{CT0_h19+2, CT1_h19+3, CT2_h19+2},{CT0_h19+3, CT1_h19+2, CT2_h19+4},{CT0_h19+2, CT1_h19+1, CT2_h19+0},{CT0_h19+1, CT1_h19+4, CT2_h19+4},{CT0_h19+4, CT1_h19+2, CT2_h19+3},{CT0_h19+3, CT1_h19+3, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+4},{CT0_h19+1, CT1_h19+5, CT2_h19+2},{CT0_h19+2, CT1_h19+0, CT2_h19+2},{CT0_h19+3, CT1_h19+4, CT2_h19+2},{CT0_h19+2, CT1_h19+5, CT2_h19+0},{CT0_h19+1, CT1_h19+2, CT2_h19+0},{CT0_h19+4, CT1_h19+4, CT2_h19+0},{CT0_h19+3, CT1_h19+2, CT2_h19+1},{CT0_h19+2, CT1_h19+2, CT2_h19+2},{CT0_h19+1, CT1_h19+2, CT2_h19+3},{CT0_h19+4, CT1_h19+1, CT2_h19+5},{CT0_h19+3, CT1_h19+4, CT2_h19+0},{CT0_h19+2, CT1_h19+2, CT2_h19+4},{CT0_h19+1, CT1_h19+3, CT2_h19+3},{CT0_h19+2, CT1_h19+2, CT2_h19+2},{CT0_h19+3, CT1_h19+1, CT2_h19+4},{CT0_h19+2, CT1_h19+4, CT2_h19+2},{CT0_h19+4, CT1_h19+2, CT2_h19+2},{CT0_h19+4, CT1_h19+3, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+0},{CT0_h19+2, CT1_h19+5, CT2_h19+0},{CT0_h19+2, CT1_h19+4, CT2_h19+0},{CT0_h19+1, CT1_h19+5, CT2_h19+1},{CT0_h19+1, CT1_h19+1, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+3},{CT0_h19+4, CT1_h19+1, CT2_h19+2},{CT0_h19+3, CT1_h19+2, CT2_h19+1},{CT0_h19+2, CT1_h19+4, CT2_h19+4},{CT0_h19+2, CT1_h19+3, CT2_h19+3},{CT0_h19+3, CT1_h19+2, CT2_h19+2},{CT0_h19+2, CT1_h19+2, CT2_h19+1},{CT0_h19+1, CT1_h19+3, CT2_h19+2},{CT0_h19+0, CT1_h19+0, CT2_h19+0},
				{CT0_h20+0, CT1_h20+3, CT2_h20+4},{CT0_h20+1, CT1_h20+2, CT2_h20+3},{CT0_h20+2, CT1_h20+1, CT2_h20+2},{CT0_h20+3, CT1_h20+4, CT2_h20+4},{CT0_h20+2, CT1_h20+3, CT2_h20+2},{CT0_h20+4, CT1_h20+2, CT2_h20+2},{CT0_h20+2, CT1_h20+1, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+0},{CT0_h20+3, CT1_h20+3, CT2_h20+0},{CT0_h20+2, CT1_h20+2, CT2_h20+0},{CT0_h20+4, CT1_h20+1, CT2_h20+1},{CT0_h20+2, CT1_h20+4, CT2_h20+2},{CT0_h20+2, CT1_h20+3, CT2_h20+3},{CT0_h20+2, CT1_h20+2, CT2_h20+5},{CT0_h20+1, CT1_h20+4, CT2_h20+0},{CT0_h20+4, CT1_h20+2, CT2_h20+4},{CT0_h20+2, CT1_h20+2, CT2_h20+5},{CT0_h20+3, CT1_h20+2, CT2_h20+2},{CT0_h20+2, CT1_h20+1, CT2_h20+4},{CT0_h20+1, CT1_h20+4, CT2_h20+2},{CT0_h20+4, CT1_h20+2, CT2_h20+3},{CT0_h20+2, CT1_h20+3, CT2_h20+2},{CT0_h20+3, CT1_h20+2, CT2_h20+4},{CT0_h20+2, CT1_h20+1, CT2_h20+0},{CT0_h20+1, CT1_h20+4, CT2_h20+4},{CT0_h20+4, CT1_h20+2, CT2_h20+3},{CT0_h20+3, CT1_h20+3, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+4},{CT0_h20+1, CT1_h20+5, CT2_h20+2},{CT0_h20+2, CT1_h20+0, CT2_h20+2},{CT0_h20+3, CT1_h20+4, CT2_h20+2},{CT0_h20+2, CT1_h20+5, CT2_h20+0},{CT0_h20+1, CT1_h20+2, CT2_h20+0},{CT0_h20+4, CT1_h20+4, CT2_h20+0},{CT0_h20+3, CT1_h20+2, CT2_h20+1},{CT0_h20+2, CT1_h20+2, CT2_h20+2},{CT0_h20+1, CT1_h20+2, CT2_h20+3},{CT0_h20+4, CT1_h20+1, CT2_h20+5},{CT0_h20+3, CT1_h20+4, CT2_h20+0},{CT0_h20+2, CT1_h20+2, CT2_h20+4},{CT0_h20+1, CT1_h20+3, CT2_h20+3},{CT0_h20+2, CT1_h20+2, CT2_h20+2},{CT0_h20+3, CT1_h20+1, CT2_h20+4},{CT0_h20+2, CT1_h20+4, CT2_h20+2},{CT0_h20+4, CT1_h20+2, CT2_h20+2},{CT0_h20+4, CT1_h20+3, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+0},{CT0_h20+2, CT1_h20+5, CT2_h20+0},{CT0_h20+2, CT1_h20+4, CT2_h20+0},{CT0_h20+1, CT1_h20+5, CT2_h20+1},{CT0_h20+1, CT1_h20+1, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+3},{CT0_h20+4, CT1_h20+1, CT2_h20+2},{CT0_h20+3, CT1_h20+2, CT2_h20+1},{CT0_h20+2, CT1_h20+4, CT2_h20+4},{CT0_h20+2, CT1_h20+3, CT2_h20+3},{CT0_h20+3, CT1_h20+2, CT2_h20+2},{CT0_h20+2, CT1_h20+2, CT2_h20+1},{CT0_h20+1, CT1_h20+3, CT2_h20+2},{CT0_h20+0, CT1_h20+0, CT2_h20+0},
				{CT0_h21+0, CT1_h21+3, CT2_h21+4},{CT0_h21+1, CT1_h21+2, CT2_h21+3},{CT0_h21+2, CT1_h21+1, CT2_h21+2},{CT0_h21+3, CT1_h21+4, CT2_h21+4},{CT0_h21+2, CT1_h21+3, CT2_h21+2},{CT0_h21+4, CT1_h21+2, CT2_h21+2},{CT0_h21+2, CT1_h21+1, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+0},{CT0_h21+3, CT1_h21+3, CT2_h21+0},{CT0_h21+2, CT1_h21+2, CT2_h21+0},{CT0_h21+4, CT1_h21+1, CT2_h21+1},{CT0_h21+2, CT1_h21+4, CT2_h21+2},{CT0_h21+2, CT1_h21+3, CT2_h21+3},{CT0_h21+2, CT1_h21+2, CT2_h21+5},{CT0_h21+1, CT1_h21+4, CT2_h21+0},{CT0_h21+4, CT1_h21+2, CT2_h21+4},{CT0_h21+2, CT1_h21+2, CT2_h21+5},{CT0_h21+3, CT1_h21+2, CT2_h21+2},{CT0_h21+2, CT1_h21+1, CT2_h21+4},{CT0_h21+1, CT1_h21+4, CT2_h21+2},{CT0_h21+4, CT1_h21+2, CT2_h21+3},{CT0_h21+2, CT1_h21+3, CT2_h21+2},{CT0_h21+3, CT1_h21+2, CT2_h21+4},{CT0_h21+2, CT1_h21+1, CT2_h21+0},{CT0_h21+1, CT1_h21+4, CT2_h21+4},{CT0_h21+4, CT1_h21+2, CT2_h21+3},{CT0_h21+3, CT1_h21+3, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+4},{CT0_h21+1, CT1_h21+5, CT2_h21+2},{CT0_h21+2, CT1_h21+0, CT2_h21+2},{CT0_h21+3, CT1_h21+4, CT2_h21+2},{CT0_h21+2, CT1_h21+5, CT2_h21+0},{CT0_h21+1, CT1_h21+2, CT2_h21+0},{CT0_h21+4, CT1_h21+4, CT2_h21+0},{CT0_h21+3, CT1_h21+2, CT2_h21+1},{CT0_h21+2, CT1_h21+2, CT2_h21+2},{CT0_h21+1, CT1_h21+2, CT2_h21+3},{CT0_h21+4, CT1_h21+1, CT2_h21+5},{CT0_h21+3, CT1_h21+4, CT2_h21+0},{CT0_h21+2, CT1_h21+2, CT2_h21+4},{CT0_h21+1, CT1_h21+3, CT2_h21+3},{CT0_h21+2, CT1_h21+2, CT2_h21+2},{CT0_h21+3, CT1_h21+1, CT2_h21+4},{CT0_h21+2, CT1_h21+4, CT2_h21+2},{CT0_h21+4, CT1_h21+2, CT2_h21+2},{CT0_h21+4, CT1_h21+3, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+0},{CT0_h21+2, CT1_h21+5, CT2_h21+0},{CT0_h21+2, CT1_h21+4, CT2_h21+0},{CT0_h21+1, CT1_h21+5, CT2_h21+1},{CT0_h21+1, CT1_h21+1, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+3},{CT0_h21+4, CT1_h21+1, CT2_h21+2},{CT0_h21+3, CT1_h21+2, CT2_h21+1},{CT0_h21+2, CT1_h21+4, CT2_h21+4},{CT0_h21+2, CT1_h21+3, CT2_h21+3},{CT0_h21+3, CT1_h21+2, CT2_h21+2},{CT0_h21+2, CT1_h21+2, CT2_h21+1},{CT0_h21+1, CT1_h21+3, CT2_h21+2},{CT0_h21+0, CT1_h21+0, CT2_h21+0},
				{CT0_h22+0, CT1_h22+3, CT2_h22+4},{CT0_h22+1, CT1_h22+2, CT2_h22+3},{CT0_h22+2, CT1_h22+1, CT2_h22+2},{CT0_h22+3, CT1_h22+4, CT2_h22+4},{CT0_h22+2, CT1_h22+3, CT2_h22+2},{CT0_h22+4, CT1_h22+2, CT2_h22+2},{CT0_h22+2, CT1_h22+1, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+0},{CT0_h22+3, CT1_h22+3, CT2_h22+0},{CT0_h22+2, CT1_h22+2, CT2_h22+0},{CT0_h22+4, CT1_h22+1, CT2_h22+1},{CT0_h22+2, CT1_h22+4, CT2_h22+2},{CT0_h22+2, CT1_h22+3, CT2_h22+3},{CT0_h22+2, CT1_h22+2, CT2_h22+5},{CT0_h22+1, CT1_h22+4, CT2_h22+0},{CT0_h22+4, CT1_h22+2, CT2_h22+4},{CT0_h22+2, CT1_h22+2, CT2_h22+5},{CT0_h22+3, CT1_h22+2, CT2_h22+2},{CT0_h22+2, CT1_h22+1, CT2_h22+4},{CT0_h22+1, CT1_h22+4, CT2_h22+2},{CT0_h22+4, CT1_h22+2, CT2_h22+3},{CT0_h22+2, CT1_h22+3, CT2_h22+2},{CT0_h22+3, CT1_h22+2, CT2_h22+4},{CT0_h22+2, CT1_h22+1, CT2_h22+0},{CT0_h22+1, CT1_h22+4, CT2_h22+4},{CT0_h22+4, CT1_h22+2, CT2_h22+3},{CT0_h22+3, CT1_h22+3, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+4},{CT0_h22+1, CT1_h22+5, CT2_h22+2},{CT0_h22+2, CT1_h22+0, CT2_h22+2},{CT0_h22+3, CT1_h22+4, CT2_h22+2},{CT0_h22+2, CT1_h22+5, CT2_h22+0},{CT0_h22+1, CT1_h22+2, CT2_h22+0},{CT0_h22+4, CT1_h22+4, CT2_h22+0},{CT0_h22+3, CT1_h22+2, CT2_h22+1},{CT0_h22+2, CT1_h22+2, CT2_h22+2},{CT0_h22+1, CT1_h22+2, CT2_h22+3},{CT0_h22+4, CT1_h22+1, CT2_h22+5},{CT0_h22+3, CT1_h22+4, CT2_h22+0},{CT0_h22+2, CT1_h22+2, CT2_h22+4},{CT0_h22+1, CT1_h22+3, CT2_h22+3},{CT0_h22+2, CT1_h22+2, CT2_h22+2},{CT0_h22+3, CT1_h22+1, CT2_h22+4},{CT0_h22+2, CT1_h22+4, CT2_h22+2},{CT0_h22+4, CT1_h22+2, CT2_h22+2},{CT0_h22+4, CT1_h22+3, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+0},{CT0_h22+2, CT1_h22+5, CT2_h22+0},{CT0_h22+2, CT1_h22+4, CT2_h22+0},{CT0_h22+1, CT1_h22+5, CT2_h22+1},{CT0_h22+1, CT1_h22+1, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+3},{CT0_h22+4, CT1_h22+1, CT2_h22+2},{CT0_h22+3, CT1_h22+2, CT2_h22+1},{CT0_h22+2, CT1_h22+4, CT2_h22+4},{CT0_h22+2, CT1_h22+3, CT2_h22+3},{CT0_h22+3, CT1_h22+2, CT2_h22+2},{CT0_h22+2, CT1_h22+2, CT2_h22+1},{CT0_h22+1, CT1_h22+3, CT2_h22+2},{CT0_h22+0, CT1_h22+0, CT2_h22+0},
				{CT0_h23+0, CT1_h23+3, CT2_h23+4},{CT0_h23+1, CT1_h23+2, CT2_h23+3},{CT0_h23+2, CT1_h23+1, CT2_h23+2},{CT0_h23+3, CT1_h23+4, CT2_h23+4},{CT0_h23+2, CT1_h23+3, CT2_h23+2},{CT0_h23+4, CT1_h23+2, CT2_h23+2},{CT0_h23+2, CT1_h23+1, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+0},{CT0_h23+3, CT1_h23+3, CT2_h23+0},{CT0_h23+2, CT1_h23+2, CT2_h23+0},{CT0_h23+4, CT1_h23+1, CT2_h23+1},{CT0_h23+2, CT1_h23+4, CT2_h23+2},{CT0_h23+2, CT1_h23+3, CT2_h23+3},{CT0_h23+2, CT1_h23+2, CT2_h23+5},{CT0_h23+1, CT1_h23+4, CT2_h23+0},{CT0_h23+4, CT1_h23+2, CT2_h23+4},{CT0_h23+2, CT1_h23+2, CT2_h23+5},{CT0_h23+3, CT1_h23+2, CT2_h23+2},{CT0_h23+2, CT1_h23+1, CT2_h23+4},{CT0_h23+1, CT1_h23+4, CT2_h23+2},{CT0_h23+4, CT1_h23+2, CT2_h23+3},{CT0_h23+2, CT1_h23+3, CT2_h23+2},{CT0_h23+3, CT1_h23+2, CT2_h23+4},{CT0_h23+2, CT1_h23+1, CT2_h23+0},{CT0_h23+1, CT1_h23+4, CT2_h23+4},{CT0_h23+4, CT1_h23+2, CT2_h23+3},{CT0_h23+3, CT1_h23+3, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+4},{CT0_h23+1, CT1_h23+5, CT2_h23+2},{CT0_h23+2, CT1_h23+0, CT2_h23+2},{CT0_h23+3, CT1_h23+4, CT2_h23+2},{CT0_h23+2, CT1_h23+5, CT2_h23+0},{CT0_h23+1, CT1_h23+2, CT2_h23+0},{CT0_h23+4, CT1_h23+4, CT2_h23+0},{CT0_h23+3, CT1_h23+2, CT2_h23+1},{CT0_h23+2, CT1_h23+2, CT2_h23+2},{CT0_h23+1, CT1_h23+2, CT2_h23+3},{CT0_h23+4, CT1_h23+1, CT2_h23+5},{CT0_h23+3, CT1_h23+4, CT2_h23+0},{CT0_h23+2, CT1_h23+2, CT2_h23+4},{CT0_h23+1, CT1_h23+3, CT2_h23+3},{CT0_h23+2, CT1_h23+2, CT2_h23+2},{CT0_h23+3, CT1_h23+1, CT2_h23+4},{CT0_h23+2, CT1_h23+4, CT2_h23+2},{CT0_h23+4, CT1_h23+2, CT2_h23+2},{CT0_h23+4, CT1_h23+3, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+0},{CT0_h23+2, CT1_h23+5, CT2_h23+0},{CT0_h23+2, CT1_h23+4, CT2_h23+0},{CT0_h23+1, CT1_h23+5, CT2_h23+1},{CT0_h23+1, CT1_h23+1, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+3},{CT0_h23+4, CT1_h23+1, CT2_h23+2},{CT0_h23+3, CT1_h23+2, CT2_h23+1},{CT0_h23+2, CT1_h23+4, CT2_h23+4},{CT0_h23+2, CT1_h23+3, CT2_h23+3},{CT0_h23+3, CT1_h23+2, CT2_h23+2},{CT0_h23+2, CT1_h23+2, CT2_h23+1},{CT0_h23+1, CT1_h23+3, CT2_h23+2},{CT0_h23+0, CT1_h23+0, CT2_h23+0},
				{CT0_h24+0, CT1_h24+3, CT2_h24+4},{CT0_h24+1, CT1_h24+2, CT2_h24+3},{CT0_h24+2, CT1_h24+1, CT2_h24+2},{CT0_h24+3, CT1_h24+4, CT2_h24+4},{CT0_h24+2, CT1_h24+3, CT2_h24+2},{CT0_h24+4, CT1_h24+2, CT2_h24+2},{CT0_h24+2, CT1_h24+1, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+0},{CT0_h24+3, CT1_h24+3, CT2_h24+0},{CT0_h24+2, CT1_h24+2, CT2_h24+0},{CT0_h24+4, CT1_h24+1, CT2_h24+1},{CT0_h24+2, CT1_h24+4, CT2_h24+2},{CT0_h24+2, CT1_h24+3, CT2_h24+3},{CT0_h24+2, CT1_h24+2, CT2_h24+5},{CT0_h24+1, CT1_h24+4, CT2_h24+0},{CT0_h24+4, CT1_h24+2, CT2_h24+4},{CT0_h24+2, CT1_h24+2, CT2_h24+5},{CT0_h24+3, CT1_h24+2, CT2_h24+2},{CT0_h24+2, CT1_h24+1, CT2_h24+4},{CT0_h24+1, CT1_h24+4, CT2_h24+2},{CT0_h24+4, CT1_h24+2, CT2_h24+3},{CT0_h24+2, CT1_h24+3, CT2_h24+2},{CT0_h24+3, CT1_h24+2, CT2_h24+4},{CT0_h24+2, CT1_h24+1, CT2_h24+0},{CT0_h24+1, CT1_h24+4, CT2_h24+4},{CT0_h24+4, CT1_h24+2, CT2_h24+3},{CT0_h24+3, CT1_h24+3, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+4},{CT0_h24+1, CT1_h24+5, CT2_h24+2},{CT0_h24+2, CT1_h24+0, CT2_h24+2},{CT0_h24+3, CT1_h24+4, CT2_h24+2},{CT0_h24+2, CT1_h24+5, CT2_h24+0},{CT0_h24+1, CT1_h24+2, CT2_h24+0},{CT0_h24+4, CT1_h24+4, CT2_h24+0},{CT0_h24+3, CT1_h24+2, CT2_h24+1},{CT0_h24+2, CT1_h24+2, CT2_h24+2},{CT0_h24+1, CT1_h24+2, CT2_h24+3},{CT0_h24+4, CT1_h24+1, CT2_h24+5},{CT0_h24+3, CT1_h24+4, CT2_h24+0},{CT0_h24+2, CT1_h24+2, CT2_h24+4},{CT0_h24+1, CT1_h24+3, CT2_h24+3},{CT0_h24+2, CT1_h24+2, CT2_h24+2},{CT0_h24+3, CT1_h24+1, CT2_h24+4},{CT0_h24+2, CT1_h24+4, CT2_h24+2},{CT0_h24+4, CT1_h24+2, CT2_h24+2},{CT0_h24+4, CT1_h24+3, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+0},{CT0_h24+2, CT1_h24+5, CT2_h24+0},{CT0_h24+2, CT1_h24+4, CT2_h24+0},{CT0_h24+1, CT1_h24+5, CT2_h24+1},{CT0_h24+1, CT1_h24+1, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+3},{CT0_h24+4, CT1_h24+1, CT2_h24+2},{CT0_h24+3, CT1_h24+2, CT2_h24+1},{CT0_h24+2, CT1_h24+4, CT2_h24+4},{CT0_h24+2, CT1_h24+3, CT2_h24+3},{CT0_h24+3, CT1_h24+2, CT2_h24+2},{CT0_h24+2, CT1_h24+2, CT2_h24+1},{CT0_h24+1, CT1_h24+3, CT2_h24+2},{CT0_h24+0, CT1_h24+0, CT2_h24+0},
				/*{CT0_h25+0, CT1_h25+3, CT2_h25+4},{CT0_h25+1, CT1_h25+2, CT2_h25+3},{CT0_h25+2, CT1_h25+1, CT2_h25+2},{CT0_h25+3, CT1_h25+4, CT2_h25+4},{CT0_h25+2, CT1_h25+3, CT2_h25+2},{CT0_h25+4, CT1_h25+2, CT2_h25+2},{CT0_h25+2, CT1_h25+1, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+0},{CT0_h25+3, CT1_h25+3, CT2_h25+0},{CT0_h25+2, CT1_h25+2, CT2_h25+0},{CT0_h25+4, CT1_h25+1, CT2_h25+1},{CT0_h25+2, CT1_h25+4, CT2_h25+2},{CT0_h25+2, CT1_h25+3, CT2_h25+3},{CT0_h25+2, CT1_h25+2, CT2_h25+5},{CT0_h25+1, CT1_h25+4, CT2_h25+0},{CT0_h25+4, CT1_h25+2, CT2_h25+4},{CT0_h25+2, CT1_h25+2, CT2_h25+5},{CT0_h25+3, CT1_h25+2, CT2_h25+2},{CT0_h25+2, CT1_h25+1, CT2_h25+4},{CT0_h25+1, CT1_h25+4, CT2_h25+2},{CT0_h25+4, CT1_h25+2, CT2_h25+3},{CT0_h25+2, CT1_h25+3, CT2_h25+2},{CT0_h25+3, CT1_h25+2, CT2_h25+4},{CT0_h25+2, CT1_h25+1, CT2_h25+0},{CT0_h25+1, CT1_h25+4, CT2_h25+4},{CT0_h25+4, CT1_h25+2, CT2_h25+3},{CT0_h25+3, CT1_h25+3, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+4},{CT0_h25+1, CT1_h25+5, CT2_h25+2},{CT0_h25+2, CT1_h25+0, CT2_h25+2},{CT0_h25+3, CT1_h25+4, CT2_h25+2},{CT0_h25+2, CT1_h25+5, CT2_h25+0},{CT0_h25+1, CT1_h25+2, CT2_h25+0},{CT0_h25+4, CT1_h25+4, CT2_h25+0},{CT0_h25+3, CT1_h25+2, CT2_h25+1},{CT0_h25+2, CT1_h25+2, CT2_h25+2},{CT0_h25+1, CT1_h25+2, CT2_h25+3},{CT0_h25+4, CT1_h25+1, CT2_h25+5},{CT0_h25+3, CT1_h25+4, CT2_h25+0},{CT0_h25+2, CT1_h25+2, CT2_h25+4},{CT0_h25+1, CT1_h25+3, CT2_h25+3},{CT0_h25+2, CT1_h25+2, CT2_h25+2},{CT0_h25+3, CT1_h25+1, CT2_h25+4},{CT0_h25+2, CT1_h25+4, CT2_h25+2},{CT0_h25+4, CT1_h25+2, CT2_h25+2},{CT0_h25+4, CT1_h25+3, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+0},{CT0_h25+2, CT1_h25+5, CT2_h25+0},{CT0_h25+2, CT1_h25+4, CT2_h25+0},{CT0_h25+1, CT1_h25+5, CT2_h25+1},{CT0_h25+1, CT1_h25+1, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+3},{CT0_h25+4, CT1_h25+1, CT2_h25+2},{CT0_h25+3, CT1_h25+2, CT2_h25+1},{CT0_h25+2, CT1_h25+4, CT2_h25+4},{CT0_h25+2, CT1_h25+3, CT2_h25+3},{CT0_h25+3, CT1_h25+2, CT2_h25+2},{CT0_h25+2, CT1_h25+2, CT2_h25+1},{CT0_h25+1, CT1_h25+3, CT2_h25+2},{CT0_h25+0, CT1_h25+0, CT2_h25+0},
				{CT0_h26+0, CT1_h26+3, CT2_h26+4},{CT0_h26+1, CT1_h26+2, CT2_h26+3},{CT0_h26+2, CT1_h26+1, CT2_h26+2},{CT0_h26+3, CT1_h26+4, CT2_h26+4},{CT0_h26+2, CT1_h26+3, CT2_h26+2},{CT0_h26+4, CT1_h26+2, CT2_h26+2},{CT0_h26+2, CT1_h26+1, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+0},{CT0_h26+3, CT1_h26+3, CT2_h26+0},{CT0_h26+2, CT1_h26+2, CT2_h26+0},{CT0_h26+4, CT1_h26+1, CT2_h26+1},{CT0_h26+2, CT1_h26+4, CT2_h26+2},{CT0_h26+2, CT1_h26+3, CT2_h26+3},{CT0_h26+2, CT1_h26+2, CT2_h26+5},{CT0_h26+1, CT1_h26+4, CT2_h26+0},{CT0_h26+4, CT1_h26+2, CT2_h26+4},{CT0_h26+2, CT1_h26+2, CT2_h26+5},{CT0_h26+3, CT1_h26+2, CT2_h26+2},{CT0_h26+2, CT1_h26+1, CT2_h26+4},{CT0_h26+1, CT1_h26+4, CT2_h26+2},{CT0_h26+4, CT1_h26+2, CT2_h26+3},{CT0_h26+2, CT1_h26+3, CT2_h26+2},{CT0_h26+3, CT1_h26+2, CT2_h26+4},{CT0_h26+2, CT1_h26+1, CT2_h26+0},{CT0_h26+1, CT1_h26+4, CT2_h26+4},{CT0_h26+4, CT1_h26+2, CT2_h26+3},{CT0_h26+3, CT1_h26+3, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+4},{CT0_h26+1, CT1_h26+5, CT2_h26+2},{CT0_h26+2, CT1_h26+0, CT2_h26+2},{CT0_h26+3, CT1_h26+4, CT2_h26+2},{CT0_h26+2, CT1_h26+5, CT2_h26+0},{CT0_h26+1, CT1_h26+2, CT2_h26+0},{CT0_h26+4, CT1_h26+4, CT2_h26+0},{CT0_h26+3, CT1_h26+2, CT2_h26+1},{CT0_h26+2, CT1_h26+2, CT2_h26+2},{CT0_h26+1, CT1_h26+2, CT2_h26+3},{CT0_h26+4, CT1_h26+1, CT2_h26+5},{CT0_h26+3, CT1_h26+4, CT2_h26+0},{CT0_h26+2, CT1_h26+2, CT2_h26+4},{CT0_h26+1, CT1_h26+3, CT2_h26+3},{CT0_h26+2, CT1_h26+2, CT2_h26+2},{CT0_h26+3, CT1_h26+1, CT2_h26+4},{CT0_h26+2, CT1_h26+4, CT2_h26+2},{CT0_h26+4, CT1_h26+2, CT2_h26+2},{CT0_h26+4, CT1_h26+3, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+0},{CT0_h26+2, CT1_h26+5, CT2_h26+0},{CT0_h26+2, CT1_h26+4, CT2_h26+0},{CT0_h26+1, CT1_h26+5, CT2_h26+1},{CT0_h26+1, CT1_h26+1, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+3},{CT0_h26+4, CT1_h26+1, CT2_h26+2},{CT0_h26+3, CT1_h26+2, CT2_h26+1},{CT0_h26+2, CT1_h26+4, CT2_h26+4},{CT0_h26+2, CT1_h26+3, CT2_h26+3},{CT0_h26+3, CT1_h26+2, CT2_h26+2},{CT0_h26+2, CT1_h26+2, CT2_h26+1},{CT0_h26+1, CT1_h26+3, CT2_h26+2},{CT0_h26+0, CT1_h26+0, CT2_h26+0},
				{CT0_h27+0, CT1_h27+3, CT2_h27+4},{CT0_h27+1, CT1_h27+2, CT2_h27+3},{CT0_h27+2, CT1_h27+1, CT2_h27+2},{CT0_h27+3, CT1_h27+4, CT2_h27+4},{CT0_h27+2, CT1_h27+3, CT2_h27+2},{CT0_h27+4, CT1_h27+2, CT2_h27+2},{CT0_h27+2, CT1_h27+1, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+0},{CT0_h27+3, CT1_h27+3, CT2_h27+0},{CT0_h27+2, CT1_h27+2, CT2_h27+0},{CT0_h27+4, CT1_h27+1, CT2_h27+1},{CT0_h27+2, CT1_h27+4, CT2_h27+2},{CT0_h27+2, CT1_h27+3, CT2_h27+3},{CT0_h27+2, CT1_h27+2, CT2_h27+5},{CT0_h27+1, CT1_h27+4, CT2_h27+0},{CT0_h27+4, CT1_h27+2, CT2_h27+4},{CT0_h27+2, CT1_h27+2, CT2_h27+5},{CT0_h27+3, CT1_h27+2, CT2_h27+2},{CT0_h27+2, CT1_h27+1, CT2_h27+4},{CT0_h27+1, CT1_h27+4, CT2_h27+2},{CT0_h27+4, CT1_h27+2, CT2_h27+3},{CT0_h27+2, CT1_h27+3, CT2_h27+2},{CT0_h27+3, CT1_h27+2, CT2_h27+4},{CT0_h27+2, CT1_h27+1, CT2_h27+0},{CT0_h27+1, CT1_h27+4, CT2_h27+4},{CT0_h27+4, CT1_h27+2, CT2_h27+3},{CT0_h27+3, CT1_h27+3, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+4},{CT0_h27+1, CT1_h27+5, CT2_h27+2},{CT0_h27+2, CT1_h27+0, CT2_h27+2},{CT0_h27+3, CT1_h27+4, CT2_h27+2},{CT0_h27+2, CT1_h27+5, CT2_h27+0},{CT0_h27+1, CT1_h27+2, CT2_h27+0},{CT0_h27+4, CT1_h27+4, CT2_h27+0},{CT0_h27+3, CT1_h27+2, CT2_h27+1},{CT0_h27+2, CT1_h27+2, CT2_h27+2},{CT0_h27+1, CT1_h27+2, CT2_h27+3},{CT0_h27+4, CT1_h27+1, CT2_h27+5},{CT0_h27+3, CT1_h27+4, CT2_h27+0},{CT0_h27+2, CT1_h27+2, CT2_h27+4},{CT0_h27+1, CT1_h27+3, CT2_h27+3},{CT0_h27+2, CT1_h27+2, CT2_h27+2},{CT0_h27+3, CT1_h27+1, CT2_h27+4},{CT0_h27+2, CT1_h27+4, CT2_h27+2},{CT0_h27+4, CT1_h27+2, CT2_h27+2},{CT0_h27+4, CT1_h27+3, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+0},{CT0_h27+2, CT1_h27+5, CT2_h27+0},{CT0_h27+2, CT1_h27+4, CT2_h27+0},{CT0_h27+1, CT1_h27+5, CT2_h27+1},{CT0_h27+1, CT1_h27+1, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+3},{CT0_h27+4, CT1_h27+1, CT2_h27+2},{CT0_h27+3, CT1_h27+2, CT2_h27+1},{CT0_h27+2, CT1_h27+4, CT2_h27+4},{CT0_h27+2, CT1_h27+3, CT2_h27+3},{CT0_h27+3, CT1_h27+2, CT2_h27+2},{CT0_h27+2, CT1_h27+2, CT2_h27+1},{CT0_h27+1, CT1_h27+3, CT2_h27+2},{CT0_h27+0, CT1_h27+0, CT2_h27+0},
				{CT0_h28+0, CT1_h28+3, CT2_h28+4},{CT0_h28+1, CT1_h28+2, CT2_h28+3},{CT0_h28+2, CT1_h28+1, CT2_h28+2},{CT0_h28+3, CT1_h28+4, CT2_h28+4},{CT0_h28+2, CT1_h28+3, CT2_h28+2},{CT0_h28+4, CT1_h28+2, CT2_h28+2},{CT0_h28+2, CT1_h28+1, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+0},{CT0_h28+3, CT1_h28+3, CT2_h28+0},{CT0_h28+2, CT1_h28+2, CT2_h28+0},{CT0_h28+4, CT1_h28+1, CT2_h28+1},{CT0_h28+2, CT1_h28+4, CT2_h28+2},{CT0_h28+2, CT1_h28+3, CT2_h28+3},{CT0_h28+2, CT1_h28+2, CT2_h28+5},{CT0_h28+1, CT1_h28+4, CT2_h28+0},{CT0_h28+4, CT1_h28+2, CT2_h28+4},{CT0_h28+2, CT1_h28+2, CT2_h28+5},{CT0_h28+3, CT1_h28+2, CT2_h28+2},{CT0_h28+2, CT1_h28+1, CT2_h28+4},{CT0_h28+1, CT1_h28+4, CT2_h28+2},{CT0_h28+4, CT1_h28+2, CT2_h28+3},{CT0_h28+2, CT1_h28+3, CT2_h28+2},{CT0_h28+3, CT1_h28+2, CT2_h28+4},{CT0_h28+2, CT1_h28+1, CT2_h28+0},{CT0_h28+1, CT1_h28+4, CT2_h28+4},{CT0_h28+4, CT1_h28+2, CT2_h28+3},{CT0_h28+3, CT1_h28+3, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+4},{CT0_h28+1, CT1_h28+5, CT2_h28+2},{CT0_h28+2, CT1_h28+0, CT2_h28+2},{CT0_h28+3, CT1_h28+4, CT2_h28+2},{CT0_h28+2, CT1_h28+5, CT2_h28+0},{CT0_h28+1, CT1_h28+2, CT2_h28+0},{CT0_h28+4, CT1_h28+4, CT2_h28+0},{CT0_h28+3, CT1_h28+2, CT2_h28+1},{CT0_h28+2, CT1_h28+2, CT2_h28+2},{CT0_h28+1, CT1_h28+2, CT2_h28+3},{CT0_h28+4, CT1_h28+1, CT2_h28+5},{CT0_h28+3, CT1_h28+4, CT2_h28+0},{CT0_h28+2, CT1_h28+2, CT2_h28+4},{CT0_h28+1, CT1_h28+3, CT2_h28+3},{CT0_h28+2, CT1_h28+2, CT2_h28+2},{CT0_h28+3, CT1_h28+1, CT2_h28+4},{CT0_h28+2, CT1_h28+4, CT2_h28+2},{CT0_h28+4, CT1_h28+2, CT2_h28+2},{CT0_h28+4, CT1_h28+3, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+0},{CT0_h28+2, CT1_h28+5, CT2_h28+0},{CT0_h28+2, CT1_h28+4, CT2_h28+0},{CT0_h28+1, CT1_h28+5, CT2_h28+1},{CT0_h28+1, CT1_h28+1, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+3},{CT0_h28+4, CT1_h28+1, CT2_h28+2},{CT0_h28+3, CT1_h28+2, CT2_h28+1},{CT0_h28+2, CT1_h28+4, CT2_h28+4},{CT0_h28+2, CT1_h28+3, CT2_h28+3},{CT0_h28+3, CT1_h28+2, CT2_h28+2},{CT0_h28+2, CT1_h28+2, CT2_h28+1},{CT0_h28+1, CT1_h28+3, CT2_h28+2},{CT0_h28+0, CT1_h28+0, CT2_h28+0},
				{CT0_h29+0, CT1_h29+3, CT2_h29+4},{CT0_h29+1, CT1_h29+2, CT2_h29+3},{CT0_h29+2, CT1_h29+1, CT2_h29+2},{CT0_h29+3, CT1_h29+4, CT2_h29+4},{CT0_h29+2, CT1_h29+3, CT2_h29+2},{CT0_h29+4, CT1_h29+2, CT2_h29+2},{CT0_h29+2, CT1_h29+1, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+0},{CT0_h29+3, CT1_h29+3, CT2_h29+0},{CT0_h29+2, CT1_h29+2, CT2_h29+0},{CT0_h29+4, CT1_h29+1, CT2_h29+1},{CT0_h29+2, CT1_h29+4, CT2_h29+2},{CT0_h29+2, CT1_h29+3, CT2_h29+3},{CT0_h29+2, CT1_h29+2, CT2_h29+5},{CT0_h29+1, CT1_h29+4, CT2_h29+0},{CT0_h29+4, CT1_h29+2, CT2_h29+4},{CT0_h29+2, CT1_h29+2, CT2_h29+5},{CT0_h29+3, CT1_h29+2, CT2_h29+2},{CT0_h29+2, CT1_h29+1, CT2_h29+4},{CT0_h29+1, CT1_h29+4, CT2_h29+2},{CT0_h29+4, CT1_h29+2, CT2_h29+3},{CT0_h29+2, CT1_h29+3, CT2_h29+2},{CT0_h29+3, CT1_h29+2, CT2_h29+4},{CT0_h29+2, CT1_h29+1, CT2_h29+0},{CT0_h29+1, CT1_h29+4, CT2_h29+4},{CT0_h29+4, CT1_h29+2, CT2_h29+3},{CT0_h29+3, CT1_h29+3, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+4},{CT0_h29+1, CT1_h29+5, CT2_h29+2},{CT0_h29+2, CT1_h29+0, CT2_h29+2},{CT0_h29+3, CT1_h29+4, CT2_h29+2},{CT0_h29+2, CT1_h29+5, CT2_h29+0},{CT0_h29+1, CT1_h29+2, CT2_h29+0},{CT0_h29+4, CT1_h29+4, CT2_h29+0},{CT0_h29+3, CT1_h29+2, CT2_h29+1},{CT0_h29+2, CT1_h29+2, CT2_h29+2},{CT0_h29+1, CT1_h29+2, CT2_h29+3},{CT0_h29+4, CT1_h29+1, CT2_h29+5},{CT0_h29+3, CT1_h29+4, CT2_h29+0},{CT0_h29+2, CT1_h29+2, CT2_h29+4},{CT0_h29+1, CT1_h29+3, CT2_h29+3},{CT0_h29+2, CT1_h29+2, CT2_h29+2},{CT0_h29+3, CT1_h29+1, CT2_h29+4},{CT0_h29+2, CT1_h29+4, CT2_h29+2},{CT0_h29+4, CT1_h29+2, CT2_h29+2},{CT0_h29+4, CT1_h29+3, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+0},{CT0_h29+2, CT1_h29+5, CT2_h29+0},{CT0_h29+2, CT1_h29+4, CT2_h29+0},{CT0_h29+1, CT1_h29+5, CT2_h29+1},{CT0_h29+1, CT1_h29+1, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+3},{CT0_h29+4, CT1_h29+1, CT2_h29+2},{CT0_h29+3, CT1_h29+2, CT2_h29+1},{CT0_h29+2, CT1_h29+4, CT2_h29+4},{CT0_h29+2, CT1_h29+3, CT2_h29+3},{CT0_h29+3, CT1_h29+2, CT2_h29+2},{CT0_h29+2, CT1_h29+2, CT2_h29+1},{CT0_h29+1, CT1_h29+3, CT2_h29+2},{CT0_h29+0, CT1_h29+0, CT2_h29+0},
				{CT0_h30+0, CT1_h30+3, CT2_h30+4},{CT0_h30+1, CT1_h30+2, CT2_h30+3},{CT0_h30+2, CT1_h30+1, CT2_h30+2},{CT0_h30+3, CT1_h30+4, CT2_h30+4},{CT0_h30+2, CT1_h30+3, CT2_h30+2},{CT0_h30+4, CT1_h30+2, CT2_h30+2},{CT0_h30+2, CT1_h30+1, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+0},{CT0_h30+3, CT1_h30+3, CT2_h30+0},{CT0_h30+2, CT1_h30+2, CT2_h30+0},{CT0_h30+4, CT1_h30+1, CT2_h30+1},{CT0_h30+2, CT1_h30+4, CT2_h30+2},{CT0_h30+2, CT1_h30+3, CT2_h30+3},{CT0_h30+2, CT1_h30+2, CT2_h30+5},{CT0_h30+1, CT1_h30+4, CT2_h30+0},{CT0_h30+4, CT1_h30+2, CT2_h30+4},{CT0_h30+2, CT1_h30+2, CT2_h30+5},{CT0_h30+3, CT1_h30+2, CT2_h30+2},{CT0_h30+2, CT1_h30+1, CT2_h30+4},{CT0_h30+1, CT1_h30+4, CT2_h30+2},{CT0_h30+4, CT1_h30+2, CT2_h30+3},{CT0_h30+2, CT1_h30+3, CT2_h30+2},{CT0_h30+3, CT1_h30+2, CT2_h30+4},{CT0_h30+2, CT1_h30+1, CT2_h30+0},{CT0_h30+1, CT1_h30+4, CT2_h30+4},{CT0_h30+4, CT1_h30+2, CT2_h30+3},{CT0_h30+3, CT1_h30+3, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+4},{CT0_h30+1, CT1_h30+5, CT2_h30+2},{CT0_h30+2, CT1_h30+0, CT2_h30+2},{CT0_h30+3, CT1_h30+4, CT2_h30+2},{CT0_h30+2, CT1_h30+5, CT2_h30+0},{CT0_h30+1, CT1_h30+2, CT2_h30+0},{CT0_h30+4, CT1_h30+4, CT2_h30+0},{CT0_h30+3, CT1_h30+2, CT2_h30+1},{CT0_h30+2, CT1_h30+2, CT2_h30+2},{CT0_h30+1, CT1_h30+2, CT2_h30+3},{CT0_h30+4, CT1_h30+1, CT2_h30+5},{CT0_h30+3, CT1_h30+4, CT2_h30+0},{CT0_h30+2, CT1_h30+2, CT2_h30+4},{CT0_h30+1, CT1_h30+3, CT2_h30+3},{CT0_h30+2, CT1_h30+2, CT2_h30+2},{CT0_h30+3, CT1_h30+1, CT2_h30+4},{CT0_h30+2, CT1_h30+4, CT2_h30+2},{CT0_h30+4, CT1_h30+2, CT2_h30+2},{CT0_h30+4, CT1_h30+3, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+0},{CT0_h30+2, CT1_h30+5, CT2_h30+0},{CT0_h30+2, CT1_h30+4, CT2_h30+0},{CT0_h30+1, CT1_h30+5, CT2_h30+1},{CT0_h30+1, CT1_h30+1, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+3},{CT0_h30+4, CT1_h30+1, CT2_h30+2},{CT0_h30+3, CT1_h30+2, CT2_h30+1},{CT0_h30+2, CT1_h30+4, CT2_h30+4},{CT0_h30+2, CT1_h30+3, CT2_h30+3},{CT0_h30+3, CT1_h30+2, CT2_h30+2},{CT0_h30+2, CT1_h30+2, CT2_h30+1},{CT0_h30+1, CT1_h30+3, CT2_h30+2},{CT0_h30+0, CT1_h30+0, CT2_h30+0},
				{CT0_h31+0, CT1_h31+3, CT2_h31+4},{CT0_h31+1, CT1_h31+2, CT2_h31+3},{CT0_h31+2, CT1_h31+1, CT2_h31+2},{CT0_h31+3, CT1_h31+4, CT2_h31+4},{CT0_h31+2, CT1_h31+3, CT2_h31+2},{CT0_h31+4, CT1_h31+2, CT2_h31+2},{CT0_h31+2, CT1_h31+1, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+0},{CT0_h31+3, CT1_h31+3, CT2_h31+0},{CT0_h31+2, CT1_h31+2, CT2_h31+0},{CT0_h31+4, CT1_h31+1, CT2_h31+1},{CT0_h31+2, CT1_h31+4, CT2_h31+2},{CT0_h31+2, CT1_h31+3, CT2_h31+3},{CT0_h31+2, CT1_h31+2, CT2_h31+5},{CT0_h31+1, CT1_h31+4, CT2_h31+0},{CT0_h31+4, CT1_h31+2, CT2_h31+4},{CT0_h31+2, CT1_h31+2, CT2_h31+5},{CT0_h31+3, CT1_h31+2, CT2_h31+2},{CT0_h31+2, CT1_h31+1, CT2_h31+4},{CT0_h31+1, CT1_h31+4, CT2_h31+2},{CT0_h31+4, CT1_h31+2, CT2_h31+3},{CT0_h31+2, CT1_h31+3, CT2_h31+2},{CT0_h31+3, CT1_h31+2, CT2_h31+4},{CT0_h31+2, CT1_h31+1, CT2_h31+0},{CT0_h31+1, CT1_h31+4, CT2_h31+4},{CT0_h31+4, CT1_h31+2, CT2_h31+3},{CT0_h31+3, CT1_h31+3, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+4},{CT0_h31+1, CT1_h31+5, CT2_h31+2},{CT0_h31+2, CT1_h31+0, CT2_h31+2},{CT0_h31+3, CT1_h31+4, CT2_h31+2},{CT0_h31+2, CT1_h31+5, CT2_h31+0},{CT0_h31+1, CT1_h31+2, CT2_h31+0},{CT0_h31+4, CT1_h31+4, CT2_h31+0},{CT0_h31+3, CT1_h31+2, CT2_h31+1},{CT0_h31+2, CT1_h31+2, CT2_h31+2},{CT0_h31+1, CT1_h31+2, CT2_h31+3},{CT0_h31+4, CT1_h31+1, CT2_h31+5},{CT0_h31+3, CT1_h31+4, CT2_h31+0},{CT0_h31+2, CT1_h31+2, CT2_h31+4},{CT0_h31+1, CT1_h31+3, CT2_h31+3},{CT0_h31+2, CT1_h31+2, CT2_h31+2},{CT0_h31+3, CT1_h31+1, CT2_h31+4},{CT0_h31+2, CT1_h31+4, CT2_h31+2},{CT0_h31+4, CT1_h31+2, CT2_h31+2},{CT0_h31+4, CT1_h31+3, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+0},{CT0_h31+2, CT1_h31+5, CT2_h31+0},{CT0_h31+2, CT1_h31+4, CT2_h31+0},{CT0_h31+1, CT1_h31+5, CT2_h31+1},{CT0_h31+1, CT1_h31+1, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+3},{CT0_h31+4, CT1_h31+1, CT2_h31+2},{CT0_h31+3, CT1_h31+2, CT2_h31+1},{CT0_h31+2, CT1_h31+4, CT2_h31+4},{CT0_h31+2, CT1_h31+3, CT2_h31+3},{CT0_h31+3, CT1_h31+2, CT2_h31+2},{CT0_h31+2, CT1_h31+2, CT2_h31+1},{CT0_h31+1, CT1_h31+3, CT2_h31+2},{CT0_h31+0, CT1_h31+0, CT2_h31+0},
				{CT0_h32+0, CT1_h32+3, CT2_h32+4},{CT0_h32+1, CT1_h32+2, CT2_h32+3},{CT0_h32+2, CT1_h32+1, CT2_h32+2},{CT0_h32+3, CT1_h32+4, CT2_h32+4},{CT0_h32+2, CT1_h32+3, CT2_h32+2},{CT0_h32+4, CT1_h32+2, CT2_h32+2},{CT0_h32+2, CT1_h32+1, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+0},{CT0_h32+3, CT1_h32+3, CT2_h32+0},{CT0_h32+2, CT1_h32+2, CT2_h32+0},{CT0_h32+4, CT1_h32+1, CT2_h32+1},{CT0_h32+2, CT1_h32+4, CT2_h32+2},{CT0_h32+2, CT1_h32+3, CT2_h32+3},{CT0_h32+2, CT1_h32+2, CT2_h32+5},{CT0_h32+1, CT1_h32+4, CT2_h32+0},{CT0_h32+4, CT1_h32+2, CT2_h32+4},{CT0_h32+2, CT1_h32+2, CT2_h32+5},{CT0_h32+3, CT1_h32+2, CT2_h32+2},{CT0_h32+2, CT1_h32+1, CT2_h32+4},{CT0_h32+1, CT1_h32+4, CT2_h32+2},{CT0_h32+4, CT1_h32+2, CT2_h32+3},{CT0_h32+2, CT1_h32+3, CT2_h32+2},{CT0_h32+3, CT1_h32+2, CT2_h32+4},{CT0_h32+2, CT1_h32+1, CT2_h32+0},{CT0_h32+1, CT1_h32+4, CT2_h32+4},{CT0_h32+4, CT1_h32+2, CT2_h32+3},{CT0_h32+3, CT1_h32+3, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+4},{CT0_h32+1, CT1_h32+5, CT2_h32+2},{CT0_h32+2, CT1_h32+0, CT2_h32+2},{CT0_h32+3, CT1_h32+4, CT2_h32+2},{CT0_h32+2, CT1_h32+5, CT2_h32+0},{CT0_h32+1, CT1_h32+2, CT2_h32+0},{CT0_h32+4, CT1_h32+4, CT2_h32+0},{CT0_h32+3, CT1_h32+2, CT2_h32+1},{CT0_h32+2, CT1_h32+2, CT2_h32+2},{CT0_h32+1, CT1_h32+2, CT2_h32+3},{CT0_h32+4, CT1_h32+1, CT2_h32+5},{CT0_h32+3, CT1_h32+4, CT2_h32+0},{CT0_h32+2, CT1_h32+2, CT2_h32+4},{CT0_h32+1, CT1_h32+3, CT2_h32+3},{CT0_h32+2, CT1_h32+2, CT2_h32+2},{CT0_h32+3, CT1_h32+1, CT2_h32+4},{CT0_h32+2, CT1_h32+4, CT2_h32+2},{CT0_h32+4, CT1_h32+2, CT2_h32+2},{CT0_h32+4, CT1_h32+3, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+0},{CT0_h32+2, CT1_h32+5, CT2_h32+0},{CT0_h32+2, CT1_h32+4, CT2_h32+0},{CT0_h32+1, CT1_h32+5, CT2_h32+1},{CT0_h32+1, CT1_h32+1, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+3},{CT0_h32+4, CT1_h32+1, CT2_h32+2},{CT0_h32+3, CT1_h32+2, CT2_h32+1},{CT0_h32+2, CT1_h32+4, CT2_h32+4},{CT0_h32+2, CT1_h32+3, CT2_h32+3},{CT0_h32+3, CT1_h32+2, CT2_h32+2},{CT0_h32+2, CT1_h32+2, CT2_h32+1},{CT0_h32+1, CT1_h32+3, CT2_h32+2},{CT0_h32+0, CT1_h32+0, CT2_h32+0},
				{CT0_h33+0, CT1_h33+3, CT2_h33+4},{CT0_h33+1, CT1_h33+2, CT2_h33+3},{CT0_h33+2, CT1_h33+1, CT2_h33+2},{CT0_h33+3, CT1_h33+4, CT2_h33+4},{CT0_h33+2, CT1_h33+3, CT2_h33+2},{CT0_h33+4, CT1_h33+2, CT2_h33+2},{CT0_h33+2, CT1_h33+1, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+0},{CT0_h33+3, CT1_h33+3, CT2_h33+0},{CT0_h33+2, CT1_h33+2, CT2_h33+0},{CT0_h33+4, CT1_h33+1, CT2_h33+1},{CT0_h33+2, CT1_h33+4, CT2_h33+2},{CT0_h33+2, CT1_h33+3, CT2_h33+3},{CT0_h33+2, CT1_h33+2, CT2_h33+5},{CT0_h33+1, CT1_h33+4, CT2_h33+0},{CT0_h33+4, CT1_h33+2, CT2_h33+4},{CT0_h33+2, CT1_h33+2, CT2_h33+5},{CT0_h33+3, CT1_h33+2, CT2_h33+2},{CT0_h33+2, CT1_h33+1, CT2_h33+4},{CT0_h33+1, CT1_h33+4, CT2_h33+2},{CT0_h33+4, CT1_h33+2, CT2_h33+3},{CT0_h33+2, CT1_h33+3, CT2_h33+2},{CT0_h33+3, CT1_h33+2, CT2_h33+4},{CT0_h33+2, CT1_h33+1, CT2_h33+0},{CT0_h33+1, CT1_h33+4, CT2_h33+4},{CT0_h33+4, CT1_h33+2, CT2_h33+3},{CT0_h33+3, CT1_h33+3, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+4},{CT0_h33+1, CT1_h33+5, CT2_h33+2},{CT0_h33+2, CT1_h33+0, CT2_h33+2},{CT0_h33+3, CT1_h33+4, CT2_h33+2},{CT0_h33+2, CT1_h33+5, CT2_h33+0},{CT0_h33+1, CT1_h33+2, CT2_h33+0},{CT0_h33+4, CT1_h33+4, CT2_h33+0},{CT0_h33+3, CT1_h33+2, CT2_h33+1},{CT0_h33+2, CT1_h33+2, CT2_h33+2},{CT0_h33+1, CT1_h33+2, CT2_h33+3},{CT0_h33+4, CT1_h33+1, CT2_h33+5},{CT0_h33+3, CT1_h33+4, CT2_h33+0},{CT0_h33+2, CT1_h33+2, CT2_h33+4},{CT0_h33+1, CT1_h33+3, CT2_h33+3},{CT0_h33+2, CT1_h33+2, CT2_h33+2},{CT0_h33+3, CT1_h33+1, CT2_h33+4},{CT0_h33+2, CT1_h33+4, CT2_h33+2},{CT0_h33+4, CT1_h33+2, CT2_h33+2},{CT0_h33+4, CT1_h33+3, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+0},{CT0_h33+2, CT1_h33+5, CT2_h33+0},{CT0_h33+2, CT1_h33+4, CT2_h33+0},{CT0_h33+1, CT1_h33+5, CT2_h33+1},{CT0_h33+1, CT1_h33+1, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+3},{CT0_h33+4, CT1_h33+1, CT2_h33+2},{CT0_h33+3, CT1_h33+2, CT2_h33+1},{CT0_h33+2, CT1_h33+4, CT2_h33+4},{CT0_h33+2, CT1_h33+3, CT2_h33+3},{CT0_h33+3, CT1_h33+2, CT2_h33+2},{CT0_h33+2, CT1_h33+2, CT2_h33+1},{CT0_h33+1, CT1_h33+3, CT2_h33+2},{CT0_h33+0, CT1_h33+0, CT2_h33+0},
				{CT0_h34+0, CT1_h34+3, CT2_h34+4},{CT0_h34+1, CT1_h34+2, CT2_h34+3},{CT0_h34+2, CT1_h34+1, CT2_h34+2},{CT0_h34+3, CT1_h34+4, CT2_h34+4},{CT0_h34+2, CT1_h34+3, CT2_h34+2},{CT0_h34+4, CT1_h34+2, CT2_h34+2},{CT0_h34+2, CT1_h34+1, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+0},{CT0_h34+3, CT1_h34+3, CT2_h34+0},{CT0_h34+2, CT1_h34+2, CT2_h34+0},{CT0_h34+4, CT1_h34+1, CT2_h34+1},{CT0_h34+2, CT1_h34+4, CT2_h34+2},{CT0_h34+2, CT1_h34+3, CT2_h34+3},{CT0_h34+2, CT1_h34+2, CT2_h34+5},{CT0_h34+1, CT1_h34+4, CT2_h34+0},{CT0_h34+4, CT1_h34+2, CT2_h34+4},{CT0_h34+2, CT1_h34+2, CT2_h34+5},{CT0_h34+3, CT1_h34+2, CT2_h34+2},{CT0_h34+2, CT1_h34+1, CT2_h34+4},{CT0_h34+1, CT1_h34+4, CT2_h34+2},{CT0_h34+4, CT1_h34+2, CT2_h34+3},{CT0_h34+2, CT1_h34+3, CT2_h34+2},{CT0_h34+3, CT1_h34+2, CT2_h34+4},{CT0_h34+2, CT1_h34+1, CT2_h34+0},{CT0_h34+1, CT1_h34+4, CT2_h34+4},{CT0_h34+4, CT1_h34+2, CT2_h34+3},{CT0_h34+3, CT1_h34+3, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+4},{CT0_h34+1, CT1_h34+5, CT2_h34+2},{CT0_h34+2, CT1_h34+0, CT2_h34+2},{CT0_h34+3, CT1_h34+4, CT2_h34+2},{CT0_h34+2, CT1_h34+5, CT2_h34+0},{CT0_h34+1, CT1_h34+2, CT2_h34+0},{CT0_h34+4, CT1_h34+4, CT2_h34+0},{CT0_h34+3, CT1_h34+2, CT2_h34+1},{CT0_h34+2, CT1_h34+2, CT2_h34+2},{CT0_h34+1, CT1_h34+2, CT2_h34+3},{CT0_h34+4, CT1_h34+1, CT2_h34+5},{CT0_h34+3, CT1_h34+4, CT2_h34+0},{CT0_h34+2, CT1_h34+2, CT2_h34+4},{CT0_h34+1, CT1_h34+3, CT2_h34+3},{CT0_h34+2, CT1_h34+2, CT2_h34+2},{CT0_h34+3, CT1_h34+1, CT2_h34+4},{CT0_h34+2, CT1_h34+4, CT2_h34+2},{CT0_h34+4, CT1_h34+2, CT2_h34+2},{CT0_h34+4, CT1_h34+3, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+0},{CT0_h34+2, CT1_h34+5, CT2_h34+0},{CT0_h34+2, CT1_h34+4, CT2_h34+0},{CT0_h34+1, CT1_h34+5, CT2_h34+1},{CT0_h34+1, CT1_h34+1, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+3},{CT0_h34+4, CT1_h34+1, CT2_h34+2},{CT0_h34+3, CT1_h34+2, CT2_h34+1},{CT0_h34+2, CT1_h34+4, CT2_h34+4},{CT0_h34+2, CT1_h34+3, CT2_h34+3},{CT0_h34+3, CT1_h34+2, CT2_h34+2},{CT0_h34+2, CT1_h34+2, CT2_h34+1},{CT0_h34+1, CT1_h34+3, CT2_h34+2},{CT0_h34+0, CT1_h34+0, CT2_h34+0},
				{CT0_h35+0, CT1_h35+3, CT2_h35+4},{CT0_h35+1, CT1_h35+2, CT2_h35+3},{CT0_h35+2, CT1_h35+1, CT2_h35+2},{CT0_h35+3, CT1_h35+4, CT2_h35+4},{CT0_h35+2, CT1_h35+3, CT2_h35+2},{CT0_h35+4, CT1_h35+2, CT2_h35+2},{CT0_h35+2, CT1_h35+1, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+0},{CT0_h35+3, CT1_h35+3, CT2_h35+0},{CT0_h35+2, CT1_h35+2, CT2_h35+0},{CT0_h35+4, CT1_h35+1, CT2_h35+1},{CT0_h35+2, CT1_h35+4, CT2_h35+2},{CT0_h35+2, CT1_h35+3, CT2_h35+3},{CT0_h35+2, CT1_h35+2, CT2_h35+5},{CT0_h35+1, CT1_h35+4, CT2_h35+0},{CT0_h35+4, CT1_h35+2, CT2_h35+4},{CT0_h35+2, CT1_h35+2, CT2_h35+5},{CT0_h35+3, CT1_h35+2, CT2_h35+2},{CT0_h35+2, CT1_h35+1, CT2_h35+4},{CT0_h35+1, CT1_h35+4, CT2_h35+2},{CT0_h35+4, CT1_h35+2, CT2_h35+3},{CT0_h35+2, CT1_h35+3, CT2_h35+2},{CT0_h35+3, CT1_h35+2, CT2_h35+4},{CT0_h35+2, CT1_h35+1, CT2_h35+0},{CT0_h35+1, CT1_h35+4, CT2_h35+4},{CT0_h35+4, CT1_h35+2, CT2_h35+3},{CT0_h35+3, CT1_h35+3, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+4},{CT0_h35+1, CT1_h35+5, CT2_h35+2},{CT0_h35+2, CT1_h35+0, CT2_h35+2},{CT0_h35+3, CT1_h35+4, CT2_h35+2},{CT0_h35+2, CT1_h35+5, CT2_h35+0},{CT0_h35+1, CT1_h35+2, CT2_h35+0},{CT0_h35+4, CT1_h35+4, CT2_h35+0},{CT0_h35+3, CT1_h35+2, CT2_h35+1},{CT0_h35+2, CT1_h35+2, CT2_h35+2},{CT0_h35+1, CT1_h35+2, CT2_h35+3},{CT0_h35+4, CT1_h35+1, CT2_h35+5},{CT0_h35+3, CT1_h35+4, CT2_h35+0},{CT0_h35+2, CT1_h35+2, CT2_h35+4},{CT0_h35+1, CT1_h35+3, CT2_h35+3},{CT0_h35+2, CT1_h35+2, CT2_h35+2},{CT0_h35+3, CT1_h35+1, CT2_h35+4},{CT0_h35+2, CT1_h35+4, CT2_h35+2},{CT0_h35+4, CT1_h35+2, CT2_h35+2},{CT0_h35+4, CT1_h35+3, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+0},{CT0_h35+2, CT1_h35+5, CT2_h35+0},{CT0_h35+2, CT1_h35+4, CT2_h35+0},{CT0_h35+1, CT1_h35+5, CT2_h35+1},{CT0_h35+1, CT1_h35+1, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+3},{CT0_h35+4, CT1_h35+1, CT2_h35+2},{CT0_h35+3, CT1_h35+2, CT2_h35+1},{CT0_h35+2, CT1_h35+4, CT2_h35+4},{CT0_h35+2, CT1_h35+3, CT2_h35+3},{CT0_h35+3, CT1_h35+2, CT2_h35+2},{CT0_h35+2, CT1_h35+2, CT2_h35+1},{CT0_h35+1, CT1_h35+3, CT2_h35+2},{CT0_h35+0, CT1_h35+0, CT2_h35+0},
				{CT0_h36+0, CT1_h36+3, CT2_h36+4},{CT0_h36+1, CT1_h36+2, CT2_h36+3},{CT0_h36+2, CT1_h36+1, CT2_h36+2},{CT0_h36+3, CT1_h36+4, CT2_h36+4},{CT0_h36+2, CT1_h36+3, CT2_h36+2},{CT0_h36+4, CT1_h36+2, CT2_h36+2},{CT0_h36+2, CT1_h36+1, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+0},{CT0_h36+3, CT1_h36+3, CT2_h36+0},{CT0_h36+2, CT1_h36+2, CT2_h36+0},{CT0_h36+4, CT1_h36+1, CT2_h36+1},{CT0_h36+2, CT1_h36+4, CT2_h36+2},{CT0_h36+2, CT1_h36+3, CT2_h36+3},{CT0_h36+2, CT1_h36+2, CT2_h36+5},{CT0_h36+1, CT1_h36+4, CT2_h36+0},{CT0_h36+4, CT1_h36+2, CT2_h36+4},{CT0_h36+2, CT1_h36+2, CT2_h36+5},{CT0_h36+3, CT1_h36+2, CT2_h36+2},{CT0_h36+2, CT1_h36+1, CT2_h36+4},{CT0_h36+1, CT1_h36+4, CT2_h36+2},{CT0_h36+4, CT1_h36+2, CT2_h36+3},{CT0_h36+2, CT1_h36+3, CT2_h36+2},{CT0_h36+3, CT1_h36+2, CT2_h36+4},{CT0_h36+2, CT1_h36+1, CT2_h36+0},{CT0_h36+1, CT1_h36+4, CT2_h36+4},{CT0_h36+4, CT1_h36+2, CT2_h36+3},{CT0_h36+3, CT1_h36+3, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+4},{CT0_h36+1, CT1_h36+5, CT2_h36+2},{CT0_h36+2, CT1_h36+0, CT2_h36+2},{CT0_h36+3, CT1_h36+4, CT2_h36+2},{CT0_h36+2, CT1_h36+5, CT2_h36+0},{CT0_h36+1, CT1_h36+2, CT2_h36+0},{CT0_h36+4, CT1_h36+4, CT2_h36+0},{CT0_h36+3, CT1_h36+2, CT2_h36+1},{CT0_h36+2, CT1_h36+2, CT2_h36+2},{CT0_h36+1, CT1_h36+2, CT2_h36+3},{CT0_h36+4, CT1_h36+1, CT2_h36+5},{CT0_h36+3, CT1_h36+4, CT2_h36+0},{CT0_h36+2, CT1_h36+2, CT2_h36+4},{CT0_h36+1, CT1_h36+3, CT2_h36+3},{CT0_h36+2, CT1_h36+2, CT2_h36+2},{CT0_h36+3, CT1_h36+1, CT2_h36+4},{CT0_h36+2, CT1_h36+4, CT2_h36+2},{CT0_h36+4, CT1_h36+2, CT2_h36+2},{CT0_h36+4, CT1_h36+3, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+0},{CT0_h36+2, CT1_h36+5, CT2_h36+0},{CT0_h36+2, CT1_h36+4, CT2_h36+0},{CT0_h36+1, CT1_h36+5, CT2_h36+1},{CT0_h36+1, CT1_h36+1, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+3},{CT0_h36+4, CT1_h36+1, CT2_h36+2},{CT0_h36+3, CT1_h36+2, CT2_h36+1},{CT0_h36+2, CT1_h36+4, CT2_h36+4},{CT0_h36+2, CT1_h36+3, CT2_h36+3},{CT0_h36+3, CT1_h36+2, CT2_h36+2},{CT0_h36+2, CT1_h36+2, CT2_h36+1},{CT0_h36+1, CT1_h36+3, CT2_h36+2},{CT0_h36+0, CT1_h36+0, CT2_h36+0},
				{CT0_h37+0, CT1_h37+3, CT2_h37+4},{CT0_h37+1, CT1_h37+2, CT2_h37+3},{CT0_h37+2, CT1_h37+1, CT2_h37+2},{CT0_h37+3, CT1_h37+4, CT2_h37+4},{CT0_h37+2, CT1_h37+3, CT2_h37+2},{CT0_h37+4, CT1_h37+2, CT2_h37+2},{CT0_h37+2, CT1_h37+1, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+0},{CT0_h37+3, CT1_h37+3, CT2_h37+0},{CT0_h37+2, CT1_h37+2, CT2_h37+0},{CT0_h37+4, CT1_h37+1, CT2_h37+1},{CT0_h37+2, CT1_h37+4, CT2_h37+2},{CT0_h37+2, CT1_h37+3, CT2_h37+3},{CT0_h37+2, CT1_h37+2, CT2_h37+5},{CT0_h37+1, CT1_h37+4, CT2_h37+0},{CT0_h37+4, CT1_h37+2, CT2_h37+4},{CT0_h37+2, CT1_h37+2, CT2_h37+5},{CT0_h37+3, CT1_h37+2, CT2_h37+2},{CT0_h37+2, CT1_h37+1, CT2_h37+4},{CT0_h37+1, CT1_h37+4, CT2_h37+2},{CT0_h37+4, CT1_h37+2, CT2_h37+3},{CT0_h37+2, CT1_h37+3, CT2_h37+2},{CT0_h37+3, CT1_h37+2, CT2_h37+4},{CT0_h37+2, CT1_h37+1, CT2_h37+0},{CT0_h37+1, CT1_h37+4, CT2_h37+4},{CT0_h37+4, CT1_h37+2, CT2_h37+3},{CT0_h37+3, CT1_h37+3, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+4},{CT0_h37+1, CT1_h37+5, CT2_h37+2},{CT0_h37+2, CT1_h37+0, CT2_h37+2},{CT0_h37+3, CT1_h37+4, CT2_h37+2},{CT0_h37+2, CT1_h37+5, CT2_h37+0},{CT0_h37+1, CT1_h37+2, CT2_h37+0},{CT0_h37+4, CT1_h37+4, CT2_h37+0},{CT0_h37+3, CT1_h37+2, CT2_h37+1},{CT0_h37+2, CT1_h37+2, CT2_h37+2},{CT0_h37+1, CT1_h37+2, CT2_h37+3},{CT0_h37+4, CT1_h37+1, CT2_h37+5},{CT0_h37+3, CT1_h37+4, CT2_h37+0},{CT0_h37+2, CT1_h37+2, CT2_h37+4},{CT0_h37+1, CT1_h37+3, CT2_h37+3},{CT0_h37+2, CT1_h37+2, CT2_h37+2},{CT0_h37+3, CT1_h37+1, CT2_h37+4},{CT0_h37+2, CT1_h37+4, CT2_h37+2},{CT0_h37+4, CT1_h37+2, CT2_h37+2},{CT0_h37+4, CT1_h37+3, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+0},{CT0_h37+2, CT1_h37+5, CT2_h37+0},{CT0_h37+2, CT1_h37+4, CT2_h37+0},{CT0_h37+1, CT1_h37+5, CT2_h37+1},{CT0_h37+1, CT1_h37+1, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+3},{CT0_h37+4, CT1_h37+1, CT2_h37+2},{CT0_h37+3, CT1_h37+2, CT2_h37+1},{CT0_h37+2, CT1_h37+4, CT2_h37+4},{CT0_h37+2, CT1_h37+3, CT2_h37+3},{CT0_h37+3, CT1_h37+2, CT2_h37+2},{CT0_h37+2, CT1_h37+2, CT2_h37+1},{CT0_h37+1, CT1_h37+3, CT2_h37+2},{CT0_h37+0, CT1_h37+0, CT2_h37+0},
				{CT0_h38+0, CT1_h38+3, CT2_h38+4},{CT0_h38+1, CT1_h38+2, CT2_h38+3},{CT0_h38+2, CT1_h38+1, CT2_h38+2},{CT0_h38+3, CT1_h38+4, CT2_h38+4},{CT0_h38+2, CT1_h38+3, CT2_h38+2},{CT0_h38+4, CT1_h38+2, CT2_h38+2},{CT0_h38+2, CT1_h38+1, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+0},{CT0_h38+3, CT1_h38+3, CT2_h38+0},{CT0_h38+2, CT1_h38+2, CT2_h38+0},{CT0_h38+4, CT1_h38+1, CT2_h38+1},{CT0_h38+2, CT1_h38+4, CT2_h38+2},{CT0_h38+2, CT1_h38+3, CT2_h38+3},{CT0_h38+2, CT1_h38+2, CT2_h38+5},{CT0_h38+1, CT1_h38+4, CT2_h38+0},{CT0_h38+4, CT1_h38+2, CT2_h38+4},{CT0_h38+2, CT1_h38+2, CT2_h38+5},{CT0_h38+3, CT1_h38+2, CT2_h38+2},{CT0_h38+2, CT1_h38+1, CT2_h38+4},{CT0_h38+1, CT1_h38+4, CT2_h38+2},{CT0_h38+4, CT1_h38+2, CT2_h38+3},{CT0_h38+2, CT1_h38+3, CT2_h38+2},{CT0_h38+3, CT1_h38+2, CT2_h38+4},{CT0_h38+2, CT1_h38+1, CT2_h38+0},{CT0_h38+1, CT1_h38+4, CT2_h38+4},{CT0_h38+4, CT1_h38+2, CT2_h38+3},{CT0_h38+3, CT1_h38+3, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+4},{CT0_h38+1, CT1_h38+5, CT2_h38+2},{CT0_h38+2, CT1_h38+0, CT2_h38+2},{CT0_h38+3, CT1_h38+4, CT2_h38+2},{CT0_h38+2, CT1_h38+5, CT2_h38+0},{CT0_h38+1, CT1_h38+2, CT2_h38+0},{CT0_h38+4, CT1_h38+4, CT2_h38+0},{CT0_h38+3, CT1_h38+2, CT2_h38+1},{CT0_h38+2, CT1_h38+2, CT2_h38+2},{CT0_h38+1, CT1_h38+2, CT2_h38+3},{CT0_h38+4, CT1_h38+1, CT2_h38+5},{CT0_h38+3, CT1_h38+4, CT2_h38+0},{CT0_h38+2, CT1_h38+2, CT2_h38+4},{CT0_h38+1, CT1_h38+3, CT2_h38+3},{CT0_h38+2, CT1_h38+2, CT2_h38+2},{CT0_h38+3, CT1_h38+1, CT2_h38+4},{CT0_h38+2, CT1_h38+4, CT2_h38+2},{CT0_h38+4, CT1_h38+2, CT2_h38+2},{CT0_h38+4, CT1_h38+3, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+0},{CT0_h38+2, CT1_h38+5, CT2_h38+0},{CT0_h38+2, CT1_h38+4, CT2_h38+0},{CT0_h38+1, CT1_h38+5, CT2_h38+1},{CT0_h38+1, CT1_h38+1, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+3},{CT0_h38+4, CT1_h38+1, CT2_h38+2},{CT0_h38+3, CT1_h38+2, CT2_h38+1},{CT0_h38+2, CT1_h38+4, CT2_h38+4},{CT0_h38+2, CT1_h38+3, CT2_h38+3},{CT0_h38+3, CT1_h38+2, CT2_h38+2},{CT0_h38+2, CT1_h38+2, CT2_h38+1},{CT0_h38+1, CT1_h38+3, CT2_h38+2},{CT0_h38+0, CT1_h38+0, CT2_h38+0},
				{CT0_h39+0, CT1_h39+3, CT2_h39+4},{CT0_h39+1, CT1_h39+2, CT2_h39+3},{CT0_h39+2, CT1_h39+1, CT2_h39+2},{CT0_h39+3, CT1_h39+4, CT2_h39+4},{CT0_h39+2, CT1_h39+3, CT2_h39+2},{CT0_h39+4, CT1_h39+2, CT2_h39+2},{CT0_h39+2, CT1_h39+1, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+0},{CT0_h39+3, CT1_h39+3, CT2_h39+0},{CT0_h39+2, CT1_h39+2, CT2_h39+0},{CT0_h39+4, CT1_h39+1, CT2_h39+1},{CT0_h39+2, CT1_h39+4, CT2_h39+2},{CT0_h39+2, CT1_h39+3, CT2_h39+3},{CT0_h39+2, CT1_h39+2, CT2_h39+5},{CT0_h39+1, CT1_h39+4, CT2_h39+0},{CT0_h39+4, CT1_h39+2, CT2_h39+4},{CT0_h39+2, CT1_h39+2, CT2_h39+5},{CT0_h39+3, CT1_h39+2, CT2_h39+2},{CT0_h39+2, CT1_h39+1, CT2_h39+4},{CT0_h39+1, CT1_h39+4, CT2_h39+2},{CT0_h39+4, CT1_h39+2, CT2_h39+3},{CT0_h39+2, CT1_h39+3, CT2_h39+2},{CT0_h39+3, CT1_h39+2, CT2_h39+4},{CT0_h39+2, CT1_h39+1, CT2_h39+0},{CT0_h39+1, CT1_h39+4, CT2_h39+4},{CT0_h39+4, CT1_h39+2, CT2_h39+3},{CT0_h39+3, CT1_h39+3, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+4},{CT0_h39+1, CT1_h39+5, CT2_h39+2},{CT0_h39+2, CT1_h39+0, CT2_h39+2},{CT0_h39+3, CT1_h39+4, CT2_h39+2},{CT0_h39+2, CT1_h39+5, CT2_h39+0},{CT0_h39+1, CT1_h39+2, CT2_h39+0},{CT0_h39+4, CT1_h39+4, CT2_h39+0},{CT0_h39+3, CT1_h39+2, CT2_h39+1},{CT0_h39+2, CT1_h39+2, CT2_h39+2},{CT0_h39+1, CT1_h39+2, CT2_h39+3},{CT0_h39+4, CT1_h39+1, CT2_h39+5},{CT0_h39+3, CT1_h39+4, CT2_h39+0},{CT0_h39+2, CT1_h39+2, CT2_h39+4},{CT0_h39+1, CT1_h39+3, CT2_h39+3},{CT0_h39+2, CT1_h39+2, CT2_h39+2},{CT0_h39+3, CT1_h39+1, CT2_h39+4},{CT0_h39+2, CT1_h39+4, CT2_h39+2},{CT0_h39+4, CT1_h39+2, CT2_h39+2},{CT0_h39+4, CT1_h39+3, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+0},{CT0_h39+2, CT1_h39+5, CT2_h39+0},{CT0_h39+2, CT1_h39+4, CT2_h39+0},{CT0_h39+1, CT1_h39+5, CT2_h39+1},{CT0_h39+1, CT1_h39+1, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+3},{CT0_h39+4, CT1_h39+1, CT2_h39+2},{CT0_h39+3, CT1_h39+2, CT2_h39+1},{CT0_h39+2, CT1_h39+4, CT2_h39+4},{CT0_h39+2, CT1_h39+3, CT2_h39+3},{CT0_h39+3, CT1_h39+2, CT2_h39+2},{CT0_h39+2, CT1_h39+2, CT2_h39+1},{CT0_h39+1, CT1_h39+3, CT2_h39+2},{CT0_h39+0, CT1_h39+0, CT2_h39+0},
				{CT0_h40+0, CT1_h40+3, CT2_h40+4},{CT0_h40+1, CT1_h40+2, CT2_h40+3},{CT0_h40+2, CT1_h40+1, CT2_h40+2},{CT0_h40+3, CT1_h40+4, CT2_h40+4},{CT0_h40+2, CT1_h40+3, CT2_h40+2},{CT0_h40+4, CT1_h40+2, CT2_h40+2},{CT0_h40+2, CT1_h40+1, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+0},{CT0_h40+3, CT1_h40+3, CT2_h40+0},{CT0_h40+2, CT1_h40+2, CT2_h40+0},{CT0_h40+4, CT1_h40+1, CT2_h40+1},{CT0_h40+2, CT1_h40+4, CT2_h40+2},{CT0_h40+2, CT1_h40+3, CT2_h40+3},{CT0_h40+2, CT1_h40+2, CT2_h40+5},{CT0_h40+1, CT1_h40+4, CT2_h40+0},{CT0_h40+4, CT1_h40+2, CT2_h40+4},{CT0_h40+2, CT1_h40+2, CT2_h40+5},{CT0_h40+3, CT1_h40+2, CT2_h40+2},{CT0_h40+2, CT1_h40+1, CT2_h40+4},{CT0_h40+1, CT1_h40+4, CT2_h40+2},{CT0_h40+4, CT1_h40+2, CT2_h40+3},{CT0_h40+2, CT1_h40+3, CT2_h40+2},{CT0_h40+3, CT1_h40+2, CT2_h40+4},{CT0_h40+2, CT1_h40+1, CT2_h40+0},{CT0_h40+1, CT1_h40+4, CT2_h40+4},{CT0_h40+4, CT1_h40+2, CT2_h40+3},{CT0_h40+3, CT1_h40+3, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+4},{CT0_h40+1, CT1_h40+5, CT2_h40+2},{CT0_h40+2, CT1_h40+0, CT2_h40+2},{CT0_h40+3, CT1_h40+4, CT2_h40+2},{CT0_h40+2, CT1_h40+5, CT2_h40+0},{CT0_h40+1, CT1_h40+2, CT2_h40+0},{CT0_h40+4, CT1_h40+4, CT2_h40+0},{CT0_h40+3, CT1_h40+2, CT2_h40+1},{CT0_h40+2, CT1_h40+2, CT2_h40+2},{CT0_h40+1, CT1_h40+2, CT2_h40+3},{CT0_h40+4, CT1_h40+1, CT2_h40+5},{CT0_h40+3, CT1_h40+4, CT2_h40+0},{CT0_h40+2, CT1_h40+2, CT2_h40+4},{CT0_h40+1, CT1_h40+3, CT2_h40+3},{CT0_h40+2, CT1_h40+2, CT2_h40+2},{CT0_h40+3, CT1_h40+1, CT2_h40+4},{CT0_h40+2, CT1_h40+4, CT2_h40+2},{CT0_h40+4, CT1_h40+2, CT2_h40+2},{CT0_h40+4, CT1_h40+3, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+0},{CT0_h40+2, CT1_h40+5, CT2_h40+0},{CT0_h40+2, CT1_h40+4, CT2_h40+0},{CT0_h40+1, CT1_h40+5, CT2_h40+1},{CT0_h40+1, CT1_h40+1, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+3},{CT0_h40+4, CT1_h40+1, CT2_h40+2},{CT0_h40+3, CT1_h40+2, CT2_h40+1},{CT0_h40+2, CT1_h40+4, CT2_h40+4},{CT0_h40+2, CT1_h40+3, CT2_h40+3},{CT0_h40+3, CT1_h40+2, CT2_h40+2},{CT0_h40+2, CT1_h40+2, CT2_h40+1},{CT0_h40+1, CT1_h40+3, CT2_h40+2},{CT0_h40+0, CT1_h40+0, CT2_h40+0},
				{CT0_h41+0, CT1_h41+3, CT2_h41+4},{CT0_h41+1, CT1_h41+2, CT2_h41+3},{CT0_h41+2, CT1_h41+1, CT2_h41+2},{CT0_h41+3, CT1_h41+4, CT2_h41+4},{CT0_h41+2, CT1_h41+3, CT2_h41+2},{CT0_h41+4, CT1_h41+2, CT2_h41+2},{CT0_h41+2, CT1_h41+1, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+0},{CT0_h41+3, CT1_h41+3, CT2_h41+0},{CT0_h41+2, CT1_h41+2, CT2_h41+0},{CT0_h41+4, CT1_h41+1, CT2_h41+1},{CT0_h41+2, CT1_h41+4, CT2_h41+2},{CT0_h41+2, CT1_h41+3, CT2_h41+3},{CT0_h41+2, CT1_h41+2, CT2_h41+5},{CT0_h41+1, CT1_h41+4, CT2_h41+0},{CT0_h41+4, CT1_h41+2, CT2_h41+4},{CT0_h41+2, CT1_h41+2, CT2_h41+5},{CT0_h41+3, CT1_h41+2, CT2_h41+2},{CT0_h41+2, CT1_h41+1, CT2_h41+4},{CT0_h41+1, CT1_h41+4, CT2_h41+2},{CT0_h41+4, CT1_h41+2, CT2_h41+3},{CT0_h41+2, CT1_h41+3, CT2_h41+2},{CT0_h41+3, CT1_h41+2, CT2_h41+4},{CT0_h41+2, CT1_h41+1, CT2_h41+0},{CT0_h41+1, CT1_h41+4, CT2_h41+4},{CT0_h41+4, CT1_h41+2, CT2_h41+3},{CT0_h41+3, CT1_h41+3, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+4},{CT0_h41+1, CT1_h41+5, CT2_h41+2},{CT0_h41+2, CT1_h41+0, CT2_h41+2},{CT0_h41+3, CT1_h41+4, CT2_h41+2},{CT0_h41+2, CT1_h41+5, CT2_h41+0},{CT0_h41+1, CT1_h41+2, CT2_h41+0},{CT0_h41+4, CT1_h41+4, CT2_h41+0},{CT0_h41+3, CT1_h41+2, CT2_h41+1},{CT0_h41+2, CT1_h41+2, CT2_h41+2},{CT0_h41+1, CT1_h41+2, CT2_h41+3},{CT0_h41+4, CT1_h41+1, CT2_h41+5},{CT0_h41+3, CT1_h41+4, CT2_h41+0},{CT0_h41+2, CT1_h41+2, CT2_h41+4},{CT0_h41+1, CT1_h41+3, CT2_h41+3},{CT0_h41+2, CT1_h41+2, CT2_h41+2},{CT0_h41+3, CT1_h41+1, CT2_h41+4},{CT0_h41+2, CT1_h41+4, CT2_h41+2},{CT0_h41+4, CT1_h41+2, CT2_h41+2},{CT0_h41+4, CT1_h41+3, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+0},{CT0_h41+2, CT1_h41+5, CT2_h41+0},{CT0_h41+2, CT1_h41+4, CT2_h41+0},{CT0_h41+1, CT1_h41+5, CT2_h41+1},{CT0_h41+1, CT1_h41+1, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+3},{CT0_h41+4, CT1_h41+1, CT2_h41+2},{CT0_h41+3, CT1_h41+2, CT2_h41+1},{CT0_h41+2, CT1_h41+4, CT2_h41+4},{CT0_h41+2, CT1_h41+3, CT2_h41+3},{CT0_h41+3, CT1_h41+2, CT2_h41+2},{CT0_h41+2, CT1_h41+2, CT2_h41+1},{CT0_h41+1, CT1_h41+3, CT2_h41+2},{CT0_h41+0, CT1_h41+0, CT2_h41+0},
				{CT0_h42+0, CT1_h42+3, CT2_h42+4},{CT0_h42+1, CT1_h42+2, CT2_h42+3},{CT0_h42+2, CT1_h42+1, CT2_h42+2},{CT0_h42+3, CT1_h42+4, CT2_h42+4},{CT0_h42+2, CT1_h42+3, CT2_h42+2},{CT0_h42+4, CT1_h42+2, CT2_h42+2},{CT0_h42+2, CT1_h42+1, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+0},{CT0_h42+3, CT1_h42+3, CT2_h42+0},{CT0_h42+2, CT1_h42+2, CT2_h42+0},{CT0_h42+4, CT1_h42+1, CT2_h42+1},{CT0_h42+2, CT1_h42+4, CT2_h42+2},{CT0_h42+2, CT1_h42+3, CT2_h42+3},{CT0_h42+2, CT1_h42+2, CT2_h42+5},{CT0_h42+1, CT1_h42+4, CT2_h42+0},{CT0_h42+4, CT1_h42+2, CT2_h42+4},{CT0_h42+2, CT1_h42+2, CT2_h42+5},{CT0_h42+3, CT1_h42+2, CT2_h42+2},{CT0_h42+2, CT1_h42+1, CT2_h42+4},{CT0_h42+1, CT1_h42+4, CT2_h42+2},{CT0_h42+4, CT1_h42+2, CT2_h42+3},{CT0_h42+2, CT1_h42+3, CT2_h42+2},{CT0_h42+3, CT1_h42+2, CT2_h42+4},{CT0_h42+2, CT1_h42+1, CT2_h42+0},{CT0_h42+1, CT1_h42+4, CT2_h42+4},{CT0_h42+4, CT1_h42+2, CT2_h42+3},{CT0_h42+3, CT1_h42+3, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+4},{CT0_h42+1, CT1_h42+5, CT2_h42+2},{CT0_h42+2, CT1_h42+0, CT2_h42+2},{CT0_h42+3, CT1_h42+4, CT2_h42+2},{CT0_h42+2, CT1_h42+5, CT2_h42+0},{CT0_h42+1, CT1_h42+2, CT2_h42+0},{CT0_h42+4, CT1_h42+4, CT2_h42+0},{CT0_h42+3, CT1_h42+2, CT2_h42+1},{CT0_h42+2, CT1_h42+2, CT2_h42+2},{CT0_h42+1, CT1_h42+2, CT2_h42+3},{CT0_h42+4, CT1_h42+1, CT2_h42+5},{CT0_h42+3, CT1_h42+4, CT2_h42+0},{CT0_h42+2, CT1_h42+2, CT2_h42+4},{CT0_h42+1, CT1_h42+3, CT2_h42+3},{CT0_h42+2, CT1_h42+2, CT2_h42+2},{CT0_h42+3, CT1_h42+1, CT2_h42+4},{CT0_h42+2, CT1_h42+4, CT2_h42+2},{CT0_h42+4, CT1_h42+2, CT2_h42+2},{CT0_h42+4, CT1_h42+3, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+0},{CT0_h42+2, CT1_h42+5, CT2_h42+0},{CT0_h42+2, CT1_h42+4, CT2_h42+0},{CT0_h42+1, CT1_h42+5, CT2_h42+1},{CT0_h42+1, CT1_h42+1, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+3},{CT0_h42+4, CT1_h42+1, CT2_h42+2},{CT0_h42+3, CT1_h42+2, CT2_h42+1},{CT0_h42+2, CT1_h42+4, CT2_h42+4},{CT0_h42+2, CT1_h42+3, CT2_h42+3},{CT0_h42+3, CT1_h42+2, CT2_h42+2},{CT0_h42+2, CT1_h42+2, CT2_h42+1},{CT0_h42+1, CT1_h42+3, CT2_h42+2},{CT0_h42+0, CT1_h42+0, CT2_h42+0},
				{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},	
				{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},
				{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},*/
				{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0}
				
				
				
				
			};
		
		if (dados == null) {
			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0,7,7);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			/*if(slot%5==4)
			{
				try {
					if(rodada.estatistica.devolucoes(300)>=5||rodada.estatistica.preempcoes(300)>=5)
					{
						Lsp LSPaux= new Lsp(); 
						LSPaux.Carga=0; 
						to.link[0].bamType = BAMType.PreemptionGBAM;
						to.link[0].BCLTH= new double[]
						{	000, //BC0 
							000, //BC1
							0  //BC2 Nunca mudar
						};
						LSPaux.CT=0; 
			      		BAM.devolutionG(to.link[0],LSPaux);
						
						
						to.link[0].BCHTL= new double[]
						{	0, //BC0 Nunca mudar
							000, //BC1
							000 //BC2
						};
						
						LSPaux.CT=2; 
			      		BAM.preemptionG(to.link[0],LSPaux); 
						
					}
					else if(rodada.estatistica.picoDeUtilizacaoDoEnlace(300,to.link[0])<=800)
					{
						to.link[0].bamType = BAMType.PreemptionGBAM;
						to.link[0].BCLTH= new double[]
						{	100, //BC0 
							100, //BC1
							0  //BC2 Nunca mudar
						};
						
						to.link[0].BCHTL= new double[]
						{	0, //BC0 Nunca mudar
							100, //BC
							100 //BC2
						};
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RrdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
			}
			*/
			for (int z=0; z<fonteDeTrafego; z++)
			{
				for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
	
					for (int i = 0; i < matrizB[slot][ct]; i++) {
						No dadosLSP = new No();
	
						Lsp lsp = new Lsp(rodada);
						lsp.CargaReduzida = 0;
						if (z==0)
						{
							lsp.src = 0; // id do router fonte
							lsp.dest = 9; // id do router destino
						} else if (z==1)
						{
							lsp.src = 0; // id do router fonte
							lsp.dest = 10; // id do router destino
						}
						else 
						{
							lsp.src = 0; // id do router fonte
							lsp.dest = 11; // id do router destino
						}
						/*lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
						// do
						// router
						// fonte
						do {
						lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
													// do
													// router
													// destino
						} while (lsp.src == lsp.dest);*/
						lsp.CT = ct;
						lsp.Carga = 10;//(int) GeradorDeNumerosAleatorios.uniform(5, 15);   //10
						lsp.tempoDeVida = 120; //(int) GeradorDeNumerosAleatorios.expntl(250);  //120
						dadosLSP.item = lsp;
	
						Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
								+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
								+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao(),7,7);
						rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);
	
					}
				}
			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot,7,7);
			dados.item = slot + 1;
			rodada.schedulep(3, 60, dados);

		}

	}

		
		
	public static void trafegoDeterministico(RodadaDeSimulacao rodada, Topologia to, No dados) {

			
			/*try {
				if(rodada.estatistica.devolucoes(300)>=5||rodada.estatistica.preempcoes(300)>=5)
				{
					Lsp LSPaux= new Lsp(); 
					LSPaux.Carga=0; 
					to.link[0].bamType = BAMType.PreemptionGBAM;
					to.link[0].BCLTH= new double[]
					{	000, //BC0 
						000, //BC1
						0  //BC2 Nunca mudar
					};
					LSPaux.CT=0; 
		      		BAM.devolutionG(to.link[0],LSPaux);
					
					
					to.link[0].BCHTL= new double[]
					{	0, //BC0 Nunca mudar
						000, //BC1
						000 //BC2
					};
					
					LSPaux.CT=2; 
		      		BAM.preemptionG(to.link[0],LSPaux); 
					
				}
				else if(rodada.estatistica.picoDeUtilizacaoDoEnlace(300,to.link[0])<=800)
				{
					to.link[0].bamType = BAMType.PreemptionGBAM;
					to.link[0].BCLTH= new double[]
					{	100, //BC0 
						100, //BC1
						0  //BC2 Nunca mudar
					};
					
					to.link[0].BCHTL= new double[]
					{	0, //BC0 Nunca mudar
						100, //BC
						100 //BC2
					};
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RrdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		// Inicializar Tráfego
		if (dados == null) {
			for (int i = 0; i < ParametrosDSTE.MaxClassType; i++) {
				int auxCT = i;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; // id do router fonte
				lsp.dest = 1; // id do router destino
				lsp.CT = auxCT;
				lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
				dados.item = lsp;
				Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
						+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
						+ to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
				rodada.schedulep(3, 3600 * 0, dados);
			}
		} else {
			Debug.setMensagem(
					"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
							+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
			rodada.schedulep(1, 0, dados);

			// Repetição do tráfego
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
			int auxCT = ((Lsp) dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; // id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT = auxCT;
			lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
			dados.item = lsp;

			double tempoDeVida = 250;

			if (rodada.simtime() < 3600 * 1) // Uma hora de 0 a 1 hora
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 2) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 3) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 4)// 14.400 de 3 a 4 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}
			} else if (rodada.simtime() < 3600 * 5) // 18000 de 4 a 5 horas
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 6) // de 5 a 6 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 7)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);
				}
			} else if (rodada.simtime() < 3600 * 8){
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 9)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 10) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 11) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 12)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 13)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			} else if (rodada.simtime() < 3600 * 14)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			} else if (rodada.simtime() < 3600 * 15) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			} else if (rodada.simtime() < 3600 * 16) /// de 15 as 16
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 17) /// de 16 as 17
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			}

		}

	}

	public static void trafegoDeterministico2(RodadaDeSimulacao rodada, Topologia to, No dados) {

		/*try {
		if(rodada.estatistica.devolucoes(300)>=5||rodada.estatistica.preempcoes(300)>=5)
		{
			Lsp LSPaux= new Lsp(); 
			LSPaux.Carga=0; 
			to.link[0].bamType = BAMType.PreemptionGBAM;
			to.link[0].BCLTH= new double[]
			{	000, //BC0 
				000, //BC1
				0  //BC2 Nunca mudar
			};
			LSPaux.CT=0; 
      		BAM.devolutionG(to.link[0],LSPaux);
			
			
			to.link[0].BCHTL= new double[]
			{	0, //BC0 Nunca mudar
				000, //BC1
				000 //BC2
			};
			
			LSPaux.CT=2; 
      		BAM.preemptionG(to.link[0],LSPaux); 
			
		}
		else if(rodada.estatistica.picoDeUtilizacaoDoEnlace(300,to.link[0])<=800)
		{
			to.link[0].bamType = BAMType.PreemptionGBAM;
			to.link[0].BCLTH= new double[]
			{	100, //BC0 
				100, //BC1
				0  //BC2 Nunca mudar
			};
			
			to.link[0].BCHTL= new double[]
			{	0, //BC0 Nunca mudar
				100, //BC
				100 //BC2
			};
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RrdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		
		
		
		
		// Inicializar Tráfego
		if (dados == null) {
			for (int i = 0; i < ParametrosDSTE.MaxClassType; i++) {
				int auxCT = i;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; // id do router fonte
				lsp.dest = 1; // id do router destino
				lsp.CT = auxCT;
				lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
				dados.item = lsp;
				Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
						+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
						+ to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
				if (((Lsp) dados.item).CT == 2) {

					rodada.schedulep(3, 3600 * 0, dados); //// CT2
					rodada.schedulep(3, 3600 * 15, dados);

				} else if (((Lsp) dados.item).CT == 1) {

					rodada.schedulep(3, 3600 * 4, dados); // CT1
					rodada.schedulep(3, 3600 * 10, dados);

				} else {
					rodada.schedulep(3, 3600 * 2, dados); //// CT0
					rodada.schedulep(3, 3600 * 9, dados);
					rodada.schedulep(3, 3600 * 16, dados);
				}
			}
		} else {
			Debug.setMensagem(
					"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
							+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
			rodada.schedulep(1, 0, dados);

			// Repetição do tráfego
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
			int auxCT = ((Lsp) dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; // id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT = auxCT;
			lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
			dados.item = lsp;

			double tempoDeVida = 250;

			if (rodada.simtime() < 3600 * 1) // Uma hora de 0 a 1 hora
			{
				((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

			} else if (rodada.simtime() < 3600 * 2) // 7200 Duas horas de 1 a 2
													// horas
			{

				((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

			} else if (rodada.simtime() < 3600 * 3) // 10800 Três horas de 2 a 3
													// horas
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 4)// 14.400 de 3 a 4 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
			} else if (rodada.simtime() < 3600 * 5) // 18000 de 4 a 5 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
			} else if (rodada.simtime() < 3600 * 6) // de 5 a 6 horas
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
			} else if (rodada.simtime() < 3600 * 7) /// de 6 a 7
													/// horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 8) /// de 7 as 8
													/// horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}

			} else if (rodada.simtime() < 3600 * 9) /////// de 8 as 9
													/////// horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{

			} else if (rodada.simtime() < 3600 * 10) /////// de 9 as
														/////// 10horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			} else if (rodada.simtime() < 3600 * 11) /////// de 10 as 11
														/////// horas///////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}

			} else if (rodada.simtime() < 3600 * 12) /////// de 11 as 12
														/////// horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 13) //// de 12 as 13
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 14) //// de 13 as 14
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			} else if (rodada.simtime() < 3600 * 15) //// de 14 as 15
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 16) /// de 15 as 16
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(6), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(6), dados);
				}

			} else if (rodada.simtime() < 3600 * 17) /// de 16 as 17
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}

			} else if (rodada.simtime() < 3600 * 18) /// de 16 as 17
			/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				} else if (((Lsp) dados.item).CT == 1) 
				{
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				} else if (((Lsp) dados.item).CT == 2) 
				{
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}
			}else if (rodada.simtime() < 3600 * 19) /// de 16 as 17
				/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					}
				}else if (rodada.simtime() < 3600 * 20) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					}
				}else if (rodada.simtime() < 3600 * 21) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					}
				}else if (rodada.simtime() < 3600 * 22) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					}
				}else if (rodada.simtime() < 3600 * 23) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					}
				}else if (rodada.simtime() < 3600 * 24) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(7), dados);
					}
				}
			
			
			
			
			
			
			
			
			
			

		}

	}

	// Aleatório1
	public static void trafegoAleatorioNSF(RodadaDeSimulacao rodada, Topologia to, No dados) {
		int fonteDeTrafego = 2;
		if (dados != null) {
			Lsp [] lsps=(Lsp[]) dados.item;
			for (int i=0; i<lsps.length; i++)
			{
				Debug.setMensagem("Agenda estabelecimento da LSP " + lsps[i].ID + " - "
						+ to.getRoteador(lsps[i].src).getDescricao() + " -->"
						+ to.getRoteador(lsps[i].dest).getDescricao(),7,7);
				No dadosAux = new No();
				dadosAux.item=lsps[i];
				rodada.schedulep(1, 0.0, dadosAux);
			}
		}

		dados = new No();
		Lsp [] lsps = new Lsp[fonteDeTrafego];
		for (int i=0; i<fonteDeTrafego; i++)
		{
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																							// do
																							// router
																							// fonte
			do {
				lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																									// do
																									// router
																									// destino
			} while (lsp.src == lsp.dest);
			// lsp.src = 0;
			// lsp.dest = 1;
			lsp.CT = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType - 1);
			lsp.Carga = GeradorDeNumerosAleatorios.uniform(10, 60);
			lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(600);
			lsps[i] = lsp;
			Debug.setMensagem(
					"Cria LSP " + lsps[i].ID + " - " + to.getRoteador(lsps[i].src).getDescricao()
							+ " -->" + to.getRoteador(lsps[i].dest).getDescricao(),7,7);
			
		
		}
		dados.item = lsps;
		if (rodada.simtime() < 1800)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(8), dados);
		else if (rodada.simtime() < 3600)//1
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(8), dados);
		else if (rodada.simtime() < 5400)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(1), dados);
		else if (rodada.simtime() < 7200)//2
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(1), dados);
		else if (rodada.simtime() < 9000)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(2), dados);
		else if (rodada.simtime() < 10800)//3
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(1), dados);
		else if (rodada.simtime() < 12600)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(1), dados);
		else if (rodada.simtime() < 14400)//4
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(8), dados);
		else if (rodada.simtime() < 16200)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(8), dados);
		else if (rodada.simtime() < 18000)//5
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(1), dados);
		else if (rodada.simtime() < 19800)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(1), dados);
		else
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(8), dados);
	}

	// Aleatório2
	public static void trafegoManualB(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados != null) {
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);
			rodada.schedulep(1, 0.0, dados);
		}

		dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																						// do
																						// router
																						// fonte
		do {
			lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																								// do
																								// router
																								// destino
		} while (lsp.src == lsp.dest);
		// lsp.src = 0;
		// lsp.dest = 1;
		lsp.CT = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType - 1);
		lsp.Carga = GeradorDeNumerosAleatorios.uniform(50, 150);
		dados.item = lsp;
		Debug.setMensagem(
				"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
						+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao(),7,7);

		if (((Lsp) dados.item).CT == 0) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(2), dados);

		} else if (((Lsp) dados.item).CT == 1) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(15), dados);

		} else if (((Lsp) dados.item).CT == 2) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(1), dados);

		}

	}

	// Aleatório3
	public static void trafegoPoisson(RodadaDeSimulacao rodada, Topologia to, No dados) {
		
		/*try {
		if(rodada.estatistica.devolucoes(300)>=5||rodada.estatistica.preempcoes(300)>=5)
		{
			Lsp LSPaux= new Lsp(); 
			LSPaux.Carga=0; 
			to.link[0].bamType = BAMType.PreemptionGBAM;
			to.link[0].BCLTH= new double[]
			{	000, //BC0 
				000, //BC1
				0  //BC2 Nunca mudar
			};
			LSPaux.CT=0; 
      		BAM.devolutionG(to.link[0],LSPaux);
			
			
			to.link[0].BCHTL= new double[]
			{	0, //BC0 Nunca mudar
				000, //BC1
				000 //BC2
			};
			
			LSPaux.CT=2; 
      		BAM.preemptionG(to.link[0],LSPaux); 
			
		}
		else if(rodada.estatistica.picoDeUtilizacaoDoEnlace(300,to.link[0])<=800)
		{
			to.link[0].bamType = BAMType.PreemptionGBAM;
			to.link[0].BCLTH= new double[]
			{	100, //BC0 
				100, //BC1
				0  //BC2 Nunca mudar
			};
			
			to.link[0].BCHTL= new double[]
			{	0, //BC0 Nunca mudar
				100, //BC
				100 //BC2
			};
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RrdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

		if (dados == null) {
			numeroDeLSPsPorMinuto = gerarCurvasPoisson((int) ParametrosDSTE.TempoSimulacao + 60);
			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0,7,7);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {

				for (int i = 0; i < numeroDeLSPsPorMinuto[ct][slot]; i++) {
					No dadosLSP = new No();

					Lsp lsp = new Lsp(rodada);
					lsp.CargaReduzida = 0;
					lsp.src = 0; // id do router fonte
					lsp.dest = 1; // id do router destino
					lsp.CT = ct;
					lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
					lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
					dadosLSP.item = lsp;

					Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
							+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
							+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao(),7,7);
					rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);

				}
			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot,7,7);
			dados.item = slot + 1;
			rodada.schedulep(3, 60, dados);

		}

	}

	public static void trafegoPoissonTempo(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados == null) {

			numeroDeLSPsPorSlot = new int[][] {
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[0] * 60, 3, 1, 3),
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[1] * 60, 3, 1, 3),
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[2] * 60, 3, 1, 3) };

			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0,7,7);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
				// if (ct!=0){
				for (int i = 0; i < numeroDeLSPsPorSlot[ct][slot]; i++) {
					No dadosLSP = new No();

					Lsp lsp = new Lsp(rodada);
					lsp.CargaReduzida = 0;
					lsp.src = 0; // id do router fonte
					lsp.dest = 2; // id do router destino
					lsp.CT = ct;
					lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
					lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
					dadosLSP.item = lsp;

					Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
							+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
							+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao(),7,7);
					rodada.schedulep(1, GeradorDeNumerosAleatorios.expntl(3599), dadosLSP);
					// .uniform(0, 3599)
				}

				/*
				 * }else if (slot !=0){ for (int i = 0; i <
				 * numeroDeLSPsPorSlot[ct][slot-1]; i++) { No dadosLSP = new
				 * No();
				 * 
				 * Lsp lsp = new Lsp(rodada); lsp.CargaReduzida = 0; lsp.src =
				 * 0; // id do router fonte lsp.dest = 1; // id do router
				 * destino lsp.CT = ct; lsp.Carga = (int)
				 * GeradorDeNumerosAleatorios.uniform(5, 15); lsp.tempoDeVida =
				 * (int) GeradorDeNumerosAleatorios.expntl(250); dadosLSP.item =
				 * lsp;
				 * 
				 * Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID +
				 * " - " + to.getRoteador(((Lsp)
				 * dadosLSP.item).src).getDescricao() + " -->" +
				 * to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
				 * rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0,
				 * 3599), dadosLSP);
				 * 
				 * } }
				 */

			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot,7,7);
			dados.item = slot + 1;
			rodada.schedulep(3, 3600, dados);

		}

	}

	public static void trafegoPoissonTempo2(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados == null) {
			// numeroDeLSPsPorSlot = gerarCurvasPoisson(
			// (int)(ParametrosDSTE.TempoSimulacao/3600)+1, 60, 3);

			numeroDeLSPsPorSlot = new int[][] {
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[0] * 20, 3, 1, 3), /// numero
																				/// de
																				/// slot
																				/// ,
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[1] * 20, 3, 1, 3),
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[2] * 20, 3, 1, 3) };

			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0,7,7);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
				// if (ct!=0){

				int numeroDeLSPsPorMinuto[] = gerarCurvasPoissonPorSlot(60, numeroDeLSPsPorSlot[ct][slot], 30, -1, -1);

				for (int i = 0; i < 60; i++) {

					for (int z = 0; z < numeroDeLSPsPorMinuto[i]; z++) {
						No dadosLSP = new No();

						Lsp lsp = new Lsp(rodada);
						lsp.CargaReduzida = 0;
						lsp.src = 0; // id do router fonte
						lsp.dest = 2; // id do router destino
						lsp.CT = ct;
						lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
						lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
						dadosLSP.item = lsp;

						Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
								+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
								+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao(),7,7);

						rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform((60 * i), 59 + (60 * i)), dadosLSP);
					} // .uniform(0, 3599)

				}

				/*
				 * }else if (slot !=0){ for (int i = 0; i <
				 * numeroDeLSPsPorSlot[ct][slot-1]; i++) { No dadosLSP = new
				 * No();
				 * 
				 * Lsp lsp = new Lsp(rodada); lsp.CargaReduzida = 0; lsp.src =
				 * 0; // id do router fonte lsp.dest = 1; // id do router
				 * destino lsp.CT = ct; lsp.Carga = (int)
				 * GeradorDeNumerosAleatorios.uniform(5, 15); lsp.tempoDeVida =
				 * (int) GeradorDeNumerosAleatorios.expntl(250); dadosLSP.item =
				 * lsp;
				 * 
				 * Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID +
				 * " - " + to.getRoteador(((Lsp)
				 * dadosLSP.item).src).getDescricao() + " -->" +
				 * to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
				 * rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0,
				 * 3599), dadosLSP);
				 * 
				 * } }
				 */

			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot,7,7);
			dados.item = slot + 1;
			rodada.schedulep(3, 3600, dados);

		}

	}

	public static int[][] gerarCurvasPoisson(int tempoEmSegundos) {

		int slotsDeTempo = tempoEmSegundos / 60;
		int numeroDeLSPsPorMinuto[][] = new int[ParametrosDSTE.MaxClassType][slotsDeTempo];
		int pesoLSPHora = 30;
		int numeroDeLSPPorHoraCT[] = new int[] { (int) ParametrosDSTE.BCPadrao[0] * pesoLSPHora,
				(int) ParametrosDSTE.BCPadrao[1] * pesoLSPHora, (int) ParametrosDSTE.BCPadrao[2] * pesoLSPHora };
		for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
			// System.out.println("========" + ct + "========");
			int countSlotDeTempo = 0;
			while (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length) {

				int numeroDeLSPPorHora = GeradorDeNumerosAleatorios.uniform(
						numeroDeLSPPorHoraCT[ct] - ((int) (numeroDeLSPPorHoraCT[ct] * 0.01)),
						numeroDeLSPPorHoraCT[ct] + ((int) (numeroDeLSPPorHoraCT[ct] * 0.01)));
				int lambdaPico = GeradorDeNumerosAleatorios.uniform(150, 210);
				// System.out.println("Lambda="+lambdaPico);
				int numeroDeLSPs[] = new int[lambdaPico * 3];
				for (int i = 0; i < numeroDeLSPPorHora; i++) {
					int minuto = GeradorDeNumerosAleatorios.poisson(lambdaPico);
					// if(minuto<lambdaPico*2)
					++numeroDeLSPs[minuto];
					// else
					// System.out.println("Discartado="+minuto);

				}

				int iCurva = 0;

				for (int i = 0; i < lambdaPico * 2 && numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(1, 3); i++) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					// System.out.println(numeroDeLSPs[i]);
					iCurva++;
				}
				int fCurva = numeroDeLSPs.length - 1;

				for (int i = numeroDeLSPs.length - 1; i > 0
						&& numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(2, 3); i--) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					fCurva--;
					// System.out.println(numeroDeLSPs[i]);
				}

				for (int i = iCurva; i <= fCurva && (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length); i++) {
					// System.out.println(numeroDeLSPs[i]);
					numeroDeLSPsPorMinuto[ct][countSlotDeTempo++] = numeroDeLSPs[i];
				}

			}

		}

		return numeroDeLSPsPorMinuto;
	}

	/*
	 * public static int[][] gerarCurvasPoisson(int slotsDeLSP, int pesoLSPHora,
	 * int pico) { // escala é a unidade de tempo
	 * 
	 * //int slotsDeTempo = tempoEmSegundos / escala; int
	 * numeroDeLSPsPorMinuto[][] = new
	 * int[ParametrosDSTE.MaxClassType][slotsDeLSP];
	 * 
	 * int numeroDeLSPPorHoraCT[] = new int[]
	 * {(int)ParametrosDSTE.BCPadrao[0]*pesoLSPHora,
	 * (int)ParametrosDSTE.BCPadrao[1]*pesoLSPHora,
	 * (int)ParametrosDSTE.BCPadrao[2]*pesoLSPHora};
	 * 
	 * for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) { //
	 * System.out.println("========" + ct + "========"); int countSlotDeTempo =
	 * 0; while (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length) {
	 * 
	 * int numeroDeLSPPorHora = numeroDeLSPPorHoraCT[ct]
	 * ;GeradorDeNumerosAleatorios.uniform(numeroDeLSPPorHoraCT[ct]-
	 * ((int)(numeroDeLSPPorHoraCT[ct]*0.01)),numeroDeLSPPorHoraCT[ct]+
	 * ((int)(numeroDeLSPPorHoraCT[ct]*0.01))); int lambdaPico = pico;
	 * //GeradorDeNumerosAleatorios.uniform(pico - ((int)(pico*0.01)) , pico +
	 * ((int)(pico*0.01))); // System.out.println("Lambda="+lambdaPico); int
	 * numeroDeLSPs[] = new int[lambdaPico * 2]; for (int i = 0; i <
	 * numeroDeLSPPorHora; i++) { int tempo =
	 * GeradorDeNumerosAleatorios.poisson(lambdaPico); if(tempo<lambdaPico*2){
	 * ++numeroDeLSPs[tempo]; }else{ i--;
	 * System.out.println("Discartado="+tempo); } //
	 * System.out.println("Discartado="+minuto);
	 * 
	 * }
	 * 
	 * int iCurva = 0;
	 * 
	 * for (int i = 0; i < lambdaPico * 2 && numeroDeLSPs[i] <
	 * GeradorDeNumerosAleatorios.uniform(1, 3); i++) { //
	 * System.out.println(i+"="+numeroDeLSPs[i]); //
	 * System.out.println(numeroDeLSPs[i]); iCurva++; } int fCurva =
	 * numeroDeLSPs.length - 1;
	 * 
	 * for (int i = numeroDeLSPs.length - 1; i > 0 && numeroDeLSPs[i] <
	 * GeradorDeNumerosAleatorios.uniform(2, 3); i--) { //
	 * System.out.println(i+"="+numeroDeLSPs[i]); fCurva--; //
	 * System.out.println(numeroDeLSPs[i]); }
	 * 
	 * for (int i = iCurva; i <= fCurva && (countSlotDeTempo <
	 * numeroDeLSPsPorMinuto[ct].length); i++) { //
	 * System.out.println(numeroDeLSPs[i]);
	 * numeroDeLSPsPorMinuto[ct][countSlotDeTempo++] = numeroDeLSPs[i]; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return numeroDeLSPsPorMinuto; }
	 */

	public static int[] gerarCurvasPoissonPorSlot(int slotsDeLSP, int numeroDeLSPSlot, int pico, int corteInicio,
			int corteFim) { // escala é a unidade de tempo

		// int slotsDeTempo = tempoEmSegundos / escala;
		int numeroDeLSPsPorSlot[] = new int[slotsDeLSP];

		int countSlotDeTempo = 0;
		while (countSlotDeTempo < numeroDeLSPsPorSlot.length) {

			int lambdaPico = pico;
			int numeroDeLSPs[] = new int[lambdaPico * 2];
			for (int i = 0; i < numeroDeLSPSlot; i++) {
				int tempo = GeradorDeNumerosAleatorios.poisson(lambdaPico);
				if (tempo < lambdaPico * 2) {
					++numeroDeLSPs[tempo];
				}
			}
			int iCurva = 0;

			if (corteInicio == -1) {
				iCurva = 0;
			} else {
				for (int i = 0; i < lambdaPico * 2 && numeroDeLSPs[i] < corteInicio; i++) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					// System.out.println(numeroDeLSPs[i]);
					iCurva++;
				}
			}

			int fCurva = numeroDeLSPs.length - 1;

			if (corteFim == -1) {
				fCurva = 2 * pico;
			} else {
				for (int i = numeroDeLSPs.length - 1; i > 0 && numeroDeLSPs[i] < corteFim; i--) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					fCurva--;
					// System.out.println(numeroDeLSPs[i]);
				}

			}

			for (int i = iCurva; i <= fCurva && (countSlotDeTempo < numeroDeLSPsPorSlot.length); i++) {
				// System.out.println(numeroDeLSPs[i]);
				numeroDeLSPsPorSlot[countSlotDeTempo++] = numeroDeLSPs[i];
			}

		}

		return numeroDeLSPsPorSlot;
	}

}
