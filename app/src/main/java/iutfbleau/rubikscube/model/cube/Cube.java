package iutfbleau.rubikscube.model.cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import iutfbleau.rubikscube.model.rotation.Rotation;
import iutfbleau.rubikscube.model.rotation.RotationCube;
import iutfbleau.rubikscube.model.rotation.RotationX;
import iutfbleau.rubikscube.model.rotation.RotationY;
import iutfbleau.rubikscube.model.rotation.RotationZ;
import iutfbleau.rubikscube.model.shuffle.Shuffle;
import iutfbleau.rubikscube.model.observer.Observable;
import iutfbleau.rubikscube.model.observer.Observer;

public class Cube implements Observable, Observer
{
	public static final int TOP   = 0;
	public static final int LEFT  = 1;
	public static final int FRONT = 2;
	public static final int RIGHT = 3;
	public static final int BACK  = 4;
	public static final int DOWN  = 5;
	
	protected Square[] squares;
	protected int size;
	protected List<Observer> observers;
	
	public Cube(int size)
	{
		this.size = size;
		this.squares = new Square[6];
		
		for(int color = 0; color < 6; color++)
		{
			this.squares[color] = new Square(this.size,color);
		}
		this.observers = new ArrayList<>();
	}
	
	public Cube(Cube copy)
	{
		this.size = copy.size;
		this.squares = new Square[6];
		for(int i = 0; i < 6; i++)
		{
			this.squares[i] = new Square(copy.getSquare(i));
		}
		this.observers = new ArrayList<>();
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
			if(!this.squares[i].isSolved())
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
		this.notifyObservers() ;
		new RotationCube().rotate(this,direction);
	}
	
	public void rotate(int direction, int index)
	{
		this.notifyObservers() ;
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

	@Override
	public void addObserver(Observer newObserver) 
	{
		this.observers.add(newObserver);
	}

	@Override
	public void notifyObservers() 
	{
		for(int i = 0; i < this.observers.size(); i++)
		{
			this.observers.get(i).update(this,0);
		}
		
	}

	@Override
	public void update(Observable observable, Object object) 
	{
		this.rotate((Integer[]) object);
	}
}
