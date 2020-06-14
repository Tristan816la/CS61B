package creatures;

import huglife.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.awt.Color;

public class TestClorus {
    @Test
    public void testAttack() {
        Plip ap = new Plip(3.0);
        Clorus bc = new Clorus(0.5);
        bc.attack(ap);
        assertEquals(3.5, bc.energy(), 0.1);
    }

    @Test
    public void testReplicate() {
        Clorus bc = new Clorus(1.0);
        bc.replicate();
        assertEquals(0.5, bc.energy(), 0.01);
    }


}
