package com.techpointsos.harmoneats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigDecimal;

public class Checkout extends Fragment {

    Button confirmPayment;
    Checkout.OnOrderCompleteListener callback;

    public Checkout() {
        // Required empty public constructor
    }

    public void setOnOrderCompleteListener(Checkout.OnOrderCompleteListener callback) {
        this.callback = callback;
    }

    public interface OnOrderCompleteListener {
        void onOrderComplete();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmPayment = view.findViewById(R.id.confirm_payment);

        confirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Order has been placed!",Toast.LENGTH_LONG).show();
                callback.onOrderComplete();
            }
        });
    }
}