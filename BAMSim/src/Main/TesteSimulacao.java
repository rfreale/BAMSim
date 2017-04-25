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
		
		rodada.schedulep(7, ParametrosDSTE.RRDBatida + 0.20, null);
		
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
				
				BancoDeDados.setXML(rodada.simtime() + "\tEntrou na Recomendação", rodada.filename);
				
				int mudouBAM= -1;
				CBRCase cbrCase = null;
				CBRQuery query = null;
				
				query = rodada.estatistica.getQuery(to.link[0]);
				cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);
				
				String nomeBAMAtual = ((BAMDescription)query.getDescription()).getBAMAtual().name();
				
				BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
				
				CBRCase novocase = new CBRCase();
				novocase.setDescription(desc);
				No no = new No();
				
				
			
				
				if (cbrCase != null) {
					
					String solutionRecomendada = ((BAMSolution) cbrCase.getSolution()).getBAMNovo().toString();
					
					
					if (solutionRecomendada != nomeBAMAtual ){
						switchBAM(to, solutionRecomendada);
						mudouBAM= 1;
						BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
						novocase.setSolution(sol);
						no.item=novocase;
						BancoDeDados.setXML(rodada.simtime() + "\t## Dentro da linha de corte ## BAM ALTERADO. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)) + "##################", rodada.filename);
						
					}else{
						/*mudouBAM= 0;
						BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
						novocase.setSolution(sol);
						no.item=novocase;*/
						//BancoDeDados.setXML("\n" + rodada.simtime() + "######## Dentro da linha de corte ###### BAM mantido. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela-0.10)) + "##################", rodada.filename);
						BancoDeDados.setXML(rodada.simtime() + "\t## Dentro da linha de corte ## BAM mantido. NADA a ser feito.", rodada.filename);
						
					}
					
					
					
				}else if (ParametrosDSTE.RecomendacaoCBRRetencao){
					mudouBAM=1;
					BancoDeDados.setXML("\tNenhum caso válido na base", rodada.filename);
					int []bams = BAMRecommenderNoGUI.getInstance().foraDaLinha(query);  // busca na bae de casos negativa se existe alguma caso negativado na base e de que BAm eles são
					BAMTypes bam = null;
					
					BancoDeDados.setXML("\tBAMs Negativos em: MAM:"+bams[0]+" RDM:"+bams[1]+" ALLOC: "+bams[2] , rodada.filename);
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
					BancoDeDados.setXML("\tCASO SUGERIDO: " + ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo()+ "\n", rodada.filename);
					//Por enquanto só recomendação
									
					if (mudouBAM==1)
					{
						BancoDeDados.setXML(rodada.simtime() + "\t######## Fora da linha de corte ######  BAM Modificado. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)) + "##################", rodada.filename);
					}else {
						BancoDeDados.setXML(rodada.simtime() + "\t######## Fora da linha de corte ######  BAM Mantido. Agendado retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela-0.10)) + "##################", rodada.filename);

					}
				}
					
				//Agenda avaliar rentenção 
				if (ParametrosDSTE.RecomendacaoCBRRetencao){
					if (mudouBAM==1){
						rodada.schedulep(6, ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10, no);
						rodada.schedulep(5, ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida, null);						
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
					BancoDeDados.setXML(rodada.simtime() + "\tSaio da Recomendação.", rodada.filename);	
					//rodada.schedulep(5, ParametrosDSTE.Janela, null);
				
				
				break;
			
			case 6:
				//Avalia rentenção
				BancoDeDados.setXML(rodada.simtime() + "\tEntrou em retenção.", rodada.filename);
				
					 novocase = ((CBRCase)dados.item);
					((BAMDescription)novocase.getDescription()).setCaseId("tmp01") ;
					
					query = rodada.estatistica.getQuery(to.link[0]);
					
					//Caso que será colocado na base de caos negativos. OBS é possivel cria tb um 3° caso aqui  (caso atual com a nova query acima) 
					CBRCase badcase = new CBRCase();
					badcase.setDescription(    ( (BAMDescription)novocase.getDescription()  ).clone()    );
					badcase.setSolution(null);
								
					
					int lspRequestedAgora = rodada.estatistica.lspRequested(ParametrosDSTE.Janela);
					int lspRequestedAnterior = Math.abs(rodada.estatistica.lspRequested(ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida) - rodada.estatistica.lspRequested(ParametrosDSTE.Janela*2+ParametrosDSTE.RRDBatida ) );
					
					int difLSPs = lspRequestedAgora - lspRequestedAnterior;
					
					
					if (Math.abs(difLSPs)< 200){ // verifica se houve mudança na rede	
						
							
							//int score = 10;
							String bamAnterior = ((BAMDescription)novocase.getDescription()).getBAMAtual().name();
							/*double []utilizacaoCTJanelaAnterior  	= new double [] {((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT0(), ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT1(), ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT2()} ;  
							double []bloqueioCTJanelaAnterior   	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT2()} ;
							double []preempcoesCTJanelaAnterior  	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT2()} ;  
							double []devolucoesCTJanelaAnterior   	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT2()} ;				
							*/
							double utilizacaoJanelaAnterior  	= ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlace();  
							double bloqueioJanelaAnterior   	= ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueios() ;
							double preempcoesJanelaAnterior  	= ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoes();  
							double devolucoesJanelaAnterior   	= ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoes();				
							
							
							String bamAgora = ((BAMDescription)query.getDescription()).getBAMAtual().name();
							/*double []utilizacaoCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT0(), ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT1(), ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT2()} ;
							double []bloqueiosCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT0(), ((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT1(), ((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT2()} ; 
							double []preempcoesCTJanelaAgora  	= new double [] {((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT0(), ((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT1(), ((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT2()} ;  
							double []devolucoesCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT0(), ((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT1(), ((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT2()} ;
							*/
							double utilizacaoJanelaAgora   	= ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlace();
							double bloqueiosJanelaAgora   	= ((BAMDescription)query.getDescription()).getNumeroDeBloqueios() ; 
							double preempcoesJanelaAgora  	= ((BAMDescription)query.getDescription()).getNumeroDePreempcoes();  
							double devolucoesJanelaAgora   	= ((BAMDescription)query.getDescription()).getNumeroDeDevolucoes();
							
							
							//double somatorioUtilizacaoCTJanelaAnterior = utilizacaoCTJanelaAnterior[0]+utilizacaoCTJanelaAnterior[1]+	utilizacaoCTJanelaAnterior[2];
							//double somatorioUtilizacaoCTJanelaAgora    = utilizacaoCTJanelaAgora[0]+utilizacaoCTJanelaAgora[1]+utilizacaoCTJanelaAgora[2];
							
							//double somatorioPonderadoBloqueioCTJanelaAnterior = (bloqueioCTJanelaAnterior[0]*to.link[0].BC[0] + bloqueioCTJanelaAnterior[1]*to.link[0].BC[1] + bloqueioCTJanelaAnterior[2]*to.link[0].BC[2])  /  (to.link[0].BC[0] + to.link[0].BC[1] + to.link[0].BC[2]) ;				
							//double somatorioPonderadoBloqueioCTJanelaAgora = (bloqueiosCTJanelaAgora[0]*to.link[0].BC[0] + bloqueiosCTJanelaAgora[1]*to.link[0].BC[1] + bloqueiosCTJanelaAgora[2]*to.link[0].BC[2])  /  (to.link[0].BC[0] + to.link[0].BC[1] + to.link[0].BC[2]) ;				
							
							//double somatorioPreempcoesCTJanelaAnterior = preempcoesCTJanelaAnterior[0]+preempcoesCTJanelaAnterior[1]+preempcoesCTJanelaAnterior[2];
							//double somatorioPreempcoesCTJanelaAgora = preempcoesCTJanelaAgora[0]+preempcoesCTJanelaAgora[1]+preempcoesCTJanelaAgora[2];
							
							//double somatorioDevolucoesCTJanelaAnterior = devolucoesCTJanelaAnterior[0]+devolucoesCTJanelaAnterior[1]+devolucoesCTJanelaAnterior[2];
							//double somatorioDevolucoesCTJanelaAgora = devolucoesCTJanelaAgora[0]+devolucoesCTJanelaAgora[1]+devolucoesCTJanelaAgora[2];
							
							
							//double utilizacao = ParametrosDSTE.SLAUtilizacao; //( ( Math.min(to.link[0].BC[0], to.link[0].BC[1]) + Math.min(to.link[0].BC[1], to.link[0].BC[2]) ))/ (to.link[0].BC[0] + to.link[0].BC[1] + to.link[0].BC[2]);
							//double bloqueio = utilizacao * ParametrosDSTE.SLABloqueios;
							
							
							
						if (bamAnterior == BAMTypes.NoPreemptionMAM.name()){
							switch (bamAgora) {
							case "NoPreemptionMAM":
								//não tive problema de performasse na rede. Vou verificar agora subina e decida
								/*if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
								{
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
								}*/
							break;
							
							case "PreemptionRDM":
								/*if(somatorioPreempcoesCTJanelaAnterior+somatorioDevolucoesCTJanelaAnterior > ParametrosDSTE.SLADevolucoes){
								}*/
								if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else {
										//não tive problema de performasse na rede. Vou verificar agora subina e decida
										/*if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										}*/
								}
							break;
							
							case "PreemptionAllocCTSharing":
								if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(devolucoesJanelaAgora > ParametrosDSTE.SLADevolucoes ){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else {
									/*	//não tive problema de performasse na rede. Vou verificar agora subina e decida <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,, descomentar
									 * 
										if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										}*/
								}
							break;
							}
							
							
							
							
							
						} else if (bamAnterior == BAMTypes.PreemptionRDM.name()){
							switch (bamAgora) {
							case "NoPreemptionMAM":								
								//não tive problema de performasse na rede. Vou verificar agora subina e decida
									/*if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
									{
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
									}*/
							break;
							
							case "PreemptionRDM":
								if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){   
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else {
									/*if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
									{
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
									}*/
								}
							break;
							
							case "PreemptionAllocCTSharing":
								if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(devolucoesJanelaAgora > ParametrosDSTE.SLADevolucoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else {
										/*//não tive problema de performasse na rede. Vou verificar agora subina e decida  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
										if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										}*/
								}
							break;
							}


							
							
							
						}else if (bamAnterior == BAMTypes.PreemptionAllocCTSharing.name()){
							switch (bamAgora) {
							case "NoPreemptionMAM":
								
								//não tive problema de performasse na rede. Vou verificar agora subina e decida
							/*	if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
								{
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
								}*/
								
							break;
							
							case "PreemptionRDM":
								if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){   
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else {
										//não tive problema de performasse na rede. Vou verificar agora subina e decida
										/*if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
										{
											badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
											novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
										}*/
								}
							break;
							
							case "PreemptionAllocCTSharing":
								if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
								}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
									badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
									novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
								}else {
									//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
								}
							break;
							}
						}
							
							
							if (novocase.getSolution() !=null ){
								BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
								((BAMDescription)novocase.getDescription()).setCaseId("N_"+(recommender.getCaseBase().getCases().size()+1));
								((BAMSolution)novocase.getSolution()).setId("N_"+(recommender.getCaseBase().getCases().size()+1));
								if (recommender.equal(novocase, recommender.getCaseBase(), ParametrosDSTE.RecomendacaoCBRLimiarArmazenar)){
									BancoDeDados.setXML("\t#*123*# Caso muito similar ja na base de casos positiva",rodada.filename );
								}else{
									jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBase(), novocase);
									BancoDeDados.setXML( rodada.simtime() + "\tAceito o caso: "+  ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo()  +"\tDif LSFs:" + difLSPs  ,rodada.filename );
								}
							}
							
							
							if(badcase.getSolution()!=null) {
								BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
								((BAMDescription)badcase.getDescription()).setCaseId("R_"+(recommender.getCaseBaseDB2().getCases().size()+1));
								((BAMSolution)badcase.getSolution()).setId("R_"+(recommender.getCaseBaseDB2().getCases().size()+1));
								if (recommender.equal(badcase, recommender.getCaseBaseDB2(), ParametrosDSTE.RecomendacaoCBRLimiarArmazenar)){
									BancoDeDados.setXML("\t#*124*# Caso muito similar ja na base de casos Negativa",rodada.filename );
								}else {
									jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBaseDB2(), badcase);
									BancoDeDados.setXML( rodada.simtime() + "\tRejeitado o caso: "+ ((BAMDescription)badcase.getDescription()).toTabela() + ((BAMSolution)badcase.getSolution()).getBAMNovo()  +  "\tDif LSFs:" + difLSPs,rodada.filename );
								}							
							}
							
							
					}else{
							BancoDeDados.setXML(rodada.simtime() + "\tBAM não validado. A rede mudou o comportamento. Dif LSFs:" + difLSPs,rodada.filename );
						}
				BancoDeDados.setXML(rodada.simtime() + "\tSaiu em retenção\n", rodada.filename);
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
				
				double bloqueiosJanela   	= 0.0;  
				double preempcoesJanela  	= 0.0;
				double devolucoesJanela   	= 0.0;
			 	
				 	
				bloqueiosJanela = rodada.estatistica.lspRequested(ParametrosDSTE.Janela) > 0 ? (double)rodada.estatistica.bloqueios(ParametrosDSTE.Janela)/rodada.estatistica.lspRequested(ParametrosDSTE.Janela):0;
				
				bloqueiosCTJanela[0] = rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 0) > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,0)/rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 0):0;
				bloqueiosCTJanela[1] = rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 1) > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,1)/rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 1):0;
				bloqueiosCTJanela[2] = rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 2) > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,2)/rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela, 2):0;

				
				preempcoesJanela = (rodada.estatistica.lspEstablishedTotal(ParametrosDSTE.Janela) + rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela)) > 0 
							? (double)rodada.estatistica.preempcoes(ParametrosDSTE.Janela)/	(rodada.estatistica.lspEstablishedTotal(ParametrosDSTE.Janela)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela))		:0;
				
								
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

								
								
				devolucoesJanela = (rodada.estatistica.lspEstablishedTotal(ParametrosDSTE.Janela) + rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela)) > 0 
							? (double)rodada.estatistica.devolucoes(ParametrosDSTE.Janela)/	(rodada.estatistica.lspEstablishedTotal(ParametrosDSTE.Janela)
										+ rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela))		:0;
				
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

					
								BancoDeDados.setXML(  rodada.simtime() + "\t\t"
										+ nomeBAMAtual + "\t"
										/*+ ParametrosDSTE.Janela + "\t"
										
										+ to.link[0].CargaEnlace * to.link[0].BC[0] / 100 + "\t"
										+ to.link[0].CargaEnlace * to.link[0].BC[1] / 100 + "\t"
										+ to.link[0].CargaEnlace * to.link[0].BC[2] / 100 + "\t"*/
										
										
									/*	+ to.link[0].CargaCTAtual[0] + "\t"
										+ to.link[0].CargaCTAtual[1] + "\t"
										+ to.link[0].CargaCTAtual[2] + "\t"*/
										
										+ rodada.estatistica.picoDeUtilizacaoDoEnlace(ParametrosDSTE.Janela, to.link[0]) /to.link[0].CargaEnlace + "\t"
										
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace + "\t"
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace + "\t"
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace + "\t"

										
										//+ rodada.estatistica.lspRequested + "\t"
									
									
										+ bloqueiosJanela + "\t"				
										+ bloqueiosCTJanela[0] + "\t"
										+ bloqueiosCTJanela[1] + "\t"
										+ bloqueiosCTJanela[2] + "\t"
										
										+ preempcoesJanela + "\t"
										/*+ preempcoesCTJanela[0] + "\t"
										+ preempcoesCTJanela[1] + "\t"
										+ preempcoesCTJanela[2] + "\t"*/
										
										+ devolucoesJanela + "\t"
										/*+ devolucoesCTJanela[0] + "\t"
										+ devolucoesCTJanela[1] + "\t"
										+ devolucoesCTJanela[2] + "\t"*/
										
										//+ (rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace)  + "\t"
										+ "\t"+rodada.estatistica.lspRequested(ParametrosDSTE.Janela)

										//, "saida");
							//, rodada.filename+"_7");
							, rodada.filename);
								
							/*	BancoDeDados.setXML(  rodada.simtime() + "\t"
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
										//+ (rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 0) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 1) /to.link[0].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[0], 2) /to.link[0].CargaEnlace)  + "\t"
										//+ rodada.estatistica.lspRequested(ParametrosDSTE.Janela)
										

										//,"saida");
										, rodada.filename+"_7");*/
								
				
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










/*

if (bamAnterior == BAMTypes.NoPreemptionMAM.name()){
	switch (bamAgora) {
	case "NoPreemptionMAM":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
			{
				badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
				novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
			}
		}																
	break;
	
	case "PreemptionRDM":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
		//	badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
		//	novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
		//	badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
		//	novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
					badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
					((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
				}
		}
	break;
	
	case "PreemptionAllocCTSharing":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
					badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
					novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
				}
		}
	break;
	}
	
	
	
} else if (bamAnterior == BAMTypes.PreemptionRDM.name()){
	switch (bamAgora) {
	case "NoPreemptionMAM":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
			{
				badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
				novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
			}
		}																
	break;
	
	case "PreemptionRDM":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
		//	badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
		//	novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
		//	badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
		//	novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
					badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
					((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
				}
		}
	break;
	
	case "PreemptionAllocCTSharing":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
					badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
					novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
				}
		}
	break;
	}


	
	
}else if (bamAnterior == BAMTypes.PreemptionAllocCTSharing.name()){
	switch (bamAgora) {
	case "NoPreemptionMAM":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior < utilizacao  && bloqueioJanelaAnterior<bloqueio )
			{
				badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
				novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MA
			}
		}
																		
	break;
	
	case "PreemptionRDM":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			//badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			//novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
		//	badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
		//	novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
					badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
					((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
				}
		}
		
	break;
	
	case "PreemptionAllocCTSharing":
		if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
		}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
			badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
			novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
		}else {
			//não tive problema de performasse na rede. Vou verificar agora subina e decida????????
			if ( utilizacaoJanelaAnterior > utilizacao  || bloqueioJanelaAnterior>bloqueio ){
					badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
					novocase.setSolution(null);///// nesse caso eu não si o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
				}
		}
		
	break;
	}
}*/





















