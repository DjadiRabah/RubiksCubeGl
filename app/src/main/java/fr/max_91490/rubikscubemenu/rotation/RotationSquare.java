package fr.max_91490.rubikscubemenu.rotation;

import fr.max_91490.rubikscubemenu.cube.Piece;
import fr.max_91490.rubikscubemenu.cube.Square;

public class RotationSquare implements Rotation
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

    protected void reverseRows(Square square)
    {
        int size = square.getSize();
        for(int i = 0; i < size - 2; i++)
        {
            Piece[] temp = square.getRow(i);
            square.setRow(i, square.getRow(size - i - 1));
            square.setRow(size-i-1, temp);
        }
    }

    protected void reverseCols(Square square)
    {
        int size = square.getSize();
        for(int i = 0; i < size - 2; i++)
        {
            Piece[] temp = square.getCol(i);
            square.setCol(i, square.getCol(size - i - 1));
            square.setCol(size-i-1, temp);
        }
    }


    public void rotate(Square square, int direction)
    {
        if(direction == Rotation.CLOCKWISE)
        {
            square.setSquare(this.transposeMatrix(square.getPieces()));
            this.reverseCols(square);
        }
        else if(direction == Rotation.COUNTERCLOCKWISE)
        {
            square.setSquare(this.transposeMatrix(square.getPieces()));
            this.reverseRows(square);
        }
    }
}