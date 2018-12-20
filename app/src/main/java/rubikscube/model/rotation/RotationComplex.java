package rubikscube.model.rotation;

import rubikscube.model.cube.Cube;

public interface RotationComplex extends Rotation
{
	public void rotate(Cube cube,int direction, int index);
}
