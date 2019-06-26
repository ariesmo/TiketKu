package com.example.tiketku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.Set;

public class TicketCheckoutAct extends AppCompatActivity {

    Button btn_back, btn_pay_now, btnmines, btnpls;
    TextView textjumlahtiket, textmybalance, texttotalhargaticket, nama_wisata, lokasi, ketentuan;
    ImageView notice_uang;
    Integer valuejumlahtiket = 1;
    Integer myBalance = 0;
    Integer valuehargaticket = 0;
    Integer valuetotalhargaticket = 0;
    Integer sisa_balance = 0;

    //generate nomor integer secara random untuk membuat transaksi secara unik
    Integer nomor_transaksi = new Random().nextInt();

    DatabaseReference reference, reference2, reference3, reference4;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";
    String date_wisata = "";
    String time_wisata = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);

        //mengambil data local
        getUsernameLocal();

        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString("jenis_tiket");

        //target id
        btn_back = findViewById(R.id.btn_back);
        btn_pay_now = findViewById(R.id.btn_pay_now);
        btnmines = findViewById(R.id.btnmines);
        btnpls = findViewById(R.id.btnpls);
        textjumlahtiket = findViewById(R.id.textjumlahtiket);
        textmybalance = findViewById(R.id.textmybalance);
        texttotalhargaticket = findViewById(R.id.texttotalhargaticket);
        notice_uang = findViewById(R.id.notice_uang);
        nama_wisata = findViewById(R.id.nama_wisata);
        lokasi = findViewById(R.id.lokasi);
        ketentuan = findViewById(R.id.ketentuan);

        //setting value
        textjumlahtiket.setText(valuejumlahtiket.toString());
        notice_uang.setVisibility(View.GONE);

        // default button hide
        btnmines.animate().alpha(0).setDuration(300).start();
        btnmines.setEnabled(false);

        //mengambil data user dari firebase
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myBalance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                textmybalance.setText("US$ "+myBalance+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //mengambil data user dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_tiket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
                date_wisata = dataSnapshot.child("date_wisata").getValue().toString();
                time_wisata = dataSnapshot.child("time_wisata").getValue().toString();
                valuehargaticket = Integer.valueOf(dataSnapshot.child("harga_tiket").getValue().toString());
                valuetotalhargaticket = valuehargaticket * valuejumlahtiket;
                texttotalhargaticket.setText("US $ "+valuetotalhargaticket+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnpls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahtiket+=1;
                textjumlahtiket.setText(valuejumlahtiket.toString());
                if (valuejumlahtiket > 1){
                    btnmines.animate().alpha(1).setDuration(300).start();
                    btnmines.setEnabled(true);
                }
                valuetotalhargaticket = valuehargaticket * valuejumlahtiket;
                texttotalhargaticket.setText("US $ "+valuetotalhargaticket+"");
                if(valuetotalhargaticket > myBalance){
                    btn_pay_now.animate().translationY(250).alpha(0).setDuration(350).start();
                    btn_pay_now.setEnabled(false);
                    textmybalance.setTextColor(Color.RED);
                    notice_uang.setVisibility(View.VISIBLE);
                }
            }
        });

        btnmines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahtiket-=1;
                textjumlahtiket.setText(valuejumlahtiket.toString());
                if(valuejumlahtiket < 2){
                    btnmines.animate().alpha(0).setDuration(300).start();
                    btnmines.setEnabled(false);
                }
                valuetotalhargaticket = valuehargaticket * valuejumlahtiket;
                texttotalhargaticket.setText("US $"+valuetotalhargaticket+"");
                if(valuetotalhargaticket < myBalance){
                    btn_pay_now.animate().translationY(0).alpha(1).setDuration(350).start();
                    btn_pay_now.setEnabled(true);
                    textmybalance.setTextColor(Color.BLUE);
                    notice_uang.setVisibility(View.GONE);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackdetail = new Intent(TicketCheckoutAct.this, TicketDetailAct.class);
                startActivity(gobackdetail);
            }
        });

        btn_pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //menyimpan data user kedalam firebase dan membuat table baru "MyTickets"
                reference3 = FirebaseDatabase.getInstance().getReference().child("MyTickets").child(username_key_new).child(nama_wisata.getText().toString()+nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("nama_wisata").setValue(nama_wisata.getText().toString());
                        reference3.getRef().child("lokasi").setValue(lokasi.getText().toString());
                        reference3.getRef().child("ketentuan").setValue(ketentuan.getText().toString());
                        reference3.getRef().child("jumlah_tiket").setValue(valuejumlahtiket.toString());
                        reference3.getRef().child("date_wisata").setValue(date_wisata);
                        reference3.getRef().child("time_wisata").setValue(time_wisata);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                reference4 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sisa_balance = myBalance - valuetotalhargaticket;
                        reference4.getRef().child("user_balance").setValue(sisa_balance);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent gotosuccess = new Intent(TicketCheckoutAct.this, SuccessBuyTicketAct.class);
                startActivity(gotosuccess);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
