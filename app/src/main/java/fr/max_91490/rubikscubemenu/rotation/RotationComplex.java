package fr.max_91490.rubikscubemenu.rotation;

import fr.max_91490.rubikscubemenu.cube.Cube;

public interface RotationComplex extends Rotation
{
    public void rotate(Cube cube, int direction, int index);
}