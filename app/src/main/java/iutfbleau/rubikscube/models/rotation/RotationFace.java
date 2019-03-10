package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.face.Face;

public class RotationFace implements Rotation
{
	protected int[][] transposeMatrix(int[][] colors)
	{
		int size = colors.length;
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				temp[i][j] = colors[j][i];
			}
		}
		return temp;
	}
	
	protected void reverseRows(Face square)
    {
		int size = square.getSize();
		if(size == 2)
		{
			for(int i = 0; i < size - 1; i++)
	    	{
	    		int[] temp = square.getRow(i);
	    		square.setRow(i, square.getRow(size - i - 1));
	    		square.setRow(size-i-1, temp);
	    	}
			
		}
		else
    	for(int i = 0; i < size - 2; i++)
    	{
    		int[] temp = square.getRow(i);
    		square.setRow(i, square.getRow(size - i - 1));
    		square.setRow(size-i-1, temp);
    	}
    }
	
	protected void reverseCols(Face square)
    {
		int size = square.getSize();
		if(size == 2)
			for(int i = 0; i < size - 1; i++)
	    	{
	    		int[] temp = square.getCol(i);
	    		square.setCol(i, square.getCol(size - i - 1));
	    		square.setCol(size-i-1, temp);
	    	}
		else
    	for(int i = 0; i < size - 2; i++)
    	{
    		int[] temp = square.getCol(i);
    		square.setCol(i, square.getCol(size - i - 1));
    		square.setCol(size-i-1, temp);
    	}
    }
	
	
	public void rotate(Face square, int direction)
	{
		if(direction == Rotation.CLOCKWISE)
		{
			square.setFace(this.transposeMatrix(square.getColors()));
			this.reverseCols(square);
		}
		else if(direction == Rotation.COUNTERCLOCKWISE)
		{
			square.setFace(this.transposeMatrix(square.getColors()));
			this.reverseRows(square);
		} 
	}
}
