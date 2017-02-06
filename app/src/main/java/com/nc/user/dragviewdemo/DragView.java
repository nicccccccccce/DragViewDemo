package com.nc.user.dragviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by user on 2016/11/18.
 */

public class DragView extends RelativeLayout {
    private WindowManager windowManager;// 用于可拖动的浮动窗口
    private WindowManager.LayoutParams windowParams;// 浮动窗口的参数
    private ImageView myButton;
    private OnDragClickListener mOnDragClickListener;

    public void setOnDragClickListener(OnDragClickListener mOnDragClickListener) {
        this.mOnDragClickListener = mOnDragClickListener;
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.layout_my, this);
        myButton = new ImageView(getContext());
        myButton.setImageResource(R.mipmap.waning);
        myButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        myButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnDragClickListener==null)
                    mOnDragClickListener.OnClick(v);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前点的xy位置
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (windowManager == null) {
                    setWindowParams(currentX,currentY);
                    windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                    windowManager.addView(myButton, windowParams);
                }
                break;
            case MotionEvent.ACTION_MOVE:

                windowParams.x = currentX;
                windowParams.y = currentY;
                windowManager.updateViewLayout(myButton, windowParams);
                break;
            case MotionEvent.ACTION_UP:
                // windowManager.removeView(myButton);
                break;

        }
        return true;
    }

    private void setWindowParams(int x, int y) {
        // 建立item的缩略图
        windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;// 这个必须加
        // 得到preview左上角相对于屏幕的坐标
        windowParams.x = x;
        windowParams.y = y;
        // 设置宽和高
        windowParams.width = 200;
        windowParams.height = 200;
        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.windowAnimations = 0;
    }
    public interface OnDragClickListener{
        void OnClick(View v);
    }
}
