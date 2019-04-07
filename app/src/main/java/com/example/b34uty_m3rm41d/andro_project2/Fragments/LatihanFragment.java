package com.example.b34uty_m3rm41d.andro_project2.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.b34uty_m3rm41d.andro_project2.LoginActivity;
import com.example.b34uty_m3rm41d.andro_project2.Model.Latihan;
import com.example.b34uty_m3rm41d.andro_project2.R;
import com.example.b34uty_m3rm41d.andro_project2.SoalActivity;


public class LatihanFragment extends Fragment {

    private Button btn1, btn2, btn3, btn4;
    private RecyclerView rv;

    private String lpLatihan  ;
    private int d;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
/*        if(getArguments().getString("BUNDLE") == null){
            //lpLatihan = getArguments().getString("BUNDLE");
        }
        if(getArguments().getString("BUNDLE") == null){
            //lpLatihan = getArguments().getString("BUNDLE");
        }
*/
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_latihan, container, false);

        //
        btn1 = view.findViewById(R.id.b);
        btn2 = view.findViewById(R.id.a);
        btn3 = view.findViewById(R.id.c);
        btn4 = view.findViewById(R.id.d);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SoalActivity.class);
                intent.putExtra("nilai", "s1");
                //intent.putExtra("lLatihan", "lpLatihan");
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SoalActivity.class);
                intent.putExtra("nilai", "s2");
               // intent.putExtra("lLatihan", "lpLatihan");
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SoalActivity.class);
                intent.putExtra("nilai", "s1");
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SoalActivity.class);
                intent.putExtra("nilai", "s1");
                startActivity(intent);
            }
        });

        return view;
    }



}
