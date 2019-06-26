package com.example.tiketku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TicketDetailAct extends AppCompatActivity {

    ImageView header_ticket_detail;
    TextView title_ticket, location_ticket,
            photo_spot_ticket, wifi_available,
            festival_ticket, ticket_desc;
    Button btn_buy_ticket, btn_back;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        header_ticket_detail = findViewById(R.id.header_ticket_detail);
        title_ticket = findViewById(R.id.title_ticket);
        location_ticket = findViewById(R.id.location_ticket);
        photo_spot_ticket = findViewById(R.id.photo_spot_ticket);
        wifi_available = findViewById(R.id.wifi_available);
        festival_ticket = findViewById(R.id.festival_ticket);
        ticket_desc = findViewById(R.id.ticket_desc);
        btn_buy_ticket = findViewById(R.id.btn_buy_ticket);
        btn_back = findViewById(R.id.btn_back);

        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString("jenis_tiket");

        Toast.makeText(getApplicationContext(), "Jenis Tiket "+ jenis_tiket_baru, Toast.LENGTH_SHORT).show();

        //mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_tiket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //menimpa data yang ada dengan data yang baru
                title_ticket.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                location_ticket.setText(dataSnapshot.child("lokasi").getValue().toString());
                photo_spot_ticket.setText(dataSnapshot.child("is_photo_spot").getValue().toString());
                wifi_available.setText(dataSnapshot.child("is_wifi").getValue().toString());
                festival_ticket.setText(dataSnapshot.child("is_festival").getValue().toString());
                ticket_desc.setText(dataSnapshot.child("short_desc").getValue().toString());
                Picasso.with(TicketDetailAct.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit()
                        .into(header_ticket_detail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_buy_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(TicketDetailAct.this, TicketCheckoutAct.class);
                gotocheckout.putExtra("jenis_tiket", jenis_tiket_baru);
                startActivity(gotocheckout);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackhome = new Intent(TicketDetailAct.this, HomeAct.class);
                startActivity(gobackhome);
            }
        });
    }
}
