package Main;

import java.io.IOException;
import java.util.Date;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;

import org.apache.hivemind.SymbolSourceContribution;
import org.jrobin.core.RrdException;

import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMDescription.BAMTypes;
//import BAM.BAMRecommender.BAMDescription.Problemas;
import BAM.BAMRecommender.BAMRecommenderNoGUI;
import BAM.BAMRecommender.BAMSolution;
import DSTE.*;
import Simulador.Debug;
import Simulador.Estatisticas;
import Simulador.GeradorDeNumerosAleatorios;
import Simulador.No;
import Simulador.RodadaDeSimulacao;

public class TesteSimulacao {

	public TesteSimulacao(RodadaDeSimulacao rodada) throws IOException,
			RrdException {


		Debug.setMensagem("============================ Início da Primeira Rodada ============================");
		Topologia to = new Topologia();

		// Mostra parámetros padrões
		Debug.setMensagem(ParametrosDSTE.getParametros(), 7, 7);

		/*
		 * ParametrosDSTE.BAMTypePadrao = BAMType.NoPreemptionMAM;
		 * ParametrosDSTE.BCPadrao= new double[] { 30, // BC[0] =CT0 + CT1 + CT2
		 * (Valor do Enlace) 30, // BC[1] = CT1 + CT2 40 // BC[2] = CT2 };
		 */
		
		if (ParametrosDSTE.topologiaManual)
		{
			// Carrega a topologia da rede manual
			Debug.setMensagem("Inicio: to.carregarTopologiaManual()", 10, 10);
			to.carregarTopologiaManual();
			Debug.setMensagem("Fim: to.carregarTopologiaManual()", 10, 10);
		} else
		{
			// Carrega a topologia da rede manual
			Debug.setMensagem("Inicio: to.carregarTopologiaArquivo()", 10, 10);
			to.carregarTopologiaArquivo();
			Debug.setMensagem("Fim: to.carregarTopologiaArquivo()", 10, 10);
		}
		if (ParametrosDSTE.matrizCaminhosManual)
		{
			// Carrega a matriz de caminhos por roteador manual
			Debug.setMensagem("Inicio: to.carregarMatrizDeCaminhosManual()", 10, 10);
			to.carregarMatrizDeCaminhosManual();
			Debug.setMensagem("Fim: to.carregarMatrizDeCaminhosManual()", 10, 10);
		}else
		{
			// Carrega a topologia da rede manual
			Debug.setMensagem("Inicio: to.carregarMatrizDeCaminhosArquivo()", 10, 10);
			to.carregarMatrizDeCaminhosArquivo();
			Debug.setMensagem("Fim: to.carregarMatrizDeCaminhosArquivo()", 10, 10);
		}

		// Gera Topologia Roteador x Roteador
		Debug.setMensagem("Inicio: to.gerarTopologiaDosRoteadores()", 10, 10);
		to.gerarTopologiaDosRoteadores();
		Debug.setMensagem("Fim: to.gerarTopologiaDosRoteadores()", 10, 10);

		// Gera Topologia Link x Roteador
		Debug.setMensagem("Inicio: to.gerarTopologiaDosLinks()", 10, 10);
		to.gerarTopologiaDosLinks();
		Debug.setMensagem("Fim: to.gerarTopologiaDosLinks()", 10, 10);

		// Imprime no console a Topologia Roteador x Roteador
		Debug.setMensagem("\r\n\r\n ==== Topologia Roteador x Roteador  ====");
		Debug.setMensagem(to.imprimirTopologiaDosRoteadores());

		// Imprime no console a Topologia Link x Roteador
		Debug.setMensagem("\r\n\r\n ==== Topologia Link x Roteador  ====");
		Debug.setMensagem(to.imprimirTopologiaDosLinks());

		// Imprime no console a Matriz de Caminhos				 por Roteador
		Debug.setMensagem("\r\n\r\n ==== Matriz de Caminhos por Roteador  ====");
		Debug.setMensagem(to.imprimirCaminhos());

		// Imprime no console o Status dos Links
		Debug.setMensagem("\r\n\r\n ==== Status dos Links  ====");
		Debug.setMensagem(to.statusLinks());

		// Inicializa tráfego
		Debug.setMensagem("\r\n\r\n ==== Inicializa o tráfego  ====");
		rodada.schedulep (3, 0.0, null);	

		// agenda estatísticas
		rodada.schedulep(4, ParametrosDSTE.RRDBatida+0.10, null);

		// agenda avaliação CBR
		if(ParametrosDSTE.RecomendacaoCBRSwitchBAM)
		{
			//Inicia Debug CBR
			BancoDeDados.setXML("", rodada.filename);
			//Agenda primeira avaliação
			rodada.schedulep (5, ParametrosDSTE.Janela+0.30, null);
		}
		//rodada.schedulep(7, ParametrosDSTE.RRDBatida + 0.20, null);
		
		try {
			rodada.estatistica.iniciarRRDLinks(to);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// inciatrafego2(rodada);

		// Inicializa a cadeia de eventos
		Debug.setMensagem("\r\n\r\n ==== Inicio da simulação  ====");
		try {
			cadeiaDeEventos(rodada, to);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Debug.setMensagem("\r\n\r\n ==== Fim da simulação  ====");

		Debug.setMensagem("============================ Fim da Primeira Rodada ============================");

	}

	public void cadeiaDeEventos(RodadaDeSimulacao rodada, Topologia to)
			throws IOException, RrdException, ExecutionException {
		No dados;
		// Lsp lsp;
		rodada.estatistica.tempoSimulacaoInicio=System.currentTimeMillis();
		//tempo de simulação
		while (ParametrosDSTE.condicaoDeParada(rodada))
		{

			

			// Debug.setMensagem("\r\n\r\n///////// Interacao("+(i++)+")////////");
			// Debug.setMensagem(rodada.imprime_evchain());
			dados = rodada.causep();
			Lsp auxLSP;
			switch (rodada.causep_ev) {
			case 1:
				// Estabelecer uma lsp
				rodada.estatistica.lspRequested++;
				rodada.estatistica.lspRequestedCT[((Lsp) dados.item).CT]++;
				rodada.estatistica.bandaRequested += ((Lsp) dados.item).Carga;
				rodada.estatistica.bandaRequestedCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
				Debug.setMensagem("Tipo 1 - Tentar estabelecer LSP "
						+ ((Lsp) dados.item).ID + " com "
						+ ((Lsp) dados.item).Carga + " Mbps CT="
						+ ((Lsp) dados.item).CT  + "");
				
				Long tempoInicial=System.nanoTime();
				Link[] menorCaminho = Roteamento.TryPath_CSPF(
						((Lsp) dados.item), to);
				rodada.estatistica.tempoAcumuladoGrantDeny+=System.nanoTime()-tempoInicial;
				
				if (menorCaminho != null) {
					Debug.setMensagem(" ==== Menor caminho  ====");
					Debug.setMensagem(to.imprimirCaminho(menorCaminho));
					((Lsp) dados.item).estabelecerLSP(menorCaminho);
					((Lsp) dados.item).status = LspStatus.estabelecida;

					
					Debug.setMensagem("========= LSP" + ((Lsp) dados.item).ID + " Estabelecida ========"  + " Em CT=" + ((Lsp) dados.item).CT);

					// agenda desestabelecimento
					rodada.schedulep(2, ((Lsp) dados.item).tempoDeVida, dados);

				} else {
					Debug.setMensagem("========= LSP" + ((Lsp) dados.item).ID + " Bloqueada ========" + " Em CT=" + ((Lsp) dados.item).CT );
					((Lsp) dados.item).status = LspStatus.bloqueada;
					rodada.estatistica.bloqueios++;
					rodada.estatistica.bloqueiosCT[((Lsp) dados.item).CT]++;
				}
				rodada.estatistica.tempoAcumuladoEstabelecimento+=System.nanoTime()-tempoInicial;
				break;
			case 2:
				// Desestabelece LSP: Liberacao da Banda Ocupada
				Debug.setMensagem("Tipo 2 - Desestabelece LSP "
						+ ((Lsp) dados.item).ID + " com "
						+ ((Lsp) dados.item).Carga + " Mbps  CT="
						+ ((Lsp) dados.item).CT 
						);
				((Lsp) dados.item).desestabeleceLSP();
				rodada.estatistica.lspUnbroken++;
				rodada.estatistica.lspUnbrokenCT[((Lsp) dados.item).CT]++;
				rodada.estatistica.bandaUnbrokenCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
				rodada.estatistica.bandaUnbroken += ((Lsp) dados.item).Carga;
				((Lsp) dados.item).status = LspStatus.finalizada;

				break;
			case 3:// geracao de trafego
				Debug.setMensagem("Tipo 3 - Agenda/Cria LSP ");
				ParametrosDSTE.trafegoManual(rodada, to, dados);

				break;

			
			case 4:
				//Insere estatísticas RDD
				rodada.estatistica.inserirDadosRRD((long) rodada.simtime()-ParametrosDSTE.RRDBatida);
				rodada.estatistica.inserirDadosAbsolutoRRD((long) rodada.simtime()-ParametrosDSTE.RRDBatida);
				rodada.estatistica.statusLinks(to, (long) rodada.simtime()-ParametrosDSTE.RRDBatida);

				rodada.schedulep(4, ParametrosDSTE.RRDBatida, null);
				break;
			case 5:
				//Avalia BAM via CBR
				
				
				CBRCase cbrCase = null;
				CBRQuery query = null;
				
				BancoDeDados.setXML( "Tempo de simulação:\t" + rodada.simtime() , rodada.filename);
				
				query = rodada.estatistica.getQuery(to.link[0], ParametrosDSTE.Gestor, ParametrosDSTE.SLAUtilizacaoCT, ParametrosDSTE.SLABloqueiosCT,ParametrosDSTE.SLAPreempcoesCT,ParametrosDSTE.SLADevolucoesCT);
				cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);
							
				
				if (cbrCase != null) {
					
					String nomeBAMAtual = null;
					
					if(to.link[0].bamType!=BAMType.PreemptionGBAM)
					{
						nomeBAMAtual = to.link[0].bamType.toString();
					}else
					{
						//Se BCLTH diferente de 0 é pq reflete Alloc
						if (to.link[0].BCLTH[0]!=0)
							
							nomeBAMAtual = "PreemptionAllocCTSharing";
						
						//Se BCLTH diferente é igual a 0 e BCHTL diferente de 0 é pq reflete RDM
						else if (to.link[0].BCHTL[2]!=0)
							
							nomeBAMAtual = "PreemptionRDM";
						
						//Se BCLTH e BCHTL igual a 0 é pq reflete MAM
						else
							nomeBAMAtual = "NoPreemptionMAM";
					}
					
					
					if (!cbrCase.getSolution().equals(nomeBAMAtual) ){
						
						BAMSolution solution = (BAMSolution) cbrCase.getSolution();
						//Temporário para forçar devolução
						Lsp LSPaux= new Lsp(); 
	            		LSPaux.Carga=0; 
						
						
						switch (solution.getBAMNovo()) {
						case NoPreemptionMAM:
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
							break;
						case PreemptionRDM:
							to.link[0].bamType = BAMType.PreemptionGBAM;
							to.link[0].BCLTH= new double[]
							{	100, //BC0 
								100, //BC1
								0  //BC2 Nunca mudar
							};
							LSPaux.CT=0; 
		              		BAM.devolutionG(to.link[0],LSPaux);
							
							
							to.link[0].BCHTL= new double[]
							{	0, //BC0 Nunca mudar
								000, //BC1
								000 //BC2
							};
							
							break;
						case PreemptionAllocCTSharing:
							to.link[0].bamType = BAMType.PreemptionGBAM;
							to.link[0].BCLTH= new double[]
							{	100, //BC0 
								100, //BC1
								0  //BC2 Nunca mudar
							};
							
							to.link[0].BCHTL= new double[]
							{	0, //BC0 Nunca mudar
								100, //BC1
								100 //BC2
							};
							break;
						}
						
						

						//BancoDeDados.setXML(rodada.simtime() + " SimCaseID - "+((BAMDescription) cbrCase.getDescription()).getCaseId()+"-> Recomenda BAM"+solution.getBAMNovo()+":"+((BAMDescription) query.getDescription()).toString(), rodada.filename);
						BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
						BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
						CBRCase novocase = new CBRCase();
						novocase.setDescription(desc);
						novocase.setSolution(sol);
						No no = new No();
						no.item=novocase;
						
						
						//Por enquanto só recomendação
						rodada.schedulep(5, ParametrosDSTE.Janela, no);
						
						//Agenda avaliar rentenção 
					////	rodada.schedulep(6, ParametrosDSTE.Janela, no);
						
						
					}else{
						Debug.setMensagem("Nada a fazer = mesmo BAM");
						
						//Agenda avaliar BAM via CBR
						rodada.schedulep(5, ParametrosDSTE.Janela, null );
					}
					
				}else {
					//Agenda avaliar BAM via CBR
					rodada.schedulep(5, ParametrosDSTE.Janela, null );
				}
					
				
				break;
			case 6:
				//Avalia rentenção
				CBRQuery queryRetain  = null;
				CBRCase atualCase = null;
				CBRCase novocase = ((CBRCase)dados.item);
				
				
								
				queryRetain = rodada.estatistica.getQuery(to.link[0], ParametrosDSTE.Gestor, ParametrosDSTE.SLAUtilizacaoCT, ParametrosDSTE.SLABloqueiosCT,ParametrosDSTE.SLAPreempcoesCT,ParametrosDSTE.SLADevolucoesCT);
				
				
				atualCase = BAMRecommenderNoGUI.getInstance().cycle(queryRetain);
				
				
				
				
				if(atualCase!= null){
				
					if(atualCase.getSolution().toString() == novocase.getSolution().toString()){
						BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
						((BAMDescription)novocase.getDescription()).setCaseId("bam"+(recommender.getCaseBase().getCases().size()+1));
						((BAMSolution)novocase.getSolution()).setId("bam"+(recommender.getCaseBase().getCases().size()+1));
						jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBase(), novocase);
					}else{
						BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
						((BAMDescription)novocase.getDescription()).setCaseId("bam"+(recommender.getCaseBaseDB2().getCases().size()+1));
						((BAMSolution)novocase.getSolution()).setId("bam"+(recommender.getCaseBaseDB2().getCases().size()+1));
						jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBaseDB2(), novocase);
						
					}
				
				
				}
					
				
				rodada.schedulep(5, ParametrosDSTE.Janela, null);
				

				
				
				/*Boolean UCT0, UCT1, UCT2, BCT0, BCT1, BCT2, PCT0, PCT1, PCT2, DCT0, DCT1, DCT2  = null;
				
				
				if ( to.link[0].CargaCTAtual[0]*100/to.link[0].CargaEnlace >=  ParametrosDSTE.SLAUtilizacaoCT[0] ){
					System.out.println("UtilizaçãoCT0 Okay");
					UCT0 = true;
				}else{
					UCT0 = false;
				}
				if ( to.link[0].CargaCTAtual[1]*100/to.link[0].CargaEnlace >=  ParametrosDSTE.SLAUtilizacaoCT[1] ){
					System.out.println("UtilizaçãoCT1 Okay");
					UCT1 = true;
				}else{
					UCT1 = false;
				}
				if ( to.link[0].CargaCTAtual[2]*100/to.link[0].CargaEnlace >=  ParametrosDSTE.SLAUtilizacaoCT[2] ){
					System.out.println("UtilizaçãoCT2 Okay");
					UCT2 = true;
				}else{
					UCT2 = false;
				}
				
				
				
				if ( rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,0)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLABloqueiosCT[0] ){
					System.out.println("BloqueiosCT0 Okay");
					BCT0 = true;
				}else{
					BCT0 = false;
				}
				if ( rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,1)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLABloqueiosCT[1] ){
					System.out.println("BloqueiosCT1 Okay");
					BCT1 = true;
				}else{
					BCT1 = false;
				}
				if ( rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,2)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLABloqueiosCT[2] ){
					System.out.println("BloqueiosCT2 Okay");
					BCT2 = true;
				}else{
					BCT2 = false;
				}
				
				
				
				if ( rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,0)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLAPreempcoesCT[0] ){
					System.out.println("PreempcoesCT0 Okay");
					PCT0 = true;
				}else{
					PCT0 = false;
				}
				if ( rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,1)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLAPreempcoesCT[1] ){
					System.out.println("PreempcoesCT1 Okay");
					PCT1 = true;
				}else{
					PCT1 = false;
				}
				if ( rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,2)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLAPreempcoesCT[2] ){
					System.out.println("PreempcoesCT2 Okay");
					PCT2 = true;
				}else{
					PCT2 = false;
				}
				
				
				
				if ( rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,0)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLADevolucoesCT[0] ){
					System.out.println("DevolucoesCT0  Okay");
					DCT0 = true;
				}else{
					DCT0 = false;
				}
				if ( rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,1)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLADevolucoesCT[1] ){
					System.out.println("DevolucoesCT1  Okay");
					DCT1 = true;
				}else{
					DCT1 = false;
				}
				if ( rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,2)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) <=ParametrosDSTE.SLADevolucoesCT[2] ){
					System.out.println("DevolucoesCT2  Okay");
					DCT2 = true;
				}else{
					DCT2 = false;
				}
				
				
				
				if (UCT0 && UCT1 && UCT2 && BCT0 && BCT1 && BCT2 && PCT0 && PCT1 && PCT2 && DCT0 && DCT1 && DCT2){
					BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
					jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBase(), novocase);
				}*/
				
				
				
				
			break;
			
			
			case 7:
				//Gerar base log debug,etc
				
				
				
				
				/*if (rodada.simtime()== 3660.2){
					Lsp LSPaux= new Lsp(rodada); 
            		LSPaux.Carga=0; 
					to.link[0].bamType = BAMType.PreemptionGBAM;
					
			
					to.link[0].BCLTH= new double[]
					{	100, //BC0 
						100, //BC1
						0  //BC2 Nunca mudar
					};
					LSPaux.CT=0; 
              		BAM.devolutionG(to.link[0],LSPaux);
					
					
					to.link[0].BCHTL= new double[]
					{	0, //BC0 Nunca mudar
						100, //BC1
						100 //BC2
					};
					
					LSPaux.CT=2; 
              		BAM.preemptionG(to.link[0],LSPaux); 
				} */
				
				

				String nomeBAMAtual = null;
				
				if(to.link[0].bamType!=BAMType.PreemptionGBAM)
				{
					nomeBAMAtual = to.link[0].bamType.toString();
				}else
				{
					//Se BCLTH diferente de 0 é pq reflete Alloc
					if (to.link[0].BCLTH[0]!=0)
						
						nomeBAMAtual = "PreemptionAllocCTSharing";
					
					//Se BCLTH diferente é igual a 0 e BCHTL diferente de 0 é pq reflete RDM
					else if (to.link[0].BCHTL[2]!=0)
						
						nomeBAMAtual = "PreemptionRDM";
					
					//Se BCLTH e BCHTL igual a 0 é pq reflete MAM
					else
						
						nomeBAMAtual = "NoPreemptionMAM";
				}
				
				
					
					/*if(rodada.simtime() <= 3600*1){
						
												
					}else if (rodada.simtime() <= 3600*2){// 7.200
						

					}else if (rodada.simtime() <= 3600*3){//  10.800
						
						
					}else if (rodada.simtime() <= 3600*4){ //  14.400	
						
						
					}else if (rodada.simtime() <= 3600*5){
						
						
					}else if (rodada.simtime() >= 16800)
					{
						
						
					}*/
					
					
				/*	
				 * Lsp LSPaux= new Lsp(rodada); 
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
						100, //BC1
						100 //BC2
					};
					
					LSPaux.CT=2; 
              		BAM.preemptionG(to.link[0],LSPaux); 
					*
					*/
					
					
					
				double []bloqueiosCTJanela   	= new double [] {0, 0, 0} ;  
				double []preempcoesCTJanela  	= new double [] {0, 0, 0} ;  
				double []devolucoesCTJanela   	= new double [] {0, 0, 0} ;  
			 	
				 	
				bloqueiosCTJanela[0] = rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 0) > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,0)/rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 0):0;
				bloqueiosCTJanela[1] = rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 1) > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,1)/rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 1):0;
				bloqueiosCTJanela[2] = rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 2) > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,2)/rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 2):0;

				
				preempcoesCTJanela[0] = (rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 0)
						+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 0)) > 0 
							? (double)rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,0)/
								(rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 0)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 0))		:0;
				
				preempcoesCTJanela[1] = (rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 1)
						+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 1)) > 0 
							? (double)rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,1)/
								(rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 1)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 1))		:0;				
				
				preempcoesCTJanela[2] = (rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 2)
						+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 2)) > 0 
							? (double)rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,2)/
								(rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 2)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 2))		:0;				

				devolucoesCTJanela[0] = (rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 0)
						+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 0)) > 0 
							? (double)rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,0)/
								(rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 0)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 0))		:0;
								
				devolucoesCTJanela[1] = (rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 1)
						+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 1)) > 0 
							? (double)rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,1)/
								(rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 1)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 1))		:0;	
								
				devolucoesCTJanela[2] = (rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 2)
						+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 2)) > 0 
							? (double)rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,2)/
								(rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela, 2)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela, 2))		:0;	

					
					BancoDeDados.setXML(  rodada.simtime() + "\t"
							+ nomeBAMAtual + "\t"
							+ ParametrosDSTE.Janela + "\t"
							
							+ to.link[0].CargaEnlace * to.link[0].BC[0] / 100 + "\t"
							+ to.link[0].CargaEnlace * to.link[0].BC[1] / 100 + "\t"
							+ to.link[0].CargaEnlace * to.link[0].BC[2] / 100 + "\t"
							
							
							/*+ to.link[0].CargaCTAtual[0] + "\t"
							+ to.link[0].CargaCTAtual[1] + "\t"
							+ to.link[0].CargaCTAtual[2] + "\t"*/
							
							
							+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace + "\t"
							+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace + "\t"
							+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace + "\t"

							
							//+ rodada.estatistica.lspRequested + "\t"
							
						/*								
							+ rodada.estatistica.lspRequestedCT[0] + "\t"
							+ rodada.estatistica.lspRequestedCT[1] + "\t"
							+ rodada.estatistica.lspRequestedCT[2] + "\t"
							
														
							+ rodada.estatistica.lspEstablishedCT[0] + "\t"
							+ rodada.estatistica.lspEstablishedCT[1] + "\t"
							+ rodada.estatistica.lspEstablishedCT[2] + "\t"

							
							+ rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 0) + "\t"
							+ rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 1) + "\t"
							+ rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 2) + "\t"

							
							+ rodada.estatistica.lspEstablishedCT(ParametrosDSTE.Janela, 0) + "\t"
							+ rodada.estatistica.lspEstablishedCT(ParametrosDSTE.Janela, 1) + "\t"
							+ rodada.estatistica.lspEstablishedCT(ParametrosDSTE.Janela, 2) + "\t"
											*/

							
						
							
						/*
							+ rodada.estatistica.bloqueios + "\t"
							+ rodada.estatistica.preempcoes + "\t"
							+ rodada.estatistica.devolucoes + "\t"
							
							
							
							+ rodada.estatistica.bloqueiosCT[0] + "\t"
							+ rodada.estatistica.bloqueiosCT[1] + "\t"
							+ rodada.estatistica.bloqueiosCT[2] + "\t"
							
							+ rodada.estatistica.preempcoesCT[0] + "\t"
							+ rodada.estatistica.preempcoesCT[1] + "\t"
							+ rodada.estatistica.preempcoesCT[2] + "\t"

							+ rodada.estatistica.devolucoesCT[0] + "\t"
							+ rodada.estatistica.devolucoesCT[1] + "\t"
							+ rodada.estatistica.devolucoesCT[2] + "\t"
							*/
											
							+ bloqueiosCTJanela[0] + "\t"
							+ bloqueiosCTJanela[1] + "\t"
							+ bloqueiosCTJanela[2] + "\t"
							
							+ preempcoesCTJanela[0] + "\t"
							+ preempcoesCTJanela[1] + "\t"
							+ preempcoesCTJanela[2] + "\t"
							
							+ devolucoesCTJanela[0] + "\t"
							+ devolucoesCTJanela[1] + "\t"
							+ devolucoesCTJanela[2] + "\t"

							//, "saida");

							, rodada.filename);
				
				
					
					rodada.schedulep(7, ParametrosDSTE.Janela , null);
					
					
				break;
			
			

			}
			Debug.setMensagem(" ==== Status dos Links  ====");
			Debug.setMensagem(to.statusLinks());
			/////Debug.setMensagem(rodada.imprime_evchain(), 0, 0);
			//BancoDeDados.setXML(rodada.imprime_evchain(),"debug2");

		}
		Debug.setMensagem("\r\n\r\n ==== Status dos Links  ====");
		Debug.setMensagem(to.statusLinks());
		rodada.estatistica.tempoSimulacaoFim=System.currentTimeMillis();
		Debug.setMensagem(rodada.estatistica.getEstatisticas());
		Debug.setMensagem(BAMRecommenderNoGUI.getInstance().getStringCases());
		try {
			rodada.estatistica.gerarRRDPNGpreempcao();
			rodada.estatistica.gerarRRDPNGlspRequested();
			rodada.estatistica.gerarRRDPNGlspEstablished();
			rodada.estatistica.gerarRRDPNGlspUnbroken();
			rodada.estatistica.gerarRRDPNGbloqueio();
			rodada.estatistica.gerarRRDPNGdevolucao();
			rodada.estatistica.gerarRRDXML();
			rodada.estatistica.gerarLinksRRDXML();
			rodada.estatistica.gerarLinkRRDPNG(to);
		} catch (RrdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
