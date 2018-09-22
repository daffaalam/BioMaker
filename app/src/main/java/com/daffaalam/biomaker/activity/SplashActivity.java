package com.daffaalam.biomaker.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.daffaalam.biomaker.R;
import com.daffaalam.biomaker.function.AllFunction;

public class SplashActivity extends AllFunction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation anim_rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        anim_rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                MyIntentClass(HomeActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        findViewById(R.id.iv_splash).startAnimation(anim_rotate);
    }
}
