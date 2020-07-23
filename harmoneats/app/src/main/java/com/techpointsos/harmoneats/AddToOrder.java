package com.techpointsos.harmoneats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.math.BigDecimal;

public class AddToOrder extends Fragment {

    private String itemName;
    private String itemDescription;
    private String restaurantName;
    private BigDecimal itemPrice;
    private int itemCount;
    private TextView itemCountBox, itemDescriptionBox, itemNameBox;
    private EditText specialRequests;
    OnItemAddedToOrderListener callback;

    public AddToOrder(String itemName, String itemDescription, BigDecimal itemPrice, String restaurantName) {
        this.itemName = itemName;
        this.restaurantName = restaurantName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCount = 1;
    }


    public void setOnItemAddedToOrderListener(OnItemAddedToOrderListener callback) {
        this.callback = callback;
    }

    public interface OnItemAddedToOrderListener {
        void onItemAdded(BigDecimal currentPrice, int itemCount, String itemName, String specialRequests, String restaurantName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_to_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemNameBox = view.findViewById(R.id.item_name);
        itemDescriptionBox = view.findViewById(R.id.item_description);
        itemCountBox = view.findViewById(R.id.item_count);
        specialRequests = view.findViewById(R.id.special_requests);

        itemNameBox.setText(itemName);
        itemDescriptionBox.setText(itemDescription);
        itemCountBox.setText(String.valueOf(itemCount));

        final Button addToOrderButton = view.findViewById(R.id.add_to_order_button);
        addToOrderButton.setText("Add 1 to Order - $"+itemPrice);

        FloatingActionButton addItem = view.findViewById(R.id.add_item);
        FloatingActionButton removeItem = view.findViewById(R.id.remove_item);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCount++;
                itemCountBox.setText(String.valueOf(itemCount));
                addToOrderButton.setText("Add " + itemCount + " to Order - $" + itemPrice.multiply(new BigDecimal(itemCount)));
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemCount > 1) {
                    itemCount--;
                    itemCountBox.setText(String.valueOf(itemCount));
                    addToOrderButton.setText("Add " + itemCount + " to Order - $" + itemPrice.multiply(new BigDecimal(itemCount)));
                }
            }
        });

        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemAdded(itemPrice,itemCount,itemName,specialRequests.getText().toString(),restaurantName);
            }
        });
    }
}