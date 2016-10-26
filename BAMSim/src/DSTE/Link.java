package DSTE;

import Simulador.Debug;
import Simulador.No;

/* o link entre dois roteadores atende de maneira uniforme os
os pacotes que chegam a ele (uniforme em funcao da taxa de
transmiss�o e tempo de propaga��o) */
public class Link {
	//Lista Enlaces
	    public double CargaEnlace; // Valor do F�sico do Enlace em bps
		private double CargaEnlaceAtual=0; // Carga de Tr�fego Atual ocupada do Enlace em bps
		public double CargaCTAtual[] = new double [ParametrosDSTE.MaxClassType]; // Carga de Tr�fego Atual ocupada do Enlace em bps
		public String Descricao; // Descri��o do Enlace. Cada enlace est� associado a uma letra do alfabeto
		int ID; // Descri��o num�rica para o enlace
		int CustoEnlace; // Custo do Enlace
		Roteador lsrSrc; // N� origem do Enlace
		Roteador lsrDest; // n� destino do Enlace
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
		
		//Estat�sticas
		double BandaPreemptada =0; // Vari�vel que cont�m a Banda Preemptada atual no Enlace, se preemp��o for necess�ria
		double TotalBandaPreemptada = 0; // Vari�vel com o total de banda preemptada de cada enlace para computa��o das estat�sticas finais
		double BandaPreemptadaCT[] = new double[ParametrosDSTE.MaxClassType];
		
		public int preempcoes = 0;
		public int devolucoes = 0;
		public int lspEstabelecidas = 0;
		public int lspUnbroken = 0;
		public double bandaUnbroken = 0;

		public int [] lspUnbrokenCT = new int [ParametrosDSTE.MaxClassType];
		public int [] lspEstabelecidasCT = new int [ParametrosDSTE.MaxClassType];
		public int [] preempcoesCT = new int [ParametrosDSTE.MaxClassType];
		public int [] devolucoesCT = new int [ParametrosDSTE.MaxClassType];

		
		Link()
		{
	        for (int i = 0; i < ListaLSPsPorCT.length; i++) {  
	              
	        	ListaLSPsPorCT[i]=new Lista();
	        	CargaCTAtual[i]=0;
	        }  
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
	                System.out.println("BAM n�o implementado!");
	                break;
					
			
			}
			
			return BAMStatus.bloqueada;
			
			
		}
			
		
		public double CargaResidual() // Carga de Tr�fego n�o utilizada no Enlace (CargaEnlace - CargaEnlaceAtual)
		{
			return  CargaEnlace - CargaEnlaceAtual;
		}
		

		public double getCargaEnlaceAtual()
		{
			
			return this.CargaEnlaceAtual;
		}
		
		public void insereLsp(Lsp lsp)
		{
			Debug.setMensagem("------- "+this.Descricao+" -------");
			Debug.setMensagem("Estabelece LSP"+lsp.ID+":"+lsp.Carga+"("+lsp.src+"-"+lsp.dest+")(CT="+lsp.CT+")");
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
			Debug.setMensagem("------- "+this.Descricao+" -------");
			Debug.setMensagem("Remove LSP"+lsp.ID+":"+lsp.Carga+"("+lsp.src+"-"+lsp.dest+")(CT="+lsp.CT+")");
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
					//Efetua c�lculo do emprestimos de menor prioridade + banda livre
					TotalFreeBandWithDebt = this.CargaEnlace - this.getCargaEnlaceAtual() + emprestimo;
				}
				else
				//N�o tendo emprestimo somar todos emprestimos + banda livre
				{
					double naoUtilizadoBC=0;
					double emprestimo=0;
					//Soma de todos emprestimos
					//for(int r=ParametrosDSTE.MaxClassType-1;r>=0;r--)
						//emprestimo+=this.emprestimo(r);
					emprestimo+=this.emprestimoCTSuperiores(0);
					//Efetua c�lculo do emprestimos + banda livre
					TotalFreeBandWithDebt = this.CargaEnlace - this.getCargaEnlaceAtual() + emprestimo;

					//Existe a necessidade de verificar se o dispon�vel para o BC � maior que emprestimos + banda livre
					//Essa necessidade � devido as prioridades da CT hierarquicamente mais altas
					//Essa condi��o implicar� em preemp��o
					//Efetua c�lculo do emprestimo + N�o utilizado pelo BC
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
			retorno+=("||BC| BC%  |BC(Mbps)|| HTL% |HTL(Mbps)|| LTH% |LTH(Mbps)||M�x. Comp.||Privado||HTL Acum.||LTH Acum.||\r\n");
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
			retorno+=("||BC| BC%  |BC(Mbps)|| HTL% |HTL(Mbps)|| LTH% |LTH(Mbps)||M�x. Comp.||Privado||HTL Acum.||LTH Acum.||Exce. Sup.||\r\n");
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
			//Se n�o usou o privado � pq tem dispon�vel o compartilhado
			if(this.BCAtual[BC]<=this.privado(BC))
			{
				return LTHMbps(BC);
			}
			else
			{
				//se o uso retirando o privado � maior do que o compatilh�vel o retorno � zero
				if((this.BCAtual[BC]-this.privado(BC))>=LTHMbps(BC))
					return 0;
				else
					return LTHMbps(BC)-(this.BCAtual[BC]-this.privado(BC));	
				
			}
			
		}
		public double HTLMbpsCompartilhavel(int BC)
		{
			
			//Se n�o usou o privado � pq tem dispon�vel o compartilhado
			if(this.BCAtual[BC]<=this.privado(BC))
			{
				return HTLMbps(BC);
			}
			else
			{
				//se o uso retirando o privado � maior do que o compatilh�vel o retorno � zero
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
			//Se n�o usou o privado � pq tem dispon�vel o compartilhado
			if(this.BCAtual[BC]+carga<=this.privado(BC))
			{
				return LTHMbps(BC);
			}
			else
			{
				//se o uso retirando o privado � maior do que o compatilh�vel o retorno � zero
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
			
			//Se n�o usou o privado � pq tem dispon�vel o compartilhado
			if(this.BCAtual[BC]+carga<=this.privado(BC))
			{
				return HTLMbps(BC);
			}
			else
			{
				//se o uso retirando o privado � maior do que o compatilh�vel o retorno � zero
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
}

