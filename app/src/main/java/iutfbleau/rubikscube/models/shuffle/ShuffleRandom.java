package iutfbleau.rubikscube.models.shuffle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import iutfbleau.rubikscube.models.cube.cube.Cube;

public class ShuffleRandom implements Shuffle
{
	@Override
	public Deque<Integer[]> shuffle(Cube cube)
	{
		Deque<Integer[]> rotations =  new ArrayDeque<>();
		for(int i = 0; i < 100000; i++)
		{
			Random r = new Random();
			int direction = r.nextInt(6);
			int index = r.nextInt(cube.getSize());
			cube.rotate(direction, index);
		}
		return rotations;
	}
}
