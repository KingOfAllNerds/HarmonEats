package com.techpointsos.harmoneats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;


public class Account extends Fragment implements RecyclerViewClickInterface{

    private TextView nameAccountTextView, emailAccountTextView;
    private FirebaseUser user;

    public Account(FirebaseUser user) {
        this.user = user;
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

        nameAccountTextView = view.findViewById(R.id.nameAccountTextView);
        emailAccountTextView = view.findViewById(R.id.emailAccountTextView);

        nameAccountTextView.setText(user.getDisplayName());
        emailAccountTextView.setText(user.getEmail());
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}