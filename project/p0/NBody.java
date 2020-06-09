public class NBody {
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
}