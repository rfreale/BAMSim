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
		// Matriz de caminhos R0 para R1 
		//0->1
		this.adicionar(0,0,0);
		
		
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
		
		roteador[0]= new Roteador(); 
		roteador[0].ID = 0;
		roteador[0].Descricao = "S1";
		roteador[1]= new Roteador(); 
		roteador[1].ID = 1;
		roteador[1].Descricao = "D1";
		
		
		
		link[0]=new Link();
		link[0].Descricao = "S1->D1";
		link[0].ID = 1;
		link[0].CustoEnlace = 1;
		link[0].CargaEnlace = 1000;
		link[0].lsrSrc = roteador[0];
		link[0].lsrDest = roteador[1];
		
		

	}
	public void carregarTopologiaArquivo()
	{
		
		try {
			FileInputStream stream = new FileInputStream("D:\\Dropbox\\Java\\workspace\\BAMSim\\NTT-55n-144e.txt");
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
				System.out.println("Arquivo inv�lido!");
			}
			if(linha != null)
			{
				System.out.println("N�mero de roteadores: "+linha);
				ParametrosDSTE.ROTEADORES = Integer.parseInt(linha); 
				linha = br.readLine();
			}
			else
			{
				System.out.println("Arquivo inv�lido!");
			}
			if(linha != null)
			{
				System.out.println("N�mero de enlaces: "+linha);
				ParametrosDSTE.LINKS = Integer.parseInt(linha); 
				linha = br.readLine();
			}
			else
			{
				System.out.println("Arquivo inv�lido!");
			}
			this.roteador= new Roteador[ParametrosDSTE.ROTEADORES];
			this.link= new Link[ParametrosDSTE.LINKS];
			
			for(int i=0;i<ParametrosDSTE.ROTEADORES;i++)
			{
				roteador[i]= new Roteador(); 
				roteador[i].ID = i;
				roteador[i].Descricao = "R"+i;
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
					link[count].Descricao = roteador[origem].Descricao+"->"+roteador[destino].Descricao;
					link[count].ID = count;
					link[count].CustoEnlace = 1;
					link[count].CargaEnlace = 622;
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
			}	
		retorno+=("\r\n--------------------------------------------------------------\r\n");
		return retorno;
	}
}
