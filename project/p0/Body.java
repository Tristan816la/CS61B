public class Body {
    public final double gConstant = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }

    public double calcForceExertedBy(Body b) {
        return ((gConstant * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2));
    }

    public double calcForceExertedByX(Body b) {
        return (this.calcForceExertedBy(b) * (b.xxPos - xxPos)) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        return (this.calcForceExertedBy(b) * (b.yyPos - yyPos)) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] all) {
        double result = 0;
        for (Body var : all) {
            if (var.equals(this))
                continue;
            result += calcForceExertedByX(var);
        }
        return result;
    }

    public double calcNetForceExertedByY(Body[] all) {
        double result = 0;
        for (Body var : all) {
            if (var.equals(this))
                continue;
            result += calcForceExertedByY(var);
        }
        return result;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}