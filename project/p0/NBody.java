
public class NBody {
    public static String background = "images/starfield.jpg";

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        in.readDouble();
        Body[] result = new Body[num];
        for (int i = 0; i < num; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String n = in.readString();
            result[i] = new Body(x, y, xV, yV, m, n);
        }
        return result;
    }

    public static void main(String args[]) {
        StdDraw.enableDoubleBuffering();

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] bodies = NBody.readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Body planet : bodies) {
            planet.draw();
        }
        double time = 0;

        while (time <= T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, background);
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos,
                    bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}