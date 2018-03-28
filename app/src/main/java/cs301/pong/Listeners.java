package cs301.pong;

import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import edu.snook20up.ponggame.R;

/**
 * @author Riley Snook
 * 3/20/2018.
 * Listeners class for button, radio buttons, and
 * animation surface touch events
 */

public class Listeners implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnTouchListener{

    private PongAnimator newAnimator;
    private int paddleSelection;
    private Button newBallButton;
    private int radioSelection;

    public Listeners(Button newBall, PongAnimator pongAnimator){
        this.newBallButton = newBall;
        this.newAnimator= pongAnimator;
        this.radioSelection = 1;
    }

    @Override
    /*
     *on click listener for new ball button
     */
    public void onClick(View v) {
        double yVel = newAnimator.getyVel();
        double xVel = newAnimator.getxVel();

        if( !(yVel == 0 && xVel == 0)){
            return;
        }

        //checks to see if the player has any lives yet
        //if no lives left restarts game with 5 lives and score 0
        if( newAnimator.getLives() == 0){
            newAnimator.setScore(0);
            newAnimator.setLives(5);
            newAnimator.randomBall();
            newAnimator.setxPos((int)(newAnimator.getPaddleX()));
            newAnimator.setyPos(1200);
            newAnimator.randomBall();
        }
        //creates a random ball
        //newAnimator.setScore(newAnimator.getScore()-1);
        newAnimator.randomBall();

    }

    @Override
    /*
     *listener for radio buttons
     */
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //checks to see which radio button is selected
        if(checkedId == R.id.smallPaddleButton){
            newAnimator.setPaddleSelection(1);
        }
        else if( checkedId == R.id.bigPaddleButton){
            newAnimator.setPaddleSelection(2);
        }
    }

    @Override
    /*
     *listener for screen touch events
     */
    public boolean onTouch(View v, MotionEvent event) {

        //gets x and y coordinate of touch
        int x = (int)event.getX();
        int y = (int)event.getY();

        //sets paddle location to location of finger
        newAnimator.setPaddleX(x);
        return true;
    }
}
