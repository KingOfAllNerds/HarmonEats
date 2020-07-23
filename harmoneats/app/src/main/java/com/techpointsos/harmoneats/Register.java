package com.techpointsos.harmoneats;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Register extends AppCompatActivity {

    private EditText uname, uemail, upass1, upass2, uphone;
    private Button regButton;
    private TextView mLoginButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private VerifyEmailActivity mVerify;
    private String phoneNumber, userID, name, email, phone;
    private static final String TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uname = findViewById(R.id.uname);
        uemail = findViewById(R.id.email);
        upass1 = findViewById(R.id.pass1);
        upass2 = findViewById(R.id.pass2);
        uphone = findViewById((R.id.phone));
        regButton = findViewById((R.id.register));
        mLoginButton = findViewById(R.id.glogin);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = uname.getText().toString().trim();
                email = uemail.getText().toString().trim();
                String pass1 = upass1.getText().toString().trim();
                String pass2  = upass2.getText().toString().trim();
                phone = uphone.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    uname.setError("Username is required");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    uemail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(pass1)){
                    upass1.setError("Password is required");
                    return;
                }

                if(TextUtils.isEmpty(pass2)){
                    upass2.setError("Confirm Password is required");
                    return;
                }

                if(!pass1.equals(pass2)){
                    Toast.makeText(Register.this, "Passwords need to match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    uphone.setError("Phone number is required");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("uname", name);
                            user.put("email", email);
                            user.put("phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Success: User Profile created for "+name+" "+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Failure: "+ e.toString());
                                }
                            });
                            Intent verifyIntent = new Intent(getApplicationContext(), VerifyEmailActivity.class);
                            startActivity(verifyIntent);
                        }
                        else {
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}