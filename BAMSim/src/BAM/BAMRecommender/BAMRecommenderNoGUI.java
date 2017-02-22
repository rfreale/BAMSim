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
import DSTE.BancoDeDados;
import DSTE.ParametrosDSTE;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.method.reuse.NumericDirectProportionMethod;
import jcolibri.util.FileIO;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;

/**
 * Implementes the recommender main class
 * 
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class BAMRecommenderNoGUI implements StandardCBRApplication {

	int K = 2;
	NNConfig simConfig;

	/**
	 * @return the simConfig
	 */
	public NNConfig getSimConfig() {
		return simConfig;
	}

	/**
	 * @param simConfig
	 *            the simConfig to set
	 */
	public void setSimConfigDB2(NNConfig simConfigDB2) {
		this.simConfigDB2 = simConfigDB2;
	}

	NNConfig simConfigDB2 = getSimilarityConfigDB2();

	/**
	 * @return the simConfig
	 */
	public NNConfig getSimConfigDB2() {
		return simConfigDB2;
	}

	/**
	 * @param simConfig
	 *            the simConfig to set
	 */
	public void setSimConfig(NNConfig simConfig) {
		this.simConfig = simConfig;
	}

	private static BAMRecommenderNoGUI _instance = null;

	public static BAMRecommenderNoGUI getInstance() {
		if (_instance == null)
			_instance = new BAMRecommenderNoGUI();
		return _instance;
	}

	private BAMRecommenderNoGUI() {
	}

	/** Connector object */
	Connector _connector;
	/** CaseBase object */
	CBRCaseBase _caseBase;

	/** Connector object */
	Connector _connectorDB2;
	/** CaseBase object */
	CBRCaseBase _caseBaseDB2;

	/**
	 * @return the _caseBase
	 */
	public CBRCaseBase getCaseBase() {
		return _caseBase;
	}

	public CBRCaseBase getCaseBaseDB2() {
		return _caseBaseDB2;
	}

	public void configure() throws ExecutionException {
		try {
			// Emulate data base server
			BAM.BAMRecommender.HSQLDBserver.init();

			// Create a data base connector
			_connector = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("BAM/BAMRecommender/databaseconfig.xml"));
			// Create a Lineal case base for in-memory organization
			_caseBase = new LinealCaseBase();

			// Create a data base connector
			_connectorDB2 = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connectorDB2.initFromXMLfile(jcolibri.util.FileIO.findFile("BAM/BAMRecommender/databaseconfig2.xml"));
			// Create a Lineal case base for in-memory organization
			_caseBaseDB2 = new LinealCaseBase();

		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		// Load cases from connector into the case base
		_caseBase.init(_connector);
		System.out.println("===================DB==================");
		// Print the cases
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for (CBRCase c : cases)
			System.out.println(c);
		System.out.println("===================DB2==================");
		// Load cases from connector into the case base
		_caseBaseDB2.init(_connectorDB2);
		// Print the cases
		cases = _caseBaseDB2.getCases();
		for (CBRCase c : cases)
			System.out.println(c);
		return _caseBase;
	}

	public CBRCase cycle(CBRQuery query) throws ExecutionException {

		// Obtain configuration for KNN

		simConfig.setDescriptionSimFunction(new Average());

		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		// Select k cases
		// Collection<RetrievalResult> selectedcases =
		// SelectCases.selectTopKRR(eval,3);
		//BancoDeDados.setXML("\n");
		//BancoDeDados.setXML("====Query===");

		BancoDeDados.setXML("Query \t" + ((BAMDescription) query.getDescription()).toTabela() );
		 //BancoDeDados.setXML(query.toString());

		// BancoDeDados.setXML(((BAMDescription)query.getDescription()).getInsertDB(),
		// "Debug");
		//BancoDeDados.setXML("====Similar===");
		// BancoDeDados.setXML(eval.toArray()[0].toString(), "Debug");
		Collection<RetrievalResult> selectedcases = SelectCases.selectTopKRR(eval, 10);
		for (RetrievalResult rr : selectedcases) {
			BancoDeDados.setXML("Sim \t" + ((BAMDescription) rr.get_case().getDescription()).toTabela() +"\t"+ ((BAMSolution)rr.get_case().getSolution()).getBAMNovo() +"\t"+ rr.getEval());
			//BancoDeDados.setXML(rr.toString());
		}

		BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
		BAMSolution sol = null;
		CBRCase novocase = new CBRCase();
		novocase.setDescription(desc);

		for (RetrievalResult rr : eval) {

			if (rr.getEval() >= ParametrosDSTE.RecomendacaoCBRLimiarDeCorte) {

				sol = ((BAMSolution) rr.get_case().getSolution()).clone();
				novocase.setSolution(sol);

				if ((!this.equal(novocase, _caseBaseDB2)))
					return rr.get_case();
			} else {
				return null;

			}

		}

		return null;
		// Show result
		/*
		 * ArrayList<RetrievalResult> cases; cases = new
		 * ArrayList<RetrievalResult>(); for(RetrievalResult rr: eval) {
		 * if(selectedcases.contains(rr.get_case())) cases.add(rr); }
		 * 
		 * 
		 * for(RetrievalResult rr: cases) { double sim = rr.getEval();
		 * 
		 * CBRCase _case = rr.get_case();
		 * 
		 * BAMDescription desc = (BAMDescription) _case.getDescription();
		 * 
		 * System.out.println(desc.toString());
		 * 
		 * BAMSolution sol = (BAMSolution) _case.getSolution();
		 * System.out.println(sol.toString()); }
		 * 
		 * BAMDescription desc = ((BAMDescription)
		 * query.getDescription()).clone();
		 * desc.setCaseId("BAM"+_caseBase.getCases().size()); BAMSolution sol =
		 * ((BAMSolution) cases.get(0).get_case().getSolution()).clone();
		 * sol.setId("BAM"+_caseBase.getCases().size()); CBRCase novocase = new
		 * CBRCase(); novocase.setDescription(desc); novocase.setSolution(sol);
		 * jcolibri.method.retain.StoreCasesMethod.storeCase(_caseBase,
		 * novocase);
		 * 
		 */
		/*
		 * // Show adaptation dialog autoAdaptDialog.setVisible(false);
		 * 
		 * // Adapt depending on user selection
		 * if(autoAdaptDialog.adapt_Duration_Price()) { // Compute a direct
		 * proportion between the "Duration" and "Price" attributes.
		 * NumericDirectProportionMethod.directProportion( new
		 * Attribute("Duration",BAMDescription.class), new
		 * Attribute("price",BAMSolution.class), query, selectedcases); }
		 * 
		 * if(autoAdaptDialog.adapt_NumberOfPersons_Price()) { // Compute a
		 * direct proportion between the "Duration" and "Price" attributes.
		 * NumericDirectProportionMethod.directProportion( new
		 * Attribute("NumberOfPersons",BAMDescription.class), new
		 * Attribute("price",BAMSolution.class), query, selectedcases); }
		 */
		// Revise
		// revisionDialog.showCases(selectedcases);
		// revisionDialog.setVisible(true);

		// Retain
		// retainDialog.showCases(selectedcases, _caseBase.getCases().size(),
		// query);
		// retainDialog.setVisible(true);
		// Collection<CBRCase> casesToRetain = retainDialog.getCasestoRetain();
		// _caseBase.learnCases(casesToRetain);
		// jcolibri.method.retain.StoreCasesMethod.storeCases(_caseBase,
		// casesToRetain);
		// jcolibri.method.retain.StoreCasesMethod.storeCase(_caseBase,
		// casesToRetain.iterator().next());

	}

	public boolean equal(CBRCase cbrcase, CBRCaseBase caseBase) {
		CBRQuery query = new CBRQuery();

		// Obtain configuration for KNN

		simConfigDB2.setDescriptionSimFunction(new Average());
		query.setDescription(cbrcase.getDescription());

		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(caseBase.getCases(), query, simConfigDB2);
		// Select k cases
		for (RetrievalResult rr : eval) {
			if (rr.getEval() >= 1.0) {
				if (((BAMSolution) rr.get_case().getSolution()).BAMNovo == ((BAMSolution) cbrcase.getSolution()).BAMNovo
						&& ((BAMSolution) rr.get_case().getSolution()).aceita == ((BAMSolution) cbrcase
								.getSolution()).aceita) {
					return true;
				}
			}

		}

		return false;
	}

	public void postCycle() throws ExecutionException {
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		System.out.println("===================DB==================");
		for (CBRCase c : cases)
			System.out.println(c);
		_connector.close();
		System.out.println("===================DB2==================");
		cases = _caseBaseDB2.getCases();
		for (CBRCase c : cases)
			System.out.println(c);
		_connectorDB2.close();

		HSQLDBserver.shutDown();
	}

	public String getStringCases() throws ExecutionException {
		String aux = "===================DB==================\r\n";
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for (CBRCase c : cases)
			aux += c + "\r\n";
		aux += "===================DB2==================\r\n";
		cases = _caseBaseDB2.getCases();
		for (CBRCase c : cases)
			aux += c + "\r\n";
		return aux;
	}

	private NNConfig getSimilarityConfigDB2() {
		NNConfig config = new NNConfig();
		Attribute attribute;

		LocalSimilarityFunction function;

		attribute = new Attribute("BAMAtual", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		/*
		 * attribute = new Attribute("problema",BAMDescription.class);
		 * config.addMapping(attribute, new Equal());
		 * config.setWeight(attribute, 10.0);
		 */

		attribute = new Attribute("utilizacaoDoEnlaceCT0", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);
		attribute = new Attribute("utilizacaoDoEnlaceCT1", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);
		attribute = new Attribute("utilizacaoDoEnlaceCT2", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		attribute = new Attribute("numeroDePreempcoesCT0", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		attribute = new Attribute("numeroDePreempcoesCT1", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		/*
		 * nunca existe esse valor attribute = new
		 * Attribute("numeroDePreempcoesCT2",BAMDescription.class);
		 * config.addMapping(attribute, new Interval(100));
		 * config.setWeight(attribute, 0.0);
		 */

		attribute = new Attribute("numeroDeBloqueiosCT0", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		attribute = new Attribute("numeroDeBloqueiosCT1", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		attribute = new Attribute("numeroDeBloqueiosCT2", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		/*
		 * nunca existe esse valor attribute = new
		 * Attribute("numeroDeDevolucoesCT0",BAMDescription.class);
		 * config.addMapping(attribute, new Interval(100));
		 * config.setWeight(attribute, 0.0);
		 */

		attribute = new Attribute("numeroDeDevolucoesCT1", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		attribute = new Attribute("numeroDeDevolucoesCT2", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		attribute = new Attribute("numeroDeDevolucoesCT2", BAMDescription.class);
		config.addMapping(attribute, new Equal());
		config.setWeight(attribute, 10.0);

		return config;
	}

}
