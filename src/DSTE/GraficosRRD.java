package DSTE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jrobin.core.RrdException;
import org.jrobin.graph.RrdGraph;
import org.jrobin.graph.RrdGraphDef;
import org.junit.Test;

public class GraficosRRD {


	public static void agregarLspRequested(String [] filenames) throws IOException, RrdException
	{
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
		graphDef.setVerticalLabel("Number");
		graphDef.setTitle("LSPs Geradas");
		
		for (String filename:filenames)
		{
			graphDef.datasource(filename+"_lspRequested", filename+"/"+filename+".rrd", "lspRequested", "AVERAGE");
		}
		
		String aux="";
		for (String filename:filenames)
		{
			aux+=filename+"_lspRequested,";
		}
		for (int j=0;j<filenames.length-1;j++)
			aux+="+,";
		aux+=filenames.length+",/";
		graphDef.datasource("lspRequested", aux);
		graphDef.line("lspRequested", new Color(0xFF, 0, 0), "LSPs Geradas Total", 2);
				

		//Por tempo
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
		graphDef2.setVerticalLabel("Number");
		graphDef2.setTitle("LSPs Geradas");
		for (String filename:filenames)
		{
			graphDef2.datasource(filename+"_lspRequestedMIN", filename+"/"+filename+".rrd", "lspRequested", "MIN");
			graphDef2.datasource(filename+"_lspRequestedMAX", filename+"/"+filename+".rrd", "lspRequested", "MAX");
			graphDef2.datasource(filename+"_lspRequested", filename+"_lspRequestedMAX,"+filename+"_lspRequestedMIN,-");
		}
		aux="";
		for (String filename:filenames)
		{
			aux+=filename+"_lspRequested,";
		}
		for (int j=0;j<filenames.length-1;j++)
			aux+="+,";
		aux+=filenames.length+",/";
		graphDef2.datasource("lspRequested", aux);
	
		graphDef2.area("lspRequested", Color.gray, "LSPs Geradas por Tempo");
		//graphDef2.line("lspRequestedMIN", Color.RED, "LSPs MIN Geradas por Tempo");
		//graphDef2.line("lspRequestedMAX", Color.GREEN, "LSPs MAX Geradas por Tempo");


		//acumulado
		RrdGraph graph = new RrdGraph(graphDef);
		int totalWidth=graph.getRrdGraphInfo().getWidth();
	    int totalHeight=graph.getRrdGraphInfo().getHeight();
	    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	    Graphics gfx=img.getGraphics();
	    graph.render(gfx);
	    File outputfile = new File(filenames[0]+"media/lsps_acum.png");
	    outputfile.mkdirs();
	    ImageIO.write(img,"png",outputfile);
	    

	    //Portempo
  		graph = new RrdGraph(graphDef2);
  		totalWidth=graph.getRrdGraphInfo().getWidth();
  	    totalHeight=graph.getRrdGraphInfo().getHeight();
  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
  	    gfx=img.getGraphics();
  	    graph.render(gfx);
  	    outputfile = new File(filenames[0]+"media/lsps.png");
  	    outputfile.mkdirs();
  	    ImageIO.write(img,"png",outputfile);
	    
	}
	public static void agregarRRD(String [] filenames, String titulo, String dsName ) throws IOException, RrdException
	{
			//Acumulado
			RrdGraphDef graphDef = new RrdGraphDef();
			graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+ParametrosDSTE.TempoSimulacao);
			graphDef.setVerticalLabel("Number");
			graphDef.setTitle(titulo);
			
			for (String filename:filenames)
			{
				graphDef.datasource(filename+"_"+dsName, "saida/"+filename+"/"+filename+".rrd", dsName, "MAX");
			}
			
			String aux="";
			for (String filename:filenames)
			{
				aux+=filename+"_"+dsName+",";
			}
			for (int j=0;j<filenames.length-1;j++)
				aux+="+,";
			aux+=filenames.length+",/";
			graphDef.datasource(dsName, aux);
			graphDef.line(dsName, new Color(0xFF, 0, 0), titulo+" Total", 2);
					

			//Por tempo
			RrdGraphDef graphDef2 = new RrdGraphDef();
			graphDef2.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+ParametrosDSTE.TempoSimulacao);
			graphDef2.setVerticalLabel("Number");
			graphDef2.setTitle(titulo);
			for (String filename:filenames)
			{
				graphDef2.datasource(filename+"_aux_lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "LAST");		
				graphDef2.datasource(filename+"_"+dsName, "saida/"+filename+"/"+filename+"_absoluto.rrd", dsName, "LAST");
			}
			aux="";
			for (String filename:filenames)
			{
				aux+=filename+"_"+dsName+",";
			}
			for (int j=0;j<filenames.length-1;j++)
				aux+="+,";
			aux+=filenames.length+",/";
			graphDef2.datasource(dsName, aux);
			
			aux="";
			for (String filename:filenames)
			{
				aux+=filename+"_aux_lspRequested,";
			}
			for (int j=0;j<filenames.length-1;j++)
				aux+="+,";
			aux+=filenames.length+",/";
			graphDef2.datasource("lspRequested_aux", aux);
			
			graphDef2.area("lspRequested_aux", Color.GREEN, "LSPs Geradas");
			graphDef2.area(dsName, Color.RED, titulo+" por Tempo");


			//Percentual
			
			RrdGraphDef graphDef3 = new RrdGraphDef();
			graphDef3.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+ParametrosDSTE.TempoSimulacao);
			graphDef3.setMaxValue(100);
			graphDef3.setVerticalLabel("Percent");
			//graphDef.setMinValue(0);
			graphDef3.setTitle(titulo);
			for (String filename:filenames)
			{
				graphDef3.datasource(filename+"_aux_lspRequested", "saida/"+filename+"/"+filename+"_absoluto.rrd", "lspRequested", "LAST");		
				graphDef3.datasource(filename+"_aux_"+dsName, "saida/"+filename+"/"+filename+"_absoluto.rrd", dsName, "LAST");
				
				graphDef3.datasource(filename+"_"+dsName, filename+"_aux_"+dsName+","+filename+"_aux_lspRequested,/,100,*");
			}
			aux="";
			for (String filename:filenames)
			{
				aux+=filename+"_"+dsName+",";
			}
			for (int j=0;j<filenames.length-1;j++)
				aux+="+,";
			aux+=filenames.length+",/";
			graphDef3.datasource(dsName, aux);
			graphDef3.area(dsName, Color.gray, "% "+titulo);


			//acumulado
			RrdGraph graph = new RrdGraph(graphDef);
			int totalWidth=graph.getRrdGraphInfo().getWidth();
		    int totalHeight=graph.getRrdGraphInfo().getHeight();
		    BufferedImage img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
		    Graphics gfx=img.getGraphics();
		    graph.render(gfx);
		    File outputfile = new File("saida/"+filenames[0]+"media/"+dsName+"_acum.png");
		    outputfile.mkdirs();
		    ImageIO.write(img,"png",outputfile);
		    

		    //Por tempo
	  		graph = new RrdGraph(graphDef2);
	  		totalWidth=graph.getRrdGraphInfo().getWidth();
	  	    totalHeight=graph.getRrdGraphInfo().getHeight();
	  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	  	    gfx=img.getGraphics();
	  	    graph.render(gfx);
	  	    outputfile = new File("saida/"+filenames[0]+"media/"+dsName+".png");
	  	    outputfile.mkdirs();
	  	    ImageIO.write(img,"png",outputfile);
	  	    
	  	   //Percent
	  		graph = new RrdGraph(graphDef3);
	  		totalWidth=graph.getRrdGraphInfo().getWidth();
	  	    totalHeight=graph.getRrdGraphInfo().getHeight();
	  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	  	    gfx=img.getGraphics();
	  	    graph.render(gfx);
	  	    outputfile = new File("saida/"+filenames[0]+"media/"+dsName+"_percent.png");
	  	    outputfile.mkdirs();
	  	    ImageIO.write(img,"png",outputfile);
			    
	}
}
