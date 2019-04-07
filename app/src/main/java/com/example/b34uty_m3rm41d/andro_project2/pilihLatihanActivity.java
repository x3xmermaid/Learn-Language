package com.example.b34uty_m3rm41d.andro_project2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.b34uty_m3rm41d.andro_project2.Fragments.LatihanFragment;
import com.example.b34uty_m3rm41d.andro_project2.Model.Latihan;
import com.example.b34uty_m3rm41d.andro_project2.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class pilihLatihanActivity extends AppCompatActivity {

    CircleImageView mainCircleImage;
    TextView mainUsername;

    FirebaseUser firebaseUser;
    DatabaseReference reference, database;

    Spinner aSpinner, lSpinner;
    ImageButton imgButton;
    private String aLatihan, pLatihan;

    Bundle bundle = new Bundle();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(pilihLatihanActivity.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_latihan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        mainCircleImage = findViewById(R.id.mainProfImg);
        mainUsername = findViewById(R.id.mainUser);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        database = FirebaseDatabase.getInstance().getReference();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                mainUsername.setText(user.getUsername());
                if(user.getImageUrl().equals("default")){
                    mainCircleImage.setImageResource(R.mipmap.ic_launcher);

                }else{
                    Glide.with(getApplicationContext()).load(user.getImageUrl()).into(mainCircleImage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        aSpinner  =findViewById(R.id.asalPilih);
        lSpinner  =findViewById(R.id.pelajariPilih);
        imgButton = findViewById(R.id.btnPlus);


        final LatihanFragment lf = new LatihanFragment();
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(pilihLatihanActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                jLatihan = lSpinner.getSelectedItem().toString();
                bundle.putString("BUNDLE", jLatihan);
                intent.putExtra("jLatihan", jLatihan);
                lf.setArguments(bundle);
                startActivity(intent);
                finish();*/
                pLatihan = lSpinner.getSelectedItem().toString();
                aLatihan = aSpinner.getSelectedItem().toString();
                submitData(new Latihan(aLatihan, pLatihan,firebaseUser.getUid(), "no"));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void submitData(Latihan latihan) {
        database.child("pilih_Latihan")


                .push()
                .setValue(latihan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        //Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(pilihLatihanActivity.this, HomeActivity.class));

                    }
                });
    }
}
