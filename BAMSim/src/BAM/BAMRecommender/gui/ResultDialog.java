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
import BAM.BAMRecommender.BAMTypes;
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
	
	JLabel gestor;
	JLabel BAMAtual;
	//JLabel problema;
	
	JLabel SLAUtilizacaoCT0;
	JLabel SLAUtilizacaoCT1;
	JLabel SLAUtilizacaoCT2;
	
	
	JLabel  SLABloqueiosCT0;
	JLabel  SLABloqueiosCT1;
	JLabel  SLABloqueiosCT2;
	
	JLabel  SLAPreempcoesCT0;
	JLabel  SLAPreempcoesCT1;
	/*JLabel  SLAPreempcoesCT2;
	
	JLabel  SLADevolucoesCT0;*/
	JLabel  SLADevolucoesCT1;
	JLabel  SLADevolucoesCT2;
	
	JLabel  BC0;
	JLabel  BC1;
	JLabel  BC2;
	
	JLabel  utilizacaoDoEnlaceCT0;
	JLabel  utilizacaoDoEnlaceCT1;
	JLabel  utilizacaoDoEnlaceCT2;
	
	JLabel  numeroDeBloqueiosCT0;
	JLabel  numeroDeBloqueiosCT1;
	JLabel  numeroDeBloqueiosCT2;
	
	JLabel  numeroDePreempcoesCT0;
	JLabel  numeroDePreempcoesCT1;
	/*JLabel  numeroDePreempcoesCT2;	
	
	JLabel  numeroDeDevolucoesCT0;*/
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
		
		this.setTitle("Result casos = Casos Recuperados");

		
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

		panel.add(new JLabel("Gestor"));
		panel.add(this.gestor = new JLabel());
		
		panel.add(new JLabel("BAMAtual"));
		panel.add(this.BAMAtual = new JLabel());
		
		/*panel.add(new JLabel("Problema"));
		panel.add(this.problema = new JLabel());*/
		
		
		panel.add(new JLabel("SLA Utilização CTO"));
		panel.add(this.SLAUtilizacaoCT0= new JLabel());
		
		panel.add(new JLabel("SLA Utilização CT1"));
		panel.add(this.SLAUtilizacaoCT1= new JLabel());
		
		panel.add(new JLabel("SLA Utilização CT2"));
		panel.add(this.SLAUtilizacaoCT2= new JLabel());
		
		
		
		panel.add(new JLabel("SLA Bloqueio CTO"));
		panel.add(this.SLABloqueiosCT0= new JLabel());
		
		panel.add(new JLabel("SLA Bloqueio CT1"));
		panel.add(this.SLABloqueiosCT1= new JLabel());
		
		panel.add(new JLabel("SLA Bloqueio CT2"));
		panel.add(this.SLABloqueiosCT2= new JLabel());
		


		panel.add(new JLabel("SLA Preempção CTO"));
		panel.add(this.SLAPreempcoesCT0= new JLabel());
		
		panel.add(new JLabel("SLA Preempção CT1"));
		panel.add(this.SLAPreempcoesCT1= new JLabel());
		
		/*panel.add(new JLabel("SLA Preempção CT2"));
		panel.add(this.SLAPreempcoesCT2= new JLabel());
		
		
		panel.add(new JLabel("SLA Devolução CTO"));
		panel.add(this.SLADevolucoesCT0= new JLabel());*/
		
		panel.add(new JLabel("SLA Devolução CT1"));
		panel.add(this.SLADevolucoesCT1= new JLabel());
		
		panel.add(new JLabel("SLA Devolução CT2"));
		panel.add(this.SLADevolucoesCT2= new JLabel());
		
		
		panel.add(new JLabel("Largura de Banda em BC0"));
		panel.add(this.BC0= new JLabel());
		
		panel.add(new JLabel("Largura de Banda em BC1"));
		panel.add(this.BC1= new JLabel());
		
		panel.add(new JLabel("Largura de Banda em BC2"));
		panel.add(this.BC2= new JLabel());
		
		
		panel.add(new JLabel("Utilização do Enlace CT0"));
		panel.add(this.utilizacaoDoEnlaceCT0= new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT1"));
		panel.add(this.utilizacaoDoEnlaceCT1= new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT2"));
		panel.add(this.utilizacaoDoEnlaceCT2= new JLabel());
		
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		panel.add(this.numeroDeBloqueiosCT0= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		panel.add(this.numeroDeBloqueiosCT1= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		panel.add(this.numeroDeBloqueiosCT2= new JLabel());
		
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		panel.add(this.numeroDePreempcoesCT0= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		panel.add(this.numeroDePreempcoesCT1= new JLabel());
		
		/*panel.add(new JLabel("Number de Preempções em CT2"));
		panel.add(this.numeroDePreempcoesCT2= new JLabel());
		
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		panel.add(this.numeroDeDevolucoesCT0= new JLabel());*/
		
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
		                29, 2, //rows, cols
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
	public static String valueOf(Object obj) {
	    return (obj == null) ? "null" : obj.toString();
	}
	void showCase()
	{
		
		RetrievalResult rr = cases.get(currentCase);
		double sim = rr.getEval();
		
		CBRCase _case = rr.get_case();
		this.caseId.setText(_case.getID().toString()+" -> "+sim+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		BAMDescription desc = (BAMDescription) _case.getDescription();
		
		/*this.gestor.setText(valueOf(desc.getGestor()));
		this.BAMAtual.setText(valueOf(desc.getBAMAtual()));
		//this.problema.setText(valueOf(desc.getProblema()));
		
		this.SLAUtilizacaoCT0.setText(valueOf(desc.getSLAUtilizacaoCT0()));
		this.SLAUtilizacaoCT1.setText(valueOf(desc.getSLAUtilizacaoCT1()));
		this.SLAUtilizacaoCT2.setText(valueOf(desc.getSLAUtilizacaoCT2()));
		
		
		this.SLABloqueiosCT0.setText(valueOf(desc.getSLABloqueiosCT0()));
		this.SLABloqueiosCT1.setText(valueOf(desc.getSLABloqueiosCT1()));
		this.SLABloqueiosCT2.setText(valueOf(desc.getSLABloqueiosCT2()));

		this.SLAPreempcoesCT0.setText(valueOf(desc.getSLAPreempcoesCT0()));
		this.SLAPreempcoesCT1.setText(valueOf(desc.getSLAPreempcoesCT1()));
		this.SLAPreempcoesCT2.setText(valueOf(desc.getSLAPreempcoesCT2()));

		this.SLADevolucoesCT0.setText(valueOf(desc.getSLADevolucoesCT0()));
		this.SLADevolucoesCT1.setText(valueOf(desc.getSLADevolucoesCT1()));
		this.SLADevolucoesCT2.setText(valueOf(desc.getSLADevolucoesCT2()));*/

		this.BC0.setText(valueOf(desc.getBC0()));
		this.BC1.setText(valueOf(desc.getBC1()));
		this.BC2.setText(valueOf(desc.getBC2()));
		
		this.utilizacaoDoEnlaceCT0.setText(valueOf(desc.getUtilizacaoDoEnlaceCT0()));
		this.utilizacaoDoEnlaceCT1.setText(valueOf(desc.getUtilizacaoDoEnlaceCT1()));
		this.utilizacaoDoEnlaceCT2.setText(valueOf(desc.getUtilizacaoDoEnlaceCT2()));
		
		this.numeroDeBloqueiosCT0.setText(valueOf(desc.getNumeroDeBloqueiosCT0()));
		this.numeroDeBloqueiosCT1.setText(valueOf(desc.getNumeroDeBloqueiosCT1()));
		this.numeroDeBloqueiosCT2.setText(valueOf(desc.getNumeroDeBloqueiosCT2()));
		
		this.numeroDePreempcoesCT0.setText(valueOf(desc.getNumeroDePreempcoesCT0()));
		this.numeroDePreempcoesCT1.setText(valueOf(desc.getNumeroDePreempcoesCT1()));
		/*this.numeroDePreempcoesCT2.setText(valueOf(desc.getNumeroDePreempcoesCT2()));
		
		this.numeroDeDevolucoesCT0.setText(valueOf(desc.getNumeroDeDevolucoesCT0()));*/
		this.numeroDeDevolucoesCT1.setText(valueOf(desc.getNumeroDeDevolucoesCT1()));
		this.numeroDeDevolucoesCT2.setText(valueOf(desc.getNumeroDeDevolucoesCT2()));
			
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