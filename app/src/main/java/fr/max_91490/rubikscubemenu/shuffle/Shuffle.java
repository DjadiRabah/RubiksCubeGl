package fr.max_91490.rubikscubemenu.shuffle;

import java.util.Deque;

import fr.max_91490.rubikscubemenu.cube.Cube;

public interface Shuffle
{
    public Deque<Integer[]> shuffle(Cube cube);
}