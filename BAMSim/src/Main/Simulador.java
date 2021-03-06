package Main;
import java.io.IOException;
import java.util.Random; 

import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;

import org.jrobin.core.RrdException;

import BAM.BAMRecommender.BAMRecommenderNoGUI;
import DSTE.*;
import Simulador.*;
/****************** ESTRUTURAS BÁSICAS RELACIONADAS A CADEIA DE EVENTOS **********************/

public class Simulador
{
	public static void main(String[] args) throws IOException, RrdException {

		BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
		if(ParametrosDSTE.RecomendacaoCBRSwitchBAM)
		{
			try
			{
				recommender.configure();
				recommender.preCycle();
				recommender.setSimConfig(ParametrosDSTE.getSimilarityConfig());
				
				
					
			
				
				}catch(Exception e)
				{
				org.apache.commons.logging.LogFactory.getLog(BAMRecommenderNoGUI.class).error(e);
				//javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
			RodadaDeSimulacao sim[] = new RodadaDeSimulacao[ParametrosDoSimulador.MAX_SIMULATIONS];
			String [] filenames= new String[ParametrosDoSimulador.MAX_SIMULATIONS];
			
			int sementeAgora = ParametrosDoSimulador.semente;
			
			for (int j=0;j<sim.length;j++)
			{
				 if (j>=1){
					GeradorDeNumerosAleatorios.rand= new Random(sementeAgora += (j+3)*7);
				}else{
					GeradorDeNumerosAleatorios.rand= new Random(sementeAgora);
				}
				
				sim[j]=new RodadaDeSimulacao();
				Debug.filename=sim[j].filename;
				filenames[j]=sim[j].filename;
				TesteSimulacao t1 = new TesteSimulacao(sim[j]);
				Debug.setMensagem("Numero da Rodada:\t" + (j+1) + "ª",3,3);
				Debug.setMensagem("Semente da Rodada:\t" + sementeAgora ,3,3);
				Debug.setMensagem("#===========================================================================================#\r\n\r\n",3,3);

			}
	
			/*RodadaDeSimulacao sim = new RodadaDeSimulacao();
			
			
			
			RodadaDeSimulacao sim2 = new RodadaDeSimulacao();
			Debug.filename=sim2.filename;
			//GeradorDeNumerosAleatorios.rand = new Random(ParametrosDoSimulador.semente);
			TesteSimulacao t2 = new TesteSimulacao(sim2);
			
			RodadaDeSimulacao sim3 = new RodadaDeSimulacao();
			Debug.filename=sim3.filename;
			//GeradorDeNumerosAleatorios.rand = new Random(ParametrosDoSimulador.semente);
			TesteSimulacao t3 = new TesteSimulacao(sim3);
			

			filenames[0]="1441757535792";
			filenames[1]="1441757680710";
			filenames[2]="1441757827994";
			//GraficosRRD.agregarLspGeradas(filenames);
			 * 
			 */
			/*
			if(ParametrosDoSimulador.MAX_SIMULATIONS>1)
			{
				GraficosRRD.agregarRRD(filenames,"Preempções","preempcao");
				GraficosRRD.agregarRRD(filenames,"LSPs Requested","lspRequested");
				GraficosRRD.agregarRRD(filenames,"Bloqueios","bloqueio");
				GraficosRRD.agregarRRD(filenames,"Devoloções","devolucao");
			}*/
			if(ParametrosDSTE.RecomendacaoCBRSwitchBAM)
			{	
				try {
					recommender.postCycle();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.exit(0);
	}
	
}
