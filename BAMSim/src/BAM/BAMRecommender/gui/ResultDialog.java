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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRCase;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMRecommender;
import BAM.BAMRecommender.BAMSolution;
import BAM.BAMRecommender.BAMDescription.BAMTypes;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.util.FileIO;

/**
 * Result dialog
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class ResultDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	
	JLabel caseId;
	JLabel BAMAtual;
	JLabel problema;
	JLabel  utilizacaoDoEnlace;
	JLabel  numeroDePreempcoesCT0;
	JLabel  numeroDePreempcoesCT1;
	JLabel  numeroDePreempcoesCT2;
	JLabel  numeroDeBloqueiosCT0;
	JLabel  numeroDeBloqueiosCT1;
	JLabel  numeroDeBloqueiosCT2;
	JLabel  numeroDeDevolucoesCT0;
	JLabel  numeroDeDevolucoesCT1;
	JLabel  numeroDeDevolucoesCT2;
	JLabel BAMNovo;
	JLabel aceita;
	
	 
	
	ArrayList<RetrievalResult> cases;
	int currentCase;
	
	public ResultDialog(JFrame main)
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
		
		this.setTitle("Casos recuperados");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step3.png")));
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

		
		panel.add(new JLabel("BAMAtual"));
		panel.add(this.BAMAtual = new JLabel());
		
		panel.add(new JLabel("Problema"));
		panel.add(this.problema = new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace"));
		panel.add(this.utilizacaoDoEnlace= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		panel.add(this.numeroDePreempcoesCT0= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		panel.add(this.numeroDePreempcoesCT1= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT2"));
		panel.add(this.numeroDePreempcoesCT2= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		panel.add(this.numeroDeBloqueiosCT0= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		panel.add(this.numeroDeBloqueiosCT1= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		panel.add(this.numeroDeBloqueiosCT2= new JLabel());
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		panel.add(this.numeroDeDevolucoesCT0= new JLabel());
		
		panel.add(new JLabel("Number de Devoluções em CT1"));
		panel.add(this.numeroDeDevolucoesCT1= new JLabel());
		
		panel.add(new JLabel("Number de Devoluções em CT2"));
		panel.add(this.numeroDeDevolucoesCT2= new JLabel());
		
		panel.add(label = new JLabel("Solution"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());

		panel.add(new JLabel("BAMNovo"));
		panel.add(this.BAMNovo = new JLabel());
		
		panel.add(new JLabel("Aceita?"));
		panel.add(this.aceita = new JLabel());
		
		
//		Lay out the panel.
		Utils.makeCompactGrid(panel,
		                16, 2, //rows, cols
		                6, 6,        //initX, initY
		                30, 10);       //xPad, yPad
		
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
				currentCase = (currentCase+cases.size()-1) % cases.size();
				showCase();
			}
		});
		
		follow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
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
		
		JButton ok = new JButton("Next >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
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
	
	
	public void showCases(Collection<RetrievalResult> eval, Collection<CBRCase> selected)
	{
		cases = new ArrayList<RetrievalResult>();
		for(RetrievalResult rr: eval)
			if(selected.contains(rr.get_case()))
				cases.add(rr);
		currentCase = 0;
		showCase();
	}
	
	void showCase()
	{
		RetrievalResult rr = cases.get(currentCase);
		double sim = rr.getEval();
		
		CBRCase _case = rr.get_case();
		this.caseId.setText(_case.getID().toString()+" -> "+sim+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		BAMDescription desc = (BAMDescription) _case.getDescription();
		
		this.BAMAtual.setText(desc.getBAMAtual().toString());
		this.problema.setText(desc.getProblema().toString());
		this.utilizacaoDoEnlace.setText(desc.getUtilizacaoDoEnlace().toString());
		this.numeroDePreempcoesCT0.setText(desc.getNumeroDePreempcoesCT0().toString());
		this.numeroDePreempcoesCT1.setText(desc.getNumeroDePreempcoesCT1().toString());
		this.numeroDePreempcoesCT2.setText(desc.getNumeroDePreempcoesCT2().toString());
		this.numeroDeBloqueiosCT0.setText(desc.getNumeroDeBloqueiosCT0().toString());
		this.numeroDeBloqueiosCT1.setText(desc.getNumeroDeBloqueiosCT1().toString());
		this.numeroDeBloqueiosCT2.setText(desc.getNumeroDeBloqueiosCT2().toString());
		this.numeroDeDevolucoesCT0.setText(desc.getNumeroDeDevolucoesCT0().toString());
		this.numeroDeDevolucoesCT1.setText(desc.getNumeroDeDevolucoesCT1().toString());
		this.numeroDeDevolucoesCT2.setText(desc.getNumeroDeDevolucoesCT2().toString());
			
		BAMSolution sol = (BAMSolution) _case.getSolution();
		this.BAMNovo.setText(sol.getBAMNovo().toString());
		this.aceita.setText(sol.getAceita().toString());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResultDialog qf = new ResultDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	

}
