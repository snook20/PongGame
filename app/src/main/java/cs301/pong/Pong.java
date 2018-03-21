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
 *
 * I added the feature that a new ball is
 * only created when the user taps the new ball button
 *
 * Also added a feature to change the size of the paddle
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

        PongAnimator newPongAnimator = new PongAnimator();

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this
                .findViewById(R.id.animationSurface);
        mySurface.setAnimator(newPongAnimator);

        //creates listener object
        Listeners newListener = new Listeners(newBallButton, newPongAnimator);

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
