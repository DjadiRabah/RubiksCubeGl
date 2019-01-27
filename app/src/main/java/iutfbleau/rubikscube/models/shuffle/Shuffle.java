package iutfbleau.rubikscube.models.shuffle;

import java.util.Deque;

import iutfbleau.rubikscube.models.cube.cube.Cube;

public interface Shuffle 
{
	public Deque<Integer[]> shuffle(Cube cube);
}
