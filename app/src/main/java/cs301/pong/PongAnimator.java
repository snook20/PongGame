package cs301.pong;

import android.graphics.*;
import android.view.MotionEvent;
import java.util.Random;
import cs301.animation.Animator;

/**
 *Animation class for the pong game
 *
 * @author Riley Snook
 *
 */
public class PongAnimator implements Animator {

    //instance variables
    private Random rand = new Random();
    private Paint redPaint = new Paint();
    private int xPos;
    private int yPos;
    private double xVel;
    private double yVel;
    private int paddleSelection;
    private int paddleX;
    private int score;
    private int lives;

    //constructor that calls the random ball method
    public PongAnimator() {
        this.score = 0;
        this.lives = 5;
        redPaint.setColor(Color.RED);
        this.paddleX = 1024;
    }

    /*
        Randomizes the balls position and velocity when
        a new ball is made(screen is tapped)
     */
    public void randomBall() {
        xVel = rand.nextInt(30) + 20;
        yVel = 30;
    }
    //getters and setters for variables
    public void setPaddleSelection(int paddleSelection) {
        this.paddleSelection = paddleSelection;
    }

    public void setPaddleX(int paddleX) {
        this.paddleX = paddleX;
    }

    public double getPaddleX() {
        return paddleX;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }


    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
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

        int width = g.getWidth();
        int height = g.getHeight();

        if( lives == 5 && score == 0 ){
            yPos = height-120;
            xPos = paddleX;
        }

        if( xVel ==0 && yVel == 0)
        {
            xPos = paddleX;
        }


        //updates the position of the ball
        xPos += xVel;
        yPos += yVel;

        //checks to see if player ran out of lives
        if( lives == 0){
            redPaint.setTextSize(100);
            g.drawText("GAME OVER", (width/2-250), height/2, redPaint);
            g.drawText("SCORE: "+(score-5), width/2-225,height/2+150,redPaint);

            return;
        }

        //changes paddle size based on the radio button selection
        int rectWidth;
        if( paddleSelection == 1){
            rectWidth = 100;
        }
        else if( paddleSelection == 2){
            rectWidth = 150;
        }
        else{
            rectWidth = 0;
        }

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
        if (xPos > paddleX - rectWidth && xPos < paddleX + rectWidth
                && yPos >= height - 120 && yPos < height && yVel > 0) {

            //a little bit of random to decide if ball speeds
            //up or slows down after it hits the paddle
            //speeds up more often then slows down
            if( rand.nextInt(100) < 33 ){
                yVel = -30;
            }
            else if(rand.nextInt(100) < 66) {
                yVel = -40;
            }
            else{
                yVel = -50;
            }

            score++;
        }

        //if the ball goes out of bounds
        if( yPos >= height){
            score -= 3;
            lives -= 1;
            xPos = paddleX;
            yPos = height-120;
            xVel=0;
            yVel=0;
        }

        // Draw the ball in the correct position.
        drawBall(g, xPos, yPos);

        //draws the correct score on animation surface
        drawScore(g);

        //draws circles for each life left
        for( int i = 0; i < lives; i++){
            drawLives( g, i, width );
        }

        //draws paddle in stationary position
        drawPaddle(g, width, height,rectWidth );
    }

    @Override
    /*i take care of the touch event in my listeners class
    *this is abstract in animator so i must keep the method here
    */
    public void onTouch(MotionEvent event) {

    }

    //draws the ball at the updated x and y position
    public void drawBall( Canvas g, int xPos, int yPos){
        g.drawCircle(xPos, yPos, 50, redPaint);
    }

    //draws the paddle at the correct location on the animation surface
    public void drawPaddle( Canvas g, int width, int height, int rectWidth){
        Paint paddlePaint = new Paint();
        paddlePaint.setColor(Color.CYAN);
        Rect paddle = new Rect(paddleX - rectWidth, height - 60,
                paddleX + rectWidth, height);
        g.drawRect(paddle, paddlePaint);
    }

    //draw the amount of lives on the animation surface
    public void drawLives( Canvas g, int index, int width){
        g.drawCircle(((width-200)+(index*45)), 25 ,20,redPaint);
    }

    //draws current running score on animation surface
    public void drawScore( Canvas g){
        redPaint.setTextSize(60);
        g.drawText(""+score, 20, 60, redPaint );
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
}
