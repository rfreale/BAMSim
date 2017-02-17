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
	public static int LINKS = 6; // Número de LINKS (Simplex) do Modelo
	public static int ROTEADORES = 5; // Número de roteadores DSTE
	public static BAMType BAMTypePadrao = BAMType.PreemptionGBAM;  //NoPreemptionMAM  //PreemptionAllocCTSharing  //PreemptionRDM
	
	
	
	public static final String Gestor = "Conservador";
	//SLAs em Percentual
	
	public static final int []SLAUtilizacaoCT   = new int [] {35, 40, 45} ;  // ou SLA
	public static final int []SLABloqueiosCT    = new int [] {80, 70, 80} ;  // ou SLA
	public static final int []SLAPreempcoesCT   = new int [] {80, 80, 00} ;  // ou SLA
	public static final int []SLADevolucoesCT   = new int [] {00, 80, 80} ;  // ou SLA
	
	
	
	public static final int  Escada = 128; //limite da função de similaridade Threshold
	public static final long SLAPreempcoes = 10;
	public static final long SLADevolucoes = 5;
	public static final long SLABloqueios = 10;
	public static final long SLAUtilizacao = 80;
	public static final boolean RecomendacaoCBRSwitchBAM = false;
	public static final double RecomendacaoCBRLimiarDeCorte = 0.85;
	public static final long TempoSimulacao = 3600*3;
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
	
	
	
	
	public static final Boolean topologiaManual= false;
	public static final Boolean matrizCaminhosManual= false;
	//public static final String filenameTopologia= ".//topologias//NSF-14n-42e.txt";
	//public static final String filenameMatrizCaminhos= ".//topologias//NSF-14n-42e_Caminhos.txt";
	public static final String filenameTopologia= ".//topologias//PTP-2n-1e.txt";
	public static final String filenameMatrizCaminhos= ".//topologias//PTP-2n-1e_Caminhos.txt";
	
	
	public static void trafegoManual(RodadaDeSimulacao rodada,Topologia to, No dados)
	{
		
		//Inicializar Tráfego
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
				if (((Lsp)dados.item).CT==2){
					
					rodada.schedulep (3, 3600*0, dados);   ////CT2
					rodada.schedulep (3, 3600*15, dados);
					
				} else if (((Lsp)dados.item).CT==1){
					
					rodada.schedulep (3, 3600*4, dados); //CT1
					rodada.schedulep (3, 3600*10, dados);
					
				} else
				{
					rodada.schedulep (3, 3600*2, dados);  ////CT0
					rodada.schedulep (3, 3600*9, dados);
					rodada.schedulep (3, 3600*16, dados);
				}
			}
		}
		else
		{
			Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - "
					+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
					+" -->"
					+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
			rodada.schedulep (1, 0, dados);
			
			//Repetição do tráfego
			Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - "
					+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
					+" -->"
					+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
			int auxCT=((Lsp)dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; //id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT =auxCT;
			lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5,15);
			dados.item = lsp;
			
			double tempoDeVida=250;
			
			
			
			
			if (rodada.simtime() < 3600*1)   // Uma hora    de 0 a 1 hora
			{
				((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
							
				
			}else if (rodada.simtime() < 3600*2)  //   7200 Duas horas de 1 a 2 horas
			{
				
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);
								
			}
			else if (rodada.simtime() < 3600*3)  //  10800  Três horas de 2 a 3 horas
			{
				
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					
				}else if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);
				}	
				
			}else if (rodada.simtime() < 3600*4)// 14.400  de 3 a 4 horas
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}			
			}else  if (rodada.simtime() < 3600*5)  // 18000 de 4 a 5 horas
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
					
				}else if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}			
			}else  if (rodada.simtime() < 3600*6)  // de 5 a 6 horas
			{

				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}		
			}else  if (rodada.simtime() < 3600*7)  /// de 6 a 7 horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}	

			}else  if (rodada.simtime() < 3600*8)  /// de 7 as 8 horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}	
				
			}else  if (rodada.simtime() < 3600*9)  /////// de 8 as 9 horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{
				
				
				
			}else  if (rodada.simtime() < 3600*10)  /////// de 9 as 10horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}
				
				
			}else  if (rodada.simtime() < 3600*11)  /////// de 10 as 11  horas///////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}	
				
				
			}else  if (rodada.simtime() < 3600*12)  /////// de 11 as 12 horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{
				
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}	
				
				
				
			}else  if (rodada.simtime() < 3600*13)  //// de 12 as 13 horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				 if (((Lsp)dados.item).CT==0)
				{
						((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}else  if (((Lsp)dados.item).CT==1)
				{
						((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}	
				
				
			}else  if (rodada.simtime() < 3600*14)  //// de 13 as 14 horas///////////////////////////////////////////////////////////////////////////////////////////////////// 
			{
				if (((Lsp)dados.item).CT==1)
				{
						((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}	
				
			}else  if (rodada.simtime() < 3600*15)  //// de 14 as 15 horas///////////////////////////////////////////////////////////////////////////////////////////////////// 
			{
				if (((Lsp)dados.item).CT==1)
				{
						((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}
				
				
			}else  if (rodada.simtime() < 3600*16)  /// de 15 as  16 horas///////////////////////////////////////////////////////////////////////////////////////////////////// 
			{
				if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}else if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
				
				
			}else  if (rodada.simtime() < 3600*17)  /// de 16 as 17 horas///////////////////////////////////////////////////////////////////////////////////////////////////// 
			{
				
				if (((Lsp)dados.item).CT==0)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}else if (((Lsp)dados.item).CT==1)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}else if (((Lsp)dados.item).CT==2)
				{
					((Lsp)dados.item).tempoDeVida= (int)GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}
				
			}
		
			
			

		}
		
	}
	
	
	
	
	
	
	//Aleatório1
		public static void trafegoManualA(RodadaDeSimulacao rodada,Topologia to, No dados)
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
		
		
		
		//Aleatório2
				public static void trafegoManualB(RodadaDeSimulacao rodada,Topologia to, No dados)
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
					lsp.Carga = GeradorDeNumerosAleatorios.uniform(50,150);
					dados.item = lsp;
					Debug.setMensagem("Cria LSP "+((Lsp)dados.item).ID+" - "
							+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
							+" -->"
							+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
					
					
					
					if (((Lsp)dados.item).CT==0)
					{
						((Lsp)dados.item).tempoDeVida=850;
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.poisson(2), dados);
						
					}else if (((Lsp)dados.item).CT==1)
					{
						((Lsp)dados.item).tempoDeVida=850;
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.poisson(15), dados);
						
					}else if (((Lsp)dados.item).CT==2)
					{
						((Lsp)dados.item).tempoDeVida=850;
						rodada.schedulep (3, (int) GeradorDeNumerosAleatorios.poisson(1), dados);
						
					}
					
					
				}

				
				
				
				
				//Aleatório3
				public static void trafegoManualC(RodadaDeSimulacao rodada,Topologia to, No dados)
				{
					
					if(dados==null)
					{
						
									
						

						int lambda = 24;
						int numeroDeLSPs[] = new int[lambda*3];
						
						for (int i=0;i<3600*3;i++)
						{
							int temp=GeradorDeNumerosAleatorios.poisson(lambda);
							
							if(temp<lambda*3)
								
								++numeroDeLSPs[temp];
							
							else
								System.out.println("erro="+temp);


						}
						
						
											
						
						
							dados = new No();
							Lsp lsp = new Lsp(rodada);
							lsp.CargaReduzida = 0;
							lsp.src = 0; //id do router fonte
							lsp.dest = 1; // id do router destino
							lsp.CT =2;
							lsp.Carga = (int)GeradorDeNumerosAleatorios.uniform(5,15);
							dados.item = lsp;
							Debug.setMensagem("Agenda estabelecimento da LSP "+((Lsp)dados.item).ID+" - "
									+ to.getRoteador(((Lsp)dados.item).src).getDescricao()
									+" -->"
									+ to.getRoteador(((Lsp)dados.item).dest).getDescricao());
							
							/*if (((Lsp)dados.item).CT==2){
								
								rodada.schedulep (3, 3600*0, dados);   ////CT2
								rodada.schedulep (3, 3600*15, dados);
								
							} else if (((Lsp)dados.item).CT==1){
								
								rodada.schedulep (3, 3600*4, dados); //CT1
								rodada.schedulep (3, 3600*10, dados);
								
							} else
							{
								rodada.schedulep (3, 3600*2, dados);  ////CT0
								rodada.schedulep (3, 3600*9, dados);
								rodada.schedulep (3, 3600*16, dados);
							}*/
							
							for (int i = 0; i < numeroDeLSPs.length; i++) {
								
							    for (int j=0; j < numeroDeLSPs[i]; j++) {
							    	rodada.schedulep (3, 3600*numeroDeLSPs[i], dados);
							    }
								  
							  }
						
					}
					
					
					
					
					
					
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
			{	20, // BC[0] =CT0 (Valor do Enlace)
				30, // BC[1] = CT1
				50 // BC[2] =  CT2
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
		
		LocalSimilarityFunction function;
		

		attribute = new Attribute("BAMAtual",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("gestor",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 1.0);

		/*attribute = new Attribute("problema",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 100.0);*/
		
		attribute = new Attribute("janela",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 1.0);
		
		/*attribute = new Attribute("SLAUtilizacaoCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("SLAUtilizacaoCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("SLAUtilizacaoCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
				
		attribute = new Attribute("SLABloqueiosCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("SLABloqueiosCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("SLABloqueiosCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
				
		attribute = new Attribute("SLAPreempcoesCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("SLAPreempcoesCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);*/
		
		/*attribute = new Attribute("SLAPreempcoesCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);*/
		
		
		/*attribute = new Attribute("SLADevolucoesCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);*/
	/*			
		attribute = new Attribute("SLADevolucoesCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("SLADevolucoesCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 1.0);*/
		
				
		attribute = new Attribute("BC0",BAMDescription.class); 
		config.addMapping(attribute, new Threshold(Escada));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("BC1",BAMDescription.class); 
		config.addMapping(attribute, new Threshold(Escada));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("BC2",BAMDescription.class); 
		config.addMapping(attribute, new Threshold(Escada));
		config.setWeight(attribute, 1.0);
		
				
		attribute = new Attribute("utilizacaoDoEnlaceCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("utilizacaoDoEnlaceCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		
		attribute = new Attribute("utilizacaoDoEnlaceCT2",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT0",BAMDescription.class);
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT1",BAMDescription.class);
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		

		attribute = new Attribute("numeroDeBloqueiosCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		
				
		attribute = new Attribute("numeroDePreempcoesCT0",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);

		attribute = new Attribute("numeroDePreempcoesCT1",BAMDescription.class); 
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		
		/*nunca existe esse valor
		attribute = new Attribute("numeroDePreempcoesCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 0.0);*/
		
		
		/*nunca existe esse valor
		attribute = new Attribute("numeroDeDevolucoesCT0",BAMDescription.class);
		config.addMapping(attribute, new Interval(100));
		config.setWeight(attribute, 0.0);*/
		
		
		attribute = new Attribute("numeroDeDevolucoesCT1",BAMDescription.class);
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);
		
		
		attribute = new Attribute("numeroDeDevolucoesCT2",BAMDescription.class);
		config.addMapping(attribute, new Interval(1));
		config.setWeight(attribute, 1.0);

		
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

		/*attribute = new Attribute("problema",BAMDescription.class); 
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 100.0);*/
		
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
