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
		Debug.setMensagem("============================ Inï¿½cio da Primeira Rodada ============================");

		Topologia to = new Topologia();

		// Mostra parï¿½metros padrï¿½es
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

		// Inicializa trï¿½fego
		Debug.setMensagem("\r\n\r\n ==== Inicializa o trï¿½fego  ====");
		inciatrafego(rodada);

		// agenda estatï¿½sticas
		rodada.schedulep(4, ParametrosDSTE.RRDAmostra, null);

		// agenda avaliaï¿½ï¿½o CBR
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
		Debug.setMensagem("\r\n\r\n ==== Inicio da simulaï¿½ï¿½o  ====");
		try {
			cadeiaDeEventos(rodada, to);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Debug.setMensagem("\r\n\r\n ==== Fim da si				mulaï¿½ï¿½o  ====");
		BancoDeDados.setXML("</simulacao>\r\n", rodada.filename);
		Debug.setMensagem("============================ Fim da Primeira Rodada ============================");

	}

	public void cadeiaDeEventos(RodadaDeSimulacao rodada, Topologia to)
			throws IOException, RrdException, ExecutionException {
		No dados;
		// Lsp lsp;
		rodada.estatistica.tempoSimulacaoInicio=System.currentTimeMillis();
		//tempo de simulaÃ§Ã£o
		while (ParametrosDSTE.condicaoDeParada(rodada))
		{

			/*
			 * BancoDeDados.setXML("<interacao>\r\n");
			 * BancoDeDados.setXML("<interacao_total_lsps_geradas>"
			 * +EstatisticasDSTE
			 * .lspGeradas+"</interacao_total_lsps_geradas>\r\n"); for(int
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
				rodada.estatistica.lspGeradas++;
				rodada.estatistica.lspGeradasCT[((Lsp) dados.item).CT]++;
				rodada.estatistica.bandaGerada += ((Lsp) dados.item).Carga;
				rodada.estatistica.bandaGeradaCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
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
					rodada.estatistica.lspEstabelecidas++;
					rodada.estatistica.lspEstabelecidasCT[((Lsp) dados.item).CT]++;
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
				rodada.estatistica.lspAtendidas++;
				rodada.estatistica.lspAtendidaCT[((Lsp) dados.item).CT]++;
				rodada.estatistica.bandaAtendidaCT[((Lsp) dados.item).CT] += ((Lsp) dados.item).Carga;
				rodada.estatistica.bandaAtendida += ((Lsp) dados.item).Carga;
				((Lsp) dados.item).status = LspStatus.finalizada;

				break;
			case 3:
				//Gera LSP - Tipo 3 - R0 -->R4
				Debug.setMensagem("Tipo 3 - Agenda/Cria LSP R0 -->R4");
				Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - R0 -->R4");
				rodada.schedulep (1, 0.0, dados);
				int auxCT=((Lsp)dados.item).CT;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; //id do router fonte
				lsp.dest = 4; // id do router destino
				lsp.CT =auxCT;
				lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
				dados.item = lsp;
				Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - R0 -->R4");
				if(rodada.simtime() <= 3600)
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
				}else if (rodada.simtime() <= 7200)
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(7), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(7), dados);
					} 
				}else // 10.800
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(4), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(2), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(2), dados);
					}
				}

				break;

			
			case 4:
				//Insere estatï¿½sticas RDD
				rodada.estatistica.inserirDadosRRD((long) rodada.simtime());
				rodada.estatistica.inserirDadosAbsolutoRRD((long) rodada.simtime());
				rodada.estatistica.statusLinks(to, (long) rodada.simtime());

				rodada.schedulep(4, ParametrosDSTE.RRDAmostra, null);
				break;
			case 5:
				//Avalia BAM via CBR
				
				CBRCase cbrCase = null;
				CBRQuery query = null;
				if (rodada.estatistica.devolucoes(ParametrosDSTE.Janela) >= ParametrosDSTE.SLADevolucoes) {
					query = rodada.estatistica.getQuery(to.link[0],
							Problemas.AltaDevolucao, to.link[0].bamType);
					cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);

				} else if (rodada.estatistica.preempcoes(ParametrosDSTE.Janela) >= ParametrosDSTE.SLAPreempcoes) {
					query = rodada.estatistica.getQuery(to.link[0],
							Problemas.AltaPreempcao, to.link[0].bamType);
					cbrCase = BAMRecommenderNoGUI.getInstance().cycle(query);

				} else if ((rodada.estatistica.bloqueios(ParametrosDSTE.Janela) >= ParametrosDSTE.SLABloqueios)&&((to.link[0].getCargaEnlaceAtual() * 100 / to.link[0].CargaEnlace) <= ParametrosDSTE.SLAUtilizacao)) {
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
					
					BancoDeDados.setXML("Recomenda BAM"+solution.getBAMNovo()+" em "+ rodada.simtime(), rodada.filename);
					BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
					BAMSolution sol = ((BAMSolution) cbrCase.getSolution()).clone();
					CBRCase novocase = new CBRCase();
					novocase.setDescription(desc);
					novocase.setSolution(sol);
					No no = new No();
					no.item=novocase;
					
					
					//Agenda avaliar rentenï¿½ï¿½o 
					rodada.schedulep(6, ParametrosDSTE.Janela, no);
					
					
				}
				else
				{
					//Agenda avaliar BAM via CBR
					rodada.schedulep(5, ParametrosDSTE.Janela, null);
				}
				break;
			case 6:
				//Avalia rentenï¿½ï¿½o
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
			
			case 7:
				//Gera LSP - Tipo 7 - R1 -->R4
				Debug.setMensagem("Tipo 7 - Agenda/Cria LSP R1 -->R4");
				Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - R1 -->R4");
				rodada.schedulep (1, 0.0, dados);
				auxCT=((Lsp)dados.item).CT;
				dados = new No();
				lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 1; //id do router fonte
				lsp.dest = 4; // id do router destino
				lsp.CT =auxCT;
				lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
				dados.item = lsp;
				Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - R1 -->R4");
				if(rodada.simtime() <= 3600)
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(15), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
				}else if (rodada.simtime() <= 7200)
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(10), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(10), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(10), dados);
					} 
				}else // 10.800
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(2), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(4), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (7, GeradorDeNumerosAleatorios.expntl(4), dados);
					}
				}

				break;
			
				case 8:
				//Gera LSP - Tipo 8 - R2 -->R4
				Debug.setMensagem("Tipo 8 - Agenda/Cria LSP R2 -->R4");
				Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - R2 -->R4");
				rodada.schedulep (1, 0.0, dados);
				auxCT=((Lsp)dados.item).CT;
				dados = new No();
				lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 2; //id do router fonte
				lsp.dest = 4; // id do router destino
				lsp.CT =auxCT;
				lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
				dados.item = lsp;
				Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - R2 -->R4");
				if(rodada.simtime() <= 3600)
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(15), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
				

				}else if (rodada.simtime() <= 7200)
				{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(14), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(14), dados);
				} 
			}else // 10.800
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(4), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(4), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (8, GeradorDeNumerosAleatorios.expntl(6), dados);
				}
			}

				break;
					

				case 9:
				//Gera LSP - Tipo 9 – R3 -->R4
				Debug.setMensagem("Tipo 9 - Agenda/Cria LSP R3 -->R4");
				Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" – R3 -->R4");
				rodada.schedulep (1, 0.0, dados);
				auxCT=((Lsp)dados.item).CT;
				dados = new No();
				lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 3; //id do router fonte
				lsp.dest = 4; // id do router destino
				lsp.CT =auxCT;
				lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
				dados.item = lsp;
				Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - R2 -->R4");
				if(rodada.simtime() <= 3600)
				{
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
					if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(25), dados);
					}
					if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
						rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(15), dados);
					}
				

				}else if (rodada.simtime() <= 7200)
				{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(14), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(14), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(10), dados);
				} 
			}else // 10.800
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(2), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(2), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(250);
					rodada.schedulep (9, GeradorDeNumerosAleatorios.expntl(2), dados);
				}
			}

				break;

			}
			Debug.setMensagem(" ==== Status dos Links  ====");
			Debug.setMensagem(to.statusLinks());
			Debug.setMensagem(rodada.imprime_evchain(), 0, 0);

		}
		Debug.setMensagem("\r\n\r\n ==== Status dos Links  ====");
		Debug.setMensagem(to.statusLinks());
		rodada.estatistica.tempoSimulacaoFim=System.currentTimeMillis();
		Debug.setMensagem(rodada.estatistica.getEstatisticas());
		try {
			rodada.estatistica.gerarRRDPNGpreempcao();
			rodada.estatistica.gerarRRDPNGlspGeradas();
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

	public void inciatrafego(RodadaDeSimulacao rodada) {
		iniciaTrafegoR0(rodada);
		iniciaTrafegoR1(rodada);
		iniciaTrafegoR2(rodada);
		iniciaTrafegoR3(rodada);

	}

	public void iniciaTrafegoR0(RodadaDeSimulacao rodada) {

		//Gera LSP - Tipo 3 (CT0) - R0 -->R4
		No dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (3, 0.0, dados);	
		Debug.setMensagem(rodada.imprime_evchain());
		
		
		//Gera LSP - Tipo 3 (CT1) - R0 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 1;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (3, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());
		
		//Gera LSP - Tipo 3 (CT2) - R0 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 2;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (3, 0.0, dados); //7200
		Debug.setMensagem(rodada.imprime_evchain());


	}
public void iniciaTrafegoR1(RodadaDeSimulacao rodada) {

	//Gera LSP - Tipo 7 (CT0) - R1 -->R4
		No dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 1;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (7, 0.0, dados);	//case, tempo pra iniciar o evento
		Debug.setMensagem(rodada.imprime_evchain());
		
		
		//Gera LSP - Tipo 7 (CT1) - R1 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 1;
		lsp.dest = 4;
		lsp.CT = 1;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (7, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());
		
		//Gera LSP - Tipo 7 (CT2) - R1 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 1;
		lsp.dest = 4;
		lsp.CT = 2;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (7, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());


	}

public void iniciaTrafegoR2(RodadaDeSimulacao rodada) {

//Gera LSP - Tipo 8 (CT0) - R2 -->R4
		No dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 2;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (8, 0.0, dados);	//case, tempo pra iniciar o evento
		Debug.setMensagem(rodada.imprime_evchain());
		
		
		//Gera LSP - Tipo 8 (CT1) - R2 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 2;
		lsp.dest = 4;
		lsp.CT = 1;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (8, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());
		
		//Gera LSP - Tipo 8 (CT2) - R2 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 2;
		lsp.dest = 4;
		lsp.CT = 2;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (8, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());


	}

public void iniciaTrafegoR3(RodadaDeSimulacao rodada) {

//Gera LSP - Tipo 9 (CT0) - R3 -->R4
		No dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 3;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (9, 0.0, dados);	//case, tempo pra iniciar o evento
		Debug.setMensagem(rodada.imprime_evchain());
		
		
		//Gera LSP - Tipo 9 (CT1) - R3 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 3;
		lsp.dest = 4;
		lsp.CT = 1;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (9, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());
		
		//Gera LSP - Tipo 9 (CT2) – R3 -->R4
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 3;
		lsp.dest = 4;
		lsp.CT = 2;
		lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
		lsp.tempoDeVida = GeradorDeNumerosAleatorios.expntl(250);
		dados.item = lsp;
		rodada.schedulep (9, 0.0, dados);
		Debug.setMensagem(rodada.imprime_evchain());


	}

}
