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
        org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).info("Creating data base ...");

	server = new Server();
	
	
	Path directory = Paths.get("database");
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
	server.setDatabaseName(0, "bam");
	server.setDatabasePath(0, "database/bam");
	//server.setDatabasePath(0, "mem:bam;sql.enforce_strict_size=true");
	server.setDatabaseName(1, "bam2");
	server.setDatabasePath(1, "database/bam2");
	//server.setDatabasePath(1, "mem:bam2;sql.enforce_strict_size=true");
	
	server.setLogWriter(null);
	server.setErrWriter(null);
	server.setSilent(true);
	server.start();

	initialized = true;
	try
	{
	    Class.forName("org.hsqldb.jdbcDriver");

	    PrintStream out = new PrintStream(new ByteArrayOutputStream());
	    Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bam", "sa", "");
	    Connection conn2 = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bam2", "sa", "");
	    //conn.prepareStatement("create database bam;").execute();
	   // conn.prepareStatement("use bam;").execute();
	   // conn.prepareStatement("drop table bam;").execute();
	    conn.prepareStatement("create table bam(caseId VARCHAR(15), "
	    		+ "link INTEGER, "
	    		+ "gestor VARCHAR(30), "
	    		+ "BAMAtual VARCHAR(30), "
//	    		+ "problema VARCHAR(30), "
	    		+ "Janela INTEGER, "
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
	    		+ "SLADevolucoesCT2 DOUBLE, "
	    		+ "BC0 DOUBLE, "
	    		+ "BC1 DOUBLE, "
	    		+ "BC2 DOUBLE, "
	    		+ "utilizacaoDoEnlaceCT0 DOUBLE, "
	    		+ "utilizacaoDoEnlaceCT1 DOUBLE, "
	    		+ "utilizacaoDoEnlaceCT2 DOUBLE, "
	    		+ "numeroDeBloqueiosCT0 DOUBLE, "
	    		+ "numeroDeBloqueiosCT1 DOUBLE, "
	    		+ "numeroDeBloqueiosCT2 DOUBLE, "
	    		+ "numeroDePreempcoesCT0 DOUBLE, "
	    		+ "numeroDePreempcoesCT1 DOUBLE, "
	    		+ "numeroDePreempcoesCT2 DOUBLE, "
	    		+ "numeroDeDevolucoesCT0 DOUBLE, "
	    		+ "numeroDeDevolucoesCT1 DOUBLE, "
	    		+ "numeroDeDevolucoesCT2 DOUBLE, "
	    		+ "BAMNovo VARCHAR(30), "
	    		+ "aceita BIT);").execute();
	    
	    conn2.prepareStatement("create table bam(caseId VARCHAR(15), "
	    		+ "link INTEGER, "
	    		+ "gestor VARCHAR(30), "
	    		+ "BAMAtual VARCHAR(30), "
//	    		+ "problema VARCHAR(30), "
	    		+ "Janela INTEGER, "
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
	    		+ "SLADevolucoesCT2 DOUBLE, "
	    		+ "BC0 DOUBLE, "
	    		+ "BC1 DOUBLE, "
	    		+ "BC2 DOUBLE, "
	    		+ "utilizacaoDoEnlaceCT0 DOUBLE, "
	    		+ "utilizacaoDoEnlaceCT1 DOUBLE, "
	    		+ "utilizacaoDoEnlaceCT2 DOUBLE, "
	    		+ "numeroDeBloqueiosCT0 DOUBLE, "
	    		+ "numeroDeBloqueiosCT1 DOUBLE, "
	    		+ "numeroDeBloqueiosCT2 DOUBLE, "
	    		+ "numeroDePreempcoesCT0 DOUBLE, "
	    		+ "numeroDePreempcoesCT1 DOUBLE, "
	    		+ "numeroDePreempcoesCT2 DOUBLE, "
	    		+ "numeroDeDevolucoesCT0 DOUBLE, "
	    		+ "numeroDeDevolucoesCT1 DOUBLE, "
	    		+ "numeroDeDevolucoesCT2 DOUBLE, "
	    		+ "BAMNovo VARCHAR(30), "
	    		+ "aceita BIT);").execute();
	    
	   if(ParametrosDSTE.baseCBRManual)
	    	carregarBaseCRBManual(conn);
	   else
		   carregarBaseCBRDeArquivo(conn);
		
		 

	    /*SqlFile file = new SqlFile(new
	    File(FileIO.findFile("BAM/BAMRecommender/bam.sql").getFile()),false,new HashMap());
	    file.execute(conn,out,out, true);*/
	    
	    
	    
	    org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).info("Data base generation finished");
	    
	} catch (Exception e)
	{
	    org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).error(e);
	}

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
    public static void carregarBaseCBRDeArquivo(Connection conn) throws SQLException
	{
		
		try {
			FileInputStream stream = new FileInputStream(ParametrosDSTE.filenameBaseCBR);
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
	
    private static void carregarBaseCRBManual(Connection conn) throws SQLException
    {
    	/////////************************************** ID    Nome Gerente    BAMAtual           Problema          Utilização     Bloqueio       Preempção        Devolução        Largura de banda   Utilização     Bloqueio       Preempção        Devolução         Solução      
	    
	    conn.prepareStatement("insert into bam values('1',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.057,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('2',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.186,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('3',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.296,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('4',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.336,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('5',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.326,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('6',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.235,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('7',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.292,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('8',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.357,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('9',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.291,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('10',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.315,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('11',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.371,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('12',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.393,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('13',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.497,0,0,0.208333333333333,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('14',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.495,0,0,0.636363636363636,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('15',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.673267326732673,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('16',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.499,0,0,0.614906832298136,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('17',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.499,0,0,0.655555555555555,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('18',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.496,0,0,0.618811881188118,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('19',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.727659574468085,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('20',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.498,0,0,0.658291457286432,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('21',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.687830687830687,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('22',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.498,0,0,0.735849056603773,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('23',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.693693693693693,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('24',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.653658536585365,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('25',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.033,0,0.5,0,0,0.609625668449197,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('26',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.08,0,0.498,0,0,0.618090452261306,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('27',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.098,0,0.498,0,0,0.723076923076923,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('28',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.149,0,0.499,0,0,0.643243243243243,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('29',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0,0.497,0,0,0.605882352941176,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('30',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.138,0,0.498,0,0,0.69047619047619,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('31',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.142,0,0.5,0,0,0.657004830917874,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('32',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.172,0,0.497,0,0,0.639344262295082,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('33',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.163,0,0.499,0,0,0.675555555555555,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('34',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.188,0,0.5,0,0,0.642857142857142,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('35',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.171,0,0.5,0.142857142857142,0,0.78695652173913,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('36',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.172,0,0.495,0,0,0.621890547263681,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('37',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0,0.499,0.513513513513513,0,0.666666666666666,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('38',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0,0.5,0.808080808080808,0,0.5,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('39',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.499,0.706349206349206,0,0.330188679245283,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('40',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.5,0.803418803418803,0,0.515873015873015,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('41',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0,0.498,0.735042735042735,0,0.468253968253968,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('42',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.498,0.678571428571428,0,0.546875,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('43',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.494,0.796747967479674,0,0.345454545454545,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('44',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.494,0.722689075630252,0,0.435114503816793,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('45',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.499,0.707070707070707,0,0.523076923076923,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('46',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0,0.5,0.828125,0,0.416,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('47',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0,0.498,0.756521739130434,0,0.520661157024793,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('48',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0,0.499,0.718181818181818,0,0.517985611510791,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('49',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.07,0.493,0.825,0,0.543307086614173,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('50',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.102,0.497,0.751937984496124,0,0.466101694915254,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('51',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.17,0.498,0.720430107526881,0,0.432989690721649,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('52',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.197,0.5,0.763636363636363,0,0.601503759398496,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('53',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.179,0.495,0.771428571428571,0,0.472,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('54',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.15,0.499,0.72072072072072,0,0.429752066115702,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('55',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.19,0.496,0.817518248175182,0,0.379629629629629,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('56',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.203,0.499,0.765625,0,0.460176991150442,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('57',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.214,0.498,0.654411764705882,0,0.535433070866141,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('58',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.222,0.5,0.782608695652174,0,0.45045045045045,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('59',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.159,0.497,0.68595041322314,0,0.31578947368421,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('60',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.222,0.499,0.738317757009345,0,0.473684210526315,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('61',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.297,0.492,0.756097560975609,0.15,0.471544715447154,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('62',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.3,0.496,0.785046728971962,0.601941747572815,0.473684210526315,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('63',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.3,0.496,0.63063063063063,0.762589928057554,0.349514563106796,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('64',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.295,0.49,0.777777777777777,0.558333333333333,0.429824561403508,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('65',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0.499,0.743362831858407,0.651785714285714,0.401639344262295,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('66',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.297,0.497,0.706896551724137,0.533333333333333,0.495726495726495,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('67',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.298,0.494,0.759259259259259,0.650943396226415,0.59375,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('68',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.3,0.499,0.724489795918367,0.794520547945205,0.368932038834951,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('69',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.3,0.5,0.747747747747747,0.754237288135593,0.576642335766423,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('70',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.299,0.495,0.773109243697479,0.576576576576576,0.44776119402985,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('71',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.3,0.497,0.694656488549618,0.70754716981132,0.263736263736263,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('72',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.298,0.496,0.725490196078431,0.721311475409836,0.444444444444444,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('73',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.3,0.491,0.896825396825396,0.743362831858407,0.319587628865979,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('74',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.299,0.321,0.710526315789473,0.514018691588785,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('75',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.298,0.106,0.764227642276422,0.631578947368421,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('76',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0.045,0.846153846153846,0.722222222222222,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('77',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0.008,0.709090909090909,0.644927536231884,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('78',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.297,0.008,0.780487804878048,0.647058823529411,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('79',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.298,0,0.8099173553719,0.65891472868217,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('80',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.299,0,0.732673267326732,0.68103448275862,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('81',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.296,0,0.793650793650793,0.619402985074626,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('82',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.296,0,0.714285714285714,0.647727272727272,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('83',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0,0.773109243697479,0.698113207547169,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('84',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.297,0,0.612903225806451,0.657894736842105,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('85',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.299,0,0.838095238095238,0.572815533980582,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('86',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.29,0,0.75,0.0454545454545454,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('87',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.263,0,0.864285714285714,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('88',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.224,0,0.853658536585365,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('89',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.194,0,0.8,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('90',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.205,0,0.741666666666666,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('91',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.19,0,0.8130081300813,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('92',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.182,0,0.792792792792792,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('93',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.181,0,0.75968992248062,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('94',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.16,0,0.701612903225806,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('95',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.159,0,0.809523809523809,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('96',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.145,0,0.796610169491525,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('97',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.158,0,0.855670103092783,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('98',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.164,0.119,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('99',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.028,0.042,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('100',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.02,0.014,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('101',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.01,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('102',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('103',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('104',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('105',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('106',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('107',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('108',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('109',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.015,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('110',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.075,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('111',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.07,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('112',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.122,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('113',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.171,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('114',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.192,0,0,0.125,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('115',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.164,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('116',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.105,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('117',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('118',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.144,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('119',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.128,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('120',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.162,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('121',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.038,0,0.446428571428571,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('122',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.104,0,0.787671232876712,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('123',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.201,0,0.776978417266187,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('124',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.23,0,0.783018867924528,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('125',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.156,0,0.759259259259259,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('126',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.135,0,0.777777777777777,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('127',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.218,0,0.73076923076923,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('128',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.159,0,0.779661016949152,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('129',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.227,0,0.75221238938053,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('130',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.241,0,0.744680851063829,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('131',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.21,0,0.796875,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('132',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.182,0,0.727272727272727,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('133',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.299,0,0.73469387755102,0.0714285714285714,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('134',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.3,0,0.793388429752066,0.653225806451612,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('135',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.299,0,0.7734375,0.686868686868686,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('136',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.299,0,0.776923076923076,0.734848484848484,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('137',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.298,0,0.76978417266187,0.721428571428571,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('138',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0,0.748466257668711,0.741666666666666,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('139',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.197,0.299,0,0.754716981132075,0.6953125,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('140',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.298,0,0.8515625,0.612244897959183,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('141',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.298,0,0.769230769230769,0.678260869565217,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('142',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0,0.818181818181818,0.702290076335877,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('143',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.3,0,0.825396825396825,0.681159420289855,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('144',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0,0.808695652173913,0.64,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('145',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.3,0,0.678160919540229,0.736434108527131,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('146',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.194,0.298,0,0.074074074074074,0.75968992248062,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('147',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.188,0.297,0,0.272727272727272,0.698529411764705,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('148',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.17,0.3,0,0,0.580952380952381,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('149',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.181,0.3,0,0,0.696,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('150',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.162,0.298,0,0.0769230769230769,0.653465346534653,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('151',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.196,0.298,0,0.208333333333333,0.659090909090909,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('152',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.181,0.3,0,0.117647058823529,0.632075471698113,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('153',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0.297,0,0,0.558558558558558,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('154',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.159,0.296,0,0,0.446601941747572,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('155',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.194,0.299,0,0.0476190476190476,0.645669291338582,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('156',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.299,0,0.275862068965517,0.654545454545454,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('157',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.192,0.298,0,0.111111111111111,0.825688073394495,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('158',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.063,0.196,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('159',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.028,0.209,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('160',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.014,0.178,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('161',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.236,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('162',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.227,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('163',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.172,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('164',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.1,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('165',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.097,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('166',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.179,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('167',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.188,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('168',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.148,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('169',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.299,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('170',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0,0,0.65040650406504,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('171',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0,0,0.622047244094488,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('172',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0,0,0.714285714285714,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('173',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0,0,0.661971830985915,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('174',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.299,0,0,0.615384615384615,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('175',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0,0,0.646551724137931,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('176',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0,0,0.678899082568807,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('177',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0,0,0.723076923076923,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('178',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.299,0,0,0.666666666666666,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('179',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0,0,0.671232876712328,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('180',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0,0,0.666666666666666,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('181',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.297,0.088,0,0.68595041322314,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('182',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.295,0.498,0,0.583333333333333,0.0348837209302325,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('183',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0.492,0,0.387096774193548,0.1875,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('184',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.294,0.499,0,0.392857142857142,0.310344827586206,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('185',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.294,0.48,0,0.136363636363636,0.0862068965517241,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('186',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0.499,0,0.608108108108108,0.195652173913043,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('187',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.297,0.499,0,0.647058823529411,0.111111111111111,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('188',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.294,0.5,0,0.24074074074074,0.207792207792207,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('189',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.299,0.489,0,0.455882352941176,0.126760563380281,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('190',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.3,0.481,0,0.424242424242424,0.114754098360655,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('191',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0.471,0,0.318840579710144,0.114754098360655,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('192',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0.495,0,0.345238095238095,0.03125,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('193',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.079,0.298,0.476,0,0.291666666666666,0.0666666666666666,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('194',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.195,0.243,0.318,0.307692307692307,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('195',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.207,0.156,0.591549295774647,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('196',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.144,0.142,0.55,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('197',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.124,0.128,0.840579710144927,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('198',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.151,0.142,0.59375,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('199',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.121,0.154,0.523809523809523,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('200',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.146,0.113,0.540983606557377,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('201',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.195,0.139,0.103,0.679012345679012,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('202',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.181,0.13,0.73972602739726,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('203',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.178,0.191,0.554054054054054,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('204',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.147,0.153,0.59090909090909,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('205',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.131,0.146,0.694444444444444,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('206',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.107,0.096,0.111,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('207',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.042,0.015,0.031,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('208',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.009,0,0.026,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('209',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.015,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('210',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('211',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('212',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('213',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('214',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('215',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('216',0,'Conservador','NoPreemptionMAM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();










	    conn.prepareStatement("insert into bam values('217',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.057,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('218',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.186,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('219',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.296,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('220',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.336,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('221',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.326,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('222',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.235,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('223',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.292,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('224',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.357,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('225',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.291,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('226',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.315,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('227',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.371,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('228',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.393,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('229',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.497,0,0,0.208333333333333,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('230',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.495,0,0,0.636363636363636,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('231',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.673267326732673,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('232',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.499,0,0,0.614906832298136,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('233',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.499,0,0,0.655555555555555,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('234',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.496,0,0,0.618811881188118,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('235',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.727659574468085,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('236',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.498,0,0,0.658291457286432,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('237',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.687830687830687,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('238',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.498,0,0,0.735849056603773,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('239',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.693693693693693,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('240',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.5,0,0,0.653658536585365,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('241',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.033,0,0.5,0,0,0.609625668449197,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('242',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.08,0,0.498,0,0,0.618090452261306,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('243',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.098,0,0.498,0,0,0.723076923076923,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('244',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.149,0,0.499,0,0,0.643243243243243,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('245',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0,0.497,0,0,0.605882352941176,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('246',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.138,0,0.498,0,0,0.69047619047619,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('247',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.142,0,0.5,0,0,0.657004830917874,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('248',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.172,0,0.497,0,0,0.639344262295082,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('249',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.163,0,0.499,0,0,0.675555555555555,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('250',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.188,0,0.5,0,0,0.642857142857142,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('251',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.196,0,0.5,0,0,0.78695652173913,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('252',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.172,0,0.495,0,0,0.621890547263681,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('253',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.331,0,0.499,0,0,0.666666666666666,0,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('254',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.514,0,0.5,0.272727272727272,0,0.5,0.0388349514563106,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('255',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.521,0,0.499,0.317460317460317,0,0.330188679245283,0.108695652173913,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('256',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.499,0,0.5,0.461538461538461,0,0.515873015873015,0.110169491525423,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('257',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.507,0,0.498,0.264957264957264,0,0.468253968253968,0.0496453900709219,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('258',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.489,0,0.498,0.178571428571428,0,0.546875,0.075,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('259',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.508,0,0.494,0.32520325203252,0,0.345454545454545,0.159090909090909,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('260',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.549,0,0.494,0.310924369747899,0,0.435114503816793,0.177777777777777,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('261',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.5,0,0.499,0.303030303030303,0,0.523076923076923,0.0826446280991735,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('262',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.503,0,0.5,0.4140625,0,0.416,0.169354838709677,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('263',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.502,0,0.498,0.356521739130434,0,0.520661157024793,0.0703125,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('264',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.521,0,0.499,0.254545454545454,0,0.517985611510791,0.0935251798561151,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('265',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.507,0.07,0.493,0.383333333333333,0,0.543307086614173,0.140625,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('266',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.426,0.102,0.497,0.333333333333333,0,0.466101694915254,0.145038167938931,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('267',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.44,0.17,0.498,0.376344086021505,0,0.432989690721649,0.213592233009708,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('268',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.394,0.197,0.5,0.354545454545454,0,0.601503759398496,0.219047619047619,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('269',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.348,0.179,0.495,0.485714285714285,0,0.472,0.31578947368421,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('270',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.407,0.15,0.499,0.306306306306306,0,0.429752066115702,0.169642857142857,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('271',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.365,0.19,0.496,0.48905109489051,0,0.379629629629629,0.275862068965517,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('272',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.325,0.203,0.499,0.53125,0,0.460176991150442,0.293478260869565,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('273',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.331,0.214,0.498,0.470588235294117,0,0.535433070866141,0.23076923076923,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('274',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.346,0.222,0.5,0.434782608695652,0,0.45045045045045,0.260416666666666,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('275',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.397,0.159,0.497,0.347107438016528,0,0.31578947368421,0.184210526315789,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('276',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.346,0.222,0.499,0.364485981308411,0,0.473684210526315,0.252252252252252,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('277',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.347,0.304,0.492,0.455284552845528,0.125,0.471544715447154,0.443396226415094,0.0192307692307692,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('278',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.219,0.324,0.496,0.383177570093457,0.45631067961165,0.473684210526315,0.397727272727272,0.149425287356321,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('279',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.22,0.374,0.496,0.423423423423423,0.575539568345323,0.349514563106796,0.306818181818181,0.311827956989247,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('280',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.202,0.36,0.49,0.472222222222222,0.333333333333333,0.429824561403508,0.355263157894736,0.228070175438596,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('281',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.21,0.319,0.499,0.47787610619469,0.464285714285714,0.401639344262295,0.3625,0.275510204081632,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('282',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.235,0.306,0.497,0.517241379310344,0.361904761904761,0.495726495726495,0.45,0.177083333333333,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('283',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.208,0.303,0.494,0.472222222222222,0.471698113207547,0.59375,0.32051282051282,0.193181818181818,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('284',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.205,0.327,0.499,0.418367346938775,0.493150684931506,0.368932038834951,0.3125,0.305555555555555,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('285',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.205,0.303,0.5,0.522522522522522,0.508474576271186,0.576642335766423,0.285714285714285,0.257731958762886,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('286',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.215,0.315,0.495,0.411764705882352,0.378378378378378,0.44776119402985,0.46875,0.174757281553398,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('287',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.226,0.329,0.497,0.435114503816793,0.415094339622641,0.263736263736263,0.414141414141414,0.244680851063829,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('288',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.203,0.302,0.496,0.392156862745098,0.475409836065573,0.444444444444444,0.37037037037037,0.267326732673267,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('289',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.215,0.37,0.491,0.523809523809523,0.442477876106194,0.319587628865979,0.402439024390243,0.224489795918367,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('290',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.265,0.592,0.321,0.403508771929824,0.0934579439252336,0,0.355555555555555,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('291',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.217,0.742,0.106,0.430894308943089,0.166666666666666,0,0.447916666666666,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('292',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.222,0.777,0.045,0.546153846153846,0.325396825396825,0,0.385542168674698,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('293',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.241,0.791,0.008,0.372727272727272,0.253623188405797,0,0.366666666666666,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('294',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.233,0.782,0.008,0.398373983739837,0.243697478991596,0,0.329896907216494,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('295',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.206,0.8,0,0.504132231404958,0.325581395348837,0,0.447058823529411,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('296',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.227,0.8,0,0.485148514851485,0.310344827586206,0,0.378378378378378,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('297',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.239,0.788,0,0.365079365079365,0.261194029850746,0,0.50943396226415,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('298',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.299,0.774,0,0.386554621848739,0.0795454545454545,0,0.440860215053763,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('299',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.213,0.789,0,0.478991596638655,0.273584905660377,0,0.321428571428571,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('300',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.209,0.798,0,0.290322580645161,0.175438596491228,0,0.325842696629213,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('301',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.271,0.796,0,0.342857142857142,0.116504854368932,0,0.433333333333333,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('302',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.638,0.56,0,0.166666666666666,0,0,0.0078125,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('303',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.728,0.344,0,0.371428571428571,0,0,0.0666666666666666,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('304',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.771,0.239,0,0.300813008130081,0,0,0.0625,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('305',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.852,0.194,0,0.142857142857142,0,0,0.00588235294117647,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('306',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.83,0.205,0,0.225,0,0,0.0239520958083832,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('307',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.836,0.19,0,0.227642276422764,0,0,0.0239520958083832,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('308',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.822,0.182,0,0.153153153153153,0,0,0.0289017341040462,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('309',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.869,0.181,0,0.217054263565891,0,0,0.0388888888888888,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('310',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.846,0.16,0,0.0887096774193548,0,0,0.0149253731343283,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('311',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.891,0.159,0,0.104761904761904,0,0,0.0168539325842696,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('312',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.875,0.145,0,0.135593220338983,0,0,0.00523560209424083,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('313',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.845,0.158,0,0.144329896907216,0,0,0.0239520958083832,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('314',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.607,0.119,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('315',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.16,0.042,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('316',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.041,0.014,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('317',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.01,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('318',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('319',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('320',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('321',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('322',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('323',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('324',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('325',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.015,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('326',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.075,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('327',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.07,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('328',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.122,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('329',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.171,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('330',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.216,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('331',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.188,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('332',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.105,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('333',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('334',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.144,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('335',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.128,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('336',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.162,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('337',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.416,0.038,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('338',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.881,0.104,0,0.0821917808219178,0,0,0.00561797752808988,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('339',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.852,0.201,0,0.244604316546762,0,0,0.0261780104712041,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('340',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.792,0.23,0,0.20754716981132,0,0,0.0552147239263803,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('341',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.883,0.156,0,0.0277777777777777,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('342',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.858,0.135,0,0.0769230769230769,0,0,0.00507614213197969,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('343',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.789,0.218,0,0.192307692307692,0,0,0.0174418604651162,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('344',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.81,0.159,0,0.110169491525423,0,0,0.00546448087431694,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('345',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.767,0.227,0,0.11504424778761,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('346',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.755,0.241,0,0.0957446808510638,0,0,0.00609756097560975,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('347',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.769,0.21,0,0.140625,0,0,0.0337078651685393,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('348',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.821,0.182,0,0.0272727272727272,0,0,0.00546448087431694,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('349',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.835,0.331,0,0.132653061224489,0,0,0.0359281437125748,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('350',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.497,0.787,0,0.52892561983471,0.0403225806451612,0,0.601626016260162,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('351',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.235,0.788,0,0.46875,0.202020202020202,0,0.329545454545454,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('352',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.284,0.793,0,0.446153846153846,0.265151515151515,0,0.381443298969072,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('353',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.22,0.8,0,0.58273381294964,0.357142857142857,0,0.391304347826087,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('354',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.226,0.774,0,0.54601226993865,0.308333333333333,0,0.45360824742268,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('355',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.224,0.794,0,0.424528301886792,0.328125,0,0.411764705882352,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('356',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.327,0.782,0,0.4296875,0.0714285714285714,0,0.385416666666666,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('357',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.267,0.796,0,0.495726495726495,0.191304347826086,0,0.5,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('358',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.232,0.79,0,0.446280991735537,0.274809160305343,0,0.459770114942528,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('359',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.286,0.8,0,0.484126984126984,0.17391304347826,0,0.651685393258427,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('360',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.266,0.797,0,0.4,0.32,0,0.420454545454545,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('361',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.255,0.793,0,0.344827586206896,0.255813953488372,0,0.402439024390243,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('362',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.189,0.778,0,0.037037037037037,0.333333333333333,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('363',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.198,0.799,0,0.0909090909090909,0.220588235294117,0,0.1,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('364',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.181,0.79,0,0,0.114285714285714,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('365',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.181,0.792,0,0,0.264,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('366',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.162,0.797,0,0,0.207920792079207,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('367',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.21,0.795,0,0.0416666666666666,0.325757575757575,0,0.0769230769230769,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('368',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.796,0,0,0.198113207547169,0,0.054054054054054,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('369',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.124,0.793,0,0,0.045045045045045,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('370',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.159,0.794,0,0,0.0679611650485436,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('371',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.194,0.798,0,0,0.299212598425196,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('372',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.235,0.798,0,0.0344827586206896,0.127272727272727,0,0.113636363636363,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('373',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.205,0.796,0,0,0.403669724770642,0,0.037037037037037,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('374',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.063,0.515,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('375',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.028,0.339,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('376',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.014,0.189,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('377',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.236,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('378',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.227,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('379',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.172,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('380',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.1,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('381',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.097,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('382',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.179,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('383',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.188,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('384',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.148,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('385',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.299,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('386',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.735,0,0,0.024390243902439,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('387',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.79,0,0,0.173228346456692,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('388',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.798,0,0,0.35,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('389',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.8,0,0,0.373239436619718,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('390',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.787,0,0,0.10576923076923,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('391',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.791,0,0,0.0517241379310344,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('392',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.794,0,0,0.36697247706422,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('393',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.796,0,0,0.253846153846153,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('394',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.799,0,0,0.148148148148148,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('395',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.794,0,0,0.253424657534246,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('396',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.796,0,0,0.208333333333333,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('397',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.785,0.088,0,0.223140495867768,0,0,0.0171428571428571,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('398',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.486,0.498,0,0.361111111111111,0.0348837209302325,0,0.338983050847457,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('399',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.329,0.492,0,0.225806451612903,0.1875,0,0.10126582278481,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('400',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.298,0.499,0,0.196428571428571,0.310344827586206,0,0.116883116883116,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('401',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.315,0.48,0,0.0227272727272727,0.0862068965517241,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('402',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.348,0.499,0,0.243243243243243,0.195652173913043,0,0.238636363636363,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('403',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.338,0.499,0,0.411764705882352,0.111111111111111,0,0.15,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('404',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.293,0.5,0,0.148148148148148,0.207792207792207,0,0.119047619047619,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('405',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.324,0.489,0,0.161764705882352,0.126760563380281,0,0.166666666666666,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('406',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.388,0.481,0,0.272727272727272,0.114754098360655,0,0.0975609756097561,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('407',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.378,0.471,0,0.159420289855072,0.114754098360655,0,0.119565217391304,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('408',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.343,0.495,0,0.226190476190476,0.03125,0,0.11578947368421,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('409',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.079,0.322,0.476,0,0.104166666666666,0.0666666666666666,0,0.106666666666666,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('410',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.332,0.279,0.318,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('411',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.436,0.238,0.156,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('412',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.465,0.144,0.142,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('413',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.606,0.124,0.128,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('414',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.649,0.151,0.142,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('415',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.577,0.121,0.154,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('416',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.563,0.146,0.113,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('417',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.605,0.139,0.103,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('418',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.636,0.181,0.13,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('419',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.575,0.178,0.191,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('420',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.638,0.147,0.153,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('421',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.701,0.131,0.146,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('422',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.46,0.096,0.111,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('423',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.144,0.015,0.031,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('424',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.028,0,0.026,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('425',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.015,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('426',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('427',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('428',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('429',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('430',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('431',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('432',0,'Conservador','PreemptionRDM',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();



	    conn.prepareStatement("insert into bam values('433',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.057,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('434',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.186,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('435',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.296,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('436',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.336,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('437',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.326,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('438',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.235,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('439',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.292,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('440',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.357,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('441',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.291,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('442',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.315,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('443',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.371,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('444',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.393,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('445',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.627,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('446',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.992,0,0,0.181818181818181,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('447',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.999,0,0,0.400990099009901,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('448',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.995,0,0,0.267080745341614,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('449',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,1,0,0,0.322222222222222,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('450',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.988,0,0,0.301980198019801,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('451',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.997,0,0,0.468085106382978,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('452',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.999,0,0,0.356783919597989,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('453',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.998,0,0,0.322751322751322,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('454',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,1,0,0,0.504716981132075,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('455',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.999,0,0,0.432432432432432,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('456',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.988,0,0,0.302439024390243,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('457',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.033,0,0.993,0,0,0.342245989304812,0,0,0,0,0,0.00873362445414847,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('458',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.08,0,0.959,0,0,0.331658291457286,0,0,0,0,0,0.0252100840336134,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('459',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.098,0,0.901,0,0,0.42051282051282,0,0,0,0,0,0.0373831775700934,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('460',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.149,0,0.909,0,0,0.313513513513513,0,0,0,0,0,0.0231481481481481,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('461',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0,0.907,0,0,0.335294117647058,0,0,0,0,0,0.0382775119617224,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('462',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.138,0,0.916,0,0,0.404761904761904,0,0,0,0,0,0.0232558139534883,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('463',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.142,0,0.879,0,0,0.463768115942029,0,0,0,0,0,0.0382775119617224,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('464',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.172,0,0.87,0,0,0.382513661202185,0,0,0,0,0,0.0656565656565656,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('465',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.163,0,0.89,0,0,0.471111111111111,0,0,0,0,0,0.0287081339712918,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('466',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.188,0,0.869,0,0,0.505494505494505,0,0,0,0,0,0.0543478260869565,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('467',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.182,0,0.867,0.0714285714285714,0,0.469565217391304,0,0,0,0,0,0.0390243902439024,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('468',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.172,0,0.864,0,0,0.373134328358209,0,0,0,0,0,0.055045871559633,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('469',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.191,0,0.817,0.27027027027027,0,0.502564102564102,0.159090909090909,0,0,0,0,0.0218579234972677,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('470',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.317,0,0.787,0.363636363636363,0,0.141509433962264,0.37037037037037,0,0,0,0,0.0113636363636363,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('471',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.286,0,0.799,0.436507936507936,0,0.0377358490566037,0.34375,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('472',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.204,0,0.795,0.538461538461538,0,0.261904761904761,0.486486486486486,0,0,0,0,0.00581395348837209,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('473',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.225,0,0.807,0.444444444444444,0,0.238095238095238,0.302325581395348,0,0,0,0,0.0109890109890109,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('474',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.201,0,0.799,0.464285714285714,0,0.2578125,0.309859154929577,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('475',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.258,0,0.784,0.439024390243902,0,0.163636363636363,0.456521739130434,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('476',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.33,0,0.8,0.352941176470588,0,0.114503816793893,0.495238095238095,0,0,0,0,0.0105820105820105,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('477',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.22,0,0.795,0.414141414141414,0,0.192307692307692,0.439024390243902,0,0,0,0,0.010752688172043,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('478',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.273,0,0.794,0.4765625,0,0.168,0.488636363636363,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('479',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.224,0,0.799,0.48695652173913,0,0.322314049586776,0.404761904761904,0,0,0,0,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('480',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.245,0,0.799,0.363636363636363,0,0.223021582733812,0.347826086956521,0,0,0,0,0.0104712041884816,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('481',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.237,0.07,0.761,0.433333333333333,0,0.314960629921259,0.43010752688172,0,0,0,0,0.0231213872832369,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('482',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.25,0.102,0.71,0.503875968992248,0,0.305084745762711,0.337209302325581,0,0,0,0,0.0316455696202531,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('483',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.246,0.17,0.703,0.43010752688172,0,0.247422680412371,0.325,0,0,0,0,0.0425531914893617,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('484',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.261,0.197,0.687,0.409090909090909,0,0.338345864661654,0.383720930232558,0,0,0,0,0.0331125827814569,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('485',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.263,0.179,0.643,0.504761904761904,0,0.304,0.301369863013698,0,0,0,0,0.0625,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('486',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.251,0.15,0.658,0.423423423423423,0,0.231404958677685,0.402173913043478,0,0,0,0,0.0198675496688741,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('487',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.217,0.19,0.66,0.525547445255474,0,0.268518518518518,0.427083333333333,0,0,0,0,0.0533333333333333,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('488',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.209,0.203,0.605,0.53125,0,0.300884955752212,0.419753086419753,0,0,0,0,0.0689655172413793,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('489',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.201,0.214,0.605,0.433823529411764,0,0.362204724409448,0.34020618556701,0,0,0,0,0.0699300699300699,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('490',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.256,0.222,0.603,0.426086956521739,0,0.261261261261261,0.321428571428571,0,0,0,0,0.05,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('491',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.295,0.159,0.644,0.330578512396694,0,0.0526315789473684,0.365384615384615,0,0,0,0,0.0135135135135135,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('492',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.24,0.222,0.59,0.392523364485981,0,0.263157894736842,0.428571428571428,0,0,0,0,0.0264900662251655,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('493',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.259,0.307,0.613,0.56910569105691,0.125,0.284552845528455,0.341772151898734,0,0,0,0,0.120805369127516,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('494',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.203,0.342,0.51,0.457943925233644,0.427184466019417,0.342105263157894,0.25,0.109890109890109,0,0,0.0329670329670329,0.0859375,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('495',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.207,0.366,0.509,0.531531531531531,0.489208633093525,0.252427184466019,0.263157894736842,0.277777777777777,0,0,0.074074074074074,0.024,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('496',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.209,0.365,0.498,0.472222222222222,0.325,0.403508771929824,0.367088607594936,0.130434782608695,0,0,0.0608695652173913,0.0403225806451612,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('497',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.203,0.306,0.509,0.619469026548672,0.544642857142857,0.40983606557377,0.19047619047619,0.252747252747252,0,0,0.0219780219780219,0.0254237288135593,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('498',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.208,0.321,0.501,0.637931034482758,0.457142857142857,0.47008547008547,0.328125,0.111111111111111,0,0,0.0111111111111111,0.0769230769230769,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('499',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.207,0.302,0.51,0.601851851851851,0.462264150943396,0.4765625,0.208955223880597,0.131868131868131,0,0,0.0219780219780219,0.136752136752136,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('500',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.2,0.301,0.509,0.479591836734693,0.520547945205479,0.407766990291262,0.283783783783783,0.207920792079207,0,0,0.0594059405940594,0.0769230769230769,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('501',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.199,0.305,0.528,0.63063063063063,0.550847457627118,0.467153284671532,0.292307692307692,0.264367816091954,0,0,0.0114942528735632,0.08,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('502',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.22,0.304,0.503,0.495798319327731,0.396396396396396,0.410447761194029,0.317647058823529,0.103092783505154,0,0,0.0412371134020618,0.104477611940298,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('503',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.202,0.324,0.514,0.557251908396946,0.396226415094339,0.252747252747252,0.337349397590361,0.163265306122448,0,0,0.0510204081632653,0.0423728813559322,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('504',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.202,0.311,0.505,0.519607843137254,0.475409836065573,0.388888888888888,0.260869565217391,0.267326732673267,0,0,0.0495049504950495,0.0683760683760683,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('505',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.203,0.327,0.508,0.595238095238095,0.495575221238938,0.391752577319587,0.342465753424657,0.175824175824175,0,0,0.0329670329670329,0.0727272727272727,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('506',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.249,0.587,0.342,0.43859649122807,0.121495327102803,0,0.395348837209302,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('507',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.21,0.722,0.123,0.471544715447154,0.228070175438596,0,0.393258426966292,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('508',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.224,0.764,0.056,0.492307692307692,0.277777777777777,0,0.42391304347826,0,0,0,0.0123456790123456,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('509',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.237,0.78,0.019,0.354545454545454,0.253623188405797,0,0.365591397849462,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('510',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.231,0.801,0,0.439024390243902,0.243697478991596,0,0.351648351648351,0,0,0,0.00584795321637426,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('511',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.213,0.8,0,0.52892561983471,0.286821705426356,0,0.37037037037037,0,0,0,0.0177514792899408,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('512',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.234,0.797,0,0.465346534653465,0.344827586206896,0,0.31578947368421,0,0,0,0.00649350649350649,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('513',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.238,0.806,0,0.388888888888888,0.201492537313432,0,0.509803921568627,0,0,0,0.0054054054054054,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('514',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.274,0.797,0,0.352941176470588,0.0909090909090909,0,0.414141414141414,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('515',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.225,0.8,0,0.512605042016806,0.188679245283018,0,0.411764705882352,0,0,0,0.0125,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('516',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.229,0.802,0,0.311827956989247,0.122807017543859,0,0.390804597701149,0,0,0,0.0169491525423728,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('517',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.264,0.796,0,0.39047619047619,0.155339805825242,0,0.4,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('518',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.629,0.561,0,0.141666666666666,0,0,0.0151515151515151,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('519',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.722,0.357,0,0.35,0,0,0.0789473684210526,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('520',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.767,0.239,0,0.300813008130081,0,0,0.0566037735849056,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('521',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.847,0.194,0,0.142857142857142,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('522',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.823,0.205,0,0.225,0,0,0.0181818181818181,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('523',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.836,0.19,0,0.24390243902439,0,0,0.0240963855421686,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('524',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.816,0.182,0,0.198198198198198,0,0,0.029585798816568,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('525',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.838,0.181,0,0.186046511627906,0,0,0.0329670329670329,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('526',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.854,0.16,0,0.0887096774193548,0,0,0.0199004975124378,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('527',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.891,0.159,0,0.0952380952380952,0,0,0.0223463687150838,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('528',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.86,0.145,0,0.144067796610169,0,0,0.00526315789473684,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('529',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.845,0.158,0,0.144329896907216,0,0,0.0238095238095238,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('530',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.607,0.119,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('531',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.16,0.042,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('532',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.041,0.014,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('533',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.01,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('534',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('535',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('536',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('537',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('538',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('539',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('540',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('541',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.015,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('542',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.075,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('543',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.07,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('544',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.122,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('545',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.171,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('546',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.216,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('547',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.188,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('548',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.105,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('549',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('550',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.144,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('551',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.128,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('552',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.162,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('553',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.416,0.038,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('554',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.881,0.104,0,0.0821917808219178,0,0,0.00561797752808988,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('555',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.852,0.201,0,0.244604316546762,0,0,0.0261780104712041,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('556',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.792,0.23,0,0.20754716981132,0,0,0.0552147239263803,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('557',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.883,0.156,0,0.0277777777777777,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('558',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.858,0.135,0,0.0769230769230769,0,0,0.00507614213197969,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('559',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.789,0.218,0,0.192307692307692,0,0,0.0174418604651162,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('560',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.81,0.159,0,0.110169491525423,0,0,0.00546448087431694,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('561',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.767,0.227,0,0.11504424778761,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('562',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.755,0.241,0,0.0957446808510638,0,0,0.00609756097560975,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('563',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.769,0.21,0,0.140625,0,0,0.0337078651685393,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('564',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.821,0.182,0,0.0272727272727272,0,0,0.00546448087431694,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('565',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.835,0.331,0,0.132653061224489,0,0,0.0359281437125748,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('566',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.497,0.787,0,0.52892561983471,0.0403225806451612,0,0.601626016260162,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('567',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.218,0.79,0,0.515625,0.151515151515151,0,0.292682926829268,0,0,0,0.00606060606060606,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('568',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.285,0.792,0,0.476923076923076,0.272727272727272,0,0.351648351648351,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('569',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.218,0.793,0,0.56115107913669,0.328571428571428,0,0.389473684210526,0,0,0,0.0059880239520958,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('570',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.236,0.795,0,0.552147239263803,0.258333333333333,0,0.376344086021505,0,0,0,0.0118343195266272,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('571',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.215,0.799,0,0.443396226415094,0.3359375,0,0.378048780487804,0,0,0,0.00617283950617283,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('572',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.306,0.772,0,0.4609375,0.0816326530612244,0,0.421052631578947,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('573',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.264,0.805,0,0.504273504273504,0.191304347826086,0,0.505882352941176,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('574',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.219,0.807,0,0.479338842975206,0.274809160305343,0,0.441860465116279,0,0,0,0.0117647058823529,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('575',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.249,0.8,0,0.5,0.210144927536231,0,0.59090909090909,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('576',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.247,0.796,0,0.391304347826087,0.312,0,0.391304347826087,0,0,0,0.0171428571428571,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('577',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.271,0.811,0,0.379310344827586,0.240310077519379,0,0.354430379746835,0,0,0,0.0166666666666666,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('578',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.207,0.781,0,0.037037037037037,0.294573643410852,0,0,0,0,0,0.0116959064327485,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('579',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.201,0.858,0,0.136363636363636,0.117647058823529,0,0.1,0,0,0,0.0310880829015544,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('580',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.182,0.832,0,0,0.123809523809523,0,0,0,0,0,0.0277777777777777,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('581',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.181,0.852,0,0,0.192,0,0,0,0,0,0.0284090909090909,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('582',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.162,0.844,0,0,0.138613861386138,0,0,0,0,0,0.0118343195266272,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('583',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.205,0.8,0,0.0833333333333333,0.348484848484848,0,0.0263157894736842,0,0,0,0.0180722891566265,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('584',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.194,0.817,0,0,0.169811320754716,0,0.0526315789473684,0,0,0,0.0059880239520958,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('585',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.133,0.869,0,0,0,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('586',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.159,0.856,0,0,0.0679611650485436,0,0,0,0,0,0.0224719101123595,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('587',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.194,0.842,0,0,0.204724409448818,0,0,0,0,0,0.0225988700564971,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('588',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.226,0.823,0,0.0344827586206896,0.118181818181818,0,0.136363636363636,0,0,0,0.011049723756906,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('589',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.187,0.88,0,0,0.256880733944954,0,0.0384615384615384,0,0,0,0.0180722891566265,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('590',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.063,0.549,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('591',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.028,0.361,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('592',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.014,0.189,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('593',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.236,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('594',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.227,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('595',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.172,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('596',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.1,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('597',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.097,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('598',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.179,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('599',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.188,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('600',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.148,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('601',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.299,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('602',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.735,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('603',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.926,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('604',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.999,0,0,0.164285714285714,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('605',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.993,0,0,0.225352112676056,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('606',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.939,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('607',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.869,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('608',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.994,0,0,0.220183486238532,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('609',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.995,0,0,0.153846153846153,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('610',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.962,0,0,0.00925925925925925,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('611',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.993,0,0,0.10958904109589,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('612',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.997,0,0,0.05,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('613',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.971,0.088,0,0.0495867768595041,0,0,0.00471698113207547,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('614',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.686,0.521,0,0.208333333333333,0,0,0.176870748299319,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('615',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.441,0.562,0,0.129032258064516,0,0,0.0571428571428571,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('616',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.402,0.654,0,0.0892857142857142,0,0,0.141304347826086,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('617',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.329,0.537,0,0.0227272727272727,0,0,0,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('618',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.48,0.631,0,0.162162162162162,0,0,0.197916666666666,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('619',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.458,0.608,0,0.294117647058823,0,0,0.0816326530612244,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('620',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.431,0.637,0,0.111111111111111,0,0,0.103092783505154,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('621',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.419,0.593,0,0.088235294117647,0,0,0.127659574468085,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('622',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.439,0.631,0,0.151515151515151,0,0,0.0957446808510638,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('623',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.478,0.584,0,0.0289855072463768,0,0,0.0285714285714285,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('624',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0.438,0.576,0,0.0476190476190476,0,0,0.0338983050847457,0,0,0,0,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('625',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.079,0.449,0.554,0,0,0,0,0.0111111111111111,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('626',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.332,0.315,0.318,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('627',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.436,0.251,0.156,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('628',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.465,0.144,0.142,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('629',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.606,0.124,0.128,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('630',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.649,0.151,0.142,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('631',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.577,0.121,0.154,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('632',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.563,0.146,0.113,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('633',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.605,0.139,0.103,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('634',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.636,0.181,0.13,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('635',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.575,0.178,0.191,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('636',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.638,0.147,0.153,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('637',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.701,0.131,0.146,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('638',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.46,0.096,0.111,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('639',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.144,0.015,0.031,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('640',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0.028,0,0.026,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('641',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0.015,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('642',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('643',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('644',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('645',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('646',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('647',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('648',0,'Conservador','PreemptionAllocCTSharing',300,null,null,null,null,null,null,null,null,null,null,null,null,200,300,500,0,0,0,0,0,0,0,0,0,0,0,0,'PreemptionAllocCTSharing',true);").execute();



    }

}
