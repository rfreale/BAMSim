create database bam;
use bam;
drop table bam;
create table bam(caseId VARCHAR(15), BAMAtual VARCHAR(20), utilizacaoDoEnlace INTEGER, numeroDePreempcoesCT0 INTEGER, numeroDePreempcoesCT1 INTEGER, numeroDePreempcoesCT2 INTEGER, numeroDeBloqueiosCT0 INTEGER, numeroDeBloqueiosCT1 INTEGER, numeroDeBloqueiosCT2 INTEGER, numeroDeDevolucoesCT0 INTEGER, numeroDeDevolucoesCT1 INTEGER, numeroDeDevolucoesCT2 INTEGER, BAMNovo VARCHAR(20), aceita BIT);
insert into bam values('bam1','MAM',0,0,0,0,0,0,0,0,0,0,'ATCS',true);
insert into bam values('bam2','ATCS',100,100,100,100,0,0,0,0,0,0,'MAM',true);