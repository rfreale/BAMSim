/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package BAM.BAMRecommender;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import BAM.BAMRecommender.gui.AutoAdaptationDialog;
import BAM.BAMRecommender.gui.QueryDialog;
import BAM.BAMRecommender.gui.ResultDialog;
import BAM.BAMRecommender.gui.RetainDialog;
import BAM.BAMRecommender.gui.RevisionDialog;
import BAM.BAMRecommender.gui.SimilarityDialog;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.method.reuse.NumericDirectProportionMethod;
import jcolibri.util.FileIO;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;


/**
 * Implementes the recommender main class
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class BAMRecommenderNoGUI implements StandardCBRApplication {

	int K=2;
	NNConfig simConfig;
	/**
	 * @return the simConfig
	 */
	public NNConfig getSimConfig() {
		return simConfig;
	}

	/**
	 * @param simConfig the simConfig to set
	 */
	public void setSimConfig(NNConfig simConfig) {
		this.simConfig = simConfig;
	}

	private static BAMRecommenderNoGUI _instance = null;
	
	public  static BAMRecommenderNoGUI getInstance()
	{
		if(_instance == null)
		   _instance = new BAMRecommenderNoGUI();
		return _instance;
	}
	
	private BAMRecommenderNoGUI()
	{
	}
	
	/** Connector object */
	Connector _connector;
	/** CaseBase object */
	CBRCaseBase _caseBase;
	
	
	
	/**
	 * @return the _caseBase
	 */
	public CBRCaseBase getCaseBase() {
		return _caseBase;
	}


	public void configure() throws ExecutionException {
		try {
			//Emulate data base server
			BAM.BAMRecommender.HSQLDBserver.init();
			
			// Create a data base connector
			_connector = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connector.initFromXMLfile(jcolibri.util.FileIO
					.findFile("BAM/BAMRecommender/databaseconfig.xml"));
			// Create a Lineal case base for in-memory organization
			_caseBase = new LinealCaseBase();
			
			
			
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		// Load cases from connector into the case base
		_caseBase.init(_connector);		
		// Print the cases
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		return _caseBase;
	}

	public CBRCase cycle(CBRQuery query) throws ExecutionException {
		
		

		// Obtain configuration for KNN

		simConfig.setDescriptionSimFunction(new Average());
		
		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		// Select k cases
		Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval, 1);
		
		return selectedcases.iterator().next();
		// Show result
		/*
		ArrayList<RetrievalResult> cases;
		cases = new ArrayList<RetrievalResult>();
		for(RetrievalResult rr: eval)
		{
			if(selectedcases.contains(rr.get_case()))
				cases.add(rr);
		}

				
		for(RetrievalResult rr: cases)
		{	
				double sim = rr.getEval();
				
				CBRCase _case = rr.get_case();
								
				BAMDescription desc = (BAMDescription) _case.getDescription();
								
				System.out.println(desc.toString());
					
				BAMSolution sol = (BAMSolution) _case.getSolution();
				System.out.println(sol.toString());
		}
		
		BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
		desc.setCaseId("BAM"+_caseBase.getCases().size());
		BAMSolution sol = ((BAMSolution) cases.get(0).get_case().getSolution()).clone();
		sol.setId("BAM"+_caseBase.getCases().size());
		CBRCase novocase = new CBRCase();
		novocase.setDescription(desc);
		novocase.setSolution(sol);
		jcolibri.method.retain.StoreCasesMethod.storeCase(_caseBase, novocase);
		
		*/
		/*
		// Show adaptation dialog
		autoAdaptDialog.setVisible(false);
		
		// Adapt depending on user selection
		if(autoAdaptDialog.adapt_Duration_Price())
		{
			// Compute a direct proportion between the "Duration" and "Price" attributes.
			NumericDirectProportionMethod.directProportion(	new Attribute("Duration",BAMDescription.class), 
				 											new Attribute("price",BAMSolution.class), 
				 											query, selectedcases);
		}
		
		if(autoAdaptDialog.adapt_NumberOfPersons_Price())
		{
			// Compute a direct proportion between the "Duration" and "Price" attributes.
			NumericDirectProportionMethod.directProportion(	new Attribute("NumberOfPersons",BAMDescription.class), 
				 											new Attribute("price",BAMSolution.class), 
				 											query, selectedcases);
		}
		*/
		// Revise
		//revisionDialog.showCases(selectedcases);
		//revisionDialog.setVisible(true);
		
		// Retain
		//retainDialog.showCases(selectedcases, _caseBase.getCases().size(), query);
		//retainDialog.setVisible(true);
		//Collection<CBRCase> casesToRetain = retainDialog.getCasestoRetain();
		//_caseBase.learnCases(casesToRetain);
		//jcolibri.method.retain.StoreCasesMethod.storeCases(_caseBase, casesToRetain);
		//jcolibri.method.retain.StoreCasesMethod.storeCase(_caseBase, casesToRetain.iterator().next());
		
		
	}

	public void postCycle() throws ExecutionException {
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		_connector.close();
		HSQLDBserver.shutDown();
	}


	
	}
