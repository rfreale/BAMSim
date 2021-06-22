package DSTE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.jrobin.core.FetchData;
import org.jrobin.core.FetchRequest;
import org.jrobin.core.RrdDb;
import org.jrobin.core.RrdDef;
import org.jrobin.core.RrdException;
import org.jrobin.core.Sample;
import org.jrobin.graph.RrdGraph;
import org.jrobin.graph.RrdGraphDef;
import org.junit.Test;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RVector;
import org.rosuda.JRI.Rengine;
//import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RserveException;

import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMRecommenderNoGUI;
import BAM.BAMRecommender.BAMTypes;
import Simulador.Debug;
import Simulador.Estatisticas;
import Simulador.GeradorDeNumerosAleatorios;
import Simulador.No;
import Simulador.ParametrosDoSimulador;
import Simulador.RodadaDeSimulacao;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;

public class BAMTest {
	@Test
	public void testeSoftSwitchGBAM() {
		//Definição da Topologia de Testes
				RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 1000;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				link.bamType = BAMType.PreemptionGBAM;
				
				roteadorOrigem.caminhos[0][0]=link;
				
				
				link.BC= new double[]
						{	10, // BC[0] =CT0 (Valor do Enlace)
							70, // BC[1] = CT1
							20 // BC[2] =  CT2
						};
						
				link.BCHTL= new double[]
						{	0, 
							90, 
							100 
						};
				
				link.BCLTH= new double[]
						{	100, 
							80,
							0 
						};
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 0;
				lsp.Carga = 50;
				lsp.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 100;
				lsp2.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 2;
				lsp3.Carga = 450;
				lsp3.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 250;
				lsp4.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 0;
				lsp5.Carga = 100;
				lsp5.caminho=roteadorOrigem.caminhos[0];
				
				Lsp lsp6 = new Lsp(r);
				lsp6.CargaReduzida = 0;
				lsp6.src = 0;
				lsp6.dest = 4;
				lsp6.CT = 1;
				lsp6.Carga = 100;
				lsp6.caminho=roteadorOrigem.caminhos[0];
				
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp));
				if(BAM.preemptionGBAM(link, lsp)==BAMStatus.aceita) lsp.estabelecerLSP(roteadorOrigem.caminhos[0]);
				//System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(link.imprimirResumoGBAM());
				System.out.println("==========CONSOLIDADO===========");
				System.out.print(link.imprimirConsolidadoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp2));
				if(BAM.preemptionGBAM(link, lsp2)==BAMStatus.aceita) lsp2.estabelecerLSP(roteadorOrigem.caminhos[0]);
				//System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(link.imprimirResumoGBAM());
				System.out.println("==========CONSOLIDADO===========");
				System.out.print(link.imprimirConsolidadoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp3));
				if(BAM.preemptionGBAM(link, lsp3)==BAMStatus.aceita) lsp3.estabelecerLSP(roteadorOrigem.caminhos[0]);
				//System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(link.imprimirResumoGBAM());
				System.out.println("==========CONSOLIDADO===========");
				System.out.print(link.imprimirConsolidadoGBAM());
				/*
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp4));
				if(BAM.preemptionGBAM(link, lsp4)==BAMStatus.aceita) lsp4.estabelecerLSP(roteadorOrigem.caminhos[0]);
				//System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(link.imprimirResumoGBAM());
				System.out.println("==========CONSOLIDADO===========");
				System.out.print(link.imprimirConsolidadoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp5));
				if(BAM.preemptionGBAM(link, lsp5)==BAMStatus.aceita) lsp5.estabelecerLSP(roteadorOrigem.caminhos[0]);
				//System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(link.imprimirResumoGBAM());
				System.out.println("==========CONSOLIDADO===========");
				System.out.print(link.imprimirConsolidadoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp6));
				if(BAM.preemptionGBAM(link, lsp6)==BAMStatus.aceita) lsp6.estabelecerLSP(roteadorOrigem.caminhos[0]);
				//System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(link.imprimirResumoGBAM());
				//System.out.print(link.statusLinks());
				System.out.println("==========CONSOLIDADO===========");
				System.out.print(link.imprimirConsolidadoGBAM());*/
				/*for(int i=ParametrosDSTE.MaxClassType-1;i>=0;i--)
				{
					System.out.println(link.BCAtual[i]-link.BCMbps(i));

				}*/
				/*Lsp LSPaux= new Lsp(); 
        		LSPaux.Carga=0; 
				
				link.BCLTH= new double[]
						{	100, 
							80,
							0 
						};
				LSPaux.CT=0; 
          		BAM.devolutionG(link,LSPaux);
          		System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				link.BCHTL= new double[]
						{	100, 
							80,
							0 
						};
				LSPaux.CT=2; 
          		BAM.preemptionG(link,LSPaux);
          		System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				//System.out.print(Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");*/
				
	}
	@Test
	public void testPoissonDiaHora() 
	{
		int slotsDeTempo =(int)ParametrosDSTE.TempoSimulacao/60;
		int numeroDeLSPsPorMinuto[][] = new int[ParametrosDSTE.MaxClassType][slotsDeTempo];
		
		for(int ct = 0;ct<ParametrosDSTE.MaxClassType;ct++)
		{
			System.out.println("========"+ct+"========");
			int countSlotDeTempo=0;
			while (countSlotDeTempo<numeroDeLSPsPorMinuto[ct].length)
			{
					
				
					
				int numeroDeLSPPorHora=GeradorDeNumerosAleatorios.uniform(1000, 2000);
				int lambdaPico = GeradorDeNumerosAleatorios.uniform(15, 45);
				//System.out.println("Lambda="+lambdaPico);
				int numeroDeLSPs[] = new int[lambdaPico*3];
				for (int i=0;i<numeroDeLSPPorHora;i++)
				{
					int minuto=GeradorDeNumerosAleatorios.poisson(lambdaPico);
					//if(minuto<lambdaPico*2)
						++numeroDeLSPs[minuto];
					//else
						//System.out.println("Discartado="+minuto);
		
		
				}
				
				int iCurva=0;
				
				for (int i = 0 ; i < lambdaPico*2 && numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(10, 50); i++) {
				    //System.out.println(i+"="+numeroDeLSPs[i]);
					//System.out.println(numeroDeLSPs[i]);
					iCurva++ ;
				  }
				int fCurva = numeroDeLSPs.length-1;
				
				for (int i = numeroDeLSPs.length-1; i > 0 && numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(11, 50); i--) {
				    //System.out.println(i+"="+numeroDeLSPs[i]);
					fCurva--;
					//System.out.println(numeroDeLSPs[i]);
				  }
				
				for (int i =iCurva; i<= fCurva && (countSlotDeTempo<numeroDeLSPsPorMinuto[ct].length); i++ ) {
				   System.out.println(numeroDeLSPs[i]);
				   numeroDeLSPsPorMinuto[ct][countSlotDeTempo++]=numeroDeLSPs[i];
				  }
				
				
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
	}
	@Test
	public void testeHardSwitchGBAM() {
		//Definição da Topologia de Testes
				RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 1000;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				link.bamType = BAMType.PreemptionGBAM;
				
				roteadorOrigem.caminhos[0][0]=link;
				
				
				link.BC= new double[]
						{	10, // BC[0] =CT0 (Valor do Enlace)
							70, // BC[1] = CT1
							20 // BC[2] =  CT2
						};
						
				link.BCHTL= new double[]
						{	0, 
							100, 
							100 
						};
				
				link.BCLTH= new double[]
						{	100, 
							100,
							0 
						};
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 200;
				lsp.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 0;
				lsp2.Carga = 100;
				lsp2.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 200;
				lsp3.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 300;
				lsp4.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 0;
				lsp5.Carga = 100;
				lsp5.caminho=roteadorOrigem.caminhos[0];
				
				Lsp lsp6 = new Lsp(r);
				lsp6.CargaReduzida = 0;
				lsp6.src = 0;
				lsp6.dest = 4;
				lsp6.CT = 2;
				lsp6.Carga = 100;
				lsp6.caminho=roteadorOrigem.caminhos[0];
				
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp));
				if(BAM.preemptionGBAM(link, lsp)==BAMStatus.aceita) lsp.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp2));
				if(BAM.preemptionGBAM(link, lsp2)==BAMStatus.aceita) lsp2.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp3));
				if(BAM.preemptionGBAM(link, lsp3)==BAMStatus.aceita) lsp3.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp4));
				if(BAM.preemptionGBAM(link, lsp4)==BAMStatus.aceita) lsp4.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp5));
				if(BAM.preemptionGBAM(link, lsp5)==BAMStatus.aceita) lsp5.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp6));
				if(BAM.preemptionGBAM(link, lsp6)==BAMStatus.aceita) lsp6.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				
				Lsp LSPaux= new Lsp(); 
        		LSPaux.Carga=0; 
				
				link.BCLTH= new double[]
						{	00, 
							00,
							0 
						};
				LSPaux.CT=0; 
          		BAM.devolutionG(link,LSPaux);
          		System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				link.BCHTL= new double[]
						{	00, 
							00,
							0 
						};
				LSPaux.CT=2; 
          		BAM.preemptionG(link,LSPaux);
          		System.out.print(link.imprimirUtilizacaoGBAM());
				System.out.print(link.imprimirResumoGBAM());
				//System.out.print(Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
				
	}
	@Test
	public void rrdTest() throws IOException, RrdException
	{
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		EstatisticasDSTE estatistica = r.estatistica;
		Debug.filename=r.filename;
		
		/*
		//Inicia Base zerada
		estatistica.inserirDadosAbsolutoRRD(-ParametrosDSTE.RRDBatida*2+1);
		//Inicia Base zerada
		estatistica.inserirDadosRRD(-ParametrosDSTE.RRDBatida*2+1);
		*/

		
		
		int i=0;
		estatistica.lspUnbroken=30;
		estatistica.preempcoes=10;
		estatistica.devolucoes=20;
		estatistica.lspEstablished=60;
		estatistica.bloqueios=40;
		estatistica.lspRequested=100;
		estatistica.inserirDadosRRD(0);
		//estatistica.inserirDadosAbsolutoRRD(0);
		
		System.out.println("preempções="+estatistica.preempcoes);
		System.out.println("preempções-base="+estatistica.preempcoesAUX);
		System.out.println("preempções-janela="+estatistica.preempcoes(ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps*5));
		//System.out.println("preempções-janelaAbs="+estatistica.preempcoesAbsoluto(ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps*5));
		
		for (i=2;i<=20;i++)
		{
			estatistica.lspUnbroken=30*i;
			estatistica.preempcoes=10*i;
			estatistica.devolucoes=20*i;
			estatistica.lspEstablished=60*i;
			estatistica.bloqueios=40*i;
			estatistica.lspRequested=100*i;
			
			
			estatistica.inserirDadosRRD(ParametrosDSTE.RRDBatida*(i-1));
			//estatistica.inserirDadosAbsolutoRRD(ParametrosDSTE.RRDBatida*(i-1));
			System.out.println("preempções="+estatistica.preempcoes);
			System.out.println("preempções-base="+estatistica.preempcoesAUX);
			System.out.println("preempções-janela="+estatistica.preempcoes(ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps*5));
			//System.out.println("preempções-janelaAbs="+estatistica.preempcoesAbsoluto(ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps*5));
			
		}
		estatistica.gerarRRDXML();

		/*
		Topologia to = new Topologia();
		to.carregarTopologiaArquivo();
		
		estatistica.iniciarRRDLinks(to);
		
		estatistica.statusLinks(to, -60);
		
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0; //id do router fonte
		lsp.dest = 1; // id do router destino
		lsp.CT =0;
		lsp.Carga = 10;
		
		to.link[0].insereLsp(lsp);

		estatistica.statusLinks(to, 0);
		
		
		for (i=2;i<=20;i++)
		{
			lsp = new Lsp(r);
			lsp.CargaReduzida = 0;
			lsp.src = 0; //id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT =(i-1)%3;
			lsp.Carga = (((i-1)%3)+1) * 10;
			
			to.link[0].insereLsp(lsp);

			estatistica.statusLinks(to, ParametrosDSTE.RRDBatida*(i-1));
			System.out.println("utilização="+estatistica.picoDeUtilizacaoDoEnlace(ParametrosDSTE.RRDBatida*5, to.link[0]));
			
			if (i%5==4)
			{
				to.link[0].removeLsp((Lsp)to.link[0].ListaLSPs.primeiro.prox.item);
				to.link[0].removeLsp((Lsp)to.link[0].ListaLSPs.primeiro.prox.item);
				to.link[0].removeLsp((Lsp)to.link[0].ListaLSPs.primeiro.prox.item);
			}
			

		}
		estatistica.gerarLinksRRDXML();
		*/
		
		
		estatistica.gerarRRDPNGlspRequested();
		estatistica.gerarRRDPNGlspEstablished();
		estatistica.gerarRRDPNGlspUnbroken();
		estatistica.gerarRRDPNGpreempcao();
		estatistica.gerarRRDPNGbloqueio();
		estatistica.gerarRRDPNGdevolucao();
		//estatistica.gerarLinkRRDPNG(to);
	}
	@Test
	public void cbrSimilarityConfigTest()
	{
		BAMRecommenderNoGUI recommender = BAMRecommenderNoGUI.getInstance();
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
		CBRCase cbrCase = null;
		CBRQuery query = new CBRQuery();

		BAMDescription desc = new BAMDescription();
		
		//desc.setGestor("Rafael");
		desc.setLink(0);

		
		
		
		desc.setBAMAtual(BAMTypes.NoPreemptionMAM);
		//desc.setProblema(Problemas.valueOf(problema.toString()));
		
	/*	desc.setSLAUtilizacaoCT0(ParametrosDSTE.SLAUtilizacaoCT[0]);
		desc.setSLAUtilizacaoCT1(ParametrosDSTE.SLAUtilizacaoCT[1]);
		desc.setSLAUtilizacaoCT2(ParametrosDSTE.SLAUtilizacaoCT[2]);
							
		desc.setSLABloqueiosCT0( ParametrosDSTE.SLABloqueiosCT[0]); 
		desc.setSLABloqueiosCT1( ParametrosDSTE.SLABloqueiosCT[1]);
		desc.setSLABloqueiosCT2( ParametrosDSTE.SLABloqueiosCT[2]);
		
		desc.setSLAPreempcoesCT0( ParametrosDSTE.SLAPreempcoesCT[0]);
		desc.setSLAPreempcoesCT1( ParametrosDSTE.SLAPreempcoesCT[1]);
		//desc.setSLAPreempcoesCT2( ParametrosDSTE.SLAPreempcoesCT[2]);
		
		//desc.setSLADevolucoesCT0( ParametrosDSTE.SLADevolucoesCT[0]);
		desc.setSLADevolucoesCT1( ParametrosDSTE.SLADevolucoesCT[1]);
		desc.setSLADevolucoesCT2( ParametrosDSTE.SLADevolucoesCT[2]);*/
		
					
		desc.setBC0( 250.0 );
		desc.setBC1( 600.0 );
		desc.setBC2( 1000.0 );
		
		desc.setUtilizacaoDoEnlaceCT0(10.0);
		desc.setUtilizacaoDoEnlaceCT1(20.0);
		desc.setUtilizacaoDoEnlaceCT2(30.0);
		
					
		/*desc.setNumeroDeBloqueiosCT0(5);
		desc.setNumeroDeBloqueiosCT1(6);
		desc.setNumeroDeBloqueiosCT2(8);
		
		desc.setNumeroDePreempcoesCT0(0);
		desc.setNumeroDePreempcoesCT1(0);
		//desc.setNumeroDePreempcoesCT2(0);
		
		//desc.setNumeroDeDevolucoesCT0(0);
		desc.setNumeroDeDevolucoesCT1(0);
		desc.setNumeroDeDevolucoesCT2(0);*/

		query.setDescription(desc);
		try {
			cbrCase = recommender.cycle(query);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		System.out.println(cbrCase.toString());
		
			
	
		
		
		
	
		
	}
	
	@Test
	public void scalaX()
	{
		double x=11;
		System.out.println(x/5/10);
		System.out.println(x%50);
		System.out.println(x-(x%50));
	}
	@Test
	public void carregarMatrizDeCaminhosArquivo()
	{
		
		try {
			Topologia t = new Topologia();
			t.carregarTopologiaArquivo();
			t.gerarTopologiaDosRoteadores();
			t.gerarTopologiaDosLinks();
			System.out.println(t.imprimirTopologiaDosRoteadores());
			System.out.println(t.imprimirTopologiaDosLinks());
			System.out.println(t.imprimirCaminhos());
			
			FileInputStream stream = new FileInputStream(ParametrosDSTE.filenameMatrizCaminhos);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();
			int count=0;
			while(linha != null) {
				StringTokenizer st2 = new StringTokenizer(linha, "-");
				t.adicionar(Integer.parseInt((String)st2.nextElement()), Integer.parseInt((String)st2.nextElement()), Integer.parseInt((String)st2.nextElement()));
				//System.out.println((String)st2.nextElement()+"-"+(String)st2.nextElement()+"-"+(String)st2.nextElement());
				linha = br.readLine();	
				count++;
			}
			
			
			System.out.println("Total de caminhos :"+count);
			br.close();
			System.out.println(t.imprimirCaminhos());
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}
	@Test
	public void rJava() throws RserveException, REXPMismatchException
	{
		int ROTEADORES =999;
		int teste =9;
		
		String resultado = String.format("%0"+String.valueOf(ROTEADORES).length()+"d", teste);
		 System.out.println(resultado);
	}
	@Test
	public void rJava2() throws REXPMismatchException, REngineException
	{

				Topologia t = new Topologia();
				t.carregarTopologiaArquivo();
				t.gerarTopologiaDosRoteadores();
				t.gerarTopologiaDosLinks();
				System.out.println(t.imprimirTopologiaDosRoteadores());
				System.out.println(t.imprimirTopologiaDosLinks());
				System.out.println(t.imprimirCaminhos());
				
	            try {

	  			  File outputfile = new File("topologias/NSF-14n-42e_Caminhos.txt");
	  			  outputfile.getParentFile().mkdirs();
	  			  
	  			  BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile,false));

	        	Rengine re = new Rengine(new String[]{"--no-save"}, false, null);

	        	re.eval("source('D:\\\\GitHub\\\\BAMSim\\\\rjava\\\\grafo.r')");
	        	for (int z=0; z<ParametrosDSTE.ROTEADORES;z++)
	    		{
	        		System.out.println(" ==== Roteador "+t.roteador[z].Descricao+"("+t.roteador[z].ID+") ====\r\n");
	    			
	    			
	        		
	    			REXP caminhosREXP= re.eval("sortestPathByName(net,'"+t.roteador[z].Descricao+"')$epath");
		            if (caminhosREXP != null)
		            {
		            	System.out.println(caminhosREXP+"\n");
		            	
			            RVector caminhosVector = caminhosREXP.asVector();
			            for (int i=0; i < caminhosVector.size(); i++)
			            {
			            	System.out.println("=== "+i+" ===");
			            	for (int j=0; j < caminhosVector.at(i).asIntArray().length; j++)
				            {
			            		System.out.println(caminhosVector.at(i).asIntArray()[j]);
			            		t.adicionar(z,i,caminhosVector.at(i).asIntArray()[j]-1);
			            		 writer.write(String.valueOf(z)+"-"+String.valueOf(i)+"-"+String.valueOf(caminhosVector.at(i).asIntArray()[j]-1));
			            		 writer.newLine();
				            }
			            	
			            }
		            }
	            
	    		}
	            System.out.println(t.imprimirCaminhos());

	  			  
	  			 
	  			  
	  			  writer.close();  
	  		 
	  		  }
	  		  catch (IOException e) {
	  			   // TODO Auto-generated catch block
	  			   System.out.println("Erro na gravação do arquivo:"+e.toString());
	  		  }
	            

                
                
	            
	            

	            
	            
	            
	        
	        
	    
	}
	
	@Test
	public void path() throws IOException, RrdException
	{
		String current = new java.io.File( ".//topologias" ).getCanonicalPath();
        System.out.println("Current dir:"+current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
		
        try {
            FileReader arq = new FileReader(ParametrosDSTE.filenameTopologia);
            BufferedReader lerArq = new BufferedReader(arq);
       
            String linha = lerArq.readLine(); // lê a primeira linha
      // a variável "linha" recebe o valor "null" quando o processo
      // de repetição atingir o final do arquivo texto
            while (linha != null) {
              System.out.printf("%s\n", linha);
       
              linha = lerArq.readLine(); // lê da segunda até a última linha
            }
       
            arq.close();
          } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
          }

	}
	@Test
	public void gerarLinkRRDPNG() throws IOException, RrdException
	{
		String filename= "1460594103373";
		String filename2= "1460595216933";
		BasicStroke dotDashStroke2 = 
			    new BasicStroke(2 /*width*/,
			            BasicStroke.CAP_BUTT /*end style*/,
			            BasicStroke.JOIN_MITER /*join style*/,
			            1.0f /*miter trim limit */,
			            new float[] {5.0f, 3.0f, 1.0f, 3.0f } /* pattern array */,
			            0.0f /* offset to start of pattern */);
		
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(1451617200,1451617200+21600);
		graphDef.setVerticalLabel("Mbps");
		graphDef.setMaxValue(1000);
		graphDef.setTitle("Link 1");
		int i=0;

		graphDef.datasource("S1->D1_total", "saida/"+filename+"/"+filename+"_links.rrd","S1->D1_total", "LAST");
		
		graphDef.datasource("S1->D1_total2", "saida/"+filename2+"/"+filename2+"_links.rrd","S1->D1_total", "LAST");

		//graphDef.line(link.Descricao+"_total",cores[i],"Total",2);
		graphDef.line("S1->D1_total",Color.RED,"AllocTC-Sharing",2,new BasicStroke(2));
		graphDef.line("S1->D1_total2",Color.BLUE,"MAM",2,dotDashStroke2);
		
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida2/"+filename+"/link_1.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   // rrdDb.close();

	}
	
	@Test
	public void preempcoes() throws IOException, RrdException
	{
		long time= 600;
		long curretTime = 1451632200;
		long RRDStarTime = 1451617200;
		String filename="1460131867298";
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		//Pega o maior valor da janela anterior
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", RRDStarTime,curretTime);
		//Pega o maior valor da janela atual
		FetchRequest fetchRequest2 = rrdDb.createFetchRequest("MAX", curretTime-60,curretTime+time);
		FetchData fetchData = fetchRequest.fetchData();
		FetchData fetchData2 = fetchRequest2.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData2.getAggregate("preempcao", "MAX")-fetchData.getAggregate("preempcao", "MAX"));
		
		rrdDb.close();
		System.out.println(fetchData.getAggregate("preempcao", "MAX"));
		System.out.println(fetchData2.getAggregate("preempcao", "MAX"));
		System.out.println(fetchData2.getAggregate("preempcao", "MIN"));
	}
	@Test
	public void desvioPadrao() 
	{
		List<Double> amostras =  new ArrayList<Double>();
		
		amostras.add(125.0);
		amostras.add(93.0);
		amostras.add(91.0);
		amostras.add(89.0);
		amostras.add(125.0);
		System.out.println(Estatisticas.calculaIntervaloDeConfianca(amostras));
		System.out.println(Estatisticas.calculaMedia(amostras));
		System.out.println(Estatisticas.calculaIC(amostras));
		System.out.println(Estatisticas.calculaDesvioPadrao(amostras));
		System.out.println(Estatisticas.calculaVariancia(amostras));

	
		
	}
	
	@Test
	public void testTimeStamp() 
	{
		
		
		Timestamp timeStamp= new Timestamp(1483236000);
		Date data= new Date(timeStamp.getTime());
		System.out.println(data.toString());

	
		
	}
	@Test
	public void testPoisson() 
	{
		double lambda = 10;
		for (int i=0;i<50;i++)
		{
			System.out.println(GeradorDeNumerosAleatorios.poisson(lambda));

		}
		
	}
	@Test
	public void testUniforme() 
	{
		Random rand = new Random(ParametrosDoSimulador.semente);
		for (int i=0;i<50;i++)
		{
			//System.out.println(GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType-1));
			System.out.println(GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES-1));
			
			//System.out.println(rand.nextInt(99));

		}
		
	}
	@Test
	public void testExp() 
	{
		double lambda = 300;
		for (int i=0;i<50;i++)
		{
			System.out.println(GeradorDeNumerosAleatorios.expntl(lambda));

		}
		
	}
	@Test
	public void testPoissonArray() 
	{
		
		int lambda = 12;
		int numeroDeLSPs[] = new int[lambda*3];
		
		for (int i=0;i<3000;i++)
		{
			int temp=GeradorDeNumerosAleatorios.poisson(lambda);
			
			if(temp<lambda*3)
				
				++numeroDeLSPs[temp];
			
			else
				System.out.println("erro="+temp);


		}
		
		
		for (int i = 0; i < numeroDeLSPs.length; i++) {
		    System.out.println(i+"="+numeroDeLSPs[i]);
		  }
		System.out.println();
		
	}
	@Test
	public void agregarRRDPNG() throws IOException, RrdException
	{
		int [] sementes ={5,2172,3934,4535,8750};
		String Type="dim";
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+3600);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		//graphDef.setTitle("Preemption - "+Type);
		graphDef.setTitle("Preemption - Switching (25/65)");
		for (int semente:sementes)
		{
			graphDef.datasource(semente+"_preempcao", Type+"_s"+semente+".rrd", "preempcao", "AVERAGE");
			//graphDef.datasource(semente+"_bloqueio", Type+"_s"+semente+".rrd", "bloqueio", "AVERAGE");
			//graphDef.datasource(semente+"_devolucao", Type+"_s"+semente+".rrd", "devolucao", "AVERAGE");
			//graphDef.line(semente+"_preempcao", new Color(0xFF, 0, 0), "Preemption", 2);
		}
		
		String aux="";
		for (int semente:sementes)
		{
			aux+=semente+"_preempcao,";
		}
		for (int j=0;j<sementes.length-1;j++)
			aux+="+,";
		aux+=sementes.length+",/";
		graphDef.datasource("preempcao", aux);
		graphDef.line("preempcao",new Color(0xFF, 0, 0),"Preemption",2);
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File(Type+"_media.png");
	    ImageIO.write(img,"png",outputfile);
	}
	@Test
	public void agregaLinkRRDPNG() throws IOException, RrdException
	{
		Color [] cores ={Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK};
		int [] sementes ={5,2172,3934,4535,8750};
		//RrdDb rrdDb = new RrdDb("s5_links.rrd");
		String Descricao="R0->R2";
		String Type="dim";
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+3600);
		graphDef.setVerticalLabel("Mbps");
		graphDef.setMaxValue(622);
		//graphDef.setTitle("Link Utilization -  "+Type);
		graphDef.setTitle("Link Utilization - Switching (25/65)");
		int i=0;
		for (int semente:sementes)
		{
			for(i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				graphDef.datasource(semente+"_"+Descricao+"_CT"+i, Type+"_s"+semente+"_links.rrd",Descricao+"_CT"+i, "AVERAGE");
			}
			graphDef.datasource(semente+"_"+Descricao+"_total", Type+"_s"+semente+"_links.rrd",Descricao+"_total", "AVERAGE");
		
			/*
			for(i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				graphDef.line(semente+"_"+Descricao+"_CT"+i, cores[i],"TC"+i, 2);
			}
			graphDef.line(semente+"_"+Descricao+"_total",cores[i],"Total",2);
			*/
			
		}
		
		for(i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			String aux="";
			for (int semente:sementes)
			{
				aux+=semente+"_"+Descricao+"_CT"+i+",";
			}
			for (int j=0;j<sementes.length-1;j++)
				aux+="+,";
			aux+=sementes.length+",/";
			graphDef.datasource("TC"+i, aux);
			graphDef.line("TC"+i, cores[i],"TC"+i, 2);
		}
		
		String aux="";
		for (int semente:sementes)
		{
			aux+=semente+"_"+Descricao+"_total,";
		}
		for (int j=0;j<sementes.length-1;j++)
			aux+="+,";
		aux+=sementes.length+",/";
		graphDef.datasource("total", aux);
		graphDef.line("total",cores[i],"Total",2);
		
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File(Type+"_media_link_0.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    //rrdDb.close();
	}
	@Test
	public void testAggregateRRD() throws RrdException, IOException
	{

		RrdDb rrdDb =  new RrdDb("BAMSim.rrd");
				
		//rrdDb = new RrdDb("BAMSim.rrd");
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+300);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef.setTitle("Teste RRD");
		graphDef.datasource("preempcao", "BAMSim.rrd", "preempcao", "AVERAGE");
		graphDef.datasource("preempcao2", "BAMSim2.rrd", "preempcao", "AVERAGE");
		graphDef.datasource("preempcao3",  "preempcao,preempcao2,30,40,50,+,+,+,+,5,/");
		graphDef.line("preempcao3", new Color(0xFF, 0, 0), "Preempção", 2);
		graphDef.line("preempcao2", Color.green, "Preempção", 2);

		//graphDef.line("utilizacao", new Color(0xFF, 0, 0), "Utilização", 2);
		//graphDef.area("preempcao", Color.gray, "Utilização");
		//graphDef.datasource("realspeed", "myspeed,1,*");
		//graphDef.line("realspeed", new Color(0xFF, 0, 0), null, 2);
		RrdGraph graph = new RrdGraph(graphDef);
		
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("BAMSim.png");
	    ImageIO.write(img,"png",outputfile);
	   // rrdDb.dumpXml("BAMSim.xml");
	}
	
	@Test
	public void testRRDPercentualDeBloqueios() throws RrdException, IOException
	{
		//1
		long starTime = 1483239600;
		long curretTime = starTime+86400;

		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setMaxValue(100);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef.setTitle("Bloqueio");
		graphDef.datasource("lspGeradasMIN", "db.rrd", "lspGeradas", "MIN");
		graphDef.datasource("lspGeradasMAX","db.rrd", "lspGeradas", "MAX");
		graphDef.datasource("lspGeradas2", "lspGeradasMAX,lspGeradasMIN,-");
		
		graphDef.datasource("bloqueioMIN", "db.rrd", "bloqueio", "MIN");
		graphDef.datasource("bloqueioMAX", "db.rrd", "bloqueio", "MAX");
		graphDef.datasource("bloqueio2", "bloqueioMAX,bloqueioMIN,-");
		//graphDef.datasource("bloqueios", "bloqueioMAX,bloqueioMIN,-,lspGeradasMAX,lspGeradasMIN,-,/,100,*");
		
		//graphDef.area("bloqueios", Color.gray, "% bloqueios");
		graphDef.area("lspGeradas2", Color.GREEN, "lspGeradas");
		graphDef.area("bloqueio2", Color.RED, "Bloqueio");


		//2
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setMaxValue(100);
		graphDef2.setVerticalLabel("Percent");
		//graphDef.setMinValue(0);
		graphDef2.setTitle("Bloqueio");
		graphDef2.datasource("lspGeradasMIN", "db.rrd", "lspGeradas", "MIN");
		graphDef2.datasource("lspGeradasMAX","db.rrd", "lspGeradas", "MAX");
		graphDef2.datasource("lspGeradas2", "lspGeradasMAX,lspGeradasMIN,-");
		
		graphDef2.datasource("bloqueioMIN", "db.rrd", "bloqueio", "MIN");
		graphDef2.datasource("bloqueioMAX", "db.rrd", "bloqueio", "MAX");
		graphDef2.datasource("bloqueio2", "bloqueioMAX,bloqueioMIN,-");
		graphDef2.datasource("bloqueios", "bloqueioMAX,bloqueioMIN,-,lspGeradasMAX,lspGeradasMIN,-,/,100,*");
		
		graphDef2.area("bloqueios", Color.gray, "% bloqueios");
		//graphDef2.line("lspGeradas2", Color.GREEN, "lspGeradas", 2);
		//graphDef2.line("bloqueio2", Color.RED, "Bloqueio", 2);
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("bloqueio2.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("bloqueio.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);

	}
	
	
	@Test
	public void testRRD() throws RrdException, IOException
	{
		//Criar base
		RrdDef rrdDef = new RrdDef("BAMSim.rrd");
		rrdDef.setStep(ParametrosDSTE.RRDBatida);
		long starTime = ParametrosDSTE.RRDStarTime;
		System.out.println(starTime);
		rrdDef.setStartTime(starTime);
		rrdDef.addDatasource("preempcao", "GAUGE", ParametrosDSTE.RRDBatida, Double.NaN, Double.NaN);
		rrdDef.addDatasource("bloqueio", "GAUGE", ParametrosDSTE.RRDBatida, Double.NaN, Double.NaN);
		rrdDef.addDatasource("devolucao", "GAUGE", ParametrosDSTE.RRDBatida, Double.NaN, Double.NaN);
		rrdDef.addDatasource("utilizacao", "GAUGE", ParametrosDSTE.RRDBatida, Double.NaN, Double.NaN);
		rrdDef.addArchive("MIN", 0.5, 12, 1440);
		rrdDef.addArchive("MAX", 0.5, 12, 1440);
		rrdDef.addArchive("AVERAGE", 0.5, 1, 1440);
		RrdDb rrdDb = new RrdDb(rrdDef);
		rrdDb.close();
		
		//Inserir dados
		rrdDb = new RrdDb("BAMSim.rrd");
		long i=starTime;
		System.out.println(starTime);
		int j =10;
		System.out.println(i+j);
		
		Sample sample = rrdDb.createSample();
		sample.setAndUpdate((i+=j)+":10:40:00:40");
		sample.setAndUpdate((i+=j)+":20:30:10:40");
		sample.setAndUpdate((i+=j)+":30:20:20:40");
		sample.setAndUpdate((i+=j)+":40:10:30:40");
		sample.setAndUpdate((i+=j)+":30:00:40:40");
		sample.setAndUpdate((i+=j)+":10:10:20:40");
		sample.setAndUpdate((i+=j)+":10:20:10:40");
		rrdDb.close();
		System.out.println(i);
		rrdDb = new RrdDb("BAMSim.rrd");
		//FetchRequest fetchRequest = rrdDb.createFetchRequest("AVERAGE", i-1*j,i);
		FetchRequest fetchRequest = rrdDb.createFetchRequest("AVERAGE", starTime+j,i);
		FetchData fetchData = fetchRequest.fetchData();
		System.out.println(fetchData.getTimestamps()[1]);
		System.out.println(fetchData.getValues("preempcao")[1]);
		System.out.println(fetchData.get95Percentile("preempcao"));
		System.out.println(fetchData.dump());
		System.out.println(fetchData.getAggregate("preempcao", "MAX"));
		System.out.println(fetchData.getAggregate("preempcao", "MIN"));
		System.out.println(fetchData.getAggregate("preempcao", "AVERAGE"));
		System.out.println(fetchData.getAggregate("bloqueio", "MAX"));
		System.out.println(fetchData.getAggregate("bloqueio", "MIN"));
		System.out.println(fetchData.getAggregate("bloqueio", "AVERAGE"));
		fetchData.dump();
		rrdDb.close();
		
		//rrdDb = new RrdDb("BAMSim.rrd");
		RrdGraphDef graphDef = new RrdGraphDef();
		//graphDef.setTimeSpan(i-3*j,i);
		graphDef.setTimeSpan(starTime,i);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef.setTitle("Teste RRD");
		graphDef.datasource("preempcao", "BAMSim.rrd", "preempcao", "AVERAGE");
		graphDef.datasource("bloqueio", "BAMSim.rrd", "bloqueio", "AVERAGE");
		graphDef.datasource("devolucao", "BAMSim.rrd", "devolucao", "AVERAGE");
		graphDef.datasource("utilizacao", "BAMSim.rrd", "utilizacao", "AVERAGE");
		graphDef.line("preempcao", new Color(0xFF, 0, 0), "Preempção", 2);
		graphDef.line("bloqueio", new Color(0x04, 0, 0xFF), "Bloqueio", 2);
		graphDef.line("devolucao", Color.GREEN, "Devolução", 2);
		//graphDef.line("utilizacao", new Color(0xFF, 0, 0), "Utilização", 2);
		//graphDef.area("preempcao", Color.gray, "Utilização");
		//graphDef.datasource("realspeed", "myspeed,1,*");
		//graphDef.line("realspeed", new Color(0xFF, 0, 0), null, 2);
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("BAMSim.png");
	    ImageIO.write(img,"png",outputfile);
	   // rrdDb.dumpXml("BAMSim.xml");
	}
	
	@Test
	public void testSimulador() throws IOException, RrdException
	{
		final String filename = new Date().getTime()+"";/*Usado com prefixo para arquivos e pastas*/
		
		//RodadaDeSimulacao sim[] = new RodadaDeSimulacao[ParametrosDoSimulador.MAX_SIMULATIONS];
				BancoDeDados.setXML("<?xml version='1.0'?>\r\n",filename);
				BancoDeDados.setXML("<simulacao>\r\n",filename);
				Debug.setMensagem("============================ Início da Primeira Rodada ============================",7,7);
				RodadaDeSimulacao sim = new RodadaDeSimulacao();
				Main.TesteSimulacao t1 = new Main.TesteSimulacao(sim);
				BancoDeDados.setXML("</simulacao>\r\n",filename);
				Debug.setMensagem("============================ Fim da Primeira Rodada ============================",7,7);
				//RodadaDeSimulacao sim2 = new RodadaDeSimulacao();
				//TesteSimulacao t2 = new TesteSimulacao(sim2);
	}
	@Test
	public void testTopologiaNTT()
	{
		RodadaDeSimulacao rodada = new RodadaDeSimulacao();
		Topologia to= new Topologia();
		
		ParametrosDSTE.BAMTypePadrao = BAMType.NoPreemptionMAM;
		ParametrosDSTE.BCPadrao= new double[]
		{	30, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
			30, // BC[1] = CT1 + CT2
			40 // BC[2] =  CT2
		};

		to.carregarTopologiaArquivo();
		
		to.carregarMatrizDeCaminhosManual();
		No dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 52;
		lsp.CT = 0;
		lsp.Carga = 186.6;
		lsp.tempoDeVida=250;
		dados.item = lsp;
		
		assertArrayEquals(to.roteador[0].caminhos[0],Roteamento.TryPath_CSPF(((Lsp)dados.item), to));
		Link [] menorCaminho = Roteamento.TryPath_CSPF(((Lsp)dados.item), to);
		((Lsp)dados.item).estabelecerLSP (menorCaminho);
		((Lsp)dados.item).status=LspStatus.estabelecida;
					
		for(Link link: menorCaminho)
		{  
			assertEquals("Check link", 186.6,link.getCargaEnlaceAtual(), 0.01); 
			if(link.lsrDest.ID==((Lsp)dados.item).dest)
			{
				break;
			}
		}  			
		
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 52;
		lsp.CT = 1;
		lsp.Carga = 186.6;
		lsp.tempoDeVida=250;
		dados.item = lsp;
		
		assertArrayEquals(to.roteador[0].caminhos[0],Roteamento.TryPath_CSPF(((Lsp)dados.item), to));
		menorCaminho = Roteamento.TryPath_CSPF(((Lsp)dados.item), to);
		((Lsp)dados.item).estabelecerLSP (menorCaminho);
		((Lsp)dados.item).status=LspStatus.estabelecida;
					
		for(Link link: menorCaminho)
		{  
			assertEquals("Check link", 2*186.6,link.getCargaEnlaceAtual(), 0.01);
			if(link.lsrDest.ID==((Lsp)dados.item).dest)
			{
				break;
			}
		}
		
		dados = new No();
		lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 52;
		lsp.CT = 2;
		lsp.Carga = 248.8;
		lsp.tempoDeVida=250;
		dados.item = lsp;
		
		assertArrayEquals(to.roteador[0].caminhos[0],Roteamento.TryPath_CSPF(((Lsp)dados.item), to));
		menorCaminho = Roteamento.TryPath_CSPF(((Lsp)dados.item), to);
		((Lsp)dados.item).estabelecerLSP (menorCaminho);
		((Lsp)dados.item).status=LspStatus.estabelecida;
					
		for(Link link: menorCaminho)
		{  
			
			assertEquals("Check link", 622.0,link.getCargaEnlaceAtual(), 0.01); 
			
			if(link.lsrDest.ID==((Lsp)dados.item).dest)
			{
				break;
			}
		}
		
		System.out.println(to.statusLinks());
		//((Lsp)dados.item).status=LspStatus.bloqueada;
		//EstatisticasDSTE.bloqueios++;

		
	}
	@Test
	public void testTopologia()
	{
		Topologia t = new Topologia();
		t.carregarTopologiaArquivo();
		t.gerarTopologiaDosRoteadores();
		t.gerarTopologiaDosLinks();
		System.out.println(t.imprimirTopologiaDosRoteadores());
		System.out.println(t.imprimirTopologiaDosLinks());
	}
	@Test
	public void testeExtrapolarEnlaceMAM() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		Roteador roteadorOrigem= new Roteador(); 
		roteadorOrigem.ID = 0;
		roteadorOrigem.Descricao = "S1";
		
		Roteador roteadorDestino= new Roteador(); 
		roteadorDestino.ID = 4;
		roteadorDestino.Descricao = "D1";
		
		Link link=new Link();
		link.Descricao = "S1->D1";
		link.ID = 1;
		link.CustoEnlace = 1;
		link.CargaEnlace = 622;
		link.lsrSrc = roteadorOrigem;
		link.lsrDest = roteadorDestino;
		
		link.BC= new double[]
		{	30, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
			30, // BC[1] = CT1 + CT2
			40 // BC[2] =  CT2
		};
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = link.CargaEnlace+0.1;
		
		Lsp lsp2 = new Lsp(r);
		lsp2.CargaReduzida = 0;
		lsp2.src = 0;
		lsp2.dest = 4;
		lsp2.CT = 1;
		lsp2.Carga = link.CargaEnlace+0.1;
		
		Lsp lsp3 = new Lsp(r);
		lsp3.CargaReduzida = 0;
		lsp3.src = 0;
		lsp3.dest = 4;
		lsp3.CT = 2;
		lsp3.Carga = link.CargaEnlace+0.1;
		
		//MAM
		assertEquals("Extrapolar o enlace MAM - CT0", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp));
		assertEquals("Extrapolar o enlace MAM - CT1", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp2));
		assertEquals("Extrapolar o enlace MAM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp3));
		
	}
	@Test
	public void testeBCMAM() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		Roteador roteadorOrigem= new Roteador(); 
		roteadorOrigem.ID = 0;
		roteadorOrigem.Descricao = "S1";
		
		Roteador roteadorDestino= new Roteador(); 
		roteadorDestino.ID = 4;
		roteadorDestino.Descricao = "D1";
		
		Link link=new Link();
		link.Descricao = "S1->D1";
		link.ID = 1;
		link.CustoEnlace = 1;
		link.CargaEnlace = 622;
		link.lsrSrc = roteadorOrigem;
		link.lsrDest = roteadorDestino;
		
		link.BC= new double[]
		{	30, // BC[0] =CT0 
			30, // BC[1] = CT1
			40 // BC[2] =  CT2
		};
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = link.BC[0]*link.CargaEnlace/100;
		
		Lsp lsp2 = new Lsp(r);
		lsp2.CargaReduzida = 0;
		lsp2.src = 0;
		lsp2.dest = 4;
		lsp2.CT = 1;
		lsp2.Carga = link.BC[1]*link.CargaEnlace/100;
		
		Lsp lsp3 = new Lsp(r);
		lsp3.CargaReduzida = 0;
		lsp3.src = 0;
		lsp3.dest = 4;
		lsp3.CT = 2;
		lsp3.Carga = link.BC[2]*link.CargaEnlace/100;
		
		//LSPs no limite das BCs
		assertEquals("Limite o BC0 MAM - CT0", BAMStatus.aceita,BAM.NoPreemptionMAM(link, lsp));
		assertEquals("Limite o BC1 MAM - CT1", BAMStatus.aceita,BAM.NoPreemptionMAM(link, lsp2));
		assertEquals("Limite o BC2 MAM - CT2", BAMStatus.aceita,BAM.NoPreemptionMAM(link, lsp3));
		
		//Extrapolando o limite das BCs
		lsp.Carga+=0.1;
		lsp2.Carga+=0.1;
		lsp3.Carga+=0.1;
		assertEquals("Limite o BC0 MAM - CT0", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp));
		assertEquals("Limite o BC1 MAM - CT1", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp2));
		assertEquals("Limite o BC2 MAM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp3));
		
		
	}
	@Test
	public void testeExtrapolarEnlaceRDM() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		Roteador roteadorOrigem= new Roteador(); 
		roteadorOrigem.ID = 0;
		roteadorOrigem.Descricao = "S1";
		
		Roteador roteadorDestino= new Roteador(); 
		roteadorDestino.ID = 4;
		roteadorDestino.Descricao = "D1";
		
		Link link=new Link();
		link.Descricao = "S1->D1";
		link.ID = 1;
		link.CustoEnlace = 1;
		link.CargaEnlace = 622;
		link.lsrSrc = roteadorOrigem;
		link.lsrDest = roteadorDestino;
		link.BC = new double[]
		{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
			70, // BC[1] = CT1 + CT2
			40 // BC[2] =  CT2
		};
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = link.CargaEnlace+0.1;
		
		Lsp lsp2 = new Lsp(r);
		lsp2.CargaReduzida = 0;
		lsp2.src = 0;
		lsp2.dest = 4;
		lsp2.CT = 1;
		lsp2.Carga = link.CargaEnlace+0.1;
		
		Lsp lsp3 = new Lsp(r);
		lsp3.CargaReduzida = 0;
		lsp3.src = 0;
		lsp3.dest = 4;
		lsp3.CT = 2;
		lsp3.Carga = link.CargaEnlace+0.1;
		
		//RDM sem preempção
		assertEquals("Extrapolar o enlace RDM - CT0", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp));
		assertEquals("Extrapolar o enlace RDM - CT1", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp2));
		assertEquals("Extrapolar o enlace RDM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp3));
		
		//RDM com preempção
		assertEquals("Extrapolar o enlace RDM - CT0", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp));
		assertEquals("Extrapolar o enlace RDM - CT1", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp2));
		assertEquals("Extrapolar o enlace RDM - CT2", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp3));
		
	}
	@Test
	public void testeBCRDM() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		Roteador roteadorOrigem= new Roteador(); 
		roteadorOrigem.ID = 0;
		roteadorOrigem.Descricao = "S1";
		
		Roteador roteadorDestino= new Roteador(); 
		roteadorDestino.ID = 4;
		roteadorDestino.Descricao = "D1";
		
		Link link=new Link();
		link.Descricao = "S1->D1";
		link.ID = 1;
		link.CustoEnlace = 1;
		link.CargaEnlace = 622;
		link.lsrSrc = roteadorOrigem;
		link.lsrDest = roteadorDestino;
		
		link.BC = new double[]
		{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
			70, // BC[1] = CT1 + CT2
			40 // BC[2] =  CT2
		};
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = (link.BC[0]/link.BC[0])*link.CargaEnlace;
		
		Lsp lsp2 = new Lsp(r);
		lsp2.CargaReduzida = 0;
		lsp2.src = 0;
		lsp2.dest = 4;
		lsp2.CT = 1;
		lsp2.Carga = (link.BC[1]/link.BC[0])*link.CargaEnlace;
		
		Lsp lsp3 = new Lsp(r);
		lsp3.CargaReduzida = 0;
		lsp3.src = 0;
		lsp3.dest = 4;
		lsp3.CT = 2;
		lsp3.Carga = (link.BC[2]/link.BC[0])*link.CargaEnlace;
		
		//LSPs no limite das BCs sem preempção
		assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.NoPreemptionRDM(link, lsp));
		assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.NoPreemptionRDM(link, lsp2));
		assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionRDM(link, lsp3));
		
		//LSPs no limite das BCs com preempção
		assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionRDM(link, lsp));
		assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionRDM(link, lsp2));
		assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionRDM(link, lsp3));
		
		//Extrapolando o limite das BCs
		lsp.Carga+=0.1;
		lsp2.Carga+=0.1;
		lsp3.Carga+=0.1;
		
		//Sem preempção
		assertEquals("Limite o BC0 MAM - CT0", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp));
		assertEquals("Limite o BC1 MAM - CT1", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp2));
		assertEquals("Limite o BC2 MAM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp3));
		
		//Com preempção
		assertEquals("Limite o BC0 MAM - CT0", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp));
		assertEquals("Limite o BC1 MAM - CT1", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp2));
		assertEquals("Limite o BC2 MAM - CT2", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp3));
		
		
	}
	public void testeExtrapolarEnlaceAlloCTSharing() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		Roteador roteadorOrigem= new Roteador(); 
		roteadorOrigem.ID = 0;
		roteadorOrigem.Descricao = "S1";
		
		Roteador roteadorDestino= new Roteador(); 
		roteadorDestino.ID = 4;
		roteadorDestino.Descricao = "D1";
		
		Link link=new Link();
		link.Descricao = "S1->D1";
		link.ID = 1;
		link.CustoEnlace = 1;
		link.CargaEnlace = 622;
		link.lsrSrc = roteadorOrigem;
		link.lsrDest = roteadorDestino;
		link.BC = new double[]
		{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
			70, // BC[1] = CT1 + CT2
			40 // BC[2] =  CT2
		};
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = link.CargaEnlace+0.1;
		
		Lsp lsp2 = new Lsp(r);
		lsp2.CargaReduzida = 0;
		lsp2.src = 0;
		lsp2.dest = 4;
		lsp2.CT = 1;
		lsp2.Carga = link.CargaEnlace+0.1;
		
		Lsp lsp3 = new Lsp(r);
		lsp3.CargaReduzida = 0;
		lsp3.src = 0;
		lsp3.dest = 4;
		lsp3.CT = 2;
		lsp3.Carga = link.CargaEnlace+0.1;
		
		//RDM
		assertEquals("Extrapolar o enlace RDM - CT0", BAMStatus.bloqueada,BAM.NoPreemptionAllocCTSharing(link, lsp));
		assertEquals("Extrapolar o enlace RDM - CT1", BAMStatus.bloqueada,BAM.NoPreemptionAllocCTSharing(link, lsp2));
		assertEquals("Extrapolar o enlace RDM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionAllocCTSharing(link, lsp3));
		
	}
	@Test
	public void testeBCAlloCTSharing() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
		Roteador roteadorOrigem= new Roteador(); 
		roteadorOrigem.ID = 0;
		roteadorOrigem.Descricao = "S1";
		
		Roteador roteadorDestino= new Roteador(); 
		roteadorDestino.ID = 4;
		roteadorDestino.Descricao = "D1";
		
		Link link=new Link();
		link.Descricao = "S1->D1";
		link.ID = 1;
		link.CustoEnlace = 1;
		link.CargaEnlace = 622;
		link.lsrSrc = roteadorOrigem;
		link.lsrDest = roteadorDestino;
		
		link.BC = new double[]
		{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
			70, // BC[1] = CT1 + CT2
			40 // BC[2] =  CT2
		};
		
		Lsp lsp = new Lsp(r);
		lsp.CargaReduzida = 0;
		lsp.src = 0;
		lsp.dest = 4;
		lsp.CT = 0;
		lsp.Carga = (link.BC[0]/link.BC[0])*link.CargaEnlace;
		
		Lsp lsp2 = new Lsp(r);
		lsp2.CargaReduzida = 0;
		lsp2.src = 0;
		lsp2.dest = 4;
		lsp2.CT = 1;
		lsp2.Carga = (link.BC[1]/link.BC[0])*link.CargaEnlace;
		
		Lsp lsp3 = new Lsp(r);
		lsp3.CargaReduzida = 0;
		lsp3.src = 0;
		lsp3.dest = 4;
		lsp3.CT = 2;
		lsp3.Carga = (link.BC[2]/link.BC[0])*link.CargaEnlace;
		
		//LSPs no limite das BCs
		assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp));
		assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp2));
		assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp3));
		
		//Extrapolando o limite das BCs
		lsp.Carga+=0.1;
		lsp2.Carga+=0.1;
		lsp3.Carga+=0.1;
		assertEquals("Limite o BC0 MAM - CT0", BAMStatus.bloqueada,BAM.NoPreemptionAllocCTSharing(link, lsp));
		assertEquals("Limite o BC1 MAM - CT1", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp2));
		assertEquals("Limite o BC2 MAM - CT2", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp3));
		
		
	}
	@Test
	public void testeBCHTLMAM() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	30, 
					30, 
					40 
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 35;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 25;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.NoPreemptionMAM(link, lsp));
				if(BAM.NoPreemptionMAM(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.NoPreemptionMAM(link, lsp2));
				if(BAM.NoPreemptionMAM(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionMAM(link, lsp3));
				if(BAM.NoPreemptionMAM(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp4));
				if(BAM.NoPreemptionMAM(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionMAM(link, lsp5));
				if(BAM.NoPreemptionMAM(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				
				//System.out.print(Mensagem.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
		
	}
	@Test
	public void testeBCHTLNoPreemptionRDM() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 40;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 25;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.NoPreemptionRDM(link, lsp));
				if(BAM.NoPreemptionRDM(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.NoPreemptionRDM(link, lsp2));
				if(BAM.NoPreemptionRDM(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionRDM(link, lsp3));
				if(BAM.NoPreemptionRDM(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp4));
				if(BAM.NoPreemptionRDM(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.bloqueada,BAM.NoPreemptionRDM(link, lsp5));
				if(BAM.NoPreemptionRDM(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				
				//System.out.print(Mensagem.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
		
	}
		@Test
	public void testeBCHTLRDM() {
		//Definição da Topologia de Testes
			RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 40;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 25;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionRDM(link, lsp));
				if(BAM.preemptionRDM(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionRDM(link, lsp2));
				if(BAM.preemptionRDM(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionRDM(link, lsp3));
				if(BAM.preemptionRDM(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp4));
				if(BAM.preemptionRDM(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.bloqueada,BAM.preemptionRDM(link, lsp5));
				if(BAM.preemptionRDM(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				
				System.out.print(Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
		
	}
	@Test
	public void testeBCHTLAllocCTSharing() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 35;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 25;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp));
				if(BAM.NoPreemptionAllocCTSharing(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp2));
				if(BAM.NoPreemptionAllocCTSharing(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp3));
				if(BAM.NoPreemptionAllocCTSharing(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp4));
				if(BAM.NoPreemptionAllocCTSharing(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.NoPreemptionAllocCTSharing(link, lsp5));
				if(BAM.NoPreemptionAllocCTSharing(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				
				//System.out.print(Mensagem.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
		
	}
		@Test
	public void testeBCHTLPreemptionRDM() {
		//Definição da Topologia de Testes
			RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 0;
				lsp4.Carga = 60;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 20;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionRDM(link, lsp));
				if(BAM.preemptionRDM(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionRDM(link, lsp2));
				if(BAM.preemptionRDM(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionRDM(link, lsp3));
				if(BAM.preemptionRDM(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionRDM(link, lsp4));
				if(BAM.preemptionRDM(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.preempcao,BAM.preemptionRDM(link, lsp5));
				if(BAM.preemptionRDM(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				
				System.out.print(Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
		
	}
	@Test
	public void testeBCLTHPreemptionAllocCTSharing() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 2;
				lsp4.Carga = 20;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 40;
				
				Lsp lsp6 = new Lsp(r);
				lsp6.CargaReduzida = 0;
				lsp6.src = 0;
				lsp6.dest = 4;
				lsp6.CT = 0;
				lsp6.Carga = 20;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp));
				if(BAM.preemptionAllocCTSharing(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp2));
				if(BAM.preemptionAllocCTSharing(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp3));
				if(BAM.preemptionAllocCTSharing(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp4));
				if(BAM.preemptionAllocCTSharing(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp5));
				if(BAM.preemptionAllocCTSharing(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.devolucao,BAM.preemptionAllocCTSharing(link, lsp6));
				if(BAM.preemptionAllocCTSharing(link, lsp6)==BAMStatus.aceita) link.insereLsp(lsp6);
				
				System.out.print("testeBCHTLPreemptionAllocCTSharing\n"+Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
				for(int w=ParametrosDSTE.MaxClassType-1;w>=0;w--)
				{
					System.out.print("BC["+r+"] empréstimo real = "+link.emprestimo(w));
				}
				System.out.print("\n");

	}
	@Test
	public void testeBCHTLPreemptionAllocCTSharing() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 50;
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 30;
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 0;
				lsp4.Carga = 35;
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 45;
				
				Lsp lsp6 = new Lsp(r);
				lsp6.CargaReduzida = 0;
				lsp6.src = 0;
				lsp6.dest = 4;
				lsp6.CT = 2;
				lsp6.Carga = 30;
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp));
				if(BAM.preemptionAllocCTSharing(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp2));
				if(BAM.preemptionAllocCTSharing(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp3));
				if(BAM.preemptionAllocCTSharing(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp4));
				if(BAM.preemptionAllocCTSharing(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp5));
				if(BAM.preemptionAllocCTSharing(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.preempcao,BAM.preemptionAllocCTSharing(link, lsp6));
				if(BAM.preemptionAllocCTSharing(link, lsp6)==BAMStatus.aceita) link.insereLsp(lsp6);
				
				System.out.print("testeBCHTLPreemptionAllocCTSharing\n"+Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
				for(int w=ParametrosDSTE.MaxClassType-1;w>=0;w--)
				{
					System.out.print("BC["+w+"] empréstimo real = "+link.emprestimo(w));
				}
				System.out.print("\n");

	}
	@Test
	public void testeBC_HTL_LTH_PreemptionAllocCTSharing() {
		//Definição da Topologia de Testes
		RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				link.bamType = BAMType.PreemptionAllocCTSharing;
				
				roteadorOrigem.caminhos[0][0]=link;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 50;
				lsp.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 10;
				lsp2.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 40;
				lsp3.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 0;
				lsp4.Carga = 40;
				lsp4.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 2;
				lsp5.Carga = 60;
				lsp5.caminho=roteadorOrigem.caminhos[0];
				
				Lsp lsp6 = new Lsp(r);
				lsp6.CargaReduzida = 0;
				lsp6.src = 0;
				lsp6.dest = 4;
				lsp6.CT = 1;
				lsp6.Carga = 50;
				lsp6.caminho=roteadorOrigem.caminhos[0];
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp));
				if(BAM.preemptionAllocCTSharing(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp2));
				if(BAM.preemptionAllocCTSharing(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp3));
				if(BAM.preemptionAllocCTSharing(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp4));
				if(BAM.preemptionAllocCTSharing(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionAllocCTSharing(link, lsp5));
				if(BAM.preemptionAllocCTSharing(link, lsp5)==BAMStatus.aceita) link.insereLsp(lsp5);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.devolucaoEpreempcao,BAM.preemptionAllocCTSharing(link, lsp6));
				lsp6.estabelecerLSP(roteadorOrigem.caminhos[0]);

				
				System.out.print("testeBCHTLPreemptionAllocCTSharing\n"+Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
				for(int w=ParametrosDSTE.MaxClassType-1;w>=0;w--)
				{
					System.out.print("BC["+w+"] empréstimo real = "+link.emprestimo(w));
				}
				System.out.print("\n");

	}
	@Test
	public void testePreemption() {
		//Definição da Topologia de Testes
				RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 200;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				
				roteadorOrigem.caminhos[0][0]=link;
				
				link.BC = new double[]
				{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
					70, // BC[1] = CT1 + CT2
					40 // BC[2] =  CT2
				};
				
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 80;
				lsp.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 40;
				lsp2.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				lsp3.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 0;
				lsp4.Carga = 60;
				lsp4.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 20;
				lsp5.caminho=roteadorOrigem.caminhos[0];
				
				//LSPs no limite das BCs
				assertEquals("Limite o BC0 RDM - CT0", BAMStatus.aceita,BAM.preemptionRDM(link, lsp));
				if(BAM.preemptionRDM(link, lsp)==BAMStatus.aceita) link.insereLsp(lsp);
				assertEquals("Limite o BC1 RDM - CT1", BAMStatus.aceita,BAM.preemptionRDM(link, lsp2));
				if(BAM.preemptionRDM(link, lsp2)==BAMStatus.aceita) link.insereLsp(lsp2);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionRDM(link, lsp3));
				if(BAM.preemptionRDM(link, lsp3)==BAMStatus.aceita) link.insereLsp(lsp3);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.aceita,BAM.preemptionRDM(link, lsp4));
				if(BAM.preemptionRDM(link, lsp4)==BAMStatus.aceita) link.insereLsp(lsp4);
				assertEquals("Limite o BC2 RDM - CT2", BAMStatus.preempcao,BAM.preemptionRDM(link, lsp5));
				if(BAM.preemptionRDM(link, lsp5)==BAMStatus.preempcao) 
				{
							BAM.preemption(link, lsp5);
				}
				if(BAM.preemptionRDM(link, lsp5)==BAMStatus.aceita) 
				{
							link.insereLsp(lsp5);
				}
				
				System.out.print(Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
		
	}


	@Test
	public void testeGBAM() {
		//Definição da Topologia de Testes
				RodadaDeSimulacao r = new RodadaDeSimulacao();
				Roteador roteadorOrigem= new Roteador(); 
				roteadorOrigem.ID = 0;
				roteadorOrigem.Descricao = "S1";
				
				Roteador roteadorDestino= new Roteador(); 
				roteadorDestino.ID = 4;
				roteadorDestino.Descricao = "D1";
				
				
				
				Link link=new Link();
				link.Descricao = "S1->D1";
				link.ID = 1;
				link.CustoEnlace = 1;
				link.CargaEnlace = 622;
				link.lsrSrc = roteadorOrigem;
				link.lsrDest = roteadorDestino;
				link.bamType = BAMType.PreemptionGBAM;
				
				roteadorOrigem.caminhos[0][0]=link;
				
				
				link.BC= new double[]
						{	40, // BC[0] =CT0 (Valor do Enlace)
							35, // BC[1] = CT1
							25 // BC[2] =  CT2
						};
						
				link.BCHTL= new double[]
						{	0, 
							50, 
							50 
						};
				
				link.BCLTH= new double[]
						{	25, 
							25,
							0 
						};
				
				Lsp lsp = new Lsp(r);
				lsp.CargaReduzida = 0;
				lsp.src = 0;
				lsp.dest = 4;
				lsp.CT = 2;
				lsp.Carga = 272.12;
				lsp.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp2 = new Lsp(r);
				lsp2.CargaReduzida = 0;
				lsp2.src = 0;
				lsp2.dest = 4;
				lsp2.CT = 1;
				lsp2.Carga = 217.70;
				lsp2.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp3 = new Lsp(r);
				lsp3.CargaReduzida = 0;
				lsp3.src = 0;
				lsp3.dest = 4;
				lsp3.CT = 0;
				lsp3.Carga = 20;
				lsp3.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp4 = new Lsp(r);
				lsp4.CargaReduzida = 0;
				lsp4.src = 0;
				lsp4.dest = 4;
				lsp4.CT = 0;
				lsp4.Carga = 60;
				lsp4.caminho=roteadorOrigem.caminhos[0];
				
				
				Lsp lsp5 = new Lsp(r);
				lsp5.CargaReduzida = 0;
				lsp5.src = 0;
				lsp5.dest = 4;
				lsp5.CT = 1;
				lsp5.Carga = 20;
				lsp5.caminho=roteadorOrigem.caminhos[0];
				
				
				System.out.print(link.imprimirResumoGBAM());
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp));
				if(BAM.preemptionGBAM(link, lsp)==BAMStatus.aceita) lsp.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				assertEquals("Limite", BAMStatus.devolucao,BAM.preemptionGBAM(link, lsp2));
				BAM.devolutionG(link, lsp2);
				assertEquals("Limite", BAMStatus.aceita,BAM.preemptionGBAM(link, lsp2));
				if(BAM.preemptionGBAM(link, lsp2)==BAMStatus.aceita) lsp2.estabelecerLSP(roteadorOrigem.caminhos[0]);
				System.out.print(link.imprimirUtilizacaoGBAM());
				//System.out.print(Lsp.imprime_lista(link.ListaLSPs)+"\n - Carga:"+link.getCargaEnlaceAtual()+"\n");
				
	}
	@Test
	public void gerarRRDPNGpreempcao() throws IOException, RrdException
	{
		String filename= "1476799105354";
		EstatisticasDSTE e = new EstatisticasDSTE("a");
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(e.starTime,e.starTime+3600*24);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef.setTitle("Preempções Acumuladas");
		graphDef.datasource("preempcao", "saida/"+filename+"/"+filename+".rrd", "preempcao", "MAX");
		graphDef.line("preempcao", new Color(0xFF, 0, 0), "Preempção Total", e.graphWidthLine);
		graphDef.setWidth(e.graphWidth);
		graphDef.setHeight(e.graphHeight);
		graphDef.setLargeFont(e.graphLargeFont);
        graphDef.setSmallFont(e.graphSmallFont);
        graphDef.setTimeAxis(e.graphMinorUnit, e.graphMinorUnitCount,
        		e.graphMajorUnit, e.graphLabelUnitCount,
        		e.graphLabelUnit, e.graphLabelUnitCount,
        		e.graphLabelSpan, e.graphSimpleDateFormat);


		//Por tempo
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(e.starTime,e.starTime+3600*24);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef2.setTitle("Preempções x LSPs Geradas");
		graphDef2.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "LAST");
		graphDef2.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef2.area("lspGeradas", Color.GREEN, "LSPs Geradas");
		graphDef2.area("preempcao", Color.RED, "Preempções");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("preempcao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao_CT"+i, "LAST");
			graphDef2.line("preempcao_CT"+i, e.cores[i], "Preempcões em CT"+i, e.graphWidthLine);
			
		}
		graphDef2.setWidth(e.graphWidth);
		graphDef2.setHeight(e.graphHeight);
		graphDef2.setLargeFont(e.graphLargeFont);
        graphDef2.setSmallFont(e.graphSmallFont);
        graphDef2.setTimeAxis(e.graphMinorUnit, e.graphMinorUnitCount,
        		e.graphMajorUnit, e.graphLabelUnitCount,
        		e.graphLabelUnit, e.graphLabelUnitCount,
        		e.graphLabelSpan, e.graphSimpleDateFormat);
		
		//Percentual
		
		RrdGraphDef graphDef3 = new RrdGraphDef();
		graphDef3.setTimeSpan(e.starTime,e.starTime+3600*24);
		graphDef3.setMaxValue(100);
		graphDef3.setVerticalLabel("Percent");
		//graphDef.setMinValue(0);
		graphDef3.setTitle("Preempções");

		graphDef3.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "LAST");
		graphDef3.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef3.datasource("prempcoes", "preempcao,lspGeradas,/,100,*");
		graphDef3.area("prempcoes", Color.gray, "% Prempções");
		
		graphDef3.setWidth(e.graphWidth);
		graphDef3.setHeight(e.graphHeight);
		graphDef3.setLargeFont(e.graphLargeFont);
        graphDef3.setSmallFont(e.graphSmallFont);
        graphDef3.setTimeAxis(e.graphMinorUnit, e.graphMinorUnitCount,
        		e.graphMajorUnit, e.graphLabelUnitCount,
        		e.graphLabelUnit, e.graphLabelUnitCount,
        		e.graphLabelSpan, e.graphSimpleDateFormat);
		
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_prempt_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_prempt.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    
	    //Portempo
  		graph = new RrdGraph(graphDef3);
  		totalWidth=graph.getRrdGraphInfo().getWidth();
  	    totalHeight=graph.getRrdGraphInfo().getHeight();
  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
  	    gfx=img.getGraphics();
  	    graph.render(gfx);
  	    outputfile = new File("saida/"+filename+"/"+filename+"_prempt_percent.png");
  	    outputfile.mkdirs();
  	    ImageIO.write(img,"png",outputfile);


	    
	}

}
