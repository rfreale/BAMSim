package DSTE;

import Simulador.Debug;
import Simulador.No;
import Simulador.ParametrosDoSimulador;

/* o link entre dois roteadores atende de maneira uniforme os
os pacotes que chegam a ele (uniforme em funcao da taxa de
transmissão e tempo de propagação) */
public class Link {
	//Lista Enlaces
	    public double CargaEnlace; // Valor do Físico do Enlace em bps
		private double CargaEnlaceAtual=0; // Carga de Tráfego Atual ocupada do Enlace em bps
		public double CargaCTAtual[] = new double [ParametrosDSTE.MaxClassType]; // Carga de Tráfego Atual ocupada do Enlace em bps
		public String Descricao; // Descrição do Enlace. Cada enlace está associado a uma letra do alfabeto
		int ID; // Descrição numérica para o enlace
		int CustoEnlace; // Custo do Enlace
		Roteador lsrSrc; // Nó origem do Enlace
		Roteador lsrDest; // nó destino do Enlace
		public double [] BC = ParametrosDSTE.BCPadrao;
		public double [] BCHTL = ParametrosDSTE.BCHTLPadrao;
		public double [] BCLTH = ParametrosDSTE.BCLTHPadrao;
		public BAMType bamType = ParametrosDSTE.BAMTypePadrao;

		//Total de LSP por CT da BC
		double BCAtual[] = new double [ParametrosDSTE.MaxClassType];

		//LSPs Associadas ao link
		public Lista ListaLSPs = new Lista();
		//LSPs por CTs Associadas ao link
		public Lista ListaLSPsPorCT[] = new Lista[ParametrosDSTE.MaxClassType];
		
		//Estatísticas
		/*double BandaPreemptada =0; // Variável que contém a Banda Preemptada atual no Enlace, se preempção for necessária
		double TotalBandaPreemptada = 0; // Variável com o total de banda preemptada de cada enlace para computação das estatísticas finais
		double BandaPreemptadaCT[] = new double[ParametrosDSTE.MaxClassType];*/
		
		//como computar bloqueio e requested para o enlace, como?
		public int bloqueios = 0;
		public int preempcoes = 0;
		public int devolucoes = 0;
		//Quantas LSP Tem estabelecida no momento
		public int lspEstablished = 0;
		//Qualtas LSP foram estabelecidas no enlace até o momento
		public int lspEstablishedTotal = 0;
		public int lspUnbroken = 0;
		public int lspRequested = 0;
		public double bandaUnbroken = 0;
		public double bandaRequested = 0;

		public int [] lspRequestedCT = new int [ParametrosDSTE.MaxClassType];
		public double [] bandaRequestedCT = new double [ParametrosDSTE.MaxClassType];
		public int [] lspUnbrokenCT = new int [ParametrosDSTE.MaxClassType];
		public int [] lspEstablishedCT = new int [ParametrosDSTE.MaxClassType];
		public int [] lspEstablishedTotalCT = new int [ParametrosDSTE.MaxClassType];
		public int [] bloqueiosCT = new int [ParametrosDSTE.MaxClassType];
		public int [] preempcoesCT = new int [ParametrosDSTE.MaxClassType];
		public int [] devolucoesCT = new int [ParametrosDSTE.MaxClassType];

		
		Link()
		{
	        for (int i = 0; i < ListaLSPsPorCT.length; i++) {  
	              
	        	ListaLSPsPorCT[i]=new Lista();
	        	CargaCTAtual[i]=0;
	        }  
		}

		
		
		
		public int getID() {
			return ID;
		}




		public void setID(int iD) {
			ID = iD;
		}




		public BAMStatus checkBAM(Lsp lsp)
		{
			
			switch(this.bamType)
			{
				case NoPreemptionBestEffort:
				return BAM.NoPreemptionBestEffort(this, lsp);
				
				case NoPreemptionMAM:
					return BAM.NoPreemptionMAM(this, lsp);
				
				case NoPreemptionRDM:
					return BAM.NoPreemptionRDM(this, lsp);
					
				case NoPreemptionAllocCTSharing:
					return BAM.NoPreemptionAllocCTSharing(this, lsp);
					
				case PreemptionRDM:
					return BAM.preemptionRDM(this, lsp);
					
				case PreemptionAllocCTSharing:
					return BAM.preemptionAllocCTSharing(this, lsp);
				case PreemptionGBAM:
					return BAM.preemptionGBAM(this, lsp);
				default:
	                System.out.println("BAM não implementado!");
	                break;
					
			
			}
			
			return BAMStatus.bloqueada;
			
			
		}
			
		
		public double CargaResidual() // Carga de Tráfego não utilizada no Enlace (CargaEnlace - CargaEnlaceAtual)
		{
			return  CargaEnlace - CargaEnlaceAtual;
		}
		

		public double getCargaEnlaceAtual()
		{
			
			return this.CargaEnlaceAtual;
		}
		
		public void insereLsp(Lsp lsp)
		{
			Debug.setMensagem("------- "+this.Descricao+" -------",7,7);
			Debug.setMensagem("Estabelece LSP"+lsp.ID+":"+lsp.Carga+"("+lsp.src+"-"+lsp.dest+")(CT="+lsp.CT+")",7,7);
			if(this.bamType==BAMType.PreemptionGBAM)
			{
				Debug.setMensagem("------- Configurado -------",8,8);
				Debug.setMensagem(imprimirResumoGBAM(),8,8);
			}
			
				
			Debug.setMensagem("------- Status Antes -------",8,8);
			if(this.bamType!=BAMType.PreemptionGBAM)
				Debug.setMensagem(imprimirStatusBC(),8,8);
			else
				Debug.setMensagem(imprimirUtilizacaoGBAM(),8,8);
			Lista.insere_lista(lsp, ListaLSPs);
			Lista.insere_lista(lsp, ListaLSPsPorCT[lsp.CT]);
			CargaEnlaceAtual+=lsp.Carga;
			

			CargaCTAtual[lsp.CT]=CargaCTAtual[lsp.CT]+lsp.Carga;
			BCAtual[lsp.CT]=BCAtual[lsp.CT]+lsp.Carga; 
			Debug.setMensagem("------- Status Depois -------",8,8);
			if(this.bamType!=BAMType.PreemptionGBAM)
				Debug.setMensagem(imprimirStatusBC(),8,8);
			else
				Debug.setMensagem(imprimirUtilizacaoGBAM(),8,8);
		}
		
		public No removeLsp(Lsp lsp)
		{ 
			No retorno;
			Debug.setMensagem("------- "+this.Descricao+" -------",7,7);
			Debug.setMensagem("Remove LSP"+lsp.ID+":"+lsp.Carga+"("+lsp.src+"-"+lsp.dest+")(CT="+lsp.CT+")",7,7);
			if(this.bamType==BAMType.PreemptionGBAM)
			{
				Debug.setMensagem("------- Configurado -------",8,8);
				Debug.setMensagem(imprimirResumoGBAM(),8,8);
			}
			if(this.bamType==BAMType.PreemptionGBAM)
				imprimirResumoGBAM();
			Debug.setMensagem("------- Status Antes -------",8,8);
			if(this.bamType!=BAMType.PreemptionGBAM)
				Debug.setMensagem(imprimirStatusBC(),8,8);
			else
				Debug.setMensagem(imprimirUtilizacaoGBAM(),8,8);
			retorno=Lista.remove_lista(lsp, this.ListaLSPs);
			Lista.remove_lista(lsp, ListaLSPsPorCT[lsp.CT]);
			CargaEnlaceAtual-=lsp.Carga;
			

			
			BCAtual[lsp.CT]=BCAtual[lsp.CT]-lsp.Carga;
			CargaCTAtual[lsp.CT]=CargaCTAtual[lsp.CT]-lsp.Carga;
			Debug.setMensagem("------- Status Depois -------",8,8);
			if(this.bamType!=BAMType.PreemptionGBAM)
				Debug.setMensagem(imprimirStatusBC(),8,8);
			else
				Debug.setMensagem(imprimirUtilizacaoGBAM(),8,8);
			
			this.lspEstablished--;
			this.lspEstablishedCT[lsp.CT]--;
			
			return(retorno);		
			
		}
		//Total de LSPs acumulado conforme RDM
		public double BCAcumulado(int BC)
		{
			double BCAcumulado=0;
			for(int c=ParametrosDSTE.MaxClassType-1; c>=BC; c--)
			{
				BCAcumulado+=this.CargaCTAtual[c];
				
			}
			return BCAcumulado;
			
		}
				
		public double [] emprestimos()
		{
			double emprestimos[] = new double [ParametrosDSTE.MaxClassType];
			for(int r=ParametrosDSTE.MaxClassType-1;r>=0;r--)
			{
				if (r==ParametrosDSTE.MaxClassType-1)
					emprestimos[r]=((this.BC[r]/this.BC[0])*this.CargaEnlace)-(this.BCAcumulado(r))<0?(this.BCAcumulado(r))-((this.BC[r]/this.BC[0])*this.CargaEnlace):0;
				else if (r==0)
					emprestimos[r]=0;
				else
					emprestimos[r]=((this.BC[r]/this.BC[0])*this.CargaEnlace)-(this.BCAcumulado(r)-emprestimos[r+1])<0?(this.BCAcumulado(r)-emprestimos[r+1])-((this.BC[r]/this.BC[0])*this.CargaEnlace):0;
			}
			return emprestimos;
			
		}
		public double emprestimo(int BC)
		{
			

			return emprestimos()[BC];
			
		}
		public double emprestimoCTSuperiores(int CT)
		{
			double emprestimoCTSuperiores=0;
			for(int r=ParametrosDSTE.MaxClassType-1;r>CT;r--)
				emprestimoCTSuperiores+=this.emprestimo(r);
			return emprestimoCTSuperiores;
					
		}
		public double totalFreeBandWithDebt(int CT)
		{
			double TotalFreeBandWithDebt = 0;
			
			if(this.BCAcumulado(CT)-emprestimoCTSuperiores(CT) > (this.BC[CT]/this.BC[0])*this.CargaEnlace)
				//Tendo emprestimo deve contar apenas com emprestimos de menor prioridade + banda livre
				{
				 double emprestimo=0;
					//Soma emprestimos de menor prioridade
					for(int r=CT-1;r>=0;r--)
						emprestimo+=this.emprestimo(r);
					//Efetua cálculo do emprestimos de menor prioridade + banda livre
					TotalFreeBandWithDebt = this.CargaEnlace - this.getCargaEnlaceAtual() + emprestimo;
				}
				else
				//Não tendo emprestimo somar todos emprestimos + banda livre
				{
					double naoUtilizadoBC=0;
					double emprestimo=0;
					//Soma de todos emprestimos
					//for(int r=ParametrosDSTE.MaxClassType-1;r>=0;r--)
						//emprestimo+=this.emprestimo(r);
					emprestimo+=this.emprestimoCTSuperiores(0);
					//Efetua cálculo do emprestimos + banda livre
					TotalFreeBandWithDebt = this.CargaEnlace - this.getCargaEnlaceAtual() + emprestimo;

					//Existe a necessidade de verificar se o disponível para o BC é maior que emprestimos + banda livre
					//Essa necessidade é devido as prioridades da CT hierarquicamente mais altas
					//Essa condição implicará em preempção
					//Efetua cálculo do emprestimo + Não utilizado pelo BC
					naoUtilizadoBC = ((this.BC[CT]/this.BC[0])*this.CargaEnlace) - this.BCAcumulado(CT);
					if(naoUtilizadoBC>TotalFreeBandWithDebt)
						TotalFreeBandWithDebt=naoUtilizadoBC;
				}
			return TotalFreeBandWithDebt;
			
		}
		public String imprimirStatusBC()
		{
			String retorno="";
			for(int r=ParametrosDSTE.MaxClassType-1;r>=0;r--)
			{
				retorno+=String.format("Configurado[%d]= %6.2f|Emp.[%d]= %6.2f|Emp. SUP [%d]= %6.2f|Disp. Real[%d]= %6.2f|Acumulado[%d]= %6.2f|Total CT[%d]= %6.2f\r\n",r,(this.BC[r]*this.CargaEnlace/100),r,this.emprestimo(r),r,this.emprestimoCTSuperiores(r),r,this.totalFreeBandWithDebt(r),r,this.BCAcumulado(r),r,this.CargaCTAtual[r]);
				
			}
			return retorno;
		}
		public String imprimirResumoGBAM()
		{
			String retorno="";
			retorno+=("=====================================================================================================\r\n");
			retorno+=("||BC| BC%  |BC(Mbps)|| HTL% |HTL(Mbps)|| LTH% |LTH(Mbps)||Máx. Comp.||Privado||HTL Acum.||LTH Acum.||\r\n");
			for (int i=0; i<this.BC.length;i++){
				retorno+=(String.format("|| %d|%6.2f|%8.2f|",i,this.BC[i],this.BCMbps(i)));
				retorno+=(String.format("|%6.2f|%9.2f|",this.BCHTL[i],this.HTLMbps(i)));
				retorno+=(String.format("|%6.2f|%9.2f|",this.BCLTH[i],this.LTHMbps(i)));
				retorno+=(String.format("|%10.2f|",this.maxCompartilhadoGBAM(i)));
				retorno+=(String.format("|%7.2f|",this.privado(i)));
				retorno+=(String.format("|%9.2f|",this.HTLAcumulado(i)));
				retorno+=(String.format("|%9.2f||",this.LTHAcumulado(i)));
				retorno+=("\r\n");
			}
			retorno+=("=====================================================================================================\r\n");
			return retorno;
		}
		public String imprimirUtilizacaoGBAM()
		{
			String retorno="";
			retorno+=("=================================================================================================================\r\n");
			retorno+=("||BC| BC%  |BC(Mbps)|| HTL% |HTL(Mbps)|| LTH% |LTH(Mbps)||Máx. Comp.||Privado||HTL Acum.||LTH Acum.||Exce. Sup.||\r\n");
			for (int i=0; i<this.BC.length;i++){
				retorno+=(String.format("|| %d|%6.2f|%8.2f|",i,(this.BCAtual[i]/this.BCMbps(i)*100),this.BCAtual[i]));
				retorno+=(String.format("|%6.2f|%9.2f|",this.HTLMbpsCompartilhavel(i)/this.HTLMbps(i)*100,this.HTLMbpsCompartilhavel(i)));
				retorno+=(String.format("|%6.2f|%9.2f|",this.LTHMbpsCompartilhavel(i)/this.LTHMbps(i)*100,this.LTHMbpsCompartilhavel(i)));
				retorno+=(String.format("|%10.2f|",this.maxCompartilhavelGBAM(i)));
				retorno+=(String.format("|%7.2f|",this.privadoDisponivel(i)));
				retorno+=(String.format("|%9.2f|",this.HTLAcumuladoCompartilhavel(i)));
				retorno+=(String.format("|%9.2f|",this.LTHAcumuladoCompartilhavel(i)));
				retorno+=(String.format("|%10.2f||",this.excedenteBCSuperiores(i)));
				retorno+=("\r\n");
			}
			retorno+=("=================================================================================================================\r\n");
			return retorno;
		}
		public String imprimirConsolidadoGBAM()
		{
			String retorno="";
			retorno+=("====================================================================================================================================================================================\r\n");
			retorno+=("||BC| BC%  |BC(Mbps)|| HTL% |HTL(Mbps)|| LTH% |LTH(Mbps)||Utilização||Disp. BC.||Excedente||Disp. HTL||Disp. LTH||Pegou CT0||Pegou CT1||Pegou CT2||Emprestando HTL||Emprestando LTH||\r\n");
			for (int i=0; i<this.BC.length;i++){
				retorno+=(String.format("|| %d|%6.2f|%8.2f|",i,this.BC[i],this.BCMbps(i)));
				retorno+=(String.format("|%6.2f|%9.2f|",this.BCHTL[i],this.HTLMbps(i)));
				retorno+=(String.format("|%6.2f|%9.2f|",this.BCLTH[i],this.LTHMbps(i)));
				retorno+=(String.format("|%10.2f|",this.BCAtual[i]));
				retorno+=(String.format("|%9.2f|",bandaDisponivelNaBC(i)));
				retorno+=(String.format("|%9.2f|",bandaExcedenteDaBC(i)));
				//double dispHTL=bandaDisponivelNaBC(i)>this.HTLMbps(i)?this.HTLMbps(i):bandaDisponivelNaBC(i);
				retorno+=(String.format("|%9.2f|",HTLMbpsCompartilhavel(i)));
				//double dispLTH=bandaDisponivelNaBC(i)>this.LTHMbps(i)?this.LTHMbps(i):bandaDisponivelNaBC(i);
				retorno+=(String.format("|%9.2f|",LTHMbpsCompartilhavel(i)));
				
				retorno+=(String.format("|%9.2f|",bandaEmprestadaDeCTxParaCTy(0,i)));
				retorno+=(String.format("|%9.2f|",bandaEmprestadaDeCTxParaCTy(1,i)));
				retorno+=(String.format("|%9.2f|",bandaEmprestadaDeCTxParaCTy(2,i)));
				
				retorno+=(String.format("|%15.2f|",bandaEmprestadaDaCTPorHTL(i)));
				retorno+=(String.format("|%15.2f||",bandaEmprestadaDaCTPorLTH(i)));
				
				retorno+=("\r\n");
			}
			retorno+=("=============================================================================================================================================================================================\r\n");
			return retorno;
		}
		public double bandaEmprestadaDaCTPorHTL(int daCTx)
		{
			double retorno=0;
			for (int i=0;i<daCTx;i++)
			{
				retorno+=bandaEmprestadaDeCTxParaCTy(daCTx, i);
			}
			return retorno;
		}
		public double bandaEmprestadaDaCTPorLTH(int daCTx)
		{
			double retorno=0;
			for (int i=ParametrosDSTE.MaxClassType-1;i>daCTx;i--)
			{
				retorno+=bandaEmprestadaDeCTxParaCTy(daCTx, i);
			}
			return retorno;
		}
		public double bandaEmprestadaDeCTxParaCTy(int deCTx, int paraCTy)
		{
			double retorno=0;
			double MbpsCompartilhavel=0;
			//Não pega banda emprestada de sí mesma, então retorna 0
			//deCTx<0 permite a recursividade
			if (deCTx==paraCTy||deCTx<0)
			{
				return 0;
			} 
			//
			if (paraCTy>deCTx)
			{
				MbpsCompartilhavel=LTHMbpsCompartilhavel(deCTx);
			}
			else
			{
				MbpsCompartilhavel=HTLMbpsCompartilhavel(deCTx);
			}
			
			if ((bandaExcedenteDaBC(paraCTy)-bandaEmprestadaDeCTxParaCTy(deCTx-1, paraCTy)-bandaEmprestadaDeCTxParaCTy(deCTx-2, paraCTy)>0)&&(MbpsCompartilhavel>0))
			{
					if(bandaExcedenteDaBC(paraCTy)>MbpsCompartilhavel)
					{
						retorno=MbpsCompartilhavel;
					}else
					{
						retorno=bandaExcedenteDaBC(paraCTy)-bandaEmprestadaDeCTxParaCTy(deCTx-1, paraCTy)-bandaEmprestadaDeCTxParaCTy(deCTx-2, paraCTy);
					}
			}

			return retorno;
		}
		
		public double bandaExcedenteDaBC(int BC)
		{
			return ((this.BCAtual[BC]-this.BCMbps(BC))>0)?this.BCAtual[BC]-this.BCMbps(BC):0;
		}
		public double bandaDisponivelNaBC(int BC)
		{
			return ((this.BCMbps(BC)-this.BCAtual[BC])>0)?this.BCMbps(BC)-this.BCAtual[BC]:0;
		}
		public double HTLMbps(int BC)
		{
			
			return BCMbps(BC)*this.BCHTL[BC]/100;
			
		}
		public double LTHMbps(int BC)
		{
			
			return BCMbps(BC)*this.BCLTH[BC]/100;
			
		}
		public double BCMbps(int BC)
		{
			
			return this.BC[BC]*this.CargaEnlace/100;
			
		}
		public double maxCompartilhadoGBAM(int BC)
		{
			
			return (HTLMbps(BC)>=LTHMbps(BC)?HTLMbps(BC):LTHMbps(BC));
			
			
		}
		public double privado(int BC)
		{
			
			return BCMbps(BC)-maxCompartilhadoGBAM(BC);	
		}
		
		//Total de LSPs acumulado conforme HTL
		public double HTLAcumulado(int BC)
		{
			double HTLAcumulado=0;
			for(int c=ParametrosDSTE.MaxClassType-1; c>BC; c--)
			{
				HTLAcumulado+=this.HTLMbps(c);
				
			}
			return HTLAcumulado;
		}
		//Total de LSPs acumulado conforme HTL
		public double LTHAcumulado(int BC)
		{
			double LTHAcumulado=0;
			for(int c=0; c<BC; c++)
			{
				LTHAcumulado+=this.LTHMbps(c);
				
			}
			return LTHAcumulado;
		}
		public double privadoDisponivel(int BC)
		{
			
			return this.BCAtual[BC]>=this.privado(BC)?0:this.privado(BC)-this.BCAtual[BC];	
			
		}
		public double LTHMbpsCompartilhavel(int BC)
		{
			//Se não usou o privado é pq tem disponível o compartilhado
			if(this.BCAtual[BC]<=this.privado(BC))
			{
				return LTHMbps(BC);
			}
			else
			{
				//se o uso retirando o privado é maior do que o compatilhável o retorno é zero
				if((this.BCAtual[BC]-this.privado(BC))>=LTHMbps(BC))
					return 0;
				else
					return LTHMbps(BC)-(this.BCAtual[BC]-this.privado(BC));	
				
			}
			
		}
		public double HTLMbpsCompartilhavel(int BC)
		{
			
			//Se não usou o privado é pq tem disponível o compartilhado
			if(this.BCAtual[BC]<=this.privado(BC))
			{
				return HTLMbps(BC);
			}
			else
			{
				//se o uso retirando o privado é maior do que o compatilhável o retorno é zero
				if((this.BCAtual[BC]-this.privado(BC))>=HTLMbps(BC))
					return 0;
				else
					return HTLMbps(BC)-(this.BCAtual[BC]-this.privado(BC));	
				
			}
			
		}
		public double maxCompartilhavelGBAM(int BC)
		{
			
			return (HTLMbpsCompartilhavel(BC)>=LTHMbpsCompartilhavel(BC)?HTLMbpsCompartilhavel(BC):LTHMbpsCompartilhavel(BC));
			
			
		}
		
		//Total de LSPs acumulado conforme HTL
		public double HTLAcumuladoCompartilhavel(int BC)
		{
			double HTLAcumulado=0;
			for(int c=ParametrosDSTE.MaxClassType-1; c>BC; c--)
			{
				HTLAcumulado+=this.HTLMbpsCompartilhavel(c);
				
			}
			return HTLAcumulado;
		}
		//Total de LSPs acumulado conforme HTL
		public double LTHAcumuladoCompartilhavel(int BC)
		{
			double LTHAcumulado=0;
			for(int c=0; c<BC; c++)
			{
				LTHAcumulado+=this.LTHMbpsCompartilhavel(c);
				
			}
			return LTHAcumulado;
		}
		public double excedenteBCSuperiores(int BC)
		{
			double excedente=0;
			for(int c=ParametrosDSTE.MaxClassType-1; c>BC; c--)
			{
				
				excedente+=this.BCAtual[c]>this.BCMbps(c)?this.BCAtual[c]-this.BCMbps(c):0;
				
			}
			return excedente;
		}
		
		
		public double LTHMbpsCompartilhavelAUX(int BC, Lsp lsp)
		{
			double carga=0;
			if(lsp.CT==BC)
				carga=lsp.Carga;
			//Se não usou o privado é pq tem disponível o compartilhado
			if(this.BCAtual[BC]+carga<=this.privado(BC))
			{
				return LTHMbps(BC);
			}
			else
			{
				//se o uso retirando o privado é maior do que o compatilhável o retorno é zero
				if((this.BCAtual[BC]+carga-this.privado(BC))>=LTHMbps(BC))
					return 0;
				else
					return LTHMbps(BC)-(this.BCAtual[BC]+carga-this.privado(BC));	
				
			}
			
		}
		public double HTLMbpsCompartilhavelAUX(int BC, Lsp lsp)
		{
			double carga=0;
			if(lsp.CT==BC)
				carga=lsp.Carga;
			
			//Se não usou o privado é pq tem disponível o compartilhado
			if(this.BCAtual[BC]+carga<=this.privado(BC))
			{
				return HTLMbps(BC);
			}
			else
			{
				//se o uso retirando o privado é maior do que o compatilhável o retorno é zero
				if((this.BCAtual[BC]+carga-this.privado(BC))>=HTLMbps(BC))
					return 0;
				else
					return HTLMbps(BC)-(this.BCAtual[BC]+carga-this.privado(BC));	
				
			}
			
		}
		public double maxCompartilhavelGBAMAUX(int BC, Lsp lsp)
		{
			
			return (HTLMbpsCompartilhavelAUX(BC,lsp)>=LTHMbpsCompartilhavelAUX(BC,lsp)?HTLMbpsCompartilhavelAUX(BC,lsp):LTHMbpsCompartilhavelAUX(BC,lsp));
			
			
		}
		
		//Total de LSPs acumulado conforme HTL
		public double HTLAcumuladoCompartilhavelAUX(int BC, Lsp lsp)
		{
			double HTLAcumulado=0;
			for(int c=ParametrosDSTE.MaxClassType-1; c>BC; c--)
			{
				HTLAcumulado+=this.HTLMbpsCompartilhavelAUX(c,lsp);
				
			}
			return HTLAcumulado;
		}
		//Total de LSPs acumulado conforme HTL
		public double LTHAcumuladoCompartilhavelAUX(int BC, Lsp lsp)
		{
			double LTHAcumulado=0;
			for(int c=0; c<BC; c++)
			{
				LTHAcumulado+=this.LTHMbpsCompartilhavelAUX(c,lsp);
				
			}
			return LTHAcumulado;
		}
		public double excedenteBCSuperioresAUX(int BC, Lsp lsp)
		{
			double excedente=0;
			for(int c=ParametrosDSTE.MaxClassType-1; c>BC; c--)
			{
				double carga=0;
				if(lsp.CT==c)
					carga=lsp.Carga;
				excedente+=this.BCAtual[c]+carga>this.BCMbps(c)?this.BCAtual[c]+carga-this.BCMbps(c):0;
				
			}
			return excedente;
		}
		public String statusLinks()
		{
			Link aux=this;
			String retorno="";

			
					retorno+=("------- Enlace "+aux.Descricao+" ("+aux.lsrSrc.ID+"->"+aux.lsrDest.ID+") -------\r\n");
					for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
					{
						
						if(i!=ParametrosDSTE.MaxClassType-1)
							retorno+=String.format("BC[%d] = %3.0f(%6.2f) | CT[%d] = %3.0f(%6.2f)\r\n", i, aux.BCAcumulado(i), 100*aux.BCAcumulado(i)/aux.CargaEnlace, i, aux.BCAcumulado(i)-aux.BCAcumulado(i+1), 100*(aux.BCAcumulado(i)-aux.BCAcumulado(i+1))/aux.CargaEnlace);
						else
							retorno+=String.format("BC[%d] = %3.0f(%6.2f) | CT[%d] = %3.0f(%6.2f)\r\n", i, aux.BCAcumulado(i),100*aux.BCAcumulado(i)/aux.CargaEnlace, i, aux.BCAcumulado(i), 100*aux.BCAcumulado(i)/aux.CargaEnlace);
					}
					if(ParametrosDoSimulador.DebugFile>=8)
						retorno+=("LSPs no enlace:"+Lsp.imprime_lista(aux.ListaLSPs)+"\r\n");
					retorno+=("Carga:"+aux.getCargaEnlaceAtual()+"\r\n");
					retorno+=("Preempções:"+aux.preempcoes+"\r\n");
					retorno+=("Devoluções:"+aux.devolucoes+"\r\n");
					retorno+=("LSP Estabelecidas:"+aux.lspEstablished+"\r\n");
					retorno+=("LSP EstabelecidasCT0:"+aux.lspEstablishedCT[0]+"\r\n");
					retorno+=("LSP EstabelecidasCT1:"+aux.lspEstablishedCT[1]+"\r\n");
					retorno+=("LSP EstabelecidasCT2:"+aux.lspEstablishedCT[2]+"\r\n");
					retorno+=("LSP Estabelecidas Total:"+aux.lspEstablishedTotal+"\r\n");
					retorno+=("LSP Unbroken:"+aux.lspUnbroken+"\r\n");
					retorno+=("Banda Unbroken:"+aux.bandaUnbroken+"\r\n");
				
			return retorno;
		}
}

