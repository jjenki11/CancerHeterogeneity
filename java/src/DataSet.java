package cancer_heterogeneity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataSet 
{
	
	CHUtilities utils;	// define our utilities class to be used in DataSet file reading, etc
	
	
	// This is the Drug Sensitivity Dataset object
	class DrugSensitivityData
	{
		BTree<String, DrugSensitivity> sensTree;
		
		ArrayList<String> sample_id_list;
		
		public DrugSensitivityData(String filename)
		{
			// Do something with the file
			ArrayList<DrugSensitivity> sensList = utils.readData.readSensitivityList(filename);
			sample_id_list = new ArrayList<String>();
			sensTree = new BTree<String, DrugSensitivity>();
			sensTree = makeSensTree(sensList);
		}
		
		public BTree<String, DrugSensitivity> makeSensTree(ArrayList<DrugSensitivity> drugList)
		{
			BTree<String, DrugSensitivity> sensTree = new BTree<String, DrugSensitivity>();
			for(int i =0;i<drugList.size();i++)
			{
				sample_id_list.add(drugList.get(i).sample_ID);
				sensTree.put(drugList.get(i).sample_ID, drugList.get(i));
			}
			return sensTree;
		}
		
	} DrugSensitivityData ds;
	
	class DrugTypes
	{
		BTree<String, DrugType> typeTree;
		
		
		public DrugTypes(String filename)
		{
			ArrayList<DrugType> drugList = utils.readData.readDrugTypeList(filename);
			typeTree = new BTree<String, DrugType>();
			
			typeTree = makeTypeTree(drugList);			
			
		}
		public BTree<String, DrugType> makeTypeTree(ArrayList<DrugType> drugList)
		{
			BTree<String, DrugType> mutationTree = new BTree<String, DrugType>();
			for(int i =0;i<drugList.size();i++)
			{
				mutationTree.put(drugList.get(i).sample_ID, drugList.get(i));
			}
			return mutationTree;
		}	
		
	} DrugTypes dt;
	
	// This is the Drug Efficacy object
	
	class DrugEfficacyObj
	{
		// a is sens, b is type		
		
		ArrayList<DrugEfficacy> effList;
		public DrugEfficacyObj(DataSet a, DataSet b)
		{
			//ArrayList<DrugEfficacy> effList = utils.readData.read
			effList = new ArrayList<DrugEfficacy>();
			effList = performMerge(a,b);
			
			printEfficacyObjects();
		}
		
		public ArrayList<DrugEfficacy> performMerge(DataSet a, DataSet b)
		{
			ArrayList<String> sid_list = a.ds.sample_id_list;
			
			int len = sid_list.size();
			ArrayList<DrugEfficacy> effs = new ArrayList<DrugEfficacy>();
			DrugEfficacy de;
			
			DrugSensitivity dss;
			DrugType dtt;
			
			for(int i = 0; i < len; i++)
			{
				dss = a.ds.sensTree.get(sid_list.get(i));
				dtt = b.dt.typeTree.get(sid_list.get(i));
				de = new DrugEfficacy();
				
				de.protocol=dss.protocol;
				de.sample_ID_sens=dss.sample_ID;		
				de.DATA0=dss.DATA0;
				de.DATA1=dss.DATA1;
				de.DATA2=dss.DATA2;
				de.DATA3=dss.DATA3;
				de.DATA4=dss.DATA4;
				de.DATA5=dss.DATA5;
				de.DATA6=dss.DATA6;
				de.DATA7=dss.DATA7;
				de.DATA8=dss.DATA8;
				de.DATA9=dss.DATA9;
				de.DATA10=dss.DATA10;		
				de.C0=dss.C0;
				de.C1=dss.C1;
				de.C2=dss.C2;
				de.C3=dss.C3;
				de.C4=dss.C4;
				de.C5=dss.C5;
				de.C6=dss.C6;
				de.C7=dss.C7;
				de.C8=dss.C8;
				de.C9=dss.C9;
				de.C10=dss.C10;
				de.gene_sens=dss.gene;		
				
				de.sample_ID_type=dtt.sample_ID;
				de.gene_symbol_type=dtt.gene_symbol;
				de.sample_name=dtt.sample_name;
				de.ncgc_UID=dtt.ncgc_UID;
				de.pri_mech_action=dtt.pri_mech_action;
				effs.add(de);
			}			
			return effs;			
		}
		
		public void printEfficacyObjects()
		{
			
			for(int i = 0;i < effList.size(); i++)
			{
				System.out.println(effList.get(i).getStrings());
			}
			
		}
		
		
		
	} DrugEfficacyObj de;
	
	
	// This is the DNA Mutations Dataset Object
	
	class SingleNucleotideLevel{		
		/**
		 * Some internal tools here ~
		 * 
		 * 
		 */
		public SingleNucleotideLevel(String filename, String type)
		{
			// Do something with the file
			ArrayList<Mutation> mutationList = utils.readData.readSNLList(filename, type);
			
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
	} SingleNucleotideLevel snl;
	
	class CancerNucleotideVariation
	{		
		
		public CancerNucleotideVariation(String filename, String type)
		{
			// Do something with the file
			ArrayList<Cancer> cancerList = utils.readData.readCancerList(filename, type);
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
	} CancerNucleotideVariation cnv;
	
	
	// This is the mRNA expression Dataset object
	class GeneExpression
	{		
		public GeneExpression(String filename)
		{			
			ArrayList<Gene> geneList                = utils.readData.readGeneList(filename);			
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
	} GeneExpression ge;	

	
	public DataSet(String type, String filename, String opt, DataSet a, DataSet b)
	{
		utils = new CHUtilities();
		if(type == "ds")
		{
			ds = new DrugSensitivityData(filename);
		}
		if(type == "dt")
		{
			dt = new DrugTypes(filename);
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
		if(type == "ef")
		{
			de = new DrugEfficacyObj(a, b);
		}
		
	}
	

	
	
}
