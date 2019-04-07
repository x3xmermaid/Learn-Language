package com.example.b34uty_m3rm41d.andro_project2;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.b34uty_m3rm41d.andro_project2.Adapter.UserAdapter;
import com.example.b34uty_m3rm41d.andro_project2.Model.Latihan;
import com.example.b34uty_m3rm41d.andro_project2.Model.Soal;
import com.example.b34uty_m3rm41d.andro_project2.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SoalActivity extends AppCompatActivity {

    private TextView tanya, jSoal1, jSoal2, jSoal3, jSoal4, secret;
    private ImageButton jGambar1, jGambar2, jGambar3, jGambar4;
    private Button cJawab;


    CircleImageView mainCircleImage;
    TextView mainUsername;

    FirebaseUser firebaseUser;
    DatabaseReference reference, database;

    private ArrayList<User> fUser;

    private int sIndex = 0, textSoal;
    private String gLatihan  ;
   // private String jLatihan = getIntent().getStringExtra("lLatihan");
    private int kJawab = 0;
    private int gJawab= 0;
    private int gamJawab= 0;
    int n= 1;
    private String pilih;


    private Soal[] Soal = new Soal[]{
        new Soal(R.string.inggris1, 1),
        new Soal(R.string.inggris2, 2),
        new Soal(R.string.inggris3, 3),
        new Soal(R.string.inggris4, 4),
        new Soal(R.string.inggris5, 1),
        new Soal(R.string.inggris6, 1),
        new Soal(R.string.inggris7, 2),
        new Soal(R.string.inggris8, 3),
        new Soal(R.string.inggris9, 4),
        new Soal(R.string.inggris10, 1),
        new Soal(R.string.spain1, 2),
        new Soal(R.string.spain2, 3),
        new Soal(R.string.spain3, 4),
        new Soal(R.string.spain4, 1),
        new Soal(R.string.spain5, 2),
        new Soal(R.string.spain6, 3),
        new Soal(R.string.spain7, 4),
        new Soal(R.string.spain8, 1),
        new Soal(R.string.spain9, 2),
        new Soal(R.string.spain10, 3)
    };


    MediaPlayer mp;
    String[] tJawab;
    TypedArray Gambar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SoalActivity.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainCircleImage = findViewById(R.id.mainProfImg);
        mainUsername = findViewById(R.id.mainUser);
        secret = findViewById(R.id.rahasia);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoalActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });



        tanya = findViewById(R.id.question_text_view);

        textSoal = Soal[sIndex].getTfSoal();
        tJawab = getResources().getStringArray(R.array.jawaban);

        Gambar = getResources().obtainTypedArray(R.array.Gambar);
        final TypedArray Suara = getResources().obtainTypedArray(R.array.suara);

        jSoal1 = findViewById(R.id.j1);
        //jSoal1.setText(tJawab[gJawab]);
        jSoal2 = findViewById(R.id.j2);
        //jSoal2.setText(tJawab[gJawab+1]);
        jSoal3 = findViewById(R.id.j3);
       // jSoal3.setText(tJawab[gJawab+2]);
        jSoal4 = findViewById(R.id.j4);
        //jSoal4.setText(tJawab[gJawab+3]);
        cJawab = findViewById(R.id.next_button);
        jGambar1 = findViewById(R.id.g1);
        //jGambar1.setImageResource(Gambar.getResourceId(gJawab, -1));
        jGambar2 = findViewById(R.id.g2);
       // jGambar2.setImageResource(Gambar.getResourceId(gJawab+1, -1));
        jGambar3 = findViewById(R.id.g3);
        //jGambar3.setImageResource(Gambar.getResourceId(gJawab+2, -1));
        jGambar4 = findViewById(R.id.g4);
       // jGambar4.setImageResource(Gambar.getResourceId(gJawab+3, -1));

       // gLatihan = gLatihan + " a";


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                User user = dataSnapshot.getValue(User.class);
                mainUsername.setText(user.getUsername()+" - "+user.getPilih());

                //fUser.add(user);

                if(user.getImageUrl().equals("default")){
                    mainCircleImage.setImageResource(R.mipmap.ic_launcher);

                }else{
                    Glide.with(getApplicationContext()).load(user.getImageUrl()).into(mainCircleImage);

                }
                gLatihan =getIntent().getStringExtra("nilai");
                if(user.getPilih().equals("inggris")){
                    if(gLatihan.equals("s1")){
                        sIndex = 0;
                        gJawab = 0;
                        gamJawab = 0;
                    }else {
                        sIndex = 5;
                        gJawab = 20;
                        gamJawab = 20;
                    }

                }else if(user.getPilih().equals("spanyol")){
                    if(gLatihan.equals("s1")){
                        sIndex = 10;
                        gJawab = 40;
                        gamJawab = 20;
                    }else {
                        sIndex = 15;
                        gJawab = 60;
                        gamJawab = 0;
                    }
                }
                keluarSoal(sIndex, gJawab, gamJawab);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //if(pilih.equals("inggris")){

       // }else if(pilih.equals("spanyol")){
       //     textSoal = sSoal[sIndex].getTfSoal();
       //     tJawab = getResources().getStringArray(R.array.jSpanyol);
       // }


        jGambar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kJawab=1;
                mp = MediaPlayer.create(SoalActivity.this, Suara.getResourceId(gJawab, -1));
                mp.start();

            }
        });
        jGambar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kJawab=2;
                mp = MediaPlayer.create(SoalActivity.this, Suara.getResourceId(gJawab+1, -1));
                mp.start();

            }
        });
        jGambar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kJawab=3;
                mp = MediaPlayer.create(SoalActivity.this, Suara.getResourceId(gJawab+2, -1));
                mp.start();

            }
        });
        jGambar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kJawab=4;
                mp = MediaPlayer.create(SoalActivity.this, Suara.getResourceId(gJawab+3, -1));
                mp.start();

            }
        });

        cJawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(kJawab);
            }
        });

    }

    private void checkAnswer(int userPressed) {
        int answer = 0;
        int soal = 0;


            answer = Soal[sIndex].getTfJawab();

        if(userPressed == answer){
            Toast.makeText(SoalActivity.this, "Benar", Toast.LENGTH_SHORT).show();
            sIndex = (sIndex + 1) % Soal.length;

            //if(pilih.equals("inggris")){
                soal = Soal[sIndex].getTfSoal();
            //}else if(pilih.equals("spanyol")){
            //    soal = sSoal[sIndex].getTfSoal();
            //}

            n+=1;
            if(n == 6){
                startActivity(new Intent(SoalActivity.this, MainActivity.class));
                Toast.makeText(SoalActivity.this, "Latihan Anda Selesai", Toast.LENGTH_SHORT).show();
            }else{
                tanya.setText(soal);
                gJawab+=4;
                gamJawab+=4;
                jGambar1.setImageResource(Gambar.getResourceId(gamJawab, -1));
                jGambar2.setImageResource(Gambar.getResourceId(gamJawab+1, -1));
                jGambar3.setImageResource(Gambar.getResourceId(gamJawab+2, -1));
                jGambar4.setImageResource(Gambar.getResourceId(gamJawab+3, -1));
                jSoal1.setText(tJawab[gJawab]);
                jSoal2.setText(tJawab[gJawab+1]);
                jSoal3.setText(tJawab[gJawab+2]);
                jSoal4.setText(tJawab[gJawab+3]);
            }




        }else{
            Toast.makeText(SoalActivity.this, "Salah", Toast.LENGTH_SHORT).show();
        }
    }

    private void keluarSoal(int soalIndex,int jawabIndex, int gambarIndex ){
        int KSSOAL;
        sIndex = soalIndex;
        gJawab = jawabIndex;
        gamJawab = gambarIndex;
        KSSOAL = Soal[sIndex].getTfSoal();

        tanya.setText(KSSOAL);
        jGambar1.setImageResource(Gambar.getResourceId(gamJawab, -1));
        jGambar2.setImageResource(Gambar.getResourceId(gamJawab+1, -1));
        jGambar3.setImageResource(Gambar.getResourceId(gamJawab+2, -1));
        jGambar4.setImageResource(Gambar.getResourceId(gamJawab+3, -1));
        jSoal1.setText(tJawab[jawabIndex]);
        jSoal2.setText(tJawab[jawabIndex+1]);
        jSoal3.setText(tJawab[jawabIndex+2]);
        jSoal4.setText(tJawab[jawabIndex+3]);
    }

}
