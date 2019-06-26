package com.example.tiketku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GetStartedAct extends AppCompatActivity {

    Animation ttb, btt;
    Button btn_sign_in, btn_new_account_create;
    ImageView logo_started;
    TextView logo_discover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        logo_started = findViewById(R.id.logo_started);
        logo_discover = findViewById(R.id.logo_discover);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_new_account_create = findViewById(R.id.btn_new_account_create);

        //load animation
        logo_started.startAnimation(ttb);
        logo_discover.startAnimation(ttb);
        btn_sign_in.startAnimation(btt);
        btn_new_account_create.startAnimation(btt);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign = new Intent(GetStartedAct.this, SignInAct.class);
                startActivity(gotosign);
            }
        });

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gocreate = new Intent(GetStartedAct.this, RegisterOneAct.class);
                startActivity(gocreate);
            }
        });
    }
}
