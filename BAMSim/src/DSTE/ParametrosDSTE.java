package DSTE;
import BAM.BAMRecommender.BAMDescription;
import Simulador.No;
import Simulador.RodadaDeSimulacao;
import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.IntervalEqual;


public class ParametrosDSTE {
	//public static final int MaxInteracoes=50;
	public static final int MaxCaminhos = 20;
	//public static final int MaxOverlapFactor = 5;
	public static final int MaxSaltos = 10;
	//public static final int NumEnlacesFullDuplex = 12;
	public static final int MaxH = 0;
	public static final int MaxClassType = 3;
	public static int LINKS = 6; // Número de LINKS (Simplex) do Modelo
	public static int ROTEADORES = 5; // Número de roteadores DSTE
	public static BAMType BAMTypePadrao = BAMType.PreemptionGBAM;  //NoPreemptionMAM  //PreemptionAllocCTSharing  //PreemptionRDM
	
	
	
	public static final String Gestor = "Conservador";
	//SLAs em Percentual
	
	/*public static final double []SLAUtilizacaoCT   = new double [] {35, 40, 45} ;  // ou SLA
	public static final double []SLABloqueiosCT    = new double [] {80, 70, 80} ;  // ou SLA
	public static final double []SLAPreempcoesCT   = new double [] {80, 80, 00} ;  // ou SLA
	public static final double []SLADevolucoesCT   = new double [] {00, 80, 80} ;  // ou SLA
*/	
	
	
	public static final int  Escada = 128; //limite da função de similaridade Threshold
	public static final double SLAPreempcoes = 0.00;
	public static final double SLADevolucoes = 0.00;
	public static final double SLABloqueios = 0.00;  ////0.25
	public static final double SLAUtilizacao = 0.00;
	public static final long TempoSimulacao = 3600*24;

	/*//////Dados do RRDTools
	 * DS:ds-name:{GAUGE | COUNTER | DERIVE | DCOUNTER | DDERIVE | ABSOLUTE}:heartbeat:min:max
	 * RRA:{AVERAGE | MIN | MAX | LAST}:xff:steps:rows
	 */
	public static final long RRDStarTime = 1483239600;  //start /// date +"%s"
	//public static final long RRDAmostra = 30;  //step
	public static final long RRDBatida =60 ; //heartbeat
	public static final double RRDMin =Double.NaN;    //valor mínimo fora dos quais não deve ser considerada a leitura.
	public static final double RRDMax = Double.NaN;   //valor máximo fora dos quais não deve ser considerada a leitura.
	public static final double RRDXff = 0.5;   //percentagem de pontos primÃ¡rios que podem ser 'desconhecidos'
	public static final int RRDSteps = 1;  //Número de 'steps' que devemos esperar até armazenarmos no arquivo o valor da leitura
	public static final int RRDLinhas= (int) (TempoSimulacao/(RRDSteps*RRDBatida));  //rows  quantas leituras vamos armazenar.
	
	
	
	
	public static final long Janela = RRDBatida *5 ;
	
	
	
	
	public static final boolean RecomendacaoCBRSwitchBAM = true;
	public static final boolean RecomendacaoCBRRetencao = true ;
	public static final boolean RecomendacaoCBRIndexarBloPreDev = true;
	public static final double RecomendacaoCBRLimiarDeCorte = 0.97;
	public static final double RecomendacaoCBRLimiarDeCorte2 = 0.97;
	public static final double RecomendacaoCBRLimiarArmazenar = 0.99;    // Silimalidade para armazenar um novo vaso na base de casos positiva ou negativa (o caso não pode ser 0.99x semelhandte a algum caso ja existente)
	public static final String filenameBaseCBRP= ".//basesCBR//baseP.sql";
	public static final String filenameBaseCBRN= ".//basesCBR//baseN.sql";
	
	
	
	public static final Boolean baseCBRManual= true;
	public static final Boolean topologiaManual= false;
	public static final Boolean matrizCaminhosManual= false;
	public static final String filenameTopologia= ".//topologias//PTP-2n-1e.txt";
	public static final String filenameMatrizCaminhos= ".//topologias//PTP-2n-1e_Caminhos.txt";
	//public static final String filenameTopologia= ".//topologias//NSF-14n-42e.txt";
	//public static final String filenameMatrizCaminhos= ".//topologias//NSF-14n-42e_Caminhos.txt";
	//public static final String filenameTopologia= ".//topologias//PTP-3n-2e.txt";
	//public static final String filenameMatrizCaminhos= ".//topologias//PTP-3n-2e_Caminhos.txt";
	
	
	public static void trafegoManual(RodadaDeSimulacao rodada,Topologia to, No dados)
	{

		//GeradorDeTrafego.trafegoPoisson(rodada, to, dados);
		//GeradorDeTrafego.trafegoAleatorio(rodada, to, dados);
		//GeradorDeTrafego.trafegoDeterministico2(rodada, to, dados);
		//GeradorDeTrafego.trafegoDeterministico(rodada, to, dados);
		GeradorDeTrafego.trafegoForcado(rodada, to, dados);
	}
	
	/*	public static double [] BCPadrao= new double[]    // para AllocCTSharing  //PreemptionRDM			{	100, // BC[0] =CT0 + CT1 + CT2 (Valor do Enlace)				75, // BC[1] = CT1 + CT2				40 // BC[1] = CT2			};
*/	
	public static double [] BCPadrao= new double[]           //para MAN 
			{	40, // BC[0] =CT0 (Valor do Enlace)
				35, // BC[1] = CT1
				25 // BC[2] =  CT2
			};
	
	public static double [] BCHTLPadrao= new double[]
			{	0,//BC0 Nunca mudar
				
				100, //BC1
				100 //BC2
			};
	
	public static double [] BCLTHPadrao= new double[]
			{	100, //BC0 
				100, //BC1
				
				0//BC2 Nunca mudar
			};
	

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
		String retorno="============================ Início dos Parâmetros DSTE ============================\r\n";
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
		
		retorno+="============================ Fim dos Parâmetros DSTE ============================";

		
		
		return retorno;
		
	}
	
	public static NNConfig getSimilarityConfig()   ////////////criar a função de similaridade  colocar novos campos.
	{
		NNConfig config = new NNConfig();
		Attribute attribute;
		
		//LocalSimilarityFunction function;
		

		attribute = new Attribute("BAMAtual",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 40.0);
		
				
		/*attribute = new Attribute("BC0",BAMDescription.class); 
		config.addMapping(attribute, new Threshold(Escada));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("BC1",BAMDescription.class); 
		config.addMapping(attribute, new Threshold(Escada));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("BC2",BAMDescription.class); 
		config.addMapping(attribute, new Threshold(Escada));
		config.setWeight(attribute, 1.0);*/
		
				
		attribute = new Attribute("utilizacaoDoEnlace",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 30.0);
		
	/*	attribute = new Attribute("utilizacaoDoEnlaceCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 30.0);
		
		attribute = new Attribute("utilizacaoDoEnlaceCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 30.0);
		
		attribute = new Attribute("utilizacaoDoEnlaceCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 30.0);*/
		
		if(ParametrosDSTE.RecomendacaoCBRIndexarBloPreDev)
		{
			attribute = new Attribute("numeroDeBloqueios",BAMDescription.class);
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 20.0);
			
			/*attribute = new Attribute("numeroDeBloqueiosCT0",BAMDescription.class);
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 20.0);
			
	
			attribute = new Attribute("numeroDeBloqueiosCT1",BAMDescription.class);
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 20.0);
			
	
			attribute = new Attribute("numeroDeBloqueiosCT2",BAMDescription.class);
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 20.0);*/
			
					
			attribute = new Attribute("numeroDePreempcoes",BAMDescription.class); 
			config.addMapping(attribute, new IntervalEqual(1));
			config.setWeight(attribute, 20.0);
	
			/*attribute = new Attribute("numeroDePreempcoesCT0",BAMDescription.class); 
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 10.0);
	
			attribute = new Attribute("numeroDePreempcoesCT1",BAMDescription.class); 
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 10.0);*/
			
			/*nunca existe esse valor
			attribute = new Attribute("numeroDePreempcoesCT2",BAMDescription.class);
			config.addMapping(attribute, new Interval(100));
			config.setWeight(attribute, 0.0);*/
			
			
			/*nunca existe esse valor
			attribute = new Attribute("numeroDeDevolucoesCT0",BAMDescription.class);
			config.addMapping(attribute, new Interval(100));
			config.setWeight(attribute, 0.0);*/
			
			
			attribute = new Attribute("numeroDeDevolucoes",BAMDescription.class);
			config.addMapping(attribute, new IntervalEqual(1));
			config.setWeight(attribute, 20.0);
			
			/*attribute = new Attribute("numeroDeDevolucoesCT1",BAMDescription.class);
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 10.0);
			
			attribute = new Attribute("numeroDeDevolucoesCT2",BAMDescription.class);
			config.addMapping(attribute, new Interval(1));
			config.setWeight(attribute, 10.0);*/
		}

		
		return config;
	}
	
}
