/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package BAM.BAMRecommender;
import java.util.Collection;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
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


/**
 * Implementes the recommender main class
 * 
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class BAMRecommenderNoGUI implements StandardCBRApplication {

	boolean dBug = ParametrosDSTE.ligarDBug;
	
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
	Connector _connectorBP;
	/** CaseBase object */
	CBRCaseBase basePositiva;

	/** Connector object */
	Connector _connectorBN;
	/** CaseBase object */
	CBRCaseBase baseNegativa;

	/**
	 * @return the _caseBase
	 */
	public CBRCaseBase getCaseBase() {
		return basePositiva;
	}

	public CBRCaseBase getCaseBaseDB2() {
		return baseNegativa;
	}

	public void configure() throws ExecutionException {
		try {
			// Emulate data base server
			BAM.BAMRecommender.HSQLDBserver.init();

			// Create a data base connector
			_connectorBP = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connectorBP.initFromXMLfile(jcolibri.util.FileIO.findFile("BAM/BAMRecommender/databaseconfig.xml"));
			// Create a Lineal case base for in-memory organization
			basePositiva = new LinealCaseBase();

			// Create a data base connector
			_connectorBN = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connectorBN.initFromXMLfile(jcolibri.util.FileIO.findFile("BAM/BAMRecommender/databaseconfig2.xml"));
			// Create a Lineal case base for in-memory organization
			baseNegativa = new LinealCaseBase();

		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		// Load cases from connector into the case base
		basePositiva.init(_connectorBP);
		System.out.println("===================DB==================");
		// Print the cases
		java.util.Collection<CBRCase> cases = basePositiva.getCases();
		for (CBRCase c : cases)
			System.out.println(((BAMDescription) c.getDescription()).toTabela() + ((BAMSolution) c.getSolution()).getBAMNovo());
		System.out.println("===================DB2==================");
		// Load cases from connector into the case base
		baseNegativa.init(_connectorBN);
		// Print the cases
		cases = baseNegativa.getCases();
		for (CBRCase c : cases)
			System.out.println(((BAMDescription) c.getDescription()).toTabela() + ((BAMSolution) c.getSolution()).getBAMNovo());
		return basePositiva;
	}

	public CBRCase cycle(CBRQuery query) throws ExecutionException {

		// Obtain configuration for KNN
		boolean encontrouUmCaso = false;
		
		simConfig.setDescriptionSimFunction(new Average());

		// Execute NN
		Collection<RetrievalResult> eval1 = NNScoringMethod.evaluateSimilarity(basePositiva.getCases(), query, simConfig);
		Collection<RetrievalResult> eval2 = NNScoringMethod.evaluateSimilarity(baseNegativa.getCases(), query, simConfig);

		if (dBug) {BancoDeDados.setXML("\tBuscando caso similar ao caso: ID " + ((BAMDescription) query.getDescription()).toTabela() );}
		
		
			if(!eval1.isEmpty()) {
				encontrouUmCaso = true;
				if (dBug) {BancoDeDados.setXML("\tImprimindo os tops 4 Casos encontrado na Base Positiva:");}
				Collection<RetrievalResult> selectedcases1 = SelectCases.selectTopKRR(eval1, 4);
				for (RetrievalResult rr : selectedcases1) {
					if (dBug) {BancoDeDados.setXML("\tID: " + ((BAMDescription) rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).toTabela() +"\t"+ rr.getEval());}
				}	
			}else {
				if (dBug) {BancoDeDados.setXML("\tNenhum caso encontrado na base de casos Positiva");}
			}
				
			if(!eval2.isEmpty()) {
			
				Collection<RetrievalResult> selectedcases2 = SelectCases.selectTopKRR(eval2, 4);
				if (dBug) {BancoDeDados.setXML("\tImprimindo tops 4 base Negativa:");}
				for (RetrievalResult rr : selectedcases2) {
					if (dBug) {BancoDeDados.setXML("\tID: " + ((BAMDescription) rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).toTabela() +"\t"+ rr.getEval());}
				}
			}else {
				if (dBug) {BancoDeDados.setXML("\tNenhum caso encontrado na base de casos Negativa");}
			}	
		
		
		
		/////////////////////////////

		
		if(encontrouUmCaso) {
		

			BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
			BAMSolution sol = null;
			CBRCase novocase = new CBRCase();
			novocase.setDescription(desc);
			
			/////Verifica dse os casos encontrados estão acima da linha de corte
			/////Verifica se os casos encontrados estão na base de casos negativa ou seja, se não foram negativados
			///// Retorna o caso mais similar que coresponda as pespectativas
			if(dBug) {BancoDeDados.setXML("\tVerificando casos acima da linha de corte: "+ ParametrosDSTE.RecomendacaoCBRLimiarDeCorte + ", que não foram negativados;;;");}
			for (RetrievalResult rr : eval1) {
				
				if (rr.getEval() >= ParametrosDSTE.RecomendacaoCBRLimiarDeCorte) { /////deveria ter e a solução é true
					sol = ((BAMSolution) rr.get_case().getSolution()).clone();
					novocase.setSolution(sol);
					
					// isso é necessário para corigir casos que vão gradativamente se aproxiamndo de outro caso negativo
					if (         (!this.equal(novocase, baseNegativa, ParametrosDSTE.RecomendacaoCBRLimiarDeCorteNEGATIVAR))   &&  ((BAMSolution)rr.get_case().getSolution()).getAceita()      ){
						
						//if(dBug) {BancoDeDados.setXML("\tO caso a seguir foi qualificado... ->  ID:"+ ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).getBAMNovo());}
						if(true) {BancoDeDados.setXML("\tO caso: " + ((BAMDescription)rr.get_case().getDescription()).getCaseId() + " foi qualificado; Retornado um novo caso; Case_ID: "+ ((BAMDescription)novocase.getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).toTabela());}

						return novocase;
						//BancoDeDados.setXML("\tCaso Aprovado:"+ ((BAMDescription)rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).getBAMNovo());
						//return rr.get_case();
					}else {
						if(true) {BancoDeDados.setXML("\t**O CASO: " + ((BAMDescription)rr.get_case().getDescription()).getCaseId() + " FOI NEGATIVADO; Procurando proximo;;;  "+ ((BAMDescription)rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).toTabela());}
						
						//// Se o caso foi negativado e o aceita estiver true, ele precisa ser colocado na base negaativa e o aceiteira modificado para false
						if( ((BAMSolution) rr.get_case().getSolution()  ).getAceita()   ) {
								CBRCase caseTMP = new CBRCase();
								caseTMP.setDescription(((BAMDescription) rr.get_case().getDescription()).clone());
								caseTMP.setSolution(((BAMSolution) rr.get_case().getSolution()).clone());
								((BAMDescription)caseTMP.getDescription()).setCaseId("BAD_"+(this.getCaseBaseDB2().getCases().size()+1)+"_" + ((BAMSolution) rr.get_case().getSolution()  ).getId());
																
								jcolibri.method.retain.StoreCasesMethod.storeCase( this.getCaseBaseDB2(), caseTMP);
								((BAMSolution) rr.get_case().getSolution()).setAceita(false);
								
						}
					}
					
					
				}else {
					if(dBug) {BancoDeDados.setXML("\tNenhum caso acima da linha de corte foi aceito.");}
					return null;
				}
	
			}
			return null;
		}else {
			if(dBug) {BancoDeDados.setXML("\tFim DA BUSCA - Nenhum caso Encontrado.");}
			return null;
		}
	}
	
	
	public int[] sugerirRecomendacao(CBRQuery query) throws ExecutionException {
		
		simConfig.setDescriptionSimFunction(new Average());
		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(baseNegativa.getCases(), query, simConfig);
		
		int []bams = {0,0,0};
		
		if(dBug) {BancoDeDados.setXML("\tFunc_sugestion retornadno caso Sugerido ID: " + ((BAMDescription) query.getDescription()).toTabela() );}
				
		if(!eval.isEmpty()) {
			if(dBug) {BancoDeDados.setXML("\t#998# Func_sugestion - Imprimindo os 3 casos mais similares na base de casos NEGATIVA...");}
			Collection<RetrievalResult> selectedcases = SelectCases.selectTopKRR(eval, 3);
			for (RetrievalResult rr : selectedcases) {
				if(dBug) {BancoDeDados.setXML("\tID: " + ((BAMDescription) rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).toTabela() +"\t"+ rr.getEval());}
			}
		
		
		
			BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
			BAMSolution sol = null;
			CBRCase novocase = new CBRCase();
			novocase.setDescription(desc);
			
			
			
			
			for (RetrievalResult rr : eval) {
	
				if (rr.getEval() >= ParametrosDSTE.RecomendacaoCBRLimiarDeCorte2) {
					if(dBug) {BancoDeDados.setXML("\t#997# Func_sugestion  - Um caso acima da linha de corte da base negativa...");}
					sol = ((BAMSolution) rr.get_case().getSolution());
					if (   sol.getBAMNovo().toString() == BAMTypes.NoPreemptionMAM.toString()              )
					{
						bams[0]++;
					}else if (   sol.getBAMNovo().toString() == BAMTypes.PreemptionRDM.toString()              )
					{
						bams[1]++;
					}else if (   sol.getBAMNovo().toString() == BAMTypes.PreemptionAllocCTSharing.toString()              )
					{
						bams[2]++;
					}
					
				} else {
					if(dBug) {BancoDeDados.setXML("\t#996# Func_sugestion  - Nenhum caso encontrado na base negativa...");}
					return bams;
				}
			}
		
		}
		
		return bams;
	}

	
	/*public boolean equal(CBRCase cbrcase, CBRCaseBase caseBase) {
		CBRQuery query = new CBRQuery();

		// Obtain configuration for KNN
		BancoDeDados.setXML("\tFunction equal; Verificando se o caso já exite na base" + caseBase.toString());
		
		simConfig.setDescriptionSimFunction(new Average());
		query.setDescription(cbrcase.getDescription());

		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(caseBase.getCases(), query, simConfig);
		
		
		for (RetrievalResult rr : eval) {
			if (rr.getEval() >= ParametrosDSTE.RecomendacaoCBRLimiarDeCorteNEGATIVAR) {
				if (((BAMSolution) rr.get_case().getSolution()).BAMNovo == ((BAMSolution) cbrcase.getSolution()).BAMNovo
						&& ((BAMSolution) rr.get_case().getSolution()).aceita == ((BAMSolution) cbrcase
								.getSolution()).aceita) {
					if(dBug) {
						BancoDeDados.setXML("\tO novo caso: Case_ID: " + ((BAMDescription) cbrcase.getDescription()).toTabela() + ((BAMSolution)cbrcase.getSolution()).getBAMNovo() +"\t Similaridade entre casos abaixo:");
						BancoDeDados.setXML("\tÉ similar ao caso: Case_ID:  " + ((BAMDescription) rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).getBAMNovo() +"\t"+ rr.getEval());}
					return true;
				}else if(dBug) {
					BancoDeDados.setXML("\tO caso NÃO é similar a nunhum caso existente: Case_ID: " + ((BAMDescription) cbrcase.getDescription()).toTabela() + ((BAMSolution)cbrcase.getSolution()).getBAMNovo());
					}
			}

		}

		return false;
	}*/
	
	public boolean equal(CBRCase cbrcase, CBRCaseBase caseBase, double limiar) {
		CBRQuery query = new CBRQuery();
		
		// Obtain configuration for KNN
		if(dBug) {BancoDeDados.setXML("\tFunction equal; Verificando se o caso já exite na base" + caseBase.toString());}
		
		
		simConfig.setDescriptionSimFunction(new Average());
		query.setDescription(cbrcase.getDescription());

		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(caseBase.getCases(), query, simConfig);
		// Select k cases
		for (RetrievalResult rr : eval) {
			if (rr.getEval() >= limiar) {
				if (   ((BAMSolution) rr.get_case().getSolution()).BAMNovo == ((BAMSolution) cbrcase.getSolution()).BAMNovo  && ((BAMSolution) rr.get_case().getSolution()).aceita == ((BAMSolution) cbrcase.getSolution()).aceita) {
					if(dBug) {
						BancoDeDados.setXML("\tO novo caso: Case_ID: " + ((BAMDescription) cbrcase.getDescription()).toTabela() + ((BAMSolution)cbrcase.getSolution()).getBAMNovo() +"\t Similaridade entre casos abaixo:");
						BancoDeDados.setXML("\tÉ similar ao caso: Case_ID:  " + ((BAMDescription) rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).getBAMNovo() +"\t"+ rr.getEval());}
					return true;
				}else if(dBug) {
					BancoDeDados.setXML("\tO caso NÃO é IGUAL a nunhum caso existente: Case_ID: " + ((BAMDescription) cbrcase.getDescription()).toTabela() + ((BAMSolution)cbrcase.getSolution()).getBAMNovo());
					}
			}

		}
		if(dBug) {
			BancoDeDados.setXML("\tNenhum caso dentro da linha de corte encontrado para comparar");
			if(!eval.isEmpty()) {
				BancoDeDados.setXML("\tImprimindo os tops 3 Casos encontrado na Base:");
				Collection<RetrievalResult> selectedcases1 = SelectCases.selectTopKRR(eval, 3);
				for (RetrievalResult rr : selectedcases1) {
					BancoDeDados.setXML("\tID: " + ((BAMDescription) rr.get_case().getDescription()).toTabela() + ((BAMSolution)rr.get_case().getSolution()).toTabela() +"\t"+ rr.getEval());
				}	
			}else {
				BancoDeDados.setXML("\tBase vazia ainda");
			}
		}
		return false;
	}
	
	//BancoDeDados.setXML("Sim \t" + ((BAMDescription) rr.get_case().getDescription()).toTabela() +"\t"+ ((BAMSolution)rr.get_case().getSolution()).getBAMNovo() +"\t"+ rr.getEval());

	public void postCycle() throws ExecutionException {
		java.util.Collection<CBRCase> cases = basePositiva.getCases();
		System.out.println("===================DB==================");
		for (CBRCase c : cases)
			System.out.println( ((BAMDescription) c.getDescription()).toTabela() + ((BAMSolution) c.getSolution()).toTabela()  );
		_connectorBP.close();
		System.out.println("===================DB2==================");
		cases = baseNegativa.getCases();
		for (CBRCase c : cases)
			System.out.println( ((BAMDescription) c.getDescription()).toTabela() + ((BAMSolution) c.getSolution()).toTabela()  );
		_connectorBN.close();

		HSQLDBserver.shutDown();
	}

	public String getStringCases() throws ExecutionException {
		String aux = "===================DB==================\r\n";
		java.util.Collection<CBRCase> cases = basePositiva.getCases();
		for (CBRCase c : cases)
			aux += ((BAMDescription) c.getDescription()).toTabela() + ((BAMSolution) c.getSolution()).toTabela() + "\r\n";
		aux += "===================DB2==================\r\n";
		cases = baseNegativa.getCases();
		for (CBRCase c : cases)
			aux += ((BAMDescription) c.getDescription()).toTabela() + ((BAMSolution) c.getSolution()).toTabela() + "\r\n";
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
