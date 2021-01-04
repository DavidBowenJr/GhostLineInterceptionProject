package am.david.Raster;

import am.david.InterSectStore.InterSectStore;
import am.david.game.Game;
import am.david.game.Scratch;
import am.david.inputs.MouseEvents;
import am.david.mathlib.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TheBigPicture {

    static double mag =0;
    public static Point point;
    static final int MAX_STATE = 3;
    static int state = 1;
    static InterSectStore interSectStore = new InterSectStore();
    static Scratch scratch = null;

    public static Display display;
    public static Game game;
    public TheBigPicture(Display display, Game game) {
        TheBigPicture.display = display;
        TheBigPicture.game = game;
        TheBigPicture.scratch = new Scratch();
    }

    public void go()
    {
        Boolean goandtry = true;
        if(MouseEvents.getMouseEventTrap(goandtry) != null)
        {
            mag = Objects.requireNonNull(MouseEvents.getMouseEventTrap(goandtry)).getPoint().distance(display.GetBitmapBufferWidth()/2, display.GetBitmapBufferHeight()/2);
            point = MouseEvents.getMouseEventTrap(goandtry).getPoint();
            boolean b = Objects.requireNonNull(MouseEvents.getMouseEventTrap(goandtry)).isConsumed();
            MouseEvents.getMouseEventTrap(b);

            FactorProcess(state);
        }
    }


    private void FactorProcess(int xstate) {
        Graphics graphics = display.getStrategy().getDrawGraphics();
        //float fvar =     Float.intBitsToFloat(0x7f800000);

        // System.out.println("fvar " + fvar + " " + 0x7f800000);
        //  System.out.println(" getExp " +  Math.getExponent(Double.MIN_NORMAL));
        // System.out.println("lbtd " + Double.doubleToLongBits(  Double.longBitsToDouble(0x7fefffffffffffffL) ));
//Math.exp()
        double value = 1.2345;
        double mantissa;
        int exponent;

        // mantissa = Math.pow()
        // Math.IEEEremainder()
        //  System.out.println("mantissa " + mantissa);
        switch (xstate)
        {
            case 1:

                display.SurfaceData();

                TheBigPicture.scratch.PlotCrossHair(graphics,new Vector2(320,240), Color.yellow, 240);


                System.out.println("point 1st" + point);
                Vector2 v2Point = new Vector2(point);
                interSectStore.firstPoint = new Vector2(point);

                TheBigPicture.scratch.PlotCrossHair(graphics, interSectStore.firstPoint , Color.GREEN , 2);
                interSectStore.pA = new Vector2(point);
                interSectStore.Origin = new Vector2(display.GetBitmapBufferWidth(), display.GetBitmapBufferHeight());
                TheBigPicture.scratch.CaseUnknownPlotDeltaRelativeToOrigin(interSectStore, graphics, display);
                TheBigPicture.scratch.PlotCrossHair(graphics,interSectStore.pD, Color.RED, 2);
                display.SwapBuffers(null);
                state++;
                break;

            case 2:
                interSectStore.secondPoint = new Vector2(point);
                TheBigPicture.scratch.Plot_slope_MA_fromPointA(graphics, interSectStore );
                state++;
                break;

            case 3:
                interSectStore.thirdPoint = new Vector2(point);
                TheBigPicture.scratch.Plot_slope_MD_fromPointD(graphics, interSectStore);
                state++;
                break;

            case 4:
                Vector2 intercept = TheBigPicture.scratch.lineIntersect(interSectStore.pA, interSectStore.mA, interSectStore.pD, interSectStore.mD);
                interSectStore.pointB =  intercept;

                graphics.setColor(Color.orange);
                graphics.fillOval((int)intercept.x,(int)intercept.y, 10,10);
                graphics.drawOval((int)intercept.x, (int)intercept.y, 4, 4);

                interSectStore.magnitudeOfAToB = Point.distance(interSectStore.pA.x,interSectStore.pA.y, interSectStore.pointB.x,interSectStore.pointB.y);
                interSectStore.magnitudeOfDToB = Point.distance(interSectStore.pD.x,interSectStore.pD.y, interSectStore.pointB.x,interSectStore.pointB.y);

                TheBigPicture.scratch.HaveMagnitudesHaveSlopeWillTravel(graphics,interSectStore);
                // Save the magnitude of our line from A to B and D to B

                //    SaveImage();

                state++;
                //  state = 1;
                break;

            case 5:
                //finishing steps
                //Vector2

                //there is a better way. just a copy because the function will destroy the original instance.

                // interSectStore.pointBOld = new Vector2(interSectStore.pointB);
                //  intercept = TheBigPicture.scratch.lineIntersect(interSectStore.pA, interSectStore.mA, interSectStore.pD, interSectStore.mD);
                //intercept = TheBigPicture.scratch.lineIntersect(interSectStore.pD, -interSectStore.mA, interSectStore.pointB, -interSectStore.mD);
                //  graphics.setColor(Color.orange);
                //   interSectStore.pointB =  intercept;

                // graphics.fillOval((int)intercept.x,(int)intercept.y, 10,10);
                //  graphics.drawOval((int)intercept.x, (int)intercept.y, 4, 4);

//                interSectStore.magnitudeOfAToB = Point.distance(interSectStore.pA.x,interSectStore.pA.y, interSectStore.pointB.x,interSectStore.pointB.y);
//                interSectStore.magnitudeOfDToB = Point.distance(interSectStore.pD.x,interSectStore.pD.y, interSectStore.pointB.x,interSectStore.pointB.y);

//                interSectStore.magnitudeOfAToB = Point.distance(interSectStore.pA.x,interSectStore.pD.y, interSectStore.pointB.x,interSectStore.pointB.y);
//                interSectStore.magnitudeOfDToB = Point.distance(interSectStore.pD.x,interSectStore.pA.y, interSectStore.pointB.x,interSectStore.pointB.y);


// second time around we have the non modified point.B who is old
                //    TheBigPicture.scratch.HaveMagnitudesHaveSlopeWillTravelOp(graphics,interSectStore);

                //    TheBigPicture.scratch.QuestToSolveForB(graphics,interSectStore);
                TheBigPicture.scratch.QuestToSolveForBB(graphics,interSectStore);



                state = 1;
                break;

            default:
                state = 1;
                break;

        }
    }

    public void SaveImage()
    {
        Rectangle screenRect =  game.getCanvas().getBounds();

        Point canvasPoint = new Point(game.getCanvas().getLocationOnScreen());
        Point framePoint = new Point( game.getFrame().getLocationOnScreen());
        Point menuPointWH = new Point( (canvasPoint.x - framePoint.x) , (canvasPoint.y - framePoint.y));

        int locX = (int)(game.getFrame().getLocationOnScreen().x + menuPointWH.x);
        int locY = (int)(game.getFrame().getLocationOnScreen().y + menuPointWH.y);

        screenRect.setLocation(locX, locY);

        try {
            Robot robot = new Robot();

            String format = "png";
            String fileName = " Puppy_Fixed." + format;

            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A full screenshot saved!");
        }

        catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

}
