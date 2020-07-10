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
    private FirebaseUser user;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        mAuth = FirebaseAuth.getInstance();
        checkVerifyButton = findViewById(R.id.checkVerifiedButton);
        sendVerificationButton = findViewById(R.id.sendVerificationButton);

        user = mAuth.getCurrentUser();      //Get necessary current user information.

        setCheckVerifyButton(checkVerifyButton);
        setSendVerifyButton(sendVerificationButton);
    }
    /*
        Set the "Send Verification" button to send a new verification email to the user.
     */
    private void setSendVerifyButton(Button sendVerificationButton){
        sendVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){   //Avoid Null Pionter Exceptions
                    user.sendEmailVerification();   //Send the user a new verification email.
                }
            }
        });
    }
    /*
        Set the "Check Verification" button to let the user know if they are verified or not.
     */
    private void setCheckVerifyButton(Button checkVerifyButton){
        checkVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null) {  //Avoid Null Pointer Exceptions
                    if (user.isEmailVerified()) {   //Check if the email address is verified.
                        Toast.makeText(VerifyEmailActivity.this, "User is verified.", Toast.LENGTH_LONG).show();
                    } else {    //Instruct user to send a new verification email if they are not verified.
                        Toast.makeText(VerifyEmailActivity.this, "Click Send Verification to verify email.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
