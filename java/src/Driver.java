package cancer_heterogeneity;

public class Driver 
{
	public static void main(String[] args) 
	{
		DataSet genes = new DataSet("ge", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\mRNA_Gene_expression2.txt", "");
		
		DataSet mutationC5 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\C5.txt", "C5");
		DataSet mutationC8 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\C8.txt", "C8");
		DataSet mutationD10 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\C5.txt", "D10");
		DataSet mutationF2 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\F2.txt", "F2");
		DataSet mutationG8 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\G8.txt", "G8");
		DataSet mutationG9 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\G9.txt", "G9");
		DataSet mutationOt8873 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\Ot8873.txt", "Ot8873");
		
		DataSet cancerC5 = new DataSet("cnv", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\cnv\\C5.txt", "C5");
		
		
		
	}
}
