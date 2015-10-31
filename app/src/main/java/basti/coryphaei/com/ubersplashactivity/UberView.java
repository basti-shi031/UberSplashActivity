package basti.coryphaei.com.ubersplashactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Bowen on 2015/10/29.
 */
public class UberView extends RelativeLayout {

    public TextView logoView;

    public View point;

    public UberView(Context context) {
        this(context, null);
    }

    public UberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {

        LayoutInflater.from(context).inflate(R.layout.uberview,this);
        logoView = (TextView) findViewById(R.id.logo_name);
        point = findViewById(R.id.point);
    }


}
