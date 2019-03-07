package iutfbleau.rubikscube.event;

import iutfbleau.rubikscube.models.rotation.Rotation;

public class RotationEvent implements Event
{
	private int direction;
	private int reverseDirection;
	private int index;
	public RotationEvent(int direction, int index) 
	{
		this.direction = direction;
		this.setReverseDirection(direction);
		this.index = index;
	}

	private void setReverseDirection(int direction)
	{
		if(direction == Rotation.LEFT)
			this.reverseDirection = Rotation.RIGHT;
		else if(direction == Rotation.RIGHT)
			this.reverseDirection = Rotation.LEFT;
		else if(direction == Rotation.UP)
			this.reverseDirection = Rotation.DOWN;
		else if(direction == Rotation.DOWN)
			this.reverseDirection = Rotation.UP;
		else if(direction == Rotation.CLOCKWISE)
			this.reverseDirection = Rotation.COUNTERCLOCKWISE;
		else if(direction == Rotation.COUNTERCLOCKWISE)
			this.reverseDirection = Rotation.CLOCKWISE;
	}

	public int getDirection()
	{
		return this.direction;
	}
	public int getReverseDirection()
	{
		return this.reverseDirection;
	}
	public int getIndex()
	{
		return this.index;
	}

}
