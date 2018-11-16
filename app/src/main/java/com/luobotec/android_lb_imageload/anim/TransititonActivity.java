package com.luobotec.android_lb_imageload.anim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.luobotec.android_lb_imageload.R;

public class TransititonActivity extends AppCompatActivity {

    public static void actionActivity(Context context){
        context.startActivity(new Intent(context, TransititonActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transititon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(TransititonActivity.this, ContactActivity.class);
                ActivityOptionsCompat option= ActivityOptionsCompat.makeSceneTransitionAnimation(TransititonActivity.this,
                       new Pair<View, String>(findViewById(R.id.fab),ContactActivity.TRANSITION_NAME));
                ActivityCompat.startActivity(TransititonActivity.this, intent, option.toBundle());

            }
        });
    }



}
