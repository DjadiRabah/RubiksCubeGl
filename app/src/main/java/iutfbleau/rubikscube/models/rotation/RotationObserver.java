package iutfbleau.rubikscube.models.rotation;

import java.util.ArrayDeque;
import java.util.Deque;

import iutfbleau.rubikscube.models.observer.Observable;
import iutfbleau.rubikscube.models.observer.Observer;

public class RotationObserver implements Observer
{
	Deque<Integer[]> rotations;
	
	public RotationObserver() 
	{
		this.rotations =  new ArrayDeque<>();
	}
	
	public Integer[] pollLast()
	{
		return this.rotations.pollLast();
	}
	
	public boolean isEmpty()
	{
		return this.rotations.isEmpty();
	}

	@Override
	public void update(Observable observable, Object object)
	{
		this.rotations.addLast((Integer[]) object);		
	}
}
