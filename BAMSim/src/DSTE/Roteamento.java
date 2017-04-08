package DSTE;
public class Roteamento {
	
	public static Link [] TryPath_CSPF (Lsp LSP, Topologia topologia) 
	{											
		BAMStatus auxbreak1=BAMStatus.aceita;
		int tamanho=Integer.MAX_VALUE;
		Roteador roteadorOrigem = topologia.getRoteador(LSP.src);
		Link [][] caminho=roteadorOrigem.caminhos;
		Link [] menorCaminho=null;
		
		for(int i=0;caminho[i][0]!=null&&i<ParametrosDSTE.MaxCaminhos-1;i++)
		{
			for(int j=0;caminho[i][j]!=null&&j<ParametrosDSTE.MaxSaltos-1;j++)
			{
				//verificar se vai ser assim
				caminho[i][j].lspRequested++;
				caminho[i][j].lspRequestedCT[LSP.CT]++;
				caminho[i][j].bandaRequested += LSP.Carga;
				caminho[i][j].bandaRequestedCT[LSP.CT] += LSP.Carga;
				auxbreak1=caminho[i][j].checkBAM(LSP);
				
				if (auxbreak1==BAMStatus.bloqueada)
				{
					//verificar se vai ser assim
					caminho[i][j].bloqueios++;
					caminho[i][j].bloqueiosCT[LSP.CT]++;
					break;
				}
				
				if((caminho[i][j].lsrDest.ID==LSP.dest)&&auxbreak1!=BAMStatus.bloqueada&&j<tamanho)
				{
					tamanho=j;
					menorCaminho=caminho[i];
					
				}
					
			}
		}
		return menorCaminho;
		
	}
	
		
	
}
