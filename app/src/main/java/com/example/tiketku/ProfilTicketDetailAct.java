package com.example.tiketku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilTicketDetailAct extends AppCompatActivity {

    TextView xnama_wisata, xlokasi, xdate_wisata, xtime_wisata, xketentuan;
    Button btn_back;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_ticket_detail);

        xnama_wisata = findViewById(R.id.xnama_wisata);
        xlokasi = findViewById(R.id.xlokasi);
        xdate_wisata = findViewById(R.id.xdate_wisata);
        xtime_wisata = findViewById(R.id.xtime_wisata);
        xketentuan = findViewById(R.id.xketentuan);
        btn_back = findViewById(R.id.btn_back);

        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String nama_wisata_baru = bundle.getString("nama_wisata");

        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(nama_wisata_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xnama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                xlokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                xdate_wisata.setText(dataSnapshot.child("date_wisata").getValue().toString());
                xtime_wisata.setText(dataSnapshot.child("time_wisata").getValue().toString());
                xketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(ProfilTicketDetailAct.this, MyProfileAct.class);
                startActivity(gotoback);
            }
        });
    }
}
