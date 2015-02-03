package cancer_heterogeneity;


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
	
	Filter filter = new Filter();
	Evaluation evaluation = new Evaluation();
	MergeData mergeData = new  MergeData();
	
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
	 * API access
	 */
	public CHUtilities() 
	{
						
	}

}
