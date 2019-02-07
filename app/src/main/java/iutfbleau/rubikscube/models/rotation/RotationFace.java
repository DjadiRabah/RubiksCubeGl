package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.face.Face;
import iutfbleau.rubikscube.models.cube.piece.Piece;

public class RotationFace implements Rotation
{	
	protected Piece[][] transposeMatrix(Piece[][] pieces)
	{
		int size = pieces.length;
        Piece[][] temp = new Piece[size][size];
        for (int i = 0; i < size; i++)
        {
        	for (int j = 0; j < size; j++)
        	{
        		temp[i][j] = pieces[j][i];
            }
        }
        return temp;
    }
	
	protected void reverseRows(Face face)
    {
		int size = face.getSize();
    	for(int i = 0; i < size - 2; i++)
    	{
    		Piece[] temp = face.getRow(i);
    		face.setRow(i, face.getRow(size - i - 1));
    		face.setRow(size-i-1, temp);
    	}
    }
	
	protected void reverseCols(Face face)
    {
		int size = face.getSize();
    	for(int i = 0; i < size - 2; i++)
    	{
    		Piece[] temp = face.getCol(i);
    		face.setCol(i, face.getCol(size - i - 1));
    		face.setCol(size-i-1, temp);
    	}
    }
	
	
	public void rotate(Face face, int direction)
	{
		if(direction == CLOCKWISE)
		{
			face.setSquare(this.transposeMatrix(face.getPieces()));
			this.reverseCols(face);
		}
		else if(direction == COUNTERCLOCKWISE)
		{
			face.setSquare(this.transposeMatrix(face.getPieces()));
			this.reverseRows(face);
		} 
	}
}
