package cancer_heterogeneity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataSet {
	
	class DrugSensitivity{
		
		public DrugSensitivity(String filename){
			// Do something with the file
		}
		
	}
	
	class SingleNucleotideLevel{		
		/**
		 * Some internal tools here ~
		 * 
		 * 1) Variance score to exclude  bad quality reads
		 * 2) Coverage numbers to obtain variance ratio
		 */
		public SingleNucleotideLevel(String filename){
			// Do something with the file
		}
		
		public float varianceScore(/*Reads r*/){
			// i am assuming to find z score
			return 0.0f;
			
		}
		public float coverageNumbers(/*ArrayList<float> values*/){
			// obtain variance ratio from values
			return 0.0f;
		}
		
		public boolean isFalsePositive(float varianceScore, float varianceRatio){
			if(varianceScore > varianceRatio){
				return true;
			} else {
				return false;
			}
		}
		
		public boolean filterMutationsWithScore(float score){
			return (score >= 30);
		}
		
		public boolean filterReads(float count){
			return (count >= 8);
		}
		
		public boolean filterSNPS(String dbsnp){
			ArrayList<String> columnY = null;
			return columnY.contains(dbsnp);
		}
		
		public boolean filterMutations(ArrayList<String> reads){
			float varianceFrequency = reads.size() / coverageNumbers();
			return (varianceFrequency >= 0.18);
		}
		
		public ArrayList<String> getMutations(){
			
			// filter 8 individual mutation report .xlsx files
			
			// return list of potential cancer mutations
			
			return null;
		}
		
	}
	
	class CancerNucleotideVariation{
		
		
		
		public CancerNucleotideVariation(String filename){
			// Do something with the file
		}
		// Filters out normal (non-cancer) dna  variations by comparing
		// to healthy sister
		
		public void filterCoLocatedMutations(/*DNA , HealthySisterDataSet*/){
			
			/*
			 * if(col K == col J) ||
			 * if(col Q == col R) ||
			 * if((col S).contains(a dbsnp #))
			 * {
			 * 		ArrayList<Read> reads = filterReads(coverage < 8);
			 * 		//potential cancer mutations
			 * 		CHUtilities.MergeData.overlapDNAMutation(reads, snl.getMutations());
			 * }
			 */
			
		}		
	}
	
	class GeneExpression
	{		
		public GeneExpression(String filename)
		{
			
			ArrayList<String> str = readList(filename);
			
			
		}
	}
	
	DrugSensitivity ds;
	SingleNucleotideLevel snl;
	CancerNucleotideVariation cnv;
	GeneExpression ge;
	
	public DataSet(String type, String filename){
		if(type == "ds"){
			ds = new DrugSensitivity(filename);
		}
		if(type == "snl"){
			snl = new SingleNucleotideLevel(filename);
		}
		if(type == "cnv"){
			cnv = new CancerNucleotideVariation(filename);
		}
		if(type == "ge"){
			ge = new GeneExpression(filename);
		}
	}
	
	public ArrayList<String> readList( String filename ){
		System.out.println("file = "+filename);
		ArrayList<String> theList = new ArrayList<String>();
		
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filename));
		    String str;    
		    str = in.readLine();    	
		    while ((str = in.readLine()) != null) {
		    	//theList.add(str.substring(0,str.length()));
		    	theList.add(str);
		    	System.out.println(str);
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("BAD FILE WAS > " + filename);
		    System.out.println("File Read Error in writelist");
		    System.exit(0);
		}			
		return theList;
	}
	
	
}
