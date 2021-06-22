/**Primeira
/// * Travel Recommender example for the jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 25/07/2006
 */
package BAM.BAMRecommender.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRQuery;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMTypes;
//import BAM.BAMRecommender.BAMDescription.Problemas;
import BAM.BAMRecommender.BAMRecommender;
import jcolibri.util.FileIO;

/**
 * Query dialgo
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class QueryDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	JTextField gestor;
	JComboBox BAMAtual;
	//JComboBox problema;
	
	SpinnerNumberModel  SLAUtilizacaoCT0;
	SpinnerNumberModel  SLAUtilizacaoCT1;
	SpinnerNumberModel  SLAUtilizacaoCT2;
	
	SpinnerNumberModel  SLABloqueiosCT0;
	SpinnerNumberModel  SLABloqueiosCT1;
	SpinnerNumberModel  SLABloqueiosCT2;
	
	SpinnerNumberModel  SLAPreempcoesCT0;
	SpinnerNumberModel  SLAPreempcoesCT1;
	//SpinnerNumberModel  SLAPreempcoesCT2;
	
	//SpinnerNumberModel  SLADevolucoesCT0;
	SpinnerNumberModel  SLADevolucoesCT1;
	SpinnerNumberModel  SLADevolucoesCT2;
	
	SpinnerNumberModel  BC0;
	SpinnerNumberModel  BC1;
	SpinnerNumberModel  BC2;
	
	SpinnerNumberModel  utilizacaoDoEnlaceCT0;
	SpinnerNumberModel  utilizacaoDoEnlaceCT1;
	SpinnerNumberModel  utilizacaoDoEnlaceCT2;
	
	SpinnerNumberModel  numeroDeBloqueiosCT0;
	SpinnerNumberModel  numeroDeBloqueiosCT1;
	SpinnerNumberModel  numeroDeBloqueiosCT2;
	
	SpinnerNumberModel  numeroDePreempcoesCT0;
	SpinnerNumberModel  numeroDePreempcoesCT1;
	/*SpinnerNumberModel  numeroDePreempcoesCT2;
	
	SpinnerNumberModel  numeroDeDevolucoesCT0;*/
	SpinnerNumberModel  numeroDeDevolucoesCT1;
	SpinnerNumberModel  numeroDeDevolucoesCT2;
	
	
	
	public QueryDialog(JFrame parent)
	{
		super(parent,true);
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
		
		this.setTitle("Query cases = Preparando a Consulta");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step1.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(16,2));
		panel.setLayout(new SpringLayout());
		//panel.setPreferredSize(new Dimension(1024, 800));
		
		
		JLabel label;
		panel.add(label = new JLabel("Descrição"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel("Valor (%)"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		
		/*JLabel label2;
		panel.add(label2 = new JLabel("Descrição"));
		label2.setFont(label2.getFont().deriveFont(Font.BOLD));
		panel.add(label2 = new JLabel("Valor (%)"));
		label2.setFont(label2.getFont().deriveFont(Font.BOLD));*/
		
		
		
		panel.add(new JLabel("Nome do gestor"));
		panel.add(gestor = new JTextField());
		//gestor.getText()
		
		panel.add(new JLabel("BAM Atual"));
		String[] _BAMAtual = {"NoPreemptionMAM", "NoPreemptionRDM", "NoPreemptionAllocCTSharing","PreemptionMAM", "PreemptionRDM", "PreemptionAllocCTSharing","PreemptionGBAM"};
		panel.add(BAMAtual = new JComboBox(_BAMAtual));
		
		/*panel.add(new JLabel("Problema"));
		String[] _problema = {
				"AltaPreempcao", "AltoBloqueio", "AltaDevolucao", "BaixaUtilizacao",
				"AltaPreempcaoCT0", "AltoBloqueioCT0", "AltaDevolucaoCT0",
				"AltaPreempcaoCT1", "AltoBloqueioCT1", "AltaDevolucaoCT1",
				"AltaPreempcaoCT2", "AltoBloqueioCT2", "AltaDevolucaoCT2"};
		panel.add(problema = new JComboBox(_problema));*/
		
		
		panel.add(new JLabel("SLA para Utilização CTO"));
		SLAUtilizacaoCT0 = new SpinnerNumberModel(35,0,100,1); 
		panel.add(new JSpinner(SLAUtilizacaoCT0));
		
		panel.add(new JLabel("SLA para Utilização CT1"));
		SLAUtilizacaoCT1 = new SpinnerNumberModel(40,0,100,1); 
		panel.add(new JSpinner(SLAUtilizacaoCT1));
		
		panel.add(new JLabel("SLA para Utilização CT2"));
		SLAUtilizacaoCT2 = new SpinnerNumberModel(45,0,100,1); 
		panel.add(new JSpinner(SLAUtilizacaoCT2));
		
		
		panel.add(new JLabel("SLA para Bloqueio CTO"));
		SLABloqueiosCT0 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(SLABloqueiosCT0));
		
		panel.add(new JLabel("SLA para Bloqueio CT1"));
		SLABloqueiosCT1 = new SpinnerNumberModel(70,0,100,1); 
		panel.add(new JSpinner(SLABloqueiosCT1));
		
		panel.add(new JLabel("SLA para Bloqueio CT2"));
		SLABloqueiosCT2 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(SLABloqueiosCT2));

		
		panel.add(new JLabel("SLA para Preempção CTO"));
		SLAPreempcoesCT0 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(SLAPreempcoesCT0));
		
		panel.add(new JLabel("SLA para Preempção CT1"));
		SLAPreempcoesCT1 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(SLAPreempcoesCT1));
		
		/*panel.add(new JLabel("SLA para Preempção CT2"));
		SLAPreempcoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(SLAPreempcoesCT2));
		
		
		panel.add(new JLabel("SLA para Devolução CTO"));
		SLADevolucoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(SLADevolucoesCT0));*/
		
		panel.add(new JLabel("SLA para Devolução CT1"));
		SLADevolucoesCT1 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(SLADevolucoesCT1));
		
		panel.add(new JLabel("SLA para Devolução CT2"));
		SLADevolucoesCT2 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(SLADevolucoesCT2));
				
		
		panel.add(new JLabel("Largura de Banda em BC0"));
		BC0 = new SpinnerNumberModel(250,0,10000,1); 
		panel.add(new JSpinner(BC0));
		
		panel.add(new JLabel("Largura de Banda em BC1"));
		BC1 = new SpinnerNumberModel(600,0,10000,1); 
		panel.add(new JSpinner(BC1));
		
		panel.add(new JLabel("Largura de Banda em BC2"));
		BC2 = new SpinnerNumberModel(1000,0,10000,1); 
		panel.add(new JSpinner(BC2));
		
						
		panel.add(new JLabel("Utilização do Enlace CT0"));
		utilizacaoDoEnlaceCT0 = new SpinnerNumberModel(10,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlaceCT0));
		
		panel.add(new JLabel("Utilização do Enlace CT1"));
		utilizacaoDoEnlaceCT1 = new SpinnerNumberModel(20,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlaceCT1));
		
		panel.add(new JLabel("Utilização do Enlace CT2"));
		utilizacaoDoEnlaceCT2 = new SpinnerNumberModel(30,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlaceCT2));
		
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		numeroDeBloqueiosCT0 = new SpinnerNumberModel(5,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT0));
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		numeroDeBloqueiosCT1 = new SpinnerNumberModel(6,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT1));
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		numeroDeBloqueiosCT2 = new SpinnerNumberModel(8,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT2));
		
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		numeroDePreempcoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT0));
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		numeroDePreempcoesCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT1));
		
		/*panel.add(new JLabel("Number de Preempções em CT2"));
		numeroDePreempcoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT2));
		
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		numeroDeDevolucoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeDevolucoesCT0));*/
		
		panel.add(new JLabel("Number de Devoluções em CT1"));
		numeroDeDevolucoesCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeDevolucoesCT1));
		
		panel.add(new JLabel("Number de Devoluções em CT2"));
		numeroDeDevolucoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeDevolucoesCT2));
		
		
//		Lay out the panel.
		Utils.makeCompactGrid(panel,
		                26, 2, //rows, cols  ///aumentar o numero de linhas
		                1, 1,        //initX, initY
		                1, 1);       //xPad, yPad
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(panel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Set Query >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setQuery();
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
		//this.setSize(600, this.getHeight());
		this.setResizable(false);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - this.getWidth()) / 2,
			(screenSize.height - this.getHeight()) / 2, 
			getWidth(),
			getHeight());
	}
	
	void setQuery()
	{
		this.setVisible(false);
	}
	
	public CBRQuery getQuery()
	{
		BAMDescription desc = new BAMDescription();
		
		//desc.setGestor(this.gestor.getText());
		desc.setBAMAtual(BAMTypes.valueOf((String)this.BAMAtual.getSelectedItem()));
		//desc.setProblema(Problemas.valueOf((String)this.problema.getSelectedItem()));
		
		/*desc.setSLAUtilizacaoCT0(this.SLAUtilizacaoCT0.getNumber().doubleValue());
		desc.setSLAUtilizacaoCT1(this.SLAUtilizacaoCT1.getNumber().doubleValue());
		desc.setSLAUtilizacaoCT2(this.SLAUtilizacaoCT2.getNumber().doubleValue());
		
		desc.setSLABloqueiosCT0(this.SLABloqueiosCT0.getNumber().doubleValue());
		desc.setSLABloqueiosCT1(this.SLABloqueiosCT1.getNumber().doubleValue());
		desc.setSLABloqueiosCT2(this.SLABloqueiosCT2.getNumber().doubleValue());
		
		
		desc.setSLAPreempcoesCT0(this.SLAPreempcoesCT0.getNumber().doubleValue());
		desc.setSLAPreempcoesCT1(this.SLAPreempcoesCT1.getNumber().doubleValue());
		desc.setSLAPreempcoesCT2(this.SLAPreempcoesCT2.getNumber().intValue());
		
		desc.setSLADevolucoesCT0(this.SLADevolucoesCT0.getNumber().intValue());
		desc.setSLADevolucoesCT1(this.SLADevolucoesCT1.getNumber().doubleValue());
		desc.setSLADevolucoesCT2(this.SLADevolucoesCT2.getNumber().doubleValue());*/
		
		
		desc.setBC0(this.BC0.getNumber().doubleValue());
		desc.setBC1(this.BC1.getNumber().doubleValue());
		desc.setBC2(this.BC2.getNumber().doubleValue());
		
		
		
		desc.setUtilizacaoDoEnlaceCT0(this.utilizacaoDoEnlaceCT0.getNumber().doubleValue());
		desc.setUtilizacaoDoEnlaceCT1(this.utilizacaoDoEnlaceCT1.getNumber().doubleValue());
		desc.setUtilizacaoDoEnlaceCT2(this.utilizacaoDoEnlaceCT2.getNumber().doubleValue());
				
		/*desc.setNumeroDeBloqueiosCT0(this.numeroDeBloqueiosCT0.getNumber().intValue());
		desc.setNumeroDeBloqueiosCT1(this.numeroDeBloqueiosCT1.getNumber().intValue());
		desc.setNumeroDeBloqueiosCT2(this.numeroDeBloqueiosCT2.getNumber().intValue());

		desc.setNumeroDePreempcoesCT0(this.numeroDePreempcoesCT0.getNumber().intValue());
		desc.setNumeroDePreempcoesCT1(this.numeroDePreempcoesCT1.getNumber().intValue());
		desc.setNumeroDePreempcoesCT2(this.numeroDePreempcoesCT2.getNumber().intValue());
		
		desc.setNumeroDeDevolucoesCT0(this.numeroDeDevolucoesCT0.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT1(this.numeroDeDevolucoesCT1.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT2(this.numeroDeDevolucoesCT2.getNumber().intValue());
		*/

		
		
		
		CBRQuery query = new CBRQuery();
		query.setDescription(desc);
		
		return query;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueryDialog qf = new QueryDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	

}
