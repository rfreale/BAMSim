package DSTE;

import Simulador.Debug;
import Simulador.No;
import Simulador.RodadaDeSimulacao;


public class Lsp {
	
	public Long ID = (long) 0;
	public double Carga; // Carga de Tr�fego da LSP (Inicialmente Banda Efetiva)
	public double CargaReduzida;//Implementa��o futura de redu��o de carga da LSP
	public int src; // N� Origem da LSP
	public int dest; // N� Destino da LSP
	public  int CT; // Tipo de Classe a qual o LSP pertence
	public int setupPriority = 4; // Prioridade de estabelecimento 0 (maior prioridade) ... 7 (menor prioridade) = 4 (valor default)
	public int holdPriority = 4; // Prioridade de manuten��o 0 (maior prioridade) ... 7 (menor prioridade) = 4 (valor default)
	public RodadaDeSimulacao rodada=null;
	public double tempoDeVida;
	
	Link [] caminho=null;
	public LspStatus status= LspStatus.criada;
	//int H; // Fator H: Par�metro associado a escolha da LSP que ser� preemptada
	//int OverlapFactor; // Fator Of: Par�metro associado ao n�mero de Enlaces coincidentes entre LSPs j� estababelecidas e a nova requisi��o
	//int PosUlt; // ????Teste para tentar salvar o enlace coicidente com as LSPs existente para a nova requisi��o de LSP
	public Lsp(RodadaDeSimulacao r)
	{
		this.rodada=r;
		ID=++r.LSPcount;
	}

	public  void estabelecerLSP (Link [] caminho) 
	{
		this.caminho=caminho;
		for(int j=0;caminho[j]!=null&&j<ParametrosDSTE.MaxSaltos;j++)
		{
			BAMStatus aux=caminho[j].checkBAM(this);
			if (caminho[j].bamType!=BAMType.PreemptionGBAM)
			{
				switch(aux)
				{
				
					case devolucaoEpreempcao:
							BAM.devolution(caminho[j], this);
							BAM.preemption(caminho[j], this);
							break;
					case devolucao:
							BAM.devolution(caminho[j], this);
							break;
					case preempcao:
							BAM.preemption(caminho[j], this);
							break;
				}
			}
			else
			{
				switch(aux)
				{
				
					case devolucaoEpreempcao:
							BAM.devolutionG(caminho[j], this);
							BAM.preemptionG(caminho[j], this);
							break;
					case devolucao:
							BAM.devolutionG(caminho[j], this);
							break;
					case preempcao:
							BAM.preemptionG(caminho[j], this);
							break;
				}
			}
			
			caminho[j].insereLsp(this);
			if(caminho[j].lsrDest.ID==this.dest)
			{
				break;
			}
			
		}
	}
	public String imprimeLsp()
	{
		
		return("LSP"+this.ID+":"+this.Carga+"("+this.src+"-"+this.dest+")(CT="+this.CT+")");
	}
	
	
		public  void desestabeleceLSP()
		{
			int nEnl;
			//struct no *retorno;
			for(int j=0;this.caminho[j]!=null&&j<ParametrosDSTE.MaxSaltos;j++)
			{
				this.caminho[j].removeLsp(this);
				if(caminho[j].lsrDest.ID==this.dest)
				{
					break;
				}
				
			}

				
			
		}
		
		public  void preemptaLSP()
		{
			int nEnl;

			
			for(int j=0;this.caminho[j]!=null&&j<ParametrosDSTE.MaxSaltos;j++)
			{
				this.caminho[j].removeLsp(this);
				if(caminho[j].lsrDest.ID==this.dest)
				{
					break;
				}
				
				
			}
			
			rodada.estatistica.preempcoes++;
			rodada.estatistica.preempcoesCT[this.CT]++;
			
			//Se apenas para unitTest
			if(this.rodada!=null)
			{	
					rodada.cancelp_tkn(this);
				
			}
			
		}
		public  void devolveLSP()
		{
			int nEnl;

			
			for(int j=0;this.caminho[j]!=null&&j<ParametrosDSTE.MaxSaltos;j++)
			{
				this.caminho[j].removeLsp(this);
				if(caminho[j].lsrDest.ID==this.dest)
				{
					break;
				}
				
			}
			
			rodada.estatistica.devolucoes++;
			rodada.estatistica.devolucoesCT[this.CT]++;
			
			//Se apenas para unitTest
			if(this.rodada!=null)
			{	
					rodada.cancelp_tkn(this);
				
			}
			
		}
		public static String imprime_lista(Lista L) // Imprime elemento
		{	
			No aux;
			String texto="";
			aux = L.primeiro.prox;
			int count=0;
			if (aux == null)
			{
				texto+="Lista Vazia";
			}
			else
			{
				while(aux!=null)
				{
					//printf("%d -> ", aux->item.dest);
					texto+="LSP"+((Lsp)aux.item).ID+":"+((Lsp)aux.item).Carga+"("+((Lsp)aux.item).src+"-"+((Lsp)aux.item).dest+")(CT="+((Lsp)aux.item).CT+") -> ";
					count++;
					aux = aux.prox;
				}
				texto+="\r\nTotal de LSPs:"+count;
			}
			return texto;

		}
}
