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
	
	//SLA
	Integer  SLAUtilizacaoCT0;
	Integer  SLAUtilizacaoCT1;
	Integer  SLAUtilizacaoCT2;
	
	Integer  SLAPreempcoesCT0;
	Integer  SLAPreempcoesCT1;
	Integer  SLAPreempcoesCT2;
	
	Integer  SLABloqueiosCT0;
	Integer  SLABloqueiosCT1;
	Integer  SLABloqueiosCT2;
	
	Integer  SLADevolucoesCT0;
	Integer  SLADevolucoesCT1;
	Integer  SLADevolucoesCT2;
	
	//Limites
	Integer BC0;
	Integer BC1;
	Integer BC2;
		
		
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



	
	public Integer getSLAUtilizacaoCT0() {
		return SLAUtilizacaoCT0;
	}



	public void setSLAUtilizacaoCT0(Integer sLAUtilizacaoCT0) {
		SLAUtilizacaoCT0 = sLAUtilizacaoCT0;
	}



	public Integer getSLAUtilizacaoCT1() {
		return SLAUtilizacaoCT1;
	}



	public void setSLAUtilizacaoCT1(Integer sLAUtilizacaoCT1) {
		SLAUtilizacaoCT1 = sLAUtilizacaoCT1;
	}



	public Integer getSLAUtilizacaoCT2() {
		return SLAUtilizacaoCT2;
	}



	public void setSLAUtilizacaoCT2(Integer sLAUtilizacaoCT2) {
		SLAUtilizacaoCT2 = sLAUtilizacaoCT2;
	}

	

	public Integer getSLAPreempcoesCT0() {
		return SLAPreempcoesCT0;
	}



	public void setSLAPreempcoesCT0(Integer SLAPreempcoesCT0) {
		this.SLAPreempcoesCT0 = SLAPreempcoesCT0;
	}



	public Integer getSLAPreempcoesCT1() {
		return SLAPreempcoesCT1;
	}



	public void setSLAPreempcoesCT1(Integer SLAPreempcoesCT1) {
		this.SLAPreempcoesCT1 = SLAPreempcoesCT1;
	}



	public Integer getSLAPreempcoesCT2() {
		return SLAPreempcoesCT2;
	}



	public void setSLAPreempcoesCT2(Integer SLAPreempcoesCT2) {
		this.SLAPreempcoesCT2 = SLAPreempcoesCT2;
	}



	public Integer getSLABloqueiosCT0() {
		return SLABloqueiosCT0;
	}



	public void setSLABloqueiosCT0(Integer SLABloqueiosCT0) {
		this.SLABloqueiosCT0 = SLABloqueiosCT0;
	}



	public Integer getSLABloqueiosCT1() {
		return SLABloqueiosCT1;
	}



	public void setSLABloqueiosCT1(Integer SLABloqueiosCT1) {
		this.SLABloqueiosCT1 = SLABloqueiosCT1;
	}



	public Integer getSLABloqueiosCT2() {
		return SLABloqueiosCT2;
	}



	public void setSLABloqueiosCT2(Integer SLABloqueiosCT2) {
		this.SLABloqueiosCT2 = SLABloqueiosCT2;
	}



	public Integer getSLADevolucoesCT0() {
		return SLADevolucoesCT0;
	}



	public void setSLADevolucoesCT0(Integer SLADevolucoesCT0) {
		this.SLADevolucoesCT0 = SLADevolucoesCT0;
	}



	public Integer getSLADevolucoesCT1() {
		return SLADevolucoesCT1;
	}



	public void setSLADevolucoesCT1(Integer SLADevolucoesCT1) {
		this.SLADevolucoesCT1 = SLADevolucoesCT1;
	}



	public Integer getSLADevolucoesCT2() {
		return SLADevolucoesCT2;
	}



	public void setSLADevolucoesCT2(Integer SLADevolucoesCT2) {
		this.SLADevolucoesCT2 = SLADevolucoesCT2;
	}


	
	
	


	public Integer getBC0() {
		return BC0;
	}



	public void setBC0(Integer BC0) {
		this.BC0 = BC0;
	}



	public Integer getBC1() {
		return BC1;
	}



	public void setBC1(Integer BC1) {
		this.BC1 = BC1;
	}



	public Integer getBC2() {
		return BC2;
	}



	public void setBC2(Integer BC2) {
		this.BC2 = BC2;
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
				+ ", gestor=" + gestor              //inserir  gestor e SLA  <=
				+ ", BAMAtual=" + BAMAtual
				+ ", Problema=" + problema
				+ ", SLAUtilizacaoCT0=" + SLAUtilizacaoCT0
				+ ", SLAUtilizacaoCT1=" + SLAUtilizacaoCT1
				+ ", SLAUtilizacaoCT2=" + SLAUtilizacaoCT2
				+ ", SLAPreempcoesCT0=" + SLAPreempcoesCT0
				+ ", SLAPreempcoesCT1=" + SLAPreempcoesCT1
				+ ", SLAPreempcoesCT2=" + SLAPreempcoesCT2
				+ ", SLABloqueiosCT0=" + SLABloqueiosCT0
				+ ", SLABloqueiosCT1=" + SLABloqueiosCT1
				+ ", SLABloqueiosCT2=" + SLABloqueiosCT2
				+ ", SLADevolucoesCT0=" + SLADevolucoesCT0
				+ ", SLADevolucoesCT1=" + SLADevolucoesCT1
				+ ", SLADevolucoesCT2=" + SLADevolucoesCT2
				+ ", BC0=" +BC0
				+ ", BC1=" +BC1
				+ ", BC2=" +BC2
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
 * 
 * 
	
	
 * 
 * 
 * 
 * 
	{
		return "("+caseId+";"+HolidayType+";"+NumberOfPersons+";"+Region+";"+Transportation+";"+Duration+";"+Season+";"+Accommodation+")";
	}*/

}
