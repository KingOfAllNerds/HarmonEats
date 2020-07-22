package com.techpointsos.harmoneats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        accountFragment = new Account(mAuth.getCurrentUser(), getApplicationContext());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, featuredFragment).commit();

        FirebaseUser checkUserNull = mAuth.getCurrentUser(); //Check if we have a proper user before allowing access.
        if(checkUserNull == null){
            Toast.makeText(getApplicationContext(), "Sorry, you need to log in first.", Toast.LENGTH_LONG).show();
            Intent needToLoginIntent = new Intent(getApplicationContext(), Login.class);
            startActivity(needToLoginIntent);
        }
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
                return true;
            case R.id.logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemAdded(BigDecimal currentPrice, int itemCount, String itemName) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("price",currentPrice);
        map.put("count",itemCount);
        map.put("name",itemName);
        orderItems.add(map);
        orderFragment = new Order(orderItems);
    }

    @Override
    public void onOrderComplete() {
        orderFragment = new Order(new ArrayList<HashMap<String,Object>>());
    }
}