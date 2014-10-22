import java.util.Random;


public class PercolationStats {

	 final int INVALID_VAL = -1;
	 float []fractionOfPercolation;
	 Random mRandomNumberGenerator;
	 final int mNoOfTestCases;
	 float mMean;
	 float mStdDev;
	 
	 public PercolationStats(int N, int T)
	 {
		 fractionOfPercolation = new float[T];
		 mRandomNumberGenerator = new Random();
		 mNoOfTestCases = T;
		 mMean = INVALID_VAL;
		 mStdDev = INVALID_VAL;
		 for (int i = 0; i < T ; i++)
		 {
			 
			 fractionOfPercolation[i] = calculatePercolation(N); 
			 
		 }
		 
		 
	 }
	 
	 
	 public float mean()
	 {
		 //calculate just once
		 if (mMean != INVALID_VAL)
		 {
			 return mMean;
			 
		 }
		 
		 float sum = 0;
		 
		 for (int i = 0; i < mNoOfTestCases; i++)
		 {
			 sum += fractionOfPercolation[i];
			 //System.out.println( fractionOfPercolation[i]  + " : Sum : " +  sum); 
		 }
		 
		 mMean = sum/mNoOfTestCases;
		 
		 return mMean;
	 }
	 
	 
	 float standardDev()
	 {
		 if (mStdDev != INVALID_VAL)
		 {
			 
			 return mStdDev;
		 }
		 
		 float SquaredSum = 0;
		 float tempMean = mean();
		 
		 for (int i =0; i < mNoOfTestCases; i++)
		 {
			 
			 float diff =  fractionOfPercolation[i] - tempMean;
			 SquaredSum = diff * diff;
		 }
		 
		 double squareStdDev = SquaredSum/(mNoOfTestCases-1);
		 
		 mStdDev = (float) Math.sqrt(squareStdDev);
		 
		 return mStdDev;
		 
	 }
	 
	 
	 float calculatePercolation(int N)
	 {
		 Percolation percMatrix =  new Percolation(N);
		 
		 //while the system does not percolates
		 while(false == percMatrix.percolates())
		 {
			
			 //choose random square
			 int randomRow =  mRandomNumberGenerator.nextInt(N);
			 int randomCol =  mRandomNumberGenerator.nextInt(N);
			 
			 //open it
			 percMatrix.open(randomRow, randomCol);
			 
			 
		 }
		 //OK system has percolated , lets return
		 
		 //System.out.println(percMatrix.getTotalOpenSites());
		 float fraction = ((float)percMatrix.getTotalOpenSites())/(N*N);
		 
		 return fraction;
		 
		 
	 }
	 
	 
	 float calculateConfidenceLo()
	 {
		 float tempMean = mean();
		 float tempStdDev = standardDev();
		 
		 float cLo =  (float) (tempMean - ((1.96 * tempStdDev)/Math.sqrt(mNoOfTestCases)));
		 
		 return cLo;
	 }
	
	 float calculateConfidenceHi()
	 {
		 float tempMean = mean();
		 float tempStdDev = standardDev();
		 
		 float cHi =  (float) (tempMean + ((1.96 * tempStdDev)/Math.sqrt(mNoOfTestCases)));
		 
		 return cHi;
	 } 
	 
	 

	 
 
}
