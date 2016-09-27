package DSTE;

import Simulador.Debug;
import Simulador.No;
import Simulador.RodadaDeSimulacao;

public class BAM {

	
	public static BAMStatus NoPreemptionRDM(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.aceita;
		//Por n�o permitirmos preemp��o precisamos verificar se o estabelecimento da LSP n�o exceder� nenhum BC
		for(int w=lsp.CT; w>=0; w--)
		{
			if((link.BC[w]/link.BC[0])*link.CargaEnlace < link.BCAcumulado(w) + lsp.Carga)
			{
				
				aux=BAMStatus.bloqueada;
				//Sem espa�o
				break; 
			}
			else
				aux=BAMStatus.aceita;
		}
		return aux;
	}
	
	public static BAMStatus preemptionRDM(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.aceita;
		

		if((link.BC[lsp.CT]/link.BC[0])*link.CargaEnlace < link.BCAcumulado(lsp.CT) + lsp.Carga)
		{
			
			aux=BAMStatus.bloqueada;

		}
		else
		{
			for(int w=lsp.CT; w>=0; w--)
			{
				if((link.BC[w]/link.BC[0])*link.CargaEnlace < link.BCAcumulado(w) + lsp.Carga)
				{
					
					aux=BAMStatus.preempcao;
					//Sem espa�o
					break; 
				}
				else
					aux=BAMStatus.aceita;
			}
				
		}
			

		return aux;
	}
	
	public static BAMStatus NoPreemptionMAM(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.bloqueada;
		if(link.BC[lsp.CT]*link.CargaEnlace/100 >= link.CargaCTAtual[lsp.CT] + lsp.Carga)
				aux=BAMStatus.aceita;
		else
				aux=BAMStatus.bloqueada;

		return aux;
	}
	
	public static BAMStatus NoPreemptionAllocCTSharing(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.bloqueada;
		if (link.getCargaEnlaceAtual() + lsp.Carga>link.CargaEnlace)
		{
			
			aux=BAMStatus.bloqueada;
			
		}
		else
		{
			aux=BAMStatus.aceita;
		}
		return aux;
	}
	
	public static BAMStatus preemptionAllocCTSharing(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.bloqueada;
		
		
		if (link.getCargaEnlaceAtual() + lsp.Carga>link.CargaEnlace)
		{
			
			
			if(link.BCAcumulado(lsp.CT)+ lsp.Carga-link.emprestimoCTSuperiores(lsp.CT) > (link.BC[lsp.CT]/link.BC[0])*link.CargaEnlace)
			//Tendo emprestimo deve contar apenas com emprestimos de menor prioridade + banda livre
			{
				double emprestimo=0;
				//Soma emprestimos de menor prioridade
				for(int r=lsp.CT-1;r>=0;r--)
					emprestimo+=link.emprestimo(r);
			 	if(emprestimo+link.CargaEnlace-link.getCargaEnlaceAtual()>= lsp.Carga)
			 		return BAMStatus.devolucao;
			}
			else
			//N�o tendo emprestimo somar todos emprestimos + banda livre
			{
				if(link.emprestimoCTSuperiores(0)>= lsp.Carga)
			 		return BAMStatus.devolucao;
			}
			
			
			if((link.BC[lsp.CT]/link.BC[0])*link.CargaEnlace >= link.BCAcumulado(lsp.CT) + lsp.Carga)
			{
				for(int w=lsp.CT; w>=0; w--)
				{
					if((link.BC[w]/link.BC[0])*link.CargaEnlace < link.BCAcumulado(w) + lsp.Carga)
					{
						
						aux=BAMStatus.preempcao;
						//Sem espa�o
						break; 
					}
					else
						aux=BAMStatus.aceita;
				}
					
			}		
			else 
				if (((link.BC[lsp.CT]/link.BC[0])*link.CargaEnlace)- link.BCAcumulado(lsp.CT) + link.emprestimoCTSuperiores(0)>=lsp.Carga)
					return BAMStatus.devolucaoEpreempcao;
			
			
			
		}
		else
		{
			aux=BAMStatus.aceita;
		}
		return aux;
	}

	public static double preemption(Link link, Lsp lsp)
	{
		double bandaPreemptada=0;
		No aux;
		
		for(int n=lsp.CT-1;n>=0;n--) 
			{	
				double BandaAcimaCT = link.BCAcumulado(n)+lsp.Carga - ((link.BC[n]/link.BC[0])*link.CargaEnlace); // Se BandaAcimaCT>0 � porque o modelo RDM est� sendo desrespeitado e a preemp��o faz-se necess�ria nesse enlace
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
						
					while(aux!=null&&(BandaAcimaCT > bandaPreemptada)) //Banda que � necess�ria ser preemptada no enlace
					{
						Debug.setMensagem("============= Preemp��o =============");
						((Lsp)aux.item).preemptaLSP();
						((Lsp)aux.item).status=LspStatus.preemptada;

						bandaPreemptada = bandaPreemptada + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
						
						aux=link.ListaLSPsPorCT[n].ultimo; 	
					}
						
				 }
			}
		
			
		return bandaPreemptada;
	}
	
	public static double forcePreemption(Link link)
	{
		double bandaPreemptada=0;
		No aux;
		
		for(int n=DSTE.ParametrosDSTE.MaxClassType-1;n>=0;n--) 
			{	
				double BandaAcimaCT = link.BCAcumulado(n) - ((link.BC[n]/link.BC[0])*link.CargaEnlace); // Se BandaAcimaCT>0 � porque o modelo RDM est� sendo desrespeitado e a preemp��o faz-se necess�ria nesse enlace
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
						
					while(aux!=null&&(BandaAcimaCT > bandaPreemptada)) //Banda que � necess�ria ser preemptada no enlace
					{
						Debug.setMensagem("============= Preemp��o =============");
						((Lsp)aux.item).preemptaLSP();
						((Lsp)aux.item).status=LspStatus.preemptada;

						bandaPreemptada = bandaPreemptada + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
						
						aux=link.ListaLSPsPorCT[n].ultimo; 	
					}
						
				 }
			}
		
			
		return bandaPreemptada;
	}

	public static double devolution(Link link, Lsp lsp)
	{
		double bandaDevolvida=0;
		double bandaDevolvidaTotal=0;
		No aux;
		
		if (link.getCargaEnlaceAtual() + lsp.Carga>link.CargaEnlace)
		{
			//for(int c=ParametrosDSTE.MaxClassType-1; c>lsp.CT; c--)
			for(int n=0;ParametrosDSTE.MaxClassType>n;n++)
			//for(int n=ParametrosDSTE.MaxClassType-1; n>=0; n--)
			{
				bandaDevolvida=0;
				double BandaAcimaCT = link.emprestimo(n);
				//For�a retirar somente o valor necess�rio de emprestimo para n�o extrapolar o enlace
				if(BandaAcimaCT >(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace))
					BandaAcimaCT=(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace);
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
					
					while(aux!=null&&(BandaAcimaCT > bandaDevolvida)) //Banda que � necess�ria ser preemptada no enlace
					{
						Debug.setMensagem("============= Devolu��o =============");
						((Lsp)aux.item).devolveLSP();
						((Lsp)aux.item).status=LspStatus.devolvida;

						bandaDevolvida = bandaDevolvida + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
						
						aux=link.ListaLSPsPorCT[n].ultimo; 	
					}
					
				}
				bandaDevolvidaTotal+=bandaDevolvida;
			}
			
		}
		
			
		return bandaDevolvida;
	}

	
	public static BAMStatus preemptionGBAM(Link link, Lsp lsp)
	{
		BAMStatus status=BAMStatus.bloqueada;
		

		
		if (
				//Tem direito a banda por ser privada do BC ou priorit�ria por estar abaixo do seu BC configurado
				((link.BCAtual[lsp.CT]+lsp.Carga<=link.privado(lsp.CT))||(link.BCAtual[lsp.CT]+lsp.Carga<=link.BCMbps(lsp.CT)))
				|| //ou
				//Tem direito utilizando HTL ou LTH
				(lsp.Carga<=
							(link.BCMbps(lsp.CT)- //Limite da BC
							link.BCAtual[lsp.CT]) //Utilizado pela BC
							+
							(link.LTHAcumuladoCompartilhavel(lsp.CT) //Compartilh�vel LTH
							+link.HTLAcumuladoCompartilhavel(lsp.CT) //Compartilh�vel HTL
							-link.excedenteBCSuperiores(lsp.CT))  //Excedentes superiores utilizando HTL ou LTH
				)
			)
		{
			
			
			
			//verifica se algu�m inferior a nova LSP estourar�  -> preempcao
			for(int c=0; c<lsp.CT; c++)
			{
				//n�o estrapolou sua BC
				if(link.BCAtual[c]<=link.BCMbps(c))
				{
					int i=0;
				}else
				if(		link.BCAtual[c]
						>
						(link.BCMbps(c)
						+
						(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel LTH
						+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel HTL
						-link.excedenteBCSuperioresAUX(c,lsp))
						) 
				  )
				{
					//status=status==BAMStatus.devolucao?BAMStatus.devolucaoEpreempcao:BAMStatus.preempcao;
					status=BAMStatus.preempcao;
					break;
				}
			}
			
			//verifica se algu�m superior a nova LSP estourar� -> devolucao
			for(int c=ParametrosDSTE.MaxClassType-1; c>lsp.CT; c--)
			{
				//n�o estrapolou sua BC
				if(link.BCAtual[c]<=link.BCMbps(c))
				{
					int i=0;
				}else
				//Se o que estou utilizando for maior do que o dispon�vel(AUX) ap�s a nova LSP
				if(		link.BCAtual[c]
						>
						(link.BCMbps(c)
						+
						(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel LTH
						+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel HTL
						-link.excedenteBCSuperioresAUX(c,lsp))
						) 
				  )
				{
					//status=BAMStatus.devolucao;
					status=status==BAMStatus.devolucao?BAMStatus.devolucaoEpreempcao:BAMStatus.devolucao;
					break;
				}
			}
			
			//se algu�m superior a nova LSP estourar e  algu�m inferior a nova LSP estourar 
			//-> retirar o estouro superior e depois inferior
			//status=BAMStatus.devolucaoEpreempcao;
			
			//se algu�m superior a mim estourar com a nova LSP retirar o estouro
			//status=BAMStatus.devolucao;
			status=status==BAMStatus.bloqueada?BAMStatus.aceita:status;
			
			
		}
		else
		{
			status=BAMStatus.bloqueada;
		}
		

		return status;
	}
	
	public static double preemptionG(Link link, Lsp lsp)
	{
		double bandaPreemptada=0;
		No aux;
		
		//verifica se algu�m inferior a nova LSP estourar�  -> preempcao
		for(int c=lsp.CT-1; c>=0; c--)
		{
			
			aux = link.ListaLSPsPorCT[c].ultimo; 
			//n�o estrapolou sua BC
			if(link.BCAtual[c]<=link.BCMbps(c))
			{
				int i=0;
			}else
			while(		link.BCAtual[c]
					>
					(link.BCMbps(c)
					+
					(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel LTH
					+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel HTL
					-link.excedenteBCSuperioresAUX(c,lsp))
					) 
			  ) 
			{
					Debug.setMensagem("============= Preemp��o =============");
					((Lsp)aux.item).preemptaLSP();
					((Lsp)aux.item).status=LspStatus.preemptada;

					bandaPreemptada = bandaPreemptada + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
					
					aux=link.ListaLSPsPorCT[c].ultimo; 	
			}
				
			
		}
		
			
		return bandaPreemptada;
	}
	
	public static double devolutionG(Link link, Lsp lsp)
	{
		double bandaDevolvida=0;
		No aux;
		//verifica se algu�m superior a nova LSP estourar� -> devolucao
		for(int c=lsp.CT; c<=ParametrosDSTE.MaxClassType-1; c++)
		{
			aux = link.ListaLSPsPorCT[c].ultimo; 
			//Se o que estou utilizando for maior do que o dispon�vel(AUX) ap�s a nova LSP
			//n�o estrapolou sua BC
		//	if(link.BCAtual[c]<=link.BCMbps(c))
		//	{
		//		int i=0;
		//	}else
			while(	link.BCAtual[c]>link.BCMbps(c) &&
			
					(link.BCAtual[c]
					>
					(link.BCMbps(c)
					+
					(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel LTH
					+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilh�vel HTL
					-link.excedenteBCSuperioresAUX(c,lsp))
					)) 
			  )
			{
				Debug.setMensagem("============= Devolu��o =============");
				((Lsp)aux.item).devolveLSP();
				((Lsp)aux.item).status=LspStatus.devolvida;

				bandaDevolvida = bandaDevolvida + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
				
				aux=link.ListaLSPsPorCT[c].ultimo; 	
				
			}
		}
		
		/*
		if (link.getCargaEnlaceAtual() + lsp.Carga>link.CargaEnlace)
		{
			for(int n=0;ParametrosDSTE.MaxClassType>n;n++)
			{
				double BandaAcimaCT = link.emprestimo(n);
				//For�a retirar somente o valor necess�rio de emprestimo para n�o extrapolar o enlace
				if(BandaAcimaCT >(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace))
					BandaAcimaCT=(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace);
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
					
					while(aux!=null&&(BandaAcimaCT > bandaDevolvida)) //Banda que � necess�ria ser preemptada no enlace
					{
						Debug.setMensagem("============= Devolu��o =============");
						((Lsp)aux.item).devolveLSP();
						((Lsp)aux.item).status=LspStatus.devolvida;

						bandaDevolvida = bandaDevolvida + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
						
						aux=link.ListaLSPsPorCT[n].ultimo; 	
					}
					
				}
			}
			
		}
		*/
			
		return bandaDevolvida;
	}
	
	public static double forcePreemptionG(Link link)
	{
		double bandaPreemptada=0;
		No aux;
		
		//verifica se algu�m inferior a nova LSP estourar�  -> preempcao
		for(int c=DSTE.ParametrosDSTE.MaxClassType-1; c>=0; c--)
		{
			
			aux = link.ListaLSPsPorCT[c].ultimo; 
			//n�o estrapolou sua BC
			if(link.BCAtual[c]<=link.BCMbps(c))
			{
				int i=0;
			}else
			while(		link.BCAtual[c]
					>
					(link.BCMbps(c)
					+
					(link.LTHAcumuladoCompartilhavel(c) //Compartilh�vel LTH
					+link.HTLAcumuladoCompartilhavel(c) //Compartilh�vel HTL
					-link.excedenteBCSuperiores(c))
					) 
			  ) 
			{
					Debug.setMensagem("============= Preemp��o =============");
					((Lsp)aux.item).preemptaLSP();
					((Lsp)aux.item).status=LspStatus.preemptada;

					bandaPreemptada = bandaPreemptada + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
					
					aux=link.ListaLSPsPorCT[c].ultimo; 	
			}
				
			
		}
		
			
		return bandaPreemptada;
	}

}



