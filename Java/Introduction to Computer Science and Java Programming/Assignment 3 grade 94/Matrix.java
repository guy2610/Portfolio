package mmn13lastq;

/**
 * A class used to represent and manage information of a matrix 
 * @author Guy Even
 */ 
public class Matrix {

	private int [][]mt;
	/** 
	 *  * Constructs a Matrix from a two-dimensional array.
	 *   The dimensions as well as the values of this matrix will be the same as the dimensions and values of the two-dimensional array.
	 * @param array The mt based on which the new one will be constructed.
	 */
	public Matrix( int[][] array )
	{ 
		int rows=array.length;
		int columns=array[0].length;
		mt=new int[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				mt[i][j]=array[i][j];

			}


		}
	}
	/** 
	 *  Constructs a size1 by size2 matrix of zeros
	 * @param size1  The number of rows of the new matrix.
	 * @param size2  The number of columns of the new matrix.
	 */
	public Matrix(int size1,int size2)
	{
		mt=new int [size1][size2];
		for(int i=0;i<size1;i++)
		{
			for(int j=0;j<size2;j++)
			{
				mt[i][j]=0;
			}



		}

	}
	/**
	 * Creates a string representation of the matrix, divided by tabs
	 *  @return A string representation of the matrix, divided by tabs. 
	 */
	public String toString()

	{
		String str="";
		int rows=mt.length;
		int columns=mt[0].length;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(j==0)
					str+=mt[i][j];
				else
					str=str+"\t"+mt[i][j];

			}
			str+="\n";


		}
		return str;

	}
/**
 *  Creates a vertically-flipped copy of the matrix.
 * @return The vertically-flipped copy of the matrix.
 */
	public Matrix flipVertical ()
	{ 
		int rows=mt.length;
		int columns=mt[0].length;
		int r=mt.length;
		int[][] tmp=new int [rows][columns];
		 
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				tmp[r-1-i][j]=mt[i][j];

			}



		}
		return  new Matrix(tmp);

	}
	/**
	 * Creates an horizontally-flipped copy of the matrix.
	 * @return The horizontally-flipped copy of the matrix. 
	 */
	public Matrix flipHorizontal ()
	{
		int rows=mt.length;
		int columns=mt[0].length;
		int c=mt[0].length;
		int[][] tmp=new int [rows][columns];
		 
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				tmp[i][c-1-j]=mt[i][j];

			}



		}
		return  new Matrix(tmp);

	
	}
	/**
	 *  Rotates the matrix by 90 degrees clockwise. (use a private method)
	 * @return The rotated matrix
	 */
	public Matrix rotateClockwise()
	{
		int rows=mt.length;
		int columns=mt[0].length;
		
		int[][] tmp=new int [columns][rows];
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				tmp[j][i]=mt[i][j];

			}
		}
		
		return  new Matrix(this.flipHorizontal(tmp));

	
}
	
	private int [][] flipHorizontal (int [][] temp)/*private method which get a two-dimensional array
	and return  horizontally-flipped version.
	used in rotateClockwise() method
	*/
	{
		int rows=temp.length;
		int columns=temp[0].length;
		int c=temp[0].length;
		int[][] t=new int [rows][columns];
		 
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				t[i][c-1-j]=temp[i][j];

			}



		}
		return t;
	}
	
	private int [][] flipVertical (int [][] temp)/*private method which get a two-dimensional array
	and return  vertically-flipped version.
	used in rotateCounterClockwise() method.
	*/
	{
		int rows=temp.length;
		int columns=temp[0].length;
		int r=temp.length;
		int[][] t=new int [rows][columns];
		 
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
			
				t[r-1-i][j]=temp[i][j];
			}



		}
		return t;
	}
	/**
	 *  Rotates the matrix by 90 degrees counter clockwise(use a private method)
	 * @return  The rotated matrix. 
	 */
	public Matrix rotateCounterClockwise()
	{int rows=mt.length;
	int columns=mt[0].length;
	
	int[][] tmp=new int [columns][rows];

	for(int i=0;i<rows;i++)
	{
		for(int j=0;j<columns;j++)
		{
			tmp[j][i]=mt[i][j];

		}
	}
	
	return  new Matrix(this.flipVertical(tmp));
	
}
}
