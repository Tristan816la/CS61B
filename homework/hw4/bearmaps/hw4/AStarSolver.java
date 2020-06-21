package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private final double INF = Double.POSITIVE_INFINITY;
    private double usedTime = 0;
    private SolverOutcome outcome;
    private double solutionWeight;
    private int numberVisited = 0;
    private LinkedList<Vertex> solutions = new LinkedList<>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        Map<Vertex, Double> disTo = new HashMap<>();
        Map<Vertex, Vertex> edgeBack = new HashMap<>();
        Stopwatch sw = new Stopwatch();
        disTo.put(start, 0.0);
        pq.add(start, disTo.get(start));
        while (pq.size() != 0) {
            if (timeout <= usedTime) {
                outcome = SolverOutcome.TIMEOUT;
                return;
            } else if (pq.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = disTo.get(end);

                // Add Vertex to the solution
                Vertex current = pq.getSmallest();
                solutions.addFirst(current);
                while (!current.equals(start)) {
                    solutions.addFirst(edgeBack.get(current));
                    current = edgeBack.get(current);
                }
                return;
            } else {
                numberVisited++;
                outcome = SolverOutcome.UNSOLVABLE;
                Vertex relaxingV = pq.removeSmallest();
                List<WeightedEdge<Vertex>> allEdges = input.neighbors(relaxingV);
                while (allEdges.size() != 0) {
                    WeightedEdge<Vertex> current = allEdges.remove(0);
                    Vertex neighbor = current.to();
                    if (!disTo.containsKey(neighbor)) {
                        disTo.put(neighbor, INF);
                    }
                    double weight = current.weight();
                    if (disTo.get(relaxingV) + weight < disTo.get(neighbor)) {
                        disTo.put(neighbor, disTo.get(relaxingV) + weight);
                        // Add this to get the sequence edge to go back to the start
                        edgeBack.put(neighbor, relaxingV);
                        double changedNeighborDistance = disTo.get(neighbor) + input.estimatedDistanceToGoal(neighbor, end);
                        if (pq.contains(neighbor)) {
                            pq.changePriority(neighbor, changedNeighborDistance);
                        } else {
                            pq.add(neighbor, changedNeighborDistance);
                        }
                    }
                }
            }
            usedTime = sw.elapsedTime();
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solutions;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numberVisited;
    }

    @Override
    public double explorationTime() {
        return usedTime;
    }
}
