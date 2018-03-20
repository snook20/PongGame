package cs301.pong;

import android.graphics.*;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

import cs301.animation.Animator;

import static android.content.ContentValues.TAG;
import static edu.snook20up.ponggame.R.id.animationSurface;


/**
 * class that animates a ball repeatedly moving diagonally on
 * simple background
 *
 * @author Riley Snook
 *
 */
public class PongAnimator implements Animator {

    // instance variables
    Random rand = new Random();
    private int xPos;
    private int yPos;;
    private double xVel = 70;
    private double yVel = 90;

    //constructor that calls new ball to randomly
    //set positions and velocities
    public PongAnimator(){
        newBall();
    }

    //randomly sets positions and velocities
    public void newBall(){
        xPos = rand.nextInt(2048);
        yPos = rand.nextInt(500);

        xVel = rand.nextInt(80)+20;
        yVel = rand.nextInt(80)+20;
    }

    /**
     * Interval between animation frames
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 20;
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

        xPos += xVel;
        yPos += yVel;

        //finds width and height of animation surface
        int height = g.getHeight();
        int width = g.getWidth();

        //checks to see if the ball hit either
        // of the left or right walls
        //if so changes the x Velocity so it bounces off
        if(xPos >= width-50 || xPos < 50) {
            xVel *= -1;
        }
        //checks to see if the ball hit the top wall
        //if so changes the y Velocity so it bounces off
        if( yPos < 50){
            yVel *= -1;
        }
        //checks to see if the ball hits the paddle
        //if so chances  y velocity to bounce off
        if( xPos >= (width/2)-200 && xPos <= (width/2)+200
                && yPos >= height-150 ) {
           yVel *= -1;
        }

        /**
         *checks to see if ball left screen if so
         *creates a new ball
         * i left this out because i made it so a new ball
         * is launched when the user presses the screen
         */
        if( yPos > height){
            //spit out a new ball with random speed and coordinates
           // newBall();
        }

        // Draw the ball in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(xPos, yPos, 50, redPaint);
        redPaint.setColor(0xff0000ff);

        //draws paddle in stationary position
        Paint paddlePaint = new Paint();
        paddlePaint.setColor(Color.CYAN);
        g.drawRect((width/2)-150,height-60,(width/2)+150, height, paddlePaint);
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
    public void onTouch(MotionEvent event)
    {
        //adds a new ball everytime the user touches the screen
        newBall();

    }



}//class TextAnimator
