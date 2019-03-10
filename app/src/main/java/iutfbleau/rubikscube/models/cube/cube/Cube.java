package iutfbleau.rubikscube.models.cube.cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import iutfbleau.rubikscube.models.cube.face.Face;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.rotation.RotationCube;
import iutfbleau.rubikscube.models.rotation.RotationX;
import iutfbleau.rubikscube.models.rotation.RotationY;
import iutfbleau.rubikscube.models.rotation.RotationZ;
import iutfbleau.rubikscube.models.shuffle.Shuffle;
import iutfbleau.rubikscube.models.observer.Observable;
import iutfbleau.rubikscube.models.observer.Observer;

public class Cube implements Observable, Observer
{
	public static final int TOP   = 0;
	public static final int LEFT  = 1;
	public static final int FRONT = 2;
	public static final int RIGHT = 3;
	public static final int BACK  = 4;
	public static final int DOWN  = 5;
	
	protected Face[] faces;
	protected int size;
	protected List<Observer> observers;
	
	public Cube(int size)
	{
		this.size = size;
		this.faces = new Face[6];
		
		for(int color = 0; color < 6; color++)
		{
			this.faces[color] = new Face(this.size,color);
		}
		this.observers = new ArrayList<>();
	}
	
	public Cube(Cube copy)
	{
		this.size = copy.size;
		this.faces = new Face[6];
		for(int i = 0; i < 6; i++)
		{
			this.faces[i] = new Face(copy.getSquare(i));
		}
		this.observers = new ArrayList<>();
	}

	public void shuffle(Shuffle s)
	{
		s.shuffle(this);
	}
	
	public Face getSquare(int position)
	{
		return this.faces[position];
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public boolean isSolved()
	{
		for(int i = 0; i < this.size; i++)
		{
			if(!this.faces[i].isSolved())
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
			if(!Arrays.deepEquals(this.faces[i].getColors(), cube.faces[i].getColors()))
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
			str = str + this.faces[TOP].toStringRow(i) + '\n';
		}
		str = str + '\n';
		for(int i = 0; i < this.size; i++)
		{
			str = str + this.faces[LEFT].toStringRow(i) +  " " + this.faces[FRONT].toStringRow(i) + " " + this.faces[RIGHT].toStringRow(i) + " " + this.faces[BACK].toStringRow(i) + '\n';
		}
		str = str + '\n';
		for(int i = 0; i < this.size; i++)
		{
			for(int j = 0; j < 2*this.size+1; j++)
			{
				str = str + " ";
			}
			str = str + this.faces[DOWN].toStringRow(i) + '\n';
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
