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
	
	





	/*public enum BAMTypes {
		NoPreemptionMAM(0), NoPreemptionRDM(1), NoPreemptionAllocCTSharing(2),PreemptionMAM(3), PreemptionRDM(4), PreemptionAllocCTSharing(5),PreemptionGBAM(6),NoPreemptionBestEffort(7);
		private  int status; 
		
		BAMTypes(int valorStatus)
		{ 
			status = valorStatus; 
		} 
		public int getValor()
		{ 
			return status; 
		}
		public void setValor(int valorStatus)
		{ 
			status = valorStatus; 
		} 

	}*/
/*	public enum Problemas {
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


	}*/
	//Colocar sintoma
	String  caseId;
	//Inclusão do gestor
	int link;
	//Inclusão do gestor
	String gestor;
	

	BAMTypes BAMAtual;
	//Problemas problema;
	
	long janela;
	
	//SLA
	Double  SLAUtilizacaoCT0;
	Double  SLAUtilizacaoCT1;
	Double  SLAUtilizacaoCT2;
	
	Double  SLAPreempcoesCT0;
	Double  SLAPreempcoesCT1;
	Double  SLAPreempcoesCT2;
	
	Double  SLABloqueiosCT0;
	Double  SLABloqueiosCT1;
	Double  SLABloqueiosCT2;
	
	Double  SLADevolucoesCT0;
	Double  SLADevolucoesCT1;
	Double  SLADevolucoesCT2;
	
	//Limites
	Double BC0;
	Double BC1;
	Double BC2;
		
		
	//Medições
	Double  utilizacaoDoEnlaceCT0;
	Double  utilizacaoDoEnlaceCT1;
	Double  utilizacaoDoEnlaceCT2;
	
	Double  numeroDeBloqueiosCT0;
	Double  numeroDeBloqueiosCT1;
	Double  numeroDeBloqueiosCT2;
	
	Double  numeroDePreempcoesCT0;
	Double  numeroDePreempcoesCT1;
	Double  numeroDePreempcoesCT2;
	
	Double  numeroDeDevolucoesCT0;
	Double  numeroDeDevolucoesCT1;
	Double  numeroDeDevolucoesCT2;
	
	
	
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

	public int getLink() {
		return link;
	}



	public void setLink(int link) {
		this.link = link;
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


	
	public long getJanela() {
		return janela;
	}



	public void setJanela(long janela) {
		this.janela = janela;
	}




	
	
	

	public Double getSLAUtilizacaoCT0() {
		return SLAUtilizacaoCT0;
	}



	public void setSLAUtilizacaoCT0(Double sLAUtilizacaoCT0) {
		SLAUtilizacaoCT0 = sLAUtilizacaoCT0;
	}



	public Double getSLAUtilizacaoCT1() {
		return SLAUtilizacaoCT1;
	}



	public void setSLAUtilizacaoCT1(Double sLAUtilizacaoCT1) {
		SLAUtilizacaoCT1 = sLAUtilizacaoCT1;
	}



	public Double getSLAUtilizacaoCT2() {
		return SLAUtilizacaoCT2;
	}



	public void setSLAUtilizacaoCT2(Double sLAUtilizacaoCT2) {
		SLAUtilizacaoCT2 = sLAUtilizacaoCT2;
	}



	public Double getSLAPreempcoesCT0() {
		return SLAPreempcoesCT0;
	}



	public void setSLAPreempcoesCT0(Double sLAPreempcoesCT0) {
		SLAPreempcoesCT0 = sLAPreempcoesCT0;
	}



	public Double getSLAPreempcoesCT1() {
		return SLAPreempcoesCT1;
	}



	public void setSLAPreempcoesCT1(Double sLAPreempcoesCT1) {
		SLAPreempcoesCT1 = sLAPreempcoesCT1;
	}



	public Double getSLAPreempcoesCT2() {
		return SLAPreempcoesCT2;
	}



	public void setSLAPreempcoesCT2(Double sLAPreempcoesCT2) {
		SLAPreempcoesCT2 = sLAPreempcoesCT2;
	}



	public Double getSLABloqueiosCT0() {
		return SLABloqueiosCT0;
	}



	public void setSLABloqueiosCT0(Double sLABloqueiosCT0) {
		SLABloqueiosCT0 = sLABloqueiosCT0;
	}



	public Double getSLABloqueiosCT1() {
		return SLABloqueiosCT1;
	}



	public void setSLABloqueiosCT1(Double sLABloqueiosCT1) {
		SLABloqueiosCT1 = sLABloqueiosCT1;
	}



	public Double getSLABloqueiosCT2() {
		return SLABloqueiosCT2;
	}



	public void setSLABloqueiosCT2(Double sLABloqueiosCT2) {
		SLABloqueiosCT2 = sLABloqueiosCT2;
	}



	public Double getSLADevolucoesCT0() {
		return SLADevolucoesCT0;
	}



	public void setSLADevolucoesCT0(Double sLADevolucoesCT0) {
		SLADevolucoesCT0 = sLADevolucoesCT0;
	}



	public Double getSLADevolucoesCT1() {
		return SLADevolucoesCT1;
	}



	public void setSLADevolucoesCT1(Double sLADevolucoesCT1) {
		SLADevolucoesCT1 = sLADevolucoesCT1;
	}



	public Double getSLADevolucoesCT2() {
		return SLADevolucoesCT2;
	}



	public void setSLADevolucoesCT2(Double sLADevolucoesCT2) {
		SLADevolucoesCT2 = sLADevolucoesCT2;
	}



	public Double getBC0() {
		return BC0;
	}



	public void setBC0(Double bC0) {
		BC0 = bC0;
	}



	public Double getBC1() {
		return BC1;
	}



	public void setBC1(Double bC1) {
		BC1 = bC1;
	}



	public Double getBC2() {
		return BC2;
	}



	public void setBC2(Double bC2) {
		BC2 = bC2;
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



		public Double getNumeroDeBloqueiosCT0() {
		return numeroDeBloqueiosCT0;
	}



	public void setNumeroDeBloqueiosCT0(Double numeroDeBloqueiosCT0) {
		this.numeroDeBloqueiosCT0 = numeroDeBloqueiosCT0;
	}



	public Double getNumeroDeBloqueiosCT1() {
		return numeroDeBloqueiosCT1;
	}



	public void setNumeroDeBloqueiosCT1(Double numeroDeBloqueiosCT1) {
		this.numeroDeBloqueiosCT1 = numeroDeBloqueiosCT1;
	}



	public Double getNumeroDeBloqueiosCT2() {
		return numeroDeBloqueiosCT2;
	}



	public void setNumeroDeBloqueiosCT2(Double numeroDeBloqueiosCT2) {
		this.numeroDeBloqueiosCT2 = numeroDeBloqueiosCT2;
	}


	
	public Double getNumeroDePreempcoesCT0() {
		return numeroDePreempcoesCT0;
	}



	public void setNumeroDePreempcoesCT0(Double numeroDePreempcoesCT0) {
		this.numeroDePreempcoesCT0 = numeroDePreempcoesCT0;
	}



	public Double getNumeroDePreempcoesCT1() {
		return numeroDePreempcoesCT1;
	}



	public void setNumeroDePreempcoesCT1(Double numeroDePreempcoesCT1) {
		this.numeroDePreempcoesCT1 = numeroDePreempcoesCT1;
	}



	public Double getNumeroDePreempcoesCT2() {
		return numeroDePreempcoesCT2;
	}



	public void setNumeroDePreempcoesCT2(Double numeroDePreempcoesCT2) {
		this.numeroDePreempcoesCT2 = numeroDePreempcoesCT2;
	}
	

	public Double getNumeroDeDevolucoesCT0() {
		return numeroDeDevolucoesCT0;
	}



	public void setNumeroDeDevolucoesCT0(Double numeroDeDevolucoesCT0) {
		this.numeroDeDevolucoesCT0 = numeroDeDevolucoesCT0;
	}



	public Double getNumeroDeDevolucoesCT1() {
		return numeroDeDevolucoesCT1;
	}



	public void setNumeroDeDevolucoesCT1(Double numeroDeDevolucoesCT1) {
		this.numeroDeDevolucoesCT1 = numeroDeDevolucoesCT1;
	}



	public Double getNumeroDeDevolucoesCT2() {
		return numeroDeDevolucoesCT2;
	}



	public void setNumeroDeDevolucoesCT2(Double numeroDeDevolucoesCT2) {
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
				+ ", link=" + link 
				+ ", BAMAtual=" + BAMAtual
				+ ", Janela=" + janela
				+ ", SLAUtilizacaoCT0=" + SLAUtilizacaoCT0
				+ ", SLAUtilizacaoCT1=" + SLAUtilizacaoCT1
				+ ", SLAUtilizacaoCT2=" + SLAUtilizacaoCT2
				+ ", SLABloqueiosCT0=" + SLABloqueiosCT0
				+ ", SLABloqueiosCT1=" + SLABloqueiosCT1
				+ ", SLABloqueiosCT2=" + SLABloqueiosCT2
				+ ", SLAPreempcoesCT0=" + SLAPreempcoesCT0
				+ ", SLAPreempcoesCT1=" + SLAPreempcoesCT1
				+ ", SLAPreempcoesCT2=" + SLAPreempcoesCT2
				+ ", SLADevolucoesCT0=" + SLADevolucoesCT0
				+ ", SLADevolucoesCT1=" + SLADevolucoesCT1
				+ ", SLADevolucoesCT2=" + SLADevolucoesCT2
				+ ", BC0=" +BC0
				+ ", BC1=" +BC1
				+ ", BC2=" +BC2
				+ ", utilizacaoDoEnlaceCT0=" + utilizacaoDoEnlaceCT0
				+ ", utilizacaoDoEnlaceCT1=" + utilizacaoDoEnlaceCT1
				+ ", utilizacaoDoEnlaceCT2=" + utilizacaoDoEnlaceCT2
				+ ", numeroDeBloqueiosCT0=" + numeroDeBloqueiosCT0
				+ ", numeroDeBloqueiosCT1=" + numeroDeBloqueiosCT1
				+ ", numeroDeBloqueiosCT2=" + numeroDeBloqueiosCT2
				+ ", numeroDePreempcoesCT0=" + numeroDePreempcoesCT0
				+ ", numeroDePreempcoesCT1=" + numeroDePreempcoesCT1
				+ ", numeroDePreempcoesCT2=" + numeroDePreempcoesCT2
				+ ", numeroDeDevolucoesCT0=" + numeroDeDevolucoesCT0
				+ ", numeroDeDevolucoesCT1=" + numeroDeDevolucoesCT1
				+ ", numeroDeDevolucoesCT2=" + numeroDeDevolucoesCT2 + ")";
	}
	
	
	
	public String toTabela() {
		return  	caseId			+ "\t"
				//+ 	gestor			+ "\t" 
				+ 	link		+ "\t"
				+ 	BAMAtual		+ "\t"
				/*+ 	janela			+ "\t"
				+ 	SLAUtilizacaoCT0+ "\t"
				+ 	SLAUtilizacaoCT1+ "\t"
				+ 	SLAUtilizacaoCT2+ "\t"
				+ 	SLABloqueiosCT0+ "\t"
				+ 	SLABloqueiosCT1+ "\t"
				+ 	SLABloqueiosCT2+ "\t"
				+ 	SLAPreempcoesCT0+ "\t"
				+ 	SLAPreempcoesCT1+ "\t"
				+ 	SLAPreempcoesCT2+ "\t"
				+ 	SLADevolucoesCT0+ "\t"
				+ 	SLADevolucoesCT1+ "\t"
				+ 	SLADevolucoesCT2+ "\t"
				+ 	BC0				+ "\t"
				+	BC1				+ "\t"
				+	BC2				+ "\t"*/
				+	utilizacaoDoEnlaceCT0+ "\t"
				+ 	utilizacaoDoEnlaceCT1+ "\t"
				+	utilizacaoDoEnlaceCT2+ "\t"
				+	numeroDeBloqueiosCT0+ "\t"
				+ 	numeroDeBloqueiosCT1+ "\t"
				+ 	numeroDeBloqueiosCT2+ "\t"
				+ 	numeroDePreempcoesCT0+ "\t"
				+ 	numeroDePreempcoesCT1+ "\t"
				+ 	numeroDePreempcoesCT2+ "\t"
				+ 	numeroDeDevolucoesCT0+ "\t"
				+ 	numeroDeDevolucoesCT1+ "\t"
				+ 	numeroDeDevolucoesCT2+ "\t" ;
	}
	
	
	
	
	public String getInsertDB() {
		String aux="insert into bam values(''";
		aux+=","+link+"";
		aux+=",'"+gestor+"'";
		aux+=",'"+BAMAtual+"'";
		aux+=",'"+janela+"'";
		aux+="," + SLAUtilizacaoCT0;
		aux+="," + SLAUtilizacaoCT1;
		aux+="," + SLAUtilizacaoCT2;
		aux+="," + SLABloqueiosCT0;
		aux+="," + SLABloqueiosCT1;
		aux+="," + SLABloqueiosCT2;
		aux+="," + SLAPreempcoesCT0;
		aux+="," + SLAPreempcoesCT1;
		aux+="," + SLAPreempcoesCT2;
		aux+="," + SLADevolucoesCT0;
		aux+="," + SLADevolucoesCT1;
		aux+="," + SLADevolucoesCT2;
		aux+="," + BC0;
		aux+="," + BC1;
		aux+="," + BC2;
		aux+="," + utilizacaoDoEnlaceCT0;
		aux+="," + utilizacaoDoEnlaceCT1;
		aux+="," + utilizacaoDoEnlaceCT2;
		aux+="," + numeroDeBloqueiosCT0;
		aux+="," + numeroDeBloqueiosCT1;
		aux+="," + numeroDeBloqueiosCT2;
		aux+="," + numeroDePreempcoesCT0;
		aux+="," + numeroDePreempcoesCT1;
		aux+="," + numeroDePreempcoesCT2;
		aux+="," + numeroDeDevolucoesCT0;
		aux+="," + numeroDeDevolucoesCT1;
		aux+="," + numeroDeDevolucoesCT2;
				
		return aux;
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
