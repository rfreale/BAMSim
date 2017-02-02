package DSTE;

import Simulador.Debug;
import Simulador.No;
import Simulador.RodadaDeSimulacao;


public class Lsp {
	
	public Long ID = (long) 0;
	public double Carga; // Carga de Tráfego da LSP (Inicialmente Banda Efetiva)
	public double CargaReduzida;//Implementação futura de redução de carga da LSP
	public int src; // Nó Origem da LSP
	public int dest; // Nó Destino da LSP
	public  int CT; // Tipo de Classe a qual o LSP pertence
	public int setupPriority = 4; // Prioridade de estabelecimento 0 (maior prioridade) ... 7 (menor prioridade) = 4 (valor default)
	public int holdPriority = 4; // Prioridade de manutenção 0 (maior prioridade) ... 7 (menor prioridade) = 4 (valor default)
	public RodadaDeSimulacao rodada=null;
	public double tempoDeVida;
	
	Link [] caminho=null;
	public LspStatus status= LspStatus.criada;
	//int H; // Fator H: Parâmetro associado a escolha da LSP que será preemptada
	//int OverlapFactor; // Fator Of: Parâmetro associado ao número de Enlaces coincidentes entre LSPs já estababelecidas e a nova requisição
	//int PosUlt; // ????Teste para tentar salvar o enlace coicidente com as LSPs existente para a nova requisição de LSP
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
			caminho[j].lspEstabelecidas++;
			caminho[j].lspEstabelecidasCT[this.CT]++;
			caminho[j].lspEstabelecidasTotal++;
			caminho[j].lspEstabelecidasTotalCT[this.CT]++;
			if(caminho[j].lsrDest.ID==this.dest)
			{
				break;
			}
			
		}
		rodada.estatistica.lspEstablished++;
		rodada.estatistica.lspEstablishedCT[this.CT]++;
		rodada.estatistica.lspEstablishedTotal++;
		rodada.estatistica.lspEstablishedTotalCT[this.CT]++;
		rodada.estatistica.bandaEstabelecida += this.Carga;
		rodada.estatistica.bandaEstabelecidaCT[this.CT] += this.Carga;
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
				caminho[j].lspUnbroken++;
				caminho[j].lspUnbrokenCT[this.CT]++;
				caminho[j].bandaUnbroken+=this.Carga;
				if(caminho[j].lsrDest.ID==this.dest)
				{
					break;
				}
				
			}
			rodada.estatistica.lspEstablished--;
			rodada.estatistica.lspEstablishedCT[this.CT]--;

				
			
		}
		
		public  void preemptaLSP()
		{
			int nEnl;

			
			for(int j=0;this.caminho[j]!=null&&j<ParametrosDSTE.MaxSaltos;j++)
			{
				this.caminho[j].removeLsp(this);
				this.caminho[j].preempcoes++;
				this.caminho[j].preempcoesCT[this.CT]++;
				if(caminho[j].lsrDest.ID==this.dest)
				{
					break;
				}
				
				
			}
			
			rodada.estatistica.preempcoes++;
			rodada.estatistica.preempcoesCT[this.CT]++;
			rodada.estatistica.lspEstablished--;
			rodada.estatistica.lspEstablishedCT[this.CT]--;
			
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
				this.caminho[j].devolucoes++;
				this.caminho[j].devolucoesCT[this.CT]++;
				if(caminho[j].lsrDest.ID==this.dest)
				{
					break;
				}
				
			}
			
			rodada.estatistica.devolucoes++;
			rodada.estatistica.devolucoesCT[this.CT]++;
			rodada.estatistica.lspEstablished--;
			rodada.estatistica.lspEstablishedCT[this.CT]--;
			
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
