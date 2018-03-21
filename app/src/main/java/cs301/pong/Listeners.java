package cs301.pong;

import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import cs301.animation.AnimationSurface;
import edu.snook20up.ponggame.R;

/**
 * Created by snook on 3/20/2018.
 */

public class Listeners implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

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
        newAnimator.randomBall();
    }

    @Override
    /*
     *listener for radio buttons
     */
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(checkedId == R.id.smallPaddleButton){
            newAnimator.setPaddleSelection(1);
        }
        else if( checkedId == R.id.bigPaddleButton){
            newAnimator.setPaddleSelection(2);
        }
    }
}
