package DSTE;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class BancoDeDados {
	

	public static String filename=null;
	
	public static void setXML(String mensagem, String filename) {
		BancoDeDados.filename=filename;
		gravarArquivoXML(mensagem, filename);

	}
	public static void setXML(String mensagem) {
		if(BancoDeDados.filename!=null)
			gravarArquivoXML(mensagem, BancoDeDados.filename);
		else
			throw new NullPointerException();

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
