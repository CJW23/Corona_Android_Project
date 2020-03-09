package com.example.corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Retrofit retrofit;
    JsonApi jsonApi;
    Call<List<PatientData>> call;
    Fragment totalFragment, patientFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBottomNavigation();
    }

    private void onBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        totalFragment = new TotalFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, totalFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.total:
                        if(totalFragment == null) {
                            totalFragment = new TotalFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, totalFragment).commit();
                        }
                        if(patientFragment != null) getSupportFragmentManager().beginTransaction().hide(patientFragment).commit();
                        getSupportFragmentManager().beginTransaction().show(totalFragment).commit();
                        break;
                    case R.id.patientData:
                        if(patientFragment == null) {
                            patientFragment = new PatientFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, patientFragment).commit();
                        }
                        getSupportFragmentManager().beginTransaction().show(patientFragment).commit();
                        if(totalFragment != null) getSupportFragmentManager().beginTransaction().hide(totalFragment).commit();
                        break;
                }
                return true;
            }
        });
    }
}
