package cancer_heterogeneity;

public class Mutation 
{
	
	/*
	 * 
	 * 0) var_index, //IGNORED
	 * 1) chrom,
	 * 2) left,
	 * 3) right,	
	 * 4) ref_seq,
	 * 5) var_type,
	 * 6) zygosity,
	 * 7) var_seq1,
	 * 8) var_seq2,
	 * 9) var_score,
	 * 10) not_ref_score,
	 * 11) coverage,
	 * 12) read_count1,
	 * 13) read_count2,
	 * 14) conservation, //IGNORED
	 * 15) gene_name,
	 * 16) transcript_name,
	 * 17) where_in_transcript,
	 * 18) change_type1,
	 * 19) ref_peptide1,
	 * 20) var_peptide1,
	 * 21) change_type2,
	 * 22) ref_peptide2,
	 * 23) var_peptide2,
	 * 24) dbsnp,
	 * 25) dbsnp_build
	 */
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
	
	public Mutation()
	{
		// IGNORE 0 and 14
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
	}
}
