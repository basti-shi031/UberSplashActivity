package basti.coryphaei.com.ubersplashactivity;

import android.content.Context;

/**
 * Created by Bowen on 2015/10/29.
 */
public class DimensUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
