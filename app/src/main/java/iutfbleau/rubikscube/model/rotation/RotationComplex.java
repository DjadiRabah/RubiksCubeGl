package iutfbleau.rubikscube.model.rotation;

import iutfbleau.rubikscube.model.cube.cube.Cube;

public interface RotationComplex extends Rotation
{
	public void rotate(Cube cube, int direction, int index);
}
