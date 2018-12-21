package iutfbleau.rubikscube.model.shuffle;

import java.util.Deque;

import iutfbleau.rubikscube.model.cube.Cube;

public interface Shuffle 
{
	public Deque<Integer[]> shuffle(Cube cube);
}
