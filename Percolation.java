
public class Percolation {

	private int [][]mGrid;
	WeightedUnion mWU;
	int mSize ;
	int mOpenSites;
	final int V_TOP_INDEX = 0;
	final int V_BOT_INDEX;
	
	 public Percolation(int N)               // create N-by-N grid, with all sites blocked
	 {
		 
		 V_BOT_INDEX = N*N +1;
		 mGrid = new int[N][N];
		 mWU = new WeightedUnion(N*N+2); //0 for vtop and N*N+1 for vBottom
		 //all sites blocked, set as 0, open sites as 1
		 for (int i = 0 ; i < N ; i++)
		 {
			 for (int j = 0 ; j < N ; j++)
			 {
				 mGrid[i][j] = 0;
				 
			 }
		 }
		 mSize  = N;
		 mOpenSites = 0;
		 
	 }
	
	 
	 public int getTotalOpenSites()
	 {
		 return mOpenSites;
	 }
	 
	 private int calcIndex(int i, int j )
	 {
		 //index of vTop is 0
		 // index of vBott is N*N+2
		 return i*mSize+j+1;
	 }
	 
	 
	void makeConnect(int rowA, int colA, int rowB, int colB)
	 {
		   int indexA = calcIndex(rowA, colA);
		   int indexB = calcIndex(rowB, colB);
		   
		   mWU.union(indexA, indexB);
		 
	 }
	
	 boolean isOpen(int row, int col)
	 {
		 
		 return (mGrid[row][col] == 1);
	 }
	
	boolean withinBounds(int row, int col)
	{
		return (row >=0 && col >= 0 && row < mSize && col < mSize);
		
	}
	 
	void connectNearBySites(int row, int col)
	{
	   
		int nearRow ;
		int nearCol	;
		int origSiteIndex = calcIndex(row, col);
		
		//top
		nearRow = row - 1;
		nearCol = col;
		//if valid and open ... connect
		if ( withinBounds(nearRow, nearCol)&& isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		
		//top right
		nearRow = row - 1;
		nearCol = col + 1;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol) && isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		
		//top left
		nearRow = row - 1;
		nearCol = col - 1 ;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol)&& isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}	
		
		
		
		//left
		nearRow = row ;
		nearCol = col - 1;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol) && isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		//right
		nearRow = row ;
		nearCol = col + 1;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol) && isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		//bottom left
		nearRow = row + 1;
		nearCol = col - 1;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol) && isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		
		//bottom 
		nearRow = row + 1;
		nearCol = col ;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol) && isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		//bottom right
		nearRow = row + 1;
		nearCol = col + 1;
		//if valid and open ... connect
		if (withinBounds(nearRow, nearCol) && isOpen(nearRow,nearCol))
		{
			
			int index = calcIndex(nearRow, nearCol);
			mWU.union(origSiteIndex, index);
		}
		
		//if its top row ..connect with vTop
		if (row == 0)
		{
			
			mWU.union(origSiteIndex, V_TOP_INDEX);
		}
		
		//if bottom..connect with v bot
		
		if (row == (mSize-1))
		{
			
			mWU.union(origSiteIndex, V_BOT_INDEX);
		}
		
	}
	
	public void open(int i, int j) 
	   {
		   
			//if its already open .. return
		//System.out.println(mOpenSites);
		  if (isOpen(i, j))
		  {
			  
			  return;
		  }
		
		   //open it
		   mGrid[i][j] = 1;
		   mOpenSites++;
		   //check for nearby squares and connect will all open ones
		   connectNearBySites(i, j);
		   
		   
		   
	   }
	 
	
	public boolean percolates()
	{
		
		return mWU.isConnected(V_TOP_INDEX, V_BOT_INDEX);
	}
	
	
}
