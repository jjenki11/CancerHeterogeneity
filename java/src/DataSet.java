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
		
		BTree<String, DrugEfficacy> combinedTree;
		BTree<String, ArrayList<DrugEfficacy>> mechTree;
		BTree<String, ArrayList<DrugEfficacy>> geneTree;
		
		ArrayList<DrugEfficacy> effList;
		public DrugEfficacyObj(DataSet a, DataSet b)
		{
			//ArrayList<DrugEfficacy> effList = utils.readData.read
			effList = new ArrayList<DrugEfficacy>();
			effList = performMerge(a,b);			
			//printEfficacyObjects();
			
			geneTree = buildGeneTree(effList);
			mechTree = buildMechanismTree(effList);
			combinedTree = buildCombinedTree(effList);
		}
		
		public ArrayList<DrugEfficacy> performMerge(DataSet a, DataSet b)
		{
			ArrayList<String> sid_list = a.ds.sample_id_list;
			
			int len = sid_list.size();
			ArrayList<DrugEfficacy> effs = new ArrayList<DrugEfficacy>();
			DrugEfficacy de;
			
			DrugSensitivity dss;
			DrugType dtt;
			
			boolean missingName;
			
			for(int i = 0; i < len; i++)
			{
				missingName = false;
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
				
				if((de.gene_sens == null) && (de.gene_symbol_type == null))
				{
					System.out.println("BOTH GENE NAMES WERE NULL! DO NOT ADD ME");
					missingName = true;
				}
				else if((de.gene_sens == null) && (de.gene_symbol_type != null))
				{
					de.gene_sens = de.gene_symbol_type;
				}
				else if((de.gene_sens != null) && (de.gene_symbol_type == null))
				{
					de.gene_symbol_type = de.gene_sens;
				}
				else
				{
					//System.out.println("FOUND BOTH NAMES!");
				}
				if(!missingName)
				{
					de.combined_ID = de.gene_symbol_type+de.pri_mech_action;				
					effs.add(de);
				}
			}			
			return effs;			
		}
		
		public BTree<String, ArrayList<DrugEfficacy>> buildGeneTree(ArrayList<DrugEfficacy> list)
		{
			BTree<String, ArrayList<DrugEfficacy>> geneTree = new BTree<String, ArrayList<DrugEfficacy>>();
			ArrayList<DrugEfficacy> l;
			for(int i =0;i<list.size();i++)
			{
				if(geneTree.get(list.get(i).gene_symbol_type) == null)
				{
					l = new ArrayList<DrugEfficacy>();
					l.add(list.get(i));
					geneTree.put(list.get(i).gene_symbol_type, l);
				}
				else
				{
					l = geneTree.get(list.get(i).gene_symbol_type);
					l.add(list.get(i));
					geneTree.put(list.get(i).gene_symbol_type, l);
				}				
			}
			return geneTree;
		}
		
		public BTree<String, ArrayList<DrugEfficacy>> buildMechanismTree(ArrayList<DrugEfficacy> list)
		{
			BTree<String, ArrayList<DrugEfficacy>> priTree = new BTree<String, ArrayList<DrugEfficacy>>();
			ArrayList<DrugEfficacy> l;
			for(int i =0;i<list.size();i++)
			{
				if(priTree.get(list.get(i).pri_mech_action) == null)
				{
					l = new ArrayList<DrugEfficacy>();
					l.add(list.get(i));
					priTree.put(list.get(i).pri_mech_action, l);
				}
				else
				{
					l = priTree.get(list.get(i).pri_mech_action);
					l.add(list.get(i));
					priTree.put(list.get(i).pri_mech_action, l);
				}				
			}
			return priTree;
		}
		
		public BTree<String, DrugEfficacy> buildCombinedTree(ArrayList<DrugEfficacy> list)
		{
			BTree<String, DrugEfficacy> combTree = new BTree<String, DrugEfficacy>();
			for(int i =0;i<list.size();i++)
			{
				combTree.put(list.get(i).combined_ID, list.get(i));
			}
			return combTree;
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
		BTree<String, Mutation> combinedIDTree;
		BTree<String, ArrayList<Mutation>> mutationTree;
		ArrayList<String> combined_id_list;
		
		public SingleNucleotideLevel(String filename, String type)
		{
			// Do something with the file
			combined_id_list = new ArrayList<String>();
			ArrayList<Mutation> mutationList = utils.readData.readSNLList(filename, type);
			
			combinedIDTree = new BTree<String, Mutation>();
			mutationTree = new BTree<String, ArrayList<Mutation>>();
			
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
				combined_id_list.add(mutationList.get(i).Combined_ID);
				mutationTree.put(mutationList.get(i).Combined_ID, mutationList.get(i));
			}
			return mutationTree;
		}		
	} SingleNucleotideLevel snl;
	
	class CancerNucleotideVariation
	{		
		ArrayList<String> combined_id_list;
		BTree<String, Cancer> cancerTree;
		public CancerNucleotideVariation(String filename, String type)
		{
			// Do something with the file
			combined_id_list = new ArrayList<String>();
			ArrayList<Cancer> cancerList = utils.readData.readCancerList(filename, type);
			cancerTree = new BTree<String, Cancer>();
			cancerTree = makeCombinedIDTree(cancerList,type);
		}
		// Filters out normal (non-cancer) dna  variations by comparing
		// to healthy sister
		
		public BTree<String, Cancer> makeCombinedIDTree(ArrayList<Cancer> cancerList, String type)
		{
			BTree<String, Cancer> cancerTree = new BTree<String, Cancer>();
			for(int i =0;i<cancerList.size();i++)
			{
				combined_id_list.add(cancerList.get(i).Combined_ID);
				cancerTree.put(cancerList.get(i).Combined_ID, cancerList.get(i));				
			}
			return cancerTree;
		}		
		
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
	
	
	class CancerMutationObj
	{
		ArrayList<CancerMutation> cMutationList;
		
		ArrayList<String> mutationIDs;
		
		BTree<String, CancerMutation> cMutationTree;
		
		public CancerMutationObj(DataSet a, DataSet b, String label)
		{
			mutationIDs = new ArrayList<String>();
			cMutationList = new ArrayList<CancerMutation>();
			cMutationTree = new BTree<String, CancerMutation>();
			cMutationList = performMerge(a,b,label);
			
			//printCancerMutationObjects();
		}
		
		public ArrayList<CancerMutation> performMerge(DataSet a, DataSet b, String l)
		{
			ArrayList<String> sid_list = new ArrayList<String>();
			System.out.println("Now merging SNL and CNV files for: "+l);
			sid_list = a.snl.combined_id_list;
			
			int len = sid_list.size();
			ArrayList<CancerMutation> muts = new ArrayList<CancerMutation>();
			CancerMutation cm;
			
			Mutation mut;
			Cancer can;
			
			int reject = 0;
			int kept = 0;
			
			for(int i = 0; i < len; i++)
			{
				mut=new Mutation();
				can=new Cancer();
				mut = a.snl.combinedIDTree.get(sid_list.get(i));
				//System.out.println(mut.Combined_ID);
				
				can = b.cnv.cancerTree.get(sid_list.get(i));
				//System.out.println("Size of cancer tree: " + b.cnv.cancerTree.size() + " |  Size of mutation tree: "+a.snl.combinedIDTree.size());
				
				cm = new CancerMutation();
				
				/*From snl*/
				if(	(can != null) && (mut != null) && 
						(can.Combined_ID.equals(mut.Combined_ID)) // this was the problem.... :(
				){
					//System.out.println("mutation key: "+(mut == null ? "NOTHING" : mut.Combined_ID) + "   |||   cancer key: "+(can == null ? "NOTHING" : can.Combined_ID));
					cm.Combined_ID=mut.Combined_ID;
					cm.clone_type=mut.clone_type;
					cm.chrom=mut.chrom;
					cm.left=mut.left;
					cm.right=mut.right;
					cm.ref_seq=mut.ref_seq;
					cm.var_type=mut.var_type;
					cm.zygosity=mut.zygosity;
					cm.var_seq_1=mut.var_seq_1;
					cm.var_seq_2=mut.var_seq_2;
					cm.var_score=mut.var_score;
					cm.not_ref_score=mut.not_ref_score;
					cm.coverage=mut.coverage;
					cm.read_count_1=mut.read_count_1;
					cm.read_count_2=mut.read_count_2;
					cm.gene_name=mut.gene_name;
					cm.transcript_name=mut.transcript_name;
					cm.where_in_transcript=mut.where_in_transcript;
					cm.change_type_1=mut.change_type_1;
					cm.ref_peptide_1=mut.ref_peptide_1;
					cm.var_peptide_1=mut.var_peptide_1;
					cm.change_type_2=mut.change_type_2;
					cm.ref_peptide_2=mut.ref_peptide_2;
					cm.var_peptide_2=mut.var_peptide_2;
					cm.dbsnp=mut.dbsnp;
					cm.dbsnp_build=mut.dbsnp_build;
					
					/*From cnv*/
					cm.kind_cancer=can.kind_cancer;
					cm.ref_cancer=can.ref_cancer;
					cm.var_seq_cancer=can.var_seq_cancer;
					boolean foundit = false;
					for(int aa = 0; aa < mutationIDs.size(); aa++){
						if(mutationIDs.get(aa).equals(cm.Combined_ID)){
							foundit = true;
						}
					}
					if(!foundit)
					{
						mutationIDs.add(cm.Combined_ID);
					}
					cMutationTree.put(cm.Combined_ID, cm);
					
					muts.add(cm);
					kept++;
				}
				else
				{
					reject++;
					
				}
			}			
			System.out.println("Kept: "+kept);
			System.out.println("Rejected: "+reject);
			return muts;			
		}
		
		public void printCancerMutationObjects()
		{			
			for(int i = 0;i < cMutationList.size(); i++)
			{
				System.out.println(cMutationList.get(i).getStrings());
			}			
		}
		
	} CancerMutationObj cmo;
	
	
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
	
	class MacroDataObject
	{
		
		
		public MacroDataObject(ArrayList<BTree<String,CancerMutation>> trees, ArrayList<ArrayList<String>> ids, String fn) throws IOException
		{
			int nTrees = trees.size();
			int nIDs = ids.size();
			
			BTree<String,CancerMutation> currTree = new BTree<String, CancerMutation>();
			ArrayList<String> currList = new ArrayList<String>();
			CancerMutation cmTmp = new CancerMutation();
			
			boolean found = false;
			int numFound = 0;
			
			String[] labels = { "C5", "C8", "D10", "F2" , "G8", "G9", "Toomie" };
			
			String filename = fn+"\\mutation_shizzle";
			/*
			 * Key (Gene+Location ID)
			     ref_seq,var_type,zygosity,var_seq1,transcript_name,where_in_transcript,change_type1,ref_peptide1,var_peptide1,1 clone which has this unique mutation
			 */
			// iterate thru trees
			String str = "";
			for(int i = 0; i < nTrees; i++)
			{
				
				currList = ids.get(i);
				for(int j = 0; j < currList.size(); j++)
				{
					numFound = 0;
					found = false;
					for(int k = i; k < nTrees; k++)
					{
						if(i != k)
						{
							currTree = trees.get(k);
							if(currTree.get(currList.get(j)) != null)
							{
								
								found = true;
								cmTmp = currTree.get(currList.get(j));
								str = cmTmp.Combined_ID+","+cmTmp.ref_seq+","+cmTmp.var_type+","+cmTmp.zygosity+","+cmTmp.var_seq_1+","+cmTmp.transcript_name+","+
										cmTmp.where_in_transcript+","+cmTmp.change_type_1+","+cmTmp.ref_peptide_1+","+cmTmp.var_peptide_1+","+labels[k];
								utils.writeData.writeList(filename+"_any.txt", str);
								numFound++;
							}
						}
						else
						{							
							if(found)
							{
								currTree = trees.get(i);
								cmTmp = currTree.get(currList.get(j));
								str = cmTmp.Combined_ID+","+cmTmp.ref_seq+","+cmTmp.var_type+","+cmTmp.zygosity+","+cmTmp.var_seq_1+","+cmTmp.transcript_name+","+
										cmTmp.where_in_transcript+","+cmTmp.change_type_1+","+cmTmp.ref_peptide_1+","+cmTmp.var_peptide_1+","+labels[k];
								utils.writeData.writeList(filename+"_any.txt", str);
								numFound++;
							}
						}						
					}
					if(!found)
					{
						currTree = trees.get(i);
						cmTmp = currTree.get(currList.get(j));
						str = cmTmp.Combined_ID+","+cmTmp.ref_seq+","+cmTmp.var_type+","+cmTmp.zygosity+","+cmTmp.var_seq_1+","+cmTmp.transcript_name+","+
								cmTmp.where_in_transcript+","+cmTmp.change_type_1+","+cmTmp.ref_peptide_1+","+cmTmp.var_peptide_1+","+labels[i];
						utils.writeData.writeList(filename+"_unique.txt", str);
					}
					
					if(numFound == (nTrees-1))
					{
						
						for(int k = 0; k < nTrees; k++)
						{
							currTree = trees.get(k);
							if(currTree.get(currList.get(j)) != null)
							{
								cmTmp = currTree.get(currList.get(j));
								str = cmTmp.Combined_ID+","+cmTmp.ref_seq+","+cmTmp.var_type+","+cmTmp.zygosity+","+cmTmp.var_seq_1+","+cmTmp.transcript_name+","+
										cmTmp.where_in_transcript+","+cmTmp.change_type_1+","+cmTmp.ref_peptide_1+","+cmTmp.var_peptide_1+","+labels[k];
								utils.writeData.writeList(filename+"_all.txt", str);
							}				
						}
					}					
				}
				
			}			
		}
	} MacroDataObject mdo;

	
	public DataSet(String type, String filename, String opt, DataSet a, DataSet b, ArrayList<BTree<String,CancerMutation>> trees, ArrayList<ArrayList<String>> ids) throws IOException
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
		if(type == "cm")
		{
			cmo = new CancerMutationObj(a, b, opt);
		}
		if(type == "macro")
		{
			mdo = new MacroDataObject(trees, ids, filename);
		}
		
	}
	

	
	
}
