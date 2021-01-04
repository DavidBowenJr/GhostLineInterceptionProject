package am.david.game;

import am.david.InterSectStore.InterSectStore;
import am.david.Raster.Display;
import am.david.Raster.TheBigPicture;
import am.david.gameTime.GameTimer;
import am.david.inputs.KeyBoardEvents;
import am.david.inputs.MouseEvents;
import am.david.inputs.MouseMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Game implements Runnable{

    static Game game;
    static final String NAME = "GhostLines";
    static final int WIDTH = 640;
    static final int HEIGHT = 480;
    public boolean isApplet = false;
    static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
    public JFrame getFrame() { return frame; }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public Canvas getCanvas() { return canvas; }
    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public JFrame frame;
    protected Canvas canvas;
    protected Thread thread;
    protected GameTimer gameTimer = new GameTimer();
    protected Display display = null;
    public KeyBoardEvents keyBoardEvents;
    protected MouseEvents mouseEvent;
    protected MouseMove mouseMoveEvent;

    TheBigPicture theBigPicture = null;
    public Game() {
        Game.game = this;
    }

    public void init()
    {
        if(game.thread.isAlive()) {
            this.canvas.requestFocusInWindow();
            {
                keyBoardEvents = new KeyBoardEvents(this);
                mouseEvent = new MouseEvents(this);
                mouseMoveEvent = new MouseMove(this);
            }
            display = new Display(this, 640, 480, "GhostIntercept");
            theBigPicture = new TheBigPicture(display, this);
        }
    }

    public void run() {

        gameTimer.processTime(this);
    }

    synchronized void destroy()
    {

    }
    synchronized void start()
    {
        thread = new Thread(this, "GameIntercept");
        thread.start();
    }

    synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {

    }
    public static  Point point;

    static InterSectStore interSectStore = new InterSectStore();
    public void render() {
        theBigPicture.go();
        if(KeyBoardEvents.canExit)
        {
            game.frame.dispatchEvent(new WindowEvent(game.frame, WindowEvent.WINDOW_CLOSING));
        }
        if(display != null)
        {
            if(point != null) {

            }
        }
    }

    public void DoRectAndSwap(Display display, Point point) {
        Scratch scratch = new Scratch();
        scratch.UnitTest();
        int w = display.GetBitmapBufferWidth();
        int h = display.GetBitmapBufferHeight();

        int OriginX = w / 2;
        int OriginY = h / 2;
        display.SwapBuffers();
    }

}
