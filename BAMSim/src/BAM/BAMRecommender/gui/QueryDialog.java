/**
 * Travel Recommender example for the jCOLIBRI2 framework. 
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRQuery;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMDescription.BAMTypes;
import BAM.BAMRecommender.BAMDescription.Problemas;
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
	JComboBox BAMAtual;
	JComboBox problema;
	SpinnerNumberModel  utilizacaoDoEnlace;
	SpinnerNumberModel  numeroDePreempcoesCT0;
	SpinnerNumberModel  numeroDePreempcoesCT1;
	SpinnerNumberModel  numeroDePreempcoesCT2;
	SpinnerNumberModel  numeroDeBloqueiosCT0;
	SpinnerNumberModel  numeroDeBloqueiosCT1;
	SpinnerNumberModel  numeroDeBloqueiosCT2;
	SpinnerNumberModel  numeroDeDevolucoesCT0;
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
		
		this.setTitle("Configure Query");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step1.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(8,2));
		panel.setLayout(new SpringLayout());
		
		JLabel label;
		panel.add(label = new JLabel("Descrição"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel("Valor (%)"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		
		panel.add(new JLabel("BAM Atual"));
		String[] _BAMAtual = {"NoPreemptionMAM", "NoPreemptionRDM", "NoPreemptionAllocCTSharing","PreemptionMAM", "PreemptionRDM", "PreemptionAllocCTSharing","PreemptionGBAM"};
		panel.add(BAMAtual = new JComboBox(_BAMAtual));
		
		panel.add(new JLabel("Problema"));
		String[] _problema = {
				"AltaPreempcao", "AltoBloqueio", "AltaDevolucao", "BaixaUtilizacao",
				"AltaPreempcaoCT0", "AltoBloqueioCT0", "AltaDevolucaoCT0",
				"AltaPreempcaoCT1", "AltoBloqueioCT1", "AltaDevolucaoCT1",
				"AltaPreempcaoCT2", "AltoBloqueioCT2", "AltaDevolucaoCT2"};
		panel.add(problema = new JComboBox(_problema));
		
		panel.add(new JLabel("Utilização do Enlace"));
		utilizacaoDoEnlace = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlace));
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		numeroDePreempcoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT0));
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		numeroDePreempcoesCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT1));
		
		panel.add(new JLabel("Number de Preempções em CT2"));
		numeroDePreempcoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT2));
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		numeroDeBloqueiosCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT0));
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		numeroDeBloqueiosCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT1));
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		numeroDeBloqueiosCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT2));
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		numeroDeDevolucoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeDevolucoesCT0));
		
		panel.add(new JLabel("Number de Devoluções em CT1"));
		numeroDeDevolucoesCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeDevolucoesCT1));
		
		panel.add(new JLabel("Number de Devoluções em CT2"));
		numeroDeDevolucoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeDevolucoesCT2));
		
		
		
		
		
//		Lay out the panel.
		Utils.makeCompactGrid(panel,
		                13, 2, //rows, cols
		                6, 6,        //initX, initY
		                10, 10);       //xPad, yPad
		
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
		this.setSize(600, this.getHeight());
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
		desc.setUtilizacaoDoEnlace(this.utilizacaoDoEnlace.getNumber().doubleValue());
		desc.setNumeroDeBloqueiosCT0(this.numeroDeBloqueiosCT0.getNumber().intValue());
		desc.setNumeroDeBloqueiosCT1(this.numeroDeBloqueiosCT1.getNumber().intValue());
		desc.setNumeroDeBloqueiosCT2(this.numeroDeBloqueiosCT2.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT0(this.numeroDeDevolucoesCT0.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT1(this.numeroDeDevolucoesCT1.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT2(this.numeroDeDevolucoesCT2.getNumber().intValue());
		desc.setNumeroDePreempcoesCT0(this.numeroDePreempcoesCT0.getNumber().intValue());
		desc.setNumeroDePreempcoesCT1(this.numeroDePreempcoesCT1.getNumber().intValue());
		desc.setNumeroDePreempcoesCT2(this.numeroDePreempcoesCT2.getNumber().intValue());
		
		desc.setBAMAtual(BAMTypes.valueOf((String)this.BAMAtual.getSelectedItem()));
		desc.setProblema(Problemas.valueOf((String)this.problema.getSelectedItem()));
		
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
