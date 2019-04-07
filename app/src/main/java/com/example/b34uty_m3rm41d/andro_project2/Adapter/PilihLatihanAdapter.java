package com.example.b34uty_m3rm41d.andro_project2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b34uty_m3rm41d.andro_project2.HomeActivity;
import com.example.b34uty_m3rm41d.andro_project2.MainActivity;
import com.example.b34uty_m3rm41d.andro_project2.Model.Latihan;
import com.example.b34uty_m3rm41d.andro_project2.R;
import com.example.b34uty_m3rm41d.andro_project2.pilihLatihanActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PilihLatihanAdapter extends RecyclerView.Adapter<PilihLatihanAdapter.MyViewHolder> {
    private List<Latihan> LatihanList;
    private Activity mActivity;

    private DatabaseReference database;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rl_layout;
        public TextView asalLatihan, pilihLatihan;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl_layout);
            asalLatihan = view.findViewById(R.id.userItemAsal);
            pilihLatihan = view.findViewById(R.id.userItemke);
        }
    }

    public PilihLatihanAdapter(List<Latihan> LatihanList, Activity activity) {
        this.LatihanList = LatihanList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Latihan latihan = LatihanList.get(position);

        holder.asalLatihan.setText(latihan.getaLatihan());
        holder.pilihLatihan.setText(latihan.getpLatihan());

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latihan.setPilih("yes");
                database = FirebaseDatabase.getInstance().getReference();
                database.child("pilih_Latihan").child(latihan.getKey()).child("pilih")
                        .setValue(latihan.getPilih());
                database.child("Users").child(latihan.getId()).child("pilih")
                        .setValue(latihan.getpLatihan());
                mActivity.startActivity(new Intent(mActivity, MainActivity.class));

            }
        });

    }



    @Override
    public int getItemCount() {
        return LatihanList.size();
    }
}
