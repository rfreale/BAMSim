/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package BAM.BAMRecommender;
import BAM.BAMRecommender.BAMDescription.BAMTypes;
import jcolibri.cbrcore.Attribute;

/**
 * Bean that stores the solution of the case (trip)
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class BAMSolution  implements jcolibri.cbrcore.CaseComponent, Cloneable {

	String id;
	BAMTypes BAMNovo;
	Boolean aceita;

	@Override
	public BAMSolution clone() {
	   try {
		return (BAMSolution) super.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	public String toString()
	{
		return "("+id+";"+BAMNovo+";"+aceita+")";
	}
	
	public Attribute getIdAttribute() {
		
		return new Attribute("id", this.getClass());
	}

	
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the bAMNovo
	 */
	public BAMTypes getBAMNovo() {
		return BAMNovo;
	}

	/**
	 * @param bAMNovo the bAMNovo to set
	 */
	public void setBAMNovo(BAMTypes bAMNovo) {
		BAMNovo = bAMNovo;
	}

	/**
	 * @return the aceita
	 */
	public Boolean getAceita() {
		return aceita;
	}

	/**
	 * @param aceita the aceita to set
	 */
	public void setAceita(Boolean aceita) {
		this.aceita = aceita;
	}

	
	
}
