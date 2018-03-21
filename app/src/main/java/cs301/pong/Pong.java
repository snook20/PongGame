package cs301.pong;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

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
 * only created when the user taps the screen
 *
 */
public class Pong extends Activity {

    /**
     * creates an AnimationSurface containing a TestAnimator.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this
                .findViewById(R.id.animationSurface);
        mySurface.setAnimator(new PongAnimator());
    }
}
