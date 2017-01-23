package DSTE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
import BAM.BAMRecommender.BAMDescription.BAMTypes;
import BAM.BAMRecommender.BAMDescription.Problemas;
import Simulador.ParametrosDoSimulador;
import jcolibri.cbrcore.CBRQuery;



public class EstatisticasDSTE {
		

	public int preempcoes = 0;
	public int devolucoes = 0;
	public int bloqueios = 0;
	public int lspRequested = 0;
	public int lspUnbroken = 0;
	public int lspEstablished = 0;
	public double bandaUnbroken = 0;
	public double bandaRequested = 0;
	public double bandaEstabelecida = 0;

	public int [] lspRequestedCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaRequestedCT = new double [ParametrosDSTE.MaxClassType];
	public int [] lspEstablishedCT = new int [ParametrosDSTE.MaxClassType];
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
	
	public long starTime = ParametrosDSTE.RRDStarTime;
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
	public	int graphLabelUnitCount=1;
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
	
			rrdDef.setStep(ParametrosDSTE.RRDAmostra);
			rrdDef.setStartTime(starTime);
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
			rrdDef.addDatasource("lspUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("lspEstablished", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
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
	
			rrdDef.setStep(ParametrosDSTE.RRDAmostra);
			rrdDef.setStartTime(starTime);
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
			rrdDef.addDatasource("lspUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("lspEstablished", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaUnbroken", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaRequested", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			
			rrdDef.addArchive("LAST", ParametrosDSTE.RRDXff, ParametrosDSTE.RRDSteps, ParametrosDSTE.RRDLinhas);
			rrdDb = new RrdDb(rrdDef);
			rrdDb.close();
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
		
	
			rrdDef.setStep(ParametrosDSTE.RRDAmostra);
			rrdDef.setStartTime(starTime);
			for(Link aux : to.link)
			{
				for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
				{
					rrdDef.addDatasource(aux.ID+"_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.ID+"_total", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			}
			

			rrdDef.addArchive("LAST", ParametrosDSTE.RRDXff, ParametrosDSTE.RRDSteps, ParametrosDSTE.RRDLinhas);
			RrdDb rrdDb = new RrdDb(rrdDef);
			rrdDb.close();

	}
	public void inserirDadosRRD(long time) throws IOException, RrdException
	{
		//Inserir dados
		if (starTime+time>curretTime)
			curretTime=(long) (starTime+time);
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		Sample sample = rrdDb.createSample();
		sample.setAndUpdate(starTime+time+":"+preempcoes+":"+preempcoesCT[0]+":"+preempcoesCT[1]+":"+preempcoesCT[2]+":"+bloqueios+":"+bloqueiosCT[0]+":"+bloqueiosCT[1]+":"+bloqueiosCT[2]+":"+devolucoes+":"+devolucoesCT[0]+":"+devolucoesCT[1]+":"+devolucoesCT[2]+":"+lspRequested+":"+lspUnbroken+":"+lspEstablished+":"+bandaUnbroken+":"+bandaRequested);

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
		sample.setAndUpdate(starTime+time+":"+preempcoes+":"+preempcoesCT[0]+":"+preempcoesCT[1]+":"+preempcoesCT[2]+":"+bloqueios+":"+bloqueiosCT[0]+":"+bloqueiosCT[1]+":"+bloqueiosCT[2]+":"+devolucoes+":"+devolucoesCT[0]+":"+devolucoesCT[1]+":"+devolucoesCT[2]+":"+lspRequested+":"+lspUnbroken+":"+lspEstablished+":"+bandaUnbroken+":"+bandaRequested);

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
	public int preempcoes(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate("preempcao", "MAX")-fetchData.getAggregate("preempcao", "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int preempcoesCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate("preempcao_CT"+ct, "MAX")-fetchData.getAggregate("preempcao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int lspRequested(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspRequested=(int) (fetchData.getAggregate("lspRequested", "MAX")-fetchData.getAggregate("lspRequested", "MIN"));
		
		rrdDb.close();
		return lspRequested;
	}
	
	public int lspEstablished(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int lspEstablished=(int) (fetchData.getAggregate("lspEstablished", "MAX")-fetchData.getAggregate("lspEstablished", "MIN"));
		
		rrdDb.close();
		return lspEstablished;
	}
	public int bloqueios(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int bloqueios=(int) (fetchData.getAggregate("bloqueio", "MAX")-fetchData.getAggregate("bloqueio", "MIN"));
		
		rrdDb.close();
		return bloqueios;
	}
	public int bloqueiosCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int bloqueios=(int) (fetchData.getAggregate("bloqueio_CT"+ct, "MAX")-fetchData.getAggregate("bloqueio_CT"+ct, "MIN"));
		
		rrdDb.close();
		return bloqueios;
	}
	public int devolucoes(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate("devolucao", "MAX")-fetchData.getAggregate("devolucao", "MIN"));
		rrdDb.close();
		return devolucoes;
	}
	public int devolucoesCT(long time, int ct) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtração dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate("devolucao_CT"+ct, "MAX")-fetchData.getAggregate("devolucao_CT"+ct, "MIN"));
		
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
		graphDef.setValueAxis(this.lspRequested/50, 10);
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
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		//graphDef2.setStep(3600);
		graphDef2.setTitle("LSPs Requested");
		graphDef2.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "LAST");
		
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
		graphDef.setValueAxis(this.lspUnbroken/50, 10);
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
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		//graphDef2.setStep(3600);
		graphDef2.setTitle("LSPs Unbroken");
		graphDef2.datasource("lspUnbroken", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspUnbroken", "LAST");
		
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
		graphDef.setValueAxis(this.lspEstablished/50, 10);
		graphDef.setMinValue(0);
		graphDef.setTitle("LSPs Established");
		graphDef.datasource("lspEstablished", "saida/"+filename+"/"+filename+".rrd", "lspEstablished", "MAX");
		graphDef.line("lspEstablished", new Color(0xFF, 0, 0), "LSPs Established", graphWidthLine);
		graphDef.setWidth(this.graphWidth);
		graphDef.setHeight(this.graphHeight);
		graphDef.setLargeFont(this.graphLargeFont);
        graphDef.setSmallFont(this.graphSmallFont);
        graphDef.setTimeAxis(this.graphMinorUnit, this.graphMinorUnitCount,
        		this.graphMajorUnit, this.graphLabelUnitCount,
        		this.graphLabelUnit, this.graphLabelUnitCount,
        		this.graphLabelSpan, this.graphSimpleDateFormat);



		//Por tempo
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		//graphDef2.setStep(3600);
		graphDef2.setTitle("LSPs Established");
		graphDef2.datasource("lspEstablished", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspEstablished", "LAST");
		
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
  		graphDef3.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "LAST");
  		graphDef3.datasource("lspEstablished", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspEstablished", "LAST");
  		graphDef3.datasource("lspEstablisheds", "lspEstablished,lspRequested,/,100,*");
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
		graphDef.setValueAxis(this.preempcoes/50, 10);
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
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef2.setTitle("Preemptions x LSPs Established");
		graphDef2.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "LAST");
		graphDef2.datasource("lspEstablished", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspEstablished", "LAST");
		graphDef2.area("lspEstablished", Color.GREEN, "LSPs Established");
		graphDef2.area("preempcao", Color.RED, "Preemptions");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("preempcao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao_CT"+i, "LAST");
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

		graphDef3.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "LAST");
		graphDef3.datasource("lspEstablished", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspEstablished", "LAST");
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
		graphDef.setValueAxis(this.bloqueios/50, 10);
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
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef2.setTitle("Blocking x LSPs Requested");
		graphDef2.datasource("bloqueio", "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio", "LAST");
		graphDef2.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "LAST");
		graphDef2.area("lspRequested", Color.GREEN, "LSPs Requested");
		graphDef2.area("bloqueio", Color.RED, "Blocking");
		
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("bloqueio_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio_CT"+i, "LAST");
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
		graphDef3.datasource("lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "LAST");
		graphDef3.datasource("bloqueio", "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio", "LAST");
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
		graphDef.setValueAxis(((double)this.devolucoes)/50, 10);
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
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(starTime,curretTime);
		graphDef2.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef2.setTitle("Devolutions x LSPs Established");
		graphDef2.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "LAST");
		graphDef2.datasource("lspEstablished", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspEstablished", "LAST");
		graphDef2.area("lspEstablished", Color.GREEN, "LSPs Established");
		graphDef2.area("devolucao", Color.RED, "Devolutions");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("devolucao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao_CT"+i, "LAST");
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
		graphDef3.datasource("lspEstablished", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspEstablished", "LAST");
		graphDef3.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "LAST");
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
		retorno+=String.format("Average GRANT/DENY (ns) = %d\r\n", tempoAcumuladoGrantDeny/lspRequested);
		retorno+=String.format("Average GRANT/DENY (ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis(tempoAcumuladoGrantDeny/lspRequested));
		retorno+=String.format("Establishment Total(ns) = %d\r\n", tempoAcumuladoEstabelecimento);
		retorno+=String.format("Establishment Total(ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis(tempoAcumuladoEstabelecimento));
		retorno+=String.format("Average of Establishment (ns) = %d\r\n", tempoAcumuladoEstabelecimento/lspRequested);
		retorno+=String.format("Average of Establishment (ms) = %d\r\n", TimeUnit.NANOSECONDS.toMillis(tempoAcumuladoEstabelecimento/lspRequested));
		retorno+=String.format("LSPs Requested = %d\r\n", lspRequested);
		retorno+=String.format("LSPs Established = %d\r\n", lspEstablished);
		retorno+=String.format("LSPs Unbroken = %d\r\n", lspUnbroken);
		retorno+=String.format("LSPs Preemptions = %d\r\n", preempcoes);
		retorno+=String.format("LSPs Devolutions = %d\r\n", devolucoes); 
		retorno+=String.format("LSPs Blocking = %d\r\n", bloqueios);
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
			retorno+="LSP EstablishedTC["+i+"] = "+lspEstablishedCT[i]+"\r\n"; 
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

		
		retorno+="============================ Fim dos Estatï¿œsticas DSTE ============================";

		
		
		return retorno;
		
	}
	
	public CBRQuery getQuery(Link link, 
							String  gestor, 
							int []SLAUtilizacaoCT,
							int []SLABloqueiosCT,
							int []SLAPreempcoesCT, 
							int []SLADevolucoesCT )   
	{
		BAMDescription desc = new BAMDescription();
		
		
		try {
			
			desc.setGestor(gestor);
			
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
			desc.setSLADevolucoesCT2(  SLADevolucoesCT[2]);
			
						
			desc.setBC0( (int) (link.BC[0] * link.CargaEnlace) /100);
			desc.setBC1( (int) (link.BC[1] * link.CargaEnlace) /100);
			desc.setBC2( (int) (link.BC[2] * link.CargaEnlace) /100);
			
			desc.setUtilizacaoDoEnlaceCT0(link.CargaCTAtual[0]);
			desc.setUtilizacaoDoEnlaceCT1(link.CargaCTAtual[1]);
			desc.setUtilizacaoDoEnlaceCT2(link.CargaCTAtual[2]);
			
						
			if (lspRequested(ParametrosDSTE.Janela)!=0)
			{
				desc.setNumeroDeBloqueiosCT0(this.bloqueiosCT(ParametrosDSTE.Janela,0)*100/lspRequested(ParametrosDSTE.Janela));
				desc.setNumeroDeBloqueiosCT1(this.bloqueiosCT(ParametrosDSTE.Janela,1)*100/lspRequested(ParametrosDSTE.Janela));
				desc.setNumeroDeBloqueiosCT2(this.bloqueiosCT(ParametrosDSTE.Janela,2)*100/lspRequested(ParametrosDSTE.Janela));
				
				desc.setNumeroDePreempcoesCT0(this.preempcoesCT(ParametrosDSTE.Janela,0)*100/lspEstablished(ParametrosDSTE.Janela));
				desc.setNumeroDePreempcoesCT1(this.preempcoesCT(ParametrosDSTE.Janela,1)*100/lspEstablished(ParametrosDSTE.Janela));
				desc.setNumeroDePreempcoesCT2(this.preempcoesCT(ParametrosDSTE.Janela,2)*100/lspEstablished(ParametrosDSTE.Janela));
				
				desc.setNumeroDeDevolucoesCT0(this.devolucoesCT(ParametrosDSTE.Janela,0)*100/lspEstablished(ParametrosDSTE.Janela));
				desc.setNumeroDeDevolucoesCT1(this.devolucoesCT(ParametrosDSTE.Janela,1)*100/lspEstablished(ParametrosDSTE.Janela));
				desc.setNumeroDeDevolucoesCT2(this.devolucoesCT(ParametrosDSTE.Janela,2)*100/lspEstablished(ParametrosDSTE.Janela));
				
				
				
			}else
			{
				desc.setNumeroDeBloqueiosCT0(0);
				desc.setNumeroDeBloqueiosCT1(0);
				desc.setNumeroDeBloqueiosCT2(0);
				
				desc.setNumeroDePreempcoesCT0(0);
				desc.setNumeroDePreempcoesCT1(0);
				desc.setNumeroDePreempcoesCT2(0);
				
				desc.setNumeroDeDevolucoesCT0(0);
				desc.setNumeroDeDevolucoesCT1(0);
				desc.setNumeroDeDevolucoesCT2(0);
				
				
			}
			
			
		
		CBRQuery query = new CBRQuery();
		query.setDescription(desc);
		
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
