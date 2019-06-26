package com.example.tiketku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation app_splash, btt;
    ImageView app_logo;
    TextView app_titlelogo;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mendapati target id
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        app_logo = findViewById(R.id.app_logo);
        app_titlelogo = findViewById(R.id.app_titlelogo);

        //run animation
        app_logo.startAnimation(app_splash);
        app_titlelogo.startAnimation(btt);

        getUsernameLocal();
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");

        if(username_key_new.isEmpty()){
            //membuat timer untuk menuju ke halaman lain
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //membuat timer selama 2000ms / 2s
                    //menuju halaman lain
                    Intent abc = new Intent(MainActivity.this, GetStartedAct.class);
                    startActivity(abc);
                    finish();
                }
            }, 2000);
        } else {
            //membuat timer untuk menuju ke halaman lain
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //membuat timer selama 2000ms / 2s
                    //menuju halaman lain
                    Intent abc = new Intent(MainActivity.this, HomeAct.class);
                    startActivity(abc);
                    finish();
                }
            }, 2000);
        }
    }
}
