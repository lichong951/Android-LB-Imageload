package com.luobotec.android_lb_imageload;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.luobotec.android_lb_imageload.anim.TransititonActivity;

public class AnimDemoActivity extends AppCompatActivity {

    public static void actionActivity(Context context){
        context.startActivity(new Intent(context, AnimDemoActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_demo);
    }

    public void transition(View view){
        TransititonActivity.actionActivity(this);
    }
}
