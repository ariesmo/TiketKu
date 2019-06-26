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
import android.widget.Toast;

public class SuccessBuyTicketAct extends AppCompatActivity {

    Animation app_splash, ttb, btt;
    ImageView icon_buysuccess;
    TextView text_buytitle, text_buysubtitle;
    Button btn_view_tickets, btn_view_dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        icon_buysuccess = findViewById(R.id.icon_buysuccess);
        text_buytitle = findViewById(R.id.text_buytitle);
        text_buysubtitle = findViewById(R.id.text_buysubtitle);
        btn_view_tickets = findViewById(R.id.btn_view_tickets);
        btn_view_dashboard = findViewById(R.id.btn_view_dashboard);

        icon_buysuccess.startAnimation(app_splash);
        text_buytitle.startAnimation(ttb);
        text_buysubtitle.startAnimation(ttb);
        btn_view_tickets.startAnimation(btt);
        btn_view_dashboard.startAnimation(btt);

        btn_view_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Menuju Profile Ticket Detail ", Toast.LENGTH_SHORT).show();
                Intent gotoprofile = new Intent(SuccessBuyTicketAct.this, MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

        btn_view_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodashboard = new Intent(SuccessBuyTicketAct.this, HomeAct.class);
                startActivity(gotodashboard);
            }
        });
    }
}
