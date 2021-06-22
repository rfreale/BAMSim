create database bam;
use bam;
drop table bam;
create table bam( caseId VARCHAR(15),gestor VARCHAR(30),BAMAtual VARCHAR(30),"
Janela INTEGER,SLAUtilizacaoCT0 INTEGER,SLAUtilizacaoCT1 INTEGER,SLAUtilizacaoCT2 INTEGER,SLABloqueiosCT0 INTEGER,SLABloqueiosCT1 INTEGER,SLABloqueiosCT2 INTEGER,SLAPreempcoesCT0 INTEGER,SLAPreempcoesCT1 INTEGER,SLAPreempcoesCT2 INTEGER,SLADevolucoesCT0 INTEGER,SLADevolucoesCT1 INTEGER,SLADevolucoesCT2 INTEGER,BC0 DECIMAL(10,2),BC1 DECIMAL(10,2),BC2 DECIMAL(10,2),utilizacaoDoEnlaceCT0 DECIMAL(10,2),utilizacaoDoEnlaceCT1 DECIMAL(10,2),utilizacaoDoEnlaceCT2 DECIMAL(10,2),numeroDeBloqueiosCT0 INTEGER,numeroDeBloqueiosCT1 INTEGER,numeroDeBloqueiosCT2 INTEGER,numeroDePreempcoesCT0 INTEGER,numeroDePreempcoesCT1 INTEGER,numeroDePreempcoesCT2 INTEGER,numeroDeDevolucoesCT0 INTEGER,numeroDeDevolucoesCT1 INTEGER,numeroDeDevolucoesCT2 INTEGER,BAMNovo VARCHAR(30),aceita BIT);


