package gudong.name.button;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * author  : gudong
 * create  : 2017/8/26 - 下午10:47.
 */

public class ResUtils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getColor(Context context, int color) {
        return context.getResources().getColor(color);
    }

    public static Drawable getDrawable(Context context, int drawable) {
        return context.getResources().getDrawable(drawable);
    }
}
