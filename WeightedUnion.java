
public class WeightedUnion {
	
	private int [] mNode;
	private int [] mCount;
	
	
	WeightedUnion(int N)
	{
		
		mNode = new int [N];
		mCount = new int [N];
		
		for (int i = 0; i < N; i++)
		{
			mNode[i] = i;
			mCount[i] = 1;
			
		}
		
		
	}
	
	
	int findRoot(int i)
	{
		
		int temp = i;
		
		while (mNode[temp] != temp)
		{
			//goto parent
			temp = mNode[temp];
			
		}
	
		//path Compression
		mNode[i] = temp;
		
		return temp;
		
	}
	
	
	boolean isConnected (int i, int j)
	{
		return (findRoot(i) == findRoot(j));
	}
	
	
	void union(int p, int q)
	{
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		
		if (rootP != rootQ)
		{
			//check count
			
			if (mCount[rootP] >= mCount[rootQ])
			{
				
				//add root of Q as subtree of P
				mNode[rootQ] = rootP;
				//add count of Q root to P root
				mCount[rootP] += mCount[rootQ];
			}
			else
			{	
				//Q has more nodes
				
				//add root of Q as subtree of P
				mNode[rootP] = rootQ;
				//add count of Q root to P root
				mCount[rootQ] += mCount[rootP];
				
			}
			
			
		}
		
		
		
		
	}
	
	

}
