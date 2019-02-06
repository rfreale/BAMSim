package DSTE;

import Simulador.Debug;
import Simulador.No;
import Simulador.RodadaDeSimulacao;

public class BAM {

	public static BAMStatus NoPreemptionBestEffort(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.aceita;
		
			if(link.getCargaEnlaceAtual() + lsp.Carga>link.CargaEnlace)
			{
				
				aux=BAMStatus.bloqueada;
				//Sem espaço
			}
			else
				aux=BAMStatus.aceita;
		
		return aux;
	}
	public static BAMStatus NoPreemptionRDM(Link link, Lsp lsp)
	{
		BAMStatus aux=BAMStatus.aceita;
		//Por não permitirmos preempção precisamos verificar se o estabelecimento da LSP não excederá nenhum BC
		for(int w=lsp.CT; w>=0; w--)
		{
			if((link.BC[w]/link.BC[0])*link.CargaEnlace < link.BCAcumulado(w) + lsp.Carga)
			{
				
				aux=BAMStatus.bloqueada;
				//Sem espaço
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
					//Sem espaço
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
			//Não tendo emprestimo somar todos emprestimos + banda livre
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
						//Sem espaço
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
				double BandaAcimaCT = link.BCAcumulado(n)+lsp.Carga - ((link.BC[n]/link.BC[0])*link.CargaEnlace); // Se BandaAcimaCT>0 é porque o modelo RDM está sendo desrespeitado e a preempção faz-se necessária nesse enlace
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
						
					while(aux!=null&&(BandaAcimaCT > bandaPreemptada)) //Banda que é necessária ser preemptada no enlace
					{
						Debug.setMensagem("============= preempção =============",7,7);
						((Lsp)aux.item).preemptaLSP(link);
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
				double BandaAcimaCT = link.BCAcumulado(n) - ((link.BC[n]/link.BC[0])*link.CargaEnlace); // Se BandaAcimaCT>0 é porque o modelo RDM está sendo desrespeitado e a preempção faz-se necessária nesse enlace
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
						
					while(aux!=null&&(BandaAcimaCT > bandaPreemptada)) //Banda que é necessária ser preemptada no enlace
					{
						Debug.setMensagem("============= preempção =============",7,7);
						((Lsp)aux.item).preemptaLSP(link);
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
				//Força retirar somente o valor necessário de emprestimo para Não extrapolar o enlace
				if(BandaAcimaCT >(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace))
					BandaAcimaCT=(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace);
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
					
					while(aux!=null&&(BandaAcimaCT > bandaDevolvida)) //Banda que é necessária ser preemptada no enlace
					{
						Debug.setMensagem("============= Devolução =============",7,7);
						((Lsp)aux.item).devolveLSP(link);
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
				//Tem direito a banda por ser privada do BC ou prioritária por estar abaixo do seu BC configurado
				((link.BCAtual[lsp.CT]+lsp.Carga<=link.privado(lsp.CT))||(link.BCAtual[lsp.CT]+lsp.Carga<=link.BCMbps(lsp.CT)))
				|| //ou
				//Tem direito utilizando HTL ou LTH
				(lsp.Carga<=
							(link.BCMbps(lsp.CT)- //Limite da BC
							link.BCAtual[lsp.CT]) //Utilizado pela BC
							+
							(link.LTHAcumuladoCompartilhavel(lsp.CT) //Compartilhável LTH
							+link.HTLAcumuladoCompartilhavel(lsp.CT) //Compartilhável HTL
							-link.excedenteBCSuperiores(lsp.CT))  //Excedentes superiores utilizando HTL ou LTH
				)
			)
		{
			
			
			
			//verifica se alguém inferior a nova LSP estourará  -> preempcao
			for(int c=0; c<lsp.CT; c++)
			{
				//Não estrapolou sua BC
				if(link.BCAtual[c]<=link.BCMbps(c))
				{
					int i=0;
				}else
				if(		link.BCAtual[c]
						>
						(link.BCMbps(c)
						+
						(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável LTH
						+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável HTL
						-link.excedenteBCSuperioresAUX(c,lsp))
						) 
				  )
				{
					//status=status==BAMStatus.devolucao?BAMStatus.devolucaoEpreempcao:BAMStatus.preempcao;
					status=BAMStatus.preempcao;
					break;
				}
			}
			
			//verifica se alguém superior a nova LSP estourará -> devolucao
			for(int c=ParametrosDSTE.MaxClassType-1; c>lsp.CT; c--)
			{
				//Não estrapolou sua BC
				if(link.BCAtual[c]<=link.BCMbps(c))
				{
					int i=0;
				}else
				//Se o que estou utilizando for maior do que o disponível(AUX) após a nova LSP
				if(		link.BCAtual[c]
						>
						(link.BCMbps(c)
						+
						(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável LTH
						+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável HTL
						-link.excedenteBCSuperioresAUX(c,lsp))
						) 
				  )
				{
					//status=BAMStatus.devolucao;
					status=status==BAMStatus.preempcao?BAMStatus.devolucaoEpreempcao:BAMStatus.devolucao;
					break;
				}
			}
			
			//se alguém superior a nova LSP estourar e  alguém inferior a nova LSP estourar 
			//-> retirar o estouro superior e depois inferior
			//status=BAMStatus.devolucaoEpreempcao;
			
			//se alguém superior a mim estourar com a nova LSP retirar o estouro
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
		
		//verifica se alguém inferior a nova LSP estourará  -> preempcao
		for(int c=0 ; c<= lsp.CT-1; c++)
		{
			
			aux = link.ListaLSPsPorCT[c].ultimo; 
						
			while((link.BCAtual[c]>link.BCMbps(c)) &&
					(		link.BCAtual[c]
					>
					(link.BCMbps(c)
					+
					(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável LTH
					+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável HTL
					-link.excedenteBCSuperioresAUX(c,lsp))
					) 
			  ) )
			{
					Debug.setMensagem("============= preempção =============",7,7);
					((Lsp)aux.item).preemptaLSP(link);
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
		//verifica se alguém superior a nova LSP estourará -> devolucao
		for(int c=lsp.CT; c<=ParametrosDSTE.MaxClassType-1; c++)
		{
			aux = link.ListaLSPsPorCT[c].ultimo; 
			//Se o que estou utilizando for maior do que o disponível(AUX) após a nova LSP
			//Não estrapolou sua BC
		//	if(link.BCAtual[c]<=link.BCMbps(c))
		//	{
		//		int i=0;
		//	}else
			while(	link.BCAtual[c]>link.BCMbps(c) &&
			
					(link.BCAtual[c]
					>
					(link.BCMbps(c)
					+
					(link.LTHAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável LTH
					+link.HTLAcumuladoCompartilhavelAUX(c,lsp) //Compartilhável HTL
					-link.excedenteBCSuperioresAUX(c,lsp))
					)) 
			  )
			{
				Debug.setMensagem("============= Devolução =============",7,7);
				((Lsp)aux.item).devolveLSP(link);
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
				//Força retirar somente o valor necessário de emprestimo para Não extrapolar o enlace
				if(BandaAcimaCT >(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace))
					BandaAcimaCT=(link.BCAcumulado(0)+ lsp.Carga - link.CargaEnlace);
				if(BandaAcimaCT>0)
				{
					aux = link.ListaLSPsPorCT[n].ultimo; 						
					
					while(aux!=null&&(BandaAcimaCT > bandaDevolvida)) //Banda que é necessária ser preemptada no enlace
					{
						Debug.setMensagem("============= Devolução =============");
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
		
		//verifica se alguém inferior a nova LSP estourará  -> preempcao
		for(int c=DSTE.ParametrosDSTE.MaxClassType-1; c>=0; c--)
		{
			
			aux = link.ListaLSPsPorCT[c].ultimo; 
			//Não estrapolou sua BC
			if(link.BCAtual[c]<=link.BCMbps(c))
			{
				int i=0;
			}else
			while(		link.BCAtual[c]
					>
					(link.BCMbps(c)
					+
					(link.LTHAcumuladoCompartilhavel(c) //Compartilhável LTH
					+link.HTLAcumuladoCompartilhavel(c) //Compartilhável HTL
					-link.excedenteBCSuperiores(c))
					) 
			  ) 
			{
					Debug.setMensagem("============= preempção =============",7,7);
					((Lsp)aux.item).preemptaLSP(link);
					((Lsp)aux.item).status=LspStatus.preemptada;

					bandaPreemptada = bandaPreemptada + ((Lsp)aux.item).Carga; // Incrementa Banda Preemptada acima do BC atual
					
					aux=link.ListaLSPsPorCT[c].ultimo; 	
			}
				
			
		}
		
			
		return bandaPreemptada;
	}

}