package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    private String phoneNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    private String codeSent;
    private Button verifyButton, getCodeButton;
    private EditText phoneAuthPlainText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        phoneAuthPlainText = findViewById(R.id.phoneAuthPlainText);
        getCodeButton = findViewById(R.id.getCodeButton);
        phoneNumber = "6363752544";
        //phoneNumber = getIntent().getStringExtra("phone number");
        mAuth = FirebaseAuth.getInstance();
        verifyButton = findViewById(R.id.verifyButton);

        setGetCodeClickListener(getCodeButton);
        setVerifyClickListener(verifyButton);
        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                codeSent = s;
            }
        };
    }

    private void setVerifyClickListener(Button verifyButton) {
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });
    }

    private void verifySignInCode(){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, phoneAuthPlainText.getText().toString());
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent successIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(successIntent);
                        }else{
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(getApplicationContext(), "Incorrect Verification Code", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

    private void setGetCodeClickListener(Button getCodeButton){
        getCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });
    }

    private void sendVerificationCode(){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallBack
        );
    }

}
