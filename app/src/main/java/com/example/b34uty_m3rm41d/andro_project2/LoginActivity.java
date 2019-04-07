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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText logEmail, logPassword;
    Button logButton;

    ProgressBar logProgress;

    FirebaseAuth auth;
    TextView forgot_Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        logEmail = findViewById(R.id.logMail);
        logPassword = findViewById(R.id.logPass);
        logButton = findViewById(R.id.butLog);
        logProgress = findViewById(R.id.logProg);
        forgot_Pass = findViewById(R.id.forgot_Pass);


        logProgress.setVisibility(View.INVISIBLE);

        forgot_Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logButton.setVisibility(View.INVISIBLE);
                logProgress.setVisibility(View.VISIBLE);
                String txt_mail = logEmail.getText().toString();
                String txt_pass = logPassword.getText().toString();

                if(TextUtils.isEmpty(txt_mail) || TextUtils.isEmpty(txt_pass)){
                    showMessage("Semua kolom wajib di isi");
                    logButton.setVisibility(View.VISIBLE);
                    logProgress.setVisibility(View.INVISIBLE);

                }else{
                    auth.signInWithEmailAndPassword(txt_mail, txt_pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        showMessage("Authentikasi gagal");
                                        logButton.setVisibility(View.VISIBLE);
                                        logProgress.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                }
            }
        });
    }

    private void showMessage(String semua_kolom_wajib_di_isi) {
        Toast.makeText(LoginActivity.this, semua_kolom_wajib_di_isi, Toast.LENGTH_SHORT).show();
    }
}
