package bearmaps;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class KDTree implements PointSet {
    private Node root;

    private class Node {
        Point val;
        int depth;

        Node(Point p, Node l, Node r, int d) {
            val = p;
            left = l;
            right = r;
            depth = d;
        }

        Node left;
        Node right;
    }

    public KDTree(List<Point> points) {
        for (Point point : points) {
            root = insert(point, root, 0);
        }
    }

    private Node insert(Point point, Node node, int depth) {
        if (node == null)
            return new Node(point, null, null, depth);
        if (node.val.equals(point)) {
            return node; // Duplicate node
        } else if (downOrLeft(point, node.val, depth)) {
            node.left = insert(point, node.left, depth + 1);
        } else {
            node.right = insert(point, node.right, depth + 1);
        }
        return node;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(root, target, root.val);
    }

    private Point nearest(Node node, Point target, Point best) {
        if (node == null) {
            return best;
        }
        double bestDis = Point.distance(best, target);
        double curDis = Point.distance(target, node.val);
        if (Double.compare(bestDis, curDis) > 0) {
            best = node.val;
        }
        Node goodSide;
        Node badeSide;
        boolean next = downOrLeft(target, node.val, node.depth);
        if (next) { // is downer or lefter
            goodSide = node.left;
            badeSide = node.right;
        } else {
            goodSide = node.right;
            badeSide = node.left;
        }
        best = nearest(goodSide, target, best);//first dfs to find the best of goodSide
        if (worthChecking(node, target, best)) {
            best = nearest(badeSide, target, best);
        }
        return best;
    }

    private boolean worthChecking(Node node, Point target, Point best) {
        double bestDis = Point.distance(best, target);
        double distToBad;
        if (node.depth % 2 == 0) {
            distToBad = Point.distance(new Point(node.val.getX(), target.getY()), target);
        } else {
            distToBad = Point.distance(new Point(target.getX(), node.val.getY()), target);
        }
        return Double.compare(distToBad, bestDis) < 0;
    }

    // Return true if a is at upper or left position of b
    private boolean downOrLeft(Point a, Point b, int depth) {
        if (depth % 2 == 0) {
            return Double.compare(a.getX(), b.getX()) < 0;
        } else {
            return Double.compare(a.getY(), b.getY()) < 0;
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        Point t = new Point(0, 7);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        assertTrue(nn.nearest(t.getX(), t.getY()).equals(p6));
    }
}
