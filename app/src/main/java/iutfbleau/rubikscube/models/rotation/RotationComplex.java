package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.Cube;

public interface RotationComplex extends Rotation
{
	public void rotate(Cube cube, int direction, int index);
}
