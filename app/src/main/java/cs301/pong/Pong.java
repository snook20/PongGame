package cs301.pong;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cs301.animation.AnimationSurface;
import edu.snook20up.ponggame.R;

/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 *
 *Part A additions
 * I added the feature that a new ball is
 * only created when the user taps the new ball button
 *
 * Also added a feature to change the size of the paddle
 *
 * Part B additions
 * Add a running score that increases when the ball is hit by paddle
 * and decreases by three when the user misses the ball
 *
 * Keeps track of lives. Starts game with 5 lives and each time the
 * ball goes out of bounce the user loses one life. Lives are shown on
 * the gui in top right corner
 *
 * when the ball hits the paddle the speed is randomly changed between
 * three different speed
 */
public class Pong extends Activity {

    /**
     * creates an AnimationSurface containing a TestAnimator.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong);

        //creates new ball button
        Button newBallButton = (Button)findViewById(R.id.buttonNewBall);

        //radioGroup, calls the oncheckedchanged listener in listeners class
        RadioGroup paddleSize = (RadioGroup)findViewById(R.id.paddleSizeGroup);
        RadioButton smallPaddle = (RadioButton)findViewById(R.id.smallPaddleButton);
        RadioButton bigPaddle = (RadioButton)findViewById(R.id.bigPaddleButton);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this
                .findViewById(R.id.animationSurface);

        PongAnimator newPongAnimator = new PongAnimator();
        mySurface.setAnimator(newPongAnimator);

        //creates listener object
        Listeners newListener = new Listeners(newBallButton, newPongAnimator);

        //listener for touch on screen
        mySurface.setOnTouchListener(newListener);

        //sets
        bigPaddle.setChecked(true);



        //calls on click listener to draw paddle in beginning
        newListener.onCheckedChanged(paddleSize, R.id.bigPaddleButton);

        //calls listener for radio group
        paddleSize.setOnCheckedChangeListener(newListener);

        //calls listener for button
        newBallButton.setOnClickListener(newListener);

    }
}
