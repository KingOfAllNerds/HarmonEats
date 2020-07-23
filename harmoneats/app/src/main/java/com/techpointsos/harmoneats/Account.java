package com.techpointsos.harmoneats;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Account extends Fragment implements RecyclerViewClickInterface{

    private TextView nameAccountTextView, emailAccountTextView, passwordReset;
    private ImageView accountAvatar;
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
        passwordReset = view.findViewById(R.id.accountPasswordResetTextView);
        accountAvatar = (ImageView) view.findViewById(R.id.accountImageView);

        if(user != null) {      //If we can't get a user, we need to have them login so we can access user info
            nameAccountTextView.setText(user.getPhoneNumber());
            emailAccountTextView.setText(user.getEmail());
            accountAvatar.setImageResource(R.drawable.stock_avatar);
        }
        else{
            Toast.makeText(myView.getContext(), "Sorry, you need to log in first.", Toast.LENGTH_LONG).show();
            Intent needToLoginIntent = new Intent(myView.getContext(), Login.class);
            startActivity(needToLoginIntent);
        }

        setResetPasswordListener(passwordReset);
    }

    private void setResetPasswordListener(TextView passwordReset){
        passwordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetPasswordIntent = new Intent(myView.getContext(), ResetPassword.class);
                startActivity(resetPasswordIntent);
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