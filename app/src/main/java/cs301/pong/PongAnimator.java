package cs301.pong;

import android.graphics.*;
import android.view.MotionEvent;
import java.util.Random;
import cs301.animation.Animator;

/**
 * class that animates a ball bouncing around the screen
 * the balls bounces off three of the walls and a paddle
 * the ball goes thru the fourth wall
 *
 * @author Riley Snook
 *
 */
public class PongAnimator implements Animator {

    //instance variables
    Random rand = new Random();
    int xPos;
    int yPos;
    double xVel;
    double yVel;

    //constructor that calls the random ball method
    public PongAnimator() {
        randomBall();
    }

    /*
        Randomizes the balls position and velocity when
        a new ball is made(screen is tapped)
     */
    public void randomBall() {
        xPos = rand.nextInt(2048);
        yPos = rand.nextInt(500);

        xVel = rand.nextInt(30) + 20;
        yVel = rand.nextInt(30) + 20;
    }

    /**
     * Interval between animation frames
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 7;
    }

    /**
     * The background color: black.
     *
     * @return the background color onto which we will draw the image.
     */
    public int backgroundColor() {
        // create/return the background color
        return Color.rgb(0, 0, 0);
    }

    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {

        //updates the position of the ball
        xPos += xVel;
        yPos += yVel;

        //finds width and height of animation surface
        int height = g.getHeight();
        int width = g.getWidth();

        //checks to see if the ball hit either
        // of the left or right walls
        //if so changes the x Velocity so it bounces off
        if (xPos >= width - 50 && xVel > 0) {
            xVel *= -1;
        }
        if (xPos < 50 && xVel < 0) {
            xVel *= -1;
        }
        //checks to see if the ball hit the top wall
        //if so changes the y Velocity so it bounces off
        if (yPos < 50 && yVel < 0) {
            yVel *= -1;
        }

        //checks to see if the ball hits the paddle
        //if so chances  y velocity to bounce off
        if (xPos > (width / 2) - 150 && xPos < (width / 2) + 150
                && yPos >= height - 120 && yPos < height && yVel > 0) {
            yVel *= -1;
        }

        /**
         *checks to see if ball left screen if so
         *creates a new ball
         * i left this out because i made it so a new ball
         * is launched when the user presses the screen
         */
        /*
        if( yPos > 1390){
            //spit out a new ball with random speed and coordinates
           randomBall();
        }
        */

        // Draw the ball in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(xPos, yPos, 50, redPaint);
        redPaint.setColor(0xff0000ff);

        //draws paddle in stationary position
        Paint paddlePaint = new Paint();
        paddlePaint.setColor(Color.CYAN);
        Rect paddle = new Rect((width / 2) - 150, height - 60, (width / 2) + 150, height);
        g.drawRect(paddle, paddlePaint);

    }

    /**
     * Tells that we never pause.
     *
     * @return indication of whether to pause
     */
    public boolean doPause() {
        return false;
    }

    /**
     * Tells that we never stop the animation.
     *
     * @return indication of whether to quit.
     */
    public boolean doQuit() {
        return false;
    }

    /**
     * adds a new ball when the screen is touched
     */
    public void onTouch(MotionEvent event) {
        //adds a new ball everytime the user touches the screen
        randomBall();

    }
}
