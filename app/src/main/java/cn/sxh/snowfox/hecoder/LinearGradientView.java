package cn.sxh.snowfox.hecoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author by snow on 2017/10/28
 * @time 15:23
 * @mail snowtigersong@gmail.com
 */

public class LinearGradientView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public LinearGradientView(Context context) {
        this(context,null);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint.setShader(new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300,300,200,paint);
    }
}
