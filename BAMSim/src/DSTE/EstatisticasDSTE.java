package DSTE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javafx.scene.shape.Line;

import javax.imageio.ImageIO;

import org.jrobin.core.FetchData;
import org.jrobin.core.FetchRequest;
import org.jrobin.core.RrdDb;
import org.jrobin.core.RrdDef;
import org.jrobin.core.RrdException;
import org.jrobin.core.Sample;
import org.jrobin.graph.RrdGraph;
import org.jrobin.graph.RrdGraphConstants;
import org.jrobin.graph.RrdGraphDef;

import BAM.BAMRecommender.BAMDescription;
import BAM.BAMRecommender.BAMTypes;
//import BAM.BAMRecommender.BAMDescription.Problemas;
import Simulador.ParametrosDoSimulador;
import jcolibri.cbrcore.CBRQuery;



public class EstatisticasDSTE {
		

	public int preempcoes = 0;
	public int devolucoes = 0;
	public int bloqueios = 0;
	public int lspRequested = 0;
	public int lspUnbroken = 0;
	public int lspEstablished = 0;
	public int lspEstablishedTotal = 0;
	public double bandaUnbroken = 0;
	public double bandaRequested = 0;
	public double bandaEstabelecida = 0;

	public int [] lspRequestedCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaRequestedCT = new double [ParametrosDSTE.MaxClassType];
	public int [] lspEstablishedCT = new int [ParametrosDSTE.MaxClassType];
	public int [] lspEstablishedTotalCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaEstabelecidaCT = new double [ParametrosDSTE.MaxClassType];
	public int [] lspUnbrokenCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaUnbrokenCT = new double [ParametrosDSTE.MaxClassType];

	
	
	public int [] preempcoesCT = new int [ParametrosDSTE.MaxClassType];
	public int [] devolucoesCT = new int [ParametrosDSTE.MaxClassType];
	public int [] bloqueiosCT = new int [ParametrosDSTE.MaxClassType];
	
	
	
	
	
	//auxiliares
	public int preempcoesAUX = 0;
	public int devolucoesAUX = 0;
	public int bloqueiosAUX = 0;
	public int lspRequestedAUX = 0;
	public int lspUnbrokenAUX = 0;
	public int lspEstablishedAUX = 0;
	public double bandaUnbrokenAUX = 0;
	public double bandaRequestedAUX = 0;

	public int [] preempcoesCTAUX = new int [ParametrosDSTE.MaxClassType];
	public int [] devolucoesCTAUX = new int [ParametrosDSTE.MaxClassType];
	public int [] bloqueiosCTAUX = new int [ParametrosDSTE.MaxClassType];
	
	public long starTime = ParametrosDSTE.RRDStarTime;///<<<<<<<<<<<<<<<<--------------
	public long tempoAcumuladoGrantDeny=0;
	public long tempoAcumuladoEstabelecimento=0;
	
	public long tempoSimulacaoInicio;
	public long tempoSimulacaoFim;
	
	
	public long curretTime;
	public String filename;
	public Color [] cores ={Color.BLUE,Color.CYAN,Color.GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.GREEN};


	public int graphWidthLine=6;
	public int graphWidth=((9*250)-236+24+31);
	public int graphHeight=((3*250)-242+33+15);
	public Font graphLargeFont=new Font("Arial", Font.BOLD, 70);
	public Font graphSmallFont=new Font("Arial", Font.BOLD, 40);
	public int graphMinorUnit=RrdGraphConstants.MINUTE;
	public	int graphMinorUnitCount= 5;
	public	int graphMajorUnit=RrdGraphConstants.HOUR;
	public	int graphMajorUnitCount=1;
	public	int graphLabelUnit=RrdGraphConstants.HOUR;
	public	int graphLabelUnitCount=3;  ///<<<<<<<<<<<<<<<<--------------
	public	int graphLabelSpan= 0;
	public	String graphSimpleDateFormat="HH:mm";
    
	public BasicStroke dotDashStroke = 
    	    new BasicStroke(graphWidthLine /*width*/,
    	            BasicStroke.CAP_BUTT /*end style*/,
    	            BasicStroke.JOIN_MITER /*join style*/,
    	            3.0f /*miter trim limit */,
    	            new float[] {3.0f} /* pattern array */,
    	            0.0f /* offset to start of pattern */);
    	
	public BasicStroke dotDashStroke2 = 
    		    new BasicStroke(graphWidthLine /*width*/,
    		            BasicStroke.CAP_BUTT /*end style*/,
    		            BasicStroke.JOIN_MITER /*join style*/,
    		            1.0f /*miter trim limit */,
    		            new float[] {5.0f, 3.0f, 1.0f, 3.0f } /* pattern array */,
    		            0.0f /* offset to start of pattern */);
	public BasicStroke dotDashStroke3 = 
    		    new BasicStroke(graphWidthLine /*width*/,
    		            BasicStroke.CAP_BUTT /*end style*/,
    		            BasicStroke.JOIN_MITER /*join style*/,
    		            1.0f /*miter trim limit */,
    		            new float[] {5.0f} /* pattern array */,
    		            0.0f /* offset to start of pattern */);
    	
    	
	public BasicStroke dashStroke = 		    new BasicStroke(graphWidthLine);
    	
	public BasicStroke[] dashStrokeList = {dotDashStroke,dotDashStroke2,dotDashStroke3};

	
	
	public EstatisticasDSTE(String filename)
	{
		
		try{
			//Criar base padrão
			this.filename=filename;
			curretTime=starTime;
			RrdDef rrdDef;
		    File outputfile = new File("saida/"+filename+"/"+filename+".rrd");
		    outputfile.getParentFile().mkdirs();
		    
			rrdDef = new RrdDef(outputfile.getPath());
	
			rrdDef.setStep(ParametrosDSTE.RRDBatida);
			//Inicia um pouco antes para iniciar a base com zeros
			rrdDef.setStartTime(starTime-ParametrosDSTE.RRDBatida*2);
			rrdDef.addDatasource("preempcao", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax );
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("preempcao_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("bloqueio", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("bloqueio_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("devolucao", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("devolucao_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspRequested_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspUnbroken_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspEstablished", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspEstablished_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspEstabTotal", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspEstabTotal_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("bandaUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			
			rrdDef.addArchive("MAX", ParametrosDSTE.RRDXff, ParametrosDSTE.RRDSteps, ParametrosDSTE.RRDLinhas);
			RrdDb rrdDb = new RrdDb(rrdDef);
			rrdDb.close();
			

			//Criar base com valores absolutos da amostra
			this.filename=filename;
			curretTime=starTime;
		    outputfile = new File("saida/"+filename+"/"+filename+"_absoluto.rrd");
		    outputfile.getParentFile().mkdirs();
		    
			rrdDef = new RrdDef(outputfile.getPath());
	
			rrdDef.setStep(ParametrosDSTE.RRDBatida);
			//Inicia um pouco antes para iniciar a base com zeros
			rrdDef.setStartTime(starTime-ParametrosDSTE.RRDBatida*2);
			rrdDef.addDatasource("preempcao", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax );
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("preempcao_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("bloqueio", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("bloqueio_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("devolucao", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("devolucao_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspRequested_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspUnbroken_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspEstablished", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspEstablished_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("lspEstabTotal", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				rrdDef.addDatasource("lspEstabTotal_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			rrdDef.addDatasource("bandaUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			
			rrdDef.addArchive("SUM", ParametrosDSTE.RRDXff, ParametrosDSTE.RRDSteps, ParametrosDSTE.RRDLinhas);
			rrdDb = new RrdDb(rrdDef);
			rrdDb.close();
			
			//Insere valores zerados para iniciar corretamente as bases
			this.inserirDadosRRD(-ParametrosDSTE.RRDBatida);
			this.inserirDadosAbsolutoRRD(-ParametrosDSTE.RRDBatida);
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RrdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void iniciarRRDLinks(Topologia to) throws RrdException, IOException
	{ 

			//Criar base
			curretTime=starTime;
			RrdDef rrdDef;
			File outputfile = new File("saida/"+filename+"/"+filename+"_links.rrd");
			outputfile.getParentFile().mkdirs();
		    
			rrdDef = new RrdDef(outputfile.getPath());
		
	
			rrdDef.setStep(ParametrosDSTE.RRDBatida);
			rrdDef.setStartTime(starTime-2*ParametrosDSTE.RRDBatida);
			for(Link aux : to.link)
			{
				rrdDef.addDatasource(aux.ID+"_"+"preempcao", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax );
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"preempcao_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"bloqueio", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"bloqueio_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"devolucao", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"devolucao_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"lspRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"lspRequested_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"lspUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"lspUnbroken_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"lspEstab", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"lspEstab_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"lspEstabTotal", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"lspEstabTotal_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"bandaUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				rrdDef.addDatasource(aux.ID+"_"+"bandaRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_"+"CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_"+"total", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			

			rrdDef.addArchive("LAST", ParametrosDSTE.RRDXff, ParametrosDSTE.RRDSteps, ParametrosDSTE.RRDLinhas);
			rrdDef.addArchive("MAX", ParametrosDSTE.RRDXff, ParametrosDSTE.RRDSteps, ParametrosDSTE.RRDLinhas);
			RrdDb rrdDb = new RrdDb(rrdDef);
			rrdDb.close();
			this.statusLinks(to, -ParametrosDSTE.RRDBatida);
	}
	public void inserirDadosRRD(long time) throws IOException, RrdException
	{
		//Inserir dados
		if (starTime+time>curretTime)
			curretTime=(long) (starTime+time);
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		Sample sample = rrdDb.createSample();
		sample.setAndUpdate(starTime+time+":"+preempcoes+":"+preempcoesCT[0]+":"+preempcoesCT[1]+":"+preempcoesCT[2]+":"+bloqueios+":"+bloqueiosCT[0]+":"+bloqueiosCT[1]+":"+bloqueiosCT[2]+":"+devolucoes+":"+devolucoesCT[0]+":"+devolucoesCT[1]+":"+devolucoesCT[2]+":"+lspRequested+":"+lspRequestedCT[0]+":"+lspRequestedCT[1]+":"+lspRequestedCT[2]+":"+lspUnbroken+":"+lspUnbrokenCT[0]+":"+lspUnbrokenCT[1]+":"+lspUnbrokenCT[2]+":"+lspEstablished+":"+lspEstablishedCT[0]+":"+lspEstablishedCT[1]+":"+lspEstablishedCT[2]+":"+lspEstablishedTotal+":"+lspEstablishedTotalCT[0]+":"+lspEstablishedTotalCT[1]+":"+lspEstablishedTotalCT[2]+":"+bandaUnbroken+":"+bandaRequested);

		rrdDb.close();

	}
	public  void inserirDadosAbsolutoRRD(long time) throws IOException, RrdException
	{
		int preempcoes = this.preempcoes-this.preempcoesAUX;
		int devolucoes = this.devolucoes-this.devolucoesAUX;
		int bloqueios = this.bloqueios-this.bloqueiosAUX;
		int lspRequested = this.lspRequested-this.lspRequestedAUX;
		int lspUnbroken = this.lspUnbroken-this.lspUnbrokenAUX;
		int lspEstablished = this.lspEstablished-this.lspEstablishedAUX;
		double bandaUnbroken = this.bandaUnbroken-this.bandaUnbrokenAUX;
		double bandaRequested = this.bandaRequested-this.bandaRequestedAUX;

		int [] preempcoesCT = new int [ParametrosDSTE.MaxClassType];
		int [] devolucoesCT = new int [ParametrosDSTE.MaxClassType];
		int [] bloqueiosCT = new int [ParametrosDSTE.MaxClassType];
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			preempcoesCT[i]=this.preempcoesCT[i]-this.preempcoesCTAUX[i];
			devolucoesCT[i]=this.devolucoesCT[i]-this.devolucoesCTAUX[i];
			bloqueiosCT[i]=this.bloqueiosCT[i]-this.bloqueiosCTAUX[i];
		}
		
		
		//Inserir dados
		if (starTime+time>curretTime)
			curretTime=(long) (starTime+time);
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
		Sample sample = rrdDb.createSample();
		sample.setAndUpdate(starTime+time+":"+preempcoes+":"+preempcoesCT[0]+":"+preempcoesCT[1]+":"+preempcoesCT[2]+":"+bloqueios+":"+bloqueiosCT[0]+":"+bloqueiosCT[1]+":"+bloqueiosCT[2]+":"+devolucoes+":"+devolucoesCT[0]+":"+devolucoesCT[1]+":"+devolucoesCT[2]+":"+lspRequested+":"+lspRequestedCT[0]+":"+lspRequestedCT[1]+":"+lspRequestedCT[2]+":"+lspUnbroken+":"+lspUnbrokenCT[0]+":"+lspUnbrokenCT[1]+":"+lspUnbrokenCT[2]+":"+lspEstablished+":"+lspEstablishedCT[0]+":"+lspEstablishedCT[1]+":"+lspEstablishedCT[2]+":"+bandaUnbroken+":"+bandaRequested);

		rrdDb.close();
		
		this.preempcoesAUX=this.preempcoes;
		this.devolucoesAUX=this.devolucoes;
		this.bloqueiosAUX=this.bloqueios;
		this.lspRequestedAUX=this.lspRequested;
		this.lspUnbrokenAUX=this.lspUnbroken;
		this.lspEstablishedAUX=this.lspEstablished;
		this.bandaUnbrokenAUX=this.bandaUnbroken;
		this.bandaRequestedAUX=this.bandaRequested;

		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			this.preempcoesCTAUX[i]=this.preempcoesCT[i];
			this.devolucoesCTAUX[i]=this.devolucoesCT[i];
			this.bloqueiosCTAUX[i]=this.bloqueiosCT[i];
		}

	}
	public double picoDeUtilizacaoDoEnlace(long time, Link link) throws IOException, RrdException
	{
		
		//Aponta para o arquivo da base
		//graphDef.datasource(link.ID+"_CT"+i, "saida/"+filename+"/"+filename+"_links.rrd",link.ID+"_CT"+i, "LAST");
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		double utilizacao= fetchData.getAggregate(link.ID+"_total", "MAX");
		
		rrdDb.close();
		return utilizacao;
	}
	public double picoDeUtilizacaoDoEnlaceCT(long time, Link link, int ct) throws IOException, RrdException
	{
		
		//Aponta para o arquivo da base
		//graphDef.datasource(link.ID+"_CT"+i, "saida/"+filename+"/"+filename+"_links.rrd",link.ID+"_CT"+i, "LAST");
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		double utilizacao= fetchData.getAggregate(link.ID+"_CT"+ct, "MAX");
		
		rrdDb.close();
		return utilizacao;
	}
	public int preempcoes(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate("preempcao", "MAX")-fetchData.getAggregate("preempcao", "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int preempcoes(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		int prempcoes=(int) (fetchData.getAggregate(link.ID+"_"+"preempcao", "MAX")-fetchData.getAggregate(link.ID+"_"+"preempcao", "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int preempcoesAbsoluto(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("SUM", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate("preempcao", "SUM"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int preempcoesCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate("preempcao_CT"+ct, "MAX")-fetchData.getAggregate("preempcao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int preempcoesCT(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate(link.ID+"_"+"preempcao_CT"+ct, "MAX")-fetchData.getAggregate(link.ID+"_"+"preempcao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int lspRequested(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspRequested=(int) (fetchData.getAggregate("lspRequested", "MAX")-fetchData.getAggregate("lspRequested", "MIN"));
		
		rrdDb.close();
		return lspRequested;
	}
	public int lspRequested(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspRequested=(int) (fetchData.getAggregate(link.ID+"_"+"lspRequested", "MAX")-fetchData.getAggregate(link.ID+"_"+"lspRequested", "MIN"));
		
		rrdDb.close();
		return lspRequested;
	}
	
	public int lspRequestedCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspRequested=(int) (fetchData.getAggregate("lspRequested_CT"+ct, "MAX")-fetchData.getAggregate("lspRequested_CT"+ct, "MIN"));
		
		rrdDb.close();
		return lspRequested;
	}
	public int lspRequestedCT(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspRequested=(int) (fetchData.getAggregate(link.ID+"_"+"lspRequested_CT"+ct, "MAX")-fetchData.getAggregate(link.ID+"_"+"lspRequested_CT"+ct, "MIN"));
		
		rrdDb.close();
		return lspRequested;
	}
	
	public int lspEstablished(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstablished", "AVERAGE"));//////////////////////////////<<<<----- porque média??????????
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablished(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate(link.ID+"_"+"lspEstab", "AVERAGE"));//////////////////////////////<<<<----- porque média??????????
		
		rrdDb.close();
		return lspEstablished;
	}
	
	public int lspEstablishedCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstablished_CT"+ct, "AVERAGE")); 
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedCT(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate(link.ID+"_"+"lspEstab_CT"+ct, "AVERAGE")); 
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedAnterior(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstablished", "FIRST")); 
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedAnterior(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate(link.ID+"_"+"lspEstab", "FIRST")); 
		
		rrdDb.close();
		return lspEstablished;
	}
	
	public int lspEstablishedAnterior(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstablished_CT"+ct, "FIRST"));  
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedAnterior(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate(link.ID+"_"+"lspEstab_CT"+ct, "FIRST"));  
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedTotal(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstabTotal", "MAX")-fetchData.getAggregate("lspEstabTotal", "MIN"));
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedTotal(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate(link.ID+"_"+"lspEstabTotal", "MAX")-fetchData.getAggregate(link.ID+"_"+"lspEstabTotal", "MIN"));
		
		rrdDb.close();
		return lspEstablished;
	}
	public int lspEstablishedTotalCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstabTotal_CT"+ct, "MAX")-fetchData.getAggregate("lspEstabTotal_CT"+ct, "MIN"));
		
		rrdDb.close();
		return lspEstablished;
		
	}
	public int lspEstablishedTotalCT(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate(link.ID+"_"+"lspEstabTotal_CT"+ct, "MAX")-fetchData.getAggregate(link.ID+"_"+"lspEstabTotal_CT"+ct, "MIN"));
		
		rrdDb.close();
		return lspEstablished;
		
	}
	public int bloqueios(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int bloqueios=(int) (fetchData.getAggregate("bloqueio", "MAX")-fetchData.getAggregate("bloqueio", "MIN"));
		
		rrdDb.close();
		return bloqueios;
	}
	public int bloqueios(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int bloqueios=(int) (fetchData.getAggregate(link.ID+"_"+"bloqueio", "MAX")-fetchData.getAggregate(link.ID+"_"+"bloqueio", "MIN"));
		
		rrdDb.close();
		return bloqueios;
	}
	public int bloqueiosCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int bloqueios=(int) (fetchData.getAggregate("bloqueio_CT"+ct, "MAX")-fetchData.getAggregate("bloqueio_CT"+ct, "MIN"));
		
		rrdDb.close();
		return bloqueios;
	}
	public int bloqueiosCT(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int bloqueios=(int) (fetchData.getAggregate(link.ID+"_"+"bloqueio_CT"+ct, "MAX")-fetchData.getAggregate(link.ID+"_"+"bloqueio_CT"+ct, "MIN"));
		
		rrdDb.close();
		return bloqueios;
	}
	public int devolucoes(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate("devolucao", "MAX")-fetchData.getAggregate("devolucao", "MIN"));
		rrdDb.close();
		return devolucoes;
	}
	public int devolucoes(long time, Link link) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate(link.ID+"_"+"devolucao", "MAX")-fetchData.getAggregate(link.ID+"_"+"devolucao", "MIN"));
		rrdDb.close();
		return devolucoes;
	}
	public int devolucoesCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate("devolucao_CT"+ct, "MAX")-fetchData.getAggregate("devolucao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return devolucoes;
	}
	public int devolucoesCT(long time, Link link, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDBatida*ParametrosDSTE.RRDSteps,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate(link.ID+"_"+"devolucao_CT"+ct, "MAX")-fetchData.getAggregate(link.ID+"_"+"devolucao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return devolucoes;
	}
	public void gerarRRDPNGlspRequested() throws IOException, RrdException
	{

		//RrdDb rrdDb = new RrdDb(filename+"/"+filename+".rrd");
		/*FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,i);
		FetchData fetchData = fetchRequest.fetchData();
		fetchData.dump();*/
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		graphDef.setValueAxis(this.lspRequested>=50?this.lspRequested/50:1, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("LSPs Requested");
		graphDef.datasource("lspRequested", "saida/"+filename+"/"+filename+".rrd", "lspRequested", "MAX");
		graphDef.line("lspRequested", new Color(0xFF, 0, 0), "LSPs Requested", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);



		//Por tempo
        
        //Pega Máximo
  		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
  		FetchRequest fetchRequest = rrdDb.createFetchRequest("SUM", starTime,curretTime);
  		FetchData fetchData = fetchRequest.fetchData();
  		//Faz a subtração dos dois valores para pegar o valor na janela
  		int max=(int) (fetchData.getAggregate("lspRequested", "MAX")); 
  		rrdDb.close();       
        
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		//graphDef2.setStep(3600);
		graphDef2.setValueAxis(max>=50?max/50:1, 10);
		graphDef2.setTitle("LSPs Requested");
		graphDef2.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "SUM");
		
		graphDef2.area("lspRequested", Color.gray, "LSPs Requested");
		graphDef2.setWidth(this.graphWidth);
		graphDef2.setHeight(this.graphHeight);
		graphDef2.setLargeFont(this.graphLargeFont);
        graphDef2.setSmallFont(this.graphSmallFont);
        graphDef2.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_lsps_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_lsps.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);

	    
	}
	
	public void gerarRRDPNGlspUnbroken() throws IOException, RrdException
	{

		//RrdDb rrdDb = new RrdDb(filename+"/"+filename+".rrd");
		/*FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,i);
		FetchData fetchData = fetchRequest.fetchData();
		fetchData.dump();*/
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		graphDef.setValueAxis(this.lspRequested>=50?this.lspRequested/50:1, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("LSPs Unbroken");
		graphDef.datasource("lspUnbroken", "saida/"+filename+"/"+filename+".rrd", "lspUnbroken", "MAX");
		graphDef.line("lspUnbroken", new Color(0xFF, 0, 0), "LSPs Unbroken", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);



		//Por tempo
        //Pega Máximo
  		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
  		FetchRequest fetchRequest = rrdDb.createFetchRequest("SUM", starTime,curretTime);
  		FetchData fetchData = fetchRequest.fetchData();
  		//Faz a subtração dos dois valores para pegar o valor na janela
  		int max=(int) (fetchData.getAggregate("lspUnbroken", "MAX")); 
  		rrdDb.close();  
  		
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		graphDef2.setValueAxis(max>=50?max/50:1, 10);
		//graphDef.setMinValue(0);
		//graphDef2.setStep(3600);
		graphDef2.setTitle("LSPs Unbroken");
		graphDef2.datasource("lspUnbroken", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspUnbroken", "SUM");
		
		graphDef2.area("lspUnbroken", Color.gray, "LSPs Unbroken");
		graphDef2.setWidth(this.graphWidth);
		graphDef2.setHeight(this.graphHeight);
		graphDef2.setLargeFont(this.graphLargeFont);
        graphDef2.setSmallFont(this.graphSmallFont);
        graphDef2.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_lsps_unbroken_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_lsps_unbroken.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);

	    
	}
	
	public void gerarRRDPNGlspEstablished() throws IOException, RrdException
	{

		//RrdDb rrdDb = new RrdDb(filename+"/"+filename+".rrd");
		/*FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,i);
		FetchData fetchData = fetchRequest.fetchData();
		fetchData.dump();*/
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		graphDef.setValueAxis(this.lspRequested>=50?this.lspRequested/50:1, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("LSPs Established Total");
		graphDef.datasource("lspEstabTotal", "saida/"+filename+"/"+filename+".rrd", "lspEstabTotal", "MAX");
		graphDef.line("lspEstabTotal", new Color(0xFF, 0, 0), "LSPs Established Total", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);



		//Por tempo
        
        //Pega Máximo
  		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
  		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,curretTime);
  		FetchData fetchData = fetchRequest.fetchData();
  		//Faz a subtração dos dois valores para pegar o valor na janela
  		int max=(int) (fetchData.getAggregate("lspEstablished", "MAX")); 
  		rrdDb.close();  
  		
  		
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		graphDef2.setValueAxis(max>=50?max/50:1, 10);
		//graphDef.setMinValue(0);
		//graphDef2.setStep(3600);
		graphDef2.setTitle("LSPs Established");
		graphDef2.datasource("lspEstablished", "saida/"+filename+"/"+filename+".rrd", "lspEstablished", "MAX");
		
		graphDef2.area("lspEstablished", Color.gray, "LSPs Established");
		graphDef2.setWidth(this.graphWidth);
		graphDef2.setHeight(this.graphHeight);
		graphDef2.setLargeFont(this.graphLargeFont);
        graphDef2.setSmallFont(this.graphSmallFont);
        graphDef2.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
        
      //Percentual
		
  		RrdGraphDef graphDef3 = new RrdGraphDef();
  		graphDef3.setTimeSpan(starTime,curretTime);
  		graphDef3.setMaxValue(100);
  		graphDef3.setVerticalLabel("Percent");
  		//graphDef.setMinValue(0);
  		graphDef3.setTitle("LSPs Established x LSPs Requested (Percent)");
  		graphDef3.datasource("lspRequested", "saida/"+filename+"/"+filename+".rrd", "lspRequested", "MAX");
  		graphDef3.datasource("lspEstabTotal", "saida/"+filename+"/"+filename+".rrd", "lspEstabTotal", "MAX");
  		graphDef3.datasource("lspEstablisheds", "lspEstabTotal,lspRequested,/,100,*");
  		graphDef3.area("lspEstablisheds", Color.gray, "% Established");
  		graphDef3.setWidth(this.graphWidth);
  		graphDef3.setHeight(this.graphHeight);
  		graphDef3.setLargeFont(this.graphLargeFont);
          graphDef3.setSmallFont(this.graphSmallFont);
          graphDef3.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
          		this.graphMajorUnit, this.graphLabelUnitCount,
          		this.graphLabelUnit, this.graphLabelUnitCount,
          		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_lsps_established_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_lsps_established.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    
	  //Percentual
  		graph = new RrdGraph(graphDef3);
  		totalWidth=graph.getRrdGraphInfo().getWidth();
  	    totalHeight=graph.getRrdGraphInfo().getHeight();
  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
  	    gfx=img.getGraphics();
  	    graph.render(gfx);
  	    outputfile = new File("saida/"+filename+"/"+filename+"_lsps_established_percent.png");
  	    outputfile.mkdirs();
  	    ImageIO.write(img,"png",outputfile);

	    
	}

	public void gerarRRDPNGpreempcao() throws IOException, RrdException
	{

		
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		graphDef.setValueAxis(this.lspRequested>=50?this.lspRequested/50:1, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("Preemptions (Total)");
		graphDef.datasource("preempcao", "saida/"+filename+"/"+filename+".rrd", "preempcao", "MAX");
		graphDef.line("preempcao", new Color(0xFF, 0, 0), "Preemptions", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);


		//Por tempo
        //Pega Máximo
  		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
  		FetchRequest fetchRequest = rrdDb.createFetchRequest("SUM", starTime,curretTime);
  		FetchData fetchData = fetchRequest.fetchData();
  		//Faz a subtração dos dois valores para pegar o valor na janela
  		int max=(int) (fetchData.getAggregate("lspEstablished", "MAX")); 
  		
  		rrdDb.close();  
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef2.setValueAxis(max>=50?max/50:1, 10);
		graphDef2.setTitle("Preemptions x LSPs Established");
		graphDef2.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "SUM");
		graphDef2.datasource("lspEstablished", "saida/"+filename+"/"+filename+".rrd", "lspEstablished", "MAX");
		graphDef2.area("lspEstablished", Color.GREEN, "LSPs Established");
		graphDef2.area("preempcao", Color.RED, "Preemptions");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("preempcao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao_CT"+i, "SUM");
			graphDef2.line("preempcao_CT"+i, cores[i], "Preemptions in TC"+i, graphWidthLine);
			
		}
		graphDef2.setWidth(this.graphWidth);
		graphDef2.setHeight(this.graphHeight);
		graphDef2.setLargeFont(this.graphLargeFont);
        graphDef2.setSmallFont(this.graphSmallFont);
        graphDef2.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		//Percentual
		
		RrdGraphDef graphDef3 = new RrdGraphDef();
		graphDef3.setTimeSpan(starTime,curretTime);
		graphDef3.setMaxValue(100);
		graphDef3.setVerticalLabel("Percent");
		//graphDef.setMinValue(0);
		graphDef3.setTitle("Preemptions X LSPs Established (Percent)");

		graphDef3.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "SUM");
		graphDef3.datasource("lspEstablished", "saida/"+filename+"/"+filename+".rrd", "lspEstablished", "MAX");
		graphDef3.datasource("prempcoes", "preempcao,lspEstablished,/,100,*");
		graphDef3.area("prempcoes", Color.gray, "% Preemptions");
		
		graphDef3.setWidth(this.graphWidth);
		graphDef3.setHeight(this.graphHeight);
		graphDef3.setLargeFont(this.graphLargeFont);
        graphDef3.setSmallFont(this.graphSmallFont);
        graphDef3.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_prempt_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_prempt.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    
	    //Portempo
  		graph = new RrdGraph(graphDef3);
  		totalWidth=graph.getRrdGraphInfo().getWidth();
  	    totalHeight=graph.getRrdGraphInfo().getHeight();
  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
  	    gfx=img.getGraphics();
  	    graph.render(gfx);
  	    outputfile = new File("saida/"+filename+"/"+filename+"_prempt_percent.png");
  	    outputfile.mkdirs();
  	    ImageIO.write(img,"png",outputfile);


	    
	}
	
	public void gerarRRDPNGbloqueio() throws IOException, RrdException
	{

		//RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		/*FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,i);
		FetchData fetchData = fetchRequest.fetchData();
		fetchData.dump();*/
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		graphDef.setValueAxis(this.lspRequested>=50?this.lspRequested/50:1, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("Blocking (Total)");
		graphDef.datasource("bloqueio", "saida/"+filename+"/"+filename+".rrd", "bloqueio", "MAX");
		graphDef.line("bloqueio", new Color(0xFF, 0, 0), "Blocking", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);


		//Por tempo
        //Pega Máximo
  		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
  		FetchRequest fetchRequest = rrdDb.createFetchRequest("SUM", starTime,curretTime);
  		FetchData fetchData = fetchRequest.fetchData();
  		//Faz a subtração dos dois valores para pegar o valor na janela
  		int max=(int) (fetchData.getAggregate("lspRequested", "MAX")); 
  		rrdDb.close();  
  		
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		graphDef2.setValueAxis(max>=50?max/50:1, 10);
		
		//graphDef.setMinValue(0);
		graphDef2.setTitle("Blocking x LSPs Requested");
		graphDef2.datasource("bloqueio", "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio", "SUM");
		graphDef2.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "SUM");
		graphDef2.area("lspRequested", Color.GREEN, "LSPs Requested");
		graphDef2.area("bloqueio", Color.RED, "Blocking");
		
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("bloqueio_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio_CT"+i, "SUM");
			graphDef2.line("bloqueio_CT"+i, cores[i], "Blocking in TC"+i, graphWidthLine);
			
		}
		graphDef2.setWidth(this.graphWidth);
		graphDef2.setHeight(this.graphHeight);
		graphDef2.setLargeFont(this.graphLargeFont);
        graphDef2.setSmallFont(this.graphSmallFont);
        graphDef2.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		
		//Percentual
		
		RrdGraphDef graphDef3 = new RrdGraphDef();
		graphDef3.setTimeSpan(starTime,curretTime);
		graphDef3.setMaxValue(100);
		graphDef3.setVerticalLabel("Percent");
		//graphDef.setMinValue(0);
		graphDef3.setTitle("Blocking x LSPs Requested (Percent)");
		graphDef3.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "SUM");
		graphDef3.datasource("bloqueio", "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio", "SUM");
		graphDef3.datasource("bloqueios", "bloqueio,lspRequested,/,100,*");
		graphDef3.area("bloqueios", Color.gray, "% Blocking");
		graphDef3.setWidth(this.graphWidth);
		graphDef3.setHeight(this.graphHeight);
		graphDef3.setLargeFont(this.graphLargeFont);
        graphDef3.setSmallFont(this.graphSmallFont);
        graphDef3.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_bloqueio_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_bloqueio.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    
	    //Portempo
  		graph = new RrdGraph(graphDef3);
  		totalWidth=graph.getRrdGraphInfo().getWidth();
  	    totalHeight=graph.getRrdGraphInfo().getHeight();
  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
  	    gfx=img.getGraphics();
  	    graph.render(gfx);
  	    outputfile = new File("saida/"+filename+"/"+filename+"_bloqueio_percent.png");
  	    outputfile.mkdirs();
  	    ImageIO.write(img,"png",outputfile);

		  	    
		

	    
	}
	
	public void gerarRRDPNGdevolucao() throws IOException, RrdException
	{

		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		graphDef.setValueAxis(this.lspRequested>=50?this.lspRequested/50:1, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("Devolutions (Total)");
		graphDef.datasource("devolucao", "saida/"+filename+"/"+filename+".rrd", "devolucao", "MAX");
		graphDef.line("devolucao", new Color(0xFF, 0, 0), "Devolutions", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);

		//Por tempo
        //Pega Máximo
  		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
  		FetchRequest fetchRequest = rrdDb.createFetchRequest("SUM", starTime,curretTime);
  		FetchData fetchData = fetchRequest.fetchData();
  		//Faz a subtração dos dois valores para pegar o valor na janela
  		int max=(int) (fetchData.getAggregate("lspEstablished", "MAX")); 
  		rrdDb.close();  
  		
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef2.setValueAxis(max>=50?max/50:1, 10);
		graphDef2.setTitle("Devolutions x LSPs Established");
		graphDef2.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "SUM");
		graphDef2.datasource("lspEstablished", "saida/"+filename+"/"+filename+".rrd", "lspEstablished", "MAX");
		graphDef2.area("lspEstablished", Color.GREEN, "LSPs Established");
		graphDef2.area("devolucao", Color.RED, "Devolutions");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("devolucao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao_CT"+i, "SUM");
			graphDef2.line("devolucao_CT"+i, cores[i], "Devolutions in  TC"+i, graphWidthLine);
			
		}
		graphDef2.setWidth(this.graphWidth);
		graphDef2.setHeight(this.graphHeight);
		graphDef2.setLargeFont(this.graphLargeFont);
        graphDef2.setSmallFont(this.graphSmallFont);
        graphDef2.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		//Percentual
		
		RrdGraphDef graphDef3 = new RrdGraphDef();
		graphDef3.setTimeSpan(starTime,curretTime);
		graphDef3.setMaxValue(100);
		graphDef3.setVerticalLabel("Percent");
		//graphDef.setMinValue(0);
		graphDef3.setTitle("Devolutions x LSP Established (Percent)");
		graphDef3.datasource("lspEstablished", "saida/"+filename+"/"+filename+".rrd", "lspEstablished", "MAX");
		graphDef3.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "SUM");
		graphDef3.datasource("devolucoes", "devolucao,lspEstablished,/,100,*");
		graphDef3.area("devolucoes", Color.gray, "% Devolutions");
		graphDef3.setWidth(this.graphWidth);
		graphDef3.setHeight(this.graphHeight);
		graphDef3.setLargeFont(this.graphLargeFont);
        graphDef3.setSmallFont(this.graphSmallFont);
        graphDef3.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
      /*  
      //Janela
		
  		RrdGraphDef graphDef4 = new RrdGraphDef();
  		graphDef4.setTimeSpan(starTime,curretTime);
  		graphDef4.setMaxValue(100);
  		graphDef4.setVerticalLabel("Percent");
  		//graphDef4.setMinValue(0);
  		graphDef4.setTitle("Devolutions (Janela)");
  		graphDef4.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "TOTAL");
  		graphDef4.area("devolucao", Color.gray, "% Devolutions");
  		graphDef4.setWidth(this.graphWidth);
  		graphDef4.setHeight(this.graphHeight);
  		graphDef4.setLargeFont(this.graphLargeFont);
  		graphDef4.setSmallFont(this.graphSmallFont);
  		graphDef4.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
          		this.graphMajorUnit, this.graphLabelUnitCount,
          		this.graphLabelUnit, this.graphLabelUnitCount,
          		this.graphLabelSpan, this.graphSimpleDateFormat);
		*/
		
		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/"+filename+"_devolucao_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   
	    //Portempo
		graph = new RrdGraph(graphDef2);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_devolucao.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    
	    //Portempo
  		graph = new RrdGraph(graphDef3);
  		totalWidth=graph.getRrdGraphInfo().getWidth();
  	    totalHeight=graph.getRrdGraphInfo().getHeight();
  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
  	    gfx=img.getGraphics();
  	    graph.render(gfx);
  	    outputfile = new File("saida/"+filename+"/"+filename+"_devolucao_percent.png");
  	    outputfile.mkdirs();
  	    ImageIO.write(img,"png",outputfile);
  	    
  	    /*
  	    //Por Janela
		graph = new RrdGraph(graphDef4);
		totalWidth=graph.getRrdGraphInfo().getWidth();
	    totalHeight=graph.getRrdGraphInfo().getHeight();
	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    gfx=img.getGraphics();
	    graph.render(gfx);
	    outputfile = new File("saida/"+filename+"/"+filename+"_devolucao_janela.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);*/

  	    

	    
	}
	
	
	
	
	
	public void gerarRRDXML() throws IOException, RrdException 
	{
		File outputfile = new File("saida/"+filename+"/"+filename+"_rrd.xml");
		OutputStream outputStream = new FileOutputStream(outputfile);
	    outputfile.mkdirs();
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		rrdDb.dumpXml(outputStream);
	    rrdDb.close();
	    
	    outputfile = new File("saida/"+filename+"/"+filename+"_absoluto_rrd.xml");
		outputStream = new FileOutputStream(outputfile);
	    outputfile.mkdirs();
		rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_absoluto.rrd");
		rrdDb.dumpXml(outputStream);
	    rrdDb.close();
	}
	
	public void gerarLinksRRDXML() throws IOException, RrdException 
	{
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		rrdDb.dumpXml("saida/"+filename+"/"+filename+"_links_rrd.xml");
	    rrdDb.close();
	    
	}
	
	
	
	public void statusLinks(Topologia to, long time) throws IOException, RrdException
	{ 
		//Inserir dados
		if (starTime+time>curretTime)
			curretTime=(long) (starTime+time);
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+"_links.rrd");
		Sample sample = rrdDb.createSample();
		
		String retorno="";
		
		for(Link aux : to.link)
		{
			retorno+=":"+aux.preempcoes+":"+aux.preempcoesCT[0]+":"+aux.preempcoesCT[1]+":"+aux.preempcoesCT[2]+":"+aux.bloqueios+":"+aux.bloqueiosCT[0]+":"+aux.bloqueiosCT[1]+":"+aux.bloqueiosCT[2]+":"+aux.devolucoes+":"+aux.devolucoesCT[0]+":"+aux.devolucoesCT[1]+":"+aux.devolucoesCT[2]+":"+aux.lspRequested+":"+aux.lspRequestedCT[0]+":"+aux.lspRequestedCT[1]+":"+aux.lspRequestedCT[2]+":"+aux.lspUnbroken+":"+aux.lspUnbrokenCT[0]+":"+aux.lspUnbrokenCT[1]+":"+aux.lspUnbrokenCT[2]+":"+aux.lspEstablished+":"+aux.lspEstablishedCT[0]+":"+aux.lspEstablishedCT[1]+":"+aux.lspEstablishedCT[2]+":"+aux.lspEstablishedTotal+":"+aux.lspEstablishedTotalCT[0]+":"+aux.lspEstablishedTotalCT[1]+":"+aux.lspEstablishedTotalCT[2]+":"+aux.bandaUnbroken+":"+aux.bandaRequested;
			for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
			{
				retorno+=":"+aux.CargaCTAtual[i];
			}
			retorno+=":"+aux.getCargaEnlaceAtual();
		}
		sample.setAndUpdate(starTime+time+retorno);
		
		
		rrdDb.close();
		

	}
	public void gerarLinkRRDPNG(Topologia to) throws IOException, RrdException
	{
		for(Link aux : to.link)
		{
			gerarLinkRRDPNG(aux);
		}
	}
	public void gerarLinkRRDPNG(Link link) throws IOException, RrdException
	{

		//RrdDb rrdDb = new RrdDb(filename+"/"+filename+"_links.rrd");
		/*FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,i);
		FetchData fetchData = fetchRequest.fetchData();
		fetchData.dump();*/
		
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Mbps");
		graphDef.setMaxValue(link.CargaEnlace);
		graphDef.setTitle(link.Descricao);
		int i=0;
		for(i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef.datasource(link.ID+"_CT"+i, "saida/"+filename+"/"+filename+"_links.rrd",link.ID+"_CT"+i, "LAST");
		}
		graphDef.datasource(link.ID+"_total", "saida/"+filename+"/"+filename+"_links.rrd",link.ID+"_total", "LAST");
		
		for(i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef.line(link.ID+"_CT"+i, cores[i],"TC"+i, graphWidthLine,dashStrokeList[i]);
		}
		//graphDef.line(link.Descricao+"_total",cores[i],"Total",2);
		graphDef.line(link.ID+"_total",cores[i],"Total",graphWidthLine,dashStroke);
		
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);
		
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File("saida/"+filename+"/link_"+link.ID+".png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	   // rrdDb.close();

	}
	public String getEstatisticas()
	{
		String retorno="============================ Início dos Estatisticas DSTE ============================\r\n";
		
		retorno+=String.format("Simulation Time = %03d:%02d:%02d\r\n", (tempoSimulacaoFim-tempoSimulacaoInicio) / 3600000, ( (tempoSimulacaoFim-tempoSimulacaoInicio) / 60000 ) % 60 , ((tempoSimulacaoFim-tempoSimulacaoInicio) / 1000 ) % 60);
		retorno+=String.format("Simulation Time (ms) = %d\r\n", tempoSimulacaoFim-tempoSimulacaoInicio);
		retorno+=String.format("Total GRANT/DENY (ns) = %d\r\n", tempoAcumuladoGrantDeny);
		retorno+=String.format("Total GRANT/DENY (ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis(tempoAcumuladoGrantDeny));
		//lspRequestedCT(ParametrosDSTE.Janela, 0) > 0 ? valor verdadeiro : valor falso
		retorno+=String.format("Average GRANT/DENY (ns) = %d\r\n", lspRequested > 0 ?  tempoAcumuladoGrantDeny/lspRequested : 0);
		retorno+=String.format("Average GRANT/DENY (ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis( lspRequested > 0 ?  tempoAcumuladoGrantDeny/lspRequested : 0)   );
		retorno+=String.format("Establishment Total(ns) = %d\r\n", tempoAcumuladoEstabelecimento);
		retorno+=String.format("Establishment Total(ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis(tempoAcumuladoEstabelecimento));
		retorno+=String.format("Average of Establishment (ns) = %d\r\n", lspRequested > 0 ?  tempoAcumuladoEstabelecimento/lspRequested : 0);
		retorno+=String.format("Average of Establishment (ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis(lspRequested > 0 ?  tempoAcumuladoEstabelecimento/lspRequested : 0)    );
		retorno+=String.format("LSPs Requested = %d\r\n", lspRequested);
		retorno+=String.format("LSPs Established Total = %d\r\n", lspEstablishedTotal);
		retorno+=String.format("LSPs Unbroken = %d\r\n", lspUnbroken);
		retorno+=String.format("LSPs Preemptions = %d\r\n", preempcoes);
		retorno+=String.format("LSPs Preemptions / Established = %6.4f\r\n", lspEstablishedTotal > 0 ? ((double)preempcoes)/lspEstablishedTotal: 0 );
		retorno+=String.format("LSPs Devolutions = %d\r\n", devolucoes); 
		retorno+=String.format("LSPs Devolutions / Established = %6.4f\r\n", lspEstablishedTotal > 0 ? ((double)devolucoes)/lspEstablishedTotal: 0 );
		retorno+=String.format("LSPs Blocking = %d\r\n", bloqueios);
		retorno+=String.format("LSPs Blocking / Requested  = %6.4f\r\n", lspRequested > 0 ?  ((double)bloqueios) / lspRequested : 0);
		
		
		
		retorno+=String.format("Bandwidth Requested = %6.2f\r\n", bandaRequested);
		retorno+=String.format("Bandwidth Established = %6.2f\r\n", bandaEstabelecida);
		retorno+=String.format("Bandwidth Unbroken = %6.2f\r\n", bandaUnbroken);
		for(int i=0;i<bloqueiosCT.length;i++){ 
			retorno+="LSP Blocking TC["+i+"] = "+bloqueiosCT[i]+"\r\n"; 
		} 
		for(int i=0;i<preempcoesCT.length;i++){ 
			retorno+="LSP Preemptions TC["+i+"] = "+preempcoesCT[i]+"\r\n"; 
		}   
		for(int i=0;i<devolucoesCT.length;i++){ 
			retorno+="LSP Devolutions TC["+i+"] = "+devolucoesCT[i]+"\r\n"; 
		} 
		for(int i=0;i<lspRequestedCT.length;i++){ 
			retorno+="LSP Requested TC["+i+"] = "+lspRequestedCT[i]+"\r\n"; 
		} 
		
		for(int i=0;i<lspEstablishedCT.length;i++){ 
			retorno+="LSP EstablishedTC Total["+i+"] = "+lspEstablishedTotalCT[i]+"\r\n"; 
		} 
		for(int i=0;i<lspUnbrokenCT.length;i++){ 
			retorno+="LSP UnbrokenTC["+i+"] = "+lspUnbrokenCT[i]+"\r\n"; 
		} 
		for(int i=0;i<bandaRequestedCT.length;i++){ 
			retorno+="RequestedTC["+i+"] = "+bandaRequestedCT[i]+"\r\n"; 
		} 
		for(int i=0;i<bandaUnbrokenCT.length;i++){ 
			retorno+="Bandwidth Unbroken TC["+i+"] = "+bandaUnbrokenCT[i]+"\r\n"; 
		} 
		for(int i=0;i<bandaEstabelecidaCT.length;i++){ 
			retorno+="Bandwidth Established TC["+i+"] = "+bandaEstabelecidaCT[i]+"\r\n"; 
		} 

		
		retorno+="============================ Fim dos Estatísticas DSTE ============================";

		
		
		return retorno;
		
	}
	
	public CBRQuery getQuery(Link link /*, 
							String  gestor,
							double []SLAUtilizacaoCT,
							double []SLABloqueiosCT,
							double []SLAPreempcoesCT, 
							double []SLADevolucoesCT*/ )   
	{
		BAMDescription desc = new BAMDescription();
		
		
		try {
			
			//desc.setGestor(gestor);
			desc.setLink(link.ID);

			
			//Compatibilidade com G-BAM apenas refeltindo MAM, RDM e Alloc
			
			if(link.bamType!=BAMType.PreemptionGBAM)
			{
				desc.setBAMAtual(BAMTypes.valueOf(link.bamType.toString()));
			}else
			{
				//Se BCLTH diferente de 0 é pq reflete Alloc
				if (link.BCLTH[0]!=0)
					desc.setBAMAtual(BAMTypes.PreemptionAllocCTSharing);
				//Se BCLTH diferente é igual a 0 e BCHTL diferente de 0 é pq reflete RDM
				else if (link.BCHTL[2]!=0)
					desc.setBAMAtual(BAMTypes.PreemptionRDM);
				//Se BCLTH e BCHTL igual a 0 é pq reflete MAM
				else
					desc.setBAMAtual(BAMTypes.NoPreemptionMAM);
			}
			//desc.setProblema(Problemas.valueOf(problema.toString()));
			/*desc.setJanela(ParametrosDSTE.Janela);
			
			desc.setSLAUtilizacaoCT0(SLAUtilizacaoCT[0]);
			desc.setSLAUtilizacaoCT1(SLAUtilizacaoCT[1]);
			desc.setSLAUtilizacaoCT2(SLAUtilizacaoCT[2]);
								
			desc.setSLABloqueiosCT0(  SLABloqueiosCT[0]); 
			desc.setSLABloqueiosCT1(  SLABloqueiosCT[1]);
			desc.setSLABloqueiosCT2(  SLABloqueiosCT[2]);
			
			desc.setSLAPreempcoesCT0(  SLAPreempcoesCT[0]);
			desc.setSLAPreempcoesCT1(  SLAPreempcoesCT[1]);
			desc.setSLAPreempcoesCT2(  SLAPreempcoesCT[2]);
			
			desc.setSLADevolucoesCT0(  SLADevolucoesCT[0]);
			desc.setSLADevolucoesCT1(  SLADevolucoesCT[1]);
			desc.setSLADevolucoesCT2(  SLADevolucoesCT[2]);*/
			
						
			desc.setBC0(  (link.BC[0] * link.CargaEnlace) /100);
			desc.setBC1(  (link.BC[1] * link.CargaEnlace) /100);
			desc.setBC2(  (link.BC[2] * link.CargaEnlace) /100);
			
			desc.setUtilizacaoDoEnlace(this.picoDeUtilizacaoDoEnlace(ParametrosDSTE.Janela,link) /  link.CargaEnlace );
			
			desc.setUtilizacaoDoEnlaceCT0(this.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela,link,0) /  link.CargaEnlace );
			desc.setUtilizacaoDoEnlaceCT1(this.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela,link,1) /  link.CargaEnlace );
			desc.setUtilizacaoDoEnlaceCT2(this.picoDeUtilizacaoDoEnlaceCT(ParametrosDSTE.Janela,link,2) /  link.CargaEnlace );
			
			//Variáveis para evitar muitas consultas repetidas
			int lspRequested=lspRequested(ParametrosDSTE.Janela,link);
			int lspRequestedCT0=lspRequestedCT(ParametrosDSTE.Janela,link, 0);
			int lspRequestedCT1=lspRequestedCT(ParametrosDSTE.Janela,link, 1);
			int lspRequestedCT2=lspRequestedCT(ParametrosDSTE.Janela,link, 2);
			int lspEstablishedTotal = lspEstablishedTotal(ParametrosDSTE.Janela,link);
			int lspEstablishedTotalCT0=lspEstablishedTotalCT(ParametrosDSTE.Janela,link, 0);
			int lspEstablishedTotalCT1=lspEstablishedTotalCT(ParametrosDSTE.Janela,link, 1);
			int lspEstablishedTotalCT2=lspEstablishedTotalCT(ParametrosDSTE.Janela,link, 2);
			int lspEstablishedAnterior=lspEstablishedAnterior(ParametrosDSTE.Janela,link);
			int lspEstablishedAnteriorCT0=lspEstablishedAnterior(ParametrosDSTE.Janela,link, 0);
			int lspEstablishedAnteriorCT1=lspEstablishedAnterior(ParametrosDSTE.Janela,link, 1);
			int lspEstablishedAnteriorCT2=lspEstablishedAnterior(ParametrosDSTE.Janela,link, 2);
			
			// operador ternário de if - a = condicao ? 1 : 2			
			//lspRequestedCT(ParametrosDSTE.Janela, 0) > 0 ? valor verdadeiro : valor falso

			desc.setNumeroDeBloqueios(lspRequested > 0 ? (double)this.bloqueios(ParametrosDSTE.Janela,link)/lspRequested:0);
			
			desc.setNumeroDeBloqueiosCT0(lspRequestedCT0 > 0 ? (double)this.bloqueiosCT(ParametrosDSTE.Janela,link,0)/lspRequestedCT0:0);
			desc.setNumeroDeBloqueiosCT1(lspRequestedCT1 > 0 ? (double)this.bloqueiosCT(ParametrosDSTE.Janela,link,1)/lspRequestedCT1:0);
			desc.setNumeroDeBloqueiosCT2(lspRequestedCT2 > 0 ? (double)this.bloqueiosCT(ParametrosDSTE.Janela,link,2)/lspRequestedCT2:0);
			
			
			desc.setNumeroDePreempcoes((lspEstablishedTotal + lspEstablishedAnterior ) > 0 ? (double)preempcoes(ParametrosDSTE.Janela,link)/ (lspEstablishedTotal	+ lspEstablishedAnterior):0);  	
			
			desc.setNumeroDePreempcoesCT0((lspEstablishedTotalCT0 + lspEstablishedAnteriorCT0 ) > 0 ? (double)preempcoesCT(ParametrosDSTE.Janela,link,0)/ (lspEstablishedTotalCT0	+ lspEstablishedAnteriorCT0):0);  	
			desc.setNumeroDePreempcoesCT1((lspEstablishedTotalCT1 + lspEstablishedAnteriorCT1 ) > 0 ? (double)preempcoesCT(ParametrosDSTE.Janela,link,1)/ (lspEstablishedTotalCT1	+ lspEstablishedAnteriorCT1):0);  	
			desc.setNumeroDePreempcoesCT2((lspEstablishedTotalCT2 + lspEstablishedAnteriorCT2 ) > 0 ? (double)preempcoesCT(ParametrosDSTE.Janela,link,2)/ (lspEstablishedTotalCT2	+ lspEstablishedAnteriorCT2):0);  	
			
			
			desc.setNumeroDeDevolucoes((lspEstablishedTotal + lspEstablishedAnterior) > 0 ? (double)devolucoes(ParametrosDSTE.Janela,link)/(lspEstablishedTotal+ lspEstablishedAnterior):0);
			
			desc.setNumeroDeDevolucoesCT0((lspEstablishedTotalCT0 + lspEstablishedAnteriorCT0) > 0 ? (double)devolucoesCT(ParametrosDSTE.Janela,link,0)/(lspEstablishedTotalCT0+ lspEstablishedAnteriorCT0):0);
			desc.setNumeroDeDevolucoesCT1((lspEstablishedTotalCT1 + lspEstablishedAnteriorCT1) > 0 ? (double)devolucoesCT(ParametrosDSTE.Janela,link,1)/(lspEstablishedTotalCT1+ lspEstablishedAnteriorCT1):0);
			desc.setNumeroDeDevolucoesCT2((lspEstablishedTotalCT2 + lspEstablishedAnteriorCT2) > 0 ? (double)devolucoesCT(ParametrosDSTE.Janela,link,2)/(lspEstablishedTotalCT2+ lspEstablishedAnteriorCT2):0);

				

			/*//BancoDeDados.setXML("UCT0" + "\t" + "UCT1" + "\t" + "UCT2" + "\t" + "BCT0" + "\t" + "BCT1" + "\t" + "BCT2" + "\t" + "PCT0" + "\t" + "PCT1" + "\t" + "PCT2"+ "\t" + "DCT0" + "\t" + "DCT1" + "\t" + "DCT2", "Gerar_base");
			
			BancoDeDados.setXML(
								desc.getUtilizacaoDoEnlaceCT0().toString() + "\t" + desc.getUtilizacaoDoEnlaceCT1().toString() + "\t" + desc.getUtilizacaoDoEnlaceCT2()            + "\t"
							 +  desc.getNumeroDeBloqueiosCT0().toString()  + "\t" + desc.getNumeroDeBloqueiosCT1().toString()  + "\t" + desc.getNumeroDeBloqueiosCT2().toString()  + "\t"
							 +  desc.getNumeroDePreempcoesCT0().toString() + "\t" + desc.getNumeroDePreempcoesCT1().toString() + "\t" + desc.getNumeroDePreempcoesCT2().toString() + "\t" 
							 +  desc.getNumeroDeDevolucoesCT0().toString() + "\t" + desc.getNumeroDeDevolucoesCT1().toString() + "\t" + desc.getNumeroDeDevolucoesCT2().toString() + "\t" 
							 , "Gerar_base");
		*/
		CBRQuery query = new CBRQuery();
		query.setDescription(desc);
		
		
		
		
		/*Validação comparativa das estatísticas da rede com um enlace vesus estatíticas do enlace
		String aux = "";
		aux += String.valueOf(lspRequestedCT(ParametrosDSTE.Janela, 0))+"\r\n"; 
		aux += String.valueOf(lspRequestedCT(ParametrosDSTE.Janela,link, 0))+"\r\n"; 
		aux += String.valueOf(lspRequestedCT(ParametrosDSTE.Janela, 1))+"\r\n"; 
		aux += String.valueOf(lspRequestedCT(ParametrosDSTE.Janela,link, 1))+"\r\n";
		aux += String.valueOf(lspRequestedCT(ParametrosDSTE.Janela, 2))+"\r\n";
		aux += String.valueOf(lspRequestedCT(ParametrosDSTE.Janela,link, 2))+"\r\n"; 
		aux +=this.bloqueiosCT(ParametrosDSTE.Janela,0)+"\r\n"; 
		aux +=this.bloqueiosCT(ParametrosDSTE.Janela,link,0)+"\r\n"; 
		aux +=this.bloqueiosCT(ParametrosDSTE.Janela,1)+"\r\n"; 
		aux +=this.bloqueiosCT(ParametrosDSTE.Janela,link,1)+"\r\n"; 
		aux +=this.bloqueiosCT(ParametrosDSTE.Janela,2)+"\r\n"; 
		aux +=this.bloqueiosCT(ParametrosDSTE.Janela,link,2)+"\r\n"; 
		aux += lspEstablishedTotalCT(ParametrosDSTE.Janela, 0)+"\r\n"; 
		aux += lspEstablishedTotalCT(ParametrosDSTE.Janela,link, 0)+"\r\n"; 
		aux += lspEstablishedTotalCT(ParametrosDSTE.Janela, 1)+"\r\n"; 
		aux += lspEstablishedTotalCT(ParametrosDSTE.Janela,link, 1)+"\r\n"; 
		aux += lspEstablishedTotalCT(ParametrosDSTE.Janela, 2)+"\r\n"; 
		aux += lspEstablishedTotalCT(ParametrosDSTE.Janela,link, 2)+"\r\n"; 
		aux += lspEstablishedAnterior(ParametrosDSTE.Janela, 0)+"\r\n"; 
		aux += lspEstablishedAnterior(ParametrosDSTE.Janela,link, 0)+"\r\n"; 
		aux += lspEstablishedAnterior(ParametrosDSTE.Janela, 1)+"\r\n"; 
		aux += lspEstablishedAnterior(ParametrosDSTE.Janela,link, 1)+"\r\n"; 
		aux += lspEstablishedAnterior(ParametrosDSTE.Janela, 2)+"\r\n"; 
		aux += lspEstablishedAnterior(ParametrosDSTE.Janela,link, 2)+"\r\n"; 
		aux += preempcoesCT(ParametrosDSTE.Janela,0)+"\r\n"; 
		aux += preempcoesCT(ParametrosDSTE.Janela,link,0)+"\r\n"; 
		aux += preempcoesCT(ParametrosDSTE.Janela,1)+"\r\n"; 
		aux += preempcoesCT(ParametrosDSTE.Janela,link,1)+"\r\n"; 
		aux += preempcoesCT(ParametrosDSTE.Janela,2)+"\r\n"; 
		aux += preempcoesCT(ParametrosDSTE.Janela,link,2)+"\r\n"; 
		aux += devolucoesCT(ParametrosDSTE.Janela,0)+"\r\n"; 
		aux += devolucoesCT(ParametrosDSTE.Janela,link,0)+"\r\n"; 
		aux += devolucoesCT(ParametrosDSTE.Janela,1)+"\r\n"; 
		aux += devolucoesCT(ParametrosDSTE.Janela,link,1)+"\r\n"; 
		aux += devolucoesCT(ParametrosDSTE.Janela,2)+"\r\n"; 
		aux += devolucoesCT(ParametrosDSTE.Janela,link,2)+"\r\n"; 
		
		aux += "=====================================================\r\n";
		File outputfile = new File("saida/teste/teste.txt");
		  outputfile.getParentFile().mkdirs();
		  
		  BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile,true));
		  writer.write(aux);
		  writer.newLine();
		  writer.close();  
		  */
		return query;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (RrdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
}