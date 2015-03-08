package cancer_heterogeneity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * 
 * @author jeff.jenkins
 * 
 * CHUtilities is a class which returns a set of utilities
 * 
 * (cancer heterogeniety utilities include)
 * 
 * 	- Filtering
 *  - Evaluation
 *  - Overlap / Merging
 *  - File Reading
 *
 */


public class CHUtilities 
{
	
	Filter filterData;
	Evaluation evaluateData = new Evaluation();
	MergeData mergeData = new  MergeData();
	ReadData readData = new ReadData();
	
	/**
	 * FIltering class
	 * @author jeff.jenkins
	 *
	 */
	class Filter
	{	
		
		public Filter(){
			
		}
		
		public void filterDrugData(String data){
			
		}
		
		public void filterGeneExpressionData(String data){
			
		}
		
		public void filterDNAMutationData(String data){
			
		}
	}
	
	/**
	 * Evaluation class
	 * @author jeff.jenkins
	 *
	 */
	class Evaluation
	{
		
		public Evaluation(){
			
		}
		
		public void identifyUniqueTopTargets(/*Clone c*/)
		{
			
		}
		
		public void identifySharedTopTargets(/*ArrayList<Clone> clones*/)
		{
			
		}
		
		public void identifyUniqueDeregulatedGenes(/*Clone c*/)
		{
			
		}
		
		public void lookAtGeneExpressionOfDrugTarget(/*Clone c*/)
		{
			
		}
		
		public void identifyUniqueDNAMutations(/*Clone c*/)
		{
			
		}
		
		public void identifyOverlappingDNAMutationsIn(/*Clone c, Tumor t*/)
		{
			
		}
		
		public void checkIfMutatedGeneTargeted(/*Clone c, 
												Drug d*/){
			
		}
		
	}
	
	/**
	 * MergeData class
	 * @author jeff.jenkins
	 *
	 */
	class MergeData
	{		
		
		public MergeData(){
			
		}
		
		// Finding overlap in THREE different cases
		
		///////
		//	1) gene expression explaining drug sensitivity
		
		public boolean overlapGeneExpression(/*Clone target,  Gene gene*/)
		{
			
			boolean overlap = true; /*do something*/ 
			
			if(overlap){
				return true;
			} else {
				return false;
			}
		}
		
		///////
		//	2) dna mutation explaining drug sensitivity
				// if (overlap){
				// 	 dna mutation explains drug sens.
				// if (!overlap){
				//   dna mutation is not targeted by drugs
		
		public boolean overlapDNAMutationTarget(/*Clone c, Drug d*/)
		{
			
			boolean overlap = true; /*do something*/
			
			if(overlap){
				return true;
			} else {
				return false;
			}
		}
		
		///////
		//	3) dna mutation in clones and tumor tissue
				// if (overlap){
				// 	 cancer mutations shared between tumor tissue and clone
				// if (!overlap){
				//   no linkage between tumor tissue and clone
		
		public boolean overlapDNAMutation(/*Clone c, Tissue t*/)
		{
			
			boolean overlap = true; /*do something*/			
			
			if(overlap){
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Read data utils for each file type
	 */
	class ReadData
	{
		public ReadData()
		{
			
		}
		
		// mRNA expression util
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
		
		// DNA Mutations util (1/2)
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
		
		// DNA Mutations util (2/2)
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
		
		// Drug Sensitivity util (1/2)
		public ArrayList<DrugType> readDrugTypeList(String filename) {
			System.out.println("file = "+filename);
			ArrayList<DrugType> theList = new ArrayList<DrugType>();
			DrugType aDrugType;
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
			    	aDrugType = new DrugType();
			    	values = str.split(",");
			    	aDrugType.sample_ID=values[0];
			    	aDrugType.gene_symbol=values[1];
			    	//aDrugType.structure="";
			    	aDrugType.sample_name=values[2];
			    	//aDrugType.drug_name="";
			    	aDrugType.ncgc_UID=values[3];
			    	aDrugType.pri_mech_action=values[4];
			    	
			    	
			    	// insert conditional to add to list...
			    	
			    	theList.add(aDrugType);
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
		
		// Drug Sensitivity  util (2/2)
		public ArrayList<DrugSensitivity> readSensitivityList(String filename) {
			System.out.println("file = "+filename);
			ArrayList<DrugSensitivity> theList = new ArrayList<DrugSensitivity>();
			DrugSensitivity aDrugSensitivity;
			String[] values;
			
			int counterYES = 0;
			int counterNO = 0;
			String str2;
			try {
			    BufferedReader in = new BufferedReader(new FileReader(filename));
			    String str;
			    
			    str = in.readLine();
			    
			    
			    
			    while ((str = in.readLine()) != null) 
			    {
			    	//theList.add(str.substring(0,str.length()));
			    	str2 = "";
			    	aDrugSensitivity = new DrugSensitivity();
			    	values = str.split(",");
			    	/*
			    	System.out.println("Number of fields = "+values.length + "\nSize of string = " + str.length() + "  " + str);
			    	
			    	StringTokenizer st = new StringTokenizer(str, ",");
			    	int cnt = 0;
			    	while (st.hasMoreTokens()) {
			    		String col = st.nextToken();
			    		str+=col;
			    	     System.out.println(col);
			    	     cnt++;
			    	}
			    	System.out.println("Number of tokens = "+cnt + " \nsize of string = " + str.length()+ " "+str);
			    	
			    	char[] cha = str.toCharArray();
				    System.out.println("\n DA CHAR LENGTHS BYE SIZE = " + cha.length);
			    	*/
			    	
			    	aDrugSensitivity.protocol=values[0];
			    	aDrugSensitivity.sample_ID=values[1];
			    	/*aDrugSensitivity.CCLASS= (values[2]);
			    	aDrugSensitivity.CCLASS2= (values[3]);
			    	aDrugSensitivity.LAC50= (values[4]);
			    	aDrugSensitivity.INF= (values[5]);
			    	aDrugSensitivity.ZERO= (values[6]);
			    	aDrugSensitivity.HILL= (values[7]);
			    	aDrugSensitivity.MAXR= (values[8]);*/
					
			    	aDrugSensitivity.DATA0= (values[2]);
			    	aDrugSensitivity.DATA1= (values[3]);
			    	aDrugSensitivity.DATA2= (values[4]);
			    	aDrugSensitivity.DATA3= (values[5]);
			    	aDrugSensitivity.DATA4= (values[6]);
			    	aDrugSensitivity.DATA5= (values[7]);
			    	aDrugSensitivity.DATA6= (values[8]);
			    	aDrugSensitivity.DATA7= (values[9]);
			    	aDrugSensitivity.DATA8= (values[10]);
			    	aDrugSensitivity.DATA9= (values[11]);
			    	aDrugSensitivity.DATA10= (values[12]);
					

			    	aDrugSensitivity.C0= (values[13]);
			    	aDrugSensitivity.C1= (values[14]);
			    	aDrugSensitivity.C2= (values[15]);
			    	aDrugSensitivity.C3= (values[16]);
			    	aDrugSensitivity.C4= (values[17]);
			    	aDrugSensitivity.C5= (values[18]);
			    	aDrugSensitivity.C6= (values[19]);
			    	aDrugSensitivity.C7= (values[20]);
			    	aDrugSensitivity.C8= (values[21]);
			    	aDrugSensitivity.C9= (values[22]);
			    	aDrugSensitivity.C10= (values[23]);
					
					
			    	//aDrugSensitivity.sample_name=values[24];
			    	//System.out.println(values[31]);
			    	aDrugSensitivity.gene=values[24];
			    	
			    	//System.exit(0);
			    	
			    	//aDrugSensitivity.smi=values[32];
			    			
			    	// insert if statements to determine 'significance'
			    	
			    	counterYES++;
			    	
			    	theList.add(aDrugSensitivity);
			    	
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
		
	}
	
	/**
	 * API access
	 */
	public CHUtilities() 
	{
		 filterData   = new Filter();
		 evaluateData = new Evaluation();
		 mergeData    = new MergeData();
		 readData	  = new ReadData();
	}

}
