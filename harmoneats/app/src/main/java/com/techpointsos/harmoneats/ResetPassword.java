package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private Class classToReturnTo;
    private EditText resetEmail;
    private FirebaseAuth fAuth;
    private Button resetButton;

    public ResetPassword(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        fAuth = FirebaseAuth.getInstance();
        resetButton = findViewById(R.id.resetConfirmButton);
        resetEmail = findViewById(R.id.resetEmailEditText);

        setResetButton(resetButton);
    }

    private void setResetButton(Button resetButton){
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailToSendLink = resetEmail.getText().toString();
                fAuth.sendPasswordResetEmail(emailToSendLink).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ResetPassword.this, "A reset link has been sent to your email", Toast.LENGTH_LONG).show();
                        Intent backToLoginIntent = new Intent(getApplicationContext(), Login.class);
                        startActivity(backToLoginIntent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPassword.this, "Error!  A reset link could not be sent.  Here is the error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
