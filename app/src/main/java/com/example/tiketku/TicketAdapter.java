package com.example.tiketku;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MyTicket> data;

    public TicketAdapter(Context context, ArrayList<MyTicket> data) {
        this.context = context;
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xnama_wisata, xlokasi, xjumlah_tiket;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xnama_wisata = itemView.findViewById(R.id.xnama_wisata);
            xlokasi = itemView.findViewById(R.id.xlokasi);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_tiket);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myticket, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.xnama_wisata.setText(data.get(i).getNama_wisata());
        viewHolder.xlokasi.setText(data.get(i).getLokasi());
        viewHolder.xjumlah_tiket.setText(data.get(i).getJumlah_tiket()+" Tickets");

        final String getNamaWisata = data.get(i).getNama_wisata();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyticketdetails = new Intent(context, ProfilTicketDetailAct.class);
                gotomyticketdetails.putExtra("nama_wisata", getNamaWisata);
                context.startActivity(gotomyticketdetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
