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


		Debug.setMensagem("============================ Início da Rodada "+ rodada.filename +  " ============================",3,3);
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
		Debug.setMensagem("\r\n\r\n ==== Topologia Roteador x Roteador  ====",7,7);
		Debug.setMensagem(to.imprimirTopologiaDosRoteadores(),7,7);

		// Imprime no console a Topologia Link x Roteador
		Debug.setMensagem("\r\n\r\n ==== Topologia Link x Roteador  ====",7,7);
		Debug.setMensagem(to.imprimirTopologiaDosLinks(),7,7);

		// Imprime no console a Matriz de Caminhos				 por Roteador
		Debug.setMensagem("\r\n\r\n ==== Matriz de Caminhos por Roteador  ====",7,7);
		Debug.setMensagem(to.imprimirCaminhos(),7,7);

		// Imprime no console o Status dos Links
		Debug.setMensagem("\r\n\r\n ==== Status dos Links  ====",7,7);
		Debug.setMensagem(to.statusLinks(),7,7);

		// Inicializa tráfego
		Debug.setMensagem("\r\n\r\n ==== Inicializa o tráfego  ====",7,7);
		rodada.schedulep (3, 0.0, null);	

		// agenda estatísticas
		rodada.schedulep(4, ParametrosDSTE.RRDBatida+0.10, null);

		// agenda avaliação CBR
		if(ParametrosDSTE.RecomendacaoCBRSwitchBAM)
		{
			//Inicia Debug CBR
			BancoDeDados.setXML("", rodada.filename);
			//Agenda primeira avaliação
			for (int i=0;i<ParametrosDSTE.LINKS;i++)
			{
				No no = new No();
				no.item=to.link[i];
				rodada.schedulep(5, ParametrosDSTE.Janela+0.40, no);    ///////<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			}
		}
		if (ParametrosDSTE.ligarDBug)
		{
			rodada.schedulep(7, ParametrosDSTE.RRDBatida + 0.20, null);/////<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		}
		try {
			rodada.estatistica.iniciarRRDLinks(to);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		// inciatrafego2(rodada);

		// Inicializa a cadeia de eventos
		Debug.setMensagem("\r\n\r\n ==== Inicio da simulação  ====",3,3);
		try {
			cadeiaDeEventos(rodada, to);
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		Debug.setMensagem("\r\n ==== Fim da simulação  ====",3,3);

		Debug.setMensagem("============================ Fim da Rodada " + rodada.filename + " ============================",3,3);

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
						+ ((Lsp) dados.item).CT  + "",7,7);
				
				Long tempoInicial=System.nanoTime();
				Link[] menorCaminho = Roteamento.TryPath_CSPF(
						((Lsp) dados.item), to);
				rodada.estatistica.tempoAcumuladoGrantDeny+=System.nanoTime()-tempoInicial;
				
				if (menorCaminho != null) {
					Debug.setMensagem(" ==== Menor caminho  ====",7,7);
					Debug.setMensagem(to.imprimirCaminho(menorCaminho),7,7);
					((Lsp) dados.item).estabelecerLSP(menorCaminho);
					((Lsp) dados.item).status = LspStatus.estabelecida;

					
					Debug.setMensagem("========= LSP" + ((Lsp) dados.item).ID + " Estabelecida ========"  + " Em CT=" + ((Lsp) dados.item).CT,7,7);

					// agenda desestabelecimento
					rodada.schedulep(2, ((Lsp) dados.item).tempoDeVida, dados);

				} else {
					Debug.setMensagem("========= LSP" + ((Lsp) dados.item).ID + " Bloqueada ========" + " Em CT=" + ((Lsp) dados.item).CT ,7,7);
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
						,7,7);
				((Lsp) dados.item).desestabeleceLSP();
				rodada.estatistica.lspUnbroken++;
				rodada.estatistica.lspUnbrokenCT[((Lsp) dados.item).CT]++;
				rodada.estatistica.bandaUnbrokenCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
				rodada.estatistica.bandaUnbroken += ((Lsp) dados.item).Carga;
				((Lsp) dados.item).status = LspStatus.finalizada;

				break;
			case 3:// geracao de trafego
				Debug.setMensagem("Tipo 3 - Agenda/Cria LSP ",7,7);
				ParametrosDSTE.trafegoManual(rodada, to, dados);

				break;

			
			case 4:
				//Insere estatísticas RDD
				rodada.estatistica.inserirDadosRRD((long) rodada.simtime()-ParametrosDSTE.RRDBatida);
				rodada.estatistica.inserirDadosAbsolutoRRD((long) rodada.simtime()-ParametrosDSTE.RRDBatida);
				rodada.estatistica.statusLinks(to, (long) rodada.simtime()-ParametrosDSTE.RRDBatida);

				rodada.schedulep(4, ParametrosDSTE.RRDBatida, null);
				break;
			case 5:	//inicia o BAMCBR 

				No noComLinkAtual=dados;
				BancoDeDados.setXML( rodada.simtime() + "\tIniciando BAMCBR;;;", rodada.filename);
				Link link = ((Link)noComLinkAtual.item);
				int mudouBAM= -1;
				CBRCase cbrCase = null;
				CBRQuery query = null;
				
				query = rodada.estatistica.getQuery(link);
				////inicia a procura na base de casos
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
						switchBAM(link, solutionRecomendada);
						mudouBAM= 1;
						BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
						novocase.setSolution(sol);
						no.item=novocase;
						BancoDeDados.setXML( rodada.simtime() + "\tRecomendação encontrada; Açao: ALTERAR BAM; Agendando retenção de novo caso para tempo: " + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)) , rodada.filename);
						
					}else{
						mudouBAM= 0;
						BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
						novocase.setSolution(sol);
						no.item=novocase;
						BancoDeDados.setXML( rodada.simtime() + "\tRecomendação encontrada; Ação: MANTER BAM ATUAL;  Agendando retenção para tempo:" + (rodada.simtime() + (ParametrosDSTE.Janela-0.10)), rodada.filename);
						//BancoDeDados.setXML( rodada.simtime() + "\tRecomendação encontrada; Ação: MANTER BAM ATUAL; *Sem novo caso*", rodada.filename);
						
					}
					
					
					////Se não achou nenhuma recomendação faça:
				}else if (ParametrosDSTE.RecomendacaoCBRRetencao){
					mudouBAM=1;
					BancoDeDados.setXML( rodada.simtime() + "\tNenhum caso encontrado na base, iniciado Sugestão;;;", rodada.filename);
					int []bams = BAMRecommenderNoGUI.getInstance().sugerirRecomendacao(query);  // busca na bae de casos negativa se existe alguma caso negativado na base e de que BAm eles são
					BAMTypes bam = null;
					
					if (ParametrosDSTE.ligarDBug) {BancoDeDados.setXML( rodada.simtime() + "\tSituação das negativações - MAM:"+bams[0]+" RDM:"+bams[1]+" ALLOC: "+bams[2] , rodada.filename);}
					///man=0   RDM=4  ALLOC=5
					
					switch (nomeBAMAtual) {
					case "NoPreemptionMAM":
						if (bams[2]==0){
							 bam = BAMTypes.values()[5];
							 switchBAM(link, bam.name());
						}else if (bams[1]==0){
							 bam = BAMTypes.values()[4];
							 switchBAM(link, bam.name());
						}else {
							bam = BAMTypes.values()[0];
							 mudouBAM= 0;
						}	
						break;
					
					case "PreemptionRDM":
						if (bams[2]==0){
							 bam = BAMTypes.values()[5];
							 switchBAM(link, bam.name());
						}else if (bams[1]==0){
							bam = BAMTypes.values()[4];
							mudouBAM= 0;
						}else {
							 bam = BAMTypes.values()[0];
							 switchBAM(link, bam.name());
						}
						break;
					
					case "PreemptionAllocCTSharing":
						if (bams[2]==0){
							bam = BAMTypes.values()[5];
							mudouBAM= 0;
						}else if (bams[1]==0){
							 bam = BAMTypes.values()[4];
							 switchBAM(link, bam.name());
						}else {
							 bam = BAMTypes.values()[0];
							 switchBAM(link, bam.name());
						}
						break;
					}
					
					BAMSolution sol = new BAMSolution() ;
					sol.setBAMNovo(bam);
					sol.setAceita(true);
					novocase.setSolution(sol);
					no.item=novocase;
					//BancoDeDados.setXML( rodada.simtime() + "\tCASO SUGERIDO: " + ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo()+ "\n", rodada.filename);
					//Por enquanto só recomendação
									
					if (mudouBAM==1){
						BancoDeDados.setXML( rodada.simtime() + "\tCaso sugerido, agendado retenção para tempo: " + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)) + " Case_ID: " +((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo(), rodada.filename) ;
						if(ParametrosDSTE.ligarDBug) {BancoDeDados.setXML( rodada.simtime() + "\tFinalizando sugestão: #BAM Modificado#; Agendado retenção para tempo: " + (rodada.simtime() + (ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10)), rodada.filename) ;}
					}else {
						BancoDeDados.setXML( rodada.simtime() + "\tCaso sugerido, agendado retenção para tempo: " + (rodada.simtime() + (ParametrosDSTE.Janela-0.10)) +                          " Case_ID: " +((BAMDescription)novocase.getDescription()).toTabela() +((BAMSolution)novocase.getSolution()).getBAMNovo() , rodada.filename);
						if(ParametrosDSTE.ligarDBug) {BancoDeDados.setXML( rodada.simtime() + "\tFinalizando sugestão: #BAM Mantido#;    Agendado retenção para tempo: " + (rodada.simtime() + (ParametrosDSTE.Janela-0.10)) +                          " CASO SUGERIDO -> ID: " +((BAMDescription)novocase.getDescription()).toTabela() +((BAMSolution)novocase.getSolution()).getBAMNovo() , rodada.filename);}
					}
				}
					
				//Agenda avaliar rentenção 
				if (ParametrosDSTE.RecomendacaoCBRRetencao){
					if (mudouBAM==1){
						rodada.schedulep(6, ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida-0.10, no);
						rodada.schedulep(5, ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida, noComLinkAtual);
						//rodada.schedulep(5, ParametrosDSTE.Janela, null);
						
					}else if (mudouBAM==0){
						rodada.schedulep(6, ParametrosDSTE.Janela-0.10, no);///////////////////////colocar  batida em vez ee janela??? ,,,<<<<<<<<<<<<<+============
						//casoRenAntigo = null;
						rodada.schedulep(5, ParametrosDSTE.Janela, noComLinkAtual);
					}else{
						//casoRenAntigo = null;
						rodada.schedulep(5, ParametrosDSTE.Janela, noComLinkAtual);
					}
					
				}else{
					rodada.schedulep(5, ParametrosDSTE.Janela, noComLinkAtual);
				}
					
				if(ParametrosDSTE.ligarDBug) {BancoDeDados.setXML( rodada.simtime() + "\tSaio da Recomendação;", rodada.filename);}	
					//rodada.schedulep(5, ParametrosDSTE.Janela, null);
				
				
				break;
			
			case 6:
				//Avalia rentenção
				BancoDeDados.setXML( rodada.simtime() + "\tIniciando revisão e reteção;;;", rodada.filename);
				
				novocase = ((CBRCase)dados.item);
				((BAMDescription)novocase.getDescription()).setCaseId("tmp01") ;
				link=to.link[((BAMDescription)novocase.getDescription()).getLink()];
				
				int lspRequestedAgora = rodada.estatistica.lspRequested(ParametrosDSTE.Janela,link);
				int lspRequestedAnterior = Math.abs(rodada.estatistica.lspRequested(ParametrosDSTE.Janela+ParametrosDSTE.RRDBatida, link) - rodada.estatistica.lspRequested(ParametrosDSTE.Janela*2+ParametrosDSTE.RRDBatida,link ) );
				int difLSPs = Math.abs(lspRequestedAgora - lspRequestedAnterior);
				if (ParametrosDSTE.ligarDBug) {BancoDeDados.setXML( rodada.simtime() + "\tDiferença da rede:\t" + difLSPs, rodada.filename);}
				
				if (difLSPs <= ParametrosDSTE.DifLSP){ // verifica se houve mudança na rede	
				
						query = rodada.estatistica.getQuery(link);
	
						
						//Caso que será colocado na base de caos negativos. OBS é possivel cria tb um 3° caso aqui  (caso atual com a nova query acima) 
						CBRCase badcase = new CBRCase();
						badcase.setDescription(    ( (BAMDescription)novocase.getDescription()  ).clone()    );
						badcase.setSolution(null);
								
											
						//int score = 10;
						String bamAnterior = ((BAMDescription)novocase.getDescription()).getBAMAtual().name();
						/*double []utilizacaoCTJanelaAnterior  	= new double [] {((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT0(), ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT1(), ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlaceCT2()} ;  
						double []bloqueioCTJanelaAnterior   	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueiosCT2()} ;
						double []preempcoesCTJanelaAnterior  	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoesCT2()} ;  
						double []devolucoesCTJanelaAnterior   	= new double [] {((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT0(), ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT1(), ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoesCT2()} ;				
						*/
						//double utilizacaoJanelaAnterior  	= ((BAMDescription)novocase.getDescription()).getUtilizacaoDoEnlace();  
						//double bloqueioJanelaAnterior   	= ((BAMDescription)novocase.getDescription()).getNumeroDeBloqueios() ;
						double preempcoesJanelaAnterior  	= ((BAMDescription)novocase.getDescription()).getNumeroDePreempcoes();  
						double devolucoesJanelaAnterior   	= ((BAMDescription)novocase.getDescription()).getNumeroDeDevolucoes();				
						
						
						String bamAgora = ((BAMDescription)query.getDescription()).getBAMAtual().name();
						/*double []utilizacaoCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT0(), ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT1(), ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlaceCT2()} ;
						double []bloqueiosCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT0(), ((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT1(), ((BAMDescription)query.getDescription()).getNumeroDeBloqueiosCT2()} ; 
						double []preempcoesCTJanelaAgora  	= new double [] {((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT0(), ((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT1(), ((BAMDescription)query.getDescription()).getNumeroDePreempcoesCT2()} ;  
						double []devolucoesCTJanelaAgora   	= new double [] {((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT0(), ((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT1(), ((BAMDescription)query.getDescription()).getNumeroDeDevolucoesCT2()} ;
						*/
						//double utilizacaoJanelaAgora   	= ((BAMDescription)query.getDescription()).getUtilizacaoDoEnlace();
						//double bloqueiosJanelaAgora   	= ((BAMDescription)query.getDescription()).getNumeroDeBloqueios() ; 
						double preempcoesJanelaAgora  	= ((BAMDescription)query.getDescription()).getNumeroDePreempcoes();  
						double devolucoesJanelaAgora   	= ((BAMDescription)query.getDescription()).getNumeroDeDevolucoes();
						
						
						//double somatorioUtilizacaoCTJanelaAnterior = utilizacaoCTJanelaAnterior[0]+utilizacaoCTJanelaAnterior[1]+	utilizacaoCTJanelaAnterior[2];
						//double somatorioUtilizacaoCTJanelaAgora    = utilizacaoCTJanelaAgora[0]+utilizacaoCTJanelaAgora[1]+utilizacaoCTJanelaAgora[2];
						
						//double somatorioPonderadoBloqueioCTJanelaAnterior = (bloqueioCTJanelaAnterior[0]*link.BC[0] + bloqueioCTJanelaAnterior[1]*link.BC[1] + bloqueioCTJanelaAnterior[2]*link.BC[2])  /  (link.BC[0] + link.BC[1] + link.BC[2]) ;				
						//double somatorioPonderadoBloqueioCTJanelaAgora = (bloqueiosCTJanelaAgora[0]*link.BC[0] + bloqueiosCTJanelaAgora[1]*link.BC[1] + bloqueiosCTJanelaAgora[2]*link.BC[2])  /  (link.BC[0] + link.BC[1] + link.BC[2]) ;				
						
						//double somatorioPreempcoesCTJanelaAnterior = preempcoesCTJanelaAnterior[0]+preempcoesCTJanelaAnterior[1]+preempcoesCTJanelaAnterior[2];
						//double somatorioPreempcoesCTJanelaAgora = preempcoesCTJanelaAgora[0]+preempcoesCTJanelaAgora[1]+preempcoesCTJanelaAgora[2];
						
						//double somatorioDevolucoesCTJanelaAnterior = devolucoesCTJanelaAnterior[0]+devolucoesCTJanelaAnterior[1]+devolucoesCTJanelaAnterior[2];
						//double somatorioDevolucoesCTJanelaAgora = devolucoesCTJanelaAgora[0]+devolucoesCTJanelaAgora[1]+devolucoesCTJanelaAgora[2];
						

						//double utilizacao =  ( ( Math.min(link.BC[0], link.BC[1]) + Math.min(link.BC[1], link.BC[2]) ))/ (link.BC[0] + link.BC[1] + link.BC[2]);
						//double bloqueio = utilizacao * ParametrosDSTE.SLABloqueios;
						
							boolean apg=false; //solução Aprendida Pelo Gestor
							
							if (bamAnterior == BAMTypes.NoPreemptionMAM.name()){         /////////////////////1
								switch (bamAgora) {
								case "NoPreemptionMAM":
									break;

								case "PreemptionRDM":
									if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										apg=true;
									}else {
										apg=false;	
									}
									break;

								case "PreemptionAllocCTSharing":
									if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										apg=true;
									}else if(devolucoesJanelaAgora > ParametrosDSTE.SLADevolucoes ){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										apg=false;
									}else {
										apg=false;
									}
									break;
								}

							} else if (bamAnterior == BAMTypes.PreemptionRDM.name()){    /////////////////////2
								switch (bamAgora) {
								case "NoPreemptionMAM":								
									break;

								case "PreemptionRDM":
									if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){ //// Porque  eu testo a janela anterior??? R: Porque se eu ja tinha prempção não era pra ir para RMD ou Alloc.
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM); ////Aproveitando para já aprender qual deveria ter sido a solução correta
										apg=true;
									}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){   
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );									
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);////Aproveitando para já aprender qual deveria ter sido a solução correta
										apg=true;
									}else {
										apg=false;
									}
									break;

								case "PreemptionAllocCTSharing":
									if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										apg=true;
									}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										apg=true;
									}else if(devolucoesJanelaAgora > ParametrosDSTE.SLADevolucoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										apg=false;
									}else {
										apg=false;	
									}
									break;
								}

							}else if (bamAnterior == BAMTypes.PreemptionAllocCTSharing.name()){    /////////////////////3
								switch (bamAgora) {
								case "NoPreemptionMAM":

									break;

								case "PreemptionRDM":
									if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);////Aproveitando para já aprender qual deveria ter sido a solução correta
										apg=true;
									}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){   
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);////Aproveitando para já aprender qual deveria ter sido a solução correta
										apg=true;
									}

									break;

								case "PreemptionAllocCTSharing":
									if(preempcoesJanelaAnterior > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										apg=true;
									}else if(preempcoesJanelaAgora > ParametrosDSTE.SLAPreempcoes){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										((BAMSolution)novocase.getSolution()).setBAMNovo(BAMTypes.NoPreemptionMAM);
										apg=true;
									}else if(devolucoesJanelaAnterior  > ParametrosDSTE.SLADevolucoes		){ 
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
										apg=false;
									}else if(devolucoesJanelaAgora  > ParametrosDSTE.SLADevolucoes ){
										badcase.setSolution(    ( (BAMSolution)novocase.getSolution()  ).clone()    );
										novocase.setSolution(null);///// nesse caso eu não sei o que fazer, por isso eu preciso do CBR pois eu posso ir tanto pra RDM quanto pra MAM
									}else {
										apg=false;	
									}
									break;
								}
							}
							
							
							if(badcase.getSolution()!=null) {
								BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
								((BAMDescription)badcase.getDescription()).setCaseId("BAD_"+(recommender.getCaseBaseDB2().getCases().size()+1));
								((BAMSolution)badcase.getSolution()).setId("BAD_"+(recommender.getCaseBaseDB2().getCases().size()+1));
								if (recommender.equal(badcase, recommender.getCaseBaseDB2(), ParametrosDSTE.RecomendacaoCBRLimiarArmazenar)){
									BancoDeDados.setXML("\tCaso muito SIMILAR ja na base de casos Negativa",rodada.filename );
								}else {
									jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBaseDB2(), badcase);
									BancoDeDados.setXML( rodada.simtime() + "\tO caso foi regeitado e armazenado na base NEGATIVA; Case_ID: "+ ((BAMDescription)badcase.getDescription()).toTabela() + ((BAMSolution)badcase.getSolution()).getBAMNovo()  ,rodada.filename );
								}							
							}
						
						
							if (novocase.getSolution() !=null ){
								BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
								((BAMDescription)novocase.getDescription()).setCaseId("New_"+(recommender.getCaseBase().getCases().size()+1));
								((BAMSolution)novocase.getSolution()).setId("New_"+(recommender.getCaseBase().getCases().size()+1));
								if (recommender.equal(novocase, recommender.getCaseBase(), ParametrosDSTE.RecomendacaoCBRLimiarArmazenar)){
									BancoDeDados.setXML("\tO caso foi aceito, porem é muito SIMILAR a um caso exstnte na base e por isso não será armazenedo;",rodada.filename );
								}else{
									jcolibri.method.retain.StoreCasesMethod.storeCase( recommender.getCaseBase(), novocase);
									if (apg) {
										BancoDeDados.setXML( rodada.simtime() + "\tCaso aprendido pelo módulo de inteligência; Finaliznado retenção;;; Case_ID: "+  ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo()    ,rodada.filename );
									}else {
										BancoDeDados.setXML( rodada.simtime() + "\tCaso Aceito; Finaliznado retenção;;; Case_ID: "+  ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)novocase.getSolution()).getBAMNovo()    ,rodada.filename );
									}
								}
							}
							
							
							
							
					}else{
							BancoDeDados.setXML( rodada.simtime() + "\tA rede mudou o comportamento, não é possível validar o caso; Descartando alterações; " ,rodada.filename );
						}
				
				BancoDeDados.setXML( rodada.simtime() + "\tFinalizando BAMCBR;;;", rodada.filename);
				
			break;
			
			
			
			
			case 7:
				
				//link=to.link[((BAMDescription)novocase.getDescription()).getLink()];
				
				int idLinkDebug=0;

				nomeBAMAtual = null;
				
				if(to.link[idLinkDebug].bamType!=BAMType.PreemptionGBAM)
				{
					nomeBAMAtual = to.link[idLinkDebug].bamType.toString();
				}else
				{
					//Se BCLTH diferente de 0 é pq reflete Alloc
					if (to.link[idLinkDebug].BCLTH[0]!=0)
						nomeBAMAtual = "PreemptionAllocCTSharing";
					//Se BCLTH diferente é igual a 0 e BCHTL diferente de 0 é pq reflete RDM
					else if (to.link[idLinkDebug].BCHTL[2]!=0)
						nomeBAMAtual = "PreemptionRDM";
					//Se BCLTH e BCHTL igual a 0 é pq reflete MAM
					else
						nomeBAMAtual = "NoPreemptionMAM";
				}
				
				int lspRequested=rodada.estatistica.lspRequested(ParametrosDSTE.Janela,to.link[idLinkDebug]);
				int lspRequestedCT0=rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela,to.link[idLinkDebug], 0);
				int lspRequestedCT1=rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela,to.link[idLinkDebug], 1);
				int lspRequestedCT2=rodada.estatistica.lspRequestedCT(ParametrosDSTE.Janela,to.link[idLinkDebug], 2);
				int lspEstablishedTotal = rodada.estatistica.lspEstablishedTotal(ParametrosDSTE.Janela,to.link[idLinkDebug]);
				int lspEstablishedTotalCT0=rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela,to.link[idLinkDebug], 0);
				int lspEstablishedTotalCT1=rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela,to.link[idLinkDebug], 1);
				int lspEstablishedTotalCT2=rodada.estatistica.lspEstablishedTotalCT(ParametrosDSTE.Janela,to.link[idLinkDebug], 2);
				int lspEstablishedAnterior=rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela,to.link[idLinkDebug]);
				int lspEstablishedAnteriorCT0=rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela,to.link[idLinkDebug], 0);
				int lspEstablishedAnteriorCT1=rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela,to.link[idLinkDebug], 1);
				int lspEstablishedAnteriorCT2=rodada.estatistica.lspEstablishedAnterior(ParametrosDSTE.Janela,to.link[idLinkDebug], 2);
				
				double []bloqueiosCTJanela   	= new double [] {0, 0, 0} ;  
				double []preempcoesCTJanela  	= new double [] {0, 0, 0} ;  
				double []devolucoesCTJanela   	= new double [] {0, 0, 0} ;  
				
				double bloqueiosJanela   	= 0.0;  
				double preempcoesJanela  	= 0.0;
				double devolucoesJanela   	= 0.0;
			 	
				 	
				bloqueiosJanela = lspRequested > 0 ? (double)rodada.estatistica.bloqueios(ParametrosDSTE.Janela,to.link[idLinkDebug])/lspRequested:0;
				bloqueiosCTJanela[0] = lspRequestedCT0 > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,to.link[idLinkDebug],0)/lspRequestedCT0:0;
				bloqueiosCTJanela[1] = lspRequestedCT1 > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,to.link[idLinkDebug],1)/lspRequestedCT1:0;
				bloqueiosCTJanela[2] = lspRequestedCT2 > 0 ? (double)rodada.estatistica.bloqueiosCT(ParametrosDSTE.Janela,to.link[idLinkDebug],2)/lspRequestedCT2:0;

				
				preempcoesJanela = lspEstablishedTotal + lspEstablishedAnterior > 0 ? (double)rodada.estatistica.preempcoes(ParametrosDSTE.Janela,to.link[idLinkDebug]) / (lspEstablishedTotal+lspEstablishedAnterior):0;
				preempcoesCTJanela[0] = lspEstablishedTotalCT0 + lspEstablishedAnteriorCT0 > 0 ? (double)rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,to.link[idLinkDebug],0) / (lspEstablishedTotalCT0 + lspEstablishedAnteriorCT0) :0;
				preempcoesCTJanela[1] = lspEstablishedTotalCT1 + lspEstablishedAnteriorCT1 > 0 ? (double)rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,to.link[idLinkDebug],1) / (lspEstablishedTotalCT1 + lspEstablishedAnteriorCT1) :0;				
				preempcoesCTJanela[2] = lspEstablishedTotalCT2 + lspEstablishedAnteriorCT2 > 0 ? (double)rodada.estatistica.preempcoesCT(ParametrosDSTE.Janela,to.link[idLinkDebug],2) / (lspEstablishedTotalCT2 + lspEstablishedAnteriorCT2) :0;
				
				devolucoesJanela = lspEstablishedTotal + lspEstablishedAnterior > 0 ? (double)rodada.estatistica.devolucoes(ParametrosDSTE.Janela,to.link[idLinkDebug]) / (lspEstablishedTotal + lspEstablishedAnterior) :0;
				devolucoesCTJanela[0] = lspEstablishedTotalCT0 + lspEstablishedAnteriorCT0 > 0 ? (double)rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,to.link[idLinkDebug],0) / (lspEstablishedTotalCT0 + lspEstablishedAnteriorCT0) :0;
				devolucoesCTJanela[1] = lspEstablishedTotalCT1 + lspEstablishedAnteriorCT1 > 0 ? (double)rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,to.link[idLinkDebug],1) / (lspEstablishedTotalCT1 + lspEstablishedAnteriorCT1) :0;
				devolucoesCTJanela[2] = lspEstablishedTotalCT2 + lspEstablishedAnteriorCT2 > 0 ? (double)rodada.estatistica.devolucoesCT(ParametrosDSTE.Janela,to.link[idLinkDebug],2) / (lspEstablishedTotalCT2 + lspEstablishedAnteriorCT2) :0;
				
				

					
								BancoDeDados.setXML(  rodada.simtime() + "\t<<<- Leitura da rede ->>>\t"       ////Imprime  o tempo de execução da simulação
										//+ to.link[idLinkDebug].getID() + "\t"         ////Imprime  o ID do link debugado
										+ nomeBAMAtual + "\t"                         ////Imprime  o nome do Bam Atual
										//+ ParametrosDSTE.Janela + "\t"                ////Imprime  o tamanho da jamela escolhida
										//+ to.link[idLinkDebug].CargaEnlace  + "\t"    ////Imprime  a banda Do link
										
										//+ to.link[idLinkDebug].CargaEnlace * to.link[idLinkDebug].BC[0] / 100 + "\t"  ////Imprime  a banda reservada para o BC0
										///+ to.link[idLinkDebug].CargaEnlace * to.link[idLinkDebug].BC[1] / 100 + "\t"  ////Imprime  a banda reservada para o BC1
										//+ to.link[idLinkDebug].CargaEnlace * to.link[idLinkDebug].BC[2] / 100 + "\t"  ////Imprime  a banda reservada para o BC2
										
										
									/*	+ to.link[idLinkDebug].getCargaEnlaceAtual()  + "\t"  ////Imprime  a Carga de utilização Geral
										+ to.link[idLinkDebug].CargaCTAtual[0] + "\t"  ////Imprime  a Carga de utilização no CT0
										+ to.link[idLinkDebug].CargaCTAtual[1] + "\t"  ////Imprime  a Carga de utilização no CT1
										+ to.link[idLinkDebug].CargaCTAtual[2] + "\t"  ////Imprime  a Carga de utilização no CT2
										*/
										//+"\t\t\t\t"
										
										+ rodada.estatistica.picoDeUtilizacaoDoEnlace(ParametrosDSTE.Janela, to.link[idLinkDebug]) /to.link[idLinkDebug].CargaEnlace + "\t"       ////Imprime  a % da Utilização TOTAL do enlarce em cada janela
										//+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 0) /to.link[idLinkDebug].CargaEnlace + "\t"  ////Imprime  a % da Utilização do enlarce  no CT0 em cada janela
										//+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 1) /to.link[idLinkDebug].CargaEnlace + "\t"  ////Imprime  a % da Utilização do enlarce  no CT1 em cada janela
										//+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 2) /to.link[idLinkDebug].CargaEnlace + "\t"  ////Imprime  a % da Utilização do enlarce  no CT2 em cada janela


									
										+ bloqueiosJanela + "\t"		     ////Imprime  a % da taxa de BLOQUEIO TOTAL do enlarce em cada janela		
										//+ bloqueiosCTJanela[0] + "\t"        ////Imprime  a % da taxa de BLOQUEIO do enlarce No CT0 em cada janela
										//+ bloqueiosCTJanela[1] + "\t"        ////Imprime  a % da taxa de BLOQUEIO do enlarce No CT1 em cada janela
										//+ bloqueiosCTJanela[2] + "\t"        ////Imprime  a % da taxa de BLOQUEIO do enlarce No CT2 em cada janela
										
										+ preempcoesJanela + "\t"		     ////Imprime  a % da taxa de PREEMPÇÃO TOTAL do enlarce em cada janela
										//+ preempcoesCTJanela[0] + "\t"		 ////Imprime  a % da taxa de PREEMPÇÃO  do enlarce No CT0 em cada janela
										//+ preempcoesCTJanela[1] + "\t"		 ////Imprime  a % da taxa de PREEMPÇÃO  do enlarce No CT1 em cada janela
										//+ preempcoesCTJanela[2] + "\t"		 ////Imprime  a % da taxa de PREEMPÇÃO  do enlarce No CT2 em cada janela
										
										+ devolucoesJanela + "\t"		     ////Imprime  a % da taxa de DEVOLUÇÃO TOTAL do enlarce em cada janela
										//+ devolucoesCTJanela[0] + "\t"		 ////Imprime  a % da taxa de DEVOLUÇÃO  do enlarce No CT0 em cada janela
										//+ devolucoesCTJanela[1] + "\t"		 ////Imprime  a % da taxa de DEVOLUÇÃO  do enlarce No CT0 em cada janela
										//+ devolucoesCTJanela[2] + "\t"		 ////Imprime  a % da taxa de DEVOLUÇÃO  do enlarce No CT0 em cada janela
										
										//+ (rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 0) /to.link[idLinkDebug].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 1) /to.link[idLinkDebug].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 2) /to.link[idLinkDebug].CargaEnlace)  + "\t"
										+ "\t"+lspRequested 		 ////Imprime  o numero de LSPs geradas até o momento.

										//, "saida");
//							, rodada.filename+"_7");
							, rodada.filename);
								
							/*	BancoDeDados.setXML(  rodada.simtime() + "\t"
										+ nomeBAMAtual + "\t"
										
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 0) /to.link[idLinkDebug].CargaEnlace + "\t"
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 1) /to.link[idLinkDebug].CargaEnlace + "\t"
										+ rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 2) /to.link[idLinkDebug].CargaEnlace + "\t"
														
										+ bloqueiosCTJanela[0] + "\t"
										+ bloqueiosCTJanela[1] + "\t"
										+ bloqueiosCTJanela[2] + "\t"
										
										+ preempcoesCTJanela[0] + "\t"
										+ preempcoesCTJanela[1] + "\t"
										+ preempcoesCTJanela[2] + "\t"
										
										+ devolucoesCTJanela[0] + "\t"
										+ devolucoesCTJanela[1] + "\t"
										+ devolucoesCTJanela[2] + "\t"
										//+ (rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 0) /to.link[idLinkDebug].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 1) /to.link[idLinkDebug].CargaEnlace+rodada.estatistica.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela, to.link[idLinkDebug], 2) /to.link[idLinkDebug].CargaEnlace)  + "\t"
										//+ lspRequested
										

										//,"saida");
										, rodada.filename+"_7");*/
								
				
					rodada.schedulep(7, ParametrosDSTE.RRDBatida, null);
					//rodada.schedulep(7, ParametrosDSTE.Janela*3 , null);
					
					
				break;
			
			

			}
			Debug.setMensagem(" ==== Status dos Links  ====",5,5);
			Debug.setMensagem(to.statusLinks(),5,5);
			//Debug.setMensagem(rodada.imprime_evchain(), 0, 0);
			//BancoDeDados.setXML(rodada.imprime_evchain(),"debug2");

		}
		
		
		Debug.setMensagem("\r\n\r\n ==== Status dos Links  ====",5,5);
		Debug.setMensagem(to.statusLinks(),5,5);
		rodada.estatistica.tempoSimulacaoFim=System.currentTimeMillis();
		Debug.setMensagem(rodada.estatistica.getEstatisticas(),3,3);
		if(ParametrosDSTE.RecomendacaoCBRSwitchBAM)
		{
			Debug.setMensagem(BAMRecommenderNoGUI.getInstance().getStringCases(),3,3);
		}
		try {
			Debug.setMensagem("Plotando os gráficos..." ,3,3);
			rodada.estatistica.gerarLinkRRDPNG(to);
			rodada.estatistica.gerarRRDPNGpreempcao();
			rodada.estatistica.gerarRRDPNGlspRequested();
			rodada.estatistica.gerarRRDPNGlspEstablished();
			rodada.estatistica.gerarRRDPNGlspUnbroken();
			rodada.estatistica.gerarRRDPNGbloqueio();
			rodada.estatistica.gerarRRDPNGdevolucao();
			rodada.estatistica.gerarRRDXML();
			rodada.estatistica.gerarLinksRRDXML();
			//System.out.println("Agora sim acabou");
			
		} catch (RrdException e) {
			
			e.printStackTrace();
		}

	}

	private void switchBAM(Link link, String solutionRecomendada) {
		Lsp LSPaux= new Lsp(); 
		LSPaux.Carga=0; 
		
		
		switch (solutionRecomendada) {
		case "NoPreemptionMAM":

			link.bamType = BAMType.PreemptionGBAM;
			link.BCLTH= new double[]
			{	000, //BC0 
				000, //BC1
				0  //BC2 Nunca mudar
			};
			LSPaux.CT=0; 
      		BAM.devolutionG(link,LSPaux);
			
			
      		link.BCHTL= new double[]
			{	0, //BC0 Nunca mudar
				000, //BC1
				000 //BC2
			};
			
			LSPaux.CT=2; 
      		BAM.preemptionG(link,LSPaux);
			
			break;
		case "PreemptionRDM":

			link.bamType = BAMType.PreemptionGBAM;
			link.BCLTH= new double[]
			{	000, //BC0 
				000, //BC1
				0  //BC2 Nunca mudar
			};
			LSPaux.CT=0; 
      		BAM.devolutionG(link,LSPaux);
				
				
      		link.BCHTL= new double[]
			{	0, //BC0 Nunca mudar
				100, //BC1
				100 //BC2
			};
			
			break;
		case "PreemptionAllocCTSharing":
			
			link.bamType = BAMType.PreemptionGBAM;
			link.BCLTH= new double[]
			{	100, //BC0 
				100, //BC1
				0  //BC2 Nunca mudar
			};
				
			link.BCHTL= new double[]
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





















