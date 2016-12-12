package DSTE;
import Simulador.*;
import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import BAM.BAMRecommender.BAMDescription;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumCyclicDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;


public class ParametrosDSTE {
	//public static final int MaxInteracoes=50;
	public static final int MaxCaminhos = 20;
	//public static final int MaxOverlapFactor = 5;
	public static final int MaxSaltos = 10;
	//public static final int NumEnlacesFullDuplex = 12;
	public static final int MaxH = 0;
	public static final int MaxClassType = 3;
	public static int LINKS = 6; // NÔøΩmero de LINKS (Simplex) do Modelo
	public static int ROTEADORES = 5; // NÔøΩmero de roteadores DSTE
	public static BAMType BAMTypePadrao = BAMType.PreemptionGBAM;  //NoPreemptionMAM  //PreemptionAllocCTSharing  //PreemptionRDM
	public static final long Janela = 600;
	
	//SLAs em Percentual
	public static final long SLAPreempcoes = 10;
	public static final long SLADevolucoes = 5;
	public static final long SLABloqueios = 10;
	public static final long SLAUtilizacao = 80;
	public static final boolean RecomendacaoCBRSwitchBAM = true;
	public static final double RecomendacaoCBRLimiarDeCorte = 0.65;
	public static final long TempoSimulacao = 3600*5;//86400
	/*//////Dados do RRDTools
	 * DS:ds-name:{GAUGE | COUNTER | DERIVE | DCOUNTER | DDERIVE | ABSOLUTE}:heartbeat:min:max
	 * RRA:{AVERAGE | MIN | MAX | LAST}:xff:steps:rows
	 */
	public static final long RRDStarTime = 1451617200;  //start /// date +"%s"
	public static final long RRDAmostra = 30;  //step
	public static final long RRDBatida =RRDAmostra*2 ; //heartbeat
	public static final double RRDMin =Double.NaN;    //valor m√≠nimo fora dos quais n√£o deve ser considerada a leitura.
	public static final double RRDMax = Double.NaN;   //valor m√°ximo fora dos quais n√£o deve ser considerada a leitura.
	public static final double RRDXff = 0.5;   //percentagem de pontos prim√°rios que podem ser 'desconhecidos'
	public static final int RRDSteps = 2;  //N√∫mero de 'steps' que devemos esperar at√© armazenarmos no arquivo o valor da leitura
	public static final int RRDLinhas= (int) (TempoSimulacao/(RRDSteps*RRDAmostra));  //rows  quantas leituras vamos armazenar.
	
	public static final Boolean topologiaManual= false;
	public static final Boolean matrizCaminhosManual= false;
	//public static final String filenameTopologia= ".//topologias//NSF-14n-42e.txt";
	//public static final String filenameMatrizCaminhos= ".//topologias//NSF-14n-42e_Caminhos.txt";
	public static final String filenameTopologia= ".//topologias//PTP-2n-1e.txt";
	public static final String filenameMatrizCaminhos= ".//topologias//PTP-2n-1e_Caminhos.txt";
	
	public static void trafegoManual(RodadaDeSimulacao rodada,Topologia to, No dados)
	{
		
		//Inicializar Tr·fego
		if(dados==null)
		{
			for(int i=0; i<ParametrosDSTE.MaxClassType;i++)
			{
				int auxCT=i;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; //id do router fonte
				lsp.dest = 1; // id do router destino
				lsp.CT =auxCT;
				lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
				dados.item = lsp;
				Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - "
						+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
						+" -->"
						+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
				rodada.schedulep (3, 0.0, dados);
			}
		}
		else
		{
			//RepetiÁ„o do tr·fego
			Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - "
					+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
					+" -->"
					+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
			rodada.schedulep (1, 0.0, dados);
	
			
	
			
			int auxCT=((Lsp)dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; //id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT =auxCT;
			lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
			dados.item = lsp;
			
			double tempoDeVida=250;
			Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - R0 -->R4");
			if(rodada.simtime() <= 3600*1)
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			}else if (rodada.simtime() <= 3600*2)
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(3), dados);
				} 
				// 10.800
			}else if (rodada.simtime() <= 3600*3)
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(3), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				} 
			}
			else if (rodada.simtime() <= 3600*4)// 14.400
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(3), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			}else // 14.400
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(3), dados);
				}
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(3), dados);
				}
				if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(3), dados);
				}
			}

		}
			
		
		
	}
	//AleatÛrio
	public static void trafegoManual2(RodadaDeSimulacao rodada,Topologia to, No dados)
	{
		
		
		if(dados!=null)
		{
			Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - "
					+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
					+" -->"
					+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
			rodada.schedulep (1, 0.0, dados);
		}

		dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES-1); //id do router fonte
		do {
			lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES-1); // id do router destino
		} while (lsp.src==lsp.dest);
		//lsp.src = 0;
		//lsp.dest = 1;
		lsp.CT =GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType-1); 
		lsp.Carga = GeradorDeNumerosAleatorios.uniform(5,30);
		dados.item = lsp;
		Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - "
				+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
				+" -->"
				+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
		((Lsp)dados.item).tempoDeVida=GeradorDeNumerosAleatorios.expntl(300);
		if(rodada.simtime() <2000)
			rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(2), dados);
		else
			rodada.schedulep (3, GeradorDeNumerosAleatorios.expntl(20), dados);
			
		
		
	}
	public static void topologiaManual(Topologia t)
	{
		
		t.roteador[0]= new Roteador(); 
		t.roteador[0].ID = 0; //int
		t.roteador[0].Descricao = "R0";
		t.roteador[1]= new Roteador(); 
		t.roteador[1].ID = 1;
		t.roteador[1].Descricao = "R1";
		t.roteador[2]= new Roteador(); 
		t.roteador[2].ID = 2;
		t.roteador[2].Descricao = "R2";
		t.roteador[3]= new Roteador(); 
		t.roteador[3].ID = 3;
		t.roteador[3].Descricao = "R3";
		t.roteador[4]= new Roteador(); 
		t.roteador[4].ID = 4;
		t.roteador[4].Descricao = "R4";
				
		t.link[0]=new Link();
		t.link[0].Descricao = "R0->R4";
		t.link[0].ID = 0;
		t.link[0].CustoEnlace = 1;
		t.link[0].CargaEnlace = 1000; // 1 GB
		t.link[0].lsrSrc = t.roteador[0];
		t.link[0].lsrDest = t.roteador[4];
		
		t.link[1]=new Link();
		t.link[1].Descricao = "R0->R1";
		t.link[1].ID = 1;
		t.link[1].CustoEnlace = 1;
		t.link[1].CargaEnlace = 1000; // 1 GB
		t.link[1].lsrSrc = t.roteador[0];
		t.link[1].lsrDest = t.roteador[1];
		
		t.link[2]=new Link();
		t.link[2].Descricao = "R1->R4";
		t.link[2].ID = 2;
		t.link[2].CustoEnlace = 1;
		t.link[2].CargaEnlace = 1000; // 1 GB
		t.link[2].lsrSrc = t.roteador[1];
		t.link[2].lsrDest = t.roteador[4];
		
		t.link[3]=new Link();
		t.link[3].Descricao = "R0->R2";
		t.link[3].ID = 3;
		t.link[3].CustoEnlace = 1;
		t.link[3].CargaEnlace = 1000; // 1 GB
		t.link[3].lsrSrc = t.roteador[0];
		t.link[3].lsrDest = t.roteador[2];
		
		t.link[4]=new Link();
		t.link[4].Descricao = "R2->R3";
		t.link[4].ID = 4;
		t.link[4].CustoEnlace = 1;
		t.link[4].CargaEnlace = 1000; // 1 GB
		t.link[4].lsrSrc = t.roteador[2];
		t.link[4].lsrDest = t.roteador[3];
		
		t.link[5]=new Link();
		t.link[5].Descricao = "R3->R4";
		t.link[5].ID = 5;
		t.link[5].CustoEnlace = 1;
		t.link[5].CargaEnlace = 1000; // 1 GB
		t.link[5].lsrSrc = t.roteador[3];
		t.link[5].lsrDest = t.roteador[4];		
		

	}
	
	public static void matrizDeCaminhosManual(Topologia t)
	{
		
		// Matriz de caminhos R0 para R4 
		// Caminho [0] [0]
		// R0->R4
		t.adicionar(0,0,0); // roteadorID,  caminhoID,  linkID
		// Caminho [0] [1]
		// R0->R1
		t.adicionar(0,1,1);
		// R1->R4
		t.adicionar(0,1,2);
		// Caminho [0] [2]
		// R0->R2
		t.adicionar(0,2,3);
		// R2->R3
		t.adicionar(0,2,4);
		// R3->R4
		t.adicionar(0,2,5);

//Matriz de caminhos R1 para R4
		// Caminho [1] [0]
		// R1->R4
		t.adicionar(1,0,2);
						
//Matriz de caminhos R2 para R4 	
		// Caminho [2] [0]
		// R2->R3
		t.adicionar(2,0,4);
		// R3->R4
		t.adicionar(2,0,5);
					
//Matriz de caminhos R3 para R4
		// Caminho [3] [0]
		// R3->R4
		t.adicionar(3,0,5);
		
	}
	
	
	/*
	public static double [] BCPadrao= new double[]    // para AllocCTSharing  //PreemptionRDM
			{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)
				75, // BC[1] = CT1 + CT2
				40 // BC[1] = CT2
			};
			
			
	
	
*/	
	public static double [] BCPadrao= new double[]           //para MAN
			{	25, // BC[0] =CT0 (Valor do Enlace)
				35, // BC[1] = CT1
				40 // BC[2] =  CT2
			};
	
	public static double [] BCHTLPadrao= new double[]
			{	0, //BC0 Nunca mudar
				100, //BC1
				100 //BC2
			};
	
	public static double [] BCLTHPadrao= new double[]
			{	100, //BC0 
				100, //BC1
				0  //BC2 Nunca mudar
			};
		
	public static boolean condicaoDeParada(RodadaDeSimulacao rodada)
	{
		// rodada.simtime()<=36000
		//rodada.estatistica.lspRequested<=500
		if(rodada.simtime() <= TempoSimulacao) 
			return true;
		else
			return false;
	}
	public static String getParametros()
	{
		String retorno="============================ InÔøΩcio dos ParÔøΩmtros DSTE ============================\r\n";
		retorno+="MaxCaminhos:"+MaxCaminhos+"\r\n";
		retorno+="MaxSaltos:"+MaxSaltos+"\r\n";
		retorno+="MaxH:"+MaxH+"\r\n";
		retorno+="MaxClassType:"+MaxClassType+"\r\n";
		retorno+="LINKS:"+LINKS+"\r\n";
		retorno+="ROTEADORES:"+ROTEADORES+"\r\n";
		retorno+="BAMTypePadrao:"+BAMTypePadrao+"\r\n";
		for(int i=0;i<BCPadrao.length;i++){ 
			
			retorno+="BCPadrao["+i+"]:"+BCPadrao[i]+"\r\n"; 
			
		}   
		
		retorno+="============================ Fim dos ParÔøΩmtros DSTE ============================";

		
		
		return retorno;
		
	}
	
	public static NNConfig getSimilarityConfig()
	{
		NNConfig config = new NNConfig();
		Attribute attribute;
		
		LocalSimilarityFunction function;
		

		attribute = new Attribute("BAMAtual",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 100.0);

		attribute = new Attribute("problema",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 100.0);
		
		attribute = new Attribute("utilizacaoDoEnlaceCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		attribute = new Attribute("utilizacaoDoEnlaceCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		attribute = new Attribute("utilizacaoDoEnlaceCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);


		attribute = new Attribute("numeroDePreempcoesCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);


		attribute = new Attribute("numeroDePreempcoesCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		
		/*nunca existe esse valor
		attribute = new Attribute("numeroDePreempcoesCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 0.0);*/
		

		attribute = new Attribute("numeroDeBloqueiosCT0",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT1",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		
		/*nunca existe esse valor
		attribute = new Attribute("numeroDeDevolucoesCT0",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 0.0);*/
		
		
		attribute = new Attribute("numeroDeDevolucoesCT1",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);
		
		
		attribute = new Attribute("numeroDeDevolucoesCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 10.0);

		
		return config;
	}
	public static NNConfig getSimilarityConfig2()
	{
		NNConfig config = new NNConfig();
		Attribute attribute;
		
		LocalSimilarityFunction function;
		

		attribute = new Attribute("BAMAtual",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 100.0);

		attribute = new Attribute("problema",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 100.0);
		
		/*attribute = new Attribute("utilizacaoDoEnlace",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 9.0);*/


		attribute = new Attribute("numeroDePreempcoesCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 5.0);


		attribute = new Attribute("numeroDePreempcoesCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 6.0);
		

		attribute = new Attribute("numeroDePreempcoesCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 0.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT0",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 2.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT1",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 3.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 4.0);
		
		
		attribute = new Attribute("numeroDeDevolucoesCT0",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 0.0);
		
		
		attribute = new Attribute("numeroDeDevolucoesCT1",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 7.0);
		
		
		attribute = new Attribute("numeroDeDevolucoesCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 8.0);

		
		return config;
	}
	

}
