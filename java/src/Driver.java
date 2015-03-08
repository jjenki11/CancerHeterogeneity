package cancer_heterogeneity;

public class Driver 
{
	public static void main(String[] args) 
	{		
		//	mRNA Gene Expression data
		DataSet genes = new DataSet("ge", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\mRNA_Gene_expression2.txt", "", null, null);
		
		//	Single Nucleotide Level data
		DataSet mutationC5 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\C5.txt", "C5", null, null);
		DataSet mutationC8 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\C8.txt", "C8", null, null);
		DataSet mutationD10 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\D10.txt", "D10", null, null);
		DataSet mutationF2 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\F2.txt", "F2", null, null);
		DataSet mutationG8 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\G8.txt", "G8", null, null);
		DataSet mutationG9 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\G9.txt", "G9", null, null);
		DataSet mutationOt8873 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\Ot8873.txt", "Ot8873", null, null);

		//	Cancer Nucleotide Variation data
		DataSet cancerC5 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\C5.txt", "C5", null, null);
		DataSet cancerC8 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\C8.txt", "C8", null, null);
		DataSet cancerD10 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\D10.txt", "D10", null, null);
		DataSet cancerF2 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\F2.txt", "F2", null, null);
		DataSet cancerG8 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\G8.txt", "G8", null, null);
		DataSet cancerG9 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\G9.txt", "G9", null, null);
		DataSet cancerOt8873 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\Ot8873.txt", "Ot8873", null, null);
		
		//	Drug Type data
		DataSet drugTypes = new DataSet("dt", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\type_of_drugs.txt","", null, null);
		
		//	Drug Sensitivity data
		DataSet sensC5 = new DataSet("ds", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\C5.txt","C5", null, null);
		DataSet sensC8 = new DataSet("ds", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\C8.txt","C8", null, null);
		DataSet sensD10 = new DataSet("ds", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\D10.txt","D10", null, null);
		DataSet sensF2 = new DataSet("ds", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\F2.txt","F2", null, null);
		DataSet sensG8 = new DataSet("ds", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\G8.txt","G8", null, null);
		DataSet sensG9 = new DataSet("ds", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\drug_data\\G9.txt","G9", null, null);
		
		//	Drug Efficacy data
		DataSet effC5 = new DataSet("ef", "", "", sensC5, drugTypes);
		DataSet effC8 = new DataSet("ef", "", "", sensC8, drugTypes);
		DataSet effD10 = new DataSet("ef", "", "", sensD10, drugTypes);
		DataSet effF2 = new DataSet("ef", "", "", sensF2, drugTypes);
		DataSet effG8 = new DataSet("ef", "", "", sensG8, drugTypes);
		DataSet effG9 = new DataSet("ef", "", "", sensG9, drugTypes);
		
	}
}
