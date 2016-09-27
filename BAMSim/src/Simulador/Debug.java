package Simulador;
import java.io.*;

import Simulador.*;

import java.util.Date;


public class Debug {
	
	public static String filename = new Date().getTime()+"";

	public static void setMensagem(String mensagem) {
		setMensagem(mensagem, 0, 0);
	}
	
	public static void setMensagem(String mensagem, int debugFile, int debugConsole) {
		if(ParametrosDoSimulador.DebugFile>=debugFile)
			gravarArquivoTexto(mensagem);
		if(ParametrosDoSimulador.DebugConsole>=debugConsole)
			System.out.println(mensagem);
	}
	

	 public static void gravarArquivoTexto(String mensagem)
	 {
		  try {

			  File outputfile = new File("saida/"+filename+"/"+filename+".txt");
			  outputfile.getParentFile().mkdirs();
			  
			  BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile,true));
			  
			  writer.write(mensagem);
			  writer.newLine();
			  writer.close();  
		 
		  }
		  catch (IOException e) {
			   // TODO Auto-generated catch block
			   System.out.println("Erro na gravação do arquivo:"+e.toString());
		  }
	 }
/*
	 // Imprime Lista de LSPs Preemptados Atual
	String imprime_LSPs_Preemptados(Lista L) // Imprime elemento
	{	
		No aux;
		String texto="";

		aux = L.primeiro;
		while(aux.prox!=null)
		{
			//printf("%d -> ", aux->item.dest);
			//printf("LSP%d:CT%d -> ", aux->prox->item.NumParOD, aux->prox->item.CT);
			texto+="LSP"+((Lsp)aux.prox.item).caminho.NumParOD+":CT"+((Lsp)aux.prox.item).CT+" -> ";
			aux = aux.prox;
		}
		return texto;
	}

	*/



}
