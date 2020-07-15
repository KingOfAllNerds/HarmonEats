package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmailActivity extends AppCompatActivity {

    private Button sendVerificationButton, checkVerifyButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private static final String TAG = "VerifyEmailActivity";
    private Handler handler;
    private Runnable runnableCode;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        handler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        checkVerifyButton = findViewById(R.id.checkVerifiedButton);
        sendVerificationButton = findViewById(R.id.sendVerificationButton);

        user = mAuth.getCurrentUser();   //Get necessary current user information.

        sendVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){   //Avoid Null Pionter Exceptions
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(VerifyEmailActivity.this, "Verification email sent", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Failure: "+ e.toString());
                        }
                    });   //Send the user a new verification email.
                }
            }
        });
        runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                Log.d(TAG, "Waiting for Verification");
                user.reload();
                if(user.isEmailVerified()){
                    Log.d(TAG, "Success: Email verified");
                    handler.removeCallbacks(runnableCode);
                    Toast.makeText(VerifyEmailActivity.this, "User is verified.", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return;
                }
                handler.postDelayed(this, 1000);
            }
        };
        //handler.post(runnableCode);
        runnableCode.run();


        //setCheckVerifyButton(checkVerifyButton);
        //setSendVerifyButton(sendVerificationButton);
        checkVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null) {  //Avoid Null Pointer Exceptions
                    user.reload();
                    if (user.isEmailVerified()) {   //Check if the email address is verified.
                        Toast.makeText(VerifyEmailActivity.this, "User is verified.", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {    //Instruct user to send a new verification email if they are not verified.
                        Toast.makeText(VerifyEmailActivity.this, "User is not verified.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    /*
        Set the "Send Verification" button to send a new verification email to the user.
     */
    private void setSendVerifyButton(Button sendVerificationButton){

    }
    /*
        Set the "Check Verification" button to let the user know if they are verified or not.
     */
    private void setCheckVerifyButton(Button checkVerifyButton){

    }
}
