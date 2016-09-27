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


	public static void agregarLspGeradas(String [] filenames) throws IOException, RrdException
	{
		
		//Acumulado
		RrdGraphDef graphDef = new RrdGraphDef();
		graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
		graphDef.setVerticalLabel("Number");
		graphDef.setTitle("LSPs Geradas");
		
		for (String filename:filenames)
		{
			graphDef.datasource(filename+"_lspGeradas", filename+"/"+filename+".rrd", "lspGeradas", "AVERAGE");
		}
		
		String aux="";
		for (String filename:filenames)
		{
			aux+=filename+"_lspGeradas,";
		}
		for (int j=0;j<filenames.length-1;j++)
			aux+="+,";
		aux+=filenames.length+",/";
		graphDef.datasource("lspGeradas", aux);
		graphDef.line("lspGeradas", new Color(0xFF, 0, 0), "LSPs Geradas Total", 2);
				

		//Por tempo
		RrdGraphDef graphDef2 = new RrdGraphDef();
		graphDef2.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
		graphDef2.setVerticalLabel("Number");
		graphDef2.setTitle("LSPs Geradas");
		for (String filename:filenames)
		{
			graphDef2.datasource(filename+"_lspGeradasMIN", filename+"/"+filename+".rrd", "lspGeradas", "MIN");
			graphDef2.datasource(filename+"_lspGeradasMAX", filename+"/"+filename+".rrd", "lspGeradas", "MAX");
			graphDef2.datasource(filename+"_lspGeradas", filename+"_lspGeradasMAX,"+filename+"_lspGeradasMIN,-");
		}
		aux="";
		for (String filename:filenames)
		{
			aux+=filename+"_lspGeradas,";
		}
		for (int j=0;j<filenames.length-1;j++)
			aux+="+,";
		aux+=filenames.length+",/";
		graphDef2.datasource("lspGeradas", aux);
	
		graphDef2.area("lspGeradas", Color.gray, "LSPs Geradas por Tempo");
		//graphDef2.line("lspGeradasMIN", Color.RED, "LSPs MIN Geradas por Tempo");
		//graphDef2.line("lspGeradasMAX", Color.GREEN, "LSPs MAX Geradas por Tempo");


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
			graphDef.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
			graphDef.setVerticalLabel("Number");
			graphDef.setTitle(titulo);
			
			for (String filename:filenames)
			{
				graphDef.datasource(filename+"_"+dsName, filename+"/"+filename+".rrd", dsName, "AVERAGE");
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
			graphDef2.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
			graphDef2.setVerticalLabel("Number");
			graphDef2.setTitle(titulo);
			for (String filename:filenames)
			{
				graphDef2.datasource(filename+"_"+"lspGeradasMIN", filename+"/"+filename+".rrd", "lspGeradas", "MIN");
				graphDef2.datasource(filename+"_"+"lspGeradasMAX",filename+"/"+filename+".rrd", "lspGeradas", "MAX");
				graphDef2.datasource(filename+"_lspGeradas", filename+"_lspGeradasMAX,"+filename+"_lspGeradasMIN,-");
				
				graphDef2.datasource(filename+"_"+dsName+"MIN", filename+"/"+filename+".rrd", dsName, "MIN");
				graphDef2.datasource(filename+"_"+dsName+"MAX", filename+"/"+filename+".rrd", dsName, "MAX");
				graphDef2.datasource(filename+"_"+dsName, filename+"_"+dsName+"MAX,"+filename+"_"+dsName+"MIN,-");
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
				aux+=filename+"_lspGeradas,";
			}
			for (int j=0;j<filenames.length-1;j++)
				aux+="+,";
			aux+=filenames.length+",/";
			graphDef2.datasource("lspGeradas", aux);
			
			graphDef2.area("lspGeradas", Color.GREEN, "LSPs Geradas");
			graphDef2.area(dsName, Color.RED, titulo+" por Tempo");


			//Percentual
			
			RrdGraphDef graphDef3 = new RrdGraphDef();
			graphDef3.setTimeSpan(ParametrosDSTE.RRDStarTime,ParametrosDSTE.RRDStarTime+86400);
			graphDef3.setMaxValue(100);
			graphDef3.setVerticalLabel("Percent");
			//graphDef.setMinValue(0);
			graphDef3.setTitle(titulo);
			for (String filename:filenames)
			{
				graphDef3.datasource(filename+"_"+"lspGeradasMIN", filename+"/"+filename+".rrd", "lspGeradas", "MIN");
				graphDef3.datasource(filename+"_"+"lspGeradasMAX",filename+"/"+filename+".rrd", "lspGeradas", "MAX");
				graphDef3.datasource(filename+"_"+dsName+"MIN", filename+"/"+filename+".rrd", dsName, "MIN");
				graphDef3.datasource(filename+"_"+dsName+"MAX", filename+"/"+filename+".rrd", dsName, "MAX");
				graphDef3.datasource(filename+"_"+dsName, filename+"_"+dsName+"MAX,"+filename+"_"+dsName+"MIN,-,"+filename+"_lspGeradasMAX,"+filename+"_lspGeradasMIN,-,/,100,*");
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
		    File outputfile = new File(filenames[0]+"media/"+dsName+"_acum.png");
		    outputfile.mkdirs();
		    ImageIO.write(img,"png",outputfile);
		    

		    //Por tempo
	  		graph = new RrdGraph(graphDef2);
	  		totalWidth=graph.getRrdGraphInfo().getWidth();
	  	    totalHeight=graph.getRrdGraphInfo().getHeight();
	  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	  	    gfx=img.getGraphics();
	  	    graph.render(gfx);
	  	    outputfile = new File(filenames[0]+"media/"+dsName+".png");
	  	    outputfile.mkdirs();
	  	    ImageIO.write(img,"png",outputfile);
	  	    
	  	   //Percent
	  		graph = new RrdGraph(graphDef3);
	  		totalWidth=graph.getRrdGraphInfo().getWidth();
	  	    totalHeight=graph.getRrdGraphInfo().getHeight();
	  	    img=new BufferedImage(totalWidth,totalHeight,BufferedImage.TYPE_USHORT_565_RGB);
	  	    gfx=img.getGraphics();
	  	    graph.render(gfx);
	  	    outputfile = new File(filenames[0]+"media/"+dsName+"_percent.png");
	  	    outputfile.mkdirs();
	  	    ImageIO.write(img,"png",outputfile);
			    
	}
}
