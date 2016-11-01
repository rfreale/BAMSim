/**
 * HSQLDBserver.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 04/07/2007
 */
package BAM.BAMRecommender;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import jcolibri.util.FileIO;

import org.hsqldb.Server;

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
	server.setDatabaseName(0, "bam");
	server.setDatabasePath(0, "mem:bam;sql.enforce_strict_size=true");
	
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
	    //conn.prepareStatement("create database bam;").execute();
	   // conn.prepareStatement("use bam;").execute();
	   // conn.prepareStatement("drop table bam;").execute();
	    conn.prepareStatement("create table bam(caseId VARCHAR(15), "
	    		+ "BAMAtual VARCHAR(30), problema VARCHAR(30), "
	    		+ "BC0 DECIMAL(10,2), "
	    		+ "BC1 DECIMAL(10,2), "
	    		+ "BC2 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT0 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT1 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT2 DECIMAL(10,2), "
	    		+ "numeroDePreempcoesCT0 INTEGER, "
	    		+ "numeroDePreempcoesCT1 INTEGER, "
	    		+ "numeroDePreempcoesCT2 INTEGER, "
	    		+ "numeroDeBloqueiosCT0 INTEGER, "
	    		+ "numeroDeBloqueiosCT1 INTEGER, "
	    		+ "numeroDeBloqueiosCT2 INTEGER, "
	    		+ "numeroDeDevolucoesCT0 INTEGER, "
	    		+ "numeroDeDevolucoesCT1 INTEGER, "
	    		+ "numeroDeDevolucoesCT2 INTEGER, "
	    		+ "toleranciaPreempcoesCT0 INTEGER, "
	    		+ "toleranciaPreempcoesCT1 INTEGER, "
	    		+ "toleranciaPreempcoesCT2 INTEGER, "
	    		+ "toleranciaBloqueiosCT0 INTEGER, "
	    		+ "toleranciaBloqueiosCT1 INTEGER, "
	    		+ "toleranciaBloqueiosCT2 INTEGER, "
	    		+ "toleranciaDevolucoesCT0 INTEGER, "
	    		+ "toleranciaDevolucoesCT1 INTEGER, "
	    		+ "toleranciaDevolucoesCT2 INTEGER, "
	    		+ "BAMNovo VARCHAR(30), "
	    		+ "aceita BIT);").execute();
	    //Baixa Utilização
	    //																									BC				Utilização		Preempção		Bloqueio		Devolução			Outros
	    conn.prepareStatement("insert into bam values('bam1','PreemptionAllocCTSharing','BaixaUtilizacao'	,null,null,null,	0,0,0,			null,null,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('bam2','NoPreemptionMAM','BaixaUtilizacao'			,null,null,null,	0,0,0,			null,null,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'PreemptionAllocCTSharing',true);").execute();
	    conn.prepareStatement("insert into bam values('bam3','PreemptionRDM','BaixaUtilizacao'			,null,null,null,	0,0,0,			null,null,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'PreemptionAllocCTSharing',true);").execute();
  
        //Alto Bloqueio
	    //conn.prepareStatement("insert into bam values('bam3','PreemptionAllocCTSharing','AltoBloqueio',100,0,0,0,100,100,100,0,0,0,'NoPreemptionMAM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam4','PreemptionRDM','AltoBloqueio',100,0,0,0,100,100,100,0,0,0,'NoPreemptionMAM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam5','NoPreemptionMAM','AltoBloqueio',100,0,0,0,100,100,100,0,0,0,'NoPreemptionMAM',true);").execute();
	    
	    //Alta Preempção
	    //																									BC				Utilização		Preempção		Bloqueio		Devolução			Outros
	    conn.prepareStatement("insert into bam values('bam4','PreemptionAllocCTSharing','AltaPreempcao'	,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam5','NoPreemptionMAM','AltaPreempcao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam6','PreemptionRDM','AltaPreempcao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();

	   	    
	    //Alta Devolução
	    //																									BC				Utilização		Preempção		Bloqueio		Devolução			Outros
	    conn.prepareStatement("insert into bam values('bam7','PreemptionAllocCTSharing','AltaDevolucao'	,null,null,null,	null,null,null,	null,null,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam8','NoPreemptionMAM','AltaDevolucao'			,null,null,null,	null,null,null,	null,null,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam9','PreemptionRDM','AltaDevolucao'			,null,null,null,	null,null,null,	null,null,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'PreemptionRDM',true);").execute();

	  //Alta Devolução e Preempção
	    //																									BC				Utilização		Preempção		Bloqueio		Devolução			Outros
	    conn.prepareStatement("insert into bam values('bam10','PreemptionAllocCTSharing','AltaDevolucao',null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam11','NoPreemptionMAM','AltaDevolucao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam12','PreemptionRDM','AltaDevolucao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    /*
	    conn.prepareStatement("insert into bam values('bam7','PreemptionAllocCTSharing',100,0,0,0,0,0,0,0,50,50,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam8','PreemptionAllocCTSharing',100,0,0,0,0,0,0,0,50,0,'PreemptionRDM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam9','PreemptionAllocCTSharing',100,0,0,0,0,0,0,0,0,50,'PreemptionRDM',true);").execute();
	    */
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

}
