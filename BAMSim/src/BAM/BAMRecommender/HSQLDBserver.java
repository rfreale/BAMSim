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
	    		+ "SLAUtilizacaoCT0 INTEGER, "
	    		+ "SLAUtilizacaoCT1 INTEGER, "
	    		+ "SLAUtilizacaoCT2 INTEGER, "
	    		+ "SLABloqueiosCT0 INTEGER, "
	    		+ "SLABloqueiosCT1 INTEGER, "
	    		+ "SLABloqueiosCT2 INTEGER, "
	    		+ "SLAPreempcoesCT0 INTEGER, "
	    		+ "SLAPreempcoesCT1 INTEGER, "
	    		+ "SLAPreempcoesCT2 INTEGER, "
	    		+ "SLADevolucoesCT0 INTEGER, "
	    		+ "SLADevolucoesCT1 INTEGER, "
	    		+ "SLADevolucoesCT2 INTEGER, "
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
	    		+ "SLAUtilizacaoCT0 INTEGER, "
	    		+ "SLAUtilizacaoCT1 INTEGER, "
	    		+ "SLAUtilizacaoCT2 INTEGER, "
	    		+ "SLABloqueiosCT0 INTEGER, "
	    		+ "SLABloqueiosCT1 INTEGER, "
	    		+ "SLABloqueiosCT2 INTEGER, "
	    		+ "SLAPreempcoesCT0 INTEGER, "
	    		+ "SLAPreempcoesCT1 INTEGER, "
	    		+ "SLAPreempcoesCT2 INTEGER, "
	    		+ "SLADevolucoesCT0 INTEGER, "
	    		+ "SLADevolucoesCT1 INTEGER, "
	    		+ "SLADevolucoesCT2 INTEGER, "
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
	    
	    /////////************************************** ID  Gerente  BAMAtual          		  	Problema         Utilização     Bloqueio    Preempção   Devolução   Largura de banda    Utilização     		Bloqueio    Preempção   Devolução    Solução
	    conn.prepareStatement("insert into bam values('1','Eliseu','PreemptionAllocCTSharing','BaixaUtilizacao',80,80,80,		7,7,7,		5,5,5,		3,3,3,		200,300,500,		0.0,0.0,336.0,		0,0,0,		0,0,0,		0,0,0,		'PreemptionAllocCTSharing', true);").execute();
	    conn.prepareStatement("insert into bam values('2','Eliseu','PreemptionAllocCTSharing','BaixaUtilizacao',80,80,80,		7,7,7,		5,5,5,		3,3,3,		200,300,500,		0.0,0.0,999.0,		0,0,27,		0,0,0,		0,0,0, 		'PreemptionAllocCTSharing', true);").execute();
	    conn.prepareStatement("insert into bam values('3','Eliseu','PreemptionAllocCTSharing','BaixaUtilizacao',80,80,80,		7,7,7,		5,5,5,		3,3,3,		200,300,500,		215.0,0.0,812.0,	63,0,50,	44,0,0,		0,0,13,		'NoPreemptionMAM', true);").execute();
	    //conn.prepareStatement("insert into bam values('4','Eliseu','NoPreemptionMAM',		  'BaixaUtilizacao',80,80,80,		7,7,7,		5,5,5,		3,3,3,		200,300,500,		200.0,0.0,500.0,	83,0,64,	0,0,0,		0,0,14
	    
		
		
		 /////////************************************** ID    Nome Gerente    BAMAtual           Problema          Utilização     Bloqueio       Preempção        Devolução        Largura de banda   Utilização     Bloqueio       Preempção        Devolução         Solução      
		 /* conn.prepareStatement("insert into bam values('1' ,'Carlos','NoPreemptionMAM'         ,'BaixaUtilizacao', 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 10 , 15 , 20 , 2  , 5  , 3  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('2' ,'Marcos','NoPreemptionMAM'         ,'BaixaUtilizacao', 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 10 , 15 , 20 , 5  , 4  , 2  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('3' ,'Lucas' ,'NoPreemptionMAM'         ,'BaixaUtilizacao', 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 10 , 15 , 20 , 7  , 4  , 5  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('4' ,'Carlos','NoPreemptionMAM'         ,'BaixaUtilizacao', 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 10 , 15 , 20 , 4  , 2  , 1  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('5' ,'Marcos','NoPreemptionMAM'         ,'BaixaUtilizacao', 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 10 , 15 , 20 , 1  , 5  , 8  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('6' ,'Lucas' ,'NoPreemptionMAM'         ,'BaixaUtilizacao', 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 10 , 15 , 20 , 4  , 2  , 6  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('7' ,'Carlos','NoPreemptionMAM'         ,'AltoBloqueio'   , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('8' ,'Marcos','NoPreemptionMAM'         ,'AltoBloqueio'   , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('9' ,'Lucas' ,'NoPreemptionMAM'         ,'AltoBloqueio'   , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('10','Carlos','NoPreemptionMAM'         ,'AltoBloqueio'   , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('11','Marcos','NoPreemptionMAM'         ,'AltoBloqueio'   , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('12','Lucas' ,'NoPreemptionMAM'         ,'AltoBloqueio'   , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('13','Carlos','PreemptionRDM'           ,'BaixaUtilizacao', 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('14','Marcos','PreemptionRDM'           ,'BaixaUtilizacao', 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('15','Lucas' ,'PreemptionRDM'           ,'BaixaUtilizacao', 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('16','Carlos','PreemptionRDM'           ,'BaixaUtilizacao', 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('17','Marcos','PreemptionRDM'           ,'BaixaUtilizacao', 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('18','Lucas' ,'PreemptionRDM'           ,'BaixaUtilizacao', 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('19','Carlos','PreemptionRDM'           ,'AltoBloqueio'   , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('20','Marcos','PreemptionRDM'           ,'AltoBloqueio'   , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('21','Lucas' ,'PreemptionRDM'           ,'AltoBloqueio'   , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('22','Carlos','PreemptionRDM'           ,'AltoBloqueio'   , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('23','Marcos','PreemptionRDM'           ,'AltoBloqueio'   , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('24','Lucas' ,'PreemptionRDM'           ,'AltoBloqueio'   , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('25','Carlos','PreemptionRDM'           ,'AltaPreempcao'  , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('26','Marcos','PreemptionRDM'           ,'AltaPreempcao'  , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('27','Lucas' ,'PreemptionRDM'           ,'AltaPreempcao'  , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('28','Carlos','PreemptionRDM'           ,'AltaPreempcao'  , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('29','Marcos','PreemptionRDM'           ,'AltaPreempcao'  , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('30','Lucas' ,'PreemptionRDM'           ,'AltaPreempcao'  , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('31','Carlos','PreemptionAllocCTSharing','BaixaUtilizacao', 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('32','Marcos','PreemptionAllocCTSharing','BaixaUtilizacao', 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('33','Lucas' ,'PreemptionAllocCTSharing','BaixaUtilizacao', 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('34','Carlos','PreemptionAllocCTSharing','BaixaUtilizacao', 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('35','Marcos','PreemptionAllocCTSharing','BaixaUtilizacao', 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('36','Lucas' ,'PreemptionAllocCTSharing','BaixaUtilizacao', 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 10 , 15 , 20 , 20 , 25 , 30 , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
		  conn.prepareStatement("insert into bam values('37','Carlos','PreemptionAllocCTSharing','AltoBloqueio'   , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('38','Marcos','PreemptionAllocCTSharing','AltoBloqueio'   , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('39','Lucas' ,'PreemptionAllocCTSharing','AltoBloqueio'   , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('40','Carlos','PreemptionAllocCTSharing','AltoBloqueio'   , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('41','Marcos','PreemptionAllocCTSharing','AltoBloqueio'   , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('42','Lucas' ,'PreemptionAllocCTSharing','AltoBloqueio'   , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 95 , 90 , 99 , 90 , 95 , 99 , 0  , 0  , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('43','Carlos','PreemptionAllocCTSharing','AltaPreempcao'  , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('44','Marcos','PreemptionAllocCTSharing','AltaPreempcao'  , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('45','Lucas' ,'PreemptionAllocCTSharing','AltaPreempcao'  , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('46','Carlos','PreemptionAllocCTSharing','AltaPreempcao'  , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('47','Marcos','PreemptionAllocCTSharing','AltaPreempcao'  , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('48','Lucas' ,'PreemptionAllocCTSharing','AltaPreempcao'  , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 80 , 90 , null , null , 0  , 0  ,'NoPreemptionMAM'         , true);").execute();
		  conn.prepareStatement("insert into bam values('49','Carlos','PreemptionAllocCTSharing','AltaDevolucao'  , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 0  , 0  , null , null , 90 , 80 ,'PreemptionRDM'           , true);").execute();
		  conn.prepareStatement("insert into bam values('50','Marcos','PreemptionAllocCTSharing','AltaDevolucao'  , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 0  , 0  , null , null , 90 , 80 ,'PreemptionRDM'           , true);").execute();
		  conn.prepareStatement("insert into bam values('51','Lucas' ,'PreemptionAllocCTSharing','AltaDevolucao'  , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 256 , 768 , 1024 , 95 , 90 , 99 , 0  , 0  , 0  , 0  , 0  , null , null , 90 , 80 ,'PreemptionRDM'           , true);").execute();
		  conn.prepareStatement("insert into bam values('52','Carlos','PreemptionAllocCTSharing','AltaDevolucao'  , 44 , 50 , 55 , 60 , 55 , 50 , 60 , 55 , null , null , 55 , 50 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 0  , 0  , null , null , 90 , 80 ,'PreemptionRDM'           , true);").execute();
		  conn.prepareStatement("insert into bam values('53','Marcos','PreemptionAllocCTSharing','AltaDevolucao'  , 30 , 35 , 40 , 75 , 70 , 65 , 75 , 70 , null , null , 70 , 65 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 0  , 0  , null , null , 90 , 80 ,'PreemptionRDM'           , true);").execute();
		  conn.prepareStatement("insert into bam values('54','Lucas' ,'PreemptionAllocCTSharing','AltaDevolucao'  , 15 , 20 , 25 , 90 , 85 , 80 , 90 , 85 , null , null , 85 , 80 , 128 , 256 , 512  , 95 , 90 , 99 , 0  , 0  , 0  , 0  , 0  , null , null , 90 , 80 ,'PreemptionRDM'           , true);").execute();
		  conn.prepareStatement("insert into bam values('55','Fred'  ,'NoPreemptionMAM'         ,'BaixaUtilizacao', 35 , 40 , 45 , 80 , 70 , 80 , 80 , 80 , null , null , 80 , 80 , 250 , 600 , 1000 , 10 , 20 , 30 , 5  , 6  , 8  , 0  , 0  , null , null , 0  , 0  ,'PreemptionAllocCTSharing', true);").execute();
*/
		
	    
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
