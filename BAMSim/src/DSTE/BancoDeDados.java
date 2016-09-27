package DSTE;
import java.io.*;

import Simulador.*;

import java.util.Date;


public class BancoDeDados {
	

	
	
	public static void setXML(String mensagem, String filename) {
		
		gravarArquivoXML(mensagem, filename);

	}
	

	 public static void gravarArquivoXML(String mensagem, String filename)
	 {
		  try {
			  File outputfile = new File("saida/"+filename+"/"+filename+".xml");
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




}
