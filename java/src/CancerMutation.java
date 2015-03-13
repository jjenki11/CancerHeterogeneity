package cancer_heterogeneity;

public class CancerMutation 
{
	/*FROM SNL*/
	
	String Combined_ID;
	String clone_type;
	String chrom;
	String left;
	String right;
	String ref_seq;
	String var_type;
	String zygosity;
	String var_seq_1;
	String var_seq_2;
	String var_score;
	String not_ref_score;
	String coverage;
	String read_count_1;
	String read_count_2;
	String gene_name;
	String transcript_name;
	String where_in_transcript;
	String change_type_1;
	String ref_peptide_1;
	String var_peptide_1;
	String change_type_2;
	String ref_peptide_2;
	String var_peptide_2;
	String dbsnp;
	String dbsnp_build;
	
	/* FROM CNV*/
	
	String kind_cancer;
	String ref_cancer;
	String var_seq_cancer;

	public CancerMutation()
	{
		Combined_ID="";
		clone_type="";
		chrom="";
		left="";
		right="";
		ref_seq="";
		var_type="";
		zygosity="";
		var_seq_1="";
		var_seq_2="";
		var_score="";
		not_ref_score="";
		coverage="";
		read_count_1="";
		read_count_2="";
		gene_name="";
		transcript_name="";
		where_in_transcript="";
		change_type_1="";
		ref_peptide_1="";
		var_peptide_1="";
		change_type_2="";
		ref_peptide_2="";
		var_peptide_2="";
		dbsnp="";
		dbsnp_build="";
		
		kind_cancer="";
		ref_cancer="";
		var_seq_cancer="";
	}
	
	public String getStrings()
	{
		String x =
				Combined_ID+", "+
				clone_type+", "+
				chrom+", "+
				left+", "+
				right+", "+
				ref_seq+", "+
				var_type+", "+
				zygosity+", "+
				var_seq_1+", "+
				var_seq_2+", "+
				var_score+", "+
				not_ref_score+", "+
				coverage+", "+
				read_count_1+", "+
				read_count_2+", "+
				gene_name+", "+
				transcript_name+", "+
				where_in_transcript+", "+
				change_type_1+", "+
				ref_peptide_1+", "+
				var_peptide_1+", "+
				change_type_2+", "+
				ref_peptide_2+", "+
				var_peptide_2+", "+
				dbsnp+", "+
				dbsnp_build+", "+
				
				kind_cancer+", "+
				ref_cancer+", "+
				var_seq_cancer;
		return x;
	}
	
}
