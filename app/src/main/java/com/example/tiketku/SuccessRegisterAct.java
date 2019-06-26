package com.example.tiketku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessRegisterAct extends AppCompatActivity {

    Animation app_splash, ttb, btt;
    ImageView logo_success;
    TextView app_titlesuccess, app_subtitle;
    Button btn_explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this,R.anim.btt);
        logo_success = findViewById(R.id.logo_success);
        app_titlesuccess = findViewById(R.id.app_titlesuccess);
        app_subtitle = findViewById(R.id.app_subtitle);
        btn_explore = findViewById(R.id.btn_explore);

        //load animation
        logo_success.startAnimation(app_splash);
        app_titlesuccess.startAnimation(ttb);
        app_subtitle.startAnimation(ttb);
        btn_explore.startAnimation(btt);

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(SuccessRegisterAct.this, HomeAct.class);
                startActivity(gotohome);
            }
        });
    }
}
