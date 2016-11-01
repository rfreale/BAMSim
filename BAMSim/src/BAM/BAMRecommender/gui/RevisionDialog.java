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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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


import jcolibri.cbrcore.CBRCase;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMDescription.Problemas;
import BAM.BAMRecommender.BAMRecommender;
import BAM.BAMRecommender.BAMSolution;
import BAM.BAMRecommender.BAMDescription.BAMTypes;
import jcolibri.util.FileIO;

/**
 * Revision Dialog
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class RevisionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	
	JLabel caseId;
	JComboBox BAMAtual;
	JComboBox problema;
	SpinnerNumberModel  utilizacaoDoEnlaceCT0;
	SpinnerNumberModel  utilizacaoDoEnlaceCT1;
	SpinnerNumberModel  utilizacaoDoEnlaceCT2;
	SpinnerNumberModel  numeroDePreempcoesCT0;
	SpinnerNumberModel  numeroDePreempcoesCT1;
	SpinnerNumberModel  numeroDePreempcoesCT2;
	SpinnerNumberModel  numeroDeBloqueiosCT0;
	SpinnerNumberModel  numeroDeBloqueiosCT1;
	SpinnerNumberModel  numeroDeBloqueiosCT2;
	SpinnerNumberModel  numeroDeDevolucoesCT0;
	SpinnerNumberModel  numeroDeDevolucoesCT1;
	SpinnerNumberModel  numeroDeDevolucoesCT2;
	JComboBox BAMNovo;
	JCheckBox aceita;
	
	
	
	ArrayList<CBRCase> cases;
	int currentCase;
	
	public RevisionDialog(JFrame main)
	{
		super(main, true);
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
		
		this.setTitle("Revise Cases");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step5.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(8,2));
		panel.setLayout(new SpringLayout());
		
		JLabel label;

		panel.add(label = new JLabel("Description"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());
		
		panel.add(new JLabel("BAM Atual"));
		String[] _BAMAtual = {"NoPreemptionMAM", "NoPreemptionRDM", "NoPreemptionAllocCTSharing","PreemptionMAM", "PreemptionRDM", "PreemptionAllocCTSharing","PreemptionGBAM"};
		panel.add(BAMAtual = new JComboBox(_BAMAtual));
		
		panel.add(new JLabel("Problema "));
		String[] _problema = {
				"AltaPreempcao", "AltoBloqueio", "AltaDevolucao", "BaixaUtilizacao",
				"AltaPreempcaoCT0", "AltoBloqueioCT0", "AltaDevolucaoCT0",
				"AltaPreempcaoCT1", "AltoBloqueioCT1", "AltaDevolucaoCT1",
				"AltaPreempcaoCT2", "AltoBloqueioCT2", "AltaDevolucaoCT2"};
		panel.add(problema = new JComboBox(_problema));
		
		panel.add(new JLabel("Utilização do Enlace CT0"));
		utilizacaoDoEnlaceCT0 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlaceCT0));
		
		panel.add(new JLabel("Utilização do Enlace CT1"));
		utilizacaoDoEnlaceCT1 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlaceCT1));
		
		panel.add(new JLabel("Utilização do Enlace CT2"));
		utilizacaoDoEnlaceCT2 = new SpinnerNumberModel(0,0,100,1); 
		panel.add(new JSpinner(utilizacaoDoEnlaceCT2));
		
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
		
		panel.add(label = new JLabel("Solution"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());

		panel.add(new JLabel("BAM Novo"));
		String[] _BAMNovo = {"NoPreemptionMAM", "NoPreemptionRDM", "NoPreemptionAllocCTSharing","PreemptionMAM", "PreemptionRDM", "PreemptionAllocCTSharing","PreemptionGBAM"};
		panel.add(BAMNovo = new JComboBox(_BAMNovo));
		
		panel.add(new JLabel("Aceita?"));
		panel.add(aceita = new JCheckBox());
		
//		Lay out the panel.
		Utils.makeCompactGrid(panel,
		                16, 2, //rows, cols
		                6, 6,        //initX, initY
		                10, 10);       //xPad, yPad
		
		JPanel casesPanel = new JPanel();
		casesPanel.setLayout(new BorderLayout());
		casesPanel.add(panel, BorderLayout.CENTER);
		
		JPanel casesIterPanel = new JPanel();
		casesIterPanel.setLayout(new FlowLayout());
		JButton prev = new JButton("<<");
		casesIterPanel.add(prev);
		casesIterPanel.add(caseId = new JLabel("Case id"));
		JButton follow = new JButton(">>");
		casesIterPanel.add(follow);
		
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveCase();
				currentCase = (currentCase+cases.size()-1) % cases.size();
				showCase();
			}
		});
		
		follow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveCase();
				currentCase = (currentCase+1) % cases.size();
				showCase();
			}
		});
		
		casesPanel.add(casesIterPanel, BorderLayout.NORTH);
		
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(casesPanel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Set Revisions >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveCase();
				next();
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
	
	void next()
	{
		this.setVisible(false);
	}
	
	
	public void showCases(Collection<CBRCase> cases)
	{
		this.cases = new ArrayList<CBRCase>(cases);
		currentCase = 0;
		showCase();
	}
	
	void showCase()
	{
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString()+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		
		BAMDescription desc = (BAMDescription) _case.getDescription();
		
		this.BAMAtual.setSelectedItem(desc.getBAMAtual().toString());
		this.problema.setSelectedItem(desc.getProblema().toString());
		this.utilizacaoDoEnlaceCT0.setValue(desc.getUtilizacaoDoEnlaceCT0());
		this.utilizacaoDoEnlaceCT1.setValue(desc.getUtilizacaoDoEnlaceCT1());
		this.utilizacaoDoEnlaceCT2.setValue(desc.getUtilizacaoDoEnlaceCT2());
		this.numeroDePreempcoesCT0.setValue(desc.getNumeroDePreempcoesCT0());
		this.numeroDePreempcoesCT1.setValue(desc.getNumeroDePreempcoesCT1());
		this.numeroDePreempcoesCT2.setValue(desc.getNumeroDePreempcoesCT2());
		this.numeroDeBloqueiosCT0.setValue(desc.getNumeroDeBloqueiosCT0());
		this.numeroDeBloqueiosCT1.setValue(desc.getNumeroDeBloqueiosCT1());
		this.numeroDeBloqueiosCT2.setValue(desc.getNumeroDeBloqueiosCT2());
		this.numeroDeDevolucoesCT0.setValue(desc.getNumeroDeDevolucoesCT0());
		this.numeroDeDevolucoesCT1.setValue(desc.getNumeroDeDevolucoesCT1());
		this.numeroDeDevolucoesCT2.setValue(desc.getNumeroDeDevolucoesCT2());
			
		BAMSolution sol = (BAMSolution) _case.getSolution();
		this.BAMNovo.setSelectedItem(sol.getBAMNovo().toString());
		this.aceita.setText(sol.getAceita().toString());
		
		

	}
	
	void saveCase()
	{
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString()+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		BAMDescription desc = (BAMDescription) _case.getDescription();
		
		desc.setUtilizacaoDoEnlaceCT0(this.utilizacaoDoEnlaceCT0.getNumber().doubleValue());
		desc.setUtilizacaoDoEnlaceCT1(this.utilizacaoDoEnlaceCT1.getNumber().doubleValue());
		desc.setUtilizacaoDoEnlaceCT2(this.utilizacaoDoEnlaceCT2.getNumber().doubleValue());
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
		
				
		BAMSolution sol = (BAMSolution) _case.getSolution();
		sol.setBAMNovo(BAMTypes.valueOf((String)this.BAMNovo.getSelectedItem()));
		sol.setAceita(this.aceita.isSelected());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RevisionDialog qf = new RevisionDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	

}
