package cancer_heterogeneity;

public class DrugEfficacy 
{
	
	String protocol;
	String sample_ID_sens;
	
	String DATA0;
	String DATA1;
	String DATA2;
	String DATA3;
	String DATA4;
	String DATA5;
	String DATA6;
	String DATA7;
	String DATA8;
	String DATA9;
	String DATA10;	

	String C0;
	String C1;
	String C2;
	String C3;
	String C4;
	String C5;
	String C6;
	String C7;
	String C8;
	String C9;
	String C10;

	String gene_sens;
	
	String sample_ID_type;
	String gene_symbol_type;
	String sample_name;
	String ncgc_UID;
	String pri_mech_action;
	
	public DrugEfficacy()
	{
		protocol="";
		sample_ID_sens="";		
		DATA0="";
		DATA1="";
		DATA2="";
		DATA3="";
		DATA4="";
		DATA5="";
		DATA6="";
		DATA7="";
		DATA8="";
		DATA9="";
		DATA10="";		
		C0="";
		C1="";
		C2="";
		C3="";
		C4="";
		C5="";
		C6="";
		C7="";
		C8="";
		C9="";
		C10="";
		gene_sens="";		
		sample_ID_type="";
		gene_symbol_type="";
		sample_name="";
		ncgc_UID="";
		pri_mech_action="";
	}
	
	public String getStrings()
	{
		String list = ""+
				protocol+ ", " +
				sample_ID_sens+ ", " +		
				DATA0+ ", " +
				DATA1+ ", " +
				DATA2+ ", " +
				DATA3+ ", " +
				DATA4+ ", " +
				DATA5+ ", " +
				DATA6+ ", " +
				DATA7+ ", " +
				DATA8+ ", " +
				DATA9+ ", " +
				DATA10+ ", " +		
				C0+ ", " +
				C1+ ", " +
				C2+ ", " +
				C3+ ", " +
				C4+ ", " +
				C5+ ", " +
				C6+ ", " +
				C7+ ", " +
				C8+ ", " +
				C9+ ", " +
				C10+ ", " +
				gene_sens+ ", " +		
				sample_ID_type+ ", " +
				gene_symbol_type+ ", " +
				sample_name+ ", " +
				ncgc_UID+ ", " +
				pri_mech_action
				;

		return list;
	}
	
}
