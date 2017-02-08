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
	JLabel	gestor;
	JLabel  BAMAtual;
	//JLabel  problemaAtual;
	
	JLabel  SLAUtilizacaoCT0;
	JLabel  SLAUtilizacaoCT1;
	JLabel  SLAUtilizacaoCT2;
	
	
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
	
	
	
	
	JLabel	gestorNew;
	JLabel  BAMAtualNew;
	JLabel  problemaNew;
	
	

	
	JLabel  SLAUtilizacaoCT0New;
	JLabel  SLAUtilizacaoCT1New;
	JLabel  SLAUtilizacaoCT2New;
	
	JLabel  SLABloqueiosCT0New;
	JLabel  SLABloqueiosCT1New;
	JLabel  SLABloqueiosCT2New;
	
	JLabel  SLAPreempcoesCT0New;
	JLabel  SLAPreempcoesCT1New;
	JLabel  SLAPreempcoesCT2New;
	
	JLabel  SLADevolucoesCT0New;
	JLabel  SLADevolucoesCT1New;
	JLabel  SLADevolucoesCT2New;
	
	JLabel  BC0New;
	JLabel  BC1New;
	JLabel  BC2New;
	

	JLabel  utilizacaoDoEnlaceCT0New;
	JLabel  utilizacaoDoEnlaceCT1New;
	JLabel  utilizacaoDoEnlaceCT2New;
	
	JLabel  numeroDeBloqueiosCT0New;
	JLabel  numeroDeBloqueiosCT1New;
	JLabel  numeroDeBloqueiosCT2New;
	
	JLabel  numeroDePreempcoesCT0New;
	JLabel  numeroDePreempcoesCT1New;
	/*JLabel  numeroDePreempcoesCT2New;
	
	JLabel  numeroDeDevolucoesCT0New;*/
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
		
		this.setTitle("Retain cases = Guardar casos");

		
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

		
		panel.add(new JLabel("Gestor"));
		panel.add(this.gestor = new JLabel());
		panel.add(this.gestorNew = new JLabel());
		
		panel.add(new JLabel("BAMAtual"));
		panel.add(this.BAMAtual = new JLabel());
		panel.add(this.BAMAtualNew = new JLabel());
		
	/*	panel.add(new JLabel("Problema Atual"));
		panel.add(this.problemaAtual = new JLabel());
		panel.add(this.problemaNew = new JLabel());
		*/
		
		
		panel.add(new JLabel("SLA Utilização CTO"));
		panel.add(this.SLAUtilizacaoCT0= new JLabel());
		panel.add(this.SLAUtilizacaoCT0New= new JLabel());
		
		panel.add(new JLabel("SLA Utilização CT1"));
		panel.add(this.SLAUtilizacaoCT1= new JLabel());
		panel.add(this.SLAUtilizacaoCT1New= new JLabel());
		
		panel.add(new JLabel("SLA Utilização CT2"));
		panel.add(this.SLAUtilizacaoCT2= new JLabel());
		panel.add(this.SLAUtilizacaoCT2New= new JLabel());
		
		
		
		panel.add(new JLabel("SLA Bloqueio CTO"));
		panel.add(this.SLABloqueiosCT0= new JLabel());
		panel.add(this.SLABloqueiosCT0New= new JLabel());
		
		panel.add(new JLabel("SLA Bloqueio CT1"));
		panel.add(this.SLABloqueiosCT1= new JLabel());
		panel.add(this.SLABloqueiosCT1New= new JLabel());
		
		panel.add(new JLabel("SLA Bloqueio CT2"));
		panel.add(this.SLABloqueiosCT2= new JLabel());
		panel.add(this.SLABloqueiosCT2New= new JLabel());
		
		
		panel.add(new JLabel("SLA Preempção CTO"));
		panel.add(this.SLAPreempcoesCT0= new JLabel());
		panel.add(this.SLAPreempcoesCT0New= new JLabel());
		
		panel.add(new JLabel("SLA Preempção CT1"));
		panel.add(this.SLAPreempcoesCT1= new JLabel());
		panel.add(this.SLAPreempcoesCT1New= new JLabel());
		
		/*panel.add(new JLabel("SLA Preempção CT2"));
		panel.add(this.SLAPreempcoesCT2= new JLabel());
		panel.add(this.SLAPreempcoesCT2New= new JLabel());
		
		
		
		panel.add(new JLabel("SLA Devolução CTO"));
		panel.add(this.SLADevolucoesCT0= new JLabel());
		panel.add(this.SLADevolucoesCT0New= new JLabel());*/
		
		panel.add(new JLabel("SLA Devolução CT1"));
		panel.add(this.SLADevolucoesCT1= new JLabel());
		panel.add(this.SLADevolucoesCT1New= new JLabel());
		
		panel.add(new JLabel("SLA Devolução CT2"));
		panel.add(this.SLADevolucoesCT2= new JLabel());
		panel.add(this.SLADevolucoesCT2New= new JLabel());
				
		
		panel.add(new JLabel("Largura de Banda em BC0"));
		panel.add(this.BC0= new JLabel());
		panel.add(this.BC0New= new JLabel());
		
		panel.add(new JLabel("Largura de Banda em BC1"));
		panel.add(this.BC1= new JLabel());
		panel.add(this.BC1New= new JLabel());
		
		panel.add(new JLabel("Largura de Banda em BC2"));
		panel.add(this.BC2= new JLabel());
		panel.add(this.BC2New= new JLabel());
		
				
		panel.add(new JLabel("Utilização do Enlace CT0"));
		panel.add(this.utilizacaoDoEnlaceCT0= new JLabel());
		panel.add(this.utilizacaoDoEnlaceCT0New= new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT1"));
		panel.add(this.utilizacaoDoEnlaceCT1= new JLabel());
		panel.add(this.utilizacaoDoEnlaceCT1New= new JLabel());
		
		panel.add(new JLabel("Utilização do Enlace CT2"));
		panel.add(this.utilizacaoDoEnlaceCT2= new JLabel());
		panel.add(this.utilizacaoDoEnlaceCT2New= new JLabel());
		
		
		panel.add(new JLabel("Number de Bloqueios em CT0"));
		panel.add(this.numeroDeBloqueiosCT0= new JLabel());
		panel.add(this.numeroDeBloqueiosCT0New= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT1"));
		panel.add(this.numeroDeBloqueiosCT1= new JLabel());
		panel.add(this.numeroDeBloqueiosCT1New= new JLabel());
		
		panel.add(new JLabel("Number de Bloqueios em CT2"));
		panel.add(this.numeroDeBloqueiosCT2= new JLabel());
		panel.add(this.numeroDeBloqueiosCT2New= new JLabel());
		
		
		panel.add(new JLabel("Number de Preempções em CT0"));
		panel.add(this.numeroDePreempcoesCT0= new JLabel());
		panel.add(this.numeroDePreempcoesCT0New= new JLabel());
		
		panel.add(new JLabel("Number de Preempções em CT1"));
		panel.add(this.numeroDePreempcoesCT1= new JLabel());
		panel.add(this.numeroDePreempcoesCT1New= new JLabel());
		
		/*panel.add(new JLabel("Number de Preempções em CT2"));
		panel.add(this.numeroDePreempcoesCT2= new JLabel());
		panel.add(this.numeroDePreempcoesCT2New= new JLabel());
		
		
		panel.add(new JLabel("Number de Devoluções em CT0"));
		panel.add(this.numeroDeDevolucoesCT0= new JLabel());
		panel.add(this.numeroDeDevolucoesCT0New= new JLabel());*/
		
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
		                29, 3, //rows, cols
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
		
		this.gestor.setText(desc.getGestor().toString());
		this.BAMAtual.setText(desc.getBAMAtual().toString());
		/*this.problemaAtual.setText(desc.getProblema().toString());
		*/
		this.SLAUtilizacaoCT0.setText(valueOf(desc.getSLAUtilizacaoCT0()));
		this.SLAUtilizacaoCT1.setText(valueOf(desc.getSLAUtilizacaoCT1()));
		this.SLAUtilizacaoCT2.setText(valueOf(desc.getSLAUtilizacaoCT2()));
		
		
		this.SLABloqueiosCT0.setText(valueOf(desc.getSLABloqueiosCT0()));
		this.SLABloqueiosCT1.setText(valueOf(desc.getSLABloqueiosCT1()));
		this.SLABloqueiosCT2.setText(valueOf(desc.getSLABloqueiosCT2()));
		
		this.SLAPreempcoesCT0.setText(valueOf(desc.getSLAPreempcoesCT0()));
		this.SLAPreempcoesCT1.setText(valueOf(desc.getSLAPreempcoesCT1()));
		/*this.SLAPreempcoesCT2.setText(valueOf(desc.getSLAPreempcoesCT2()));
		
		this.SLADevolucoesCT0.setText(valueOf(desc.getSLADevolucoesCT0()));*/
		this.SLADevolucoesCT1.setText(valueOf(desc.getSLADevolucoesCT1()));
		this.SLADevolucoesCT2.setText(valueOf(desc.getSLADevolucoesCT2()));
		
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
		
		
		this.gestorNew.setText(descNew.getGestor().toString());
		this.BAMAtualNew.setText(descNew.getBAMAtual().toString());
		/*this.problemaNew.setText(descNew.getProblema().toString());*/
		
		
		this.SLAUtilizacaoCT0New.setText(valueOf(descNew.getSLAUtilizacaoCT0()));
		this.SLAUtilizacaoCT1New.setText(valueOf(descNew.getSLAUtilizacaoCT1()));
		this.SLAUtilizacaoCT2New.setText(valueOf(descNew.getSLAUtilizacaoCT2()));
		
		
		this.SLABloqueiosCT0New.setText(valueOf(descNew.getSLABloqueiosCT0()));
		this.SLABloqueiosCT1New.setText(valueOf(descNew.getSLABloqueiosCT1()));
		this.SLABloqueiosCT2New.setText(valueOf(descNew.getSLABloqueiosCT2()));
		
		this.SLAPreempcoesCT0New.setText(valueOf(descNew.getSLAPreempcoesCT0()));
		this.SLAPreempcoesCT1New.setText(valueOf(descNew.getSLAPreempcoesCT1()));
		/*this.SLAPreempcoesCT2New.setText(valueOf(descNew.getSLAPreempcoesCT2()));
		
		this.SLADevolucoesCT0New.setText(valueOf(descNew.getSLADevolucoesCT0()));*/
		this.SLADevolucoesCT1New.setText(valueOf(descNew.getSLADevolucoesCT1()));
		this.SLADevolucoesCT2New.setText(valueOf(descNew.getSLADevolucoesCT2()));
		
		this.BC0New.setText(valueOf(descNew.getBC0()));
		this.BC1New.setText(valueOf(descNew.getBC1()));
		this.BC2New.setText(valueOf(descNew.getBC2()));
		
		this.utilizacaoDoEnlaceCT0New.setText(valueOf(descNew.getUtilizacaoDoEnlaceCT0()));
		this.utilizacaoDoEnlaceCT1New.setText(valueOf(descNew.getUtilizacaoDoEnlaceCT1()));
		this.utilizacaoDoEnlaceCT2New.setText(valueOf(descNew.getUtilizacaoDoEnlaceCT2()));
		
		this.numeroDeBloqueiosCT0New.setText(valueOf(descNew.getNumeroDeBloqueiosCT0()));
		this.numeroDeBloqueiosCT1New.setText(valueOf(descNew.getNumeroDeBloqueiosCT1()));
		this.numeroDeBloqueiosCT2New.setText(valueOf(descNew.getNumeroDeBloqueiosCT2()));
		
		this.numeroDePreempcoesCT0New.setText(valueOf(descNew.getNumeroDePreempcoesCT0()));
		this.numeroDePreempcoesCT1New.setText(valueOf(descNew.getNumeroDePreempcoesCT1()));
		/*this.numeroDePreempcoesCT2New.setText(valueOf(descNew.getNumeroDePreempcoesCT2()));
		
		this.numeroDeDevolucoesCT0New.setText(valueOf(descNew.getNumeroDeDevolucoesCT0()));*/
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