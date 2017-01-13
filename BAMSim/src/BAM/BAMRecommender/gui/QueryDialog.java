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
import javax.swing.JTextField;
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
	JTextField gestor;
	JComboBox BAMAtual;
	JComboBox problema;
	
	SpinnerNumberModel  toleranciaBloqueiosCT0;
	SpinnerNumberModel  toleranciaBloqueiosCT1;
	SpinnerNumberModel  toleranciaBloqueiosCT2;
	
	SpinnerNumberModel  toleranciaPreempcoesCT0;
	SpinnerNumberModel  toleranciaPreempcoesCT1;
	SpinnerNumberModel  toleranciaPreempcoesCT2;
	
	SpinnerNumberModel  toleranciaDevolucoesCT0;
	SpinnerNumberModel  toleranciaDevolucoesCT1;
	SpinnerNumberModel  toleranciaDevolucoesCT2;
	
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
	SpinnerNumberModel  numeroDePreempcoesCT2;
	
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
		
		this.setTitle("Query cases = Preparando a Consulta");

		
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
		
		panel.add(new JLabel("Nome do gestor"));
		panel.add(gestor = new JTextField());
		//gestor.getText()
		
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
		
		
		panel.add(new JLabel("Limite Bloqueio CTO"));
		toleranciaBloqueiosCT0 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(toleranciaBloqueiosCT0));
		
		panel.add(new JLabel("Limite Bloqueio CT1"));
		toleranciaBloqueiosCT1 = new SpinnerNumberModel(70,0,100,1); 
		panel.add(new JSpinner(toleranciaBloqueiosCT1));
		
		panel.add(new JLabel("Limite Bloqueio CT2"));
		toleranciaBloqueiosCT2 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(toleranciaBloqueiosCT2));

		
		panel.add(new JLabel("Limite Preempção CTO"));
		toleranciaPreempcoesCT0 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(toleranciaPreempcoesCT0));
		
		panel.add(new JLabel("Limite Preempção CT1"));
		toleranciaPreempcoesCT1 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(toleranciaPreempcoesCT1));
		
		panel.add(new JLabel("Limite Preempção CT2"));
		toleranciaPreempcoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(toleranciaPreempcoesCT2));
		
		
		panel.add(new JLabel("Limite Devolução CTO"));
		toleranciaDevolucoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(toleranciaDevolucoesCT0));
		
		panel.add(new JLabel("Limite Devolução CT1"));
		toleranciaDevolucoesCT1 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(toleranciaDevolucoesCT1));
		
		panel.add(new JLabel("Limite Devolução CT2"));
		toleranciaDevolucoesCT2 = new SpinnerNumberModel(80,0,100,1); 
		panel.add(new JSpinner(toleranciaDevolucoesCT2));
				
		
		panel.add(new JLabel("Largura de Banda em BC0"));
		BC0 = new SpinnerNumberModel(250,0,10000,1); 
		panel.add(new JSpinner(BC0));
		
		panel.add(new JLabel("Largura de Banda em BC1"));
		BC1 = new SpinnerNumberModel(200,0,10000,1); 
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
		numeroDeBloqueiosCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT0));
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		numeroDeBloqueiosCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT1));
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		numeroDeBloqueiosCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDeBloqueiosCT2));
		
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		numeroDePreempcoesCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT0));
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		numeroDePreempcoesCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT1));
		
		panel.add(new JLabel("Number de Preempções em CT2"));
		numeroDePreempcoesCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(numeroDePreempcoesCT2));
		
		
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
		                28, 2, //rows, cols  ///aumentar o numero de linhas
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
		
		desc.setGestor(this.gestor.getText());
		desc.setBAMAtual(BAMTypes.valueOf((String)this.BAMAtual.getSelectedItem()));
		desc.setProblema(Problemas.valueOf((String)this.problema.getSelectedItem()));
		
		desc.setToleranciaBloqueiosCT0(this.toleranciaBloqueiosCT0.getNumber().intValue());
		desc.setToleranciaBloqueiosCT1(this.toleranciaBloqueiosCT1.getNumber().intValue());
		desc.setToleranciaBloqueiosCT2(this.toleranciaBloqueiosCT2.getNumber().intValue());
		
		desc.setToleranciaDevolucoesCT0(this.toleranciaDevolucoesCT0.getNumber().intValue());
		desc.setToleranciaDevolucoesCT1(this.toleranciaDevolucoesCT1.getNumber().intValue());
		desc.setToleranciaDevolucoesCT2(this.toleranciaDevolucoesCT2.getNumber().intValue());
		
		desc.setToleranciaPreempcoesCT0(this.toleranciaPreempcoesCT0.getNumber().intValue());
		desc.setToleranciaPreempcoesCT1(this.toleranciaPreempcoesCT1.getNumber().intValue());
		desc.setToleranciaPreempcoesCT2(this.toleranciaPreempcoesCT2.getNumber().intValue());
		
		
		desc.setBC0(this.BC0.getNumber().intValue());
		desc.setBC1(this.BC1.getNumber().intValue());
		desc.setBC2(this.BC2.getNumber().intValue());
		
		
		
		desc.setUtilizacaoDoEnlaceCT0(this.utilizacaoDoEnlaceCT0.getNumber().doubleValue());
		desc.setUtilizacaoDoEnlaceCT1(this.utilizacaoDoEnlaceCT1.getNumber().doubleValue());
		desc.setUtilizacaoDoEnlaceCT2(this.utilizacaoDoEnlaceCT2.getNumber().doubleValue());
				
		desc.setNumeroDeBloqueiosCT0(this.numeroDeBloqueiosCT0.getNumber().intValue());
		desc.setNumeroDeBloqueiosCT1(this.numeroDeBloqueiosCT1.getNumber().intValue());
		desc.setNumeroDeBloqueiosCT2(this.numeroDeBloqueiosCT2.getNumber().intValue());

		desc.setNumeroDePreempcoesCT0(this.numeroDePreempcoesCT0.getNumber().intValue());
		desc.setNumeroDePreempcoesCT1(this.numeroDePreempcoesCT1.getNumber().intValue());
		desc.setNumeroDePreempcoesCT2(this.numeroDePreempcoesCT2.getNumber().intValue());
		
		desc.setNumeroDeDevolucoesCT0(this.numeroDeDevolucoesCT0.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT1(this.numeroDeDevolucoesCT1.getNumber().intValue());
		desc.setNumeroDeDevolucoesCT2(this.numeroDeDevolucoesCT2.getNumber().intValue());
		

		
		
		
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
