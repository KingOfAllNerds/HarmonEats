package com.techpointsos.harmoneats;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Account extends Fragment implements RecyclerViewClickInterface{

    private TextView nameAccountTextView, emailAccountTextView;
    private EditText emailResetEditText;
    private Button resetPasswordButton, sendResetLinkButton;
    private FirebaseUser user;
    private FirebaseAuth fAuth;
    private View myView;
    private Context myContext;

    public Account(FirebaseUser user, Context context) {
        this.user = user;
        myContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        myView = view;

        fAuth = FirebaseAuth.getInstance();
        nameAccountTextView = view.findViewById(R.id.nameAccountTextView);
        emailAccountTextView = view.findViewById(R.id.emailAccountTextView);
        resetPasswordButton = (Button)view.findViewById(R.id.resetPasswordButton);
        emailResetEditText = (EditText)view.findViewById(R.id.emailResetEditText);
        sendResetLinkButton = (Button)view.findViewById(R.id.sendResetLinkButton);

        nameAccountTextView.setText(user.getDisplayName());
        emailAccountTextView.setText(user.getEmail());

        setForgotPasswordListener(resetPasswordButton);
    }

    private void setForgotPasswordListener(Button resetPasswordButton){
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailResetEditText.setVisibility(View.VISIBLE);
                sendResetLinkButton.setVisibility(View.VISIBLE);

                setSendResetLinkButton(sendResetLinkButton);
            }
        });
    }

    private void setSendResetLinkButton(Button sendResetLinkButton){
        sendResetLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailResetEditText.getText().toString();
                fAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(myContext, "You have been sent a password reset link.", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(myContext, "Error!  Here is the exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}