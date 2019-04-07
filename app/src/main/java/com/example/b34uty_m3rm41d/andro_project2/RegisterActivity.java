package com.example.b34uty_m3rm41d.andro_project2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText regUsername, regEmail, regPassword;
    Button regButton;
    ProgressBar regProgress;

    FirebaseAuth regAuth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        regUsername = findViewById(R.id.regUser);
        regEmail = findViewById(R.id.regMail);
        regPassword = findViewById(R.id.regPass);
        regButton = findViewById(R.id.butReg);
        regProgress = findViewById(R.id.regProg);

        regAuth = FirebaseAuth.getInstance();

        regProgress.setVisibility(View.INVISIBLE);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regButton.setVisibility(View.INVISIBLE);
                regProgress.setVisibility(View.VISIBLE);
                String txt_user = regUsername.getText().toString();
                String txt_mail = regEmail.getText().toString();
                String txt_pass = regPassword.getText().toString();

                if(TextUtils.isEmpty(txt_user) || TextUtils.isEmpty(txt_mail) || TextUtils.isEmpty(txt_pass)){
                    showMessage("Isi Semua Kolom Register");
                    regButton.setVisibility(View.VISIBLE);
                    regProgress.setVisibility(View.INVISIBLE);
                }else if(txt_pass.length() < 6){
                    showMessage("Password tidak boleh kurang dari 6");
                    regButton.setVisibility(View.VISIBLE);
                    regProgress.setVisibility(View.INVISIBLE);

                }else{
                    register(txt_user, txt_mail, txt_pass);
                }
            }
        });
    }

    private void register(final String username, String email, String password){
        regAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //showMessage("Berhasil");
                            FirebaseUser firebaseUser = regAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userId = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("username", username);
                            hashMap.put("imageUrl", "default");
                            hashMap.put("status", "offline");
                            hashMap.put("pilih", "default");
                            hashMap.put("search", username.toLowerCase());

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            showMessage("Anda tidak bisa registrasi dengan email dan password ini");
                            regButton.setVisibility(View.VISIBLE);
                            regProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
