package com.bingo.customer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bingo.customer.view.scrollrulerview.HeightView;

/**
 * ================================
 *
 * @author: zcb
 * @email: zhang-cb@foxmail.com
 * @time: 2019/3/6 11:08
 * @version: 1.0
 * @description: Height View activity
 * =================================
 */
public class HeightViewActivity extends AppCompatActivity {


    HeightView mHeightView;
    TextView mTvSelectedHeight;
    /**
     * 当前身高
     */
    private int mCurrentHeight;
    private boolean isStartAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_view);
        mHeightView = findViewById(R.id.height_ruler);
        mTvSelectedHeight = findViewById(R.id.tv_selected_height);
        mHeightView.setOnItemChangedListener(new HeightView.OnItemChangedListener() {
            @Override
            public void onItemChanged(int index, int value) {
                mCurrentHeight = value;
                mTvSelectedHeight.setText(value + "");
            }
        });


    }
}
