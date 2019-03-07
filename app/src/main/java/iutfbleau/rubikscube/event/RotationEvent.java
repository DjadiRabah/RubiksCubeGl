package iutfbleau.rubikscube.event;

import iutfbleau.rubikscube.models.rotation.Rotation;

public class RotationEvent implements Event
{
	private int direction;
	private int oppositeDirection;
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
			this.oppositeDirection = Rotation.RIGHT;
		else if(direction == Rotation.RIGHT)
			this.oppositeDirection = Rotation.LEFT;
		else if(direction == Rotation.UP)
			this.oppositeDirection = Rotation.DOWN;
		else if(direction == Rotation.DOWN)
			this.oppositeDirection = Rotation.UP;
		else if(direction == Rotation.CLOCKWISE)
			this.oppositeDirection = Rotation.COUNTERCLOCKWISE;
		else if(direction == Rotation.COUNTERCLOCKWISE)
			this.oppositeDirection = Rotation.CLOCKWISE;
	}

	public int getDirection()
	{
		return this.direction;
	}
	public int getOppositeDirection()
	{
		return this.oppositeDirection;
	}
	public int getIndex()
	{
		return this.index;
	}

}
