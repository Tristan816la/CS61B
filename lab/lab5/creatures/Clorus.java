package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;

import static huglife.HugLifeUtils.randomEntry;

import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public void move() {
        energy -= 0.3;
    }

    /**
     * Called when this creature attacks C.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    public Creature replicate() {
        energy /= 2;
        Clorus a = new Clorus(energy);
        return a;
    }

    /**
     * Called when this creature chooses stay.
     */
    public void stay() {
        energy -= 0.1;
    }

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plips = new ArrayDeque<>();
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty"))
                emptyNeighbors.add(key);
            if (neighbors.get(key).name().equals("plip"))
                plips.add(key);
        }
        if (emptyNeighbors.size() == 0)
            return new Action(Action.ActionType.STAY);
        else if (!plips.isEmpty())
            return new Action(Action.ActionType.ATTACK, randomEntry(plips));
        else if (energy >= 1.0)
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        else
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }

    public Color color() {
        return color(34, 0, 231);
    }
}
