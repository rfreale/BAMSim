/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
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
		NoPreemptionMAM(0), NoPreemptionRDM(1), NoPreemptionAllocCTSharing(2),PreemptionMAM(3), PreemptionRDM(4), PreemptionAllocCTSharing(5),PreemptionGBAM(6);
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
		AltaPreempcao(0), AltoBloqueio(1), AltaDevolucao(2), BaixaUtilizacao(3),
		AltaPreempcaoCT0(4), AltoBloqueioCT0(5), AltaDevolucaoCT0(6),
		AltaPreempcaoCT1(7), AltoBloqueioCT1(8), AltaDevolucaoCT1(9),
		AltaPreempcaoCT2(10), AltoBloqueioCT2(11), AltaDevolucaoCT2(12);
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
	BAMTypes BAMAtual;
	Problemas problema;
	Double  utilizacaoDoEnlace;
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

	/**
	 * @return the problema
	 */
	public Problemas getProblema() {
		return problema;
	}


	/**
	 * @param problema the problema to set
	 */
	public void setProblema(Problemas problema) {
		this.problema = problema;
	} 
	
	/**
	 * @return the utilizacaoDoEnlace
	 */
	public Double getUtilizacaoDoEnlace() {
		return utilizacaoDoEnlace;
	}

	/**
	 * @param utilizacaoDoEnlace the utilizacaoDoEnlace to set
	 */
	public void setUtilizacaoDoEnlace(Double utilizacaoDoEnlace) {
		this.utilizacaoDoEnlace = utilizacaoDoEnlace;
	}

	/**
	 * @return the numeroDePreempcoesCT0
	 */
	public Integer getNumeroDePreempcoesCT0() {
		return numeroDePreempcoesCT0;
	}

	/**
	 * @param numeroDePreempcoesCT0 the numeroDePreempcoesCT0 to set
	 */
	public void setNumeroDePreempcoesCT0(Integer numeroDePreempcoesCT0) {
		this.numeroDePreempcoesCT0 = numeroDePreempcoesCT0;
	}

	/**
	 * @return the numeroDePreempcoesCT1
	 */
	public Integer getNumeroDePreempcoesCT1() {
		return numeroDePreempcoesCT1;
	}

	/**
	 * @param numeroDePreempcoesCT1 the numeroDePreempcoesCT1 to set
	 */
	public void setNumeroDePreempcoesCT1(Integer numeroDePreempcoesCT1) {
		this.numeroDePreempcoesCT1 = numeroDePreempcoesCT1;
	}

	/**
	 * @return the numeroDePreempcoesCT2
	 */
	public Integer getNumeroDePreempcoesCT2() {
		return numeroDePreempcoesCT2;
	}

	/**
	 * @param numeroDePreempcoesCT2 the numeroDePreempcoesCT2 to set
	 */
	public void setNumeroDePreempcoesCT2(Integer numeroDePreempcoesCT2) {
		this.numeroDePreempcoesCT2 = numeroDePreempcoesCT2;
	}

	/**
	 * @return the numeroDeBloqueiosCT0
	 */
	public Integer getNumeroDeBloqueiosCT0() {
		return numeroDeBloqueiosCT0;
	}

	/**
	 * @param numeroDeBloqueiosCT0 the numeroDeBloqueiosCT0 to set
	 */
	public void setNumeroDeBloqueiosCT0(Integer numeroDeBloqueiosCT0) {
		this.numeroDeBloqueiosCT0 = numeroDeBloqueiosCT0;
	}

	/**
	 * @return the numeroDeBloqueiosCT1
	 */
	public Integer getNumeroDeBloqueiosCT1() {
		return numeroDeBloqueiosCT1;
	}

	/**
	 * @param numeroDeBloqueiosCT1 the numeroDeBloqueiosCT1 to set
	 */
	public void setNumeroDeBloqueiosCT1(Integer numeroDeBloqueiosCT1) {
		this.numeroDeBloqueiosCT1 = numeroDeBloqueiosCT1;
	}

	/**
	 * @return the numeroDeBloqueiosCT2
	 */
	public Integer getNumeroDeBloqueiosCT2() {
		return numeroDeBloqueiosCT2;
	}

	/**
	 * @param numeroDeBloqueiosCT2 the numeroDeBloqueiosCT2 to set
	 */
	public void setNumeroDeBloqueiosCT2(Integer numeroDeBloqueiosCT2) {
		this.numeroDeBloqueiosCT2 = numeroDeBloqueiosCT2;
	}

	/**
	 * @return the numeroDeDevolucoesCT0
	 */
	public Integer getNumeroDeDevolucoesCT0() {
		return numeroDeDevolucoesCT0;
	}

	/**
	 * @param numeroDeDevolucoesCT0 the numeroDeDevolucoesCT0 to set
	 */
	public void setNumeroDeDevolucoesCT0(Integer numeroDeDevolucoesCT0) {
		this.numeroDeDevolucoesCT0 = numeroDeDevolucoesCT0;
	}

	/**
	 * @return the numeroDeDevolucoesCT1
	 */
	public Integer getNumeroDeDevolucoesCT1() {
		return numeroDeDevolucoesCT1;
	}

	/**
	 * @param numeroDeDevolucoesCT1 the numeroDeDevolucoesCT1 to set
	 */
	public void setNumeroDeDevolucoesCT1(Integer numeroDeDevolucoesCT1) {
		this.numeroDeDevolucoesCT1 = numeroDeDevolucoesCT1;
	}

	/**
	 * @return the numeroDeDevolucoesCT2
	 */
	public Integer getNumeroDeDevolucoesCT2() {
		return numeroDeDevolucoesCT2;
	}

	/**
	 * @param numeroDeDevolucoesCT2 the numeroDeDevolucoesCT2 to set
	 */
	public void setNumeroDeDevolucoesCT2(Integer numeroDeDevolucoesCT2) {
		this.numeroDeDevolucoesCT2 = numeroDeDevolucoesCT2;
	}

	/**
	 * @return the BAMAtual
	 */
	public BAMTypes getBAMAtual() {
		return BAMAtual;
	}
	/**
	 * @param BAMAtual the BAMAtual to set
	 */
	public void setBAMAtual(BAMTypes _BAMAtual) {
		BAMAtual = _BAMAtual;
	}

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the duration
	 */

	public Attribute getIdAttribute() {
		return new Attribute("caseId", this.getClass());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(CaseID=" + caseId 
				+ ", BAMAtual=" + BAMAtual
				+ ", Problema=" + problema
				+ ", utilizacaoDoEnlace=" + utilizacaoDoEnlace
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
