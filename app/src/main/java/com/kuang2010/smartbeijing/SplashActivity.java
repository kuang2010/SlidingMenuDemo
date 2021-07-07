package com.kuang2010.smartbeijing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.kuang2010.smartbeijing.utils.MyConstaints;
import com.kuang2010.smartbeijing.utils.SPUtils;

public class SplashActivity extends AppCompatActivity {

    private ImageView mIv_splash;
    private AnimationSet mSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIv_splash = findViewById(R.id.iv_splash);

        initAnimation();

        initEvent();
    }

    private void initEvent() {
        mSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean guideFinish = SPUtils.getBoolean(SplashActivity.this, MyConstaints.GUIDE_FINISH, false);
                if (guideFinish){
                    //进入主页面
                    Intent main = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(main);
                    finish();
                }else {
                    //进入向导页面
                    Intent guide = new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(guide);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initAnimation() {
        mSet = new AnimationSet(false);

        RotateAnimation ra = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);
        ra.setDuration(1500);

        ScaleAnimation sa = new ScaleAnimation(0,1, 0,1,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        sa.setFillAfter(true);
        sa.setDuration(1500);

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setFillAfter(true);
        aa.setDuration(1500);

        mSet.addAnimation(ra);
        mSet.addAnimation(sa);
        mSet.addAnimation(aa);

        mIv_splash.startAnimation(mSet);

    }
}
