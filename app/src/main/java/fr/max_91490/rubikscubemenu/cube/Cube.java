package fr.max_91490.rubikscubemenu.cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.max_91490.rubikscubemenu.rotation.*;
import fr.max_91490.rubikscubemenu.shuffle.Shuffle;

public class Cube
{
    public static final int TOP   = 0;
    public static final int LEFT  = 1;
    public static final int FRONT = 2;
    public static final int RIGHT = 3;
    public static final int BACK  = 4;
    public static final int DOWN  = 5;

    protected Square[] squares;
    protected int size;

    public Cube(int size)
    {
        this.size = size;
        this.squares = new Square[6];

        for(int color = 0; color < 6; color++)
        {
            this.squares[color] = new Square(this.size,color);
        }
    }

    public Cube(Cube copy)
    {
        this.size = copy.size;
        this.squares = new Square[6];
        for(int i = 0; i < 6; i++)
        {
            this.squares[i] = new Square(copy.getSquare(i));
        }
    }

    public void shuffle(Shuffle s)
    {
        s.shuffle(this);
    }

    public Square getSquare(int position)
    {
        return this.squares[position];
    }

    public int getSize()
    {
        return this.size;
    }

    public boolean isSolved()
    {
        for(int i = 0; i < this.size; i++)
        {
            if(this.squares[i].isSolved() == false)
            {
                return false;
            }
        }
        return true;
    }

    public void rotate(Integer[] rotation)
    {
        if(rotation[1] == -1)
            this.rotate(rotation[0]);
        else
            this.rotate(rotation[0], rotation[1]);
    }

    public void rotate(int direction)
    {
        new RotationCube().rotate(this,direction);
    }

    public void rotate(int direction, int index)
    {
        if ((direction == Rotation.RIGHT) || (direction == Rotation.LEFT))
        {
            new RotationX().rotate(this, direction, index);
        }
        else if ((direction == Rotation.UP) || (direction == Rotation.DOWN))
        {
            new RotationY().rotate(this, direction, index);
        }
        else if ((direction == Rotation.CLOCKWISE) || (direction == Rotation.COUNTERCLOCKWISE))
        {
            new RotationZ().rotate(this, direction, index);
        }
    }

    public boolean isSameCube(Cube cube)
    {
        for(int i = Cube.TOP; i < Cube.DOWN; i++)
        {
            if(!Arrays.deepEquals(this.squares[i].getColors(), cube.squares[i].getColors()))
            {
                return false;
            }
        }

        return true;
    }



    public String toString()
    {
        String str = "";

        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < 2*this.size+1; j++)
            {
                str = str + " ";
            }
            str = str + this.squares[TOP].toStringRow(i) + '\n';
        }
        str = str + '\n';
        for(int i = 0; i < this.size; i++)
        {
            str = str + this.squares[LEFT].toStringRow(i) +  " " + this.squares[FRONT].toStringRow(i) + " " + this.squares[RIGHT].toStringRow(i) + " " + this.squares[BACK].toStringRow(i) + '\n';
        }
        str = str + '\n';
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < 2*this.size+1; j++)
            {
                str = str + " ";
            }
            str = str + this.squares[DOWN].toStringRow(i) + '\n';
        }

        return str;
    }
}
