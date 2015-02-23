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
			ArrayList<Gene> geneList = readGeneList(filename);
			
			BTree<String, Gene> combinedIDTree = new BTree<String, Gene>();
			BTree<String, ArrayList<Gene>> geneTree = new BTree<String, ArrayList<Gene>>();
			
			combinedIDTree 	= makeCombinedIDTree(geneList); 
			geneTree 		= makeGeneTree(geneList);
		}
		
		
		public BTree<String, Gene> makeCombinedIDTree(ArrayList<Gene> geneList)
		{
			BTree<String, Gene> geneTree = new BTree<String, Gene>();
			for(int i =0;i<geneList.size();i++)
			{
				geneTree.put(geneList.get(i).Combined_ID, geneList.get(i));
			}
			return geneTree;
		}
		
		public BTree<String, ArrayList<Gene>> makeGeneTree(ArrayList<Gene> geneList)
		{
			BTree<String, ArrayList<Gene>> geneTree = new BTree<String, ArrayList<Gene>>();
			ArrayList<Gene> l;
			for(int i =0;i<geneList.size();i++)
			{
				if(geneTree.get(geneList.get(i).Gene_Symbol) == null)
				{
					l = new ArrayList<Gene>();
					l.add(geneList.get(i));
					geneTree.put(geneList.get(i).Gene_Symbol, l);
				}
				else
				{
					l = geneTree.get(geneList.get(i).Gene_Symbol);
					l.add(geneList.get(i));
					geneTree.put(geneList.get(i).Gene_Symbol, l);
				}				
			}
			return geneTree;
		}
		
	}
	
	DrugSensitivity ds;
	SingleNucleotideLevel snl;
	CancerNucleotideVariation cnv;
	GeneExpression ge;
	
	public DataSet(String type, String filename)
	{
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
	
	public ArrayList<Gene> readGeneList( String filename )
	{
		System.out.println("file = "+filename);
		ArrayList<Gene> theList = new ArrayList<Gene>();
		Gene aGene;
		String[] values;
		
		int counterYES = 0;
		int counterNO = 0;
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filename));
		    String str;    
		    str = in.readLine();    	
		    while ((str = in.readLine()) != null) {
		    	//theList.add(str.substring(0,str.length()));
		    	aGene = new Gene();
		    	values = str.split(",");
	    	
		    	aGene.setIlluminaID(values[0]);
		    	aGene.setGeneSymbol(values[1]);
		    	
		    	aGene.setCombinedID(aGene.getIlluminaID(), aGene.getGeneSymbol());
		    	
		    	double[] clones = new double[6];
		    	
		    	clones[0] = Double.parseDouble(values[2]);
		    	clones[1] = Double.parseDouble(values[3]);
		    	clones[2] = Double.parseDouble(values[4]);
		    	clones[3] = Double.parseDouble(values[5]);
		    	clones[4] = Double.parseDouble(values[6]);
		    	clones[5] = Double.parseDouble(values[7]);
		    		    	
		    	aGene.setCloneValues(clones);
		    	
		    	if(aGene.isSignificant())
		    	{
		    		ArrayList<String> imp = aGene.whichAreSignificant();		    		
		    		System.out.println("For gene "+aGene.getCombinedID()+", the important clones are: { " + imp.toString() + " }");		    		
		    		theList.add(aGene);		    		
		    		counterYES ++;
		    	} 
		    	else 
		    	{
		    		counterNO ++;
		    	}
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("BAD FILE WAS > " + filename);
		    System.out.println("File Read Error in writelist");
		    System.exit(0);
		}			
		System.out.println("Significant: "+ counterYES);
		System.out.println("Not significant: "+ counterNO);
		return theList;
	}
	
	
}
