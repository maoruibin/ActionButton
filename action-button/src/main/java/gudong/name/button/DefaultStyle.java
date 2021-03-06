package gudong.name.button;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * author  : gudong
 * create  : 2017/8/26 - 下午10:47.
 */

public class DefaultStyle {
    private Context mContext;
    ActionButton mButton;

    public DefaultStyle(ActionButton button) {
        mContext = button.getContext();
        mButton = button;
    }

    public void initDefaultStyle(){
        Drawable defaultDrawable = DrawableUtils.generateOrangeBgBtnLine(mContext);
        int defaultHeight = ResUtils.dip2px(mContext,36);
        mButton.setBackground(defaultDrawable);
        mButton.setHeight(defaultHeight);
        mButton.setClickable(true);
        mButton.setTextColor(Color.WHITE);
        mButton.setGravity(Gravity.CENTER);
    }
}
