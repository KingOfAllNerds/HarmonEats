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

    private TextView verifiedTextView;
    private Button sendVerificationButton, checkVerifyButton;
    private FirebaseAuth mAuth;
    private Intent verifyIntent;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        verifyIntent = getIntent();
        mAuth = FirebaseAuth.getInstance();
        verifiedTextView = findViewById(R.id.verifiedTextView);
        checkVerifyButton = findViewById(R.id.checkVerifiedButton);
        sendVerificationButton = findViewById(R.id.sendVerificationButton);

        loadUserInformation();
    }
    private void loadUserInformation(){

        final FirebaseUser user = mAuth.getCurrentUser();

        checkVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    if (user.isEmailVerified()) {
                        verifiedTextView.setText("Email Verified");
                    } else {
                        verifiedTextView.setText("Email Not Verified. Press Send Verification Button, please.");
                    }
                }
            }
        });

        sendVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    }
                });
            }
        });
    }
}
