package iutfbleau.rubikscube.models.solver.solver.beginner;

import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.FirstLayer;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SecondLayer;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverBeginnerCornersTop;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverBeginnerEdgesTop;
import iutfbleau.rubikscube.models.solver.SolverComposite;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverCrossTop;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverFaceTop;
import iutfbleau.rubikscube.models.solver.SolverFirstCross;
import iutfbleau.rubikscube.models.solver.SolverComposite;
import iutfbleau.rubikscube.models.solver.SolverFirstCross;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.FirstLayer;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SecondLayer;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverBeginnerCornersTop;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverBeginnerEdgesTop;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverCrossTop;
import iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components.SolverFaceTop;

public class SolverBeginner extends SolverComposite
{
	public SolverBeginner() 
	{
		super();
		this.addSolver(new SolverFirstCross());
		this.addSolver(new FirstLayer());
		this.addSolver(new SecondLayer());
		this.addSolver(new SolverCrossTop());
		this.addSolver(new SolverFaceTop());
		this.addSolver(new SolverBeginnerCornersTop());
		this.addSolver(new SolverBeginnerEdgesTop());
	}
}
