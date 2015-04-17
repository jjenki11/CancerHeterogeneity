package cancer_heterogeneity;

import java.io.IOException;
import java.util.ArrayList;

public class Driver 
{
	public static void main(String[] args) throws IOException 
	{		
		//	mRNA Gene Expression data
		
		String basePath = "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\"; // JEFF
		
		//String basePath = ""; // RUTGER
		
		DataSet genes = new DataSet("ge", basePath, "", null, null,null,null);
	/*	
		//	Single Nucleotide Level data
		DataSet mutationC5 = new DataSet("snl",  basePath+"snl\\C5.txt", "C5", null, null,null,null);
		DataSet mutationC8 = new DataSet("snl",  basePath+"snl\\C8.txt", "C8", null, null,null,null);
		DataSet mutationD10 = new DataSet("snl",  basePath+"snl\\D10.txt", "D10", null, null,null,null);
		DataSet mutationF2 = new DataSet("snl", basePath+"snl\\F2.txt", "F2", null, null,null,null);
		DataSet mutationG8 = new DataSet("snl", basePath+"snl\\G8.txt", "G8", null, null,null,null);
		DataSet mutationG9 = new DataSet("snl", basePath+"snl\\G9.txt", "G9", null, null,null,null);
		DataSet mutationOt8873 = new DataSet("snl", basePath+"snl\\Ot8873.txt", "Ot8873", null, null,null,null);

		//	Cancer Nucleotide Variation data
		DataSet cancerC5 = new DataSet("cnv", basePath+"cnv\\C5.txt", "C5", null, null,null,null);
		DataSet cancerC8 = new DataSet("cnv", basePath+"cnv\\C8.txt", "C8", null, null,null,null);
		DataSet cancerD10 = new DataSet("cnv", basePath+"cnv\\D10.txt", "D10", null, null,null,null);
		DataSet cancerF2 = new DataSet("cnv", basePath+"cnv\\F2.txt", "F2", null, null,null,null);
		DataSet cancerG8 = new DataSet("cnv", basePath+"cnv\\G8.txt", "G8", null, null,null,null);
		DataSet cancerG9 = new DataSet("cnv", basePath+"cnv\\G9.txt", "G9", null, null,null,null);
		DataSet cancerOt8873 = new DataSet("cnv", basePath+"cnv\\Ot8873.txt", "Ot8873", null, null,null,null);
		
		//  Cancer mutation combined data
		DataSet cmC5 = new DataSet("cm", "", "C5", mutationC5, cancerC5,null,null);
		DataSet cmC8 = new DataSet("cm", "", "C8", mutationC8, cancerC8,null,null);
		DataSet cmD10 = new DataSet("cm", "", "D10", mutationD10, cancerD10,null,null);
		DataSet cmF2 = new DataSet("cm", "", "F2", mutationF2, cancerF2,null,null);
		DataSet cmG8 = new DataSet("cm", "", "G8", mutationG8, cancerG8,null,null);
		DataSet cmG9 = new DataSet("cm", "", "G9", mutationG9, cancerG9,null,null);
		DataSet tumorrr = new DataSet("cm", "", "TUMOR", mutationOt8873, cancerOt8873,null,null);		
				
		ArrayList<ArrayList<String>> mutationLists = new ArrayList<ArrayList<String>>();
		mutationLists.add(cmC5.cmo.mutationIDs);
		mutationLists.add(cmC8.cmo.mutationIDs);
		mutationLists.add(cmD10.cmo.mutationIDs);
		mutationLists.add(cmF2.cmo.mutationIDs);
		mutationLists.add(cmG8.cmo.mutationIDs);
		mutationLists.add(cmG9.cmo.mutationIDs);
		mutationLists.add(tumorrr.cmo.mutationIDs);
		ArrayList<BTree<String, CancerMutation>> treeList = new ArrayList<BTree<String, CancerMutation>>();
		treeList.add(cmC5.cmo.cMutationTree);
		treeList.add(cmC8.cmo.cMutationTree);
		treeList.add(cmD10.cmo.cMutationTree);
		treeList.add(cmF2.cmo.cMutationTree);
		treeList.add(cmG8.cmo.cMutationTree);
		treeList.add(cmG9.cmo.cMutationTree);
		treeList.add(tumorrr.cmo.cMutationTree);		
		DataSet bigboyCancer = new DataSet("macroCancer", basePath+"\\results\\mutations\\", "", null,null,treeList,mutationLists);
		
		
		*/
		//	Drug Type data
		DataSet drugTypes = new DataSet("dt", basePath+"drug_data\\type_of_drugs.txt","", null, null,null,null);
		
		//	Drug Sensitivity data
		DataSet sensC5 = new DataSet("ds", basePath+"drug_data\\C5.txt","C5", null, null,null,null);
		DataSet sensC8 = new DataSet("ds", basePath+"drug_data\\C8.txt","C8", null, null,null,null);
		DataSet sensD10 = new DataSet("ds", basePath+"drug_data\\D10.txt","D10", null, null,null,null);
		DataSet sensF2 = new DataSet("ds", basePath+"drug_data\\F2.txt","F2", null, null,null,null);
		DataSet sensG8 = new DataSet("ds", basePath+"drug_data\\G8.txt","G8", null, null,null,null);
		DataSet sensG9 = new DataSet("ds", basePath+"drug_data\\G9.txt","G9", null, null,null,null);
		
		//	Drug Efficacy data
		DataSet effC5 = new DataSet("ef", "", "", sensC5, drugTypes,null,null);
		DataSet effC8 = new DataSet("ef", "", "", sensC8, drugTypes,null,null);
		DataSet effD10 = new DataSet("ef", "", "", sensD10, drugTypes,null,null);
		DataSet effF2 = new DataSet("ef", "", "", sensF2, drugTypes,null,null);
		DataSet effG8 = new DataSet("ef", "", "", sensG8, drugTypes,null,null);
		DataSet effG9 = new DataSet("ef", "", "", sensG9, drugTypes,null,null);
		
		
/*				
		ArrayList<ArrayList<String>> drugMechLists = new ArrayList<ArrayList<String>>();
		drugMechLists.add(effC5.de.mech_list);
		drugMechLists.add(effC8.de.mech_list);
		drugMechLists.add(effD10.de.mech_list);
		drugMechLists.add(effF2.de.mech_list);
		drugMechLists.add(effG8.de.mech_list);
		drugMechLists.add(effG9.de.mech_list);
		ArrayList<BTree<String, DrugEfficacy>> mechTreeList = new ArrayList<BTree<String, DrugEfficacy>>();
		mechTreeList.add(effC5.de.mechTree);
		mechTreeList.add(effC8.de.mechTree);
		mechTreeList.add(effD10.de.mechTree);
		mechTreeList.add(effF2.de.mechTree);
		mechTreeList.add(effG8.de.mechTree);
		mechTreeList.add(effG9.de.mechTree);		
		DataSet bigboyEfficacyMech = new DataSet("macroDrug", basePath+"\\results\\mech_drug_efficacy\\,mech", "", null, null, mechTreeList, drugMechLists);
*/
		ArrayList<BTree<String,DrugEfficacy>> combTreeList = new ArrayList<BTree<String, DrugEfficacy>>();
		combTreeList.add(effC5.de.combinedTree);
		combTreeList.add(effC8.de.combinedTree);
		combTreeList.add(effD10.de.combinedTree);
		combTreeList.add(effF2.de.combinedTree);
		combTreeList.add(effG8.de.combinedTree);
		combTreeList.add(effG9.de.combinedTree);
		
		ArrayList<ArrayList<String>> drugCombinedLists = new ArrayList<ArrayList<String>>();
		drugCombinedLists.add(effC5.de.combined_id_list);
		drugCombinedLists.add(effC8.de.combined_id_list);
		drugCombinedLists.add(effD10.de.combined_id_list);
		drugCombinedLists.add(effF2.de.combined_id_list);
		drugCombinedLists.add(effG8.de.combined_id_list);
		drugCombinedLists.add(effG9.de.combined_id_list);
				
		DataSet bigboyEfficacyCombined = new DataSet("macroDrug", basePath+"\\results\\combined_drug_efficacy\\,combined", "", null, null, combTreeList, drugCombinedLists);
		
	}
}
