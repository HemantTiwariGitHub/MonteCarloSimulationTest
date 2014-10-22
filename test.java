
public  class test {

	
	public static void main (String arg[])
	{
		PercolationStats pS = new PercolationStats(Integer.parseInt(arg[0]), Integer.parseInt(arg[1]));
		
		System.out.println("Mean for percolation: " + pS.mean());
		
	}
	
}
