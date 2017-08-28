package gudong.name.button;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

public class DrawableUtils {
    /**
     * 圆角尺寸
     */
    private static final int DEFAULT_RADIUS = 2;

    /**
     * 边框粗细
     */
    private static final float DEFAULT_LINE_WIDTH = 1f;

    public static GradientDrawable generateBtnBackground(Context context, int
            fillColor, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置圆角
        gradientDrawable.setCornerRadius(ResUtils.dip2px(context, DEFAULT_RADIUS));
        //设置填充
        gradientDrawable.setColor(fillColor);
        //设置边框
        gradientDrawable.setStroke(ResUtils.dip2px(context, DEFAULT_LINE_WIDTH), strokeColor);
        return gradientDrawable;
    }

    public static StateListDrawable generateGreyBgBtnLine(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable normal = generateBtnBackground(context, Color.WHITE, ResUtils.getColor(context, R.color.ab_grey_block_normal));
        Drawable press = generateBtnBackground(context, Color.WHITE, ResUtils.getColor(context, R.color.ab_grey_block_press));
        Drawable disable = generateBtnBackground(context, Color.WHITE, ResUtils.getColor(context, R.color.ab_grey_block_disable));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disable);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    public static StateListDrawable generateStateListDrawable(Drawable normal, Drawable press, Drawable disable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disable);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    public static StateListDrawable generateStateListDrawable(Context context, int normal, int press) {
        return generateStateListDrawable(ResUtils.getDrawable(context, normal), ResUtils.getDrawable(context, press));
    }

    public static StateListDrawable generateStateListDrawable(Drawable normal, Drawable press) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    public static ColorStateList generateColorStateListByRes(Context context, @ColorRes int normal, @ColorRes int press, @ColorRes int disable) {
        int colorNormal = ResUtils.getColor(context, normal);
        int colorPress = ResUtils.getColor(context, press);
        int colorDisable = ResUtils.getColor(context, disable);
        return generateColorStateList(colorNormal, colorPress, colorDisable);
    }

    public static ColorStateList generateColorStateList(@ColorInt int normal, @ColorInt int press, @ColorInt int disable) {
        int[][] states = new int[3][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{-android.R.attr.state_enabled};
        states[2] = new int[]{};
        int[] colors = new int[]{press, disable, normal};
        return new ColorStateList(states, colors);
    }


    public static StateListDrawable generateOrangeBgBtnLine(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable normal = generateBtnBackgroundFill(context, ResUtils.getColor(context, R.color.ab_orange_block_normal));
        Drawable press = generateBtnBackgroundFill(context, ResUtils.getColor(context, R.color.ab_orange_block_press));
        Drawable disable = generateBtnBackgroundFill(context, ResUtils.getColor(context, R.color.ab_orange_block_disable));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disable);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    public static GradientDrawable generateBtnBackgroundFill(Context context,@ColorRes int fillColor, int radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setColor(ResUtils.getColor(context,fillColor));
        return gradientDrawable;
    }
    public static GradientDrawable generateBtnBackgroundFill(Context context, int fillColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置圆角
        gradientDrawable.setCornerRadius(ResUtils.dip2px(context, DEFAULT_RADIUS));
        //设置填充
        gradientDrawable.setColor(fillColor);
        return gradientDrawable;
    }
}
