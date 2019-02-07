package Main.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMSolution;
import jcolibri.cbrcore.CBRCase;
import javax.swing.JComboBox;

public class Recomendacao extends JDialog {
	JLabel  utilizacaoDoEnlace;
	JLabel  numeroDeBloqueios;
	JLabel  numeroDePreempcoes;
	JLabel  numeroDeDevolucoes;
	
	JLabel  utilizacaoDoEnlaceNew;
	JLabel  numeroDeBloqueiosNew;
	JLabel  numeroDePreempcoesNew;
	JLabel  numeroDeDevolucoesNew;
	
	JLabel  bamAtual;
	JLabel  bamSugerido;
	
	String[] opcoes = {"NoPreemptionMAM", "PreemptionRDM", "PreemptionAllocCTSharing"};

	private final JPanel contentPanel = new JPanel();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Recomendacao dialog = new Recomendacao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Recomendacao() {
		this.setTitle("Informações");
		setBounds(100, 100, 850, 350);
		//contentPanel.setLayout(new FlowLayout());
		contentPanel.setLayout(new GridLayout(0,3));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		

		JLabel label;
		contentPanel.add(label = new JLabel("Description"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		contentPanel.add(label = new JLabel("Estado da Rede"));
		contentPanel.add(label = new JLabel("Sugestão"));

		
		contentPanel.add(new JLabel("Utilização do Enlace"));
		contentPanel.add(this.utilizacaoDoEnlace= new JLabel());
		contentPanel.add(this.utilizacaoDoEnlaceNew= new JLabel());
		
		
		contentPanel.add(new JLabel("Number de Bloqueios"));
		contentPanel.add(this.numeroDeBloqueios= new JLabel());
		contentPanel.add(this.numeroDeBloqueiosNew= new JLabel());
		
				
		contentPanel.add(new JLabel("Number de Preempções"));
		contentPanel.add(this.numeroDePreempcoes= new JLabel());
		contentPanel.add(this.numeroDePreempcoesNew= new JLabel());
		
				
		contentPanel.add(new JLabel("Number de Devoluções"));
		contentPanel.add(this.numeroDeDevolucoes= new JLabel());
		contentPanel.add(this.numeroDeDevolucoesNew= new JLabel());
		
		contentPanel.add(new JLabel("Modelo"));
		contentPanel.add(this.bamAtual= new JLabel());
		contentPanel.add(this.bamSugerido= new JLabel());
		
		
		

		//this.teste();
	}
	
	public void showCase(CBRCase estado, CBRCase sugestao)
	{

		
		
		BAMDescription desc = (BAMDescription) estado.getDescription();
		this.utilizacaoDoEnlace.setText(valueOf(desc.getUtilizacaoDoEnlace()));
		this.numeroDeBloqueios.setText(valueOf(desc.getNumeroDeBloqueios()));
		this.numeroDePreempcoes.setText(valueOf(desc.getNumeroDePreempcoes()));
		this.numeroDeDevolucoes.setText(valueOf(desc.getNumeroDeDevolucoes()));
		this.bamAtual.setText(valueOf(desc.getBAMAtual().toString()));
		
		if(sugestao!=null)
		{
			BAMDescription descNew =(BAMDescription) sugestao.getDescription();
			this.utilizacaoDoEnlaceNew.setText(valueOf(descNew.getUtilizacaoDoEnlace()));
			this.numeroDeBloqueiosNew.setText(valueOf(descNew.getNumeroDeBloqueios()));
			this.numeroDePreempcoesNew.setText(valueOf(descNew.getNumeroDePreempcoes()));
			this.numeroDeDevolucoesNew.setText(valueOf(descNew.getNumeroDeDevolucoes()));
			this.bamSugerido.setText(valueOf(((BAMSolution) sugestao.getSolution()).getBAMNovo().name()));
			//((BAMSolution) sugestao.getSolution()).getBAMNovo().name()
		}
		


		
	}
	
	public static String valueOf(Object obj) {
	    return (obj == null) ? "null" : obj.toString();
	}
	
	public void teste()
	{

		
		

	
		
		this.utilizacaoDoEnlace.setText("teste");
		this.numeroDeBloqueios.setText("teste");
		this.numeroDePreempcoes.setText("teste");
		this.numeroDeDevolucoes.setText("teste");
		
		
		this.utilizacaoDoEnlaceNew.setText("teste");
		this.numeroDeBloqueiosNew.setText("teste");
		this.numeroDePreempcoesNew.setText("teste");
		this.numeroDeDevolucoesNew.setText("teste");
		
		this.bamAtual.setText("teste");
		this.bamSugerido.setText("teste");
		
	}

}
