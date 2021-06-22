/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
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
public class BAMRecommender implements StandardCBRApplication {

	private static BAMRecommender _instance = null;
	public  static BAMRecommender getInstance()
	{
		if(_instance == null)
		   _instance = new BAMRecommender();
		return _instance;
	}
	
	private BAMRecommender()
	{
	}
	
	/** Connector object */
	Connector _connector;
	/** CaseBase object */
	CBRCaseBase _caseBase;
	
	/** Connector object */
	Connector _connector2;
	/** CaseBase object */
	CBRCaseBase _caseBase2;
	
	SimilarityDialog similarityDialog;
	ResultDialog resultDialog;
	AutoAdaptationDialog autoAdaptDialog;
	RevisionDialog revisionDialog;
	RetainDialog retainDialog;
	
	
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
			
			
			// Create a data base connector
			_connector2 = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connector2.initFromXMLfile(jcolibri.util.FileIO
					.findFile("BAM/BAMRecommender/databaseconfig2.xml"));
			// Create a Lineal case base for in-memory organization
			_caseBase2 = new LinealCaseBase();
			
			// Obtain a reference to OntoBridge
			//OntoBridge ob = jcolibri.util.OntoBridgeSingleton.getOntoBridge();
			// Configure it to work with the Pellet reasoner
			//ob.initWithPelletReasoner();
			// Setup the main ontology
			//OntologyDocument mainOnto = new OntologyDocument("http://gaia.fdi.ucm.es/ontologies/travel-destinations.owl", 
			//						 FileIO.findFile("jcolibri/examples/TravelRecommender/travel-destinations.owl").toExternalForm());
			// There are not subontologies
			//ArrayList<OntologyDocument> subOntologies = new ArrayList<OntologyDocument>();
			// Load the ontology
			//ob.loadOntology(mainOnto, subOntologies, false);

			// Create the dialogs
			similarityDialog = new SimilarityDialog(main);
			resultDialog     = new ResultDialog(main);
			autoAdaptDialog  = new AutoAdaptationDialog(main);
			revisionDialog   = new RevisionDialog(main);
			retainDialog     = new RetainDialog(main);
			
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
		
		// Load cases from connector into the case base
		_caseBase2.init(_connector2);		
		// Print the cases
		cases = _caseBase2.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		
		
		return _caseBase;
	}

	public CBRCase cycle(CBRQuery query) throws ExecutionException {
		// Obtain configuration for KNN
		similarityDialog.setVisible(true);
		NNConfig simConfig = similarityDialog.getSimilarityConfig();
		simConfig.setDescriptionSimFunction(new Average());
		
		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		
		// Select k cases
		Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval, similarityDialog.getK());
		
		// Show result
		resultDialog.showCases(eval, selectedcases);
		resultDialog.setVisible(true);
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
		retainDialog.showCases(selectedcases, _caseBase.getCases().size(), query);
		retainDialog.setVisible(true);
		Collection<CBRCase> casesToRetain = retainDialog.getCasestoRetain();
		_caseBase.learnCases(casesToRetain);
		//jcolibri.method.retain.StoreCasesMethod.storeCases(_caseBase, casesToRetain);
		//jcolibri.method.retain.StoreCasesMethod.storeCase(_caseBase, casesToRetain.iterator().next());

		return null;
	}

	public void postCycle() throws ExecutionException {
		_connector.close();
		HSQLDBserver.shutDown();
	}

	static JFrame main;
	void showMainFrame()
	{
		main = new JFrame("BAM Recommender");
		main.setResizable(false);
		main.setUndecorated(true);
		JLabel label = new JLabel(new ImageIcon(jcolibri.util.FileIO.findFile("BAM/BAMRecommender/jcolibri2.jpg")));
		main.getContentPane().add(label);
		main.pack();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		main.setBounds((screenSize.width - main.getWidth()) / 2,
			(screenSize.height - main.getHeight()) / 2, 
			main.getWidth(),
			main.getHeight());
		main.setVisible(true);
	}
	
	public static void main(String[] args) {
	
		BAMRecommender recommender = getInstance();
		recommender.showMainFrame();
		try
		{
			recommender.configure();
			recommender.preCycle();

			QueryDialog qf = new QueryDialog(main);
			

			boolean cont = true;
			while(cont)
			{
				qf.setVisible(true);
				CBRQuery query = qf.getQuery();
			
				recommender.cycle(query);
				int ans = javax.swing.JOptionPane.showConfirmDialog(null, "CBR cycle finished, query again?", "Cycle finished", javax.swing.JOptionPane.YES_NO_OPTION);
				cont = (ans == javax.swing.JOptionPane.YES_OPTION);
			}
			recommender.postCycle();
		}catch(Exception e)
		{
			org.apache.commons.logging.LogFactory.getLog(BAMRecommender.class).error(e);
			javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.exit(0);
	}
}
