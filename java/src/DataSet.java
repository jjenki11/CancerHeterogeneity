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
	
	class DrugTypes{
		public DrugTypes(String filename){
			
		}
	}
	
	class SingleNucleotideLevel{		
		/**
		 * Some internal tools here ~
		 * 
		 * 1) Variance score to exclude  bad quality reads
		 * 2) Coverage numbers to obtain variance ratio
		 */
		public SingleNucleotideLevel(String filename, String type)
		{
			// Do something with the file
			ArrayList<Mutation> mutationList = readSNLList(filename, type);
			
			BTree<String, Mutation> combinedIDTree = new BTree<String, Mutation>();
			BTree<String, ArrayList<Mutation>> mutationTree = new BTree<String, ArrayList<Mutation>>();
			
			combinedIDTree 	= makeCombinedIDTree(mutationList); 
			mutationTree 	= makeMutationTree(mutationList);
		}
				
		public BTree<String, ArrayList<Mutation>> makeMutationTree(ArrayList<Mutation> mutationList)
		{
			BTree<String, ArrayList<Mutation>> mutationTree = new BTree<String, ArrayList<Mutation>>();
			ArrayList<Mutation> l;
			for(int i =0;i<mutationList.size();i++)
			{
				if(mutationTree.get(mutationList.get(i).gene_name) == null)
				{
					l = new ArrayList<Mutation>();
					l.add(mutationList.get(i));
					mutationTree.put(mutationList.get(i).gene_name, l);
				}
				else
				{
					l = mutationTree.get(mutationList.get(i).gene_name);
					l.add(mutationList.get(i));
					mutationTree.put(mutationList.get(i).gene_name, l);
				}				
			}
			return mutationTree;
		}
		
		public BTree<String, Mutation> makeCombinedIDTree(ArrayList<Mutation> mutationList)
		{
			BTree<String, Mutation> mutationTree = new BTree<String, Mutation>();
			for(int i =0;i<mutationList.size();i++)
			{
				mutationTree.put(mutationList.get(i).Combined_ID, mutationList.get(i));
			}
			return mutationTree;
		}		
		
	}
	
	class CancerNucleotideVariation
	{		
		
		public CancerNucleotideVariation(String filename, String type)
		{
			// Do something with the file
			ArrayList<Cancer> mutationList = readCancerList(filename, type);
		}
		// Filters out normal (non-cancer) dna  variations by comparing
		// to healthy sister
		
		public void filterCoLocatedMutations(/*DNA , HealthySisterDataSet*/)
		{			
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
			ArrayList<Gene> geneList                = readGeneList(filename);			
			BTree<String, Gene> combinedIDTree      = new BTree<String, Gene>();
			BTree<String, ArrayList<Gene>> geneTree = new BTree<String, ArrayList<Gene>>();			
			combinedIDTree 	                        = makeCombinedIDTree(geneList); 
			geneTree 		                        = makeGeneTree(geneList);
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
	
	public DataSet(String type, String filename, String opt)
	{
		if(type == "ds")
		{
			ds = new DrugSensitivity(filename);
		}
		if(type == "snl")
		{
			snl = new SingleNucleotideLevel(filename, opt);
		}
		if(type == "cnv")
		{
			cnv = new CancerNucleotideVariation(filename, opt);
		}
		if(type == "ge")
		{
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
		    while ((str = in.readLine()) != null) 
		    {
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
		    		//System.out.println("For gene "+aGene.getCombinedID()+", the important clones are: { " + imp.toString() + " }");		    		
		    		theList.add(aGene);		    		
		    		counterYES ++;
		    	} 
		    	else 
		    	{
		    		counterNO ++;
		    	}
		    }
		    in.close();
		} 
		catch (IOException e) 
		{
			System.out.println("BAD FILE WAS > " + filename);
		    System.out.println("File Read Error in writelist");
		    System.exit(0);
		}			
		//System.out.println("Significant: "+ counterYES);
		//System.out.println("Not significant: "+ counterNO);
		System.out.println("Kept:\t\t"+counterYES);
		System.out.println("Discareded:\t\t\t"+counterNO);
		return theList;
	}
	
	public ArrayList<Mutation> readSNLList( String filename, String cloneType )
	{
		System.out.println("file = "+filename);
		ArrayList<Mutation> theList = new ArrayList<Mutation>();
		Mutation aMutation;
		String[] values;
		
		int counterYES = 0;
		int counterNO = 0;
		int thrownOutSnips = 0;
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filename));
		    String str;    
		    str = in.readLine();    	// take out the header file
		    while ((str = in.readLine()) != null) 
		    {
		    	aMutation = new Mutation();
		    	values = str.split(",");
		    	
		    	aMutation.clone_type=cloneType;
		    	aMutation.chrom=values[1];
		    	aMutation.left=values[2];
		    	aMutation.right=values[3];
		    	aMutation.ref_seq=values[4];
		    	aMutation.var_type=values[5];
		    	aMutation.zygosity=values[6];
				aMutation.var_seq_1=values[7];
				aMutation.var_seq_2=values[8];				
				aMutation.var_score=values[9];
				aMutation.not_ref_score=values[10];
				aMutation.coverage=values[11];
				aMutation.read_count_1=values[12];
				aMutation.read_count_2=values[13];
				aMutation.gene_name=values[15];
				aMutation.transcript_name=values[16];
				aMutation.where_in_transcript=values[17];
				aMutation.change_type_1=values[18];
				aMutation.ref_peptide_1=values[19];
				aMutation.var_peptide_1=values[20];
				aMutation.change_type_2=values[21];
				aMutation.ref_peptide_2=values[22];
				aMutation.var_peptide_2=values[23];
				aMutation.dbsnp=values[24];
				
				if(values.length < 26)
				{
					aMutation.dbsnp_build= "NA";
				} 
				else 
				{
					aMutation.dbsnp_build=values[25];	
				}
				
				// EVAL 1 
				if(
					(Double.parseDouble(aMutation.var_score) < 30.0)     										 ||
					(Double.parseDouble(aMutation.not_ref_score) < 30.0) 										 ||
					(Double.parseDouble(aMutation.read_count_1) < 8.0)   										 ||
					(Double.parseDouble(aMutation.read_count_2) < 8.0)   										 ||
					(Double.parseDouble(aMutation.read_count_1) / Double.parseDouble(aMutation.coverage) < 0.18) ||
					(aMutation.gene_name == " ")
				) 
				{
					counterNO++;
				}			
				// EVAL 2
				else if((aMutation.dbsnp.length()) >= 2) //really we were checking if there is a non-empty DBSNP but the char in the 'empty' ones is not null or a space
				{
					thrownOutSnips++;		
				}
				else
				{
					counterYES++;
					aMutation.Combined_ID = aMutation.gene_name+aMutation.left;
					theList.add(aMutation);
					//System.out.println(str);
				}				
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("BAD FILE WAS > " + filename);
		    System.out.println("File Read Error in writelist");
		    System.exit(0);
		}			
		/*
		System.out.println("Added after filter: "+ counterYES);
		System.out.println("Not added because of EVAL 1: "+ counterNO);
		System.out.println("Snips thrown out because of EVAL 2: "+ thrownOutSnips);
		System.out.println("Total thrown out: "+ (counterNO + thrownOutSnips));
		*/
		System.out.println("Kept:\t\t"+counterYES);
		System.out.println("Discareded:\t\t\t"+counterNO);
		return theList;
	}
	
	public ArrayList<Cancer> readCancerList( String filename, String type )
	{
		System.out.println("file = "+filename);
		ArrayList<Cancer> theList = new ArrayList<Cancer>();
		Cancer aCancer;
		String[] values;
		
		int counterYES = 0;
		int counterNO = 0;
		
		int sharedCP = 0;
		int missingCP = 0;
		
		int geneDesert = 0;
		int thrownOutSnips = 0;
		
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filename));
		    String str;    
		    str = in.readLine();    	
		    while ((str = in.readLine()) != null) 
		    {

		    	aCancer = new Cancer();
		    	values = str.split(",");
		    	
		    	aCancer.clone_type = type;
		    	aCancer.chrom=values[0];
		    	aCancer.left_cancer=values[1];
		    	aCancer.left_normal=values[2];
		    	aCancer.right_cancer=values[3];
		    	aCancer.right_normal=values[4];
		    	aCancer.ref_cancer=values[5];
		    	aCancer.ref_normal=values[6];
		    	aCancer.kind_cancer=values[7];
		    	aCancer.kind_normal=values[8];
		    	aCancer.var_seq_cancer=values[9];
		    	aCancer.var_seq_normal=values[10];
		    	aCancer.coverage_cancer=values[11];
		    	aCancer.coverage_normal=values[12];
		    	aCancer.conservation=values[13];
		    	aCancer.gene=values[14];
		    	aCancer.where_in_gene=values[15];
		    	aCancer.annot_cancer=values[16];
		    	aCancer.annot_normal=values[17];
		    	
				if(values.length < 19)
				{
					aCancer.dbsnp= "NA";
				} 
				else 
				{
					aCancer.dbsnp=values[18];	
				}
				
				
				if(aCancer.left_normal.length() >= 2)
				{
					missingCP++;
					counterNO++;
				} 
				else if(!(aCancer.gene.length() >= 2))
				{
					geneDesert++;
					counterNO++;
				}
				else if((aCancer.dbsnp) == "NA") //really we were checking if there is a non-empty DBSNP but the char in the 'empty' ones is not null or a space
				{
					thrownOutSnips++;		
					counterNO++;
				}
				else if(Double.parseDouble(aCancer.coverage_cancer) < 8.0)
				{
					counterNO++;
				}
				else
				{
					theList.add(aCancer);
					counterYES++;
					//System.out.println(str);
				}
		    	
		    	//aCancer.Combined_ID;

		    }
		    in.close();
		} 
		catch (IOException e) 
		{
			System.out.println("BAD FILE WAS > " + filename);
		    System.out.println("File Read Error in writelist");
		    System.exit(0);
		}	
		/*
		System.out.println("Total lost in gene desert: "+geneDesert);
		System.out.println("Total missing CP: "+missingCP);
		System.out.println("Total thrown out snips: "+thrownOutSnips);
		System.out.println("Kept: "+ counterYES);
		System.out.println("Total not kept: "+ counterNO);
		*/
		System.out.println("Kept:\t\t"+counterYES);
		System.out.println("Discareded:\t\t\t"+counterNO);
		
		return theList;
	}
	
	
}
