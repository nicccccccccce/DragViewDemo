package com.nc.user.dragviewdemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nc.user.dragviewdemo.dView.TableShowView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    DragView mDragView;
    int lastX = 0;
    int lastY = 0;
    int screenWidth;
    int screenHeight;
    RelativeLayout relate;

    float dX;
    float dY;
    int akukudupiye;
    TableShowView tableShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        mDragView= (DragView) findViewById(R.id.main_touchview);
        mDragView.setOnDragClickListener(new DragView.OnDragClickListener() {
            @Override
            public void OnClick(View v) {
                Toast.makeText(MainActivity.this,"点击！", Toast.LENGTH_LONG).show();
            }
        });*/
       /* setContentView(R.layout.drag_view);
        relate= (RelativeLayout) findViewById(R.id.relate);
        relate.setOnTouchListener(this);
        //获取屏幕宽高，用于控制控件在屏幕内移动
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 100;//这里减去的100是下边的back键和menu键那一栏的高度，看情况而定*/
       /* setContentView(R.layout.dd_view);
        LinearLayout llContainerMain = (LinearLayout) findViewById(R.id.llMainContainer);
        DragDropView dragDropView = new DragDropView(this);
        dragDropView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ImageView ivTest = new ImageView(this);
        ivTest.setImageDrawable(getResources().getDrawable(R.mipmap.waning));
        dragDropView.AddDraggableView(ivTest, 50, 50, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        llContainerMain.addView(dragDropView);
        ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"WWWW",Toast.LENGTH_LONG).show();
            }
        });*/
      /*  setContentView(R.layout.drag_view_1);

        final View dragView = findViewById(R.id.draggable_view);
        dragView.setOnTouchListener(this);*/
        setContentView(R.layout.main_table_view);
        tableShowView = new TableShowView(getApplicationContext());
        tableShowView.fun();
        tableShowView.setOnTableShowListener(new TableShowView.OnTableShowListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点到我了……疼！！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN://0
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
//                akukudupiye = MotionEvent.ACTION_DOWN;

                Log.e("drag", dX + "-------down--------" + dY + "---" + akukudupiye);
                break;

            case MotionEvent.ACTION_MOVE://2
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
//                akukudupiye = MotionEvent.ACTION_MOVE;
                Log.e("drag", dX + "--------move-------" + dY + "---" + akukudupiye);

                break;

            case MotionEvent.ACTION_UP://1
//                if (akukudupiye == MotionEvent.ACTION_DOWN)
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                Log.e("drag", dX + "-------up--------" + dY + "---" + akukudupiye);
                break;

            default:
                return false;
        }

        return true;
    }

    //核心代码段【OnTouchListener()的onTouch方法，控件去设置它就可以了】
    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();//移动
//                LogTool.e("发生Touch事件x:y____"":""<span lang="EN-US" style="" font-size:11.5pt;"="">\nrawX:rawY____"
//                    +event.getRawX()+":"+event.getRawY());
                //event.getRawX()事件点距离屏幕左上角的距离
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                if (left < 0) { //最右边
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                if (top < 0) {  //最下边
                    bottom = screenHeight;
                    top = bottom - v.getHeight();

                }
                v.layout(left, top, right, bottom);//再次将滑动其实位置定位
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }*/
    public void CancelClick(View v) {
        tableShowView.removeTable();
    }
}
