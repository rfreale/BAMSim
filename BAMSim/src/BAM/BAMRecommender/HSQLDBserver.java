/**
 * HSQLDBserver.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 04/07/2007
 */
package BAM.BAMRecommender;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;

import DSTE.ParametrosDSTE;

/**
 * Creates a data base server with the tables for the examples/tests using the HSQLDB library.
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class HSQLDBserver
{
    static boolean initialized = false;

    private static Server server;

    /**
     * Initialize the server
     */
    public static void init()
    {
		if (initialized)
		    return;
		org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).info("Data base initialization ...");

	
		server = new Server();
		
		
		Path directory = Paths.get("saida/database");
		if(ParametrosDSTE.limpaBaseCBR){
		   try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
				   @Override
				   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					   Files.delete(file);
					   return FileVisitResult.CONTINUE;
				   }
	
				   @Override
				   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					   Files.delete(dir);
					   return FileVisitResult.CONTINUE;
				   }
	
			   });
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		server.setDatabaseName(0, "bam");
		server.setDatabasePath(0, "saida/database/bam");
		//server.setDatabasePath(0, "mem:bam;sql.enforce_strict_size=true");
		server.setDatabaseName(1, "bam2");
		server.setDatabasePath(1, "saida/database/bam2");
		//server.setDatabasePath(1, "mem:bam2;sql.enforce_strict_size=true");
		
		server.setLogWriter(null);
		server.setErrWriter(null);
		server.setSilent(true);
		server.start();
	
		initialized = true;
		if(ParametrosDSTE.limpaBaseCBR)
		{
			try
			{
			    Class.forName("org.hsqldb.jdbcDriver");
			    org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).info("Creating data base ...");
			    PrintStream out = new PrintStream(new ByteArrayOutputStream());
			    Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bam", "sa", "");
			    Connection conn2 = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bam2", "sa", "");
			    //conn.prepareStatement("create database bam;").execute();
			   // conn.prepareStatement("use bam;").execute();
			   // conn.prepareStatement("drop table bam;").execute();
			    conn.prepareStatement("create table bam(caseId VARCHAR(15), "
			    		+ "link INTEGER, "
			    		+ "BAMAtual VARCHAR(30), "
		//	    		+ "problema VARCHAR(30), "
			    		/*+ "Janela INTEGER, "
			    		+ "SLAUtilizacaoCT0 DOUBLE, "
			    		+ "SLAUtilizacaoCT1 DOUBLE, "
			    		+ "SLAUtilizacaoCT2 DOUBLE, "
			    		+ "SLABloqueiosCT0 DOUBLE, "
			    		+ "SLABloqueiosCT1 DOUBLE, "
			    		+ "SLABloqueiosCT2 DOUBLE, "
			    		+ "SLAPreempcoesCT0 DOUBLE, "
			    		+ "SLAPreempcoesCT1 DOUBLE, "
			    		+ "SLAPreempcoesCT2 DOUBLE, "
			    		+ "SLADevolucoesCT0 DOUBLE, "
			    		+ "SLADevolucoesCT1 DOUBLE, "
			    		+ "SLADevolucoesCT2 DOUBLE, "*/
			    		+ "BC0 DOUBLE, "
			    		+ "BC1 DOUBLE, "
			    		+ "BC2 DOUBLE, "
			    		+ "utilizacaoDoEnlace DOUBLE, "
			    		+ "utilizacaoDoEnlaceCT0 DOUBLE, "
			    		+ "utilizacaoDoEnlaceCT1 DOUBLE, "
			    		+ "utilizacaoDoEnlaceCT2 DOUBLE, "
			    		+ "numeroDeBloqueios DOUBLE, "
			    		+ "numeroDeBloqueiosCT0 DOUBLE, "
			    		+ "numeroDeBloqueiosCT1 DOUBLE, "
			    		+ "numeroDeBloqueiosCT2 DOUBLE, "
			    		+ "numeroDePreempcoes DOUBLE, "
			    		+ "numeroDePreempcoesCT0 DOUBLE, "
			    		+ "numeroDePreempcoesCT1 DOUBLE, "
			    		+ "numeroDePreempcoesCT2 DOUBLE, "
			    		+ "numeroDeDevolucoes DOUBLE, "
			    		+ "numeroDeDevolucoesCT0 DOUBLE, "
			    		+ "numeroDeDevolucoesCT1 DOUBLE, "
			    		+ "numeroDeDevolucoesCT2 DOUBLE, "
			    		+ "BAMNovo VARCHAR(30), "
			    		+ "aceita BIT);").execute();
			    
			    conn2.prepareStatement("create table bam(caseId VARCHAR(15), "
			    		//+ "gestor VARCHAR(30), "
			    		+ "link INTEGER, "
			    		+ "BAMAtual VARCHAR(30), "
		//	    		+ "problema VARCHAR(30), "
			    		/*+ "Janela INTEGER, "
			    		+ "SLAUtilizacaoCT0 DOUBLE, "
			    		+ "SLAUtilizacaoCT1 DOUBLE, "
			    		+ "SLAUtilizacaoCT2 DOUBLE, "
			    		+ "SLABloqueiosCT0 DOUBLE, "
			    		+ "SLABloqueiosCT1 DOUBLE, "
			    		+ "SLABloqueiosCT2 DOUBLE, "
			    		+ "SLAPreempcoesCT0 DOUBLE, "
			    		+ "SLAPreempcoesCT1 DOUBLE, "
			    		+ "SLAPreempcoesCT2 DOUBLE, "
			    		+ "SLADevolucoesCT0 DOUBLE, "
			    		+ "SLADevolucoesCT1 DOUBLE, "
			    		+ "SLADevolucoesCT2 DOUBLE, "*/
			    		+ "BC0 DOUBLE, "
			    		+ "BC1 DOUBLE, "
			    		+ "BC2 DOUBLE, "
			    		+ "utilizacaoDoEnlace DOUBLE, "
			    		+ "utilizacaoDoEnlaceCT0 DOUBLE, "
			    		+ "utilizacaoDoEnlaceCT1 DOUBLE, "
			    		+ "utilizacaoDoEnlaceCT2 DOUBLE, "
			    		+ "numeroDeBloqueios DOUBLE, "
			    		+ "numeroDeBloqueiosCT0 DOUBLE, "
			    		+ "numeroDeBloqueiosCT1 DOUBLE, "
			    		+ "numeroDeBloqueiosCT2 DOUBLE, "
			    		+ "numeroDePreempcoes DOUBLE, "
			    		+ "numeroDePreempcoesCT0 DOUBLE, "
			    		+ "numeroDePreempcoesCT1 DOUBLE, "
			    		+ "numeroDePreempcoesCT2 DOUBLE, "
			    		+ "numeroDeDevolucoes DOUBLE, "
			    		+ "numeroDeDevolucoesCT0 DOUBLE, "
			    		+ "numeroDeDevolucoesCT1 DOUBLE, "
			    		+ "numeroDeDevolucoesCT2 DOUBLE, "
			    		+ "BAMNovo VARCHAR(30), "
			    		+ "aceita BIT);").execute();
			    
			   if(ParametrosDSTE.baseCBRManual){
				   carregarBaseCRBManualP(conn);
				   carregarBaseCRBManualN(conn2);  
			   }
			   else{
				   carregarBaseCBRDeArquivoP(conn);
				   carregarBaseCBRDeArquivoN(conn2);
			   }
				   
				
				 
		
			    /*SqlFile file = new SqlFile(new
			    File(FileIO.findFile("BAM/BAMRecommender/bam.sql").getFile()),false,new HashMap());
			    file.execute(conn,out,out, true);*/
			    
			    
			    
			    org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).info("Data base generation finished");
			    
			} catch (Exception e)
			{
			    org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).error(e);
			}
		}
		org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).info("Data base initialization finished");
    }

    /**
     * Shutdown the server
     */
    public static void shutDown()
    {

	if (initialized)
	{
	    server.stop();
	    initialized = false;
	}
    }

    /**
     * Testing method
     */
    public static void main(String[] args)
    {
	HSQLDBserver.init();
	HSQLDBserver.shutDown();
	System.exit(0);
	
    }
    
    
    public static void carregarBaseCBRDeArquivoP(Connection conn) throws SQLException
	{
		
		try {
			FileInputStream stream = new FileInputStream(ParametrosDSTE.filenameBaseCBRP);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();

			while(linha != null) {
				conn.prepareStatement(linha).execute();
				linha = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}
    
    public static void carregarBaseCBRDeArquivoN(Connection conn2) throws SQLException
	{
		
		try {
			FileInputStream stream = new FileInputStream(ParametrosDSTE.filenameBaseCBRN);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();

			while(linha != null) {
				conn2.prepareStatement(linha).execute();
				linha = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}
    
    
	
    private static void carregarBaseCRBManualP(Connection conn) throws SQLException
    {
    	/////////************************************** ID    Nome Gerente    BAMAtual           Problema          Utilização     Bloqueio       Preempção        Devolução        Largura de banda   Utilização     Bloqueio       Preempção        Devolução         Solução      
	    

    }
    
    private static void carregarBaseCRBManualN(Connection conn2) throws SQLException
    {
    	/////////************************************** ID    Nome Gerente    BAMAtual           Problema          Utilização     Bloqueio       Preempção        Devolução        Largura de banda   Utilização     Bloqueio       Preempção        Devolução         Solução      
	    

    }

}
