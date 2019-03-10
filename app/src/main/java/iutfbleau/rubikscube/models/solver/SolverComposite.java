package iutfbleau.rubikscube.models.solver;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.cube.Cube;

public class SolverComposite implements Solver
{
	protected ArrayList<SolverComponent> solvers;
	public SolverComposite() 
	{
		solvers = new ArrayList<SolverComponent>();	
	}

	public void addSolver(SolverComponent solver)
	{
		this.solvers.add(solver);
	}
	
	public ArrayList<ArrayList<RotationEvent>> solve(Cube cube)
	{
		Cube copy = new Cube(cube);
		ArrayList<ArrayList<RotationEvent>> rotations = new ArrayList<ArrayList<RotationEvent>>();
		for(int currentSolver = 0; currentSolver < this.solvers.size(); currentSolver++)
		{
			rotations.add(this.solvers.get(currentSolver).solve(copy));
		}
		return rotations;
	}
}
