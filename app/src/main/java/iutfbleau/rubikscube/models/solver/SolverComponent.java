package iutfbleau.rubikscube.models.solver;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.cube.Cube;

public abstract class SolverComponent implements Solver
{
	protected ArrayList<RotationEvent> rotations;
	
	public SolverComponent()
	{
		this.rotations = new ArrayList<RotationEvent>();
	}
	public abstract ArrayList<RotationEvent> solve(Cube cube);
}
