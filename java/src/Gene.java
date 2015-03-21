package cancer_heterogeneity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Gene 
{	
	class GeneValue
	{
		String name;
		double value;
		public GeneValue(String n, double v)
		{
			name = n;
			value = v;
		}
	}
	String Illumina_ID;
	String Gene_Symbol;
	String Combined_ID;
	String[] Gene_Name;
	double[] value;
	ArrayList<GeneValue> geneValues;
	
	double[] medDiffValues;
	String medDiffs = "";
	
	public Gene( )
	{		
		geneValues = new ArrayList<GeneValue>();
	}
	
	
	public String getGeneValues(){
		String str = "";
		for(int i = 0; i < geneValues.size(); i++){
			str+= geneValues.get(i).value;
			if(i != geneValues.size()-1){
				str+=",";
			}
		}
		return str;
	}
	
	public String getIlluminaID( )
	{
		return Illumina_ID;
	}
	public String getGeneSymbol( )
	{
		return Gene_Symbol;
	}
	public String getCombinedID( )
	{
		Combined_ID = getIlluminaID() + "," + getGeneSymbol();
		return Combined_ID;
	}
	
	public void setIlluminaID(String id)
	{
		Illumina_ID = id;
	}
	
	public void setGeneSymbol(String id)
	{
		Gene_Symbol = id;
	}
	public void setCombinedID(String ill, String gs)
	{
		Combined_ID = ill + "," + gs;
	}
	public void setCloneValues(double[] vals)
	{
		geneValues.add(new GeneValue("C5", vals[0]));
		geneValues.add(new GeneValue("C8", vals[1]));
		geneValues.add(new GeneValue("D10", vals[2]));
		geneValues.add(new GeneValue("F2", vals[3]));
		geneValues.add(new GeneValue("G8", vals[4]));
		geneValues.add(new GeneValue("G9", vals[5]));
	}
	
	public double getMedian()
	{
		double[] tempArr = new double[6];
		for(int i=0; i < geneValues.size(); i++){
			tempArr[i] = geneValues.get(i).value;
		}
		medDiffs="";
		Arrays.sort(tempArr);
	
		double median;
		if (tempArr.length % 2 == 0)
		{
		    median = (tempArr[(tempArr.length/2)] + tempArr[tempArr.length/2 - 1])/2;
		}
		else
		{
		    median = tempArr[tempArr.length/2];
		}
		for(int i = 0; i < geneValues.size(); i++){
			//medDiffValues[i] = geneValues.get(i).value - median;
			medDiffs+=(geneValues.get(i).value - median);
			if(i != geneValues.size()-1){
				medDiffs+=",";
			}
		}
		return median;
	}	
	
	public boolean isSignificant()
	{
		double median = getMedian();
		for(int i =0; i < geneValues.size(); i++)
		{
			if(Math.abs((geneValues.get(i).value - median)) >= /*0.58*/1)
			{
				//printSignificantCloneName(i);
				return true;
			}
		}
		return false;
	}	
	
	public ArrayList<String> whichAreSignificant()
	{
		double median = getMedian();
		ArrayList<String> important = new ArrayList<String>();
		
		for(int i =0; i < geneValues.size(); i++)
		{
			if( (((geneValues.get(i).value - median)) >= /*0.58*/1) || 
			   (((geneValues.get(i).value - median)) <= /*-0.58*/-1))
			{
				//important.add(this.Combined_ID+","+medDiffs);
				important.add(this.geneValues.get(i).name+","+Combined_ID+","+((geneValues.get(i).value - median)));
			}
		}
		return important;
	}
	
	// If we found a signifcant clone, print its name
	public void printSignificantCloneName(int i)
	{
		System.out.println("Your significant clone is: " + geneValues.get(i).name + " with value: " + geneValues.get(i).value + " from combined ID: " + Combined_ID);		
	}

}
