package gudong.name.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import name.gudong.loading.LoadingDrawable;

/**
 * 文字一般为居中模式
 * 要解决的问题：
 * 1、padding
 * 2、commentDrawable 按下状态
 * author  : gudong
 * create  : 2017/8/4 - 上午12:01.
 */
public class ActionButton extends TextView {
    private static final String TAG = "WBActionButton";
    private static final boolean isDebug = true;
    private static final int style_stroke = 1;
    private static final int style_fill = 2;

    private int mTextContentWidth;
    private int mTextContentHeight;
    Drawable mLoadingDrawable;
    // 是够启动 Loading 效果的标志 如果设置为 false 那么在按钮执行 Action 时将不会有任何 Loading 操作
    private boolean mEnableLoading = true;

    Paint mPaint = new Paint();

    int style;

    //上次显示的文本
    private String mLastText;
    //上次显示的 left icon
    private Drawable mLastLeftDrawable;


    public ActionButton(Context context) {
        this(context, null);
    }

    public ActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);

        DefaultStyle style = new DefaultStyle(this);
        style.initDefaultStyle();

        //when text changed resize text width and height
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                resizeTextSize();
            }
        });
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionButton);
        style = typedArray.getInt(R.styleable.ActionButton_style, style_stroke);
        typedArray.recycle();
    }


    public void setOnActionClick(final OnActionClick listener) {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionTask(listener).execute();
            }
        });
    }

    public void setLoadingDrawable(Drawable loadingDrawable) {
        this.mLoadingDrawable = loadingDrawable;
    }

    public void enableLoading(boolean flag) {
        this.mEnableLoading = flag;
    }

    private class ActionTask extends AsyncTask<Void, Void, Boolean> {
        private OnActionClick mListener;

        public ActionTask(OnActionClick listener) {
            mListener = listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mEnableLoading) {
                showLoading();
            }
            if (mListener != null) {
                mListener.onStart();
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (mEnableLoading) {
                hideLoading();
            }
            if (mListener != null) {
                mListener.onEnd(aBoolean);
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (mListener != null) {
                mListener.onAction();
            }
            return true;
        }
    }

    public interface OnActionClick {
        void onStart();

        /**
         * execute Action
         *
         * @return true if action success else return false
         */
        boolean onAction();

        void onEnd(Boolean result);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resizeTextSize();
    }

    // resize text content width and height
    private void resizeTextSize() {
        Rect bounds = new Rect();
        Paint textPaint = getPaint();
        textPaint.getTextBounds(getText().toString(), 0, getText().length(), bounds);
        mTextContentWidth = bounds.width();
        mTextContentHeight = bounds.height();
    }

    public void showLoading() {
        if (mLoadingDrawable == null) {
            mLoadingDrawable = new LoadingDrawable(getContext());
        }
        showLoading(mLoadingDrawable);
    }


    public void showLoading(final Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (!(drawable instanceof Animatable)) {
            throw new IllegalStateException("drawable have to implement Animatable");
        }

        mLoadingDrawable = drawable;

        clearCompoundDrawable();
        clearText();

        post(new Runnable() {
            @Override
            public void run() {
                int iconWidth = drawable.getIntrinsicWidth();
                int iconHeight = drawable.getIntrinsicHeight();

                int left = (getWidth() - iconWidth) / 2;

                // make drawable position in center
                drawable.setBounds(
                        left,
                        0,
                        left + iconWidth,
                        iconHeight
                );

                setCompoundDrawables(drawable, null, null, null);
                startLoadingAnim();
            }
        });
    }

    public void hideLoading() {
        stopLoadingAnim();
        clearCompoundDrawable();
    }

    private void clearCompoundDrawable() {
        setCompoundDrawables(null, null, null, null);
    }

    private void clearText() {
        setText("");
    }

    public void setLeftIcon(
            final Drawable drawable,
            final int drawablePadding,
            final IconSize iconSize) {
        post(new Runnable() {
            @Override
            public void run() {

                int iconWidth = iconSize.mWidth;
                int iconHeight = iconSize.mHeight;
                //icon left size
                int leftDistance = (getWidth() - mTextContentWidth - iconWidth) / 2 - drawablePadding;
                drawable.setBounds(
                        leftDistance,
                        0,
                        leftDistance + iconWidth,
                        iconHeight
                );
                setCompoundDrawables(drawable, null, null, null);
            }
        });
    }

    public void setLeftIcon(int icon) {
        setLeftIcon(icon, 0);
    }

    public void setLeftIcon(Drawable icon) {
        if (icon == null) {
            clearCompoundDrawable();
        } else {
            setLeftIcon(icon, 0);
        }
    }

    public void setLeftIcon(int icon, int drawablePadding) {
        Drawable iconDrawable = getResources().getDrawable(icon);
        setLeftIcon(iconDrawable, drawablePadding);
    }

    public void setLeftIcon(Drawable iconDrawable, int drawablePadding) {
        setLeftIcon(iconDrawable, drawablePadding, IconSize.wrap(iconDrawable));
    }

    public void setLeftIcon(final int icon, final int drawablePadding, final IconSize iconSize) {
        Drawable drawable = getResources().getDrawable(icon);
        setLeftIcon(drawable, drawablePadding, iconSize);
    }

    private void startLoadingAnim() {
        if (mLoadingDrawable == null) {
            return;
        }
        if (mLoadingDrawable instanceof Animatable) {
            Animatable loadingDrawable = (Animatable) mLoadingDrawable;
            loadingDrawable.start();
        }
    }

    private void stopLoadingAnim() {
        if (mLoadingDrawable == null) {
            return;
        }
        if (mLoadingDrawable instanceof Animatable) {
            Animatable loadingDrawable = (Animatable) mLoadingDrawable;
            loadingDrawable.stop();
        }
    }


    /**
     * icon size mode
     */
    public static class IconSize {
        int mWidth;
        int mHeight;

        public IconSize(int width, int height) {
            mWidth = width;
            mHeight = height;
        }

        public static IconSize wrap(Drawable drawable) {
            if (drawable.getIntrinsicHeight() < 0 || drawable.getIntrinsicWidth() < 0) {
                throw new IllegalStateException("please indicator your custom drawable width and " +
                        "height");
            }
            return new IconSize(drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
        }

        public static IconSize size(int size) {
            return new IconSize(size, size);
        }
    }
}
