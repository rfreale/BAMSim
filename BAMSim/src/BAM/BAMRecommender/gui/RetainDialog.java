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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMRecommender;
import BAM.BAMRecommender.BAMSolution;
import jcolibri.util.FileIO;

/**
 * Retain dialog
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class RetainDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static int numcases = 0;
	
JLabel image;
	CBRQuery query;
	JLabel  caseId;
	JLabel  BAMAtual;
	JLabel  problemaAtual;
	JLabel  utilizacaoDoEnlaceCT0;
	JLabel  utilizacaoDoEnlaceCT1;
	JLabel  utilizacaoDoEnlaceCT2;
	JLabel  numeroDePreempcoesCT0;
	JLabel  numeroDePreempcoesCT1;
	JLabel  numeroDePreempcoesCT2;
	JLabel  numeroDeBloqueiosCT0;
	JLabel  numeroDeBloqueiosCT1;
	JLabel  numeroDeBloqueiosCT2;
	JLabel  numeroDeDevolucoesCT0;
	JLabel  numeroDeDevolucoesCT1;
	JLabel  numeroDeDevolucoesCT2;
	JLabel  BAMAtualNew;
	JLabel  problemaNew;
	JLabel  utilizacaoDoEnlaceCT0New;
	JLabel  utilizacaoDoEnlaceCT1New;
	JLabel  utilizacaoDoEnlaceCT2New;
	JLabel  numeroDePreempcoesCT0New;
	JLabel  numeroDePreempcoesCT1New;
	JLabel  numeroDePreempcoesCT2New;
	JLabel  numeroDeBloqueiosCT0New;
	JLabel  numeroDeBloqueiosCT1New;
	JLabel  numeroDeBloqueiosCT2New;
	JLabel  numeroDeDevolucoesCT0New;
	JLabel  numeroDeDevolucoesCT1New;
	JLabel  numeroDeDevolucoesCT2New;
	JLabel  BAMNovo;
	JLabel  aceita;
	
	JTextField idEditor;
	JButton setId;
	JCheckBox saveCheck;
	
	ArrayList<CBRCase> cases;
	int currentCase;
	
	ArrayList<CBRCase> casesToRetain;
	
	public RetainDialog(JFrame main)
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
		
		this.setTitle("Revise cases");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("BAM/BAMRecommender/gui/step6.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(8,2));
		panel.setLayout(new SpringLayout());
		
		JLabel label;

		
		panel.add(label = new JLabel("Description"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel("Old Case"));
		panel.add(label = new JLabel("New Case"));

		
		panel.add(new JLabel("BAMAtual"));
		panel.add(this.BAMAtual = new JLabel());
		panel.add(this.BAMAtualNew = new JLabel());
		
		panel.add(new JLabel("Problema Atual"));
		panel.add(this.problemaAtual = new JLabel());
		panel.add(this.problemaNew = new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT0"));
		panel.add(this.utilizacaoDoEnlaceCT0= new JLabel());
		panel.add(this.utilizacaoDoEnlaceCT0New= new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT1"));
		panel.add(this.utilizacaoDoEnlaceCT1= new JLabel());
		panel.add(this.utilizacaoDoEnlaceCT1New= new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT2"));
		panel.add(this.utilizacaoDoEnlaceCT2= new JLabel());
		panel.add(this.utilizacaoDoEnlaceCT2New= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		panel.add(this.numeroDePreempcoesCT0= new JLabel());
		panel.add(this.numeroDePreempcoesCT0New= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		panel.add(this.numeroDePreempcoesCT1= new JLabel());
		panel.add(this.numeroDePreempcoesCT1New= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT2"));
		panel.add(this.numeroDePreempcoesCT2= new JLabel());
		panel.add(this.numeroDePreempcoesCT2New= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		panel.add(this.numeroDeBloqueiosCT0= new JLabel());
		panel.add(this.numeroDeBloqueiosCT0New= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		panel.add(this.numeroDeBloqueiosCT1= new JLabel());
		panel.add(this.numeroDeBloqueiosCT1New= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		panel.add(this.numeroDeBloqueiosCT2= new JLabel());
		panel.add(this.numeroDeBloqueiosCT2New= new JLabel());
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		panel.add(this.numeroDeDevolucoesCT0= new JLabel());
		panel.add(this.numeroDeDevolucoesCT0New= new JLabel());
		
		panel.add(new JLabel("Number de Devoluções em CT1"));
		panel.add(this.numeroDeDevolucoesCT1= new JLabel());
		panel.add(this.numeroDeDevolucoesCT1New= new JLabel());
		
		panel.add(new JLabel("Number de Devoluções em CT2"));
		panel.add(this.numeroDeDevolucoesCT2= new JLabel());
		panel.add(this.numeroDeDevolucoesCT2New= new JLabel());
		
		panel.add(label = new JLabel("Solution"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());
		panel.add(label = new JLabel());		

		
		panel.add(new JLabel("BAMNovo"));
		panel.add(this.BAMNovo = new JLabel());
		panel.add(label = new JLabel());
		
		panel.add(new JLabel("Aceita?"));
		panel.add(this.aceita = new JLabel());
		panel.add(label = new JLabel());
		
//		Lay out the panel.
		Utils.makeCompactGrid(panel,
		                18, 3, //rows, cols
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
		
		
		JPanel defineIdsPanel = new JPanel();
		saveCheck = new JCheckBox("Save Case with new Id:");
		defineIdsPanel.add(saveCheck);
		saveCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				enableSaveCase();
			}
		});
		idEditor = new JTextField(20);
		defineIdsPanel.add(idEditor);
		setId = new JButton("Apply");
		defineIdsPanel.add(setId);
		
		setId.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setId();
			}
		});
		enableSaveCase();
		
		casesPanel.add(defineIdsPanel, BorderLayout.SOUTH);
		
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
	
	void enableSaveCase()
	{
		idEditor.setEnabled(saveCheck.isSelected());
		setId.setEnabled(saveCheck.isSelected());
	}
	
	public void showCases(Collection<CBRCase> eval, int casebasesize, CBRQuery _query)
	{
		cases = new ArrayList<CBRCase>(eval);
		casesToRetain = new ArrayList<CBRCase>();
		currentCase = 0;
		if(numcases<casebasesize)
			numcases = casebasesize;
		idEditor.setText("bam"+(numcases+1));
		this.query=_query;
		showCase();
	}
	public static String valueOf(Object obj) {
	    return (obj == null) ? "null" : obj.toString();
	}
	void showCase()
	{
		
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString()+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		
		BAMDescription desc = (BAMDescription) _case.getDescription();
		BAMDescription descNew =(BAMDescription) query.getDescription();
		
		this.BAMAtual.setText(desc.getBAMAtual().toString());
		this.problemaAtual.setText(desc.getProblema().toString());
		this.utilizacaoDoEnlaceCT0.setText(valueOf(desc.getUtilizacaoDoEnlaceCT0()));
		this.utilizacaoDoEnlaceCT1.setText(valueOf(desc.getUtilizacaoDoEnlaceCT1()));
		this.utilizacaoDoEnlaceCT2.setText(valueOf(desc.getUtilizacaoDoEnlaceCT2()));
		this.numeroDePreempcoesCT0.setText(valueOf(desc.getNumeroDePreempcoesCT0()));
		this.numeroDePreempcoesCT1.setText(valueOf(desc.getNumeroDePreempcoesCT1()));
		this.numeroDePreempcoesCT2.setText(valueOf(desc.getNumeroDePreempcoesCT2()));
		this.numeroDeBloqueiosCT0.setText(valueOf(desc.getNumeroDeBloqueiosCT0()));
		this.numeroDeBloqueiosCT1.setText(valueOf(desc.getNumeroDeBloqueiosCT1()));
		this.numeroDeBloqueiosCT2.setText(valueOf(desc.getNumeroDeBloqueiosCT2()));
		this.numeroDeDevolucoesCT0.setText(valueOf(desc.getNumeroDeDevolucoesCT0()));
		this.numeroDeDevolucoesCT1.setText(valueOf(desc.getNumeroDeDevolucoesCT1()));
		this.numeroDeDevolucoesCT2.setText(valueOf(desc.getNumeroDeDevolucoesCT2()));
			
		this.BAMAtualNew.setText(descNew.getBAMAtual().toString());
		this.problemaNew.setText(descNew.getProblema().toString());
		this.utilizacaoDoEnlaceCT0New.setText(valueOf(descNew.getUtilizacaoDoEnlaceCT0()));
		this.utilizacaoDoEnlaceCT1New.setText(valueOf(descNew.getUtilizacaoDoEnlaceCT1()));
		this.utilizacaoDoEnlaceCT2New.setText(valueOf(descNew.getUtilizacaoDoEnlaceCT2()));
		this.numeroDePreempcoesCT0New.setText(valueOf(descNew.getNumeroDePreempcoesCT0()));
		this.numeroDePreempcoesCT1New.setText(valueOf(descNew.getNumeroDePreempcoesCT1()));
		this.numeroDePreempcoesCT2New.setText(valueOf(descNew.getNumeroDePreempcoesCT2()));
		this.numeroDeBloqueiosCT0New.setText(valueOf(descNew.getNumeroDeBloqueiosCT0()));
		this.numeroDeBloqueiosCT1New.setText(valueOf(descNew.getNumeroDeBloqueiosCT1()));
		this.numeroDeBloqueiosCT2New.setText(valueOf(descNew.getNumeroDeBloqueiosCT2()));
		this.numeroDeDevolucoesCT0New.setText(valueOf(descNew.getNumeroDeDevolucoesCT0()));
		this.numeroDeDevolucoesCT1New.setText(valueOf(descNew.getNumeroDeDevolucoesCT1()));
		this.numeroDeDevolucoesCT2New.setText(valueOf(descNew.getNumeroDeDevolucoesCT2()));
		
		BAMSolution sol = (BAMSolution) _case.getSolution();
		this.BAMNovo.setText(sol.getBAMNovo().toString());
		this.aceita.setText(sol.getAceita().toString());
	}
	
	
	void setId()
	{
		CBRCase _case = cases.get(currentCase);
		cases.remove(_case);
		
		BAMDescription desc = ((BAMDescription) query.getDescription()).clone();
		desc.setCaseId(idEditor.getText());
		BAMSolution sol = ((BAMSolution) _case.getSolution()).clone();
		sol.setId(idEditor.getText());
		CBRCase novocase = new CBRCase();
		novocase.setDescription(desc);
		novocase.setSolution(sol);
		casesToRetain.add(novocase);
		
		currentCase = 0;
		idEditor.setText("bam"+(numcases++));
		saveCheck.setSelected(false);
		enableSaveCase();
		showCase();
	}
	
	
	public Collection<CBRCase> getCasestoRetain()
	{
		return casesToRetain;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RetainDialog qf = new RetainDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	

}
