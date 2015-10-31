package basti.coryphaei.com.ubersplashactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Bowen on 2015/10/28.
 */
public class CustomVideoView extends VideoView {
    public CustomVideoView(Context context) {
        this(context, null);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //确定
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else{

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
