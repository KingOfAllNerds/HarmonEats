package com.techpointsos.harmoneats;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AboutUSDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About Us")
                .setMessage("HarmonEats is a state of the art food delivery app that is set to benefit " +
                        "all known stakeholders i.e. the Customers, Drivers and the Restaurants during these testing times. " +
                        "Applications such as GrubHub and UberEats take a hefty amount of revenue from these restaurants leaving them " +
                        "little room to make profits. Thanks to our very affordable and rewarding subscription based model, you can be " +
                        "sure that everyone prospers equally. Stay tuned for more updates.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
