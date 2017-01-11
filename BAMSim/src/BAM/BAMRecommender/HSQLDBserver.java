/**
 * HSQLDBserver.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
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
	server.setDatabaseName(1, "bam2");
	server.setDatabasePath(1, "mem:bam2;sql.enforce_strict_size=true");
	
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
	    		+ "gestor VARCHAR(30), "
	    		+ "BAMAtual VARCHAR(30), problema VARCHAR(30), "
	    		+ "toleranciaBloqueiosCT0 INTEGER, "
	    		+ "toleranciaBloqueiosCT1 INTEGER, "
	    		+ "toleranciaBloqueiosCT2 INTEGER, "
	    		+ "toleranciaPreempcoesCT0 INTEGER, "
	    		+ "toleranciaPreempcoesCT1 INTEGER, "
	    		+ "toleranciaPreempcoesCT2 INTEGER, "
	    		+ "toleranciaDevolucoesCT0 INTEGER, "
	    		+ "toleranciaDevolucoesCT1 INTEGER, "
	    		+ "toleranciaDevolucoesCT2 INTEGER, "
	    		+ "BC0 DECIMAL(10,2), "
	    		+ "BC1 DECIMAL(10,2), "
	    		+ "BC2 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT0 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT1 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT2 DECIMAL(10,2), "
	    		+ "numeroDeBloqueiosCT0 INTEGER, "
	    		+ "numeroDeBloqueiosCT1 INTEGER, "
	    		+ "numeroDeBloqueiosCT2 INTEGER, "
	    		+ "numeroDePreempcoesCT0 INTEGER, "
	    		+ "numeroDePreempcoesCT1 INTEGER, "
	    		+ "numeroDePreempcoesCT2 INTEGER, "
	    		+ "numeroDeDevolucoesCT0 INTEGER, "
	    		+ "numeroDeDevolucoesCT1 INTEGER, "
	    		+ "numeroDeDevolucoesCT2 INTEGER, "
	    		+ "BAMNovo VARCHAR(30), "
	    		+ "aceita BIT);").execute();
	    
	    conn2.prepareStatement("create table bam(caseId VARCHAR(15), "
	    		+ "gestor VARCHAR(30), "
	    		+ "BAMAtual VARCHAR(30), problema VARCHAR(30), "
	    		+ "toleranciaBloqueiosCT0 INTEGER, "
	    		+ "toleranciaBloqueiosCT1 INTEGER, "
	    		+ "toleranciaBloqueiosCT2 INTEGER, "
	    		+ "toleranciaPreempcoesCT0 INTEGER, "
	    		+ "toleranciaPreempcoesCT1 INTEGER, "
	    		+ "toleranciaPreempcoesCT2 INTEGER, "
	    		+ "toleranciaDevolucoesCT0 INTEGER, "
	    		+ "toleranciaDevolucoesCT1 INTEGER, "
	    		+ "toleranciaDevolucoesCT2 INTEGER, "
	    		+ "BC0 DECIMAL(10,2), "
	    		+ "BC1 DECIMAL(10,2), "
	    		+ "BC2 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT0 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT1 DECIMAL(10,2), "
	    		+ "utilizacaoDoEnlaceCT2 DECIMAL(10,2), "
	    		+ "numeroDeBloqueiosCT0 INTEGER, "
	    		+ "numeroDeBloqueiosCT1 INTEGER, "
	    		+ "numeroDeBloqueiosCT2 INTEGER, "
	    		+ "numeroDePreempcoesCT0 INTEGER, "
	    		+ "numeroDePreempcoesCT1 INTEGER, "
	    		+ "numeroDePreempcoesCT2 INTEGER, "
	    		+ "numeroDeDevolucoesCT0 INTEGER, "
	    		+ "numeroDeDevolucoesCT1 INTEGER, "
	    		+ "numeroDeDevolucoesCT2 INTEGER, "
	    		+ "BAMNovo VARCHAR(30), "
	    		+ "aceita BIT);").execute();
	    
	   
	    
	    //Baixa Utilizaçao
	    //																									BC				Utilizaçao		Preempçao		Bloqueio		Devoluçao			Outros
	    //conn.prepareStatement("insert into bam values('bam1', 'marcos', 'PreemptionAllocCTSharing','BaixaUtilizacao'	,null,null,null,	0,0,0,			null,null,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'PreemptionAllocCTSharing',true);").execute();
	    //conn.prepareStatement("insert into bam values('bam2', 'marcos', 'NoPreemptionMAM','BaixaUtilizacao'			,null,null,null,	0,0,0,			null,null,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'PreemptionAllocCTSharing',true);").execute();
	    //conn.prepareStatement("insert into bam values('bam3', 'marcos', 'PreemptionRDM','BaixaUtilizacao'			,null,null,null,	0,0,0,			null,null,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'PreemptionAllocCTSharing',true);").execute();
	    
	    //Baixa Utilizaçao							 //ID	 Gestor		BAM Atual					Problema		 Tole_Blo	   Tole_Pre		Tole_Dev	 Banda			   Utilização	 Bloqueio	   Preemp	  Devolu    Novo bam			       aceita 
		conn.prepareStatement("insert into bam values('1' , 'Carlos', 'NoPreemptionMAM'         , 'BaixaUtilizacao', 70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('2' , 'Marcos', 'NoPreemptionMAM'         , 'BaixaUtilizacao', 60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('3' , 'Lucas' , 'NoPreemptionMAM'         , 'BaixaUtilizacao', 65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('4' , 'Carlos', 'NoPreemptionMAM'         , 'BaixaUtilizacao', 70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('5' , 'Marcos', 'NoPreemptionMAM'         , 'BaixaUtilizacao', 60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('6' , 'Lucas' , 'NoPreemptionMAM'         , 'BaixaUtilizacao', 65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('13', 'Carlos', 'PreemptionRDM'           , 'BaixaUtilizacao', 70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('14', 'Marcos', 'PreemptionRDM'           , 'BaixaUtilizacao', 60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('15', 'Lucas' , 'PreemptionRDM'           , 'BaixaUtilizacao', 65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('16', 'Carlos', 'PreemptionRDM'           , 'BaixaUtilizacao', 70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('17', 'Marcos', 'PreemptionRDM'           , 'BaixaUtilizacao', 60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('18', 'Lucas' , 'PreemptionRDM'           , 'BaixaUtilizacao', 65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('31', 'Carlos', 'PreemptionAllocCTSharing', 'BaixaUtilizacao', 70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('32', 'Marcos', 'PreemptionAllocCTSharing', 'BaixaUtilizacao', 60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('33', 'Lucas' , 'PreemptionAllocCTSharing', 'BaixaUtilizacao', 65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('34', 'Carlos', 'PreemptionAllocCTSharing', 'BaixaUtilizacao', 70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('35', 'Marcos', 'PreemptionAllocCTSharing', 'BaixaUtilizacao', 60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		conn.prepareStatement("insert into bam values('36', 'Lucas' , 'PreemptionAllocCTSharing', 'BaixaUtilizacao', 65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    10, 15, 20,   20, 25, 30,   0, 0, 0,   0, 0, 0, 'PreemptionAllocCTSharing', true);").execute();
		
		//AltoBloqueio
		conn.prepareStatement("insert into bam values('7' , 'Carlos', 'NoPreemptionMAM'         , 'AltoBloqueio',    70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('8' , 'Marcos', 'NoPreemptionMAM'         , 'AltoBloqueio',    60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('9' , 'Lucas' , 'NoPreemptionMAM'         , 'AltoBloqueio',    65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('10', 'Carlos', 'NoPreemptionMAM'         , 'AltoBloqueio',    70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('11', 'Marcos', 'NoPreemptionMAM'         , 'AltoBloqueio',    60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('12', 'Lucas' , 'NoPreemptionMAM'         , 'AltoBloqueio',    65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('19', 'Carlos', 'PreemptionRDM'           , 'AltoBloqueio',    70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('20', 'Marcos', 'PreemptionRDM'           , 'AltoBloqueio',    60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('21', 'Lucas' , 'PreemptionRDM'           , 'AltoBloqueio',    65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('22', 'Carlos', 'PreemptionRDM'           , 'AltoBloqueio',    70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('23', 'Marcos', 'PreemptionRDM'           , 'AltoBloqueio',    60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('24', 'Lucas' , 'PreemptionRDM'           , 'AltoBloqueio',    65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('37', 'Carlos', 'PreemptionAllocCTSharing', 'AltoBloqueio',    70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('38', 'Marcos', 'PreemptionAllocCTSharing', 'AltoBloqueio',    60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('39', 'Lucas' , 'PreemptionAllocCTSharing', 'AltoBloqueio',    65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('40', 'Carlos', 'PreemptionAllocCTSharing', 'AltoBloqueio',    70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('41', 'Marcos', 'PreemptionAllocCTSharing', 'AltoBloqueio',    60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('42', 'Lucas' , 'PreemptionAllocCTSharing', 'AltoBloqueio',    65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    95, 90, 99,   90, 95, 99,   0, 0, 0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		
		//AltaPreempcao
		conn.prepareStatement("insert into bam values('25', 'Carlos', 'PreemptionRDM'           , 'AltaPreempcao',   70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('26', 'Marcos', 'PreemptionRDM'           , 'AltaPreempcao',   60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('27', 'Lucas' , 'PreemptionRDM'           , 'AltaPreempcao',   65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('28', 'Carlos', 'PreemptionRDM'           , 'AltaPreempcao',   70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('29', 'Marcos', 'PreemptionRDM'           , 'AltaPreempcao',   60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('30', 'Lucas' , 'PreemptionRDM'           , 'AltaPreempcao',   65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('43', 'Carlos', 'PreemptionAllocCTSharing', 'AltaPreempcao',   70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0     80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('44', 'Marcos', 'PreemptionAllocCTSharing', 'AltaPreempcao',   60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('45', 'Lucas' , 'PreemptionAllocCTSharing', 'AltaPreempcao',   65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('46', 'Carlos', 'PreemptionAllocCTSharing', 'AltaPreempcao',   70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('47', 'Marcos', 'PreemptionAllocCTSharing', 'AltaPreempcao',   60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		conn.prepareStatement("insert into bam values('48', 'Lucas' , 'PreemptionAllocCTSharing', 'AltaPreempcao',   65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   80,90,0,   0, 0, 0, 'NoPreemptionMAM'         , true);").execute();
		
		//AltaDevolucao
		conn.prepareStatement("insert into bam values('49', 'Carlos', 'PreemptionAllocCTSharing', 'AltaDevolucao',   70, 65, 60,   80, 70, 0,   0, 70, 80,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   0, 0, 0,   0, 90, 80, 'PreemptionRDM'         , true);").execute();
		conn.prepareStatement("insert into bam values('50', 'Marcos', 'PreemptionAllocCTSharing', 'AltaDevolucao',   60, 50, 40,   90, 80, 0,   0, 80, 90,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   0, 0, 0,   0, 90, 80, 'PreemptionRDM'         , true);").execute();
		conn.prepareStatement("insert into bam values('51', 'Lucas' , 'PreemptionAllocCTSharing', 'AltaDevolucao',   65, 60, 70,   70, 60, 0,   0, 60, 70,   256, 512, 1024,   95, 90, 99,   0 , 0 , 0 ,   0, 0, 0,   0, 90, 80, 'PreemptionRDM'         , true);").execute();
		conn.prepareStatement("insert into bam values('52', 'Carlos', 'PreemptionAllocCTSharing', 'AltaDevolucao',   70, 65, 60,   80, 70, 0,   0, 70, 80,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   0, 0, 0,   0, 90, 80, 'PreemptionRDM'         , true);").execute();
		conn.prepareStatement("insert into bam values('53', 'Marcos', 'PreemptionAllocCTSharing', 'AltaDevolucao',   60, 50, 40,   90, 80, 0,   0, 80, 90,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   0, 0, 0,   0, 90, 80, 'PreemptionRDM'         , true);").execute();
		conn.prepareStatement("insert into bam values('54', 'Lucas' , 'PreemptionAllocCTSharing', 'AltaDevolucao',   65, 60, 70,   70, 60, 0,   0, 60, 70,   128, 256, 512,    95, 90, 99,   0 , 0 , 0 ,   0, 0, 0,   0, 90, 80, 'PreemptionRDM'         , true);").execute();
	    
	    
	    
	    
        //Alto Bloqueio
	    //conn.prepareStatement("insert into bam values('bam3','PreemptionAllocCTSharing','AltoBloqueio',100,0,0,0,100,100,100,0,0,0,'NoPreemptionMAM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam4','PreemptionRDM','AltoBloqueio',100,0,0,0,100,100,100,0,0,0,'NoPreemptionMAM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam5','NoPreemptionMAM','AltoBloqueio',100,0,0,0,100,100,100,0,0,0,'NoPreemptionMAM',true);").execute();
	    
	    //Alta Preempçao
	    //																									BC				Utilizaçao		Preempçao		Bloqueio		Devoluçao			Outros
	  //  conn.prepareStatement("insert into bam values('bam4','PreemptionAllocCTSharing','AltaPreempcao'	,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam5','NoPreemptionMAM','AltaPreempcao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam6','PreemptionRDM','AltaPreempcao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,null,null,		null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();

	   	    
	    //Alta Devoluçao
	    //																									BC				Utilizaçao		Preempçao		Bloqueio		Devoluçao			Outros
	   // conn.prepareStatement("insert into bam values('bam7','PreemptionAllocCTSharing','AltaDevolucao'	,null,null,null,	null,null,null,	null,null,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'PreemptionRDM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam8','NoPreemptionMAM','AltaDevolucao'			,null,null,null,	null,null,null,	null,null,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'PreemptionRDM',true);").execute();
	   // conn.prepareStatement("insert into bam values('bam9','PreemptionRDM','AltaDevolucao'			,null,null,null,	null,null,null,	null,null,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'PreemptionRDM',true);").execute();
	    /*
	  //Alta Devoluçao e Preempçao
	    //																									BC				Utilizaçao		Preempçao		Bloqueio		Devoluçao			Outros
	    conn.prepareStatement("insert into bam values('bam10','PreemptionAllocCTSharing','AltaDevolucao',null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam11','NoPreemptionMAM','AltaDevolucao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	    conn.prepareStatement("insert into bam values('bam12','PreemptionRDM','AltaDevolucao'			,null,null,null,	null,null,null,	100,100,null,	null,null,null,	null,100,100,			null,null,null,null,null,null,null,null,null,'NoPreemptionMAM',true);").execute();
	   
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
