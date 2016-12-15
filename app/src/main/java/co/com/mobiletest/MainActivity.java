package co.com.mobiletest;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.wefor.circularanim.CircularAnim;


/**
 * @author  Jaime Gamboa
 * @see AppCompatActivity
 */
public class MainActivity extends Activity {

    @BindView(R.id._title_splash) HTextView title_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        title_splash.setAnimateType(HTextViewType.EVAPORATE);
        title_splash.setTextSize(20f);
        title_splash.animateText(getResources().getString(R.string._splash_title));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                initListReddists();
            }
        }, 1500);
    }

    /**
     * intent next activity
     */
    @UiThread
    private void initListReddists () {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CircularAnim.fullActivity(MainActivity.this, title_splash).go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(new Intent(MainActivity.this, ListReddits.class));
                        finish();
                    }
                });
            }
        });
    }
}
