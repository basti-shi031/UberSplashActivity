package basti.coryphaei.com.ubersplashactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Bowen on 2015/10/29.
 */
public class RingView extends View {

    private Paint mPaint;
    private Context mContext;
    private int innerCircle = 160;//内圆半径
    private int ringWidth = 10;//圆环宽度

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;

        int innerCirclePx = DimensUtils.dip2px(mContext,innerCircle);
        int ringWidthPx = DimensUtils.dip2px(mContext,ringWidth);
        /*//绘制内圆
        this.mPaint.setARGB(155, 167, 190, 206);
        this.mPaint.setStrokeWidth(2);
        canvas.drawCircle(centerX,centerY, innerCirclePx, this.mPaint);*/

        //绘制圆环
        mPaint.setColor(mContext.getResources().getColor(R.color.ringview_color));
        this.mPaint.setStrokeWidth(ringWidth);
        canvas.drawCircle(centerX,centerY, innerCirclePx+ringWidthPx/2, this.mPaint);

/*        //绘制外圆
        this.mPaint.setARGB(155, 167, 190, 206);
        this.mPaint.setStrokeWidth(2);
        canvas.drawCircle(centerX,centerY, innerCirclePx+ringWidthPx, this.mPaint);*/
        super.onDraw(canvas);
    }
}
