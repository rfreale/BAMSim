/** Segunda
/// * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package Main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import jcolibri.cbrcore.Attribute;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMRecommender;
import DSTE.ParametrosDSTE;
import Simulador.ParametrosDoSimulador;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumCyclicDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.util.FileIO;

/**
 * Similarity Dialog
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class BAMSimConfiguration extends JDialog {



	JLabel image;	
	
	
		
	SimilConfigPanel	BAMAtual;
	//SimilConfigPanel	problema;
	
	SpinnerNumberModel	SLABloqueios;
	SpinnerNumberModel	SLAPreempcoes;
	SpinnerNumberModel	SLADevolucoes;
		
	SimilConfigPanel	utilizacaoDoEnlace;
	SimilConfigPanel	numeroDeBloqueios;
	SimilConfigPanel	numeroDePreempcoes;
	SimilConfigPanel	numeroDeDevolucoes;
		
	SpinnerNumberModel semente;
	SpinnerNumberModel rodadas;
	SpinnerNumberModel janela;
	SpinnerNumberModel tempoSimulacao;
	
	SpinnerNumberModel recomendacaoCBRLimiarDeCorte;
	
	JCheckBox cleanDatabase;
	
	SpinnerNumberModel difLSP;
	
	
	public BAMSimConfiguration(JFrame main)
	{
		super(main,true);
		configureFrame();
	}
	
	private void configureFrame()
	{
		try
		{
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1)
		{
		}
		
		this.setTitle("BAMSim - General Configuration");

		/*
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step2.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);*/
		
		
		
		/********************Estudar  aqui  *****************************/
		/*
		 *****************************************************************
		 *****************************************************************
		 *****************************************************************
		 *****Ver quais as funções que se adaptam melhor ao desejado******
		 *Class Summary
		 *	EnumCyclicDistance 		= This function computes the similarity between two enum values as their cyclic distance.
		 *	EnumDistance 			= This function returns the similarity of two enum values as the their distance sim(x,y)=|ord(x) - ord(y)|
		 *	Equal 					= This function returns 1 if both individuals are equal, otherwise returns 0
		 *	EqualsStringIgnoreCase	= This function returns 1 if both String are the same despite case letters, 0 in the other case
		 *	Interval				= This function returns the similarity of two number inside an interval.
		 *	MaxString				= This function returns a similarity value depending of the biggest substring that belong to both strings.
		 *	Table					= Similarity function that uses a table to obtain the similarity between two values.
		 *	Threshold				= This function returns 1 if the difference between two numbers is less than a threshold, 0 in the other case.
		 *****************************************************************
		 *****************************************************************
		 *****************************************************************
		 *****************************************************************
		 * 
		 */
		
		Vector<String> stringfunctions = new Vector<String>();
		stringfunctions.add("Equal");  

		
		Vector<String> numberfunctions = new Vector<String>();
		numberfunctions.add("Interval");  
		numberfunctions.add("Threshold");
		numberfunctions.add("Equal");
		
		Vector<String> numberfunctions2 = new Vector<String>();
		numberfunctions2.add("Threshold");
		numberfunctions2.add("Interval");  
		
		
		Vector<String> enumfunctions = new Vector<String>();
		enumfunctions.add("Equal");
		enumfunctions.add("EnumCyclicDistance");
		enumfunctions.add("EnumDistance");
		
		Vector<String> ontofunctions = new Vector<String>();
		ontofunctions.add("OntCosine");
		ontofunctions.add("OntDeep");
		ontofunctions.add("OntDeepBasic");
		ontofunctions.add("OntDetail");
		ontofunctions.add("Equal");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(20,2));
		
		panel.setPreferredSize(new Dimension(600, 550));
		//panel.setLayout(new SpringLayout());
		
		
		/*panel.add(new JLabel("Problema"));
		panel.add(problema = new SimilConfigPanel(stringfunctions));   
		problema.weight.setValue(1);*/
		JLabel label;		
		panel.add(label = new JLabel("Simulation Configuration", SwingConstants.RIGHT));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(new JLabel());
		
		panel.add(new JLabel("Simulation Seed"));
		panel.add(new JSpinner(semente = new SpinnerNumberModel(7417,1,65000,100)));

		panel.add(new JLabel("Simulation Rounds"));
		panel.add(new JSpinner(rodadas = new SpinnerNumberModel(1,1,65000,1)));
		
		panel.add(new JLabel("Duration of the Simulation (hour)"));
		panel.add(new JSpinner(tempoSimulacao = new SpinnerNumberModel(24,1,65000,1)));
		
		panel.add(new JLabel("Evaluation Window (minutes)"));
		panel.add(new JSpinner(janela = new SpinnerNumberModel(5,1,65000,1)));
		
		panel.add(new JLabel());panel.add(new JLabel());


		panel.add(label = new JLabel("SLA Configuration", SwingConstants.RIGHT));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(new JLabel());
		
		/*panel.add(new JLabel("% SLA Blocking"));
		panel.add(new JSpinner(SLABloqueios = new SpinnerNumberModel(0,0,100,1)));
		//panel.add(SLABloqueios = new SimilConfigPanel(numberfunctions));   
		//SLABloqueios.weight.setValue(0);*/
		
				
		panel.add(new JLabel("% SLA Preemption"));
		panel.add(new JSpinner(SLAPreempcoes = new SpinnerNumberModel(0,0,100,1)));
		//panel.add(SLAPreempcoes = new SimilConfigPanel(numberfunctions));   
		//SLAPreempcoes.weight.setValue(0);
		
	
		panel.add(new JLabel("% SLA Devolution"));
		panel.add(new JSpinner(SLADevolucoes = new SpinnerNumberModel(0,0,100,1)));
		//panel.add(SLADevolucoes = new SimilConfigPanel(numberfunctions));   
		//SLADevolucoes.weight.setValue(1);
		
		panel.add(new JLabel());panel.add(new JLabel());

		panel.add(label = new JLabel("CBR Configuration", SwingConstants.RIGHT));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(new JLabel());
		
		
		panel.add(label = new JLabel("Attribute"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		JPanel l = new JPanel();
		l.setLayout(new GridLayout(1,3));
		l.add(label = new JLabel("Function"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		l.add(label = new JLabel("Weight"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		l.add(label = new JLabel("Parameter"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(l);
		
		panel.add(new JLabel("Current BAM"));
		panel.add(BAMAtual = new SimilConfigPanel(stringfunctions));   
		BAMAtual.weight.setValue(40);
		
		panel.add(new JLabel("% Link Load"));
		panel.add(utilizacaoDoEnlace = new SimilConfigPanel(numberfunctions));   
		utilizacaoDoEnlace.weight.setValue(30);
		
		panel.add(new JLabel("% Blocking"));
		panel.add(numeroDeBloqueios = new SimilConfigPanel(numberfunctions));
		numeroDeBloqueios.weight.setValue(30);
		
		
		panel.add(new JLabel("% Preemption"));
		panel.add(numeroDePreempcoes = new SimilConfigPanel(numberfunctions));
		numeroDePreempcoes.weight.setValue(20);
		
		
		panel.add(new JLabel("% Devolution"));
		panel.add(numeroDeDevolucoes = new SimilConfigPanel(numberfunctions));
		numeroDeDevolucoes.weight.setValue(20);
		
		panel.add(new JLabel("% Threshold to Suggest Case"));
		panel.add(new JSpinner(recomendacaoCBRLimiarDeCorte = new SpinnerNumberModel(96,0,100,1)));
		
		panel.add(new JLabel("Traffic Profile Difference (LSPs)"));
		panel.add(new JSpinner(difLSP = new SpinnerNumberModel(30,0,100,1)));
		
		panel.add(new JLabel("Clean Database"));
		panel.add(cleanDatabase=new JCheckBox());
		cleanDatabase.setSelected(true);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(panel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Set Configuration >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setConfiguration();
			}
		});
		buttons.add(ok,BorderLayout.CENTER);
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttons.add(exit,BorderLayout.WEST);
		
		panelAux.add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(panelAux, BorderLayout.CENTER);
		
		/**********************************************************/
		
		
		this.pack();
		
		//this.setSize(this.getWidth(), this.getHeight());
		//this.setResizable(false);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - this.getWidth()) / 2,
			(screenSize.height - this.getHeight()) / 2, 
			getWidth(),
			getHeight());
	}
	
	void setConfiguration()
	{
		ParametrosDoSimulador.semente=semente.getNumber().intValue();
		ParametrosDoSimulador.MAX_SIMULATIONS= rodadas.getNumber().intValue();
		ParametrosDSTE.Janela= janela.getNumber().longValue()*60;
		ParametrosDSTE.TempoSimulacao= tempoSimulacao.getNumber().longValue()*3600;
		
		ParametrosDSTE.SLAPreempcoes=SLAPreempcoes.getNumber().doubleValue();
		ParametrosDSTE.SLADevolucoes=SLADevolucoes.getNumber().doubleValue();
		
		ParametrosDSTE.similarityConfig = getSimilarityConfig();
		ParametrosDSTE.RecomendacaoCBRLimiarDeCorte =recomendacaoCBRLimiarDeCorte.getNumber().doubleValue()/100;
		ParametrosDSTE.DifLSP= difLSP.getNumber().intValue();
		ParametrosDSTE.limpaBaseNoInicioCBR=cleanDatabase.isSelected();
		
		ParametrosDSTE.RRDLinhas= (int) (ParametrosDSTE.TempoSimulacao/(ParametrosDSTE.RRDSteps*ParametrosDSTE.RRDBatida));
		

		this.dispose();
	}
	
	public NNConfig getSimilarityConfig()
	{
		NNConfig config = new NNConfig();
		Attribute attribute;
		SimilConfigPanel similConfig;
		LocalSimilarityFunction function;
		
		similConfig = this.BAMAtual;
		attribute = new Attribute("BAMAtual",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = this.utilizacaoDoEnlace;
		attribute = new Attribute("utilizacaoDoEnlace",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.numeroDeBloqueios;
		attribute = new Attribute("numeroDeBloqueios",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.numeroDePreempcoes;
		attribute = new Attribute("numeroDePreempcoesCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = this.numeroDeDevolucoes;
		attribute = new Attribute("numeroDeDevolucoesCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		
		return config;
	}
	
	public int getK()
	{
		return semente.getNumber().intValue();
	}
	
	private LocalSimilarityFunction localSimilFactory(String name, int param)
	{
		if(name.equals("Equal"))
			return new Equal();
		else if(name.equals("Interval"))
			return new Interval(param);
		else if(name.equals("Threshold"))
			return new Threshold(param);
		else if(name.equals("EnumCyclicDistance"))
			return new EnumCyclicDistance();
		else if(name.equals("EnumDistance"))
			return new EnumDistance();
		else if(name.equals("OntCosine"))
			return new OntCosine();
		else if(name.equals("OntDeep"))
			return new OntDeep();
		else if(name.equals("OntDeepBasic"))
			return new OntDeepBasic();
		else if(name.equals("OntDetail"))
			return new OntDetail();
		else
		{
			org.apache.commons.logging	.LogFactory.getLog(this.getClass()).error("Simil Function not found");
			return null;
		}
	}
	
	private class SimilConfigPanel extends JPanel
	{
		
		Vector<String> functions;
		JComboBox functionCombo;
		SpinnerNumberModel param;
		JSpinner paramSpinner;
		JSlider weight;
		
		public SimilConfigPanel(Vector<String> functions)
		{
			this.functions = new Vector<String>(functions);
			
			this.setLayout(new GridLayout(1,4));
			//this.setLayout(new FlowLayout(FlowLayout.CENTER));
			///int largura = 5;
			//int altura = 32;
			//this.setPreferredSize( new Dimension(largura,altura) );

			
			
			functionCombo = new JComboBox(functions);
			
			this.add(functionCombo);
			
			weight = new JSlider(0,100,10);
			weight.setPaintLabels(false);
			
			this.add(weight);
			
			paramSpinner = new JSpinner(param = new SpinnerNumberModel(100,0,100,1));
			
			this.add(paramSpinner);
			
			functionCombo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					updateParam();
				}
				
			});
			updateParam();
		}
		
		void updateParam()
		{
			int sel = functionCombo.getSelectedIndex();
			if(sel == -1)
			{
				paramSpinner.setVisible(false);
				return;
			}
			String f = functions.elementAt(sel);
			paramSpinner.setVisible(f.endsWith("Interval") || f.endsWith("Threshold"));
			paramSpinner.setEnabled(f.endsWith("Threshold"));
		}
		
		
		public double getWeight()
		{
			return ((double)weight.getValue());
		}
		
		public int getParam()
		{
			return param.getNumber().intValue()/100;
		}
		
		public String getSimilFuntion()
		{
			return (String)functionCombo.getSelectedItem();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BAMSimConfiguration qf = new BAMSimConfiguration(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

}
