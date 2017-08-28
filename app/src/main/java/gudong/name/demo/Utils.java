package gudong.name.demo;

import android.content.Context;

/**
 * author  : ruibin1 (ruibin1@staff.weibo.com)
 * version : 5.0.0
 * create  : 2017/8/27 - 下午7:26.
 */

public class Utils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getColor(Context context, int color) {
        return context.getResources().getColor(color);
    }
}
