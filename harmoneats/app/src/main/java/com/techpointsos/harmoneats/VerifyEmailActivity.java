package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmailActivity extends AppCompatActivity {

    private Button sendVerificationButton, checkVerifyButton;
    private FirebaseAuth mAuth;
    private Intent verifyIntent;
    private String email;
    private FirebaseUser user;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        verifyIntent = getIntent();
        mAuth = FirebaseAuth.getInstance();
        checkVerifyButton = findViewById(R.id.checkVerifiedButton);
        sendVerificationButton = findViewById(R.id.sendVerificationButton);
        user = mAuth.getCurrentUser();
        setCheckVerifyButton(checkVerifyButton);
        setSendVerifyButton(sendVerificationButton);
    }

    private void setSendVerifyButton(Button sendVerificationButton){
        sendVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    user.sendEmailVerification();
                }
            }
        });
    }

    private void setCheckVerifyButton(Button checkVerifyButton){
        checkVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null) {
                    if (user.isEmailVerified()) {
                        Toast.makeText(VerifyEmailActivity.this, "User is verified.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(VerifyEmailActivity.this, "Click Send Verification to verify email.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
