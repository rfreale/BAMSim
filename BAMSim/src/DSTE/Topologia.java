package DSTE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import Simulador.ParametrosDoSimulador;

public class Topologia {
	public Link [] link = new Link[ParametrosDSTE.LINKS];
	public Roteador [] roteador = new Roteador[ParametrosDSTE.ROTEADORES];
	int [][] topologiaDosLinks = new int[ParametrosDSTE.LINKS][ParametrosDSTE.ROTEADORES]; 
	Link [][] topologiaDosRoteadores = new Link[ParametrosDSTE.ROTEADORES][ParametrosDSTE.ROTEADORES];

	
	public void gerarTopologiaDosRoteadores()
	{
		this.topologiaDosRoteadores = new Link[ParametrosDSTE.ROTEADORES][ParametrosDSTE.ROTEADORES];
		
		for (int i=0; i<ParametrosDSTE.ROTEADORES;i++)
		{
			
			for (int j=0; j<ParametrosDSTE.ROTEADORES;j++)
			{
				for(int z=0; z<ParametrosDSTE.LINKS;z++)
					if(link[z].lsrSrc==roteador[i]&&link[z].lsrDest==roteador[j])
						topologiaDosRoteadores[i][j]=link[z];
					
			}

		}
		
		
	}
	public void gerarTopologiaDosLinks()
	{
		this.topologiaDosLinks = new int[ParametrosDSTE.LINKS][ParametrosDSTE.ROTEADORES];
		for (int i=0; i<ParametrosDSTE.LINKS;i++)
		{
			for(int z=0; z<ParametrosDSTE.ROTEADORES;z++)
					if(link[i].lsrSrc==roteador[z]||link[i].lsrDest==roteador[z])
						topologiaDosLinks[i][z]=1;

		}
		
		
	}
	public String imprimirTopologiaDosLinks()
	{
		String retorno="";
		retorno+=("        ");
		for (int i=0; i<ParametrosDSTE.ROTEADORES;i++)
		{
			retorno+=("["+roteador[i].Descricao+"]");
			
		}
		retorno+=("\r\n");
		for (int i=0; i<ParametrosDSTE.LINKS;i++)
		{
			retorno+=("["+link[i].Descricao+"]");
			
			for (int j=0; j<ParametrosDSTE.ROTEADORES;j++)
			{
				if(topologiaDosLinks[i][j]==1)
					retorno+=("[ "+topologiaDosLinks[i][j]+"]");
				else
					retorno+=("[  ]");
			}
			retorno+=("\r\n");
		}
		return retorno;
	}
	public String imprimirTopologiaDosRoteadores()
	{
		String retorno="";
		
		retorno+=("    ");
		for (int i=0; i<ParametrosDSTE.ROTEADORES;i++)
		{
			retorno+=("["+roteador[i].Descricao+"]");
			
		}
		retorno+=("\r\n");
		for (int i=0; i<ParametrosDSTE.ROTEADORES;i++)
		{
			retorno+=("["+roteador[i].Descricao+"]");
			
			for (int j=0; j<ParametrosDSTE.ROTEADORES;j++)
			{
				if(topologiaDosRoteadores[i][j]!=null)
					retorno+=("[ "+topologiaDosRoteadores[i][j].ID+"]");
				else
					retorno+=("[  ]");
			}
			retorno+=("\r\n");
		}
		return retorno;
	}
	public void carregarMatrizDeCaminhosManual()
	{
		ParametrosDSTE.matrizDeCaminhosManual(this);
	}
	
	public void adicionar(int roteadorID, int caminhoID, int linkID) {  
		int posicao=0;
		
        for (int i = 0; i < roteador[roteadorID].caminhos[caminhoID].length; i++) {  
            if (roteador[roteadorID].caminhos[caminhoID][i] != null) {  
                posicao += 1;  
            }  
        }  
        roteador[roteadorID].caminhos[caminhoID][posicao] = link[linkID];  
    } 
	
	public void carregarTopologiaManual()
	{
		
		ParametrosDSTE.topologiaManual(this);
		
		

	}
	public void carregarTopologiaArquivo()
	{
		
		try {
			FileInputStream stream = new FileInputStream(ParametrosDSTE.filenameTopologia);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();
			if(linha != null)
			{
				System.out.println("Nome da Topologia: "+linha);
				linha = br.readLine();
			}
			else
			{
				System.out.println("Arquivo inválido!");
			}
			if(linha != null)
			{
				System.out.println("Número de roteadores: "+linha);
				ParametrosDSTE.ROTEADORES = Integer.parseInt(linha); 
				linha = br.readLine();
			}
			else
			{
				System.out.println("Arquivo inválido!");
			}
			if(linha != null)
			{
				System.out.println("Número de enlaces: "+linha);
				ParametrosDSTE.LINKS = Integer.parseInt(linha); 
				linha = br.readLine();
			}
			else
			{
				System.out.println("Arquivo inválido!");
			}
			this.roteador= new Roteador[ParametrosDSTE.ROTEADORES];
			this.link= new Link[ParametrosDSTE.LINKS];
			

			for(int i=0;i<ParametrosDSTE.ROTEADORES;i++)
			{
				roteador[i]= new Roteador(); 
				roteador[i].ID = i;
				roteador[i].Descricao = "R"+String.format("%0"+String.valueOf(ParametrosDSTE.ROTEADORES).length()+"d", i);
			}
			
			int count=0;
			while(linha != null) {
				int origem = Integer.parseInt(linha.substring(0, linha.indexOf('#')));
				System.out.println("Roteador origem :"+origem);
				String destinos = linha.substring(linha.indexOf('#')+1);
				StringTokenizer st2 = new StringTokenizer(destinos, " ");
		 
				while (st2.hasMoreElements()) {
					int destino=Integer.parseInt((String)st2.nextElement());
					System.out.println("Destino: "+destino+"(Pelo link:"+count+")");
					link[count]=new Link();
					link[count].Descricao = "Physical Link "+count+" ("+roteador[origem].Descricao+"->"+roteador[destino].Descricao+")";
					link[count].ID = count;
					link[count].CustoEnlace = 1;
					link[count].CargaEnlace = 1000;
					link[count].lsrSrc = roteador[origem];
					link[count].lsrDest = roteador[destino];
					count++;
				}
				linha = br.readLine();
			}
			System.out.println("Total de links :"+count);
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}
	
	public void carregarMatrizDeCaminhosArquivo()
	{
		
		try {
						
			FileInputStream stream = new FileInputStream(ParametrosDSTE.filenameMatrizCaminhos);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();
			int count=0;
			while(linha != null) {
				StringTokenizer st2 = new StringTokenizer(linha, "-");
				this.adicionar(Integer.parseInt((String)st2.nextElement()), Integer.parseInt((String)st2.nextElement()), Integer.parseInt((String)st2.nextElement()));
				//System.out.println((String)st2.nextElement()+"-"+(String)st2.nextElement()+"-"+(String)st2.nextElement());
				linha = br.readLine();	
				count++;
			}
				
			
			System.out.println("Total de caminhos :"+count);
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}
	public int getIndiceRoteador(int ID)
	{
		for(int i=0;i<ParametrosDSTE.ROTEADORES;i++)
		{
			if (roteador[i].ID==ID)
					return (i);

		}
		
		return -1;
	}
	
	public Roteador getRoteador(int ID)
	{
		for(int i=0;i<ParametrosDSTE.ROTEADORES;i++)
		{
			if (roteador[i].ID==ID)
					return (roteador[i]);

		}
		
		return null;
	}
	
	public Link [] getLinks(int idRoteador)
	{
		int indice = getIndiceRoteador(idRoteador);
		Link [] aux = new Link[ParametrosDSTE.LINKS];
		int z=0;
		for (int i=0;i<ParametrosDSTE.LINKS;i++)
		{
			if(topologiaDosLinks[i][indice]==1)
			{
				aux[z]=link[i];
				z++;

			}
		}
		return aux;
		
	}
	public String imprimirCaminhos()
	{
		String retorno="";
		retorno+=(" Tabela de caminhos \r\n");
		for (int i=0; i<ParametrosDSTE.ROTEADORES;i++)
		{
			retorno+=(" ==== Roteador "+roteador[i].Descricao+"("+roteador[i].ID+") ====\r\n");
			retorno+=imprimirCaminho(roteador[i].caminhos);
			
		}
		return retorno;
		
	}
	public String imprimirCaminho(Link [][] caminho)
	{
		String retorno="";
		
		for(int i=0;i<ParametrosDSTE.MaxCaminhos&&caminho[i][0]!=null;i++)
		{
			for(int j=0;j<ParametrosDSTE.MaxSaltos&&caminho[i][j]!=null;j++)
			{
				if(j!=0)
					retorno+=(" - ");
				retorno+=(caminho[i][j].Descricao);
			}
			retorno+=("\r\n");
		}
		
		return retorno;
		
	}
	
	public String imprimirCaminho(Link [] caminho)
	{
		String retorno="";

			for(int j=0;caminho[j]!=null&&j<ParametrosDSTE.MaxSaltos;j++)
			{
				if(j!=0)
					retorno+=(" - ");
				retorno+=(caminho[j].Descricao);
			}
		return retorno;
		
	}
	

	
	public String statusLinks()
	{

		String retorno="";

		retorno+=("---------------- Valor Atual dos BCs por Enlace -----------\r\n");
		for(Link aux : link)
			{
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
				retorno+=("LSP Estabelecidas:"+aux.lspEstabelecidas+"\r\n");
				retorno+=("LSP EstabelecidasCT0:"+aux.lspEstabelecidasCT[0]+"\r\n");
				retorno+=("LSP EstabelecidasCT1:"+aux.lspEstabelecidasCT[1]+"\r\n");
				retorno+=("LSP EstabelecidasCT2:"+aux.lspEstabelecidasCT[2]+"\r\n");
				retorno+=("LSP Estabelecidas Total:"+aux.lspEstabelecidasTotal+"\r\n");
				retorno+=("LSP Unbroken:"+aux.lspUnbroken+"\r\n");
				retorno+=("Banda Unbroken:"+aux.bandaUnbroken+"\r\n");
			}	
		retorno+=("\r\n--------------------------------------------------------------\r\n");
		return retorno;
	}
}
