package cancer_heterogeneity;

public class Driver 
{
	public static void main(String[] args) 
	{
		DataSet genes = new DataSet("ge", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\mRNA_Gene_expression2.txt", "");
		
		DataSet mutationC5 = new DataSet("snl", "C:\\Users\\blackhole\\Desktop\\cancerRepo\\CancerHeterogeneity\\java\\src\\snl\\C5.txt", "C5");
	}
}
