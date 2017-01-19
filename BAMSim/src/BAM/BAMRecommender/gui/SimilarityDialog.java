/** Segunda
/// * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package BAM.BAMRecommender.gui;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.Attribute;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMRecommender;
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
public class SimilarityDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;	
	
	
		
	SimilConfigPanel	BAMAtual;
	//SimilConfigPanel	problema;
	
	SimilConfigPanel	SLAUtilizacaoCT0;
	SimilConfigPanel	SLAUtilizacaoCT1;
	SimilConfigPanel	SLAUtilizacaoCT2;
	
	SimilConfigPanel	SLABloqueiosCT0;
	SimilConfigPanel	SLABloqueiosCT1;
	SimilConfigPanel	SLABloqueiosCT2;
		
	SimilConfigPanel	SLAPreempcoesCT0;
	SimilConfigPanel	SLAPreempcoesCT1;
	//SimilConfigPanel	SLAPreempcoesCT2;
		
	//SimilConfigPanel	SLADevolucoesCT0;
	SimilConfigPanel	SLADevolucoesCT1;
	SimilConfigPanel	SLADevolucoesCT2;
		
	SimilConfigPanel	BC0;
	SimilConfigPanel	BC1;
	SimilConfigPanel	BC2;
		
	SimilConfigPanel	utilizacaoDoEnlaceCT0;
	SimilConfigPanel	utilizacaoDoEnlaceCT1;
	SimilConfigPanel	utilizacaoDoEnlaceCT2;
		
	SimilConfigPanel	numeroDeBloqueiosCT0;
	SimilConfigPanel	numeroDeBloqueiosCT1;
	SimilConfigPanel	numeroDeBloqueiosCT2;
		
	SimilConfigPanel	numeroDePreempcoesCT0;
	SimilConfigPanel	numeroDePreempcoesCT1;
	//SimilConfigPanel	numeroDePreempcoesCT2;
		
	//SimilConfigPanel	numeroDeDevolucoesCT0;
	SimilConfigPanel	numeroDeDevolucoesCT1;
	SimilConfigPanel	numeroDeDevolucoesCT2;
		
	SpinnerNumberModel k;
	
	
	
	
	public SimilarityDialog(JFrame main)
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
		
		this.setTitle("Silimarity = Configurando a Similaridade");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step2.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		
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
		panel.setLayout(new GridLayout(15,2));
		
		panel.setPreferredSize(new Dimension(1200, 550));
		//panel.setLayout(new SpringLayout());
		
		JLabel label;
		panel.add(label = new JLabel("Atributo"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		JPanel l = new JPanel();
		l.setLayout(new GridLayout(1,3));
		l.add(label = new JLabel("Função"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		l.add(label = new JLabel("Peso"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		l.add(label = new JLabel("Parâmetro da função."));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(l);
		
		JLabel label2;
		panel.add(label2 = new JLabel("Atributo"));
		label2.setFont(label2.getFont().deriveFont(Font.BOLD));
		JPanel l2 = new JPanel();
		l2.setLayout(new GridLayout(1,3));
		l2.add(label2 = new JLabel("Função"));
		label2.setFont(label2.getFont().deriveFont(Font.BOLD));
		l2.add(label2 = new JLabel("Peso"));
		label2.setFont(label2.getFont().deriveFont(Font.BOLD));
		l2.add(label2 = new JLabel("Parâmetro da função."));
		label2.setFont(label2.getFont().deriveFont(Font.BOLD));
		panel.add(l2);
		
		
		
		panel.add(new JLabel("BAM Atual"));
		panel.add(BAMAtual = new SimilConfigPanel(stringfunctions));   
		BAMAtual.weight.setValue(1);
		
		/*panel.add(new JLabel("Problema"));
		panel.add(problema = new SimilConfigPanel(stringfunctions));   
		problema.weight.setValue(1);*/
		
		
		panel.add(new JLabel("SLA Utilização CTO"));
		panel.add(SLAUtilizacaoCT0 = new SimilConfigPanel(numberfunctions));   
		SLAUtilizacaoCT0.weight.setValue(1);
		
		panel.add(new JLabel("SLA Utilização CT1"));
		panel.add(SLAUtilizacaoCT1 = new SimilConfigPanel(numberfunctions));   
		SLAUtilizacaoCT1.weight.setValue(1);
		
		panel.add(new JLabel("SLA Utilização CT2"));
		panel.add(SLAUtilizacaoCT2 = new SimilConfigPanel(numberfunctions));   
		SLAUtilizacaoCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("SLA Bloqueio CTO"));
		panel.add(SLABloqueiosCT0 = new SimilConfigPanel(numberfunctions));   
		SLABloqueiosCT0.weight.setValue(1);
		
		panel.add(new JLabel("SLA Bloqueio CT1"));
		panel.add(SLABloqueiosCT1 = new SimilConfigPanel(numberfunctions));   
		SLABloqueiosCT1.weight.setValue(1);
		
		panel.add(new JLabel("SLA Bloqueio CT2"));
		panel.add(SLABloqueiosCT2 = new SimilConfigPanel(numberfunctions));   
		SLABloqueiosCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("SLA Preempção CTO"));
		panel.add(SLAPreempcoesCT0 = new SimilConfigPanel(numberfunctions));   
		SLAPreempcoesCT0.weight.setValue(1);
		
		panel.add(new JLabel("SLA Preempção CT1"));
		panel.add(SLAPreempcoesCT1 = new SimilConfigPanel(numberfunctions));   
		SLAPreempcoesCT1.weight.setValue(1);
		
		/*panel.add(new JLabel("SLA Preempção CT2"));
		panel.add(SLAPreempcoesCT2 = new SimilConfigPanel(numberfunctions));   
		SLAPreempcoesCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("SLA Devolução CTO"));
		panel.add(SLADevolucoesCT0 = new SimilConfigPanel(numberfunctions));   
		SLADevolucoesCT0.weight.setValue(1);*/
		
		panel.add(new JLabel("SLA Devolução CT1"));
		panel.add(SLADevolucoesCT1 = new SimilConfigPanel(numberfunctions));   
		SLADevolucoesCT1.weight.setValue(1);
		
		panel.add(new JLabel("SLA Devolução CT2"));
		panel.add(SLADevolucoesCT2 = new SimilConfigPanel(numberfunctions));   
		SLADevolucoesCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("Largura de Banda em BC0"));
		panel.add(BC0 = new SimilConfigPanel(numberfunctions2));   
		BC0.weight.setValue(1);
		BC0.param.setValue(128);
		
		panel.add(new JLabel("Largura de Banda em BC1"));
		panel.add(BC1 = new SimilConfigPanel(numberfunctions2));   
		BC1.weight.setValue(1);
		BC1.param.setValue(128);
		
		panel.add(new JLabel("Largura de Banda em BC2"));
		panel.add(BC2 = new SimilConfigPanel(numberfunctions2));   
		BC2.weight.setValue(1);
		BC2.param.setValue(128);
		
		
		panel.add(new JLabel("Utilização do Enlace CT0"));
		panel.add(utilizacaoDoEnlaceCT0 = new SimilConfigPanel(numberfunctions));   
		utilizacaoDoEnlaceCT0.weight.setValue(1);
		
		panel.add(new JLabel("Utilização do Enlace CT1"));
		panel.add(utilizacaoDoEnlaceCT1 = new SimilConfigPanel(numberfunctions));
		utilizacaoDoEnlaceCT1.weight.setValue(1);
		
		panel.add(new JLabel("Utilização do Enlace CT2"));
		panel.add(utilizacaoDoEnlaceCT2 = new SimilConfigPanel(numberfunctions));
		utilizacaoDoEnlaceCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		panel.add(numeroDeBloqueiosCT0 = new SimilConfigPanel(numberfunctions));
		numeroDeBloqueiosCT0.weight.setValue(1);
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		panel.add(numeroDeBloqueiosCT1 = new SimilConfigPanel(numberfunctions));
		numeroDeBloqueiosCT1.weight.setValue(1);
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		panel.add(numeroDeBloqueiosCT2 = new SimilConfigPanel(numberfunctions));
		numeroDeBloqueiosCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		panel.add(numeroDePreempcoesCT0 = new SimilConfigPanel(numberfunctions));
		numeroDePreempcoesCT0.weight.setValue(1);
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		panel.add(numeroDePreempcoesCT1 = new SimilConfigPanel(numberfunctions));
		numeroDePreempcoesCT1.weight.setValue(1);
		
		/*panel.add(new JLabel("Number de Preempções em CT2"));
		panel.add(numeroDePreempcoesCT2 = new SimilConfigPanel(numberfunctions));
		numeroDePreempcoesCT2.weight.setValue(1);
		
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		panel.add(numeroDeDevolucoesCT0 = new SimilConfigPanel(numberfunctions));
		numeroDeDevolucoesCT0.weight.setValue(1);*/
		
		panel.add(new JLabel("Number de Devoluções em CT1"));
		panel.add(numeroDeDevolucoesCT1 = new SimilConfigPanel(numberfunctions));
		numeroDeDevolucoesCT1.weight.setValue(1);
		
		panel.add(new JLabel("Number de Devoluções em CT2"));
		panel.add(numeroDeDevolucoesCT2 = new SimilConfigPanel(numberfunctions));
		numeroDeDevolucoesCT2.weight.setValue(1);
		
		panel.add(new JLabel());
		panel.add(new JLabel());

		panel.add(label = new JLabel("K"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(new JSpinner(k = new SpinnerNumberModel(3,1,100,1)));

//		Lay out the panel.
		Utils.makeCompactGrid(panel,
		                29,4, //rows, cols   ///   <===========add mais linhas
		                10, 10,        //initX, initY
		                5, 5);       //xPad, yPad
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(panel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Set Similarity Configuration >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setSimilarity();
			}
		});
		buttons.add(ok,BorderLayout.CENTER);
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					BAMRecommender.getInstance().postCycle();
				} catch (Exception ex) {
					org.apache.commons.logging.LogFactory.getLog(BAMRecommender.class).error(ex);
				}
				System.exit(-1);
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
	
	void setSimilarity()
	{
		this.setVisible(false);
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

		/*similConfig = this.problema;
		attribute = new Attribute("problema",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());*/
		
		
		
		similConfig = this.SLAUtilizacaoCT0;
		attribute = new Attribute("SLAUtilizacaoCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.SLAUtilizacaoCT1;
		attribute = new Attribute("SLAUtilizacaoCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.SLAUtilizacaoCT2;
		attribute = new Attribute("SLAUtilizacaoCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		
		similConfig = this.SLABloqueiosCT0;
		attribute = new Attribute("SLABloqueiosCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.SLABloqueiosCT1;
		attribute = new Attribute("SLABloqueiosCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.SLABloqueiosCT2;
		attribute = new Attribute("SLABloqueiosCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.SLAPreempcoesCT0;
		attribute = new Attribute("SLAPreempcoesCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.SLAPreempcoesCT1;
		attribute = new Attribute("SLAPreempcoesCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		/*similConfig = this.SLAPreempcoesCT2;
		attribute = new Attribute("SLAPreempcoesCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.SLADevolucoesCT0;
		attribute = new Attribute("SLADevolucoesCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());*/
		
		similConfig = this.SLADevolucoesCT1;
		attribute = new Attribute("SLADevolucoesCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.SLADevolucoesCT2;
		attribute = new Attribute("SLADevolucoesCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.BC0;
		attribute = new Attribute("BC0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam());
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.BC1;
		attribute = new Attribute("BC1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.BC2;
		attribute = new Attribute("BC2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
			
		similConfig = this.utilizacaoDoEnlaceCT0;
		attribute = new Attribute("utilizacaoDoEnlaceCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.utilizacaoDoEnlaceCT1;
		attribute = new Attribute("utilizacaoDoEnlaceCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.utilizacaoDoEnlaceCT2;
		attribute = new Attribute("utilizacaoDoEnlaceCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.numeroDeBloqueiosCT0;
		attribute = new Attribute("numeroDeBloqueiosCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.numeroDeBloqueiosCT1;
		attribute = new Attribute("numeroDeBloqueiosCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.numeroDeBloqueiosCT2;
		attribute = new Attribute("numeroDeBloqueiosCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.numeroDePreempcoesCT0;
		attribute = new Attribute("numeroDePreempcoesCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = this.numeroDePreempcoesCT1;
		attribute = new Attribute("numeroDePreempcoesCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		/*similConfig = this.numeroDePreempcoesCT2;
		attribute = new Attribute("numeroDePreempcoesCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		
		similConfig = this.numeroDeDevolucoesCT0;
		attribute = new Attribute("numeroDeDevolucoesCT0",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());*/
		
		similConfig = this.numeroDeDevolucoesCT1;
		attribute = new Attribute("numeroDeDevolucoesCT1",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = this.numeroDeDevolucoesCT2;
		attribute = new Attribute("numeroDeDevolucoesCT2",BAMDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion(), similConfig.getParam()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		
		return config;
	}
	
	public int getK()
	{
		return k.getNumber().intValue();
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
		private static final long serialVersionUID = 1L;
		
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
			
			paramSpinner = new JSpinner(param = new SpinnerNumberModel(100,0,200,1));
			
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
		}
		
		
		public double getWeight()
		{
			return ((double)weight.getValue()) /10;
		}
		
		public int getParam()
		{
			return param.getNumber().intValue();
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
		SimilarityDialog qf = new SimilarityDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

}
