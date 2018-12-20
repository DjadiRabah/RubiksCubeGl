package rubikscube.model.rotation;

import java.util.ArrayDeque;
import java.util.Deque;

import rubikscube.model.observer.Observable;
import rubikscube.model.observer.Observer;

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
