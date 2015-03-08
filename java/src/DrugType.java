package cancer_heterogeneity;

public class DrugType 
{
	/*
	 * Sample ID,
	 * Structure,
	 * Sample Name,
	 * Drug Name,
	 * NCGC Unique ID,
	 * Primary Mechanism of Action,
	 * Gene Symbol
	 */
	
	String sample_ID;
	String gene_symbol;
	String sample_name;
	String ncgc_UID;
	String pri_mech_action;
	
	
	public DrugType()
	{
		sample_ID="";
		gene_symbol="";
		//structure="";
		sample_name="";
		//drug_name="";
		ncgc_UID="";
		pri_mech_action="";
		
	}
	
	public void printDT()
	{
		System.out.print(sample_ID+", "+gene_symbol+", "+sample_name+", "+ncgc_UID+", "+pri_mech_action+"\n");
	}
}
