package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.jrobin.core.RrdException;

import BAM.BAMRecommender.BAMDescription;
//import BAM.BAMRecommender.BAMDescription.Problemas;
import BAM.BAMRecommender.BAMRecommenderNoGUI;
import BAM.BAMRecommender.BAMSolution;
import BAM.BAMRecommender.BAMTypes;
import DSTE.BAM;
import DSTE.BAMType;
import DSTE.BancoDeDados;
import DSTE.Link;
import DSTE.Lsp;
import DSTE.LspStatus;
import DSTE.ParametrosDSTE;
import DSTE.Roteamento;
import DSTE.Topologia;
import Simulador.Debug;
import Simulador.No;
import Simulador.RodadaDeSimulacao;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;

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
			rodada.schedulep (5, ParametrosDSTE.Janela+0.40, null);
		}
		//rodada.schedulep(7, ParametrosDSTE.RRDBatida + 0.20, null);
		
		try {
			rodada.estatistica.iniciarRRDLinks(to);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		// inciatrafego2(rodada);

		// Inicializa a cadeia de eventos
		Debug.setMensagem("\r\n\r\n ==== Inicio da simulação  ====");
		try {
			cadeiaDeEventos(rodada, to);
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		Debug.setMensagem("\r\n\r\n ==== Fim da simulação  ====");

		Debug.setMensagem("============================ Fim da Primeira Rodada ============================");

	}

	public void cadeiaDeEventos(RodadaDeSimulacao rodada, Topologia to)
			throws IOException, RrdException, ExecutionException {
		No dados;
		//int AGENDAMENTO = 0;
		CBRCase casoRenAntigo = null;

		// Lsp lsp;
		rodada.estatistica.tempoSimulacaoInicio=System.currentTimeMillis();
		//tempo de simulação
		while (ParametrosDSTE.condicaoDeParada(rodada))
		{

			

			// Debug.setMensagem("\r\n\r\n///////// Interacao("+(i++)+")////////");
			// Debug.setMensagem(rodada.imprime_evchain());
			dados = rodada.causep();
			//Lsp auxLSP;
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
				
				BancoDeDados.setXML(rodada.simtime() + " Entrou na Recomendação", rodada.filename);
				
				int mudouBAM= -1;
				CBRCase cbrCase = null;
				CBRQuery query = null;
				
				query = rodada.estatistica.getQuery(to.link[0], ParametrosDSTE.Gestor, ParametrosDSTE.SLAUtilizacaoCT, ParametrosDSTE.SLABloqueiosCT,ParametrosDSTE.SLAPreempcoesCT,ParametrosDSTE.SLADevolucoesCT);
				cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);
				
				String nomeBAMAtual = ((BAMDescription)query.getDescription()).getBAMAtual().name();
				
				BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
				
				CBRCase novocase = new CBRCase();
				novocase.setDescription(desc);
				No no = new No();
				
				
			
				
				if (cbrCase != null) {
					
					String solutionRecomendada = ((BAMSolution) cbrCase.getSolution()).getBAMNovo().toString();
					
					if (solutionRecomendada != nomeBAMAtual ){
						//Temporário para forçar devolução
						switchBAM(to, solutionRecomendada);
						mudouBAM= 1;
						BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
						//BancoDeDados.setXML(rodada.simtime() + " SimCaseID"+((BAMDescription) cbrCase.getDescription()).getCaseId()+"-> Recomenda BAM"+solutionRecomendada+":"+((BAMDescription) query.getDescription()).toTabela(), rodada.filename);
						novocase.setSolution(sol);
						no.item=novocase;
						BancoDeDados.setXML("\nCASO SUGERIDO ID: " + ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo(), rodada.filename);
						
						
						//BancoDeDados.setXML(/*rodada.simtime() +*/ "######## Dentro da linha de corte ######  BAM modificado. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)) + "##################", rodada.filename);
						
						
					}else{
						mudouBAM=-1; /// não é necessário agendar avaliação
						BancoDeDados.setXML(/*"\n" + rodada.simtime() + */"######## Dentro da linha de corte ###### BAM mantido MESMO CENARIO - Nada a fazer ", rodada.filename);
						//Agenda avaliar BAM via CBR
						
					}
					
				}else if (ParametrosDSTE.RecomendacaoCBRRetencao){
					mudouBAM=1;
					//BancoDeDados.setXML("Nenhum caso válido na base", rodada.filename);
					int []bams = BAMRecommenderNoGUI.getInstance().foraDaLinha(query);  // busca na bae de casos negativa se existe alguma caso negativado na base e de que BAm eles são
					BAMTypes bam = null;
					
					BancoDeDados.setXML( "Valor MAM:"+bams[0]+" Valor RDM:"+bams[1]+" Valor ALLOC: "+bams[2] , rodada.filename);
					///man=0   RDM=4  ALLOC=5
					
					switch (nomeBAMAtual) {
					case "NoPreemptionMAM":
						if (bams[2]==0){
							 bam = BAMTypes.values()[5];
							 switchBAM(to, bam.name());
						}else if (bams[1]==0){
							 bam = BAMTypes.values()[4];
							 switchBAM(to, bam.name());
						}else {
							bam = BAMTypes.values()[0];
							 mudouBAM= 0;
						}	
						break;
					
					case "PreemptionRDM":
						if (bams[2]==0){
							 bam = BAMTypes.values()[5];
							 switchBAM(to, bam.name());
						}else if (bams[1]==0){
							bam = BAMTypes.values()[4];
							mudouBAM= 0;
						}else {
							 bam = BAMTypes.values()[0];
							 switchBAM(to, bam.name());
						}
						break;
					
					case "PreemptionAllocCTSharing":
						if (bams[2]==0){
							bam = BAMTypes.values()[5];
							mudouBAM= 0;
						}else if (bams[1]==0){
							 bam = BAMTypes.values()[4];
							 switchBAM(to, bam.name());
						}else {
							 bam = BAMTypes.values()[0];
							 switchBAM(to, bam.name());
						}
						break;
					}
					
					BAMSolution sol = new BAMSolution() ;
					sol.setBAMNovo(bam);
					sol.setAceita(true);
					novocase.setSolution(sol);
					no.item=novocase;
					BancoDeDados.setXML("\nCASO SUGERIDO ID:" + ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo()+ "\n", rodada.filename);
					//Por enquanto só recomendação
									
					if (mudouBAM==1)
					{
						//BancoDeDados.setXML(rodada.simtime() + "######## Fora da linha de corte ######  BAM Modificado. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)) + "##################", rodada.filename);
					}else {
						//BancoDeDados.setXML(rodada.simtime() + "######## Fora da linha de corte ######  BAM Mantido. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela-0.10)) + "##################", rodada.filename);

					}
				}
					
				//Agenda avaliar rentenção 
				if (ParametrosDSTE.RecomendacaoCBRRetencao){
					if (mudouBAM==1){
						if (casoRenAntigo!=null){
						//	rodada.cancelp_tkn(casoRenAntigo);
							//BancoDeDados.setXML(rodada.simtime()+300 + "##############. Desagenda retenção - Duas Trocas ==== avaliar isso",rodada.filename );
						}
						casoRenAntigo = (CBRCase)no.item;
						
						rodada.schedulep(6, ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10, no);
						rodada.schedulep(5, ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida, null);
						//rodada.schedulep(5, ParametrosDSTE.Janela, null);
						
					}else if (mudouBAM==0){
						rodada.schedulep(6, ParametrosDSTE.Janela-0.10, no);///////////////////////colocar  batida em vez ee janela??? ,,,<<<<<<<<<<<<<+============
						//casoRenAntigo = null;
						rodada.schedulep(5, ParametrosDSTE.Janela, null);
					}else{
						//casoRenAntigo = null;
						rodada.schedulep(5, ParametrosDSTE.Janela, null);
					}
					
				}else{
					rodada.schedulep(5, ParametrosDSTE.Janela, null);
				}
					BancoDeDados.setXML(rodada.simtime() + " Saio da Recomendação.", rodada.filename);	
					//rodada.schedulep(5, ParametrosDSTE.Janela, null);
				
				
				break;
			
			case 6:
				//Avalia rentenção
				BancoDeDados.setXML(rodada.simtime() + " Entrou em retenção.", rodada.filename);
				
					 novocase = ((CBRCase)dados.item);
					((BAMDescription)novocase.getDescription()).setCaseId("tmp01") ;
					
					query = rodada.estatistica.getQuery(to.link[0], ParametrosDSTE.Gestor, ParametrosDSTE.SLAUtilizacaoCT, ParametrosDSTE.SLABloqueiosCT,ParametrosDSTE.SLAPreempcoesCT,ParametrosDSTE.SLADevolucoesCT);
					
					//Caso que será colocado na base de caos negativos. OBS é possivel cria tb um 3° caso aqui  (caso atual com a nova query acima) 
					CBRCase badcase = new CBRCase();
					badcase.setDescription(    ( (BAMDescription)novocase.getDescription()  ).clone()    );
					badcase.setSolution(null);
								
					
					int lspRequestedAgora = rodada.estatistica.lspRequested(ParametrosDSTE.Janela);
					int lspRequestedAnterior = Math.abs(rodada.estatistica.lspRequested(ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida) - rodada.estatistica.lspRequested(ParametrosDSTE.Janela*2+ParametrosDSTE.RRDBatida ) );
					
					int difLSPs = lspRequestedAgora - lspRequestedAnterior;
					
					
					if (Math.abs(difLSPs)< 200){ // verifica se houve mudança na rede	
						
							
							int score = 10;
							String bamAnterior = ((BAMDescription)novocase.getDescription()).getBAMAtual().name();
							double []utilizacaoCTJanelaAnterior  	= new double [] {((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT0(), ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT1(), ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT2()} ;  
							double []bloqueioCTJanelaAnterior   	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT2()} ;
							double []preempcoesCTJanelaAnterior  	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT2()} ;  
							double []devolucoesCTJanelaAnterior   	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT2()} ;				
							
							String bamAgora = ((BAMDescription)query.getDescription()).getBAMAtual().name();
							double []utilizacaoCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT0(), ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT1(), ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT2()} ;
							double []bloqueiosCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT0(), ((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT1(), ((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT2()} ; 
							double []preempcoesCTJanelaAgora  	= new double [] {((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT0(), ((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT1(), ((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT2()} ;  
							double []devolucoesCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT0(), ((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT1(), ((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT2()} ;
							
							double somatorioUtilizacaoCTJanelaAnterior = utilizacaoCTJanelaAnterior[0]+utilizacaoCTJanelaAnterior[1]+	utilizacaoCTJanelaAnterior[2];
							double somatorioUtilizacaoCTJanelaAgora    = utilizacaoCTJanelaAgora[0]+utilizacaoCTJanelaAgora[1]+utilizacaoCTJanelaAgora[2];
							
							double somatorioPonderadoBloqueioCTJanelaAnterior = (bloqueioCTJanelaAnterior[0]*to.link[0].BC[0] + bloqueioCTJanelaAnterior[1]*to.link[0].BC[1] + bloqueioCTJanelaAnterior[2]*to.link[0].BC[2])  /  (to.link[0].BC[0] + to.link[0].BC[1] + to.link[0].BC[2]) ;				
							double somatorioPonderadoBloqueioCTJanelaAgora = (bloqueiosCTJanelaAgora[0]*to.link[0].BC[0] + bloqueiosCTJanelaAgora[1]*to.link[0].BC[1] + bloqueiosCTJanelaAgora[2]*to.link[0].BC[2])  /  (to.link[0].BC[0] + to.link[0].BC[1] + to.link[0].BC[2]) ;				
							
							double somatorioPreempcoesCTJanelaAnterior = preempcoesCTJanelaAnterior[0]+preempcoesCTJanelaAnterior[1]+preempcoesCTJanelaAnterior[2];
							double somatorioPreempcoesCTJanelaAgora = preempcoesCTJanelaAgora[0]+preempcoesCTJanelaAgora[1]+preempcoesCTJanelaAgora[2];
							
							double somatorioDevolucoesCTJanelaAnterior = devolucoesCTJanelaAnterior[0]+devolucoesCTJanelaAnterior[1]+devolucoesCTJanelaAnterior[2];
							double somatorioDevolucoesCTJanelaAgora = devolucoesCTJanelaAgora[0]+devolucoesCTJanelaAgora[1]+devolucoesCTJanelaAgora[2];
							
							
							double utilizacao =  ( ( Math.min(to.link[0].BC[0], to.link[0].BC[1]) + Math.min(to.link[0].BC[1], to.link[0].BC[2]) ))/ (to.link[0].BC[0] + to.link[0].BC[1] + to.link[0].BC[2]);
							double bloqueio = utilizacao * ParametrosDSTE.SLABloqueios;
							
							
							
						if (bamAnterior == BAMTypes.NoPreemptionMAM.name()){
							switch (bamAgora) {
							case "NoPreemptionMAM":
								//não tive problema de performasse na rede. Vou verificar agora subina e decida
								if ( somatorioUtilizacaoCTJanelaAnterior < utilizacao  && somatorioPonderadoBloqueioCTJanelaAgora<bloqueio )
								{
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
								}
							break;
							
							case "PreemptionRDM":
								/*if(somatorioPreempcoesCTJanelaAnterior+somatorioDevolucoesCTJanelaAnterior > ParametrosDSTE.SLADevolucoes){
								}*/
								if(somatorioPreempcoesCTJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else {
										//não tive problema de performasse na rede. Vou verificar agora subina e decida
										if ( somatorioUtilizacaoCTJanelaAnterior > utilizacao  || somatorioPonderadoBloqueioCTJanelaAgora>bloqueio ){
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										}
								}
							break;
							
							case "PreemptionAllocCTSharing":
								if(somatorioPreempcoesCTJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioDevolucoesCTJanelaAgora > ParametrosDSTE.SLADevolucoes ){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else {
										//não tive problema de performasse na rede. Vou verificar agora subina e decida
										if ( somatorioUtilizacaoCTJanelaAnterior > utilizacao  || somatorioPonderadoBloqueioCTJanelaAgora>bloqueio ){
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										}
								}
							break;
							}
							
							
							
							
							
						} else if (bamAnterior == BAMTypes.PreemptionRDM.name()){
							switch (bamAgora) {
							case "NoPreemptionMAM":
								//não tive problema de performasse na rede. Vou verificar agora subina e decida
									if ( somatorioUtilizacaoCTJanelaAnterior < utilizacao  && somatorioPonderadoBloqueioCTJanelaAgora<bloqueio )
									{
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
									}
							break;
							
							case "PreemptionRDM":
								if(somatorioPreempcoesCTJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioPreempcoesCTJanelaAgora > ParametrosDSTE.SLAPreempcoes){   
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else {
									//não tive problema de performasse na rede. Vou verificar agora subina e decida ??????/
								}
							break;
							
							case "PreemptionAllocCTSharing":
								if(somatorioPreempcoesCTJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioPreempcoesCTJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioDevolucoesCTJanelaAgora > ParametrosDSTE.SLADevolucoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else {
										//não tive problema de performasse na rede. Vou verificar agora subina e decida
										if ( somatorioUtilizacaoCTJanelaAnterior > utilizacao  || somatorioPonderadoBloqueioCTJanelaAgora>bloqueio ){
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										}
								}
							break;
							}


							
							
							
						}else if (bamAnterior == BAMTypes.PreemptionAllocCTSharing.name()){
							switch (bamAgora) {
							case "NoPreemptionMAM":
								
								//não tive problema de performasse na rede. Vou verificar agora subina e decida
								if ( somatorioUtilizacaoCTJanelaAnterior < utilizacao  && somatorioPonderadoBloqueioCTJanelaAgora<bloqueio )
								{
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
								}
								
							break;
							
							case "PreemptionRDM":
								if(somatorioPreempcoesCTJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioPreempcoesCTJanelaAgora > ParametrosDSTE.SLAPreempcoes){   
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else {
										//não tive problema de performasse na rede. Vou verificar agora subina e decida
										if ( somatorioUtilizacaoCTJanelaAnterior < utilizacao  && somatorioPonderadoBloqueioCTJanelaAgora<bloqueio )
										{
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
										}
								}
							break;
							
							case "PreemptionAllocCTSharing":
								if(somatorioPreempcoesCTJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioPreempcoesCTJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(somatorioDevolucoesCTJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else if(somatorioDevolucoesCTJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else {
									//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
								}
							break;
							}
						}
							
							//BancoDeDados.setXML(rodada.simtime() + ": Avaliando BAM, pois Dif LSPs: "+ difLSPs + ". O Score é:" + score,rodada.filename );
							
							//System.out.println(rodada.simtime() + " " + "Score:" + score);
							if (novocase.getSolution() !=null ){
								BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
								
								((BAMDescription)novocase.getDescription()).setCaseId("N_"+(recommender.getCaseBase().getCases().size()+1));
								
								((BAMSolution)novocase.getSolution()).setId("N_"+(recommender.getCaseBase().getCases().size()+1));
								
								jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBase(), novocase);
								BancoDeDados.setXML( rodada.simtime() + ": BAM: "+  ((BAMDescription)novocase.getDescription()).getCaseId() + " = Aceito. Armazenado na base positiva. Score: "+score + " Dif LSFs:" + difLSPs  ,rodada.filename );
								
							}
							
							if(badcase.getSolution()!=null) {
								BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
								
								((BAMDescription)badcase.getDescription()).setCaseId("R_"+(recommender.getCaseBaseDB2().getCases().size()+1));
								
								((BAMSolution)badcase.getSolution()).setId("R_"+(recommender.getCaseBaseDB2().getCases().size()+1));
								
								jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBaseDB2(), badcase);
								BancoDeDados.setXML( rodada.simtime() + ": BAM: "+ ((BAMDescription)badcase.getDescription()).getCaseId()   +  " = Rejeitado. Armazenado na base negativa. Score:" + score + " Dif LSFs:" + difLSPs,rodada.filename );
								/*BancoDeDados.setXML( rodada.simtime() + "Voltando para :"+ bamAnterior ,rodada.filename );
								 switchBAM(to, bamAnterior);*/
								
							}
					}else{
							BancoDeDados.setXML(rodada.simtime() + ": BAM não validado. A rede mudou o comportamento. Dif LSFs:" + difLSPs,rodada.filename );
						}
				BancoDeDados.setXML(rodada.simtime() + " Saiu em retenção\n", rodada.filename);
			break;
			
			
			case 7:
				
				
				

				 nomeBAMAtual = null;
				
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
										/////////////+ ParametrosDSTE.Janela + "\t"
										
										///////////////+ to.link[0].CargaEnlace * to.link[0].BC[0] / 100 + "\t"
										///////////////+ to.link[0].CargaEnlace * to.link[0].BC[1] / 100 + "\t"
										////////////////+ to.link[0].CargaEnlace * to.link[0].BC[2] / 100 + "\t"
										
										
									/*	+ to.link[0].CargaCTAtual[0] + "\t"
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
										+ (rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace)  + "\t"
										+ rodada.estatistica.lspRequested(ParametrosDSTE.Janela)

										//, "saida");
							//, rodada.filename+"_7");
							, rodada.filename);
								
								BancoDeDados.setXML(  rodada.simtime() + "\t"
										+ nomeBAMAtual + "\t"
										
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace + "\t"
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace + "\t"
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace + "\t"
														
										+ bloqueiosCTJanela[0] + "\t"
										+ bloqueiosCTJanela[1] + "\t"
										+ bloqueiosCTJanela[2] + "\t"
										
										+ preempcoesCTJanela[0] + "\t"
										+ preempcoesCTJanela[1] + "\t"
										+ preempcoesCTJanela[2] + "\t"
										
										+ devolucoesCTJanela[0] + "\t"
										+ devolucoesCTJanela[1] + "\t"
										+ devolucoesCTJanela[2] + "\t"
										+ (rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace)  + "\t"
										+ rodada.estatistica.lspRequested(ParametrosDSTE.Janela)
										

										//,"saida");
										, rodada.filename+"_7");
								
				
					rodada.schedulep(7, ParametrosDSTE.RRDBatida, null);
					//rodada.schedulep(7, ParametrosDSTE.Janela*3 , null);
					
					
				break;
			
			

			}
			Debug.setMensagem(" ==== Status dos Links  ====");
			Debug.setMensagem(to.statusLinks());
			//Debug.setMensagem(rodada.imprime_evchain(), 0, 0);
			//BancoDeDados.setXML(rodada.imprime_evchain(),"debug2");

		}
		
		
		Debug.setMensagem("\r\n\r\n ==== Status dos Links  ====");
		Debug.setMensagem(to.statusLinks());
		rodada.estatistica.tempoSimulacaoFim=System.currentTimeMillis();
		Debug.setMensagem(rodada.estatistica.getEstatisticas());
		Debug.setMensagem(BAMRecommenderNoGUI.getInstance().getStringCases());
		try {
			System.out.println("Plotando os gráficos...");
			rodada.estatistica.gerarLinkRRDPNG(to);
			rodada.estatistica.gerarRRDPNGpreempcao();
			rodada.estatistica.gerarRRDPNGlspRequested();
			rodada.estatistica.gerarRRDPNGlspEstablished();
			rodada.estatistica.gerarRRDPNGlspUnbroken();
			rodada.estatistica.gerarRRDPNGbloqueio();
			rodada.estatistica.gerarRRDPNGdevolucao();
			rodada.estatistica.gerarRRDXML();
			rodada.estatistica.gerarLinksRRDXML();
			System.out.println("Agora sim acabou");
		} catch (RrdException e) {
			
			e.printStackTrace();
		}

	}

	private void switchBAM(Topologia to, String solutionRecomendada) {
		Lsp LSPaux= new Lsp(); 
		LSPaux.Carga=0; 
		
		
		switch (solutionRecomendada) {
		case "NoPreemptionMAM":
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
		case "PreemptionRDM":
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
			
			break;
		case "PreemptionAllocCTSharing":
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
			break;
		}
		
		
	}

}
