package iutfbleau.rubikscube.event;

public class RotationEvent implements Event
{
	private int direction;
	private int index;
	public RotationEvent(int direction, int index) 
	{
		this.direction = direction;
		this.index = index;
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	
	public int getIndex()
	{
		return this.index;
	}

}
