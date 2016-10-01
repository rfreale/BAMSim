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
	public int lspGeradas = 0;
	public int lspAtendidas = 0;
	public int lspEstabelecidas = 0;
	public double bandaAtendida = 0;
	public double bandaGerada = 0;
	public double bandaEstabelecida = 0;

	public int [] lspGeradasCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaGeradaCT = new double [ParametrosDSTE.MaxClassType];
	public int [] lspEstabelecidasCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaEstabelecidaCT = new double [ParametrosDSTE.MaxClassType];
	public int [] lspAtendidaCT = new int [ParametrosDSTE.MaxClassType];
	public double [] bandaAtendidaCT = new double [ParametrosDSTE.MaxClassType];

	
	
	public int [] preempcoesCT = new int [ParametrosDSTE.MaxClassType];
	public int [] devolucoesCT = new int [ParametrosDSTE.MaxClassType];
	public int [] bloqueiosCT = new int [ParametrosDSTE.MaxClassType];
	
	
	
	
	
	//auxiliares
	public int preempcoesAUX = 0;
	public int devolucoesAUX = 0;
	public int bloqueiosAUX = 0;
	public int lspGeradasAUX = 0;
	public int lspAtendidasAUX = 0;
	public int lspEstabelecidasAUX = 0;
	public double bandaAtendidaAUX = 0;
	public double bandaGeradaAUX = 0;

	public int [] preempcoesCTAUX = new int [ParametrosDSTE.MaxClassType];
	public int [] devolucoesCTAUX = new int [ParametrosDSTE.MaxClassType];
	public int [] bloqueiosCTAUX = new int [ParametrosDSTE.MaxClassType];
	
	private long starTime = ParametrosDSTE.RRDStarTime;
	private long curretTime;
	public String filename;
	public Color [] cores ={Color.BLUE,Color.CYAN,Color.GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.GREEN};


	private int graphWidthLine=6;
	private int graphWidth=((9*250)-236+24+31);
	private int graphHeight=((3*250)-242+33+15);
	private Font graphLargeFont=new Font("Arial", Font.BOLD, 70);
	private Font graphSmallFont=new Font("Arial", Font.BOLD, 40);
    private int graphMinorUnit=RrdGraphConstants.MINUTE;
    private	int graphMinorUnitCount= 1;
    private	int graphMajorUnit=RrdGraphConstants.MINUTE;
    private	int graphMajorUnitCount=10;
    private	int graphLabelUnit=RrdGraphConstants.MINUTE;
    private	int graphLabelUnitCount=20;
    private	int graphLabelSpan= 0;
    private	String graphSimpleDateFormat="HH:mm";
    
    private BasicStroke dotDashStroke = 
    	    new BasicStroke(graphWidthLine /*width*/,
    	            BasicStroke.CAP_BUTT /*end style*/,
    	            BasicStroke.JOIN_MITER /*join style*/,
    	            3.0f /*miter trim limit */,
    	            new float[] {3.0f} /* pattern array */,
    	            0.0f /* offset to start of pattern */);
    	
    private BasicStroke dotDashStroke2 = 
    		    new BasicStroke(graphWidthLine /*width*/,
    		            BasicStroke.CAP_BUTT /*end style*/,
    		            BasicStroke.JOIN_MITER /*join style*/,
    		            1.0f /*miter trim limit */,
    		            new float[] {5.0f, 3.0f, 1.0f, 3.0f } /* pattern array */,
    		            0.0f /* offset to start of pattern */);
    private BasicStroke dotDashStroke3 = 
    		    new BasicStroke(graphWidthLine /*width*/,
    		            BasicStroke.CAP_BUTT /*end style*/,
    		            BasicStroke.JOIN_MITER /*join style*/,
    		            1.0f /*miter trim limit */,
    		            new float[] {5.0f} /* pattern array */,
    		            0.0f /* offset to start of pattern */);
    	
    	
    private BasicStroke dashStroke = 		    new BasicStroke(graphWidthLine);
    	
    private BasicStroke[] dashStrokeList = {dotDashStroke,dotDashStroke2,dotDashStroke3};

	
	
	public EstatisticasDSTE(String filename)
	{
		
		try{
			//Criar base padr�o
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
			rrdDef.addDatasource("lspGeradas", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("lspAtendidas", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("lspEstabelecidas", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaAtendida", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaGerada", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			
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
			rrdDef.addDatasource("lspGeradas", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("lspAtendidas", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("lspEstabelecidas", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaAtendida", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			rrdDef.addDatasource("bandaGerada", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
			
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
					rrdDef.addDatasource(aux.Descricao+"_CT"+i, "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
				}
				rrdDef.addDatasource(aux.Descricao+"_total", "GAUGE", ParametrosDSTE.RRDBatida, ParametrosDSTE.RRDMin, ParametrosDSTE.RRDMax);
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
		sample.setAndUpdate(starTime+time+":"+preempcoes+":"+preempcoesCT[0]+":"+preempcoesCT[1]+":"+preempcoesCT[2]+":"+bloqueios+":"+bloqueiosCT[0]+":"+bloqueiosCT[1]+":"+bloqueiosCT[2]+":"+devolucoes+":"+devolucoesCT[0]+":"+devolucoesCT[1]+":"+devolucoesCT[2]+":"+lspGeradas+":"+lspAtendidas+":"+lspEstabelecidas+":"+bandaAtendida+":"+bandaGerada);

		rrdDb.close();

	}
	public  void inserirDadosAbsolutoRRD(long time) throws IOException, RrdException
	{
		int preempcoes = this.preempcoes-this.preempcoesAUX;
		int devolucoes = this.devolucoes-this.devolucoesAUX;
		int bloqueios = this.bloqueios-this.bloqueiosAUX;
		int lspGeradas = this.lspGeradas-this.lspGeradasAUX;
		int lspAtendidas = this.lspAtendidas-this.lspAtendidasAUX;
		int lspEstabelecidas = this.lspEstabelecidas-this.lspEstabelecidasAUX;
		double bandaAtendida = this.bandaAtendida-this.bandaAtendidaAUX;
		double bandaGerada = this.bandaGerada-this.bandaGeradaAUX;

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
		sample.setAndUpdate(starTime+time+":"+preempcoes+":"+preempcoesCT[0]+":"+preempcoesCT[1]+":"+preempcoesCT[2]+":"+bloqueios+":"+bloqueiosCT[0]+":"+bloqueiosCT[1]+":"+bloqueiosCT[2]+":"+devolucoes+":"+devolucoesCT[0]+":"+devolucoesCT[1]+":"+devolucoesCT[2]+":"+lspGeradas+":"+lspAtendidas+":"+lspEstabelecidas+":"+bandaAtendida+":"+bandaGerada);

		rrdDb.close();
		
		this.preempcoesAUX=this.preempcoes;
		this.devolucoesAUX=this.devolucoes;
		this.bloqueiosAUX=this.bloqueios;
		this.lspGeradasAUX=this.lspGeradas;
		this.lspAtendidasAUX=this.lspAtendidas;
		this.lspEstabelecidasAUX=this.lspEstabelecidas;
		this.bandaAtendidaAUX=this.bandaAtendida;
		this.bandaGeradaAUX=this.bandaGerada;

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
		//Faz a subtra��o dos dois valores para pegar o valor na janela
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
		//Faz a subtra��o dos dois valores para pegar o valor na janela
		int prempcoes=(int) (fetchData.getAggregate("preempcao_CT"+ct, "MAX")-fetchData.getAggregate("preempcao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return prempcoes;
	}
	public int lspGeradas(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtra��o dos dois valores para pegar o valor na janela
		int lspGeradas=(int) (fetchData.getAggregate("lspGeradas", "MAX")-fetchData.getAggregate("lspGeradas", "MIN"));
		
		rrdDb.close();
		return lspGeradas;
	}
	public int bloqueios(long time) throws IOException, RrdException
	{
		//Aponta para o arquivo da base
		RrdDb rrdDb = new RrdDb("saida/"+filename+"/"+filename+".rrd");
		FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", curretTime-time-ParametrosDSTE.RRDAmostra,curretTime);
		FetchData fetchData = fetchRequest.fetchData();
		//Faz a subtra��o dos dois valores para pegar o valor na janela
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
		//Faz a subtra��o dos dois valores para pegar o valor na janela
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
		//Faz a subtra��o dos dois valores para pegar o valor na janela
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
		//Faz a subtra��o dos dois valores para pegar o valor na janela
		int devolucoes=(int) (fetchData.getAggregate("devolucao_CT"+ct, "MAX")-fetchData.getAggregate("devolucao_CT"+ct, "MIN"));
		
		rrdDb.close();
		return devolucoes;
	}
	public void gerarRRDPNGlspGeradas() throws IOException, RrdException
	{

		//RrdDb rrdDb = new RrdDb(filename+"/"+filename+".rrd");
		/*FetchRequest fetchRequest = rrdDb.createFetchRequest("MAX", starTime,i);
		FetchData fetchData = fetchRequest.fetchData();
		fetchData.dump();*/
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef.setTitle("LSPs Geradas");
		graphDef.datasource("lspGeradas", "saida/"+filename+"/"+filename+".rrd", "lspGeradas", "MAX");
		graphDef.line("lspGeradas", new Color(0xFF, 0, 0), "LSPs Geradas Total", graphWidthLine);
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
		graphDef2.setTitle("LSPs Geradas");
		graphDef2.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		
		graphDef2.area("lspGeradas", Color.gray, "LSPs Geradas por Tempo");
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

	public void gerarRRDPNGpreempcao() throws IOException, RrdException
	{

		
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(starTime,curretTime);
		graphDef.setVerticalLabel("Number");
		//graphDef.setMinValue(0);
		graphDef.setTitle("Preemp��es Acumuladas");
		graphDef.datasource("preempcao", "saida/"+filename+"/"+filename+".rrd", "preempcao", "MAX");
		graphDef.line("preempcao", new Color(0xFF, 0, 0), "Preemp��o Total", graphWidthLine);
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
		graphDef2.setTitle("Preemp��es x LSPs Geradas");
		graphDef2.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "LAST");
		graphDef2.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef2.area("lspGeradas", Color.GREEN, "LSPs Geradas");
		graphDef2.area("preempcao", Color.RED, "Preemp��es");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("preempcao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao_CT"+i, "LAST");
			graphDef2.line("preempcao_CT"+i, cores[i], "Preempc�es em CT"+i, graphWidthLine);
			
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
		graphDef3.setTitle("Preemp��es");

		graphDef3.datasource("preempcao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "preempcao", "LAST");
		graphDef3.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef3.datasource("prempcoes", "preempcao,lspGeradas,/,100,*");
		graphDef3.area("prempcoes", Color.gray, "% Premp��es");
		
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
		//graphDef.setMinValue(0);
		graphDef.setTitle("Bloqueios Acumulados");
		graphDef.datasource("bloqueio", "saida/"+filename+"/"+filename+".rrd", "bloqueio", "MAX");
		graphDef.line("bloqueio", new Color(0xFF, 0, 0), "Bloqueio", graphWidthLine);
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
		graphDef2.setTitle("Bloqueios x LSPs Geradas");
		graphDef2.datasource("bloqueio", "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio", "LAST");
		graphDef2.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef2.area("lspGeradas", Color.GREEN, "LSPs Geradas");
		graphDef2.area("bloqueio", Color.RED, "Bloqueios");
		
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("bloqueio_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio_CT"+i, "LAST");
			graphDef2.line("bloqueio_CT"+i, cores[i], "Bloqueios em CT"+i, graphWidthLine);
			
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
		graphDef3.setTitle("Bloqueio");
		graphDef3.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef3.datasource("bloqueio", "saida/"+filename+"/"+filename+"_absoluto.rrd", "bloqueio", "LAST");
		graphDef3.datasource("bloqueios", "bloqueio,lspGeradas,/,100,*");
		graphDef3.area("bloqueios", Color.gray, "% Bloqueios");
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
		//graphDef.setMinValue(0);
		graphDef.setTitle("Devolu��es Acumuladas");
		graphDef.datasource("devolucao", "saida/"+filename+"/"+filename+".rrd", "devolucao", "MAX");
		graphDef.line("devolucao", new Color(0xFF, 0, 0), "Devolu��o", graphWidthLine);
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
		graphDef2.setTitle("Devolu��es x LSPs Geradas");
		graphDef2.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "LAST");
		graphDef2.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef2.area("lspGeradas", Color.GREEN, "LSPs Geradas");
		graphDef2.area("devolucao", Color.RED, "Devolu��es");
		for(int i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef2.datasource("devolucao_CT"+i, "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao_CT"+i, "LAST");
			graphDef2.line("devolucao_CT"+i, cores[i], "Devolu��es em CT"+i, graphWidthLine);
			
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
		graphDef3.setTitle("Devolu��es");
		graphDef3.datasource("lspGeradas", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspGeradas", "LAST");
		graphDef3.datasource("devolucao", "saida/"+filename+"/"+filename+"_absoluto.rrd", "devolucao", "LAST");
		graphDef3.datasource("devolucoes", "devolucao,lspGeradas,/,100,*");
		graphDef3.area("devolucoes", Color.gray, "% Devolu��es");
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
		graphDef.setTitle("Link "+link.ID);
		int i=0;
		for(i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef.datasource(link.Descricao+"_CT"+i, "saida/"+filename+"/"+filename+"_links.rrd",link.Descricao+"_CT"+i, "LAST");
		}
		graphDef.datasource(link.Descricao+"_total", "saida/"+filename+"/"+filename+"_links.rrd",link.Descricao+"_total", "LAST");
		
		for(i=0;i<ParametrosDSTE.MaxClassType;i++)
		{
			graphDef.line(link.Descricao+"_CT"+i, cores[i],"TC"+i, graphWidthLine,dashStrokeList[i]);
		}
		//graphDef.line(link.Descricao+"_total",cores[i],"Total",2);
		graphDef.line(link.Descricao+"_total",cores[i],"Total",graphWidthLine,dashStroke);
		
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
		String retorno="============================ In�cio dos Estat�sticas DSTE ============================\r\n";
		retorno+=String.format("N�mero de LSPs Preemptadas = %d\r\n", preempcoes);
		retorno+=String.format("N�mero de LSPs Preemptadas Debt = %d\r\n", devolucoes); 
		retorno+=String.format("N�mero de LSPs Bloqueadas = %d\r\n", bloqueios);
		retorno+=String.format("N�mero de LSPs Geradas = %d\r\n", lspGeradas);
		retorno+=String.format("N�mero de LSPs Estabelecidas = %d\r\n", lspEstabelecidas);
		retorno+=String.format("N�mero de LSPs Atendidas = %d\r\n", lspAtendidas);
		retorno+=String.format("Total de Banda Gerada = %6.2f\r\n", bandaGerada);
		retorno+=String.format("Total de Banda Atendida = %6.2f\r\n", bandaAtendida);
		retorno+=String.format("Total de Banda Estabelecida = %6.2f\r\n", bandaEstabelecida);
		for(int i=0;i<bloqueiosCT.length;i++){ 
			retorno+="bloqueiosCT["+i+"] = "+bloqueiosCT[i]+"\r\n"; 
		} 
		for(int i=0;i<preempcoesCT.length;i++){ 
			retorno+="preempcoesCT["+i+"] = "+preempcoesCT[i]+"\r\n"; 
		}   
		for(int i=0;i<devolucoesCT.length;i++){ 
			retorno+="devolucoesCT["+i+"] = "+devolucoesCT[i]+"\r\n"; 
		} 
		for(int i=0;i<lspGeradasCT.length;i++){ 
			retorno+="lspGeradasCT["+i+"] = "+lspGeradasCT[i]+"\r\n"; 
		} 
		
		for(int i=0;i<lspEstabelecidasCT.length;i++){ 
			retorno+="lspEstabelecidasCT["+i+"] = "+lspEstabelecidasCT[i]+"\r\n"; 
		} 
		for(int i=0;i<lspAtendidaCT.length;i++){ 
			retorno+="lspAtendidaCT["+i+"] = "+lspAtendidaCT[i]+"\r\n"; 
		} 
		for(int i=0;i<bandaGeradaCT.length;i++){ 
			retorno+="bandaGeradaCT["+i+"] = "+bandaGeradaCT[i]+"\r\n"; 
		} 
		for(int i=0;i<bandaAtendidaCT.length;i++){ 
			retorno+="bandaAtendidaCT["+i+"] = "+bandaAtendidaCT[i]+"\r\n"; 
		} 
		for(int i=0;i<bandaEstabelecidaCT.length;i++){ 
			retorno+="bandaEstabelecidaCT["+i+"] = "+bandaEstabelecidaCT[i]+"\r\n"; 
		} 

		
		retorno+="============================ Fim dos Estat�sticas DSTE ============================";

		
		
		return retorno;
		
	}
	public CBRQuery getQuery(Link link, Problemas problema, BAMType BAMAtual)
	{
		BAMDescription desc = new BAMDescription();
		desc.setUtilizacaoDoEnlace(link.getCargaEnlaceAtual()*100/link.CargaEnlace);
		try {
			if (lspGeradas(ParametrosDSTE.Janela)!=0)
			{
				desc.setNumeroDeBloqueiosCT0(this.bloqueiosCT(ParametrosDSTE.Janela,0)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDeBloqueiosCT1(this.bloqueiosCT(ParametrosDSTE.Janela,1)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDeBloqueiosCT2(this.bloqueiosCT(ParametrosDSTE.Janela,2)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDeDevolucoesCT0(this.devolucoesCT(ParametrosDSTE.Janela,0)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDeDevolucoesCT1(this.devolucoesCT(ParametrosDSTE.Janela,1)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDeDevolucoesCT2(this.devolucoesCT(ParametrosDSTE.Janela,2)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDePreempcoesCT0(this.preempcoesCT(ParametrosDSTE.Janela,0)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDePreempcoesCT1(this.preempcoesCT(ParametrosDSTE.Janela,1)*100/lspGeradas(ParametrosDSTE.Janela));
				desc.setNumeroDePreempcoesCT2(this.preempcoesCT(ParametrosDSTE.Janela,2)*100/lspGeradas(ParametrosDSTE.Janela));
			}else
			{
				desc.setNumeroDeBloqueiosCT0(0);
				desc.setNumeroDeBloqueiosCT1(0);
				desc.setNumeroDeBloqueiosCT2(0);
				desc.setNumeroDeDevolucoesCT0(0);
				desc.setNumeroDeDevolucoesCT1(0);
				desc.setNumeroDeDevolucoesCT2(0);
				desc.setNumeroDePreempcoesCT0(0);
				desc.setNumeroDePreempcoesCT1(0);
				desc.setNumeroDePreempcoesCT2(0);
			}
			
			//Compatibilidade com G-BAM apenas refeltindo MAM, RDM e Alloc
			if(BAMAtual!=BAMType.PreemptionGBAM)
			{
				desc.setBAMAtual(BAMTypes.valueOf(BAMAtual.toString()));
			}else
			{
				//Se BCLTH diferente de 0 � pq reflete Alloc
				if (link.BCLTH[0]!=0)
					desc.setBAMAtual(BAMTypes.PreemptionAllocCTSharing);
				//Se BCLTH diferente � igual a 0 e BCHTL diferente de 0 � pq reflete RDM
				else if (link.BCHTL[2]!=0)
					desc.setBAMAtual(BAMTypes.PreemptionRDM);
				//Se BCLTH e BCHTL igual a 0 � pq reflete MAM
				else
					desc.setBAMAtual(BAMTypes.NoPreemptionMAM);
			}
			desc.setProblema(Problemas.valueOf(problema.toString()));

		
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
