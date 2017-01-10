/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package BAM.BAMRecommender;


import javax.swing.SpinnerNumberModel;

import jcolibri.cbrcore.Attribute;
import jcolibri.datatypes.Instance;


/**
 * Bean that stores the description of the case.
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class BAMDescription implements jcolibri.cbrcore.CaseComponent, Cloneable{
	
	
	public enum BAMTypes {
		NoPreemptionMAM(0), NoPreemptionRDM(1), NoPreemptionAllocCTSharing(2),PreemptionMAM(3), PreemptionRDM(4), PreemptionAllocCTSharing(5),PreemptionGBAM(6),NoPreemptionBestEffort(7);
		private final int status; 
		
		BAMTypes(int valorStatus)
		{ 
			status = valorStatus; 
		} 
		public int getValor()
		{ 
			return status; 
		}


	}
	public enum Problemas {
		AltaPreempcao(0), 		AltoBloqueio(1), 		AltaDevolucao(2), 		BaixaUtilizacao(3),
		AltaPreempcaoCT0(4), 	AltoBloqueioCT0(5), 	AltaDevolucaoCT0(6),	BaixaUtilizacaoCT0(7),
		AltaPreempcaoCT1(8), 	AltoBloqueioCT1(8), 	AltaDevolucaoCT1(10),	BaixaUtilizacaoCT1(11),
		AltaPreempcaoCT2(12), 	AltoBloqueioCT2(13), 	AltaDevolucaoCT2(14),	BaixaUtilizacaoCT2(15);
		private final int status; 
		
		Problemas(int valorStatus)
		{ 
			status = valorStatus; 
		} 
		public int getValor()
		{ 
			return status; 
		}


	}
	//Colocar sintoma
	String  caseId;
	//Inclusão do gestor
	String gestor;
	

	BAMTypes BAMAtual;
	Problemas problema;
	//Limites
	Double BC0;
	Double BC1;
	Double BC2;
	//Tolerancia
	Integer  toleranciaPreempcoesCT0;
	Integer  toleranciaPreempcoesCT1;
	Integer  toleranciaPreempcoesCT2;
	Integer  toleranciaBloqueiosCT0;
	Integer  toleranciaBloqueiosCT1;
	Integer  toleranciaBloqueiosCT2;
	Integer  toleranciaDevolucoesCT0;
	Integer  toleranciaDevolucoesCT1;
	Integer  toleranciaDevolucoesCT2;
	//Medições
	Double  utilizacaoDoEnlaceCT0;
	Double  utilizacaoDoEnlaceCT1;
	Double  utilizacaoDoEnlaceCT2;
	Integer  numeroDePreempcoesCT0;
	Integer  numeroDePreempcoesCT1;
	Integer  numeroDePreempcoesCT2;
	Integer  numeroDeBloqueiosCT0;
	Integer  numeroDeBloqueiosCT1;
	Integer  numeroDeBloqueiosCT2;
	Integer  numeroDeDevolucoesCT0;
	Integer  numeroDeDevolucoesCT1;
	Integer  numeroDeDevolucoesCT2;
	
	@Override
	public BAMDescription clone() {
	   try {
		return (BAMDescription) super.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}

	

	public String getCaseId() {
		return caseId;
	}



	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}


	
	public String getGestor() {
		return gestor;
	}



	public void setGestor(String gestor) {
		this.gestor = gestor;
	}
	
	

	public BAMTypes getBAMAtual() {
		return BAMAtual;
	}



	public void setBAMAtual(BAMTypes bAMAtual) {
		BAMAtual = bAMAtual;
	}



	public Problemas getProblema() {
		return problema;
	}



	public void setProblema(Problemas problema) {
		this.problema = problema;
	}



	public Double getBC0() {
		return BC0;
	}



	public void setBC0(Double bC) {
		BC0 = bC;
	}
	
	public Double getBC1() {
		return BC1;
	}



	public void setBC1(Double bC) {
		BC1 = bC;
	}
	
	public Double getBC2() {
		return BC2;
	}


	public void setBC2(Double bC) {
		BC2 = bC;
	}


	public Integer getToleranciaPreempcoesCT0() {
		return toleranciaPreempcoesCT0;
	}



	public void setToleranciaPreempcoesCT0(Integer toleranciaPreempcoesCT0) {
		this.toleranciaPreempcoesCT0 = toleranciaPreempcoesCT0;
	}



	public Integer getToleranciaPreempcoesCT1() {
		return toleranciaPreempcoesCT1;
	}



	public void setToleranciaPreempcoesCT1(Integer toleranciaPreempcoesCT1) {
		this.toleranciaPreempcoesCT1 = toleranciaPreempcoesCT1;
	}



	public Integer getToleranciaPreempcoesCT2() {
		return toleranciaPreempcoesCT2;
	}



	public void setToleranciaPreempcoesCT2(Integer toleranciaPreempcoesCT2) {
		this.toleranciaPreempcoesCT2 = toleranciaPreempcoesCT2;
	}



	public Integer getToleranciaBloqueiosCT0() {
		return toleranciaBloqueiosCT0;
	}



	public void setToleranciaBloqueiosCT0(Integer toleranciaBloqueiosCT0) {
		this.toleranciaBloqueiosCT0 = toleranciaBloqueiosCT0;
	}



	public Integer getToleranciaBloqueiosCT1() {
		return toleranciaBloqueiosCT1;
	}



	public void setToleranciaBloqueiosCT1(Integer toleranciaBloqueiosCT1) {
		this.toleranciaBloqueiosCT1 = toleranciaBloqueiosCT1;
	}



	public Integer getToleranciaBloqueiosCT2() {
		return toleranciaBloqueiosCT2;
	}



	public void setToleranciaBloqueiosCT2(Integer toleranciaBloqueiosCT2) {
		this.toleranciaBloqueiosCT2 = toleranciaBloqueiosCT2;
	}



	public Integer getToleranciaDevolucoesCT0() {
		return toleranciaDevolucoesCT0;
	}



	public void setToleranciaDevolucoesCT0(Integer toleranciaDevolucoesCT0) {
		this.toleranciaDevolucoesCT0 = toleranciaDevolucoesCT0;
	}



	public Integer getToleranciaDevolucoesCT1() {
		return toleranciaDevolucoesCT1;
	}



	public void setToleranciaDevolucoesCT1(Integer toleranciaDevolucoesCT1) {
		this.toleranciaDevolucoesCT1 = toleranciaDevolucoesCT1;
	}



	public Integer getToleranciaDevolucoesCT2() {
		return toleranciaDevolucoesCT2;
	}



	public void setToleranciaDevolucoesCT2(Integer toleranciaDevolucoesCT2) {
		this.toleranciaDevolucoesCT2 = toleranciaDevolucoesCT2;
	}



	public Double getUtilizacaoDoEnlaceCT0() {
		return utilizacaoDoEnlaceCT0;
	}



	public void setUtilizacaoDoEnlaceCT0(Double utilizacaoDoEnlaceCT0) {
		this.utilizacaoDoEnlaceCT0 = utilizacaoDoEnlaceCT0;
	}



	public Double getUtilizacaoDoEnlaceCT1() {
		return utilizacaoDoEnlaceCT1;
	}



	public void setUtilizacaoDoEnlaceCT1(Double utilizacaoDoEnlaceCT1) {
		this.utilizacaoDoEnlaceCT1 = utilizacaoDoEnlaceCT1;
	}



	public Double getUtilizacaoDoEnlaceCT2() {
		return utilizacaoDoEnlaceCT2;
	}



	public void setUtilizacaoDoEnlaceCT2(Double utilizacaoDoEnlaceCT2) {
		this.utilizacaoDoEnlaceCT2 = utilizacaoDoEnlaceCT2;
	}



	public Integer getNumeroDePreempcoesCT0() {
		return numeroDePreempcoesCT0;
	}



	public void setNumeroDePreempcoesCT0(Integer numeroDePreempcoesCT0) {
		this.numeroDePreempcoesCT0 = numeroDePreempcoesCT0;
	}



	public Integer getNumeroDePreempcoesCT1() {
		return numeroDePreempcoesCT1;
	}



	public void setNumeroDePreempcoesCT1(Integer numeroDePreempcoesCT1) {
		this.numeroDePreempcoesCT1 = numeroDePreempcoesCT1;
	}



	public Integer getNumeroDePreempcoesCT2() {
		return numeroDePreempcoesCT2;
	}



	public void setNumeroDePreempcoesCT2(Integer numeroDePreempcoesCT2) {
		this.numeroDePreempcoesCT2 = numeroDePreempcoesCT2;
	}



	public Integer getNumeroDeBloqueiosCT0() {
		return numeroDeBloqueiosCT0;
	}



	public void setNumeroDeBloqueiosCT0(Integer numeroDeBloqueiosCT0) {
		this.numeroDeBloqueiosCT0 = numeroDeBloqueiosCT0;
	}



	public Integer getNumeroDeBloqueiosCT1() {
		return numeroDeBloqueiosCT1;
	}



	public void setNumeroDeBloqueiosCT1(Integer numeroDeBloqueiosCT1) {
		this.numeroDeBloqueiosCT1 = numeroDeBloqueiosCT1;
	}



	public Integer getNumeroDeBloqueiosCT2() {
		return numeroDeBloqueiosCT2;
	}



	public void setNumeroDeBloqueiosCT2(Integer numeroDeBloqueiosCT2) {
		this.numeroDeBloqueiosCT2 = numeroDeBloqueiosCT2;
	}



	public Integer getNumeroDeDevolucoesCT0() {
		return numeroDeDevolucoesCT0;
	}



	public void setNumeroDeDevolucoesCT0(Integer numeroDeDevolucoesCT0) {
		this.numeroDeDevolucoesCT0 = numeroDeDevolucoesCT0;
	}



	public Integer getNumeroDeDevolucoesCT1() {
		return numeroDeDevolucoesCT1;
	}



	public void setNumeroDeDevolucoesCT1(Integer numeroDeDevolucoesCT1) {
		this.numeroDeDevolucoesCT1 = numeroDeDevolucoesCT1;
	}



	public Integer getNumeroDeDevolucoesCT2() {
		return numeroDeDevolucoesCT2;
	}



	public void setNumeroDeDevolucoesCT2(Integer numeroDeDevolucoesCT2) {
		this.numeroDeDevolucoesCT2 = numeroDeDevolucoesCT2;
	}

	public Attribute getIdAttribute() {
		return new Attribute("caseId", this.getClass());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(CaseID=" + caseId 
				//inserir  gestor e tolerancia  <=
				+ ", BAMAtual=" + BAMAtual
				+ ", Problema=" + problema
				+ ", utilizacaoDoEnlaceCT0=" + utilizacaoDoEnlaceCT0
				+ ", utilizacaoDoEnlaceCT1=" + utilizacaoDoEnlaceCT1
				+ ", utilizacaoDoEnlaceCT2=" + utilizacaoDoEnlaceCT2
				+ ", numeroDePreempcoesCT0=" + numeroDePreempcoesCT0
				+ ", numeroDePreempcoesCT1=" + numeroDePreempcoesCT1
				+ ", numeroDePreempcoesCT2=" + numeroDePreempcoesCT2
				+ ", numeroDeBloqueiosCT0=" + numeroDeBloqueiosCT0
				+ ", numeroDeBloqueiosCT1=" + numeroDeBloqueiosCT1
				+ ", numeroDeBloqueiosCT2=" + numeroDeBloqueiosCT2
				+ ", numeroDeDevolucoesCT0=" + numeroDeDevolucoesCT0
				+ ", numeroDeDevolucoesCT1=" + numeroDeDevolucoesCT1
				+ ", numeroDeDevolucoesCT2=" + numeroDeDevolucoesCT2 + ")";
	}
/*	public String toString()
	{
		return "("+caseId+";"+HolidayType+";"+NumberOfPersons+";"+Region+";"+Transportation+";"+Duration+";"+Season+";"+Accommodation+")";
	}*/

}
