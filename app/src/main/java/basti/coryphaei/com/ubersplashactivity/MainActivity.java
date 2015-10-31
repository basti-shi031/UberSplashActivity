package basti.coryphaei.com.ubersplashactivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private CustomVideoView videoView;
    private UberView uberView;
    private RingView mRingView;
    private View point;
    private File videoviewFile;
    private String VIDEONAME = "welcome_video";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        getSupportActionBar().hide();

        videoviewFile = getFileStreamPath(VIDEONAME);
        if (!videoviewFile.exists()) {
            videoviewFile = copyVideoFile();
        }
        startVideo(videoviewFile);

        startAnim(uberView);
    }

    private void initview() {
        videoView = (CustomVideoView) findViewById(R.id.videoview);
        uberView = (UberView) findViewById(R.id.uberview);
        mRingView = (RingView) findViewById(R.id.ringview);
        point = findViewById(R.id.point);
        uberView.bringToFront();
    }

    private void startAnim(UberView view) {

        //圆环放大动画
        final AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mRingView, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mRingView, "scaleY", 0f, 1f);
        ObjectAnimator alphaRingView = ObjectAnimator.ofFloat(mRingView,"alpha",1f,0f);
        ObjectAnimator alphaPoint = ObjectAnimator.ofFloat(uberView.point,"alpha",1f,0f);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY).before(alphaRingView).before(alphaPoint);

        //uber位移动画
        AnimatorSet animatorSetTranslate = new AnimatorSet();
        ObjectAnimator animX = ObjectAnimator.ofFloat(view.logoView, "X", -500, view.logoView.getX());
        ObjectAnimator animY = ObjectAnimator.ofFloat(view.logoView, "Y", -500, view.logoView.getY());
        animatorSetTranslate.setDuration(800);
        animatorSetTranslate.play(animY).with(animX);
        animatorSetTranslate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mRingView.setVisibility(View.VISIBLE);
                point.setVisibility(View.VISIBLE);
                animatorSet.start();
            }
        });
        animatorSetTranslate.start();
    }

    private void startVideo(File videoviewFile) {
        videoView.setVideoPath(videoviewFile.getPath());
        videoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }


    private File copyVideoFile() {
        File videoFile;
        try {
            FileOutputStream fos = openFileOutput(VIDEONAME, MODE_PRIVATE);
            InputStream in = getResources().openRawResource(R.raw.welcome_video);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEONAME);
        if (!videoFile.exists())
            throw new RuntimeException("video file has problem, are you sure you have welcome_video.mp4 in res/raw folder?");
        return videoFile;
    }
}
