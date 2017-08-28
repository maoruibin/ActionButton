package gudong.name.demo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import gudong.name.button.ActionButton;
import gudong.name.button.DrawableUtils;
import gudong.name.button.ResUtils;
import name.gudong.loading.LoadingDrawable;

public class MainActivity extends AppCompatActivity {
    private static final int default_duration_time = 600;
    ActionButton mActionButton;
    ActionButton mActionButtonIcon;
    ActionButton mActionButtonLine;

    ActionButton mActionButtonToutiao;
    ActionButton mActionButtonZhihu;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionButton = (ActionButton) findViewById(R.id.btn_follow);
        mActionButtonIcon = (ActionButton) findViewById(R.id.btn_follow_icon);
        mActionButtonLine = (ActionButton) findViewById(R.id.btn_follow_icon_line);
        mActionButtonToutiao = (ActionButton) findViewById(R.id.btn_follow_toutiao);
        mActionButtonZhihu = (ActionButton) findViewById(R.id.btn_follow_zhihu);


        //纯文本模式的按钮
        setUpButtonPureTextMode(mActionButton);
        //右侧按钮模式
        setUpButtonIconTextMode(mActionButtonIcon);
        // 描边模式的按钮
        setUpButtonIconTextLineMode(mActionButtonLine);

        setUpButtonToutiao(mActionButtonToutiao);
        setUpButtonZhihu(mActionButtonZhihu);



    }

    private void setUpButtonZhihu(final ActionButton button) {
        button.enableLoading(false);
        int colorBlueNormal = Utils.getColor(this, R.color.zhihu_blue_normal);
        int colorBluePress = Utils.getColor(this, R.color.zhihu_blue_press);
        int colorBlueDisable = Utils.getColor(this, R.color.zhihu_blue_disable);
        final Drawable drawableNormal = DrawableUtils.generateBtnBackground(this, Color.WHITE, colorBlueNormal);
        Drawable drawablePress = DrawableUtils.generateBtnBackgroundFill(this, colorBluePress);
        Drawable drawableDisable = DrawableUtils.generateBtnBackground(this, Color.WHITE, colorBlueDisable);
        final Drawable bkDrawable = DrawableUtils.generateStateListDrawable(drawableNormal, drawablePress, drawableDisable);

        //set button background
        button.setBackground(bkDrawable);

        final ColorStateList textColorList = DrawableUtils.generateColorStateListByRes(this,
                R.color.zhihu_blue_normal,
                R.color.ab_white,
                R.color.zhihu_blue_disable);

        // set button text color
        button.setTextColor(textColorList);
        //set button text "关注"
        button.setText("关注");
        final int drawablePadding = Utils.dip2px(this, 6);
        final StateListDrawable leftDrawable = DrawableUtils.generateStateListDrawable(this, R.drawable.icon_blue_plus, R.drawable.icon_plus);
        button.setLeftIcon(leftDrawable, drawablePadding);

        button.setOnActionClick(new ActionButton.OnActionClick() {
            private boolean isFollow = false;
            @Override
            public void onStart() {

            }

            @Override
            public boolean onAction() {
                SystemClock.sleep(80);
                return true;
            }

            @Override
            public void onEnd(Boolean result) {
                if (isFollow) {
                    isFollow = false;
                    button.setText("关注");
                    button.setBackground(bkDrawable);
                    button.setTextColor(textColorList);
                    button.setLeftIcon(leftDrawable, drawablePadding);
                    Toast.makeText(MainActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                } else {
                    isFollow = true;
                    button.setText("已关注");
                    button.setLeftIcon(null);
                    button.setTextColor(getResources().getColor(R.color.zhihu_text_normal_color));
                    int greyColor = Utils.getColor(MainActivity.this, R.color.ab_grey_block_normal);
                    Drawable followDrawable = DrawableUtils.generateBtnBackgroundFill(MainActivity.this,greyColor);
                    button.setBackground(followDrawable);
                    Toast.makeText(MainActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpButtonToutiao(final ActionButton button) {
        button.setText("关注");
        final Drawable drawableNormal = DrawableUtils.generateBtnBackgroundFill(this, R.color.toutiao_red, Utils.dip2px(this,6));
        button.setBackground(drawableNormal);
        final LoadingDrawable lightDrawable = new LoadingDrawable(this, R.drawable.loading_progress_grey);
        lightDrawable.setRotateStep(10);
        lightDrawable.setDuration(30);
        button.setLoadingDrawable(lightDrawable);
        button.setOnActionClick(new ActionButton.OnActionClick() {
            private boolean isFollow = false;
            @Override
            public void onStart() {

            }

            @Override
            public boolean onAction() {
                SystemClock.sleep(default_duration_time);
                return true;
            }

            @Override
            public void onEnd(Boolean result) {
                if (isFollow) {
                    isFollow = false;
                    button.setText("关注");
                    button.setTextColor(Color.WHITE);
                    button.setBackground(drawableNormal);
                    Toast.makeText(MainActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                } else {
                    isFollow = true;
                    button.setText("已关注");
                    button.setTextColor(getResources().getColor(R.color.ab_grey_block_normal));
                    Drawable normal = DrawableUtils.generateBtnBackground(MainActivity.this, Color.WHITE, Utils.getColor(MainActivity.this, R.color.ab_grey_block_normal));
                    button.setBackground(normal);
                    Toast.makeText(MainActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpButtonIconTextLineMode(final ActionButton button) {
        int colorOrangeNormal = Utils.getColor(this, R.color.ab_orange_block_normal);
        int colorOrangeDisable = Utils.getColor(this, R.color.ab_orange_block_disable);
        final Drawable drawableNormal = DrawableUtils.generateBtnBackground(this, Color.WHITE, colorOrangeNormal);
        Drawable drawablePress = DrawableUtils.generateBtnBackgroundFill(this, colorOrangeNormal);
        Drawable drawableDisable = DrawableUtils.generateBtnBackground(this, Color.WHITE, colorOrangeDisable);
        final Drawable bkDrawable = DrawableUtils.generateStateListDrawable(drawableNormal, drawablePress, drawableDisable);

        //set button background
        button.setBackground(bkDrawable);

        final ColorStateList textColorList = DrawableUtils.generateColorStateListByRes(this,
                R.color.ab_orange_block_normal,
                R.color.ab_white,
                R.color.ab_orange_block_disable);

        // set button text color
        button.setTextColor(textColorList);
        //set button text "关注"
        button.setText("关注");
        final StateListDrawable leftDrawable = DrawableUtils.generateStateListDrawable(this, R.drawable.icon_orange_plus, R.drawable.icon_plus);
        button.setLeftIcon(leftDrawable, Utils.dip2px(this, 6));

        button.setOnActionClick(new ActionButton.OnActionClick() {
            private boolean isFollow = false;
            @Override
            public void onStart() {

            }

            @Override
            public boolean onAction() {
                SystemClock.sleep(default_duration_time);
                return true;
            }

            @Override
            public void onEnd(Boolean result) {
                if (isFollow) {
                    isFollow = false;
                    button.setText("关注");
                    button.setBackground(bkDrawable);
                    button.setTextColor(textColorList);
                    button.setLeftIcon(leftDrawable, Utils.dip2px(MainActivity.this, 6));
                    Toast.makeText(MainActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                } else {
                    isFollow = true;
                    button.setText("已关注");
                    button.setTextColor(getResources().getColor(R.color.ab_grey_block_normal));
                    Drawable followDrawable = DrawableUtils.generateGreyBgBtnLine(MainActivity.this);
                    button.setBackground(followDrawable);
                    button.setLeftIcon(R.drawable.icon_right, ResUtils.dip2px(MainActivity.this, 6));
                    Toast.makeText(MainActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpButtonIconTextMode(final ActionButton button) {

        button.setText("关注");
        button.setLeftIcon(R.drawable.icon_plus, ResUtils.dip2px(this, 6));
        Drawable loadingDrawable = new LoadingDrawable(this, R.drawable.icon_loading_white);
        button.setLoadingDrawable(loadingDrawable);
        button.setOnActionClick(new ActionButton.OnActionClick() {
            private boolean isFollow = false;

            @Override
            public void onStart() {

            }

            @Override
            public boolean onAction() {
                SystemClock.sleep(default_duration_time);
                return true;
            }

            @Override
            public void onEnd(Boolean result) {
                if (isFollow) {
                    isFollow = false;
                    button.setText("关注");
                    button.setLeftIcon(R.drawable.icon_plus, ResUtils.dip2px(MainActivity.this, 6));
                    button.setTextColor(Color.WHITE);
                    Drawable followDrawable = DrawableUtils.generateOrangeBgBtnLine(MainActivity.this);
                    button.setBackground(followDrawable);
                    button.setLoadingDrawable(new LoadingDrawable(MainActivity.this, R.drawable.icon_loading_white));
                    Toast.makeText(MainActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                } else {
                    isFollow = true;
                    button.setText("已关注");
                    button.setTextColor(getResources().getColor(R.color.ab_grey_block_normal));
                    Drawable followDrawable = DrawableUtils.generateGreyBgBtnLine(MainActivity.this);
                    button.setBackground(followDrawable);
                    button.setLeftIcon(R.drawable.icon_right, ResUtils.dip2px(MainActivity.this, 6));
                    button.setLoadingDrawable(new LoadingDrawable(MainActivity.this));
                    Toast.makeText(MainActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpButtonPureTextMode(final ActionButton button) {

        button.setText("加关注");

        Drawable loadingDrawable = new LoadingDrawable(this, R.drawable.icon_loading_white);
        button.setLoadingDrawable(loadingDrawable);
        button.setOnActionClick(new ActionButton.OnActionClick() {
            private boolean isFollow = false;

            @Override
            @WorkerThread
            public boolean onAction() {
                SystemClock.sleep(default_duration_time);
                return true;
            }

            @Override
            public void onEnd(Boolean result) {
                if (isFollow) {
                    isFollow = false;
                    button.setText("加关注");
                    button.setTextColor(Color.WHITE);
                    Drawable followDrawable = DrawableUtils.generateOrangeBgBtnLine(MainActivity.this);
                    button.setBackground(followDrawable);
                    button.setLoadingDrawable(new LoadingDrawable(MainActivity.this, R.drawable.icon_loading_white));
                } else {
                    isFollow = true;
                    button.setText("已关注");
                    button.setTextColor(getResources().getColor(R.color.ab_grey_block_normal));
                    Drawable followDrawable = DrawableUtils.generateGreyBgBtnLine(MainActivity.this);
                    button.setBackground(followDrawable);
                    button.setLoadingDrawable(new LoadingDrawable(MainActivity.this));
                }


            }

            @Override
            public void onStart() {

            }
        });
    }
}
