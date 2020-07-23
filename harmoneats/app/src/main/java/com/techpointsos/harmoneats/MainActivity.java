package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        AddToOrder.OnItemAddedToOrderListener, Checkout.OnOrderCompleteListener {

    private BottomNavigationView bottomNavigationView;
    private List<HashMap<String,Object>> orderItems = new ArrayList<>();
    private FirebaseAuth mAuth;
    private Account accountFragment;

    Search searchFragment = new Search();
    Order orderFragment = new Order(orderItems);
    Recommended recommendedFragment = new Recommended();
    Featured featuredFragment = new Featured();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.fragment_featured);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        accountFragment = new Account(mAuth.getCurrentUser(),getApplicationContext());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, featuredFragment).commit();
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof AddToOrder) {
            AddToOrder addToOrderFragment = (AddToOrder) fragment;
            addToOrderFragment.setOnItemAddedToOrderListener(this);
        }
        if(fragment instanceof Checkout) {
            Checkout checkoutFragment = (Checkout) fragment;
            checkoutFragment.setOnOrderCompleteListener(this);
        }
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch (item.getItemId()) {
            case R.id.fragment_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, searchFragment).commit();
                return true;
            case R.id.fragment_recommended:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendedFragment).commit();
                return true;
            case R.id.fragment_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, accountFragment).commit();
                return true;
            case R.id.fragment_featured:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, featuredFragment).commit();
                return true;
            case R.id.fragment_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, orderFragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                Toast.makeText(this.getApplicationContext(),"Oops! Feature not yet implemented",Toast.LENGTH_LONG).show();
                return true;
            case R.id.logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemAdded(BigDecimal currentPrice, int itemCount, String itemName, String specialRequests, String restaurantName) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("price",currentPrice);
        map.put("count",itemCount);
        map.put("name",itemName);
        map.put("requests",specialRequests);
        map.put("restaurant",restaurantName);

        if(orderItems.size() < 1) {
            orderItems.add(map);
            orderFragment = new Order(orderItems);
        } else {
            if(map.get("restaurant").toString().equals(orderItems.get(0).get("restaurant").toString())) {
                boolean added = false;
                for(int i=0;i<orderItems.size();i++) {
                    HashMap<String,Object> item = orderItems.get(0);
                    if(item.equals(map)) {
                        int newQuantity = (int) item.get("count") + (int) map.get("count");
                        map.put("count",newQuantity);
                        orderItems.remove(item);
                        orderItems.add(map);
                        orderFragment = new Order(orderItems);
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    orderItems.add(map);
                    orderFragment = new Order(orderItems);
                }
            } else {
                Toast.makeText(this.getApplicationContext(),"Only order from one place at a time!",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onOrderComplete() {
        orderFragment = new Order(new ArrayList<HashMap<String,Object>>());
    }
}