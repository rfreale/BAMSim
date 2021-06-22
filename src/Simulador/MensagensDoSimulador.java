package Simulador;

import java.io.*;


public class MensagensDoSimulador {
	private static String  mensagem="";

	public static String getMensagem() {
		return mensagem;
	}

	public static void setMensagem(String mensagem) {
		MensagensDoSimulador.mensagem += mensagem;
	}
	

	 static void gravarArquivoTexto()
	 {
	  try {
	  BufferedWriter writer = new BufferedWriter(new FileWriter(ParametrosDoSimulador.SAIDA_TXT));
	  writer.write(mensagem);
	  writer.newLine();
	  writer.close();  
	 
	  }
	  catch (IOException e) {
	   // TODO Auto-generated catch block
	   System.out.println("Erro na gravação do arquivo");
	  }
	 }


}
