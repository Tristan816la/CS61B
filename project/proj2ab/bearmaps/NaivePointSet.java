package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    List<Point> allPoints;

    public NaivePointSet(List<Point> points) {
        allPoints = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Point result = allPoints.get(0);
        double shortest = Point.distance(target, result);
        for (Point p : allPoints) {
            if (Point.distance(p, target) < shortest) {
                shortest = Point.distance(p, target);
                result = p;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX() + " and " + ret.getY()); // evaluates to 3.3 and 4.4;
    }
}
