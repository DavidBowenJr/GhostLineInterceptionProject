package am.david.game;

import am.david.InterSectStore.InterSectStore;
import am.david.Raster.Display;
import am.david.mathlib.Vector2;

import java.awt.*;
import java.awt.geom.Point2D;

public class Scratch {

    public void PlotCrossHair(Graphics display, Vector2 ordinate, Color color) {
        int hlen = 5;
        display.setColor(color);
        display.drawLine((int) ordinate.x - hlen, (int) ordinate.y + 0, (int) ordinate.x + hlen, (int) ordinate.y + 0);
        display.drawLine((int) ordinate.x + 0, (int) ordinate.y - hlen, (int) ordinate.x + 0, (int) ordinate.y + hlen);
    }

    public void PlotCrossHair(Graphics display, Vector2 ordinate, Color color, int hlen) {
        // int hlen = 15;
        display.setColor(color);
        display.drawLine((int) ordinate.x - hlen, (int) ordinate.y + 0, (int) ordinate.x + hlen, (int) ordinate.y + 0);
        display.drawLine((int) ordinate.x + 0, (int) ordinate.y - hlen, (int) ordinate.x + 0, (int) ordinate.y + hlen);
    }

    public void Plot_slope_MA_fromPointA(Graphics display, InterSectStore interSectStore) {
        int dX = (int) interSectStore.secondPoint.x - (int) interSectStore.pA.x;
        int dY = (int) interSectStore.secondPoint.y - (int) interSectStore.pA.y;

        display.setColor(Color.WHITE);
        display.drawLine((int) interSectStore.secondPoint.x, (int) interSectStore.secondPoint.y, ((int) interSectStore.pA.x), ((int) interSectStore.pA.y));

        double slope = 0;
        if (dX != 0) {
            System.out.println("is not zero");
            slope = (double) dY / (double) dX;
        }
        interSectStore.mA = slope;
    }

    public void Plot_slope_MD_fromPointD(Graphics display, InterSectStore interSectStore) {

        int dX = (int) interSectStore.thirdPoint.x - (int) interSectStore.pD.x;
        int dY = (int) interSectStore.thirdPoint.y - (int) interSectStore.pD.y;

        display.setColor(Color.WHITE);
        display.drawLine((int) interSectStore.thirdPoint.x, (int) interSectStore.thirdPoint.y, ((int) interSectStore.pD.x), ((int) interSectStore.pD.y));

        double slope = 0;
        if (dX != 0) {
            System.out.println("is not zero");
            slope = (double) dY / (double) dX;
        }
        interSectStore.mD = slope;
    }

    public void HaveMagnitudesHaveSlopeWillTravel(Graphics graphics, InterSectStore interSectStore) {
        double magnitudeAToB = interSectStore.magnitudeOfAToB;
        double magnitudeDToB = interSectStore.magnitudeOfDToB;
        Vector2 PointA = interSectStore.pA;
        Vector2 PointD = interSectStore.pD;
        Vector2 PointB = interSectStore.pointB;
        double mA = interSectStore.mA;
        double mD = interSectStore.mD;

        PlotCrossHair(graphics, new Vector2(640 / 2, 480 / 2), Color.WHITE, 280);


        graphics.setColor(Color.YELLOW);

        // Set Lines to new Intersection
        graphics.drawLine(
                (int) (PointA.x), (int) (PointA.y),
                (int) (interSectStore.pointB.x), (int) (interSectStore.pointB.y));
        graphics.drawLine(
                (int) (PointD.x), (int) (PointD.y),
                (int) (interSectStore.pointB.x), (int) (interSectStore.pointB.y));
        // Robot robot = new Robot();



        double dtheta = Math.toDegrees(Math.atan(mA)) + 180;


        dtheta = Math.toRadians(dtheta);


    }


    public Vector2 Translate(Vector2 Vector, Vector2 amount) {
        return new Vector2(amount.x + Vector.x, amount.y + Vector.y);
    }

    public Vector2 Delta(Vector2 A, Vector2 D) {
        return new Vector2(A.x - D.x, A.y - D.y);
    }

    public double CalculateAngle(Vector2 terminal) {
        double rise = (terminal.y);
        double run = (terminal.x);
        double theta = Math.atan2(rise, run);
        return theta;
    }


    // we should use this one I don't need the magnitude for say I think
    public static Point2D ClosestPoint(double x3, double y3, double x1, double y1, double x2, double y2) {
        final Point2D p3 = new Point2D.Double(x3, y3);
        final Point2D p1 = new Point2D.Double(x1, y1);
        final Point2D p2 = new Point2D.Double(x2, y2);
        return ClosestPoint(p1, p2, p3);
    }

    public static Point2D ClosestPoint(Point2D p1, Point2D p2, Point2D p3) {
        final double xDelta = p2.getX() - p1.getX();
        final double yDelta = p2.getY() - p1.getY();
        if ((xDelta == 0) && (yDelta == 0)) {
            throw new IllegalArgumentException("p1 and p2 cannot be the same point");
        }

        final double u = ((p3.getX() - p1.getX()) * xDelta + (p3.getY() - p1.getY()) * yDelta) / (xDelta * xDelta + yDelta * yDelta);

        final Point2D closestPoint;
        if (u < 0) {
            closestPoint = p1;
        } else if (u > 1) {
            closestPoint = p2;
        } else {
            closestPoint = new Point2D.Double(p1.getX() + u * xDelta, p1.getY() + u * yDelta);
        }

        return closestPoint;//.distance(p3);

    }


    public static double distanceToSegment(Point2D p1, Point2D p2, Point2D p3) {
        final double xDelta = p2.getX() - p1.getX();
        final double yDelta = p2.getY() - p1.getY();
        if ((xDelta == 0) && (yDelta == 0)) {
            throw new IllegalArgumentException("p1 and p2 cannot be the same point");
        }

        final double u = ((p3.getX() - p1.getX()) * xDelta + (p3.getY() - p1.getY()) * yDelta) / (xDelta * xDelta + yDelta * yDelta);

        final Point2D closestPoint;
        if (u < 0) {
            closestPoint = p1;
        } else if (u > 1) {
            closestPoint = p2;
        } else {
            closestPoint = new Point2D.Double(p1.getX() + u * xDelta, p1.getY() + u * yDelta);
        }
// Yes the closestPoint is the tangent place on the line from the Brim of our triangles.
        // But here It is just asking for the distance.
        // I have made another function just like this one.
        // But it returns the exact point.
        // This gives us our Coefficient C who is always 90 degrees.
        // With this point we can deduce the height of B Brim, Bridge, Perimeter with respect to the Adjacent line.
        return closestPoint.distance(p3);

    }


    public static double DistancePointSegment(double x3, double y3, double x1, double y1, double x2, double y2) {
        final Point2D p3 = new Point2D.Double(x3, y3);
        final Point2D p1 = new Point2D.Double(x1, y1);
        final Point2D p2 = new Point2D.Double(x2, y2);
        return distanceToSegment(p1, p2, p3);

    }


    public void XXX(Graphics graphics, InterSectStore interSectStore) {
        Vector2 Aa = interSectStore.pA;
        Vector2 Bb = interSectStore.pointB;
        Vector2 Dd = interSectStore.pD;

        Point2D p2d = ClosestPoint(Bb.x, Bb.y, Aa.x, Aa.y, Dd.x, Dd.y);

        graphics.setColor(Color.ORANGE);
        graphics.drawLine((int) Bb.x, (int) Bb.y, (int) p2d.getX(), (int) p2d.getY());

    }

    public void yXXX(Graphics graphics, InterSectStore interSectStore) {
        Vector2 Aa = interSectStore.pA;
        Vector2 Bb = interSectStore.pointB;
        Vector2 Dd = interSectStore.pD;

        Point2D p2d = ClosestPoint(Bb.x, Bb.y, Aa.x, Aa.y, Dd.x, Dd.y);

        graphics.drawLine((int) Bb.x, (int) Bb.y, (int) p2d.getX(), (int) p2d.getY());


        Vector2 DSOrigin = new Vector2(640d / 2d, 480d / 2d);
        // Plot center of screen or what ever we are useing as our Origin
        PlotCrossHair(graphics, DSOrigin, Color.red);

        // This will make D our pivot relative to now our origin.
        Vector2 ADelta = Delta(Aa, Dd);
        Vector2 BDelta = Delta(Bb, Dd);


        // Translate  ADelta and BDelta  to into Quadrant against D  D will be the Arc of our function
        Vector2 aXY = Translate(ADelta, Dd);
        Vector2 bXY = Translate(BDelta, Dd);

        aXY = Translate(ADelta, DSOrigin);
        bXY = Translate(BDelta, DSOrigin);

        // Now we are in our Quadrant let plot... same Arcs relative to D

        graphics.drawLine((int) DSOrigin.x, (int) DSOrigin.y, (int) aXY.x, (int) aXY.y);
        graphics.drawLine((int) DSOrigin.x, (int) DSOrigin.y, (int) bXY.x, (int) bXY.y);
        PlotCrossHair(graphics, aXY, Color.green);
        PlotCrossHair(graphics, bXY, Color.YELLOW);


        // PlotCrossHair(graphics, new Vector2((640d/2d),(480d/2d)), Color.red);
        // Ok we have our point relative to our origin we can complete some task...

        double MagnitudeOfDtoA = Aa.distance(Dd);


        graphics.setColor(Color.ORANGE);
        graphics.drawLine((int) DSOrigin.x, (int) DSOrigin.y, (int) ((MagnitudeOfDtoA / 2d) + DSOrigin.x), (int) DSOrigin.y);

        double theta = CalculateAngle(ADelta);
        double theta1 = CalculateAngle(BDelta);
        double degreeDiff = Math.toDegrees(theta) - Math.toDegrees(theta1);
        double thetaAB = Math.toRadians(degreeDiff);


        double lx = Math.cos(thetaAB);
        double ly = Math.sin(thetaAB);
        PlotCrossHair(graphics, new Vector2(Dd.x - (lx * BDelta.x), Dd.y + (ly * BDelta.y)), Color.PINK);


        {
            double cMagx = (lx * BDelta.x);
            lx = Math.cos(thetaAB + theta1 + theta);
            ly = Math.sin(thetaAB + theta1 + theta);

            Vector2 C = new Vector2((cMagx * lx), (cMagx * ly));

            C.x = C.x + Dd.x;
            C.y = C.y + Dd.y;

            graphics.drawLine((int) C.x, (int) C.y, (int) Bb.x, (int) Bb.y);
            PlotCrossHair(graphics, C, Color.PINK);

        }

        lx = Math.cos(theta);
        ly = Math.sin(theta);
        PlotCrossHair(graphics, new Vector2(DSOrigin.x + (lx * ADelta.x), DSOrigin.y + (ly * ADelta.y)), Color.WHITE);

    }

    public void QuestToSolveForBB(Graphics graphics, InterSectStore interSectStore) {
        XXX(graphics, interSectStore);
    }


    public void CaseUnknownPlotDeltaRelativeToOrigin(InterSectStore interSectStore, Graphics display, Object objectOrigin) {
        Point origin = new Point();
        if (objectOrigin instanceof Display) {
            origin.x = ((Display) objectOrigin).GetBitmapBufferWidth();
            origin.y = ((Display) objectOrigin).GetBitmapBufferHeight();
        }
        if (objectOrigin instanceof Point) {
            origin.x = ((Point) objectOrigin).x;
            origin.y = ((Point) objectOrigin).y;
        }
        if (objectOrigin instanceof Vector2) {
            origin.x = (int) ((Vector2) objectOrigin).x;
            origin.y = (int) ((Vector2) objectOrigin).y;
        }

        // Say we have a display of 100
        // Then   Pivot(50) =   Domain(100 / 2)

        // Say mPosition is at 49
        // Then -1 = mPosition(49) - Pivot(50)

        // Say mPosition is at 50
        // Then 0 = mPosition(50) - Pivot(50)

        // Say we have a mPosition at 51
        // Then  1 =  mPosition(51) - Pivot(50)


        // -99 =  minusPivot(50) - mPosition(49)
        //        -1 =  ( -100 -  -99)
        //        -1 =  (   99 + -100)
        //        -1 =  (   99 -  100)
        //         1 =  (  -99 +  100)
        //         1 =  (  100 +  -99)
        //         1 =  (  100 -   99)
        //       199 =  (   99 - -100)
        //      -199 =  ( -100 +  -99)


        interSectStore.CartesianP = new Vector2(origin.x / 2, origin.y / 2);

        display.setColor(Color.WHITE);
        display.drawLine((int) interSectStore.pA.x, (int) interSectStore.pA.y, (int) interSectStore.CartesianP.x, (int) interSectStore.CartesianP.y);

        int dX = origin.x - (int) interSectStore.pA.x;//   point.x;
        int dY = origin.y - (int) interSectStore.pA.y; ///   point.y;

        display.setColor(Color.MAGENTA);
        display.drawLine((int) interSectStore.CartesianP.x, (int) interSectStore.CartesianP.y, dX, dY);
        interSectStore.pD = new Vector2(dX, dY);

    }




    // Algebra
    public Vector2 lineIntersect(Vector2 l1, double m1, Vector2 l2, double m2) {
        Vector2 temp = new Vector2(0, 0);
        // temp.x = (m1 * l1.x - m2 * l2.x + l2.y - l1.y) / ((m1 - m2));

        temp.x = ((m1 * l1.x) - (m2 * l2.x) + (l2.y - l1.y)) / (m1 - m2);

        temp.x = ((m1 * l1.x) - (m2 * l2.x) + (l2.y - l1.y)) / (m1 - m2);

        temp.y = (m1 * (temp.x - l1.x) + l1.y);
        temp.x = temp.x;

        return temp;
    }

    public void UnitTest() {
        // a run 2 rise 3 pos;
        // d run 1 rise 3 pos;
        double Am = 4.d / 3.d; // Arc base
        double Dm = -(4.d / 5.d); // Arc base Reflective different grade
        Vector2 VA = new Vector2(-3, 0);
        Vector2 VD = new Vector2(5, 0);
        Vector2 Bp = lineIntersect(VA, Am, VD, Dm);
        double AHMag = VA.distance(Bp);
        double DHMag = VD.distance(Bp);
    }
}
