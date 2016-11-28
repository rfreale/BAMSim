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
import BAM.BAMRecommender.BAMDescription.Problemas;
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

		BancoDeDados.setXML("<?xml version='1.0'?>\r\n", rodada.filename);
		BancoDeDados.setXML("<simulacao>\r\n", rodada.filename);
		Debug.setMensagem("============================ In�cio da Primeira Rodada ============================");

		Topologia to = new Topologia();

		// Mostra par�metros padr�es
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

		// Inicializa tr�fego
		Debug.setMensagem("\r\n\r\n ==== Inicializa o tr�fego  ====");
		rodada.schedulep (3, 0.0, null);	

		// agenda estat�sticas
		rodada.schedulep(4, ParametrosDSTE.RRDAmostra, null);

		// agenda avalia��o CBR
		if(ParametrosDSTE.RecomendacaoCBRSwitchBAM)
		{
			rodada.schedulep (5, ParametrosDSTE.Janela+2*ParametrosDSTE.RRDAmostra, null);
		}
		try {
			rodada.estatistica.iniciarRRDLinks(to);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// inciatrafego2(rodada);

		// Inicializa a cadeia de eventos
		Debug.setMensagem("\r\n\r\n ==== Inicio da simula��o  ====");
		try {
			cadeiaDeEventos(rodada, to);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Debug.setMensagem("\r\n\r\n ==== Fim da si				mula��o  ====");
		BancoDeDados.setXML("</simulacao>\r\n", rodada.filename);
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

			/*
			 * BancoDeDados.setXML("<interacao>\r\n");
			 * BancoDeDados.setXML("<interacao_total_lsps_geradas>"
			 * +EstatisticasDSTE
			 * .lspRequested+"</interacao_total_lsps_geradas>\r\n"); for(int
			 * j=0;j<ParametrosDSTE.MaxClassType;j++)
			 * BancoDeDados.setXML("<interacao_lsps_preempted_class_"
			 * +j+">"+EstatisticasDSTE
			 * .preempcoesCT[j]+"</interacao_lsps_preempted_class_"+j+">\r\n");
			 * for(int j=0;j<ParametrosDSTE.MaxClassType;j++)
			 * BancoDeDados.setXML
			 * ("<interacao_lsps_preempted_class_debt_"+j+">"+
			 * EstatisticasDSTE.devolucoesCT
			 * [j]+"</interacao_lsps_preempted_class_debt_"+j+">\r\n"); for(int
			 * j=0;j<ParametrosDSTE.MaxClassType;j++)
			 * BancoDeDados.setXML("<interacao_lsps_blocked_class_"
			 * +j+">"+EstatisticasDSTE
			 * .bloqueiosCT[j]+"</interacao_lsps_blocked_class_"+j+">\r\n");
			 * BancoDeDados
			 * .setXML("<interacao_total_lsps_preemptadas>"+EstatisticasDSTE
			 * .preempcoes+"</interacao_total_lsps_preemptadas>\r\n");
			 * BancoDeDados
			 * .setXML("<interacao_total_lsps_bloq				ueadas>"+EstatisticasDSTE
			 * .bloqueios+"</interacao_total_lsps_bloqueadas>\r\n");
			 * BancoDeDados
			 * .setXML("<interacao_total_lsps_preemptadas_debt>"+EstatisticasDSTE
			 * .devolucoes+"</interacao_total_lsps_preemptadas_debt>\r\n");
			 * BancoDeDados
			 * .setXML("<interacao_total_banda_atendida>"+EstatisticasDSTE
			 * .bandaAtendida+"</interacao_total_banda_atendida>\r\n"); for(int
			 * y=0;y<ParametrosDSTE.LINKS;y++) {
			 * BancoDeDados.setXML("<enlace>\r\n");
			 * BancoDeDados.setXML("<enlace_numero>"+y+"</enlace_numero>\r\n");
			 * 
			 * 
			 * BancoDeDados.setXML("<enlace_carga>"+to.link[y].getCargaEnlaceAtual
			 * ()+"</enlace_carga>\r\n");
			 * BancoDeDados.setXML("<enlace_residual>"
			 * +to.link[y].CargaResidual()+"</enlace_residual>\r\n");
			 * 				
			 * if(to.link[y].bamType!=BAMType.NoPreemptionMAM||to.link[y].bamType
			 * !=BAMType.PreemptionMAM) { for(int
			 * j=0;j<ParametrosDSTE.MaxClassType;j++) {
			 * if(j!=ParametrosDSTE.MaxClassType-1)
			 * BancoDeDados.setXML("<enlace_carga_ct_"
			 * +j+">"+(to.link[y].BCAcumulado
			 * (j)-to.link[y].BCAcumulado(j+1))+"</enlace_carga_ct_"+j+">\r\n");
			 * else
			 * BancoDeDados.setXML("<enlace_carga_ct_"+j+">"+to.link[y].BCAcumulado
			 * (j)+"</enlace_carga_ct_"+j+">\r\n");
			 * 
			 * } } else { for(int j=0;j<ParametrosDSTE.MaxClassType;j++) {
			 * BancoDeDados
			 * .setXML("<enlace_carga_ct_"+j+">"+to.link[y].BCAcumulado
			 * (j)+"</enlace_carga_ct_"+j+">\r\n");
			 * 
			 * } } BancoDeDados.setXML("</enlace>\r\n"); }
			 * BancoDeDados.setXML("</interacao>\r\n");
			 */

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
						+ ((Lsp) dados.item).Carga + " Mbps");
				/*BancoDeDados.setXML((long) rodada.simtime()
						+ " - Tipo 1 - Tentar estabelecer LSP "
						+ ((Lsp) dados.item).ID + " com "
						+ ((Lsp) dados.item).Carga + " Mbps", rodada.filename);*/
				Long tempoInicial=System.nanoTime();
				Link[] menorCaminho = Roteamento.TryPath_CSPF(
						((Lsp) dados.item), to);
				rodada.estatistica.tempoAcumuladoGrantDeny+=System.nanoTime()-tempoInicial;
				
				if (menorCaminho != null) {
					Debug.setMensagem(" ==== Menor caminho  ====");
					Debug.setMensagem(to.imprimirCaminho(menorCaminho));
					((Lsp) dados.item).estabelecerLSP(menorCaminho);
					((Lsp) dados.item).status = LspStatus.estabelecida;
					rodada.estatistica.lspEstablished++;
					rodada.estatistica.lspEstablishedCT[((Lsp) dados.item).CT]++;
					rodada.estatistica.bandaEstabelecida += ((Lsp) dados.item).Carga;
					rodada.estatistica.bandaEstabelecidaCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
					
					Debug.setMensagem("========= LSP" + ((Lsp) dados.item).ID
							+ " Estabelecida ========");

					// agenda desestabelecimento
					rodada.schedulep(2, ((Lsp) dados.item).tempoDeVida, dados);

				} else {
					Debug.setMensagem("========= LSP" + ((Lsp) dados.item).ID
							+ " Bloqueada ========");
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
						+ ((Lsp) dados.item).Carga + " Mbps");
				((Lsp) dados.item).desestabeleceLSP();
				rodada.estatistica.lspUnbroken++;
				rodada.estatistica.lspUnbrokenCT[((Lsp) dados.item).CT]++;
				rodada.estatistica.bandaUnbrokenCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
				rodada.estatistica.bandaUnbroken += ((Lsp) dados.item).Carga;
				((Lsp) dados.item).status = LspStatus.finalizada;

				break;
			case 3:
				Debug.setMensagem("Tipo 3 - Agenda/Cria LSP ");
				ParametrosDSTE.trafegoManual(rodada, to, dados);

				break;

			
			case 4:
				//Insere estat�sticas RDD
				rodada.estatistica.inserirDadosRRD((long) rodada.simtime());
				rodada.estatistica.inserirDadosAbsolutoRRD((long) rodada.simtime());
				rodada.estatistica.statusLinks(to, (long) rodada.simtime());

				rodada.schedulep(4, ParametrosDSTE.RRDAmostra, null);
				break;
			case 5:
				//Avalia BAM via CBR

				CBRCase cbrCase = null;
				CBRQuery query = null;
				if (rodada.estatistica.devolucoes(ParametrosDSTE.Janela)*100/rodada.estatistica.lspEstablished(ParametrosDSTE.Janela) >= ParametrosDSTE.SLADevolucoes) {
					query = rodada.estatistica.getQuery(to.link[0],
							Problemas.AltaDevolucao, to.link[0].bamType);
					cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);

				} else if (rodada.estatistica.preempcoes(ParametrosDSTE.Janela)*100/rodada.estatistica.lspEstablished(ParametrosDSTE.Janela) >= ParametrosDSTE.SLAPreempcoes) {
					query = rodada.estatistica.getQuery(to.link[0],
							Problemas.AltaPreempcao, to.link[0].bamType);
					cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);

				} else if ((rodada.estatistica.bloqueios(ParametrosDSTE.Janela)*100/rodada.estatistica.lspRequested(ParametrosDSTE.Janela) >= ParametrosDSTE.SLABloqueios)&&((to.link[0].getCargaEnlaceAtual() * 100 / to.link[0].CargaEnlace) <= ParametrosDSTE.SLAUtilizacao)) {
					query = rodada.estatistica.getQuery(to.link[0],
							Problemas.BaixaUtilizacao, to.link[0].bamType);
					cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);
				}
				if (cbrCase != null) {
					
					BAMSolution solution = (BAMSolution) cbrCase.getSolution();
					switch (solution.getBAMNovo()) {
					case NoPreemptionMAM:
						to.link[0].bamType = BAMType.PreemptionGBAM;
						to.link[0].BCHTL= new double[]
								{	0, //BC0 Nunca mudar
							000, //BC1
							000 //BC2
						};
				
						to.link[0].BCLTH= new double[]
						{	000, //BC0 
							000, //BC1
							0  //BC2 Nunca mudar
						};
						//BAM.forcePreemption(to.link[0]);
						break;
					case PreemptionRDM:
						to.link[0].bamType = BAMType.PreemptionGBAM;
						to.link[0].BCHTL= new double[]
						{	0, //BC0 Nunca mudar
							100, //BC1
							100 //BC2
						};
				
						to.link[0].BCLTH= new double[]
						{	000, //BC0 
							000, //BC1
							0  //BC2 Nunca mudar
						};
						//BAM.forcePreemption(to.link[0]);
						break;
					case PreemptionAllocCTSharing:
						to.link[0].bamType = BAMType.PreemptionGBAM;
						to.link[0].BCHTL= new double[]
						{	0, //BC0 Nunca mudar
							100, //BC1
							100 //BC2
						};
				
						to.link[0].BCLTH= new double[]
						{	100, //BC0 
							100, //BC1
							0  //BC2 Nunca mudar
						};
						break;
					}
					
					BancoDeDados.setXML("Problema:"+((BAMDescription) query.getDescription()).getProblema()+"->Recomenda BAM"+solution.getBAMNovo()+" em "+ rodada.simtime()+":\n"+((BAMDescription) query.getDescription()).toString(), rodada.filename);
					BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
					BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
					CBRCase novocase = new CBRCase();
					novocase.setDescription(desc);
					novocase.setSolution(sol);
					No no = new No();
					no.item=novocase;
					
					
					//Agenda avaliar renten��o 
					rodada.schedulep(6, ParametrosDSTE.Janela, no);
					
					
				}
				else
				{
					//Agenda avaliar BAM via CBR
					rodada.schedulep(5, ParametrosDSTE.Janela, null);
				}
				break;
			case 6:
				//Avalia renten��o
				if ((rodada.estatistica.devolucoes(ParametrosDSTE.Janela) >=
						ParametrosDSTE.SLADevolucoes)||
					 (rodada.estatistica.preempcoes(ParametrosDSTE.Janela) >= 
					 ParametrosDSTE.SLAPreempcoes) || 
					 (
						 
								 (rodada.estatistica.bloqueios(ParametrosDSTE.Janela) >= ParametrosDSTE.SLABloqueios)
						 &&( (to.link[0].getCargaEnlaceAtual() * 100 / to.link[0].CargaEnlace) <= ParametrosDSTE.SLAUtilizacao)
									
					 )
					)
				 {
					rodada.schedulep(5, ParametrosDSTE.Janela, null);
				 }
				else
				{
					CBRCase novocase = ((CBRCase)dados.item);
					BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
					
					((BAMDescription)novocase.getDescription()).setCaseId("BAM"+(recommender.getCaseBase().getCases().size()+1));
					((BAMSolution)novocase.getSolution()).setId("BAM"+(recommender.getCaseBase().getCases().size()+1));
					jcolibri.method.retain.StoreCasesMethod.storeCase(recommender.getCaseBase(), novocase);
					rodada.schedulep(5, ParametrosDSTE.Janela, null);
				}
			break;
			
			

			}
			//Debug.setMensagem(" ==== Status dos Links  ====");
			//Debug.setMensagem(to.statusLinks());
			//Debug.setMensagem(rodada.imprime_evchain(), 0, 0);

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
